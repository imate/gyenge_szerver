package org.jakabhegy.servlet;

import java.io.IOException;
import java.util.Calendar;
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

import org.jakabhegy.dao.AccountDao;
import org.jakabhegy.dao.ArticleDao;
import org.jakabhegy.pojo.Account;
import org.jakabhegy.pojo.Article;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "messages"; //$NON-NLS-1$
	private static EntityManagerFactory factory;
	private EntityManager em;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegServlet() {
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
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		AccountDao dao = new AccountDao(em);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (!password.equals(password2)) {
			session.setAttribute("error", "A két jelszó nem egyezik meg!");
			session.setAttribute("reset", "Reg.jsp");
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
		} else if (dao.usernameIsUsed(username)) {
			session.setAttribute("error", "Foglalt felhasználónév!");
			session.setAttribute("reset", "Reg.jsp");
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
		} else {
			Account user = new Account();
			user.setUsername(username);
			user.setPassword(password);
			user.setRegDate(Calendar.getInstance().getTime());
			dao.create(user);
			session.setAttribute("user", user);
			response.sendRedirect(response.encodeRedirectURL("Hello"));
		}
	}
}