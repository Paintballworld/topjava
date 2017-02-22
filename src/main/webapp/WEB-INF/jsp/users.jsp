<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="topjava" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/userDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><spring:message code="users.title"/></h3>

            <div class="view-box">
                <a class="btn btn-info" onclick="add('<spring:message code="users.add"/>')">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                </a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><spring:message code="users.name"/></th>
                        <th><spring:message code="users.email"/></th>
                        <th><spring:message code="users.roles"/></th>
                        <th><spring:message code="users.active"/></th>
                        <th><spring:message code="users.registered"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form modelAttribute="userTo" class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">
                            <spring:message code="users.name" var="userName"/>
                        </label>

                        <div class="col-xs-9">
                            <topjava:inputField label='${userName}' name="name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3">
                            <spring:message code="users.email" var="userEmail"/>
                        </label>

                        <div class="col-xs-9">
                            <topjava:inputField label='${userEmail}' name="email"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3">
                            <spring:message code="users.password" var="userPassword"/>
                        </label>

                        <div class="col-xs-9">
                            <topjava:inputField label='${userPassword}' name="password" inputType="password"/>
                        </div>
                        </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="save()" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
