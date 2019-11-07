<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>欢迎页面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>

<body>

<form action="/api/invoke" method="post" content="application/x-www-form-urlencoded">
    <div id="service-info">
        <select>
            <option v-for="option in interfaceList" v-bind:value="option.value">
                {{ option.text }}
            </option>
        </select>
        <input type="button" value="获取方法列表" @click="listMethod" />
        <br />
        <div id="method-div"></div>
    </div>
</form>

</body>
<script type="text/javascript">
    // todo: 构造参数组件
    Vue.component('param-component', {
        data: {
          rowTemplate: '<tr><td><input type="text" placeholder="参数值" /></td></tr>'
        },
        template: `<table>
                    <thead><th><td>参数类型</td><td>参数值</td></th></thead>
                    <tbody id="param-row">
                        <tr>
                            <td><input type="text" placeholder="参数类型" /></td>
                            <td><input type="text" placeholder="参数值" /></td>
                        </tr>
                    </tbody>
                    </table>`,
        methods: {
            addRow: function () {
                this
            }
        }
    });

    new Vue({
        el: '#service-info',
        data: {
            interfaceList: [
                <#list interfaceList as interface >
                    {
                        value: '${interface.name}',
                        text: '${interface.name}'
                    },
                </#list>
            ]
        },
        methods: {
            listMethod: function (event) {
                axios.get('/api/method/list', {
                    params: {
                        address: '${address}',
                        serviceName: "org.apache.dubbo.demo.DemoService"
                    }
                })
                        .then(function (response) {
                            // if response successfully, split join items in methodList
                            console.log(response);
                            var data = response.data;
                            var joinMethodList = new Array(data.length);
                            for (var i = 0; i < data.length; i++) {
                                var optionItem = {
                                    value: data[i],
                                    text: data[i]
                                };
                                joinMethodList[i] = optionItem;
                            }
                            var methodList = joinMethodList;
                            console.log(methodList);

                            <!-- TODO: 独立出“方法调用组件” -->
                            new Vue({
                                el: '#method-div',
                                data: {
                                    methodList: methodList
                                },
                                template: `<div>
                                                <select id="method-select">
                                                    <option v-for="option in methodList" v-bind:value="option.value">
                                                        {{ option.text }}
                                                    </option>
                                                </select>
                                                <param-component></param-component>
                                                <input id="method-invoker-btn" type="button" value="调用方法" v-on:click="invoke" />
                                            </div>`,
                                methods: {
                                    invoke: function (event) {
                                        axios.get('/api/method/invoke', {
                                            params: {
                                                address: '${address}',
                                                serviceName: "org.apache.dubbo.demo.DemoService"
                                            }
                                        })
                                                .then(function (response) {
                                                    // todo: 处理返回结果
                                                    console.log(error);
                                                })
                                                .catch(function (error) {
                                                    console.log(error);
                                                });
                                    }
                                }
                            });
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
        }
    });
</script>
</html>