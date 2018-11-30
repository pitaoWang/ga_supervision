<template>
    <div class="content" style="margin-left:15px;">
        <div class="contet" style="width: 992px;">
            <div class="steps">
                <div class="stepsimg" style="width: 100%">
                    <img v-bind:src="img1" style="width: 16%" v-on:click="chiladshow1()">
                    <img v-bind:src="img2" style="width: 16%" v-on:click="chiladshow2()">
                    <img v-bind:src="img3" style="width: 16%" v-on:click="chiladshow3()">
                    <img v-bind:src="img4" style="width: 16%" v-on:click="chiladshow4()">
                    <img v-bind:src="img5" style="width: 16%" v-on:click="chiladshow5()">
                    <img v-bind:src="img6" style="width: 16%" v-on:click="chiladshow6()">
                </div>
                <div class="stepschild">
                    <span ref="jichu" v-bind:style="styleObject1" style=" color: rgb(21, 184, 252);margin-left: 45px;font-size: 14px;">{{baseinfo}}</span>
                    <span ref="quali" v-bind:style="styleObject" style="margin-left:82px;font-size: 14px ;">{{zhizhaoinfo}}</span>
                    <span ref="tjian" style="margin-left: 85px;font-size: 14px;">{{tijianinfo}}</span>
                    <span ref="kshi" style="margin-left: 65px;font-size: 14px;">{{kshiinfo}}</span>
                    <span ref="pxun" style="margin-left: 65px;font-size: 14px;">{{peixuninfo}}</span>
                    <span ref="zshu" style="margin-left: 85px;font-size: 14px;">{{zhshuinfo}}</span>
                </div>

                <hr style="width: 100%;height:3px;background-color: aliceblue;margin-top: 50px">
            </div>
            <div title="填写资质信息" v-if="qualificationisshow"
                 style="padding-left:20px;padding-right:30px;margin-top: 50px">
                <licenselist :employeeId="employeeId" ></licenselist>
            </div>

            <div title="填写基础资料" v-show="jichuxinxi" style="padding-left:20px;padding-right:30px;margin-top: 50px">
                <Form :label-width="80":model="employee" style="margin:-15px 10px;" ref="formVali">
                    <div style="width:75%;display:inline-table;margin-left:15px;">
                        <div class="bottom5" style="border:1px solid #919191;height:100%;">
                            <h2>基本信息</h2>
                            <Row>
                                <Col :md="12">
                                <FormItem label="姓名" class="bottom5">
                                    <Input v-model="employee.userName" placeholder="请输入" readonly></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="性别" class="bottom5">
                                    <Select v-model="employee.gender" placeholder="请选择" readonly="true">
                                        <Option value="男">男</Option>
                                        <Option value="女">女</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="部门" class="bottom5">
                                    <Input v-model="deptname" placeholder="请输入" readonly></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="岗位" class="bottom5">
                                    <Input v-model="employee.jobTitle" placeholder="请输入" readonly></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="工号" class="bottom5">
                                    <Input v-model="employee.workId" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                </Col>
                                <Col :md="12">
                                <FormItem label="出生年月" prop="birthday"  class="bottom5" :rules="  { required: true, type: 'date', message: '请选择日期', trigger: 'change' }" >
                                    <DatePicker type="date" placeholder="选择日期" :editable="false" v-model="employee.birthday"></DatePicker>
                                </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col :md="12">
                                <FormItem label="入职时间" class="bottom5" prop="joinDate"  :rules="  { required: true, type: 'date', message: '请选择日期', trigger: 'change' }"  >
                                    <DatePicker type="date" placeholder="选择日期" v-model="employee.joinDate"></DatePicker>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="国籍" class="bottom5" prop="nationality"  :rules="{required: true, message: '国籍不能为空', trigger: 'blur'}" >
                                    <Input v-model="employee.nationality" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="身份证号" class="bottom5" prop="cardId"  :rules="{required: true, message: '身份证号不能为空', trigger: 'blur'}">
                                    <Input v-model="employee.cardId" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="教育程度" class="bottom5" prop="education" :rules="{required: true, message: '请选择教育程度', trigger: 'change'}" >
                                    <Select v-model="employee.education" placeholder="请选择">
                                        <Option value="1">博士</Option>
                                        <Option value="2">研究生</Option>
                                        <Option value="3">本科</Option>
                                        <Option value="4">大专</Option>
                                        <Option value="5">大专以下</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="护照号码" class="bottom5">
                                    <Input v-model="employee.passPort" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="政治面貌" class="bottom5" prop="politicalStatus" :rules="{required: true, message: '请选择政治面貌', trigger: 'change'}" >
                                    <Select v-model="employee.politicalStatus" placeholder="请选择">
                                        <Option value="1">党员</Option>
                                        <Option value="2">团员</Option>
                                        <Option value="3">群众</Option>
                                        <Option value="4">其他党派</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="局方委任代表" class="bottom5">
                                    <Select v-model="employee.isagent" placeholder="请选择" readonly="true">
                                        <Option value="是">是</Option>
                                        <Option value="否">否</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="微信号" class="bottom5">
                                    <Input v-model="employee.wechatNo" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                        </div>

                        <div class="bottom5" style="border:1px solid #919191;height:250px;" v-if="employee.jobtitle==='飞行人员'||employee.jobtitle==='飞行学员'">
                            <h2>扩展信息</h2>
                            <Row>
                                <Col :md="12">
                                <FormItem label="可飞机型" class="bottom5">
                                    <Input v-model="pilot.aircraftType" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="所属大队" class="bottom5">
                                    <Input v-model="pilot.flightTeam" placeholder="请输入"></Input>
                                </FormItem>

                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="员工状态" class="bottom5">
                                    <Select v-model="employee.status" placeholder="请选择">
                                        <Option :value="0">全职</Option>
                                        <Option :value="1">兼职</Option>
                                        <Option :value="2">离职</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="汉语水平" class="bottom5">
                                    <Select v-model="pilot.chineselevel" placeholder="请选择" >
                                        <Option value="汉语语言4级">汉语语言4级</Option>
                                        <Option value="汉语语言5级">汉语语言5级</Option>
                                        <Option value="汉语语言6级">汉语语言6级</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="英语水平" class="bottom5">
                                    <Select v-model="pilot.englishLevel" placeholder="请选择" >
                                        <Option value="ICAO英语4级">ICAO英语4级</Option>
                                        <Option value="ICAO英语5级">ICAO英语5级</Option>
                                        <Option value="ICAO英语6级">ICAO英语6级</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="通过时间" class="bottom5">
                                    <DatePicker type="date" placeholder="选择日期"
                                                v-model="pilot.englisPassDate"></DatePicker>
                                </FormItem>
                                </Col>
                            </Row>
                        </div>

                        <div class="bottom5" style="border:1px solid #919191;height:100%;">
                            <h2>紧急联系人信息</h2>
                            <Row>
                                <Col :md="12">
                                <FormItem label="姓名" class="bottom5">
                                    <Input v-model="employee.homeName" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="移动电话" class="bottom5">
                                    <Input v-model="employee.homeMobile" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="固定电话" class="bottom5">
                                    <Input v-model="employee.homeTel" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="邮编" class="bottom5">
                                    <Input v-model="employee.homePostcode" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="24">
                                <FormItem label="家庭地址" class="bottom5">
                                    <Input v-model="employee.homeAddress" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                        </div>

                        <div class="bottom5" style="border:1px solid #919191;height:100%;">
                            <h2>单位主管联系人信息</h2>
                            <Row>
                                <Col :md="12">
                                <FormItem label="姓名" class="bottom5">
                                    <Input v-model="employee.businessName" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="移动电话" class="bottom5">
                                    <Input v-model="employee.businessMobile" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="工作电话" class="bottom5">
                                    <Input v-model="employee.businessTel" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="邮编 " class="bottom5">
                                    <Input v-model="employee.businessPostcode" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="24">
                                <FormItem label="单位地址" class="bottom5">
                                    <Input v-model="employee.businessAddress" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                            </Row>
                        </div>

                        <div class="bottom5" style="margin-top:20px;height:40px;">
                            <div class="gacompany-div"
                                 style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px;margin-left: 140px;">
                                <a data-v-942377fa="" href="javascript:;" @click="submitEmployeeInfo(employeeId)"
                                   class="gacompany-btn">保存</a>
                                <a data-v-942377fa="" href="javascript:;" @click="resetting"
                                   class="gacompany-btn">重置</a>
                            </div>
                        </div>
                    </div>
                    <FormItem style="display: none">
                        <Input    v-model="employee.employeeId" ></Input>
                    </FormItem>
                    <div style="width:20%;display:inline-table;margin-left:10px;padding:10px 0;">
                        <div class="bottom5" style="border:1px solid #919191;">
                            <h2>用户头像</h2>
                            <div class="image-list">
                                <Upload name="file"
                                        :on-success="handleSuccess"
                                        :before-upload="handleBeforeUpload"
                                        action="http://fs.glinkus.com:18081/upload">
                                    <div v-if="employee.photo"
                                         style="width: 100px; height: 140px;   margin-left: 29px;">
                                        <img :src="employee.photo" style="width: 100px">
                                    </div>
                                    <div v-else style="width: 100px; height: 140px;   margin-left: 29px;">
                                        <img src="../../../../static/images/默认人像.png" style="width: 100px">
                                    </div>
                                </Upload>
                            </div>
                            <h4 style="font-size: 0.7rem;text-align: left; color: lightslategray;margin-left: 10px">图片小于2M尺寸小于640*480</h4>
                        </div>
                        <div class="bottom5" style="border:1px solid #919191;margin-top: 35px;">
                            <h2>身份证正面</h2>
                            <div class="image-list">
                                <Upload name="file" :with-credentials="true"
                                        :on-success="handleSuccess1"
                                        :before-upload="handleBeforeUpload1"
                                        action="http://fs.glinkus.com:18081/upload">
                                    <div v-if="scanCardlist.firturl===''" style="width: 148px;height: 91px;">
                                        <img src="../../../assets/defalutupload.png" style="width: 148px">

                                    </div>
                                    <div v-else>
                                        <img :src="scanCardlist.firturl" style="width: 148px;height: 91px;">
                                    </div>
                                </Upload>
                            </div>
                            <h4 style="font-size: 0.7rem;text-align: left; color: lightslategray;margin-left: 10px">图片小于2M尺寸小于640*480</h4>
                        </div>
                        <div class="bottom5" style="border:1px solid #919191;margin-top: 35px;">
                            <h2>身份证反面</h2>
                            <div class="image-list">
                                <Upload name="file" :with-credentials="true"
                                        :on-success="handleSuccess2"
                                        :before-upload="handleBeforeUpload2"
                                        action="http://fs.glinkus.com:18081/upload">
                                    <div v-if="scanCardlist.lasturl===''" style="width: 148px;height: 91px;">
                                        <img src="../../../assets/defalutupload.png" style="width: 148px">
                                    </div>
                                    <div v-else>
                                        <img :src="scanCardlist.lasturl" style="width: 148px;height: 91px;">
                                    </div>
                                </Upload>
                            </div>
                            <h4 style="font-size: 0.7rem;text-align: left; color: lightslategray;margin-left: 10px">图片小于2M尺寸小于640*480</h4>
                        </div>
                        <div class="bottom5" style="border:1px solid #919191;margin-top: 35px;">
                            <h2>个人签名</h2>
                            <div class="image-list">
                                <Upload name="file" :with-credentials="true"
                                        :before-upload="handleBeforeUpload3"
                                        :on-success="handleSuccess3" :on-error="handleError"
                                        action="http://fs.glinkus.com:18081/upload">
                                    <div v-if="employee.scanSignature" style="width: 148px;height: 91px;">
                                        <img :src="employee.scanSignature" style="width: 148px">
                                    </div>
                                    <div v-else>
                                        <img src="../../../assets/defalutupload.png">
                                    </div>
                                </Upload>
                            </div>
                            <h4 style="font-size: 0.7rem;text-align: left; color: lightslategray;margin-left: 10px">图片小于2M尺寸小于640*480</h4>
                        </div>
                    </div>
                </Form>
            </div>
            <div title="填写体检信息" v-if="physicalrecordlist"
                 style="padding-left:20px;padding-right:30px;margin-top: 50px">
                <physicalrecordlist :employeeId="employeeId"></physicalrecordlist>
            </div>

            <div title="填写考试信息" v-if="personExamRecord" style="padding-left:20px;padding-right:30px;margin-top: 50px">
                <personExamRecord :employeeId="employeeId"></personExamRecord>

            </div>

            <div title="填写培训信息" v-if="trainInfo" style="padding-left:20px;padding-right:30px;margin-top: 50px">
                <trainInfo :employeeId="employeeId"></trainInfo>

            </div>

            <div title="填写培训证书信息" v-if="trainingcertificate" style="padding-left:20px;padding-right:30px;margin-top: 50px">
                <trainingcertificate :employeeId="employeeId"></trainingcertificate>
            </div>

        </div>

    </div>
</template>

<script>
    import licenselist from '../../pages/employee/licenseinlist.vue';
    import physicalrecordlist from '../../pages/employee/physicalrecordlist.vue';
    import personExamRecord from '../../pages/employee/personexamrecord.vue';
    import trainingcertificate from '../../pages/employee/trainingcertificate.vue';
    import trainInfo from '../../pages/employee/traininfo.vue';

    export default {
        components: {
            licenselist,
            physicalrecordlist,
            personExamRecord,
            trainingcertificate,
            trainInfo
        },
        data (){
            return {
                styleObject:{
                    color:''
                },
                styleObject1:{
                    color:''
                },
                pro:'',
                employeeId:'',
                employee: {
                },
                pilot: {},
                scanCardaray: [],
                scanCardlist: {
                    firturl: '',
                    lasturl: ''
                },
                img1: '',
                img2: '',
                img3: '',
                img4: '',
                img5: '',
                img6: '',
                img7: '',
                qualificationisshow: false,
                jichuxinxi: false,
                physicalrecordlist: false,
                personExamRecord: false,
                trainingcertificate:false,
                trainInfo:false,
                applyauthor:false,
                deptname:'',
                baseinfo:'',
                zhizhaoinfo:'',
                tijianinfo:'',
                kshiinfo:'',
                peixuninfo:'',
                zhshuinfo:'',
                applyinfo:'',
                ststus:''

            }
        },
        created() {
            let self = this;
            let msg=self.$route.query.id;
            self.employeeId = msg;
            self.getPilotInfo(msg);
        },
        mounted(){
            let flag;
            if(this.$route.query.qualificationisshow){
                this.jichuxinxi = false;
                this.qualificationisshow = true;
                this.physicalrecordlist = false;
                this.personExamRecord = false;
                this.trainInfo = false;
                this.trainingcertificate=false;
                this.img1 = require('../../../assets/灰1.png');
                this.img2 = require('../../../assets/2.png');
                this.img3 = require('../../../assets/灰3.png');
                this.img4 = require('../../../assets/灰4.png');
                this.img5 = require('../../../assets/灰5.png');
                this.img6 = require('../../../assets/灰6.png');
                this.img7 = require('../../../assets/灰7.png');
                this.styleObject.color='rgb(21, 184, 252)';
                this.styleObject1.color='';
                this.baseinfo = '基础资料';
                this.zhizhaoinfo = '执照信息';
                this.tijianinfo = '体检信息';
                this.kshiinfo = '考试/检查信息x';
                this.peixuninfo = '培训信息';
                this.zhshuinfo = '证书信息';
                flag=0;
            }
            else{
                flag=1;
            }
            this.gatdata(flag);
        },
        methods: {
            handleBeforeUpload () {
                this.employee.photo=require( '../../../assets/imgupload.gif');
            },
            handleBeforeUpload1 () {
                this.scanCardlist.firturl=require( '../../../assets/imgupload.gif');
            },
            handleBeforeUpload2 () {
                this.scanCardlist.lasturl=require( '../../../assets/imgupload.gif');
            },
            handleBeforeUpload3 () {
                this.employee.scanSignature=require( '../../../assets/imgupload.gif');
            },
            resetting(){
                this.getPilotInfo(this.employeeId);
            },
            chiladshow2(){
                    this.jichuxinxi = false;
                    this.qualificationisshow = true;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = 'rgb(21, 184, 252)';
                    this.$refs.jichu.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息x';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
            },
            chiladshow1(){
                    this.jichuxinxi = true;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.img1 = require('../../../assets/1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.$refs.jichu.style.color = 'rgb(21, 184, 252)';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息x';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
            },
            chiladshow3(){
                    this.jichuxinxi = false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = true;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = 'rgb(21, 184, 252)';
                    this.$refs.jichu.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息x';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
            },
            chiladshow4(){
                    this.jichuxinxi = false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = true;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.jichu.style.color = '';
                    this.$refs.kshi.style.color = 'rgb(21, 184, 252)';
                    this.$refs.pxun.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息x';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
            },
            chiladshow5(){
                    this.jichuxinxi = false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = true;
                    this.trainingcertificate = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.jichu.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.pxun.style.color = 'rgb(21, 184, 252)';
                    this.$refs.zshu.style.color = '';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息x';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
            },
            chiladshow6(){
                    this.jichuxinxi = false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = true;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.jichu.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.zshu.style.color = 'rgb(21, 184, 252)';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息x';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
            },
            gatdata(val){
                if(val==1) {
                    this.chiladshow1();
                }
                else{
                    return
                }
            },


            getPilotInfo(id){
                let self = this;
                let url = "/app/api";
                let userId;
                let infoUrl = url + "/pilot/search/" +id;

                self.$http.httpGet(infoUrl, {}).then((res) => {
                    let result = res.data
                    if (result.resultCode == 200) {
                        if (result.data.employee) {
                            self.employee = result.data.employee;
                            userId=self.employee.userId;
                            this.$http.httpGet('/app/api/sysuser/search/byuserid',{
                                userid:userId
                            }).then((res) => {
                                let orgId=res.data.data.orgId;
                                this.$http.httpGet('/app/api/sysorg/search/byId', {
                                    orgId:orgId
                                }).then((res) => {
                                    self.deptname=res.data.data.orgName;
                                }).catch(function (error) {
                                    console.log(error);
                                });

                            }).catch(function (error) {
                                console.log(error);
                            });


                            self.employeeId = self.employee.employeeId;
                            if (result.data.employee.scanCard) {
                                self.scanCardaray = result.data.employee.scanCard.split(",");
                                self.scanCardlist.firturl = self.scanCardaray[0];
                                self.scanCardlist.lasturl = self.scanCardaray[1];
                            }
                        }
                        //飞行员
                        if (result.data.pilot) {
                            self.pilot = result.data.pilot;
                        }
                    }
                });





            },
            submitEmployeeInfo(id){
                let self = this;
                let url = "/app/api";
                self.$refs.formVali.validate((valid) => {
                    if(valid) {
                        self.$profile.getProfile().then((profile) => {

                            let employeeUrl = url + "/employee/save";
                            if(profile.buz.employeeId){
                                self.employee.employeeId= profile.buz.employeeId;
                            }
                            if(self.employee.gender==null||self.employee.gender==''){
                                if(profile.user.sex=='0'){
                                    self.employee.gender="男"
                                }
                                else{
                                    self.employee.gender="女"
                                }
                            }
                            if(self.employee.jobtitle==="飞行人员"||self.employee.jobtitle==="飞行学员") {
                                self.employee.deptname = self.deptname;
                                self.employee.scanCard = self.scanCardaray.join(",");
                                self.$http.httpPost(employeeUrl, self.employee).then((res) => {
                                    let result = res.data
                                    if (result.resultCode == 200) {
                                        //更新保存飞行员信息
                                        let employee = result.data;
                                        self.pilot.employeeId = employee.employeeId;
                                        let pilotUrl = url + "/pilot/save";
                                        self.$http.httpPost(pilotUrl, self.pilot).then((res) => {
                                            let result = res.data;
                                            if (result.resultCode == 200) {
                                                self.pilot=result.data;
                                                alert('信息保存成功');
                                                self.$profile.getProfile(true).then((updatedProfile) => {
                                                    console.log("updatedProfile", updatedProfile)
                                                })

                                            }
                                        });
                                    }
                                    else {
                                        self.$Message.info(result.message);
                                    }


                                }).catch(function (error) {
                                    self.$Message.error(error);
                                });
                            }
                            else{
                                self.employee.deptname = self.deptname;
                                self.employee.scanCard = self.scanCardaray.join(",");
                                self.$http.httpPost(employeeUrl, self.employee).then((res) => {
                                    let result = res.data
                                    if (result.resultCode == 200) {
                                        alert('员工信息保存成功');
                                        //更新保存飞行员信息
                                    }
                                    else {
                                        self.$Message.info(result.message);
                                    }


                                }).catch(function (error) {
                                    self.$Message.error(error);
                                });

                            }

                        }, (error) => {
                            self.$Message.error('user profile error = ' + error);
                        });
                    }
                    else{
                        this.$Message.error('表单验证失败!');
                        return;
                    }
                })
            },
            handleSuccess (res, file) {
                if (res.resultCode == 200) {

                    this.employee.photo = res.url;

                }
            },
            handleSuccess1 (res, file) {
                if (res.resultCode == 200) {
                    console.log(this.scanCardlist);

                    this.scanCardlist.firturl = res.url;
                    this.scanCardaray[0] = res.url;

                }
            },
            handleSuccess2 (res, file) {
                if (res.resultCode == 200) {

                    this.scanCardlist.lasturl = res.url;
                    this.scanCardaray[1] = res.url;

                }
            },
            handleSuccess3 (res, file) {
                if (res.resultCode == 200) {
                    this.employee.scanSignature = res.url;
                }
            },
            handleError (res, file) {
                console.log(res)
            }
        },
        watch: {

        }
    }

</script>

<style scoped>
    .bottom5 {
        margin-bottom: 24px;
    }

    .btn-list {
        margin: 10px 10px;
        display: inline-block;
    }

    .image-list {
        margin: 10px 10px;
        display: inline-block;
        width: 150px;
        text-align: center;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
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

    .ivu-steps .ivu-steps-head {
        background: #282828;
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

    .gacompany-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);
        background: linear-gradient(#a3a3a3, #e7e7e7);
    }

    .gacompany-div a:hover {
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