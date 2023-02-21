<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Joooble</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="css/register.css">
	</head>
	
	<h1>Registration</h1>
	<body>
		
		<form:form action="registrationEmployee" method="POST">
		
			<label>User name: <input type="text" name="username"/> </label>
		
			<label>Password: <input type="password" name="password"/> </label>
			<label>Password again: <input type="password" name="passwordConfirm"/> </label>
			<label>E-mail address: <input type="email" name="email"/> </label>
			<label>Birthdate: <input type="date" name="birthdate"/> </label>
		
			<input type="submit" name="register"/>
		

		</form:form>
			
		
	</body>
</html>