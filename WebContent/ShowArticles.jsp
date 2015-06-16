<%@page import="org.jakabhegy.dao.AccountDao"%>
<%@page import="org.jakabhegy.pojo.Account"%>
<%@page import="org.jakabhegy.tools.Tools"%>
<%@page import="org.jakabhegy.pojo.Article"%>
<%@page import="java.util.List"%>
<%@page import="org.jakabhegy.dao.ArticleDao"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%=Tools.beforeBody("Cikkek", "style.css") %>

<%= Tools.makeHeader((Account)session.getAttribute("user")) %>
<% String text_search=request.getParameter("text_search");
	int author_id;
	try{
		author_id=Integer.parseInt(request.getParameter("author_id").toString());
	}catch(Exception e)
	{author_id=0;}
	boolean search=text_search!=null;
	boolean author=author_id>0;
	%>

<form method="post" action="ShowArticles.jsp" class="form_cucc"
	accept-charset="utf-8">
	<input type="text" name="text_search" required /> <input type="submit"
		value="keresés" />
</form>

<%
	EntityManagerFactory factory = Persistence
	.createEntityManagerFactory("messages");
	EntityManager em = factory.createEntityManager();
	ArticleDao articleDao = new ArticleDao(em);
	AccountDao accountDao = new AccountDao(em);
	List<Article> articleList;
	if (search) {
		articleList = articleDao.searchInText(text_search);
	} else if (author) {
		articleList = articleDao.listByAuthor(accountDao.listOne(author_id));
	} else {
		articleList = articleDao.listAll();
	}
%><ul>
	<% 
		int i=1;
		for (Article article : articleList) {
			
			%>
	<li><article class="item">
		<h1>
			<% out.println("#"+(i++)+" "+Tools.linkTag("ShowArticle?id="+article.getId(), article.getTitle()));%>
		</h1>
		<!-- <h2><%= article.getText() %></h2> -->
		<h3><%= Tools.linkTag(article.getAuthor().getProfileLink(), article.getAuthorName())+" | "+Tools.getFormattedDate(article.getDate())+" | "+article.getMessageCount() %></h3>
		</article></li>



	<% }%>
</ul>
<%out.println("<h2 style='text-align: center;'>" + Tools.linkTag("Hello", "vissza a főoldalra") + "</h2>"); %>
<%= Tools.afterBody() %>