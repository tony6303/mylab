package com.cos.person.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//STS에서 제공하는 return ResponseEntity로는 Chrome Console에서 log가 안찍혀서 자체적으로 만듬
@Data
public class CommonDto<T> {
	private int statusCode;
	private T data;
	
	
	public CommonDto(int statusCode, T data) {
		super();
		this.statusCode = statusCode;
		this.data = data;
	}
	
	public CommonDto(int statusCode) {
		super();
		this.statusCode = statusCode;
	}
	
	
}
