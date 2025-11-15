<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Mathematics Authors</title>
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
    <h1>Mathematics Authors</h1>
    <p class="muted">Manage mathematicians and their publications.</p>

    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>

    <a class="btn" href="${pageContext.request.contextPath}/authors/new">Add New Author</a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="author" items="${authors}">
            <tr>
                <td>${author.id}</td>
                <td>${author.firstName} ${author.lastName}</td>
                <td>${author.email}</td>
                <td class="table-actions">
                    <a class="btn secondary" href="${pageContext.request.contextPath}/authors/edit/${author.id}">Edit</a>
                    <form action="${pageContext.request.contextPath}/authors/delete/${author.id}"
                          method="post"
                          style="display:inline"
                          onsubmit="return confirm('Delete this author? Related books will also be removed.');">
                        <button class="btn secondary" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
