<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="cz.moro.sokrates.i18n.messages"/>
<c:set var="now" value="<%=new java.util.Date()%>" />
<sec:authentication var="principal" property="principal" />

<div style="margin: 50px 0px;">
	<p>
		<b><fmt:message key="logged"/>: </b> <c:out value="${principal.username}"/>&nbsp;&nbsp;
		<b><fmt:message key="date"/>: </b> <fmt:formatDate value="${now}" pattern="d.M.yyyy HH:mm:ss" />&nbsp;&nbsp;
		<b><fmt:message key="count"/>: </b> <c:out value="${count}"/>
	</p>
</div>