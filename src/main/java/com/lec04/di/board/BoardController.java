package com.lec04.di.board;

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
import java.util.Map;

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
	//private BoardDAO boardDAO;
	private BoardService boardService; //부모타입_인터페이스 = new 자식(); -> 다형성 // service 연결
	
	//board게시글쓰기
	@RequestMapping(value = "/board_insert", method = RequestMethod.POST)
	public String ctlBoardInsert(@ModelAttribute BoardVO bvo) {
		int insertRows = boardService.svcBoardInsert(bvo);
		return "redirect:/board_list";
		// "forword:/board_list"; 값까지 같이 이동하려면!
		
	}
	
	//board리스트보기
	@RequestMapping(value = "/board_list")
	public String ctlBoardList(Model model) {
		ArrayList<BoardVO> list = boardService.svcBoardList();
		model.addAttribute("KEY_BOARDLIST", list); // key, 값 => jsp와 servlet을 연결해서 값을 받아오는
		
		return "lec04_board/board_list"; //lec04_board/board_list.jsp
		
	}
	
	//board상세보기
	@RequestMapping(value = "/board_detail")
	public String ctlBoardDetail(@RequestParam("seq") int seq, Model model) {
		Map map = boardService.svcBoardDetail(seq);
		model.addAttribute("KEY_BOARDVO", (BoardVO)map.get("my_bvo")); // key, 값 => jsp와 servlet을 연결해서 값을 받아오는
		model.addAttribute("KEY_REPLYLIST", (ArrayList<ReplyVO>)map.get("my_rlist"));
		
//		BoardVO bvo = boardDAO.boardReplySelect(seq); 게시글 댓글 JOIN으로 한번에 보여주기
//		model.addAttribute("KEY_BORDVO", bvo);
		return "lec04_board/board_detail";
	}
	
	//board수정
	@RequestMapping(value = "/board_update", method = RequestMethod.POST)
	public String ctlBoardUpdate(@ModelAttribute BoardVO bvo) {
		int updateRows = boardService.svcBoardUpdate(bvo);
		System.out.println("게시글 수정 작업");
		return "redirect:/board_list";
		
	}
	
	//board삭제
	@RequestMapping(value = "/board_del", method = RequestMethod.POST)
	public String ctlBoardDel(@ModelAttribute BoardVO bvo) {
		int delRows = boardService.svcBoardDel(bvo);
		System.out.println("게시글 삭제 작업");
		return "redirect:/board_list";
		
	}
	
	//Reply쓰기
	@RequestMapping(value = "/reply_insert", method = RequestMethod.POST)
	public String ctlReplyInsert(@ModelAttribute ReplyVO rvo) {
		int insertRows = boardService.svcReplyInsert(rvo);
		System.out.println("댓글 쓰기 작업");
		return "redirect:/board_detail?seq="+ rvo.getSeq();	// board_detail?seq=3
		//return "forward:/board_detail"; 새로고침할때마다 계속넘어감 옳지않음 board_detail rvo객체가 넘어감
	}
	
	//Reply REST
	
	
	//Reply삭제
		@RequestMapping(value = "/reply_del", method = RequestMethod.GET)
		public String ctlBoardDel(@ModelAttribute ReplyVO rvo) {
			int delRows = boardService.svcBoardDel(rvo);
			System.out.println("댓글 삭제 작업");
			return "redirect:/board_detail?seq="+ rvo.getSeq();
			
		}
	
	//Reply수정
	@RequestMapping(value = "/reply_update", method = RequestMethod.POST)
	public String ctlReplyUpdate(@ModelAttribute ReplyVO rvo) {
		int updateRows = boardService.svcReplyUpdate(rvo);
		System.out.println("댓글 수정 작업");
		return "redirect:/board_detail?seq="+ rvo.getSeq();
		
	}
}
