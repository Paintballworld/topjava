<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fnjj" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fnjj:substring(url, 0, fnjj:length(url) - fnjj:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <title><fmt:message key="app.title"/></title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>