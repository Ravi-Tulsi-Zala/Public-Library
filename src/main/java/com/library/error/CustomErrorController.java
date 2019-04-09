package com.library.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

	//Snippet for error page is referred from https://www.baeldung.com/spring-boot-custom-error-page
	
	@RequestMapping("/error")
	  @ResponseBody
	  public String handleError(HttpServletRequest request) {
	  
	      return "Error";
	  }
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

	
}
