package com.stackroute.emailservice.consumer;

import com.stackroute.emailservice.consumerConfig.ConsumerConfig;
import com.stackroute.emailservice.model.Buyer;
import com.stackroute.emailservice.model.Seller;
import com.stackroute.emailservice.model.UserDto;
import com.stackroute.emailservice.repository.BuyerUserRepository;
import com.stackroute.emailservice.repository.SellerUserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    @Autowired
    SellerUserRepository SellerRepository;

    @Autowired
    BuyerUserRepository buyerRepository;


    @RabbitListener(queues =  ConsumerConfig.QUEUE)
    public void consumeSellerUserDetailsFromQueue(UserDto user) {
        System.out.println("User details recieved from queue : " + user);
        Seller existingSeller = new Seller();
        existingSeller.setSellerName(user.getName());
        existingSeller.setSellerEmail(user.getEmail());
        existingSeller.setSellerPassword(user.getPassword());
        existingSeller.setSellerAddress(user.getAddress());
        existingSeller.setSellerPincode(user.getPincode());

        SellerRepository.save(existingSeller);
    }

    @RabbitListener(queues =  ConsumerConfig.QUEUE1)
    public void consumeBuyerUserDetailsFromQueue(UserDto user) {
        System.out.println("User details recieved from queue : " + user);
        Buyer existingSeller = new Buyer();
        existingSeller.setBuyerName(user.getName());
        existingSeller.setBuyerEmail(user.getEmail());
        existingSeller.setBuyerPassword(user.getPassword());
        existingSeller.setBuyerAddress(user.getAddress());
        existingSeller.setBuyerPincode(user.getPincode());

        buyerRepository.save(existingSeller);
    }

}
