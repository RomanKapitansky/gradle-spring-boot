<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>All Users page</title>
</head>
<body>
    <h1>All Users page</h1>

    <ul>
        <c:forEach items="${users}" var="users">
            <li>${user}</li>
        </c:forEach>
    </ul>

</body>
</html>