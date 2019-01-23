<%--
  Copyright (c) Microsoft Corporation. All rights reserved.
  Licensed under the MIT License. See License.txt in the project root for
  license information.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.InetAddress" %>
<html>
<head>
  <title>Azure App Demo</title>
</head>
<body>
 	<H1>Azure App Demo - Jenkins</H1>
	<h2>
		<%
			InetAddress ia = InetAddress.getLocalHost();
			String node = ia.getHostName();
			out.println(node);
		%>
	</h2>
	<h3>
		<% out.println(ia.toString());%>
	</h3>
	<p>hello DevanshuSrivastava!</p>
</body>
</html>
