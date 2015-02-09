<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Search result page</h1>
<br>

<form action="administrator/search" method="post">
	<p>Search user by userName: <input type="text" name="userName" />
	<button type="submit" name="command" value="search_user_by_userName">Search</button></p>
</form>
<br>

<hr/>
<br>
<h3>
	Search result by UserName 
	"<jsp:getProperty property="userName" name="search_user" />":
</h3>
<br>
<jsp:useBean id="search_user" scope="session" class="ua.store.model.instances.users.User" />

<form action="account" method="post">
	<p>
		<b>UserId:</b>&nbsp;&nbsp;
		<jsp:getProperty property="userId" name="search_user" /></p>

	<p>
		<b>Type:</b>&nbsp;&nbsp;
		<jsp:getProperty property="userType" name="search_user" />&nbsp;&nbsp;
		<select name="userType" >
			<option value="empty">--- Choose the user type ---</option>
			<option value="CLIENT">CLIENT</option>
			<option value="ADMIN">ADMIN</option>
		</select>	
		</p>

	<p>
		<b>UserName:</b>&nbsp;&nbsp;
		<jsp:getProperty property="userName" name="search_user" /></p>

	<p>
		<b>First Name:</b>&nbsp;&nbsp;
		<jsp:getProperty property="firstName" name="search_user" />
	<p>
		<b>Last Name:</b>&nbsp;&nbsp;
		<jsp:getProperty property="lastName" name="search_user" />
	<p>
		<b>Email:</b>&nbsp;&nbsp;
		<jsp:getProperty property="email" name="search_user" />
	<p>
		<b>Phone:</b>&nbsp;&nbsp;
		<jsp:getProperty property="phone" name="search_user" />
	<p>
		<b>Address:</b>&nbsp;&nbsp;
		<jsp:getProperty property="address" name="search_user" /></p>
		

		<b>Comments:</b>
		<br>
		<textarea name="comments" rows="5" cols="60">
			<jsp:getProperty property="comments" name="search_user" />
		</textarea>
		
	<p>
		<b>Black list:</b>
		<jsp:getProperty property="inBlackList" name="search_user" /><br>
		
		<c:set var="search_user" value='${sessionScope["search_user"]}' />
		<c:choose>
			<c:when test="${search_user.inBlackList == false}">
				<input type="radio" name="inBlackList" value="false" checked>Not in Black list<br>
				<input type="radio" name="inBlackList" value="true" >In Black list
			</c:when>
			<c:otherwise>
				<input type="radio" name="inBlackList" value="false" >Not in Black list<br>
				<input type="radio" name="inBlackList" value="true" checked>In Black list
 			</c:otherwise>
		
		</c:choose>
		
	</p>
	
	<p>
		<button type="submit" name="command" value="update_edit_user">Update
			user</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="submit" name="command" value="delete_user">Delete
			user</button>
	</p>

</form>

