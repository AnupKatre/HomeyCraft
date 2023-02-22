package com.stackroute.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProducerConfig {
//    public static final String QUEUE1 = "paymentQueue-Email";
    public static final String QUEUE2 = "paymentQueue-Order";
    public static final String FANOUT_EXCHANGE = "paymentExchange";

//    @Bean
//    public Queue producerQueue1() {
//        return new Queue(QUEUE1);
//    }
    @Bean
    public Queue producerQueue2() {
        return new Queue(QUEUE2);
    }

    @Bean
    public FanoutExchange producerFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

//    @Bean
//    public Binding producerBinding1(FanoutExchange producerFanoutExchange, Queue producerQueue1) {
//        return BindingBuilder.bind(producerQueue1).to(producerFanoutExchange);
//    }
    @Bean
    public Binding producerBinding2(FanoutExchange producerFanoutExchange,Queue producerQueue2) {
        return BindingBuilder.bind(producerQueue2).to(producerFanoutExchange);
    }


    @Bean
    public MessageConverter producerConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate producerTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerConverter());
        return rabbitTemplate;
    }

}
