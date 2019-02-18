package com.library.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class libraryItems_demo {
	@RequestMapping("/")
	String entry() {
		return "Home.jsp";
	}
	@GetMapping("/signUp")
	public String responseBody() {
		return "signUp.jsp";
	}	
	@PostMapping("/post")
	public @ResponseBody ResponseEntity<String> post() {
	    return new ResponseEntity<String>("POST Response", HttpStatus.OK);
	}
}
