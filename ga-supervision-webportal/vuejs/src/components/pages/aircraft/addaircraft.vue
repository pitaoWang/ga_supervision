<style>
    .form-addair {
        width: 1018px;
        background: #e2f2ff;
        overflow: hidden;
        padding-top: 40px;
        padding-left: 15px;
        padding-right: 45px;
    }
    .div-sumbit-but {
        text-align: center;
        margin-top: 26px;
        height: 50px;
    }
    .submit-bttn{
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 35px;
        background-color: #257fdd;
        border-radius: 3px;
        color: white;
    }
    a:hover{
        color:white;
    }

</style>
<template>
    <div style=" width:1020px;height: 520px;overflow: hidden">
        <div style=" height: 520px;overflow-y: auto;width:1050px;color: rgb(1,107,217);">
            <Form :model="aircraft" :label-width="130" :rules="aircraftValidate" ref="aircraftRef">
                <div class="form-addair">
                    <FormItem label="航空器型号:" style="width: 45%;display: inline-block;" prop="aircraftModel">
                        <Input v-model="aircraft.aircraftModel" placeholder="输入型号" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="生产厂家:" style="width: 45%;display: inline-block;margin-left: 20px"
                              prop="manufacturer">
                        <Input v-model="aircraft.manufacturer" placeholder="输入厂家" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="出厂日期:" style="width: 45%;display: inline-block;" prop="productionDate">
                        <Row>
                            <Col span="100">
                            <DatePicker type="date" placeholder="选择日期" v-model="aircraft.productionDate"
                                        style="width: 90%;"></DatePicker>
                            </Col>
                        </Row>
                    </FormItem>
                    <FormItem label="出厂序列号:" style="width: 45%;display: inline-block;margin-left: 20px"
                              prop="airframeid">
                        <Input v-model="aircraft.airframeid" placeholder="输入出厂序列号" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="航空器所有权:" style="width: 45%;display: inline-block;" prop="isRent">
                        <Select v-model="aircraft.isRent" placeholder="请选择" style="width: 90%;">
                            <Option value="自有">自有</Option>
                            <Option value="租用">租用</Option>
                        </Select>
                    </FormItem>
                    <FormItem v-if="aircraft.isRent=='租用'" label="归属企业名称:" style="width: 45%;display: inline-block;margin-left: 20px" prop="">
                        <Input v-model="aircraft.companyName" placeholder="请输入航空器归属公司" style="width: 90%;" @on-blur="validatecompany()"></Input>
                    </FormItem>
                    <FormItem v-else style="width: 45%;display: inline-block;margin-left: 20px" prop="">
                    </FormItem>
                    <FormItem label="类别:" style="width: 45%;display: inline-block;" prop="aircraftClass">
                        <Select v-model="aircraft.aircraftClass" placeholder="请选择" style="width: 90%;">
                            <Option value="固定翼">固定翼</Option>
                            <Option value="旋翼">旋翼</Option>
                            <Option value="滑翔机">滑翔机</Option>
                            <Option value="其他">其他</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="型别:" style="width: 45%;display: inline-block;margin-left: 20px" prop="aircraftType">
                        <Select v-model="aircraft.aircraftType" placeholder="请选择" style="width: 90%;">
                            <Option value="5700千克以上的航空器">5700千克以上的航空器</Option>
                            <Option value="3180千克以上的航空器">3180千克以上的航空器</Option>
                            <Option value="涡轮喷气动力飞机">涡轮喷气动力飞机</Option>
                            <Option value="其他航空器">其他航空器</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="航空器登记号:" style="width: 45%;display: inline-block;" prop="registernumber">
                        <Input v-model="aircraft.registernumber" placeholder="输入航空器登记号" style="width: 90%;"></Input>
                    </FormItem>
                    <!--<FormItem v-else label="航空器登记号:" style="width: 45%;display: inline-block;" prop="registernumber" >-->
                        <!--<Input v-model="aircraft.registernumber" placeholder="输入国籍登记证编号" style="width: 90%;" readonly ></Input>-->
                    <!--</FormItem>-->
                    <FormItem label="适航证编号:" style="width: 45%;display: inline-block;margin-left: 20px"
                              prop="airworthinessCertificateNumber">
                        <Input v-model="aircraft.airworthinessCertificateNumber" placeholder="输入适航证编号"
                               style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="电台执照编号:" style="width: 45%;display: inline-block" prop="radioLicenceNumber">
                        <Input v-model="aircraft.radioLicenceNumber" placeholder="输入电台执照编号" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="航空器状态:" style="width: 45%;display: inline-block;margin-left: 20px" prop="aircraftStatus">
                        <Select v-model="aircraft.aircraftStatus" placeholder="请选择" style="width: 90%;">
                            <Option value="可用">可用</Option>
                            <Option value="停用">停用</Option>
                            <Option value="报废">报废</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="运行区域:" style="width: 45%;" prop="area">
                        <Input v-model="aircraft.area" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="运行种类" class="bottom5" prop="functionintentList">
                        <CheckboxGroup v-model="aircraft.functionintentList">
                            <Checkbox label="一般商业">
                                <span>一般商业</span>
                            </Checkbox>
                            <Checkbox label="农林喷洒">
                                <span>农林喷洒</span>
                            </Checkbox>
                            <Checkbox label="训练（私照、商照）">
                                <span>训练（私照、商照）</span>
                            </Checkbox>
                            <Checkbox label="旋翼机外载荷">
                                <span>旋翼机外载荷</span>
                            </Checkbox>
                            <Checkbox label="空中旅游">
                                <span>空中旅游</span>
                            </Checkbox>
                            <Checkbox label="其他">
                                <span>其他</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </FormItem>

                    <FormItem label="发动机数量:" style="width: 45%;" prop="enginenumber">
                        <Select v-model.number="aircraft.enginenumber" placeholder="请选择" style="width: 90%;">
                            <Option :value=1>1</Option>
                            <Option :value=2>2</Option>
                            <Option :value=3>3</Option>
                            <Option :value=4>4</Option>
                        </Select>
                    </FormItem>

                    <FormItem label="1号发动机编号:" v-if="aircraft.enginenumber>0" style="width: 45%;display: inline-block;">
                        <Input v-model="aircraft.engineOne" placeholder="输入1号发动机编号" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="2号发动机编号:" v-if="aircraft.enginenumber>1"
                              style="width: 45%;display: inline-block;margin-left: 20px">
                        <Input v-model="aircraft.engineTwo" placeholder="输入2号发动机编号" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="3号发动机编号:" v-if="aircraft.enginenumber>2" style="width: 45%;display: inline-block;">
                        <Input v-model="aircraft.engineThree" placeholder="输入3号发动机编号" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="4号发动机编号:" v-if="aircraft.enginenumber>3"
                              style="width: 45%;display: inline-block;margin-left: 20px">
                        <Input v-model="aircraft.engineFour" placeholder="输入4号发动机编号" style="width: 90%;"></Input>
                    </FormItem>
                    <!--<div class="info-div-img"><img src="../../../../static/img/testjiantou.png" style="display: -webkit-inline-box;"/></div>-->
                    <div>
                        <aircraftupload v-on:childurlone="urldata" ref="aircraftupdateurl"></aircraftupload>
                    </div>
                    <div class="div-sumbit-but">
                        <div class="submit-div"
                             style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px; width: 980px;    margin-bottom: 20px;">
                            <a data-v-942377fa="" href="javascript:;" @click="savechenk" class="submit-bttn">保存</a>
                            <a data-v-942377fa="" href="javascript:;" @click="startaircraft" class="submit-bttn">提交审批</a>
                            <a data-v-942377fa="" href="javascript:;" @click="onclose" class="submit-bttn">返回</a>
                        </div>
                    </div>
                    <div style="width: 600px;height: 92px;float: left;margin-left: 118px;margin-top: 20px;font-size: 18px;color: red;">
                    注:航空器信息在提交等待审核的过程中不允许修改
                    </div>
                </div>
            </Form>
        </div>
    </div>
</template>
<script>
    import aircraftupload from './aircraftupload'

    export default {
        data() {
            return {
                isshow: true,
                isshowupload: false,
                isaddnew:false,
                companyId:'',
                companyName: '',
                aircraft: {
                    aircraftModel: '',
                    aircraftStatus:'',
                    isRent:'自有',
                    manufacturer: '',
                    productionDate: null,
                    airframeid: '',
                    aircraftClass: '',
                    aircraftType: '',
                    registernumber: '',
                    airworthinessCertificateNumber: '',
                    radioLicenceNumber: '',
                    area: '中华人民共和国全境（除港澳台）',
                    functionintent: '',//运行种类
                    functionintentList: [],
                    enginenumber: '',
                    engineOne: '',
                    engineTwo: '',
                    engineThree: '',
                    engineFour: '',
                    picturePath: '',
                    airworthinessFrontCatalogue: '',
                    airworthinessBackCatalogue: '',
                    radioLicenceCatalogue: '',
                    scanCatalogue: '',
                    companyId: null,
                    companyName:'',
                },
                aircraftValidate: {
                    aircraftModel: [{required: true, message: '航空器型号不能为空', trigger: 'blur'}],
                    manufacturer: [{required: true, message: '生产厂家不能为空', trigger: 'blur'}],
                    aircraftStatus:[{required: true, message: '航空器状态不能为空', trigger: 'change'}],
                    isRent:[{required: true, message: '航空器所有权不能为空', trigger: 'change'}],
//                    productionDate:[{required: true,type:"date",message: '出厂日期不能为空', trigger: 'change'}],
//                    airframeid:[{required: true, message: '出厂序列号不能为空', trigger: 'blur'}],
                    aircraftClass: [{required: true, message: '航空器类别不能为空', trigger: 'change'}],
                    aircraftType: [{required: true, message: '航空器型号不能为空', trigger: 'change'}],
                    registernumber: [{required: true, message: '国籍登记证号不能为空', trigger: 'blur'}],
                    airworthinessCertificateNumber: [{required: true, message: '适航证编号不能为空', trigger: 'blur'}],
                    radioLicenceNumber: [{required: true, message: '电台执照编号不能为空', trigger: 'blur'}],
                    area: [{required: true, message: '运行区域不能为空', trigger: 'blur'}],
                    functionintentList: [{required: true, type: 'array', min: 1, message: '运行种类至少选一个', trigger: 'change'}],
                    enginenumber: [{required: true, type: "number", message: '发动机数量不能为空', trigger: 'change'}]
                }
            }
        },
        components: {
            aircraftupload
        },
        mounted() {
            this.companylist();
        },
        methods: {
            validatecompany(){
                this.$http.httpGet('/app/api/company/search/infobyname', {
                    name: this.aircraft.companyName,
                }).then((res) =>{
                    let result = res.data;
                    if(result != null && result != ''){
                        alert("租用的航空器在本系统通航公司名下，无需维护航空器信息！")
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            initvalidate(){
                this.$refs['aircraftRef'].resetFields();
            },
            showupload: function () {
                this.isshow = false;
                this.isshowupload = true;
            },
            closeupload: function () {
                this.isshow = true;
                this.isshowupload = false;
            },
            initadd(){
                this.aircraft = {
                    aircraftModel: '',
                    manufacturer: '',
                    productionDate: null,
                    aircraftStatus:'',
                    isRent:'自有',
                    airframeid: '',
                    aircraftClass: '',
                    aircraftType: '',
                    registernumber: '',
                    airworthinessCertificateNumber: '',
                    radioLicenceNumber: '',
                    area: '中华人民共和国全境（除港澳台）',
                    functionintent: '',//运行种类
                    functionintentList: [],
                    enginenumber: '',
                    engineOne: '',
                    engineTwo: '',
                    engineThree: '',
                    engineFour: '',
                    picturePath: '',
                    airworthinessFrontCatalogue: '',
                    airworthinessBackCatalogue: '',
                    radioLicenceCatalogue: '',
                    scanCatalogue: '',
                    companyId: null,
                    companyName:'',
                }
            },
            sumbitaircraft: function () {
                this.$refs.aircraftRef.validate((valid) => {
                    if (valid) {
                        if(this.isaddnew == true) {
                            this.$http.httpGet('/app/api/aircraft/search/isexistaircraft', {
                                    registernumber: this.aircraft.registernumber,
                                }).then((res) => {
                                let result = res.data.data;
                                let exist = false;
                                for(let line of result){
                                    if(line.aircraftStatus != "报废"){
                                        exist = true;
                                    }
                                }
                                if (exist) {
                                    alert("该航空器已存在，不能重复添加");
                                }
                                else{
                                    this.savedata();
                                }
                            }).catch(function (error) {
                                console.log(error);
                            });
                        }
                        else{
                            this.savedata();
                        }
                    } else {
                        alert("表单验证失败");
                    }
                });
            },
            savedata(){
                let self = this;
                self.$emit('childshow', '1');
                self.$profile.getProfile().then((profile) => {
                    self.aircraft.companyId = self.$profile.getStoredProfile().buz.companyId;
                    if (self.aircraft.isRent == '自有') {
                        self.aircraft.companyName = self.companyName
                    }
                    if (self.aircraft.productionDate != null) {
                        var d = new Date(self.aircraft.productionDate);
                        self.aircraft.productionDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                    }
                    let number = self.aircraft.enginenumber;
                    if (number == 1) {
                        self.aircraft.engineTwo = '';
                        self.aircraft.engineThree = '';
                        self.aircraft.engineFour = '';
                    }
                    else if (number == 2) {
                        self.aircraft.engineThree = '';
                        self.aircraft.engineFour = '';
                    }
                    else if (number == 3) {
                        self.aircraft.engineFour = '';
                    }
                    self.aircraft.functionintent = self.aircraft.functionintentList.join(",");
                    self.$http.jsonPost('/app/api/aircraft',
                        self.aircraft
                    ).then(function (response) {
                        alert("保存成功");
                        self.$refs.aircraftupdateurl.updateclean();
                        self.$emit('childshow', '1');
                    })
                        .catch(function (error) {
                            console.log(error);
                            self.$emit('childshow', '1');
                        });
                }, (error) => {
                    console.log('error', error);
                });
            },
            initurl(){
                this.$refs.aircraftupdateurl.url1='';
                this.$refs.aircraftupdateurl.url2='';
                this.$refs.aircraftupdateurl.url3='';
                this.$refs.aircraftupdateurl.url4='';
                this.$refs.aircraftupdateurl.url5='';
            },
            urldata(msg, rt) {
                if (rt == 'url1') {
                    this.aircraft.picturePath = msg;
                } else if (rt == 'url2') {
                    this.aircraft.airworthinessFrontCatalogue = msg;
                } else if (rt == 'url3') {
                    this.aircraft.airworthinessBackCatalogue = msg;
                } else if (rt == 'url4') {
                    this.aircraft.scanCatalogue = msg;
                } else if (rt == 'url5') {
                    this.aircraft.radioLicenceCatalogue = msg;
                }
            },
            onclose: function () {
                this.aircraft = {};
                this.$refs.aircraftupdateurl.updateclean();
                this.$emit('childshow', '1');
            },
            companylist: function () {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    self.companyId = self.$profile.getStoredProfile().buz.companyId;
                    self.$http.httpGet('/app/api/company/'+ self.companyId
                    ).then(function (response) {
                        let datainfo = response.data;
                        self.companyName= datainfo.name;
                    }).catch(function (error) {
                        console.log(error);
                    });
                }, (error) => {
                    console.log('error', error);
                });
            },
            aircraftinfos: function (obj) {
                let self = this;
                self.$http.httpGet('/app/api/aircraft/' + obj, {}).then((res) => {
                    let datainfo = res.data;
//                    this.aircraft= datainfo;
                    console.log("self.aircraft===",self.aircraft);
                    for (var key in datainfo) {
                        self.$set(self.aircraft,key, datainfo[key]);
                    }
                    console.log("self.aircraft===",self.aircraft);
                    if(this.aircraft.productionDate!=null)
                    {
                        self.aircraft.productionDate = new Date(this.aircraft.productionDate);
                    }
                    self.$set(self.aircraft,'functionintentList',self.aircraft.functionintent.split(","));
                    self.$refs.aircraftupdateurl.urlupdate(self.aircraft);
                }).catch(function (error) {

                });
            },
            startaircraft: function () {
                let self =this;
                if (self.aircraft.aircraftId) {
                    self.$http.httpGet('/app/api/processrun/businesskeyrun', {
                        defId: 'hkqzlsh',
                        businessKey: this.aircraft.aircraftId
                    }).then(function (response) {
                        if (response.data.data) {
                            alert("不可重复发起流程");
                        } else {
                            self.startrun();
                        }
                    }).catch(function (error) {
                        alert("获取流程信息失败");
                    });
                } else {
                    alert("请先保存");
                }
            },
            startrun: function () {
                this.$http.httpGet('/app/api/bpmtask/search/startdata', {
                    defId: 'hkqzlsh',
                    businessKey: this.aircraft.aircraftId
                }).then(function (response) {
                    alert("提交成功");
                }).catch(function (error) {
                    console.log(error);
                });
            },
            savechenk:function () {
                let self=this;
                if(self.aircraft.aircraftId){
                    self.$http.httpGet('/app/api/processrun/businesskeyrun', {
                        defId:'hkqzlsh',
                        businessKey:self.aircraft.aircraftId
                    }).then(function (response) {
                        if(response.data.data){
                            alert("等待审核流程不可修改");
                        }else{
                            self.sumbitaircraft();
                        }
                    }) .catch(function (error) {
                        console.log(error);
                    });
                }else{
                    self.sumbitaircraft();
                }
            }
        }
    }
</script>