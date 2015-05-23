package org.jakabhegy.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;

import org.jakabhegy.dao.ArticleDao;
import org.jakabhegy.dao.MessageDao;
import org.jakabhegy.pojo.Article;
import org.jakabhegy.pojo.Message;
import org.jakabhegy.tools.Tools;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String PERSISTENCE_UNIT_NAME = "messages"; //$NON-NLS-1$
	private static EntityManagerFactory factory;
	private EntityManager em;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ArticleDao dao = new ArticleDao(em);
		List<Article> articles = dao.listAll("Article");
		
		String title =   Tools.stripHtmlRegex(request.getParameter("title")) ;
		String creator = Tools.stripHtmlRegex(request.getParameter("creator"));
		String text = Tools.stripHtmlRegex(request.getParameter("text"));
		Date actDate = Calendar.getInstance().getTime();
		Article article = new Article();
		article.setCreator(creator);
		article.setDate(actDate);
		article.setText(text);
		article.setTitle(title);
		dao.create(article);

		response.sendRedirect(response.encodeRedirectURL("ShowArticles.jsp"));
		
		
	}

}
