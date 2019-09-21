<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>欢迎页面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>

<body>

<form action="/api/invoke" method="post" content="application/x-www-form-urlencoded">
    <div id="service-info">
        <input name="address" type="hidden" value="${address}" />
        <select>
            <option v-for="option in interfaceList" v-bind:value="option.value" @change="getMethods(option.value)">
                {{ option.text }}
            </option>
        </select>
        <input type="submit" value="调用" />
    </div>
</form>

</body>

<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    new Vue({
        el: '#service-info',
        data: {
            interfaceList: [
                <#list interfaceList as interface >
                    {
                        text: '${interface.name}',
                        value: '${interface.name}'
                    },
                </#list>
            ]
        }
    });

    methods: {
        getMethods: function(interface){
            axios.post('/user', {
                firstName: 'Fred',
            }).then(function (response) {
                console.log(response);
            }).catch(function (error) {
                console.log(error);
            });
        }
    }
</script>
</html>