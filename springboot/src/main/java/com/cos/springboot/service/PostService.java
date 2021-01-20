package com.cos.springboot.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.cos.springboot.domain.post.Post;
import com.cos.springboot.domain.post.PostRepository;

//@Controller , @RestController, @Service, @Configuration, @Component

@Service //서버 실행시에 IoC에 등록됨(메모리에 뜸) -> 컨트롤러가 서비스에 접근할수있음!
public class PostService {
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
		System.out.println("@PostService 실행됨");
	}
	
	public Post 글저장(Post post) {
		return postRepository.save(post);
	}
	
	public List<Post> 글목록(){
		return postRepository.findAll();
	}
	
	public Post 글상세보기(int id) {//Optional  제대로 처리 안될수도있다. 옵션을 주겠다
		return postRepository.findById(id).orElse(new Post());
	}
}
