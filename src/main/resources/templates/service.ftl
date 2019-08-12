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

<div id="service-info">
    <div>服务名：{{ name }}</div>
    <select name="public-choice" v-model="providerSelected" @change="getProvider">
        <option :value="provider.host" v-for="provider in providers" >{{ provider.host }}</option>
    </select>
</div>

</body>

<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    new Vue({
        el: '#service-info',
        data: {
            name: "${userApiVO.providerVO.serviceName}",
            providers: [
                <#list userApiVO.providerVO.providerDetailVOList as provider >
                    {
                        host: '${provider.host}',
                        port: '${provider.port}'
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