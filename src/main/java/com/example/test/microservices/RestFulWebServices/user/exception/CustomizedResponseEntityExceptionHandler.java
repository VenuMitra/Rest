package com.example.test.microservices.RestFulWebServices.user.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)  {
		ExceptionResponse exceptionResponse =new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
		
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request)  {
		ExceptionResponse exceptionResponse =new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}
	

	@ExceptionHandler(NoUsersFoundExcpetion.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(NoUsersFoundExcpetion ex, WebRequest request)  {
		ExceptionResponse exceptionResponse =new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}

}
