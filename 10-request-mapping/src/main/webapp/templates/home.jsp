<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello Request Mapping</h1>
	<c:url value="/multi" var="mul"></c:url>
	<a href="${ mul }">multi</a>
	<c:url value="/inputs" var="in"></c:url>
	<a href="${ in }">inputs</a>
	<c:url value="/course" var="c"></c:url>
	<a href="${ c }">course</a>
</body>
</html>