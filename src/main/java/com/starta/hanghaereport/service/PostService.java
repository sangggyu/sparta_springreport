package com.starta.hanghaereport.service;

import com.starta.hanghaereport.dto.PostRequestDto;
import com.starta.hanghaereport.entity.Post;
import com.starta.hanghaereport.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //스프링이 Model계층의 서비스로 인식할 수 있게끔 하기위함 , 서비스라는 걸 알려주는 어노테이션
@RequiredArgsConstructor //final 선언할 때 스프링에게 알려줌
public class PostService {

    private final PostRepository postRepository; // PostRepository 와 연결 , final 은 서비스에게 꼭 필요함을 명시

    //모델 계층
    //Post (@Entity) : 데이터베이스의 테이블 역할을한다.
    //PostRepository : 데이터베이스와 통신한다.
    //게시판의 핵심 비지니스 로직
    //1.작성게시글을 데이터베이스에 저장한다.

    //게시글작성
    @Transactional // 데이터베이스에 저장, 업데이트 할 때, DB에 반영이 되는 것을 스프링에게 알려줌
    //Post 반환타입 , createPost 메소드
    //PostRequestDto JSON 타입으로 넘어오는 데이터를 받는 객체??
    //requestDto 매개변수 --> controller 에서 넘어온 ??
    public Post createPost(PostRequestDto requestDto) {
        //Post : Entity명 (테이블명)
        //매개변수 requestDto를 넣을 새로운 post 객체생성 -->텅빈상태
        //가지고온 데이터(requestDto) 를 넣음 ???
        Post post = new Post(requestDto);
        //데이터가 들어간 객체 post 를 postResponseDto 로 반환
        //(post): postRepository 에서 ?????????
        postRepository.save(post); // 리턴부분 설명 못하겠음 밑에도
        return post;

    }
    //전체 게시글 목록 조회
    @Transactional(readOnly = true)
    //Post : 반환타입, getPosts : 메소드명 () -> 전부 Client 에게로 반환하므로 비워둠
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }
    //선택한 게시글 조회
    @Transactional
    //Post : 반환타입, searchPosts : 메소드 () -> Client 에게 반환할 값
    public Post searchPosts(Long id) {
        return postRepository.findById(id).orElse(null);
    }
    //게시물 수정 (비밀번호가 일치하면)
    @Transactional
    //String : 반환타입, update : 메소드 () -> Client 에게 반환할 값
    //PostRequestDto JSON 타입으로 넘어오는 데이터를 받는 객체??
    //requestDto 매개변수 --> controller 에서 넘어온 ??
    public String update(Long id, PostRequestDto requestDto) {
        Post post = checkPost(id);
        //Post Entity에 update 메소드를 생성
        //설명이 필요함 어디 post 에서 패스워드를 가져와서 requestDto (필드값에 사용된 password와 비교해라 (equals)
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            return post.getTitle() + "의 게시물 수정 성공";
        } else {
            return post.getTitle() + "와 비밀번호가 일치하지 않습니다.";
        }
    }
    //게시물 삭제 (비밀번호가 일치하면)
    @Transactional
    //String : 반환타입, deletePost : 메소드 () -> Long 타입 id와 String 타입의 password 를 Client 에게 반환해
    public String deletePost(Long id, String password) {
        Post post = checkPost(id);
        if (post.getPassword().equals(password)) {
            postRepository.deleteById(id);

            return post.getTitle() + "의 게시물 삭제 성공";
        } else {
        }
        return post.getTitle() + "와 비밀번호가 일치하지 않습니다.";
    }

    private Post checkPost (Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }
}


