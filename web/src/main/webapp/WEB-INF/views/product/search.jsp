<%@ include file="/common/global.jsp"%>
<html>
<head>
	<title>Product | Search</title>
	
	<style type="text/css" media="screen"> 
	 
	table{ 
	border-collapse:collapse; 
	border:1px solid #FF0000; 
	} 
	 
	table th,td{ 
	border:1px solid #FF0000; 
	} 
	</style> 
	
</head>
<body>

	<div id="search">
	<form method="post" action="${ctx}/products/search" id="searchForm">
	    <input type="text" size="20" name="productName" id="productName" value="${param.productName}"
	           placeholder="Enter Product Name"/>
	    <input type="submit" value="Search"/>
	</form>
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
