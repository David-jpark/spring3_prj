package com.lec04.di.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	//board게시글쓰기
	public int svcBoardInsert(BoardVO bvo) {
		int insertRows = boardDAO.boardInsert(bvo);
		return insertRows;
	}
		
	//board리스트보기
	public ArrayList<BoardVO> svcBoardList() {
		ArrayList<BoardVO> list = boardDAO.selectBoard();		
		return list; //lec04_board/board_list.jsp
		
	}
		
	//board상세보기
	public Map svcBoardDetail(int seq) {
		BoardVO bvo = boardDAO.selectBoardOne(seq);
		ArrayList<ReplyVO> reply = boardDAO.replyList(seq);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("my_bvo", bvo);
		map.put("my_rlist", reply);
		return map;
	}

	//board수정
	public int svcBoardUpdate(BoardVO bvo) {
		int updateRows = boardDAO.boardUpdate(bvo);
		System.out.println("게시글 수정 작업");
		return updateRows;
		
	}
	
	//board삭제
	public int svcBoardDel(BoardVO bvo) {
		int delRows = boardDAO.deleteBoard(bvo.getSeq());
		System.out.println("게시글 삭제 작업");
		return delRows;
		
	}
	
	//Reply쓰기
	public int svcReplyInsert(ReplyVO rvo) {
		int insertRows = boardDAO.replyInsert(rvo);
		System.out.println("댓글 쓰기 작업");
		return insertRows;	
	}

	//Reply삭제
	public int svcBoardDel(ReplyVO rvo) {
		int delRows = boardDAO.deleteReply(rvo.getRseq());
		System.out.println("댓글 삭제 작업");
		return delRows;
		
	}
	
	//Reply수정
	public int svcReplyUpdate(ReplyVO rvo) {
		int updateRows = boardDAO.ReplyUpdate(rvo);
		System.out.println("댓글 수정 작업");
		return updateRows;
		
	}
}
