package org.jakabhegy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jakabhegy.tools.Tools;

/**
 * Servlet implementation class Email
 */
@WebServlet("/Email")
public class Email extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Email() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println(Tools.beforeBody("Gyenge Szerver", "style.css"));
		
		 Properties mailServerProperties;
		 Session getMailSession;
		 MimeMessage generateMailMessage;
		 try {
			//Step1		
				System.out.println("\n 1st ===> setup Mail Server Properties..");
				mailServerProperties = System.getProperties();
				mailServerProperties.put("mail.smtp.port", "587");
				mailServerProperties.put("mail.smtp.auth", "true");
				mailServerProperties.put("mail.smtp.starttls.enable", "true");
				mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				System.out.println("Mail Server Properties have been setup successfully..");
		 
		//Step2		
				System.out.println("\n\n 2nd ===> get Mail Session..");
				getMailSession = Session.getDefaultInstance(mailServerProperties, null);
				generateMailMessage = new MimeMessage(getMailSession);
				generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("sztomi1994@gmail.com"));
				generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
				generateMailMessage.setSubject("Greetings from Crunchify..");
				String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
				generateMailMessage.setContent(emailBody, "text/html");
				System.out.println("Mail Session has been created successfully..");
		 
		//Step3		
				System.out.println("\n\n 3rd ===> Get Session and Send mail");
				Transport transport = getMailSession.getTransport("smtp");
				
				// Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
				transport.connect("smtp.gmail.com", "csoroskrokodil@gmail.com", "456852159357");
				transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
				transport.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			 
			
		
		
	/*	
		final String username = "csoroskrokodil@gmail.com";
		final String password = "456852159357";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("csoroskrokodil@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("sztomi1994@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		*/
		
		out.println(Tools.afterBody());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
