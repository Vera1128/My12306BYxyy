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
<script src="../js/order.js"></script>
<script src="../js/user.js"></script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<img src="../picture/2.jpg" width="1200" /> <span
					class="label label-default" style="background-color: #A4186A;">欢迎进入12306订票服务系统</span>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-3 column">
				<div class="panel-heading">
					<a class="panel-title" data-toggle="collapse"
						data-parent="#panel-543722" href="#panel-element-376069"
						style="font-size: 20px; color: black;">我的订单</a>
				</div>
				<div id="panel-element-376069" class="panel-collapse in">
					<div class="panel-body">
						<a href="../User/user?action=show&status=1"> 已完成订单</a>
					</div>
					<div class="panel-body">
						<a href="../User/user?action=show&status=2">未完成订单</a>
					</div>
				</div>
				<div class="panel-heading">
					<a class="panel-title collapsed" data-toggle="collapse"
						data-parent="#panel-543722" href="#panel-element-657715"
						style="font-size: 20px; color: black;">个人信息</a>
				</div>
				<br>
				<div id="panel-element-657715" class="panel-collapse collapse">
					<div class="panel-body">
						<a href="../User/user?action=showUserInfo">查看个人信息</a>
					</div>
					<div class="panel-body">
						<a href="../User/user?action=showUserInfo">常用联系人</a>
					</div>
				</div>
				<button type="button" class="btn btn-large btn-block" id="uBuyBtn"
					style="background-color: pink;">查票或购票</button>
			</div>
			<div class="col-md-9 column">
				<div class="btn-group btn-group-md">
					<button type="button" id="umUpdateBtn"
						style="background-color: pink;">改签</button>
					<button type="button" id="umDeleteBtn" name="umDeleteBtn"
						style="background-color: pink;">退票</button>
					<button type="button" id="" style="background-color: pink;">
						变更到站</button>
				</div>

				<div class="panel-heading"  >
					<c:forEach items="${data.orderList}" var="ss" varStatus="s1">
						<tbody>
							<a class="panel-title" data-toggle="collapse"
								data-parent="#panel-543722" href="#panel-element-${s1.index}">
								<tr>
									<div class="row clearfix" name="cilckDiv"
										value="${ss.id}">
										<div class="col-md-4 column"
											style="background-color: #99C5E5;">
											<td><c:out value="${ss.order_date}" /></td>
										</div>
										<div class="col-md-3 column"
											style="background-color: #99C5E5;">
											<td>预订人：<c:out value="${ss.userid.realname}" /></td>
										</div>
										<div class="col-md-2 column"
											style="background-color: #99C5E5;">
											<td>票数：<c:out value="${ss.tickets}" />张
											</td>
										</div>
										<div class="col-md-3 column"
											style="background-color: #99C5E5;">
											<td>总金额：<c:out value="${ss.totalmoney}" />￥
											</td>
										</div>
										
										
										
									</div>
								</tr>
							</a>
							<br />
							
							
							<div id="panel-element-${s1.index}" class="panel-collapse in">
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>选择</th>
												<th>车次信息</th>
												<th>旅客信息</th>
												<th>票款金额</th>
												<th>票样</th>
											</tr>
										</thead>
										<tbody>
<%-- 										${data.data} --%>
					        	<c:forEach items="${detaildata.orderDetails}" var="s" 
											varStatus="s1"> 
												<tr>
													<td><input type="checkbox" name="sODid" id="sODid"
														value="${s.id}">
													<td><c:out value="${s.trainid.train_number}" /></td>
													<td><c:out value="${s.userid.realname}" /></td>
													<td><c:out value="${s.trainid.price}" /></td>
													<td><button type="button" id="umDeleteBtn"
															style="background-color: pink;">票${s1.index + 1}</button></td>
												</tr>
</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							
							
					</c:forEach>
					</tbody>
				</div>

			</div>
		</div>
	</div>
</body>
</html>