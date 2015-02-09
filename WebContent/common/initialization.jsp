<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<%@ page import="ua.store.model.instances.products.Product"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/tld/custom.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1>
	<fmt:message key="initialization.Initialization" />
</h1>
<br>

<h3><c:out value="${sessionScope['message']}" /></h3>
<br>

<c:if test="${sessionScope['init_success'] != 'init_success'}">
	<form action="initialization" method="post">
		<button type="submit" name="command" value="initialize_DB">
			<fmt:message key="initialization.initialize_button" />
		</button>
	</form>
</c:if>