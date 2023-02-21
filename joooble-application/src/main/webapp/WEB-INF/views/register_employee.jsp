<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Joooble</title>
		<meta charset="UTF-8">
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
			crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/register.css">
	</head>
	
	<h1>Registration</h1>
	<body>

		<form:form action="registrationEmployee" method="POST">
		
			<label>Password: <input type="password" name="password" class="form-control" /> </label><br>
			<form:errors cssClass="message" path="email"/><br>
			<label>Password again: <input type="password" name="passwordConfirm" class="form-control"/> </label><br>
			<form:errors cssClass="message" path="password"/><br>
			<label>E-mail address: <input type="email" name="email" class="form-control"/> </label><br>
			<form:errors cssClass="message" path="email"/><br>
			<label>Birthdate: <input type="date" name="birthday" class="form-control"/> </label><br>
			<form:errors cssClass="message" path="birthday"/><br>

			<input  class="btn btn-primary btn-lg m-4 w-25" type="submit" name="register" value="Register"/>


		</form:form>


	</body>
</html>