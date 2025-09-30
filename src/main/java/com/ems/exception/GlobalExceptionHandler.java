package com.ems.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object>  handleEmployeeException(EmployeeNotFoundException ex)
	{
		// key : value
		Map<String,Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object>  handleEmployeeException(Exception ex)
	{
		// key : value
		Map<String,Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
