<%@page import="org.jakabhegy.pojo.Account"%>
<%@page import="org.jakabhegy.tools.Tools"%>
<%@page import="org.jakabhegy.pojo.Article"%>
<%@page import="java.util.List"%>
<%@page import="org.jakabhegy.dao.ArticleDao"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%=Tools.beforeBody("Cikkek", "style.css") %>
	
	<%= Tools.makeHeader((Account)session.getAttribute("user")) %>
	<% String text_search=request.getParameter("text_search");
	boolean search=text_search!=null;
	%>

<form method="post" action="ShowArticles.jsp" class="form_cucc" accept-charset="utf-8">
<input type="text" name="text_search" required />
<input type="submit"value="keres�s" />
</form>

<%
	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("messages");
	EntityManager em = factory.createEntityManager();
	ArticleDao dao = new ArticleDao(em);
	List<Article> articleList;
	if (search) {
		articleList = dao.searchInText(text_search);
	} else {
		articleList = dao.listAll("Article");
	}
%><ul><% 
		int i=1;
		for (Article article : articleList) {
			
			%> <li><article class="item">
			<h1><% out.println("#"+(i++)+" "+Tools.linkTag("ShowArticle?id="+article.getId(), article.getTitle()));%></h1>
			<!-- <h2><%= article.getText() %></h2> -->
			<h3><%= article.getAuthorName()+", "+article.getFormattedDate() %></h3>	
			</article></li>
		
			
	
		<% }%>
		</ul>
		<%out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>"); %>
<%=Tools.afterBody() %>