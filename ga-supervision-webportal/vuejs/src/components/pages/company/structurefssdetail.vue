<template>
    <div style="width: 1280px; height: 900px; background:white">
        <div>
            <span style="width:95%; display:inline-block;text-align:right;margin-top: 10px; margin-bottom: 10px; padding-right: 5px;">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>
                <!--<Table border stripe :columns="columns" :data="data"></Table>-->
                <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                            飞行服务站详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3">名称</td>
                        <td class="tr-2 tr-4">{{structure.name}}</td>
                        <td class="tr-1 tr-3">编号</td>
                        <td class="tr-2 tr-4">{{structure.code}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">类型</td>
                        <td class="tr-2 tr-4">{{structure.type}}</td>
                        <td class="tr-1 tr-3">传真</td>
                        <td class="tr-2 tr-4">{{structure.faxNo}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">所属管理监管局</td>
                        <td class="tr-2 tr-4">{{structure.administrationId}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4">{{structure.atmbId}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">联系人</td>
                        <td class="tr-2 tr-4">{{structure.contacts}}</td>
                        <td class="tr-1 tr-3">联系方式</td>
                        <td class="tr-2 tr-4">{{structure.contactsTel}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">地址</td>
                        <td class="tr-2 tr-4">{{structure.address}}</td>
                        <td class="tr-1 tr-3">管辖空域形状</td>
                        <td class="tr-2 tr-4">{{structure.airspaceShape}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">空域形状经纬度(小数)</td>
                        <td class="tr-2 tr-4">{{structure.pointsDecimal}}</td>
                        <td class="tr-1 tr-3">空域形状经纬度(分秒)</td>
                        <td class="tr-2 tr-4">{{structure.pointsDFM}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">半径</td>
                        <td class="tr-2 tr-4">{{structure.radius}}</td>
                        <td class="tr-1 tr-3">创建时间</td>
                        <td class="tr-2 tr-4">{{structure.createTime}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">创建者</td>
                        <td class="tr-2 tr-4">{{structure.createUser}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: "structurefssdetail",
        data(){
            return{
                structure:{}
            }
        },
        methods:{
            black:function () {
                history.go(-1)
                //this.$router.push({path:'/blackstruct'})
            }
        },
        mounted(){
            this.structure = JSON.parse(this.$route.query.structure)
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