<template>
    <div style="width: 1280px; height: 900px; background:white">
        <div>
        <span style="width:96%; display:inline-block;text-align:right;margin-top: 10px; margin-bottom: 10px;padding-right: 17px; ">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>
                <!--<Table border  :columns="columns" :data="data"></Table>-->
                <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                            监管发动机详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3">发动机型号</td>
                        <td class="tr-2 tr-4">{{mmisbaseengine.model}}</td>
                        <td class="tr-1 tr-3">序号</td>
                        <td class="tr-2 tr-4">{{mmisbaseengine.serial}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">循环数寿命(次)</td>
                        <td class="tr-2 tr-4">{{mmisbaseengine.lifeCycleTime}}</td>
                        <td class="tr-1 tr-3">剩余循环数(次)</td>
                        <td class="tr-2 tr-4">{{mmisbaseengine.remainFlyFime}}</td>


                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">装机状态</td>
                        <td class="tr-2 tr-4">{{mmisbaseengine.equipmentStatus}}</td>
                        <td class="tr-1 tr-3">装机日期</td>
                        <td class="tr-2 tr-4">{{mmisbaseengine.equipmentDate}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">装机航空器</td>
                        <td class="tr-2 tr-4">{{mmisbaseengine.aircraftId}}</td>
                        <td class="tr-1 tr-3">飞行小时数寿命(小时)</td>
                        <td class="tr-2 tr-4">{{mmisbaseengine.lifeFlyTime}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">生产厂家</td>
                        <td class="tr-2 tr-4" colspan="3">{{mmisbaseengine.manufacturer}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="bottom5" style="margin-top:20px;">
                <Table style="width: 1190px; margin-left: 20px; margin-bottom: 40px"  :columns="columns"
                       :data="data"></Table>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "mmisbaseenginedetail",
        data(){
            return{
                columns: [
                    {
                        title: '记录类型',
                        key: 'installStatus',
                        align:'center',
                        width:300,
                        render: (h, params) => {
                            let texts = '';
                            if (params.row.installStatus == 1) {
                                texts = "安装"
                            } else if (params.row.installStatus == 2) {
                                texts = "拆卸"
                            }
                            /*return h('div', {
                                props: {},
                            }, texts)*/
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: texts
                                    }
                                }, texts)
                            ]);
                        }
                    },

                    {
                        title: '航空器注册号',
                        key: 'aircraftRegNo',
                        align:'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aircraftRegNo
                                    }
                                }, params.row.aircraftRegNo)
                            ]);

                        }
                    },
                    {
                        title: '完成人',
                        key: 'completeOperson',
                        align:'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.completeOperson
                                    }
                                }, params.row.completeOperson)
                            ]);

                        }
                    },
                    {
                        title: '完成时间',
                        key: 'installDate',
                        align:'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.installDate
                                    }
                                }, params.row.installDate)
                            ]);

                        }

                    },
                ],
                data:[],
                mmisbaseengine:{}
            }
        },
        methods:{
            searchistory(id){
                this.$http.httpGet('/eim/api/mmisenginehis/findbyengineId?engineId='+id).then(res=>{
                    this.data = res.data;
                });
            },
            black:function () {
                history.go(-1);
            }
        },
        mounted(){
            this.mmisbaseengine = this.$route.query.mmisbaseengine
            if(this.mmisbaseengine.equipmentStatus==1){
                this.mmisbaseengine.equipmentStatus='未装机'
            }else if(this.mmisbaseengine.equipmentStatus==2){
                this.mmisbaseengine.equipmentStatus='已装机'
            }
            this.searchistory(this.mmisbaseengine.id);
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