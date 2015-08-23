<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

	function submitQuery(){
		$("#currentPage").attr("value",1);
		
		queryForm.submit();
	}
	
	function nextPage(){
		currentPage = $("#currentPage").attr("value");
		//alert(currentPage);
		if(currentPage == null || currentPage == ""){
			currentPage = 1;
		}else{
			currentPage ++;
		}
		//alert(currentPage);
		$("#currentPage").attr("value",currentPage);
		queryForm.submit();
	}
	
	function prePage(){
		currentPage = $("#currentPage").attr("value");
		//alert(currentPage);
		if(currentPage == null || currentPage == ""){
			currentPage = 1;
		}else{
			currentPage --;
		}
		//alert(currentPage);
		$("#currentPage").attr("value",currentPage);
		queryForm.submit();
	}
	
	function firstPage(){
		$("#currentPage").attr("value",1);
		queryForm.submit();
	}
	
	function lastPage(currentPage){
		//alert(currentPage);
		$("#currentPage").attr("value",currentPage);
		queryForm.submit();
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
				<li>
					<a href="admin_addNewUser.jsp">
						<span><img src="${pageContext.request.contextPath }/Resources/images/t01.png" /></span>
					</a>添加
				</li>
				
				<li>
					<span><img src="${pageContext.request.contextPath }/Resources/images/t02.png" /></span>修改
				</li>
				
				<li>
					<a href="admin_removeUser.jsp">
						<span><img src="${pageContext.request.contextPath }/Resources/images/t03.png" /></span>
					</a>删除
				</li>
				
				<li onclick="getUserList()">
					<span><img src="${pageContext.request.contextPath }/Resources/images/t04.png" /></span>查看
				</li>
			</ul>

			<ul class="toolbar1">
				<li>
					<span><img src="${pageContext.request.contextPath }/Resources/images/t05.png" /></span>设置
				</li>
			</ul>
		</div>

		<div>
			<form id="queryForm" name="queryForm" action="${pageContext.request.contextPath}/user/userList"
				method="post">
				<div id="tab2" class="tabson">
					<ul class="searchform">
						<li>
							<label>用户ID</label>
							<input name="userDTO.userId" type="text" class="scinput"/>
						</li>
						
						<li>
							<label>用户名称</label>
							<input name="userDTO.userName" type="text" class="scinput"/>
						</li>
						
						<li>
							<label>&nbsp;</label>
							<input name="" type="submit" class="scbtn" value="查询" onclick="submitQuery()"/>
							<input id="currentPage" name="page.currentPage" value="<s:property value="page.currentPage"/>"/>
						</li>
					</ul>
				</div>
			</form>
		</div>
		
		<table class="tablelist">
			<thead>
				<tr>
					<th class="checkbox_format">
						<input name="" type="checkbox" value=""/>
					</th>
					<th>用户ID
						<i class="sort">
							<img src="${pageContext.request.contextPath }/Resources/images/px.gif" />
						</i>
					</th>
					<th>用户名</th>
					<th>性别</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>状态</th>
					<th>等级</th>
					<th>操作</th>
				</tr>
			</thead>
			<%-- <s:debug></s:debug> --%>
			<tbody>
				<s:iterator var="user" value="page.list">
					<tr>
						<td><input name="" type="checkbox" value="" /></td>
						<td><s:property value="userId"/></td>
						<td><s:property value="userName"/></td>
						<td><s:property value="gender"/></td>
						<td><s:property value="telphone"/></td>
						<td><s:property value="email"/></td>
						<td><s:property value="state"/></td>
						<td><s:property value="grade"/></td>
						<td>
							<a href="${pageContext.request.contextPath }/pages/backend/goods/findById.do?rowId='%{#user.Id}'"
								class="tablelink">查看</a>
							<a href="${pageContext.request.contextPath }/pages/backend/goods/deleteById.do?rowId='%{#user.Id}'"
								class="tablelink">删除</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<!-- Page start -->
		<div class="pagin">
			<div class="message">
				<s:if test="page.totalRecord > 0">
					共<i class="blue"><s:property value="page.totalRecord"/></i>条记录，当前显示第&nbsp;
					  <i class="blue"><s:property value="page.currentPage"/>&nbsp;</i>页(共 <s:property value="page.totalPage"/> 页)
					  <!-- 首页 -->
					  <s:if test="page.isFirstPage eq true">
							【首  页】
					  </s:if>
					  <s:else>
						<i>
							<a class="blue" onclick="firstPage()" style="cursor: pointer;">【首 页】</a>
						</i>
					  </s:else>
					  <!-- 上一页  -->
					  <s:if test="page.hasPreviousPage eq true">
						<i><a class="blue" onclick="prePage()" style="cursor: pointer;">【上一页】</a></i>
					  </s:if>
					  <s:else>【上一页】</s:else>
					  <!-- 下一页 -->
					  <s:if test="page.hasNextPage eq true">
						<i>
							<a class="blue" onclick="nextPage()" style="cursor: pointer;">【下一页】</a>
						</i>
					  </s:if>
					  <s:else>【下一页】</s:else>
					  
					  <!-- 尾页  -->
					  <s:if test="page.isLastPage eq true">【尾  页】</s:if>
					  <s:else>
                        <i>
							<a class="blue" onclick="lastPage('<s:property value="page.totalPage"/>')" style="cursor: pointer;">【尾 页】</a>
						</i>
					  </s:else>
				</s:if>
				
				<s:else>
					<div class="noresult">没有符合条件的记录！</div>
				</s:else>
			</div>
		</div>
		<!-- Page end --> 
		
		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span>
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
