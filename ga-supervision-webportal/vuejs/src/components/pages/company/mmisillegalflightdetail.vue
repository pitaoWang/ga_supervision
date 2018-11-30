<template>
    <div style="width: 1280px; height: 900px; background:white">
        <div>
        <span style="width:96%; display:inline-block;text-align:right;margin-top: 10px; margin-bottom: 10px; padding-right: 17px;">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>
                <!--<Table border stripe :columns="columns" :data="data"></Table>-->
                <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="6">
                            违规飞行记录详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3">违规单位名称</td>
                        <td class="tr-2 tr-4">{{mmisillegalflight.name}}</td>
                        <td class="tr-1 tr-3">航空器注册号</td>
                        <td class="tr-2 tr-4">{{mmisillegalflight.aircraftId}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">违规开始时间</td>
                        <td class="tr-2 tr-4">{{mmisillegalflight.starttime}}</td>
                        <td class="tr-1 tr-3">违规结束时间</td>
                        <td class="tr-2 tr-4">{{mmisillegalflight.endtime}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">违规原因</td>
                        <td class="tr-2 tr-4">{{mmisillegalflight.reason}}</td>
                        <td class="tr-1 tr-3">违规说明</td>
                        <td class="tr-2 tr-4">{{mmisillegalflight.explain}}</td>

                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "mmisillegalflightdetail",
        data(){
            return{
                mmisillegalflight:{},
            }
        },
        methods:{
            black:function () {
                history.go(-1);
            }
        },
        mounted(){
            this.mmisillegalflight = this.$route.query.mmisillegalflight
        }
    }
</script>

<style scoped>
    .tr-1 {
        font-size: 14px;
        width: 180px;
        padding-left: 13px;
        height: 45px;
        color: #606265;
        border-left: solid 1px #DCDFE6;
        border-right: solid 1px #DCDFE6;
        border-bottom: solid 1px #DCDFE6;
        background-color: #F8F8F9;
    }

    .tr-2 {
        font-size: 14px;
        width: 200px;
        text-align: center;
        color: #606265;
        background-color: #FFF;
    }

    table {
        border-collapse: collapse;
    }

    table tr td {
        border: 1px solid #DCDFE6;
    }

    .tr-3 {
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
    /*background-color: #FFF;*/
    /*}*/
    .tr-5 {
        background-color: #f8f8f9;
    }

    .flightdispatcherimg > img {
        float: left;
        margin-left: 1%;

    }

    .infoPart1 table {
        border-collapse: collapse;
        border-spacing: 0;
        border-left: 1px solid #888;
        border-top: 1px solid #888;
        background: #efefef;
        width: 100%;
    }

    .div-button {
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }
</style>