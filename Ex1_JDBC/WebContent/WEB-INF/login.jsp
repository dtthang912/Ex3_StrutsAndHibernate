<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="com.fsoft.thangdt3.properties.language" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/css1.css">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="get">
		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="vi" ${language == 'vi' ? 'selected' : ''}>Tiếng
				Việt</option>
		</select>
	</form>
	<div class="container well">
		<div class="col-md-4 col-md-offset-4">
			<h1>
				<fmt:message key="login.label.login" />
			</h1>
			<form action="login" method="post">
				<fmt:message key="login.button.login" var="loginButton" />
				<ul class="message">
					<c:forEach var="message text-center"
						items="${requestScope.loginMessage}">
						<li>${message.value }</li>
					</c:forEach>
				</ul>
				<table>
					<tr>
						<td><fmt:message key="login.label.username" />:</td>
						<td><input name="username" type="text" maxlength="45"
							required autofocus></td>
					</tr>
					<tr>
						<td><fmt:message key="login.label.password" /></td>
						<td><input name="pass" type="password" maxlength="45"
							required></td>
					</tr>
					<tr>
						<td><input type="submit" value="${loginButton}"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/js1.js"></script>
</body>
</html>