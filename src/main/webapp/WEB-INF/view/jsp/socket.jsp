<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SockJS</title>

<style>
  .inset {
    box-shadow: inset 0 0 4px #000000;
    -moz-box-shadow: inset 0 0 4px #000000;
    -webkit-box-shadow: inset 0 0 4px #000000;
    width: 400px;
    border-width: 1px;
    padding: 5px;
    overflow-y: scroll;
  }
  input.inset {
    height: 40px;
  }
  div.inset {
    height: 300px;
    white-space: pre-wrap
  }
</style>
</head>
<body>

<table border="1">
	<tr>
		<td>
			<ul>
				<li>주제토론방 : Javascript 구조화가 필요한가??</li>
				<li>토론시간 : 11:10 ~ 12:00</li>
			</ul>
		</td>
		<td>
			진행자: ******* [토론종료] [(00)-토론금지] <br/>
		</td>
		<td>
			<ol>
				<li>진행자 : ***님</li>
				<li>패널1 : ***님</li>
				<li>패널2 : ***대학 교수 -익명요청</li>
				<li>패널3 : 방송인 -익명요청</li>
				<li>패널4 : 고수 -익명요청</li>
			</ol>
		</td>
	</tr>
	<tr>
		<td>패널1:***님<div id="chat1" class="inset"></div></td>
		<td rowspan=2 valign=top>
			<div id="chatMain" class="inset"></div>
		</td>
		<td>패널2:***대학 교수<div id="chat2" class="inset"></div></td>
	</tr>
	<tr>
		<td>패널3:방송인<div id="chat3" class="inset"></div></td>
		<td>패널4:고수<div id="chat4" class="inset"></div></td>
	</tr>
	<tr>
		<td>
			<form id="private_chat1">
				userTimeStamp : <input id="userTimeStamp1" name="userTimeStamp"/>
			<input id="input" name="message" type="text" onkeydown="send(event)" placeholder="press enter to send" class="inset">
			</form>
		</td>
		<td> **
		</td>
		<td>
			<form id="private_chat2">
				<input id="userId" name="userId" value="12345"/>
				<select name="type">
					<option value="all">all</option>
				</select>

				userTimeStamp : <input id="userTimeStamp2" name="userTimeStamp"/>
				<input id="input2" name="message" type="text" onkeyup="send2(event)" placeholder="press to send" class="inset">
				<p>Byte size: <span id="byteSize"></span></p>
				<p>live:<input type="text" id="onair2" name="onair" value="0"/> </p>
			</form>
		</td>
	</tr>
</table>

<script src="/spring-test/resources/static/js/jquery.js"></script>
<script src="/spring-test/resources/static/js/json2.js"></script>
<script src="/spring-test/resources/static/js/jquery.form.min.js"></script>
<script src="/spring-test/resources/static/js/jquery.serializejson.min.js"></script>
<script src="//cdn.jsdelivr.net/sockjs/1.0.0/sockjs.min.js"></script>

<script type="text/javascript">

$(document).ready(function () {

	setTimeStamp($('#userTimeStamp1'));
	setTimeStamp($('#userTimeStamp2'));

});

var sock = new SockJS('/spring-test/chat1');

sock.onopen = function() {
  console.log('socket open');
}

/////////////////////////////////////////////////
sock.onmessage = function(message) {
  var jsonObj = JSON.parse(message.data);

  var curDiv = $("#chat1 #"+jsonObj.userTimeStamp);

  if(curDiv.text() == '' )  {
	  $("#chat1").append('<div id='+jsonObj.userTimeStamp+'>' + getMessage(jsonObj) + '</div>');
	  preDiv.css('background','#ffffff');
  }else{
	  preDiv = $("#chat1 #"+jsonObj.userTimeStamp);
	  curDiv.text(getMessage(jsonObj)).css('background','#8ec252');
  }

  $('#chatMain').append(getMessage(jsonObj) + "\n");

  chatScrollTop();

}

sock.onclose = function(e) {
  console.log('socket close');
}

function getMessage(jsonObj) {
	return jsonObj.userId + '>>' + jsonObj.message;
}

// jquery function 으로 대체
function chatScrollTop() {
	var wtf1    = $('#chat1');
	wtf1.scrollTop(wtf1[0].scrollHeight);

	var wtf2    = $('#chat2');
	wtf2.scrollTop(wtf2[0].scrollHeight);

	var wtfMain    = $('#chatMain');
	wtfMain.scrollTop(wtfMain[0].scrollHeight);

}




function send(event) {
	if (event.keyCode == 13 || event.which == 13) {
		var formData = $('#private_chat1').serializeJSON();
		var param = JSON.stringify(formData);

		var message = $('#input').val();
      	if (message.length > 0) {
        	sock.send(param);
        	$('#input').val("");
     	}
		setTimeStamp($('#userTimeStamp1'));
    }
}

////////////////////////////////



var sock2 = new SockJS('/spring-test/chat2');
sock2.onopen = function() {
  console.log('socket open');
}

var preDiv, preMainDiv = "";
sock2.onmessage = function(message) {
  //console.log('message received : ' + message.data);
  //message.data을 JSON으로 파싱하여,
  // var result = toJSON(message.data);
  var jsonObj = JSON.parse(message.data);
  //console.log(jsonObj.userTimeStamp);
  //console.log(jsonObj.message);
  // result.userTimeStamp 단위로 div를 생성한다.
  // userTimeStamp가 같으면 같은 div를 갱신,
  // 없다면, 새로운 userTimeStamp값으로 div id를 추가한다.
  //$('#chat2').append(message.data + "\n");

  var curDiv = $("#chat2 #"+jsonObj.id+'_'+jsonObj.userTimeStamp);
  var curMainDiv = $("#chatMain #"+jsonObj.id+'_'+jsonObj.userTimeStamp);

  //console.log(curDiv);
  //console.log(curDiv.text());

  //.val() == undefined || curDiv.val().length == 0
  if(curDiv.text() == '' )  {
	  $("#chat2").append('<div id='+jsonObj.id+'_'+jsonObj.userTimeStamp+'>'+jsonObj.userId + '>>' + jsonObj.message+'</div>');
	  preDiv.css('background','#ffffff');
//	  $("#chatMain").append('<div id='+jsonObj.id+'_'+jsonObj.userTimeStamp+'>'+jsonObj.userId + '>>' + jsonObj.message+'</div>');
	  preMainDiv.css('background','#ffffff');
  }else{
	  curDiv.text(jsonObj.userId + '>>' + jsonObj.message).css('background','#8ec252');
	  preDiv = $("#chat2 #"+jsonObj.id+'_'+jsonObj.userTimeStamp);
//	  curMainDiv.text(jsonObj.userId + '>>' + jsonObj.message).css('background','#8ec252');
	  preMainDiv = $("#chat2 #"+jsonObj.id+'_'+jsonObj.userTimeStamp);
  }

  if(jsonObj.onair === 0) {
	  $("#chatMain").append('<div id='+jsonObj.id+'_'+jsonObj.userTimeStamp+' align="right" >'+jsonObj.message + '<<' + jsonObj.userId+'</div>');
	  curMainDiv.css('background','#ffffff');
  }

  chatScrollTop();

}

sock2.onclose = function(e) {
  console.log('socket close');
}



var p1_len = 0;
var live_on = false;

function send2(event) {

	if (event.keyCode == 13 || event.which == 13) {
		$('#onair2').val("0");
	}else{
		$('#onair2').val("1");
	}

	var formData = $('#private_chat2').serializeJSON();
	var param = JSON.stringify(formData);

	var message = $('#input2').val();

	$('#byteSize').text(message.length);

//    if (message.length != p1_len ) {
      	console.log('message send : ' +message.length);
      	sock2.send(param);
//   	}

//	p1_len = message.length;

	if (event.keyCode == 13 || event.which == 13) {
		$('#input2').val("");
		$('#byteSize').innerHTML = "";

		//p1_len = 0 ;

		setTimeStamp($('#userTimeStamp2'));
	}
}

function setTimeStamp(obj) {
	var d = new Date();
	obj.val(d.getTime());
}

function send21(event) {
	var formData = $('#private_chat2').serializeJSON();
	var param = JSON.stringify(formData);

	var message = $('#input2').val();

	$('#byteSize').text(message.length);

    if (message.length != p1_len ) {
      	console.log('message send : ' +message.length);
      	sock2.send(param);
   	}

	p1_len = message.length;

	if (event.keyCode == 13 || event.which == 13) {
		$('#input2').val("");
		$('#byteSize').innerHTML = "";

		p1_len = 0 ;

		var d = new Date();
		var n = d.getTime();
		$('#userTimeStamp').val(n);
		live_on = true;
	}else {
		live_on = false;
	}
}

//미사용
function isHan(str){
	for(var i = 0; i < str.length; i++){
		var chr = str.substr(i,1);
		chr = escape(chr);
		if(chr.charAt(1) == "u"){
			chr = chr.substr(2, (chr.length - 1));
			if((chr < "AC00") || (chr > "D7A3"))
			return false;
		}
		else{
			return false;
		}
	}
	return true;
}

function ForumPanel(){

	this.init = function(options){

		this.sock = new SockJS('/spring-test/chat1');
		this.onOpen = function() {
			  console.log('socket open');
		};

	};

	this.bindEvents = function(){

		this.sock.onopen = this.onOpen;
		this.sock.onmessage = function(){
			if(true){

			}else{

			}
		}
	};

	this.reactiveMessage = function(){

	};

	this.nonReactiveMessage = function(){

	};

}

var panel1 = new ForumPanel(["",""]);
var panel2 = new ForumPanel(["",""]);

function ForumFrame(){

}

var frame = new ForumFrame(["",""]);
frame.setPanel(panel1);
frame.setPanel(panel2);


</script>

</body>
</html>
