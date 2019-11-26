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
            serviceName: '',
            methodList: [],
            methodName: '',
            paramList: [],
            selectedTab: '',
            invokeResult: ''
        },
        methods: {
            clickTab(target) {
                let targetName = target.name;
                let tabs = this.groupTab;
                tabs.forEach((tab, index) => {
                    if (tab.name === targetName) {
                        let serviceList = JSON.parse(tab.content);
                        this.serviceList = serviceList;
                    }
                });
            },
            async listMethod() {
                if (this.serviceName == '') {
                    return;
                }
                let methodList = [];
                await axios.get('/api/method/list', {
                    params: {
                        address: '${address}',
                        serviceName: this.serviceName
                    }
                })
                    .then(function (response) {
                        // if response successfully, split join items in methodList
                        let data = JSON.stringify(response.data);
                        methodList = JSON.parse(data);
                    })
                    .catch(function (error) {
                        alert(error);
                    });
                this.methodList = methodList;
            },
            async invoke() {
                // check method name
                if (this.methodName == '') {
                    alert("请选择要调用的方法！");
                }

                // clear invoke result
                invokeResult = '';

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

                let result = '';
                await axios.get('/api/method/invoke', {
                    params: {
                        protocol: '${protocol}',
                        address: '${address}',
                        serviceName: this.serviceName,
                        methodName: this.methodName,
                        registryGroup: '${registryGroup}',
                        paramList: paramLArray
                    }
                })
                    .then(function (response) {
                        result = JSON.stringify(response.data, null, "\t");
                    })
                    .catch(function (error) {
                        alert(error);
                    });
                this.invokeResult = result;
            },
            editType(index, event) {
                this.paramList[index].paramType = event.currentTarget.value;
            },
            editValue(index, event) {
                this.paramList[index].paramValue = event.currentTarget.value;
            },
            addRow() {
                this.paramList.push({
                    paramType: '',
                    paramValue: ''
                });
            },
            delRow(index) {
                this.paramList.splice(index, 1);
            }
        },
        template:
            `
            <el-tabs type="border-card" tab-position="left" @tab-click="clickTab">
                <el-tab-pane
                v-for="(item, index) in groupTab"
                :label="item.title"
                :name="item.name">

                <el-select v-model="serviceName" filterable @change="listMethod" placeholder="请选择接口">
                    <el-option v-for="service in serviceList"
                    :key="service"
                    :label="service"
                    :value="service">
                    </el-option>
                </el-select>

                <el-select v-model="methodName" filterable placeholder="请选择方法">
                    <el-option
                    v-for="item in methodList"
                    :key="item"
                    :label="item"
                    :value="item">
                    </el-option>
                </el-select>

                <el-table
                :data="paramList"
                border>
                    <el-table-column
                    prop="paramType"
                    label="参数类型"
                    width="300">
                        <template slot-scope="scope">
                            <el-input v-model="scope.row.paramType" placeholder="请输入参数类型"></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column
                    prop="paramValue"
                    label="参数值"
                    width="300">
                        <template slot-scope="scope">
                            <el-input v-model="scope.row.paramValue" placeholder="请输入参数值"></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column
                    label="操作"
                    width="120">
                        <template slot-scope="scope">
                            <el-button
                            size="mini"
                            @click="delRow(scope.$index)">-</el-button>
                        </template>
                    </el-table-column>
                </el-table>

                <el-button
                size="mini"
                @click="addRow()">+</el-button>
                <br />

                <el-button
                size="mini"
                @click="invoke()">调用方法</el-button>

                <el-input
                autosize
                readonly
                resize="none"
                type="textarea"
                placeholder="结果"
                v-model="invokeResult">
                </el-input>

                </el-tab-pane>
            </el-tabs>
            `
    });
</script>
</html>