package com.cos.start.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class IndexController {
	
	
	//이곳을 호칭하는 주소 http://localhost:8080/start/index
	@GetMapping("/index")
	public String index() {
		return "index함수 입니다."; //메시지 컨버터 작동 , @RestController
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello"; //ViewResolver 작동 , @Controller
	}
}
