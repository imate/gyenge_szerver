<!DOCTYPE html>
<%@page import="org.jakabhegy.tools.Tools"%>
<html>
<head>
<title>Egy kis �zenet</title>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<meta charset="UTF-8">
</head>
<body>
	<header>
		<h1>K�ldj nekem egy nagy �zenetet!</h1>
	</header>
	<div class="form_cucc">
		<form action="MessageServlet" method="post" name="messageForm" accept-charset="UTF-8">
			<input type="text" name="name" placeholder="N�v" />
			<textarea name="message" placeholder="�zenet"></textarea>
			<input type="submit" value="K�ld�s" />
		</form>
	</div>
</body>
</html>