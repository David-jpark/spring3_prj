package com.lec05.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lec05.rest.BoardDAO;
import com.lec05.rest.BoardVO;
import com.lec05.rest.ReplyVO;

@RestController
//@Controller + @ResponseBody
public class TestRestControllerForRest {
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping(value = "/restctl_str_str", method = RequestMethod.POST)
	public String estctlStrStr(@RequestParam("ename") String ename, Model model) {
		System.out.println(ename);
		
		return "200 ok";
	}

	//REST 댓글입력
	@RequestMapping(value = "/reply_insert_rest", method = RequestMethod.POST)
	public ResponseEntity<String> restReplyInsert(@ModelAttribute ReplyVO rvo ) {
		int insertRows = boardDAO.replyInsert(rvo);
		System.out.println("댓글 REST 정상");
	
		return new ResponseEntity<>("REST CON", HttpStatus.OK);
	}
	

	//REST 댓글삭제
	@RequestMapping(value = "/reply_del_rest", method = RequestMethod.POST)
	public ResponseEntity<String> restReplyDel(@RequestParam("rseq") int rseq ) {
		int delRows = boardDAO.deleteReply(rseq);
		System.out.println("댓글삭제 REST 정상");
	
		return new ResponseEntity<>("REST CON", HttpStatus.OK);
	}
	
	//REST 댓글목록 보기
	@RequestMapping(value = "/reply_list_rest", method = RequestMethod.POST
			, produces = "application/json")
	@ResponseBody
	public ResponseEntity<ArrayList<ReplyVO>> restReplyList(@RequestParam("seq") int seq) {
		ArrayList<ReplyVO> rlist = boardDAO.replyList(seq);
		
		return new ResponseEntity<>(rlist, HttpStatus.OK);
	}
	
	//REST 검색어 자동완성
	@RequestMapping(value = "/board_search", method = RequestMethod.POST
			, produces = "application/json")
	@ResponseBody
	public ResponseEntity<ArrayList<BoardVO>> restSearch(@RequestParam("search_str") String search_str) {
		ArrayList<BoardVO> rlist = boardDAO.selectBoardSearch(search_str);
		
		return new ResponseEntity<>(rlist, HttpStatus.OK);
	}
}
