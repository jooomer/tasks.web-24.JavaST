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
	<fmt:message key="orders.Orders" />
</h1>
<br>

<h3><c:out value="${requestScope['message']}" /></h3>
<br>

<c:set var="ordersList" value='${sessionScope["ordersList"]}' />

<c:choose>
	<c:when test="${ordersList.size != 0}" >
				<ulist:orders-table rows="${ ordersList.size }" >
			${ ordersList.orderId } </td><td>
			${ ordersList.amount } </td><td>
			${ ordersList.orderDate } </td><td>
			${ ordersList.orderStatus } </td><td>
			${ ordersList.comments } </td><td>
			${ ordersList.productNamesList }
		</ulist:orders-table>
	</c:when>
	<c:otherwise>
		<fmt:message key="orders.list_is_empty" />.
	</c:otherwise>
</c:choose>
