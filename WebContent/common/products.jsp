<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/tld/custom.tld"%>
<%@ page import="ua.store.model.instances.products.Product"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="productType" value='${sessionScope["productType"]}' />

<h1>
	<fmt:message key="products.List_of" />
	${productType}
	<fmt:message key="products.s" />
</h1>

<br>
<form action="product" method="post">
	<fmt:message key="products.Search_product" />:
	<input type="text" name="productName" />
	<button type="submit" name="command" value="common_search_product_by_productName">
		<fmt:message key="products.Search_product_button" />
	</button>
</form>
<br>

<hr/>
<br>
<ulist:product-table rows="${ productMap.size }" >
	${ productMap.productId } </td><td>
	${ productMap.productType } </td><td>
	${ productMap.productName } </td><td>
	${ productMap.price } </td><td>
	${ productMap.quantityInStock } </td><td>
	${ productMap.description }
</ulist:product-table>

