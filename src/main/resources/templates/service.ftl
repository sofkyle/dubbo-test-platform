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
    <div id="service-group"></div>

</form>

</body>
<script type="text/javascript">
    // 方法分组
    new Vue({
        el: '#service-group',
        data: {
            groupTab: [
                <#list serviceGroupStrList?keys as key >
                {
                    title: '${key}',
                    name: '${key}',
                    content: '${serviceGroupStrList["${key}"]}'
                },
                </#list>
            ],
            groupName: '',
            serviceList: [],
            serviceName: ''
        },
        methods: {
            clickTab(target) {
                let targetName = target.name;
                let tabs = this.groupTab;
                tabs.forEach((tab, index) => {
                    if (tab.name === targetName) {
                        let serviceList = JSON.parse(tab.content);
                        console.log(serviceList);
                        this.serviceList = serviceList;
                    }
                });
            },
            listMethod() {
                axios.get('/api/method/list', {
                    params: {
                        address: '${address}',
                        serviceName: this.serviceName

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
                                paramList: [],
                                methodName: methodList[0].value
                            },
                            template: `<div>
                                                <select id="method-select" v-model="methodName">
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
                                    // check method name
                                    if (this.methodName == '') {
                                        alert("请选择要调用的方法！");
                                    }

                                    // check param
                                    let paramLArray = new Array(this.paramList.length);
                                    for (let i = 0; i < this.paramList.length; i++) {
                                        if (this.paramList[i].paramType.length == 0 || this.paramList[i].paramValue.length == 0) {
                                            alert("参数配置非法！");
                                            return;
                                        }
                                        let param = {
                                            type: this.paramList[i].paramType,
                                            value: this.paramList[i].paramValue
                                        };
                                        paramLArray[i] = param;
                                    }
                                    console.log(paramLArray);

                                    axios.get('/api/method/invoke', {
                                        params: {
                                            protocol: '${protocol}',
                                            address: '${address}',
                                            serviceName: 'org.apache.dubbo.demo.DemoService',
                                            methodName: this.methodName,
                                            registryGroup: '${registryGroup}',
                                            paramList: paramLArray
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
        },
        template:
            `
            <el-tabs type="border-card" tab-position="left" @tab-click="clickTab">
                <el-tab-pane
                v-for="(item, index) in groupTab"
                :label="item.title"
                :name="item.name">

                <el-select v-model="serviceName" filterable placeholder="请选择">
                    <el-option v-for="service in serviceList"
                    :key="service"
                    :label="service"
                    :value="service">
                    </el-option>
                </el-select>
                <input type="button" value="获取方法列表" @click="listMethod" />
                <br />
                <div id="method-div"></div>

                </el-tab-pane>
            </el-tabs>
            `
    });
</script>
</html>