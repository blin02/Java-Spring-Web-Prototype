<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/global.jsp"%>    
<html>
<head>
	<title>My Site | Admin | <sitemesh:write property='title'/></title>
	
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value="/styles/admin.css" />" />
	
    <sitemesh:write property='head'/>
</head>
<body>
	<jsp:include page="/WEB-INF/fragments/header.jsp" />

	<br />
	
	<h1 class='title'><sitemesh:write property='title'/></h1>

    <div class='mainBody'>
      <sitemesh:write property='body'/>
    </div>

	<jsp:include page="/WEB-INF/fragments/footer.jsp" />

</body>
</html>