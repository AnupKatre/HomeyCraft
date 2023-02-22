package com.stackroute.paymentController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.paymentEntity.MyOrder;
import com.stackroute.paymentRepository.MyOrderRepo;
import com.stackroute.rabbitMQ.ProducerConfig;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
//@CrossOrigin
public class OrderController {
    @Autowired
    private MyOrderRepo orderRepo;

    int i=1001;


    @Autowired
    private RabbitTemplate template;

//    @GetMapping("/Welcome")
//    public String Welcome(){
//        return "Welcome to payment service";
//    }
//creating order
    @PostMapping("/createorder/{amount}")
    @ResponseBody
    public String createOrder(@PathVariable("amount") float data,@RequestParam("customerEmail") String email) throws RazorpayException{
        System.out.println("order function executed");
        System.out.println(data);

//        int amt = Integer.parseInt(data);
        RazorpayClient client = new RazorpayClient("rzp_test_skTthSZONeefQl","mD7pGK0hwmw1fU9UerijdqjH");

        JSONObject options = new JSONObject();
        //options.put("order id",orderRepo.findByOrderId(myOrder.));
        options.put("amount",data);
        options.put("currency","INR");
        options.put("receipt","txn_"+i++);
        Order order = client.Orders.create(options);
        System.out.println(order);


//save order details into database

        MyOrder myOrder=new MyOrder();
        myOrder.setAmount(order.get("amount")+"");
        myOrder.setOrderId(order.get("order_id"));
        myOrder.setReceipt(order.get("receipt"));
        myOrder.setStatus(order.get("status"));
        myOrder.setCustomerEmail(email);

//        this.orderRepo.save(myOrder);

        template.convertAndSend(ProducerConfig.FANOUT_EXCHANGE,"",orderRepo.save(myOrder));
        return order.toString();
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<MyOrder>updateOrder(@RequestBody MyOrder myOrder){

        MyOrder od = orderRepo.findByOrderId(myOrder.getOrderId());
        od.setPaymentId(myOrder.getPaymentId());
        od.setRazorpaySignature(myOrder.getRazorpaySignature());
        od.setStatus(myOrder.getStatus());
        MyOrder update = this.orderRepo.save(od);
        System.out.println(myOrder);
        return ResponseEntity.ok(update);
    }


    @GetMapping("/getpayment")
    public List<MyOrder> getAllPayment(){
        return orderRepo.findAll();
    }
}
