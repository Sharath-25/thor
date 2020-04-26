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
<title>Login</title>
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
				<a class="navbar-brand" href="#">X-workz</a>
			</div>
			<ul class="nav navbar-nav">
			</ul>
			<br>
			<div align="right">
				<form action="registerpage.do">
					<button class="submit" class="btn btn-primary">Register</button>
				</form>
			</div>
		</div>
	</nav>
	<br />
	<br>
	<br>
	<br>
	<br>
	<div align="center">
		<h3>In case of wrong Password entered after 3 times,Your account
			gets blocked</h3>
		<form:form action="login.do" modelAttribute="loginDTO" method="post">
			<h2 class="text primary">LOG INTO YOUR ACCOUNT</h2>
			<label>Email</label>
			<form:input path="email" />
			<form:errors path="email" cssClass="error" />
			<br>
			<br>
			<label>Password</label>
			<form:input path="randomPassword" type="password" />
			<form:errors path="randomPassword" cssClass="error" />
			<br>
			<br>
			<input type="submit" value="LOGIN" class="btn btn-primary">
		</form:form>
		<h2>${msg}</h2>
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