<%@ include file="/WEB-INF/jsp/main/header.jsp" %>
        <h1>User Add</h1>

<form:form action="addProcess" commandName="user">
             <form:hidden path="id" />
            <table>
		<tr>
			<td>User Name :</td>
			<td><form:input path="name"   /></td>
		</tr>
		<tr>
			<td>Password :</td>
			<td><form:password path="password" /></td>
		</tr>
		<tr>
			<td>Gender :</td>
			<td><form:radiobutton path="gender" value="M" label="M" /> <form:radiobutton path="gender" value="F" label="F" /></td>
		</tr>

	 
		<tr>
			<td colspan="2"><input type="submit" value="Save"></td>
		</tr>
	</table>
</form:form>
     

<%@ include file="/WEB-INF/jsp/main/footer.jsp" %>