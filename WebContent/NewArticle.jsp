<%@page import="org.jakabhegy.dao.ArticleDao"%>
<%@page import="org.jakabhegy.pojo.Article"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="org.jakabhegy.tools.Tools"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�j cikk</title>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<meta charset="UTF-8">
</head>
<body>
	<header>
	<h1>J�het az emberi cikk!</h1>
	</header>
	<div class="form_cucc">

		<form action="ArticleServlet" method="post" name="messageForm"
			accept-charset="UTF-8">
			<input type="text" name="creator" placeholder="N�v" />
			<input type="text" name="title" placeholder="C�m" />
			<textarea name="text" placeholder="Sz�veg"></textarea>
			<input type="submit" value="K�ld�s" />
<%
			out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>");
		%>
		</form>
		
	</div>
	
</body>
</html>