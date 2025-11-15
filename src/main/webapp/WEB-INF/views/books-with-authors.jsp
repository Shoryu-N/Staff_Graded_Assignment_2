<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books with Authors</title>
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
    <h1>Books with Authors</h1>
    <p class="muted">Math books and their authors.</p>

    <a class="btn secondary" href="${pageContext.request.contextPath}/books">Back to Books</a>

    <table>
        <thead>
        <tr>
            <th>Book Title</th>
            <th>Code</th>
            <th>Author Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="row" items="${bookAuthorRows}">
            <tr>
                <td>${row[0].title}</td>
                <td>${row[0].isbn}</td>
                <td>${row[1].firstName} ${row[1].lastName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
