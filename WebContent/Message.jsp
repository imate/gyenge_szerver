<!DOCTYPE html>
<%@page import="org.jakabhegy.tools.Tools"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Egy kis üzenet</title>
<link rel="stylesheet" href="hello.css" type="text/css"></link>
</head>
<body>
	<form action="MessageServlet" method="post" name="messageForm">
		Név: <input type="text" name="name" />
		<p></p>
		Üzenet: <input type="text" name="message" />
		<p></p>
		<input type="submit" value="Küldés" />
	</form>
</body>
</html>