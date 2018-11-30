<template>
    <div>

        <div style="margin-bottom: 1px; margin-top: 40px">
            <div class="div-list-aircraftdata1" style="margin-bottom: 1px;">

                <div @click="open.base_info_open = !open.base_info_open" style="float: left;width: 1190px; padding-left: 12px;">基础资料
                    <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open? 'minus':'plus'"></Icon>
                    </span>
                </div>
            </div>
            <!--<div class="tit active div-list-aircraftdata" @click="open.base_info_open = !open.base_info_open">-->
                    <!--<span class="fr">-->
                        <!--<Icon :type="open.base_info_open? 'minus':'plus'"></Icon>-->
                    <!--</span>-->
                <!--<div>    <img src="../../assets/基本信息.png" class="logoimgpoilt">基础资料</div>-->
            <!--</div>-->
            <div class="cut container-center" v-show="open.base_info_open" style="margin-left: 40px">
                <table style="width:1190px;border-spacing:0px; border: solid 1px #EEF1F7;">
                    <tbody>
                    <tr>
                        <td class="tr-first tr-third">姓名</td>
                        <td class="tr-two tr-four">{{employee.userName}}</td>
                        <td class="tr-first tr-third">性别</td>
                        <td class="tr-two tr-four">{{employee.gender}}</td>
                        <td rowspan="4" colspan="2" style="width:180px;padding-top: 20px;padding-left: 20px">
                            <div  v-if="employee.photo">
                                <img :src="employee.photo" style="width:140px;height: 135px;padding-left:30px;"  @click="imgdetail('',employee.photo)">
                            </div>
                            <div  v-else>
                                <img src="../../../static/images/默认人像.png" style="width:140px;height: 135px;padding-left:40px;">
                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td class="tr-first tr-third">人员类型</td>
                        <td class="tr-two tr-four">{{employee.jobTitle}}</td>
                        <td class="tr-first tr-five">出生年月</td>
                        <td class="tr-two tr-six">{{employee.birthday}}</td>

                    </tr>
                    <tr>

                        <td class="tr-first tr-five">国籍</td>
                        <td class="tr-two tr-six">{{employee.nationality}}</td>
                        <td class="tr-first tr-five">民族</td>
                        <td class="tr-two tr-six">{{employee.ethnicity}}</td>
                    </tr>
                    <tr>
                        <td class="tr-first tr-third">教育程度</td>
                        <td class="tr-two tr-four">{{employee.education}}</td>

                        <td class="tr-first tr-third">毕业院校</td>
                        <td class="tr-two tr-four">{{employee.school}}</td>
                    </tr>
                    <tr>
                        <td class="tr-first tr-third">年龄</td>
                        <td class="tr-two tr-four">{{employee.age}}</td>
                        <td class="tr-first tr-five">所属企业</td>
                        <td class="tr-two tr-six">{{employee.companyName}}</td>
                        <td rowspan="4" colspan="2" style="width:180px;height:150px;padding:0 10px 0 22px">
                            <div  v-if="employee.scanCard" style="width: 180px;height: 135px;margin-left: -10px">
                                <img :src="scanCardarra[0]"  style="width: 180px;height: 135px;"  @click="imgdetail('',scanCardarra[1])">
                            </div>
                            <div v-else>
                                <img :src="noimg" style="width: 180px;height: 135px;padding-left:5px;"/>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="tr-first tr-third">所属地区管理局</td>
                        <td class="tr-two tr-four">{{employee.authorityName}}</td>
                        <td class="tr-first tr-third">所属监管局</td>
                        <td class="tr-two tr-four">{{employee.supervisionName}}</td>
                    </tr>


                    <tr>
                        <td class="tr-first tr-five">职位</td>
                        <td class="tr-two tr-six">{{employee.techpos}}</td>
                        <td class="tr-first tr-five">出勤状态</td>
                        <td class="tr-two tr-six">{{employee.turnOut}}</td>
                    </tr>
                    <tr>
                        <td class="tr-first tr-five">所属运行基地</td>
                        <td class="tr-two tr-six">{{employee.baseName}}</td>
                        <td class="tr-first tr-five">合同状态</td>
                        <td class="tr-two tr-six">{{employee.contract}}</td>
                    </tr>
                    <tr>
                        <td class="tr-first tr-third">身份证号码</td>
                        <td colspan="3" class="tr-four tr-seven">{{employee.cardId}}</td>
                        <td rowspan="4" colspan="2" style="width:180px;height:150px;padding:0 10px 0 22px">
                            <div  v-if="employee.scanCard" style="width: 180px;height: 135px;margin-left: -10px">
                                <img :src="scanCardarra[1]"  style="width: 180px;height: 135px;"  @click="imgdetail('',scanCardarra[1])">
                            </div>
                            <div v-else>
                                <img :src="noimg" style="width: 180px;height: 135px;padding-left:5px;"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="tr-first tr-third">英语水平</td>
                        <td class="tr-two tr-six">{{pilot.englishLevel}}</td>
                        <td class="tr-first tr-third">汉语水平</td>
                        <td class="tr-two tr-six">{{pilot.chineselevel}}</td>

                    </tr>
                    <!--<tr>-->
                    <!--<td class="tr-first tr-third">技术职务</td>-->
                    <!--<td class="tr-two tr-four">{{employee.techpos}}</td>-->

                    <!--</tr>-->

                    <tr>
                        <td class="tr-first tr-third">邮政编号</td>
                        <td class="tr-two tr-four">{{employee.homePostcode}}</td>
                        <td class="tr-first tr-third">手机号码</td>
                        <td class="tr-two tr-four">{{employee.homeMobile}}</td>

                    </tr>
                    <tr>
                        <td class="tr-first tr-third">有效通讯地址</td>
                        <td class="tr-two tr-four">{{employee.homeAddress}}</td>
                        <td class="tr-first tr-five">电子邮箱</td>
                        <td class="tr-two tr-six">{{employee.email}}</td>

                    </tr>
                    <tr>
                        <td class="tr-first tr-third">紧急联系人</td>
                        <td colspan="3" class="tr-four tr-seven">{{employee.urgencyLinkman}}</td>

                    </tr>


                    <tr>
                        <td class="tr-first tr-third" style="">是否是委任代表</td>
                        <td colspan="3" class="tr-seven" style="">{{employee.isagent}}</td>
                    </tr>


                    </tbody>
                </table>
            </div>
        </div>
        <div style="margin-bottom: 1px">
            <div class="div-list-aircraftdata1" style="margin-bottom: 1px;">

                <div @click="open.base_info_open1 = !open.base_info_open1" style="float: left;width: 1190px; padding-left: 14px;">执照信息
                    <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open1? 'minus':'plus'"></Icon>
                    </span>
                </div>
            </div>
            <!--<div class="tit active div-list-aircraftdata" @click="open.base_info_open1 = !open.base_info_open1">-->
                    <!--<span class="fr">-->
                        <!--<Icon :type="open.base_info_open1? 'minus':'plus'"></Icon>-->
                    <!--</span>-->
                <!--<div><img src="../../assets/执照信息.png" class="logoimgpoilt">执照信息</div>-->
            <!--</div>-->
            <div class="cut" v-show="open.base_info_open1" style="width: 900px; margin-left: 40px"  v-if="PersonQulificationInfolist!=null">
                <Table border :columns="columns7" :data="PersonQulificationInfolist"></Table>
            </div>
        </div>
        <div style="margin-bottom: 1px">
            <div class="div-list-aircraftdata1" style="margin-bottom: 1px;">

                <div @click="open.base_info_open2 = !open.base_info_open2" style="float: left;width: 1190px; padding-left: 12px;">体检信息
                    <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open2? 'minus':'plus'"></Icon>
                    </span>
                </div>
            </div>
            <!--<div class="tit active div-list-aircraftdata" @click="open.base_info_open2 = !open.base_info_open2">-->
                <!--<span class="fr">-->
                  <!--<Icon :type="open.base_info_open2 ? 'minus':'plus'"></Icon>-->
                <!--</span>-->
                <!--<div><img src="../../assets/体检信息.png" class="logoimgpoilt">体检信息</div>-->
            <!--</div>-->
            <div class="cut" v-show="open.base_info_open2" style="width: 1190px; margin-left: 40px"  v-if="prList!=null">
                <Table border :columns="columns8" :data="prList"></Table>
            </div>
        </div>
        <div style="margin-bottom: 1px">
            <div class="div-list-aircraftdata1" style="margin-bottom: 1px;">
                <div @click="open.base_info_open15 = !open.base_info_open15" style="float: left;width: 1190px; padding-left: 12px;">工作信息
                    <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open15? 'minus':'plus'"></Icon>
                    </span>
                </div>
            </div>
            <!--<div class="tit active div-list-aircraftdata" @click="open.base_info_open10 = !open.base_info_open10">-->
            <!--<span class="fr">-->
            <!--<Icon :type="open.base_info_open10? 'minus':'plus'"></Icon>-->
            <!--</span>-->
            <!--<div><img src="../../assets/培训信息.png" class="logoimgpoilt">培训信息</div>-->
            <!--</div>-->
            <div class="cut" v-show="open.base_info_open15" style="width: 1190px; margin-left: 40px"  v-if="workRecord!=null">
                <Table border :columns="columns15" :data="workRecord"></Table>
            </div>
        </div>
        <div style="margin-bottom: 1px">
            <div class="div-list-aircraftdata1" style="margin-bottom: 1px;">

                <div @click="open.base_info_open4 = !open.base_info_open4" style="float: left;width: 1190px; padding-left: 12px; ">考试/检查信息
                    <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open4? 'minus':'plus'"></Icon>
                    </span>
                </div>
            </div>
            <!--<div class="tit active div-list-aircraftdata" @click="open.base_info_open4 = !open.base_info_open4">-->
                <!--<span class="fr">-->
                  <!--<Icon :type="open.base_info_open4 ? 'minus':'plus'"></Icon>-->
                <!--</span>-->
                <!--<div><img src="../../assets/考试信息.png" class="logoimgpoilt">考试/检查信息</div>-->
            <!--</div>-->
            <div class="cut" v-show="open.base_info_open4" style="width: 1190px; margin-left: 40px"  v-if="peList!=null">
                <Table border :columns="columns9" :data="peList"></Table>
            </div>
        </div>
        <div style="margin-bottom: 1px">
            <div class="div-list-aircraftdata1" style="margin-bottom: 1px;">

                <div @click="open.base_info_open10 = !open.base_info_open10" style="float: left;width: 1190px; padding-left: 12px;">培训信息
                    <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open10? 'minus':'plus'"></Icon>
                    </span>
                </div>
            </div>
            <!--<div class="tit active div-list-aircraftdata" @click="open.base_info_open10 = !open.base_info_open10">-->
                    <!--<span class="fr">-->
                        <!--<Icon :type="open.base_info_open10? 'minus':'plus'"></Icon>-->
                    <!--</span>-->
                <!--<div><img src="../../assets/培训信息.png" class="logoimgpoilt">培训信息</div>-->
            <!--</div>-->
            <div class="cut" v-show="open.base_info_open10" style="width:1190px; margin-left: 40px"  v-if="trainingRecords!=null">
                <Table border :columns="columns10" :data="trainingRecords"></Table>
            </div>
        </div>

        <div>
            <div class=" div-list-aircraftdata1" style="margin-bottom: 1px;">

                <div @click="open.base_info_open11 = !open.base_info_open11" style="float: left;width: 1190px; padding-left: 12px; ">证书信息
                    <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open11? 'minus':'plus'"></Icon>
                    </span>
                </div>
            </div>
            <div class="cut" v-show="open.base_info_open11" style="width: 1190px; margin-left: 40px"  v-if="certificateRecords!=null">
                <Table border :columns="columns11" :data="certificateRecords"></Table>
            </div>
        </div>

        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>

<script>

    import noimg from '../../../static/img/noimgpictrue.png';
    import share from '../views/shareflightrecords.vue';
    import imgdetail from '../views/imgdetail.vue';
    export default {
        components: {
            share,
            imgdetail
        },
        data () {
            return {
                share:true,
                showflightrecord:'',
                open: {
                    base_info_open: false,
                    base_info_open1: false,
                    base_info_open2: false,
                    base_info_open3: false,
                    base_info_open4: false,
                    base_info_open10: false,
                    base_info_open11: false,
                    base_info_open15: false,
                },
                noimg: noimg,
                keyword: '',
                employee: {},
                pilot: {},
                flightRecordVOs: [],
                prList: [],
                peList: [],
                personQulificationInfoVo: {},
                personQulificationInfo: {},
                pilotLicense: {},
                scanCardarra:[],
                personQuliPic:[],
                PersonQulificationInfolist:[],
                columns7: [
                    {
                        title: '执照编号',
                        key: 'licenseno',
                        align: 'center',
                        width: 175
                    },
                    {
                        title: '执照名称',
                        width: 191,
                        key: 'licensetype',
                        align: 'center'
                    },
                    {
                        title: '颁发时间',
                        key: 'issuedate',
                        align: 'center',
                        width: 174
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 174,
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
                                }, '查看'),

                            ]);
                        }
                    }
                ],
                columns8: [
                    {
                        title: '合格级别',
                        key: 'certificateGrade',
                        align: 'center',
                        width: 175
                    },
                    {
                        title: '合格状态',
                        width: 191,
                        key: 'passStatus',
                        align: 'center'
                    },
                    {
                        title: '截止时间',
                        width: 174,
                        key: 'certificateValidityTime',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 174,
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
                                            this.gophyrecinfo(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
                columns9: [
                    {
                        title: '考试科目',
                        key: 'subject',
                        align: 'center',
                        width: 200
                    },
                    {
                        title: '考试成绩',
                        width: 191,
                        key: 'score',
                        align: 'center'
                    },
                    {
                        title: '考试日期',
                        key: 'exameDate',
                        align: 'center',
                        width: 200
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 174,
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
                                            this.goperexaminfo(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
                //培训集合
                trainingRecords: [],
                columns10: [
                    {
                        title: '培训类型',
                        key: 'trainType',
                        align: 'center',
                        width: 175
                    },
                    {
                        title: '培训科目',
                        width: 191,
                        key: 'trainSubject',
                        align: 'center'
                    },
                    {
                        title: '培训结果',
                        key: 'result',
                        align: 'center',
                        width: 174
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 174,
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
                                            this.findtraininfo(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
                //证书信息
                certificateRecords:[],
                columns11: [
                    {
                        title: '合格证类型',
                        key: 'certificatetype',
                        align: 'center',
                        width: 175
                    },
                    {
                        title: '合格证编号',
                        width: 191,
                        key: 'certificateno',
                        align: 'center'
                    },
                    {
                        title: '颁发单位',
                        key: 'issueoffice',
                        align: 'center',
                        width: 174
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 174,
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
                                            this.findtrainingcertificate(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
                //工作记录
                workRecord:[],
                columns15: [
                    {
                        title: '工作单位',
                        key: 'workUnit',
                        align: 'center',
                        width: 170
                    },
                    {
                        title: '工作时间',
                        width: 100,
                        key: 'workTime',
                        align: 'center'
                    },
                    {
                        title: '所任职位',
                        key: 'post',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '工作内容',
                        key: 'jobContent',
                        align: 'center',
                        width: 230
                    },
                    {
                        title: '证明人',
                        key: 'witness',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '证明人联系方式',
                        key: 'proofContact',
                        align: 'center',
                        width: 160
                    }
                ],
                flightimplementRecords:[],
                columns3: [
                    {
                        title: '飞行日期',
                        key: 'flyDate',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '航空器型号',
                        width: 100,
                        key: 'aircraftModel',
                        align: 'center'
                    },
                    {
                        title: '起飞站',
                        key: 'origin',
                        align: 'center',
                        width: 100
                    },{
                        title: '起飞时刻',
                        key: 'takeOffTime',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '降落站',
                        key: 'destination',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '降落时刻',
                        key: 'landTime',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 100,
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
                                            this.gpflightrecordinfo(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
            }
        },
        props: {
            employeeId: {
                type: Number,
                default: 0
            }
        },
        watch: {
            employeeId (val) {

                this.getData(val);
                this.gettraininfoData(val);
                this.gettrainingcertificateData(val);

            },
            '$route' (to, from) {
                this.keyword='';
                this.employee={};
                this.pilot={};
                this.flightRecordVOs=[];
                this.prList=[];
                this.peList=[];
                this.personQulificationInfoVo={};
                this.personQulificationInfo={};
                this.pilotLicense={};
                this.scanCardarra=[];
                this.personQuliPic=[];
                this.PersonQulificationInfolist=[];
                this.trainingRecords=[];
                this.certificateRecords=[];
                this.workRecord=[];
            }
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            getemployeeid(){
                let self=this;
                self.$profile.getProfile().then((profile) => {
                    self.employee = profile.buz.companyId;
                    self.$http.httpGet('/app/api/company/' + self.companyId, {
                    }).then(function (response) {
                        console.log(response.data,'response.data............company');
                        let datainfo = response.data;
                        self.companyName= datainfo.name;
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },
            //加载飞行记录的信息
            getflightmplementrecordsData(val){
                let self = this;
                if (self.employeeId > 0) {
                    self.$http.httpGet('/app/api/flightimplementrecord/findFlightImplementRecordsByEmployeeId', {employeeId:val}).then((res) => {
                        let result = res.data;
                        console.log(result,'findFlightImplementRecordsByEmployeeId...........');
                        self.flightimplementRecords= result;
                    })
                }
            },

            //点击查看执照信息

            findtrainingcertificate(index){
                this.$emit('childinfotypeid',this.certificateRecords[index].id,"traincertificate");

            },
            findtraininfo(index){
                this.$emit('childinfotypeid',this.trainingRecords[index].id,"traininfo");

            },
            gophyrecinfo(index){
                this.$emit('childinfotypeid',this.prList[index].id,"physicalrecinfo");

            },
            goperexaminfo(index){
                this.$emit('childinfotypeid',this.peList[index].id,"personexamrecinfo");

            },
            gpflightrecordinfo(index){
                this.$emit('childinfo-typeid',this.flightimplementRecords[index].id,flightrecord);

            },

            gettrainingcertificateData(val){
                let self = this;
                if (self.employeeId > 0) {
                    self.$http.httpGet('/app/api/trainingcertificate/serach/getbyemployeeId', {employeeId:val}).then((res) => {
                        let result = res.data;
                        self.certificateRecords= result;
                    })
                }
            },
            gettraininfoData(val){
                let self = this;
                if (self.employeeId > 0) {
                    self.$http.httpGet('/app/api/training/serach/getbyemployeeId', {employeeId:val}).then((res) => {
                        let result = res.data;
                        self.trainingRecords= result;
                    })
                }
            },
            getData (val) {
                let self = this;
                self.scanCardarra=[];
                if (self.employeeId > 0) {
                    let url = '/app/api/employee/search/getdetailbyid';
                    self.$http.httpGet(url, {
                        employeeId:val
                    }).then((res) => {
                        let result = res.data
                        console.log("*********result:",result)
                        if (result.resultCode == 200) {
                            let temp = result.data;
                            self.employee = temp.employee;
                            self.prList = temp.prList;
                            self.peList = temp.peList;
                            self.workRecord=temp.workRecordInfo;
                            for(var i=0;i<self.prList.length;i++){
                                if(self.prList[i].passStatus == 0)
                                {
                                    self.prList[i].passStatus = "通过";
                                }
                                else
                                {
                                    self.prList[i].passStatus = "不通过";
                                }

                            }
                            //飞行执照
                            self.PersonQulificationInfolist = temp.PersonQulificationInfolist;
                            if(self.employee.scanCard!=''&&self.employee.scanCard!=null) {
                                self.scanCardarra = self.employee.scanCard.split(",");
                            }else {
                                self.scanCardarra=[];
                            }
                            switch (self.employee.status)
                            {
                                case 0:self.employee.status="全职"; break;
                                case 1:self.employee.status="兼职";break;
                                case 2:self.employee.status="离职";break;
                            }
                            switch (self.employee.education)
                            {
                                case '3':self.employee.education="本科"; break;
                                case '1':self.employee.education="博士";break;
                                case '2':self.employee.education="研究生";break;
                                case '4':self.employee.education="大专";break;
                                case '5':self.employee.education="大专以下";break;
                            }
                            switch (self.employee.politicalStatus)
                            {
                                case '1':self.employee.politicalStatus="党员"; break;
                                case '2':self.employee.politicalStatus="团员";break;
                                case '3':self.employee.politicalStatus="群众";break;
                                case '4':self.employee.politicalStatus="其他党派";break;

                            }
                            if(self.personQuliPic.length < 4)
                            {
                                for (var i = self.personQuliPic.length;i<4;i++)
                                {
                                    self.personQuliPic[i]=0;
                                }
                            }
                            console.log("self.personQuliPic",self.personQuliPic)
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                }
            },
            modifyData(index) {
//                this.selected = index; // 修改的位置
//                this.selectedlist = this.list[index];
                this.visible = true;
            },

            updateCheck(index) {
                this.$emit('childinfotypeid',this.PersonQulificationInfolist[index].id,"Qulification",this.PersonQulificationInfolist[index].licensetype);


            },
            onComplete: function () {
                //console.log(JSON.stringify(this.pilot));
                alert("done")
                this.visible = false;
            }
        }
    }
</script>
<style >
    .logoimgpoilt{
        height: 31px;
        display: inline;
        /* padding: 3px; */
        vertical-align: middle;

    }
    .content .row-4 {
        float: left;
        width: 79%;
    }

    .fr {
        float: right !important;
    }

    .mod {
        margin-bottom: 15px;
    }

    .mod .tit {
        padding: 5px 7px 5px 8px;
        border: 1px solid #373737;
        font-size: 14px;
        color: rgba(255, 255, 255, 0.85);
        border-radius: 1px;
    }

    .mod .tit:before, .mod .tit:after {
        content: " ";
        display: table;
    }

    .icon-font {
        display: inline-block;
        width: 24px;
        height: 22px;
        margin-top: -2px;
        vertical-align: middle;
        background-position: center;
        background-repeat: no-repeat;
    }

    .mod .tit .icon-font {
        margin-right: 5px;
    }

    .icon-font.icon00 {
        background-image: url(../../../static/images/pilot.png);
    }
    .noimg {
        background-image: url(../../../static/img/noimgpictrue.png);
    }
    .mod .cut {
        padding: 0;
        border: 1px solid #0e0e0e;
    }
    .tr-first {
        font-size: 14px;
        width: 156px;
        padding-left: 13px;
        height: 45px;
        color:  #606265;
        border-right: solid 1px  #EEF1F7;
        border-bottom: solid 1px  #EEF1F7;
    }
    .tr-two {
        width: 160px;
        text-align: center;
        color:  #606265;
        border-bottom: solid 1px #EEF1F7;
        border-right: solid 1px #EEF1F7;
    }

    .tr-third{
        border-bottom: solid 1px #EEF1F7;
        /*  background-color: rgba(179, 221, 252, 1);*/
    }

    .tr-four{
        border-bottom: solid 1px #EEF1F7;
        /* background-color: rgba(218, 236, 252, 1);*/
    }
    .tr-five{
        /*   background-color: rgba(201, 231, 253, 1);*/
    }
    .tr-six{
        border-bottom: solid 1px #EEF1F7;
        /*  background-color: rgba(239, 246, 253, 1);*/
    }
    .tr-seven{
        font-size: 14px;
        padding-left: 30px;
        color:  #606265;
        border-bottom: solid 1px #EEF1F7;
        border-right:  solid 1px #EEF1F7;
    }
    .tr-1-one-employ{
        height: 30px;
        padding: 15px 0px 0px 6px;
        font-size: 14px;
        color: #606265;
        border-bottom: solid 1px #EEF1F7;
        border-right: solid 1px #EEF1F7;
    }
</style>