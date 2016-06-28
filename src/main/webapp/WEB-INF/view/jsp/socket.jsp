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
			<form id="form1" onsubmit="return false;">
			     <input id="input_chat1_area" name="message" type="text" placeholder="press enter to send" class="inset">
			</form>
		</td>
		<td> **
		</td>
		<td>
			<form id="form2" onsubmit="return false;">
				<input id="input_chat2_area" name="message" type="text" placeholder="press to send" class="inset">
			</form>
		</td>
	</tr>
    <tr>
        <td>
            <form id="form3" onsubmit="return false;">
                 <input id="input_chat3_area" name="message" type="text" placeholder="press enter to send" class="inset">
            </form>
        </td>
        <td> **
        </td>
        <td>
            <form id="form4" onsubmit="return false;">
                <input id="input_chat4_area" name="message" type="text" placeholder="press to send" class="inset">
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
function Chat(options){

	this.init = function(options){
		
		this.EVENT = {
				"update" : "update"
		};
		
		this.options = {
			debug : false,
			userId : "kim76",
			onair : false,
			type : "all",
			apiUrl : "/spring-test/chat1",
			showAreaId : "chat1",
			inputAreaId : "input_chat1_area"
		};
		$.extend(this.options,options);

		this.sock = new SockJS(this.options.apiUrl);
		
		this.showArea = $("#" + this.options.showAreaId);
		this.inputArea = $("#" + this.options.inputAreaId);
		
		this.eventHandler = {};
		
		this.chatItem = [];
		
		this.currentChatItemWithOnair = null;

		this.bindEvents();

	};
	
	this.createChatItemId = function(timeStamp){
		return 	this.options.userId + "-chat-" + timeStamp;
	};

	this.write = function(dataObject){
		this.log("nickName :" + dataObject.userId + ", message :" + dataObject.message);
		
		if( this.currentChatItemWithOnair === null && dataObject.onair == 1 ){
			var currentChatItem = $('<div id=' + this.createChatItemId(new Date().getTime()) + '>');
			this.showArea.append(currentChatItem);
			this.currentChatItemWithOnair = currentChatItem;
		}
		
		if( this.currentChatItemWithOnair !== null ){
			this.currentChatItemWithOnair.html(dataObject.userId + ' : ' + dataObject.message);
		}else{
			var elId = this.createChatItemId(dataObject.userTimeStamp);
			var elChatOneLine = '<div id=' + elId + '>' + dataObject.userId + ' : ' + dataObject.message + '</div>';
			this.showArea.append(elChatOneLine);
		}
		
		if ( dataObject.onair == 0 ){
			this.currentChatItemWithOnair = null;
			this.trigger(this.EVENT.update,{
				"userId" : this.options.userId , 
				"data" : dataObject
			});
			this.inputArea.val("");
		}
	};

	this.on = function(eventName,handler){

		if(typeof eventName !== "string" || typeof handler !== "function"){
			this.log("파라미터가 유효하지 않음 eventName : " + eventName + ", handler : " + handler);
			return;
		}

		var handlerList = this.eventHandler[eventName];
		if (typeof handlerList === "undefined") {
			handlerList = this.eventHandler[eventName] = [];
		}

		this.log("이벤트 핸들러 추가 eventName : " + eventName + ", handler : " + handler);
		handlerList.push(handler);

	};

	this.trigger = function(eventName,customEvent){
		customEvent = customEvent || {};
		var handlerList = this.eventHandler[eventName] || [];
		var hasHandlerList = handlerList.length > 0;

		if (!hasHandlerList) {
			return true;
		}

		// 이벤트를 떼어낼때 문제가 발생할 수 있기에 해당 핸들러를 모두 복사해 사용
		handlerList = handlerList.concat();

		customEvent.eventType = eventName;

		var isCanceled = false;
		var arg = [customEvent];
		var i;
		var len;
		var handler;

		customEvent.stop = function() {
			isCanceled = true;
		};

		// 해당 메소드의 인자가 2개 이상일 경우 3번째부터는 arg에 넣어둔다.
		if ((len = arguments.length) > 2) {
			arg = arg.concat(Array.prototype.slice.call(arguments, 2, len));
		}

		for (i = 0; handler = handlerList[i]; i++) {
			handler.apply(this, arg);
			this.log("이벤트 동작 eventName : " + eventName + ", handler : " + handler + ", customEvent : " + customEvent);
		}

		return !isCanceled;
	},
	
	this.onSockOpen = function(e){
		this.log("소켓열기");
	};
	
	this.onSockClose = function(e){
		this.log("소켓닫기");
	};
	
	this.onSockMessage = function(e){
		
		var dataObject = JSON.parse(e.data);
		this.write(dataObject);
		
	};
	
	this.send = function(e){
		if (e.keyCode !== 13 && event.which !== 13) {
			return;
		}
		
		var message = this.inputArea.val();
		if (message.length <= 0) {
			return;
		}
		
		this.sock.send(JSON.stringify({
			"userId" : this.options.userId, 
			"userTimeStamp" : new Date().getTime(),
			"message" : message,
			"onair" : 0,
			"type": this.options.type 
		}));
		
	};
	
	this.sendOnair = function(e){
		
		//입력된 문자가 없으면 튕김
		var message = this.inputArea.val();
		if (message.length <= 0) {
			return;
		}
		
		var onairParameter = 1;
		
		if (e.keyCode === 13 || event.which === 13) {
			onairParameter = 0;
		}
		
		this.sock.send(JSON.stringify({
			"userId" : this.options.userId, 
			"userTimeStamp" : this.currentChatItemIdWithOnair,
			"message" : message,
			"onair" : onairParameter,
			"type": this.options.type 
		}));
		
		return;
	};

	this.onKeyDown = function(e){
		
		if (this.options.onair) {
			this.sendOnair(e);
		}else{
			this.send(e);
		}
		
	};

	this.bindEvents = function(){
		this.sock.onopen = $.proxy(this.onSockOpen,this);
		this.sock.onclose = $.proxy(this.onSockClose,this);
		this.sock.onmessage = $.proxy(this.onSockMessage,this);
		$("#" + this.options.inputAreaId).keydown($.proxy(this.onKeyDown,this));
	};

	this.log = function(message){
		if(this.options.debug){
			console.log(message);
		}
	};

	($.proxy(function(options){
		this.init(options);
	},this))(options);

}

function Forum(options){

	this.init = function(options){
		this.options = {
			showAreaId : "chatMain",
			debug : false
		};
		$.extend(this.options,options);

		this.showArea = $("#" + this.options.showAreaId);
		
		this.chats = [];

		this.bindEvents();

	};

	this.bindEvents = function(){

	};

	this.write = function(dataObject){
		this.log("nickName :" + dataObject.userId + ", message :" + dataObject.message);
		var elChatOneLine = '<div id=' + dataObject.userTimeStamp + '>' + dataObject.userId + ' : ' + dataObject.message + '</div>';
		this.showArea.append(elChatOneLine);
	};

	this.onUpdate = function(e){
		this.write(e.data);
	};

	this.addChat = function(chat){
		chat.on("update",$.proxy(this.onUpdate,this));
		this.chats.push(chat);
	};

	this.log = function(message){
		if(this.options.debug){
			console.log(message);
		}
	};

	($.proxy(function(options){
		this.init(options);
	},this))(options);

}

$(document).ready(function(){
	var testChat1 = new Chat({
		debug : true,
		userId : "chat1",
		apiUrl : "/spring-test/chat1",
		showAreaId : "chat1",
		inputAreaId : "input_chat1_area",
		onair : false
	});

	var testChat2 = new Chat({
		debug : true,
		userId : "chat2",
		apiUrl : "/spring-test/chat2",
		showAreaId : "chat2",
		inputAreaId : "input_chat2_area",
		onair : true
	});
	
	var testChat3 = new Chat({
		debug : true,
		userId : "chat3",
		apiUrl : "/spring-test/chat3",
		showAreaId : "chat3",
		inputAreaId : "input_chat3_area",
		onair : true
	});
	
	var testChat4 = new Chat({
		debug : true,
		userId : "chat4",
		apiUrl : "/spring-test/chat4",
		showAreaId : "chat4",
		inputAreaId : "input_chat4_area",
		onair : true
	});

	var testForum = new Forum({
		debug : true
	});

	testForum.addChat(testChat1);
	testForum.addChat(testChat2);
	testForum.addChat(testChat3);
	testForum.addChat(testChat4);
});
</script>
</body>
</html>