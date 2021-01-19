package com.cos.person.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateReqDto {
	@NotBlank(message = "패스워드가 없습니다.")
	private String password;
	private String phone;
}