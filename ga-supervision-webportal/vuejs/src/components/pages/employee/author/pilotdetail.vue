<template>
<div>

            <div style="margin-bottom: 3px">
                <div class="div-list-aircraftdata1-author" style="margin-bottom: 4px;">
                    <div style="float: left;backgroundColor:rgba(62, 148, 246, 1.5);width: 30px;height: 32px;"><img src="../../../../assets/基本信息.png"  style="width: 100%"></div>
                    <div @click="open.base_info_open = !open.base_info_open" style="float: left;width: 932px;height: 32px;padding-top: 3px;backgroundColor:rgba(255, 255, 255, 0.65);padding-left: 12px;color:rgba(1, 107, 217, 1) ">基础资料
                        <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open? 'minus':'plus'"></Icon>
                    </span>
                    </div>
                </div>
                <div class="cut employeecut container-center" v-show="open.base_info_open">
                    <table style="width:965px;border-spacing:0px; border: solid 1px rgba(1, 107, 217, 1);">
                        <tbody>
                        <tr>
                            <td class="tr-first tr-third">姓名</td>
                            <td class="tr-two-author tr-four">{{employee.userName}}</td>
                            <td class="tr-first tr-third">性别</td>
                            <td class="tr-two-author tr-four">{{employee.gender}}</td>
                            <td rowspan="4" colspan="2" style="width:180px;padding-top: 20px;padding-left: 20px">
                                <div  v-if="employee.photo">
                                    <img :src="employee.photo" style="width:140px;height: 135px;padding-left:30px;"  @click="imgdetail('',employee.photo)">
                                </div>
                                <div  v-else>
                                    <img src="../../../../../static/images/默认人像.png" style="width:140px;height: 135px;padding-left:40px;">
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td class="tr-first tr-five">工号</td>
                            <td class="tr-two-author tr-six">{{employee.workId}}</td>
                            <td class="tr-first tr-five">出生年月</td>
                            <td class="tr-two-author tr-six">{{employee.birthday}}</td>

                        </tr>
                        <tr>
                            <td class="tr-first tr-third">岗位类型</td>
                            <td class="tr-two-author tr-four">{{employee.jobTitle}}</td>
                            <td class="tr-first tr-third">政治面貌</td>
                            <td class="tr-two-author tr-four">{{employee.politicalStatus}}</td>
                        </tr>
                        <tr>
                            <td class="tr-first tr-five">国籍</td>
                            <td class="tr-two-author tr-six">{{employee.nationality}}</td>
                            <td class="tr-first tr-five">民族</td>
                            <td class="tr-two-author tr-six">{{employee.ethnicity}}</td>
                        </tr>
                        <tr>
                            <td class="tr-first tr-third">微信号</td>
                            <td class="tr-two-author tr-four">{{employee.wechatNo}}</td>
                            <td class="tr-first tr-third">教育程度</td>
                            <td class="tr-two-author tr-four">{{employee.education}}</td>
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
                            <td class="tr-first tr-five">所属大队</td>
                            <td class="tr-two-author tr-six">{{pilot.flightTeam}}</td>
                            <td class="tr-first tr-five">所飞机型</td>
                            <td class="tr-two-author tr-six">{{pilot.aircraftType}}</td>
                        </tr>
                        <tr>
                            <td class="tr-first tr-third">技术级别</td>
                            <td class="tr-two-author tr-four">{{employee.techgrade}}</td>
                            <td class="tr-first tr-third">技术职务</td>
                            <td class="tr-two-author tr-four">{{employee.techpos}}</td>
                        </tr>


                        <tr>
                            <td class="tr-first tr-five">员工状态</td>
                            <td class="tr-two-author tr-six">{{employee.status}}</td>
                            <td class="tr-first tr-five">护照号码</td>
                            <td class="tr-two-author tr-six">{{employee.passPort}}</td>
                        </tr>
                        <tr>
                            <td class="tr-first tr-third">身份证号码</td>
                            <td colspan="3" class="tr-four tr-seven">{{employee.cardId}}</td>
                            <td rowspan="5" colspan="2" style="width:180px;height:150px;padding:0 10px 0 22px">
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
                            <td class="tr-two-author tr-six">{{pilot.englishLevel}}</td>
                            <td class="tr-first tr-third">汉语水平</td>
                            <td class="tr-two-author tr-six">{{pilot.chineselevel}}</td>

                        </tr>
                        <!--<tr>-->
                            <!--<td class="tr-first tr-third">技术职务</td>-->
                            <!--<td class="tr-two-author tr-four">{{employee.techpos}}</td>-->

                        <!--</tr>-->
                        <tr>
                            <td class="tr-1-one-employ" colspan="4">紧急联系人</td>
                        </tr>
                        <tr>
                            <td class="tr-first tr-third">家庭联系人姓名</td>
                            <td class="tr-two-author tr-four">{{employee.homeName}}</td>
                            <td class="tr-first tr-third">家庭所在国家</td>
                            <td class="tr-two-author tr-four">{{employee.homeCountry}}</td>

                        </tr>
                        <tr>
                            <td class="tr-first tr-five">家庭电话</td>
                            <td class="tr-two-author tr-six">{{employee.homeTel}}</td>
                            <td class="tr-first tr-five">家庭手机</td>
                            <td class="tr-two-author tr-six">{{employee.homeMobile}}</td>

                        </tr>
                        <tr>
                            <td class="tr-first tr-third">家庭住址</td>
                            <td colspan="3" class="tr-four tr-seven">{{employee.homeAddress}}</td>
                            <td rowspan="4" colspan="2" style="width:180px;height:150px;padding:0 10px 0 22px">
                                <div  v-if="employee.scanSignature" style="width: 180px;height: 135px;margin-left: -10px">
                                    <img :src="employee.scanSignature"  style="width: 180px;height:135px;"  @click="imgdetail('',employee.scanSignature)">
                                </div>
                                <div v-else>
                                    <img :src="noimg" style="width: 180px;height:135px;padding-left:5px;"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1-one-employ" colspan="4">单位主管联系人</td>
                        </tr>
                        <tr>
                            <td class="tr-first tr-third">单位联系人</td>
                            <td class="tr-two-author tr-six">{{employee.businessName}}</td>
                            <td class="tr-first tr-third">工作电话</td>
                            <td class="tr-two-author tr-six">{{employee.businessTel}}</td>
                        </tr>
                        <tr>
                            <td class="tr-first tr-five">单位邮编</td>
                            <td class="tr-two-author tr-four">{{employee.homePostcode}}</td>
                            <td class="tr-first tr-five">单位所在国家</td>
                            <td class="tr-two-author tr-four">{{employee.businessCountry}}</td>
                        </tr>
                        <tr>
                            <td class="tr-first tr-third" style="border-bottom: 0">单位地址</td>
                            <td colspan="3" class="tr-seven" style="border-bottom: 0">{{employee.businessAddress}}</td>
                            <td  style="border-bottom: 1px solid;border-right: solid 1px"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="margin-bottom: 3px">
                <div class="div-list-aircraftdata1-author" style="margin-bottom: 4px;">
                    <div style="float: left;backgroundColor:rgba(62, 148, 246, 1.5);width: 30px;height: 32px;"><img src="../../../../assets/执照信息.png" style="width: 100%"></div>
                    <div @click="open.base_info_open1 = !open.base_info_open1" style="float: left;width: 932px;height: 32px;padding-top: 3px;backgroundColor:rgba(255, 255, 255, 0.65);padding-left: 12px;color:rgba(1, 107, 217, 1) ">执照信息
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
                <div class="cut employeecut" v-show="open.base_info_open1" style="width: 965px"  v-if="PersonQulificationInfolist!=null">
                    <Table border :columns="columns7" :data="PersonQulificationInfolist"></Table>
                </div>
            </div>
            <div style="margin-bottom: 3px">
                <div class="div-list-aircraftdata1-author" style="margin-bottom: 4px;">
                    <div style="float: left;backgroundColor:rgba(62, 148, 246, 1.5);width: 30px;height: 32px;"><img src="../../../../assets/体检信息.png" style="width: 100%"></div>
                    <div @click="open.base_info_open2 = !open.base_info_open2" style="float: left;width: 932px;height: 32px;padding-top: 3px;backgroundColor:rgba(255, 255, 255, 0.65);padding-left: 12px;color:rgba(1, 107, 217, 1) ">体检信息
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
                <div class="cut employeecut" v-show="open.base_info_open2" style="width: 965px"  v-if="prList!=null">
                    <Table border :columns="columns8" :data="prList"></Table>
                </div>
            </div>
            <div style="margin-bottom: 3px">
                <div class="div-list-aircraftdata1-author" style="margin-bottom: 4px;">
                    <div style="float: left;backgroundColor:rgba(62, 148, 246, 1.5);width: 30px;height: 32px;"><img src="../../../../assets/考试信息.png" style="width: 100%"></div>
                    <div @click="open.base_info_open4 = !open.base_info_open4" style="float: left;width: 932px;height: 32px;padding-top: 3px;backgroundColor:rgba(255, 255, 255, 0.65);padding-left: 12px;color:rgba(1, 107, 217, 1) ">考试/检查信息
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
                <div class="cut employeecut" v-show="open.base_info_open4" style="width: 965px"  v-if="peList!=null">
                    <Table border :columns="columns9" :data="peList"></Table>
                </div>
            </div>
            <div style="margin-bottom: 3px">
                <div class="div-list-aircraftdata1-author" style="margin-bottom: 4px;">
                    <div style="float: left;backgroundColor:rgba(62, 148, 246, 1.5);width: 30px;height: 32px;"><img src="../../../../assets/培训信息.png" style="width: 100%"></div>
                    <div @click="open.base_info_open10 = !open.base_info_open10" style="float: left;width: 932px;height: 32px;padding-top: 3px;backgroundColor:rgba(255, 255, 255, 0.65);padding-left: 12px;color:rgba(1, 107, 217, 1) ">培训信息
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
                <div class="cut employeecut" v-show="open.base_info_open10" style="width: 965px"  v-if="trainingRecords!=null">
                    <Table border :columns="columns10" :data="trainingRecords"></Table>
                </div>
            </div>

            <div style="margin-bottom: 3px">
                <div class=" div-list-aircraftdata1-author" style="margin-bottom: 4px;">
                    <div style="float: left;backgroundColor:rgba(62, 148, 246, 1.5);width: 30px;height: 32px;"><img src="../../../../assets/证书信息.png" style="width: 100%"></div>
                    <div @click="open.base_info_open11 = !open.base_info_open11" style="float: left;width: 932px;height: 32px;padding-top: 3px;backgroundColor:rgba(255, 255, 255, 0.65);padding-left: 12px;color:rgba(1, 107, 217, 1) ">证书信息
                        <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open11? 'minus':'plus'"></Icon>
                    </span>
                    </div>
                </div>
                <!--<div class="tit active div-list-aircraftdata" @click="open.base_info_open11 = !open.base_info_open11">-->
                    <!--<span class="fr">-->
                        <!--<Icon :type="open.base_info_open11? 'minus':'plus'"></Icon>-->
                    <!--</span>-->
                    <!--<div><img src="../../assets/证书信息.png" class="logoimgpoilt">证书信息</div>-->
                <!--</div>-->
                <div class="cut employeecut" v-show="open.base_info_open11" style="width: 965px"  v-if="certificateRecords!=null">
                    <Table border :columns="columns11" :data="certificateRecords"></Table>
                </div>
            </div>

            <div>
                <div class="div-list-aircraftdata1-author" style="margin-bottom: 4px;">
                    <div style="float: left;backgroundColor:rgba(62, 148, 246, 1.5);width: 30px;height: 32px;"><img src="../../../../assets/飞行记录列表.png" style="width: 100%"></div>
                    <div @click="open.base_info_open3 = !open.base_info_open3" style="float: left;width: 932px;height: 32px;padding-top: 3px;backgroundColor:rgba(255, 255, 255, 0.65);padding-left: 12px;color:rgba(1, 107, 217, 1) ">飞行记录
                        <span class="fr" style="margin-right: 10px">
                        <Icon :type="open.base_info_open3? 'minus':'plus'"></Icon>
                    </span>
                    </div>
                </div>
                <div class="cut" v-show="open.base_info_open3" style="width: 965px"  v-if="certificateRecords!=null">
                    <Table border :columns="columns3" :data="flightimplementRecords"></Table>
                    <div style="margin: 10px;overflow: hidden">
                        <div style="float: right;">
                            <Page :total="pagecount" :current="currentpage" @on-change="changePage"></Page>
                        </div>
                    </div>
                </div>
            </div>
    <imgdetail ref="imgdetailref"></imgdetail>
</div>



</template>

<script>

    import noimg from '../../../../../static/img/noimgpictrue.png';
    import share from './shareflightrecords.vue';
    import imgdetail from './imgdetail.vue';
    export default {
        components: {
            share,
            imgdetail
        },
        data () {
            return {
                pagecount:'',
                weatherlicenseshow:true,
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
                        width: 240
                    },
                    {
                        title: '执照名称',
                        width: 240,
                        key: 'licensetype',
                        align: 'center'
                    },
                    {
                        title: '颁发时间',
                        key: 'issuedate',
                        align: 'center',
                        width: 240
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 244,
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
                        width: 240
                    },
                    {
                        title: '合格状态',
                        width: 240,
                        key: 'passStatus',
                        align: 'center'
                    },
                    {
                        title: '截止时间',
                        width: 240,
                        key: 'certificateValidityTime',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 244,
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
                        width: 240
                    },
                    {
                        title: '是否通过',
                        width: 240,
                        key: 'passStatus',
                        align: 'center'
                    },
                    {
                        title: '考试地点',
                        key: 'examSite',
                        align: 'center',
                        width: 240
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 244,
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
                        width: 240
                    },
                    {
                        title: '培训科目',
                        width: 240,
                        key: 'trainSubject',
                        align: 'center'
                    },
                    {
                        title: '培训结果',
                        key: 'result',
                        align: 'center',
                        width: 240
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 244,
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
                        title: '证书类型',
                        key: 'certificatetype',
                        align: 'center',
                        width: 240
                    },
                    {
                        title: '证书编号',
                        width: 240,
                        key: 'certificateno',
                        align: 'center'
                    },
                    {
                        title: '颁发单位',
                        key: 'issueoffice',
                        align: 'center',
                        width: 240
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 244,
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
                flightimplementRecords:[],
                columns3: [
                    {
                        title: '飞行日期',
                        key: 'flyDate',
                        align: 'center',
                        width: 240
                    },
                    {
                        title: '航空器型号',
                        width: 120,
                        key: 'aircraftModel',
                        align: 'center'
                    },
                    {
                        title: '起飞站',
                        key: 'origin',
                        align: 'center',
                        width: 120
                    },{
                        title: '起飞时刻',
                        key: 'takeOffTime',
                        align: 'center',
                        width: 120
                    },
                    {
                        title: '降落站',
                        key: 'destination',
                        align: 'center',
                        width: 120
                    },
                    {
                        title: '降落时刻',
                        key: 'landTime',
                        align: 'center',
                        width: 120
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 124,
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
        created () {
            this.getData();
            this.gettraininfoData();
            this.gettrainingcertificateData();
            this.getflightmplementrecordsData();

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
                this.getflightmplementrecordsData(val,0);

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

            }
        },
        methods: {
            changePage(val){
                if(this.aircraftid==0){
                    this.getflightmplementrecordsData(this.employeeid,val-1);
                }
                else{
                    this.getflightmplementrecordsDataByAircraftId(this.aircraftid,val-1);
                }
            },
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            getemployeeid(){
                let self=this;
                self.$profile.getProfile().then((profile) => {
                    self.employee = profile.buz.companyId;
                    self.$http.httpGet('/eim/api/company/' + self.companyId, {
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
            getflightmplementrecordsData(val,val1){
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    if (val > 0) {
                        self.$http.httpGet('/eim/api/flightimplementrecord/findPageFlightImplementRecordsByEmployeeId', {
                            employeeId:val,
                            pageno:val1,
                            pagesize:10
                        }).then((res) => {
                            let result ;
                            self.pagecount=res.data.totalElements;
                            //self.items = res.data;
                            result=res.data.content;
                            self.flightimplementRecords= result;
                        })
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },

            //点击查看执照信息
            gpflightrecordinfo(index){
                let id = this.flightimplementRecords[index].id;
                this.$emit('childinfotypeid',id,"flightimplement");
            },


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
            getflyrecordid(mesg){
                this.$emit('childinfotypeid',mesg,"flightrecord");
            },
            gettrainingcertificateData(val){
                let self = this;
                if (self.employeeId > 0) {
                    self.$http.httpGet('/eim/api/trainingcertificate/serach/getbyemployeeId', {employeeId:val}).then((res) => {
                        let result = res.data;
                        self.certificateRecords= result;
                    })
                }
            },
            gettraininfoData(val){
                let self = this;
                if (self.employeeId > 0) {
                    self.$http.httpGet('/eim/api/training/serach/getbyemployeeId', {employeeId:val}).then((res) => {
                        let result = res.data;
                        self.trainingRecords= result;
                    })
                }
            },
            getData (val) {
                this.employeeid=val;
                var getCheckedData = function(data, defaultVal) {
                    return (data != null) ? data : defaultVal;
                }

                let self = this;
                self.scanCardarra=[];
                if (self.employeeId > 0) {
                    let url = '/eim/api/pilot/'+val;
                    self.$http.httpGet(url, {}).then((res) => {
                        let result = res.data
                        if (result.resultCode == 200) {
                            let temp = result.data;
                            self.employee = getCheckedData(temp.employee.employee, {});
                            self.pilot = getCheckedData(temp.employee.pilot, {});
                            //飞行记录
                            self.flightRecordVOs = getCheckedData(temp.flightRecordVOs, {});
                            self.prList = getCheckedData(temp.prList, []);
                            self.peList = getCheckedData(temp.peList, []);
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
                            self.PersonQulificationInfolist = getCheckedData(temp.PersonQulificationInfolist, []);

                            if(self.employee.scanCard!='') {
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
//                            if(self.personQulificationInfo.filePath !=''){
//                                self.personQuliPic = self.personQulificationInfo.filePath.split(",");
//                            }
//                            else{
//                                self.personQuliPic = [];
//                            }
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
                            self.employee= {};
                            self.pilot= {};
                            self.flightRecordVOs= [];
                            self.prList= [];
                            self.peList= [];
                            self.personQulificationInfoVo= {};
                            self.personQulificationInfo= {};
                            self.pilotLicense= {};
                            self.scanCardarra=[];
                            self.personQuliPic=[];
                            self.PersonQulificationInfolist=[];
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
        background-image: url(../../../../../static/images/pilot.png);
    }
    .noimg {
        background-image: url(../../../../../static/img/noimgpictrue.png);
    }
    .mod .cut {
        padding: 0;
        border: 1px solid #0e0e0e;
    }
    .tr-first {
        font-size: 14px;
        width: 136px;
        padding-left: 13px;
        height: 45px;
        color:  rgba(1, 107, 217, 1);
        border-right: solid 1px rgba(1, 107, 217, 1);
        border-bottom: solid 1px rgba(1, 107, 217, 1);
    }
    .tr-two-author {
        width: 243px;
        text-align: center;
        color:  black;
        border-bottom: solid 1px rgba(1, 107, 217, 1);
        border-right: solid 1px rgba(1, 107, 217, 1);
    }

    .tr-third{
        background-color: rgba(179, 221, 252, 1);
    }

    .tr-four{
        background-color: rgba(218, 236, 252, 1);
    }
    .tr-five{
        background-color: rgba(201, 231, 253, 1);
    }
    .tr-six{
        background-color: rgba(239, 246, 253, 1);
    }
    .tr-seven{
        font-size: 14px;
        padding-left: 30px;
        color:  black;
        border-bottom: solid 1px rgba(1, 107, 217, 1);
        border-right: solid 1px rgba(1,107,217,1);
    }
    .tr-1-one-employ{
        height: 35px;
        padding: 15px 0px 0px 6px;
        font-size: 14px;
        color: #1668c1;
        border-bottom: solid 1px rgba(1, 107, 217, 1);
        border-right: solid 1px rgba(1, 107, 217, 1);
    }
</style>