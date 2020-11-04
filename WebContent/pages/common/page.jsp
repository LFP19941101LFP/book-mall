<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/8/12
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <%--坑:将 & 写成 $ 导致返回上一页失败--%>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--<a href="#">3</a>--%>
    [${requestScope.page.pageNo}]
    <%--<a href="#">5</a>--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal }">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${page.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        // 调到指定页码
        $(function () {
            //location地址对象有一个href属性,可以获取浏览器地址栏中的地址
            $("#searchPageBtn").click(function () {
                var pageBtn = $("#pn_input").val();
                location.href="${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNO;
            });
        });
    </script>
</div>