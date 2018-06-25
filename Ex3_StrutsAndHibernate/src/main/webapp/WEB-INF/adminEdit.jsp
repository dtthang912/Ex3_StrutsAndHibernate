<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/css1.css">
<title></title>
</head>
<body>
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
		<html:errors />
		<div class="message">
			<html:errors />
		</div>
		<html:form action="process" method="post">
			<html:hidden property="method" value="update" />
			<div class="row top-10">
				<div class="col-md-6 col-md-offset-3">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th><bean:message key="label.studentid" /></th>
								<th><bean:message key="label.studentname" /></th>
								<th><bean:message key="label.mark" /></th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate id="student" name="StudentForm"
								property="studentList" indexId="i">
								<tr>
									<html:hidden name="student" property="id" indexed="true" />
									<td><bean:write name="StudentForm"
											property="studentList[${i}].id" /></td>
									<td>
										<div class="form-group">
											<html:text styleClass="form-control student-name input-sm"
												name="student" property="name" indexed="true" />
										</div>
									</td>
									<td>
										<div class="form-group">
											<html:text styleClass="form-control student-mark input-sm"
												name="student" property="mark" indexed="true" />
										</div>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row top-10">
				<div class="col-md-6 col-md-offset-3">
					<div class="form-group">
						<html:submit styleClass="btn btn-default"
							onclick="return checkStudentInput();">
							<bean:message key="button.applyupdate" />
						</html:submit>
						<html:cancel styleClass="btn btn-default">
							<bean:message key="button.cancel" />
						</html:cancel>
					</div>
				</div>
			</div>
			<html:hidden property="sName" />
			<html:hidden property="sPage" />
		</html:form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/js1.js"></script>
</body>
</html:html>