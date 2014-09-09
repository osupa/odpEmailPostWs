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

			<form:label path="from">From:</form:label>
			<br>
			<form:input path="from" size="50"/>
			<br>

			<form:label path="to">To:</form:label>
			<br>
			<form:input path="to" size="50"/><br>
			
			<form:label path="subject">Subject:</form:label>
			<br>
			<form:input path="subject" size="50"/>
			<br><br>

			<form:label path="body">Body:</form:label>
			<br>
			<form:textarea path="body" rows="10" cols="75"/>
			<br><br>

			<br>
			<form:label path="attachments">Select File:</form:label>
			<br>
			<form:input type="file" path="attachments"/>
			
			<br><br>
			<input type="submit" value="Send">
			<input type="reset" value="Reset">
		</form:form>

	</body>
</html>
