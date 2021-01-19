package com.cos.person.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.CommonDto;
import com.cos.person.domain.JoinReqDto;
import com.cos.person.domain.UpdateReqDto;
import com.cos.person.domain.User;
import com.cos.person.domain.UserRepository;


//주소의 EndPoint 를 모델로 정한다.
//동사표현은 @Get, Post, Delete
@RestController
public class UserController {
	
	private UserRepository userRepository;
	
	//@RestController 가 있기떄문에 가능한 DI(의존성 주입)
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//http://localhost:8080/user
	@GetMapping("/user")
	public List<User> findAll() {
		System.out.println("findAll()");
		return userRepository.findAll(); //MessageConverter (JavaObject -> JSON String)
	}
	
	@GetMapping("/user/{id}")
	public CommonDto<User> findById(@PathVariable int id) {
		System.out.println("findById()");
		return new CommonDto<>(HttpStatus.OK.value(),userRepository.findById(id));
		//JSON String 으로 반환되는중.
	}
	
//	@PostMapping("/user")
//	//x-www-form-urlencoded => request.getParameter()
	// 쿼리 스트링이든 , body 데이터든 상관없이 파싱함
//	public void save(String username, String password, String phone) {
//		System.out.println("save()");
//		System.out.println("username :" + username);
//		System.out.println("password :" + password);
//		System.out.println("phone :" + phone);
//	} 
	
	//@RequestBody를 사용하면 , 
//	@PostMapping("/user") // text/plain : Buffer 로 읽음. JSON말고 String data를 보내도 그대로읽음
//	public void save(@RequestBody String data) {
//		System.out.println("save()");
//		System.out.println("data :" + data);
//	}
	
	@CrossOrigin // CORS 정책 무시
	@PostMapping("/user") // applicaion/json + 오브젝트로 받기
	public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult ) { // "ok" 반환하기때문에 String
		//if 문에서는 errorMap을 반환하고, 아닐때는 "ok"를 반환하기때문에 리턴을 알수가 없어서 ? << 를 사용.
		
		//함수 하나는 하나의 기능을 하도록 하기위해 오류검출 코드는 필터처리로 이동!!!
		//용어 : 공통기능 = advice , 필터가 작동하는 위치 = pointcut , advice를 넣는행위 = weaving
//		if(bindingResult.hasErrors()) {
//			Map<String,String> errorMap = new HashMap<>();
//			
//			//AOP3강 11분00초
//			for (FieldError error : bindingResult.getFieldErrors()) {
//				errorMap.put(error.getField(), error.getDefaultMessage());
//				//(오류목록 , 해당오류의 메시지) -> JoinReqDto 에 어노테이션 걸어놓았음.
//			}
//			return new CommonDto<>(HttpStatus.BAD_REQUEST.value(),errorMap);
//		}
		
		
		
		System.out.println("save()");
		System.out.println("user :" + dto);
		userRepository.save(dto); // 컨트롤러가 하는 핵심 기능.
		
		return new CommonDto<>(HttpStatus.CREATED.value(),"ok");
	}
	
	@DeleteMapping("/user/{id}")  //@PathVariable붙은 인자의 이름과 정확히 일치해야 바인딩 됨
	public CommonDto delete(@PathVariable int id) {
		System.out.println("delete()");
		userRepository.delete(id);
		return new CommonDto<>(HttpStatus.OK.value());
		
	}
	
	@PutMapping("/user/{id}") // 업데이트 할 정보(ReqDto= password, phone)를 인자에 넣는다.
	public CommonDto update(@PathVariable int id,@Valid @RequestBody UpdateReqDto dto, BindingResult bindingResult) {
		System.out.println("update()");
		userRepository.update(id, dto); 
		return new CommonDto<>(HttpStatus.OK.value()); 
	}
}
