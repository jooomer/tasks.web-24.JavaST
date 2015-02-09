<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/tld/custom.tld"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<jsp:useBean id="product" scope="session" class="ua.store.model.instances.products.Product" />

<h1>
	<fmt:message key="common_product.Product_page" />
</h1>
<br>

	<pre>
	<b><fmt:message key="common_product.Product_ID" />:</b>			${product.productId}

	<b><fmt:message key="common_product.Product_type" />:</b> 			${product.productType}

	<b><fmt:message key="common_product.Product_name" />:</b>			${product.productName}

	<b><fmt:message key="common_product.Product_price" />:</b>			${product.price}

	<b><fmt:message key="common_product.Product_description" />:</b> 	${product.description}
	</pre>

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<form action="cart" method="post" >
	<button type="submit" name="command" value="send_to_cart" ><fmt:message key="common_product.Send_to_cart_button" /></button>
</form>

