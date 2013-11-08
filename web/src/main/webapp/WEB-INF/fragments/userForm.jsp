<%@ include file="/common/global.jsp"%>
	<form:form method="POST" commandName="user">
	<form:errors path="*" cssClass="form_error"></form:errors>
		<table >			
			<tr>				
				<td>User Name*</td>
				<security:authorize ifNotGranted="Guest">				
				<td><form:input path="username" readonly="true" cssStyle="color:gray"/></td>
				</security:authorize>					
				<security:authorize ifAnyGranted="Guest">
				<td><form:input path="username" autocomplete="false"/></td>	
				</security:authorize>		
				<td><form:errors path="username" cssClass="form_error" /></td>				
			</tr>
			<tr>
				<td>Password*</td>
				<td><form:password path="password" showPassword="false"/></td>
				<td><form:errors path="password"/></td>			
			</tr>							
			<tr>
				<td>First Name*</td>
				<td><form:input path="firstName"/></td>
				<td><form:errors path="firstName" cssClass="form_error"/></td>
			</tr>
			<tr>
				<td>Last Name*</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" cssClass="form_error"/></td>
			</tr>
			<tr>
				<td>Email Address</td>
				<td><form:input path="email"/></td>
				<td><form:errors path="email" cssClass="form_error"/></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><form:input path="phoneNumber"/></td>
				<td><form:errors path="phoneNumber" cssClass="form_error"/></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Submit" /></td>
			</tr>						
		</table>	
	</form:form>