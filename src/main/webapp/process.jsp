<%@page import="com.library.buisness.User"
import ="com.library.buisness.SignUpController"
%>  

<jsp:useBean id="obj" class="com.library.buisness.User"/>  

<jsp:setProperty property="*" name="obj"/>  
  
	<%
	User user = new User();
	if(request.getParameter("uname") == ""){
		out.print("FAIL");
	}
	user.setFullName(request.getParameter("uname"));
	user.setPassword(request.getParameter("upass"));
	user.setCpassword(request.getParameter("ucpass"));
	user.setEmail(request.getParameter("uemail"));
	user.setPhoneNumber(request.getParameter("uphone"));
	SignUpController object = new SignUpController(user);
	object.registerUser();
	User ab = object.call();
	out.println(ab.getEmail()+"--"+ab.getCpassword()+"--"+ab.getPassword()+"--"+ab.getFullName()+"--"+
			ab.getEmail()+"--"+ab.getPhoneNumber()+"--");%>
	
	out.print("You are successfully registered"+ request.getParameter("uname"));  
	
  
%>  