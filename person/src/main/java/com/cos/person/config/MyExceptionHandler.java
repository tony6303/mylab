package com.cos.person.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String 요청잘못(IllegalArgumentException e) {
		return "에러났어요 : " + e.getMessage();
	}
}
