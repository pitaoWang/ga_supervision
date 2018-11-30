<template>
    <div style="width: 1280px; height: 900px; background:white">
        <div style="">
        <span style="width:96%; display:inline-block;text-align:right; margin-bottom: 10px; margin-top: 10px ;padding-right: 20px;">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>
                <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="6">
                            执照详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3" >执照类型</td>
                        <td class="tr-2 tr-4">{{qualification.licensetype}}</td>
                        <td class="tr-1 tr-3">执照编号</td>
                        <td class="tr-2 tr-4">{{qualification.licenseno}}</td>

                    </tr>
                  <tr>
                      <td class="tr-1 tr-3" >是否长久有效</td>
                      <td class="tr-2 tr-4" >{{radio}}</td>
                      <td class="tr-1 tr-3">颁发单位</td>
                      <td class="tr-2 tr-4">{{qualification.issueUnit}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">执照颁发时间</td>
                        <td class="tr-2 tr-4">{{qualification.issuedate}}</td>
                        <td class="tr-1 tr-3">执照到期时间</td>
                        <td class="tr-2 tr-4">{{qualification.expiredate}}</td>
                    </tr>
                     <tr v-if="qualification.licensetype=='气象执照'" >
                       <td class="tr-1 tr-3">观测资格有效性</td>
                       <td class="tr-2 tr-4">{{weatherlicense.obsereffect}}</td>
                       <td class="tr-1 tr-3">预测资格有效性</td>
                       <td class="tr-2 tr-4">{{weatherlicense.forecasteffect}}</td>

                   </tr>

                    <tr  v-if="qualification.licensetype=='气象执照'">
                        <td class="tr-1 tr-3">观测设备保障资格有效性</td>
                        <td class="tr-2 tr-4">{{weatherlicense.obserequipeffect}}</td>
                        <td class="tr-1 tr-3">气象雷达设备保障资格有效性</td>
                        <td class="tr-2 tr-4">{{weatherlicense.radarequipeffect}}</td>

                    </tr>
                    <tr v-if="qualification.licensetype=='气象执照'">
                        <td class="tr-1 tr-3">系统设备保障资格有效性</td>
                        <td class="tr-2 tr-4">{{weatherlicense.infoequipeffect}}</td>
                        <td class="tr-1 tr-3">专业类型</td>
                        <td class="tr-2 tr-4">{{weatherlicense.majorType}}</td>
                    </tr>
                     <tr v-if="qualification.licensetype=='气象执照'">
                        <td class="tr-1 tr-3" >适用地区</td>
                        <td class="tr-2 tr-4" colspan="3">{{weatherlicense.businesslist}}</td>

                    </tr>
                    <tr v-if="qualification.licensetype=='情报执照'">
                        <td class="tr-1 tr-3">适用地区</td>
                        <td class="tr-2 tr-4" colspan="3">{{intelligencelicense.businesslist}}</td>
                    </tr>

                    <tr v-if="qualification.licensetype=='飞行签派员执照'">
                        <td class="tr-1 tr-3">执照状态</td>
                        <td class="tr-2 tr-4">{{flightdispatcher.licenseState}}</td>
                        <td class="tr-1 tr-3">执照类型</td>
                        <td class="tr-2 tr-4">{{flightdispatcher.licenseProp}}</td>

                    </tr>

                    <tr  v-if="qualification.licensetype=='飞行签派员执照'">
                        <td class="tr-1 tr-3">执照颁发人</td>
                        <td class="tr-2 tr-4">{{flightdispatcher.holderName}}</td>
                        <td class="tr-1 tr-3" >持证人执业公司</td>
                        <td class="tr-2 tr-4">{{flightdispatcher.companyId}}</td>

                    </tr>
                    </tbody>
                </table>

                <div id="test" v-html="quliphoto" style="width: 1190px; border-spacing:0px; margin-left: 20px"></div>

                <div style="width: 1190px;margin-left: 20px;margin-top: 10px" v-if="qualification.licensetype=='气象执照'||qualification.licensetype=='情报执照'||qualification.licensetype=='管制执照'||qualification.licensetype=='飞行签派员执照'">
                    <Table border :columns="columns7" :data="licenseChecks"></Table>
                </div>

                <div style="width: 1190px;margin-left: 20px;margin-top: 10px" v-if="qualification.licensetype=='航空器维修执照'">
                    <Table border :columns="repair"  :data="secondSpecialInfolist"></Table>
                </div>
                <div style="width: 1190px;margin-left: 20px;margin-top: 10px" v-if="qualification.licensetype=='航空器维修执照'">
                    <Table border :columns="arlmccolumns" :data="allinfo.aircraftrepair.arlmc" ></Table>
                </div>
                <div style="width: 1190px;margin-left: 20px;margin-top: 10px" v-if="qualification.licensetype=='航空器维修执照'">
                    <Table border :columns="aprlsccolumns"  :data="allinfo.aircraftrepair.arlsc"></Table>
                </div>


                <div style="width: 1190px;margin-left: 20px;margin-top: 10px" v-if="qualification.licensetype=='航空器部件维修执照'">
                    <Table border :columns="partRepair"  :data="pallinfo.repairSpecialInfolist"></Table>
                </div>
                <div style="width: 1190px;margin-left: 20px;margin-top: 10px" v-if="qualification.licensetype=='航空器部件维修执照'">
                    <Table border :columns="aprlpccolumns" :data="pallinfo.aircraftpart.aprlpc" ></Table>
                </div>
                <div style="width: 1190px;margin-left: 20px;margin-top: 10px" v-if="qualification.licensetype=='航空器部件维修执照'">
                    <Table border :columns="aprlsccolumns" :data="pallinfo.aircraftpart.aprlsc" ></Table>
                </div>


            </div>
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        /*name: "companyqualificationdetail",*/
        data(){
            return{
                columns7: [
                    {
                        title: '签注时间',
                        key: 'licenseCheckDate',
                        align: 'center',
                        width: 588,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.licenseCheckDate
                                    }
                                }, params.row.licenseCheckDate)
                            ]);

                        }
                    },
                    {
                        title: '签注内容',
                        width: 599,
                        key: 'licenseCheckContent',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.licenseCheckContent
                                    }
                                }, params.row.licenseCheckContent)
                            ]);

                        }
                    },
                ],
                //航空器维修

                repair: [
                    {
                        title: '专业名称',
                        width: 284,
                        key: 'aprName',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aprName
                                    }
                                }, params.row.aprName)
                            ]);

                        }
                    },
                    {
                        title: '专业代码',
                        width: 280,
                        key: 'aprCode',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aprCode
                                    }
                                }, params.row.aprCode)
                            ]);

                        }
                    },
                    {
                        title: '签发日期',
                        width: 340,
                        key: 'aprissuerTime',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aprissuerTime
                                    }
                                }, params.row.aprissuerTime)
                            ]);

                        }
                    },
                    {
                        title: '有效截止日期',
                        key: 'aprexpireTime',
                        align: 'center',
                        width: 283,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aprexpireTime
                                    }
                                }, params.row.aprexpireTime)
                            ]);

                        }
                    },
                ],

                arlmccolumns: [
                    {
                        title: '航空器型号',
                        width: 284,
                        key: 'aircraftModel',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aircraftModel
                                    }
                                }, params.row.aircraftModel)
                            ]);

                        }
                    },
                    {
                        title: '航空器类别',
                        width: 280,
                        key: 'aircraftClass',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aircraftClass
                                    }
                                }, params.row.aircraftClass)
                            ]);

                        }
                    },
                    {
                        title: '签署人',
                        width: 340,
                        key: 'checkorman',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.checkorman
                                    }
                                }, params.row.checkorman)
                            ]);

                        }
                    },
                    {
                        title: '签署机型日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 283,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.checkDate
                                    }
                                }, params.row.checkDate)
                            ]);

                        }
                    },
                ],
                aprlsccolumns: [
                    {
                        title: '续签专业代码',
                        key: 'specialCode',
                        align: 'center',
                        width: 283,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.specialCode
                                    }
                                }, params.row.specialCode)
                            ]);

                        }
                    },
                    {
                        title: '续签签发时间',
                        key: 'checkDate',
                        align: 'center',
                        width: 284,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.checkDate
                                    }
                                }, params.row.checkDate)
                            ]);

                        }
                    },
                    {
                        title: '续签有效截止日期',
                        key: 'expireDate',
                        align: 'center',
                        width: 290,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.expireDate
                                    }
                                }, params.row.expireDate)
                            ]);

                        }
                    },
                    {
                        title: '签署项目签署人',
                        key: 'checkorman',
                        align: 'center',
                        width: 330,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.checkorman
                                    }
                                }, params.row.checkorman)
                            ]);

                        }
                    },
                ],
                //航空器部件维修
                partRepair: [
                    {
                        title: '执照名称',
                        width: 237,
                        key: 'aprName',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aprName
                                    }
                                }, params.row.aprName)
                            ]);

                        }
                    },
                    {
                        title: '执照代码',
                        width: 237,
                        key: 'aprCode',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aprCode
                                    }
                                }, params.row.aprCode)
                            ]);

                        }
                    },
                    {
                        title: '签发日期',
                        width: 237,
                        key: 'aprissuerTime',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aprissuerTime
                                    }
                                }, params.row.aprissuerTime)
                            ]);

                        }
                    },
                    {
                        title: '截止日期',
                        key: 'aprexpireTime',
                        align: 'center',
                        width: 237,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.aprexpireTime
                                    }
                                }, params.row.aprexpireTime)
                            ]);

                        }
                    },
                    {
                        title: '是否有效',
                        key: 'apravailable',
                        align: 'center',
                        width: 239,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.apravailable
                                    }
                                }, params.row.apravailable)
                            ]);

                        }
                    },
                ],
                aprlpccolumns: [
                    {
                        title: '签署项目日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 368,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.checkDate
                                    }
                                }, params.row.checkDate)
                            ]);

                        }
                    },
                    {
                        title: '签署项目名称',
                        width: 369,
                        key: 'projectName',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.projectName
                                    }
                                }, params.row.projectName)
                            ]);

                        }
                    },
                    {
                        title: '签署项目签署人',
                        width: 450,
                        key: 'checkorman',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.checkorman
                                    }
                                }, params.row.checkorman)
                            ]);

                        }
                    },
                ],

                aprlsccolumns: [
                    {
                        title: '续签专业代码',
                        key: 'specialCode',
                        align: 'center',
                        width: 283,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.specialCode
                                    }
                                }, params.row.specialCode)
                            ]);

                        }
                    },
                    {
                        title: '续签签发时间',
                        key: 'checkDate',
                        align: 'center',
                        width: 284,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.checkDate
                                    }
                                }, params.row.checkDate)
                            ]);

                        }
                    },
                    {
                        title: '续签有效截止日期',
                        key: 'expireDate',
                        align: 'center',
                        width: 290,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.expireDate
                                    }
                                }, params.row.expireDate)
                            ]);

                        }
                    },
                    {
                        title: '签署项目签署人',
                        width: 330,
                        key: 'checkorman',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.checkorman
                                    }
                                }, params.row.checkorman)
                            ]);

                        }
                    },
                ],


                radio:'',
                qualification: '',
                weatherlicense:'',
                intelligencelicense:'',
                flightdispatcher:'',
                licenseChecks: [],
                allinfo:{
                    aircraftrepair:{
                        secondSpecialInfo:'',
                        issueUserId:'',
                        primarySpecialName:'',
                        primarySpecialCode:'',
                        primarySpecialName1:'',
                        primarySpecialCode1:'',
                        primarySpecialIssueDate:'',
                        primarySpecialExpireDate:'',
                        arlmc:[],
                        arlsc:[]
                    },
                    personQulificationInfo:{
                    }
                },
                secondSpecialInfolist:[{
                    aprName:'',
                    aprCode:'',
                    aprissuerTime:'',
                    aprexpireTime:'',
                    apravailable:'',
                }],

                pallinfo: {
                    repairSpecialInfolist: [{
                        aprName: '',
                        aprCode: '',
                        aprissuerTime: '',
                        aprexpireTime: '',
                        apravailable: '',
                    }],
                    aircraftpart: {
                        repairSpecialInfo: '',
                        issueUserId: '',
                        aprlpc: [],
                        aprlsc: []
                    },
                    personQulificationInfo: {}
                },
                employeeId:'',
                employeeData:'',
                quliphoto:'',
            }
        },
        /*methods:{
            imgdetail:function (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },

        },*/

        mounted(){
            if (this.$route.query.licenseId) {
                let licenseId = this.$route.query.licenseId;
                let licensetype = this.$route.query.licensetype;
               /* this.employeeid =this.$route.query.employeeId;
                this.employee = this.$route.query.employee;*/
                this.employeeId =  this.$route.query.employeeId;
                this.employeeData =  this.$route.query.employeeData;
                this.init(licenseId, licensetype)
            }
            if(this.$route.query.id){
                this.qualification.employeeId = this.$route.query.id;
            }
            if(this.$route.query.type){
                this.type=this.$route.query.type;
            }
        },
        methods:{
            black:function () {
                this.$router.push({path:'/viewEmployee',query: {tab:'name2',employeeId:this.employeeId, user:this.employeeData}})
            },
            init(val1, val2) {
                let self = this;
                let url = "/eim/api";
                let qulificationUrl = url + "/qulification/search/license";

                if (val2 == "航空器部件维修执照") {
                    self.AiPartsRepairshow = true;
                    self.checkrecordshow = false;
                    self.$http.httpGet('/eim/api/qulification/' + val1, {}).then((res) => {
                        self.qualification = res.data.data;
                        let filePath = (self.qualification.filePath).split(",")
                      // alert(JSON.stringify(self.qualification))
                        let doc = '';
                        for(let i=0;i<filePath.length;i++){
                            doc =doc+ '<div class="divs"  style="display: inline-block;" ><img src="'+self.imageUrl+filePath[i]+'.thumb.jpg'+'" style="height: 120px;margin-top: 10px"></div>'
                        }
                        self.quliphoto = doc
                        if (self.qualification.expiredate == '2099-09-09' || self.qualification.expiredate == '2099-09-06' || self.qualification.expiredate === null) {
                            self.radio = '是';
                        } else {
                            self.radio = '否';
                        }
                        if (res.data.filePath != '' & res.data.filePath != null) {
                            self.uploadList = res.data.filePath.split(",");
                        }
                        //self.$refs.addaprl.initinfo(val1);
                        self.$http.httpGet("/eim/api/aircraftprl/" + val1, {}).then(function (response) {
                            let datainfo = response.data;
                            if (typeof(datainfo) != 'undefined' && datainfo != '' && datainfo != null) {

                                for(var key in datainfo)
                                {
                                    self.$set(self.pallinfo.aircraftpart,key,datainfo[key]);
                                }

                                if (self.pallinfo.aircraftpart.repairSpecialInfo != null) {
                                    self.pallinfo.repairSpecialInfolist = eval(self.pallinfo.aircraftpart.repairSpecialInfo);
                                    self.pallinfo.repairSpecialInfolist.forEach(function (item) {
                                        if(item.aprName=='STR'){
                                            item.aprCode='STR'
                                            item.aprName='航空器结构'
                                        }
                                        if(item.aprName=='MEC'){
                                            item.aprCode='MEC'
                                            item.aprName='航空器机械附件'
                                        }
                                        if(item.aprName=='PWT'){
                                            item.aprCode='PWT'
                                            item.aprName='航空器动力装置'
                                        }
                                        if(item.aprName=='LGR'){
                                            item.aprCode='LGR'
                                            item.aprName='航空器起落架'
                                        }
                                        if(item.aprName=='AVC'){
                                            item.aprCode='AVC'
                                            item.aprName='航空器电子附件'
                                        }
                                        if(item.aprName=='ELC'){
                                            item.aprCode='ELC'
                                            item.aprName='航空器电气附件'
                                        }
                                        if(item.apravailable=='0'){
                                            item.apravailable='是'
                                        }
                                        if(item.apravailable=='1'){
                                            item.apravailable='否'
                                        }

                                    })

                                    for(var i=0;i<self.pallinfo.repairSpecialInfolist.length;i++)
                                    {
                                        if(self.pallinfo.repairSpecialInfolist[i].aprissuerTime!=undefined)
                                        {
                                            self.pallinfo.repairSpecialInfolist[i].aprissuerTime=new Date(self.pallinfo.repairSpecialInfolist[i].aprissuerTime);
                                        }
                                        if(self.pallinfo.repairSpecialInfolist[i].aprexpireTime!=undefined)
                                        {
                                            self.pallinfo.repairSpecialInfolist[i].aprexpireTime=new Date(self.pallinfo.repairSpecialInfolist[i].aprexpireTime);
                                        }
                                    }
                                }
                                if (typeof (self.pallinfo.aircraftpart.aprlpc) == 'undefined' || self.pallinfo.aircraftpart.aprlpc == '') {
                                    self.pallinfo.aircraftpart.aprlpc = [];
                                }
                                if (typeof (self.pallinfo.aircraftpart.aprlsc) == 'undefined' || self.pallinfo.aircraftpart.aprlsc == '') {
                                    self.pallinfo.aircraftpart.aprlsc = [];
                                }
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });

                        return;
                    }).catch(function (error) {
                        console.log(error);
                        return;
                    });
                } else if (val2 == "航空器维修执照") {
                    self.aircraftRepairShow = true;
                    self.checkrecordshow = false;
                    self.$http.httpGet('/eim/api/qulification/' + val1, {}).then((res) => {
                        self.qualification = res.data;
                        let filePath = (self.qualification.filePath).split(",")
                        //alert(JSON.stringify(self.qualification))
                        let doc = '';
                        for(let i=0;i<filePath.length;i++){
                            doc =doc+ '<div class="divs"  style="display: inline-block;" ><img src="'+self.imageUrl+filePath[i]+'.thumb.jpg'+'" style="height: 120px;margin-top: 10px;"></div>'
                        }
                        self.quliphoto = doc
                        if (self.qualification.expiredate == '2099-09-09' || self.qualification.expiredate == '2099-09-06' || self.qualification.expiredate === null) {
                            self.radio = '是';
                        } else {
                            self.radio = '否';
                        }
                        if (res.data.filePath != '' & res.data.filePath != null) {
                            self.uploadList = res.data.filePath.split(",");
                        }
                        //self.$refs.addarl.initinfo(val1);
                        self.$http.httpGet("/eim/api/aircraftrl/"+val1,{}).then(function (response) {
                            let datainfo = response.data;
                            if(typeof(datainfo)!='undefined'&&datainfo!=''&&datainfo!=null) {

                                for(var key in datainfo)
                                {
                                    self.$set(self.allinfo.aircraftrepair,key,datainfo[key]);
                                }

                                if (self.allinfo.aircraftrepair.secondSpecialInfo != null) {
                                    self.secondSpecialInfolist = eval(self.allinfo.aircraftrepair.secondSpecialInfo);
                                }
                                self.secondSpecialInfolist.forEach(function (item) {
                                    if(item.aprName=='ME-TA'){
                                        item.aprName='航空机械-涡轮式飞机'
                                        item.aprCode='ME-TA'
                                    }
                                    if(item.aprName=='ME-PA'){
                                        item.aprName='航空机械-活塞式飞机'
                                        item.aprCode='ME-PA'
                                    }
                                    if(item.aprName=='ME-TH'){
                                        item.aprName='航空机械-涡轮式直升机'
                                        item.aprCode='ME-TH'
                                    }
                                    if(item.aprName=='ME-PH'){
                                        item.aprName='航空机械-活塞式直升机'
                                        item.aprCode='ME-PH'
                                    }
                                    if(item.aprName=='AV'){
                                        item.aprName='航空电子'
                                        item.aprCode='AV'
                                    }
                                })

                                if (typeof (self.allinfo.aircraftrepair.arlmc) == 'undefined' || self.allinfo.aircraftrepair.arlmc == '') {
                                    self.allinfo.aircraftrepair.arlmc = [];
                                }
                                if (typeof (self.allinfo.aircraftrepair.arlsc) == 'undefined' || self.allinfo.aircraftrepair.arlsc == '') {
                                    self.allinfo.aircraftrepair.arlsc = [];
                                }
                                if(self.allinfo.aircraftrepair.primarySpecialIssueDate!=undefined)
                                {
                                    self.allinfo.aircraftrepair.primarySpecialIssueDate=new Date(self.allinfo.aircraftrepair.primarySpecialIssueDate);
                                }
                                if(self.allinfo.aircraftrepair.primarySpecialExpireDate!=undefined)
                                {
                                    self.allinfo.aircraftrepair.primarySpecialExpireDate=new Date(self.allinfo.aircraftrepair.primarySpecialExpireDate);
                                }
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });



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

                        let filePath = (self.qualification.filePath).split(",")
                        //alert(JSON.stringify(self.qualification))
                        let doc = '';
                        for(let i=0;i<filePath.length;i++){
                            doc =doc+ '<div class="divs"  style="display: inline-block;" ><img src="'+self.imageUrl+filePath[i]+'.thumb.jpg'+'" style="height: 120px;margin-top: 10px;"></div>'
                        }
                        self.quliphoto = doc

                        if (self.qualification.expiredate == '2099-09-09' || self.qualification.expiredate == '2099-09-06' || self.qualification.expiredate === null) {
                            self.radio = '是';
                        } else {
                            self.radio = '否';
                        }
                        /*if (result.data.personQulificationInfo.filePath != '' & result.data.personQulificationInfo.filePath != null) {
                            self.uploadList = result.data.personQulificationInfo.filePath.split(",");
                        }*/

                        if (val2 == "气象执照") {
                            self.weatherlicense = result.data.weatherLicense;
                            self.weatherlicense.businesslist = result.data.weatherLicense.licensearea.split(",");
                            self.licenseChecks = result.data.licenseChecks;
                        }
                        if (val2 == "飞行签派员执照") {
                            self.flightdispatcher = result.data.flightDispatcherLicense;
                            self.licenseChecks = result.data.licenseChecks;
                        }
                        if (val2 == "情报执照") {
                            self.intelligencelicense = result.data.intelligenceLicense;
                            self.intelligencelicense.businesslist = result.data.intelligenceLicense.licenseArea.split(",");
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
        }
    }
</script>

<style scoped>
    .tr-1 {
        font-size: 14px;
        width: 180px;
        padding-left: 13px;
        height: 45px;
        color: #606265;
        border-left: solid 1px #DCDFE6;
        border-right: solid 1px #DCDFE6;
        border-bottom: solid 1px #DCDFE6;
        background-color: #F8F8F9;
    }

    .tr-2 {
        width: 200px;
        text-align: center;
        color: #606265;
        background-color: #FFF;
    }

    table {
        border-collapse: collapse;
    }

    table tr td {
        border: 1px solid #DCDFE6;
    }

    .tr-3 {
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
    /*background-color: #FFF;*/
    /*}*/
    .tr-5 {
        background-color: #f8f8f9;
    }

    .flightdispatcherimg > img {
        float: left;
        margin-left: 1%;

    }

    .infoPart1 table {
        border-collapse: collapse;
        border-spacing: 0;
        border-left: 1px solid #888;
        border-top: 1px solid #888;
        background: #efefef;
        width: 100%;
    }

    .div-button {
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }
</style>