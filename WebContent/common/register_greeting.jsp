<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<jsp:useBean id="user" scope="session" class="ua.store.model.instances.users.User"/>

	<h3> Hi! <jsp:getProperty name="user" property="userName"/></h3>

	<c:set var="message" value='${requestScope["message"]}' />
	<h3>${message}</h3>

	<h3>Congratulations! You are successfully registered!</h3>
