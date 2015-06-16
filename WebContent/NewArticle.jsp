<%@page import="org.jakabhegy.pojo.Account"%>
<%@page import="org.jakabhegy.dao.ArticleDao"%>
<%@page import="org.jakabhegy.pojo.Article"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="org.jakabhegy.tools.Tools"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%=Tools.beforeBody("Új cikk", "style.css")%>
<%
	Account user = (Account) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect(response.encodeRedirectURL("Login.jsp"));
	}
%>
<%= Tools.makeHeader(user) %>
	<div class="form_cucc">
	
		<form action="ArticleServlet" method="post" name="messageForm" 
			accept-charset="UTF-8">
			<input type="text" id="cim"name="title" placeholder="Cím" required/>
			<textarea rows="10" name="text" id="szoveg" placeholder="Szöveg" required></textarea>
			<input type="submit" value="Küldés"/>
<%
			out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>");
		%>
		</form>
		
	</div>
	
<%=Tools.afterBody() %>