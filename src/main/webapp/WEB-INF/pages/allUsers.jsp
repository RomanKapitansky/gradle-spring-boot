<html>
<head>
    <title>All Users page</title>
</head>
<body>
    <h1>All Users page ${users}</h1>

    <ul>
        <c:forEach items="${users}" var="user">
            <li>${user}</li>
        </c:forEach>
    </ul>

</body>
</html>