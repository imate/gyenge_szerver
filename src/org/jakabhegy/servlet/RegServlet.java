package org.jakabhegy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

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
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "messages"; //$NON-NLS-1$
	private static EntityManagerFactory factory;
	private EntityManager em;
	/*private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;*/

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
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

		AccountDao dao = new AccountDao(em);
		String checkText = request.getParameter("checkText");

		try {
			int id = Integer.parseInt(Tools.stripHtmlRegex(request
					.getParameter("id")));
			String text = Tools.stripHtmlRegex(checkText);
			Account user = dao.listOne(id);

			if (user.getCheckText().equals(text)) {
				user.setEllenorzott(true);
				dao.update(user);
				out.println(Tools.beforeBody("Hitelesítés", "style.css"));
				out.println(Tools.makeHeader(null));
				out.println("<h2>Kedves " + user.getUsername()
						+ "!\n Regisztrációját sikeresen aktiválta! </h2>");
				out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>");
				out.println(Tools.afterBody());
			} else {
				session.setAttribute("reset", "Reg.jsp");
				session.setAttribute("error",
						"Rossz text" + user.getCheckText() + "  " + text);
				// session.setAttribute("error","Rossz text");
				response.sendRedirect(response.encodeRedirectURL("Error.jsp"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			session.setAttribute("reset", "Reg.jsp");
			session.setAttribute("error", "Hibás link!");
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));

		}

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
	//	pattern = Pattern.compile(EMAIL_PATTERN);
		String errorString = "";
		Boolean errorBoolean = false;

		String username = Tools
				.stripHtmlRegex(request.getParameter("username"));
		String password = Tools
				.stripHtmlRegex(request.getParameter("password"));
		String password2 = Tools.stripHtmlRegex(request
				.getParameter("password2"));
		String email = Tools.stripHtmlRegex(request.getParameter("email"));
	//	matcher = pattern.matcher(email);
		session.setAttribute("reset", "Reg.jsp");
		if (!password.equals(password2)) {
			errorString += "\nA két jelszó nem eggyezik!";
			session.setAttribute("error", errorString);
			errorBoolean = true;

		}
		if (dao.usernameIsUsed(username)) {
			errorString += "\nFoglalt felhasználónév!";
			session.setAttribute("error", errorString);
			errorBoolean = true;

		}
		if (!Tools.checkEmail(email)) {
			errorString += "\nRossz e-mail cím!";
			session.setAttribute("error", errorString);
			errorBoolean = true;

		}
		if (dao.emailIsUsed(email)) {
			errorString += "\nFoglalt Email cím!";
			session.setAttribute("error", errorString);
			errorBoolean = true;

		}
		if (errorBoolean == true) {
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
		}
		else {
			Account user = new Account();
			Email mail = new Email();
			RandomString randomS = new RandomString(15);
			String randomText = randomS.nextString();
			user.setUsername(username);
			user.setPassword(password);
			user.setRegDate(Calendar.getInstance().getTime());
			user.setEmail(email);
			user.setEllenorzott(false);
			user.setCheckText(randomText);
			dao.create(user);
			// session.setAttribute("user", user);
			// response.sendRedirect(response.encodeRedirectURL("Hello"));
			//randomS.nextString();
			try {
				mail.sendMail("Gyenge szerver regisztráció",
						"Az alábbi linken aktiválhatja regisztrációját:\n http://"
								+ request.getServerName()
								+ ":8080/RegServlet?id=" + user.getId()
								+ "&checkText=" + randomText, user.getEmail());
				out.println(Tools.beforeBody("Regisztráció", "style.css"));
				out.println(Tools.makeHeader(null));

				out.println("<h2>Sikeres regisztráció! A megadott e-mail-re küldtünk egy linket amellyel aktiválhatja a regisztrációját.</h2>");
				out.println("<h2>" + Tools.linkTag("Hello", "Vissza") + "</h2>");
				out.println(Tools.afterBody());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}