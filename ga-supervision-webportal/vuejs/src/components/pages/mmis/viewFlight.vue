<template>
  <div style="width: 1280px; height: auto; margin-top: 30px;  margin-right: 40px;background:white; padding-top: 20px; padding-bottom: 40px">
    <div style = "width: 1190px;">
      <button class="div-button" style="width:88px; height:30px; border-radius:4px; border:1px solid #33A6FC; background-color: #FFFFFF; color:#33A6FC; float: right; cursor:pointer; margin-top: 10px; margin-bottom: 10px; margin-right:-40px"
              @click="cancel()" >返  回</button>
    </div>
    <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
      <tbody>
      <tr>
        <td class="tr-1">设备型号</td>
        <td class="tr-2 tr-4">{{simulate.equipmentModel}}</td>
        <td class="tr-1 tr-3">设备名称</td>
        <td class="tr-2 tr-4">{{simulate.equipmentName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">合格证编号</td>
        <td class="tr-2 tr-4">{{simulate.certificateNum}}</td>
        <td class="tr-1 tr-3">合格证有效期</td>
        <td class="tr-2 tr-4">{{simulate.certificateValidityPeriod}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">设备类型</td>
        <td class="tr-2 tr-4">{{simulate.equipmentType}}</td>
        <td class="tr-1 tr-3">生产序号</td>
        <td class="tr-2 tr-4"> {{simulate.productionSn}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">等级</td>
        <td class="tr-2 tr-4">{{simulate.level}}</td>
        <td class="tr-1 tr-3">设备安装地点</td>
        <td class="tr-2 tr-4">{{simulate.installationSite}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">设备生产厂家</td>
        <td class="tr-2 tr-4" >{{simulate.equipmentManufacturer}}</td>
        <td class="tr-1 tr-3">设备生产日期</td>
        <td class="tr-2 tr-4" >{{simulate.equipmentProductDate}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">初始鉴定日期</td>
        <td class="tr-2 tr-4" >{{simulate.identificationDate}}</td>
        <td class="tr-1 tr-3">视景系统制造厂家</td>
        <td class="tr-2 tr-4" >{{simulate.visionSystemManufacturer}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">视影系统显示类型</td>
        <td class="tr-2 tr-4" >{{simulate.visionSystemType}}</td>
        <td class="tr-1 tr-3">视影系统型号</td>
        <td class="tr-2 tr-4" >{{simulate.visionSystemModel}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">运动系统型号</td>
        <td class="tr-2 tr-4" >{{simulate.sportSystemModel}}</td>
        <td class="tr-1 tr-3">运动系统制造厂家</td>
        <td class="tr-2 tr-4" >{{simulate.sprotSystemManufacturer}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">运动系统自由度</td>
        <td class="tr-2 tr-4" > {{simulate.sprotSystemFree}}</td>
        <td class="tr-1 tr-3">模拟航空器型号</td>
        <td class="tr-2 tr-4" >{{simulate.aircraftModel}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">空气动力数据修订号</td>
        <td class="tr-2 tr-4" > {{simulate.airAmendmentNumber}}</td>
        <td class="tr-1 tr-3">空气动力数据出处</td>
        <td class="tr-2 tr-4" >{{simulate.airDataSource}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">飞行操纵数据修订号</td>
        <td class="tr-2 tr-4" > {{simulate.flightAmendmentNuber}}</td>
        <td class="tr-1 tr-3">飞行操纵数据出处</td>
        <td class="tr-2 tr-4" >{{simulate.flightDataSource}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">飞行管理系统标识</td>
        <td class="tr-2 tr-4" > {{simulate.flightManagerFlag}}</td>
        <td class="tr-1 tr-3">飞行管理系统修订版本</td>
        <td class="tr-2 tr-4" >{{simulate.systemVersion}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">飞行模拟设备计算机标识</td>
        <td class="tr-2 tr-4" > {{simulate.computerFlag}}</td>
        <td class="tr-1 tr-3">模拟航空器制造厂家</td>
        <td class="tr-2 tr-4" >{{simulate.aircraftManufacturer}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">运行总时间</td>
        <td class="tr-2 tr-4" > {{simulate.totalTime}}</td>
        <td class="tr-1 tr-3">设备状态</td>
        <td class="tr-2 tr-4" >{{simulate.equipmentStatus}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">所属管理局</td>
        <td class="tr-2 tr-4" > {{simulate.manageName}}</td>
        <td class="tr-1 tr-3">所属监管局</td>
        <td class="tr-2 tr-4" >{{simulate.superName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">所属企业</td>
        <td class="tr-2 tr-4" > {{simulate.companyName}}</td>
        <td class="tr-1 tr-3">附加设备和功能</td>
        <td class="tr-2 tr-4" >{{simulate.additionalFeatures}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">鉴定测试指南</td>
        <td class="tr-2 tr-4" > {{simulate.testGuide}}</td>
        <td class="tr-1 tr-3">训练附加要求</td>
        <td class="tr-2 tr-4" >{{simulate.trainingRequirements}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">使用声明</td>
        <td class="tr-2 tr-4" colspan="3" > {{simulate.userGuide}}</td>
      </tr>
      </tbody>
    </table>
    <div style="margin-left: 125px; margin-top: 15px">
      <div  v-if="simulate.photos" style="width: 250px;height:150px;margin-left: 50px;float:left;">
        <img :src="simulate.photos"  style="width: 250px;height:150px;"  @click="imgdetail('',simulate.photos)">
      </div>
      <div v-else style="margin-left:50px;float:left">
        <img :src="noimg" style="width: 250px;height:150px;;padding-left:5px;"/>
      </div>
      <div  v-if="simulate.certificateFile" style="width: 250px;height:150px;margin-left: 320px;float:left">
        <img :src="simulate.certificateFile"  style="width: 250px;height:150px;"  @click="imgdetail('',simulate.certificateFile)">
      </div>
      <div v-else style="margin-left: 320px;float:left">
        <img :src="noimg" style="width: 250px;height:150px;padding-left:5px;"/>
      </div>
    </div>
    <div class="bottom5" style="width:87%;margin-left:40px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">鉴定记录</h2>
                </span>

      <div class="bottom5" style="margin-top:5px;">
        <Table border :columns="columns7" :data="simulatorRecord"></Table>
      </div>

    </div>

    <div class="bottom5" style="width:87%;margin-left:40px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">适用课程</h2>

                </span>

      <div class="bottom5" style="margin-top:5px;">
        <Table border :columns="columns8" :data="course"></Table>
      </div>

    </div>
    <div class="bottom5" style="width:87%;margin-left:40px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">发动机</h2>
                </span>

      <div class="bottom5" style="margin-top:5px;">
        <Table border :columns="columns9" :data="engine"></Table>
      </div>

    </div>
    <imgdetail ref="imgdetailref"></imgdetail>
  </div>
</template>

<script>
    import noimg from '../../../../static/img/noimgpictrue.png';
    import Cookies from 'js-cookie';
    import imgdetail from '../../views/imgdetail.vue';
    export default {
        data() {
            return {
                noimg: noimg,
                add_dialog_show2: false,
                temp2:{
                    id:'',
                    simulatorId:'',
                    engineType:'',
                    dataRevisionNum:'',
                    dataSource:''

                },
                check_form2: {},
                engine:[],
                engineindex:'',
                columns9: [
                    {
                        title: '发动机型号',
                        key: 'engineType',
                        align: 'center',
                        width: 371
                    },
                    {
                        title: '发动机数据修订号',
                        width: 390,
                        key: 'dataRevisionNum',
                        align: 'center'
                    },
                    {
                        title: '发动机数据出处',
                        width: 350,
                        key: 'dataSource',
                        align: 'center'
                    }
                ],



                add_dialog_show1: false,
                temp1:{
                    id:'',
                    simulatorId:'',
                    courseNum:'',
                    courseType:'',
                    courseName:''

                },
                check_form1: {},
                course:[],
                courseindex:'',
                columns8: [
                    {
                        title: '适用课程编号',
                        key: 'courseNum',
                        align: 'center',
                        width: 371
                    },
                    {
                        title: '适用课程类型',
                        width: 390,
                        key: 'courseType',
                        align: 'center'
                    },
                    {
                        title: '适用课程名称',
                        width: 350,
                        key: 'courseName',
                        align: 'center'
                    }
                ],

                add_dialog_show: false,
                temp:{
                    id:'',
                    simulatorId:'',
                    recordType:'',
                    identificationResult:'',
                    beginTime:'',
                    endTime:'',
                    content:''

                },
                check_form: {},
                simulatorRecord:[],
                simulatorRecordindex:'',
                columns7: [
                    {
                        title: '鉴定类型',
                        key: 'recordType',
                        align: 'center',
                        width: 110
                    },
                    {
                        title: '鉴定结果',
                        width: 191,
                        key: 'identificationResult',
                        align: 'center'
                    },
                    {
                        title: '申请等级',
                        width: 90,
                        key: 'applicationLever',
                        align: 'center'
                    },
                    {
                        title: '鉴定标准',
                        width: 130,
                        key: 'identStandard',
                        align: 'center'
                    },
                    {
                        title: '开始鉴定时间',
                        width: 120,
                        key: 'beginTime',
                        align: 'center'
                    },
                    {
                        title: '结束鉴定时间',
                        width: 120,
                        key: 'endTime',
                        align: 'center'
                    },
                    {
                        title: '备注信息',
                        width: 350,
                        key: 'content',
                        align: 'center'
                    }
                ],
                // isshow: true,
                // isshowupload: false,
                manageList:[],
                superList:[],
                tranList: [
                    {
                        value: '1',
                        label: '1'
                    },
                    {
                        value: '2',
                        label: '2'
                    },
                    {
                        value: '3',
                        label: '3'
                    },
                    {
                        value: '4',
                        label: '4'
                    },
                    {
                        value: '5',
                        label: '5'
                    },
                    {
                        value: '6',
                        label: '6'
                    },
                    {
                        value: '7',
                        label: '7'
                    }
                ],
                simList: [
                    {
                        value: 'A',
                        label: 'A'
                    },
                    {
                        value: 'B',
                        label: 'B'
                    },
                    {
                        value: 'C',
                        label: 'C'
                    },
                    {
                        value: 'D',
                        label: 'D'
                    }
                ],
                simulate: {
                    id:'',
                    equipmentModel: '',
                    equipmentName: '',
                    companyId: '',
                    superId: '',
                    manageId: '',
                    superName:'',
                    manageName:'',
                    certificateNum: '',
                    certificateValidityPeriod: '',
                    certificateFile: '',
                    equipmentType: '',
                    equipmentModel: '',
                    productionSn: '',
                    level: '',
                    installationSite: '',
                    equipmentManufacturer: '',
                    equipmentProductDate: '',
                    identificationDate: '',
                    visionSystemManufacturer: '',
                    visionSystemType: '',
                    visionSystemModel: '',
                    sportSystemModel: '',
                    sprotSystemManufacturer: '',
                    sprotSystemFree: '',
                    additionalFeatures: '',
                    photos: '',
                    aircraftModel: '',
                    airAmendmentNumber: '',
                    airDataSource: '',
                    flightAmendmentNuber: '',
                    flightDataSource: '',
                    flightManagerFlag: '',
                    systemVersion: '',
                    computerFlag: '',
                    trainingRequirements: '',
                    testGuide: '',
                    aircraftManufacturer: '',
                    userGuide: '',
                    totalTime: 0.0,
                    equipmentStatus: '',
                    companyName:'',
                    superName:'',
                    manageName:''
                },
                certificateFile:[],
                photos:[],

            }
        },
        components: {
            imgdetail
        },
        mounted() {
            let self = this;

            self.simulate = self.$route.query.simulate;

            self.$http.httpGet('/eim/api/simulate/getSimulatorById/'+self.$route.query.simulate.id, {

            }).then(function (response) {
                self.engine = response.data.engine;
                self.course = response.data.course;
                self.simulatorRecord = response.data.simulatorRecord;
                let buz = JSON.parse(Cookies.get("profile"));
                    self.simulate.companyName = buz.buz.companyName;
                    self.simulate.companyId = buz.buz.companyId;

            }).catch(function (error) {
                console.log(error);
            });

        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
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