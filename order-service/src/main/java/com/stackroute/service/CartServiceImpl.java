package com.stackroute.service;

import com.stackroute.model.NewCartDto;
import com.stackroute.model.PaymentDto;
import com.stackroute.repository.NewCartRepository;
import com.stackroute.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//import org.springframework.data.mongodb.core.FindAndModifyOptions;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;

@Service
public class CartServiceImpl {

    @Autowired
    public NewCartRepository cartRepository;

    @Autowired
    public PaymentRepository paymentRepository;

    @Autowired
    OrderServiceImpl service;

    public void saveCartData(NewCartDto cart){
        Optional<NewCartDto> obj = cartRepository.findByCustomerEmail(cart.getCustomerEmail());
        if(obj.isPresent()){
            NewCartDto repoCart = obj.get();
            repoCart.setCartProducts(cart.getCartProducts());
            repoCart.setTotalCartValue(cart.getTotalCartValue());
            repoCart.setTotalItems(cart.getTotalItems());
            repoCart.setProductQuantity(cart.getProductQuantity());
            cartRepository.save(repoCart);


        }else {
            NewCartDto newCartDto = new NewCartDto();
            newCartDto.setCustomerEmail(cart.getCustomerEmail());
            newCartDto.setId(cart.getId());
            newCartDto.setCartProducts(cart.getCartProducts());
            newCartDto.setTotalCartValue(cart.getTotalCartValue());
            newCartDto.setTotalItems(cart.getTotalItems());
            newCartDto.setProductQuantity(cart.getProductQuantity());
            cartRepository.save(newCartDto);
        }



    }

    public void savePaymentData(PaymentDto payment){

        paymentRepository.save(payment);
        service.createNewOrder(payment);


    }


//
//    @Override
//    public Cart createCart(Cart cart) {
//
//        Cart cart1= cartRepository.save(cart);
//        if(cart1 != null ){
////            rabbitTemplate.convertAndSend("Order_exchange","Order_routing key",cart);
//            System.out.println("Cart is added successfully.");
//        } else {
//
//
//            System.out.println("Cart Creation terminated.");
//        }
//        return cart;
//    }
//
//    @Override
//    public Cart  getCartById(String cartOwnerEmail) {
//        Cart carts = cartRepository.findByCartId(cartOwnerEmail);
//        if (carts != null ) {
//            return carts;
//
//        }
//        else{
//            throw new CartNotFoundException("user email doesn't exist");
//        }
//    }
//
//    @Override
//    public boolean deleteCartById(String cartOwnerEmail) {
//        Cart carted = cartRepository.findByCartId(cartOwnerEmail);
//        if(carted != null){
//            cartRepository.deleteByCartId(cartOwnerEmail);
//
//            return true;
//        } else {
//            throw new CartNotFoundException("Cart with id " + cartOwnerEmail + " is not found.");
//        }
//    }




}
