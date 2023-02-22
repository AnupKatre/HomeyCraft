package com.stackroute.producerConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConsumerConfig {
    public static final String QUEUE = "sellerQueue-Product";
    public static final String FANOUT_EXCHANGE = "sellerExchange";

    @Bean
    public Queue consumerQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public FanoutExchange consumerFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding consumerBinding1(FanoutExchange consumerFanoutExchange, Queue consumerQueue) {
        return BindingBuilder.bind(consumerQueue).to(consumerFanoutExchange);
    }
}
