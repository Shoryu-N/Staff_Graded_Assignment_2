<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Math Books</title>
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
    <h1>Mathematics Books</h1>
    <p class="muted">View and edit math-related titles and their authors.</p>

    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>

    <a class="btn" href="${pageContext.request.contextPath}/books/new">Add New Math Book</a>
    <a class="btn secondary" href="${pageContext.request.contextPath}/books/with-authors">View Books with Authors</a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Code</th>
            <th>Year</th>
            <th>Price</th>
            <th>Author</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.isbn}</td>
                <td>${book.publishedYear}</td>
                <td>${book.price}</td>
                <td>${book.author.firstName} ${book.author.lastName}</td>
                <td class="table-actions">
                    <a class="btn secondary" href="${pageContext.request.contextPath}/books/edit/${book.id}">Edit</a>
                    <form action="${pageContext.request.contextPath}/books/delete/${book.id}"
                          method="post"
                          style="display:inline"
                          onsubmit="return confirm('Delete this book?');">
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
