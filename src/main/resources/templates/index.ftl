<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>欢迎页面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
</head>

<body>

<b>请添加要测试的API：</b>
<input id="apiTxt" name="apiTxt" type="text" value="${api}" />
<input name="addApiBtn" type="submit" value="添加" />

</body>
<script src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $().ready(function(){
        $('#addApiBtn').click(function() {
            var apiTxt = $('#apiTxt').val();
            var url = "/api/add?api=" + apiTxt;
            $.post(url, function(result) {

            });
        });
    });
</script>
</html>