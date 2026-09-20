<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${article.title}</title>
</head>
<body>
<h2>${article.title}</h2>
<p>作者：${article.username}｜分类：${article.categoryName}｜发布时间：${article.createTime}</p>
<hr/>
<div>${article.content}</div>
<br/>
<a href="${pageContext.request.contextPath}/article/list">← 返回文章列表</a>
</body>
</html>
