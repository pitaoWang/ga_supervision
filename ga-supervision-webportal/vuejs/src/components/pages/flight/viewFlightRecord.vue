<template>
  <div >
    <span style="width:1190px; display:inline-block;text-align:right; margin-top:10px;margin-bottom: 10px; margin-left: 20px;">
                    <Button primary @click="cancelpr()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

            </span>
    <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
      <tbody>
      <tr>
        <td class="tr-1 tr-3">飞行计划</td>
        <td class="tr-2 tr-4" colspan="3" >{{flightRecord.flightPlanName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">飞行性质</td>
        <td class="tr-2 tr-4">{{flightRecord.flightNature}}</td>
        <td class="tr-1 tr-3">航空器注册号</td>
        <td class="tr-2 tr-4">{{flightRecord.aircraftNo}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">飞行人员</td>
        <td class="tr-2 tr-4">{{flightRecord.flightPersonName}}</td>
        <td class="tr-1 tr-3">飞行日期</td>
        <td class="tr-2 tr-4">{{flightRecord.flightDate}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">起飞点</td>
        <td class="tr-2 tr-4">{{flightRecord.portDepartureName}}</td>
        <td class="tr-1 tr-3">降落点</td>
        <td class="tr-2 tr-4">{{flightRecord.portDestinationName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">起飞时刻</td>
        <td class="tr-2 tr-4" >{{flightRecord.flightTimeBegin}}</td>
        <td class="tr-1 tr-3">降落时刻</td>
        <td class="tr-2 tr-4" >{{flightRecord.flightTimeEnd}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">白天降落次数</td>
        <td class="tr-2 tr-4" >{{flightRecord.dayLandCount}}</td>
        <td class="tr-1 tr-3">夜晚降落次数</td>
        <td class="tr-2 tr-4" >{{flightRecord.nightLandCount}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">白天飞行时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.dayFlightTime}}</td>
        <td class="tr-1 tr-3">夜晚飞行时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.nightFlightTime}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">机长飞行时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.pilotFlightTime}}</td>
        <td class="tr-1 tr-3">机长单飞时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.pilotTransferTime}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">副驾飞行时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.copilotFlightTime}}</td>
        <td class="tr-1 tr-3">副驾转场时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.copilotTransferTime}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">转场时间(h)</td>
        <td class="tr-2 tr-4" > {{flightRecord.signleFlightTime}}</td>
        <td class="tr-1 tr-3">带飞飞行时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.instructionalFlightTime}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">带飞转场时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.instructionalTransferTime}}</td>
        <td class="tr-1 tr-3">仪表类型</td>
        <td class="tr-2 tr-4" >{{flightRecord.instrumentTypeName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">真实仪表时间(h)</td>
        <td class="tr-2 tr-4" > {{flightRecord.instrumentRealTime}}</td>
        <td class="tr-1 tr-3">模拟仪表时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.instrumentSimulationTime}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">训练机构</td>
        <td class="tr-2 tr-4" > {{flightRecord.trainingInstitutionName}}</td>
        <td class="tr-1 tr-3">教员时间(h)</td>
        <td class="tr-2 tr-4" >{{flightRecord.teachTime}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">备注</td>
        <td class="tr-2 tr-4" colspan="3" > {{flightRecord.remark}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

    export default {
        data() {
            return {
                flightRecord: {},
            }
        },

        mounted() {
            let self = this;

            self.flightRecord = JSON.parse(self.$route.query.flightRecord);

            switch (self.flightRecord.flightNature)
            {
                case 1:self.flightRecord.flightNature="任务飞行"; break;
                case 2:self.flightRecord.flightNature="训练飞行";break;
                case 3:self.flightRecord.flightNature="检查飞行";break;
                case 4:self.flightRecord.flightNature="实践考试";break;
            }

        },
        methods: {
            cancelpr:function () {
                this.$router.go(-1);
            }
        }
    }
</script>

<style scoped>
  .tr-1 {
    font-size: 14px;
    width: 180px;
    padding-left: 13px;
    height: 45px;
    color:  #606265;
    border-left: solid 1px #DCDFE6;
    border-right: solid 1px #DCDFE6;
    border-bottom: solid 1px #DCDFE6;
    background-color: #F8F8F9;
  }
  .tr-2 {
    font-size: 14px;
    width: 200px;
    text-align: center;
    color:  #606265;
    background-color: #FFF;
  }

  table{
    border-collapse:collapse;
  }
  table tr td{
    border:1px solid #DCDFE6;
  }

  .tr-3{
    background-color: #f8f8f9;
  }

  /*.tr-4{*/
  /*background-color: #FFF;*/
  /*}*/
  .tr-5{
    background-color: #f8f8f9;
  }

  .everyf{
    border: 1px solid ;
    border-color: #bbbec4;
    margin-top: 5px;
    position: relative;
  }
  .child{
    position: absolute;
    top:60px;
    right: 20px;
    width: 283px;
  }

  .flag{
    position: absolute;
    top: 5px;
    right: 3px;

  }
  .flag>img{
    width: 30px;
    display: block;
  }

  .form{
    width: 900px;
    background-color: #363636
  }
  .ivu-input-icon+.ivu-input{
    height: 30px;
    background: #b3b3b3;
    padding-right: 0;
  }
  .ivu-row{
    margin-bottom: 30px;
  }
  .submit-btn {
    font-size: 14px;
    margin: 0px 30px;
    display: inline-block;
    width: 120px;
    height: 35px;
    line-height: 30px;
    border: 1px solid #ececec;
    border-radius: 3px;
    color: #FFF;
    background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);
    background: linear-gradient(#a3a3a3, #e7e7e7);
  }
  .flybase-submit-div a:hover {
    display: inline-block;
    width: 120px;
    height: 35px;
    line-height: 30px;
    border: 1px solid #ececec;
    border-radius: 3px;
    color: black;
    background: -webkit-linear-gradient(#e7e7e7, #a3a3a3);
    background: linear-gradient(#e7e7e7, #a3a3a3);
  }
</style>