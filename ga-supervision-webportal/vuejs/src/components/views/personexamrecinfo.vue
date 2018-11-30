<template>
    <div>
        <div class="" >
            <div>
                <div class="cut" style="border-bottom-color:white">
                    <table class="personexam">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center;" colspan="4">雇员考试详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 200px;text-align: center">考试科目</td>
                            <td class="tr-2 tr-6" style="width: 200px;">{{personExamRecord.subject}}</td>
                            <td rowspan="6" colspan="2" style="background-color: rgba(255,255,255,0.65)">
                                <div v-if="personExamRecord.scanning == ''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="noimg" alt=""/>
                                </div>
                                <div v-if="personExamRecord.scanning != ''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="personExamRecord.scanning" @click="imgdetail('',personExamRecord.scanning)" alt=""/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">考试成绩</td>
                            <td class="tr-2 tr-4">{{personExamRecord.score}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">考试合格证编号</td>
                            <td class="tr-2 tr-6">{{personExamRecord.certificateNo}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">考试日期</td>
                            <td class="tr-2 tr-4">{{personExamRecord.exameDate}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">颁发单位</td>
                            <td class="tr-2 tr-6">{{personExamRecord.issueUnit}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="border-bottom: 0px;text-align: center">颁发时间</td>
                            <td class="tr-2 tr-4" style="border-bottom: 0px;">{{personExamRecord.issueDate}}</td>
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
        props: {
            id: {
                type: Number,
                default: 0
            }
        },
        data () {
            return {
                companyId:1,
                noimg: noimg,
                personExamRecord:{
                        id: '',
                        employeeid: '',
                        certificateNo:'',
                        issueDate:'',
                        issueUnit:'',
                        scanning:'',
                        subject: '',
                        score: '',
                        exameDate: '',
                        examSite: '',
                        examTeacherId: '',
                        scoreFile:'',
                        createTime:'',
                        updateTime:'',
                        flag:'',
                    },

            }
        },

        watch: {
            id (val) {

                this.getexaminfo(val);
            },
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            getexaminfo(val){
                let self = this;


                    self.$http.httpGet('/eim/api/personexam/serach/getbyId', {
                        id: val,
                    }).then(function (response) {
                        self.personExamRecord = response.data;
                    }).catch(function (error) {
                        console.log(error);
                    });

            },
            backtotraininfo(){
                let self=this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'personexamrecinfo'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'personexamrecinfo'}});
                }

            },
        },
    }
</script>
<style scoped>
    .personexam{
        width: 1190px;
        border:1px solid #DCDFE6;
        border-spacing: 0px;
        margin-left: 40px;
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
