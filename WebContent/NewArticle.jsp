<%@page import="org.jakabhegy.pojo.Account"%>
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
<%=Tools.beforeBody("�j cikk", "style.css") %>
	<%= Tools.makeHeader((Account)session.getAttribute("user")) %>
	
	<div class="form_cucc">
	
		<form action="ArticleServlet" method="post" name="messageForm" 
			accept-charset="UTF-8">
			<input type="text" id="cim"name="title" placeholder="C�m" required/>
			<textarea rows="10" name="text" id="szoveg" placeholder="Sz�veg" required></textarea>
			<input type="submit" value="K�ld�s"/>
<%
			out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>");
		%>
		</form>
		
	</div>
	
<%=Tools.beforeBody("�j cikk", "style.css") %>