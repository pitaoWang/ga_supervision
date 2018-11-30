<template>
    <div id="faultreserveinfo" style="width: 1280px; height:auto; display: inline-block;background: white;">
        <Form style="">
            <div>
                <div style="width: 100%;; display:inline-block;text-align:right;">
                    <!--<Button @click="onclose" class="cancelBtn" style="width: 100px;">返回</Button>-->

                    <span style="width:100%; display:inline-block;text-align:right;margin-top:10px;margin-bottom: 10px;margin-left: 20px; ">
                    <Button primary @click="onclose()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
                </div>
            </div>
        </Form>
        <table style="width: 100%; border-spacing:0px; margin-left: 20px">
            <tbody>
            <tr>
                <td class="tr-1 tr-3">故障保留单编号</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.no}}</td>
                <td class="tr-1 tr-3">航空器型号</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.aircraftModel}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">航空器注册号</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.aircraftRegNo}}</td>
                <td class="tr-1 tr-3">保留单位</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.reserveCompany}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">ATA章节</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.ataSection}}</td>
                <td class="tr-1 tr-3">保留期限</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.reserveDate}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">保留故障描述</td>
                <td class="tr-2 tr-4" colspan="3">{{faultreserveinfo.faultDescribe}}</td>

            </tr>
            <tr>
                <td class="tr-1 tr-3">保留故障原因</td>
                <td class="tr-2 tr-4" colspan="3">{{faultreserveinfo.faultReserveCause}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">保留依据</td>
                <td class="tr-2 tr-4" colspan="3">{{faultreserveinfo.reserveBasis}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">已采取措施</td>
                <td class="tr-2 tr-4" colspan="3">{{faultreserveinfo.measuresTaken}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">处理状态</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.status}}</td>
                <td class="tr-1 tr-3">申请人</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.apply}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">申请时间</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.applyTime}}</td>
                <td class="tr-1 tr-3">审批人</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.approver}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">审批时间</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.approveTime}}</td>
                <td class="tr-1 tr-3">完成人</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.completor}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">完成时间</td>
                <td class="tr-2 tr-4">{{faultreserveinfo.completeTime}}</td>
                <td class="tr-1 tr-3"></td>
                <td class="tr-2 tr-4"></td>
            </tr>

            </tbody>
        </table>
    </div>
</template>

<script>
export default {
  name: "faultreserveinfo",
  props: ['id'],
  data() {
    return {
      faultreserveinfo: {
        no: "",
        ataSection: "",
        aircraftModel: "",
        aircraftRegNo: "",
        reserveCompany: "",
        reserveDate: "",
        faultDescribe: "",
        faultReserveCause: "",
        reserveBasis: "",
        status: "",
        measuresTaken: "",
        apply: "",
        applyTime: "",
        approver: "",
        approveTime: "",
        completor: "",
        completeTime: ""
      },
      aircraft : {},
    };
  },
  created () {
      this.id = this.$route.query.Id;
      this.aircraft=this.$route.query.aircraft;
     // console.info(this.aircraft);
      this.getFaultReserveById(this.id);
  },
  methods: {
    onclose: function() {
       this.$router.push({path:'/aircraftmonitoring/tabs', query: {value:'faultreserve', regNumber: this.faultreserveinfo.aircraftRegNo, aircraft: this.aircraft}})
    },
    getFaultReserveById: function (id) {
        var self = this;
        self.$http.httpGet('/eim/api/mmis/getFaultReserveById', {
            id: id,
        }).then((res) => {
            self.faultreserveinfo = res.data;

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
#faultreserveinfo table td {
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
#faultreserveinfo .ivu-table-tbody td:nth-child(2n-1) {
    background-color: #F8F8F9;
}
</style>

