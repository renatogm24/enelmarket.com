<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Book</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="js/app.js"></script>
</head>
<body>
	<div class="container">
		<div class="row justify-content-evenly">
			<div class="px-5 px-sm-0 col-sm-6 col-md-4 mb-5">
				<h2 class="text-center my-3">Add a book to your Shelf!!</h2>
				<a class="btn btn-primary my-3" href="/books">Back to the
					shelves</a>
				<div class="mx-auto">
					<div class="d-flex flex-column">
						<h5>
							<span class="text-danger">${book.getUser().getName()}</span> read
							<span class="text-primary">${book.getTitle()}</span> by <span
								class="text-success">${book.getAuthor()}</span>
						</h5>
						<c:choose>
							<c:when test="${book.getUser().getId() == sessionScope.userid}">
    							<h5>Here are your thoughts:</h5>
  							</c:when>
							<c:otherwise>
    							<h5>Here are ${book.getUser().getName()}'s thoughts:</h5>
  							</c:otherwise>
						</c:choose>
						<p class="my-3 h5">
							"${book.getThought()}"
						</p>
						<c:choose>
							<c:when test="${book.getUser().getId() == sessionScope.userid}">
    							<a class="btn btn-sm btn-success" style="width:30%;" href="/books/${book.getId()}/edit">edit</a>
  							</c:when>							
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>