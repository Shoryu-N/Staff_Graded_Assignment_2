<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Math Book Form</title>
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
    <h1>Math Book</h1>

    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>

    <c:choose>
        <c:when test="${empty book.id}">
            <!-- Create -->
            <c:set var="formAction" value='${pageContext.request.contextPath}/books/new' />
        </c:when>
        <c:otherwise>
            <!-- Update -->
            <c:set var="formAction" value='${pageContext.request.contextPath}/books/edit/${book.id}' />
        </c:otherwise>
    </c:choose>

    <form action="${formAction}" method="post">
        <input type="hidden" name="id" value="${book.id}" />

        <div class="form-row">
            <label for="title">Title</label>
            <input id="title" type="text" name="title" value="${book.title}" />
        </div>

        <div class="form-row">
            <label for="isbn">Code (ISBN-like)</label>
            <input id="isbn" type="text" name="isbn" value="${book.isbn}" />
        </div>

        <div class="form-row">
            <label for="year">Publication Year</label>
            <input id="year" type="number" name="publishedYear" value="${book.publishedYear}" />
        </div>

        <div class="form-row">
            <label for="price">Price</label>
            <input id="price" type="number" step="0.01" name="price" value="${book.price}" />
        </div>

        <div class="form-row">
            <label for="authorId">Author</label>
            <select id="authorId" name="authorId">
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}"
                        <c:if test="${book.author != null && book.author.id == author.id}">selected</c:if>>
                        ${author.firstName} ${author.lastName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button class="btn" type="submit">Save</button>
        <a class="btn secondary" href="${pageContext.request.contextPath}/books">Back</a>
    </form>
</div>

</body>
</html>
