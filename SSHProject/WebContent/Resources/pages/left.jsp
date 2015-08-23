<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/Resources/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath }/Resources/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>工作台</div>
    
    <dl class="leftmenu">
	    <dd>
		    <div class="title">
		    	<span><img src="${pageContext.request.contextPath}/Resources/images/leftico01.png" /></span>
		                     用户管理
		    </div>
	    	<ul class="menuson">
		        <li><cite></cite><a href="right.jsp" target="rightFrame">用户列表</a><i></i></li>
		        <li><cite></cite><a href="imgtable.jsp" target="rightFrame">图片数据表</a><i></i></li>
		        <li><cite></cite><a href="form.jsp" target="rightFrame">添加编辑</a><i></i></li>
		        <li><cite></cite><a href="imglist.jsp" target="rightFrame">图片列表</a><i></i></li>
		        <li><cite></cite><a href="imglist1.jsp" target="rightFrame">自定义</a><i></i></li>
		        <li><cite></cite><a href="tools.jsp" target="rightFrame">常用工具</a><i></i></li>
		        <li><cite></cite><a href="filelist.jsp" target="rightFrame">信息管理</a><i></i></li>
		        <li><cite></cite><a href="tab.jsp" target="rightFrame">Tab页</a><i></i></li>
		        <li><cite></cite><a href="error.jsp" target="rightFrame">404页面</a><i></i></li>
	        </ul>    
	    </dd>
	    
	    <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath }/Resources/images/leftico02.png" /></span>商品管理
	    </div>
	    <ul class="menuson">
	        <li><cite></cite><a href="#">活跃帖子</a><i></i></li>
	        <li><cite></cite><a href="#">已删除帖子</a><i></i></li>
	        <li><cite></cite><a href="#">待审核帖子</a><i></i></li>
	        </ul>     
	    </dd> 
	    
	    
	    <dd>
		    <div class="title"><span><img src="${pageContext.request.contextPath }/Resources/images/leftico03.png" /></span>目录管理</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="#">目录列表</a><i></i></li>
		    </ul>    
	    </dd>  
	    
	    
	    <dd>
	    	<div class="title"><span><img src="${pageContext.request.contextPath }/Resources/images/leftico04.png" /></span>日期管理</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="#">自定义</a><i></i></li>
		        <li><cite></cite><a href="#">常用资料</a><i></i></li>
		        <li><cite></cite><a href="#">信息列表</a><i></i></li>
		        <li><cite></cite><a href="#">其他</a><i></i></li>
		    </ul>
	    </dd>   
    </dl>
</body>
</html>
