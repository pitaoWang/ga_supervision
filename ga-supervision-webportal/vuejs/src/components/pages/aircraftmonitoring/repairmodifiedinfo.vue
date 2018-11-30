<template>
    <div id="repairmodifiedinfo" style="width: 1280px; height:auto; display: inline-block;background: white;">
        <Form style="">
            <div>
                <!--<div style="width: 100%;; display:inline-block;text-align:right;">
                    <Button @click="onclose" class="cancelBtn">返回</Button>
                </div>-->
                <span style="width:100%; display:inline-block;text-align:right;margin-top:10px;margin-bottom: 10px;margin-left: 20px; ">
                    <Button primary @click="onclose()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

             </span>
            </div>
        </Form>
        <!--<table cellspacing="0" cellpadding="0" border="0" class="ivu-table ivu-table-border" style="border: 1px solid #dcdee2; width: 100%;margin-left: 20px;">
            <tbody class="ivu-table-tbody">
                <tr class="ivu-table-row">
                    <td>
                        <div class="ivu-table-cell">
                            <span>记录编号</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.no}}</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>公司名称</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.companyName}}</span>
                        </div>
                    </td>
                </tr>
                <tr class="ivu-table-row">
                    <td>
                        <div class="ivu-table-cell">
                            <span>航空器制造厂家</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.aircraftManufacturer}}</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>航空器型号</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.aircraftModel}}</span>
                        </div>
                    </td>
                </tr>
                <tr class="ivu-table-row">
                    <td>
                        <div class="ivu-table-cell">
                            <span>航空器注册号</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.aircraftRegNo}}</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>所有人名称</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.possessor}}</span>
                        </div>
                    </td>
                </tr>
                <tr class="ivu-table-row">
                    <td>
                        <div class="ivu-table-cell">
                            <span>地址</span>
                        </div>
                    </td>
                    <td colspan="3">
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.address}}</span>
                        </div>
                    </td>
                </tr>
                <tr class="ivu-table-row">
                    <td>
                        <div class="ivu-table-cell">
                            <span>类型</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.type}}</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>项目</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.project}}</span>
                        </div>
                    </td>
                </tr>
                <tr class="ivu-table-row">
                    <td>
                        <div class="ivu-table-cell">
                            <span>放行人</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.releaser}}</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>放行时间</span>
                        </div>
                    </td>
                    <td>
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.releaseTime}}</span>
                        </div>
                    </td>
                </tr>
                <tr class="ivu-table-row">
                    <td>
                        <div class="ivu-table-cell">
                            <span>维修工作概述</span>
                        </div>
                    </td>
                    <td colspan="3">
                        <div class="ivu-table-cell">
                            <span>{{repairmodifiedinfo.mcSummary}}</span>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>-->

        <table style="width: 100%; border-spacing:0px; margin-left: 20px">
            <tbody>
            <tr>
                <td class="tr-1 tr-3">记录编号</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.no}}</td>
                <td class="tr-1 tr-3">公司名称</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.companyName}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">航空器制造厂家</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.aircraftManufacturer}}</td>
                <td class="tr-1 tr-3">航空器型号</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.aircraftModel}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">航空器注册号</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.aircraftRegNo}}</td>
                <td class="tr-1 tr-3">所有人名称</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.possessor}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">地址</td>
                <td class="tr-2 tr-4" colspan="3">{{repairmodifiedinfo.address}}</td>

            </tr>
            <tr>
                <td class="tr-1 tr-3">类型</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.type}}</td>
                <td class="tr-1 tr-3">项目</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.project}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">放行人</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.releaser}}</td>
                <td class="tr-1 tr-3">放行时间</td>
                <td class="tr-2 tr-4">{{repairmodifiedinfo.releaseTime}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">维修工作概述</td>
                <td class="tr-2 tr-4" colspan="3">{{repairmodifiedinfo.mcSummary}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
  name: "repairmodifiedinfo",
  props: ['id'],
  data() {
    return {
      repairmodifiedinfo: {},
        aircraft : {}
    };
  },
  created: function() {
      this.id = this.$route.query.Id;
      this.aircraft=this.$route.query.aircraft;
      this.getRepairModifiedById(this.id);
  },
  methods: {
    onclose: function() {
       this.$router.push({path:'/aircraftmonitoring/tabs',query: {value:'repairmodified', regNumber: this.repairmodifiedinfo.aircraftRegNo, aircraft: this.aircraft }})
    },
    getRepairModifiedById: function (id) {
        var self = this;
        self.$http.httpGet('/eim/api/mmis/getRepairModifiedById', {
            id: id
        }).then((res) => {
            console.log("getWorkListByPage res", res);
            self.repairmodifiedinfo = res.data;

        }).catch(function (error) {
            console.log(error);
        });
    }
  }
};
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
#repairmodifiedinfo table td {
    width: 25%;
    font-size: 14px;
}
.cancelBtn {
  width: 98px;
  height: 32px;
  /* margin:24px 43px 11px 0px; */
  border: 1px dashed #2b82da;
  background: #ddf4ff;
  font-size: 14px;
  color: #2fa5fb;
}
#repairmodifiedinfo .ivu-table-tbody td:nth-child(2n-1) {
    background-color: #F8F8F9;
}
</style>

