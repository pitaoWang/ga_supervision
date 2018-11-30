<template>
  <div style="width: 1280px; height: auto; background:white;">
    <span style="width:100%; display:inline-block;text-align:right; margin-top:10px;margin-bottom: 10px; padding-right: 70px;">
                    <Button primary @click="cancelpr()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

            </span>
    <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
      <tbody>
      <tr>
        <td class="tr-1 tr-3">飞行计划</td>
        <td class="tr-2 tr-4">{{prepareRelease.flightPlanName}}</td>
        <td class="tr-1 tr-3">放行时间</td>
        <td class="tr-2 tr-4">{{prepareRelease.releaseTime}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">航空器型别</td>
        <td class="tr-2 tr-4">{{prepareRelease.aircraftType}}</td>
        <td class="tr-1 tr-3">空机重量(Kg)</td>
        <td class="tr-2 tr-4">{{prepareRelease.emptyWeight}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">航空器注册号</td>
        <td class="tr-2 tr-4">{{prepareRelease.aircraftNo}}</td>
        <td class="tr-1 tr-3">起飞油量(Kg)</td>
        <td class="tr-2 tr-4"> {{prepareRelease.takeoffFuel}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">飞行规则</td>
        <td class="tr-2 tr-4">{{prepareRelease.flightRule}}</td>
        <td class="tr-1 tr-3">最大起飞重量(Kg)</td>
        <td class="tr-2 tr-4">{{prepareRelease.takeoffWeightMax}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">起飞机场</td>
        <td class="tr-2 tr-4" >{{prepareRelease.airportDepartureName}}</td>
        <td class="tr-1 tr-3">实际起飞重量(Kg)</td>
        <td class="tr-2 tr-4" >{{prepareRelease.takeoffWeightReal}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">降落机场</td>
        <td class="tr-2 tr-4" >{{prepareRelease.airportDestinationName}}</td>
        <td class="tr-1 tr-3">飞行高度(m)</td>
        <td class="tr-2 tr-4" >{{prepareRelease.airlineHeight}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">备降机场</td>
        <td class="tr-2 tr-4" >{{prepareRelease.airportAlternateName}}</td>
        <td class="tr-1 tr-3">载重平衡</td>
        <td class="tr-2 tr-4" >{{prepareRelease.isBalance==1?'合格':'不合格'}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">机长</td>
        <td class="tr-2 tr-4" >{{prepareRelease.captainName}}</td>
        <td class="tr-1 tr-3">副驾驶</td>
        <td class="tr-2 tr-4" >{{prepareRelease.copilotName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">机长酒精限制</td>
        <td class="tr-2 tr-4" > {{prepareRelease.isPassCaptainWine==1?'合格':'不合格'}}</td>
        <td class="tr-1 tr-3">副驾驶酒精限制</td>
        <td class="tr-2 tr-4" >{{prepareRelease.isPassCopilotWine==1?'合格':'不合格'}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">机长药物限制</td>
        <td class="tr-2 tr-4" > {{prepareRelease.isPassCaptainDrug==1?'合格':'不合格'}}</td>
        <td class="tr-1 tr-3">副驾驶药物限制</td>
        <td class="tr-2 tr-4" >{{prepareRelease.isPassCopilotDrug==1?'合格':'不合格'}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">机长身体状况</td>
        <td class="tr-2 tr-4" > {{prepareRelease.isPassCaptainHealth==1?'合格':'不合格'}}</td>
        <td class="tr-1 tr-3">副驾驶身体状况</td>
        <td class="tr-2 tr-4" >{{prepareRelease.isPassCopilotHealth==1?'合格':'不合格'}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">机长思想</td>
        <td class="tr-2 tr-4" > {{prepareRelease.isPassCaptainMind==1?'合格':'不合格'}}</td>
        <td class="tr-1 tr-3">副驾驶思想</td>
        <td class="tr-2 tr-4" >{{prepareRelease.isPassCopilotMind==1?'合格':'不合格'}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">起降场天气</td>
        <td class="tr-2 tr-4" colspan="3" > {{prepareRelease.airportWeather}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

    export default {
        data() {
            return {
                prepareRelease: {
                    ID:'',
                    createBy: '',
                    createDate: '',
                    updateBy: '',
                    updateDate: '',
                    deleteFlag: '',
                    organizationId:'',
                    organizationBaseId:'',
                    organizationBaseName: '',
                    flightPlanId: '',
                    flightPlanName: '',
                    aircraftId: '',
                    aircraftName: '',
                    aircraftType: '',
                    aircraftNo: '',
                    aircraftCall: '',
                    airportDepartureId: '',
                    airportDepartureName: '',
                    airportDestinationId: '',
                    airportDestinationName: '',
                    airportAlternateId: '',
                    airportAlternateName: '',
                    flightRule: '',
                    isBalance: '',
                    stowageTime: '',
                    emptyWeight: '',
                    takeoffFuel: '',
                    takeoffWeightMax: '',
                    takeoffWeightReal: '',
                    airlineHeight: '',
                    captainId: '',
                    captainName: '',
                    isPassCaptainWine: '',
                    isPassCaptainDrug: '',
                    isPassCaptainHealth: '',
                    isPassCaptainMind: '',
                    copilotId: '',
                    copilotName: '',
                    isPassCopilotWine: '',
                    isPassCopilotDrug:'',
                    isPassCopilotHealth: '',
                    isPassCopilotMind:'',
                    airportWeather:'',
                    isPassWeather:'',
                    releaseTime:''
                },
            }
        },

        mounted() {
            let self = this;

            self.prepareRelease = JSON.parse(self.$route.query.prepareRelease);
            if(self.prepareRelease.releaseTime!=null)
            {
                var date = new Date(self.prepareRelease.releaseTime);

                self.prepareRelease.releaseTime = this.getdata(date);
            }


        },
        methods: {
            getdata:function (date) {
                var y = date.getFullYear();
                var m = date.getMonth() + 1;
                m = m < 10 ? '0' + m : m;
                var d = date.getDate();
                d = d < 10 ? ('0' + d) : d;
                return y + '-' + m + '-' + d;
            },
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