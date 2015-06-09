<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="org.jakabhegy.pojo.Account"%>
<%@page import="org.jakabhegy.tools.Tools"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%Account user=null;
	if (session.getAttribute("user") != null) {
		 user = (Account) session.getAttribute("user");
	}else{
		response.sendRedirect(response.encodeRedirectURL("Login.jsp"));	
	}

%>
<%=Tools.beforeBody(user.getUsername(), "style.css")%>
<%=Tools.makeHeader(user)%>
<div class=profile>
	<h1><%=user.getUsername()%></h1>
	<%=Tools.imgTag(user.getImgPath())%>

	<form action="ImgUploadServlet" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file" accept=".jpg" /> <input type="submit"
			value="upload" />
	</form>
	<h3>Email: <%=user.getEmail()  %></h3>
	<h3><%=Tools.linkTag("NewEmail.jsp", "Email cím megváltoztatása") %></h3>
	<h3>Regisztráció dátuma: <%= Tools.getFormattedDate(user.getRegDate()) %></h3>
	<h3> <%=Tools.linkTag("NewPassword.jsp", "Jelszó megváltoztatása")  %></h3>
	<h3><%=Tools.linkTag("ShowArticles.jsp?author_id=" + user.getId(),
			"cikkeim") %></h3>
	
</div>
<%
	out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>");
%>
<%=Tools.afterBody()%>