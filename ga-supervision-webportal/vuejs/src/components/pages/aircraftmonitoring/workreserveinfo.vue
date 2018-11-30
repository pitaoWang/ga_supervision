<template>
    <div id="workreserveinfo" style="width: 1190px; height:auto; display: inline-block;background: white;">
        <Form style="">
            <div>
                <!--<div style="width: 100%;; display:inline-block;text-align:right;">
                    <Button @click="onclose" class="cancelBtn" style="width: 100px;">返 回</Button>
                </div>-->
                <span style="width:100%; display:inline-block;text-align:right;margin-top:10px;margin-bottom: 10px;margin-left: 20px; ">
                    <Button primary @click="onclose()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            </div>
        </Form>

        <table style="width: 100%; border-spacing:0px; margin-left: 20px">
            <tbody>
            <tr>
                <td class="tr-1 tr-3">工作保留单编号</td>
                <td class="tr-2 tr-4">{{workreserveinfo.id}}</td>
                <td class="tr-1 tr-3">航空器型号</td>
                <td class="tr-2 tr-4">{{workreserveinfo.aircraftModel}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">航空器注册号</td>
                <td class="tr-2 tr-4">{{workreserveinfo.aircraftRegNo}}</td>
                <td class="tr-1 tr-3">保留单位</td>
                <td class="tr-2 tr-4">{{workreserveinfo.companyName}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">工作单卡编号</td>
                <td class="tr-2 tr-4">{{workreserveinfo.wocNo}}</td>
                <td class="tr-1 tr-3">工作单卡标题</td>
                <td class="tr-2 tr-4">{{workreserveinfo.wocTitle}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">推迟原因</td>
                <td class="tr-2 tr-4" colspan="3">{{workreserveinfo.delayReason}}</td>

            </tr>
            <tr>
                <td class="tr-1 tr-3">推迟依据</td>
                <td class="tr-2 tr-4" colspan="3">{{workreserveinfo.delayBasis}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">处理状态</td>
                <td class="tr-2 tr-4">{{workreserveinfo.status}}</td>
                <td class="tr-1 tr-3">推迟期限</td>
                <td class="tr-2 tr-4">{{workreserveinfo.delayDate}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">申请人</td>
                <td class="tr-2 tr-4">{{workreserveinfo.applicant}}</td>
                <td class="tr-1 tr-3">申请时间</td>
                <td class="tr-2 tr-4">{{workreserveinfo.applyTime}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">审批人</td>
                <td class="tr-2 tr-4">{{workreserveinfo.approver}}</td>
                <td class="tr-1 tr-3">审批时间</td>
                <td class="tr-2 tr-4">{{workreserveinfo.approveTime}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">完成人</td>
                <td class="tr-2 tr-4">{{workreserveinfo.completor}}</td>
                <td class="tr-1 tr-3">完成时间</td>
                <td class="tr-2 tr-4">{{workreserveinfo.completeTime}}</td>
            </tr>

            </tbody>
        </table>
    </div>
</template>

<script>
export default {
  name: "workreserveinfo",
  props: ['id'],
  data() {
    return {
      workreserveinfo: {
        id: "",
        wocNo: "",
        aircraftModel: "",
        aircraftRegNo: "",
        companyName: "",
        wocTitle: "",
        delayReason: "",
        delayBasis: "",
        status: "",
        delayDate: "",
        applicant: "",
        applyTime: "",
        approver: "",
        approveTime: "",
        completor: "",
        completeTime: ""
      },
      aircraft : {}
    };
  },
  created () {
      this.id = this.$route.query.Id;
      this.aircraft=this.$route.query.aircraft;
      this.getWorkReserveById(this.id);
  },
  methods: {
    onclose: function() {
       this.$router.push({path:'/aircraftmonitoring/tabs',query: {value:'workreserve', regNumber: this.workreserveinfo.aircraftRegNo, aircraft: this.aircraft}})
    },
    getWorkReserveById: function (id) {
        var self = this;
        self.$http.httpGet('/eim/api/mmis/getWorkReserveById', {
            id: id
        }).then((res) => {
            self.workreserveinfo = res.data;

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
#workreserveinfo table td {
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
#workreserveinfo .ivu-table-tbody td:nth-child(2n-1) {
    background-color: #F8F8F9;
}
</style>

