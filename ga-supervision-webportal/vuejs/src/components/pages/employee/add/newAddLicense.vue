<template>
    <div style="width: 1280px; height:auto; margin-top: 30px; padding-top: 40px; background:white;">
        <div style="border: 1px solid #f0f0f0; margin-left: 40px; margin-right: 40px ">
            <Form :label-width="205" :model="qualification" ref="formVali" style="margin-top: 20px">
                <div>
                    <div>
                        <FormItem label="请选择执照类别" style="display: inline-block;width: 550px" prop="licensetype" :rules="{required: true,message: '请选择执照类别', trigger: 'change'}">
                            <Select v-model="qualification.licensetype" placeholder="请选择" @on-change="changeExt">
                                <Option value="气象执照">气象执照</Option>
                                <Option value="情报执照">情报执照</Option>
                                <Option value="管制执照">管制执照</Option>
                                <Option value="飞行签派员执照">飞行签派员执照</Option>
                                <Option value="飞行执照">飞行执照</Option>
                                <Option value="航空器维修执照">航空器维修执照</Option>
                                <Option value="航空器部件维修执照">航空器部件维修执照</Option>
                            </Select>
                        </FormItem>

                        <FormItem label="执照编号"  style="display: inline-block;width: 550px" prop="licenseno" :rules="{required: true, message: '执照编号不能为空', trigger: 'blur'}" >
                            <Input v-model="qualification.licenseno" placeholder="请输入"/>
                        </FormItem>
                    </div>

                    <div>
                        <FormItem label="是否长久有效" style="display: inline-block;width: 550px">
                            <RadioGroup v-model="radio" style="color: #016bd9">
                                <Radio label="yes" style="color: #5b6270">是</Radio>
                                <Radio label="no" style="color: #5b6270">否</Radio>
                            </RadioGroup>
                        </FormItem>
                        <FormItem label="颁发单位" style="display: inline-block;width: 550px" prop="issueUnit" :rules="{required: true, message: '颁发单位不能为空', trigger: 'blur'}">
                            <Input v-model="qualification.issueUnit" placeholder="请输入"></Input>
                        </FormItem>
                    </div>

                    <div>
                        <FormItem label="执照颁发时间" style="display: inline-block;width: 550px"  prop="issuedate" :rules="{required: true, type: 'date',message: '请选择日期', trigger: 'change'}">
                            <DatePicker v-model="qualification.issuedate" type="date" placeholder="选择颁发时间" style="width: 345px"></DatePicker>
                        </FormItem>

                        <FormItem label="执照到期时间" style="display: inline-block;width: 550px" v-if="radio=='no'">
                            <DatePicker v-model="qualification.expiredate" type="date" placeholder="选择到期时间" style="width: 345px"></DatePicker>
                        </FormItem>
                    </div>
                </div>
            </Form>
            <Form :label-width="205" v-show=flightdislicenseshow  >
                <flightdislicense ref="flightdis" :flightdispatcher=flightDispatcherLicense v-on:getChildMsg="getLicenseExt"></flightdislicense>

            </Form>
            <Form :label-width="205" v-show=weatherlicenseshow  >
                <weatherlicense ref="abc" :weatherlicense=weatherlicense  v-on:getChildMsg="getLicenseExt"></weatherlicense>
            </Form>
            <Form :label-width="205" v-show=intelligencelicenseshow >
                <intelligencelicense ref="intelligeninfo"></intelligencelicense>
            </Form>
            <Form :label-width="205" :model="license" v-show=pilotlicenseinfoshow >
                <pilotlicenseinfo  ref="pilotlicense"></pilotlicenseinfo>
            </Form>

            <div  v-show=AiPartsRepairshow class="bottom5">
                <addaprl :personQulificationInfo=qualification ref="addaprl"    v-on:getChildMsg="getLicenseExt"></addaprl>
            </div>
            <div  v-show=aircraftRepairShow class="bottom5">
                <addarl :personQulificationInfo=qualification  :employee=employee ref="addarl"    v-on:getChildMsg="getLicenseExt"></addarl>
            </div>

            <div  style="" v-show="checkrecordshow">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <Button type="primary" @click="add_dialog_show=true" icon="plus" style="width: 98px;height: 32px;margin:24px 20px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>

                <div style="width: 1160px;margin-left: 20px">
                    <Table border :columns="columns7" :data="licenseChecks"></Table>
                </div>

            </div>
            <div  style="margin-left: 10px">
                <uploadfile ref="upload" :uploadList=uploadList v-on:listenTochildEvent="getuploadList" ></uploadfile>
                <h4 style="font-size: 0.7rem;text-align: left; color: lightslategray;margin-left: 10px">执照扫描件<br>图片小于2M尺寸小于640*480</h4>
            </div>

            <div class="bottom5" style="margin-top:10px;height:180px;">
                <div class="bottom5" style="margin-top:20px;height:40px;">
                    <div  style="padding-left:100px;float: left; text-align: center; padding-bottom: 10px; padding-top: 10px; margin-left: 700px;">
                        <a data-v-942377fa="" href="javascript:;" @click="submitLicenseInfo" class="submit-btn-user">保存</a>
                        <a data-v-942377fa="" href="javascript:;" @click="tosshangyiye" class="submit-btn-user">返回</a>
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
    import controllicense from '../controllicense.vue'

    export default {
        components: {
            'uploadfile': uploadfile,
            flightdislicense,
            weatherlicense,
            intelligencelicense,
            pilotlicenseinfo,
            addaprl,
            addarl,
            controllicense
        },
        data() {
            return {
                radio: 'yes',
                textcontent: '新增执照',
                modeldata: {
                    qualification: {},
                    license: {},
                },
                checklicenseindex: '',
                childInfo: '',
                columns7: [
                    {
                        title: '签注时间',
                        key: 'licenseCheckDate',
                        align: 'center',
                        width: 518
                    },
                    {
                        title: '签注内容',
                        width: 519,
                        key: 'licenseCheckContent',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
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
                                        background: 'url(../../../../../static/images/icon_operate_edit.png) no-repeat',
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
                                        marginLeft: '4px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.removeCheck(params.index);
                                        }
                                    }
                                })

                            ]);
                        }
                    }
                ],
                qualification: {},
                licenseChecks: [],
                groundinstructorlicense: {},
                add_dialog_show: false,
                check_form: {},
                uploadList: [],
                flightdislicenseshow: false,
                weatherlicenseshow: false,
                intelligencelicenseshow: false,
                pilotlicenseinfoshow: false,
                controllicenseshow: false,
                weatherlicense: {},
                flightDispatcherLicense: {
//                    id: '',
//                    licenseState: '',
//                    licenseProp: '',
//                    holderName: '',
//                    companyId: '',
                },
                checkrecordshow: true,
                AiPartsRepairshow: false,
                aircraftRepairShow: false,
                type:'',
                temp: {
                    id: '',
                    licenseId: '',
                    licenseCheckDate: '',
                    licenseCheckContent: ''
                },
                license: {},
                employeeid:'',
                employee:'',
//                flightdispatcher:{}
            }
        },
        created() {
            if (this.$route.query.licenseId) {
               // this.textcontent = '修改执照'
                let licenseId = this.$route.query.licenseId;
                let licensetype = this.$route.query.licensetype;
                this.employeeid =this.$route.query.employeeId;
                this.employee = this.$route.query.employee;
                this.init(licenseId, licensetype)
            }
        },
        mounted() {
            this.license = this.$refs.pilotlicense.license;
            this.qualification.employeeId = this.$route.query.id;
            this.type=this.$route.query.type;


        },
        methods: {
            changeExt() {
                console.log("ooo");
                if (this.qualification.licensetype == "气象执照") {
                    this.weatherlicenseshow = true;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow = false;
                    this.AiPartsRepairshow = false;
                    this.aircraftRepairShow = false;
                    this.checkrecordshow = true;
                    this.controllicenseshow = false;
                }
                else if (this.qualification.licensetype == "飞行签派员执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = true;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow = false;
                    this.AiPartsRepairshow = false;
                    this.checkrecordshow = true;
                    this.aircraftRepairShow = false;
                    this.controllicenseshow = false;
                }
                else if (this.qualification.licensetype == "情报执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = true;
                    this.pilotlicenseinfoshow = false;
                    this.AiPartsRepairshow = false;
                    this.checkrecordshow = true;
                    this.aircraftRepairShow = false;
                    this.controllicenseshow = false;
                }

                else if (this.qualification.licensetype == "航空器部件维修执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicense = false;
                    this.pilotlicenseinfoshow = false;
                    this.AiPartsRepairshow = true;
                    this.checkrecordshow = false;
                    this.aircraftRepairShow = false;
                    this.controllicenseshow = false;
                }
                else if (this.qualification.licensetype == "飞行执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow = true;
                    this.AiPartsRepairshow = false;
                    this.checkrecordshow = true;
                    this.aircraftRepairShow = false;
                    this.controllicenseshow = false;
                } else if (this.qualification.licensetype == "航空器维修执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow = false;
                    this.AiPartsRepairshow = false;
                    this.checkrecordshow = false;
                    this.aircraftRepairShow = true;
                    this.controllicenseshow = false;
                } else if (this.qualification.licensetype == "管制执照") {
                    this.weatherlicenseshow = false;
                    this.flightdislicenseshow = false;
                    this.intelligencelicenseshow = false;
                    this.pilotlicenseinfoshow = false;
                    this.AiPartsRepairshow = false;
                    this.checkrecordshow = true;
                    this.aircraftRepairShow = false;
                    this.controllicenseshow = true;

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

                this.temp.id = this.licenseChecks[index].id;
                this.temp.licenseId = this.licenseChecks[index].licenseId;
                this.temp.licenseCheckDate = this.licenseChecks[index].licenseCheckDate;
                this.temp.licenseCheckContent = this.licenseChecks[index].licenseCheckContent;
                this.check_form = this.temp;
                this.checklicenseindex = index;
                this.add_dialog_show = true;
            },
            removeCheck(index) {
                let self = this;
                let role = "pilot";
                let url = "/eim/api";
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
                let url = "/eim/api";
                let qulificationUrl = url + "/qulification/save/license";
                let request = {}
                self.$refs.formVali.validate((valid) => {
                    if (valid) {
                        if (self.radio == 'yes') {
                            self.qualification.expiredate = null;
                        }
                        // self.qualification.employeeId = profile.buz.employeeId;
                        // alert(self.qualification.employeeId);
                        self.qualification.flag = 2;
                        self.qualification.filePath = self.uploadList.join(",");
                        // self.$refs.abc.weatherlicense.licensearea = self.$refs.abc.businesslist.join(",");
                        if (this.qualification.licensetype == "气象执照") {
                            self.$refs.abc.weatherlicense.licensearea = self.$refs.abc.businesslist.join(",");
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
                        else if (this.qualification.licensetype == "管制执照") {
                            request = {
                                personQulificationInfo: self.qualification,
                                /*controllicense: self.$refs.control.controllicense,*/
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
                                var employee = this.$route.query.employee;
                                alert("执照信息保存成功!");
                                this.$router.push({path: '/name2',query:{employeeId:this.qualification.employeeId,tab:'name2'}});
                                /*if(this.type =='add'){
                                    this.$router.push({path: '/name2',query:{employeeId:this.qualification.employeeId,tab:'name2'}});

                                }else if(this.type == 'edit'){
                                    var employeeId=this.qualification.employeeId;
                                    self.$http.httpGet('/eim/api/employee/'+employeeId,{
                                    }).then(function(response){
                                        let obj = response.data.data;
                                        if(obj.jobTitle =='飞行人员'){
                                            var id = obj.employeeId;
                                            self.$http.httpGet('/eim/api/pilot/search/byEmployeeId', {
                                                id,
                                            }).then(function (response) {
                                                let pilot = response.data.data;
                                                //alert(obj.supervisionId);
                                                self.$router.push({path: '/editEmployee',query:{employee:obj,pilot:pilot,tab:'name2'}});
                                            }).catch(function (error) {
                                                console.log(error);
                                            });
                                        }else if(obj.jobTitle=='管制人员'){
                                            var id = obj.employeeId;
                                            self.$http.httpGet('/eim/api/controlEmpl/search/byEmployeeId', {
                                                id,
                                            }).then(function (response) {
                                                let control = response.data.data;
                                                self.$router.push({path: '/editEmployee',query:{employee:obj,control:control,tab:'name2'}});
                                            }).catch(function (error) {
                                                console.log(error);
                                            });
                                            //self.$router.push({path: '/adduser',query:{employeeId:employeeId,tab:'name2'}});
                                        }else{
                                            self.$router.push({path: '/editEmployee',query:{employee:obj,tab:'name2'}});
                                        }
                                    })
                                }*/
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                    else {
                        this.$Message.error('表单验证失败!');
                        return;
                    }
                })


            },
            submitCheckForm() {
                let self = this;
                let url = "/eim/api";
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
                        else {
                            self.licenseChecks.splice(self.checklicenseindex, 1, self.temp)
                            self.licenseChecks[self.checklicenseindex] = result.data;
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            tosshangyiye() {
                var employeeId=this.qualification.employeeId;
                /*var employee = this.$route.query.employee;
                var pilot= this.$route.query.pilot;*/
                let self = this;
                if(this.type == 'add'){
                    alert("xxx");
                    self.$router.push({path: '/name2',query:{employeeId:employeeId,/*employee:employee,pilot:pilot,*/tab:'name2'}});
                }else if(this.type == 'edit'){
                    self.$http.httpGet('/eim/api/employee/'+employeeId,{
                    }).then(function(response){
                        let obj = response.data.data;
                        if(obj.jobTitle =='飞行人员'){
                            var id = obj.employeeId;
                            self.$http.httpGet('/eim/api/pilot/search/byEmployeeId', {
                                id,
                            }).then(function (response) {
                                let pilot = response.data.data;
                                //alert(obj.supervisionId);
                                self.$router.push({path: '/editEmployee',query:{employee:obj,pilot:pilot,tab:'name2'}});
                            }).catch(function (error) {
                                console.log(error);
                            });
                            // self.$router.push({path: '/adduser',query:{employeeId:employeeId,tab:'name2'}});
                        }else if(obj.jobTitle=='管制人员'){
                            var id = obj.employeeId;
                            self.$http.httpGet('/eim/api/controlEmpl/search/byEmployeeId', {
                                id,
                            }).then(function (response) {
                                let control = response.data.data;
                                self.$router.push({path: '/editEmployee',query:{employee:obj,control:control,tab:'name2'}});
                            }).catch(function (error) {
                                console.log(error);
                            });
                            //self.$router.push({path: '/adduser',query:{employeeId:employeeId,tab:'name2'}});
                        }else{
                            self.$router.push({path: '/editEmployee',query:{employee:obj,tab:'name2'}});
                        }
                    })



                }


                /*self.$http.httpGet('/eim/api/employee/'+employeeId,{
                }).then(function(response){
                    let obj = response.data.data;
                    if(obj.jobTitle =='飞行人员'){
                        self.$router.push({path: '/adduser',query:{employeeId:employeeId,tab:'name2'}});
                    }else if(obj.jobTitle=='管制人员'){
                        self.$router.push({path: '/adduser',query:{employeeId:employeeId,tab:'name2'}});
                    }else{
                        self.$router.push({path: '/name2',query:{employeeId:employeeId,tab:'name2'}});
                    }
                })*/

                // history.go(-1);
                //this.$router.push({path: '/pilot_info', query: {qualificationisshow: true}});
                //this.$router.push({path: '/editEmployee'});
            },
            init(val1, val2) {
                let self = this;
                let url = "/eim/api";

                let qulificationUrl = url + "/qulification/search/license";

                    if (val2 == "航空器部件维修执照") {
                        self.AiPartsRepairshow = true;
                        self.checkrecordshow = false;
                        self.$http.httpGet('/eim/api/qulification/' + val1, {}).then((res) => {
                            self.qualification = res.data;
                            if (self.qualification.expiredate == '2099-09-09' || self.qualification.expiredate == '2099-09-06' || self.qualification.expiredate === null) {
                                self.radio = 'yes';
                            } else {
                                self.radio = 'no';
                            }
                            if (res.data.filePath != '' & res.data.filePath != null) {
                                self.uploadList = res.data.filePath.split(",");
                            }
                            self.$refs.addaprl.initinfo(val1);
                            return;
                        }).catch(function (error) {
                            console.log(error);
                            return;
                        });
                    } else if (val2 == "航空器维修执照") {
                        self.aircraftRepairShow = true;
                        self.checkrecordshow = false;
                        self.$http.httpGet('/eim/api/qulification/' + val1, {}).then((res) => {
                            // alert('xxxxxxx');
                            self.qualification = res.data;
                            if (self.qualification.expiredate == '2099-09-09' || self.qualification.expiredate == '2099-09-06' || self.qualification.expiredate === null) {
                                self.radio = 'yes';
                            } else {
                                self.radio = 'no';
                            }
                            if (res.data.filePath != '' & res.data.filePath != null) {
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
                        self.$http.httpGet(qulificationUrl, {id: val1, licensetype: val2}).then((res) => {
                            let result = res.data;
                            self.qualification = result.data.personQulificationInfo;
                            if (self.qualification.expiredate == '2099-09-09' || self.qualification.expiredate == '2099-09-06' || self.qualification.expiredate === null) {
                                self.radio = 'yes';
                            } else {
                                self.radio = 'no';
                            }
                            if (result.data.personQulificationInfo.filePath != '' & result.data.personQulificationInfo.filePath != null) {
                                self.uploadList = result.data.personQulificationInfo.filePath.split(",");
                            }

                            if (val2 == "气象执照") {
                                self.weatherlicenseshow = true;
                                self.$refs.abc.weatherlicense = result.data.weatherLicense;
                                self.$refs.abc.businesslist = result.data.weatherLicense.licensearea.split(",");
                                self.licenseChecks = result.data.licenseChecks;
                            }
                            if (val2 == "飞行签派员执照") {
                                self.flightdislicenseshow = true;
                                self.qualification = result.data.personQulificationInfo;
                                self.weatherlicense = result.data.weatherLicense;
                                self.$refs.flightdis.flightdispatcher = result.data.flightDispatcherLicense;
                                self.licenseChecks = result.data.licenseChecks;
                            }
                            if (val2 == "情报执照") {
                                self.intelligencelicenseshow = true;
                                self.$refs.intelligeninfo.intelligencelicense = result.data.intelligenceLicense;
                                self.$refs.intelligeninfo.businesslist = result.data.intelligenceLicense.licenseArea.split(",");
                                self.licenseChecks = result.data.licenseChecks;
                            }
                            if(val2=="管制执照"){
                                self.licenseChecks = result.data.licenseChecks;
                            }
                        }).catch(function (error) {
                            console.log(error);
                            return;
                        });
                    }

            }

        },
        watch: {
            'uploadList': function (newval) {
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
    .submit-btn-user{
        color: #33a6fc;
        background-color: #fff;
        font-size: 14px;
        display: inline-block;
        width: 80px;
        margin-right: 10px;
        height: 32px;
        line-height: 30px;
        border: 1px solid #2D8cF0;
        border-radius: 4px;
        text-align: center;
    }

    .stepsimg > img {
        width: 25%;
        float: left;
    }
</style>