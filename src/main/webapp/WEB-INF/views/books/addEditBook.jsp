<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../elements/external.jsp" />
<body>
	<div class="container">
		<jsp:include page="../elements/header.jsp" />
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="../elements/menu.jsp" />
			</div>
			<div class="col-md-6">
				<c:choose>
					<c:when test="${isNew == true}">
						<c:set var="method" value="post" />
						<c:set var="action" value="Add" />
					</c:when>
					<c:otherwise>
						<c:set var="method" value="put" />
						<c:set var="action" value="Edit" />
					</c:otherwise>
				</c:choose>

				<div class="row">
					<ol class="breadcrumb">
						<li><a href="/sokrates/admin/users">Users</a></li>
						<li><a href="/sokrates/admin/users/view/${user.id}">${user.name}</a></li>
						<li class="active"><c:out value="${action}" /> book</li>
					</ol>
				</div>

				<div class="row">
					<form:form modelAttribute="book" method="${method}"
						class="form-horizontal" role="form">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Title:
							</label>
							<div class="col-xs-5">
								<form:input path="title" class="form-control" value=""/>
								<form:errors path="title" cssClass="alert-danger" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Description:
							</label>
							<div class="col-xs-8">
								<form:textarea path="description" class="form-control" value=""/>
								<form:errors path="description" cssClass="alert-danger" />
							</div>
						</div>

						<div class="form-actions">
							<div class="col-sm-5">
								<button class="btn btn-default" type="submit">${action}</button>
							</div>

						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


