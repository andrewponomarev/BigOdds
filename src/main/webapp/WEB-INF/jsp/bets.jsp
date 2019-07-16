<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><spring:message code="bet.title"/></h3>
    <form method="post" action="bets/filter">
        <dl>
            <dt><spring:message code="bet.startDate"/></dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="bet.endDate"/></dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="bet.startTime"/></dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="bet.endTime"/></dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><spring:message code="bet.filter"/></button>
    </form>
    <hr/>
    <a href="bets/create"><spring:message code="bet.add"/></a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="bet.date"/></th>
            <th><spring:message code="bet.event"/></th>
            <th><spring:message code="bet.coefficient"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${bets}" var="bet">
            <jsp:useBean id="bet" scope="page" type="ru.betanalysis.model.Bet"/>
            <tr>
                <td>
                        <%--${bet.dateTime.toLocalDate()} ${bet.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(bet.getDateTime())%>--%>
                        <%--${fn:replace(bet.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(bet.dateTime)}
                </td>
                <td>${bet.event}</td>
                <td>${bet.coefficient}</td>
                <td><a href="bets/update?id=${bet.id}"><spring:message code="bet.update"/></a></td>
                <td><a href="bets/delete?id=${bet.id}"><spring:message code="bet.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>