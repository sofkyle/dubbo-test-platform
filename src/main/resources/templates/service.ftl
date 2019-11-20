<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>欢迎页面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css" />
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>

    <script
            src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
            crossorigin="anonymous"></script>
</head>

<body>

<form>
    <div id="method-group"></div>

</form>

</body>
<script type="text/javascript">
    // 方法分组
    new Vue({
        el: '#method-group',
        data: {
            groupTab: [
                <#list serviceList ? keys as key >
                {
                    title: '${key}',
                    name: '${key}'
                },
                </#list>
            ]
        },
        template:
            `
            <el-tabs type="border-card">
                <el-tab-pane
                v-for="(item, index) in groupTab"
                :key="item.name"
                :label="item.title"
                :name="item.name">
                {{item.content}}
                </el-tab-pane>
            </el-tabs>
            `
    });
</script>
</html>