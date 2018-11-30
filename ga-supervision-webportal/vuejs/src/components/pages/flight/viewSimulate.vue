<template>
  <div style="width: 1280px; height: auto;   margin-right: 40px;background:white;">
    <!--<div style = "width: 1190px;">
      <button class="div-button" style="width:88px; height:30px; border-radius:4px; border:1px solid #33A6FC; background-color: #FFFFFF; color:#33A6FC; float: right; cursor:pointer; margin-top: 10px; margin-bottom: 10px; margin-right:-20px"
              @click="cancel()" >返  回</button>
    </div>-->
    <span style="width:100%; display:inline-block;text-align:right;margin-top:10px;margin-bottom: 10px;margin-left: -70px; ">
                    <Button primary @click="cancel()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
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
        <td class="tr-1 tr-3">模拟器名称</td>
        <td class="tr-2 tr-4" > {{simulate.name}}</td>
        <td class="tr-1 tr-3">引进日期</td>
        <td class="tr-2 tr-4" >{{simulate.comeDate}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">基地</td>
        <td class="tr-2 tr-4" > {{simulate.baseName}}</td>
        <td class="tr-1 tr-3">次数</td>
        <td class="tr-2 tr-4" >{{simulate.count}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">使用声明</td>
        <td class="tr-2 tr-4" colspan="3" > {{simulate.userGuide}}</td>
      </tr>
      </tbody>
    </table>
    <div style="margin-left: 125px; margin-top: 15px">
      <div  v-if="simulate.photos" style="width: 250px;height:150px;margin-left: 20px;float:left;">
        <img :src="this.imageUrl+simulate.photos+'.thumb.jpg'"  style="width: 250px;height:150px;"  @click="imgdetail('',simulate.photos)">
      </div>
      <div v-else style="margin-left:50px;float:left">
        <img :src="noimg" style="width: 250px;height:150px;;padding-left:5px;"/>
      </div>
      <div  v-if="simulate.certificateFile" style="width: 250px;height:150px;margin-left: 320px;float:left">
        <img :src="this.imageUrl+simulate.certificateFile+'.thumb.jpg'"  style="width: 250px;height:150px;"  @click="imgdetail('',simulate.certificateFile)">
      </div>
      <div v-else style="margin-left: 320px;float:left">
        <img :src="noimg" style="width: 250px;height:150px;padding-left:5px;"/>
      </div>
    </div>
    <div class="bottom5" style="width: 1190px;margin-left:20px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">鉴定记录</h2>
                </span>

      <div class="bottom5" style="margin-top:5px;">
        <Table  :columns="columns7" :data="simulatorRecord"></Table>
      </div>

    </div>

    <div class="bottom5" style="width: 1190px;margin-left:20px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">适用课程</h2>

                </span>

      <div class="bottom5" style="margin-top:5px;">
        <Table  :columns="columns8" :data="course"></Table>
      </div>

    </div>
    <div class="bottom5" style="width: 1190px;margin-left:20px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">发动机</h2>
                </span>

      <div class="bottom5" style="margin-top:5px;">
        <Table  :columns="columns9" :data="engine"></Table>
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
                                        title: params.row.engineType
                                    }
                                }, params.row.engineType)
                            ]);

                        }
                    },
                    {
                        title: '发动机数据修订号',

                        key: 'dataRevisionNum',
                        align: 'center',
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
                                        title: params.row.dataRevisionNum
                                    }
                                }, params.row.dataRevisionNum)
                            ]);

                        }
                    },
                    {
                        title: '发动机数据出处',

                        key: 'dataSource',
                        align: 'center',
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
                                        title: params.row.dataSource
                                    }
                                }, params.row.dataSource)
                            ]);

                        }
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
                                        title: params.row.courseNum
                                    }
                                }, params.row.courseNum)
                            ]);

                        }
                    },
                    {
                        title: '适用课程类型',

                        key: 'courseType',
                        align: 'center',
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
                                        title: params.row.courseType
                                    }
                                }, params.row.courseType)
                            ]);

                        }
                    },
                    {
                        title: '适用课程名称',

                        key: 'courseName',
                        align: 'center',
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
                                        title: params.row.courseName
                                    }
                                }, params.row.courseName)
                            ]);

                        }
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
                                        title: params.row.recordType
                                    }
                                }, params.row.recordType)
                            ]);

                        }
                    },
                    {
                        title: '鉴定结果',

                        key: 'identificationResult',
                        align: 'center',
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
                                        title: params.row.identificationResult
                                    }
                                }, params.row.identificationResult)
                            ]);

                        }
                    },
                    {
                        title: '申请等级',

                        key: 'applicationLever',
                        align: 'center',
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
                                        title: params.row.applicationLever
                                    }
                                }, params.row.applicationLever)
                            ]);

                        }
                    },
                    {
                        title: '鉴定标准',

                        key: 'identStandard',
                        align: 'center',
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
                                        title: params.row.identStandard
                                    }
                                }, params.row.identStandard)
                            ]);

                        }
                    },
                    {
                        title: '开始鉴定时间',

                        key: 'beginTime',
                        align: 'center',
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
                                        title: params.row.beginTime
                                    }
                                }, params.row.beginTime)
                            ]);

                        }
                    },
                    {
                        title: '结束鉴定时间',

                        key: 'endTime',
                        align: 'center',
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
                                        title: params.row.endTime
                                    }
                                }, params.row.endTime)
                            ]);

                        }
                    },
                    {
                        title: '备注信息',

                        key: 'content',
                        align: 'center',
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
                                        title: params.row.content
                                    }
                                }, params.row.content)
                            ]);

                        }
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
                    name:'',
                    comeDate:'',
                    baseName:'',
                    count:'',
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

            self.simulate = JSON.parse(self.$route.query.simulate);

            self.$http.httpGet('/eim/api/simulate/getSimulatorById/'+self.simulate.id, {

            }).then(function (response) {
                self.engine = response.data.engine;
                self.course = response.data.course;
                self.simulatorRecord = response.data.simulatorRecord;
            }).catch(function (error) {
                console.log(error);
            });

        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,this.imageUrl+path);
            },
            cancel:function () {
                this.$router.push({path: '/airworthiness/simulateList'});
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