<!DOCTYPE html>
<%@page import="org.jakabhegy.tools.Tools"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Egy kis �zenet</title>
<link rel="stylesheet" href="hello.css" type="text/css"></link>
</head>
<body>
	<form action="MessageServlet" method="post" name="messageForm">
		N�v: <input type="text" name="name" />
		<p></p>
		�zenet: <input type="text" name="message" />
		<p></p>
		<input type="submit" value="K�ld�s" />
	</form>
</body>
</html>