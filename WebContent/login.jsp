<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>

<body>
	<form action ="login" method="post">
	用户名：<input id="username" name="username" type="text"></input><br><br>
	密码：<input name="password" type="password"></input>
	 <button id="submit" onclick="addcookie()" type="submit">登录</button>
	 <font color="red">${error}</font>
	 </form>
</body>
<script>

var username=null;
function addcookie() {
	username=document.getElementById("username").value;
	document.cookie="username="+encodeURIComponent(username);
}



</script>
</html>