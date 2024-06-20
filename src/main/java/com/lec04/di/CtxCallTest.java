package com.lec04.di;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.lec03.spring.EmpDAO;
import com.lec03.spring.EmpVO;

public class CtxCallTest {
/*
 * 기능 : xml을 읽어 해당 설정에 있는 클래스들의 인스턴스 초기화 == (new)
 * <interface>						<class>
 * BeanFactory    			XmlBeanFactory
   ApplicationContext   	ClassPathXmlApplicationContext : src/main/resources
							FileSystemXmlApplicationContext: full path
   WebApplicationContext	XmlWebApplicationContext	   : + session path
 */
	public static void main(String[] args) {
		
		String xmlFile = "C:\\IT\\S3917_J11\\workspace_sts3\\spring3_prj\\src\\main\\webapp\\WEB-INF\\spring\\lec03-servlet-context.xml";
        ApplicationContext ctx = new FileSystemXmlApplicationContext(xmlFile);
        
        /*
        <beans:bean name="MY_IR_VIEW_RESOLVER_BEAN_NAME" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/" />
		<beans:property name="suffix" value=".jsp" />
		</beans:bean>
		import org.springframework.web.servlet.view.InternalResourceViewResolver;
		InternalResourceViewResolver irvr = new InternalResourceViewResolver(); 와 같다.
        */
        InternalResourceViewResolver ds = (InternalResourceViewResolver)ctx.getBean("MY_IR_VIEW_RESOLVER_BEAN_NAME");
        if(ds != null) {
        	System.out.println("인스턴스생성" + ds);
        } else {
        	System.out.println("인스턴스생성 실패");
        }
        
        //------POJO(Plan Old Java Object) 방식 - 결합도가 높아 비효율적---------
        EmpDAO dao = new EmpDAO();
        ArrayList<EmpVO> list = dao.empSelect();
        System.out.println(list.size() + "건 - new");
        //---------------------------------------------------------------
        
        //------------------DI(Dependency Injection) 방식-----------------
        // 결합도를 낮춘다, 효율적 방식 (재사용성, 확장)
        
        //<beans:bean name="MY_EMPDAO_BEAN_NAME" class="com.lec03.spring.EmpDAO"></beans:bean>
        // == EmpDAO dao = new EmpDAO();
        String xmlFile04 = "C:\\IT\\S3917_J11\\workspace_sts3\\spring3_prj\\src\\main\\webapp\\WEB-INF\\spring\\lec04-servlet-context.xml";
        ApplicationContext ctx4 = new FileSystemXmlApplicationContext(xmlFile04);
        EmpDAO sdao = (EmpDAO)ctx4.getBean("MY_EMPDAO_BEAN_NAME");
        
        ArrayList<EmpVO> list4 = sdao.empSelect();
        System.out.println(list4.size() + "건 - 스프링 사용");
        //---------------------------------------------------------------
        
        
//        DataSource ds = (DataSource)ctx.getBean("MY_IR_VIEW_RESOLVER_BEAN_NAME"); // MY_IR_VIEW_RESOLVER_BEAN_NAME는 bean의 name이다.
//        Connection conn;

	}

}
