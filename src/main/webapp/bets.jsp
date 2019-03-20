<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Bet list</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Bet</h2>
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
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
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