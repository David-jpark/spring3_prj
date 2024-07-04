package com.lec09.orm.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lec09.orm.mybatis.mapper.UserMapper;

@Service
//@Transactional //DataSourceTXManager를 토안 트랜잭션 관리 대상 : 문제발생 시 롤백 
				//AOP-Spring dependency추가
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;
	//------------------------------------------
	// 어노테이션 기반 트랜잭션 테스트 대상 메서드 @Transactional
	//------------------------------------------
	@Transactional
	
	//TX rollback test용
		//3건의 insert 중 에러 발생 시 TX에 의해 모두 rollback 처리됨
	public void svcInsertRuntimeErrorFunc(UserVO uvo) throws RuntimeException {
		mapper.userInsert(uvo);
		System.err.println("----------------- 모두 롤백처리 됨 ----------------");
		throw new RuntimeException("RuntimeException(Unchecked Exception) -- UserServiceImpl.svcInsertRuntimeErrorFunc() 강제 에러 발생");
	}
	
	public void svcInsertSQLErrorFunc(UserVO uvo) throws SQLException {
		mapper.userInsert(uvo);
		mapper.userInsert(uvo);
		System.err.println("----------------- 모두 롤백처리 됨 ----------------");
		throw new SQLException("SQLException(Checked Exception) -- UserServiceImpl.svcInsertSQLErrorFunc() 강제 에러 발생");
		
	}
		
	@Override
	public int svcUserInsert(UserVO uvo) {
		return mapper.userInsert(uvo);
		}
	@Override	
	public ArrayList<UserVO> svcUserSelectAll(){	
		return mapper.allUser();
		}
	@Override
	public UserVO svcUserSelectOne(UserVO uvo) {
		return mapper.userSelectOne(uvo);
		}
	@Override
	public int svcUserUpdate(UserVO uvo) {
		return mapper.userUpdate(uvo);
		}
	@Override
	public int svcUserDelete(UserVO uvo) {
		return mapper.userDelete(uvo);
		}
}
