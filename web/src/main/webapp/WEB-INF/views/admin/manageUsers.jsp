<%@ include file="/common/global.jsp"%>
<%@ page session="false" %>
<html>
<head>
	<title>Manage Users</title>
</head>
<body>
		
	<% //Use display tag to generate the table %>
	<display:table name="users" id="users" cellspacing="0" cellpadding="0" requestURI="" 
	    defaultsort="1" pagesize="25" class="mytable" export="true" >
		<display:column property="id" escapeXml="true" title="Id" />
		<display:column property="username" escapeXml="true" title="username" />
		<display:column property="email" escapeXml="true" title="Email" />
		<display:column property="firstName" escapeXml="true" title="First Name" />
		<display:column property="lastName" escapeXml="true" title="Last Name" />
		<display:column property="phoneNumber" escapeXml="true" title="Phone Number" />
		<display:column property="lockedOut" escapeXml="true" title="Locked Out" />
	</display:table>

</body>
</html>
