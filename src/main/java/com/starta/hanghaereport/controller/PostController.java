package com.starta.hanghaereport.controller;

import com.starta.hanghaereport.dto.PostRequestDto;
import com.starta.hanghaereport.entity.Post;
import com.starta.hanghaereport.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // -> 스프링이 Controller (유저의 요청을 수신하는 객체) 로 인식할 수 있어 ,
// JSON 데이터 타입으로 response 해줄것이므로 -> 이걸 달아주지 않으면, 각각의 API에 responsebody를 달아줘야하는 번거로움
@RequiredArgsConstructor
//final 선언할때 스프링에게 알려줌
public class PostController {
    //1. 사용자는 필요한 데이터를 보낸다.
    //2. PostRequestDto는 사용자가 보낸 데이터를 Model 계층으로 보낸다.
    //3. 글작성에 필요한 데이터를 받아서 PostTable에 저장한다.

    private final PostService postService; //PostServise 와 연결


    //게시글작성
    @PostMapping ("/api/posts") //request 종류 Post
    //Post는 반환타입 , createPost : 메소드명 (원하는대로)
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        //RequestBody: Post안에 저장된 body 값들을 key:value 형태 (JSON 형태) 로 짝지음 body에 들어오는 데이터들을 가지고 오는 역할 --> Controller에서만 들어가는 부분
        //PostRequestDto : JSON 타입으로 넘어오는 데이터를 받는 객체(데이터를 저장할 공간)
        //requestDto : 매게변수
        //메개변수 requestDto 를 메소드 createPost 를 사용해서, Post 로 반환 PostService와 연결
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/api/posts/{id}")
    public Post searchPosts (@PathVariable Long id) {
        return postService.searchPosts(id);
    }

    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}/{password}")
    public String deletePost(@PathVariable Long id, @PathVariable String password) {
        postService.deletePost(id, password);

        String msg = "성공입니다.";
        return msg;
    }
}
