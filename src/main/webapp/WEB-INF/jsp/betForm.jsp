<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <jsp:useBean id="bet" type="ru.betanalysis.model.Bet" scope="request"/>
    <h3><spring:message code="${bet.isNew() ? 'bet.add' : 'bet.edit'}"/></h3>
    <hr>
    <form method="post" action="bets">
        <input type="hidden" name="id" value="${bet.id}">
        <dl>
            <dt><spring:message code="bet.dateTime"/>:</dt>
            <dd><input type="datetime-local" value="${bet.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="bet.event"/>:</dt>
            <dd><input type="text" value="${bet.event}" size=40 name="event" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="bet.coefficient"/>:</dt>
            <dd><input type="number" step="0.01" value="${bet.coefficient}" name="coefficient" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
</body>
</html>
