<%--
  Created by IntelliJ IDEA.
  User: me
  Date: 12/11/16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals list</title>
</head>
<body>
    <table width="%80">
        <c:forEach var="mealWExceed" items="$list">
            <tr>
                <td>$mealWExceed.getId()</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
