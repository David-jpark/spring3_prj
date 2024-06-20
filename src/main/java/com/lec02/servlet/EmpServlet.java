package com.lec02.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Java Servlet방식

//@WebServlet("/Emp")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public EmpServlet() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = dao.empSelect();
		request.setAttribute("KEY_EMPLIST", list);
//		System.out.println("총:" + list.size());
//		for(EmpVO evo : list) {
//			System.out.println(evo.getSal());
//		}
		RequestDispatcher rd = request.getRequestDispatcher("lec02_servlet.jsp");
		rd.forward(request, response);
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		
//		EmpDAO dao = new EmpDAO();
//		
//		ArrayList<EmpVO> list = dao.empSelect();
//		Gson gson = new Gson();
//		String jsonStr = gson.toJson(list);
//		PrintWriter out = response.getWriter();
//		out.print(jsonStr);
//		
//		response.setContentType("application/json; charset=UTF-8"); //json으로 내보냄
//		
//		EmpDAO dao = new EmpDAO();
//		ArrayList<EmpVO> list = dao.empSelect();
//		
//		
//		PrintWriter out = response.getWriter();
//		
//		request.setAttribute("KEY_EMPLIST", list);
//		RequestDispatcher rd = request.getRequestDispatcher("lec_chart.jsp");
//		rd.forward(request, response);
//
//		
//}

}
