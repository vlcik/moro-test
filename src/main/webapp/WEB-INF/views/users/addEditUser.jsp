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
						<li class="active"><c:out value="${action}" /> user</li>
					</ol>
				</div>

				<div class="row">
					<form:form modelAttribute="user" method="${method}"
						class="form-horizontal" role="form">
						<div class="form-group">
							<label for="name" class="col-sm-3 text-left control-label">Username:
							</label>
							<div class="col-xs-5">
								<form:input path="username" class="form-control" value="" />
								<form:errors path="username" cssClass="alert-danger" />
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-3 text-left control-label">Name:
							</label>
							<div class="col-xs-5">
								<form:input path="name" class="form-control" value="" />
								<form:errors path="name" cssClass="alert-danger" />
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="col-sm-3 text-left control-label">Password:
							</label>
							<div class="col-xs-5">
								<form:password path="password" class="form-control" value="" />
								<form:errors path="password" cssClass="alert-danger" />
							</div>
						</div>

						<div class="form-group">
							<label for="verifiedPassword" class="col-sm-3 control-label">Verify
								password: </label>
							<div class="col-xs-5">
								<form:password path="verifiedPassword" class="form-control"
									value="" />
								<form:errors path="verifiedPassword" cssClass="alert-danger" />
							</div>
						</div>

						<div class="form-group">
							<label for="favouriteBook" class="col-sm-3 control-label">Favourite
								book: </label>
							<div class="col-xs-5">
								<form:select path="favouriteBook" class="form-control">
									<form:option value="-1" label="-- Select book --" />
									<form:options items="${books}" itemValue="id" itemLabel="title" />
								</form:select>
								<form:errors path="favouriteBook" cssClass="alert-danger" />
							</div>
						</div>

						<div class="form-group">
							<label for="favouriteAccount" class="col-sm-3 control-label">Favourite
								account: </label>
							<div class="col-xs-5">
								<form:select path="favouriteAccount" class="form-control">
									<form:option value="-1" label="-- Select account --" />
									<form:options items="${accounts}" itemValue="id"
										itemLabel="number" />
								</form:select>
								<form:errors path="favouriteAccount" cssClass="alert-danger" />
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


