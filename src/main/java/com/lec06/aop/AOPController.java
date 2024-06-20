package com.lec06.aop;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lec04.di.EmpVO;

@Controller
public class AOPController extends MultiActionController{
	// void, map, String, modelAndview
	
	//property 방식으로 DI주입
	@Autowired
	private AOPService aOPService;

	public void setAOPService(AOPService svc) {
		this.aOPService = svc;
	}
	
	public void ctlDelete(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("1. AOPController.ctlDelete() 실행");
		aOPService.svcDelete();
		
	}
	
}

