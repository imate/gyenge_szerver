<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Regisztráció</title>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<meta charset="UTF-8">
</head>
<body>
	<header>
	<h1>Regisztrálj be!</h1>
	</header>
	<div class="form_cucc">

		<form action="RegServlet" method="post" name="messageForm"
			accept-charset="UTF-8">

			<input type="text" name="username" placeholder="Név" />
			<input type="password" name="password" placeholder="Jelszó"></textarea>
			<input type="submit" value="OK" />
		</form>

	</div>
	</ul>
</body>
</html>