<%@page import="org.jakabhegy.pojo.Account"%>
<%@page import="org.jakabhegy.tools.Tools"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%=Tools.beforeBody("Bejelentkezés", "style.css") %>
	
	<%= Tools.makeHeader((Account)session.getAttribute("user")) %>
	
	<div class="form_cucc">

		<form action="LoginServlet" method="post" name="messageForm"
			accept-charset="UTF-8">

			<input type="text" name="username" placeholder="Név" required />
			<input type="password" name="password" placeholder="Jelszó" required/>
			<input type="submit" value="OK" />
		</form>
	<%out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>"); %>
	</div>
<%=Tools.afterBody() %>