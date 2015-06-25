<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录后台管理系统</title>
<link href="${pageContext.request.contextPath }/Resources/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath }/Resources/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/Resources/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    	})  
	});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(${pageContext.request.contextPath }/Resources/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录四年后台管理系统</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    <form name="login" action="login" method="post" onsubmit="checkData()" enctype="multipart/form-data">
	    <div class="loginbody">
		    <span class="systemlogo"></span> 
		    <div class="loginbox">
				    <ul>
					    <li><input name="userDTO.userName" id="userName" type="text" class="loginuser"/></li>
					    <li><input name="userDTO.password" id="password" type="password" class="loginpwd" ></li>
					    <li><input type="submit" class="loginbtn" value="登录"/>
					        <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>
					        <label><a href="#">忘记密码？</a></label>
					    </li>
				    </ul>
		    </div>
	    </div>
    </form>
</body>
</html>
