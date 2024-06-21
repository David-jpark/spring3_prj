package com.lec08.dao;

import javax.naming.Context; 
import javax.naming.InitialContext; 
import javax.sql.DataSource; 
import java.sql.Connection; 
public class JNDCallTest {
	public static void main(String[] args) {
		try {
			Context initialContext = new InitialContext(); 
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/MyDataSource_MYNAME"); 
			Connection conn = ds.getConnection(); 
			//TODO
			if(conn != null) {
				System.out.println("conn ok");
			}else {
				System.out.println("faild");
			}
			conn.close(); 
		}
		catch (Exception e) {
			e.printStackTrace(); 
		}
	}
}
