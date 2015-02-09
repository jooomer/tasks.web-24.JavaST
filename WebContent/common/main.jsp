<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!-- Products -->
<div class="products">
	<div class="title">
		<h2>
			<fmt:message key="main.Products" />
		</h2>
		<img class="bullet" src="css/images/bullet.png"
			alt="small grey bullet" />
	</div>

	<div class="row">
		<div class="product-holder">
			<div class="product">
				<a title="More Details" href="cabinets"><img src="css/images/1.jpg"
					alt="Yin Yang shaped bookshelf" /></a> <img class="top-label"
					src="css/images/top.png" alt="top sign" />
				<div class="desc">
						<fmt:message key="main.Cabinets" />
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="product-holder">
			<div class="product">
				<a title="More Details" href="sofas"><img src="css/images/6.jpg"
					alt="Brown table with pink, blue and two green stools" /></a> <img
					class="top-label" src="css/images/top.png" alt="top sign" />
				<div class="desc">
						<fmt:message key="main.Sofas" />
				</div>
				<div class="bottom"></div>
			</div>
			<div class="product-bottom"></div>
		</div>
	</div>

	<div class="row">
		<div class="product-holder">
			<div class="product">
				<a title="More Details" href="armchairs"><img src="css/images/5.jpg"
					alt="table" /></a>
				<div class="desc">
						<fmt:message key="main.Armchairs" />
				</div>
				<div class="bottom"></div>
			</div>
			<div class="product-bottom"></div>
		</div>
		<div class="product-holder">
			<div class="product">
				<a title="More Details" href="tables"><img src="css/images/4.jpg"
					alt="Armchair with a round stool" /></a> <img class="new-label"
					src="css/images/new.png" alt="new sign" />
				<div class="desc">
						<fmt:message key="main.Tables" />
				</div>
				<div class="bottom"></div>
			</div>
			<div class="product-bottom"></div>
		</div>

	</div>
	<div class="cl"></div>
</div>
<!-- END Products -->