<template>
    <div>
        <div class="">
            <div>
                <div class="cut" style="border-bottom-color:white">
                    <table class="physic" >
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style=" ;text-align: center;" colspan="4">雇员体检详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">合格级别</td>
                            <td class="tr-2 tr-6" >{{physicalRecord.certificateGrade}}</td>
                            <td rowspan="5" colspan="2" style="background-color: #eff6fd">
                                <div v-if="physicalRecord.certificateFilepath==''" style="height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" :src="noimg" alt=""/>
                                </div>
                                <div v-if="physicalRecord.certificateFilepath!=''" style="height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" :src="physicalRecord.certificateFilepath"  @click="imgdetail('',physicalRecord.certificateFilepath)" alt=""/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">合格状态</td>
                            <td class="tr-2 tr-4">{{physicalRecord.passStatus}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">颁发时间</td>
                            <td class="tr-2 tr-6">{{physicalRecord.certificateStartTime}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">截止时间</td>
                            <td class="tr-2 tr-4">{{physicalRecord.certificateValidityTime}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="border-bottom: 0px;text-align: center">备注</td>
                            <td class="tr-2 tr-6" style="border-bottom: 0px;">{{physicalRecord.remark}}</td>
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
                physicalRecord:{
                    id:'',
                    employeeid:'',
                    certificateGrade: '',
                    certificateStartTime: '',
                    certificateValidityTime: '',
                    passStatus:'',
                    remark: '',
                    certificateFilepath: '',
                    createTime:'',
                    updateTime:'',
                    flag: '',
                }

            }
        },


        watch: {
            id (val) {
                this.getphysicalinfo(val);
            },
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            getphysicalinfo(val){
                let self = this;
                let id=val;
                if(id != 0){
                    self.$http.httpGet('/eim/api/physical/serach/getbyid', {
                        id: id
                    }).then(function (response) {
                        self.physicalRecord = response.data;
                        if(self.physicalRecord.passStatus == "0"){
                            self.physicalRecord.passStatus = "通过";
                        }
                        else{
                            self.physicalRecord.passStatus = "不通过"
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                }
            },
            backtotraininfo(){
                let self=this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'physicalrecinfo'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'physicalrecinfo'}});
                }

            }
        },
    }
</script>
<style scoped>
    .physic{
        width: 965px;
        border-spacing: 0;
        border:1px solid rgb(1,107,217);
    }
    .physic .tr-1 {
        font-size: 14px;
        width: 318px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border:0;
        border-bottom: solid 1px #55a2eb;
    }
    .physic .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        width: 318px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border:solid 1px #55a2eb;
        border-top:0px;
        text-align: center;
    }
    .physic .tr-3{
        background-color: rgb(179,221,252);
    }

    .physic .tr-4{
        background-color: rgb(218,236,252);
    }
    .physic .tr-5{
        background-color: rgb(201,231,253);
    }
    .physic .tr-6{
        background-color: rgb(239,246,253);
    }

    .div-chxun{
        margin-bottom: 15px;
        height: 30px;
    }

    .div-input{
        height: 30px;
        width: 190px;
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
