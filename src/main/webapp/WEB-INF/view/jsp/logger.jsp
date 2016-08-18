<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Logger List</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<div class="container">
  <h2>Logger List</h2>
  <p>Logger Level이 NONE인 경우 ROOT Logger의 레벨을 상속 받습니다.</p>
  
  <div class="pull-right button-set">
	<button id="button_save" type="button" class="btn btn-sm btn-default editable" ><span class="glyphicon glyphicon-floppy-save"></span>  저장</button>
  </div>
  
  <table class="table table-hover">
    <thead>
      <tr>
        <th>선택</th>
        <th>Category</th>
        <th>Level</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${loggerList}" var="item">
    		<tr>
    			<td><input type="checkbox" name="name" value="${item.name}"/></td>
    			<td>${item.name}</td>
    			<td>
    				<select name="level">
    					<option <c:if test="${item.level eq ''}">selected</c:if>>NONE</option>
					  	<option <c:if test="${item.level eq 'OFF'}">selected</c:if>>OFF</option>
					  	<option <c:if test="${item.level eq 'ERROR'}">selected</c:if>>ERROR</option>
					  	<option <c:if test="${item.level eq 'WARN'}">selected</c:if>>WARN</option>
					  	<option <c:if test="${item.level eq 'INFO'}">selected</c:if>>INFO</option>
					  	<option <c:if test="${item.level eq 'DEBUG'}">selected</c:if>>DEBUG</option>
					  	<option <c:if test="${item.level eq 'TRACE'}">selected</c:if>>TRACE</option>
					  	<option <c:if test="${item.level eq 'ALL'}">selected</c:if>>ALL</option>
					</select>
    			</td>
    		</tr>
    	</c:forEach>
    </tbody>
  </table>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var jsonObj = [];

	$("#button_save").click(function(event) {
		event.preventDefault();
		
		$(":checkbox[name='name']:checked").each(function(pi, po) {
			var index = $(":checkbox[name='name']").index(po);
			
			var logger = new Object();
			logger.name = po.value;
			logger.level = $("select").eq(index).val();
			jsonObj.push(logger);
		});
		
		$.ajax({
			url: "/spring-test/logger/save",
			method: "POST",
			data: JSON.stringify(jsonObj),
			type: "json",
			contentType:"application/json",
			success: function(data){
				alert("반영이 완료되었습니다.");
				location.href = data;
			}
		});
	})
</script>
</body>
</html>
