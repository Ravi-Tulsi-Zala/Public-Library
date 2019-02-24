<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Getting Started: Serving Web Content</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<java> java.lang.String s = "deva"; </java>

<script>
alert(s);
function myFunc(){
	var d = s;
	alert(d);
}
function checkPassword(str)
{
  var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
  return re.test(str);
}

function checkForm(form)
{
  if(form.uname.value == "") {
    alert("Error: Username cannot be blank!");
    form.uname.focus();
    return false;
  }
  re = /^\w+$/;
  if(!re.test(form.uname.value)) {
    alert("Error: Username must contain only letters, numbers and underscores!");
    form.uname.focus();
    return false;
  }
  if(form.upass.value != "" && form.upass.value == form.ucpass.value) {
    if(!checkPassword(form.upass.value)) {
      alert("The password you have entered is not valid!");
      form.upass.focus();
      return false;
    }
  } else {
    alert("Error: Please check that you've entered and confirmed your password!");
    form.upass.focus();
    return false;	
  }
  return true;
}
function myFunction(this) {
	  var x = document.getElementById(this);
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }nj,
	} 
</script>

<body>
	<h1>Library items:</h1>
	<h2>Books, Movies, Music</h2>
	<form action="/process" name="signUpForm" method="post">
		<input type="text" name="uname" value="na%>" onclick="this.value=''" /><br />
		<input type="text" name="uemail" value="Email-ID"
			onclick="this.value=''" /><br /> <input type="password"
			name="upass" id="u_pass" value="Password" onclick="this.value=''" /><br />
		<input type="checkbox" onclick="myFunction(this)">Show
		Password <input type="password" name="ucpass" id="uc_pass"
			value="Password" onclick="this.value=''" /><br /> <input
			type="checkbox" onclick="myFunction(this)">Show Password <input
			type="number" name="uphone" value="Phone" onclick="this.value=''" /><br />
		<input type="submit" value="register" />
	</form>
	<script>
</script>

</body>
</html>