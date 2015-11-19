<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>    
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>Welcome</title>
</head> 
<body>
	<h2>RequestBody!!</h2>
	
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript">
var jsonArg = new Object();
jsonArg.id = 10;
jsonArg.title = "test title";
jsonArg.content = "test content";

alert(JSON.stringify(jsonArg));

function ajax() {
	$.ajax({
	    type: "POST",
	    url: "http://localhost:8080/spring-test/mvc/items",
	    data: JSON.stringify(jsonArg),
	    contentType: "application/json",
	    success: function(data){
		    alert(data);
	    }
	});
}

ajax();
</script>
</body>
</html>
