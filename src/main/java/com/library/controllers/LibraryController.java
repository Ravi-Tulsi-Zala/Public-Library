package com.library.controllers;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.signUp.SignUpController;
import com.library.signUp.User;

@Controller
public class LibraryController extends HttpServlet implements WebMvcConfigurer {

	@PostMapping("/signUp")
	public String process(ModelMap model, HttpServletRequest request, HttpServletResponse response, User user) {
		ArrayList<Map.Entry<String, String>> list = SignUpController.setUserData(user, request);
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute(list.get(i).getKey(), list.get(i).getValue());
		}
		if (model.size()>2) {
			user = null;
			return "SignUpForm";
		}
		return "Results";
	}
	
	@GetMapping("/signUp")
	public String showForm(User user) {
		return "SignUpForm";
	}

}
