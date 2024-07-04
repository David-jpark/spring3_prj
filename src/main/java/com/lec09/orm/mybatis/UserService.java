package com.lec09.orm.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	public int svcUserInsert(UserVO uvo);
		
	public ArrayList<UserVO> svcUserSelectAll();

	public UserVO svcUserSelectOne(UserVO uvo);
	
	public int svcUserUpdate(UserVO uvo);
	
	public int svcUserDelete(UserVO uvo);
	
	public void svcInsertRuntimeErrorFunc(UserVO uvo) throws RuntimeException ;
	
	public void svcInsertSQLErrorFunc(UserVO uvo) throws SQLException ;
	/**
	public int svcUserDelete2(int seq);

	public int svcUserDelete3(int seq);
	*/
}
