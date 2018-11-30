<template>
  <div style="width: 1280px; height: 1008px; margin-top: 30px;background:white; padding-top: 30px">
    <div style="border: 1px solid #f0f0f0; margin-left: 40px; margin-right: 40px;">
      <Form :model="aircraft" :label-width="140" :rules="aircraftValidate" ref="aircraftRef" style="margin-top: 30px; margin-left: 30px;">
        <div class="form-addair">
          <FormItem label="机型编号:" style="width: 45%;display: inline-block;" prop="planTypeId">
            <Select v-model.number="aircraft.planTypeId"   placeholder="请选择" style="width: 90%;">
              <Option v-for="site in supervision" :key="site.id" :value="site.id">{{ site.typeNumber }}</Option>
            </Select>
          </FormItem>
          <FormItem  style="width: 25%;display: inline-block; margin-left: -160px">
            <Button type="primary" @click="viewPage">查看机型</Button>
          </FormItem>
          <FormItem label="航空器注册号:" style="width: 45%;display: inline-block;" prop="regNumber">
            <Input v-model="aircraft.regNumber" placeholder="输入注册号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="航空器序列号:" style="width: 45%;display: inline-block;margin-left: 20px" prop="airframeid">
            <Input v-model="aircraft.airframeid" placeholder="输入序列号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="航空器拥有形式:" style="width: 45%;display: inline-block;" prop="aircraftModel">
            <Select v-model="aircraft.aircraftModel" placeholder="请选择" style="width: 90%;" @on-change="changeExt">
              <Option value="执管">执管</Option>
              <Option value="租赁">租赁</Option>
              <Option value="借用">借用</Option>
            </Select>
          </FormItem>
          <FormItem label="所属单位:" style="width: 45%;display: inline-block;margin-left: 20px" prop="companyName" >
            <Input v-model="aircraft.companyName" placeholder="输入单位" style="width: 90%;" :readonly="isDisaled"></Input>
          </FormItem>
          <FormItem label="所属管理局:"  style="width: 45%;display: inline-block;" prop="managerId">
            <Input v-model="aircraft.managerId" placeholder="所属管理局" style="width: 90%;" :readonly="true"></Input>
          </FormItem>
          <FormItem label="所属监管局:" style="width: 45%;display: inline-block;margin-left: 20px" prop="superId">
            <Input v-model="aircraft.superId" placeholder="所属监管局" style="width: 90%;" :readonly="true"></Input>
          </FormItem>
          <FormItem label="产权人:"  style="width: 45%;display: inline-block;" prop="propertyOwner">
            <Input v-model="aircraft.propertyOwner" placeholder="产权人" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="航空器状态:" style="width: 45%;display: inline-block;margin-left: 20px" prop="aircraftStatus">
            <Select v-model="aircraft.aircraftStatus" placeholder="请选择" style="width: 90%;">
              <Option value="在用">在用</Option>
              <Option value="大修">大修</Option>
              <Option value="报停">报停</Option>
              <Option value="报废">报废</Option>
              <Option value="维护">维护</Option>
            </Select>
          </FormItem>
          <FormItem label="出厂时间:" style="width: 45%;display: inline-block;" prop="productionDate">
            <DatePicker type="date" placeholder="选择日期" v-model="aircraft.productionDate"
                        style="width: 90%;"></DatePicker>
          </FormItem>
          <FormItem label="引进时间:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="introductionTime">
            <DatePicker type="date" placeholder="选择日期" v-model="aircraft.introductionTime"
                        style="width: 90%;"></DatePicker>
          </FormItem>
          <FormItem label="国籍登记证编号:" style="width: 45%;display: inline-block;" prop="registernumber">
            <Input v-model="aircraft.registernumber" placeholder="国籍登记证编号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="适航证编号:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="airworthinessCertificateNumber">
            <Input v-model="aircraft.airworthinessCertificateNumber" placeholder="适航证编号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="电台执照编号:" style="width: 45%;display: inline-block;" prop="radioLicenceNumber">
            <Input v-model="aircraft.radioLicenceNumber" placeholder="电台执照编号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="电台执照失效日期:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="radioLicenceInvalidDate">
            <DatePicker type="date" placeholder="选择日期" v-model="aircraft.radioLicenceInvalidDate"
                        style="width: 90%;"></DatePicker>
          </FormItem>
          <FormItem label="飞行时间:" style="width: 45%;display: inline-block;" prop="flySumTime">
            <Input v-model="aircraft.flySumTime"  placeholder="飞行时间" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="起落次数:" style="width: 45%;display: inline-block;margin-left: 20px" prop="upDownTimes">
            <Input v-model="aircraft.upDownTimes"  placeholder="起落次数" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="运行区域:" style="width: 45%;display: inline-block;" prop="operateArea">
            <Input v-model="aircraft.operateArea"  placeholder="运行区域" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="运行种类:" style="width: 45%;display: inline-block;margin-left: 20px" prop="operateType">
            <Input v-model="aircraft.operateType"  placeholder="运行种类" style="width: 90%;"></Input>
          </FormItem>
          <div>
            <aircrAftupload v-on:childurlone="urldata" ref="aircraftupdateurl"></aircrAftupload>
          </div>
          <div class="bottom5" style="width:87%;margin-left:20px;" >
                <span style="width:100%; display:inline-block; text-align:right; padding-top: 10px">
                    <h2 style="float:left;">定检记录</h2>
                    <Button type="primary" @click="add_dialog_show=true" icon="plus"
                            style="margin-right: -40px;width: 98px;height: 32px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>
            <div style="margin-top:5px; width:1060px">
              <Table border :columns="columns7" :data="checkRecord"></Table>
            </div>
          </div>
          <div class="div-sumbit-but">
            <div style="float: left; margin-left: 800px; padding-bottom: 10px; padding-top: 20px; width: 680px;">
              <Button type="primary" @click="cancel" style="margin-right: 15px">取消</Button>
              <Button type="primary" @click="saveType">保存</Button>
            </div>
          </div>
        </div>
      </Form>
      <Modal v-model="add_dialog_show" width="400" title="新增定检记录"  :mask-closable="false" @on-ok="submitCheckForm"
             @on-cancel="add_dialog_show = false" :styles="{top: '20px'}">
        <Form :label-width="100" v-model="check_form" >
          <div >
            <Row>
              <Col :md="23">
                <FormItem label="定检记录标题" class="bottom5">
                  <Input v-model="check_form.recordTitle" placeholder="请输入定检记录标题"></Input>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="定检完成人" class="bottom5">
                  <Input v-model="check_form.completePerson"  placeholder="请输入定检完成人..."></Input>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="定检完成时间" class="bottom5">
                  <DatePicker v-model="check_form.completeTime" type="date" placeholder="选择日期"></DatePicker>
                </FormItem>
              </Col>
            </Row>
          </div>
        </Form>
      </Modal>
    </div>
  </div>
</template>

<script>
    import aircrAftupload from './aircrAftupload'
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                add_dialog_show: false,
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
                    regNumber: '',
                    superId: '',
                    managerId: '',
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
                    propertyOwner: ''
                },
                isflg:'',
                columns7: [
                    {
                        title: '定检记录标题',
                        key: 'recordTitle',
                        align: 'center',
                        width: 311
                    },
                    {
                        title: '定检完成人',
                        width: 310,
                        key: 'completePerson',
                        align: 'center'
                    },
                    {
                        title: '定检完成时间',
                        width: 310,
                        key: 'completeTime',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 126,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '16px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_edit.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.removeCheck(params.index)
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],
                aircraftValidate: {
                    planTypeId: [{required: true,type:'number', message: '机型名称不能为空', trigger: 'change'}],
                    aircraftModel: [{required: true, message: '航空器拥有形式不能为空', trigger: 'change'}],
                    regNumber: [{required: true, message: '航空器注册号不能为空', trigger: 'blur'}],
                    companyName: [{required: true, message: '所属单位不能为空', trigger: 'blur'}],
                    aircraftStatus:[{required: true, message: '航空器状态不能为空', trigger: 'change'}],
                    radioLicenceInvalidDate:[{required: true,type:"date",message: '电台执照失效日期不能为空', trigger: 'change'}],
                    airframeid:[{required: true, message: '航空器序列号不能为空', trigger: 'blur'}],
                    registernumber: [{required: true, message: '国籍登记证号不能为空', trigger: 'blur'}],
                    airworthinessCertificateNumber: [{required: true, message: '适航证编号不能为空', trigger: 'blur'}],
                    radioLicenceNumber: [{required: true, message: '电台执照编号不能为空', trigger: 'blur'}],
                    flySumTime: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    upDownTimes: [{ pattern: /^[+-]*\d+$/,message: '必須輸入数字！'}],
                }
            }
        },
        components: {
            aircrAftupload
        },
        mounted() {
            let self = this;
            if(typeof(self.$route.query.aircraft) != "undefined"){
                if(self.$route.query.aircraft.aircraftId!=""&&self.$route.query.aircraft.aircraftId!=null){
                    self.$http.httpGet('/eim/api/checkRecord/serach/getbyaircaftId', {

                        aircaftId:self.$route.query.aircraft.aircraftId
                    }).then(function (response) {
                        let datainfo=response.data;
                        self.checkRecord=datainfo;
                    }) .catch(function (error) {
                        console.log(error);
                    });
                }
                self.aircraft = self.$route.query.aircraft;
                if (this.aircraft.aircraftModel == "执管") {
                    this.isDisaled=true
                }else(
                    this.isDisaled=false

                )
                if(self.aircraft.productionDate!=null)
                {
                    self.aircraft.productionDate = new Date(self.aircraft.productionDate);
                }
                if(self.aircraft.introductionTime!=null)
                {
                    self.aircraft.introductionTime = new Date(self.aircraft.introductionTime);
                }
                if(self.aircraft.radioLicenceInvalidDate!=null)
                {
                    self.aircraft.radioLicenceInvalidDate = new Date(self.aircraft.radioLicenceInvalidDate);
                }
                self.$refs.aircraftupdateurl.urlupdate(self.aircraft);
                self.isflg = '1'
            }else {
                self.isflg = '2'
            }


        },
        created() {
            let self = this;
            let buz = JSON.parse(Cookies.get("profile"));
                self.aircraft.companyId = buz.buz.companyId;
            self.aircraft.superId = buz.buz.supervisionId;
            self.aircraft.managerId = buz.buz.authorityId;
                self.$http.httpGet('/eim/api/plantype', {

                }).then(function (response) {

                    let datainfo=response.data.data;

                    self.supervision=datainfo;

                }) .catch(function (error) {
                    console.log(error);
                });

            /*self.$http.httpGet('/eim/api/company/search/orgname', {
                //查找全部管理局信息
                orgtype:2
            }).then(function (response) {
                let datainfo=response.data;
                self.supervision1=datainfo;
            }) .catch(function (error) {
                console.log(error);
            });

            self.$http.httpGet('/eim/api/company/search/orgname', {
                //查找全部监管局信息
                orgtype:1
            }).then(function (response) {
                let datainfo=response.data;
                self.supervision2=datainfo;
            }) .catch(function (error) {
                console.log(error);
            });
*/
        },
        methods: {
            //查看详情
            viewPage() {

                if(this.aircraft.planTypeId!=null&&this.aircraft.planTypeId!=""){
                    this.$router.push({path: '/viewType',query:{aircraft:this.aircraft,isfg:'2'}});
                }else{
                    alert("请选择机型")
                }

            },
            removeCheck(index) {
                let self = this;

                let url = "/eim/api";

                let checkUrl = url + "/checkRecord/" + this.checkRecord[index].id;
                self.$http.httpDelete(checkUrl, {}).then((res) => {
                    let result = res.data
                    if (result.resultCode == 200) {
                        self.checkRecord.splice(index, 1);
                        self.$Message.info("删除成功，id=" + result.data);
                    }
                });


            },
            updateCheck(index) {
                this.temp.id=this.checkRecord[index].id;
                this.temp.aircaftId=this.checkRecord[index].aircaftId;
                this.temp.recordTitle=this.checkRecord[index].recordTitle;
                this.temp.completePerson=this.checkRecord[index].completePerson;
                this.temp.completeTime=this.checkRecord[index].completeTime;

                this.check_form = this.temp;
                this.checkRecordindex = index;
                this.add_dialog_show = true;
            },
            submitCheckForm() {
                let self = this;
                let url = "/eim/api";
                let checkUrl = url + "/checkRecord";
                let is_update = false;
                if (self.check_form.id) {
                    is_update = true;
                }


                self.$http.httpPost(checkUrl, self.check_form).then(function (res) {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.check_form = {};
                        if (!is_update) {
                            self.checkRecord.push(result.data);

                        }
                        else{
                            self.checkRecord.splice(self.checkRecordindex,1,self.temp)

                            self.checkRecord[self.checkRecordindex] = result.data;


                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            changeExt() {
                let buz = JSON.parse(Cookies.get("profile"));
                    if (this.aircraft.aircraftModel == "执管") {
                        this.isDisaled=true,
                            this.aircraft.companyName = buz.buz.companyName
                    }else(
                        this.isDisaled=false,
                            this.aircraft.companyName=''
                    )

            },
            initvalidate(){
                this.$refs['planTypeRef'].resetFields();
            },
            urldata(msg, rt) {
                if (rt == 'url1') {
                    this.aircraft.aircraftCatalogue = msg;
                } else if (rt == 'url2') {
                    this.aircraft.scanCatalogue = msg;
                } else if (rt == 'url3') {
                    this.aircraft.airworthinessBackCatalogue = msg;
                } else if (rt == 'url4') {
                    this.aircraft.radioLicenceCatalogue = msg;
                } else if (rt == 'url5') {
                    this.aircraft.airworthinessCertificate = msg;
                }
            },
            // showupload: function () {
            //     this.isshow = false;
            //     this.isshowupload = true;
            // },
            // closeupload: function () {
            //     this.isshow = true;
            //     this.isshowupload = false;
            // },
            addPlanType: function () {


                let self = this;
                let request = {
                    aircraft: self.aircraft,
                    checkRecord: self.checkRecord,

                };

                self.$http.jsonPost('/eim/api/aircraft',
                    request
                ).then(function (response) {
                    alert("保存成功");
                    self.$router.push({path: '/flight/aircraftList'});
                })
                    .catch(function (error) {
                        console.log(error);
                        self.$emit('childshow', '1');
                    });
            },
            saveType:function () {
                let self=this;
                this.$refs.aircraftRef.validate((valid) => {
                    if (valid) {
                        if (self.isflg == 1) {
                            let buz = JSON.parse(Cookies.get("profile"));
                                self.$http.httpGet('/eim/api/aircraft/search/findaircraftbyregnumbers', {
                                    aircaftId: self.aircraft.aircraftId,
                                    regNumber: self.aircraft.regNumber,
                                    companyId: buz.buz.companyId
                                }).then(function (response) {
                                    let result = response.data.data;
                                    if (result != null && result != '') {
                                        alert("该航空器已存在，不能重复添加");
                                    } else {
                                        self.addPlanType();
                                    }
                                })
                                    .catch(function (error) {
                                        console.log(error);
                                        self.$emit('childshow', '1');
                                    });

                        }else{
                            let buz = JSON.parse(Cookies.get("profile"));
                                self.$http.httpGet('/eim/api/aircraft/search/findaircraftbyregnumberss', {
                                    regNumber: self.aircraft.regNumber,
                                    companyId: buz.buz.companyId
                                }).then(function (response) {
                                    let result = response.data.data;
                                    if (result != null && result != '') {
                                        alert("该航空器已存在，不能重复添加");
                                    } else {
                                        self.addPlanType();
                                    }
                                })
                                    .catch(function (error) {
                                        console.log(error);
                                        self.$emit('childshow', '1');
                                    });

                        }
                    } else {
                        alert("表单验证失败");
                    }
                });

            },
            cancel:function () {
                this.$router.push({path: '/flight/aircraftList'});
            }
        }
    }
</script>

<style scoped>
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