<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://fsoft.com/functions" prefix="util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/css1.css">
<title><bean:message key="homepage.title" /></title>
</head>
<body>
	<c:set var="page" value="${requestScope.StudentForm.sPage }" />
	<c:set var="name"
		value="${util:encodeURI(requestScope.StudentForm.sName)}" />

	<nav class="navbar navbar-default navbar-statictop">
	<div class="container">
		<html:form styleClass="nav navbar-nav navbar-left" action="locale"
			method="get">
			<html:select property="language" onchange="submit()">
				<html:option value="en">English</html:option>
				<html:option value="vi">Tiếng Việt</html:option>
			</html:select>
		</html:form>
		<ul class="nav navbar-nav navbar-right">
			<li><a class="logout" href="logout.do">Logout</a></li>
		</ul>
	</div>
	</nav>
	<div class="container well">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<html:form styleClass="form-horizontal" action="process"
					method="get">
					<html:hidden property="method" value="list" />
					<div class="form-group">
						<html:text property="sName" maxlength="45" />
						<html:submit styleClass="btn btn-info">
							<bean:message key="button.search" />
						</html:submit>
					</div>
				</html:form>
			</div>
		</div>
		<div class="message">
			<html:errors />
		</div>
		<html:form action="process" method="get">
			<div class="row top-10">
				<div class="col-md-6 col-md-offset-3">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<c:if test="${sessionScope.user.role eq 'ADMIN' }">
									<th></th>
								</c:if>
								<th><bean:message key="label.studentid" /></th>
								<th><bean:message key="label.studentname" /></th>
								<th><bean:message key="label.mark" /></th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate id="student" name="studentDisplayList">
								<tr>
									<c:if test="${sessionScope.user.role eq 'ADMIN' }">
										<td><html:multibox property="id">${student.id}</html:multibox></td>
									</c:if>
									<td>${fn:escapeXml(student.id)}</td>
									<td>${fn:escapeXml(student.name)}</td>
									<td>${fn:escapeXml(student.mark)}</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<c:if test="${sessionScope.user.role eq 'ADMIN' }">
				<div class="row top-10">
					<div class="col-md-6 col-md-offset-3">
						<div class="form-group">
							<html:submit styleClass="btn btn-default" property="method"
								onclick="return checkCheckbox();">
								<bean:message key="button.edit" />
							</html:submit>
							<html:submit styleClass="btn btn-default" property="method"
								onclick="return checkDelete();">
								<bean:message key="button.delete" />
							</html:submit>
							<a href="process.do?method=showAdd&sName=${name }&sPage=${page}" class="btn btn-default">
								<bean:message key="button.add" />
							</a>
						</div>
					</div>
				</div>
			</c:if>
			<html:hidden property="sName" />
			<html:hidden property="sPage" />
		</html:form>
	</div>
	<div class="text-center">
		<ul class="page-display pagination">
			<c:if test="${page > 1}">
				<li><a
					href="process.do?method=list&sPage=${page - 1}&sName=${name}">Prev</a></li>
			</c:if>
			<c:forEach var="pageIndex" items="${requestScope.pageIndexes }">
				<c:choose>
					<c:when test="${page eq pageIndex}">
						<li class="current-page active"><a href="#">${pageIndex}</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="other-display-pages"
							href="process.do?method=list&sPage=${pageIndex}&sName=${name}">${pageIndex }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if
				test="${requestScope.pageIndexes[fn:length(requestScope.pageIndexes) - 1] > page}">
				<li><a
					href="process.do?method=list&sPage=${page + 1}&sName=${name}">Next</a></li>
			</c:if>
		</ul>
	</div>
	<script src="js/js1.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html:html>