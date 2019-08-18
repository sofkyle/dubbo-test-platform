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

<form action="/api/invoke" method="post" content="application/x-www-form-urlencoded">
    <div id="service-info">
        <div>服务名：<input name="service-name" type="text" v-model="name" readonly /></div>
        <div>协议：<input name="protocol" type="text" v-model="name" readonly /></div>
        <div>Version：<input name="version" type="text" v-model="name" readonly /></div>
        <div>Group：<input name="group" type="text" v-model="name" readonly /></div>
        <select name="public-choice" v-model="providerSelected" @change="getProvider">
            <option :value="provider.host" v-for="provider in providers" >{{ provider.methods[0] }}</option>
        </select><br />
        <input type="submit" value="调用" />
    </div>
</form>

</body>

<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    new Vue({
        el: '#service-info',
        data: {
            name: "${apiVO.providerVO.serviceName}",
            providers: [
                <#list apiVO.providerVO.providerDetailVOList as provider >
                    {
                        host: '${provider.host}',
                        port: '${provider.port}',
                        methods: [
                            <#list provider.methods as method>
                                '${method}',
                            </#list>
                        ]
                    }
                </#list>
            ],
            providerSelected: ''
        },
        created() {
            this.providerSelected = this.providers[0].host;
        },
        methods: {
            getProvider() {
                console.log(this.providerSelected)
            }

        }
    })
</script>
</html>