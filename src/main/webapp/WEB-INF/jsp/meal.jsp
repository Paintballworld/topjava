<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>


<section>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="/meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt><fmt:message key="meals.DateTime"/></dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="meals.description"/></dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="meals.calories"/></dt>
            <dd><input type="number" value="${meal.calories}" name="calories"></dd>
        </dl>
        <button type="submit"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()"><fmt:message key="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
