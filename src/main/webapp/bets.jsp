<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Bets management</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3>Bets</h3>
    <form method="post" action="bets?action=filter">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt>From Time:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt>To Time:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr/>
    <a href="bets?action=create">Add bet</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Event</th>
            <th>Coefficient</th>
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
                <td><a href="bets?action=update&id=${bet.id}">Update</a></td>
                <td><a href="bets?action=delete&id=${bet.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>