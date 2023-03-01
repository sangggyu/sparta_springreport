package com.starta.hanghaereport.dto;

import com.starta.hanghaereport.entity.Post;
import lombok.Getter;
//게시글 작성 요청사항
@Getter
//PostRequestDto : 테이블의 데이터에 접근할 때, 완충재 역할
public class PostRequestDto {
    //Client 가 요청(request)한 데이터들 (username, contents, password, title --> 이 객체들 안에 데이터가 저장됨)을 DB로 넘긴다.
    //Dto는 어느 계층에도 속해있지 않음
    private String username;
    private String contents;
    private String password;
    private String title;




}
