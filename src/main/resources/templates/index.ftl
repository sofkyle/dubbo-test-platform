<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>欢迎页面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
</head>

<body>

<form action="/api/search" method="post" content="application/x-www-form-urlencoded">
    <b>请检索要测试的API：</b>
    <input id="apiName" name="apiName" type="text" value="${apiVO.apiName}" />
    <input name="searchApiBtn" type="submit" value="检索" />
</form>
</body>
<script src="/js/jquery-3.2.1.min.js"></script>
</html>