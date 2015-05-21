package org.jakabhegy.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		factory= Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		Query query = this.em.createQuery("Select t from Message t"); //$NON-NLS-1$ //$NON-NLS-2$
		List<Message> messages;
		try {
			messages= query.getResultList();
			for (Message message : messages) {
				System.out.println(message);
			}
			//request.getSession().setAttribute("students", students);
		}
		catch(Exception x){
			
			
		}
		
		
		String name = request.getParameter("name");
		String text = request.getParameter("message");
		Date actDate=Calendar.getInstance().getTime();
		Message message=new Message();
		message.setName(name);
		message.setDate(actDate);
		message.setText(text);
		System.out.println(message);
		try {
			em.getTransaction().begin();
			em.persist(message);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		response.sendRedirect(response.encodeRedirectURL("Hello"));
	}

}
