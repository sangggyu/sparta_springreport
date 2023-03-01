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
        //RequestBody: Post 안에 저장된 body 값들을 key:value 형태 (JSON 형태) 로 짝지음 body 에 들어오는 데이터들을 가지고 오는 역할 --> Controller에서만 들어가는 부분
        //PostRequestDto : JSON 타입으로 넘어오는 데이터를 받는 객체(데이터를 저장할 공간)
        //requestDto : 매개변수
        //메개변수 requestDto 를 메소드 createPost 를 사용해서, Post 로 반환 PostService 와 연결
        return postService.createPost(requestDto);
    }
    //게시글 목록 조회
    @GetMapping("/api/posts") //request 종류 Get
    public List<Post> getPosts(){ //List타입 , Post는 반환타입, 메소드명 getPost, () : 전부 Client 에게로 반환하므로 비워둠
        //getPost 메소드를 사용해서 postService로 연결
        return postService.getPosts();
    }
    //선택한 게시글 조회
    @GetMapping("/api/posts/{id}") //request 종류 Get
    public Post searchPosts (@PathVariable Long id) { //Post  반환타입, 메소드명 searchPosts
        //@PathVariable : URL 경로에 변수를 넣어주는것
        //@PathVariable Long id는 PathVariable 방식으로 Long 타입 id를 가져온다 --> 전체 게시글 중 id 값으로 각각의 게시글 구별
        return postService.searchPosts(id);
        //id 값을 담은 searchPosts 메소드를 사용해서 , postService 와 연결
    }
    //선택한 게시글 수정
    @PutMapping("/api/posts/{id}") //request 종류 Put
    public String updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){ //Post 반환타입
        //requestDto 는 매개변수
        //id 값을 담은 getBoard 메소드를 사용해서, boardService 와 연결
        return postService.update(id, requestDto);
        //(id, requestDto): requestDto 에 가져올 내용으로 id 값만 적어서 id만 나오는 건가????
    }
    //선택한 게시글 삭제
    @DeleteMapping("/api/posts/{id}/{password}")//request 종류: DELETE
    public String deletePost(@PathVariable Long id, @PathVariable String password) {
        //@PathVariable 방식으로 Long 타입 id 와 String 타입 password 를 가져온다.
        //id 랑 password 는 어디서 가져오는거지????????????
       return postService.deletePost(id, password);
    }
}
