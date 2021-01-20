package com.cos.springboot.domain.post;



import org.springframework.data.jpa.repository.JpaRepository;
 

//@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
}
