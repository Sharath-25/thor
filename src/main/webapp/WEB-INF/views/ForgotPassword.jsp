<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>reset password</title>
<style type="text/css">
.error {
	color: red
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">X-workz</a> <br>
			</div>
			<ul class="nav navbar-nav">

			</ul>
			<br>
			<div align="center">
				<form action="/common-modules/login/page.do">
					<button class="submit" class="btn btn-primary">LOGIN</button>
				</form>
			</div>
			<br>
		</div>
	</nav>
	<br />
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div align="center">
		<h2>Re-set your password</h2>
		<br>
		<form:form action="resetPassword.do"
			modelAttribute="forgotPasswordDTO" method="post">
			<label>Email</label>
			<form:input path="email" />
			<form:errors path="email" cssClass="error" />
			<br>
			<br>
			<input type="submit" value="RESET" class="btn btn-primary">
		</form:form>
		<h1>${message}</h1>
	</div>

	<br>
	<br />
	<nav class="navbar navbar-inverse navbar-fixed-bottom">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">X-workz</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#"></a></li>
			</ul>
		</div>
	</nav>
</body>
</html>