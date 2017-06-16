<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1>Add new user</h1>

    <form:form modelAttribute="form">
        <form:errors path="" element="div" />
        <div>
            <form:label path="name">Name</form:label>
            <form:input path="name" />
        </div>
        <div>
            <form:label path="age">Age </form:label>
            <form:input path="age" />
        </div>
        <br>
        <div>
            <input type="submit" value="ADD"/>
        </div>
    </form:form>
</body>
</html>