<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/css1.css">
<title><bean:message key="login.title" /></title>
</head>
<body>
	<html:form styleClass="nav navbar-nav navbar-left" action="locale"
		method="get">
		<html:select property="language" onchange="submit()">
			<html:option value="en">English</html:option>
			<html:option value="vi">Tiếng Việt</html:option>
		</html:select>
	</html:form>
	<div class="container well">
		<div class="col-md-4 col-md-offset-4">
			<h1>
				<bean:message key="login.title" />
			</h1>
			<html:errors />
			<html:form action="auth" method="post">
				<%-- 				<ul class="message">
					<c:forEach var="message text-center"
						items="${requestScope.loginMessage}">
						<li>${message.value }</li>
					</c:forEach>
				</ul> --%>
				<table>
					<tr>
						<td><bean:message key="login.label.username" />:</td>
						<td><html:text property="username" maxlength="45"></html:text>
						</td>
					</tr>
					<tr>
						<td><bean:message key="login.label.password" /></td>
						<td><html:password property="pass" maxlength="45">
							</html:password></td>
					</tr>
					<tr>
						<td><html:submit>
								<bean:message key="login.button.login" />
							</html:submit></td>
					</tr>
				</table>
			</html:form>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/js1.js"></script>
</body>
</html:html>