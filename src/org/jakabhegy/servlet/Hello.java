package org.jakabhegy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jakabhegy.pojo.Account;
import org.jakabhegy.tools.Tools;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Hello() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account user = null;
		boolean loggedIn = session.getAttribute("user") != null;
		if (loggedIn)
			user = (Account) session.getAttribute("user");

		PrintWriter out = response.getWriter();
		out.println(Tools.beforeBody("Gyenge Szerver", "style.css"));

		out.println(Tools.makeHeader(user));
		
//		out.println("<header>");
//		out.println("<h1>Üdvözöllek a jakabhegyi gyenge szerveren!</h1>");
//		out.println("</header>");

		out.println("<article class=\"hello_article\">");	
		out.println("<nav>");

		// out.println("<h2>"+ Tools.linkTag("Message.jsp",
		// "Küldj egy óriási üzenetet!") + "</h2>");
		if (loggedIn) {
			out.println("<h2>" + user.getUsername()
					+ " néven vagy bejelentkezve!</h2>");
			out.println("<h2>"
					+ Tools.linkTag("NewArticle.jsp",
							"Írj nekem egy nagy cikket!") + "</h2>");
			out.println("<h2>"
					+ Tools.linkTag("LogoutServlet", "Kijelentkezés") + "</h2>");
		} else {
			out.println("<h2>" + Tools.linkTag("Login.jsp", "Jelentkezz be!")
					+ "</h2>");
			out.println("<h2>" + Tools.linkTag("Reg.jsp", "Vagy regisztrálj!")
					+ "</h2>");
		}
		out.println("<h1>" + Tools.linkTag("ShowArticles.jsp", "Cikkek")
				+ "</h1>");
		out.println("</nav>");
		out.println("<h2>a te ip-címed: " + request.getRemoteAddr() + "</h2>");
		out.println(Tools.imgTag("img/prima.jpg")
				+ Tools.imgTag("img/tama.jpg"));
		out.println("</article>");
		out.println(Tools.audioTag("heman.ogg", "audio/ogg"));
		out.println(Tools.afterBody());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
