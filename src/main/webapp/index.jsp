<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <%--jquery--%>
    <script src="static/js/jquery-3.4.1.min.js"></script>
    <!-- Bootstrap -->
    <link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set value="${pageContext.request.contextPath}" var="ctx"/>

</head>
<body>
<h1>Atguigu-SSM-CRUD-首页</h1><br><br>
<h3><a href="emps/jsp">点击此处查看员工列表(JSP页面解析形式)</a>
    <h3></h3><br><br>
    <h3><a href="emps/toJson">点击此处查看员工列表(AJAX加JSON解析形式)</a>
        <h3></h3><br><br>
        <%--<jsp:forward page="/emps"/>--%>

        <%--将数据封装到，在jsp页面显示，此种方式下
            返回的页面只能被浏览器解析
        --%>

        <%--发起ajax请求，以json形式获取数据，局部刷新，不管是安卓客户端，IOS客户端
            还是浏览器，都能解析json数据并渲染出页面（使用js操作DOM）
        --%>
</body>
</html>
