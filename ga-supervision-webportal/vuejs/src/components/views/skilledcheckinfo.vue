<template>
    <div>
        <div>
            <div>
                <div>
                    <table class="skilledcheckinfo">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center;" colspan="4">熟练检查详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 200px;text-align: center">熟练检查记录标单号</td>
                            <td class="tr-2 tr-6" style="width: 200px;">{{skilledCheckRecord.labelNo}}</td>
                            <td class="tr-1 tr-5" style="width: 200px;text-align: center">检查单位名称</td>
                            <td class="tr-2 tr-6" style="width: 200px;border-right: 0px;">{{skilledCheckRecord.checkUnit}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">定期检查设备类型</td>
                            <td class="tr-2 tr-4">{{skilledCheckRecord.type}}</td>
                            <td class="tr-1 tr-3" style="text-align: center">设备型号</td>
                            <td class="tr-2 tr-4" style="border-right: 0px;">{{skilledCheckRecord.equipmentModel}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">检查日期</td>
                            <td class="tr-2 tr-6">{{skilledCheckRecord.checkDate}}</td>
                            <td class="tr-1 tr-5" style="text-align: center">检查时间</td>
                            <td class="tr-2 tr-6" style="border-right: 0px;">{{skilledCheckRecord.checkTime}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">检查人</td>
                            <td class="tr-2 tr-4">{{skilledCheckRecord.checkPerson}}</td>
                            <td class="tr-1 tr-3" style="text-align: center">检查结果</td>
                            <td class="tr-2 tr-4" style="border-right: 0px;">{{skilledCheckRecord.checkResult}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="border-bottom: 0px;text-align: center">下次检查期限</td>
                            <td class="tr-2 tr-6" style="border-bottom: 0px;">{{skilledCheckRecord.nextTerm}}</td>
                            <td class="tr-1 tr-3" style="border-bottom: 0px;text-align: center">熟练检查工作实践单</td>
                            <td class="tr-2 tr-4" style="border-bottom: 0px;border-right: 0px;">{{skilledCheckRecord.checkList}}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    import noimg from '../../../static/img/noimgpictrue.png';
    import imgdetail from '../views/imgdetail.vue';
    export default {

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
                skilledCheckRecord:{
                        id: '',
                    pilotId: '',
                    labelNo:'',
                    checkUnit:'',
                    type:'',
                    equipmentModel:'',
                    checkDate: '',
                    checkTime: '',
                    checkPerson: '',
                    checkResult: '',
                    nextTerm: '',
                    checkList:''
                    },
            }
        },

        watch: {
            id (val) {
                this.getexaminfo(val);
            },
        },
        methods: {
            getexaminfo(val){
                let self = this;
                    self.$http.httpGet('/eim/api/skilledCheckRecord/serach/getbyId', {
                        id: val,
                    }).then(function (response) {
                        self.skilledCheckRecord = response.data;
                    }).catch(function (error) {
                        console.log(error);
                    });
            },
        },
    }
</script>
<style scoped>
    .skilledcheckinfo{
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
