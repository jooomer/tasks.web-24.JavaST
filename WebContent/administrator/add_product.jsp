<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/tld/custom.tld"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1>
	<fmt:message key="add_product.Add_new_product" />
</h1>
<br>

<form action="administrator/product" method="post" >
	<pre>
	<fmt:message key="add_product.Product_type" />: 			<select name="productType" >
		<option value="ARMCHAIR"><fmt:message key="add_product.Armchair" /></option>
		<option value="CABINET"><fmt:message key="add_product.Cabinet" /></option>
		<option value="SOFA"><fmt:message key="add_product.Sofa" /></option>
		<option value="TABLE"><fmt:message key="add_product.Table" /></option>
	</select>

	<fmt:message key="add_product.Product_name" />:			<input type="text" name="productName" />

	<fmt:message key="add_product.Product_price" />: 			<input type="text" name="price" />

	<fmt:message key="add_product.Quantity_in_stock" />: 	<input type="text" name="quantityInStock" />

	<fmt:message key="add_product.Product_description" />:
	<textarea name="description" rows="5" cols="60">
	</textarea>

	<button type="submit" name="command" value="add_product" ><fmt:message key="add_product.Save_new_product_button" /></button>
	</pre>
</form>

