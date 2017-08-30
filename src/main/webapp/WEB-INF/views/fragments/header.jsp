<%@ page language="java" contentType="text/html;  charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<script src="${contextPath}/resources/js/jquery-3.1.1.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css"/>
	<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.twbsPagination.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap-checkbox.min.js"></script>


</head>
<title>VetClient</title>

<spring:url value="/sales" var="salesUrl"/>
<spring:url value="/load_price" var="loadPrice"/>
<spring:url value="/price" var="price"/>

<nav class="navbar navbar-light bg-faded" >
	<ul class="nav navbar-nav">
		<li class="nav-item active" style="margin-left: 5%">
			<a class="nav-link" onclick="location.href='${loginUrl}'" >Курс 1.95<span class="sr-only">(current)</span></a>
		</li>
		<li class="nav-item active" style="margin-left: 7%">
			<a class="nav-link" onclick="location.href='${salesUrl}'" >Продажи<span class="sr-only">(current)</span></a>
		</li>
		<li class="nav-item active" style="margin-right: 10%">
			<a class="nav-link" onclick="location.href='${loadPrice}'" >Загрузить прайс<span class="sr-only">(current)</span></a>
		</li>
		<li class="nav-item active" style="margin-right: 10%">
			<a class="nav-link" onclick="location.href='${price}'" >прайс<span class="sr-only">(current)</span></a>
		</li>
	</ul>
</nav>


</html>