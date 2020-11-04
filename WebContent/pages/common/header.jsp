<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/8/9
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--动态获取请求地址,拼接字符串--%>
<%
    String basePath = request.getScheme()
                        + "://"
                        + request.getServerName()
                        + ":"
                        + request.getServerPort()
                        + request.getContextPath()
                        + "/";
    pageContext.setAttribute("basePath",basePath);
%>
<!--base标签永远固定相对路径的跳转结果-->
<%--<base href="http://localhost:8080/book_mall/">--%>
<base href = "<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>