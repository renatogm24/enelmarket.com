<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<div class="container my-3">
		<div class="d-flex justify-content-between">
			<div class="d-flex flex-column">
				<h3>Hello, ${user.getName()}. Welcome to..</h3>
				<h4 class="my-3">The Book Broker!</h4>
			</div>
			<div class="d-flex flex-column">
				<a class="btn btn-sm btn-primary" href="/">back to the shelves</a>
			</div>
		</div>
		<div class="my-3">
			<h3>Available Books to Borrow</h3>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Title</th>
						<th scope="col">Author Name</th>
						<th scope="col">Posted By</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${booksAvailable}">
						<tr>
							<td><c:out value="${book.getId()}" /></td>
							<td><a href="/books/${book.getId()}"><c:out
										value="${book.getTitle()}" /></a></td>
							<td><c:out value="${book.getAuthor()}" /></td>
							<td><c:out value="${book.getUser().getName()}" /></td>

							<td><c:choose>
									<c:when
										test="${book.getUser().getId() == sessionScope.userid}">
										<a href="/books/${book.getId()}/edit">edit</a>
										<a href="/books/${book.getId()}/delete">delete</a>
									</c:when>
									<c:otherwise>
										<a href="/books/${book.getId()}/borrow">borrow</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="my-3">
			<h3>Books I'm Borrowing</h3>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Title</th>
						<th scope="col">Author Name</th>
						<th scope="col">Posted By</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${booksBorrowedBy}">
						<tr>
							<td><c:out value="${book.getId()}" /></td>
							<td><a href="/books/${book.getId()}"><c:out
										value="${book.getTitle()}" /></a></td>
							<td><c:out value="${book.getAuthor()}" /></td>
							<td><c:out value="${book.getUser().getName()}" /></td>
							<td><a href="/books/${book.getId()}/return">return</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>