package org.jakabhegy.servlet;

import java.io.IOException;

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
import org.jakabhegy.pojo.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "messages"; //$NON-NLS-1$
	private static EntityManagerFactory factory;
	private EntityManager em;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		AccountDao dao = new AccountDao(em);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account user = dao.listOne(username, password);

		if (user == null) {
			session.setAttribute("error", "Nincs ilyen felhasználó!");
			session.setAttribute("reset", "Login.jsp");
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
		} else if(!user.isEllenorzott()){
			session.setAttribute("error", "Nincs aktiválva a felhasználó!");
			session.setAttribute("reset", "Login.jsp");
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
		}else{
			session.setAttribute("user", user);
			response.sendRedirect(response.encodeRedirectURL("Hello"));
		}
	}
}