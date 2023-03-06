<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Form</title>
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

	<div class="container pt-4">
		<h1><sp:message code="app.title"></sp:message></h1>
		
		<sf:form modelAttribute="userInput">  <!-- default post -->
		
			<div class="row">
			
				<div class="col">
			
					<sf:label path="name">Student Name</sf:label>
					<sf:input path="name" cssClass="form-control" placeholder="Enter your name"/>
					<sf:errors path="name" cssClass="text-danger"></sf:errors>
			
				</div>
				
				<div class="col">
				
					<sf:label path="phone">Phone</sf:label>
					<sf:input path="phone" cssClass="form-control" placeholder="Enter your number"/>
					<sf:errors path="phone" cssClass="text-danger"></sf:errors>
				
				</div>
				
				<div class="col">
				
					<sf:label path="email">Email</sf:label>
					<sf:input path="email" cssClass="form-control" placeholder="Enter your email"/>
					<sf:errors path="email" cssClass="text-danger"></sf:errors>
				
				</div>
			
			</div>
			
			<div class="row mt-4">
			
				<div class="col">
				
					<sf:label path="password">Password</sf:label>
					<sf:input path="password" cssClass="form-control" placeholder="Enter your password" type="password"/>
					<sf:errors path="password" cssClass="text-danger"></sf:errors>
				
				</div>
				
				<div class="col">
				
					<sf:label path="course">Select Course</sf:label>
					<sf:select path="course" cssClass="form-select">
						<sf:option value="" disabled="true">Select Course</sf:option>
						<%-- items="${courses}" itemLabel="name" = <option value="">${course.name}</option> --%>
						<%-- itemValue="id" = <option value="${course.id}"></option> --%>
						<sf:options items="${courses}" itemValue="id" itemLabel="name"/>  
					</sf:select>
					<sf:errors path="course" cssClass="text-danger"></sf:errors>
				
				</div>
				
				<div class="col">
				
					<sf:label path="registration">Registration date</sf:label>
					<sf:input path="registration" cssClass="form-control" placeholder="Enter your registration date" type="date"/>
				
				</div>
			
			</div>	
			
			<div class="row mt-4">
			
				<div class="col">
				
					<sf:label path="gender">Gender</sf:label>
					<sf:radiobuttons path="gender"/>
				
				</div>
				
				<div class="col">
				
					<sf:label path="knowledge">Knowledge</sf:label>
					<sf:checkboxes items="${knowledges}" path="knowledge"/>
				
				</div>
				
				<div class="col">
				
					<sf:label path="agree">Agree</sf:label>
					<sf:checkbox path="agree" label="Yes I agree"/>
					<sf:errors path="agree" cssClass="text-danger"></sf:errors>
				
				</div>
			
			</div>
			
			<div class="row mt-4">
			
				<div class="col">
				
					<sf:label path="remark">Remark</sf:label>
					<sf:textarea path="remark" cssClass="form-control"/>
				
				</div>
			
			</div>
			
			<div class="row mt-4">
				
				<div class="col">
					<button type="submit" class="btn btn-primary">Submit form</button>				
				</div>
					
			</div>
		
		</sf:form>
		
		<h3 class="mt-4">Data List</h3>
		
		<table class="table">
		
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Gender</th>
					<th>Course</th>
					<th>Registration</th>
					<th>Remark</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="d" items="${list}">
					<tr>
						<td>${d.id}</td>
						<td>${d.name}</td>
						<td>${d.phone}</td>
						<td>${d.email}</td>
						<td>${d.gender}</td>
						<td>${d.course.name}</td>
						<td>${d.registration}</td>
						<td>${d.remark}</td>
					</tr>
				</c:forEach>
			</tbody>
		
		</table>
		
	</div>

</body>
</html>