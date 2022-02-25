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
				<h3>Welcome, ${user.getName()}</h3>
				<h4 class="my-3">Books from everyone's shelves:</h4>
				<a class="btn btn-sm btn-primary" href="/bookmarket">Book Market</a>
			</div>
			<div class="d-flex flex-column">
				<a class="btn btn-sm btn-danger" href="/logout">Logout</a>
				<a class="btn btn-sm btn-primary my-3" href="/books/new">+ Add a to my shelf!</a>
			</div>
		</div>
		<div class="my-3">
			<table class="table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Title</th>
					<th scope="col">Author Name</th>
					<th scope="col">Posted By</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
						<td><c:out value="${book.getId()}" /></td>
						<td><a href="/books/${book.getId()}"><c:out
									value="${book.getTitle()}" /></a></td>
						<td><c:out value="${book.getAuthor()}" /></td>
						<td><c:out value="${book.getUser().getName()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>