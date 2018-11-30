<template>
    <div  class="container_div" style="height: auto">
        <Form :model="iform" :label-width="100" style="" >
            <FormItem label="所属监管局" style="width:350px;float: left;" >
                <Select  filterable clearable v-model="iform.supId" placeholder="请选择" @on-change="selectSupId" >
                    <Option v-for="site in supervision" :key="site.id" :value="site.id">{{ site.title }}</Option>
                </Select>
            </FormItem>

            <FormItem label="所属企业" style="width:350px;float: left;" >
                <Select filterable clearable v-model="iform.companyId" placeholder="请选择" ref="sss" @on-change="selectQy">
                    <Option v-for="site in supervision1" :key="site.companyId" :value="site.companyId">{{ site.name }}</Option>
                </Select>
            </FormItem>

        </Form>
        <div style="clear: both;"></div>
        <!--<div style="height: 300px;margin-bottom:50px;">
            <div style="margin-top:10px;margin-bottom:18px;border: 1px solid #a3a3a3;float: left;width:30%;">
                <div id="pilotpie" style="width:100%;height:300px;padding-top:10px;margin-bottom:18px;"></div>
            </div>
            <div style="margin-top:10px;margin-bottom:18px;border: 1px solid #a3a3a3;border-left:0px;float: right;width:70%;">
                <div id="pilotbar" style="width:100%;height:300px;padding-top:10px;margin-bottom:18px;"></div>
            </div>
        </div>-->
        <Row style="padding:10px">
            <Col span="11">
                <Card style="height:320px;">
                    <div style="margin-bottom:10px;width:100%;">
                        <div id="pilotpie" style="width:100%;height:300px;padding-top:10px;"></div>
                    </div>
                </Card>
            </Col>
            <Col span="11" offset="2">
                <Card style="height:320px;">
                    <div style="margin-bottom:10px;width:100%;">
                        <div id="pilotbar" style="width:100%;height:300px;padding-top:10px;"></div>
                    </div>
                </Card>
            </Col>
        </Row>
        <!--<Row style="height: 40px;">
        <Col span="6">
            <span style="float: left;font-size: 14px;line-height:40px;margin-left: 22px;">开始日期&nbsp;</span><DatePicker type="date" placeholder="选择日期" style="width: 260px;float: left" v-model="startDate"></DatePicker>
        </Col>
        <Col span="6">
            <span style="float: left;font-size: 14px;line-height:40px">结束日期&nbsp;</span><DatePicker type="date" placeholder="选择日期" style="width: 260px;float: left" v-model="endDate"></DatePicker>
        </Col>
        <Button style="margin-top: 3px" type="primary" @click="gettongji()">查询</Button>
        <Button style="margin-top: 3px"type="primary" @click="gettongjirecent30()">最近30天</Button>
        <Button style="margin-top: 3px"type="primary" @click="gettongjirecent90()">最近3个月</Button>
    </Row>-->
        <div>
            <!--<div class=" active div-list-aircraftdato" @click="open.showinfo = !open.showinfo">
                <div class="name" >
                    <div class="logoimgaircraft">
                        <img src="../../../assets/基本信息.png"  >
                    </div >
                    <div class="name-text">
                        飞行总时长详情
                    </div>
                    <div class="name-img">
                        <img v-if="open.showinfo" src="../../../assets/减号.png" style="margin-top: 15px">
                        <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                    </div>
                </div>
            </div>
            <Table v-show="open.showinfo" style="" :columns="columns6" :data="tabledata"></Table>
            <Page :total="pagecount" @on-change="pageclick" :page-size="pagesize" style="height: 50px;float:right;padding: 10px 35px 0 0;" ></Page>-->

            <Row style="padding:10px;">
                <Col span="24">
                    <Card style="height: auto ;padding:30px;">

                        <div style="height: 50px;margin-top: -25px;">
                                <span style="float: left;font-size: 14px;line-height:40px;margin-left: 0px;">开始日期&nbsp;</span><DatePicker type="date" placeholder="选择日期" style="width: 260px;float: left" v-model="startDate"></DatePicker>


                                <span style="float: left;font-size: 14px;line-height:40px">结束日期&nbsp;</span><DatePicker type="date" placeholder="选择日期" style="width: 260px;float: left" v-model="endDate"></DatePicker>

                            <Button style="margin-top: 3px;margin-left: 30px;" type="primary" @click="gettongji()">查询</Button>
                            <Button style="margin-top: 3px;"type="primary" @click="gettongjirecent30()">最近30天</Button>
                            <Button style="margin-top: 3px;"type="primary" @click="gettongjirecent90()">最近3个月</Button>
                        </div>
                        <div class=" active div-list-aircraftdato" @click="open.showinfo = !open.showinfo">
                            <div class="name" >
                                <div class="logoimgaircraft">
                                    <img src="../../../assets/基本信息.png"  >
                                </div >
                                <div class="name-text">
                                    飞行总时长详情
                                </div>
                                <div class="name-img">
                                    <img v-if="open.showinfo" src="../../../assets/减号.png" style="margin-top: 15px">
                                    <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                                </div>
                            </div>
                        </div>
                        <Table v-show="open.showinfo" style="" :columns="columns6" :data="tabledata"></Table>
                        <Page :total="pagecount" @on-change="pageclick" :page-size="pagesize" style="height: 50px;float:right;padding: 10px 35px 0 0;" ></Page>
                    </Card>
                </Col>
            </Row>
        </div>
    </div>
</template>

<script>
    import {
        findByParentId
    } from "@/api/index";
    import Cookies from 'js-cookie';
    // 引入 ECharts 主模块
    var echarts = require('echarts/lib/echarts');
    // 引入柱状图
    require('echarts/lib/chart/bar');
    require('echarts/lib/chart/line');
    // 引入饼图组件
    require('echarts/lib/chart/pie');
    // 引入提示框和标题组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');

    export default {
        data(){
            return {
                iform: {
                    supId: '',
                    companyId:'',
                },
                supervision:[],
                supervision1:[],
                columns6: [
                    {
                        title: "国籍登记证号",
                        key: "registernumber",
                        align: 'center'

                    },
                    {
                        title: "航空器类别",
                        key: "aircraftClass",
                        align: 'center'
                    },
                    {
                        title: "航空器型号",
                        key: "aircraftModel",
                        align: 'center'
                    },
                    {
                        title: "总计时长(h)",
                        key: "sumTime",
                        sortable: true,
                        align: 'center'
                    },
                    {
                        title: "所属公司",
                        key: "companyName",
                        align: 'center'
                    }
                ],
                pagecount:'',
                pagesize:10,
                startDate:'',
                endDate:'',
                tabledata: [],
                open:{
                    showinfo:true,
                },
                piedata:[],
                bardata:[],
            }
        },
        created(){
            this.gettongjirecent90();
            let users = JSON.parse(Cookies.get("userInfo"));
            let datas = JSON.parse(Cookies.get("profile"));
            let ids = users.companyId;
            let type = datas.buz.type;
            let title = datas.buz.title;
           if(type==1){
            findByParentId(ids).then(res => {

                if (res.success === true) {

                    this.supervision = res.result;

                }
            });
           }else{
               this.supervision = [{"title":title,"id":ids}]
           }

        },
        computed: {
            opinionpieData: function (){
                let temp = [];
                for (let row of this.piedata) {
                    temp.push(row)
                }
                return temp;
            },
            opinionbarData: function (){
                let temp = [];
                for (let row of this.bardata) {
                    temp.push(row)
                }
                return temp;
            },
            xdata: function(){
                let temp = [];
                for (let row of this.bardata) {
                    temp.push(row.name)
                }
                return temp;
            },
            ydata: function () {
                let temp = [];
                for (let row of this.bardata) {
                    temp.push(row)
                }
                return temp;
            }

        },
        mounted(){
            this.mybarChart = echarts.init(document.getElementById('pilotbar'));
            this.mypieChart = echarts.init(document.getElementById('pilotpie'));
            this.initData();
        },
        methods: {
            selectQy(){
                this.gettongji();
            },
            selectSupId(){
                this.$refs.sss.reset();
                this.iform.companyId="";
                let self = this;
                self.supervision1=[];
                let supervisionId = this.iform.supId

               if(typeof (supervisionId)!="undefined"&&supervisionId!=""){


                self.$http.httpGet('/eim/api/company/listbysupid/'+supervisionId, {


                }).then(function (response) {

                    let datainfo = response.data;

                    self.supervision1 = datainfo;

                }).catch(function (error) {
                    console.log(error);
                });
               }
                this.gettongji();
            },
            gettongji(){
                var d = new Date(this.startDate);
                this.startDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                var d = new Date(this.endDate);
                this.endDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                this.getoperationdata();
                this.gettabledata(1);
            },
            gettongjirecent30(){
                let date= this.timeForMat(30);
                this.startDate = date.t2;
                this.endDate = new Date();
                this.getoperationdata();
                this.gettabledata(1);
            },
            gettongjirecent90(){
                let date= this.timeForMat(90);
                this.startDate = date.t2;
                this.endDate = new Date();
                this.getoperationdata();
                this.gettabledata(1);
            },
            timeForMat(count){
                let time1 = new Date()
                time1.setTime(time1.getTime() - (24 * 60 * 60 * 1000))
                let Y1 = time1.getFullYear()
                let M1 = ((time1.getMonth() + 1) > 10 ? (time1.getMonth() + 1) : '0' + (time1.getMonth() + 1))
                let D1 = (time1.getDate() > 10 ? time1.getDate() : '0' + time1.getDate())
                let timer1 = Y1 + '-' + M1 + '-' + D1 // 当前时间
                let time2 = new Date()
                time2.setTime(time2.getTime() - (24 * 60 * 60 * 1000 * count))
                let Y2 = time2.getFullYear()
                let M2 = ((time2.getMonth() + 1) >= 10 ? (time2.getMonth() + 1) : '0' + (time2.getMonth() + 1))
                let D2 = (time2.getDate() > 10 ? time2.getDate() : '0' + time2.getDate())
                let timer2 = Y2 + '-' + M2 + '-' + D2 // 之前的7天或者30天
                return {
                    t1: timer1,
                    t2: timer2
                }
            },
            initData(){
                const pieoption = {
                    title : {
                        text: '航空器型号比例',
                        x:'center',
                        textStyle:{fontSize: 14}
                    },
                    color:['#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570'],
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    series : [
                        {
                            name: '数量',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '50%'],
                            data: this.opinionpieData,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                const baroption=({
                    title : {
                        text: '各型号航空器飞行总时长',
                        x:'center',
                        textStyle:{fontSize: 14}
                    },
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '3%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',

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
                            },
                            data : this.xdata,
                            axisTick: {
                                alignWithLabel: true
                            },
                            padding:[0,-20,0,0]
                        }
                    ],
                    yAxis : [
                        {
                            name:'飞行总时长',
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
                            axisLabel:{
                                formatter: '{value} 小时',

                            },

                            type : 'value'

                        }
                    ],
                    series : [
                        {
                            data:this.opinionbarData,
                            barWidth: '60%',
                            type: 'bar',
                            barMaxWidth: 50,
                            itemStyle: {
                                normal: {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#83bff6'},
                                            {offset: 0.5, color: '#188df0'},
                                            {offset: 1, color: '#188df0'}
                                        ]
                                    )
                                },
                                emphasis: {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#2378f7'},
                                            {offset: 0.7, color: '#2378f7'},
                                            {offset: 1, color: '#83bff6'}
                                        ]
                                    )
                                }
                            },
                        }
                    ]

                })
                this.mypieChart.setOption(pieoption);
                this.mybarChart.setOption(baroption);
            },
            getoperationdata () {
                    let self = this;
                if(self.iform.companyId ==""||typeof(self.iform.companyId) == "undefined"){
                    var companyIds = "";
                    let users = JSON.parse(Cookies.get("userInfo"));
                    let datas = JSON.parse(Cookies.get("profile"));
                    let ids = users.companyId;
                    let type = datas.buz.type;
                    if(self.iform.supId ==""||typeof(self.iform.supId) == "undefined"){
                        if(type==1){
                            let authorityId = ids
                            self.$http.httpGet('/eim/api/company/listbyautid/'+authorityId, {
                            }).then(function (response) {
                                let datainfo = response.data;
                                for(var i = 0;i<datainfo.length;i++){
                                    companyIds+=datainfo[i].companyId+","
                                }
                                companyIds = companyIds.substring(0,companyIds.length-1);
                                let data = {};
                                self.piedata = [];
                                self.bardata = [];
                                self.$http.httpGet('/eim/api/aircraft/search/findaircraftmodelbycompanyids', {companyids:companyIds}).then((res) => {
                                    let result = res.data;
                                    for (let line of result)
                                    {
                                        let map={ name: line[0],value: line[1] };
                                        self.piedata.push(map)
                                    }
                                });
                                self.$http.httpGet('/eim/api/aircraft/search/findallaircraftfly', {
                                    companyIds:companyIds,
                                    startDate:self.startDate,
                                    endDate:self.endDate
                                }).then((res) =>{
                                    let result = res.data.data;
                                    let hour;
                                    for (let line of result)
                                    {
                                        hour = Math.round(line[1]/3600*100)/100
                                        let map={ name: line[0],value: hour };
                                        self.bardata.push(map)
                                    }
                                })

                            }).catch(function (error) {
                                console.log(error);
                            });
                        }else{
                            let supervisionId = ids;

                            self.$http.httpGet('/eim/api/company/listbysupid/'+supervisionId, {


                            }).then(function (response) {

                                let datainfo = response.data;
                                for(var i = 0;i<datainfo.length;i++){
                                    companyIds+=datainfo[i].companyId+","
                                }
                                companyIds = companyIds.substring(0,companyIds.length-1);
                                let data = {};
                                self.piedata = [];
                                self.bardata = [];
                                self.$http.httpGet('/eim/api/aircraft/search/findaircraftmodelbycompanyids', {companyids:companyIds}).then((res) => {
                                    let result = res.data;
                                    for (let line of result)
                                    {
                                        let map={ name: line[0],value: line[1] };
                                        self.piedata.push(map)
                                    }
                                });
                                self.$http.httpGet('/eim/api/aircraft/search/findallaircraftfly', {
                                    companyIds:companyIds,
                                    startDate:self.startDate,
                                    endDate:self.endDate
                                }).then((res) =>{
                                    let result = res.data.data;
                                    let hour;
                                    for (let line of result)
                                    {
                                        hour = Math.round(line[1]/3600*100)/100
                                        let map={ name: line[0],value: hour };
                                        self.bardata.push(map)
                                    }
                                })

                            }).catch(function (error) {
                                console.log(error);
                            });
                        }
                    }else{
                        let supervisionId = self.iform.supId;


                        self.$http.httpGet('/eim/api/company/listbysupid/'+supervisionId, {


                        }).then(function (response) {

                            let datainfo = response.data;
                            for (var i = 0; i < datainfo.length; i++) {

                                companyIds += datainfo[i].companyId + ","
                            }
                            companyIds = companyIds.substring(0, companyIds.length - 1);
                            let data = {};
                            self.piedata = [];
                            self.bardata = [];
                            self.$http.httpGet('/eim/api/aircraft/search/findaircraftmodelbycompanyids', {companyids: companyIds}).then((res) => {
                                let result = res.data;
                                for (let line of result) {
                                    let map = {name: line[0], value: line[1]};
                                    self.piedata.push(map)
                                }
                            });
                            self.$http.httpGet('/eim/api/aircraft/search/findallaircraftfly', {
                                companyIds: companyIds,
                                startDate: self.startDate,
                                endDate: self.endDate
                            }).then((res) => {
                                let result = res.data.data;
                                let hour;
                                for (let line of result) {
                                    hour = Math.round(line[1] / 3600 * 100) / 100
                                    let map = {name: line[0], value: hour};
                                    self.bardata.push(map)
                                }
                            })
                        })
                    }


                }else{
                    var companyIds = self.iform.companyId;
                    let data = {};
                    self.piedata = [];
                    self.bardata = [];
                    self.$http.httpGet('/eim/api/aircraft/search/findaircraftmodelbycompanyids', {companyids:companyIds}).then((res) => {
                        let result = res.data;
                        for (let line of result)
                        {
                            let map={ name: line[0],value: line[1] };
                            self.piedata.push(map)
                        }
                    });
                    self.$http.httpGet('/eim/api/aircraft/search/findallaircraftfly', {
                        companyIds:companyIds,
                        startDate:self.startDate,
                        endDate:self.endDate
                    }).then((res) =>{
                        let result = res.data.data;
                        let hour;
                        for (let line of result)
                        {
                            hour = Math.round(line[1]/3600*100)/100
                            let map={ name: line[0],value: hour };
                            self.bardata.push(map)
                        }
                    })

                }

            },
            pageclick:function (obj) {
                this.gettabledata(obj);
            },
            gettabledata (obj) {
                    let self = this;

                if(self.iform.companyId ==""||typeof(self.iform.companyId) == "undefined"){
                    var companyIds = "";
                    let users = JSON.parse(Cookies.get("userInfo"));
                    let datas = JSON.parse(Cookies.get("profile"));
                    let ids = users.companyId;
                    let type = datas.buz.type;
                    if(self.iform.supId ==""||typeof(self.iform.supId) == "undefined"){
                        if(type==1){
                            let authorityId = ids

                            self.$http.httpGet('/eim/api/company/listbyautid/'+authorityId, {


                            }).then(function (response) {

                                let datainfo = response.data;
                                for(var i = 0;i<datainfo.length;i++){
                                    companyIds+=datainfo[i].companyId+","
                                }
                                companyIds = companyIds.substring(0,companyIds.length-1);
                                let url = '/eim/api/aircraft/search/findviewlisttable';

                                self.$http.httpGet(url, {
                                    pageno: obj-1,
                                    pagesize: self.pagesize,
                                    companyIds:companyIds,
                                    startDate:self.startDate,
                                    endDate:self.endDate,
                                }).then((res) => {
                                    let result = res.data.content
                                    self.pagecount = res.data.totalElements;
                                    for (let line of result){
                                        line.sumTime = Math.round(line.sumTime/3600*100)/100
                                        line.companyName = line.companyId.name;
                                    }
                                    self.tabledata = result;

                                })
                            }).catch(function (error) {
                                console.log(error);
                            });
                        }else{
                            let supervisionId = ids;

                            self.$http.httpGet('/eim/api/company/listbysupid/'+supervisionId, {


                            }).then(function (response) {

                                let datainfo = response.data;
                                for(var i = 0;i<datainfo.length;i++){

                                    companyIds+=datainfo[i].companyId+","
                                }
                                companyIds = companyIds.substring(0,companyIds.length-1);
                                let url = '/eim/api/aircraft/search/findviewlisttable';

                                self.$http.httpGet(url, {
                                    pageno: obj-1,
                                    pagesize: self.pagesize,
                                    companyIds:companyIds,
                                    startDate:self.startDate,
                                    endDate:self.endDate,
                                }).then((res) => {
                                    let result = res.data.content
                                    self.pagecount = res.data.totalElements;
                                    for (let line of result){
                                        line.sumTime = Math.round(line.sumTime/3600*100)/100
                                        line.companyName = line.companyId.name;
                                    }
                                    self.tabledata = result;
                                })
                            }).catch(function (error) {
                                console.log(error);
                            });
                        }
                    }else{
                        let supervisionId = self.iform.supId;


                        self.$http.httpGet('/eim/api/company/listbysupid/'+supervisionId, {


                        }).then(function (response) {

                            let datainfo = response.data;
                            for(var i = 0;i<datainfo.length;i++){

                                companyIds+=datainfo[i].companyId+","
                            }
                            companyIds = companyIds.substring(0,companyIds.length-1);
                            let url = '/eim/api/aircraft/search/findviewlisttable';

                            self.$http.httpGet(url, {
                                pageno: obj-1,
                                pagesize: self.pagesize,
                                companyIds:companyIds,
                                startDate:self.startDate,
                                endDate:self.endDate,
                            }).then((res) => {
                                let result = res.data.content
                                self.pagecount = res.data.totalElements;
                                for (let line of result){
                                    line.sumTime = Math.round(line.sumTime/3600*100)/100
                                    line.companyName = line.companyId.name;
                                }
                                self.tabledata = result;
                            })
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }


                }else{
                    var companyIds = self.iform.companyId;
                    let url = '/eim/api/aircraft/search/findviewlisttable';

                    self.$http.httpGet(url, {
                        pageno: obj-1,
                        pagesize: self.pagesize,
                        companyIds:companyIds,
                        startDate:self.startDate,
                        endDate:self.endDate,
                    }).then((res) => {
                        let result = res.data.content
                        self.pagecount = res.data.totalElements;
                        for (let line of result){
                            line.sumTime = Math.round(line.sumTime/3600*100)/100
                            line.companyName = line.companyId.name;
                        }
                        self.tabledata = result;
                    })
                }


            },

        },
        watch: {
            tabledata: function(val){
               this.initData()
            },
            opinionpieData: function(val){
                this.initData()
            },
            opinionbarData: function(val){
                this.initData()
            }
        }
    }
</script>
<style scoped>

    .div-list-aircraftdato{

        margin-top: 4px;
        height: 34px;
        background-color: rgba(255,255,255,0.65) ;
        border:1px solid #a3a3a3;
        font-size: 14px;
        font-family: "Microsoft YaHei Regular", "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", Arial, sans-serif;
        color: #a3a3a3;
        margin-bottom: 4px;
    }
    .div-list-aircraftdato .logoimgaircraft{
        width: 34px;
        float:left;
        vertical-align: middle;
    }
    .name-text{
        float:left;
        margin-left: 16px;
        line-height: 34px
    }
    .name-img{
        float: right;
        margin-right: 11px;
    }

</style>