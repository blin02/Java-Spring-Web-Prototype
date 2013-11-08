<%@ include file="/common/global.jsp"%>	
	<div align="right">
		<security:authorize ifNotGranted="Guest">
		Welcome <security:authentication property="name" />
		<a href="<c:url value="/user/logout" />">Logout</a>
		</security:authorize>
		<security:authorize ifAnyGranted="Guest">
		Welcome Guest 
		<a href="<c:url value="/user/login" />">Login</a>
		</security:authorize>			
	</div>
	
	<div align="center">
		<a href="<c:url value="/" />">Home</a>
		<a href="<c:url value="/product" />">Product</a>
		<a href="<c:url value="/weather" />">Weather</a>
		<a href="<c:url value="/user" />">My Account</a>
		<security:authorize ifAnyGranted="Admin">
		<a href="<c:url value="/admin" />">Admin</a>
		</security:authorize>		
	</div>
	
