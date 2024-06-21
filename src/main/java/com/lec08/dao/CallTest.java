package com.lec08.dao;

import java.util.ArrayList;

public class CallTest {

	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.selectBoard();
		System.out.println(list.size());

	}

}
