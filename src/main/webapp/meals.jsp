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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>


<jsp:include page="mealForm.html"></jsp:include>

<table class="table">

    <c:forEach items="${list}" var="m">
        <c:if test="${m.exceed == false}"><tr class="info"></c:if>
        <c:if test="${m.exceed == true}"><tr class="danger"></c:if>
            <td hidden>${m.id}</td>
            <td>${m.dateTime}</td>
            <td>${m.description}</td>
            <td>${m.calories}</td>
            <td width="%5">
                <a href="update?id=${m.id}"><span class="glyphicon glyphicon-pencil"></span></a>
            </td>
            <td width="%5">
                <a href="delete?id=${m.id}"><span class="glyphicon glyphicon-remove"></span></a>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
