<template>
    <div>
        <div >
            <div>
                <div>
                    <table class="updatecheckinfo">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center;" colspan="4">更新检查详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 200px;text-align: center">更新检查记录标单号</td>
                            <td class="tr-2 tr-6" style="width: 200px;">{{updateCheckRecord.labelNo}}</td>
                            <td class="tr-1 tr-5" style="width: 200px;text-align: center">检查单位名称</td>
                            <td class="tr-2 tr-6" style="width: 200px;border-right: 0px;">{{updateCheckRecord.checkUnit}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">定期检查设备类型</td>
                            <td class="tr-2 tr-4">{{updateCheckRecord.type}}</td>
                            <td class="tr-1 tr-3" style="text-align: center">设备型号</td>
                            <td class="tr-2 tr-4" style="border-right: 0px;">{{updateCheckRecord.equipmentModel}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">检查日期</td>
                            <td class="tr-2 tr-6">{{updateCheckRecord.checkDate}}</td>
                            <td class="tr-1 tr-5" style="text-align: center">检查时间</td>
                            <td class="tr-2 tr-6" style="border-right: 0px;">{{updateCheckRecord.checkTime}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">检查人</td>
                            <td class="tr-2 tr-4">{{updateCheckRecord.checkPerson}}</td>
                            <td class="tr-1 tr-3" style="text-align: center">检查结果</td>
                            <td class="tr-2 tr-4" style="border-right: 0px;">{{updateCheckRecord.checkResult}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="border-bottom: 0px;text-align: center">下次检查期限</td>
                            <td class="tr-2 tr-6" style="border-bottom: 0px;">{{updateCheckRecord.nextTerm}}</td>
                            <td class="tr-1 tr-3" style="border-bottom: 0px;text-align: center">更新检查工作实践单</td>
                            <td class="tr-2 tr-4" style="border-bottom: 0px;border-right: 0px;">{{updateCheckRecord.checkList}}</td>
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
                updateCheckRecord:{
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


                    self.$http.httpGet('/eim/api/updateCheckRecord/serach/getbyId', {
                        id: val,

                    }).then(function (response) {

                        self.updateCheckRecord = response.data;
                    }).catch(function (error) {
                        console.log(error);
                    });

            },

        },
    }
</script>
<style scoped>
    .updatecheckinfo{
        width: 1190px;
        border:1px solid #DCDFE6;
        border-spacing: 0px;
    }
    .tr-1 {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border: 0px;
        border-bottom: solid 1px #55a2eb;
    }
    .tr-2 {
        padding-left: 10px;
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
    .tr-6{
        background-color: rgb(239,246,253);
    }


</style>
