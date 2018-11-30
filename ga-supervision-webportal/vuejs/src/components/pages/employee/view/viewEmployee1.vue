<style>
    .fade.in {
        opacity: 1;
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
    .flybase-submit-user a:hover{
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
    .fade {
        opacity: 0;
        -webkit-transition: opacity .15s linear;
        -o-transition: opacity .15s linear;
        transition: opacity .15s linear;
    }
    .modal.in .modal-dialog {
        -webkit-transform: translate(0,0);
        -ms-transform: translate(0,0);
        -o-transform: translate(0,0);
        transform: translate(0,0);
    }
    .modal.fade .modal-dialog {
        -webkit-transition: -webkit-transform .3s ease-out;
        -o-transition: -o-transform .3s ease-out;
        transition: transform .3s ease-out;
        -webkit-transform: translate(0,-25%);
        -ms-transform: translate(0,-25%);
        -o-transform: translate(0,-25%);
        transform: translate(0,-25%);
    }
    .modal-content {
        -webkit-box-shadow: 0 5px 15px rgba(0,0,0,.5);
        box-shadow: 0 5px 15px rgba(0,0,0,.5);
    }
    .modal-content {
        position: relative;
        background-color: #fff;
        -webkit-background-clip: padding-box;
        background-clip: padding-box;
        border: 1px solid #999;
        border: 1px solid rgba(0,0,0,.2);
        border-radius: 6px;
        outline: 0;
        -webkit-box-shadow: 0 3px 9px rgba(0,0,0,.5);
        box-shadow: 0 3px 9px rgba(0,0,0,.5);
        margin: auto;
    }
    .modal-backdrop.in {
        filter: alpha(opacity=50);
        opacity: .5;
    }
    .modal-backdrop.fade {
        filter: alpha(opacity=0);
        opacity: 0;
    }

    .fade.in {
        opacity: 1;
    }

    .modal-backdrop {
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: 1040;
        background-color: rgba(0, 0, 0, 0.37);
    }

    .fade {
        opacity: 0;
        -webkit-transition: opacity .15s linear;
        -o-transition: opacity .15s linear;
        transition: opacity .15s linear;
    }
    .bgclass{
        background:#353535;
        overflow: hidden;
        border: solid 1px #ececec;
        width: 190px;
        height: 30px;
        text-align: center;
        color: white;
        margin-bottom: 5px;
    }
    .gbclass{
        background:#afafaf ;
        overflow: hidden;
        border: solid 1px #ececec;
        width: 190px;
        height: 30px;
        text-align: center;
        color: white;
        margin-bottom: 5px;
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
        color: black;
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
<template>
    <div style="width: 1280px; height:auto; margin-top: 30px; padding-top: 40px; background:white">
    <Tabs v-model="value" style="border: 1px solid #f0f0f0; margin-left: 40px; margin-right: 40px ">
        <TabPane label="员工基本信息" name="name1">

            <div style="width: 1280px;  margin-top: 10px; display: inline-block;background: white">
                <Form ref="employeeval" :model="employee" :label-width="150" :rules="employeeValidate">
                    <div style="width:880px; float:left;">
                    <Row>
                        <Col span="10">
                            <FormItem label="姓名" style="width: 350px;" prop="userName">
                                <Input v-model="employee.userName" readonly="true" ></Input>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="性别" style="width: 350px;" prop="gender">
                                <!--<Select v-model="employee.gender" placeholder="请选择用户性别">
                                    <Option v-for="te in sex" :value="te.sexName">{{te.sexId}}</Option>
                                </Select>-->
                                <Input v-model="employee.gender" readonly="true" ></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="19">
                            <FormItem label="人员类型" style="width: 700px;float: left;" prop="">
                                <Input v-model="employee.jobTitle"  readonly="true"/>
                                <!--<Select v-model="employee.jobTitle" multiple @on-change="selectop" readonly="true"  placeholder="请选择用户类型">
                                    <Option v-for="te in Technicalpost" :value="te.Technicalname">
                                        {{te.Technicalid}}
                                    </Option>
                                </Select>-->

                            </FormItem>
                        </Col>
                        <!--<Col span="9">
                            <FormItem label="所属企业" style="width: 350px;" prop="">
                                <Input v-model="employee.companyName" readonly="true"></Input>
                            </FormItem>
                        </Col>-->
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem label="国籍" style="width: 350px;" prop="nationality">
                                <Input v-model="employee.nationality" readonly="true" ></Input>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="民族" style="width: 350px;float: left;" prop="ethnicity">
                                <Input v-model="employee.ethnicity" readonly="true"></Input>
                            </FormItem>
                        </Col>
                    </Row>

                    <Row>
                        <Col span="10">
                            <FormItem label="教育程度" style="width: 350px;" prop="education">
                                <!--<Select v-model="employee.education" placeholder="请选择" >
                                    <Option value="1">博士</Option>
                                    <Option value="2">研究生</Option>
                                    <Option value="3">本科</Option>
                                    <Option value="4">大专</Option>
                                    <Option value="5">大专以下</Option>
                                </Select>-->
                                <Input v-model="education" readonly="true"></Input>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="毕业院校" style="width: 350px;" prop="school">
                                <Input v-model="employee.school" readonly="true"></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <!--<Col span="10">
                            <FormItem label="身份证号码" style="width: 350px;" prop="cardId">
                                <Input v-model="employee.cardId" readonly="true"></Input>
                            </FormItem>
                        </Col>-->
                        <Col span="10">
                            <FormItem label="所属企业" style="width: 350px;" prop="">
                                <Input v-model="employee.companyName" readonly="true"></Input>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="职务" style="width: 350px;" prop="techpos">
                                <!--<Select v-model="employee.techpos"  placeholder="请选择" >
                                    <Option value="主任教员">主任教员</Option>
                                    <Option value="地面主任教员">地面主任教员</Option>
                                    <Option value="助理主任教员">助理主任教员</Option>
                                    <Option value="（企业）检查员">（企业）检查员</Option>
                                </Select>-->
                                <Input v-model="employee.techpos" readonly="true"></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <!--<Row>
                        <Col span="10">
                            <FormItem label="隶属管理局" style="width: 350px;float: left;" prop="authorityId">
                                <Select v-model.Number="employee.authorityId" placeholder="请选择">
                                    <Option v-for="item in authoritys" :key="item.id" :value="item.id">
                                        {{item.name}}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="隶属监管局" style="width: 350px;float: left;" prop="supervisionId">
                                <Select v-model.Number="employee.supervisionId" placeholder="请选择">
                                    <Option v-for="item in supervisions" :key="item.id" :value="item.id">
                                        {{item.name}}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>-->
                    <Row>
                        <Col span="10">
                            <FormItem label="出勤状态" style="width: 350px;float: left;" prop="turnOut">
                                <!--<Select v-model="employee.turnOut" placeholder="请选择">
                                    <Option v-for="item in turn" :value="item.turnId">{{item.turnName}}</Option>
                                </Select>-->
                                <Input v-model="employee.turnOut" readonly="true"></Input>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="局方委任代表" style="width: 350px;float: left;" prop="isagent">
                                <!--<Select v-model="employee.isagent" placeholder="请选择">
                                    <Option v-for="te in wethor" :value="te.weName">{{te.weId}}</Option>
                                </Select>-->
                                <Input v-model="employee.isagent" readonly="true"></Input>
                            </FormItem>
                        </Col>
                    </Row>

                    <Row>
                        <Col span="10">
                            <FormItem label="所属基地" style="width: 350px;float: left;" prop="baseId">
                                <Select v-model="employee.baseId" placeholder="请选择">
                                    <Option v-for="item in operationbase" :key="item.baseId" :value="item.baseId">
                                        {{item.baseName}}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="合同状态" style="width: 350px;float: left;" prop="contract">
                                <!--<Select v-model="employee.contract" placeholder="请选择">
                                    <Option v-for="item in cons" :value="item.consId">{{item.consName}}</Option>
                                </Select>-->
                                <Input v-model="employee.contract" readonly="true"></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem label="邮政编码" style="width: 350px;float: left;" prop="homePostcode">
                                <Input v-model="employee.homePostcode" readonly="true"></Input>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="手机号码" style="width: 350px;" prop="homeMobile">
                                <Input v-model="employee.homeMobile" readonly="true"></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem label="有效通讯地址" style="width: 350px;" prop="homeAddress">
                                <Input v-model="employee.homeAddress" readonly="true"></Input>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="电子邮箱" style="width: 350px;" prop="email">
                                <Input v-model="employee.email" readonly="true" ></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem label="紧急联系人" style="width: 350px;float: left;" prop="urgencyLinkman">
                                <Input v-model="employee.urgencyLinkman" readonly="true" ></Input>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="出生日期" style="width: 350px;" prop="birthday">
                                <DatePicker type="date" placeholder="选择日期" :editable="false"
                                            v-model="employee.birthday" style="width: 200px"></DatePicker>
                            </FormItem>
                        </Col>
                    </Row>
                        <Row>
                            <Col span="10">
                                <FormItem label="身份证号码" style="width: 350px;" prop="cardId">
                                    <Input v-model="employee.cardId" readonly="true"></Input>
                                </FormItem>
                            </Col>
                        </Row>
                    <Row>
                        <Col span="10" v-show="isPoliot">
                            <FormItem label="所属大队" style="width: 350px;float: left;" prop="flightTeam">
                                <Input v-model="pilot.flightTeam" readonly="true" ></Input>
                            </FormItem>
                        </Col>
                        <Col span="9" v-show="isPoliot">
                            <FormItem label="技术身份级别" style="width: 350px;" prop="techClass">
                                <Select v-model="pilot.techClass" @on-change="selectType" placeholder="请选择">
                                    <Option v-for="item in level" :value="item.id">{{item.name}}</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10"  v-show="isTeach">
                            <FormItem label="教员任命书有效期" style="width: 350px;" prop="birthday">
                                <DatePicker type="date" placeholder="选择日期" :editable="false" style="width: 200px"
                                            v-model="pilot.validityTime"></DatePicker>
                            </FormItem>
                        </Col>
                        <Col span="9"  v-show="teachType">
                            <FormItem label="教学类型" style="width: 350px;" prop="">
                                <Select v-model="pilot.teachCategories" placeholder="请选择">
                                    <Option value="飞机">飞机</Option>
                                    <Option value="直升机">直升机</Option>
                                    <Option value="高性能">高性能</Option>
                                    <Option value="MPL训练">MPL训练</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    </div>
                    <div style="float:left; width:300px; margin-left: -50px">
                        <word v-on:childurlone="urldata" v-on:im="view" ref="ddd"></word>
                    </div>
                    <div style="padding-bottom: 20px"></div>
                </Form>
            </div>

            <div v-show="isPoliot" style="width: 1280px; margin-top: 20px;background:white">

                    <word1 v-on:childurlone2="urlPilotData" v-on:im="view" ref="aaa"></word1>
                    <br><br>

                    <div v-show="isTeach">
                    <word3 v-on:childurlone4="urlPilotTeachData" v-on:im="view" ref="ccc"></word3>
                    </div>
            </div>
            <div v-show="isController" style="width: 1280px;  margin-top: 20px;background:white">
                <Row style="padding-top: 20px">
                    <word2 v-on:childurlone3="urlControllerData" v-on:im="view" ref="bbb"></word2>
                </Row>
            </div>
            <div style="width: 1280px; background:white;  margin-bottom: 30px">
                <!--<a data-v-942377fa="" href="javascript:;" @click="handleSubmit" class="submit-btn-user" style="margin-top: 40px;margin-left: 650px">保存</a>-->
                <a data-v-942377fa="" href="javascript:;" @click="clear" class="submit-btn-user" style="margin-top: 40px;margin-left: 650px" >返回</a>
            </div>
            <imgdetail ref="imgdetailref"></imgdetail>
        </TabPane>

        <TabPane label="执照信息" name="name2">
            <licenselist :employeeId="employee.employeeId" :employeeData="this.employeeData"></licenselist>
        </TabPane>

        <TabPane label="体检信息" name="name3">
            <phrecord :employeeId="employee.employeeId"></phrecord>
        </TabPane>

        <TabPane label="考试记录" name="name4">
            <personexam :employeeId="employee.employeeId"></personexam>
        </TabPane>

        <TabPane label="培训记录" name="name5">
            <traininfo :employeeId="employee.employeeId"></traininfo>
        </TabPane>

        <TabPane label="工作经历" name="name7">
            <workexperience :employeeId="employee.employeeId"></workexperience>
        </TabPane>

        <TabPane label="奖惩记录" name="name8">
                <award-record :employeeId="employee.employeeId"></award-record>
        </TabPane>
    </Tabs>
    </div>
</template>
<script>
    import pList from './poliotTab.vue';
    import cList from './controllerManTab.vue';
    import word from './upImage.vue';
    import word1 from './upPilotImage.vue';
    import word2 from './upControllerImage.vue';
    import licenselist from './viewLicenseList.vue';
    import phrecord from './viewPhysicalrecordlist.vue';
    import personexam from './viewPersonexamrecord.vue';
    import traininfo from './viewTraininfo.vue';
    import certificate from './newTrainingcertificate.vue';
    import workexperience from './viewWorkExperiencelist.vue';
    import awardRecord from './viewAwardRecordlist.vue';
    import word3 from './upPilotTeachImage.vue';
    import imgdetail from '../../../views/imgdetail.vue';
    import Cookies from 'js-cookie';

    export default {
        components: {
            'imgdetail':imgdetail,
            pList,
            cList,
            'word': word,
            'word1': word1,
            'word2':word2,
            'word3':word3,
            licenselist,
            phrecord,
            personexam,
            traininfo,
            certificate,
            workexperience,
            /*skillcheck,
            periodcheck,
            updateccheck,*/
            awardRecord,
        },
        data() {
            return {

                value:'name1',
                authoritys: [],
                supervisions: [],
                operationbase: [],
                datas:[],
                TeachPos: [
                    {
                        TeachPosName: '主任教员',
                        TeachPosId: '主任教员'
                    },
                    {
                        TeachPosName: '地面主任教员',
                        TeachPosId: '地面主任教员'
                    },
                    {
                        TeachPosName: '助理主任教员',
                        TeachPosId: '助理主任教员'
                    },
                    {
                        TeachPosName: '（企业）检查员',
                        TeachPosId: '（企业）检查员'
                    }
                ],
                Technicalpost: [{Technicalname: '飞行人员', Technicalid: '飞行人员'},{Technicalname: '管制人员', Technicalid: '管制人员'},{Technicalname: '维修人员', Technicalid: '维修人员'},
                    {Technicalname: '气象人员', Technicalid: '气象人员'},{Technicalname: '情报人员', Technicalid: '情报人员'},{Technicalname: '飞行签派人员', Technicalid: '飞行签派人员'},
                    {Technicalname: '市场人员', Technicalid: '市场人员'},{Technicalname: '财务人员', Technicalid: '财务人员'},{Technicalname: '保障人员', Technicalid: '保障人员'},
                    {Technicalname: '飞行管理人员', Technicalid: '飞行管理人员'}],
                sex: [
                    {
                        sexName: '男',
                        sexId: '男'
                    },
                    {
                        sexName: '女',
                        sexId: '女'
                    }
                ],
                turn: [
                    {
                        turnName: '出勤',
                        turnId: '出勤'
                    },
                    {
                        turnName: '病假',
                        turnId: '病假'
                    },
                    {
                        turnName: '事假',
                        turnId: '事假'
                    },
                    {
                        turnName: '休假',
                        turnId: '休假'
                    },
                ],
                cons: [
                    {
                        consName: '全职',
                        consId: '全职'
                    },
                    {
                        consName: '兼职',
                        consId: '兼职'
                    }
                ],
                wethor: [
                    {
                        weName: '是',
                        weId: '是'
                    },
                    {
                        weName: '否',
                        weId: '否'
                    }
                ],
                level: [
                    {
                        name: '副驾驶',
                        id: '副驾驶'
                    }, {
                        name: '机长',
                        id: '机长'
                    }, {
                        name: '理论教员',
                        id: '理论教员'
                    }, {
                        name: '飞行教员',
                        id: '飞行教员'
                    }, {
                        name: '检查员',
                        id: '检查员'
                    }, {
                        name: '考试员',
                        id: '考试员'
                    }, {
                        name: '监察员',
                        id: '监察员'
                    }, {
                        name: '主任教员',
                        id: '主任教员'
                    }, {
                        name: '助理主任教员',
                        id: '助理主任教员'
                    }

                ],

                isPoliot: false,
                isController: false,
                teachType:false,
                isTeach:false,
                pilot:{
                    pilotId:'',
                    techClass:'',
                    teachCategories:'',
                    validityTime:'',
                    wqc: '',
                    esCertificate: '',
                    chCertificate: '',
                    scanning: '',
                    scannedTeacher:'',
                    teacherQualification:'',
                    flightTeam:'',  //所属大队
                    flySumTime:'',  //飞行总时长
                    flySumIllegal:'' //违规次数
                },
                education:'',
                employee: {
                    employeeId:'',
                    userName: '',
                    jobTitle: '',
                    companyId: '',
                    userId: '',
                    pilot: '',
                    workId: '',
                    cardId: '',
                    gender: '',
                    birthday: '',
                    urgencyLinkman: '',
                    homePostcode: '',
                    nationality: '',
                    ethnicity: '',
                    education: '',
                    isagent: '',
                    age: '',
                    homeAddress: '',
                    school: '',
                    techpos:'',
                    homeMobile: '',
                    email: '',
                    authorityId: '',
                    supervisionId: '',
                    turnOut: '',
                    contract: '',
                    baseId: '',
                    techClass: '',
                    photo: '',
                    scanCard: '',
                    backScancard:'',
                    namePhoto:'',
                    companyName:"",
                    wqc: '',
                    esCertificate: '',
                    chCertificate: '',
                    scanning: '',
                    skill:'',
                    englishLevel:'',
                    commuStatus:'',
                    skillLevel:'',
                    pilotId:'',
                    teachCategories:'',
                    scannedTeacher:'',
                    teacherQualification:'',
                    validityTime:'',
                    flightTeam:'',
                    flySumTime:'',
                    flySumIllegal:''

                },
                employeeData:'',
                employeeValidate: {
                    userName:[
                        { required: true, message: '姓名不能为空', trigger: 'blur' },
                    ],
                    gender:[{ required: true, message: '性别不能为空', trigger: 'blur' },],
                    contract:[{ required: true, message: '合同状态不能为空', trigger: 'blur' },],
                    nationality:[{ required: true, message: '国籍不能为空', trigger: 'blur' },],
                    /*baseId:[{ required: true, message: '所属运行基地不能为空', trigger: 'blur' },],*/
                   /* email: [
                        { required: true, message: '邮箱不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                    ],*/
                    cardId: [{required: true, message: '身份证号不能为空', trigger: 'blur'},
                        {pattern:/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,message: '身份证号输入不合法', trigger: 'blur'}],
                    homeMobile:[
                        { required: true, message: '手机号码不能为空', trigger: 'blur' },
                        {pattern:/^1(3|4|5|7|8)\d{9}$/,message: '工作电话输入不合法', trigger: 'blur'}
                    ],

                }
            }

        },
        created:function(){
            this.value = this.$route.query.tab;
            if(this.$route.query.user) {
                var data=this.$route.query.user;
                this.employee.employeeId=data.employeeId;
                this.employeeData=data;
            }

        },
        mounted:function(){
            let self = this;
            if(self.$route.query.user){
                var userdata = self.$route.query.user;
                    self.employee=userdata;
                    if(self.employee.education=='1'){
                        self.education = '博士';
                    }
                if(self.employee.education=='2'){
                    self.education = '研究生';
                }
                if(self.employee.education=='3'){
                    self.education = '本科';
                }
                if(self.employee.education=='4'){
                    self.education = '大专';
                }
                if(self.employee.education=='5'){
                    self.education = '大专以下';
                }


                    if(self.$route.query.bir){
                        self.employee.birthday=self.$route.query.bir;
                    }

                    self.$refs.ddd.urlupdate(userdata);
                if(self.$route.query.t){
                    self.employee.jobTitle =userdata.jobTitle.split(',');
                }
                    if(self.employee.jobTitle.indexOf("飞行人员")!=-1){
                        self.isPoliot = true;
                        //获取飞行人员子表信息
                        let id = self.employee.employeeId;
                        self.$http.httpGet('/eim/api/pilot/search/byEmployeeId', {
                            id,
                        }).then(function (response) {
                            let pdata = response.data.data;
                            self.pilot=pdata;
                            self.employee.pilotId=pdata.pilotId,
                                self.employee.techClass=pdata.techClass,
                                self.employee.teachCategories=pdata.teachCategories,
                                self.employee.validityTime=pdata.validityTime,
                                self.employee.wqc=pdata.wqc,
                                self.employee.esCertificate=pdata.esCertificate,
                                self.employee.chCertificate=pdata.chCertificate,
                                self.employee.scanning=pdata.scanning,
                                self.employee.scannedTeacher=pdata.scannedTeacher,
                                self.employee.teacherQualification=pdata.teacherQualification,
                                self.employee.flightTeam=pdata.flightTeam,
                                self.employee.flySumTime=pdata.flySumTime,
                                self.employee.flySumIllegal=pdata.flySumIllegal,
                                self.$refs.aaa.urlupdate(pdata);
                            if(pdata.techClass==='理论教员'||pdata.techClass==='飞行教员'||pdata.techClass==='主任教员'||pdata.techClass==='助理主任教员'){
                                self.$refs.ccc.urlupdate(pdata);
                                self.isTeach=true;
                                if(self.pilot.techClass==='理论教员'){
                                    self.teachType=true;
                                }
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    };

                    if(self.employee.jobTitle.indexOf("管制人员")!=-1){
                        self.isController = true;
                        //获取管制人员子表信息
                        let id = self.employee.employeeId;
                        self.$http.httpGet('/eim/api/controlEmpl/search/byEmployeeId', {
                            id,
                        }).then(function (response) {
                            var controlData = response.data.data;
                            self.employee.skill=controlData.skill;
                            self.employee.englishLevel=controlData.englishLevel;
                            self.employee.commuStatus=controlData.commuStatus;
                            self.employee.skillLevel=controlData.skillLevel;
                            self.$refs.bbb.urlupdate(controlData);
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                /*}).catch(function (error) {
                    console.log(error);
                });*/
            }


        },

        methods: {
           /* handleView (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },*/
            selectop: function () {
                if(this.employee.jobTitle.indexOf("飞行人员")!=-1 && this.employee.jobTitle.indexOf("管制人员")!=-1){
                    //包含飞行人员 并且包含管制人员
                    this.isPoliot = true;
                    this.isController = true;
                }
                if(this.employee.jobTitle.indexOf("飞行人员")!=-1&&this.employee.jobTitle.indexOf("管制人员")==-1){
                    //包含飞行人员 但不包含管制人员
                    this.isPoliot = true;
                    this.isController = false;
                }
                if(this.employee.jobTitle.indexOf("飞行人员")==-1&&this.employee.jobTitle.indexOf("管制人员")!=-1){
                    //包含管制人员人员 但不包含飞行人员
                    this.isPoliot = false;
                    this.isController = true;
                }
                if(this.employee.jobTitle.indexOf("飞行人员")==-1&&this.employee.jobTitle.indexOf("管制人员")==-1){
                    //都不包含
                    this.isPoliot = false;
                    this.isController = false;
                }
            },
            selectType:function(){
                if(this.pilot.techClass == '理论教员'){
                    this.teachType=true;
                }else{
                    this.teachType=false;
                    this.pilot.teachCategories='';
                }
                if(this.pilot.techClass =='理论教员'||this.pilot.techClass =='飞行教员'||this.pilot.techClass =='主任教员'||this.pilot.techClass =='助理主任教员'){
                    this.isTeach=true;
                }else{
                    this.isTeach=false;
                    this.pilot.validityTime='';
                    this.pilot.teachCategories='';
                    this.employee.scannedTeacher='';
                    this.employee.teacherQualification='';
                }
            },

            urldata(msg, rt) {
                if (rt == 'itemflag') {
                    this.employee.photo = msg;
                } else if (rt == 'itemflag1') {
                    this.employee.scanCard = msg;
                }else if(rt == 'itemflag2'){
                    this.employee.backScancard = msg;
                }else if(rt=='itemflag3'){
                    this.employee.namePhoto = msg;
                }
            },
            view(name){
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },

            urlPilotData(msg, rt) {
                if (rt == 'itemflag3') {
                    this.employee.wqc = msg;
                } else if (rt == 'itemflag4') {
                    this.employee.esCertificate = msg;
                } else if (rt == 'itemflag5') {
                    this.employee.chCertificate = msg;
                } else if (rt == 'itemflag6') {
                    this.employee.scanning = msg;
                }
            },

            urlPilotTeachData(msg, rt){

              if(rt == 'itemflag3'){
                  this.employee.scannedTeacher = msg;
              }else if(rt=='itemflag4'){
                  this.employee.teacherQualification=msg;
              }
            },

            urlControllerData(msg, rt) {
                if (rt == 'itemflag7') {
                    this.employee.skill = msg;
                } else if (rt == 'itemflag8') {
                    this.employee.englishLevel = msg;
                } else if (rt == 'itemflag9') {
                    this.employee.commuStatus = msg;
                } else if (rt == 'itemflag10') {
                    this.employee.skillLevel = msg;
                }
            },


            getitem: function (item) {
                return item;
            },
            getuploadList: function ($event, employee) {
                employee.photo = $event;
            },

            handleSubmit: function () {
                this.$refs.employeeval.validate((valid) => {
                    if (valid) {
                        let self = this;
                        self.employee.techClass=self.pilot.techClass;
                        self.employee.teachCategories=self.pilot.teachCategories;
                        self.employee.validityTime=self.pilot.validityTime;
                        self.employee.flightTeam=self.pilot.flightTeam;
                        self.employee.flySumTime=self.pilot.flySumTime;
                        self.employee.flySumIllegal=self.pilot.flySumIllegal;
                                    self.$http.jsonPost('/eim/api/employee/update', self.employee).then(function (response) {
                                        if (response.data.resultCode === 200) {
                                            //self.employee={};
                                            //如果是飞行员  获取飞行员id
                                            /*if(response.data.data.jobTitle === '飞行人员'){
                                                self.getPilotId(response.data.data.employeeId)
                                            }*/
                                           // self.employee.employeeId=response.data.data.employeeId;
                                            alert('操作成功');
                                        } else {
                                            alert('操作失败');
                                        }

                                    }).catch(function (error) {
                                        console.log(error);
                                        self.$Message.error('操作失败');
                                    });
                    } else {
                        this.$Message.error('表单验证失败');
                    }
                });

            },
            clear: function () {
                history.go(-1);
                //this.$router.push({path: '/alls'});

            },

            getPilotId:function (id) {
                let self = this;
                self.$http.httpGet('/eim/api/pilot/search/byEmployeeId', {
                    id,
                }).then(function (response) {
                    let datainfo = response.data.data;
                    self.employee.pilotId = datainfo.pilotId;
                }).catch(function (error) {
                    console.log(error);
                });
            }

        }
    }
</script>