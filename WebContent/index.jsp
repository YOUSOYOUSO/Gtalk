<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gtalk</title>
</head>
<%

Cookie[] cookies = request.getCookies(); 
for(Cookie cookie:cookies){
	String name=cookie.getName();
	if( name.equals("username")){
		String nameValue=cookie.getValue();
	%>
	<p>灯光师照这里：<%=nameValue%></p><br>
	<%
	}
}
%>

<body>
	<div>Gtalk</div>
	<div id="message"></div><br/>
	<textarea rows="10" cols="40" id="ta"></textarea>
       <hr/>
    <input id="text" type="text"/>
    <button onclick="send()">发送消息</button>
 
</body>

<script type="text/javascript">
    var websocket = null;
    var speakername=null;
	
    function getcookie(objname){//获取指定名称的cookie的值
    	var arrstr = document.cookie.split("; ");
    	for(var i = 0;i < arrstr.length;i ++){
    	var temp = arrstr[i].split("=");
    	if(temp[0] == objname) 
    		return temp[1];
    	}
    }
    
  	var username=getcookie("username");
    
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/Gtalk_java/websocket");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
    	websocket.send("WebSocket连接成功\n");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(data) {
        var ele = document.getElementById("ta");
        ele.value = ele.value + data +'\n';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = username+':'+document.getElementById('text').value;
        websocket.send(message);
    }
</script>
</html>