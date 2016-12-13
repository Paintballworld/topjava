<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yerlan
  Date: 12/9/16
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>


<table width="%90">

    <c:forEach items="${list}" var="m">
        <tr>
            <td>${m.id}</td>
            <td>${m.description}</td>
            <td>${m.calories}</td>
            <td>${m.dateTime}</td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
