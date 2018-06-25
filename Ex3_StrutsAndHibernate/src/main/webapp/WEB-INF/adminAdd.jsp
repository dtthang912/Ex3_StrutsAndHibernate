<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
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
				<div class="message"><html:errors /></div>
	<html:form styleClass="form-horizontal" action="process" method="post">
		<html:hidden property="method" value="add" />
		<div class="form-group">
			<div class="col-md-offset-3 col-md-2"><bean:message
					key="label.studentname" /></div>
			<div class="col-md-7">
				<html:text styleClass="student-name form-control" property="name"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-2"><bean:message
					key="label.mark" /></div>
			<div class="col-md-7">
				<html:text styleClass="student-mark form-control" property="mark"/>
			</div>
		</div>
		<html:hidden property="sName" />
		<html:hidden property="sPage" />
		<div class="form-group text-center">
			<html:submit styleClass="btn btn-default"
				onclick="return checkStudentInput();"><bean:message key="button.add"/></html:submit>
			<html:cancel styleClass="btn btn-default"><bean:message key="button.cancel"/></html:cancel>
		</div>
	</html:form>
	</div> 
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/js1.js"></script>
</body>
</html>