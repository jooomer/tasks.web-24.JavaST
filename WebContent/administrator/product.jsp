<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="product" scope="session" class="ua.store.model.instances.products.Product" />

<h1>Product page</h1>
<br>

<form action="administrator/product" method="post" >
	<pre>
	<b>Product ID:</b>			${product.productId}

	<b>Product type:</b> 			${product.productType}	<select name="productType" >
		<option value="empty">--- Choose the product type ---</option>
		<option value="ARMCHAIR">Armchair</option>
		<option value="CABINET">Cabinet</option>
		<option value="SOFA">Sofa</option>
		<option value="TABLE">Table</option>
	</select>

	<b>Product name:</b>			${product.productName}	<input type="text" name="productName" />

	<b>Product price:</b>			${product.price}	<input type="text" name="price" />

	<b>Product quantity in stock:</b>	${product.quantityInStock}	<input type="text" name="quantityInStock" />

	<b>Product description:</b>
	<textarea name="description" rows="5" cols="60">
		${product.description}
	</textarea>
	</pre>

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="submit" name="command" value="update_product" >Update product</button>

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="submit" name="command" value="delete_product">Delete
			product</button>

</form>

