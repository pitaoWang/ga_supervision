<template>
    <div>
        <div  >
            <div>
                <div class="cut" style="border-bottom-color:white">
                    <table class="personexam" style="border: 1px solid rgb(1,107,217);width: 965px">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style=" !important; text-align: center;" colspan="4">雇员考试详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 200px;text-align: center">考试科目</td>
                            <td class="tr-2 tr-4" style="width: 200px;">{{personExamRecord.subject}}</td>
                            <td rowspan="6" colspan="2" style="background-color: #eff6fd">
                                <div v-if="personExamRecord.scoreFile == ''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="noimg" alt=""/>
                                </div>
                                <div v-if="personExamRecord.scoreFile != ''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="personExamRecord.scoreFile" @click="imgdetail('',personExamRecord.scoreFile)" alt=""/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">考试成绩</td>
                            <td class="tr-2 tr-4">{{personExamRecord.score}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">是否通过</td>
                            <td class="tr-2 tr-4">{{personExamRecord.passStatus}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">考试日期</td>
                            <td class="tr-2 tr-4">{{personExamRecord.exameDate}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">考试地点</td>
                            <td class="tr-2 tr-4">{{personExamRecord.examSite}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="border-bottom: 0px;text-align: center">检查员/考试员</td>
                            <td class="tr-2 tr-4" style="border-bottom: 0px">{{personExamRecord.examTeacherId}}</td>
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
    import noimg from '../../../../../static/img/noimgpictrue.png';
    import imgdetail from './imgdetail.vue';
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
        width: 740px;
        border:1px solid rgb(1,107,217);
        border-spacing: 0;
    }
    .tr-1 {
        font-size: 14px;
        width: 318px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border:0px;
        border-bottom: solid 1px #55a2eb;
    }
    .tr-2 {
        padding-left: 10px;
        width: 318px;
        padding-right: 10px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border:solid 1px #55a2eb;
        border-top:0px;
        text-align: center;
    }

    .tr-3{
        background-color: rgb(179,221,252);
    }

    .tr-4{
        background-color: rgb(218,236,252);
    }
    .tr-5{
        background-color: rgb(201,231,253);
    }

</style>
