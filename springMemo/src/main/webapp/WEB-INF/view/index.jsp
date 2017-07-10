
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; cha	rset=UTF-8">
<title>Index</title>
</head>
<body>
	<h2>Movies menu</h2>
	<br />
	<br />
	<form action="/" method="POST">
		<div>
			<input name="text">
		</div>
		<div>
			<button type="submit">add</button>
		</div>
	</form>
	<br />
	<br />



	 <ol>
		<c:forEach items="${memos}" var="memo">
			<li>${memo.text}</li>
		</c:forEach>
	</ol>
	<br />
	<br /> 

	<form action="/a" method="POST">
		<div>
			<input name="text">
		</div>
		<div>
			<button type="submit">delete</button>
		</div>
	</form>
</body>
</html>