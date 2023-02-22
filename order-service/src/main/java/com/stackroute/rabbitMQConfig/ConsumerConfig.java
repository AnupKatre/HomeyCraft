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

	public static final String QUEUE = "paymentQueue-Order";

	public static final String QUEUE1 = "cartQueue-Order";
	public static final String EXCHANGE_CART = "cartExchange";
	public static final String EXCHANGE_PAYMENT = "paymentExchange";


	@Bean
	public Queue queue() {
		return new Queue(QUEUE);
	}

	@Bean
	public Queue queue1() {
		return new Queue(QUEUE1);
	}


	@Bean
	public FanoutExchange exchange() {
		return new FanoutExchange(EXCHANGE_PAYMENT);
	}

	@Bean
	public FanoutExchange exchange1() {
		return new FanoutExchange(EXCHANGE_CART);
	}


	@Bean
	public Binding binding(FanoutExchange exchange,Queue queue) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	public Binding binding1(FanoutExchange exchange1,Queue queue1) {
		return BindingBuilder.bind(queue1).to(exchange1);
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
