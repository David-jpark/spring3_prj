package com.lec09.orm.mybatis;
//java platform
//JavaME (Mobile Edition)
//JavaSE (Standard Edition)
import java.util.ArrayList;

//JavaEE(Enterprise Edition)
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Resource(name="userServiceImpl") // 이름으로 가져감
	//@Autowired // 타입으로 가져감 -> impl이 여러개면 충돌이나 에러발생
	UserService userService; //확장성을 용이하게 인터페이스를 참조
	
	@RequestMapping(value = "/user_insert", method = RequestMethod.POST)
	public String ctlUserInsert (@ModelAttribute UserVO uvo) {
		int insertRows =  userService.svcUserInsert(uvo);
		return "redirect:/user_list";
	}
	@RequestMapping(value = "/user_list")	
	public String ctlUserSelectAll(Model model){	
		ArrayList<UserVO> list = userService.svcUserSelectAll();
		model.addAttribute("KEY_USERLIST", list);
		return "/hello";
	}
	@RequestMapping(value = "/user_one", method = RequestMethod.POST)
	public String ctlUserSelectOne (@ModelAttribute UserVO uvo, Model model) {
		uvo = userService.svcUserSelectOne(uvo);
		if( uvo != null) {
			System.out.println("로그인성공!");
			return "redirect:/user_list";
		} else {
			System.out.println("로그인실패!");
			return "redirect:/user_list";
		}
		
	}
	@RequestMapping(value = "/user_update", method = RequestMethod.GET)
	public String ctlUserUpdate (@ModelAttribute UserVO uvo) {
		int upt = userService.svcUserUpdate(uvo);
		return "redirect:/user_list";
	}
	@RequestMapping(value = "/user_del", method = RequestMethod.GET)
	public String ctlUserDelete (@ModelAttribute UserVO uvo) {
		int del = userService.svcUserDelete(uvo);
		return "redirect:/user_list";
	}
	/**
	//int 타입으로 받아오기
	@RequestMapping(value = "/user_del2", method = RequestMethod.GET)
	public String ctlUserDelete2 (@RequestParam("userSeq") int seq) {
		int del = userService.svcUserDelete2(seq);
		return "/hello";
	}
	//REST 받아오기 http://localhost:80~/user_delete/16/20240626
	@RequestMapping(value = "/user_delete/{aaa}/20240626", method = RequestMethod.GET)
	public String ctlUserDelete3( @PathVariable("aaa") int seq) {
		int res = userService.svcUserDelete3(seq);
		return "/hello";
	
	}
	*/
}
