<%@page import="org.jakabhegy.tools.Tools"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%=Tools.beforeBody("Hiba", "style.css") %>
	<article class="error_article"> <%=Tools.imgTag("img/error.png")%>
	<h2><%= session.getAttribute("error") %></h2>
	<%out.println("<h3>" + Tools.linkTag(session.getAttribute("reset").toString(), "Vissza") + "</h3>"); %>
	</article>
<%=Tools.afterBody() %>