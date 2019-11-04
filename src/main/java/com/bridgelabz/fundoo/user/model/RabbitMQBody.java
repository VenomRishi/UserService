package com.bridgelabz.fundoo.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RabbitMQBody {

	private String email;
	private String subject;
	private String message;

}
