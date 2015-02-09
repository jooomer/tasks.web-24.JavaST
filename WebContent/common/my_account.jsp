<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/tld/custom.tld"%>
<%@ page import="ua.store.model.instances.users.User"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<h1>
	<fmt:message key="my_account.My_account" />
</h1>
<br>
<jsp:useBean id="user" scope="session" class="ua.store.model.instances.users.User" />

<form action="account" method="post" accept-charset="UTF-8">
<pre>

<b><fmt:message key="my_account.userName" />:</b>	<jsp:getProperty property="userName" name="user"/>

<b><fmt:message key="my_account.First_name" />:</b>	<jsp:getProperty property="firstName" name="user"/>
		<input type="text" name="firstName" />

<b><fmt:message key="my_account.Last_name" />:</b>	<jsp:getProperty property="lastName" name="user"/>
		<input type="text" name="lastName" />

<b><fmt:message key="my_account.Email" />:</b>		<jsp:getProperty property="email" name="user"/>
		<input type="text" name="email" />

<b><fmt:message key="my_account.Phone" />:</b>		<jsp:getProperty property="phone" name="user"/>
		<input type="text" name="phone" />

<b><fmt:message key="my_account.Address" />:</b>	<jsp:getProperty property="address" name="user"/>
		<input type="text" name="address" />

		<button type="submit" name="command" value="update_user"><fmt:message key="my_account.Update_button" /></button>
</pre>
</form>
<br>
<fmt:message key="my_account.Go_to" /> <a href="orders" >&nbsp;&nbsp;--- <fmt:message key="my_account.list_of_orders" /> ---</a>

