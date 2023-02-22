package com.stackroute.service;

import com.stackroute.configuration.ConsumerConfig;
import com.stackroute.model.BuyerDto;
import com.stackroute.model.SellerDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private JwtService service;

    @RabbitListener(queues =  ConsumerConfig.QUEUE)
    public void consumeDetailsFromQueue(BuyerDto userDetails) {
        System.out.println("User details received from queue : " + userDetails);
        service.save(userDetails);
    }
    @RabbitListener(queues =  ConsumerConfig.QUEUE1)
    public void consumeDetailsFromQueue(SellerDto userDetails) {
        System.out.println("User details received from queue : " + userDetails);
        service.save(userDetails);
    }

}
