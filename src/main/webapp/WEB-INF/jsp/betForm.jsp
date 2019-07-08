<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Bet</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create bet' : 'Edit bet'}</h2>
    <hr>
    <jsp:useBean id="bet" type="ru.betanalysis.model.Bet" scope="request"/>
    <form method="post" action="bets">
        <input type="hidden" name="id" value="${bet.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${bet.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt>Event:</dt>
            <dd><input type="text" value="${bet.event}" size=40 name="event" required></dd>
        </dl>
        <dl>
            <dt>Coefficient:</dt>
            <dd><input type="number" step="0.01" value="${bet.coefficient}" name="coefficient" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
