<template>
    <div>
        <div>
            <div>
                <div v-show="open.base_info_open"  v-if="personQulificationInfo!=null" style="border-bottom-color:white">
                    <table class="train-info">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center;" colspan="5">雇员培训详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 298px;text-align: center">培训机构名称</td>
                            <td class="tr-2 tr-6" style="width: 298px;">{{trainingRecord.trainOorganization}}</td>
                            <td rowspan="6" colspan="2" style="background-color: #eff6fd">
                                <div v-if="trainingRecord.certificateScanning==''" style="width: 287px;height:180px;">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="noimg" alt=""/>
                                </div>
                                <div v-if="trainingRecord.certificateScanning!=''" style="width: 287px;height:180px;">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="trainingRecord.certificateScanning" alt=""  @click="imgdetail('',trainingRecord.certificateScanning)"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">培训科目</td>
                            <td class="tr-2 tr-4">{{trainingRecord.trainSubject}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">证明人</td>
                            <td class="tr-2 tr-6">{{trainingRecord.witness}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">开始时间</td>
                            <td class="tr-2 tr-4">{{trainingRecord.beginDate}}</td>

                        </tr>
                        <tr v-if="pilotLicense!=null">
                            <td class="tr-1 tr-5" style="text-align: center">结束时间</td>
                            <td class="tr-2 tr-6">{{trainingRecord.endDate}}</td>

                        </tr>
                        <tr v-if="pilotLicense!=null">
                            <td class="tr-1 tr-3" style="text-align: center">考核结果</td>
                            <td class="tr-2 tr-4" style="">{{trainingRecord.result}}</td>

                        </tr>
                        <tr v-if="pilotLicense!=null">
                            <td class="tr-1 tr-3" style="text-align: center">证明人联系方式</td>
                            <td class="tr-2 tr-4" style="">{{trainingRecord.witnessPhone}}</td>

                        </tr>
                        <tr v-if="pilotLicense!=null">
                            <td class="tr-1 tr-3" style="text-align: center">合格证编号</td>
                            <td class="tr-2 tr-4" style="">{{trainingRecord.certificateNo}}</td>

                        </tr>
                        <tr v-if="pilotLicense!=null">
                            <td class="tr-1 tr-3" style="border-bottom:0px;text-align: center">颁发日期</td>
                            <td class="tr-2 tr-4" style="border-bottom:0px;">{{trainingRecord.issueDate}}</td>

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
                trainingRecord:
                    {
                        id:'',
                        employeeid:'',
                        trainType: '',
                        trainSubject:'',
                        trainContent:'',
                        beginDate: '',
                        endDate: '',
                        createTime:'',
                        updateTime:'',
                        result: '',
                        certifactefile:'',
                        trainOorganization:'',
                        certificateScanning:'',
                        witness:'',
                        witnessPhone:'',
                        certificateNo:'',
                        issueDate:'',
                        flag: ''
                    },
            }
        },
        created () {

//            let id=this.$route.query.traininfo;


        },
        props: {
            id: {
                type: Number,
                default: 0
            }
        },
//
        watch:{
            id (val) {
                this.hehe(val);
            },
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            hehe(val){
                let self=this;
                let id=val;
                //alert(id);
                if(id!=0){
                    self.$http.httpGet('/eim/api/training/serach/getTrainingRecordById', {
                        id
                    }).then(function (response) {
                        self.trainingRecord=response.data;

                        console.log(self.trainingRecord,'............');

                    }) .catch(function (error) {
                        console.log(error);
                    });
                }

            },
            backtotraininfo(){
                let self=this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'traininfo'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'traininfo'}});
                }

            },

            modifyData(index) {
//                this.selected = index; // 修改的位置
//                this.selectedlist = this.list[index];
                this.visible = true;
            },
            onComplete: function () {
                //console.log(JSON.stringify(this.pilot));
                alert("done")
                this.visible = false;
            }
        }
    }
</script>
<style scoped>
    .train-info{
        width: 1190px;
        border: 1px solid #DCDFE6;
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
        border: 0px;
        border-bottom: solid 1px #DCDFE6;
    }
    .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        color:  #606265;
        font-size: 13px;
        border:solid 1px #DCDFE6;
        border-top:0px;
        text-align: center;
        background: #FFF;
    }

    .tr-3{
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
        /*background-color: rgb(239,246,253);*/
    /*}*/
    .tr-5{
        background-color: #f8f8f9;
    }
    /*.tr-6{*/
        /*background-color: rgb(239,246,253);*/
    /*}*/
</style>
