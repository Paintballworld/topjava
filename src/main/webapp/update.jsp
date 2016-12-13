<%--
  Created by IntelliJ IDEA.
  User: yerlan
  Date: 12/13/16
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Updating Meal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<form action="update" method="POST">
    <table class="table">
        <tr>
            <td>Date</td>
            <td>Description</td>
            <td>Calories</td>
        </tr>
        <tr>
            <td>
                <input type="datetime" name="dateTime" value="${mealItem.dateTime}">
            </td>
            <td>
                <input type="text" name="description" value="${mealItem.description}">
            </td>
            <td>
                <input type="number" name="calories" value="${mealItem.calories}">
            </td>
        </tr>
    </table>
    <input type="submit" name="update" value="Сохранить изменения">
</form>

</body>
</html>
