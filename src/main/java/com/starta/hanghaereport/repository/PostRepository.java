package com.starta.hanghaereport.repository;

import com.starta.hanghaereport.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//게시글 작성
//jpa: 미리 검색 메소드를 정의 해 두는 것, 메소드를 호출하는 것만으로 스마트한 데이터 검색 가능
//JpaRepository: Entity 에 있는 데이터를 조회, 저장, 변경, 삭제 할때 Spring JPA 에서 제공하는 Repository 라는 인터페이스를 정의해 해당 Entity 의 데이터를 사용.
//@Repository: JpaRepository 내부에 자동으로 Bean 으로 등록될 수 있는 옵션이 들어가 있으므로, 생략 가능하고 Bean 으로 자동 등록됨
//@Repository -> 스프링에 알려주면서 의존성 주입 (추후에 공부)
public interface PostRepository extends JpaRepository<Post, Long> { //<Entity 클래스 이름, ID 필드 타입>
    //JpaRepository <Post, Long> 데이터 베이스의 Post table 과 통신할 수 있게 됐다.
    List<Post> findAllByOrderByModifiedAtDesc(); //모두 불러와 id에 대해 내림차순 정렬
}
