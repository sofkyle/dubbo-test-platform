<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>欢迎页面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>

<body>

<form action="/api/search" method="post">
    <div id="app">
        <span>
            Protocol:
            <select name="protocol" v-model="protocol" >
                <option value="zookeeper">zookeeper</option>
                <option value="multicast">multicast</option>
                <option value="redis">redis</option>
            </select>
        </span><br />
        <span>
            Address:
            <input name="address" type="text" v-model="address" />
        </span><br />
        <span>
            Group:
            <input name="group" type="text" v-model="group" />
        </span><br />
        <input name="searchApiBtn" type="submit" value="获取服务列表" />
    </div>
</form>

</body>
<script src="/public/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    window.onload = function () {
        var app = new Vue({
            el: '#app',
            data: {
                protocol: 'zookeeper',
                address: '',
                group: ''
            }
        });
    }
</script>
</html>