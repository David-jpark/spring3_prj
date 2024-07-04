package com.lec11.orm.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lec11.orm.jpa.entity.UserEntity;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/jpa_login" ,method = RequestMethod.POST)
	public String ctlUserLogin(ModelMap model, @ModelAttribute UserEntity userEntity) {
		userEntity = userService.svcUserLogin(userEntity.getUserId(), userEntity.getUserPw());
		model.addAttribute("MY_ENTITY", userEntity);
		if( userEntity != null) {
			System.out.println("로그인성공!");
			return "redirect:/helloJPA.jsp";
		} else {
			System.out.println("로그인실패!");
			return "redirect:/helloJPA.jsp";
		}
		
	}
	
	@RequestMapping(value="/jpa_list")
	public String ctlUserList(ModelMap model) {
		List<UserEntity> list = userService.svcUserList();
		model.addAttribute("MY_LIST", list);
		System.out.println(list.toString());
		return "helloJPA";
	}
	
	@RequestMapping(value="/jpa_detail/{aaa}")
	public String ctlUserDetail(ModelMap model, @PathVariable("aaa") Long userSeq) {
		UserEntity entity = userService.svcUserDetail(userSeq);
		model.addAttribute("MY_ENTITY", entity );
		System.out.println(entity.toString());
		return "helloJPA";
	}
	
	@RequestMapping(value="/jpa_update")
	public String ctlUserUpdate(@ModelAttribute UserEntity userEntity) {
		UserEntity entity = userService.svcUserUpdate(userEntity);
		if(entity != null) {
			System.out.println(entity.toString()+"\n 변경완료");
		} else {
			System.out.println("유저가 없습니다.");
		}
		return "redirect:/helloJPA.jsp";
	}
	
	@RequestMapping(value="/jpa_del",method = RequestMethod.GET)
	public String ctlUserDel(@ModelAttribute UserEntity userEntity) {
		UserEntity entity = userService.svcUserDel(userEntity);
		if(entity != null) {
			System.out.println(entity.toString()+"\n 삭제완료");
		} else {
			System.out.println("유저가 없습니다.");
		}
		return "redirect:/helloJPA.jsp";
	}
}
