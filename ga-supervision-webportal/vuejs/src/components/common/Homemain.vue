<template>
    <div class="container_div" style="background-color: #EFEFEF">

        <div  class="clearfix" style="width: 100%;">
            <div style="width: 18%;height:78px;float:left;margin-left: 5px">
                <div class="photo" style="background:url('../../../static/images/监管企业.png') no-repeat;"><span style="line-height: 110px;color: white;">监管企业</span></div>
                <div class="intro">
                    <h6 style="color: blue;height: 60%;font-size: 23px;font-weight:bold;line-height: 47px">{{qycount}}</h6>
                    <p style="color: #9ea7b4;height: 40%">企业总数</p>
                </div>
            </div>

            <div style="width: 18%;height:78px;float:left;margin-left: 20px">
                <div class="photo" style="background:url('../../../static/images/人员.png') no-repeat;"><span style="line-height: 110px;color: white;">企业员工</span></div>
                <div class="intro">
                    <h6 style="color: blue;height: 60%;font-size: 23px;font-weight:bold;line-height: 47px">{{rycount}}</h6>
                    <p style="color: #9ea7b4;height: 40%">员工总数</p>
                </div>
            </div>

            <div style="width: 18%;height:78px;float:left;margin-left: 20px">
                <div class="photo" style="background:url('../../../static/images/飞机.png') no-repeat;"><span style="line-height: 110px;color: white;">航空器</span></div>
                <div class="intro">
                    <h6 style="color: blue;height: 60%;font-size: 23px;font-weight:bold;line-height: 47px">{{hkqcount}}</h6>
                    <p style="color: #9ea7b4;height: 40%">航空器总数</p>
                </div>
            </div>

            <div style="width: 18%;height:78px;float:left;margin-left: 20px">
                <div class="photo" style="background:url('../../../static/images/训练器.png') no-repeat;"><span style="line-height: 110px;color: white;">训练器</span></div>
                <div class="intro">
                    <h6 style="color: blue;height: 60%;font-size: 23px;font-weight:bold;line-height: 47px">{{xlqcount}}</h6>
                    <p style="color: #9ea7b4;height: 40%">模拟训练器总数</p>
                </div>
            </div>

            <div style="width: 18%;height:78px;float:left;margin-left: 20px">
                <div class="photo" style="background:url('../../../static/images/飞行时长.png') no-repeat;"><span style="line-height: 110px;color: white;">飞行时长</span></div>
                <div class="intro">
                    <h6 style="color: blue;height: 60%;font-size: 23px;font-weight:bold;line-height: 47px">{{zcount}}/{{ycount}}</h6>
                    <p style="color: #9ea7b4;height: 40%">总时长/本月时长</p>
                </div>
            </div>
        </div>
        <hr style="width: 100%;margin-top: 30px;"/>
        <div  style="width: 100%;margin-top: 30px;">
              <div class="clearfix" style="width: 100%;height: 504px">
                  <div  style="width: 23%;float: left;height: 100%">
                      <div style="width: 100%;height: 100%">
                          <div style="width: 100%;background-color: #0e7dc9;height: 45px;text-align: center"><span style="line-height: 45px;color: white;font-size: 20px;font-weight:bold;">企业类别统计</span></div>
                          <div class= "introsv" style="width: 100%;">
                              <Table   :columns="columns1" :data="company"></Table>
                              <Table  :columns="columns2" :data="company1"></Table>
                          </div>
                      </div>
                  </div>
                  <div   class="clearfix" style="width: 77%;float: left;padding-left: 30px;height: 100%;">
                      <div style="height: 50px;width:100%;">
                          <span style="width:70px;float: left;font-size: 14px;line-height:40px;margin-left: 0px;">开始日期&nbsp;</span><DatePicker class="intros" type="date" placeholder="选择日期" style="float: left" v-model="startDate"></DatePicker>
                          <span style="width:70px;float: left;font-size: 14px;line-height:40px">结束日期&nbsp;</span><DatePicker class="intros" type="date" placeholder="选择日期" style="float: left" v-model="endDate"></DatePicker>
                          <Button style="width:60px;margin-top: 3px;margin-left: 30px;" type="primary" @click="gettongji()">查询</Button>
                          <Button style="width:100px;margin-top: 3px;"type="primary" @click="gettongjirecent30()">最近30天</Button>
                          <Button style="width:110px;margin-top: 3px;"type="primary" @click="gettongjirecent90()">最近3个月</Button>
                      </div>

                      <div  id="myChart"class="introsvs" style="width:100%;margin-top: 10px;"></div>
                  </div>
              </div>
            <div class="clearfix" style="width: 100%;height:413px;margin-top: 30px;">
                <div style="width: 23%;float: left;height:100%">
                    <div style="width: 100%;height: 100%">
                        <div style="width: 100%;background-color: #0e7dc9;height: 45px;text-align: center"><span style="line-height: 45px;color: white;font-size: 20px;font-weight:bold;">人员类型分布</span></div>
                        <div class= "introsv" style="width: 100%;">
                            <Table  :columns="columns" :data="employee"></Table>
                        </div>
                    </div>
                </div>
                <div style="width: 77%;height:100%;float: left;padding-left: 30px">
                    <div class="introsvsv" id="myChart1" style="width:100%;"></div>
                </div>
            </div>
            <div style="width: 100%;margin-top: 30px;height: 380px">
                <div style="width: 23%;float: left;height: 100%">
                    <div style="width: 100%;height: 100%">
                        <div style="width: 100%;background-color: #0e7dc9;height: 45px;text-align: center"><span style="line-height: 45px;color: white;font-size: 20px;font-weight:bold;">航空器型号统计</span></div>

                            <div id="pilotpie"class="ceher" style="width:100%;"></div>

                    </div>
                </div>
                <div style="width: 77%;float: left;padding-left: 30px;height: 100%">
                    <div id="pilotbar" style="width:100%;height: 100%"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
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
        name: "Homemain",
        data() {
            return {
                piedata:[],
                bardata:[],
                myChartData: [],
                myChartData1: [],
                startDate:'',
                endDate:'',
                qycount:0,
                rycount:0,
                hkqcount:0,
                xlqcount:0,
                zcount:0,
                ycount:0,
                companyIds:'',
                employee: [],
                company: [],
                company1: [],
                columns: [
                    {
                        title: '人员类型',

                        key: 'name',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.name
                                    }
                                }, params.row.name)
                            ]);

                        }
                    },{
                        title: '数量',
                        key: 'value',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.value
                                    }
                                }, params.row.value)
                            ]);

                        }

                    },
                ],

                columns1: [
                    {
                        title: '合格证类型',

                        key: 'name',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.name
                                    }
                                }, params.row.name)
                            ]);

                        }
                    },{
                        title: '数量',
                        key: 'value',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.value
                                    }
                                }, params.row.value)
                            ]);

                        }

                    },
                ],

                columns2: [
                    {
                        title: '经营类别',

                        key: 'name',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.name
                                    }
                                }, params.row.name)
                            ]);

                        }
                    },{
                        title: '数量',
                        key: 'value',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.value
                                    }
                                }, params.row.value)
                            ]);

                        }

                    },
                ],


            }
        },
        created() {
            let self = this;
            let users = JSON.parse(Cookies.get("userInfo"));
            let datas = JSON.parse(Cookies.get("profile"));
            let ids = users.companyId;
            let type = datas.buz.type;
           var companyIds1="";
            if(type==1){
                let authorityId = ids
                self.$http.httpGet('/eim/api/company/listbyautid/'+authorityId, {
                }).then(function (response) {
                    let datainfo = response.data;
                    self.qycount =datainfo.length
                    for(var i = 0;i<datainfo.length;i++){
                        companyIds1+=datainfo[i].companyId+","
                    }
                    self.companyIds = companyIds1.substring(0,companyIds1.length-1);
                }).catch(function (error) {
                    console.log(error);
                });
            }else{
                let supervisionId = ids;

                self.$http.httpGet('/eim/api/company/listbysupid/'+supervisionId, {


                }).then(function (response) {

                    let datainfo = response.data;
                    self.qycount =datainfo.length
                    for(var i = 0;i<datainfo.length;i++){
                        companyIds1+=datainfo[i].companyId+","
                    }
                    self.companyIds = companyIds1.substring(0,companyIds1.length-1);
                }).catch(function (error) {
                    console.log(error);
                });
            }

        },
        watch:{
              companyIds:function(val){
                 this.$nextTick(function () {
                this.getPsopen(val);
                this.getAisim(val);
                this.getFly(val);
                this.getCompny(val);
                this.gettongjirecent90();
               // this.initData();
                                });
              },
            myChartData: function (val) {
                this.initData()
            },
            myChartData1: function (val) {
                this.initData()
            },
            opinionpieData: function(val){
                this.initData()
            },
            opinionbarData: function(val){
                this.initData()
            }
        },
        mounted() {
            this.mybarChart = echarts.init(document.getElementById('pilotbar'));
            this.mypieChart = echarts.init(document.getElementById('pilotpie'));
            //企业飞行时间
            this.myChart = echarts.init(document.getElementById('myChart'));
            this.myChart1 = echarts.init(document.getElementById('myChart1'));
            this.initData();

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
            },
            myChartDataX: function () {
                let temp = [];
                for (let i = 0; i < this.myChartData.length; i++) {
                    temp.push(this.myChartData[i][0])
                }
                return temp;
            },
            myChartDataY: function () {
                let temp = [];
                for (let i = 0; i < this.myChartData.length; i++) {
                    temp.push(this.myChartData[i][1] / 3600)
                }
                return temp;
            },
            xData: function () {
                let temp = []
                for (let row of this.myChartData1) {
                    temp.push({value: row.username})
                }

                return temp;
            },
            yData: function () {
                let temp = []

                for (let row of this.myChartData1) {
                    temp.push(row.flySumTime/ 3600)
                }

                return temp;
            },
        },
        methods:{
            gettongji(){
                var d = new Date(this.startDate);
                this.startDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                var d = new Date(this.endDate);
                this.endDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                let val =this.companyIds;
                this.getMyChart(val);
                this.getMyChart1(val);
                this.getAri(val);
            },
            gettongjirecent30(){
                let date= this.timeForMat(30);
                this.startDate = date.t2;
                this.endDate = new Date();
                let val =this.companyIds;
                this.getMyChart(val);
                this.getMyChart1(val);
                this.getAri(val);
            },
            gettongjirecent90(){
                let date= this.timeForMat(90);
                this.startDate = date.t2;
                this.endDate = new Date();
                let val =this.companyIds;
                this.getMyChart(val);
                this.getMyChart1(val);
                this.getAri(val);


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
            getAri(val){
                let self = this;
                self.piedata = [];
                self.bardata = [];
                self.$http.httpGet('/eim/api/aircraft/search/findaircraftmodelbycompanyids', {companyids:val}).then((res) => {
                    let result = res.data;
                    for (let line of result)
                    {
                        let map={ name: line[0],value: line[1] };
                        self.piedata.push(map)
                    }
                });
                self.$http.httpGet('/eim/api/aircraft/search/findallaircraftflys', {
                    companyIds:val,
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
                }).catch(function (error) {
                    console.log(error);
               });
            },
            //通航企业飞行时间
            getMyChart(val) {
                let data = {}
                data.companyId = val
                data.beginDate = this.startDate
                data.endDate = this.endDate
                this.$http.httpGet('/eim/api/company/staticflights', data).then(res => {
                    this.myChartData = res.data;

                })
            },
            //人员飞行时间
            getMyChart1(val) {
                let data = {}
                data.companyId = val
                data.beginDate = this.startDate
                data.endDate = this.endDate
                this.$http.httpGet('/eim/api/pilot/search/company/statisticBycompanyId', data).then(res => {
                    this.myChartData1 = res.data.data;
                })
            },
            initData(){
                const pieoption = {
                    title : {
                        text: '航空器型号比例',
                        x:'center',
                        textStyle:{fontSize: 14}
                    },
                    backgroundColor:'#ffffff',
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
                        text: 'Top10各型号航空器飞行总时长',
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
                    backgroundColor:'#ffffff',
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
                            axisLabel:{
                                formatter: '{value}',

                            },

                            type : 'value'

                        }
                    ],
                    series : [
                        {
                            data:this.opinionbarData,
                            barWidth: '60%',
                            type: 'bar',
                            barMaxWidth: 20,
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
                //人员飞行时间
                const option1 = {
                    title: {text: 'Top10飞行员飞行时间统计分析',x:'center',textStyle:{fontSize:14} },
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
                        y2:29,
                    },
                    backgroundColor:'#ffffff',
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

                        }/*,
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
                        }*/
                    ],
                    series: [
                        {
                            name: '飞行时长',
                            type: 'bar',
                            barWidth: 20,
                            yAxisIndex: 0,
                            itemStyle: {normal: {color: '#0295FD'}},
                            data: this.yData
                        }/*,
                        {
                            name: '违规次数',
                            type: 'line',
                            yAxisIndex: 1,
                            itemStyle: {normal: {color: '#0295FD'}},
                            data: this.zData
                        }*/
                    ]
                };
                this.myChart1.setOption(option1);
              //通航企业飞行时间
                const option = {
                    title: {text: 'Top10通航企业飞行时间统计分析',x:'center',textStyle:{fontSize:14}},
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
                    backgroundColor:'#ffffff',
                    xAxis: [
                        {
                            type: 'category',
                            data: this.myChartDataX,
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
                            data: this.myChartDataY
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
                this.myChart.setOption(option);
            },
            getPsopen(val){
                let self = this;
                let count = 0;
                let count1 = 0;
                let count2 = 0;
                let count3 = 0;
                let count4 = 0;
                let count5 = 0;
                let count6 = 0;
                    self.$http.httpGet('/eim/api/employee/search/searchAll', {
                    companyId: val,
                }).then(function (response) {

                    let datainfo = response.data.data;
                        self.rycount =datainfo.length
                    for (let i = 0; i < datainfo.length; i++) {
                        if(datainfo[i].jobTitle!=null){
                            if (datainfo[i].jobTitle.indexOf("飞行人员") != -1 ) {
                               count++
                            }
                            if (datainfo[i].jobTitle.indexOf("维修人员") != -1 ) {
                                count1++
                            }
                            if (datainfo[i].jobTitle.indexOf("管制人员") != -1 ) {
                                count2++
                            }
                            if (datainfo[i].jobTitle.indexOf("气象人员") != -1 ) {
                                count3++
                            }
                            if (datainfo[i].jobTitle.indexOf("情报人员") != -1 ) {
                                count4++
                            }
                            if (datainfo[i].jobTitle.indexOf("飞行签派人员") != -1 ) {

                                count5++

                            }
                            if (datainfo[i].jobTitle.indexOf("行政人员") != -1 ) {
                                count6++
                            }
                        }


                    }
                        let map = {name: '飞行人员', value: count};
                        self.employee.push(map);
                        let map1 = {name: '维修人员', value: count1};
                        self.employee.push(map1);
                        let map2 = {name: '管制人员', value: count2};
                        self.employee.push(map2);
                        let map3 = {name: '气象人员', value: count3};
                        self.employee.push(map3);
                        let map4 = {name: '情报人员', value: count4};
                        self.employee.push(map4);
                        let map5 = {name: '飞行签派人员', value: count5};
                        self.employee.push(map5);
                        let map6 = {name: '行政人员', value: count6};
                        self.employee.push(map6);
                }).catch(function (error) {
                    console.log(error);
                });
            },
            getAisim(val){
                let self = this;
                self.$http.httpGet('/eim/api/aircraft/search/searchAll', {
                    companyId: val,
                }).then(function (response) {

                    let datainfo = response.data.data;

                    self.hkqcount =datainfo[0]
                    self.xlqcount =datainfo[1]
                }).catch(function (error) {
                    console.log(error);
                });
            },
            getFly(val){
                let self = this;
                self.$http.httpGet('/eim/api/aircraft/search/searchfly', {
                    companyId: val,
                }).then(function (response) {

                    let datainfo = response.data.data;


                    self.zcount =Math.round(datainfo[1]/3600*100)/100
                    self.ycount =Math.round(datainfo[0]/3600*100)/100
                }).catch(function (error) {
                    console.log(error);
                });
            },
            getCompny(val){
                let self = this;
                let count = 0;
                let count1 = 0;
                let count2 = 0;
                let count3 = 0;

                let countv = 0;
                let countv1 = 0;
                let countv2 = 0;
                let countv3 = 0;
                self.$http.httpGet('/eim/api/cq/companyqualificationstaticbycompanyIds', {
                    companyId: val,
                }).then(function (response) {

                    let datainfo = response.data;
                    console.log(JSON.stringify(datainfo))
                    for (let i = 0; i < datainfo.jylb.length; i++) {

                            if (datainfo.jylb[i].indexOf("1") != -1 ) {
                                count++
                            }
                            if (datainfo.jylb[i].indexOf("2") != -1 ) {
                                count1++
                            }
                            if (datainfo.jylb[i].indexOf("3") != -1 ) {
                                count2++
                            }
                            if (datainfo.jylb[i].indexOf("4") != -1 ) {
                                count3++
                            }

                    }
                    let map = {name: '甲类', value: count};
                    self.company1.push(map);
                    let map1 = {name: '乙类', value: count1};
                    self.company1.push(map1);
                    let map2 = {name: '丙类', value: count2};
                    self.company1.push(map2);
                    let map3 = {name: '飞行俱乐部', value: count3};
                    self.company1.push(map3);

                    for (let i = 0; i < datainfo.yxhgz.length; i++) {

                        if (datainfo.yxhgz[i][0]==91) {
                            countv = datainfo.yxhgz[i][1];

                        }
                        if (datainfo.yxhgz[i][0]==141 ) {
                            countv1 = datainfo.yxhgz[i][1];
                        }
                        if (datainfo.yxhgz[i][0]==121 ) {
                            countv2 = datainfo.yxhgz[i][1];
                        }
                        if (datainfo.yxhgz[i][0]==135 ) {
                            countv3 = datainfo.yxhgz[i][1];
                        }

                    }
                    let mapv = {name: '91', value: countv};
                    self.company.push(mapv);
                    let mapv1 = {name: '141', value: countv1};
                    self.company.push(mapv1);
                    let mapv2 = {name: '121', value: countv2};
                    self.company.push(mapv2);
                    let mapv3 = {name: '135', value: countv3};
                    self.company.push(mapv3);
                }).catch(function (error) {
                    console.log(error);
                });
            },
        }
    }
</script>

<style scoped>
    hr{/*渐变*/

        margin:0 auto;
        border: 0;
        height: 0;
        border-top: 1px solid rgba(0, 0, 0, 0.1);
        border-bottom: 1px solid rgba(255, 255, 255, 0.3);
    }


    .clearfix:after{
        content:".";
        display:block;
        height:0;
        clear:left;
        visibility:hidden;
    }

    .photo{
        text-align: center;
        height:100%;
        float:left;
        width:68px;
    }
    .intro{
        text-align: center;
        background-color: white;
        float:right;
        height:100%;
        width:-webkit-calc(100% - 68px);
        width:-moz-calc(100% - 68px);
        width:calc(100% - 68px);

    }
    .wrap {
   display: -webkit-box;
  }
    .ceher{
        min-height: calc(100% - 45px);

    }

    .intros{

        width:-webkit-calc(50% - 238px);
        width:-moz-calc(50% - 238px);
        width:calc(50% - 238px);

    }

    .introsv{

        width:-webkit-calc(100% - 60px);
        width:-moz-calc(100% - 60px);
        width:calc(100% - 60px);

    }

    .introsvs{
        min-height:-webkit-calc(100% - 60px);
        min-height:-moz-calc(100% - 60px);
        min-height:calc(100% - 60px);

    }

    .introsvsv{
        min-height:-webkit-calc(100% - 0px);
        min-height:-moz-calc(100% - 0px);
        min-height:calc(100% - 0px);

    }
</style>