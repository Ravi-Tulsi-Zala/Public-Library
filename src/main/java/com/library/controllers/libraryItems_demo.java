package com.library.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.singIn.IUserBasicInfo;
import com.library.singIn.SignInController;
import com.library.singIn.UserBasicInfo;

@Controller
public class libraryItems_demo extends HttpServlet implements WebMvcConfigurer{
	@RequestMapping("/")
	String entry() {
		return "Home.jsp";
	}
	
	@GetMapping("/signInProcess")
	public String responseBody() {
		return "signInForm";
	}
	
	@PostMapping("/signInProcess")
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		IUserBasicInfo basic = new UserBasicInfo();
		basic.setEmail(request.getParameter("uemail"));
		basic.setPwd(request.getParameter("uname"));
		SignInController _signInController = new SignInController(basic);
		return "results";
	}
	
}
