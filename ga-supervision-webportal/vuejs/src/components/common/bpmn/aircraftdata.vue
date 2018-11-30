<style>
    .ivu-select-selection {
        background-color: #b3b3b3 !important;
    }

    .ivu-input {
        background-color: #b3b3b3 !important;
    }

    .ivu-form-item-label {
        font-size: 14px !important;
        color: #ffffff !important;
    }

    .form-addair {
        width: 1018px;
        background: #222222;
        overflow: hidden;
        padding-top: 40px;
        padding-left: 15px;
        padding-right: 45px;
    }

    .div-form {
        width: 585px;
        float: left;
    }

    .div-form-is {
        width: 325px;
        float: left;
        padding-left: 35px;
        padding-top: 25px;
    }

    .main-addaircraft {
        height: 120px;
        float: left;
    }

    .div-3-addaircraft {
        width: 303px;
        float: left;
    }

    .ivu-date-picker {
        display: inline-block;
        line-height: normal;
        width: 100%;
    }

    .info-div-img {
        text-align: center;
    }

    .img-float-addair {
        width: 15px;
        display: -webkit-inline-box;
    }

    .div-retave {
        position: relative;
    }

    .div-img-abstar {
        z-index: 20;
        width: 100%;
        height: 97px;
        position: absolute;
        text-align: center;
        background: -o-linear-gradient(#151212, #222222);
        background: -moz-linear-gradient(#151212, #222222);
        background: linear-gradient(rgb(34, 34, 34), rgba(0, 0, 0, 0.25));
        padding-top: 50px;
    }

    .div-aircraftupload {
        position: absolute;
    }

    .div-aircraftup {
        position: absolute;
        top: 15px;
        left: 195px;
    }

    .div-retaveupload {
        position: relative;
        height: 35px;
    }

    .div-img-abstarupload {
        z-index: 20;
        width: 100%;
        height: 35px;
        position: absolute;
        text-align: center;
        background: -o-linear-gradient(#151212, #222222);
        background: -moz-linear-gradient(#151212, #222222);
        top: 10px;
        background: linear-gradient(rgba(0, 0, 0, 0.25), rgb(34, 34, 34));
    }

    .div-button-air {
        width: 120px;
        height: 30px;
        border-radius: 5px;
        border: none;
        background: #d8d8d8;
        margin-right: 30px;
        color: black;
    }

    .div-sumbit-but {
        text-align: center;
        margin-top: 26px;
        height: 50px;
    }


</style>
<template>
    <div style=" width:1020px;height: 520px;overflow: hidden; margin: 0 auto; ">
        <div style=" height: 520px;overflow-y: auto;width:1050px;">
            <Form :model="aircraft" :label-width="110" :rules="aircraftValidate" ref="aircraftRef">
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
                    <FormItem label="类别:" style="width: 45%;display: inline-block;" prop="aircraftClass">
                        <Select v-model="aircraft.aircraftClass" placeholder="请选择" style="width: 90%;">
                            <Option value="固定翼">固定翼</Option>
                            <Option value="旋翼">旋翼</Option>
                            <Option value="滑翔机">滑翔机</Option>
                            <Option value="其他">其他</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="型别:" style="width: 45%;display: inline-block;margin-left: 20px"
                              prop="aircraftType">
                        <Select v-model="aircraft.aircraftType" placeholder="请选择" style="width: 90%;">
                            <Option value="5700千克以上的航空器">5700千克以上的航空器</Option>
                            <Option value="3180千克以上的航空器">3180千克以上的航空器</Option>
                            <Option value="涡轮喷气动力飞机">涡轮喷气动力飞机</Option>
                            <Option value="其他航空器">其他航空器</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="航空器登记号:" style="width: 45%;display: inline-block;" prop="registernumber">
                        <Input v-model="aircraft.registernumber" placeholder="输入国籍登记证编号" style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="适航证编号:" style="width: 45%;display: inline-block;margin-left: 20px"
                              prop="airworthinessCertificateNumber">
                        <Input v-model="aircraft.airworthinessCertificateNumber" placeholder="输入适航证编号"
                               style="width: 90%;"></Input>
                    </FormItem>
                    <FormItem label="电台执照编号:" style="width: 45%;display: inline-block" prop="radioLicenceNumber">
                        <Input v-model="aircraft.radioLicenceNumber" placeholder="输入电台执照编号" style="width: 90%;"></Input>
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
                        <div class="flybase-submit-div"
                             style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px; width: 980px;    margin-bottom: 20px;">
                            <a data-v-942377fa="" href="javascript:;" @click="savechenk" class="submit-btn">保存</a>
                        </div>
                    </div>
                </div>
            </Form>
        </div>
    </div>
</template>
<script>
    import aircraftupload from '../../pages/aircraft/aircraftupload'

    export default {
        data() {
            return {
                isshow: true,
                isshowupload: false,
                companys: [],
                aircraft: {
                    aircraftModel: '',
                    manufacturer: '',
                    productionDate: '',
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
                    companyId: '',
                },
                aircraftValidate: {
                    aircraftModel: [{required: true, message: '航空器型号不能为空', trigger: 'blur'}],
                    manufacturer: [{required: true, message: '生产厂家不能为空', trigger: 'blur'}],
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
            let msg=this.$route.query.id;
            this.aircraftinfos(msg);
        },
        methods: {
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
                    productionDate: '',
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
                    companyId: '',
                }
            },
            sumbitaircraft: function () {
                this.$refs.aircraftRef.validate((valid) => {
                    if (valid) {
                        this.$emit('childshow', '1');
                        if (this.aircraft.productionDate) {
                            var d = new Date(this.aircraft.productionDate);
                            this.aircraft.productionDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                        }
                        let number = this.aircraft.enginenumber;
                        if(number==1){
                            this.aircraft.engineTwo='';
                            this.aircraft.engineThree='';
                            this.aircraft.engineFour='';
                        }
                        else if(number==2){
                            this.aircraft.engineThree='';
                            this.aircraft.engineFour='';
                        }
                        else if(number==3){
                            this.aircraft.engineFour='';
                        }
                        this.aircraft.functionintent = this.aircraft.functionintentList.join(",")
                        let self = this;
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
                    } else {
                        alert("表单验证失败");
                    }
                });
            },
            initurl(){
                this.$refs.aircraftupdateurl.url1='../../../../static/img/imgpictrue.png';
                this.$refs.aircraftupdateurl.url2='../../../../static/img/imgpictrue.png';
                this.$refs.aircraftupdateurl.url3='../../../../static/img/imgpictrue.png';
                this.$refs.aircraftupdateurl.url4='../../../../static/img/imgpictrue.png';
                this.$refs.aircraftupdateurl.url5='../../../../static/img/imgpictrue.png';
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
                self.$http.httpGet('/app/api/company', {}).then((res) => {
                    self.companys = res.data;

                }).catch(function (error) {
                    console.log(error);
                });
            },
            aircraftinfos: function (obj) {
                let self = this;
                self.$http.httpGet('/app/api/aircraft/' + obj, {}).then((res) => {
                    let datainfo = res.data;
                    console.log("self.aircraft===",self.aircraft);
                    for (var key in datainfo){
                        self.$set(self.aircraft,key, datainfo[key]);
                    }
                    self.aircraft.productionDate = new Date(this.aircraft.productionDate);
                    self.$set(self.aircraft,'functionintentList',self.aircraft.functionintent.split(","));
                    self.$refs.aircraftupdateurl.urlupdate(self.aircraft);
                }).catch(function (error) {

                });
            },
            savechenk:function () {
                let self=this;
                self.sumbitaircraft();
            }
        }
    }
</script>