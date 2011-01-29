<%-- 
    Document   : projectList
    Created on : Oct 22, 2010, 2:33:38 PM
    Author     : sandy
--%>
<%@ include file="/WEB-INF/jsp/main/header.jsp" %>
<h1>Project List</h1>
<a href="add">Add Project Document</a> <a href="report">Report</a>
<c:if test="${fn:length(projectList) > 0}">
    <table cellpadding="5">
        <tr class="even">
            <th><input type="checkbox" name="checkall"></th>
            <th><a href="list?sort=${sort}&field=id">Id</a></th>
            <th><a href="list?sort=${sort}&field=RFP">Project Name</a></th>
            <th> </th>
        </tr>
        <c:forEach items="${projectList}" var="list" varStatus="status">
            <tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
                <td><input type="checkbox" name="checkall"></td>
                <td><c:out value="${list.id}"/></td>
                <td><c:out value="${list.RFP}"/></td>

                <td><a href="delete?id=${list.id}">Delete</a> | <a href="add?id=${list.id}">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
    <c:forEach var="i" begin="1" end="${maxPage}" step="1" varStatus ="status">
        <c:choose>
            <c:when test="${empty param.offset or param.offset != i}">
                <a href="?offset=<c:out value="${i}" />"><c:out value="${i}" /></a>
            </c:when>
            <c:otherwise>
                <c:out value="${i}" />
            </c:otherwise>
        </c:choose>
    </c:forEach>
    Total Data : ${totalData}
</c:if>

<%@ include file="/WEB-INF/jsp/main/footer.jsp" %>