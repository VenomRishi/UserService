/******************************************************************************
 *  Purpose: Class is implemented for creating the POJO class which will holds
 *  		 the rabbitMQ payload
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   04-11-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.entity;

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
