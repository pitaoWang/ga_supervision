<template>
    <div style="margin-top: 30px; width: 1640px; height: 900px; background-color: #FFFFFF">
        <div class="row">
            <div class="mod">
                <div class="tit">
                    <div class="name"><i class="icon-font icon00"></i>事务处理</div>
                </div>
                <div class="cut">
                    <table>
                        <tbody>
                        <tr v-for="info in pendlist">
                            <td class="col01">{{info.processName}}</td>
                            <td>{{info.creator}}</td>
                            <td>{{info.createTime}}</td>
                        </tr>
                        <tr v-if="pendlist.length<1">
                            <td class="col01"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr v-if="pendlist.length<2">
                            <td class="col01"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr v-if="pendlist.length<3">
                            <td class="col01"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="mod">
                <div class="tit">
                    <div class="name"><i class="icon-font icon02"></i>公文处理</div>
                </div>
                <div class="cut">
                    <table>
                        <tbody>
                        <tr>
                            <td class="col03">发多少</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="col03"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="col03"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="mod">
                <div class="tit tit01">
                    <div class="name" @click="gopilotstatic()"><i class="icon-font icon04 refresh"></i>飞行员统计信息</div>
                    <div class="op">
                        <div class="refresh" @click="refreshchart()"></div>
                    </div>
                </div>
                <div class="cut">
                    <div id="pilotbar" style="width:100%;height:245px;margin: -20px 0 18px 0;"></div>
                </div>
            </div>
        </div>
        <div class="row fr">
            <div class="mod">
                <div class="tit">
                    <div class="name"><i class="icon-font icon01"></i>违规信息</div>
                </div>
                <div class="cut">
                    <table>
                        <tbody>
                        <tr>
                            <td class="col02"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="col02"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="col02"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="mod">
                <div class="tit">
                    <div class="name"><i class="icon-font icon03"></i>告警提示</div>
                </div>
                <div class="cut">
                    <table>
                        <tbody>
                        <tr>
                            <td class="col04"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="col04"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="col04"></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="mod">
                <div class="tit tit01">
                    <div class="name" @click="goaircraftstatic()"><i class="icon-font icon04 refresh"></i>航空器统计信息</div>
                    <div class="op">
                        <div  class="refresh" @click="refreshchart()"></div>
                    </div>
                </div>
                <div class="cut">
                    <div id="aircraftbar" style="width:100%;height:245px;margin: -20px 0 18px 0;"></div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    // 引入 ECharts 主模块
    var echarts = require('echarts/lib/echarts');
    // 引入柱状图
    require('echarts/lib/chart/bar');
    require('echarts/lib/chart/line');
    // 引入提示框和标题组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');

    export default {
        data: function(){
            return {
                aircraftbar:[],
                pilotbar:[],
                pendlist: [],
                startDate:'',
                endDate:'',
            }
        },
        created(){
            this.timeForMat(90);
            this.getoperationdata();
            this.getpilotdata();
            this.getpendlist();
        },
        computed:{
            opinionbarData: function (){
                let temp = [];
                for (let row of this.aircraftbar) {
                    temp.push(row)
                }
                return temp;
            },
            xdata: function(){
                let temp = [];
                for (let row of this.aircraftbar) {
                    temp.push(row.name)
                }
                return temp;
            },
            ydata: function () {
                let temp = [];
                for (let row of this.aircraftbar) {
                    temp.push(row)
                }
                return temp;
            },
            xpilotData: function () {
                let temp = []

                for (let row of this.pilotbar) {
                    temp.push(row.username)
                }
                return temp;
            },
            ypilotData: function () {
                let temp = []

                for (let row of this.pilotbar) {
                    temp.push(row.flySumTime)
                }

                return temp;
            },
            zpilotData: function () {
                let temp = []

                for (let row of this.pilotbar) {
                    temp.push(row.flySumIllegal)
                }

                return temp;
            },
        },
        mounted(){
            this.mybarChart = echarts.init(document.getElementById('aircraftbar'));
            this.pilotbarchart = echarts.init(document.getElementById('pilotbar'));
            this.initData();
        },

        methods:{
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
                this.endDate = timer1;
                this.startDate = timer2;
            },
            gopilotstatic(){
                let self = this;
                self.$router.push({path: '/employeestatic'});
            },
            goaircraftstatic(){
                let self = this;
                self.$router.push({path: '/aircraftoperation'});
            },
            refreshchart(){
                this.getoperationdata();
                this.getpilotdata();
            },
            initData(){
                const baroption=({
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },

                    grid: {
                        show:false,
                        left: '3%',
                        right: '3%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            axisLabel:{
                                color:'rgb(1,107,217)',
//                                interval:0 //数值为0的时候强制显示所有的标签
                            },
                            axisLine:{
                                lineStyle:{
                                    color:'rgb(1,107,217)'
                                }
                            },
                            data : this.xdata,
                            axisTick: {
                                alignWithLabel: true
                            },
                        }
                    ],
                    yAxis : [
                        {
                            name:'飞行总时长',
                            nameTextStyle:{
                                color:'rgb(1,107,217)'
                            },
                            axisLabel:{
                                formatter: '{value} 小时',
                                color:'rgb(1,107,217)'
                            },
                            axisLine:{
                                lineStyle:{
                                    color:'rgb(1,107,217)'
                                },
                            },
                            splitLine:{//网格线的格式
                                lineStyle:{
                                    color: '#000',//网格线颜色
                                    width: 1,//网格线宽度
                                    type: 'solid'//网格线样式

                                }
                            },
                            type : 'value'

                        }
                    ],
                    series : [
                        {
                            barMaxWidth: 50,
                            type: 'bar',
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
                            data: this.aircraftbar
                        }
                    ]

                });
                const pilotoption=({
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {
                                color: '#fff'
                            }
                        }
                    },
                    grid: {
                        show:false,
                        bottom: '3%',
                        right: '3%',
                        left: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            axisLabel:{
                                color:'rgb(1,107,217)',
//                                interval:0
                            },
                            axisTick: {
                                alignWithLabel: true
                            },
                            type: 'category',
                            data: this.xpilotData,
                            axisPointer: {type: 'shadow'},
                            axisLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)',
                                }
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '飞行总时长',
                            nameTextStyle:{
                                color:'rgb(1,107,217)'
                            },
//                            min: 0,
//                            max: 1000,
//                            interval: 100,
                            axisLabel: {
                                formatter: '{value} 小时',
                                color:'rgb(1,107,217)'
                            },
                            axisLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)',
                                }
                            },
                            splitLine:{//网格线的格式
                                lineStyle:{
                                    color: '#000',//网格线颜色
                                    width: 1,//网格线宽度
                                    type: 'solid'//网格线样式

                                }
                            },
                        },
                        {
                            type: 'value',
                            name: '违规次数',
                            nameTextStyle:{
                                color:'rgb(1,107,217)'
                            },
//                            min: 0,
//                            max: 10,
//                            interval: 1,
                            axisLabel: {
                                formatter: '{value} 次',
                                color:'rgb(1,107,217)'
                            },
                            axisLine: {
                                lineStyle: {
                                    color: 'rgb(1,107,217)',
                                }
                            },
                            splitLine: {
                                show:false,
                            },

                        }
                    ],
                    series: [
                        {
                            name: '飞行时长',
                            type: 'bar',
//                            barWidth: 20,
                            barMaxWidth: 50,
                            yAxisIndex: 0,
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
                            data: this.ypilotData
                        },
                        {
                            name: '违规次数',
                            type: 'line',
                            yAxisIndex: 1,
                            itemStyle: {
                                normal: {color: 'rgb(0,230,255)'}
                                },
                            data: this.zpilotData
                        }
                    ]
                });
                this.mybarChart.setOption(baroption);
                this.pilotbarchart.setOption(pilotoption);
            },
            getoperationdata(){
                this.$profile.getProfile().then((profile) => {
                    let self = this;
                    let companyids = profile.buz.companyId;
                    self.$http.httpGet('/app/api/aircraft/search/findallaircraftfly', {
                        companyIds:companyids,
                        startDate:this.startDate,
                        endDate:this.endDate
                    }).then((res) => {
                        let result = res.data.data;
                        let hour;
                        let line;
                        let size = 10;
                        self.aircraftbar = [];
                        if(result.length < 10 ){
                            size = result.length
                        }
                        //获取前10条数据在图表中展示
                        for (let i = 0;i < size;i++)
                        {
                            line = result[i];
                            hour = Math.round(line[1]/3600*100)/100
                            let map1={name: line[0],value: hour}
                            self.aircraftbar.push(map1);
                        }

                    })
                }, (error) => {
                    console.log('error', error);
                });
            },
            getpilotdata() {
                this.$profile.getProfile().then((profile) =>{
                    let self = this;
                    self.$http.httpGet('/app/api/pilot/search/company/more', {
                        companyId: profile.buz.companyId,
                        page:1,
                        pageSize:10,
                        begaindata:self.startDate,
                        enddate:self.endDate
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            for(var i=0;i<result.data.length;i++) {
                                if(result.data[i].flySumTime==null){
                                    result.data[i].flySumTime=0;
                                }
                                else {
                                    result.data[i].flySumTime = parseInt(result.data[i].flySumTime) / 3600;
                                    result.data[i].flySumTime = result.data[i].flySumTime.toFixed(2);
                                }
                            }
                            self.pilotbar = result.data;
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                    });
                },
            getpendlist(){
                let self = this;
                self.$http.httpGet('/app/api/bpmtask/search/pengdinglist',{}
                ).then(function (response) {
                    let datainfo = response.data.data;
                    if(datainfo.length<3){
                        self.pendlist = datainfo;
                    }
                    else{
                        for(let i =0;i<3;i++){
                            self.pendlist.push(datainfo[i])
                        }
                    }

                }).catch(function (error) {
                    console.log(error);
                });
            },
            },
        watch:{
            pilotbar: function(val){
                this.initData()
            },
            opinionbarData: function(val){
                this.initData()
            }
        }
    }
</script>

<style scoped>
.refresh{
    cursor:pointer;
}

</style>
