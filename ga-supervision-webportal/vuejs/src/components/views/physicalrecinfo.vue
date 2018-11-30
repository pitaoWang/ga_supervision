<template>
    <div>
        <div class="">
            <div>
                <div class="cut">
                    <table class="physic" >
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center;" colspan="4">雇员体检详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 200px;text-align: center">合格级别</td>
                            <td class="tr-2 tr-6" style="width: 200px;">{{physicalRecord.certificateGrade}}</td>
                            <td rowspan="5" colspan="2" style="background-color: rgba(255,255,255,0.65)">
                                <div v-if="physicalRecord.certificateFilepath==''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" :src="noimg" alt=""/>
                                </div>
                                <div v-if="physicalRecord.certificateFilepath!=''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" :src="physicalRecord.certificateFilepath"  @click="imgdetail('',physicalRecord.certificateFilepath)" alt=""/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">体检合格证编号</td>
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
                            <td class="tr-1 tr-5" style="text-align: center;border-bottom: 0px">发证单位</td>
                            <td class="tr-2 tr-6" style="border-bottom: 0px">{{physicalRecord.issueUnit}}</td>
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
                physicalRecord:{
                    id:'',
                    employeeid:'',
                    issueUnit:'',
                    passStatus:'',
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
                            self.physicalRecord.F = "通过";
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
        width: 1190px;
        border:1px solid #DCDFE6;
        margin-left: 40px;
        border-spacing: 0px;
        margin-bottom: 30px;
    }
    .physic .tr-1 {
        font-size: 14px;
        width: 200px;
        padding-left: 13px;
        height: 48px;
        color:  #606265;
        border:0px;
        border-bottom: solid 1px #DCDFE6;
    }
    .physic .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        font-size: 14px;
        border:solid 1px #DCDFE6;
        border-top:0px;
        text-align: center;
        background:#FFF;
    }
    .physic .tr-3{
        background:#f8f8f9;
    }

    /*.physic .tr-4{*/
        /*background-color: rgb(218,236,252);*/
    /*}*/
    .physic .tr-5{
        background:#f8f8f9;
    }
    /*.physic .tr-6{*/
        /*background-color: rgb(239,246,253);*/
    /*}*/

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
