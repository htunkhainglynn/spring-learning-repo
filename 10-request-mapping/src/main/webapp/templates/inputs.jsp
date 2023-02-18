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

	<h1>Inputs Search</h1>
	<div>
		<c:if test="${not empty type and not empty id}">
			<h3>${ type } / ${ id }</h3>
		</c:if>
		<c:if test="${not empty date}">
			<h3>${ date }</h3>
		</c:if>
		<c:if test="${not empty level}">
			<h3>${ level }</h3>
		</c:if>
		<c:if test="${not empty product}">
			<h3>${ product }</h3>
		</c:if>
	</div>
	<ul>
		<li>
			<c:url value="/inputs/Path Type/search/1234" var="validPath"></c:url>
			<a href="${validPath}" >Valid Path</a>
		</li>
		<li>
			<c:url value="/inputs/2022-11-21" var="validDate"></c:url>
			<a href="${validDate}" >date</a>
		</li>
		<li>
			<c:url value="/inputs/Basic" var="validEnum"></c:url>
			<a href="${validEnum}" >level</a>
		</li>
		<li>
			<c:url value="/inputs/matrix/Jersey;s=L" var="validMatrix"></c:url>
			<a href="${validMatrix}" >matrix</a>
		</li>
		<li>
			<c:url value="/inputs/request?p=Jeans&w=34&l=29" var="validInputParam"></c:url>
			<a href="${validInputParam}" >input params</a>
		</li>
	</ul>
	
</body>
</html>