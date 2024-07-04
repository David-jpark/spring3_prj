package com.lec09.orm.mybatis.mapper;

import java.util.ArrayList;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.lec09.orm.mybatis.UserVO;

@Repository //인터페이스 인데도 레파짓이 작동하는 이유 (메모리에 올라가지않음에도 불구하고)-> 
//UserMapper ump = new MapperCtxChildInstance("user-map")를 context가 읽음
//다형성으로 -> 부모타입의 인스턴스를 생성해줌
@Mapper
public interface UserMapper {
	
	public int userInsert(UserVO uvo);
	
	public ArrayList<UserVO> allUser();
	
	public UserVO userSelectOne(UserVO uvo);
	
	public int userUpdate(UserVO uvo);
	
	public int userDelete(UserVO uvo);


}
