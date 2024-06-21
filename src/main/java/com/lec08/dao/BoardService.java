package com.lec08.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

public interface BoardService {
	
	public int svcBoardInsert(BoardVO bvo);
		
	//board리스트보기
	public ArrayList<BoardVO> svcBoardList();
		
	//board상세보기
	public Map svcBoardDetail(int seq);

	//board수정
	public int svcBoardUpdate(BoardVO bvo);
		
	//board삭제
	public int svcBoardDel(BoardVO bvo);
	
	//Reply쓰기
	public int svcReplyInsert(ReplyVO rvo);

	//Reply삭제
	public int svcBoardDel(ReplyVO rvo);
	
	//Reply수정
	public int svcReplyUpdate(ReplyVO rvo);
}
