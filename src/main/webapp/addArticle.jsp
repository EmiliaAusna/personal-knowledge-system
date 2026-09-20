<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新增知识文章</title>
</head>
<body>
<h2>新增知识文章</h2>
<form action="${pageContext.request.contextPath}/article/add" method="post">
    <div>
        文章标题：<input type="text" name="title" required/>
    </div>
    <div>
        文章分类：
        <select name="categoryId">
            <c:forEach items="${categoryList}" var="cat">
                <option value="${cat.categoryId}">${cat.categoryName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        文章内容：<br>
        <textarea name="content" rows="12" cols="80" required></textarea>
    </div>
    <div>
        <input type="submit" value="提交文章">
    </div>
</form>
</body>
</html>
