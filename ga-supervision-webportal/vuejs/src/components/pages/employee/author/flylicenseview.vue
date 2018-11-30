<template>
    <div v-bind:class="{ active: isActive }">
        <div v-if="backflag==true" class="gacompany-div" style=" text-align: center; padding-bottom: 10px; padding-top: -1px; margin-left: 640px;">
            <button class="div-button" style="width: 100px;margin-bottom: 5px"  @click="back()" >返  回</button>
        </div>
        <div class="infoPart1">
            <div>
                <div class="cut container-center">
                    <table class="license">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style=" text-align: center;" colspan="4">飞行执照详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">执照名称</td>
                            <td class="tr-2 tr-6">{{qulification.licensetype}}</td>
                            <td class="tr-1 tr-5 tr-l">执照编号</td>
                            <td class="tr-2 tr-6">{{qulification.licenseno}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发时间</td>
                            <td class="tr-2 tr-4">{{qulification.issuedate}}</td>
                            <td class="tr-1 tr-3 tr-l">执照到期时间</td>
                            <td class="tr-2 tr-4">{{qulification.expiredate}}</td>

                        </tr>
                        <tr v-if="license.licenseclass!=null">
                            <td class="tr-1 tr-5">执照类型</td>
                            <td class="tr-2 tr-6">{{license.licenseclass}}</td>
                            <td v-if="license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航线运输驾驶员'" class="tr-1 tr-5 tr-l">是否是飞行教员</td>
                            <td v-if="license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航线运输驾驶员'"  class="tr-2 tr-6">{{license.ispilotteacher}}</td>
                            <td v-if="license.licenseclass=='运动驾驶员'" class="tr-1 tr-5 tr-l">是否是运动教员</td>
                            <td v-if="license.licenseclass=='运动驾驶员'" class="tr-2 tr-6">{{license.issportspilotteacher}}</td>
                            <td v-if="license.licenseclass=='多人制机组驾驶员'" class="tr-1 tr-5 tr-l">航空器型别等级</td>
                            <td v-if="license.licenseclass=='多人制机组驾驶员'" class="tr-2 tr-6">{{multiaircrafttypetostring}}</td>
                            <td v-if="license.licenseclass=='学生驾驶员'" class="tr-1 tr-5 tr-l"></td>
                        </tr>
                        <tr v-if="license.licenseclass=='运动驾驶员'">
                            <td class="tr-1 tr-3">驾驶员航空器级别等级</td>
                            <td class="tr-2 tr-4">{{license.sportsaircraftgrade}}</td>
                            <td class="tr-1 tr-3 tr-l">驾驶员航空器类别等级</td>
                            <td class="tr-2 tr-4">{{license.sportsaircraftclass}}</td>
                        </tr>

                        <tr v-if="license.licenseclass=='多人制机组驾驶员'">
                            <td class="tr-1 tr-3">航空器类别等级</td>
                            <td class="tr-2 tr-4">{{license.multiaircraftclass}}</td>
                            <td class="tr-1 tr-3 tr-l">航空器级别等级</td>
                            <td class="tr-2 tr-4">{{license.multiaircraftgrade}}</td>
                        </tr>


                        <tr v-if="license.ispilotteacher=='是'">
                            <td class="tr-1 tr-3">教员基础教员等级</td>
                            <td class="tr-2 tr-4">{{license.teachergrade}}</td>
                            <td class="tr-1 tr-3 tr-l">教员仪表等级</td>
                            <td class="tr-2 tr-4">{{license.teachermetergrade}}</td>
                        </tr>
                        <tr v-if="license.ispilotteacher=='是'">
                            <td class="tr-1 tr-5">教员航空器型别</td>
                            <td class="tr-6 tr-7" colspan="3">{{teacheraircrafttypetostring}}</td>
                        </tr>

                        <tr v-if="license.ispilotteacher=='否'&&(license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航线运输驾驶员')">
                            <td class="tr-1 tr-3">仪表等级</td>
                            <td class="tr-2 tr-4">{{license.metergrade}}</td>
                            <td class="tr-1 tr-3 tr-l">驾驶员航空器类别</td>
                            <td class="tr-2 tr-4">{{aircraftClasstostring}}</td>
                        </tr>

                        <tr v-if="license.ispilotteacher=='否'&&(license.licenseclass=='私用驾驶员'||license.licenseclass=='商用驾驶员'||license.licenseclass=='航线运输驾驶员')">
                            <td class="tr-1 tr-5">驾驶员航空器型别</td>
                            <td class="tr-2 tr-6">{{aircraftTypetostring}}</td>
                            <td class="tr-1 tr-5 tr-l">驾驶员航空器级别</td>
                            <td class="tr-2 tr-6">{{aircraftgradetostring}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="margin-top: 2px">
                <Table border :columns="columns7" :data="licenseChecks" style="width: 965px;"></Table>
            </div>

            <div class="flyliceimglist" style="margin-top: 20px;width: 750px" v-for="item in uploadList">
                <img :src="item" style="width: 19%;"  @click="imgdetail('',item)">
            </div>
        </div>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>

<script>
    import noimg from '../../../../../static/img/noimgpictrue.png';
    import imgdetail from './imgdetail.vue';
    export default {
        components: {
            imgdetail
        },
        data (){
            return {
                columns7: [
                    {
                        title: '签注时间',
                        key: 'licenseCheckDate',
                        align: 'center',
                        width: 400
                    },
                    {
                        title: '签注内容',
                        width: 563,
                        key: 'licenseCheckContent',
                        align: 'center'
                    },

                ],
                qulification: {},
                license: {},
                licenseChecks: [{}],
                groundinstructorlicense:{},
                add_dialog_show: false,
                check_form: {},
                uploadList:[],
                flightinstructorlicense:{},
                multiaircrafttype:[],
                teacheraircrafttype:[],
                aircraftClass:[],
                aircraftType:[],
                aircraftgrade:[],
                teacheraircrafttypetostring:'',
                aircraftClasstostring:'',
                aircraftTypetostring:'',
                aircraftgradetostring:'',
                multiaircrafttypetostring:'',
                backflag:false,
                isActive:false
            }
        },

        props:['licenseId'],
        created(){
            if(this.$route.query.licenseId) {
                let licenseId = this.$route.query.licenseId;
                this.backflag=true;
                this.isActive=true;
                this.getLicenseInfo(licenseId);
            }
        },

        watch: {
            licenseId:function(val){


                this.getLicenseInfo(val)
            },
        },
        methods: {
            back(){
                history.go(-1);
            },
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            getLicenseInfo(str1) {
                let self = this;


                    self.$profile.getProfile().then((profile) => {
//                    let companyid = profile.buz.companyId;

                            self.$http.httpGet('/eim/api/qulification/search/getinfoby', {
                                licenseId: str1,
                            }).then(function (res) {
                                let result = res.data;
                                if (result.resultCode == 200) {

                                    if (result.data.personQulificationInfo == null) {
                                        console.log("error")
                                    }
                                    else {
                                        self.qulification = result.data.personQulificationInfo;
                                        if (result.data.personQulificationInfo.filePath != "" & result.data.personQulificationInfo.filePath != null) {
                                            self.uploadList = result.data.personQulificationInfo.filePath.split(",");
                                        }
                                    }

                                    self.license = result.data.pilotLicense;
                                    switch (self.license.ispilotteacher) {
                                        case 0:
                                            self.license.ispilotteacher = "否";
                                            break;
                                        case 1:
                                            self.license.ispilotteacher = "是";
                                            break;
                                    }
                                    switch (self.license.issportspilotteacher) {
                                        case 0:
                                            self.license.issportspilotteacher = "否";
                                            break;
                                        case 1:
                                            self.license.issportspilotteacher = "是";
                                            break;
                                    }

                                    if (self.license.multiaircrafttype != null && self.license.multiaircrafttype != '') {
                                        self.multiaircrafttype = self.license.multiaircrafttype.split(",");
                                        self.multiaircrafttype.splice(0, 1);
                                        ;
                                        for (var i = 0; i < self.multiaircrafttype.length; i++) {
                                            switch (self.multiaircrafttype[i]) {
                                                case "1":
                                                    self.multiaircrafttype[i] = "5700千克以上的航空器";
                                                    break;
                                                case "2":
                                                    self.multiaircrafttype[i] = "3180千克以上的航空器";
                                                    break;
                                                case "3":
                                                    self.multiaircrafttype[i] = "涡轮喷气动力飞机";
                                                    break;
                                                case "4":
                                                    self.multiaircrafttype[i] = "其他航空器";
                                                    break;
                                            }
                                        }
                                        self.multiaircrafttypetostring = self.multiaircrafttype.join(",");

                                    }
                                    if (self.license.teacheraircrafttype != null && self.license.teacheraircrafttype != '') {
                                        self.teacheraircrafttype = self.license.teacheraircrafttype.split(",");
                                        self.teacheraircrafttype.splice(0, 1);
                                        for (var i = 0; i < self.teacheraircrafttype.length; i++) {
                                            switch (self.teacheraircrafttype[i]) {
                                                case "1":
                                                    self.teacheraircrafttype[i] = "5700千克以上的航空器";
                                                    break;
                                                case "2":
                                                    self.teacheraircrafttype[i] = "3180千克以上的航空器";
                                                    break;
                                                case "3":
                                                    self.teacheraircrafttype[i] = "涡轮喷气动力飞机";
                                                    break;
                                                case "4":
                                                    self.teacheraircrafttype[i] = "其他航空器";
                                                    break;

                                            }
                                        }
                                        self.teacheraircrafttypetostring = self.teacheraircrafttype.join(",");
                                    }
                                    if (self.license.aircraftClass != null && self.license.aircraftClass != '') {
                                        self.aircraftClass = self.license.aircraftClass.split(",");
                                        self.aircraftClass.splice(0, 1);
                                        for (var i = 0; i < self.aircraftClass.length; i++) {
                                            switch (self.aircraftClass[i]) {
                                                case "1":
                                                    self.aircraftClass[i] = "飞机";
                                                    break;
                                                case "2":
                                                    self.aircraftClass[i] = "直升机";
                                                    break;
                                                case "3":
                                                    self.aircraftClass[i] = "飞艇";
                                                    break;
                                                case "4":
                                                    self.aircraftClass[i] = "倾转旋翼机";
                                                    break;

                                            }
                                        }
                                        self.aircraftClasstostring = self.aircraftClass.join(",");

                                    }
                                    if (self.license.aircraftType != null && self.license.aircraftType != '') {
                                        self.aircraftType = self.license.aircraftType.split(",");
                                        self.aircraftType.splice(0, 1);
                                        ;
                                        for (var i = 0; i < self.aircraftType.length; i++) {
                                            switch (self.aircraftType[i]) {
                                                case "1":
                                                    self.aircraftType[i] = "5700千克以上的航空器";
                                                    break;
                                                case "2":
                                                    self.aircraftType[i] = "3180千克以上的航空器";
                                                    break;
                                                case "3":
                                                    self.aircraftType[i] = "涡轮喷气动力飞机";
                                                    break;
                                                case "4":
                                                    self.aircraftType[i] = "其他航空器";
                                                    break;
                                            }
                                        }
                                        self.aircraftTypetostring = self.aircraftType.join(",");
                                    }
                                    if (self.license.aircraftgrade != null && self.license.aircraftgrade != '') {
                                        self.aircraftgrade = self.license.aircraftgrade.split(",");
                                        self.aircraftgrade.splice(0, 1);
                                        ;
                                        for (var i = 0; i < self.aircraftgrade.length; i++) {
                                            switch (self.aircraftgrade[i]) {
                                                case "1":
                                                    self.aircraftgrade[i] = "单发陆地";
                                                    break;
                                                case "2":
                                                    self.aircraftgrade[i] = "多发陆地";
                                                    break;
                                                case "3":
                                                    self.aircraftgrade[i] = "单发水上";
                                                    break;
                                                case "4":
                                                    self.aircraftgrade[i] = "单发水上";
                                                    break;
                                                case "5":
                                                    self.aircraftgrade[i] = "多发水上";
                                                    break;
                                            }
                                        }
                                        self.aircraftgradetostring = self.aircraftgrade.join(",");
                                    }
                                    if (result.data.licenseChecks == null || result.data.licenseChecks.lenght == 0) {
                                        console.log("error")
                                    }
                                    else {
                                        self.licenseChecks = result.data.licenseChecks;
                                    }
                                    self.groundinstructorlicense = result.data.groundinstructorlicense;
                                    self.flightinstructorlicense = result.data.flightinstructorlicense;
                                }
                            });



                    });


            },
            backtotraininfo() {
                let self = this;

                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'flylicense'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'flylicense'}});
                }
            }
        }
    }
</script>
<style scoped>
    .license{
        border-spacing: 0px;
        width: 965px;
        border:1px solid rgb(1,107,217)
    }
    .license .tr-1 {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border-bottom: solid 1px #55a2eb;
    }
    .license .tr-l{
        border-left: solid 1px #55a2eb;
    }
    .license .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border-left: solid 1px #55a2eb;
        border-bottom: solid 1px #55a2eb;
        text-align: center;
    }
    .license .tr-3{
        background-color: rgb(179,221,252);
    }

    .license .tr-4{
        background-color: rgb(218,236,252);
    }
    .license .tr-5{
        background-color: rgb(201,231,253);
    }
    .license .tr-6{
        background-color: rgb(239,246,253);
    }

    .flyliceimglist>img{
        float: left;
        margin-left: 1%;

    }

    .div-button{
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }

</style>
