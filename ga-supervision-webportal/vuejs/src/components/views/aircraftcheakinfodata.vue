<template>
    <div>
        <div class="rightPart" >
            <div>
                <div class="cut" style="border-bottom-color:white">
                    <table class="check-info" >
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style=" !important; text-align: center;width: 750px;" colspan="4">航空器检查记录详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="width: 200px;text-align: center">检查类型</td>
                            <td class="tr-2 tr-4" style="width: 200px;">{{checkinfo.checktype}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">检查内容</td>
                            <td class="tr-2 tr-6">{{checkinfo.checkcontent}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">检查等级</td>
                            <td class="tr-2 tr-4">{{checkinfo.grade}}</td>
                            <td rowspan="5" colspan="2">
                                <div v-if="checkinfo.checkscanpath==''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="noimg" alt=""/>
                                </div>
                                <div v-if="checkinfo.checkscanpath!=''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="checkinfo.scoreFile" alt=""/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">检查结果</td>
                            <td class="tr-2 tr-6">{{checkinfo.checkresult}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">检查有限起始时间</td>
                            <td class="tr-2 tr-4">{{checkinfo.begindate}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">检查有效截止时间</td>
                            <td class="tr-2 tr-6">{{checkinfo.enddate}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">检查历史版本信息</td>
                            <td class="tr-2 tr-4">{{checkinfo.version}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">检查单位名称</td>
                            <td class="tr-2 tr-6">{{checkinfo.orgname}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">检查组织者姓名</td>
                            <td class="tr-2 tr-4">{{checkinfo.organigername}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">检查参与者</td>
                            <td class="tr-2 tr-6">{{checkinfo.participatorname}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">检查描述</td>
                            <td class="tr-2 tr-4">{{checkinfo.description}}</td>
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
    export default {
        components: {
        },
        data () {
            return {
                noimg: noimg,
                checkinfo:{},

            }
        },
        created () {
            this.getcheckinfodata();

        },
        props: ['id'],
        watch:{
            id (val) {
                this.getcheckinfodata(val);
            },
        },
        methods: {
            getcheckinfodata(){
                let self = this;
                self.$http.httpGet('/app/api/ak/'+this.id,{}).then(function (response) {
                    self.checkinfo = response.data;
                    if(self.checkinfo.checktype == "0"){
                        self.checkinfo.checktype = "定检"
                    }
                    else{
                        self.checkinfo.checktype = "年检"
                    }
                }).catch(function (error) {
                    console.log(error);
                });


            },
        }
    }
</script>
<style scoped>
    .check-info{
        width: 750px;
        display: inline-block;
        border:1px solid rgb(1,107,217);
    }
    .check-info .tr-1 {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border-bottom: solid 1px #55a2eb;
    }

    .check-info .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border-left: solid 1px #55a2eb;
        border-bottom: solid 1px #55a2eb;
        text-align: center;
    }

    .check-info .tr-3{
        background-color: rgb(179,221,252);
    }

    .check-info .tr-4{
        background-color: rgb(218,236,252);
    }
    .check-info .tr-5{
        background-color: rgb(201,231,253);
    }

    .check-info .tr-6{
        background-color: rgb(239,246,253);
    }

    .infoPart1 table{
        border-collapse:collapse;
        border-spacing:0;
        width: 100%;}

</style>
