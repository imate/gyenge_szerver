<%@page import="org.jakabhegy.tools.Tools"%>
<%@page import="org.jakabhegy.pojo.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%=Tools.beforeBody("Regisztráció", "style.css") %>
	
	<%= Tools.makeHeader((Account)session.getAttribute("user")) %>
	
	<div class="form_cucc">

		<form action="RegServlet" method="post" name="messageForm"
			accept-charset="UTF-8">

			<input type="text" name="username" placeholder="Név" required />
			<input type="password" name="password" placeholder="Jelszó" required />
			<input type="password" name="password2" placeholder="Jelszó ismét" required />
			<input type="text" name="email" placeholder="Email" required />
			<input type="checkbox" id="checkme" required/>elfogadom a <%= Tools.linkTag("felhasznalasi_feltetelek.html", "felhasználási feltételek") %>et
			<input type="submit" name="reg" id="reg" value="Regisztráció" />
		</form>
		<%out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>"); %>
	</div>
<%=Tools.afterBody() %>