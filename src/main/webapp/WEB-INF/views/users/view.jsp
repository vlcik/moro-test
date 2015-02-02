<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
				<div class="row">
					<ol class="breadcrumb">
						<li><a href="/sokrates/admin/users">Users</a></li>
						<li class="active">${user.name}</li>
					</ol>
				</div>
				<h2>
					<c:out value="${user.name}" />
				</h2>

				<ul class="nav nav-tabs">
					<li class="active"><a href="#detail">Personal info</a></li>
					<li class=""><a href="#books">Books</a></li>
					<li class=""><a href="#accounts">Accounts</a></li>
				</ul>
				<div class="tab-content">
					<div id="detail" class="tab-pane fade in active">
						<div style="margin: 20px 0px;">
							<p><b>ID: </b> <c:out value="${user.id}" /></p>
							<p><b>Username: </b> <c:out value="${user.username}" /></p>
							<p><b>Name: </b> <c:out value="${user.name}" /></p>
							<p><b>Favourite book: </b> <c:out value="${user.favouriteBook.title}" /></p>
							<p>
								<b>Favourite account: </b> 
								<c:out value="${user.favouriteAccount.prefix}" />&nbsp;<c:out value="${user.favouriteAccount.number}" />/<c:out value="${user.favouriteAccount.bankCode}" />
							</p>
						</div>
					</div>
					<div id="books" class="tab-pane fade">
						<div style="margin: 20px 0px;">
							<a href="/sokrates/admin/users/${user.id}/books/add">
								<button type="button" class="btn btn-success">
									<span class="glyphicon glyphicon-plus-sign"></span>
									&nbsp;&nbsp;Add new book
								</button>
							</a>
						</div>
						<c:if test="${!empty user.books}">
							<table class="table table-striped">
								<tr>
									<th>ID</th>
									<th>Title</th>
									<th>Description</th>
									<th class="text-right">Action</th>
								</tr>

								<c:forEach items="${user.books}" var="book">
									<tr>
										<td><c:out value="${book.id}" /></td>
										<td><c:out value="${book.title}" /></td>
										<td><c:out value="${fn:substring(book.description, 0, 50)}"/></td>
										<td>
											<div class="dropdown pull-right">
												<a id="dLabel" role="button" data-toggle="dropdown"
													data-target="#" href="#"> Action <span class="caret"></span>
												</a>
												<ul class="dropdown-menu" role="menu"
													aria-labelledby="dLabel">
													<li><a
														href="/sokrates/admin/users/${user.id}/books/edit/${book.id}">
															<span class="glyphicon glyphicon-pencil"> </span>
															&nbsp;&nbsp;Edit
													</a></li>
													<li><a
														href="/sokrates/admin/users/${user.id}/books/delete/${book.id}">
															<span class="glyphicon glyphicon-trash"> </span>
															&nbsp;&nbsp;Delete
													</a></li>
												</ul>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</c:if>

					</div>

					<div id="accounts" class="tab-pane fade active">
						<div style="margin: 20px 0px;">
							<a href="/sokrates/admin/users/${user.id}/accounts/add">
								<button type="button" class="btn btn-success">
									<span class="glyphicon glyphicon-plus-sign"></span>
									&nbsp;&nbsp;Add new account
								</button>
							</a>
						</div>
						<c:if test="${!empty user.accounts}">
							<table class="table table-striped">
								<tr>
									<th>ID</th>
									<th>Prefix</th>
									<th>Number</th>
									<th>Bank code</th>
									<th class="text-right">Action</th>
								</tr>

								<c:forEach items="${user.accounts}" var="account">
									<tr>
										<td><c:out value="${account.id}" /></td>
										<td><c:out value="${account.prefix}" /></td>
										<td><c:out value="${account.number}" /></td>
										<td><c:out value="${account.bankCode}" /></td>
										<td>
											<div class="dropdown pull-right">
												<a id="dLabel" role="button" data-toggle="dropdown"
													data-target="#" href="#"> Action <span class="caret"></span>
												</a>
												<ul class="dropdown-menu" role="menu"
													aria-labelledby="dLabel">
													<li><a
														href="/sokrates/admin/users/${user.id}/accounts/edit/${account.id}">
															<span class="glyphicon glyphicon-pencil"> </span>
															&nbsp;&nbsp;Edit
													</a></li>
													<li><a
														href="/sokrates/admin/users/${user.id}/accounts/delete/${account.id}">
															<span class="glyphicon glyphicon-trash"> </span>
															&nbsp;&nbsp;Delete
													</a></li>
												</ul>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.nav-tabs a:first').tab('show');
			$('.nav-tabs a').click(function(e) {
				e.preventDefault();
				$(this).tab('show');
			});
		});
	</script>
</body>
</html>

