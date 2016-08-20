<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人信息</title>
	<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <script src="../bootstrap/js/jquery.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../js/user.js"></script>
    <script src="../js/order.js"></script>
	<link rel="stylesheet" href="../css/User_main.css">
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		<img src="../picture/2.jpg" width="1050"/>
		 <span class="label label-default">欢迎进入12306订票服务系统</span>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-3 column">
			<div class="panel-heading">
					<a class="panel-title" data-toggle="collapse" data-parent="#panel-543722" href="#panel-element-376069" style="font-size:25px; color:black;">我的订单</a>
					</div>
					<div id="panel-element-376069" class="panel-collapse in">
						<div class="panel-body">
							<a href="../User/user?action=show&status=1">已完成订单</a>
						</div>
						<div class="panel-body">
							<a href="../User/user?action=show&status=2">未完成订单</a>
						</div>
					</div>						
				<div class="panel-heading">
						 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-543722" href="#panel-element-657715" style="font-size:25px; color:black">个人信息</a>
					</div>
					<br><div id="panel-element-657715" class="panel-collapse collapse">
						<div class="panel-body">
							<a href="../User/user?action=showUserInfo">查看个人信息</a>
						</div>
						<div class="panel-body">
							常用联系人
						</div>
						</div> 
					<button type="button" class="btn btn-large btn-block" style="background-color: pink;"
					id="uBuyBtn">查票或购票</button>
		</div>
		<div class="col-md-8 column">
		<div id="single_menu">
			<table class="table table-condensed table-bordered">
				<caption>常用联系人</caption>	
				<tr>
					</th>
					<th class="col-md-1.5 column">姓名</th>
					<th class="col-md-1.5 column">性别</th>
					<th class="col-md-1.5 column">證件類型</th>
					<th class="col-md-1.5 column">證件號碼</th>
				</tr>
				<tbody>
					<c:forEach items="${data.users}" var="s" varStatus="s1">
						<tr>
							<td><c:out value="${s.realname}" /></td>
							<td>
								<c:if test='${s.sex ne "2"}'>							
									<c:out value="男"/>
								</c:if>
								<c:if test='${s.sex ne "1"}'>							
									<c:out value="女"/>
								</c:if>
							</td>
							<td><c:out value="${s.cert_type.content}" /></td>
							<td><c:out value="${s.cert}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>			
		</div>
	</div>
</div>
</body>
</html>