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
				<h2 class="text-center mb-3">Change your Entry</h2>
				<a class="btn btn-primary my-3" href="/books">Back to the shelves</a>
				<div style="width: 50%;" class="mx-auto">
					<form:form
						class="row g-2 justify-content-center bg-dark text-light py-4 px-3 rounded"
						action="/books/${book.getId()}" method="post" modelAttribute="book">
						
						<input type="hidden" name="_method" value="put">
						
						<form:input type="hidden" path="user" value="${book.getUser().getId()}"/>
						
						<form:input type="hidden" path="borrower" value="${book.getBorrower().getId()}"/>

						<form:label for="title" path="title" class="form-label">Title:</form:label>
						<form:errors path="title" class="text-danger" />
						<form:input type="text" path="title" class="form-control" id="title" />
						
						<form:label for="author" path="author" class="form-label">Author:</form:label>
						<form:errors path="author" class="text-danger" />
						<form:input type="text" path="author" class="form-control"
							id="author" />
							
						<form:label for="thought" path="thought" class="form-label">My thoughts:</form:label>
						<form:errors path="thought" class="text-danger" />
						<form:textarea type="text" path="thought"
							class="form-control" id="thought" />

						<button class="btn btn-primary mt-4" type="submit">Submit</button>
					</form:form>
				</div>
				<a class="btn btn-danger" href="/books/${book.getId()}/delete">Delete</a>
			</div>
		</div>
	</div>
</body>
</html>