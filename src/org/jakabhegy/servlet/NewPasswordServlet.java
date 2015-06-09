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
import org.jakabhegy.tools.Tools;

/**
 * Servlet implementation class NewPassword
 */
@WebServlet("/NewPasswordServlet")
public class NewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PERSISTENCE_UNIT_NAME = "messages"; 
	private static EntityManagerFactory factory;
	private EntityManager em;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewPasswordServlet() {
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
		Account user=null;
		
		if (session.getAttribute("user") != null) {
			 user = (Account) session.getAttribute("user");
		}else{
			response.sendRedirect(response.encodeRedirectURL("Login.jsp"));	
		}

		String currentPassword = Tools
				.stripHtmlRegex(request.getParameter("currentPassword"));
		String newPassword1 = Tools
				.stripHtmlRegex(request.getParameter("newPassword1"));
		String newPassword2 = Tools.stripHtmlRegex(request
				.getParameter("newPassword2"));
		session.setAttribute("reset", "NewPassword.jsp");
		if (!user.getPassword().equals(currentPassword)) {
			errorString += "\nRossz a megadott jelenlegi jelszó!";
			session.setAttribute("error", errorString);
			errorBoolean = true;

		}
		if (!newPassword1.equals(newPassword2)) {
			errorString += "\nA két új jelszó nem eggyezik!";
			session.setAttribute("error", errorString);
			errorBoolean = true;
		}
		if (errorBoolean == true) {
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
		}
		else
		{
			try {
				user.setPassword(newPassword1);
				dao.update(user);
				out.println(Tools.beforeBody("Új jelszó", "style.css"));
				out.println(Tools.makeHeader(user));

				out.println("<h2>Sikeres jelszóváltoztatás!</h2>");
				out.println("<h2>" + Tools.linkTag("MyProfile.jsp", "Vissza") + "</h2>");
				out.println(Tools.afterBody());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		}
		
		
		
		

	}

}
