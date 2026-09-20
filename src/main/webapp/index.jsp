<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人知识系统首页</title>
</head>
<body>
<%-- 判断是否登录 --%>
<c:if test="${sessionScope.loginUser != null}">
    欢迎：${sessionScope.loginUser.username}
    <a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
    <a href="${pageContext.request.contextPath}/article/toAdd">撰写新文章</a>
</c:if>
<c:if test="${sessionScope.loginUser == null}">
    <a href="${pageContext.request.contextPath}/user/toLogin">前往登录</a>
</c:if>

<h3>文章分类</h3>
<a href="${pageContext.request.contextPath}/article/list">全部文章</a>
<c:forEach items="${categoryList}" var="cat">
    <a href="${pageContext.request.contextPath}/article/list?categoryId=${cat.categoryId}">${cat.categoryName}</a>
</c:forEach>

<h3>文章列表</h3>
<c:forEach items="${articleList}" var="art">
    <div>
        <a href="${pageContext.request.contextPath}/article/detail?articleId=${art.articleId}">${art.title}</a>
        &nbsp;&nbsp;
        作者：${art.username} | 分类：${art.categoryName} | ${art.createTime}
            <%-- 只有文章作者可以看到删除按钮 --%>
        <c:if test="${sessionScope.loginUser.userId == art.userId}">
            <a href="${pageContext.request.contextPath}/article/delete?articleId=${art.articleId}" onclick="return confirm('确定删除该文章？')">删除</a>
        </c:if>
    </div>
</c:forEach>
</body>
</html>
