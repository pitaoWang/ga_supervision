<template>
    <div style="width: 1280px; height: 900px; background:white">
        <span style="width:96%; display:inline-block;text-align:right; margin-top:10px;margin-bottom: 10px; padding-right: 17px;">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
        <div style="">
            <div >
                <!--<Table border  :columns="columns" :data="data"></Table>-->

                <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                            执照详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3" >执照名称</td>
                        <td class="tr-2 tr-4">{{qulification.licensetype}}</td>
                        <td class="tr-1 tr-3">执照编号</td>
                        <td class="tr-2 tr-4">{{qulification.licenseno}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3" >是否长久有效</td>
                        <td class="tr-2 tr-4" >{{radio}}</td>
                        <td class="tr-1 tr-3" >颁发单位</td>
                        <td class="tr-2 tr-4" colspan="2">{{qulification.issueUnit}}</td>


                    </tr>

                  <tr>
                        <td class="tr-1 tr-3">备注</td>
                        <td class="tr-2 tr-4" >{{qulification.remark}}</td>
                        <td class="tr-1 tr-3">执照颁发时间</td>
                        <td class="tr-2 tr-4">{{qulification.issuedate}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">执照到期时间</td>
                        <td class="tr-2 tr-4">{{qulification.expiredate}}</td>
                        <td class="tr-1 tr-3">执照类型</td>
                        <td class="tr-2 tr-4">{{license.licenseclass}}</td>
                    </tr>


                    <!--运动驾驶员-->
                     <tr v-if="license.licenseclass=='运动驾驶员'" >
                       <td class="tr-1 tr-3">是否是运动教员</td>
                       <td class="tr-2 tr-4">{{license.issportspilotteacher}}</td>
                       <td class="tr-1 tr-3">航空器类别等级</td>
                       <td class="tr-2 tr-4">{{license.sportsaircraftclass}}</td>

                   </tr>
                    <tr v-if="license.licenseclass=='运动驾驶员'">
                        <td class="tr-1 tr-3">航空器级别等级</td>
                        <td class="tr-2 tr-4">{{license.sportsaircraftgrade}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4"></td>
                    </tr>
                   <!--//私用 /商用驾驶员/航行运输驾驶员-->
                    <tr  v-if="license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航行运输驾驶员'">
                        <td class="tr-1 tr-3">是否是飞行教员</td>
                        <td class="tr-2 tr-4">{{license.ispilotteacher}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4"></td>
                    </tr>

                     <tr v-if="license.ispilotteacher=='否'&&(license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航行运输驾驶员')">
                        <td class="tr-1 tr-3">仪表等级</td>
                        <td class="tr-2 tr-4">{{license.metergrade}}</td>
                        <td class="tr-1 tr-3">驾驶员航空器类别</td>
                        <td class="tr-2 tr-4">{{aircraftClass}}</td>

                    </tr>
                    <tr v-if="license.ispilotteacher=='否'&&(license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航行运输驾驶员')">
                        <td class="tr-1 tr-3">驾驶员航空器型别</td>
                        <td class="tr-2 tr-4">{{aircraftType}}</td>
                        <td class="tr-1 tr-3">驾驶员航空器级别</td>
                        <td class="tr-2 tr-4">{{aircraftgrade}}</td>
                    </tr>
                    <tr v-if="license.ispilotteacher=='是'&&(license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航行运输驾驶员')">
                        <td class="tr-1 tr-3">教员仪表等级</td>
                        <td class="tr-2 tr-4">{{license.teachermetergrade}}</td>
                        <td class="tr-1 tr-3">教员基础教员等级</td>
                        <td class="tr-2 tr-4">{{license.teachergrade}}</td>

                    </tr>
                    <tr v-if="license.ispilotteacher=='是'&&(license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航行运输驾驶员')">
                        <td class="tr-1 tr-3">教员航空器型别</td>
                        <td class="tr-2 tr-4">{{teacheraircrafttype}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4"></td>
                    </tr>
                    <!--//多人制机组驾驶员-->
                    <tr  v-if="license.licenseclass=='多人制机组驾驶员'">
                        <td class="tr-1 tr-3">航空器类别等级</td>
                        <td class="tr-2 tr-4">{{license.multiaircraftclass}}</td>
                        <td class="tr-1 tr-3">航空器级别等级</td>
                        <td class="tr-2 tr-4">{{license.multiaircraftgrade}}</td>

                    </tr>
                    <tr v-if="license.licenseclass=='多人制机组驾驶员'">
                        <td class="tr-1 tr-3">航空器型别等级</td>
                        <td class="tr-2 tr-4">{{multiaircrafttype}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4"></td>
                    </tr>
                    </tbody>
                </table>
                <div id="test" v-html="quliphoto" style="width: 1190px; border-spacing:0px; margin-left: 20px"></div>
                <div style="margin-top:10px;">
                    <div style="width: 1190px;margin-left: 20px">
                        <Table  :columns="columns7" :data="licenseChecks"></Table>
                    </div>
                </div>

                <div style="margin-top:10px;">
                    <div style="width: 1190px;margin-left: 20px">
                        <Table  :columns="columns8" :data="pilotLicenseInspect"></Table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        /*name: "companyqualificationdetail",*/
        data(){
            return{
                columns7: [
                    {
                        title: '机型编号',
                        key: 'machineCode',
                        align: 'center',
                        width: 395,
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
                                        title: params.row.machineCode
                                    }
                                }, params.row.machineCode)
                            ]);

                        }
                    },
                    {
                        title: '机型签注类型',
                        width: 395,
                        key: 'licenseCheckContent',
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
                                        title: params.row.licenseCheckContent
                                    }
                                }, params.row.licenseCheckContent)
                            ]);

                        }
                    },

                    {
                        title: '签注人',
                        width: 397,
                        key: 'checkMan',
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
                                        title: params.row.checkMan
                                    }
                                }, params.row.checkMan)
                            ]);

                        }
                    },

                ],
                columns8: [
                    {
                        title: '检查时间',
                        key: 'checkDate',
                        align: 'center',
                        width: 237,
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
                                        title: params.row.checkDate
                                    }
                                }, params.row.checkDate)
                            ]);

                        }
                    },
                    {
                        title: '检查类型',
                        width: 237,
                        key: 'checkType',
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
                                        title: params.row.checkType
                                    }
                                }, params.row.checkType)
                            ]);

                        }
                    },

                    {
                        title: '航空器型号',
                        width: 237,
                        key: 'aircraftModel',
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
                                        title: params.row.aircraftModel
                                    }
                                }, params.row.aircraftModel)
                            ]);

                        }
                    },

                    {
                        title: '考试员签字',
                        width: 238,
                        key: 'examSignature',
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
                                        title: params.row.examSignature
                                    }
                                }, params.row.examSignature)
                            ]);

                        }
                    },
                    {
                        title: '备注',
                        width: 238,
                        key: 'remark',
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
                                        title: params.row.remark
                                    }
                                }, params.row.remark)
                            ]);

                        }
                    },
                ],
                radio:'',
                qulification: '',
                license: '',
                teacheraircrafttype:[],
                aircraftClass:[],
                aircraftType:[],
                aircraftgrade:[],
                multiaircrafttype:[],
                licenseChecks: [{}],
                pilotLicenseInspect:[],
                employeeId:'',
                employeeData:'',
                quliphoto:'',
            }
        },
        /*methods:{
            imgdetail:function (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },

        },*/
        mounted(){
            let licenseId=this.$route.query.licenseId;
            this.employeeId =  this.$route.query.employeeId;
            this.employeeData =  this.$route.query.employeeData;
            this.getLicenseInfo(licenseId)

        },
        methods:{
            black:function () {
                this.$router.push({path:'/viewEmployee',query: {tab:'name2',employeeId:this.employeeId, user:this.employeeData}})
            },
            getLicenseInfo(str1) {
                let self = this;
                let role = "pilot";
                let url = "/eim/api"
                if (role == "pilot") {
                    self.$http.httpGet('/eim/api/qulification/search/getinfoby', {
                        licenseId:str1,
                    }).then(function (res) {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            if (result.data.personQulificationInfo == null) {
                                console.log("error")
                            }
                            else {
                                self.qulification = result.data.personQulificationInfo;

                                //alert((self.$refs.test).innerHTML = doc);
                                //alert(self.qulification.filePath);
                                let filePath = (self.qulification.filePath).split(",")
                                //document.querySelector('#test');
                                let doc = '';
                                for(let i=0;i<filePath.length;i++){
                                     doc =doc+ '<div class="divs"  style="display: inline-block;" ><img src="'+self.imageUrl+filePath[i]+'.thumb.jpg'+'" style="height: 120px;margin-top: 10px;" ></div>'
                                }
                                self.quliphoto = doc

                                if(self.qulification.expiredate=='2099-09-09'||self.qulification.expiredate=='2099-09-06'||self.qulification.expiredate===null) {
                                    self.radio = '是';
                                }else{
                                    self.radio = '否';
                                }
                               /* if (result.data.personQulificationInfo.filePath !=""&result.data.personQulificationInfo.filePath !=null) {
                                    self.uploadList = result.data.personQulificationInfo.filePath.split(",");
                                }*/
                            }

                            self.license = result.data.pilotLicense;
                            if(self.license.ispilotteacher==0){
                                self.license.ispilotteacher="否"
                            }
                            else{
                                self.license.ispilotteacher="是";
                            }
                            if(self.license.issportspilotteacher==0){
                                self.license.issportspilotteacher="0"
                            }
                            else{
                                self.license.issportspilotteacher="1";
                            }

                            if(self.license.multiaircrafttype!=null){
                                //航空器型别等级
                                self.multiaircrafttype=self.license.multiaircrafttype.split(",");
                                self.multiaircrafttype.forEach(function (item) {
                                    if(item == '1'){
                                        self.multiaircrafttype[self.multiaircrafttype.indexOf(item)]='5700千克以上的航空器'
                                    }
                                    if(item == '2'){
                                        self.multiaircrafttype[self.multiaircrafttype.indexOf(item)]='3180千克以上的航空器'
                                    }
                                    if(item == '3'){
                                        self.multiaircrafttype[self.multiaircrafttype.indexOf(item)]='涡轮喷气动力飞机'
                                    }
                                    if(item == '4'){
                                        self.multiaircrafttype[self.multiaircrafttype.indexOf(item)]='其他航空器'
                                    }

                                })
                            }

                            if(self.license.teacheraircrafttype!=null){
                                //教员航空器型别
                                self.teacheraircrafttype=self.license.teacheraircrafttype.split(",");
                                self.teacheraircrafttype.forEach(function (item) {
                                    if(item == '1'){
                                        self.teacheraircrafttype[self.teacheraircrafttype.indexOf(item)]='5700千克以上的航空器'
                                    }
                                    if(item == '2'){
                                        self.teacheraircrafttype[self.teacheraircrafttype.indexOf(item)]='3180千克以上的航空器'
                                    }
                                    if(item == '3'){
                                        self.teacheraircrafttype[self.teacheraircrafttype.indexOf(item)]='涡轮喷气动力飞机'
                                    }
                                    if(item == '4'){
                                        self.teacheraircrafttype[self.teacheraircrafttype.indexOf(item)]='其他航空器'
                                    }
                                })

                            }

                            if(self.license.aircraftClass!=null){
                                //驾驶员航空器类别
                                self.aircraftClass=self.license.aircraftClass.split(",");
                                self.aircraftClass.forEach(function (item) {
                                    if(item == '1'){
                                        self.aircraftClass[self.aircraftClass.indexOf(item)]='飞机'
                                    }
                                    if(item == '2'){
                                        self.aircraftClass[self.aircraftClass.indexOf(item)]='直升机'
                                    }
                                    if(item == '3'){
                                        self.aircraftClass[self.aircraftClass.indexOf(item)]='飞艇'
                                    }
                                    if(item == '4'){
                                        self.aircraftClass[self.aircraftClass.indexOf(item)]='倾转旋翼机'
                                    }
                                })
                            }
                            if(self.license.aircraftType!=null){
                                //驾驶员航空器型别
                                self.aircraftType=self.license.aircraftType.split(",");
                                self.aircraftType.forEach(function (item) {
                                    if(item=='1'){
                                        self.aircraftType[self.aircraftType.indexOf(item)]='5700千克以上的航空器'
                                    }
                                    if(item=='2'){
                                        self.aircraftType[self.aircraftType.indexOf(item)]='3180千克以上的航空器'
                                    }
                                    if(item=='3'){
                                        self.aircraftType[self.aircraftType.indexOf(item)]='涡轮喷气动力飞机'
                                    }
                                    if(item=='4'){
                                        self.aircraftType[self.aircraftType.indexOf(item)]='其他航空器'
                                    }
                                })

                            }
                            if(self.license.aircraftgrade!=null){
                                //驾驶员航空器级别
                                self.aircraftgrade=self.license.aircraftgrade.split(",");
                                self.aircraftgrade.forEach(function (item) {
                                  if(item == '1'){
                                      self.aircraftgrade[self.aircraftgrade.indexOf(item)]='单发陆地'
                                  }
                                    if(item == '2'){
                                        self.aircraftgrade[self.aircraftgrade.indexOf(item)]='多发陆地'
                                    }
                                    if(item == '3'){
                                        self.aircraftgrade[self.aircraftgrade.indexOf(item)]='单发水上'
                                    }
                                    if(item == '4'){
                                        self.aircraftgrade[self.aircraftgrade.indexOf(item)]='多发水上'
                                    }

                                })
                                
                                
                            }
                            if (result.data.licenseChecks == null || result.data.licenseChecks.lenght == 0) {
                                console.log("error")
                            }
                            else {
                                self.licenseChecks = result.data.licenseChecks;
                            }
                            if(result.data.pilotLicenseInspects==null || result.data.pilotLicenseInspects.length==0){
                            }else{
                                self.pilotLicenseInspect=result.data.pilotLicenseInspects;
                            }
                            /*self.groundinstructorlicense = result.data.groundinstructorlicense;
                            self.flightinstructorlicense = result.data.flightinstructorlicense;*/
                        }
                    });
                }
            },
        }
    }
</script>

<style scoped>
    .tr-1 {
        font-size: 14px;
        width: 180px;
        padding-left: 13px;
        height: 45px;
        color: #606265;
        border-left: solid 1px #DCDFE6;
        border-right: solid 1px #DCDFE6;
        border-bottom: solid 1px #DCDFE6;
        background-color: #F8F8F9;
    }

    .tr-2 {
        width: 200px;
        text-align: center;
        color: #606265;
        background-color: #FFF;
    }

    table {
        border-collapse: collapse;
    }

    table tr td {
        border: 1px solid #DCDFE6;
    }

    .tr-3 {
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
    /*background-color: #FFF;*/
    /*}*/
    .tr-5 {
        background-color: #f8f8f9;
    }

    .flightdispatcherimg > img {
        float: left;
        margin-left: 1%;

    }

    .infoPart1 table {
        border-collapse: collapse;
        border-spacing: 0;
        border-left: 1px solid #888;
        border-top: 1px solid #888;
        background: #efefef;
        width: 100%;
    }

    .div-button {
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }
</style>