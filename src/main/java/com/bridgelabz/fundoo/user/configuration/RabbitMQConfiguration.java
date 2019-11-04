package com.bridgelabz.fundoo.user.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.fundoo.user.service.MessageReceiver;
import com.bridgelabz.fundoo.user.utility.Constant;

@Configuration
@EnableAutoConfiguration
public class RabbitMQConfiguration {

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("admin");
		cachingConnectionFactory.setPassword("admin");
		return cachingConnectionFactory;
	}

	@Bean
	Queue queue() {
		return new Queue(Constant.QUEUE_NAME, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(Constant.MESSAGE_QUEUE_EXCHANGE);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(Constant.ROUTING_KEY);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter messageListenerAdapter) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Constant.QUEUE_NAME);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}

	@Bean
	MessageReceiver receiver() {
		return new MessageReceiver();
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageReceiver messageReceiver) {
		return new MessageListenerAdapter(messageReceiver, "receiveMsg");
	}

}
