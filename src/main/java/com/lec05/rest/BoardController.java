package com.lec05.rest;

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

import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;	//@Autowired
import org.springframework.stereotype.Controller;				//@Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;	//@ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping;	//@RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BoardController {
	@Autowired
	private BoardDAO boardDAO;
	
	//board게시글쓰기
	@RequestMapping(value = "/board_insert", method = RequestMethod.POST)
	public String ctlBoardInsert(@ModelAttribute BoardVO bvo) {
		int insertRows = boardDAO.boardInsert(bvo);
		return "redirect:/board_list";
		// "forword:/board_list"; 값까지 같이 이동하려면!
		
	}
	
	//board리스트보기
	@RequestMapping(value = "/board_list")
	public String ctlBoardList(Model model) {
		ArrayList<BoardVO> list = boardDAO.selectBoard();
		model.addAttribute("KEY_BOARDLIST", list); // key, 값 => jsp와 servlet을 연결해서 값을 받아오는
		
		return "lec05_board/board_list"; //lec04_board/board_list.jsp
		
	}
	
	//board상세보기
	@RequestMapping(value = "/board_detail")
	public String ctlBoardDetail(@RequestParam("seq") int seq, Model model) {
		// 상세만 가져가고, 댓글목록은 (REST로 출력)
		
		BoardVO bvo = boardDAO.selectBoardOne(seq);
		model.addAttribute("KEY_BOARDVO", bvo); // key, 값 => jsp와 servlet을 연결해서 값을 받아오는
		//ArrayList<ReplyVO> reply = (ArrayList<ReplyVO>)boardDAO.replyList(seq);
		//model.addAttribute("KEY_REPLYLIST", reply);
		
		//REST에서는 댓글과 게시글을 따로 보는게 좋다. JOIN을 안쓰는게 낫다.
//		BoardVO bvo = boardDAO.boardReplySelect(seq); 
//		model.addAttribute("KEY_BORDVO", bvo);
		return "lec05_board/board_detail";
	}
	
	//board수정
	@RequestMapping(value = "/board_update", method = RequestMethod.POST)
	public String ctlBoardUpdate(@ModelAttribute BoardVO bvo) {
		int updateRows = boardDAO.boardUpdate(bvo);
		System.out.println("게시글 수정 작업");
		return "redirect:/board_list";
		
	}
	
	//board삭제
	@RequestMapping(value = "/board_del", method = RequestMethod.POST)
	public String ctlBoardDel(@ModelAttribute BoardVO bvo, int seq) {
		int delRows = boardDAO.deleteBoard(seq);
		System.out.println("게시글 삭제 작업");
		return "redirect:/board_list";
		
	}
	
	//Reply쓰기
//	@RequestMapping(value = "/reply_insert", method = RequestMethod.POST)
//	public String ctlReplyInsert(@ModelAttribute ReplyVO rvo, int seq) {
//		int insertRows = boardDAO.replyInsert(rvo);
//		System.out.println("댓글 쓰기 작업");
//		return "redirect:/board_detail?seq="+ seq;	// board_detail?seq=3
//		//return "forward:/board_detail"; 새로고침할때마다 계속넘어감 옳지않음 board_detail rvo객체가 넘어감
//	}
	
	//Reply REST
	
	
	//Reply삭제
		@RequestMapping(value = "/reply_del", method = RequestMethod.GET)
		public String ctlBoardDel(@ModelAttribute ReplyVO rvo, int rseq, int seq) {
			int delRows = boardDAO.deleteReply(rseq);
			System.out.println("댓글 삭제 작업");
			return "redirect:/board_detail?seq="+ seq;
			
		}
	
	//Reply수정
	@RequestMapping(value = "/reply_update", method = RequestMethod.POST)
	public String ctlReplyUpdate(@ModelAttribute ReplyVO rvo, int seq) {
		int updateRows = boardDAO.ReplyUpdate(rvo);
		System.out.println("댓글 수정 작업");
		return "redirect:/board_detail?seq="+ seq;
		
	}
}
