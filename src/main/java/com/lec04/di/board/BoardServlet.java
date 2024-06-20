//package com.lec04.di.board;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BoardServlet
 */
//@WebServlet("/BoardServlet")
//public class BoardServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    public BoardServlet() {
//        
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		
//		String pagecode = request.getParameter("pagecode");
//		// 게시물 목록보기
//		BoardDAO dao = new BoardDAO();
//		if(pagecode.equals("B001")) {
//			ArrayList<BoardVO> list = dao.selectBoard();
//			
//			request.setAttribute("KEY_BOARDLIST", list); // key, 값 => jsp와 servlet을 연결해서 값을 받아오는
//			RequestDispatcher rd = request.getRequestDispatcher("tables.jsp");
//			rd.forward(request, response);
//		
		// 게시물 상세보기	
//		} else if(pagecode.equals("B002")) {
//			//getParameter는 무조건 String이기 때문에 casting해서 seq를 담는다.
//			int seq = Integer.parseInt(request.getParameter("seq")); //**중요
//			BoardVO bvo = dao.selectBoardOne(seq);
////			BoardVO bbvo = dao.boardReplySelect(seq); 리플 게시물 한꺼번에
//			request.setAttribute("KEY_BOARDVO", bvo); // key, 값 => jsp와 servlet을 연결해서 값을 받아오는
//			
//			
//			ArrayList<ReplyVO> reply = (ArrayList<ReplyVO>)dao.replyList(seq);
//			request.setAttribute("KEY_REPLYLIST", reply);
//			
//			RequestDispatcher rd = request.getRequestDispatcher("tables_detail.jsp");
//			rd.forward(request, response);
//		} else if (pagecode.equals("B005")){
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			int rseq = Integer.parseInt(request.getParameter("rseq"));
//			int deleteRows = dao.deleteReply(rseq);
//			
//			if (deleteRows >=1) {
//				response.sendRedirect(request.getContextPath()+"/BoardServlet?pagecode=B002&seq="+seq);
//				
//			} else {
//				response.sendRedirect("500.html");
//			}
//			//상세보기 파라미터 : pagecode: B002 seq
//		}
//		
////		else {
////			response.sendRedirect("500.html");
////		}
//		
//		//DB목록 불러오기
//		
////		ArrayList<BoardVO> list = dao.selectBoard();
////		System.out.println("총 :" + list.size());
////		for(BoardVO bvo : list) {
////			int seq = bvo.getSeq();
////			String title = bvo.getTitle();
////			String contents = bvo.getContents();
////			String regid = bvo.getRegid();
////			String regdate = bvo.getRegdate();
////			System.out.println(seq+"\t"+title +"\t" + contents + "\t" + regid + "\t" + regdate);
////		}
//		
//		//DB의 list를 jsp에 넘기기 <글자 전송에 사용>
////				- response.sendRedirect("table.jsp?UID=kim"); 글자 전송에 사용
////				- response.sendRedirect("table.jsp");
//		
//		// <객체 전송에 사용>
////		page scope : 가져온 값을 어느 범위까지 공유해서 사용할 것인가?
////		page < request < session < application(tomcat의 공유된 모든 것)
//		
////		 session이기 때문에 페이지 종료시 까지 계속 변하지않고 남아있는다. 로그아웃시까지
////		HttpSession session = request.getSession();
////		session.setAttribute("KEY_SESS_USERID", uvo.getUserid());
//		
//		// sendRedirect 와 request 둘 중 하나만 사용 가능
////		request.setAttribute("KEY_BOARDLIST", list); // key, 값 => jsp와 servlet을 연결해서 값을 받아오는
////		RequestDispatcher rd = request.getRequestDispatcher("tables.jsp");
////		rd.forward(request, response);
//		
//		System.out.println("===request method : GET ===");
//
//		
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		response.getWriter().append("POST");
//		
//		String pagecode = request.getParameter("pagecode");
//		BoardDAO dao = new BoardDAO();
//		
//		if (pagecode.equals("B003")) {
//			System.out.println("003 post");
//			BoardVO vo = new BoardVO(); 
//			vo.setSeq(Integer.parseInt(request.getParameter("seq")));
//			vo.setTitle(request.getParameter("title"));
//			vo.setContents(request.getParameter("contents"));
//			int updateRows = dao.boardUpdate(vo);
//			
//			if(updateRows >= 1) {
//			String url = request.getContextPath()+ "/BoardServlet?seq="+request.getParameter("seq")+"&pagecode=B002";
//			response.sendRedirect(url);
//			} else {
//				response.sendRedirect("500.html");
//			}
//		} else if(pagecode.equals("B004")) {
//			System.out.println("004 post");
//			System.out.println(request.getParameter("seq"));
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			int deleteRows = dao.deleteBoard(seq);
//			
//			if(deleteRows >= 1) {
//				response.sendRedirect(request.getContextPath()+"/BoardServlet?&pagecode=B001");
//			} 
//			
//			else {
//				response.sendRedirect("500.html");
//			}
//			
//		} else if (pagecode.equals("B006")){
//			System.out.println("댓글 등록");
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			String reply = request.getParameter("reply");
//			ReplyVO rvo = new ReplyVO();
//			rvo.setSeq(seq);
//			rvo.setReply(reply);
//			
//			int insertRow = dao.replyInsert(rvo);
//				
//			if (insertRow >= 1) {
//			//상세보기 파라미터 : pagecode: B002 seq
//			response.sendRedirect(request.getContextPath()+"/BoardServlet?pagecode=B002&seq="+seq);
//		 }
//		}
//	
//		System.out.println("===request method : POST ===");
//	}
//
//}
