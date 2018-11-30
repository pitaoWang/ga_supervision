<template>
    <div id="engine" style="width: 100%; height:auto; display: inline-block;background: white;padding: 0px 20px;margin-bottom:16px;">
        <div v-for="(engine, index) in engines" :key="index">
            <span class="tabletitle">发动机{{index+1}}</span>

            <table style="width: 100%; border-spacing:0px; margin-left: 20px">
                <tbody>
                <tr>
                    <td class="tr-1 tr-3">发动机型号</td>
                    <td class="tr-2 tr-4">{{engine.model}}</td>
                    <td class="tr-1 tr-3">序号</td>
                    <td class="tr-2 tr-4">{{engine.serial}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">循环数寿命（次）</td>
                    <td class="tr-2 tr-4">{{engine.lifeCycleTime}}</td>
                    <td class="tr-1 tr-3">装机日期</td>
                    <td class="tr-2 tr-4">{{engine.equipmentDate}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">剩余循环数（次）</td>
                    <td class="tr-2 tr-4">{{engine.remainCycleTime}}</td>
                    <td class="tr-1 tr-3">装机航空器</td>
                    <td class="tr-2 tr-4">{{engine.aircraftId}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">飞行小时数寿命（小时）</td>
                    <td class="tr-2 tr-4">{{engine.remainFlyTime}}</td>
                    <td class="tr-1 tr-3">生产厂家</td>
                    <td class="tr-2 tr-4">{{engine.manufacturer}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
export default {
  name: "engine",
  props: ['id'],
  data() {
    return {
      engines: [{
        model: "",
        serial: "",
        lifeCycleTime: "",
        equipmentDate: "",
        remainCycleTime: "",
        aircraftId: "",
        remainFlyTime: "",

        reserveDate: "",
        faultDescribe: "",
        faultReserveCause: "",
        reserveBasis: "",
        status: "",
        delayDate: "",
        applicant: "",
        applyTime: "",
        approver: "",
        approveTime: "",
        completor: "",
        completorTime: ""
      }]
    };
  },
  props: ['regNumber'],
  mounted () {
    this.getEngineListByRegNumber(this.regNumber);
  },
  methods: {
    getEngineListByRegNumber: function (regNumber) {
        var self = this;
        self.$http.httpGet('/eim/api/engine/getEngineListByRegNumber', {
            regNumber: regNumber
        }).then((res) => {
            console.log("getEngineListByRegNumber res", res);
            self.engines = res.data;

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
#engine table td {
    width: 25%;
    font-size:14px;
}
.tabletitle {
    margin: 0 10px 10px 20px;
    display: block;
    font-size:14px;
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
#engine .ivu-table-tbody td:nth-child(2n-1) {
    background-color: #F8F8F9;
}
</style>

