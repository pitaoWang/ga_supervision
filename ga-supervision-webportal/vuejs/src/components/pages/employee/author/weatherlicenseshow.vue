<template>
    <div>
        <div class="infoPart1">
            <div>
                <div class="cut container-center" >
                    <table class="license">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">气象执照详细信息</td>
                        </tr>
                        <tr>
                            <!--160px 227px-->
                            <td class="tr-1 tr-5" style="width: 150px;">执照类型</td>
                            <td class="tr-2 tr-6" style="width: 200px;">{{qualification.licensetype}}</td>
                            <td class="tr-1 tr-5 tr-l" style="width: 150px;">执照编号</td>
                            <td class="tr-2 tr-6" style="width: 200px;">{{qualification.licenseno}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发日期</td>
                            <td class="tr-2 tr-4">{{qualification.issuedate}}</td>
                            <td class="tr-1 tr-3 tr-l">执照到期日期</td>
                            <td class="tr-2 tr-4">{{qualification.expiredate}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">观测资格有效性</td>
                            <td class="tr-2 tr-6">{{weatherlicense.obsereffect}}</td>
                            <td class="tr-1 tr-6 tr-l">预测资格有效性</td>
                            <td class="tr-2 tr-6">{{weatherlicense.forecasteffect}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">观测设备保障资格有效性</td>
                            <td class="tr-2 tr-4">{{weatherlicense.obserequipeffect}}</td>
                            <td class="tr-1 tr-3 tr-l">气象雷达设备保障资格有效性</td>
                            <td class="tr-2 tr-4">{{weatherlicense.radarequipeffect}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">系统设备保障资格有效性</td>
                            <td class="tr-2 tr-6">{{weatherlicense.infoequipeffect}}</td>
                            <td class="tr-1 tr-5 tr-l">适用地区</td>
                            <td class="tr-2 tr-6">{{weatherlicense.licensearea}}</td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
            <div style="margin-top: 2px">
            <Table border :columns="columns7" :data="licenseChecks" style="width: 965px;"></Table>
            </div>
            <div class="weatherfileshow" style="margin-top: 20px;width: 750px;" v-for="item in uploadList" >
                <img :src="item" style="width: 19%;" @click="imgdetail('',item)">
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
//            'uploadfile':uploadfile,
            imgdetail
        },
        data () {
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
                open: {
                    base_info_open: true,
                },
                noimg: noimg,
                keyword: '',
                employee: {},
                pilot: {},
                flightRecordVOs: [],
                prList: [],
                personQulificationInfoVo: {},
                personQulificationInfo: {},
                pilotLicense: {},
                scanCardarra:[],
                personQuliPic:[],
                trainingCertificate:{},

                licenseChecks: [{}],
                weatherlicense:{},
                qualification:{},
                uploadList:[],
            }
        },

        props: {
            licenseId: {
                type: Number,
                default: 0
            },
            licensetype: {
                type: String,
                default: ''
            },
        },
        watch:{
            licenseId:function(val){


                this.init(val)
            },
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            init(val){
                let id = val;
                let licensetype = '气象执照';
                let self = this;
                if (id != 0) {
                    self.$http.httpGet('/eim/api/qulification/search/license', {
                        id: id,
                        licensetype: licensetype
                    }).then((res) => {
                        let result = res.data;
                        console.log('result....', result);
                        self.qualification = result.data.personQulificationInfo;
                        self.weatherlicense = result.data.weatherLicense;
                        self.licenseChecks = result.data.licenseChecks;
                        if (result.data.personQulificationInfo.filePath != "" && result.data.personQulificationInfo.filePath != null) {
                            self.uploadList = self.qualification.filePath.split(",");
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                }
            },
         /*   backtotraininfo(){
                let self=this;
                self.$router.push({path: '/pilot'});
            },*/
            backtotraininfo(){
                let self=this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'weatherlicense'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'weatherlicense'}});
                }

            },
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

    .weatherfileshow>img{
        float: left;
        margin-left: 1%;

    }
</style>
