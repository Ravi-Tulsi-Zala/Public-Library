package com.library.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.interfaces.IUserBasicInfo;
import com.library.interfaces.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;
import com.library.singIn.SignInController;

@Controller
public class LibraryController extends HttpServlet implements WebMvcConfigurer {

	@PostMapping("/signUp")
	public String processSignUpForm(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			User user) {

		IUserExtendedInfo userExtendedInfo = new UserExtendedInfo();
		IUserBasicInfo userBasicInfo = new UserBasicInfo();
		userBasicInfo.setEmail(user.getEmail());
		userBasicInfo.setPwd(user.getPassword());
		userExtendedInfo.setCPassword(user.getCpassword());
		userExtendedInfo.setFullname(user.getFullName());
		userExtendedInfo.setPhone(user.getPhoneNumber());

		List<Map.Entry<String, String>> list = SignUpController.insertInDBIfAuthenticate(userBasicInfo,
				userExtendedInfo, request);
		for (int i = 0; i < list.size(); i++) {
			model.addAttribute(list.get(i).getKey(), list.get(i).getValue());
		}
		// model object has by default two values; anytime it gets more than that
		// signifies a validation violation
		if (model.size() > 2) {
			return "SignUpForm";
		}
		return "Results";
	}

	@GetMapping("/signUp")
	public String getSignUpForm(User user) {
		return "SignUpForm";
	}
	
	
//	@RequestMapping("/")
//	String entry() {
//		return "Home.jsp";
//	}
	
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
