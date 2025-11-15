<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Author Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>

<div class="navbar">
    <div>Math Library</div>
    <div>
        <a href="${pageContext.request.contextPath}/authors">Authors</a>
        <a href="${pageContext.request.contextPath}/books">Math Books</a>
    </div>
</div>

<div class="container">
    <h1>Author</h1>

    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>

    <c:choose>
        <c:when test="${empty author.id}">
            <!-- Create -->
            <c:set var="formAction" value='${pageContext.request.contextPath}/authors/new' />
        </c:when>
        <c:otherwise>
            <!-- Update -->
            <c:set var="formAction" value='${pageContext.request.contextPath}/authors/edit/${author.id}' />
        </c:otherwise>
    </c:choose>

    <form action="${formAction}" method="post">
        <input type="hidden" name="id" value="${author.id}" />

        <div class="form-row">
            <label for="firstName">First Name</label>
            <input id="firstName" type="text" name="firstName" value="${author.firstName}" />
        </div>

        <div class="form-row">
            <label for="lastName">Last Name</label>
            <input id="lastName" type="text" name="lastName" value="${author.lastName}" />
        </div>

        <div class="form-row">
            <label for="email">Email</label>
            <input id="email" type="email" name="email" value="${author.email}" />
        </div>

        <button class="btn" type="submit">Save</button>
        <a class="btn secondary" href="${pageContext.request.contextPath}/authors">Back</a>
    </form>
</div>

</body>
</html>
