/******************************************************************************
 *  Purpose: This class is created for catching validation related exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   21-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.exception.custom;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.fundoo.user.response.Response;

@RestControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		return new ResponseEntity<>(new Response(400, "Validation Error", errors), HttpStatus.BAD_REQUEST);

	}
}
