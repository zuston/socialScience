<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
  Created by IntelliJ IDEA.
  User: zuston
  Date: 17/1/17
  Time: 下午10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:choose>
    <c:when test="${corruptBeenList!=null&&fn:length(corruptBeenList)>0}">
        <c:forEach items="${corruptBeenList}" var="corruptBean">
            <h2>${corruptBean.title}</h2>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h2>corrupt none info</h2>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${officerMap!=null}">
        <c:forEach items="${officerMap.get('personBean')}" var="officer">
            <h2>${officer.simpleInfo}</h2>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h2>officer none info</h2>
    </c:otherwise>
</c:choose>


</body>
</html>
