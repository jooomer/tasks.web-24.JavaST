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
	<fmt:message key="cart.Cart" />
</h1>
<br>

<h3><c:out value="${sessionScope['message']}" /></h3>
<br>

<c:set var="productMap" value='${sessionScope["productMap"]}' />

<c:choose>
	<c:when test="${productMap.size != 0}" >
		<ulist:cart-table rows="${ productMap.size }" >
			${ productMap.productId } </td><td>
			${ productMap.productType } </td><td>
			${ productMap.productName } </td><td>
			${ productMap.price } </td><td>
			${ productMap.description }
		</ulist:cart-table>
		<br>

			<c:set var="cart" value="${sessionScope['cart']}" />
			<b>
				<fmt:message key="сart.Amount" />
			</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cart.amount}
		<br>
		<br>
		<br>

			<form action="order" method="post">
				<button type="submit" name="command" value="make_an_order">
					<fmt:message key="сart.Make_an_order_button" />
				</button>
			</form>

		<br>

			<form action="cart" method="post">
				<button type="submit" name="command" value="clear_cart">
					<fmt:message key="сart.Clear_cart_button" />
				</button>
			</form>
	</c:when>
	<c:otherwise>
		<fmt:message key="сart.Your_cart_is_empty" />.
	</c:otherwise>
</c:choose>
