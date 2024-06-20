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
import org.springframework.stereotype.Controller; 				//@Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;	//@RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
//import org.springframework.web.servlet.mvc.AbstractController;

//Spring Controller 방식
//@Controller
//<bean name="MY_EMPCONTROLLER_BEAN_NAME" class="com.lec04.di.EmpController">
public class EmpController extends MultiActionController {
	//------------------------Annotation 기반 ----------------------------
	//<bean name="MY_EMPDAO_BEAN_NAME" class="com.lec04.di.EmpDAO"/>
	//@Autowired // 건내달라는 annotation -> EmpDAO dao = new EmpDAO(); 
			   // 스프링 컨테이너가 dao인스턴스가 아래 멤버변수 dao에 주입
	//private EmpDAO dao;
	
	
	//-----------------------------XML 기반-------------------------------
	//프로퍼티(setter)
	private EmpDAO dao;
	public void setDao(EmpDAO dao){
		this.dao = dao;
	}
	
	private String str;
	public void setStringTest(String str) {
		this.str = str;
	}
	//생성자
//	private EmpDAO dao;
//	public EmpController(EmpDAO dao){
//		this.dao = dao;
//	}
	
//	@RequestMapping(value = "/emp_list_servlet", method = RequestMethod.GET)
	public ModelAndView empList(HttpServletRequest request, HttpServletResponse response) {
		// 무조건 public, 무조건 httpRequest, httpResponse를 매개변수로 사용
		
		//EmpDAO dao = new EmpDAO();
//		model.addAttribute("KEY_EMPLIST", list);
//		return "lec02_servlet";
		ModelAndView mav = new ModelAndView();
		//Model
		ArrayList<EmpVO> list = dao.empSelect();
		mav.addObject("KEY_EMPLIST", list);
		mav.addObject("KEY_TESTSTR", this.str);
		//View
		mav.setViewName("lec02_servlet");
		
		System.out.println("DI로 주입받은 스트링값" + this.str);
		return mav;
		
	}
	
	public ModelAndView empDumy(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		//Model
//		ArrayList<EmpVO> list = dao.empSelect();
		mav.addObject("KEY_EMPLIST", new ArrayList<EmpVO>());
		mav.addObject("KEY_TESTSTR", "empDumy : DumyTest");
		//View
		mav.setViewName("lec02_servlet");
		
		System.out.println("DI로 주입받은 스트링값" + this.str);
		return mav;
	}
//	@Override // 어노테이션을 사용하지 않고 controller로 동작하려고 abstract import 
//	import org.springframework.web.servlet.mvc.AbstractController;
//	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
