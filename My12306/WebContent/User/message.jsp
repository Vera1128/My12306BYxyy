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
		<img src="../picture/2.jpg" width="1100"/>
		 <br><span
					class="label label-default" style="background-color: #A4186A;">欢迎进入12306订票服务系统</span>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-3 column">
			<div class="panel-heading">
					<a class="panel-title" data-toggle="collapse" data-parent="#panel-543722" href="#panel-element-376069" style="font-size:25px; color:black;">我的订单</a>
					</div>
					<br><div id="panel-element-376069" class="panel-collapse in">
						<div class="panel-body">
							<a href="../User/user?action=show&status=2">未完成订单</a>
						</div>
						<div class="panel-body">
							<a href="../User/user?action=show&status=1">已完成订单</a>
						</div>
					</div>						
				<div class="panel-heading">
						 <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-543722" href="#panel-element-657715" style="font-size:25px; color:black;">个人信息</a>
					</div>
					<br><div id="panel-element-657715" class="panel-collapse collapse">
						<div class="panel-body">
							查看个人信息
						</div>
						<div class="panel-body">
							<a href="../User/user?action=showContacts">常用联系人</a>
						</div>
					</div>
					<button type="button" class="btn btn-large btn-block" id="uBuyBtn"
					style="background-color: pink;">查票或购票</button>
		</div>
		<div class="col-md-5 column">
		<div id="single_menu">
				查看个人信息<hr/>
			</div>
			<div id="single_menu">
				<font color="red">*</font>用户名：&nbsp&nbsp
				<span id="username" name="username">${userdata.UserMessage.username}</span>					
			</div>

			<div id="single_menu">
				<font color="red">*</font>真实姓名：&nbsp&nbsp
				<span id="realname" name="realname">${userdata.UserMessage.realname}</span>
			</div>
			<div id="single_menu">
				<font color="red">*</font>性别：&nbsp&nbsp
				<c:if test='${userdata.UserMessage.sex ne "2"}'>
					<span id="sex" name="sex">男</span>
				</c:if>
				<c:if test='${userdata.UserMessage.sex ne "1"}'>
					<span id="sex" name="sex">女</span>
				</c:if>
			</div>
			<div id="single_menu">
				<font color="red">*</font>省份：&nbsp&nbsp
				<span id="province" name="province">${userdata.UserMessage.city.father.province}</span>
			</div>
			<div id="single_menu">
				<font color="red">*</font>城市：&nbsp&nbsp
				<span id="city" name="city">${userdata.UserMessage.city.city}</span>
			</div>
			<div id="single_menu">
				<font color="red">*</font>证件类型：&nbsp&nbsp
				<span id="certtype" name="certtype">${userdata.UserMessage.cert_type.content}</span>
			</div>
			<div id="single_menu">
				<font color="red">*</font>证件号码：&nbsp&nbsp
				<span id="cert" name="cert">${userdata.UserMessage.cert}</span>
			</div>
		</div>
		<div class="col-md-4 column">
		<img alt="140x140" src="../picture/5.jpeg" class="img-thumbnail" />
		 <br><a id="modal-534681" href="#modal-container-534681" role="button" class="btn" data-toggle="modal">修改照片</a>
		 <div class="modal fade" id="modal-container-534681" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								请选择照片
							</h4>
						</div>
						<div class="modal-body">
							<img alt="140x140" src="../picture/5.jpeg" class="img-thumbnail" />
						</div>
						<div class="modal-footer">
							 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button type="button" class="btn btn-primary">保存</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>