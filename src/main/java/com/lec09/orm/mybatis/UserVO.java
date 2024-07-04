package com.lec09.orm.mybatis;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString

@Component// VO -> spring에서 <bean> 인스터스 초기화 // JPA에서는 Entity
@Data // lombok // ToString, EqualsAndHashCode, Getter, Setter, RequiredArgsConstructor
public class UserVO {
	private int userSeq;
	private String userId;
	private String userPw;
	private String userName;
	private String userGubun;
	private String regDate;
	
}


