package com.lec03.spring;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Spring Controller 방식
@Controller
public class EmpController {
	@RequestMapping(value = "/emp_list_servlet", method = RequestMethod.GET)
	public String empList(Model model) {
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = dao.empSelect();
		model.addAttribute("KEY_EMPLIST", list);
		return "lec02_servlet";
	}
}


/*
//@WebServlet("/EmpServlet") 서블릿에서 불러오는법
@Controller //("/board") //-> /board/emp_spring으로 경로를 더줄수있다.			// 스프링에서 불러오는법
public class EmpController {
//	private static final long serialVersionUID = 1L;
	//private static final Logger logger = LoggerFactory.getLogger(EmpServlet.class);
       // logger를 쓸거면 만들고 아니면 없어도된다.
   
//    public EmpServlet() {
//        super();
// 
//    }
    @RequestMapping(value = "/emp_list_servlet", method = RequestMethod.GET) // 기존에 web.xml에 /emp가 있기 때문에 변경
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	public String empList(Model model) {	
//    	request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
    	
    	
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = dao.empSelect();
		model.addAttribute("KEY_EMPLIST", list);
		return "lec02_servlet";
		
		
//		System.out.println("총:" + list.size());
//		for(EmpVO evo : list) {
//			System.out.println(evo.getSal());
//		}
//		RequestDispatcher rd = request.getRequestDispatcher("lec02_servlet.jsp");
//		rd.forward(request, response);
	}


}
*/
