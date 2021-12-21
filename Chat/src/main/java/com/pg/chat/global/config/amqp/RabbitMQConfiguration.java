package com.pg.chat.global.config.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
	public static final String TOPIC_EXCHANGE_NAME = "programmer-ground.chat";
	public static final String TOPIC_ROUTING_KEY = "room.#";
	public static final String QUEUE_NAME = "chatting";

	/**
	 * Declare Durable Queue
	 * @return - The Queue which name defined as {@link #QUEUE_NAME}
	 */
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME);
	}

	/**
	 * Declare Durable Topic Exchange
	 * @return The TopicExchange which name defined as {@link #TOPIC_EXCHANGE_NAME}
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

	/**
	 * Binding Queue and TopicExchange with Routing Key
	 * @param queue - Message Queue
	 * @param topicExchange - Message Topic Exchanger
	 * @return - Binding Channel between Queue and TopicExchange
	 */
	@Bean
	public Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder
			.bind(queue)
			.to(topicExchange)
			.with(TOPIC_ROUTING_KEY);
	}

	/**
	 * Declare RabbitMQ Operation Template
	 * @param connectionFactory - RabbitMQ Connection Factory
	 * @param rabbitMessageConvertor - RabbitMQ Message Convertor
	 * @return - rabbitmq operation template
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(
		ConnectionFactory connectionFactory, Jackson2JsonMessageConverter rabbitMessageConvertor
	) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(rabbitMessageConvertor);
		return rabbitTemplate;
	}

	/**
	 * Declare Jackson2JsonMessageConvertor For register to rabbitmq connection to json message convert
	 * @return Jackson2JsonMessageConvertor Object
	 */
	@Bean
	public Jackson2JsonMessageConverter rabbitMQMessageConvertor() {
		return new Jackson2JsonMessageConverter();
	}

}
