<%@ include file="/common/global.jsp"%>
<html>
<head>
	<title>Weather</title>
</head>
<body>

	<div id="search">
	<form method="post" action="${ctx}/weather/forecast" id="searchForm">
	    <input type="text" size="20" name="zip" id="zip" value="${param.zip}"
	           placeholder="Enter Zip Code"/>
	    <input type="submit" value="Search"/>
	</form>
	</div>

	<div>
	Location : <c:out value="${weatherInfo.city}"/>, <c:out value="${weatherInfo.state}"/>	
    </div>
    
    <br />
    
	<% //Use display tag to generate the table %>
	<display:table name="weatherInfo.forecast" id="forecast" cellspacing="0" cellpadding="0" requestURI="" 
	    defaultsort="1" pagesize="25" class="mytable" export="true" >
		<display:column property="date" escapeXml="true" title="Date" />
		<display:column property="temperatures.morningLow" escapeXml="true" title="Temp (low)" />
		<display:column property="temperatures.daytimeHigh" escapeXml="true" title="Temp (high)" />
		<display:column property="desciption" escapeXml="true" title="Desciption" />
		<display:column property="probabilityOfPrecipiation.daytime" escapeXml="true" title="Precip (day)" />    
		<display:column property="probabilityOfPrecipiation.nighttime" escapeXml="true" title="Precip (night)" />
	</display:table>

</body>
</html>
