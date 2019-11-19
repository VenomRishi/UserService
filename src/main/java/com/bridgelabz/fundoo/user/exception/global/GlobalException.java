/******************************************************************************
 *  Purpose: This class is major class for handling all exception which can be
 *  		 throw while the application is running in the server side, as this
 *  		 class is global exception handler it will handle custom exception 
 *  	 	 and built in exception also, this class purpose is to handle 
 *  		 application from crashing even in critical situation and giving
 *  		 proper response if crashing.
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   21-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.fundoo.user.exception.custom.ForgotPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.LoginException;
import com.bridgelabz.fundoo.user.exception.custom.RegisterException;
import com.bridgelabz.fundoo.user.exception.custom.RegisterVerifyException;
import com.bridgelabz.fundoo.user.exception.custom.SetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.UserException;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.utility.Constant;

@ControllerAdvice
public class GlobalException {

	/**
	 * Purpose: this method is created for handle the global exception which can
	 * occur while running the application
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globalExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(Constant.HTTP_STATUS_INTERNAL_SERVER_ERROR, ex.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Purpose: this method is created for handle the login exception which can
	 * occur while running the application
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> loginExceptionHandler(LoginException ex) {

		return new ResponseEntity<>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Purpose: this method is created for handle the registration exception which
	 * can occur while running the application
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(RegisterException.class)
	public ResponseEntity<Response> registerExceptionHandler(RegisterException ex) {
		return new ResponseEntity<>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Purpose: this method is created for handle the registration verification
	 * exception which can occur while running the application
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(RegisterVerifyException.class)
	public ResponseEntity<Response> registerVerifyExceptionHandler(RegisterVerifyException ex) {
		return new ResponseEntity<>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Purpose: this method is created for handle the forgot password request
	 * exception which can occur while running the application
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(ForgotPasswordException.class)
	public ResponseEntity<Response> forgotPasswordExceptionHandler(ForgotPasswordException ex) {
		return new ResponseEntity<>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Purpose: this method is created for handle the set password exception request
	 * which can occur while running the application
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(SetPasswordException.class)
	public ResponseEntity<Response> setPasswordExceptionHandler(SetPasswordException ex) {
		return new ResponseEntity<>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Purpose: this method is created for handle the set password exception request
	 * which can occur while running the application
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(UserException.class)
	public ResponseEntity<Response> userException(UserException ex) {
		return new ResponseEntity<Response>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

}
