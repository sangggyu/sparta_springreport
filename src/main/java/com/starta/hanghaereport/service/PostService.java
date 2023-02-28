package com.starta.hanghaereport.service;

import com.starta.hanghaereport.dto.PostRequestDto;
import com.starta.hanghaereport.entity.Post;
import com.starta.hanghaereport.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //스프링이 Model계층의 서비스로 인식할 수 있게끔 하기위함
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //모델 계층
    //Post (@Entity) : 데이터베이스의 테이블 역할을한다.
    //PostRepository : 데이터베이스와 통신한다.
    //게시판의 핵심 비지니스 로직
    //1.작성게시글을 데이터베이스에 저장한다.

    @Transactional // 데이터베이스에 저장
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post); //Post 레포지토리가 데이터베이스에 Post를 저장한다.
        return post;

    }
    @Transactional(readOnly = true)
    public List<Post> getPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }
    @Transactional
    public Post searchPosts(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Transactional
    public Post update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            return post;
        } else {
            return post;
        }
    }
    @Transactional
    public void deletePost(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (post.getPassword().equals(password)) {
            postRepository.deleteById(id);

        }
    }
}


