/******************************************************************************
 *  Purpose: This is configuration class which holds all the configuration
 *  		 related RabbitMQ
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   05-11-2019
 *
 ******************************************************************************/

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

	/**
	 * Purpose: this method helps application to establishes the connection with the
	 * RabbitMQ Server which is running the localhost and the port is used is 15672
	 * by default so this method checks whether that server is running or not if
	 * running then establishes the connection
	 * 
	 * @return returns the CachingConnectionFactory which has the login credentials
	 *         for rabbitMQ
	 */
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("admin");
		cachingConnectionFactory.setPassword("admin");
		return cachingConnectionFactory;
	}

	/**
	 * Purpose: this method creates queue inside the RabbitMQ Server
	 * 
	 * @return it returns queue with specific queue name
	 */
	@Bean
	Queue queue() {
		return new Queue(Constant.QUEUE_NAME, false);
	}

	/**
	 * Purpose: this method creates for defining the exchange method so we have used
	 * TopicExchange in which it contains the routing path which is specified by
	 * this method
	 * 
	 * @return returns TopicExchange with the message queue exchange
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(Constant.MESSAGE_QUEUE_EXCHANGE);
	}

	/**
	 * Purpose: this method is created to bind all the information into rabbitMQ
	 * queue which is running on the server side by the help of queue() method and
	 * exchange() method it will bind all data into RabbitMQ Server
	 * 
	 * @param queue         this parameter defines the queue name
	 * 
	 * @param topicExchange this parameter defines the exchange name
	 * 
	 * @return this method returns the binding configuration which binds queue into
	 *         rabbitMQ
	 */
	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(Constant.ROUTING_KEY);
	}

	/**
	 * Purpose: this method is the part of receiver / consumer which can consume the
	 * queue and creates container to store the queue payload
	 * 
	 * @param connectionFactory      this can specify the container to choose which
	 *                               connectionFactory they should follow
	 * @param messageListenerAdapter in this we are using messageListenerAdapter
	 *                               which can actively listening the payload
	 * @return returns the container
	 */
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter messageListenerAdapter) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Constant.QUEUE_NAME);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}

	/**
	 * Purpose: this method helps to create bean of MessageReceiver class and keep
	 * it inside the IOC(Inversion of control) container
	 * 
	 * @return returns the object of MessageReceiver class
	 */
	@Bean
	MessageReceiver receiver() {
		return new MessageReceiver();
	}

	/**
	 * Purpose: this method is used as MessageListenerAdapter which is actively
	 * listening the payload holds in the queue and helps the emptying the queue by
	 * consuming the payload which is there in rabbitMQ queue
	 * 
	 * @param messageReceiver this can specify on which class the payload should be
	 *                        consume
	 * @return returns the payload into the MessageReceiver class
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(MessageReceiver messageReceiver) {
		return new MessageListenerAdapter(messageReceiver, "receiveMsg");
	}

}
