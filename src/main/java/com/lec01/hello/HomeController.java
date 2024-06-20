package com.lec01.hello;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String boardSelect(Model model) {
	
		model.addAttribute("MY_ADDR", "서울" );
		return "board_select"; // prepix에 /board_select.jsp
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("MY_NAME", "박진우" );
		
//		<beans:property name="prefix" value="/" />
//		<beans:property name="suffix" value=".jsp" /> -> /home.jsp로 이동해라
		return "home";
	}
	
}
