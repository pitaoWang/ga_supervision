<template>
    <div>
        <Form :label-width="205"  ref="aircraftrvdref" :model="allinfo">
            <div style="margin-bottom: 8px">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <Button type="primary" @click="handleAdd" icon="plus" style="width: 98px;height: 32px;margin:24px 20px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>
            </div>
            <div class="bottom5" style="width: 1159px;border:1px solid rgba(1,107,217,.5);background-color:white; padding-bottom: 10px;margin-right: 20px;float: right;margin-bottom: 10px" :key="aprindex"
                 v-for="(apritem, aprindex) in allinfo.repairSpecialInfolist">
                <h2 style="padding: 7px;">航空器部件维修执照信息{{aprindex+1}}
                    <Icon type="minus-circled" size="20" @click.native="handleRemove(aprindex)"
                          style="float: right;"></Icon>
                </h2>
                <!--<Row style="height: 50px">
                    <Col :md="12" style="height: 37px">-->
                <div style="margin-left:-63px">
                    <FormItem label="执照名称"  style="display: inline-block;width: 550px" :key="aprindex" :prop="'repairSpecialInfolist.' + aprindex + '.aprName'" :rules="{required:true, message: '执照名称不能为空', trigger: 'change' }">
                        <Select v-model="apritem.aprName" placeholder="">
                            <Option :value="item.key" :key="item.key" v-for="item in aircraftpartcodeNamelist">
                                {{item.value}}
                            </Option>
                        </Select>
                    </FormItem>
                    <!--</Col>
                    <Col :md="11" style="height: 37px">-->
                    <FormItem label="执照代码"  style="display: inline-block;width: 550px">
                        {{apritem.aprName}}
                    </FormItem>
                    <!--    </Col>
                    </Row>-->
                </div>
                <!--<Row style="height: 50px">
                    <Col :md="12">-->
                <div style="margin-left:-63px" >
                    <FormItem label="签发日期"  style="display: inline-block;width: 550px" :key="aprindex" :prop="'repairSpecialInfolist.' + aprindex + '.aprissuerTime'" :rules="{required: true,type:'date', message: '签发日期不能为空', trigger: 'change' }">
                        <DatePicker v-model="apritem.aprissuerTime" style="width: 345px" type="date" placeholder="选择颁发时间" :editable="false"></DatePicker>
                    </FormItem>
                    <!--</Col>
                    <Col :md="11">-->
                    <FormItem label="截止日期" style="display: inline-block;width: 550px" :key="aprindex" :prop="'repairSpecialInfolist.' + aprindex + '.aprexpireTime'" :rules="{required: true,type:'date', message: '截止日期不能为空', trigger: 'change' }">
                        <DatePicker v-model="apritem.aprexpireTime" style="width: 345px" type="date" placeholder="选择到期时间" :editable="false"></DatePicker>
                    </FormItem>
                    <!--   </Col>
                   </Row>-->
                </div>
                <!--<Row style="height: 50px">
                    <Col :md="12">-->
                <div style="margin-left:-63px">
                    <FormItem label="是否有效"  style="display: inline-block;width: 550px" :key="aprindex" :prop="'repairSpecialInfolist.' + aprindex + '.apravailable'" :rules="{required: true, message: '是否有效不能为空', trigger: 'change' }">
                        <Select v-model="apritem.apravailable" placeholder="">
                            <Option value="0">是</Option>
                            <Option value="1">否</Option>
                        </Select>
                    </FormItem>
                    <!--   </Col>
                   </Row>-->
                </div>
            </div>
            <!--<Row style="height: 50px">
                <Col span="24" style="text-align: center;">
                <Button type="primary" @click="handleAdd" icon="plus"
                        style="width:150px;margin-right:10px;" size="small">增 加
                </Button>
                </Col>
            </Row>-->
            <div class="bottom5" style="margin-top:10px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <Button type="primary" @click="add_aprlpc_showfuntion" icon="plus" style="width: 98px;height: 32px;margin:24px 20px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>

                <div class="bottom5" style="margin-top:5px;width:98.5%;padding-left: 20px">
                    <Table border :columns="aprlpccolumns" :data="allinfo.aircraftpart.aprlpc"></Table>
                </div>
                <span style="width:100%;display:inline-block;text-align:right;">
                    <Button type="primary" @click="add_aprlsc_showfuntion" icon="plus" style="width: 98px;height: 32px;margin:24px 20px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>

                <div class="bottom5" style="margin-top:5px;width:98.5%;padding-left: 20px">
                    <Table border :columns="aprlsccolumns" :data="allinfo.aircraftpart.aprlsc"></Table>
                </div>
            </div>
        </Form>
        <Modal v-model="add_aprlpc_show" width="400" title="航空器部件维修执照项目签署" :closable="false" @on-ok="addaprlpc"
               @on-cancel="cancelaprlpc"
               :styles="{top: '20px'}">
            <Form :label-width="100">
                <div>
                    <Row>
                        <Col :md="23">
                            <FormItem label="签署项目名称" class="bottom5">
                                <Input v-model="aprlpcentity.projectName" placeholder=""></Input>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="签署项目日期" class="bottom5">
                                <DatePicker v-model="aprlpcentity.checkDate" type="date" placeholder="选择日期" style="width: 252px"></DatePicker>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="签署人" class="bottom5">
                                <!--<DatePicker v-model="aprlpcentity.checkDate" type="date" placeholder="选择日期"></DatePicker>-->
                                <Input v-model="aprlpcentity.checkorman" placeholder=""></Input>
                            </FormItem>
                        </Col>
                    </Row>
                </div>
            </Form>
        </Modal>
        <Modal v-model="add_aprlsc_show" width="400" title="航空器部件维修执照专业续签" :closable="false" @on-ok="addaprlsc"
               @on-cancel="cancelaprlsc"
               :styles="{top: '20px'}">
            <Form :label-width="100">
                <div>
                    <Row>
                        <Col :md="23">
                            <FormItem label="执照续签专业代码" class="bottom5">
                                <Input v-model="aprlscentity.specialCode" placeholder=""></Input>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="执照续签签发日期" class="bottom5">
                                <DatePicker v-model="aprlscentity.checkDate" type="date" placeholder="选择日期" style="width: 252px"></DatePicker>
                            </FormItem>
                            <FormItem label="执照续签有效截止日期" class="bottom5">
                                <DatePicker v-model="aprlscentity.expireDate" type="date" placeholder="选择日期" style="width: 252px"></DatePicker>
                            </FormItem>

                            <FormItem label="签署人" class="bottom5">
                                <!--<DatePicker v-model="aprlscentity.checkorman" type="date" placeholder="选择日期"></DatePicker>-->
                                <Input v-model="aprlscentity.checkorman" placeholder=""></Input>
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
    }

</style>

<script>
    import aircraftdata from '../../../data/aircraft/index';
    export default {
        data (){
            return {
                aircraftpartcodeNamelist: aircraftdata.aircraftpartcodeNamelist,
                initlicenseId: '',
                initcheckorUserId: '',
                arrayindex: '',//用于保存修改数组中的下标,从点击修改函数开始赋值(arrayindex=index),到点击取消或者保存按钮结束(arrayindex=''),
                aprlpcentity: {//与model绑定
                    id: '',
                    licenseId: '',
                    projectName: '',
                    checkorUserId: '',
                    checkDate: '',
                    checkorman:''
                },
                aprlscentity: {//与model绑定
                    id: '',
                    licenseId: '',
                    projectName: '',
                    checkorUserId: '',
                    checkDate: '',
                    checkorman:''
                },
                mapaprlpcentity: {},//作为model与aircraftpart的中间变量,保存到数组中
                aprlpccolumns: [
                    {
                        title: '签署项目日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 318
                    },
                    {
                        title: '签署项目名称',
                        width: 319,
                        key: 'projectName',
                        align: 'center'
                    },
                    {
                        title: '签署人',
                        width: 400,
                        key: 'checkorman',
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
                                            this.updateCheckaprlpc(params.index)
                                        }
                                    }
                                }),
                                /*h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            if (params.row.id != '') {
                                                this.$Modal.confirm({
                                                    title: '<p >确认对话框标题</p>',
                                                    content: '<p >确定删除</p>',
                                                    onOk: () => {
                                                        let self = this;//删除
                                                        self.$http.httpDelete("/eim/api/aircraftprlpc/" + params.row.id, {}).then(function (response) {
                                                            self.handleRemoveaprlpc(params.index);
                                                        }).catch(function (error) {
                                                            console.log(error);
                                                        });
                                                    },
                                                    onCancel: () => {
                                                        this.$Message.info('点击了取消');
                                                    }
                                                });
                                            } else {
                                                this.handleRemoveaprlpc(params.index);
                                            }
                                        }
                                    }
                                }, '删除'),*/
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
                                            if (params.row.id != '') {
                                                this.$Modal.confirm({
                                                    title: '<p >确认对话框标题</p>',
                                                    content: '<p >确定删除</p>',
                                                    onOk: () => {
                                                        let self = this;//删除
                                                        self.$http.httpDelete("/eim/api/aircraftprlpc/" + params.row.id, {}).then(function (response) {
                                                            self.handleRemoveaprlpc(params.index);
                                                        }).catch(function (error) {
                                                            console.log(error);
                                                        });
                                                    },
                                                    onCancel: () => {
                                                        this.$Message.info('点击了取消');
                                                    }
                                                });
                                            } else {
                                                this.handleRemoveaprlpc(params.index);
                                            }
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],
                aprlsccolumns: [
                    {
                        title: '执照续签专业代码',
                        key: 'specialCode',
                        align: 'center',
                        width: 243
                    },
                    {
                        title: '执照续签签发日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 244
                    },
                    {
                        title: '执照续签有效截止日期',
                        key: 'expireDate',
                        align: 'center',
                        width: 250
                    },
                    {
                        title: '签署人',
                        width: 300,
                        key: 'checkorman',
                        align: 'center'
                    },

                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                /*h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheckaprlsc(params.index)
                                        }
                                    }
                                }, '修改'),*/
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
                                            this.updateCheckaprlsc(params.index)
                                        }
                                    }
                                }),
                                /*h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            if (params.row.id != '') {
                                                this.$Modal.confirm({
                                                    title: '<p >确认对话框标题</p>',
                                                    content: '<p >确定删除</p>',
                                                    onOk: () => {
                                                        let self = this;//删除
                                                        self.$http.httpDelete("/eim/api/aircraftprlsc/" + params.row.id, {}).then(function (response) {
                                                            self.handleRemoveaprlsc(params.index);
                                                        }).catch(function (error) {
                                                            console.log(error);
                                                        });

                                                    },
                                                    onCancel: () => {
                                                        this.$Message.info('点击了取消');
                                                    }
                                                });
                                            } else {
                                                this.handleRemoveaprlsc(params.index);
                                            }
                                        }
                                    }
                                }, '删除'),*/
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
                                            if (params.row.id != '') {
                                                this.$Modal.confirm({
                                                    title: '<p >确认对话框标题</p>',
                                                    content: '<p >确定删除</p>',
                                                    onOk: () => {
                                                        let self = this;//删除
                                                        self.$http.httpDelete("/eim/api/aircraftprlsc/" + params.row.id, {}).then(function (response) {
                                                            self.handleRemoveaprlsc(params.index);
                                                        }).catch(function (error) {
                                                            console.log(error);
                                                        });

                                                    },
                                                    onCancel: () => {
                                                        //this.$Message.info('点击了取消');
                                                    }
                                                });
                                            } else {
                                                this.handleRemoveaprlsc(params.index);
                                            }
                                        }
                                    }
                                })



                            ]);
                        }
                    }
                ],
                add_aprlpc_show: false,
                add_aprlsc_show: false,
                allinfo: {
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
                }
            }
        },
        props: ['personQulificationInfo'],
//        mounted:function()
//        {
//            this.initinfo();
//        },
        methods: {
            handlesubmit()
            {
                this.$refs.aircraftrvdref.validate((valid)=>{if(valid){
                    let self = this;
                    self.allinfo.personQulificationInfo = self.personQulificationInfo;
                    self.allinfo.aircraftpart.repairSpecialInfo = JSON.stringify(self.allinfo.repairSpecialInfolist);
                   // self.allinfo.aircraftpart.issueUserId = profile.user.userId;
                    self.$http.httpPost("/eim/api/aircraftprl", self.allinfo).then(function (response) {
//                            self.$Message.success("提交成功");
                        alert("执照信息保存成功！")
                        //self.$router.push({path: '/pilot_info', query: {qualificationisshow: true}});
                    }).catch(function (error) {
                        console.log(error);
                        self.$Message.error("提交失败");
                    });
                }else {
                    this.$Message.error("表单验证失败");
                }})
            },
            initinfo(varlicenseId) {
                let self = this;
                self.initlicenseId = varlicenseId;
                //self.initcheckorUserId = profile.user.userId;
                self.aprlpcentity.licenseId = this.initlicenseId;
                self.aprlpcentity.checkorUserId = this.initcheckorUserId;
                self.aprlscentity.licenseId = this.initlicenseId;
                self.aprlscentity.checkorUserId = this.initcheckorUserId;


                self.$http.httpGet("/eim/api/aircraftprl/" + varlicenseId, {}).then(function (response) {
                    let datainfo = response.data;
                    if (typeof(datainfo) != 'undefined' && datainfo != '' && datainfo != null) {

                        for(var key in datainfo)
                        {
                            self.$set(self.allinfo.aircraftpart,key,datainfo[key]);
                        }

                        if (self.allinfo.aircraftpart.repairSpecialInfo != null) {
                            self.allinfo.repairSpecialInfolist = eval(self.allinfo.aircraftpart.repairSpecialInfo);
                            for(var i=0;i<self.allinfo.repairSpecialInfolist.length;i++)
                            {
                                if(self.allinfo.repairSpecialInfolist[i].aprissuerTime!=undefined)
                                {
                                    self.allinfo.repairSpecialInfolist[i].aprissuerTime=new Date(self.allinfo.repairSpecialInfolist[i].aprissuerTime);
                                }
                                if(self.allinfo.repairSpecialInfolist[i].aprexpireTime!=undefined)
                                {
                                    self.allinfo.repairSpecialInfolist[i].aprexpireTime=new Date(self.allinfo.repairSpecialInfolist[i].aprexpireTime);
                                }
                            }
                        }
                        if (typeof (self.allinfo.aircraftpart.aprlpc) == 'undefined' || self.allinfo.aircraftpart.aprlpc == '') {
                            self.allinfo.aircraftpart.aprlpc = [];
                        }
                        if (typeof (self.allinfo.aircraftpart.aprlsc) == 'undefined' || self.allinfo.aircraftpart.aprlsc == '') {
                            self.allinfo.aircraftpart.aprlsc = [];
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            handleAdd () {
                let info = {
                    id: this.initlicenseId,
                    issueUserId: '',
                    aprName: '',
                    aprCode: '',
                    aprissuerTime: '',
                    aprexpireTime: '',
                    apravailable: '',
                };
                if (info.aprissuerTime != null&&info.aprissuerTime != '') {

                    var d = new Date(info.aprissuerTime);
                    info.aprissuerTime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                }
                ;
                if (info.aprexpireTime != null&&info.aprissuerTime != '') {

                    var d = new Date(info.aprexpireTime);
                    info.aprexpireTime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                }
                ;
                this.allinfo.repairSpecialInfolist.push(info);
            },
            handleRemove (index) {
                this.$Modal.confirm({
                    title: '<p ">确认对话框标题</p>',
                    content: '<p ">确定删除</p>',
                    onOk: () => {
                        this.allinfo.repairSpecialInfolist.splice(index, 1);
                    },
                    onCancel: () => {
                        this.$Message.info('点击了取消');
                    }
                });

            },
            add_aprlsc_showfuntion(){
                this.add_aprlsc_show = true,
                    this.aprlscentity = {
                        id: '',
                        licenseId: '',
                        projectName: '',
                        checkorUserId: '',
                        checkDate: '',
                    }
            },
            add_aprlpc_showfuntion(){
                this.add_aprlpc_show = true,
                    this.aprlpcentity = {
                        id: '',
                        licenseId: '',
                        projectName: '',
                        checkorUserId: '',
                        checkDate: '',
                    }
            },
            addaprlpc(name){
                if (this.aprlpcentity.checkDate != null&&this.aprlpcentity.checkDate != '') {
                    alert("a");
                    var d = new Date(this.aprlpcentity.checkDate);
                    this.aprlpcentity.checkDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                }
                ;
                if (this.aprlpcentity.expireDate != null&&this.aprlpcentity.expireDate != '') {
                    alert("b");
                    var d = new Date(this.aprlpcentity.expireDate);
                    this.aprlpcentity.expireDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                }
                ;
                this.mapaprlpcentity = {
                    id: this.aprlpcentity.id,
                    licenseId: this.aprlpcentity.licenseId,
                    checkorUserId: this.aprlpcentity.checkorUserId,
                    projectName: this.aprlpcentity.projectName,
                    specialCode: this.aprlpcentity.specialCode,
                    checkDate: this.aprlpcentity.checkDate,
                    expireDate: this.aprlpcentity.expireDate,
                    checkorman: this.aprlpcentity.checkorman
                }
                let self = this;
                /*self.$profile.getProfile().then((profile)=> {
                    self.mapaprlpcentity.checkorUserId = profile.user.userId;
                }, (error)=> {
                    console.log('error', error);
                });*/
                if (self.mapaprlpcentity.id == '' && self.arrayindex == '' && typeof (this.arrayindex) == 'string') {
                    alert(self.mapaprlpcentity.checkorman);
                    self.allinfo.aircraftpart.aprlpc.push(self.mapaprlpcentity);
                }else{
                    for(var key in self.mapaprlpcentity)
                    {
                        self.$set(self.allinfo.aircraftpart.aprlpc[self.arrayindex],key,self.mapaprlpcentity[key]);
                        //this.$set(this.aprlpcentity,key,this.allinfo.aircraftpart.aprlpc[index][key]);
                        //allinfo.aircraftpart.aprlpc
                    }

                }
                this.arrayindex = '';
                this.$Message.success("操作成功");
            },
            addaprlsc(){
                if (this.aprlscentity.checkDate != null&&this.aprlscentity.checkDate != '') {
                    var d = new Date(this.aprlscentity.checkDate);
                    this.aprlscentity.checkDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                }
                ;
                if (this.aprlscentity.expireDate != null&&this.aprlscentity.expireDate != '') {

                    var d = new Date(this.aprlscentity.expireDate);
                    this.aprlscentity.expireDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                }
                ;
                this.mapaprlpcentity = {
                    id: this.aprlscentity.id,
                    licenseId: this.aprlscentity.licenseId,
                    checkorUserId: this.aprlscentity.checkorUserId,
                    projectName: this.aprlscentity.projectName,
                    specialCode: this.aprlscentity.specialCode,
                    checkDate: this.aprlscentity.checkDate,
                    expireDate: this.aprlscentity.expireDate,
                    checkorman:  this.aprlscentity.checkorman
                }
                let self = this;
               /* self.$profile.getProfile().then((profile)=> {
                    self.mapaprlpcentity.checkorUserId = profile.user.userId;
                }, (error)=> {
                    console.log('error', error);
                });*/
                if (self.mapaprlpcentity.id == '' && self.arrayindex == '' && typeof (this.arrayindex) == 'string') {
                    self.allinfo.aircraftpart.aprlsc.push(self.mapaprlpcentity);
                }else{
                    for(var key in self.mapaprlpcentity)
                    {
                        self.$set(self.allinfo.aircraftpart.aprlsc[self.arrayindex],key,self.mapaprlpcentity[key]);
                        //this.$set(this.aprlpcentity,key,this.allinfo.aircraftpart.aprlpc[index][key]);
                        //allinfo.aircraftpart.aprlpc
                    }
                }
                this.arrayindex = '';
                this.$Message.success('操作成功');
            },
            handleRemoveaprlpc (index) {
                this.allinfo.aircraftpart.aprlpc.splice(index, 1);
            },
            handleRemoveaprlsc (index) {
                this.allinfo.aircraftpart.aprlsc.splice(index, 1);
            },
            updateCheckaprlpc(index)
            {
                this.add_aprlpc_show = true;
                this.arrayindex = index;
                //    this.aprlpcentity = this.allinfo.aircraftpart.aprlpc[index];//获取要修改的值,绑定到model,未赋值
                for(var key in this.allinfo.aircraftpart.aprlpc[index])
                {
                    this.$set(this.aprlpcentity,key,this.allinfo.aircraftpart.aprlpc[index][key]);
                }
                for(var key in this.allinfo.aircraftpart.aprlpc[index])
                {
                    this.$set(this.mapaprlpcentity,key,this.allinfo.aircraftpart.aprlpc[index][key]);
                }

//
//                this.mapaprlpcentity = {
//                    id: this.aprlpcentity.id,
//                    licenseId: this.aprlpcentity.licenseId,
//                    checkorUserId: this.aprlpcentity.checkorUserId,
//                    projectName: this.aprlpcentity.projectName,
//                    specialCode: this.aprlpcentity.specialCode,
//                    checkDate: this.aprlpcentity.checkDate,
//                }
            },
            updateCheckaprlsc(index)
            {
                this.add_aprlsc_show = true;
                this.arrayindex = index;
                for(var key in this.allinfo.aircraftpart.aprlsc[index])
                {
                    this.$set(this.aprlscentity,key,this.allinfo.aircraftpart.aprlsc[index][key]);
                }
                for(var key in this.allinfo.aircraftpart.aprlsc[index])
                {
                    this.$set(this.mapaprlpcentity,key,this.allinfo.aircraftpart.aprlsc[index][key]);
                }
//                this.mapaprlpcentity = {
//                    id: this.aprlscentity.id,
//                    licenseId: this.aprlscentity.licenseId,
//                    checkorUserId: this.aprlscentity.checkorUserId,
//                    specialCode: this.aprlscentity.specialCode,
//                    checkDate: this.aprlscentity.checkDate,
//                    expireDate: this.aprlscentity.expireDate,
//                }
            },
            cancelaprlsc(){
                this.allinfo.aircraftpart.aprlsc[this.arrayindex].specialCode = this.mapaprlpcentity.specialCode;//这里只可以用参数传值,不可以用地址传值,
                this.allinfo.aircraftpart.aprlsc[this.arrayindex].checkDate = this.mapaprlpcentity.checkDate;
                this.allinfo.aircraftpart.aprlsc[this.arrayindex].expireDate = this.mapaprlpcentity.expireDate;
                this.arrayindex = '';
                this.add_aprlsc_show = false;
            },
            cancelaprlpc(){
                this.allinfo.aircraftpart.aprlpc[this.arrayindex].projectName = this.mapaprlpcentity.projectName;
                this.allinfo.aircraftpart.aprlpc[this.arrayindex].checkDate = this.mapaprlpcentity.checkDate;
                this.arrayindex = '';
                this.add_aprlpc_show = false;
            },
        },
        watch: {
            getallinfo()
            {
                let self = this;
                self.$emit("listenairprot", self.allinfo);
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