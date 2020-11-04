<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/header.jsp"%>
	<%--绑定删除提示事件--%>
	<script type="text/javascript">
		$(function () {

			/*confirm标签是确认提示函数,参数是他提示的内容
			* 他有两个按钮,一个是确认,一个是取消,返回true表示确认,false
			* 表示取消*/
			$("a.deleteClass").click(function () {
				return confirm("确定要删除"+$(this).parent().parent().find("td.first").text()+"吗?")
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/w.jpg" >
			<span class="wel_word">图书管理系统</span>
			<%@include file="/pages/common/manager.jsp"%>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<%--坑:manager前不能加 / ,绑定删除事件,给用户提示确定删除--%>
					<td><a class="deleteClass" href="manager/bookServlet?action=deleteBookInfo&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>
		<%@include file="/pages/common/page.jsp"%>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>