//Post 라는 테이블 생성

package com.starta.hanghaereport.entity;


import com.starta.hanghaereport.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //setter는 repository에서 자동으로 해주기 때문에 설정 안 함
@Entity  //데이터베이스 기준으로 테이블 역할을 하는 것을 스프링에게 알려줌 --> 데이터베이스로 데이터를 날린다
@NoArgsConstructor //기본생성자를 자동으로 만듬
public class Post extends Timestamped{ //Post 는 Entity 의 클래스명 <--Timestamped를 상속받는다.
    //필드
    //게시글 작성
    //필드값: Id, username(작성자), contents(내용), password(비밀번호), title(제목)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // nullable : null 허용여부
    // unique : 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false) //컬럼 값이고 false 이니 값이 존재해야 한다 ??????
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;


    //생성자
    //게시글 작성
    //Post: 객체  --> PostRepository 안에 데이터가 들어간 객체 Post 와 연결
    //requestDto : 객체 Post 안에 값을 넣어줄 생성자
    //this ???? requestDto ???? 내일 질문
    public Post(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }

    //메소드
    //업데이트

    public void update(PostRequestDto requestDto) {
        this.username = requestDto.getUsername(); //위의 필드값 username 에 Client 에게서 받아온 값(requestDto)을 넣음
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }




}
