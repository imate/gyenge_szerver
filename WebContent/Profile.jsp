<%@page import="java.util.ArrayList"%>
<%@page import="org.jakabhegy.pojo.Article"%>
<%@page import="java.util.List"%>
<%@page import="org.jakabhegy.dao.ArticleDao"%>
<%@page import="org.jakabhegy.dao.AccountDao"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="org.jakabhegy.pojo.Account"%>
<%@page import="org.jakabhegy.tools.Tools"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("messages");
	EntityManager em = factory.createEntityManager();
	AccountDao accountDao = new AccountDao(em);
	ArticleDao articleDao = new ArticleDao(em);
	Account user = null;
	List<Article> articles = null;
	try {

		int user_id = Integer.parseInt(Tools.stripHtmlRegex(request
				.getParameter("id").toString()));

		user = accountDao.listOne(user_id);

		articles = articleDao.listByAuthor(user);

	} catch (Exception ex) {
		session.setAttribute("reset", "ShowArticles.jsp");
		session.setAttribute("error", "Nincs ilyen felhasználó!");
		response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
	}
%>
<%=Tools.beforeBody(user.getUsername(), "style.css")%>
<%=Tools.makeHeader(user)%>
<div class=profile>
	<h1><%=user.getUsername()%></h1>
	<%=Tools.imgTag(user.getImgPath())%>
	<%
		if (!articles.isEmpty()) {
	%>
	<%="<h2>" + user.getUsername() + " cikkei:</h2>"%>

	<%
		int i = 1;
			for (Article article : articles) {
	%>
	<%="<h2>#"
							+ (i++)
							+ " "
							+ Tools.linkTag(article.getArticleLink(),
									article.getTitle()) + "</h2>"%>
	<%
		}
	%>
	<%
		}
	%>
</div>
<%=Tools.afterBody()%>