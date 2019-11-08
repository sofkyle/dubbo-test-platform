<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>欢迎页面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">

    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
</head>

<body>

<form action="/api/search" method="post">
    <div id="app">
        <span>
            Protocol:
            <select name="registerProtocol" v-model="registerProtocol" >
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
<script type="text/javascript">
    window.onload = function () {
        var app = new Vue({
            el: '#app',
            data: {
                registerProtocol: 'zookeeper',
                address: '',
                group: ''
            }
        });
    }
</script>
</html>