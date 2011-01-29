<%-- 
    Document   : projectForm
    Created on : Oct 22, 2010, 10:58:47 AM
    Author     : sandy
--%>

<%@ include file="/WEB-INF/jsp/main/header.jsp" %>
        <h1>Project Add</h1>

        <form:form action="addProcess" commandName="project">
            <form:hidden path="id" />
            <table>
                <tr>
                    <td>Project Number :</td>
                    <td><form:input path="projectNumber"/></td>
                </tr>

                <tr>
                    <td>Project Name :</td>
                    <td><form:input path="name"/>
                        <form:errors path="name" cssClass="error" />
                    </td>
                </tr>

                <tr>
                    <td>Customer :</td>
                    <td><form:input path="projectCustomer"/></td>
                </tr>

                <tr>
                    <td>Start Date :</td>
                    <td>
                        <form:input path="projectStartDate" readonly="true"/>
                       
                    </td>
                </tr>

                <tr>
                    <td>End Date :</td>
                    <td>
                        <form:input path="projectEndDate" readonly="true"/></td>
                </tr>


                <tr>
                    <td>Financial :</td>
                    <td>
                        <form:radiobutton path="projectFinancial" value="On Budget" label="On Budget" />
                        <form:radiobutton path="projectFinancial" value="Over Budget" label="Over Budget" />
                    </td>
                </tr>

                <tr>
                    <td>Schedule :</td>
                    <td>
                        <form:radiobutton path="projectSchedule" value="On Schedule" label="On Schedule" />
                        <form:radiobutton path="projectSchedule" value="Behind Schedule" label="Behind Schedule" />
                    </td>
                </tr>

                <tr>
                    <td>Technical :</td>
                    <td>
                        <form:radiobutton path="projectTechnical" value="Problem" label="Problem" />
                        <form:radiobutton path="projectTechnical" value="No Problem" label="No Problem" />
                    </td>
                </tr>

                <tr>
                    <td>Resource :</td>
                    <td>
                        <form:radiobutton path="projectResource" value="Available" label="Available" />
                        <form:radiobutton path="projectResource" value="Not Available" label="Not Available" />
                    </td>
                </tr>

                <tr>
                    <td>Contract/Legal :</td>
                    <td>
                        <form:radiobutton path="projectContract" value="Done" label="Done" />
                        <form:radiobutton path="projectContract" value="On Progress" label="On Progress" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                </tr>
            </table>
        </form:form>
 <%@ include file="/WEB-INF/jsp/main/footer.jsp" %>
