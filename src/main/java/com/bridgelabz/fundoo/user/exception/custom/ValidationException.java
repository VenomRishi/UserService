/******************************************************************************
 *  Purpose: This class is created for catching validation related exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   30-10-2019
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
import com.bridgelabz.fundoo.user.utility.Constant;

@RestControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler {

	/**
	 * Purpose: this method is used to responds back the exception to used end
	 * 
	 * @param ex      exception which will generated from program if any input is
	 *                invalid
	 * @param headers if there is any header which also comes from application
	 * @param status  this is HTTP status
	 * @param request this is request to specify which request is catch
	 * 
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		return new ResponseEntity<>(new Response(400, Constant.VALIDATION_EXCEPTION, errors), HttpStatus.BAD_REQUEST);

	}
}
