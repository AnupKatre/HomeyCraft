package com.stackroute.rabbitMQConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

	public static final String QUEUE = "buyerQueue-Cart";
	public static final String FANOUT_EXCHANGE = "buyerExchange";


	public static final String QUEUE1 = "product_queue";
	public static final String EXCHANGE1 = "product_exchange";
	public static final String ROUTING_KEY1 = "product_routingKey";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE);
	}

	@Bean
	public FanoutExchange exchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	@Bean
	public Binding binding(FanoutExchange exchange,Queue queue) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	public Queue queue1() {
		return new Queue(QUEUE1);
	}

	@Bean
	public TopicExchange exchange1() {
		return new TopicExchange(EXCHANGE1);
	}

	@Bean
	public Binding binding1(Queue queue1, TopicExchange exchange1) {
		return BindingBuilder.bind(queue1).to(exchange1).with(ROUTING_KEY1);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

}
