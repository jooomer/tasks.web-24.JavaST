<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1>
	<fmt:message key="checkout.Checkout" />
</h1>
<br>

<h3><c:out value='${requestScope["message"]}' /></h3>
<br>
<fmt:message key="checkout.all_orders" />

<a href="account" >
<fmt:message key="checkout.your_account" />
</a>
