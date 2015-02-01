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
				<div style="margin-bottom: 20px;">
					<a href="users/add">
						<button type="button" class="btn btn-success">
							<span class="glyphicon glyphicon-plus-sign"></span>
							&nbsp;&nbsp;Add new user
						</button>
					</a>
				</div>
				<c:if test="${!empty users}">
					<table class="table table-striped">
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th class="text-right">Action</th>
						</tr>

						<c:forEach items="${users}" var="user">
							<tr>
								<td><c:out value="${user.id}" /></td>
								<td><c:out value="${user.name}" /></td>
								<td>
									<div class="dropdown pull-right">
										<a id="dLabel" role="button" data-toggle="dropdown"
											data-target="#" href="#"> Action <span class="caret"></span>
										</a>
										<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
											<li><a href="users/view/${user.id}"> <span
													class="glyphicon glyphicon-info-sign"> </span>
													&nbsp;&nbsp;View
											</a></li>
											<li><a href="users/edit/${user.id}"> <span
													class="glyphicon glyphicon-pencil"> </span>
													&nbsp;&nbsp;Edit
											</a></li>
											<li>
												<a href="users/delete/${user.id}"> <span
													class="glyphicon glyphicon-trash"> </span>
													&nbsp;&nbsp;Delete
											</a>
											</li>
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
</body>
</html>

