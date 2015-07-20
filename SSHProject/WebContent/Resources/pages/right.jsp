<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/Resources/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/Resources/js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});
	});

	function addNewUser() {

	}
</script>
</head>


<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">用户管理</a></li>
			<li><a href="#">用户列表</a></li>
		</ul>
	</div>

	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li><a href="admin_addNewUser.jsp">
				<span><img src="${pageContext.request.contextPath }/Resources/images/t01.png" /></span></a>添加</li>
				<li><span><img
						src="${pageContext.request.contextPath }/Resources/images/t02.png" /></span>修改</li>
				<li><a href="admin_removeUser.jsp">
				<span><img src="${pageContext.request.contextPath }/Resources/images/t03.png" /></span></a>删除</li>
				<li><span><img
						src="${pageContext.request.contextPath }/Resources/images/t04.png" /></span>查看</li>
			</ul>

			<ul class="toolbar1">
				<li><span><img
						src="${pageContext.request.contextPath }/Resources/images/t05.png" /></span>设置</li>
			</ul>
		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th><input type="checkbox" /></th>
					<th>用户ID<i class="sort"><img
							src="${pageContext.request.contextPath }/Resources/images/px.gif" /></i></th>
					<th>用户名</th>
					<th>性别</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>状态</th>
					<th>等级</th>
					<th>注册时间</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>20130908</td>
					<td>王金平幕僚：马英九声明字字见血 人活着没意思</td>
					<td>admin</td>
					<td>江苏南京</td>
					<td>2013-09-09 15:05</td>
					<td>已审核</td>
					<td><a href="#" class="tablelink">查看</a> <a href="#"
						class="tablelink"> 删除</a></td>
				</tr>
			</tbody>
		</table>


		<div class="pagin">
			<div class="message">
				共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页
			</div>
			<ul class="paginList">
				<li class="paginItem"><a href="javascript:;"><span
						class="pagepre"></span></a></li>
				<li class="paginItem"><a href="javascript:;">1</a></li>
				<li class="paginItem current"><a href="javascript:;">2</a></li>
				<li class="paginItem"><a href="javascript:;">3</a></li>
				<li class="paginItem"><a href="javascript:;">4</a></li>
				<li class="paginItem"><a href="javascript:;">5</a></li>
				<li class="paginItem more"><a href="javascript:;">...</a></li>
				<li class="paginItem"><a href="javascript:;">10</a></li>
				<li class="paginItem"><a href="javascript:;"><span
						class="pagenxt"></span></a></li>
			</ul>
		</div>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img
					src="${pageContext.request.contextPath }/Resources/images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
