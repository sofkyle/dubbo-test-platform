<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>欢迎页面</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <script
            src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="
            crossorigin="anonymous"></script>
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
        props: ['paramList'],
        template: `<table id="param-row" cellspacing="10"">
                    <tbody>
                        <tr>
                            <td width="300px"><b>参数类型</b></td>
                            <td><b>参数值</b></td>
                        </tr>
                        <tr v-for="(list,index) in paramList">
                            <td><input type="text" placeholder="参数类型" v-bind:value="list.paramType" /></td>
                            <td><input type="text" placeholder="参数值" v-bind:value="list.paramValue" /></td>
                            <td><input type="button" value="-" v-on:click=delRow(index) /></td>
                        </tr>
                        <tr id="add-btn">
                            <td><input type="button" value="+" v-on:click="addRow" /></td>
                        </tr>
                    </tbody>
                    </table>`
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
                        serviceName: 'org.apache.dubbo.demo.DemoService'
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
                                    methodList: methodList,
                                    paramList: [{
                                            paramType: '',
                                            paramValue: ''
                                    }]
                                },
                                template: `<div>
                                                <select id="method-select">
                                                    <option v-for="option in methodList" v-bind:value="option.value">
                                                        {{ option.text }}
                                                    </option>
                                                </select>
                                                <table id="param-row" cellspacing="10"">
                                                <tbody>
                                                    <tr>
                                                        <td width="300px"><b>参数类型</b></td>
                                                        <td><b>参数值</b></td>
                                                    </tr>
                                                    <tr v-for="(list,index) in paramList">
                                                        <td><input type="text" placeholder="参数类型" v-bind:value="list.paramType" v-on:blur="editType(index, $event)" /></td>
                                                        <td><input type="text" placeholder="参数值" v-bind:value="list.paramValue" v-on:blur="editValue(index, $event)" /></td>
                                                        <td><input type="button" value="-" v-on:click=delRow(index) /></td>
                                                    </tr>
                                                    <tr id="add-btn">
                                                        <td><input type="button" value="+" v-on:click="addRow" /></td>
                                                    </tr>
                                                </tbody>
                                                </table>
                                                <input id="method-invoke-btn" type="button" value="调用方法" v-on:click="invoke" />
                                                <br />
                                                <textarea id="method-invoke-msg-txta" style="width: 100%;height: 400px;"/>
                                            </div>`,
                                methods: {
                                    invoke: function (event) {
                                        // check param
                                        for (let i = 0; i < this.paramList.length; i++) {
                                            if (this.paramList[i].paramType.length == 0 || this.paramList[i].paramValue.length == 0) {
                                                alert("参数配置非法");
                                                return;
                                            }
                                        }

                                        axios.get('/api/method/invoke', {
                                            params: {
                                                address: '${address}',
                                                serviceName: 'org.apache.dubbo.demo.DemoService',
                                                group: '${group}'
                                            }
                                        })
                                                .then(function (response) {
                                                    let result = JSON.stringify(response.data, null, "\t");
                                                    $("#method-invoke-msg-txta").val(result);
                                                })
                                                .catch(function (error) {
                                                    alert(error);
                                                });
                                    },
                                    editType: function(index, event) {
                                        this.paramList[index].paramType = event.currentTarget.value;
                                    },
                                    editValue: function(index, event) {
                                        this.paramList[index].paramValue = event.currentTarget.value;
                                    },
                                    addRow: function () {
                                        this.paramList.push({
                                            paramType: '',
                                            paramValue: ''
                                        });
                                    },
                                    delRow: function (index) {
                                        this.paramList.splice(index, 1);
                                    }
                                }
                            });
                        })
                        .catch(function (error) {
                            alert(error);
                        });
            }
        }
    });
</script>
</html>