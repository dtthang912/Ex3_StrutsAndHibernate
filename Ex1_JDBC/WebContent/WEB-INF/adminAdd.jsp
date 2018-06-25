<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://fsoft.com/functions" prefix="util"%>
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
<link rel="stylesheet" type="text/css" href="/Ex1_JDBC/css/css1.css">
<title>Insert title here</title>
</head>
<body>
	<c:set var="currentPage"
		value="${not empty param.page ? param.page : 1}" />
	<c:set var="adminPath" value="/Ex1_JDBC/admin"/>
	<c:set var="queryString" value="${sessionScope.queryString }"/>	
	<nav class="navbar navbar-default navbar-statictop">
	<div class="container">
		<form class="nav navbar-nav navbar-left" action="${adminPath}/add{queryString}" method="get">
			<select id="language" name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
				<option value="vi" ${language == 'vi' ? 'selected' : ''}>Tiếng
					Việt</option>
			</select>
		</form>
		<ul class="nav navbar-nav navbar-right">
			<li><a
				href="/Ex1_JDBC/index${queryString }">Index</a></li>
			<li><a class="logout" href="/Ex1_JDBC/logout">Logout</a></li>
		</ul>
	</div>
	</nav>
	<div class="container well">
	<ul class="message text-center">
		<c:forEach var="message" items="${requestScope.addNameAndMarkMessage}">
			<li>${message.value }</li>
		</c:forEach>
	</ul>
	<form class="form-horizontal" action="${adminPath}" method="post">
		<fmt:message key="admin.button.add" var="buttonAdd" />
		<fmt:message key="admin.button.cancel" var="buttonCancel" />
		<div class="form-group">
			<label class="control-label col-md-offset-3 col-md-2" for="name"><fmt:message
					key="admin.label.studentname" /></label>
			<div class="col-md-7">
				<input class="student-name form-control" id="name" type="text" name="studentName"
					value="${param.studentName}" autofocus>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-offset-3 col-md-2" for="mark"><fmt:message
					key="admin.label.mark" /></label>
				<div class="col-md-7">
					<input class="student-mark form-control" id="mark" type="text" name="studentMark"
						value="${param.studentMark}">
				</div>
		</div>
		<div class="form-group text-center">
			<input class="btn btn-default" type="submit" name="applyAdd" value="${buttonAdd}"
				onclick="return checkStudentInput();"> 
			<a
				href="${adminPath}${queryString }" class="btn btn-default btn">${buttonCancel }</a>
		</div>
	</form>
	</div> 
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/Ex1_JDBC/js/js1.js"></script>
</body>
</html>