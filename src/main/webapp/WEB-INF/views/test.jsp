<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>SendGrid Test</title>
	</head>
	<body>
		<h3>SendGrid Test page:</h3>

<!-- FORM action="http://localhost:8080/pdm-EmailPostWs/rest/email/postEmail" -->
<!-- 54.191.152.162 -->
		<form:form modelAttribute="mail"
				action = "rest/email/test"
				method = "post"
				enctype= "multipart/form-data"
		>			
			<br><br>
			<input type="submit" value="OK">
		</form:form>

	</body>
</html>
