package com.cos.springboot.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.springboot.domain.post.Post;
import com.cos.springboot.domain.post.PostRepository;
import com.cos.springboot.service.PostService;

//http 요청시에 @Controller , @RestController 가 붙은 클래스가
//메모리(IoC Container)에 로딩됨
@Controller
public class PostController {
	private PostService postService;
	
	public PostController(PostService postService) {
		System.out.println("@Controller생성자 실행됨");
		this.postService = postService;
	}
	
	
	//IoC에서 제공해주는 객체 : Model
	@GetMapping("/post")
	public String findAll(Model model) {
		//컨트롤러의 함수의 파라메터는 톰캣이 가지고있는 객체 + IoC컨테이너가 갖고있는 객체 (톰캣객체 : request, session)
		//적어주기만 하면 바로 주입된다. (Reflection)
		//1. DB에서 데이터가져오기
		//2. request 값 담기
		List<Post> posts = postService.글목록();
		model.addAttribute("posts",posts);
		System.out.println(posts);
		return "post/list"; //3. RequestDispatcher 사용하기
	}
	
	@GetMapping("/post/{id}")
	public String findById(@PathVariable int id, Model model) {
		
		model.addAttribute("post",postService.글상세보기(id));
		return "post/detail";
	}
	
	@PostMapping("/post")
	public String save(Post post) { 
		System.out.println("post : " + post);
		
		Post postEntity = postService.글저장(post);
		System.out.println("postEntity : " + postEntity);
		return "redirect:/post"; // response.sendRedirect
	}
}
