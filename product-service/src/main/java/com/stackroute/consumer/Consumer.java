package com.stackroute.consumer;

import com.stackroute.model.UserDto;
import com.stackroute.producerConfig.ConsumerConfig;
import com.stackroute.serviceImpl.ProductServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    @Autowired
    private ProductServiceImpl service;

    @RabbitListener(queues =  ConsumerConfig.QUEUE)
    public void consumeLoanDetailsFromQueue(UserDto userDetails) {
        System.out.println("User details recieved from queue : " + userDetails);
        service.save(userDetails);
    }
}
