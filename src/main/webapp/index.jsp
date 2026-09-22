<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人知识系统首页</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; background: #f3f4f6; color: #1f2937; padding: 30px 16px; font-size: 15px; }
        .page-card { max-width: 880px; margin: 0 auto; background: #ffffff; border: 2px solid #d1d5db; border-radius: 10px; padding: 26px 28px; box-shadow: 0 2px 10px rgba(0,0,0,0.06); }
        h2 { font-size: 22px; padding-bottom: 12px; border-bottom: 2px solid #2563eb; margin-bottom: 18px; }
        a { color: #2563eb; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .topbar { display: flex; flex-wrap: wrap; align-items: center; gap: 14px; border: 1px solid #e5e7eb; background: #f9fafb; border-radius: 8px; padding: 10px 14px; margin-bottom: 18px; }
        .topbar .welcome { font-weight: bold; }
        .topbar .links { display: flex; gap: 12px; }
        .btn { display: inline-block; border: 1px solid #2563eb; color: #2563eb; background: #fff; border-radius: 6px; padding: 6px 14px; font-size: 14px; }
        .btn:hover { background: #2563eb; color: #fff; text-decoration: none; }
        .box { border: 1px solid #e5e7eb; border-radius: 8px; margin-bottom: 20px; overflow: hidden; }
        .box-title { background: #f9fafb; border-bottom: 1px solid #e5e7eb; padding: 10px 16px; font-weight: bold; font-size: 16px; }
        .box-body { padding: 12px 16px; }
        .cat-links { display: flex; flex-wrap: wrap; gap: 12px; }
        .cat-links a { border: 1px solid #d1d5db; border-radius: 6px; padding: 4px 12px; background: #fff; }
        .cat-links a:hover { border-color: #2563eb; }
        .article-item { border: 1px solid #e5e7eb; border-radius: 8px; padding: 12px 16px; margin-bottom: 10px; }
        .article-item:last-child { margin-bottom: 0; }
        .article-title { font-size: 16px; font-weight: bold; }
        .badge { display: inline-block; border-radius: 10px; padding: 1px 10px; font-size: 12px; margin-left: 8px; vertical-align: 2px; }
        .badge-mine { color: #2563eb; border: 1px solid #93c5fd; background: #eff6ff; }
        .badge-other { color: #6b7280; border: 1px solid #d1d5db; background: #f9fafb; }
        .article-meta { color: #6b7280; font-size: 13px; margin-top: 6px; }
        .del-link { color: #dc2626; border: 1px solid #fca5a5; border-radius: 6px; padding: 2px 10px; margin-left: 10px; font-size: 13px; }
        .del-link:hover { background: #fef2f2; text-decoration: none; }
        .empty { color: #6b7280; text-align: center; padding: 16px; }
    </style>
</head>
<body>
<div class="page-card">
    <h2>个人知识系统</h2>

    <c:if test="${not empty msg}">
        <p style="border: 1px solid #fca5a5; background: #fef2f2; color: #dc2626; border-radius: 6px; padding: 8px 14px; margin-bottom: 16px;">${msg}</p>
    </c:if>

    <%-- 顶部登录信息栏 --%>
    <div class="topbar">
        <c:if test="${sessionScope.loginUser != null}">
            <span class="welcome">欢迎：${sessionScope.loginUser.username}</span>
            <span class="links">
                <a class="btn" href="${pageContext.request.contextPath}/article/toAdd">撰写新文章</a>
                <a class="btn" href="${pageContext.request.contextPath}/user/logout">退出登录</a>
            </span>
        </c:if>
        <c:if test="${sessionScope.loginUser == null}">
            <span class="links">
                <a class="btn" href="${pageContext.request.contextPath}/user/toLogin">前往登录</a>
            </span>
        </c:if>
    </div>

    <div class="box">
        <div class="box-title">文章分类</div>
        <div class="box-body cat-links">
            <a href="${pageContext.request.contextPath}/article/list">全部文章</a>
            <c:forEach items="${categoryList}" var="cat">
                <a href="${pageContext.request.contextPath}/article/list?categoryId=${cat.categoryId}">${cat.categoryName}</a>
            </c:forEach>
        </div>
    </div>

    <div class="box">
        <div class="box-title">文章列表</div>
        <div class="box-body">
            <c:forEach items="${articleList}" var="art">
                <div class="article-item">
                    <div class="article-title">
                        <a href="${pageContext.request.contextPath}/article/detail?articleId=${art.articleId}">${art.title}</a>
                        <%-- 登录后区分"我的"和"其他人的"文章 --%>
                        <c:if test="${sessionScope.loginUser != null}">
                            <c:choose>
                                <c:when test="${sessionScope.loginUser.userId == art.userId}">
                                    <span class="badge badge-mine">我的</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-other">他人</span>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                    <div class="article-meta">
                        作者：${art.username} ｜ 分类：${art.categoryName} ｜ ${art.createTime}
                        <%-- 只有文章作者可以看到删除按钮 --%>
                        <c:if test="${sessionScope.loginUser.userId == art.userId}">
                            <a class="del-link" href="${pageContext.request.contextPath}/article/delete?articleId=${art.articleId}" onclick="return confirm('确定删除该文章？')">删除</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty articleList}">
                <div class="empty">暂无文章</div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
