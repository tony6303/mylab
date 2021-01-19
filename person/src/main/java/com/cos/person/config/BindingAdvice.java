package com.cos.person.config;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.person.domain.CommonDto;

//@Controller @RestController @Component @Configuration

// 컨트롤러 진입하기 전, 설정이 필요하면 Configuration
// 그게 아니라면 Component

@Component
@Aspect
public class BindingAdvice {
	//함수를 찾은 뒤에 작동함. -> 함수를 이미 찾았음
	//Controller가 이미 메모리에 떴음.
	
	//ProccedingJoinPoint : 실행 될 함수 (save , delete , update ...) (?)
	//정규표현식으로 어떤함수에 걸 것인지 정함
	//web패키지 안에있는 모든 클래스 중 Controller 로 끝나는 클래스들에 있는 모든 인자 갯수 상관없는 함수
	@Before("execution(* com.cos.person.web..*Controller.*(..))")
	public Object testCheck() {
		System.out.println("전처리 로그 작동됨");
		return 1;
	}
	
	//web패키지 안에있는 모든 클래스 중 Controller 로 끝나는 클래스들에 있는 모든 인자 갯수 상관없는 함수
	@After("execution(* com.cos.person.web..*Controller.*(..))")
	public Object testCheck2() {
		System.out.println("후처리 로그 작동됨");
		return 1;
	}
	
	//web패키지 안에있는 모든 클래스 중 Controller 로 끝나는 클래스들에 있는 모든 인자 갯수 상관없는 함수
	//proceedingJoinPoint = 함수의 앞뒤를 제어할 때만 사용가능. 앞, 뒤만 할땐 불가능
	@Around("execution(* com.cos.person.web..*Controller.*(..))") 
	public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		
		System.out.println("type :" + type); //함수가 선언된 클래스 위치
		System.out.println("method :" + method); // 함수 이름
		
		Object[] args = proceedingJoinPoint.getArgs();
		
		for (Object arg : args) {
			//arg 가 BindingResult 타입이라면, (Valid 하기위해 필요)
			if(arg instanceof BindingResult) {
				//bindingResult 변수 에 arg를 다운캐스팅 하여 저장
				BindingResult bindingResult = (BindingResult) arg;
				
				if(bindingResult.hasErrors()) {
					Map<String,String> errorMap = new HashMap<>();
					for (FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						//(오류목록 , 해당오류의 메시지) -> JoinReqDto 에 어노테이션 걸어놓았음.
					}
					return new CommonDto<>(HttpStatus.BAD_REQUEST.value(),errorMap);
				}
			}
		} //end of for(Object arg : args) 
		return proceedingJoinPoint.proceed(); //정상이면 다음 스택 실행
	}
	
}
