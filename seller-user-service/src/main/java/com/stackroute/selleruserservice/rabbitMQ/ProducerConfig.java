package com.stackroute.selleruserservice.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProducerConfig {
    public static final String QUEUE = "sellerQueue-Product";
    public static final String FANOUT_EXCHANGE = "sellerExchange";

    @Bean
    public Queue producerQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public FanoutExchange producerFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding producerBinding1(FanoutExchange producerFanoutExchange, Queue producerQueue) {
        return BindingBuilder.bind(producerQueue).to(producerFanoutExchange);
    }
}
