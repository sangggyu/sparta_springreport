//자동으로 현재 시간을 보여줌

package com.starta.hanghaereport.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter //Getter가 없으면 작동이 안 됨
@MappedSuperclass //상속했을 때, 자동으로 컬럼으로 인식
// Springboot 에 @EnableJpaAuditing 을 추가해줘야지만, 아래 코드가 정상 작동함  <-- 사실 잘 모르겠음 뭔말인지
@EntityListeners(AuditingEntityListener.class) //생성,수정 시간을 자동으로 반영하도록 설정
public class Timestamped {
    //createdAt, modifiedAt 컬럼 2개를 가진다
    @CreatedDate    //생성일자
    private LocalDateTime createdAt;

    @LastModifiedDate //마지막 수정일자
    private LocalDateTime modifiedAt;
}
//@Column(updatable = false)	처음 작성된 날짜는 다음 날짜로 업데이트되더라도 변화 X (기존값 유지를 위해)

