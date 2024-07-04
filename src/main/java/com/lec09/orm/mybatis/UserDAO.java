//package com.lec09.orm.mybatis;
//
//import java.util.ArrayList;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class UserDAO {	
//	@Autowired
//	SqlSession session; 
//	//SqlSessionTemplate 생성자함수 호출 // 핵심!
//	
//	public int userInsert(UserVO uvo) {
//		return session.insert("UserNameSpace.userInsert", uvo);
//		}
//		
//	public ArrayList<UserVO> userSelectAll(){	
//		return (ArrayList)session.selectList("UserNameSpace.allUser");
//		}
//
//	public UserVO userSelectOne(UserVO uvo) {
//		return session.selectOne("UserNameSpace.login", uvo);
//		}
//	
//	public int userUpdate(UserVO uvo) {
//		return session.update("UserNameSpace.userUpdate", uvo);
//		}
//	
//	public int userDelete(UserVO uvo) {
//		return session.delete("UserNameSpace.userDelete", uvo);
//		}
//
//}
