<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="ua.store.model.instances.users.User"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<div id="navigation">
	<ul>
		<li class="first">
			<a title="Home" href="index.jsp">
				<fmt:message key="main_menu.Home" />
			<span class="home">&nbsp;</span>
			</a>
		</li>
		<li>
			<a title="Cabinets" href="cabinets">
				<fmt:message key="main_menu.Cabinets" />
			</a>
		</li>
		<li>
			<a title="Sofas" href="sofas">
				<fmt:message key="main_menu.Sofas" />
			</a>
		</li>

		<li>
			<a title="Armchairs" href="armchairs">
				<fmt:message key="main_menu.Armchairs" />
			</a>
		</li>
		<li>
			<a title="Tables" href="tables">
				<fmt:message key="main_menu.Tables" />
			</a>
		</li>
	</ul>
	<div class="cl"></div>
</div>