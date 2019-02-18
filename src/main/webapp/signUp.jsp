<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Getting Started: Serving Web Content</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
	<h1>Library items:</h1>
	<h2>Books, Movies, Music</h2>	
	<form action="process.jsp" method="post">  
<input type="text" name="uname" value="Name" onclick="this.value=''"/><br/>  
<input type="text" name="uemail"  value="Email-ID" onclick="this.value=''"/><br/>  
<input type="password" name="upass"  value="Password" onclick="this.value=''"/><br/>
<input type="password" name="ucpass"  value="Password" onclick="this.value=''"/><br/>
<input type="number" name="uphone"  value="Phone" onclick="this.value=''"/><br/>  
<input type="submit" value="register"/>  
</form>  

</body>
</html>