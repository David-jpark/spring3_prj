package com.lec05.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lec05.rest.BoardDAO;
import com.lec05.rest.BoardVO;
import com.lec05.rest.ReplyVO;

@Controller
public class BoardRestController {
	
	@Autowired
	private BoardDAO boardDAO;
	
	
	
	//http://localhost:8088/_ctxpath_/board_detail_rest?seq=3
	@RequestMapping(value = "/board_detail_rest")
	public String ctlBoardDetail(@RequestParam("seq") int seq, Model model) {
		BoardVO bvo = boardDAO.selectBoardOne(seq);
		model.addAttribute("KEY_BOARDVO", bvo); // key, 값 => jsp와 servlet을 연결해서 값을 받아오는
		ArrayList<ReplyVO> reply = (ArrayList<ReplyVO>)boardDAO.replyList(seq);
		model.addAttribute("KEY_REPLYLIST", reply);
		
//		BoardVO bvo = boardDAO.boardReplySelect(seq); 게시글 댓글 JOIN으로 한번에 보여주기
//		model.addAttribute("KEY_BORDVO", bvo);
		return "lec05_board/board_detail";
	}
	
	
	
	
	
	
	
}
