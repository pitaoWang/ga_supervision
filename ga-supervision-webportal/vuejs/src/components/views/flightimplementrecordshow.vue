<style scoped>
    .flight-record {
        border-spacing: 0px;
        width: 750px;
        border:1px solid rgb(1,107,217)
    }
    .flight-record .tr-1 {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border-bottom: solid 1px #55a2eb;
    }
    .flight-record .tr-l{
        border-left: solid 1px #55a2eb;
    }
    .flight-record .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border-left: solid 1px #55a2eb;
        border-bottom: solid 1px #55a2eb;
        text-align: center;
    }
    .flight-record .tr-3{
        background-color: rgb(179,221,252);
    }

    .flight-record .tr-4{
        background-color: rgb(218,236,252);
    }
    .flight-record .tr-5{
        background-color: rgb(201,231,253);
    }
    .flight-record .tr-6{
        background-color: rgb(239,246,253);
    }

    .weatherfileshow>img{
        float: left;
        margin-left: 1%;

    }
    .infoPart1 table{
        border-collapse:collapse;
        border-spacing:0;
        width: 750px;}
</style>
<template>
    <div>
        <div class="infoPart1">
            <div>
                <div class="cut container-center" >
                    <table class="flight-record">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">飞行记录详细信息</td>
                        </tr>
                        <tr>
                            <!--160px 227px-->
                            <td class="tr-1 tr-3" style="width: 150px;">所属公司</td>
                            <td class="tr-2 tr-4" style="width: 200px;" colspan="3">{{companyName}}</td>
                        </tr>
                        <tr >
                            <td class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">架次信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">计划编号</td>
                            <td class="tr-2 tr-4">{{flightrecord.flyPlanId}}</td>
                            <td class="tr-1 tr-3 tr-l">任务书编号</td>
                            <td class="tr-2 tr-4">{{flightrecord.flyTaskId}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">飞行种类</td>
                            <td class="tr-2 tr-6">{{flyType}}</td>
                            <td class="tr-1 tr-5 tr-l">飞行任务</td>
                            <td class="tr-2 tr-6">{{flightrecord.flyTask}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">飞行性质</td>
                            <td class="tr-2 tr-4">{{flightrecord.flightProperties}}</td>
                            <td class="tr-1 tr-3 tr-l"></td>
                            <td class="tr-2 tr-4"></td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">航空器来源</td>
                            <td class="tr-2 tr-6">{{flightrecord.aircraftFrom}}</td>
                            <td class="tr-1 tr-5 tr-l">航空器所属公司</td>
                            <td class="tr-2 tr-6">{{flightrecord.aircraftCompanyName}}</td>
                        </tr>

                        <tr>
                            <td class="tr-1 tr-3">航空器登记号</td>
                            <td class="tr-2 tr-4">{{flightrecord.aircraftCerNumber}}</td>
                            <td class="tr-1 tr-3 tr-l">航空器型号</td>
                            <td class="tr-2 tr-4">{{aircraft}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">日期</td>
                            <td class="tr-2 tr-6">{{flightrecord.flyDate}}</td>
                            <td class="tr-1 tr-5 tr-l"></td>
                            <td class="tr-2 tr-6"></td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">机长/教员状态</td>
                            <td class="tr-2 tr-4">{{flightrecord.captainState}}</td>
                            <td class="tr-1 tr-3 tr-l">机长/教员所属公司</td>
                            <td class="tr-2 tr-4">{{captainCompany}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">副驾驶/学员状态</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotState}}</td>
                            <td class="tr-1 tr-5 tr-l">副驾驶/学员所属公司</td>
                            <td class="tr-2 tr-6">{{copilotCompany}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">机长/教员</td>
                            <td class="tr-2 tr-4">{{captain}}</td>
                            <td class="tr-1 tr-3 tr-l">副驾驶/学员</td>
                            <td class="tr-2 tr-4">{{copilot}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">起飞站</td>
                            <td class="tr-2 tr-6">{{flightrecord.origin}}</td>
                            <td class="tr-1 tr-5 tr-l">降落站</td>
                            <td class="tr-2 tr-6">{{flightrecord.destination}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">起飞时刻</td>
                            <td class="tr-2 tr-4">{{flightrecord.takeOffTime}}</td>
                            <td class="tr-1 tr-3 tr-l">降落时刻</td>
                            <td class="tr-2 tr-4">{{flightrecord.landTime}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">本架次飞行时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.sortieFlyTime}}</td>
                            <td class="tr-1 tr-5 tr-l"></td>
                            <td class="tr-2 tr-6"></td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">本架次昼间降落架次</td>
                            <td class="tr-2 tr-4">{{flightrecord.sortieDiurnalCount}}</td>
                            <td class="tr-1 tr-3 tr-l">本架次夜间降落架次</td>
                            <td class="tr-2 tr-4">{{flightrecord.sortieNightCount}}</td>
                        </tr>
                        <tr>

                            <td class="tr-1 tr-5">本架次昼间飞行时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.sortieDiurnalFlytime}}</td>
                            <td class="tr-1 tr-5 tr-l">本架次夜间飞行时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.sortieNightFlytime}}</td>
                        </tr>
                        <tr @click="open.base_info_open1 = !open.base_info_open1">
                            <td class="tr-1 tr-3" style="width: 150px !important; text-align: center;" colspan="4">机长/教员飞行经历
                                <Icon style="float: right;margin-right: 15px"  :type="open.base_info_open1? 'minus':'plus'" ></Icon>
                            </td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-5">昼间降落次数</td>
                            <td class="tr-2 tr-6">{{flightrecord.diurnalCount}}</td>
                            <td class="tr-1 tr-5 tr-l">夜间降落次数</td>
                            <td class="tr-2 tr-6">{{flightrecord.nightCount}}</td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-3">昼间飞行时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.diumalFlyTime}}</td>
                            <td class="tr-1 tr-3 tr-l">夜间飞行时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.nightFlyTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-5">机长飞行时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.captainFlyTime}}</td>
                            <td class="tr-1 tr-5 tr-l">机长单飞时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.captainAloneFlyTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-3">机长转场时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.captainTransitTime}}</td>
                            <td class="tr-1 tr-3 tr-l">机长夜航时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.captainNightFlyTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-5">机长仪表类型</td>
                            <td class="tr-2 tr-6">{{flightrecord.instrumentType}}</td>
                            <td class="tr-1 tr-5 tr-l"></td>
                            <td class="tr-2 tr-6"></td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-3">机长真实仪表时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.actualInstrumenTime}}</td>
                            <td class="tr-1 tr-3 tr-l">机长模拟仪表时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.simulateInstrumenTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-5">机长特技飞行时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.captainStuntFlyTime}}</td>
                            <td class="tr-1 tr-5 tr-l"></td>
                            <td class="tr-2 tr-6" ></td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-3">监视时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.captainWatchTime}}</td>
                            <td class="tr-1 tr-3 tr-l">被监视时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.captainWatchedTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open1">
                            <td class="tr-1 tr-5">PF时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.captainPfTime}}</td>
                            <td class="tr-1 tr-5 tr-l">PNF时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.captainPnfTime}}</td>
                        </tr>
                        <tr @click="open.base_info_open2 = !open.base_info_open2">
                            <td class="tr-1 tr-3" style="width: 150px !important; text-align: center;" colspan="4">副驾驶/学员飞行经历
                                <Icon style="float: right;margin-right: 15px"  :type="open.base_info_open2? 'minus':'plus'" ></Icon>
                            </td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-5">昼间降落次数</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotDiurnalCount}}</td>
                            <td class="tr-1 tr-5 tr-l">夜间降落次数</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotNightCount}}</td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-3">昼间飞行时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.copilotDiurnalFlyTime}}</td>
                            <td class="tr-1 tr-3 tr-l">夜间飞行时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.copilotNightFlyTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-5">飞行时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotFlyTime}}</td>
                            <td class="tr-1 tr-5 tr-l">被带飞时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.leadFlyTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-3">转场时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.copilotTransitTime}}</td>
                            <td class="tr-1 tr-3 tr-l">被带飞转场时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.leadedTransitTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-5">特技时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotStuntFlyTime}}</td>
                            <td class="tr-1 tr-5 tr-l"></td>
                            <td class="tr-2 tr-6"></td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-3">仪表类型</td>
                            <td class="tr-2 tr-4">{{flightrecord.copilotInstrumentType}}</td>
                            <td class="tr-1 tr-3 tr-l"></td>
                            <td class="tr-2 tr-4"></td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-5">真实仪表时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotActualInstrumenTime}}</td>
                            <td class="tr-1 tr-5 tr-l">模拟仪表时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotSimulateInstrumenTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-3">监视时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.copilotWatchTime}}</td>
                            <td class="tr-1 tr-3 tr-l">被监视时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.copilotWatchedTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open2">
                            <td class="tr-1 tr-5">PF时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotPfTime}}</td>
                            <td class="tr-1 tr-5 tr-l">PNF时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.copilotPnfTime}}</td>
                        </tr>
                        <tr @click="open.base_info_open3 = !open.base_info_open3">
                            <td class="tr-1 tr-3" style="width: 150px !important; text-align: center;" colspan="4">模拟训练记录
                                <Icon style="float: right;margin-right: 15px"  :type="open.base_info_open3? 'minus':'plus'" ></Icon>
                            </td>
                        </tr>
                        <tr v-show="open.base_info_open3">
                            <td class="tr-1 tr-5">模拟器/训练器机构</td>
                            <td class="tr-2 tr-6">{{flightrecord.simulateOrg}}</td>
                            <td class="tr-1 tr-5 tr-l"></td>
                            <td class="tr-2 tr-6"></td>
                        </tr>
                        <tr v-show="open.base_info_open3">
                            <td class="tr-1 tr-3">模拟机时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.simulatorTime}}</td>
                            <td class="tr-1 tr-3 tr-l">训练器时间</td>
                            <td class="tr-2 tr-4">{{flightrecord.trainingTime}}</td>
                        </tr>
                        <tr v-show="open.base_info_open3">
                            <td class="tr-1 tr-5">教员时间</td>
                            <td class="tr-2 tr-6">{{flightrecord.instructorTime}}</td>
                            <td class="tr-1 tr-5 tr-l"></td>
                            <td class="tr-2 tr-6"></td>
                        </tr>
                        <tr >
                            <td class="tr-1 tr-3" style="width: 150px !important; text-align: center;" colspan="4">其他信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">教员/机长签字</td>
                            <td class="tr-2 tr-6">{{flightrecord.instructorSign}}</td>
                            <td class="tr-1 tr-5 tr-l"></td>
                            <td class="tr-2 tr-6"></td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">任务执行情况</td>
                            <td class="tr-2 tr-4" >{{flightrecord.taskInfo}}</td>
                            <td class="tr-1 tr-3 tr-l">备注</td>
                            <td class="tr-2 tr-4">{{flightrecord.remark}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        components: {
//            'uploadfile':uploadfile,
        },
        data () {
            return {
                instrumentType:'',
                flyType:'',
                captainCompany:'',
                copilotCompany:'',
                open: {
                    base_info_open1: false,
                    base_info_open2: false,
                    base_info_open3: false,
                },
//                keyword: '',
                employee: {},
//                pilot: {},
//                flightRecordVOs: [],
//                prList: [],
//                personQulificationInfoVo: {},
//                personQulificationInfo: {},
//                pilotLicense: {},
//                scanCardarra:[],
//                personQuliPic:[],
//                trainingCertificate:{},
//                licenseChecks: [{}],
                flightrecord:{},
                companyName:'',
                aircraft:'',
                captain:'',
                copilot:''
            }
        },
        created () {
//            this.init();

        },
        props: {
            id: {
                type: Number,
                default: 0
            }
        },
        watch:{
            id (val) {
                this.init(val);
            },
        },
        methods: {
            //时间小时分钟转化格式
            dealHourAndMins(val){
                let string='';
                if(val != '' && val !=null) {
                    let arr = val.split('-');
                    if (arr[0] == '' && arr[1] == '') {
                        string = '';
                        return string;
                    }
                    else if (arr[0] == '') {
                        string = arr[1] + '分钟';
                        return string;
                    }
                    else if (arr[1] == '') {

                        string = arr[0] + '小时';
                        return string;
                    }
                    else {
                        string = arr[0] + '小时' + arr[1] + '分钟';
                        return string;
                    }
                }
            },
            refreshdata(){
                this.flightrecord = {};
                this.companyName = '';
                this.aircraft = '';
                this.captain = '';
                this.copilot = '';
            },
            init(val){
                let self = this;
                self.refreshdata();
                let id=val;
                if (id != 0) {
                    self.$http.httpGet('/eim/api/flightimplementrecord/findFlightImplementRecordById', {
                        id: id,
                    }).then((res) => {
                        let result = res.data;
                        self.flightrecord = result;
                        console.log("findFlightImplementRecordById：",result)

                        self.flightrecord.diumalFlyTime=self.dealHourAndMins(self.flightrecord.diumalFlyTime);
                        self.flightrecord.nightFlyTime=self.dealHourAndMins(self.flightrecord.nightFlyTime);
                        self.flightrecord.captainFlyTime=self.dealHourAndMins(self.flightrecord.captainFlyTime);
                        self.flightrecord.captainAloneFlyTime=self.dealHourAndMins(self.flightrecord.captainAloneFlyTime);
                        self.flightrecord.captainTransitTime=self.dealHourAndMins(self.flightrecord.captainTransitTime);
                        self.flightrecord.leadFlyTime=self.dealHourAndMins(self.flightrecord.leadFlyTime);
                        self.flightrecord.leadTransitTime=self.dealHourAndMins(self.flightrecord.leadTransitTime);
                        self.flightrecord.copilotFlyTime=self.dealHourAndMins(self.flightrecord.copilotFlyTime);
                        self.flightrecord.copilotTransitTime=self.dealHourAndMins(self.flightrecord.copilotTransitTime);
                        self.flightrecord.actualInstrumenTime=self.dealHourAndMins(self.flightrecord.actualInstrumenTime);
                        self.flightrecord.simulateInstrumenTime=self.dealHourAndMins(self.flightrecord.simulateInstrumenTime);
                        self.flightrecord.simulatorTime=self.dealHourAndMins(self.flightrecord.simulatorTime);
                        self.flightrecord.trainingTime=self.dealHourAndMins(self.flightrecord.trainingTime);
                        self.flightrecord.instructorTime=self.dealHourAndMins(self.flightrecord.instructorTime);
                        self.flightrecord.captainWatchTime=self.dealHourAndMins(self.flightrecord.captainWatchTime);
                        self.flightrecord.captainStuntFlyTime=self.dealHourAndMins(self.flightrecord.captainStuntFlyTime);
                        self.flightrecord.captainNightFlyTime=self.dealHourAndMins(self.flightrecord.captainNightFlyTime);
                        self.flightrecord.captainWatchedTime=self.dealHourAndMins(self.flightrecord.captainWatchedTime);
                        self.flightrecord.captainPfTime=self.dealHourAndMins(self.flightrecord.captainPfTime);
                        self.flightrecord.captainPnfTime=self.dealHourAndMins(self.flightrecord.captainPnfTime);
                        self.flightrecord.copilotDiurnalFlyTime=self.dealHourAndMins(self.flightrecord.copilotDiurnalFlyTime);
                        self.flightrecord.copilotNightFlyTime=self.dealHourAndMins(self.flightrecord.copilotNightFlyTime);
                        self.flightrecord.leadedTransitTime=self.dealHourAndMins(self.flightrecord.leadedTransitTime);
                        self.flightrecord.copilotStuntFlyTime=self.dealHourAndMins(self.flightrecord.copilotStuntFlyTime);
                        self.flightrecord.copilotActualInstrumenTime=self.dealHourAndMins(self.flightrecord.copilotActualInstrumenTime);
                        self.flightrecord.copilotSimulateInstrumenTime=self.dealHourAndMins(self.flightrecord.copilotSimulateInstrumenTime);
                        self.flightrecord.copilotWatchTime=self.dealHourAndMins(self.flightrecord.copilotWatchTime);
                        self.flightrecord.copilotWatchedTime=self.dealHourAndMins(self.flightrecord.copilotWatchedTime);
                        self.flightrecord.copilotPfTime=self.dealHourAndMins(self.flightrecord.copilotPfTime);
                        self.flightrecord.copilotPnfTime=self.dealHourAndMins(self.flightrecord.copilotPnfTime);

//                        self.companyName=self.flightrecord.companyId.name;
//                        self.aircraft= self.flightrecord.aircraftId.aircraftModel;

                        let companyName=self.flightrecord.companyId;
                        self.$http.httpGet('/eim/api/company/'+companyName,{}
                        ).then((res) => {
                            let result = res.data;
                            //console.log('result....', result);
                            self.companyName= result.name;

                        }).catch(function (error) {
                            console.log(error);
                        });

                        let captainCompany=self.flightrecord.captainCompanyId;
                        self.$http.httpGet('//api/company/'+captainCompany,{}
                        ).then((res) => {
                            let result = res.data;
                            //console.log('result....', result);
                            self.captainCompany= result.name;

                        }).catch(function (error) {
                            console.log(error);
                        });

                        let copilotCompany=self.flightrecord.copilotCompanyId;
                        self.$http.httpGet('/app/api/company/'+copilotCompany,{}
                        ).then((res) => {
                            let result = res.data;
                            //console.log('result....', result);
                            self.copilotCompany= result.name;

                        }).catch(function (error) {
                            console.log(error);
                        });




                        let aircraftId=self.flightrecord.aircraftId;
                        self.$http.httpGet('/eim/api/aircraft/'+aircraftId,{}
                        ).then((res) => {
                            let result = res.data;
                            self.aircraft= result.aircraftModel;
                        }).catch(function (error) {
                            console.log(error);
                        });



                        let captain=self.flightrecord.captainId;
                        if(captain != 0){
                            self.$http.httpGet('/eim/api/employee/'+captain,{}
                            ).then((res) => {
                                let result = res.data.data;
                                //console.log('result....', result);
                                self.captain= result.userName+' : '+result.workId;

                            }).catch(function (error) {
                                console.log(error);
                            });
                        }

                        let copilot=self.flightrecord.copilotId;
                        if(copilot != 0)
                        {
                            self.$http.httpGet('/eim/api/employee/'+copilot,{}
                            ).then((res) => {
                                let result = res.data.data;
                                console.log('result....', result);
                                self.copilot= result.userName+' : '+result.workId;
                            }).catch(function (error) {
                                console.log(error);
                            });
                        }

                        let flyType =self.flightrecord.flyType;
                        if(flyType=='1'){
                            self.flyType='一般商业飞行';
                        }else if(flyType=='2'){
                            self.flyType='农林喷洒飞行';
                        }else if(flyType=='3'){
                            self.flyType='训练飞行（私照、商照';
                        }else if(flyType=='4'){
                            self.flyType='旋翼机外载荷';
                        }else if(flyType=='5'){
                            self.flyType='空中旅游';
                        }else if(flyType=='6'){
                            self.flyType='其他';
                        }

                    }).catch(function (error) {
                        console.log(error);
                    });
                }
            },


//            backtotraininfo(){
//                let self=this;
//                if (self.$route.path == '/for_pilot') {
//                    self.$router.push({path: '/for_pilot', query: {backtype: 'flightrecord'}});
//                }
//                else {
//                    self.$router.push({path: '/pilot', query: {backtype: 'flightrecord'}});
//                }
//
//            },
            modifyData(index) {

                this.visible = true;
            },
            onComplete: function () {
                alert("done")
                this.visible = false;
            }
        }
    }
</script>


