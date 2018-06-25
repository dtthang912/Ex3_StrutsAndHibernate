<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<h1></h1>
	<c:set var="currentPage"
		value="${not empty param.page ? param.page : 1}" />
	<c:set var="searchName"
		value="&searchName=${util:encodeURI(param.searchName)}" />
	<c:set var="pageIndexes" value="${sessionScope.pageIndexes }" />
	<c:set var="adminPath" value="/Ex1_JDBC/admin" />
	<c:set var="queryString" value="${sessionScope.queryString }" />

	<nav class="navbar navbar-default navbar-statictop">
	<div class="container">
		<form class="nav navbar-nav navbar-left"
			action="${adminPath}/edit${queryString}" method="get">
			<select id="language" name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
				<option value="vi" ${language == 'vi' ? 'selected' : ''}>Tiếng
					Việt</option>
			</select>
		</form>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/Ex1_JDBC/index${queryString }">Index</a></li>
			<li><a class="logout" href="/Ex1_JDBC/logout">Logout</a></li>
		</ul>
	</div>
	</nav>
	<fmt:message key="admin.button.search" var="buttonSearch" />
	<div class="container well">
		<fmt:message key="admin.button.search" var="buttonSearch" />
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form class="form-horizontal" action="${adminPath}" method="get">
					<div class="form-group">
						<input name="searchName" type="text" maxlength="45"
							value="${param.searchName }" autofocus> <input
							class="btn btn-info" type="submit" value="${buttonSearch}">
					</div>
				</form>
			</div>
		</div>
		<ul class="message text-center">
			<c:forEach var="message"
				items="${requestScope.updateNameAndMarkMessage}">
				<li>${message.value }</li>
			</c:forEach>
		</ul>
		<form action="${adminPath}${queryString }" method="post">
			<div class="row top-10">
				<div class="col-md-6 col-md-offset-3">
					<table class="student-display table table-hover table-striped">
						<thead>
							<tr>
								<th></th>
								<th><fmt:message key="admin.label.studentid" /></th>
								<th><fmt:message key="admin.label.studentname" /></th>
								<th><fmt:message key="admin.label.mark" /></th>
							</tr>
						</thead>
						<tbody>

							<c:set var="index" value="0" />
							<c:set var="selectedIndex"
								value="${sessionScope.selectedIndexes[index] }" />
							<c:set var="studentDisplayList"
								value="${sessionScope.studentDisplayList }" />
							<c:forEach var="student" items="${studentDisplayList}"
								varStatus="status">
								<tr>
									<c:choose>
										<c:when test="${status.index eq selectedIndex }">
											<c:set var="studentNameString"
												value="studentName${status.index }" />
											<c:set var="studentMarkString"
												value="studentMark${status.index }" />
											<c:set var="studentName" value="${param[studentNameString]}" />
											<c:set var="studentMark" value="${param[studentMarkString]}" />
											<td class="hidden-input"><input type="hidden"
												name="selectedIndexes" value="${status.index }"></td>
											<td>${student.id }</td>
											<td>
												<div class="form-group">
													<input class="form-control student-name input-sm"
														type="text" name="studentName${status.index }"
														value="${not empty studentName ? studentName : student.name}"
														autofocus>
												</div>
											</td>
											<td>
												<div class="form-group">
													<input type="text"
														class="form-control student-mark input-sm"
														name="studentMark${status.index }"
														value="${not empty studentMark ? studentMark : student.mark}">
												</div>
											</td>
											<c:set var="index" value="${index + 1 }" />
											<c:set var="selectedIndex"
												value="${sessionScope.selectedIndexes[index] }" />
										</c:when>
										<c:otherwise>
											<td class="hidden-input"></td>
											<td>${student.id }</td>
											<td>${student.name }</td>
											<td>${student.mark }</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row top-10">
				<div class="col-md-6 col-md-offset-3">
					<fmt:message key="admin.button.applyupdate" var="buttonApplyUpdate" />
					<fmt:message key="admin.button.cancel" var="buttonCancel" />
					<div class="form-group">
						<input class="btn btn-default" type="submit" name="applyUpdate"
							value="${buttonApplyUpdate}"
							onclick="return checkStudentInput();"> <a
							class="btn btn-default" href="${adminPath}${queryString }"
							class="button">${buttonCancel}</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="text-center">
		<ul class="page-display pagination">
			<c:if test="${currentPage > 1}">
				<li><a
					href="${adminPath}?page=${currentPage - 1}${not empty param.searchName ? searchName : "" }">Prev</a></li>
			</c:if>
			<c:forEach var="pageIndex" items="${pageIndexes }">
				<c:choose>
					<c:when test="${currentPage eq pageIndex}">
						<li class="current-page active"><a href="#">${pageIndex}</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="other-display-pages"
							href="${adminPath}?page=${pageIndex}${not empty param.searchName ? searchName : "" }">${pageIndex }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageIndexes[fn:length(pageIndexes) - 1] > currentPage}">
				<li><a
					href="${adminPath}?page=${currentPage + 1}${not empty param.searchName ? searchName : "" }">Next</a></li>
			</c:if>
		</ul>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/Ex1_JDBC/js/js1.js"></script>
</body>
</html>