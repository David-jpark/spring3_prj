package com.lec04.di;

//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.stream.Collectors;

//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;	//@Autowired
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller; 				//@Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;	//@RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

//Spring Controller 방식
//<bean name="MY_EMPCONTROLLER_BEAN_NAME" class="com.lec04.di.EmpController">
//원래는 public class EmpController2 extends MultiActionController 형태

//1. 컨트롤러로 동작 : extends **Controller 하는 효과
//2. 인스턴스 생성	: <bean name="/empList" class="com.lec04.di.EmpController"/> 하는 효과 
@Controller
public class EmpController2 {
	//------------------------Annotation 기반 ----------------------------
//	<property name="dao" ref="MY_EMPDAO_BEAN_NAME" /> 를 한 효과 setter 대신
	@Autowired
	private EmpDAO dao;
	
//	<property name="stringTest" value="abcd" />
	@Value("abcd") // 값을 넣고 싶으면 Value
	private String str;

	@RequestMapping(value = "/emp_list", method = RequestMethod.GET)
	public String empList(Model model) {
		ArrayList<EmpVO> list = dao.empSelect();
		model.addAttribute("KEY_EMPLIST", list);
		model.addAttribute("KEY_TESTSTR", this.str);
		return "lec02_servlet";
		
	}
	
	@RequestMapping(value = "/emp_dummy", method = RequestMethod.GET)
	public String empDumy(Model model) {
		model.addAttribute("KEY_EMPLIST", new ArrayList<EmpVO>());
		model.addAttribute("KEY_TESTSTR", "empDumy : DumyTest");
		return "lec02_servlet";
	}
	
}
