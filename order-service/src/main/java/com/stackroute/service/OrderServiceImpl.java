package com.stackroute.service;


import com.stackroute.exception.OrderNotFoundException;
import com.stackroute.model.NewCartDto;
import com.stackroute.model.Order;
import com.stackroute.model.PaymentDto;
import com.stackroute.repository.NewCartRepository;
import com.stackroute.repository.OrderRepository;
import com.stackroute.repository.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private NewCartRepository cartRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    public String getSequenceNumber(String seqName){
//        Query qry=new Query(Criteria.where("id").is(seqName));
//        Update update=new Update().inc("seq",1);
//        DbSequence counter = mongoOperations
//                .findAndModify(qry,update,FindAndModifyOptions.options().returnNew(true).upsert(true),DbSequence.class);
//        return !Objects.isNull(counter)?counter.getSeq():String.valueOf(1);
//    }


    @Override
    public ResponseEntity<String> createOrder(Order order) {

        Order ordered= orderRepository.save(order);
        if(ordered != null ){
            rabbitTemplate.convertAndSend("Order_exchange","Order_routing key",order);
            return new ResponseEntity<>("Order is added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Order Creation terminated.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orderList=orderRepository.findAll();
        if(orderList.isEmpty()){
            throw new OrderNotFoundException("No Order found");
        }
        return orderList;
    }

    @Override
    public ResponseEntity<Order> getOrderByEmail(String email) {
        Optional<Order> orders = orderRepository.findByBuyerEmail(email);
        if(orders.isPresent()){
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("Order for customer email " + email + " is not found.");
        }
    }

    @Override
    public ResponseEntity<String> updateOrder(Order order) {
        orderRepository.save(order);
        return new ResponseEntity<String>("Orders are updated", HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<String> deleteOrderByEmail(String email) {
        Optional<Order> orders = orderRepository.findByBuyerEmail(email);
        if(orders.isPresent()){
            orderRepository.deleteByBuyerEmail(email);
            return new ResponseEntity<>("Order with customer email " + email + " is deleted successfull", HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("Order with customer email " + email + " is not found.");
        }


    }

    @Override
    public ResponseEntity<?> deleteAll(Order order) {
        orderRepository.deleteAll();
        return new ResponseEntity<>("Orders are deleted", HttpStatus.ACCEPTED);
    }


    public void createNewOrder(PaymentDto payment) {
            String email = payment.getCustomerEmail();
            if(cartRepository.findByCustomerEmail(email).isPresent()) {
                NewCartDto cart = cartRepository.findByCustomerEmail(email).get();
                Order order = new Order();
                order.setBuyerEmail(cart.getCustomerEmail());
                order.setProducts(cart.getCartProducts());
                order.setPaymentStatus(payment.getStatus());
                order.setOrderStatus("Success!!");
                order.setProductQuantity(cart.getProductQuantity());
                order.setTotalItems(cart.getTotalItems());
                order.setTotalOrderValue(cart.getTotalCartValue());
                orderRepository.save(order);
            }

    }
}


