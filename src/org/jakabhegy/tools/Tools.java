package org.jakabhegy.tools;

import java.io.PrintWriter;

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
		return "<img src=\""+source+"\"></img>";
	}
}
