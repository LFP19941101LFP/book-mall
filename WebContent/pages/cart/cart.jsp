<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%--<base href="http://localhost:8080/BookStore02/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>
	<%@include file="/pages/common/header.jsp"%>

</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/w.jpg" >
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_success.jsp"%>
			<script type="text/javascript">

				$(function () {
					/*给删除绑定单机事件*/
					$("a.delItem").click(function () {
						return confirm("确定要删除【"+ $(this).parent().parent().find("td:first").text() +"】吗?");
					});
					/*给清空购物车绑定单机事件*/
					$("#clearCart").click(function () {
						return confirm("确定要清空购物车吗?");
					});
					/*给修改购物车中修改商品数量绑定事件*/
					$(".upCount").change(function () {
						var name = $(this).parent().parent().find("td:first").text();
						var id = $(this).attr("bookId");
						var count = this.value;
						if (confirm("确定要【"+ name +"】修改为【"+ count +"】吗？")) {
							location.href="http://localhost:8080/book-mall/cartServlet?action=updateItem&count="+ count + "&id=" + id;
						} else {
							/*defaultValue是表单项默认值,是dom对象*/
							this.value = this.defaultValue;
						}
					});
				});
			</script>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td><a href="index.jsp">当前购物车为空,前往首页浏览</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="upCount" type="text" bookId="${entry.value.id}"
								   value="${entry.value.count}" style="width: 80px"/>
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td>
							<a class="delItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>

			

			
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span  class="cart_span"><a id="clearCart" href="cartServlet?action=clearItem">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>