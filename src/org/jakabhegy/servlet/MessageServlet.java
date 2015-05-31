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
import javax.servlet.http.HttpSession;

import org.jakabhegy.dao.ArticleDao;
import org.jakabhegy.dao.MessageDao;
import org.jakabhegy.pojo.Account;
import org.jakabhegy.pojo.Message;
import org.jakabhegy.tools.Tools;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PERSISTENCE_UNIT_NAME = "messages"; //$NON-NLS-1$
	private static EntityManagerFactory factory;
	private EntityManager em;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account user = (Account) session.getAttribute("user");

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		MessageDao messageDao = new MessageDao(em);
		ArticleDao articleDao = new ArticleDao(em);

		// request.getSession().setAttribute("students", students);

		int articleId = Integer.parseInt(request.getParameter("articleId"));
		String text = Tools.stripHtmlRegex(request.getParameter("message"));
		Date actDate = Calendar.getInstance().getTime();
		Message message = new Message();
		message.setAuthor(user);
		message.setDate(actDate);
		message.setText(text);
		message.setArticle(articleDao.listOne(articleId));

		messageDao.create(message);

		response.sendRedirect(response.encodeRedirectURL("ShowArticle?id="
				+ articleId));
	}

}
