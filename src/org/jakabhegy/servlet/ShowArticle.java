package org.jakabhegy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.jakabhegy.pojo.Article;
import org.jakabhegy.pojo.Message;
import org.jakabhegy.tools.Tools;

/**
 * Servlet implementation class ShowArticle
 */
@WebServlet("/ShowArticle")
public class ShowArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowArticle() {
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
		Account user=(Account) session.getAttribute("user");
		boolean loggedIn = session.getAttribute("user") != null;
		if (loggedIn)
			user = (Account) session.getAttribute("user");
		
		PrintWriter out = response.getWriter();
		int id=0;
		out.println(Tools.beforeBody("Gyenge Szerver", "style.css"));
		out.println("<header>");
		out.println("<h1>Jakabhegyi gyenge szerver!</h1>");
		out.println("</header>");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("messages");
		EntityManager em = factory.createEntityManager();
		ArticleDao daoArticle = new ArticleDao(em);
		
		try{
			id = Integer.parseInt(Tools.stripHtmlRegex(request.getParameter("id")));
			Article article = daoArticle.listOne(id);	
			out.println("<article class=\"item\">");
			out.println("<h1>" + article.getAuthorName()+": "+article.getTitle() + "</h1>");
			out.println("<h2>" + article.getText() + "</h2>");
			out.println("<h3>" + article.getDate() + "</h3>");
			out.println("<h2>" + Tools.linkTag("ShowArticles.jsp", "Vissza")+ "</h2>");
			out.println("</article>");
			
		}catch(Exception ex){
			session.setAttribute("error", "Nincs ilyen cikk!");
			response.sendRedirect(response.encodeRedirectURL("Error.jsp"));
			
		}
		
	
		out.println("<div class=\"form_cucc\">");
		out.println("<h2>Hozzászólások:</h2>");
		out.println("</div>");
		
		MessageDao messageDao = new MessageDao(em);
		List<Message> messageList = messageDao.listAll("Message");
		out.println("<ul>");
		int i = 1;
		for (Message message : messageList) {
			if (message.getArticleId() == id) {
				out.println("<li><article class=\"item\">");
				out.println("<h1> #" + (i++) + " " + message.getAuthorName()+ "</h1>");
				out.println("<h2>" + message.getText() + "</h2>");
				out.println("<h3>" + message.getFormattedDate() + "</h3>");
				out.println("</article></li>");
			}

		}
		out.println("</ul>");
		if(loggedIn){
			out.println("<div class=\"form_cucc\">");
		
			out.println("<form action=\"MessageServlet\" method=\"post\" name=\"messageForm\"accept-charset=\"UTF-8\">");		
			//out.println("<input type=\"text\" name=\"name\" placeholder=\"Név\" value=\""+username+"\" />");		
			out.println("<textarea name=\"message\" placeholder=\"Üzenet\" required></textarea>");		
			out.println("<input type =\"hidden\"name=\"articleId\" value="+id+">");		
			out.println("<input type=\"submit\" value=\"Küldés\" />");
					
			out.println("</form>");		
			out.println("</div>");
		}else{
			out.println("<div class=\"form_cucc\"><h2>A hozzászóláshoz be kell jelentkezned!</h2>");
			out.println("<h2>" + Tools.linkTag("Login.jsp", "Bejelentkezés")
					+ "</h2></div>");
		}
		
		
		out.println(Tools.afterBody());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
