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
	<fmt:message key="order.Order" />
</h1>
<br>

<c:set var="order" value='${sessionScope["order"]}' />
<c:set var="user" value='${sessionScope["user"]}' />
<pre>
	<b><fmt:message key="order.Customer" />:</b>	${user.firstName} ${user.lastName}

	<b><fmt:message key="order.Date" />:</b>		${order.orderDate}

	<b><fmt:message key="order.Amount" />:</b>		${order.amount}

	<b><fmt:message key="order.Ship_to" />:</b>	${user.address}

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

<form action="order" method="post">
	<b><fmt:message key="order.Your_comments" />:</b><br>
	<textarea name="comments" rows="5" cols="70"></textarea>
	<br>
	<br>
	<button type="submit" name="command" value="save_order" ><fmt:message key="order.Save_order_button" /></button>
</form>
<br>
