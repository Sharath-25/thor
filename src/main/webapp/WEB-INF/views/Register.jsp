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
<title>welcome</title>
<style>
.error {
	color: red
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">X-workz-CM</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">Home</a></li>

			</ul>
			<br>
			<div align="right">
				<form action="loginpage.do">
					<button class="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>
	</nav>
	<br />
	<br />
	<br>
	<br>
	<div align="center">
		<h2>Please Sign up for the Course before you join the Course</h2>
		<h3>Fill up your details</h3>
		<br> <br>
		<form:form action="register.do" modelAttribute="registerDTO"
			method="post">
		
          User ID<form:input path="userId" />
			<br>
			<form:errors path="userId" cssClass="error" />
			<br>
			<br>

			Email<form:input path="email" />
			<br>
			<form:errors path="email" cssClass="error" />
			<br>
			<br>

			Phone<form:input path="phone" />
			<br>
			<br>
			<form:errors path="phone" cssClass="error" />
			<br>
			<br>

			<h4>
				Course Interested
				<form:select path="course">
					<form:option value="Development" label="Development" />
					<form:option value="Testing" label="Testing" />

				</form:select>
				<br>
				<form:errors path="course" cssClass="error" />
				<br> <br>
			</h4>
			<br>
			<h4>
				<label>Agree &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label> Agree
				<form:radiobutton path="agree" value="true" />
				Dis-Agree
				<form:radiobutton path="agree" value="false" />
				<br> <br>
				<form:errors path="agree" cssClass="error" />
				<br>
			</h4>
			<hr>
			<input type="submit" value="Register" class="btn btn-primary">
			<br>
		</form:form>
	</div>
	<h1>${msg}</h1>

	<br />
	<br>
	<br>
	<br>
	<nav class="navbar navbar-inverse navbar-fixed-bottom">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">X-workz-Common Modules</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#"></a></li>
			</ul>
		</div>
	</nav>
</body>
</html>