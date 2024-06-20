package com.lec05.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lec05.rest.BoardDAO;
import com.lec05.rest.BoardVO;

public class RestServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RestServletTest() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		Gson gson = new Gson();
//		String ename = request.getParameter("ename");
//		System.out.println(ename);
//		
//		//-------응답 Str : 객체
//		BoardDAO dao = new BoardDAO();
//		ArrayList<BoardVO> list = dao.selectBoard();
//		//객체 리스트[VO]
//		
//		String getString = gson.toJson(list);
//		//스트링 리스트[JSON]
//		
//		PrintWriter out = response.getWriter();
//		out.print(getString);
//		
//		//-------응답 Str : 단순문자열
//		//out.print("200 ok");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
		
		//#####################json-str###############################
//		Gson gson = new Gson();
//		String jsonStr = request.getReader().lines().collect(Collectors.joining());
//		System.out.println(jsonStr+ ","+ jsonStr.getClass());
//		
//		//obj : JSON.stringify {"title":"asssd","regid":"hong"}
//		//str : "{"title":"asssd","regid":"hong"}" 모양 글자로
//		//fromJSON : 객체로~
//		BoardVO bvo = gson.fromJson(jsonStr, BoardVO.class);
//		System.out.println(bvo.getTitle());
//		PrintWriter out = response.getWriter();
//		
//		//-------응답 Str : ArrayList<VO>
//		BoardDAO dao = new BoardDAO();
//		ArrayList<BoardVO> list = dao.selectBoard();
//		//객체 리스트[VO]
//		
//		String getString = gson.toJson(list);
//		//스트링 리스트[JSON]
//		out.print(getString);
//		
//		//-------응답 Str : VO
//		//out.print(jsonStr);
//		
//		//-------응답 Str : 단순문자열
//		//out.print("200 ok");
//		//out.print("R003 응답성공");
		
		
		//######################String - JSON#####################
//		response.setContentType("application/json;charset=UTF-8");// 핵심 json타입으로 내보냄
//		
//		Gson gson = new Gson();
//		String ename = request.getParameter("ename");
//		System.out.println(ename);
//		PrintWriter out = response.getWriter();
//		
//		BoardDAO dao = new BoardDAO();
//		ArrayList<BoardVO> list = dao.selectBoard();
//		//객체 리스트[VO]
//		
//		String getString = gson.toJson(list);
//		//스트링 리스트[JSON]
//		out.print(getString);
//		
		//######################JSON - JSON ######################
		response.setContentType("application/json;charset=UTF-8");// 핵심 json타입으로 내보냄
		
		String jsonStr = request.getReader().lines().collect(Collectors.joining());
		System.out.println(jsonStr+ ","+ jsonStr.getClass());
		
		Gson gson = new Gson();
//		String ename = request.getParameter("ename");
//		System.out.println(ename);
		BoardVO bvo = gson.fromJson(jsonStr, BoardVO.class);
		System.out.println(bvo.getTitle());
		PrintWriter out = response.getWriter();
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.selectBoard();
		//객체 리스트[VO]
		
		String getString = gson.toJson(list);
		//스트링 리스트[JSON]
		out.print(getString);
		
	}
	

}
