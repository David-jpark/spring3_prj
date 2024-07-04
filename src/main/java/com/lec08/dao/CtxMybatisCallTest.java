package com.lec08.dao;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class CtxMybatisCallTest {

	/**
	   기능 : xml을 읽어 해당 설정에 있는 (클래스들의 인스턴스 초기화) == (new)
	    <interface>				<class>
	    BeanFactory    			XmlBeanFactory
		ApplicationContext   	ClassPathXmlApplicationContext  : src/main/resources
								FileSystemXmlApplicationContext : full path
		WebApplicationContext	XmlWebApplicationContext        : + session request..
	 */
	
	
	public static void main(String[] args) {
		
        String xmlFile08 = "C:\\IT\\S3917_J11\\workspace_sts3\\spring3_prj\\src\\main\\webapp\\WEB-INF\\spring\\lec08-servlet-context.xml";
        ApplicationContext ctx = new FileSystemXmlApplicationContext(xmlFile08);

        //----------------------------------------------------------------------------
  		// Mybatis DBCP 설정을 통한 DB 연결
  		//   - 설정파일 : src/main/resources/mybatis-context.xml
  		//   - 매퍼파일 : src/main/resources/user-map.xml
  		//----------------------------------------------------------------------------
  		//Mybatis session build
  		String path = "mybatis-context-lec08.xml";
  		Reader reader; 
  		try {
  			// 아래는 myBatis API 문서에서 안내한 방법
  			reader = Resources.getResourceAsReader(path);
  			SqlSessionFactory sqlSessionFactory  = new SqlSessionFactoryBuilder().build(reader);
//  			SessionFactory sf = new Configuration().configure().buildSessionFactory(); -> hibernate
  			if(sqlSessionFactory == null)
  				System.out.println("err");
  			else
  				System.out.println("Mybatis session build ok");
  		
  			SqlSession session = sqlSessionFactory.openSession();
  			BoardVO vo = new BoardVO();
  			vo.setSeq(1);
  			
  			//1건 출력
  			// selectOne(mapper id)
  			vo = (BoardVO)session.selectOne("boardNameSpace.getBoardBySeq", vo);
  			System.out.println("DB에서 가져온 값:" + vo.getTitle());
  			
  			//목록 출력
  			List<BoardVO> list = session.selectList("boardNameSpace.getBoardReplyBySeq", vo);
  			System.out.println("DB에서 가져온 값:" + list.toString());
  			
  			
  			
  			//session.selectOne("boardNameSpace.getBoardBySeq", seq);
//  	구문 실행 기본 문법(메서드)
//  	Object selectOne(String statement, Object parameter)
//  	List selectList(String statement, Object parameter)
//  	Map selectMap(String statement, Object parameter, String mapKey) 
//  	int insert(String statement -> sql문장, Object parameter)
//  	int update(String statement, Object parameter)
//  	int delete(String statement, Object parameter
  			
//  			----------------------------------------------------------------
//  			 Mybatis를 사용한 CRUD 예
//  			UserVO , String id, String pw, int seq/pk, 
//  			
//  			----------------------------------------------------------------
  			UserVO uvo = new UserVO();
  			uvo.setUserId("user2");
  			uvo.setUserPw("999");
  			Integer res = session.insert("UserNameSpace.userInsert", uvo);
  			session.commit();
  			System.out.println("RES:" + res);
  			
  			System.out.println("\n\n----------------------");
  			ArrayList<UserVO> listUvo = (ArrayList)session.selectList("UserNameSpace.allUser");
  			for(int i=0; i<listUvo.size(); i++) {
  				uvo = listUvo.get(i);
  				System.out.println(uvo.getUserId() + "," + uvo.getUserSeq());
  			}
  			
  			uvo.setUserId("kim");
  			uvo.setUserPw("111"); 
  			uvo = session.selectOne("UserNameSpace.login", uvo);
  			System.out.println(uvo.getUserId());
  			
  			uvo.setUserPw("9990"); // update조건
  			uvo.setUserId("park"); //where조건
  			int ur = session.update("UserNameSpace.userUpdate", uvo);
  			session.commit();
  			System.out.println("upt건수" + ur);
  			
  			uvo.setUserSeq(5);
  			session.delete("UserNameSpace.userDelete", uvo);
  			System.out.println("delete 완료");
  			session.commit();
  			
  			
  			session.close();
  			
  		} catch(Exception e) {
  			e.printStackTrace();
  		}
       
        
        
	}

}
