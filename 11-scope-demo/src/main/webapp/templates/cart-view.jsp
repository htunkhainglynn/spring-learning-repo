<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session cart</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
</head>
<body>
	<h1>Session Cart</h1>



	<div class="container pt-4">
		<div class="card">
			<div class="card-header">Using Session</div>
			<div class="card-body">

				<div class="row">

					<div class="col">

						<c:url value="/add-item" var="action"></c:url>
						<form action="${action}" class="row" method="post">

							<div class="col-7">
								<input type="text" name="item" placeholder="Item name" class="form-control" autocomplete="off" required="required"/>
							</div>

							<div class="col-auto">
								<button type="submit" class="btn btn-primary mr-4">Add Item</button>
								
								<c:url value="/clear-cart" var="clear"></c:url>
								<a href="${clear}" class="btn btn-danger">Clear Item</a>
							</div>

						</form>

					</div>

				</div>

			</div>
			
			<div class="mt-4 list-group">
			
				<div class="list-group-item active">
					Item in Cart : ${sessionScope.cart.count}
				</div>
			
				<c:forEach var="item" items="${sessionScope.cart.list}">
				
					<div class="list-group-item">
						${item}
					</div>
				
				</c:forEach>
			
			</div>
		</div>
	</div>





</body>
</html>