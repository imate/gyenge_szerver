<!DOCTYPE html>
<%@page import="org.jakabhegy.tools.Tools"%>
<html>
<head>
<title>Egy kis üzenet</title>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<meta charset="UTF-8">
</head>
<body>
	<header>
		<h1>Küldj nekem egy nagy üzenetet!</h1>
	</header>
	<div class="form_cucc">
		<form action="MessageServlet" method="post" name="messageForm" accept-charset="UTF-8">
			<input type="text" name="name" placeholder="Név" />
			<textarea name="message" placeholder="Üzenet"></textarea>
			<input type="submit" value="Küldés" />
		</form>
	</div>
</body>
</html>