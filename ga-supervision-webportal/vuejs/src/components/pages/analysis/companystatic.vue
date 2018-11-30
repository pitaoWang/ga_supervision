<template>
    <div class="container_div" style="height: auto">
        <div>
            <Form :label-width="100" style="">
                <FormItem label="所属监管局" style="width:350px;margin-left: 0px;" prop="supervisionId">
                    <Select v-model="supervision.supervisionId" @on-change="onChange()">
                        <Option value="all">全部</Option>
                        <Option v-for="item in supervisions" :value="item.id" :key="item.index">{{ item.title }}
                        </Option>
                    </Select>
                </FormItem>
            </Form>
        </div>
        <Row style="padding:10px;">
            <Col span="11">
                <Card style="height:320px;">
                    <div style="margin-bottom:10px;">
                        <div id="myChart" style="width:100%;height:300px;padding-top:10px;"></div>
                    </div>
                </Card>
            </Col>
            <Col span="11" offset="2">
                <Card style="height:320px;">
                    <div style="margin-bottom:10px;padding-right: 10px;padding-left: 10px;">
                        <div id="myChart1" style="width:100%;height:300px;padding-top:10px;"></div>
                    </div>
                </Card>
            </Col>
        </Row>

        <Row style="padding:10px">
            <Col span="24">
                <Card style="height:420px;">
                    <Form :label-width="100" style="">
                        <FormItem label="开始日期" style="width:270px;margin-left: 0px;float:left" prop="begaindate">
                            <DatePicker type="date" placeholder="选择日期" style="width: 200px;float: left"
                                        v-model="begaindate"
                                        @on-change="changeSelect()"></DatePicker>
                        </FormItem>
                        <FormItem label="结束日期" style="width:270px;margin-left: 0px;float:left" prop="enddate">
                            <DatePicker type="date" placeholder="选择日期" style="width: 200px;float: left"
                                        v-model="enddate"
                                        @on-change="changeSelect()"></DatePicker>
                        </FormItem>
                        <FormItem label="作业类型" style="width:270px;margin-left: 0px;float:left" prop="flightnature">
                            <Select v-model="flightnature" style="width: 200px;float: left" placeholder="请选择"
                                    @on-change="changeSelect()">
                                <Option value="all">全部</Option>
                                <Option value="1">任务飞行</Option>
                                <Option value="2">训练飞行</Option>
                                <Option value="3">检查飞行</Option>
                                <Option value="4">实践考验</Option>
                            </Select>
                        </FormItem>
                        <FormItem label="所属监管局" style="width:270px;margin-left: 15px;float:left" prop="supervisionId">
                            <Select v-model="supervisionId" style="width: 200px;float: left"
                                    @on-change="changeSelect()">
                                <Option value="all">全部</Option>
                                <Option v-for="item in supervisions" :value="item.id" :key="item.index">{{ item.title }}
                                </Option>
                            </Select>
                        </FormItem>
                    </Form>

                    <div id="myChart3" style="width:1200px;height:300px;margin-top: 30px;margin-bottom: 10px;margin-left: 20px;"></div>
                </Card>
            </Col>
        </Row>


        <Row style="padding:10px">
            <Col span="24">
                <Card style="height:420px;">
                    <!--飞行趋势-->
                    <Form :label-width="100" style="margin-bottom: -23px;">
                        <FormItem label="开始日期" style="width:270px;margin-left: 0px;float:left" prop="begaindate1">
                            <DatePicker type="date" placeholder="选择日期" style="width: 200px;float: left"
                                        v-model="begaindate1" @on-change="changeSelect1"></DatePicker>
                        </FormItem>
                        <FormItem label="结束日期" style="width:270px;margin-left: 0px;float:left" prop="enddate1">
                            <DatePicker type="date" placeholder="选择日期" style="width: 200px;float: left"
                                        v-model="enddate1" @on-change="changeSelect1"></DatePicker>
                        </FormItem>
                        <FormItem label="作业类别" style="width:270px;margin-left: 0px;float:left" prop="flightnature1">
                            <Select v-model="flightnature1" style="width: 200px;float: left" placeholder="请选择"
                                    @on-change="changeSelect1">
                                <Option value="all">全部</Option>
                                <Option value="1">任务飞行</Option>
                                <Option value="2">训练飞行</Option>
                                <Option value="3">检查飞行</Option>
                                <Option value="4">实践考验</Option>
                            </Select>
                        </FormItem>
                        <FormItem label="所属监管局" style="width:270px;margin-left: 15px;float:left" prop="supervisionId1">
                            <Select v-model="supervisionId1" style="width: 200px;float: left"
                                    @on-change="selectCompany">
                                <Option value="all">全部
                                </Option>
                                <Option v-for="item in supervisions" :value="item.id" :key="item.index">{{ item.title }}
                                </Option>
                            </Select>
                        </FormItem>
                        <div v-if="flag">
                            <span  style="width:100px;margin-left: 70px;float:left">企业名称</span>
                                <CheckboxGroup v-model="companyId" @on-change="changeSelect1">
                                    <Checkbox v-for="item in companyIds" :label="item.companyId" :key="item.index">
                                        {{item.name}}
                                    </Checkbox>
                                </CheckboxGroup>

                        </div>
                    </Form>
                    <div id="myChart4" style="width:1200px;height:300px;margin-top: 50px;margin-left:30px;"></div>
                </Card>
            </Col>
        </Row>


    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    import {
        findByParentId
    } from "@/api/index";
    // 引入 ECharts 主模块
    var echarts = require('echarts/lib/echarts');
    // 引入柱状图
    require('echarts/lib/chart/bar');
    require('echarts/lib/chart/line');
    // 引入饼图组件
    require('echarts/lib/chart/pie')
    // 引入提示框和标题组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');

    export default {
        name: "companystatic",
        data() {
            return {
                columnData: [],
                columnData1: [],
                supervision: {
                    supervisionId: '',
                },
                supervisions: [],
                myChart3Data: [],
                begaindate: '',
                begaindate1: '',
                enddate: '',
                enddate1: '',
                flightnature: '',
                flightnature1: '',
                supervisionId: '',
                supervisionId1: '',
                companyId: [],
                companyIds: [],
                myChart4Data: [],
                flag: false,
                companyNames: [],
            }
        },
        methods: {
            initData() {
                //运行合格证类型
                const columnoption = {
                    title: {text: '企业运行合格证种类',
                        textStyle:{fontSize:14},
                        x:'center',
                        y:'top',
                        textAlign:'left'},
                    tooltip: {},
                    xAxis: {
                        data: this.columnDataX,
                        axisLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                        axisLabel: {
                            color: 'rgb(1,107,217)'
                        },
                        splitLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        }
                    },
                    yAxis: {
                        axisLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                    },
                    series: [{
                        name: '数量',
                        type: 'bar',
                        barWidth: 20,
                        yAxisIndex: 0,
                        data: this.columnDataY,
                        itemStyle: {normal: {color: '#0295FD'}},
                    }]
                }
                this.myChart.setOption(columnoption);

                //企业经营类别统计
                const columnoption1 = {
                    title: {text: '企业经营类别统计',
                        textStyle:{fontSize:14},
                        x:'center',
                        y:'top',
                        textAlign:'left'
                    },
                    tooltip: {},
                    xAxis: {
                        data: this.columnData1X,
                        axisLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                        axisLabel: {
                            color: 'rgb(1,107,217)'
                        },
                        splitLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        }
                    },
                    yAxis: {
                        axisLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                    },
                    series: [{
                        name: '数量',
                        type: 'bar',
                        barWidth: 20,
                        yAxisIndex: 0,
                        data: this.columnData1Y,
                        itemStyle: {normal: {color: '#0295FD'}},
                    }]
                }
                this.myChart1.setOption(columnoption1);


                //通航企业飞行时间
                const option = {
                    title: {text: '通航企业飞行时间统计分析',textStyle:{fontSize:14},x:'center',},
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {}
                        }
                    },
                    grid:{
                        x:45,
                        y:65,
                        x2:38,
                        y2:22,
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: this.myChart3DataX,
//                            axisPointer: {type: 'shadow'},
                            axisLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)'
                                }
                            },
                            axisLabel: {
                                color: 'rgb(1,107,217)'
                            },
                            splitLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)'
                                }
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '飞行总时长(小时)',
//                            min: 0,
//                            max: 1000,
//                            interval: 100,
                            axisLabel: {
                                formatter: '{value}'
                            },
                            axisLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)'
                                }
                            },
                            splitLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)'
                                }
                            },
                        },
                       /* {
                            type: 'value',
                            name: '违规次数',
                            min: 0,
                            max: 10,
                            interval: 1,
                            axisLabel: {
                                formatter: '{value} 次'
                            },
                            axisLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)'
                                }
                            },
                            splitLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)'
                                }
                            },
                        }*/
                    ],
                    series: [
                        {
                            name: '飞行时长',
                            type: 'bar',
                            barWidth: 20,
                            yAxisIndex: 0,
                            itemStyle: {normal: {color: '#0295FD'}},
                            data: this.myChart3DataY
                        },
                        /*{
                            name: '违规次数',
                            type: 'line',
                            yAxisIndex: 1,
                            itemStyle: {normal: {color: '#0295FD'}},
                            data: this.zData
                        }*/
                    ]
                };
                this.myChart3.setOption(option);


                const option1 = {
                    title: {text: '通航企业飞行趋势统计分析',textStyle:{fontSize:14},x:'center',},
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {}
                        }
                    },
                    legend: {

                    },
                    grid:{
                        x:45,
                        y:65,
                        x2:38,
                        y2:48,
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        //data: ['周一','周二','周三','周四','周五','周六','周日'],
                        data: this.myChart4DataX,
                        axisLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                        axisLabel: {
                            color: 'rgb(1,107,217)',
                            //rotate:-30,//-30度角倾斜显示
                        },
                        splitLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        }
                    },
                    yAxis: {
                        type: 'value',
                        name:'飞行总时长(小时)',
                        axisLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                color: 'rgb(1,107,217)'
                            }
                        },
                        axisLabel: {
                            color: 'rgb(1,107,217)',
                            formatter:'{value}',
                        },
                    },
                    series: this.myChart4DataY,
                };
                this.myChart4.setOption(option1, true);
            },
            //运行合格证类型
            getColumnData(supervisionIds) {
                this.$http.httpGet('/eim/api/cq/companyqualificationstaticbysuperIds?supervisionId=' + supervisionIds).then((res) => {
                    this.columnData = res.data;
                });
            },
            //
            onChange(val) {
                if (this.supervision.supervisionId == "all") {
                    let superIds = [];
                    for (let i = 0; i < this.supervisions.length; i++) {
                        superIds.push(this.supervisions[i]["id"]);
                    }
                    this.$http.httpGet('/eim/api/cq/companyqualificationstaticbysuperIds?supervisionId=' + superIds.join(",")).then(res => {
                        this.columnData = res.data;
                    })
                    this.$http.httpGet('/eim/api/company/staticBusinessTypeBySuperIds?superIds=' + superIds.join(",")).then(res => {
                        this.columnData1 = res.data;
                    })
                } else {
                    this.$http.httpGet('/eim/api/cq/companyqualificationstaticbysuperIds?supervisionId=' + this.supervision.supervisionId).then(res => {
                        this.columnData = res.data;
                    })
                    this.$http.httpGet('/eim/api/company/staticBusinessTypeBySuperIds?superIds=' + this.supervision.supervisionId).then((res) => {
                        this.columnData1 = res.data;
                    });
                }
            },

            //企业经营类型
            getColumnData1(supervisionIds) {
                this.$http.httpGet('/eim/api/company/staticBusinessTypeBySuperIds?superIds=' + supervisionIds).then((res) => {
                    this.columnData1 = res.data;
                });

            },


            //通航企业飞行时间
            getMyChart3(supervisionIds) {
                let data = {}
                data.supervisionIds = supervisionIds.join(",")
                data.beginDate = this.begaindate
                data.endDate = this.enddate
                data.flightNature = "1,2,3,4"
                this.$http.httpGet('/eim/api/company/staticflight', data).then(res => {
                    this.myChart3Data = res.data;
                })
            },
            changeSelect() {
                if (this.flightnature == "all" && this.supervisionId == "all") {
                    let superIds = [];
                    for (let i = 0; i < this.supervisions.length; i++) {
                        superIds.push(this.supervisions[i]["id"]);
                    }
                    let data = {}
                    data.supervisionIds = superIds.join(",")
                    data.beginDate = this.begaindate
                    data.endDate = this.enddate
                    data.flightNature = "1,2,3,4"
                    this.$http.httpGet('/eim/api/company/staticflight', data).then(res => {
                        this.myChart3Data = res.data;
                    })
                }
                if (this.flightnature == "all" && this.supervisionId != "all") {
                    let data = {}
                    data.supervisionIds = this.supervisionId
                    data.beginDate = this.begaindate
                    data.endDate = this.enddate
                    data.flightNature = "1,2,3,4"
                    this.$http.httpGet('/eim/api/company/staticflight', data).then(res => {
                        this.myChart3Data = res.data;
                    })
                }
                if (this.flightnature != "all" && this.supervisionId == "all") {
                    let superIds = [];
                    for (let i = 0; i < this.supervisions.length; i++) {
                        superIds.push(this.supervisions[i]["id"]);
                    }
                    let data = {}
                    data.supervisionIds = superIds.join(",")
                    data.beginDate = this.begaindate
                    data.endDate = this.enddate
                    data.flightNature = this.flightnature
                    this.$http.httpGet('/eim/api/company/staticflight', data).then(res => {
                        this.myChart3Data = res.data;
                    })
                }
                if (this.flightnature != "all" && this.supervisionId != "all") {
                    let data = {}
                    data.supervisionIds = this.supervisionId
                    data.beginDate = this.begaindate
                    data.endDate = this.enddate
                    data.flightNature = this.flightnature
                    this.$http.httpGet('/eim/api/company/staticflight', data).then(res => {
                        this.myChart3Data = res.data;
                    })
                }
            },

            //飞行趋势
            getMyChart4(supervisionIds) {
                this.$http.httpGet('/eim/api/company/selectcompanysBysupervisionids?supervisionIds=' + supervisionIds.join(",")).then(res => {
                    //this.companyIds = res.data;
                    let companyIds = [];
                    for (let i = 0; i < (res.data).length; i++) {
                        let obj = {};
                        obj.companyId = (res.data)[i][0]
                        obj.name = (res.data)[i][1];
                        obj.index = i;
                        companyIds.push(obj);
                    }
                    this.companyIds = companyIds;
                    let companyIds1 = [];
                    for (let i = 0; i < (res.data).length; i++) {
                        companyIds1.push((res.data)[i][0]);
                    }
                    let data = {}
                    data.beginDate = this.begaindate1;
                    data.endDate = this.enddate1;
                    data.companyId = companyIds1.join(",")
                    this.$http.httpGet('/eim/api/company/staticflightcountsbydateandcompanyId', data).then(res => {
                        this.myChart4Data = res.data;

                    })

                })
            },

            selectCompany() {
                let self = this;
                self.companyId = [];
                self.flag = true;
                if (self.supervisionId1 == "all") {
                    let supervisionId1 = [];
                    for (let i = 0; i < self.supervisions.length; i++) {
                        supervisionId1.push(self.supervisions[i]["id"]);
                    }
                    self.$http.httpGet('/eim/api/company/selectcompanysBysupervisionids?supervisionIds=' + supervisionId1.join(",")).then(res => {
                        let companyIds = [];
                        for (let i = 0; i < (res.data).length; i++) {
                            let obj = {};
                            obj.companyId = (res.data)[i][0]
                            obj.name = (res.data)[i][1];
                            companyIds.push(obj);
                        }
                        self.companyIds = companyIds;

                    })
                }else{
                    self.$http.httpGet('/eim/api/company/search/BySupervisionId', {
                        supervisionId: self.supervisionId1,
                    }).then(function (response) {
                        let datainfo = response.data.data;
                        self.companyIds = datainfo;
                    }).catch(function (error) {
                        console.log(error);
                    });
                }

            },
            changeSelect1(data) {
                if (this.companyId== '') {
                    this.myChart4.xAxis = "";
                    this.myChart4.series = [];
                }
                if (this.flightnature1 == "all") {
                    let data1 = {}
                    data1.beginDate = this.begaindate1;
                    data1.endDate = this.enddate1;
                    data1.companyId = this.companyId.join(",")
                    this.$http.httpGet('/eim/api/company/selectcompanynamesbycompanyids?companyIds=' + data1.companyId).then(res => {
                        this.companyNames = res.data;
                    })
                    data1.flightNatures = "1,2,3,4"
                    this.$http.httpGet('/eim/api/company/staticflightcountsbydateandcompanyId', data1).then(res => {
                        this.myChart4Data = res.data;
                    })
                }
                if (this.companyId.length != 0 && this.flightnature1 != "all") {
                    let data1 = {}
                    data1.beginDate = this.begaindate1;
                    data1.endDate = this.enddate1;
                    data1.companyId = this.companyId.join(",")
                    this.$http.httpGet('/eim/api/company/selectcompanynamesbycompanyids?companyIds=' + data1.companyId).then(res => {
                        this.companyNames = res.data;
                    })
                    data1.flightNatures = this.flightnature1
                    this.$http.httpGet('/eim/api/company/staticflightcountsbydateandcompanyId', data1).then(res => {
                        this.myChart4Data = res.data;
                    })
                }

            },
            forDate() {
                this.begaindate = new Date();
                let d = new Date();
                d.setMonth(new Date().getMonth() + 1);
                this.enddate = d;
            },
            forDate1() {
                this.begaindate1 = new Date();
                let d = new Date();
                d.setMonth(new Date().getMonth() + 1);
                this.enddate1 = d;
            }
        },
        created() {
            this.supervision.supervisionId = "all";
            this.flightnature = "all"
            this.supervisionId = "all"
            this.flightnature1 = "all";
            this.supervisionId1 = "all";
            let datas = JSON.parse(Cookies.get("profile"));
            let datas1 = JSON.parse(Cookies.get("userInfo"));
            //监管局
            if (datas.buz.type == 2) {
                this.supervisions = [{'title': datas.buz.title, 'id': datas1.companyId}]
                let supervisionIds = [];
                for (let i = 0; i < this.supervisions.length; i++) {
                    supervisionIds.push(this.supervisions[i].id);
                }
                this.getColumnData(supervisionIds);
                this.getColumnData1(supervisionIds);
                this.getMyChart3(supervisionIds);
                //this.getMyChart4(supervisionIds);
                this.selectCompany()
            }
            //管理局
            if (datas.buz.type == 1) {
                findByParentId(datas1.companyId).then(res => {
                    if (res.success === true) {
                        this.supervisions = res.result;
                        let supervisionIds = [];
                        for (let i = 0; i < this.supervisions.length; i++) {
                            supervisionIds.push(this.supervisions[i].id);
                        }
                        this.getColumnData(supervisionIds);
                        this.getColumnData1(supervisionIds);
                        this.getMyChart3(supervisionIds);
                        //this.getMyChart4(supervisionIds);
                        this.selectCompany()
                    }
                });
            }


            //this.companyId = "all"
        },
        computed: {
            columnDataX: function () {
                let temp = [];
                for (let i = 0; i < this.columnData.length; i++) {
                    temp.push(this.columnData[i][0])
                }
                return temp;
            },
            columnDataY: function () {
                let temp = [];
                for (let i = 0; i < this.columnData.length; i++) {
                    temp.push(this.columnData[i][1])
                }
                return temp;
            },
            columnData1X: function () {
                let temp = [];

                if(this.columnData1.甲!=0){
                    temp.push("甲");
                }
                if(this.columnData1.乙!=0){
                    temp.push("乙");
                }
                if(this.columnData1.丙!=0){
                    temp.push("丙");
                }
                if(this.columnData1.丁!=0){
                    temp.push("丁");
                }
                return temp;
            },
            columnData1Y: function () {
                let temp = [];
                if(this.columnData1.甲!=0){
                    temp.push(this.columnData1.甲);
                }
                if(this.columnData1.乙!=0){
                    temp.push(this.columnData1.乙);
                }
                if(this.columnData1.丙!=0){
                    temp.push(this.columnData1.丙);
                }
                if(this.columnData1.丁!=0){
                    temp.push(this.columnData1.丁);
                }
                return temp;
            },
            myChart3DataX: function () {
                let temp = [];
                for (let i = 0; i < this.myChart3Data.length; i++) {
                    temp.push(this.myChart3Data[i][0])
                }
                return temp;
            },
            myChart3DataY: function () {
                let temp = [];
                for (let i = 0; i < this.myChart3Data.length; i++) {
                    temp.push((this.myChart3Data[i][1] / 3600).toFixed(2))
                }
                return temp;
            },
            myChart4DataX: function () {
                let temp = [];
                for (let i = 0; i < this.myChart4Data.length; i++) {
                    let array = [];
                    for (let j = 0; j < this.myChart4Data[i].length; j++) {
                        array.push(this.myChart4Data[i][j][0].substring(0, 10));
                    }
                    temp = array;
                }

                return temp
            },
            myChart4DataY: function () {
                let ser = [];
                for (let i = 0; i < this.myChart4Data.length; i++) {
                    var obj = {};
                    let array = [];
                    for (let j = 0; j < this.myChart4Data[i].length; j++) {

                        array.push((this.myChart4Data[i][j][1] / 3600).toFixed(2));

                    }
                    obj.type = "line";
                    obj.name = this.companyNames[i];
                    obj.data = array;
                    ser.push(obj)
                }

                return ser
            },
            myChart4Legend: function () {
                let temp = [];
                for (let i = 0; i < this.companyNames.length; i++) {
                    temp.push(this.companyNames[i])
                }
                return temp;
            }
        },
        watch: {
            columnData: function (val) {
                this.initData();
            },
            columnData1: function (val) {
                this.initData();
            },
            myChart3Data: function (val) {
                this.initData()
            },
            myChart4Data: function (val) {
                this.initData()
            },
        },
        mounted() {
            //企业运行种类
            this.myChart = echarts.init(document.getElementById('myChart'));
            this.myChart1 = echarts.init(document.getElementById('myChart1'));
            //企业飞行时间
            this.myChart3 = echarts.init(document.getElementById('myChart3'));
            this.myChart4 = echarts.init(document.getElementById('myChart4'));
            this.initData();
            this.forDate();
            this.forDate1();
        },

    }
</script>

<style scoped>

</style>