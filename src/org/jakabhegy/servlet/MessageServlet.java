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

import org.jakabhegy.dao.MessageDao;
import org.jakabhegy.pojo.Message;

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

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		MessageDao dao = new MessageDao(em);
		List<Message> messages = dao.listAll("Message");
		
		//	System.out.println("elsõ");
		//	for (Message message : messages) {
		//		dao.delete(message);
		//	}
			// request.getSession().setAttribute("students", students);
		
	

		String name = request.getParameter("name");
		String text = request.getParameter("message");
		Date actDate = Calendar.getInstance().getTime();
		Message message = new Message();
		message.setName(name);
		message.setDate(actDate);
		message.setText(text);
	//	System.out.println(message);
		dao.create(message);

		response.sendRedirect(response.encodeRedirectURL("Message.jsp"));
	}

}
