<%@page import="com.library.signUp.UserExtendedInfo"%>
<%@page import="com.library.signUp.UserBasicInfo"%>
<%@page import="com.library.signUp.IUserExtendedInfo"%>
<%@page import="com.library.signUp.IUserBasicInfo"%>
<%@page import="com.library.signUp.User"
	import="com.library.signUp.SignUpController"%>

<jsp:useBean id="obj" class="com.library.signUp.User" />

<jsp:setProperty property="*" name="obj" />
<%
out.print("devanshu");
%>