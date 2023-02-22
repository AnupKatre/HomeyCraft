package com.stackroute.exception;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.stackroute.exception.ErrorInfo;
@RestControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
	Environment environment;
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleExceptionForIdNotFound(IdNotFoundException exception) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		errorInfo.setErrorCode("500");
		//errorInfo.setTime(LocalDate.now());
		errorInfo.setTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
