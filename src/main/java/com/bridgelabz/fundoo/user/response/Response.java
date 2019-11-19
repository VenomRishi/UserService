/******************************************************************************
 *  Purpose: This class is created for generating the response this is 
			 HTTP(Hyper Text Transfer Protocol) response which will contain
			 status code, message (response related), Object if it returning 
			 something.
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   21-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

	private int statusCode;
	private String message;
	private Object data;
	
}
