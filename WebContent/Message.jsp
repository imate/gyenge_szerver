<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="org.eclipse.persistence.internal.libraries.asm.tree.TryCatchBlockNode"%>
<%@page import="org.jakabhegy.pojo.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.jakabhegy.tools.Tools"%>
<%@page import="java.util.Vector"%>

<jsp:useBean id="messages" scope="session" class="java.util.Vector" />
<html>
<head>
<title>Egy kis üzenet</title>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<meta charset="UTF-8">
</head>
<body>
		
	<header>
		<h1>Küldj nekem egy nagy üzenetet!</h1>
	</header>
	<div class="form_cucc">
		<form action="MessageServlet" method="post" name="messageForm" accept-charset="UTF-8">
			<input type="text" name="name" placeholder="Név" />
			<textarea name="message" placeholder="Üzenet"></textarea>
			<input type="submit" value="Küldés" />
		</form>
	</div>
</body>
</html>