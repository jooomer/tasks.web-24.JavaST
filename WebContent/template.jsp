<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="ua.store.model.instances.users.User"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="${language}" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<c:set var="title" value='${sessionScope["title"]}' />
	<title>${title}</title>
	<c:import url="layouts/head.jsp" />
</head>
<body>
	<div class="shell">

		<!-- Top navigation -->
		<c:import url="layouts/top_menu.jsp" />
		<!-- END Top navigation -->

		<div class="cl">222</div>
		<div id="wrapper-top">333</div>
		<!-- Wrapper Middle -->
		<div id="wrapper-middle">

			<!-- Header -->
			<c:import url="layouts/header.jsp" />
			<!-- END Header -->

			<!-- Navigation -->
			<c:import url="layouts/main_menu.jsp" />
			<!-- END Navigation -->

			<!-- Main -->
			<div id="main">

				<!-- Slider -->
				<!-- END Slider -->

				<!-- Sidebar -->
				<c:import url="layouts/sidebar.jsp" />
				<!-- END Sidebar -->

				<!-- Content -->
				<div id="content">
 					<c:set var="jspPage" value='${sessionScope["jspPage"]}' />
					<c:import url="${jspPage}" />
				</div>

				<!-- END Content -->

				<div class="cl"></div>
			</div>
			<!-- END Main -->
		</div>
		<!-- END Wrapper Middle -->

		<div id="wrapper-bottom"></div>
		<!-- Footer  -->
<!-- 		<c:import url="layouts/footer.jsp" />  -->
		<!-- END Footer -->
	</div>
</body>
</html>