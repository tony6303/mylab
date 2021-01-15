package com.cos.start.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository { //DAO 같은 역할??
	
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		users.add(new User(1,"ssar","1234","0101234"));
		users.add(new User(2,"cos","1234","0101234"));
		users.add(new User(3,"love","1234","0101234"));
		
		return users;
	}
	
	public User findById(int id) {
		return new User(1,"ssar","1234","0101234");
	}
	//
	public void save(JoinReqDto dto) {
		System.out.println("DB에 INSERT");
	}
	
	public void delete(int id) {
		System.out.println("DB에 DELETE완료");
	}
	
	public void update(int id, UpdateReqDto dto) {
		//강제로 exception 작동 (예제)
		throw new IllegalArgumentException("ArgumentException");
//		System.out.println("DB에 UPDATE완료");
	}
}
