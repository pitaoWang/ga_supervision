<template>

    <div style="width:97%;display:inline-table;margin-left:15px;">
        <img src="/static/images/基础资料图标.png" style="position: absolute;margin-left: 22px;margin-top: 99px;"/>

        <div style="height: 36px;margin-bottom: 3px;padding-left: 55px;padding-top: 5px;  margin-top: 91px;background-color: rgb(46, 130, 220)">
            <div style="font-size: 16px;">修改执照</div>
        </div>
        <Form :label-width="120" :model="qualification" ref="formVali" >
            <div class="bottom5" style="height:180px;border:1px solid rgba(1,107,217,.5);background-color:rgba(193,220,248,.5);">
                <h2>执照基本信息</h2>
                <Row>
                    <Col :md="12">
                    <FormItem label="执照名称" class="bottom5">
                        <Select v-model="qulification.licensetype" placeholder="请选择">
                            <Option value="飞行执照">飞行员执照</Option>
                        </Select>
                    </FormItem>
                    </Col>
                    <Col :md="11" >
                    <FormItem label="执照编号" class="bottom5"  prop="licenseno" :rules="{required: true, message: '执照编号不能为空', trigger: 'blur'}">
                        <Input v-model="qulification.licenseno" placeholder="请输入"></Input>
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
                        <DatePicker v-model="qulification.issuedate" type="date" placeholder="选择颁发时间"></DatePicker>
                    </FormItem>
                    </Col>
                    <Col :md="11" v-if=" radio=='no'">
                    <FormItem label="执照到期时间" class="bottom5" >
                        <DatePicker v-model="qulification.expiredate" type="date" placeholder="选择到期时间"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>
            </div>
            <div  v-if="license!==null">
                <div class="bottom5" style="border:1px solid rgba(1,107,217,.5);background-color:rgba(193,220,248,.5);">
                    <h2>执照信息</h2>

                    <Row>
                        <Col :md="12">
                        <FormItem label="切换执照类型" class="bottom5">
                            <Select v-model="license.licenseclass" placeholder="请选择">
                                <Option value="学生驾驶员" >学生驾驶员</Option>
                                <Option value="运动驾驶员">运动驾驶员</Option>
                                <Option value="私用驾驶员">私用驾驶员</Option>
                                <Option value="商用驾驶员">商用驾驶员</Option>
                                <Option value="多人制机组驾驶员">多人制机组驾驶员</Option>
                                <Option value="航线运输驾驶员">航线运输驾驶员</Option>
                            </Select>
                        </FormItem>
                        </Col>
                    </Row>

            <div  v-if="license.licenseclass=='学生驾驶员'" >


                </Row>
            </div>
                    <div  v-else-if="license.licenseclass=='运动驾驶员'" >
                        <Row>
                            <Col :md="12">
                            <FormItem label="是否是运动教员" class="bottom5" >
                                <Select  v-model="license.issportspilotteacher"  placeholder="请选择">
                                    <Option value="1">是</Option>
                                    <Option value="0">否</Option>
                                </Select>
                            </FormItem>
                            </Col>
                            <Col :md="11">
                            <FormItem label="航空器类别等级" class="bottom5">
                                <Select v-model="license.sportsaircraftclass" placeholder="请选择">
                                    <Option value="初级飞机">初级飞机</Option>
                                    <Option value="自转旋翼机">自转旋翼机</Option>
                                    <Option value="滑翔机">滑翔机</Option>
                                    <Option value="自由气球">自由气球</Option>
                                    <Option value="小型飞艇">小型飞艇</Option>
                                </Select>
                            </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col :md="12">
                            <FormItem label="航空器级别等级" class="bottom5">
                                <Select v-model="license.sportsaircraftgrade" placeholder="请选择">
                                    <Option value="初级飞机-陆地">初级飞机-陆地</Option>
                                    <Option value="初级飞机-水上">初级飞机-水上</Option>
                                </Select>
                            </FormItem>
                            </Col>
                        </Row>

                    </div>
                    <div  v-else-if="license.licenseclass=='多人制机组驾驶员'" >
                        <Row>
                            <Col :md="12">
                            <FormItem label="航空器类别等级" class="bottom5">
                                <Input v-model="license.multiaircraftclass" placeholder="请输入"></Input>
                            </FormItem>
                            </Col>
                            <Col :md="11">
                            <FormItem label="航空器级别等级" class="bottom5">
                                <Input v-model="license.multiaircraftgrade" placeholder="请输入"></Input>
                            </FormItem>
                            </Col>
                        </Row>
                        <Row>

                            <FormItem label="航空器型别等级" class="bottom5">
                                <CheckboxGroup v-model="multiaircrafttype">
                                    <Checkbox label="1">
                                        <span>5700千克以上的航空器</span>
                                    </Checkbox>
                                    <Checkbox label="2">
                                        <span>3180千克以上的航空器</span>
                                    </Checkbox>
                                    <Checkbox label="3">
                                        <span>涡轮喷气动力飞机</span>
                                    </Checkbox>
                                    <Checkbox label="4">
                                        <span>其他航空器</span>
                                    </Checkbox>
                                </CheckboxGroup>
                            </FormItem>
                        </Row>

                    </div>
                    <div  v-else >

                        <Row>
                            <Col :md="12">
                            <FormItem label="是否是飞行教员" class="bottom5" >
                                <Select  v-model="license.ispilotteacher"  placeholder="请选择">
                                    <Option value="1">是</Option>
                                    <Option value="0">否</Option>
                                </Select>
                            </FormItem>
                            </Col>
                        </Row>
                        <div v-if="license.ispilotteacher==1">
                            <Row>
                                <Col :md="12">
                                <FormItem label="教员仪表等级" class="bottom5">
                                    <Select v-model="license.teachermetergrade" placeholder="请选择">
                                        <Option value="无">无</Option>
                                        <Option value="仪表-飞机">仪表-飞机</Option>
                                        <Option value="仪表-直升机">仪表-直升机</Option>
                                        <Option value="仪表-飞艇">仪表-飞艇</Option>
                                        <Option value="仪表-倾转旋翼机">仪表-倾转旋翼机</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="教员基础教员等级" class="bottom5">
                                    <Select v-model="license.teachergrade" placeholder="请选择">
                                        <Option value="单发飞机">单发飞机</Option>
                                        <Option value="多发飞机">多发飞机</Option>
                                        <Option value="直升机">直升机</Option>
                                        <Option value="飞艇">飞艇</Option>
                                        <Option value="倾转旋翼机">倾转旋翼机</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <FormItem label="教员航空器型别" class="bottom5">
                                <CheckboxGroup v-model="teacheraircrafttype">
                                    <Checkbox label="1">
                                        <span>5700千克以上的航空器</span>
                                    </Checkbox>
                                    <Checkbox label="2">
                                        <span>3180千克以上的航空器</span>
                                    </Checkbox>
                                    <Checkbox label="3">
                                        <span>涡轮喷气动力飞机</span>
                                    </Checkbox>
                                    <Checkbox label="4">
                                        <span>其他航空器</span>
                                    </Checkbox>
                                </CheckboxGroup>
                            </FormItem>

                        </div>
                        <div v-if="license.ispilotteacher==0">
                            <Row>
                                <Col :md="12">
                                <FormItem label="仪表等级" class="bottom5" >
                                    <Select v-model="license.metergrade" placeholder="请选择">
                                        <Option value="无">无</Option>
                                        <Option value="仪表-飞机">仪表-飞机</Option>
                                        <Option value="仪表-直升机">仪表-直升机</Option>
                                        <Option value="仪表-飞艇">仪表-飞艇</Option>
                                        <Option value="仪表-倾转旋翼机">仪表-倾转旋翼机</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <FormItem label="驾驶员航空器类别" class="bottom5">
                                <CheckboxGroup v-model="aircraftClass">
                                    <Checkbox label="1">
                                        <span>飞机</span>
                                    </Checkbox>
                                    <Checkbox label="2">
                                        <span>直升机</span>
                                    </Checkbox>
                                    <Checkbox label="3">
                                        <span>飞艇</span>
                                    </Checkbox>
                                    <Checkbox label="4">
                                        <span>倾转旋翼机</span>
                                    </Checkbox>
                                </CheckboxGroup>
                            </FormItem>

                            <FormItem label="驾驶员航空器型别" class="bottom5">
                                <CheckboxGroup v-model="aircraftType">
                                    <Checkbox label="1">
                                        <span>5700千克以上的航空器</span>
                                    </Checkbox>
                                    <Checkbox label="2">
                                        <span>3180千克以上的航空器</span>
                                    </Checkbox>
                                    <Checkbox label="3">
                                        <span>涡轮喷气动力飞机</span>
                                    </Checkbox>
                                    <Checkbox label="4">
                                        <span>其他航空器</span>
                                    </Checkbox>
                                </CheckboxGroup>
                            </FormItem>


                            <FormItem label="驾驶员航空器级别" class="bottom5">
                                <CheckboxGroup v-model="aircraftgrade">
                                    <Checkbox label="1">
                                        <span>单发陆地</span>
                                    </Checkbox>
                                    <Checkbox label="2">
                                        <span>多发陆地</span>
                                    </Checkbox>
                                    <Checkbox label="3">
                                        <span>单发水上</span>
                                    </Checkbox>
                                    <Checkbox label="4">
                                        <span>单发水上</span>
                                    </Checkbox>
                                    <Checkbox label="5">
                                        <span>多发水上</span>
                                    </Checkbox>
                                </CheckboxGroup>
                            </FormItem>
                        </div>
                    </div>
            </div>
            </div>

                <div class="bottom5" style="margin-top:10px;height:180px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <h2 style="float:left;">执照签注记录</h2>
                    <Button type="primary" @click="add_dialog_show=true" icon="plus"
                            style="width:150px;margin-right:10px;" size="small">增  加</Button>
                </span>

                <div class="bottom5" style="margin-top:5px;">
                    <Table border :columns="columns7" :data="licenseChecks"></Table>
                </div>

                <div class="bottom5" style="height:190px;border:1px solid rgba(1,107,217,.5);background-color:rgba(193,220,248,.5);">
                    <h2>执照扫描件</h2>
                    <uploadfile :uploadList=uploadList v-on:listenTochildEvent="getuploadList" ></uploadfile>
                </div>

                <div class="bottom5" style="margin-top:20px;height:40px;">
                    <div style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px; margin-left: 290px;">
                        <a data-v-942377fa="" href="javascript:;" @click="submitLicenseInfo" class="gacompany-btton">保存</a>
                        <a data-v-942377fa="" href="javascript:;" @click="tosshangyiye" class="gacompany-btton">返回</a>

                    </div>
                    <!--<Row>-->
                        <!--<Col :md="10">-->
                        <!--<FormItem style="text-align:center">-->
                            <!--<Button type="primary" shape="circle" :long="true" @click="submitLicenseInfo">提 交</Button>-->
                        <!--</FormItem>-->
                        <!--</Col>-->
                        <!--<Col :md="4">-->
                        <!--<FormItem style="text-align:center">-->
                            <!--<Button type="ghost" shape="circle" width="large">查看历史</Button>-->
                        <!--</FormItem>-->
                        <!--</Col>-->
                    <!--</Row>-->

                </div>

            </div>


        </Form>

        <Modal v-model="add_dialog_show" width="400" title="新增执照签注记录" @on-ok="submitCheckForm"
               @on-cancel="add_dialog_show = false" :styles="{top: '20px'}">
            <Form :label-width="100" v-model="check_form" >
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
        margin-bottom: 15px;
        color: #657180;
    }

</style>

<script>
    import uploadfile from './uploadfile.vue';
    export default {
        components: {
            'uploadfile':uploadfile,
        },
        data (){
            return {
                radio:'yes',
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
                        width: 230,
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
                qulification: {},
                license: {},
                licenseChecks: [{}],
                groundinstructorlicense:{},
                add_dialog_show: false,
                check_form: {},
                uploadList:[],
                flightinstructorlicense:{},
                multiaircrafttype:[],
                teacheraircrafttype:[],
                aircraftClass:[],
                aircraftType:[],
                aircraftgrade:[],
                temp:{
                    id:'',
                    licenseId:'',
                    licenseCheckDate:'',
                    licenseCheckContent:''
                }
            }
        },


        created() {
           let licenseId=this.$route.query.licenseId;

            this.getLicenseInfo(licenseId)
        },

        methods: {
            reseting(){
                this.getLicenseInfo();
            },
            updateCheck (index) {
                this.temp.id=this.licenseChecks[index].id;
                this.temp.licenseId=this.licenseChecks[index].licenseId;
                this.temp.licenseCheckDate=this.licenseChecks[index].licenseCheckDate;
                this.temp.licenseCheckContent=this.licenseChecks[index].licenseCheckContent;
                this.check_form = this.temp;
                this.checklicenseindex = index;
                this.add_dialog_show = true;
            },
            removeCheck (index) {
                let self = this;
                let role = "pilot";
                let url = "/eim/api";
                if (role == "pilot") {
                    let checkUrl = url + "/license/check/delete/"+this.licenseChecks[index].id;
                    console.log(checkUrl)

                    self.$http.httpDelete(checkUrl, {}).then((res) => {
                        let result = res.data
                        if (result.resultCode == 200) {
                            self.licenseChecks.splice(index, 1);
                            self.$Message.info("删除成功，id="+result.data);
                        }
                    });
                }

            },
            getLicenseInfo(str1) {
                let self = this;
                let role = "pilot";
                let url = "/eim/api"
                if (role == "pilot") {
                    self.$profile.getProfile().then((profile) => {
//                    let companyid = profile.buz.companyId;
                        if (profile.buz && profile.buz.employeeId) {
                            self.employeeId = profile.buz.employeeId;
                            self.$http.httpGet('/eim/api/qulification/search/getinfoby', {
                                licenseId:str1,
                            }).then(function (res) {
                                let result = res.data;
                                if (result.resultCode == 200) {

                                    if (result.data.personQulificationInfo == null) {
                                        console.log("error")
                                    }
                                    else {
                                        self.qulification = result.data.personQulificationInfo;
                                        if(self.qulification.expiredate=='2099-09-09'||self.qulification.expiredate=='2099-09-06'||self.qulification.expiredate===null) {
                                            self.radio = 'yes';
                                        }else{
                                            self.radio = 'no';
                                        }
                                        if (result.data.personQulificationInfo.filePath !=""&result.data.personQulificationInfo.filePath !=null) {
                                            self.uploadList = result.data.personQulificationInfo.filePath.split(",");
                                        }
                                    }

                                        self.license = result.data.pilotLicense;
                                    if(self.license.ispilotteacher==0){
                                        self.license.ispilotteacher="0"
                                    }
                                    else{
                                        self.license.ispilotteacher="1";
                                    }
                                    if(self.license.issportspilotteacher==0){
                                        self.license.issportspilotteacher="0"
                                    }
                                    else{
                                        self.license.issportspilotteacher="1";
                                    }
                                    if(self.license.multiaircrafttype!=null){
                                        self.multiaircrafttype=self.license.multiaircrafttype.split(",");
                                    }
                                    if(self.license.teacheraircrafttype!=null){
                                        self.teacheraircrafttype=self.license.teacheraircrafttype.split(",");
                                    }
                                    if(self.license.aircraftClass!=null){
                                        self.aircraftClass=self.license.aircraftClass.split(",");
                                    }
                                    if(self.license.aircraftType!=null){
                                        self.aircraftType=self.license.aircraftType.split(",");
                                    }
                                    if(self.license.aircraftgrade!=null){
                                        self.aircraftgrade=self.license.aircraftgrade.split(",");
                                    }







                                    if (result.data.licenseChecks == null || result.data.licenseChecks.lenght == 0) {
                                        console.log("error")
                                    }
                                    else {
                                        self.licenseChecks = result.data.licenseChecks;
                                    }


                                        self.groundinstructorlicense = result.data.groundinstructorlicense;



                                        self.flightinstructorlicense = result.data.flightinstructorlicense;


                                }
                            });
                        } else {
                            self.$Message.error('not employee,error = ' + error);
                        }

                    }, (error) => {
                        self.$Message.error('user profile error = ' + error);
                    });
                }

            },
            getuploadList(msg){

            },
            submitLicenseInfo(){
                let self = this;
                let url = "/eim/api";
                let role = "pilot";
                if(self.radio=='yes'){
                    self.qulification.expiredate=null;
                }
                if (role == "pilot") {
                    self.license.multiaircrafttype=self.multiaircrafttype.join(",");
                    self.license.teacheraircrafttype=self.teacheraircrafttype.join(",");
                    self.license.aircraftClass=self.aircraftClass.join(",");
                    self.license.aircraftType=self.aircraftType.join(",");
                    self.license.aircraftgrade=self.aircraftgrade.join(",");
                    let licenseUrl = url + "/license/save";
                    console.log(self.license);
                    self.$http.httpPost(licenseUrl, self.license).then((res) => {
                        let result = res.data
                        if (result.resultCode == 200) {
                            //更新保存飞行员信息
                            let license = result.data;
                            this.qulification.licenseId = license.licenseId;
                            this.qulification.filePath=this.uploadList.join(",");
                            let qulificationUrl = url + "/qulification/save";
                            self.$http.httpPost(qulificationUrl, self.qulification).then((res) => {
                                let result = res.data;
                                if (result.resultCode == 200) {
//                                    self.$Message.info('飞行执照信息保存成功');
                                    alert("执照信息保存成功！")
                                    this.$router.push({path: '/pilot_info', query: {qualificationisshow: true}});
                                }
                            });
                        }
                        else {
                            self.$Message.info(result.message);
                        }

                    }).catch(function (error) {
                        console.log(error);
                    });
                }
            },
            submitCheckForm(){
                let self = this;
                let url = "/eim/api";
                let checkUrl = url + "/license/check/save";
                let is_update = false;
                if(self.check_form.id)
                {
                    is_update = true;
                }
                    self.check_form.licenseId = self.qulification.id;


                self.$http.httpPost(checkUrl, self.check_form).then(function(res) {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.check_form = {};
                        if(!is_update)
                        {
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
            tosshangyiye(){
                var employee = this.$route.query.employee;

                this.$router.push({path: '/editEmployee',  query: {employee:employee,tab:'name2'}});
            }
        },
        watch: {
//            pilotteacher:function (newval) {
//                if(newval=="是"){
//                    self.license.ispilotteacher=1;
//                }
//                else {
//                    self.license.ispilotteacher =0;
//                }
//                console.log(newval);
//
//            }
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