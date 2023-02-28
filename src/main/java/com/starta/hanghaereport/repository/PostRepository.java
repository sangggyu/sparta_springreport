package com.starta.hanghaereport.repository;

import com.starta.hanghaereport.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//@Repository -> 스프링에 알려주면서 의존성 주입 (추후에 공부)
public interface PostRepository extends JpaRepository<Post, Long> {
    //Jparepository <Post, Long> 데이터 베이스의 Post table과 통신할 수 있게 됐다.
    List<Post> findAllByOrderByModifiedAtDesc();
}
