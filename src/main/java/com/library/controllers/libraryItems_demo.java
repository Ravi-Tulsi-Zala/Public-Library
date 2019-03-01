package com.library.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.signUp.IUserBasicInfo;
import com.library.signUp.IUserExtendedInfo;
import com.library.signUp.SignUpController;
import com.library.signUp.User;
import com.library.signUp.UserBasicInfo;
import com.library.signUp.UserExtendedInfo;

@Controller
public class libraryItems_demo extends HttpServlet implements WebMvcConfigurer {

	@RequestMapping("/process")
	public String process(HttpServletRequest request, HttpServletResponse response,@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "signUpForm";
        }

		IUserBasicInfo basic = new UserBasicInfo();
		IUserExtendedInfo extended = new UserExtendedInfo();
		basic.setEmail(request.getParameter("email"));
		basic.setPwd(request.getParameter("password"));
		extended.setCPassword(request.getParameter("cpassword"));
		extended.setFullname(request.getParameter("fullName"));
		extended.setPhone(request.getParameter("phoneNumber"));
		SignUpController _signUpController = new SignUpController(basic, extended);
		return "results";
	}

//	@PostMapping(value = "/signUp")
//	public String responseBody() {
//		return "signUp";
//	}

	@GetMapping("/signUp")
	public String showForm(User user) {
        return "signUpForm";
    }

    @PostMapping("/signUp")
    public String checkPersonInfo(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signUpForm";
        }

        return "results";
    }
}
