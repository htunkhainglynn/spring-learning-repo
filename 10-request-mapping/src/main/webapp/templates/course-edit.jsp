<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course edit</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container pt-4">
	
		<h1>Edit Course</h1>
		
		<div class="row">
		
			<div class="col-4">
			
				<c:url value="/course" var="saveUrl"></c:url>
				<form action="${saveUrl}" method="post">
					
					<div class="mb-3">
						<label class="form-label mb-1" for="name">Course Name</label>
						<input id="name" name="name" type="text" placeholder="Enter Course Name" class="form-control" autocomplete="off" autofocus/>
					</div>
					
					
				<div class="mb-3">
						<label for="level" class="form-label mb-1">Level</label>
						<select name="level" id="level" class="form-select" class="form-control">
							<option disabled selected>Select one</option>
							<option value="Basic">Basic</option>
							<option value="Intermediate">Intermediate</option>
							<option value="Advanced">Advanced</option>
						</select>
					</div>
						
					<div class="mb-3">
						<label for="duration" class="form-label mb-1">Duration</label>
						<input id="duration" name="duration" type="number" placeholder="Enter Duration" autocomplete="off" class="form-control"/>
					</div>
					
					<div class="mb-3">
						<label for="fees" class="form-label mb-1">Fees</label>
						<input id="fees" name="fees"  type="number" placeholder="Enter Fees" autocomplete="off" class="form-control"/>
					</div>
					
					<div>
						<button type="submit" class="btn btn-success">Save Course</button>
					</div>
				
				</form>
			</div>
		
		</div>
	</div>
	
</body>
</html>