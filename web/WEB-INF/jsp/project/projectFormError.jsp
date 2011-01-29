<%-- 
    Document   : projectError
    Created on : Nov 4, 2010, 10:14:38 AM
    Author     : sandy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Project</title>
    </head>
    <body>
        <!--commandName="project">-->

        <form:form action="addProcess" >
            <form:hidden path="id" />
            <table>
                <tr>
                    <td>Project Number :</td>
                    <td><form:input path="projectNumber"/></td>
                </tr>

                <tr>
                    <td>Project Name :</td>
                    <td><form:input path="name"/>
                        <form:errors path="name" cssClass="error"  />
                    </td>
                </tr>

                <tr>
                    <td>Customer :</td>
                    <td><form:input path="projectCustomer"/></td>
                </tr>




                <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
