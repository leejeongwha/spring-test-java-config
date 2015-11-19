<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>    
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h1>Please upload a file</h1>
	    <form method="post" action="/spring-test/mvc/upload" enctype="multipart/form-data">
	    	<input type="text" name="name" placeholder="save file name"/>
	      	<input type="file" name="file"/>
	      	<input type="submit"/>
	    </form>
	</body>
</html>
