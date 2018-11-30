<template>
  <div style="width: 100%;display: inline-block;background: white;padding: 0 10px;margin-bottom:16px;">
    <table style="width: 100%; border-spacing:0px;">
      <tbody>
      <tr>
        <td class="tr-1">机型编号</td>
        <td class="tr-2 tr-4"><a class="s-bt" @click="viewPage" style="color: #00a0e9">{{aircraft.typeNumber}}</a></td>
        <td class="tr-1 tr-3">航空器注册号</td>
        <td class="tr-2 tr-4">{{aircraft.regNumber}}</td>
      </tr>

      <tr>
        <td class="tr-1 tr-3">航空器序列号</td>
        <td class="tr-2 tr-4">{{aircraft.airframeid}}</td>
        <td class="tr-1 tr-3">航空器拥有形式</td>
        <td class="tr-2 tr-4">{{aircraft.aircraftModel}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">所属单位</td>
        <td class="tr-2 tr-4">{{aircraft.companyName}}</td>
        <td class="tr-1 tr-3">所属管理局</td>
        <td class="tr-2 tr-4"> {{aircraft.authorityName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">所属监管局</td>
        <td class="tr-2 tr-4">{{aircraft.supervisionName}}</td>
        <td class="tr-1 tr-3">产权人</td>
        <td class="tr-2 tr-4">{{aircraft.propertyOwner}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">航空器状态</td>
        <td class="tr-2 tr-4" >{{aircraft.aircraftStatus}}</td>
        <td class="tr-1 tr-3">出厂时间</td>
        <td class="tr-2 tr-4" >{{aircraft.productionDate}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">引进时间</td>
        <td class="tr-2 tr-4" >{{aircraft.introductionTime}}</td>
        <td class="tr-1 tr-3">国籍登记证编号</td>
        <td class="tr-2 tr-4" >{{aircraft.registernumber}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">适航证编号</td>
        <td class="tr-2 tr-4" >{{aircraft.airworthinessCertificateNumber}}</td>
        <td class="tr-1 tr-3">电台执照编号</td>
        <td class="tr-2 tr-4" >{{aircraft.radioLicenceNumber}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">电台执照失效日期</td>
        <td class="tr-2 tr-4" >{{aircraft.radioLicenceInvalidDate}}</td>
        <td class="tr-1 tr-3">飞行时间</td>
        <td class="tr-2 tr-4" >{{aircraft.flySumTime}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">运行区域</td>
        <td class="tr-2 tr-4" >{{aircraft.operateArea}}</td>
        <td class="tr-1 tr-3">运行种类</td>
        <td class="tr-2 tr-4" >{{aircraft.operateType}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">起落次数</td>
        <td class="tr-2 tr-4"  > {{aircraft.upDownTimes}}</td>
        <td class="tr-1 tr-3">所属基地</td>
        <td class="tr-2 tr-4"  > {{aircraft.baseName}}</td>
      </tr>
      </tbody>
    </table>
    <div class="form-addair" style="margin-top: 15px">
      <div>

        <div  v-if="aircraft.aircraftCatalogue" style="width: 260px;height:150px;float:left; margin-left: 30px">
          <img :src="this.imageUrl+aircraft.aircraftCatalogue+'.thumb.jpg'"  style="width: 260px;height:150px;"  @click="imgdetail('',aircraft.aircraftCatalogue)">

        </div>
        <div v-else style="float:left">
          <img :src="noimg" style="width: 260px;height:150px; margin-left: 30px"/>
        </div>

        <div  v-if="aircraft.scanCatalogue" style="width: 260px;height:150px;float:left; margin-left: 30px">
          <img :src="this.imageUrl+aircraft.scanCatalogue+'.thumb.jpg'"  style="width: 260px;height:150px;"  @click="imgdetail('',aircraft.scanCatalogue)">

        </div>
        <div v-else style="float:left">
          <img :src="noimg" style="width: 260px;height:150px;; margin-left: 30px"/>
        </div>

        <div  v-if="aircraft.radioLicenceCatalogue" style="width: 260px;height:150px;float:left; margin-left: 30px">
          <img :src="this.imageUrl+aircraft.radioLicenceCatalogue+'.thumb.jpg'"  style="width: 260px;height:150px;"  @click="imgdetail('',aircraft.radioLicenceCatalogue)">

        </div>
        <div v-else style="float:left">
          <img :src="noimg" style="width: 260px;height:150px; margin-left: 30px"/>
        </div>

        <div  v-if="aircraft.airworthinessCertificate" style="width: 260px;height:150px;float:left;margin-left: 30px">
          <img :src="this.imageUrl+aircraft.airworthinessCertificate+'.thumb.jpg'"  style="width: 260px;height:150px;"  @click="imgdetail('',aircraft.airworthinessCertificate)">

        </div>
        <div v-else style="float:left">
          <img :src="noimg" style="width: 260px;height:150px; margin-left: 30px"/>
        </div>
      </div>
      <div class="bottom5" style="width:100%;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">定检记录</h2>
                </span>
        <div style="margin-top:5px; margin-bottom: 15px">
          <Table border :columns="columns7" :data="checkRecord"></Table>
        </div>
      </div>
    </div>
    <imgdetail ref="imgdetailref"></imgdetail>
  </div>
</template>

<script>
    import noimg from '../../../../static/img/noimgpictrue.png';

    import imgdetail from '../../views/imgdetail.vue';

    export default {
        data() {
            return {
                add_dialog_show: false,
                noimg: noimg,
                temp:{
                    id:'',
                    aircaftId:'',
                    recordTitle:'',
                    completePerson:'',
                    completeTime:''

                },
                check_form: {},
                checkRecord:[],
                checkRecordindex:'',
                supervision:[],
                supervision1:[],
                supervision2:[],
                isDisaled:false,
                // isshow: true,
                // isshowupload: false,
                aircraft: {
                    aircraftId:'',
                    planTypeId: '',
                    companyId: '',
                    companyName: '',
                    typeNumber:'',
                    regNumber: '',
                    planTypeName:'',
                    supervisionName:'',
                    authorityName:'',
                    superId: '',
                    managerId: '',
                    baseName:'',
                    aircraftStatus: '',
                    aircraftModel: '',
                    introductionTime: '',
                    registernumber: '',
                    scanCatalogue:'',
                    airworthinessCertificateNumber: '',
                    airworthinessCertificate: '',
                    radioLicenceNumber:'',
                    radioLicenceCatalogue: '',
                    radioLicenceInvalidDate: '',
                    aircraftCatalogue:'',
                    productionDate: '',
                    airframeid: '',
                    flySumTime: '',
                    upDownTimes: '',
                    operateArea: '',
                    operateType: '',
                    propertyOwner: '',

                },
                isflg:'',
                columns7: [
                    {
                        title: '定检记录标题',
                        key: 'recordTitle',
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
                                        title: params.row.recordTitle
                                    }
                                }, params.row.recordTitle)
                            ]);

                        }
                    },
                    {
                        title: '定检完成人',
                        key: 'completePerson',
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
                                        title: params.row.completePerson
                                    }
                                }, params.row.completePerson)
                            ]);

                        }
                    },
                    {
                        title: '定检完成时间',
                        key: 'completeTime',
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
                                        title: params.row.completeTime
                                    }
                                }, params.row.completeTime)
                            ]);

                        }
                    }
                ]
            }
        },
        props: ['regNumber'],
        components: {
            imgdetail
        },
        mounted() {
            let self = this;
            self.aircraft = JSON.parse(self.$route.query.aircraft);
            self.$http.httpGet('/eim/api/checkRecord/serach/getbyaircaftId', {
                aircaftId:self.$route.query.aircraft.aircraftId
            }).then(function (response) {
                let datainfo=response.data;
                self.checkRecord=datainfo;
            }) .catch(function (error) {
                console.log(error);
            });
            if(self.aircraft.productionDate!=null)
            {
               // self.aircraft.productionDate = new Date(self.aircraft.productionDate);
                var date = new Date(self.aircraft.productionDate);

                self.aircraft.productionDate = this.getdata(date);

            }
            if(self.aircraft.introductionTime!=null)
            {
                var date = new Date(self.aircraft.introductionTime);

                self.aircraft.introductionTime = this.getdata(date);
            }
            if(self.aircraft.radioLicenceInvalidDate!=null)
            {
                var date = new Date(self.aircraft.radioLicenceInvalidDate);

                self.aircraft.radioLicenceInvalidDate = this.getdata(date);
            }
        },
        created() {

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
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,this.imageUrl+path);
            },
            //查看详情
            viewPage() {

                if(this.aircraft.planTypeId!=null&&this.aircraft.planTypeId!=""){
                    this.$router.push({path: '/viewType',query:{aircraft:JSON.stringify(this.aircraft),isfg:'1'}});
                }else{
                    alert("请选择机型")
                }

            },
            cancel:function () {
                this.$router.push({path: '/airworthiness/aircraftList'});
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
  .bottom5 {
    color: #657180;
    margin-bottom: 15px;
  }
</style>