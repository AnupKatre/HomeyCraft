package com.stackroute.consumer;

import com.stackroute.model.NewCartDto;
import com.stackroute.model.PaymentDto;
import com.stackroute.rabbitMQConfig.ConsumerConfig;
import com.stackroute.service.CartServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    @Autowired
    private CartServiceImpl service;

    @RabbitListener(queues =  ConsumerConfig.QUEUE1)
    public void consumeCartDetailsFromQueue(NewCartDto cart) {
        System.out.println("Cart recieved from queue : " + cart);
        service.saveCartData(cart);
    }

    @RabbitListener(queues =  ConsumerConfig.QUEUE)
    public void consumeCartDetailsFromQueue(PaymentDto payment) {
        System.out.println("Payment recieved from queue : " + payment);
        service.savePaymentData(payment);
    }

}
