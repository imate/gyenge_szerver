package org.jakabhegy.tools;

import org.jakabhegy.pojo.Account;

public class Tools {

	public static String beforeBody(String title, String css) {
		return "<html>\n<head>\n<title>" + title
				+ "</title>\n<link rel=\"stylesheet\" href=\"" + css
				+ "\" type=\"text/css\"></link>\n"
				+ "<link rel=\"shortcut icon\" href=\"img/favicon.ico\" />"
				// +"<meta charset=\"UTF-8\">"
				+ "</head>\n<body>" + "<div id=\"wrapper\">";
	}

	public static String afterBody() {
		return "</div></body>\n</html>";
	}

	public static String imgTag(String source) {
		return "<img src=\"" + source + "\"></img>";
	}

	public static String stripHtmlRegex(String source) {
		// Replace all tag characters with an empty string.
		return source.replaceAll("<.*?>", "");
	}

	public static String linkTag(String url, String text) {
		return "<a href=\"" + url + "\">" + text + "</a>";
	}

	public static String audioTag(String source, String type) {
		return "<audio controls loop>\n<source src=\"" + source + "\" type=\""
				+ type + "\">\n</audio>";
	}

	public static String makeHeader(Account user) {
		String result = "";
		if (user == null) {
			result += linkTag("Login.jsp", "bejelentkezés")
					+ linkTag("Reg.jsp", "regisztráció")
					+ linkTag("ShowArticles.jsp", "cikkek");
		} else {

			result += imgTag(user.getImgPath())
					+ linkTag("MyProfile.jsp", user.getUsername())
					+ linkTag("NewArticle.jsp", "új cikk")
					+ linkTag("ShowArticles.jsp", "cikkek")
					// + linkTag("MyArticles", "cikkeim")
					+ linkTag("LogoutServlet", "kijelentkezés");
		}
		return "<header><nav>" + result + "</nav></header>";
	}
}
