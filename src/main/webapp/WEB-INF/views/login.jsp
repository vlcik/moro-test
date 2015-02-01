<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<jsp:include page="elements/external.jsp" />
	<body>
		<div class="container" style="max-width: 330px;padding: 15px; margin: 0 auto;">
			<%
 
				String errorString = (String) request.getAttribute("error");
				if(errorString != null && errorString.trim().equals("true")){
					out.println("<div class=\"alert alert-danger\" role=\"alert\">Incorrect login name or password. Please retry using correct login name and password.</div>");
				}
			%>
			<!-- http://getbootstrap.com/examples/signin/ -->
			<form action="j_spring_security_check" method="post" class="form-horizontal">
				<h2 class="form-signin-heading">Please sign in</h2>
				<input name="j_username" style="margin-bottom:10px;" class="form-control" placeholder="Name" />
				<input name="j_password" type="password" id="inputPassword" style="margin-bottom:10px;" class="form-control" placeholder="Password"/>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Signin</button>
			</form>
		</div>
	</body>
</html>