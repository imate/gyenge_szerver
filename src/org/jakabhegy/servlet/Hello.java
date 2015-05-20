package org.jakabhegy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
		out.println(Tools.beforeBody("Gyenge Szerver", "hello.css"));
		
		out.println("<h1>Üdvözöllek a jakabhegyi gyenge szerveren!</h1>");
		out.println("<h2>a te ip-címed: " + request.getRemoteAddr() + "</h2>");
		out.println(Tools.imgTag("prima.jpg"));
		
		out.println(Tools.afterBody());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
