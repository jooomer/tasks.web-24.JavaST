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
	<fmt:message key="order_created.Order" />
</h1>
<br>

<h3><c:out value='${requestScope["message"]}' /></h3>
<br>

<c:set var="order" value='${sessionScope["order"]}' />
<c:set var="user" value='${sessionScope["user"]}' />
<pre>
	<b><fmt:message key="order_created.orderId" />:</b>	${order.orderId}

	<b><fmt:message key="order_created.Customer" />:</b>	${user.firstName} ${user.lastName}

	<b><fmt:message key="order_created.Date" />:</b>		${order.orderDate}

	<b><fmt:message key="order_created.Amount" />:</b>		${order.amount}

	<b><fmt:message key="order_created.Order_status" />:</b>	${order.orderStatus}

	<b><fmt:message key="order_created.Ship_to" />:</b>	${user.address}

</pre>
<br>

<c:set var="productMap" value='${sessionScope["productMap"]}' />
<ulist:cart-table rows="${ productMap.size }" >
	${ productMap.productId } </td><td>
	${ productMap.productType } </td><td>
	${ productMap.productName } </td><td>
	${ productMap.price } </td><td>
	${ productMap.description }
</ulist:cart-table>
<br>

<b><fmt:message key="order_created.Your_comments" />:</b><br>
${order.comments}
<br>
<br>
<form action="checkout" method="post">
	<button type="submit" name="command" value="checkout" ><fmt:message key="order_created.Checkout_button" /></button>
</form>
<br>