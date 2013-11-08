<%@ include file="/common/global.jsp"%>
<html>
<head>
	<title>Product | All</title>	
</head>
<body>
	<div>
		<a href="<c:url value="/product/search" />">Search Products</a>
    </div>
    
    <br />
    
	<% //Use display tag to generate the table %>
	<display:table name="products" id="products" cellspacing="0" cellpadding="0" requestURI="" 
	    defaultsort="1" pagesize="25" class="mytable" export="true" >
		<display:column property="name" escapeXml="true" title="name" />
		<display:column property="description" escapeXml="true" title="description" />
	</display:table>

</body>
</html>
