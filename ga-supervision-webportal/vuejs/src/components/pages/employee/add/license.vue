<template>

    <div style="width:97%;display:inline-table;margin-left:15px;">
        <img src="/static/images/基础资料图标.png" style="position: absolute;margin-left: 22px;margin-top: 99px;"/>

        <div style="height: 36px;margin-bottom: 3px;padding-left: 55px;padding-top: 5px;  margin-top: 91px;background-color: rgb(46, 130, 220)">
            <div style="font-size: 16px;">{{textcontent}}</div>
        </div>
        <Form :label-width="140" :model="qualification" ref="formVali" >
            <div class="bottom5" style="border:1px solid rgba(1,107,217,.5);background-color:rgba(193,220,248,.5);">
                <h2>执照基本信息</h2>
                <Row>
                    <Col :md="12">
                        <FormItem label="请选择执照类别" class="bottom5" prop="licensetype" :rules="{required: true,message: '请选择执照类别', trigger: 'change'}">
                            <Select v-model="qualification.licensetype" placeholder="请选择" @on-change="changeExt">
                                <Option value="气象执照">气象执照</Option>
                                <Option value="情报执照">情报执照</Option>
                                <Option value="飞行签派员执照">飞行签派员执照</Option>
                                <Option value="飞行执照">飞行执照</Option>
                                <Option value="航空器维修执照">航空器维修执照</Option>
                                <Option value="航空器部件维修执照">航空器部件维修执照</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col :md="11">
                        <FormItem label="执照编号" class="bottom5" prop="licenseno" :rules="{required: true, message: '执照编号不能为空', trigger: 'blur'}" >
                            <Input v-model="qualification.licenseno" placeholder="请输入"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <FormItem label="是否长久有效">
                        <RadioGroup v-model="radio" style="color: #016bd9">
                            <Radio label="yes">是</Radio>
                            <Radio label="no">否</Radio>
                        </RadioGroup>
                    </FormItem>
                </Row>
                <Row>
                    <Col :md="12">
                        <FormItem label="执照颁发时间" class="bottom5" prop="issuedate" :rules="{required: true, type: 'date',message: '请选择日期', trigger: 'change'}">
                            <DatePicker v-model="qualification.issuedate" type="date" placeholder="选择颁发时间"></DatePicker>
                        </FormItem>
                    </Col>
                    <Col :md="11" v-if="radio=='no'">
                        <FormItem label="执照到期时间" class="bottom5" >
                            <DatePicker v-model="qualification.expiredate" type="date" placeholder="选择到期时间" ></DatePicker>
                        </FormItem>
                    </Col>
                </Row>
            </div>
        </Form>
        <Form :label-width="120" v-show=flightdislicenseshow  >
            <flightdislicense ref="flightdis" :flightdispatcher=flightDispatcherLicense v-on:getChildMsg="getLicenseExt"></flightdislicense>

        </Form>
        <Form :label-width="120" v-show=weatherlicenseshow  >
            <weatherlicense ref="abc" :weatherlicense=weatherlicense  v-on:getChildMsg="getLicenseExt"></weatherlicense>
        </Form>
        <Form :label-width="120" v-show=intelligencelicenseshow >
            <intelligencelicense ref="intelligeninfo"></intelligencelicense>
        </Form>
        <Form :label-width="120" :model="license" v-show=pilotlicenseinfoshow >
            <pilotlicenseinfo  ref="pilotlicense"></pilotlicenseinfo>
        </Form>

        <div  v-show=AiPartsRepairshow class="bottom5">
            <addaprl :personQulificationInfo=qualification ref="addaprl"    v-on:getChildMsg="getLicenseExt"></addaprl>
        </div>
        <div  v-show=aircraftRepairShow class="bottom5">
            <addarl :personQulificationInfo=qualification ref="addarl"    v-on:getChildMsg="getLicenseExt"></addarl>
        </div>

        <div class="bottom5" style="margin-top:10px;" v-show="checkrecordshow">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <h2 style="float:left;">执照签注记录</h2>
                    <Button type="primary" @click="add_dialog_show=true" icon="plus"
                            style="width:150px;margin-right:10px;" size="small">增  加</Button>
                </span>

            <div class="bottom5" style="margin-top:5px;">
                <Table border :columns="columns7" :data="licenseChecks"></Table>
            </div>

        </div>
        <div class="bottom5" style="height:190px;border:1px solid rgba(1,107,217,.5);background-color:rgba(193,220,248,.5);">
            <h2>执照扫描件</h2>
            <uploadfile ref="upload" :uploadList=uploadList v-on:listenTochildEvent="getuploadList" ></uploadfile>
            <h4 style="font-size: 0.7rem;text-align: left; color: lightslategray;margin-left: 10px">图片小于2M尺寸小于640*480</h4>

        </div>
        <div class="bottom5" style="margin-top:10px;height:180px;">
            <div class="bottom5" style="margin-top:20px;height:40px;">
                <div  style="padding-left:100px;float: left; text-align: center; padding-bottom: 10px; padding-top: 10px; margin-left: 200px;">
                    <a data-v-942377fa="" href="javascript:;" @click="submitLicenseInfo" class="gacompany-btton">保存</a>

                    <a data-v-942377fa="" href="javascript:;" @click="tosshangyiye" class="gacompany-btton">返回</a>
                </div>
            </div>

        </div>

        <Modal v-model="add_dialog_show" width="400" title="新增执照签注记录" @on-ok="submitCheckForm"
               @on-cancel="add_dialog_show = false" :styles="{top: '20px'}">
            <Form :label-width="100" v-model="qualification" >
                <div >
                    <Row>
                        <Col :md="23">
                            <FormItem label="签注时间" class="bottom5">
                                <DatePicker v-model="check_form.licenseCheckDate" type="date" placeholder="选择日期"></DatePicker>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="签注内容" class="bottom5">
                                <Input v-model="check_form.licenseCheckContent" type="textarea" :rows="4" placeholder="请输入签注内容..."></Input>
                            </FormItem>
                        </Col>
                    </Row>
                </div>
            </Form>
        </Modal>
    </div>


</template>

<style scoped>
    .bottom5 {
        color: #657180;
        margin-bottom: 15px;
    }

</style>

<script>
    import uploadfile from '../uploadfile.vue';
    import flightdislicense from '../flightdispatcherlicense.vue'
    import weatherlicense from '../weatherlicense.vue'
    import intelligencelicense from '../intelligencelicense.vue'
    import pilotlicenseinfo from '../pilotlicenseinfo.vue'
    import addaprl from '../../aircraft/addaprl.vue'
    import addarl from '../../aircraft/addarl.vue'
    export default {
        components: {
            'uploadfile':uploadfile,
            flightdislicense,
            weatherlicense,
            intelligencelicense,
            pilotlicenseinfo,
            addaprl,
            addarl,
        },
        props:['employeeId'],
        data (){
            return {
                radio:'yes',
                textcontent:'新增执照',
                modeldata:{
                    qualification:{},
                    license:{},
                },
                checklicenseindex:'',
                childInfo:'',
                columns7: [
                    {
                        title: '签注时间',
                        key: 'licenseCheckDate',
                        align: 'center',
                        width: 120
                    },
                    {
                        title: '签注内容',
                        width: 623,
                        key: 'licenseCheckContent',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 235,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }, '修改'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.removeCheck(params.index)
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],
                qualification:{},
                licenseChecks: [],
                groundinstructorlicense:{},
                add_dialog_show: false,
                check_form: {},
                uploadList:[],
                flightdislicenseshow:false,
                weatherlicenseshow:false,
                intelligencelicenseshow:false,
                pilotlicenseinfoshow:false,
                weatherlicense:{},
                flightDispatcherLicense:{
//                    id: '',
//                    licenseState: '',
//                    licenseProp: '',
//                    holderName: '',
//                    companyId: '',
                },
                checkrecordshow:true,
                AiPartsRepairshow:false,
                aircraftRepairShow:false,
                temp:{
                    id:'',
                    licenseId:'',
                    licenseCheckDate:'',
                    licenseCheckContent:''
                },
                license:{},
//                flightdispatcher:{}
            }
        },
        created(){
            if(this.$route.query.licenseId) {
                this.textcontent='修改执照'
                let licenseId = this.$route.query.licenseId;
                let licensetype = this.$route.query.licensetype;

                this.init(licenseId, licensetype)
            }
        },
        mounted(){
            this.license=this.$refs.pilotlicense.license;
        },
        methods: {
            changeExt() {
                console.log("ooo");
                if (this.qualification.licensetype == "气象执照") {
                    this.weatherlicenseshow = true;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow=false;
                    this.AiPartsRepairshow=false;
                    this.aircraftRepairShow=false;
                    this.checkrecordshow=true;
                }
                else if (this.qualification.licensetype == "飞行签派员执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = true;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow=false;
                    this.AiPartsRepairshow=false;
                    this.checkrecordshow=true;
                    this.aircraftRepairShow=false;
                }
                else if (this.qualification.licensetype == "情报执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = true;
                    this.pilotlicenseinfoshow=false;
                    this.AiPartsRepairshow=false;
                    this.checkrecordshow=true;
                    this.aircraftRepairShow=false;
                }

                else if (this.qualification.licensetype == "航空器部件维修执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicense = false;
                    this.pilotlicenseinfoshow=false;
                    this.AiPartsRepairshow=true;
                    this.checkrecordshow=false;
                    this.aircraftRepairShow=false;
                }
                else if (this.qualification.licensetype == "飞行执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow=true;
                    this.AiPartsRepairshow=false;
                    this.checkrecordshow=true;
                    this.aircraftRepairShow=false;
                }else if(this.qualification.licensetype == "航空器维修执照")
                {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow=false;
                    this.AiPartsRepairshow=false;
                    this.checkrecordshow=false;
                    this.aircraftRepairShow=true;
                }

            },
            getLicenseExt(ext) {
                console.log(ext, 'ext...............');
                this.license = ext;
                console.log(this.license, 'license...............');
            },
            reseting() {
                this.getLicenseInfo();
            },
            updateCheck(index) {

                this.temp.id=this.licenseChecks[index].id;
                this.temp.licenseId=this.licenseChecks[index].licenseId;
                this.temp.licenseCheckDate=this.licenseChecks[index].licenseCheckDate;
                this.temp.licenseCheckContent=this.licenseChecks[index].licenseCheckContent;
                this.check_form = this.temp;
                this.checklicenseindex = index;
                this.add_dialog_show = true;
            },
            removeCheck(index) {
                let self = this;
                let role = "pilot";
                let url = "/app/api";
                if (role == "pilot") {
                    let checkUrl = url + "/license/check/delete/" + this.licenseChecks[index].id;
                    console.log(checkUrl)

                    self.$http.httpDelete(checkUrl, {}).then((res) => {
                        let result = res.data
                        if (result.resultCode == 200) {
                            self.licenseChecks.splice(index, 1);
                            self.$Message.info("删除成功，id=" + result.data);
                        }
                    });
                }

            },
            getuploadList(msg) {

            },
            submitLicenseInfo() {
                //this.getLicenseExt(ext);

                let self = this;
                alert(self.employeeId);
                let url = "/app/api";
                let qulificationUrl = url + "/qulification/save/license";
                let request = {}
                self.$refs.formVali.validate((valid) => {
                    if(valid) {
                        self.$profile.getProfile().then((profile) => {
                                if (profile.buz && profile.buz.employeeId) {
                                    if(self.radio=='yes'){
                                        self.qualification.expiredate=null;
                                    }
                                    self.qualification.employeeId = profile.buz.employeeId;
                                    self.qualification.flag = 2;
                                    self.qualification.filePath = self.uploadList.join(",");
                                    self.$refs.abc.weatherlicense.licensearea = self.$refs.abc.businesslist.join(",");
                                    if (this.qualification.licensetype == "气象执照") {
                                        //验证气象执照信息不能为空
                                        if (
                                            self.$refs.abc.weatherlicense.obsereffect == '' ||
                                            self.$refs.abc.weatherlicense.forecasteffect == '' ||
                                            self.$refs.abc.weatherlicense.obserequipeffect == '' ||
                                            self.$refs.abc.weatherlicense.radarequipeffect == '' ||
                                            self.$refs.abc.weatherlicense.infoequipeffect == '' ||
                                            self.$refs.abc.weatherlicense.licensearea == ''
                                        ) {
                                            this.$Message.error("表单验证失败");
                                            return;
                                        }


                                        request = {
                                            personQulificationInfo: self.qualification,
                                            weatherLicense: self.$refs.abc.weatherlicense,
                                            licenseChecks: self.licenseChecks
                                        }

                                    }
                                    else if (this.qualification.licensetype == "飞行签派员执照") {
                                        request = {
                                            personQulificationInfo: self.qualification,
                                            flightDispatcherLicense: self.$refs.flightdis.flightdispatcher,
                                            licenseChecks: self.licenseChecks

                                        }
                                    }
                                    else if (this.qualification.licensetype == "情报执照") {
                                        self.$refs.intelligeninfo.intelligencelicense.licenseArea = self.$refs.intelligeninfo.businesslist.join(",");
                                        request = {
                                            personQulificationInfo: self.qualification,
                                            intelligenceLicense: self.$refs.intelligeninfo.intelligencelicense,
                                            licenseChecks: self.licenseChecks
                                        }
                                    }
                                    else if (this.qualification.licensetype == "飞行执照") {
                                        request = {
                                            personQulificationInfo: self.qualification,
                                            pilotLicense: self.$refs.pilotlicense.license,
                                            licenseChecks: self.licenseChecks
                                        }
                                    }
                                    else if (this.qualification.licensetype == "航空器部件维修执照") {

                                        this.$refs.addaprl.handlesubmit();
                                    } else if (this.qualification.licensetype == "航空器维修执照") {

                                        this.$refs.addarl.handlesubmit();
                                    }

                                    self.$http.jsonPost(qulificationUrl, request).then((res) => {
                                        let result = res.data
                                        if (result.resultCode == 200) {
//                                            self.$Message.info('执照信息保存成功');
                                            alert("执照信息保存成功！");
                                            this.$router.push({path: '/pilot_info', query: {qualificationisshow: true}});
                                        }
                                    }).catch(function (error) {
                                        console.log(error);
                                    });
                                }
                            },
                            (error) => {
                                self.$Message.error('user profile error = ' + error);
                            });
                    }
                    else{
                        this.$Message.error('表单验证失败!');
                        return;
                    }
                })



            },
            submitCheckForm() {
                let self = this;
                let url = "/app/api";
                let checkUrl = url + "/license/check/save";
                let is_update = false;
                if (self.check_form.id) {
                    is_update = true;
                }


                self.$http.httpPost(checkUrl, self.check_form).then(function (res) {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.check_form = {};
                        if (!is_update) {
                            self.licenseChecks.push(result.data);
                        }
                        else{
                            self.licenseChecks.splice(self.checklicenseindex,1,self.temp)
                            self.licenseChecks[self.checklicenseindex] = result.data;
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            tosshangyiye() {

                this.$router.push({path: '/pilot_info', query: {qualificationisshow: true}});

            },
            init(val1,val2) {
                let self = this;
                let url = "/app/api";

                let qulificationUrl = url + "/qulification/search/license";
                self.$profile.getProfile().then((profile) => {
                    if (profile.buz && profile.buz.employeeId) {


                        if (val2 == "航空器部件维修执照") {
                            self.AiPartsRepairshow = true;
                            self.checkrecordshow = false;
                            self.$http.httpGet('/app/api/qulification/' + val1, {}).then((res) => {
                                self.qualification = res.data;
                                if(self.qualification.expiredate=='2099-09-09'||self.qualification.expiredate=='2099-09-06'||self.qualification.expiredate===null) {
                                    self.radio = 'yes';
                                }else{
                                    self.radio = 'no';
                                }
                                if( res.data.filePath!=''&res.data.filePath!=null) {
                                    self.uploadList = res.data.filePath.split(",");
                                }
                                self.$refs.addaprl.initinfo(val1);
                                return;
                            }).catch(function (error) {
                                console.log(error);
                                return;
                            });

                        }else if (val2 == "航空器维修执照") {
                            self.aircraftRepairShow = true;
                            self.checkrecordshow = false;
                            self.$http.httpGet('/app/api/qulification/' + val1, {}).then((res) => {
                                self.qualification = res.data;
                                if(self.qualification.expiredate=='2099-09-09'||self.qualification.expiredate=='2099-09-06'||self.qualification.expiredate===null) {
                                    self.radio = 'yes';
                                }else{
                                    self.radio = 'no';
                                }
                                if( res.data.filePath!=''&res.data.filePath!=null) {
                                    self.uploadList = res.data.filePath.split(",");
                                }
                                self.$refs.addarl.initinfo(val1);
                                return;
                            }).catch(function (error) {
                                console.log(error);
                                return;
                            });

                        }
                        else {


                            self.$http.httpGet(qulificationUrl,{id: val1,  licensetype: val2}).then((res) => {
                                let result = res.data;
                                self.qualification=result.data.personQulificationInfo;
                                if(self.qualification.expiredate=='2099-09-09'||self.qualification.expiredate=='2099-09-06'||self.qualification.expiredate===null) {
                                    self.radio = 'yes';
                                }else{
                                    self.radio = 'no';
                                }
                                if(result.data.personQulificationInfo.filePath!=''&result.data.personQulificationInfo.filePath!=null){
                                    self.uploadList =result.data.personQulificationInfo.filePath.split(",");
                                }

                                if(val2=="气象执照"){
                                    self.weatherlicenseshow=true;
                                    self.$refs.abc.weatherlicense=result.data.weatherLicense;
                                    self.$refs.abc.businesslist=result.data.weatherLicense.licensearea.split(",");
                                    self.licenseChecks=result.data.licenseChecks;
                                }
                                if(val2=="飞行签派员执照"){
                                    self.flightdislicenseshow=true;
                                    self.qualification=result.data.personQulificationInfo;
                                    self.weatherlicense=result.data.weatherLicense;
                                    self.$refs.flightdis.flightdispatcher=result.data.flightDispatcherLicense;
                                    self.licenseChecks=result.data.licenseChecks;
                                }
                                if(val2=="情报执照"){
                                    self.intelligencelicenseshow=true;
                                    self.$refs.intelligeninfo.intelligencelicense=result.data.intelligenceLicense;
                                    self.$refs.intelligeninfo.businesslist=result.data.intelligenceLicense.licenseArea.split(",");
                                    self.licenseChecks=result.data.licenseChecks;
                                }


                            }).catch(function (error) {
                                console.log(error);
                                return;
                            });

                        }
                    }
                }, (error) => {
                    console.log('error', error);
                });
            }

        },
        watch: {
            'uploadList':function (newval) {
                console.log(newval);

            }
        }
    }

</script>

<style scoped>
    .image-list {
        margin: 10px 10px;
        display: inline-block;
        width: 150px;
        height: 150px;
        text-align: center;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        background: #fff;
        position: relative;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
        margin-right: 10px;
    }

    .image-list img {
        width: 100%;
        height: 100%;
    }

    .image-list-cover {
        display: none;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0, 0, 0, .6);
    }

    .image-list:hover .image-list-cover {
        display: block;
    }

    .image-list-cover i {
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
    .gacompany-btton {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: white;
        background-color: #257fdd;
    }
    /*.gacompany-div a:hover {*/
    /*display: inline-block;*/
    /*width: 120px;*/
    /*height: 35px;*/
    /*line-height: 30px;*/
    /*border: 1px solid #ececec;*/
    /*border-radius: 3px;*/
    /*}*/
    .steps {
        margin: auto;
        padding-top: 34px;
        width: 893px;
        height: 150px;
        vertical-align: middle;

    }

    .stepsimg > img {
        width: 25%;
        float: left;
    }
</style>