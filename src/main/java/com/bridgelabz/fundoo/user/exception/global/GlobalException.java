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
import com.bridgelabz.fundoo.user.response.Response;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globalExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(500, ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> loginExceptionHandler(LoginException ex) {

		return new ResponseEntity<>(new Response(400, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RegisterException.class)
	public ResponseEntity<Response> registerExceptionHandler(RegisterException ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RegisterVerifyException.class)
	public ResponseEntity<Response> registerVerifyExceptionHandler(RegisterVerifyException ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ForgotPasswordException.class)
	public ResponseEntity<Response> forgotPasswordExceptionHandler(ForgotPasswordException ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SetPasswordException.class)
	public ResponseEntity<Response> setPasswordExceptionHandler(SetPasswordException ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

}
