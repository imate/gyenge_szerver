package org.jakabhegy.servlet;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jakabhegy.dao.AccountDao;
import org.jakabhegy.pojo.Account;

/**
 * Servlet implementation class ImgUploadServlet
 */
@WebServlet("/ImgUploadServlet")
public class ImgUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PERSISTENCE_UNIT_NAME = "messages"; //$NON-NLS-1$
	private static EntityManagerFactory factory;
	private static EntityManager em;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImgUploadServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		AccountDao dao = new AccountDao(em);
		Account user = (Account) session.getAttribute("user");
		String upload_directory = getServletContext().getRealPath("/").replace(
				"\\", "/")
				+ "/img";
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						System.out.println(name);
						item.write(new File(upload_directory + File.separator
								+ "profile_" + user.getId() + ".jpg"));
						user.setImgName(name);
					}
				}

				System.out.println("File Uploaded Successfully");
				dao.update(user);
			} catch (Exception ex) {
				System.out.println("File Upload Failed due to " + ex);
			}

		} else {
			System.out
					.println("Sorry this Servlet only handles file upload request");
		}

		response.sendRedirect(response.encodeRedirectURL("MyProfile.jsp"));
	}

}
