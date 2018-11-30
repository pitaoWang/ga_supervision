<template>
    <div>

        <div class="">
            <!--<div  class="gacompany-div" style="float: right; text-align: center; padding-bottom: 10px; padding-top: 10px; margin-left: 200px;">
                <a data-v-942377fa="" href="javascript:;" @click="backtotraininfo()" class="gacompany-btn">返回</a>
            </div>-->
            <div>

                <div class="cut" v-show="open.base_info_open"  v-if="personQulificationInfo!=null">
                    <table class="certificate" >
                        <tbody>
                        <td class="tr-1 tr-3" style=" !important; text-align: center;" colspan="4">雇员证书详细信息</td>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 318px;text-align: center">证书类型</td>
                            <td class="tr-2 tr-6" style="width: 318px;">{{trainingCertificate.certificatetype}}</td>
                            <td rowspan="6" colspan="2" style="background-color: #eff6fd">
                                <div v-if="trainingCertificate.certificatefile==''" style="width: 287px;height:180px;">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="noimg" alt=""/>
                                </div>
                                <div v-if="trainingCertificate.certificatefile!=''" style="width: 287px;height:180px;">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="trainingCertificate.certificatefile" @click="imgdetail('',trainingCertificate.certificatefile)" alt=""/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">证书编号</td>
                            <td class="tr-2 tr-4">{{trainingCertificate.certificateno}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">颁发单位</td>
                            <td class="tr-2 tr-6">{{trainingCertificate.issueoffice}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">颁发时间</td>
                            <td class="tr-2 tr-4">{{trainingCertificate.issuetime}}</td>

                        </tr>
                        <tr v-if="pilotLicense!=null">
                            <td class="tr-1 tr-5" style="border-bottom: 0px;text-align: center">有效时间</td>
                            <td class="tr-2 tr-6" style="border-bottom: 0px;">{{trainingCertificate.expiretime}}</td>

                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>

<script>
    import noimg from '../../../static/img/noimgpictrue.png';
    import imgdetail from '../views/imgdetail.vue';
    export default {
        components: {
            imgdetail
        },
        data () {
            return {
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

                trainingCertificate: {
                    id:'',
                    certificatetype:'',
                    employeeid:'',
                    certificateno:'',
                    issueoffice:'',
                    issuetime: '',
                    expiretime: '',
                    certificatefile:''
                },
            }
        },

        props: {
            id: {
                type: Number,
                default: 0
            }
        },
        watch:{
            id (val) {
                this.clickfind(val);
                //this.backtotraininfo();
            },
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            clickfind(val) {
                //原来的
//            let id=this.$route.query.traincertificateId;
                let id = val;
                let self = this;
                if (id != 0) {
                    self.$http.httpGet('/eim/api/trainingcertificate/serach/getTrainingRecordById', {
                        id
                    }).then(function (response) {
                        self.trainingCertificate = response.data;

                        console.log(self.trainingRecord, '............');

                    }).catch(function (error) {
                        console.log(error);
                    });
                }
            },
            backtotraininfo(){

                    let self=this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'traincertificate'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'traincertificate'}});
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
    .certificate{
        width: 1190px;
        border:1px solid #DCDFE6;
        margin-left: 40px;
        border-spacing: 0px;
        margin-bottom: 30px;
    }
    .tr-1 {
        font-size: 14px;
        width: 200px;
        padding-left: 13px;
        height: 48px;
        color:  #606265;
        border:0px;
        border-bottom: solid 1px #DCDFE6;
    }
    .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        font-size: 14px;
        border:solid 1px #DCDFE6;
        border-top:0px;
        text-align: center;
        background:#FFF;
    }

    .tr-3{
        background:#f8f8f9;
    }

    /*.tr-4{*/
        /*background-color: rgb(218,236,252);*/
    /*}*/
    .tr-5{
        background:#f8f8f9;
    }
    /*.tr-6{*/
        /*background-color: rgb(239,246,253);*/
    /*}*/
</style>
