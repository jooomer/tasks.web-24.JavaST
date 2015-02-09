<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="ua.store.model.instances.users.User"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<div id="top-navigation">
	<form method="post">
		<a title="My Account" href="account">
			<fmt:message key="top_menu.MY_ACCOUNT" />
		</a>
		<a class="cart"	title="Shopping Cart" href="cart">
			<fmt:message key="top_menu.SHOPPING_CART" />
		</a>&nbsp;&nbsp;
 		<select id="language" name="language" onchange="submit()">
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			<option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
		</select>
	</form>
</div>

<c:set var="successful" value='${sessionScope["successful"]}' />

<div class="welcome-message">
	<c:choose>
		<c:when test="${successful == null}">
			<fmt:message key="top_menu.Hi_guest" />&nbsp;&nbsp;
			<a href="login">
				<fmt:message key="top_menu.Sign_in_or_register" />&nbsp;&nbsp;
			</a>
		</c:when>

		<c:otherwise>
				<fmt:message key="top_menu.Hi" />&nbsp;${user.userName}!&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="login?command=logout">
				<fmt:message key="top_menu.Logout" />&nbsp;&nbsp;
			</a>
		</c:otherwise>
	</c:choose>
</div>

