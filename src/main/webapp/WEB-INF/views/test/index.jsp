<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<h1>Test data</h1>
				<h3>Title</h3>
				<p>
					<c:out value="${movie.title}" />
				</p>
				<h3>Director</h3>
				<p>
					<c:out value="${movie.director}" />
				</p>
				<h3>Publish date</h3>
				<p>
					<fmt:formatDate value="${movie.publishDate}"
						pattern="d.M.yyyy" />
				</p>

				<h3>Characters</h3>
				<c:if test="${!empty movie.characters}">
					<p>
						<c:forEach items="${movie.characters}" var="character">
							<c:out value="${character.name}" />,
						</c:forEach>
					</p>
				</c:if>

				<h3>Actors</h3>
				<c:if test="${!empty movie.actors}">
					<table class="table">
						<tr>
							<th>Character</th>
							<th>Actor</th>
						</tr>
						<c:forEach items="${movie.actors}" var="actor">
							<tr>
								<td><c:out value="${actor.key.name}" /></td>
								<td><c:out value="${actor.value}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				
				<p>
					<a href="image?name=seven.jpg">Image</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>

