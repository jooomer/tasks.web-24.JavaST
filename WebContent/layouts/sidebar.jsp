<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="ua.store.model.instances.users.User"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<c:set var="user" value='${sessionScope["user"]}' />

<div id="sidebar">
	<div class="box">
		<div class="title">
			<h2>
				<fmt:message key="sidebar.Administrator_menu" />
			</h2>
			<img class="bullet" src="css/images/bullet.png"
				alt="small grey bullet" />
		</div>
		<ul>
			<c:if test='${user.userType.toString() == "ADMIN"}'>
				<li>
					<a title="Users" href="administrator/users">
						<fmt:message key="sidebar.Users" />
					</a>
				</li>
				<li>
					<a title="Delete user" href="administrator/products">
						<fmt:message key="sidebar.Products" />
					</a>
				</li>
				<li>
					<a title="Delete user" href="administrator/add-product">
						<fmt:message key="sidebar.Add_product" />
					</a>
				</li>
<%--
				<li>
					<a title="Delete user" href="administrator/orders">
						<fmt:message key="sidebar.Orders" />
					</a>
				</li>
 --%>
			</c:if>

		</ul>

<br>
<br>
<br>

		<div class="title">
			<h2>
				<fmt:message key="sidebar.Initialization_menu" />
			</h2>
			<img class="bullet" src="css/images/bullet.png"
				alt="small grey bullet" />
		</div>
		<ul>
			<li>
				<a title="Users" href="initialization">
					<fmt:message key="sidebar.Initialization" />
				</a>
			</li>
		</ul>
	</div>
</div>