<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/tld/custom.tld"%>
<%@ page import="ua.store.model.instances.users.User"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="user" value='${sessionScope["user"]}' />

<h1>
	<fmt:message key="administrator.Administrator_page" />
</h1>
<br>

<p>
	<fmt:message key="administrator.Hi" />
	${user.userName}!
</p>
<p>
	<fmt:message key="administrator.You_are_Administrator" />
</p>
