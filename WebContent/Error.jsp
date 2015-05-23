<%@page import="org.jakabhegy.tools.Tools"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bejelentkezés</title>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<meta charset="UTF-8">
</head>
<body>
	<header>
	<h1>Hiba történt!</h1>
	</header>
	<article class="error_article"> <%=Tools.imgTag("img/error.png")%>
	<h2><%= session.getAttribute("error") %></h2>
	</article>
	</ul>
</body>
</html>