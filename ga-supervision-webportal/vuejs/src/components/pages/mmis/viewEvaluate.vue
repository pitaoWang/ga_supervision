<template>
  <div >
    <div>
      <span style="width:1190px; display:inline-block;text-align:right; margin-top:10px;margin-bottom: 10px; margin-left: 20px;">
                    <Button primary @click="cancel()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

            </span>
    </div>
    <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
      <tbody>
      <tr>
        <td class="tr-1">编号</td>
        <td class="tr-2 tr-4">{{evaluate.no}}</td>
        <td class="tr-1 tr-3">适航资料编号</td>
        <td class="tr-2 tr-4">{{evaluate.asInformNo}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">标题</td>
        <td class="tr-2 tr-4">{{evaluate.title}}</td>
        <td class="tr-1 tr-3">类型</td>
        <td class="tr-2 tr-4">{{evaluate.asType}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">来源</td>
        <td class="tr-2 tr-4">{{evaluate.asSource}}</td>
        <td class="tr-1 tr-3">涉及航空器型号</td>
        <td class="tr-2 tr-4"> {{evaluate.involveAircraftModel}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">涉及航空器序列号</td>
        <td class="tr-2 tr-4">{{evaluate.involveAircraftSerial}}</td>
        <td class="tr-1 tr-3">是否执行</td>
        <td class="tr-2 tr-4">
          <div v-if="evaluate.isExecute == '0'">
            不执行
          </div>
          <div v-if="evaluate.isExecute == '1'">
            执行
          </div>
        </td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">执行方式</td>
        <td class="tr-2 tr-4" >{{evaluate.executeWay}}</td>
        <td class="tr-1 tr-3">执行期限</td>
        <td class="tr-2 tr-4" >{{evaluate.executeDeadline}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">评估说明</td>
        <td class="tr-2 tr-4" >{{evaluate.evaluateIllustrate}}</td>
        <td class="tr-1 tr-3">评估人</td>
        <td class="tr-2 tr-4" >{{evaluate.evaluator}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">评估时间</td>
        <td class="tr-2 tr-4" >{{evaluate.evaluateTime}}</td>
        <td class="tr-1 tr-3">审批人</td>
        <td class="tr-2 tr-4" >{{evaluate.approver}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">审批时间</td>
        <td class="tr-2 tr-4" >{{evaluate.approveTime}}</td>
        <td class="tr-1 tr-3">处理状态</td>
        <td class="tr-2 tr-4" >
          {{evaluate.status}}
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                evaluate: {
                    id:'',
                    equipmentModel: '',
                    creator: '',
                    creatorName: '',
                    createTime: '',
                    procId: '',
                    no:'',
                    manageName:'',
                    certificateNum: '',
                    certificateValidityPeriod: '',
                    certificateFile: '',
                    equipmentType: '',
                    equipmentModel: '',
                    evaluateCompany: '',
                    asInformNo: '',
                    title: '',
                    asType: '',
                    asSource: '',
                    involveAircraftModel: '',
                    involveAircraftSerial: '',
                    isExecute: '',
                    executeWay: '',
                    executeDeadline: '',
                    evaluateIllustrate: '',
                    evaluator: '',
                    evaluateTime: '',
                    companyId: '',
                    approver: '',
                    approveTime: '',
                    status: '',
                    revisor: '',
                    revisorName: '',
                    reviseTime: ''
                }
            }
        },
        components: {

        },
        mounted() {
            let self = this;
            let id = self.$route.query.id;
            self.$http.httpGet('/eim/api/mmis/getEvaluateById?id='+id, {
            }).then(function (response) {
                self.evaluate = response.data;
            }).catch(function (error) {
                console.log(error);
            });

        },
        methods: {
            cancel:function () {
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