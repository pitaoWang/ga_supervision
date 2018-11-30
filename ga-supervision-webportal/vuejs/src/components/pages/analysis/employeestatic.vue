<template>
    <div class="container_div" style="height: auto">

        <Form :label-width="100" style="width: 100%">
            <FormItem label="所属监管局" style="width:350px;;display: inline-block;">
                <Select v-model="formItem.superv" @on-change="changeS" v-show="flag">
                    <Option v-for="item in supervision" :value="item.id">{{item.title}}</Option>
                </Select>
                <Input v-model="formItem.superv" readonly="true" v-show="false"/>
                <Input v-model="supername" readonly="true" v-show="fl"/>
            </FormItem>
            <FormItem label="作业单位" style="width:350px;display: inline-block">
                <Select v-model="formItem.company" placeholder="请选择用户性别" @on-change="changeC">
                    <Option v-for="item in gaCompany" :value="item.companyId">{{item.name}}</Option>
                </Select>
            </FormItem>
        </Form>
        <Row style="padding:10px">
            <Col span="11">
                <Card style="height:320px;">
                    <div style="margin-bottom:10px;width:100%;">
                        <div id="pilotpie" style="width:100%;height:300px;padding-top:10px;margin-bottom:18px;"></div>
                    </div>
                </Card>
            </Col>
            <Col span="11" offset="2">
                <Card style="height:320px;">
                    <div style="margin-bottom:10px;width:100%;">
                        <div id="pilotpie1" style="width:100%;height:300px;padding-top:10px;margin-bottom:18px;"></div>
                    </div>
                </Card>
            </Col>
        </Row>
        <Row style="padding:10px">
            <Col span="24">
                <Card style="height:580px;">

                    <Form :label-width="100" style="">
                        <FormItem label="开始日期" style="width:270px;margin-left: 0px;float:left" prop="begaindate">
                            <DatePicker type="date" placeholder="选择日期" style="width: 200px;float: left"
                                        v-model="begaindate" @on-change="getbegaindate"></DatePicker>
                        </FormItem>
                        <FormItem label="结束日期" style="width:270px;margin-left: 0px;float:left" prop="enddate">
                            <DatePicker type="date" placeholder="选择日期" style="width: 200px;float: left"
                                        v-model="enddate" @on-change="getenddate"></DatePicker>
                        </FormItem>
                        <FormItem label="执照类型" style="width:270px;margin-left: 0px;float:left" prop="licenseclass">
                            <Select v-model="licenseclass" style="width: 200px;float: left" placeholder="请选择">
                                <Option value="学生驾驶员">学生驾驶员</Option>
                                <Option value="运动驾驶员">运动驾驶员</Option>
                                <Option value="私用驾驶员">私用驾驶员</Option>
                                <Option value="商用驾驶员">商用驾驶员</Option>
                                <Option value="多人制机组驾驶员">多人制机组驾驶员</Option>
                                <Option value="航线运输驾驶员">航线运输驾驶员</Option>
                            </Select>
                            &nbsp;
                        </FormItem>
                        <!--<Button style="margin-top: 3px ; margin-left: 40px;" type="primary" @click="gettongji(1)">查询</Button>-->
                        <Button style="margin-top: 3px ; margin-left: 40px;" type="primary" @click="query">查询</Button>
                        <!--<Button style="margin-top: 3px" type="primary" @click="gettongjirecent30()">最近30天</Button>-->
                        <Button style="margin-top: 3px" type="primary" @click="query30">最近30天</Button>
                        <!--<Button style="margin-top: 3px" type="primary" @click="gettongjirecent90()">最近3个月</Button>-->
                        <Button style="margin-top: 3px" type="primary" @click="query90">最近3个月</Button>
                    </Form>

                    <div id="pilotbar"
                         style="width:1200px;height:300px;margin-top: 60px;margin-bottom: 0px;margin-left: 20px;position: relative;top: -30px;"></div>

                    <div style="height: auto;width: 1215px;margin-top: -18px;margin-left: -12px;">
                    <div class=" active div-list-employeestatic" style="width: 92%;float: left;margin-left:77px; " @click="islogshow1=!islogshow1">
                        <div class="name">
                            <div class="logoimgai">
                                <img src="../../../assets/基本信息.png">
                            </div>
                            <div class="name-text">
                                飞行员飞行记录详情统计列表
                            </div>
                            <div class="name-img">
                                <img v-if="islogshow1" src="../../../assets/减号.png" style="margin-top: 15px">
                                <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                            </div>
                        </div>
                    </div>
                    <div v-show="islogshow1" v-if="tableData!=[]" style="width: 92%;float: left;margin-left:77px;">
                        <Table :columns="columns6" :data="tableData" size="default" ref="table" ></Table>
                    </div>

                    <div style="width: 89%;float: left;margin-top: 5px;margin-left:77px;">
                        <div style="float: right;">
                            <Page :total="pagecount" :page-size="pagesize" :current="1" @on-change="changePage" style="padding: 10px 35px 0 0;"></Page>
                        </div>
                    </div>
                    </div>

                </Card>
            </Col>
        </Row>

        <!--<Row style="padding:20px">
            <Col span="8">
                <Card style="width:1280px;height: 400px">
                    <div class=" active div-list-employeestatic" style="width: 89%;float: left;margin-left:70px;" @click="islogshow1=!islogshow1">
                        <div class="name">
                            <div class="logoimgai">
                                <img src="../../../assets/基本信息.png">
                            </div>
                            <div class="name-text">
                                飞行员飞行记录详情统计列表
                            </div>
                            <div class="name-img">
                                <img v-if="islogshow1" src="../../../assets/减号.png" style="margin-top: 15px">
                                <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                            </div>
                        </div>
                    </div>
                    <div v-show="islogshow1" v-if="tableData!=[]" style="width: 89%;float: left;margin-left:70px;">
                        <Table :columns="columns6" :data="tableData" size="default" ref="table" ></Table>
                    </div>

                    <div style="width: 89%;float: left;margin-top: 5px;margin-left:70px;">
                        <div style="float: right;">
                            <Page :total="pagecount" :page-size="pagesize" :current="1" @on-change="changePage"></Page>
                        </div>
                    </div>
                </Card>
            </Col>
        </Row>-->
    </div>
</template>
<script>
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
    import Cookies from 'js-cookie';
    import {
        findByParentId
    } from "@/api/index";

    export default {
        data() {
            return {
                islogshow1: true,
                columns6: [
                    {
                        "title": "序号",
                        "key": "employeeId",
                        "fixed": "left",
                        "width": 120
                    },
                    {
                        "title": "违规次数",
                        "key": "flySumIllegal",
                        "sortable": true,
                        "fixed": "left",
                        "width": 120
                    },
                    {
                        "title": "总计时长(小时)",
                        "key": "flySumTime",
                        "sortable": true,
                        "fixed": "left",
                        "width": 180
                    },
                    {
                        "title": "飞行员",
                        "key": "username",
                        "fixed": "left",
                        "width": 200
                    },
                    {
                        "title": "技术级别",
                        "key": "techClass",
                        "fixed": "left",
                        "width": 180
                    },
                    {
                        "title": "所属飞行队",
                        "key": "flightteam",
                        "fixed": "left"
                    }
                ],
                tableData: [
                    {
                        "employeeId": "",
                        "flySumIllegal": "",
                        "flySumTime": "",
                        "username": "",
                        "licenseType": "",
                        "companyName": ""
                    }
                ],
                opinionData: [],
                opinionData2: [],
                pagecount: '',
                pagesize: 1,
                begaindate: '',
                enddate: '',
                licenseclass: '学生驾驶员',
                supervision: [],
                gaCompany: [],
                formItem: {
                    superv: '',
                    company: ''
                },
                flag: false,
                fl: false,
                companyIds:[],
                supervisionId:'',
                supername:"",

            }
        },
        created() {
            let user = JSON.parse(Cookies.get("userInfo"));
            let ids = user.companyId;
            let profile = JSON.parse(Cookies.get("profile"));
            let type = profile.buz.type;
            if (type == '1') {
                //管理局
                this.flag = true;
                this.fl = false;
                findByParentId(ids).then(res => {
                    let self = this;
                    if (res.success === true) {
                        this.supervision = res.result;
                    }
                });
                this.gettongjirecent30('guanli');
                this.getData1('guanli');
                this.getData2('guanli');

            }else if(type == '2'){

                //监管局
                this.flag = false;
                this.fl = true;
                this.formItem.superv = ids;
                this.supername=profile.buz.title;
                //profile.buz.title;
                this.supervisionId=ids;
                let self = this;
                self.$http.httpGet('/eim/api/company/search/BySupervisionId', {
                    supervisionId: ids,
                }).then(function (response) {
                    let datainfo = response.data.data;
                    self.gaCompany = datainfo;
                }).catch(function (error) {
                    console.log(error);
                });
                this.gettongjirecent30('jianguan');
            this.getData1('jianguan');
            this.getData2('jianguan');
            }



        },
        computed: {
            opinionData1: function () {
                let temp = [];
                for (let row of this.opinionData) {
                    temp.push(row)
                }
                return temp;
            },

            opinionData3: function () {
                let temp = [];
                for (let row of this.opinionData2) {
                    temp.push(row)
                }
                return temp;
            },
            xData: function () {
                let temp = []
                for (let row of this.tableData) {
                    temp.push({value: row.username, textStyle: {color: 'red'}})
                }

                return temp;
            },
            yData: function () {
                let temp = []

                for (let row of this.tableData) {
                    temp.push(row.flySumTime)
                }

                return temp;
            },
            zData: function () {
                let temp = []

                for (let row of this.tableData) {
                    temp.push(row.flySumIllegal)
                }

                return temp;
            },

        },
        mounted() {
            this.myChart = echarts.init(document.getElementById('pilotbar'));
            this.myChart1 = echarts.init(document.getElementById('pilotpie'));
            this.myChart2 = echarts.init(document.getElementById('pilotpie1'));
            this.initData();

        },
        methods: {
            query(){
                let role;
                if(this.formItem.superv ==""||typeof(this.formItem.superv) == "undefined"){
                    role="guanli";
                }
                if(this.formItem.superv!=''&&(this.formItem.company ==""||typeof(this.formItem.company) == "undefined")){
                    role="jianguan";
                }
                if(this.formItem.company !=''){
                    role="company";
                }

                this.gettongji(1,role)
            },
            query30(){
                let role;
                if(this.formItem.superv ==""||typeof(this.formItem.superv) == "undefined"){
                    role="guanli";
                }
                if(this.formItem.superv!=''&&(this.formItem.company ==""||typeof(this.formItem.company) == "undefined")){
                    role="jianguan";
                }
                if(this.formItem.company !=''){
                    role="company";
                }

                this.gettongjirecent30(role)
            },
            query90(){
                let role;
                if(this.formItem.superv ==""||typeof(this.formItem.superv) == "undefined"){
                    role="guanli";
                }
                if(this.formItem.superv!=''&&(this.formItem.company ==""||typeof(this.formItem.company) == "undefined")){
                    role="jianguan";
                }
                if(this.formItem.company !=''){
                    role="company";
                }

                this.gettongjirecent90(role)
            },
            changeS() {
                this.opinionData = [];
                this.opinionData2 = [];
                let self = this;
                var id = this.formItem.superv;
                self.formItem.company = '';
                self.gaCompany = [];
                //根据监管局查询企业
                self.$http.httpGet('/eim/api/company/search/BySupervisionId', {
                    supervisionId: id,
                }).then(function (response) {
                    let datainfo = response.data.data;
                    self.gaCompany = datainfo;
                }).catch(function (error) {
                    console.log(error);
                });
                this.getData1('jianguan');
                this.getData2('jianguan');
                this.gettongjirecent30('jianguan')

            },
            changeC() {
                this.opinionData = [];
                this.opinionData2 = [];
                this.getData1('company');
                this.getData2('company');
                this.gettongjirecent30('company')
            },

            gettongjirecent90(role) {
                let timer = this.timeForMat(90);
                this.begaindate = timer.t2;
                //this.enddate = timer.t1;
                this.enddate=new Date();
                this.gettongji(1,role)

            },
            gettongjirecent30(role) {
                let timer = this.timeForMat(30);
                this.begaindate = timer.t2;
                //this.enddate = timer.t1;
                this.enddate=new Date();
                this.gettongji(1,role)
            },
            timeForMat(count) {
                let time1 = new Date()
                /* time1.setTime(time1.getTime() )
                 let Y1 = time1.getFullYear()
                 let M1 = ((time1.getMonth() + 1) > 10 ? (time1.getMonth() + 1) : '0' + (time1.getMonth() + 1))
                 let D1 = (time1.getDate() > 10 ? time1.getDate() : '0' + time1.getDate())
                 let timer1 = Y1 + '-' + M1 + '-' + D1 // 当前时间*/
                let time2 = new Date()
                time2.setTime(time2.getTime() - (24 * 60 * 60 * 1000 * count))
                let Y2 = time2.getFullYear()
                let M2 = ((time2.getMonth() + 1) > 10 ? (time2.getMonth() + 1) : '0' + (time2.getMonth() + 1))
                let D2 = (time2.getDate() > 10 ? time2.getDate() : '0' + time2.getDate())
                let timer2 = Y2 + '-' + M2 + '-' + D2 // 之前的7天或者30天  开始时间
                return {
                    //t1: timer1,
                    t2: timer2
                }
            },
            getbegaindate(val) {
                this.begaindate = val;
            },
            getenddate(val) {
                this.enddate = val;
            },
            gettongji(val,role) {
                let self = this;
                if(role=='guanli'){
                    let user = JSON.parse(Cookies.get("userInfo"));
                    let ids = user.companyId;
                    self.$http.httpGet('/eim/api/pilot/search/company/statisticByauthorityId', {
                        authorityId: ids,
                        page: val,
                        pageSize: self.pagesize,
                        begaindata: self.begaindate,
                        enddate: self.enddate,
                        type: self.licenseclass
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            for (var i = 0; i < result.data.length; i++) {
                                if (result.data[i].flySumTime == null) {
                                    result.data[i].flySumTime = 0;
                                }
                                else {
                                    result.data[i].flySumTime = parseInt(result.data[i].flySumTime) / 3600;
                                    result.data[i].flySumTime = result.data[i].flySumTime.toFixed(2);
                                }
                            }
                            self.tableData = result.data;
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                    self.$http.httpGet('/eim/api/pilot/search/company/morecountByauthorityId', {
                        authorityId: ids,
                        begaindata: self.begaindate,
                        enddate: self.enddate,
                        type: self.licenseclass
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            self.pagecount = result.data;
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                }else if(role == 'jianguan'){

                    self.$http.httpGet('/eim/api/pilot/search/company/statisticBysupervisionId', {
                        supervisionId: self.formItem.superv,
                        page: val,
                        pageSize: self.pagesize,
                        begaindata: self.begaindate,
                        enddate: self.enddate,
                        type: self.licenseclass
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            for (var i = 0; i < result.data.length; i++) {
                                if (result.data[i].flySumTime == null) {
                                    result.data[i].flySumTime = 0;
                                }
                                else {
                                    result.data[i].flySumTime = parseInt(result.data[i].flySumTime) / 3600;
                                    result.data[i].flySumTime = result.data[i].flySumTime.toFixed(2);
                                }
                            }
                            self.tableData = result.data;
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                    self.$http.httpGet('/eim/api/pilot/search/company/morecountBysupervisionId', {
                        supervisionId: self.formItem.superv,
                        begaindata: self.begaindate,
                        enddate: self.enddate,
                        type: self.licenseclass
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            self.pagecount = result.data;
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                }else if(role="company"){
                    self.$http.httpGet('/eim/api/pilot/search/company/statistic', {
                        companyId: this.formItem.company,
                        page: val,
                        pageSize: self.pagesize,
                        begaindata: self.begaindate,
                        enddate: self.enddate,
                        type: self.licenseclass
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            for (var i = 0; i < result.data.length; i++) {
                                if (result.data[i].flySumTime == null) {
                                    result.data[i].flySumTime = 0;
                                }
                                else {
                                    result.data[i].flySumTime = parseInt(result.data[i].flySumTime) / 3600;
                                    result.data[i].flySumTime = result.data[i].flySumTime.toFixed(2);
                                }
                            }
                            self.tableData = result.data;
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                    self.$http.httpGet('/eim/api/pilot/search/company/morecount', {
                        //companyId: profile.buz.companyId,
                        companyId: this.formItem.company,
                        begaindata: self.begaindate,
                        enddate: self.enddate,
                        type: self.licenseclass
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            self.pagecount = result.data;
                            //alert(self.pagecount);
//                        self.tableData.flySumTime=self.tableData.flySumTime/3600;
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                }

            },
            changePage(val) {
                let role;
                if(this.formItem.superv ==""||typeof(this.formItem.superv) == "undefined"){
                    role="guanli";
                }
                if(this.formItem.superv!=''&&(this.formItem.company ==""||typeof(this.formItem.company) == "undefined")){
                    role="jianguan";
                }
                if(this.formItem.company !=''){
                    role="company";
                }

                this.gettongji(val,role)
            },
            initData() {
                const option = {
                    title: {text: '飞行员飞行时间统计分析',textStyle:{fontSize:14},x:'center' },
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
                            data: this.xData,
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
                            name: '飞行总时长',
//                            min: 0,
//                            max: 1000,
//                            interval: 100,
                            axisLabel: {
                                formatter: '{value} 小时'
                            },
                            axisLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)'
                                }
                            },

                        },
                        {
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
                        }
                    ],
                    series: [
                        {
                            name: '飞行时长',
                            type: 'bar',
                            barWidth: 20,
                            yAxisIndex: 0,
                            itemStyle: {normal: {color: '#0295FD'}},
                            data: this.yData
                        },
                        {
                            name: '违规次数',
                            type: 'line',
                            yAxisIndex: 1,
                            itemStyle: {normal: {color: '#0295FD'}},
                            data: this.zData
                        }
                    ]
                };
                const option1 = ({
                    title: {
                        text: '企业的各个职位员工分布图',
                        textStyle:{fontSize:14},
                        x:'center',
                        y:'top',
                        textAlign:'left',
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    color: ['#61a0a8', '#d48265', '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074', '#546570'],

                    series: [{
                        name: '种类',
                        type: 'pie',
                        // 内圆半径，外圆半径
                        radius: [30, 100],
                        // 位置，左右，上下
                        center: ['50%', '50%'],
//                        roseType: 'area',
                        data: this.opinionData1, // 别忘了this
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }

                    }]
                });
                const option2 = ({
                    title: {
                        text: '飞行员执照类型分布图',
                        textStyle:{fontSize:14},
                        x:'center',
                        y:'top',
                        textAlign:'left',
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    color: ['#61a0a8', '#d48265', '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074', '#546570'],
                    series: [{
                        name: '种类',
                        type: 'pie',
                        // 内圆半径，外圆半径
                        radius: [30, 100],
                        // 位置，左右，上下
                        center: ['50%', '50%'],
//                        roseType: 'area',
                        data: this.opinionData3, // 别忘了this
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }

                    }]
                });

                this.myChart.setOption(option);
                this.myChart1.setOption(option1);
                this.myChart2.setOption(option2);
            },
            getData1(role) {
                let self = this;
                let data = {};
                let companyId;
                let url = '/eim/api/employee';

                if (role == "company") {
                    companyId = this.formItem.company;
                    url += '/search/findAllEmployeeBycompanyId';
                    data.companyId = companyId;
                    data.page = 1;
                    data.pageSize = 1000;
                    self.$http.httpGet(url, data).then((res) => {
                        let result = res.data;
                        if (res.data.resultCode == 200) {

                            let result = res.data.data;
                            for (let i = 0; i < result.length; i++) {
                                if (result[i][1] == "飞行人员") {
                                    let map = {name: '飞行人员', value: result[i][0]};
                                    self.opinionData.push(map);
                                }
                                if (result[i][1] == "航务人员") {
                                    let map1 = {name: '航务人员', value: result[i][0]};
                                    self.opinionData.push(map1);
                                }
                                if (result[i][1] == "飞行学员") {
                                    let map2 = {name: '飞行学员', value: result[i][0]};
                                    self.opinionData.push(map2);
                                }
                                if (result[i][1] == "机务人员") {
                                    let map3 = {name: '机务人员', value: result[i][0]};
                                    self.opinionData.push(map3);
                                }
                                if (result[i][1] == "其他人员") {
                                    let map4 = {name: '其他人员', value: result[i][0]};
                                    self.opinionData.push(map4);
                                }
                            }
                            console.log(self.opinionData);
                        }
                        else {
                            console.log(result.message)
                        }
                    })

                }
                else if (role == "jianguan") {
                    //监管局
                    url += '/search/findEmployeeAndcountBysupId';
                    data.supervisionId = this.formItem.superv;
                    data.page = 1;
                    data.pageSize = 1000;
                    self.$http.httpGet(url, data).then((res) => {
                        let result = res.data;
                        if (res.data.resultCode == 200) {

                            let result = res.data.data;
                            for (let i = 0; i < result.length; i++) {
                                if (result[i][1] == "飞行人员") {
                                    let map = {name: '飞行人员', value: result[i][0]};
                                    self.opinionData.push(map);
                                }
                                if (result[i][1] == "航务人员") {
                                    let map1 = {name: '航务人员', value: result[i][0]};
                                    self.opinionData.push(map1);
                                }
                                if (result[i][1] == "飞行学员") {
                                    let map2 = {name: '飞行学员', value: result[i][0]};
                                    self.opinionData.push(map2);
                                }
                                if (result[i][1] == "机务人员") {
                                    let map3 = {name: '机务人员', value: result[i][0]};
                                    self.opinionData.push(map3);
                                }
                                if (result[i][1] == "其他人员") {
                                    let map4 = {name: '其他人员', value: result[i][0]};
                                    self.opinionData.push(map4);
                                }
                            }
                            console.log(self.opinionData);
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                }

                else if(role == "guanli"){
                    //管理局
                    let user = JSON.parse(Cookies.get("userInfo"));
                    let ids = user.companyId;
                    url += '/search/findEmployeeAndcountByautid';
                    data.authorityId = ids;
                    data.page = 1;
                    data.pageSize = 1000;
                    self.$http.httpGet(url, data).then((res) => {
                        let result = res.data;
                        if (res.data.resultCode == 200) {

                            let result = res.data.data;
                            for (let i = 0; i < result.length; i++) {
                                if (result[i][1] == "飞行人员") {
                                    let map = {name: '飞行人员', value: result[i][0]};
                                    self.opinionData.push(map);
                                }
                                if (result[i][1] == "航务人员") {
                                    let map1 = {name: '航务人员', value: result[i][0]};
                                    self.opinionData.push(map1);
                                }
                                if (result[i][1] == "飞行学员") {
                                    let map2 = {name: '飞行学员', value: result[i][0]};
                                    self.opinionData.push(map2);
                                }
                                if (result[i][1] == "机务人员") {
                                    let map3 = {name: '机务人员', value: result[i][0]};
                                    self.opinionData.push(map3);
                                }
                                if (result[i][1] == "其他人员") {
                                    let map4 = {name: '其他人员', value: result[i][0]};
                                    self.opinionData.push(map4);
                                }
                            }
                            console.log(self.opinionData);
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                }

            },
            getnumber(val) {
                for (var i = 0; i < 3; i++) {
                    if (val[i] == val.max()) {
                        return i
                    }
                }
            },
            getData2(role) {
                let self = this;
                //let role = "company";
                let data = {};
                let companyId;
                let url = '/eim/api/employee';
                if (role == "company") {
                    //companyId = profile.buz.companyId;
                    companyId = this.formItem.company;
                    url += '/search/getPilotLicenseTypeCount';
                    data.companyId = companyId;
                    data.page = 1;
                    data.pageSize = 1000;
                    self.$http.httpGet(url, data).then((res) => {
                        let result = res.data;
                        if (res.data.resultCode == 200) {
                            let result = res.data.data;
                            console.log(result)
                            for (let i = 0; i < result.length; i++) {
                                if (result[i][1] == "学生驾驶员") {
                                    let map = {name: '学生驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map);
                                }
                                if (result[i][1] == "运动驾驶员") {
                                    let map1 = {name: '运动驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map1);
                                }
                                if (result[i][1] == "私用驾驶员") {
                                    let map2 = {name: '私用驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map2);
                                }
                                if (result[i][1] == "商用驾驶员") {
                                    let map3 = {name: '商用驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map3);
                                }
                                if (result[i][1] == "多人制机组驾驶员") {
                                    let map4 = {name: '多人制机组驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map4);
                                }
                                if (result[i][1] == "航线运输驾驶员") {
                                    let map5 = {name: '航线运输驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map5);
                                }
                            }

                        }
                        else {
                            console.log(result.message)
                        }
                    })

                }
                else if (role == "jianguan") {
                    url += '/search/getPilotLicenseTypeCountBysupervisionId';
                    data.supervisionId = this.formItem.superv;
                    data.page = 1;
                    data.pageSize = 1000;
                    self.$http.httpGet(url, data).then((res) => {
                        let result = res.data;
                        if (res.data.resultCode == 200) {
                            let result = res.data.data;
                            console.log(result)
                            for (let i = 0; i < result.length; i++) {
                                if (result[i][1] == "学生驾驶员") {
                                    let map = {name: '学生驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map);
                                }
                                if (result[i][1] == "运动驾驶员") {
                                    let map1 = {name: '运动驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map1);
                                }
                                if (result[i][1] == "私用驾驶员") {
                                    let map2 = {name: '私用驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map2);
                                }
                                if (result[i][1] == "商用驾驶员") {
                                    let map3 = {name: '商用驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map3);
                                }
                                if (result[i][1] == "多人制机组驾驶员") {
                                    let map4 = {name: '多人制机组驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map4);
                                }
                                if (result[i][1] == "航线运输驾驶员") {
                                    let map5 = {name: '航线运输驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map5);
                                }
                            }
                        }
                        else {
                            console.log(result.message)
                        }
                    })

                }
                else if(role == "guanli"){
                    url += '/search/getPilotLicenseTypeCountByauthorityId';
                    let user = JSON.parse(Cookies.get("userInfo"));
                    let ids = user.companyId;
                    data.authorityId = ids;
                    data.page = 1;
                    data.pageSize = 1000;
                    self.$http.httpGet(url, data).then((res) => {
                        let result = res.data;
                        if (res.data.resultCode == 200) {
                            let result = res.data.data;
                            console.log(result)
                            for (let i = 0; i < result.length; i++) {
                                if (result[i][1] == "学生驾驶员") {
                                    let map = {name: '学生驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map);
                                }
                                if (result[i][1] == "运动驾驶员") {
                                    let map1 = {name: '运动驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map1);
                                }
                                if (result[i][1] == "私用驾驶员") {
                                    let map2 = {name: '私用驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map2);
                                }
                                if (result[i][1] == "商用驾驶员") {
                                    let map3 = {name: '商用驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map3);
                                }
                                if (result[i][1] == "多人制机组驾驶员") {
                                    let map4 = {name: '多人制机组驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map4);
                                }
                                if (result[i][1] == "航线运输驾驶员") {
                                    let map5 = {name: '航线运输驾驶员', value: result[i][0]};
                                    self.opinionData2.push(map5);
                                }
                            }
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                }

            },

            //获取飞行总时长
            getData(val) {
                let self = this;
                let role = "company";
                let data = {}
                let url = '/app/api/pilot';
                let companyId;
                if (role == "company") {
                    self.$profile.getProfile().then((profile) => {
                        companyId = profile.buz.companyId;
                        url += '/search/company/more';
                        data.companyId = companyId;
                        data.page = val;
                        data.pageSize = 10;
                        self.$http.httpGet(url, data).then((res) => {
                            let result = res.data;
                            if (result.resultCode == 200) {

                                for (var i = 0; i < result.data.length; i++) {
                                    if (result.data[i].flySumTime == null) {
                                        result.data[i].flySumTime = 0;

                                    }
                                    else {
                                        result.data[i].flySumTime = parseInt(result.data[i].flySumTime) / 3600;
                                        result.data[i].flySumTime = result.data[i].flySumTime.toFixed(2);
                                    }
                                }
                                self.tableData = result.data;
                            }
                            else {
                                console.log(result.message)
                            }
                        })
                        self.$http.httpGet('/app/api/pilot/search/company/morecount', {
                            companyId: profile.buz.companyId,
                        }).then((res) => {
                            let result = res.data;
                            if (result.resultCode == 200) {
                                self.pagecount = result.data;
//                        self.tableData.flySumTime=self.tableData.flySumTime/3600;
                            }
                            else {
                                console.log(result.message)
                            }
                        })
                    }, (error) => {
                        console.log('error', error);
                    });

                }
                else if (role == "supersion") {
                    let supersionId = 1;
                    url += '/search/supersion/more';
                    data.supersionId = supersionId;
                    data.page = 1;
                    data.pageSize = 1000;
                }


            },

            handleCurrentChange(val) {
                this.currentPage = val;
                this.offset = (val - 1) * this.limit;
            }
        },
        watch: {
            tableData: function (val) {
                this.initData()
            },
            opinionData: function (val) {
                this.initData()
            },
            opinionData2: function (val) {
                this.initData()
            }
        }
    }
</script>
<style>
    .checkbox input[type='checkbox'] {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: 2;
        opacity: 0;
    }

    .checkbox {
        width: 15px;
        height: 15px;
        border: 1px solid #ababab;
        position: relative;
        border-radius: 1px;
        display: inline-block;
        vertical-align: middle;
    }

    .tit {
        position: relative;
    }

    .tit {
        padding: 5px 7px 5px 8px;
        border: 1px solid #d6d6d6;
        font-size: 14px;
        color: rgba(255, 255, 255, .85);
        border-radius: 1px;
    }

    .div-heard-1 {
        width: 1009px;
        margin-bottom: 10px;
    }

    .topBar li {
        padding-right: 35px;
        height: 60px;
        background: url(../../../../static/images/topBarright.png) center right no-repeat;
    }

    .topBar {
        display: flex;
        justify-content: space-between;
    }

    .topBar li label.item {
        margin: 0 5px;
        font-size: 16px;
        color: #fff;
        font-style: italic;
        line-height: 60px;
        vertical-align: middle;
    }

    .radio {
        width: 16px;
        height: 16px;
        border: 1px solid #fff;
        position: relative;
        border-radius: 50%;
        display: inline-block;
        vertical-align: middle;
    }

    .radio input[type='radio'] {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: 2;
        opacity: 0;
    }

    /*.div-list-employeestatic{*/
    /*width: 100%;*/
    /*height: 35px;*/
    /*background: #272727;*/
    /*font-size: 16px;*/
    /*color: white;*/
    /*padding-top: 7px;*/
    /*border: solid 1px #ececec;*/
    /*}*/
    .div-list-employeestatic {
        width: 980px;
        height: 34px;
        background-color: rgba(255, 255, 255, 0.65);
        border: 1px solid rgb(1, 107, 217);
        font-size: 14px;
        font-family: "Microsoft YaHei Regular", "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", Arial, sans-serif;
        color: rgb(1, 107, 217);
        margin-bottom: 4px;
    }

    .div-list-employeestatic .logoimgai {
        width: 34px;
        float: left;
        vertical-align: middle;
    }

    .name-text {
        float: left;
        margin-left: 16px;
        line-height: 34px
    }

    .name-img {
        float: right;
        margin-right: 11px;
    }
</style>