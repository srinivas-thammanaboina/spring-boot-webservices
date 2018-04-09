package com.myspringboot.webservices.restwebservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice   // share this across all controllers
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(Exception.class) //to handle all Exceptions
	public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest webrequest){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webrequest.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR); // can change to other httpstatus
	}
	
	@ExceptionHandler(UserNotFoundException.class) //to handle all Exceptions
	public final ResponseEntity<Object> handleUserNotFoundExcpetion(UserNotFoundException ex,WebRequest webrequest){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webrequest.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND); // can change to other httpstatus
	}
}
