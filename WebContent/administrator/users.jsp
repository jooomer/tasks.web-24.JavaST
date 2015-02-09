<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ulist" uri="/WEB-INF/tld/custom.tld"%>
<%@ page import="ua.store.model.instances.users.User"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1>
	<fmt:message key="users.List_of_users" />
</h1>
<br>

<form action="administrator/search" method="post">
	<fmt:message key="users.Search" />:
	<input type="text" name="userName" />
	<button type="submit" name="command" value="search_user_by_userName">
		<fmt:message key="users.Search_button" />
	</button>
</form>
<br>

<hr/>
<br>
<ulist:users-table rows="${ userList.size }" >
	${ userList.userId } </td><td>
	${ userList.userType } </td><td>
	${ userList.userName } </td><td>
	${ userList.firstName } </td><td>
	${ userList.lastName } </td><td>
	${ userList.email } </td><td>
	${ userList.phone } </td><td>
	${ userList.address } </td><td>
	${ userList.comments } </td><td>
	${ userList.inBlackList }
</ulist:users-table>

