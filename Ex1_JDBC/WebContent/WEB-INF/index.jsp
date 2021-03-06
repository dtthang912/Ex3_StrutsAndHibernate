<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://fsoft.com/functions" prefix="util"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
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
	<c:set var="currentPage"
		value="${not empty param.page ? param.page : 1}" />
	<c:set var="searchName"
		value="&searchName=${util:encodeURI(param.searchName)}" />
	<c:set var="pageIndexes" value="${sessionScope.pageIndexes }" />
	<nav class="navbar navbar-default navbar-statictop">
	<div class="container">
		<form class="nav navbar-nav navbar-left" action="index" method="get">
			<select id="language" name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
				<option value="vi" ${language == 'vi' ? 'selected' : ''}>Tiếng
					Việt</option>
			</select>
		</form>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${sessionScope.user.role == 'ADMIN' }">
				<li><a
					href="admin?page=${currentPage}${not empty param.searchName ? searchName : "" }">Admin</a></li>
			</c:if>
			<li><a class="logout" href="logout">Logout</a></li>
		</ul>
	</div>
	</nav>
	<div class="container well">
		<fmt:message key="admin.button.search" var="buttonSearch" />
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form class="search-section" action="index" method="get">
					<table>
						<tr>
							<td><input name="searchName" type="text" maxlength="45"
								value="${param.searchName }" autofocus></td>
							<td><input class="btn btn-info" type="submit"
								value="${buttonSearch}"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div class="row top-10">
			<div class="col-md-6 col-md-offset-3">
					<table class="table table-striped table-hover student-display">
						<thead>
							<tr>
								<th class="hidden-input">
								</td>
								<th><fmt:message key="admin.label.studentid" />
								</td>
								<th><fmt:message key="admin.label.studentname" />
								</td>
								<th><fmt:message key="admin.label.mark" />
								</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="student"
								items="${sessionScope.studentDisplayList}" varStatus="status">
								<tr>
									<td class="hidden-input"></td>
									<td>${student.id }</td>
									<td>${student.name }</td>
									<td>${student.mark }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
		</div>
	</div>
	<div class="text-center">
		<ul class="page-display pagination">
			<c:if test="${currentPage > 1}">
				<li><a
					href="index?page=${currentPage - 1}${not empty param.searchName ? searchName : "" }">Prev</a></li>
			</c:if>
			<c:forEach var="pageIndex" items="${pageIndexes }">
				<c:choose>
					<c:when test="${currentPage eq pageIndex}">
						<li class="current-page active"><a href="#">${pageIndex}</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="other-display-pages"
							href="index?page=${pageIndex}${not empty param.searchName ? searchName : "" }">${pageIndex }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageIndexes[fn:length(pageIndexes) - 1] > currentPage}">
				<li><a
					href="index?page=${currentPage + 1}${not empty param.searchName ? searchName : "" }">Next</a></li>
			</c:if>
		</ul>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/js1.js"></script>
</body>
</html>