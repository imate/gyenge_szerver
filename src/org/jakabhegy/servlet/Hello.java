package org.jakabhegy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jakabhegy.tools.Tools;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Hello() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(Tools.beforeBody("Gyenge Szerver", "style.css"));

		out.println("<header>");
		out.println("<h1>Üdvözöllek a jakabhegyi gyenge szerveren!</h1>");
		out.println("</header>");
		out.println("<article>");
		out.println("<h2>"
				+ Tools.linkTag("Message.jsp", "Küldj egy üzenetet!") + "</h2>");
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
