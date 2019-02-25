package com.library.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.signUp.IUserBasicInfo;
import com.library.signUp.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

@Controller
public class libraryItems_demo extends HttpServlet implements WebMvcConfigurer {
//	@RequestMapping("/")
//	String entry() {
//
//		return "Home";
//	}

	@PostMapping("/process")
	public String process(HttpServletRequest request, HttpServletResponse response) {
		IUserBasicInfo basic = new UserBasicInfo();
		IUserExtendedInfo extended = new UserExtendedInfo();
		String abc = request.getParameter("uemail");
		basic.setEmail(request.getParameter("uemail"));
		basic.setPwd(request.getParameter("upass"));
		extended.setCPassword(request.getParameter("ucpass"));
		extended.setFullname(request.getParameter("uname"));
		extended.setPhone(request.getParameter("uphone"));
		SignUpController _signUpController = new SignUpController(basic, extended);
		request.setAttribute("uemail", "abc");
		return "process";
	}

	@PostMapping(value = "/signUp")
	public String responseBody() {
		return "signUp";
	}

	@GetMapping("/")
	public String showForm(User user) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "process";
    }
}
