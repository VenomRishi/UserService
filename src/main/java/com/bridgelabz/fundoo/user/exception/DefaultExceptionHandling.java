package com.bridgelabz.fundoo.user.exception;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandling extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception ex) {
		ErrorMessage exceptionMessage = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
