package com.bridgelabz.fundoo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.bridgelabz.fundoo.user.entity.RabbitMQBody;

public class MessageReceiver {

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * Purpose: this is method will take Payload from the RabbitMQ queue and send
	 * email from the SMTP(Simple Mail Transfer Protocol) server
	 * 
	 * @param token
	 */
	public void receiveMsg(RabbitMQBody rabbitMQBody) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(rabbitMQBody.getEmail());
		simpleMailMessage.setSubject(rabbitMQBody.getSubject());
		simpleMailMessage.setText(rabbitMQBody.getBody());
		javaMailSender.send(simpleMailMessage);
	}

}
