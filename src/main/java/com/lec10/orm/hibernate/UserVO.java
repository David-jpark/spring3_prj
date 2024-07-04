package com.lec10.orm.hibernate;

import lombok.Data;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


//@Entity  -> user-map-lec10에 지정되어있음
//@Table(name = "users3")
//POJO식 VO -> JPA방식으로 @Column을 쓰는 방식이 더 좋다.
@Data
public class UserVO {
    
    private Long userSeq;
    private String userId;
    private String userPw;
    private String userName;
    private char userGubun = 'u';
    private Date regdate = new Date();

    // lombok : getters and setters
}