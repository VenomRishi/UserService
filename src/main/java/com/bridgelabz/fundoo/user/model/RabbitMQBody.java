package com.bridgelabz.fundoo.user.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMQBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String subject;
	private String body;

}
