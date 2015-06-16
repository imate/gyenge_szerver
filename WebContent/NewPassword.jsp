<%@page import="org.jakabhegy.pojo.Account"%>
<%@page import="org.jakabhegy.tools.Tools"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%=Tools.beforeBody("Új jelszó", "style.css") %>
	
	<%Account user=null;
	if (session.getAttribute("user") != null) {
		 user = (Account) session.getAttribute("user");
	}else{
		response.sendRedirect(response.encodeRedirectURL("Login.jsp"));	
	}

    %>
    
	<%= Tools.makeHeader((Account)session.getAttribute("user")) %>
	
	<div class="form_cucc">
	<form action="NewPasswordServlet" method="post" name="newPasswordForm"
		accept-charset="UTF-8">
		<input type="password" name="currentPassword" placeholder="Jelenlegi jelszó" required />
		<input type="password" name="newPassword1" placeholder="Új jelszó" required />
		<input type="password" name="newPassword2" placeholder="Új jelszó ismét" required />
		<input type="submit" value="Küldés" />
		<%
			out.println("<h2>" + Tools.linkTag("MyProfile.jsp", "Vissza")
					+ "</h2>");
		%>
	</form>
	</div>
<%=Tools.afterBody() %>