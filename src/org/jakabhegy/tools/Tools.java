package org.jakabhegy.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jakabhegy.pojo.Account;

public class Tools {
	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Pattern pattern;
	private static Matcher matcher;

	public static String getFormattedDate(Date date) {
		return dateFormat.format(date);
	}

	public static boolean checkEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static String beforeBody(String title, String css) {
		return "<html>\n<head>\n<title>" + title
				+ "</title>\n<link rel=\"stylesheet\" href=\"" + css
				+ "\" type=\"text/css\"></link>\n"
				+ "<link rel=\"shortcut icon\" href=\"img/favicon.ico\" />"
				// +"<meta charset=\"UTF-8\">"
				+ "</head>\n<body>" + "<div id=\"wrapper\">" + "<h6>"
				+ linkTag("Hello", "Gyenge szerver") + "</h6>";
	}

	public static String afterBody() {
		return "</div>" + makeFooter() + "</body>\n</html>";
	}

	private static String makeFooter() {
		String footer = "<footer>Az oldalt az Alsópáhoki Gyenge Adatközpont<sup>TM</sup> üzemelteti.</br>email: primaszerver@gmail.com</br>"
				+ linkTag("felhasznalasi_feltetelek.html",
						"felhasználási feltételek") + "</footer>";
		return footer;
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
					+ linkTag("Profile.jsp?id=" + user.getId(), user.getUsername())
					+ linkTag("NewArticle.jsp", "új cikk")
					+ linkTag("ShowArticles.jsp", "cikkek")
					+ linkTag("ShowArticles.jsp?author_id=" + user.getId(), "cikkeim")
					+ linkTag("MyProfile.jsp", "profil beállítások")
					+ linkTag("LogoutServlet", "kijelentkezés");
		}
		return "<header><nav>" + result + "</nav></header>";
	}

	public static String termsAndConditions() {
		return "A blogon megjelenõ bejegyzésekhez a hozzászólni kívánó Facebook profilján keresztül történõ bejelentkezés után bárki hozzászólhat. Az üzemeltetõ fenntartja a jogot a hozzászólások elõzetes értesítés, figyelmeztetés és indokolás nélküli törlésére.";
	}
}
