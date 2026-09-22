<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${article.title}</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; background: #f3f4f6; color: #1f2937; padding: 30px 16px; font-size: 15px; }
        .page-card { max-width: 880px; margin: 0 auto; background: #ffffff; border: 2px solid #d1d5db; border-radius: 10px; padding: 26px 28px; box-shadow: 0 2px 10px rgba(0,0,0,0.06); }
        h1 { font-size: 24px; padding-bottom: 12px; border-bottom: 2px solid #2563eb; }
        .meta { border: 1px solid #e5e7eb; background: #f9fafb; border-radius: 8px; padding: 10px 14px; color: #6b7280; font-size: 14px; margin: 16px 0; }
        .content { border: 1px solid #e5e7eb; border-radius: 8px; padding: 18px; line-height: 1.8; word-break: break-word; }
        .back { margin-top: 20px; }
        .btn { display: inline-block; border: 1px solid #2563eb; color: #2563eb; background: #fff; border-radius: 6px; padding: 8px 16px; font-size: 14px; text-decoration: none; }
        .btn:hover { background: #2563eb; color: #fff; }
    </style>
</head>
<body>
<div class="page-card">
    <h1>${article.title}</h1>
    <div class="meta">作者：${article.username} ｜ 分类：${article.categoryName} ｜ 发布时间：${article.createTime}</div>
    <div class="content">${article.content}</div>
    <div class="back">
        <a class="btn" href="${pageContext.request.contextPath}/article/list">← 返回文章列表</a>
    </div>
</div>
</body>
</html>
