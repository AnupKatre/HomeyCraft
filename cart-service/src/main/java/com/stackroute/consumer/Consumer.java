package com.stackroute.consumer;
import com.stackroute.serviceImpl.CartServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.rabbitMQConfig.ConsumerConfig;
import com.stackroute.model.ProductDto;
import com.stackroute.model.UserDto;


@Component
public class Consumer {

    @Autowired
    private CartServiceImpl service;

    @RabbitListener(queues =  ConsumerConfig.QUEUE)
    public void consumeLoanDetailsFromQueue(UserDto userDetails) {
        System.out.println("User details recieved from queue : " + userDetails);
        service.save(userDetails);
    }

    @RabbitListener(queues =  ConsumerConfig.QUEUE1)
    public void producerLoanDetailsFromQueue(ProductDto productDetails) {
        System.out.println("User details recieved from queue : " + productDetails);
        service.addProduct(productDetails);
    }

}
