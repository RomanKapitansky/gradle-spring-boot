<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1>Enter user's ID to delete it</h1>

    <form:form modelAttribute="form">
        <form:errors path="" element="div" />
        <div>
            <form:label path="id">User's id</form:label>
            <form:input path="id" />
            <form:errors path="id" />
        </div>
        <div>
            <input type="submit" value="DELETE"/>
        </div>
    </form:form>
</body>
</html>