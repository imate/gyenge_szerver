package org.jakabhegy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
import org.jakabhegy.tools.Email;
import org.jakabhegy.tools.RandomString;
import org.jakabhegy.tools.Tools;

/**
 * Servlet implementation class NewEmailServlet
 */
@WebServlet("/NewEmailServlet")
public class NewEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PERSISTENCE_UNIT_NAME = "messages";
	private static EntityManagerFactory factory;
	private EntityManager em;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewEmailServlet() {
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
		PrintWriter out = response.getWriter();
		String errorString = "";
		Boolean errorBoolean = false;
		Account user = null;

		if (session.getAttribute("user") != null) {
			user = (Account) session.getAttribute("user");
		} else {
			response.sendRedirect(response.encodeRedirectURL("Login.jsp"));
		}

		String currentPassword = Tools.stripHtmlRegex(request
				.getParameter("currentPassword"));
		String newEmail = Tools
				.stripHtmlRegex(request.getParameter("newEmail"));

		if (!user.getPassword().equals(currentPassword)) {
			errorString += "\nRossz a megadott jelenlegi jelszó.";
			session.setAttribute("error", errorString);
			errorBoolean = true;

		}
		if (!Tools.checkEmail(newEmail)) {
			errorString += "\nRosszul adta meg az email-t.";
			session.setAttribute("error", errorString);
			errorBoolean = true;

		}
		if (dao.emailIsUsed(newEmail)) {
			errorString += "\nAz email már foglalt.";
			session.setAttribute("error", errorString);
			errorBoolean = true;
		}
		if (errorBoolean == true) {
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
		} else {
			RandomString randomS = new RandomString(15);
			String randomText = randomS.nextString();
			user.setCheckText(randomText);
			user.setEmail(newEmail);
			user.setEllenorzott(false);
			dao.update(user);
			Email mail= new Email();
			try {
				mail.sendMail("Gyenge szerver: Új email cím beállítása",
						"Az alábbi linken aktiválhatja új email címét:\n http://"
								+ request.getServerName()
								+ ":8080/RegServlet?id=" + user.getId()
								+ "&checkText=" + randomText, user.getEmail());
				out.println(Tools.beforeBody("Regisztráció", "style.css"));
				out.println(Tools.makeHeader(null));

				out.println("<h2>Sikeres email cím frissítés! A megadott e-mail-re küldtünk egy linket amellyel aktiválhatja.</h2>");
				out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>");
				out.println(Tools.afterBody());
				session.removeAttribute("user");
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
