
<%@page import="javax.persistence.Persistence"%>
<%@page import="org.jakabhegy.dao.MessageDao"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page
	import="org.eclipse.persistence.internal.libraries.asm.tree.TryCatchBlockNode"%>
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

		<form action="MessageServlet" method="post" name="messageForm"
			accept-charset="UTF-8">

			<textarea name="message" placeholder="Üzenet"></textarea>
			<input type="submit" value="Küldés" />
			<%
				out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>");
			%>
		</form>

	</div>

	<%
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("messages");
		EntityManager em = factory.createEntityManager();
		MessageDao dao = new MessageDao(em);
		List<Message> messageList = dao.listAll("Message");
	%><ul>
		<%
			int i = 1;
			for (Message message : messageList) {
		%>
		<li><article>
			<h1>
				<%
					out.println("#" + (i++) + " " + message.getAuthorName());
				%>
			</h1>
			<h2>
				<%
					out.println(message.getText());
				%>
			</h2>
			<h3>
				<%
					out.println(message.getFormattedDate());
				%>
			</h3>
			</article></li>



		<%
			}
		%>
	</ul>
</body>
</html>