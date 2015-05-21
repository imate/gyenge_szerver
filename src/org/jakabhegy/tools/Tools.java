package org.jakabhegy.tools;

public class Tools {

	public static String beforeBody(String title, String css) {
		return "<html>\n<head>\n<title>" + title
				+ "</title>\n<link rel=\"stylesheet\" href=\"" + css
				+ "\" type=\"text/css\"></link>\n</head>\n<body>";
	}

	public static String afterBody() {
		return "</body>\n</html>";
	}

	public static String imgTag(String source) {
		return "<img src=\"" + source + "\"></img>";
	}

	public static String linkTag(String url, String text) {
		return "<a href=\"" + url + "\">" + text + "</a>";
	}

	public static String audioTag(String source, String type) {
		return "<audio controls autoplay loop>\n<source src=\"" + source
				+ "\" type=\"" + type + "\">\n</audio>";
	}
}
