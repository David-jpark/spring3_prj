package com.lec05.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lec05.rest.BoardDAO;
import com.lec05.rest.BoardVO;


@Controller
public class TestControllerForRest {
	/**
	@RequestMapping(value = "/board_detail", method = RequestMethod.POST
			, consumes = "application/json" // 서버가 받아들일 데이터(클라이언트의 요청 데이터)
			, produces = "application/json")// 서버가 내보낼 데이터(서버 응답 데이터
			
	@ResponseBody							// HTTP바디에 응답을 실어서 보냄
//	public String ctlSample(@RequestParam("seq") int seq, Model model) {
	public String ctlSample(@ModelAttribute BoardVO bvo) { //form의 입력값들
				
		return "200 ok"; 
	
	}
	*/
	
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping(value = "/ctl_str_str", method = RequestMethod.POST)
	@ResponseBody
	public String ctlStrStr(@RequestParam("ename") String ename, Model model) {
		System.out.println(ename);
		
		return "1. 200 ok";
	}
	
	@RequestMapping(value = "/ctl_json_str", method = RequestMethod.POST
				, consumes = "application/json")
	@ResponseBody
	public String ctlJsonStr(@RequestBody BoardVO bvo) {
		System.out.println(bvo.getRegid()+ "," + bvo.getTitle());
		
		return "2. 200 ok";
	}
	
	@RequestMapping(value = "/ctl_str_json", method = RequestMethod.POST
			, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String,String>> ctlStrJson(@RequestParam("ename") String ename) {
		System.out.println(ename);
		Map<String, String> map = new HashMap<String,String>();
		map.put("Status", "200");
		map.put("message", "ok");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ctl_json_json", method = RequestMethod.POST
			, consumes = "application/json"
			, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String,String>> ctlJsonJson(@RequestBody BoardVO bvo) {
		System.out.println(bvo.getRegid()+ "," + bvo.getTitle());
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("Status", "200");
		map.put("message", "ok");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ctl_normal", method = RequestMethod.POST
			, produces = "application/json")
	@ResponseBody
	public ResponseEntity<ArrayList<BoardVO>> ctlNormal(Model model, @ModelAttribute BoardVO bvo) {
		System.out.println(bvo.getRegid()+ "," + bvo.getTitle());
		ArrayList<BoardVO> list = boardDAO.selectBoard();
		
		return new ResponseEntity<ArrayList<BoardVO>>(list, HttpStatus.OK);
	}
}
