package com.cos.person.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JoinReqDto {
	
	@NotNull(message= "유저네임의 키 값이 없습니다.")
	@NotBlank(message= "유저네임을 입력하세요.")
	@Size(max=20, message= "유저네임 길이가 너무 깁니다.")
	private String username;
	
	@NotNull(message= "비밀번호의 키 값이 없습니다.")
	private String password;
	
	@NotNull(message= "전화번호의 키 값이 없습니다.")
	private String phone;
}
