<template>
    <div  >
        <Form :label-width="205"  :model="allinfo" ref="aircraftrepairref">

            <div style="margin-bottom: 8px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <Button type="primary" @click="handleAdd" icon="plus" style="width: 98px;height: 32px;margin:24px 20px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>
            </div>
            <div  style="width: 1159px;border:1px solid rgba(1,107,217,.5);background-color:white; padding-bottom: 10px;margin-right: 20px;float: right;margin-bottom: 10px" :key="aprindex" v-for="(apritem, aprindex) in secondSpecialInfolist">
                <div>
                    <h2 style="padding: 7px;">航空器维修专业执照信息{{aprindex+1}}   <Icon type="minus-circled" size="20" @click.native="handleRemove(aprindex)" style="float: right;"></Icon></h2>
                </div>
                <div style="margin-left: -63px">
                    <FormItem label="专业名称"  :key="aprindex" style="display: inline-block;width: 550px">
                        <Select v-model="apritem.aprName" placeholder="">
                            <Option  :value="item.key" :key="item.key" v-for="item in aircraftpartcodeNamelist">{{item.value}}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="专业代码"  style="display: inline-block;width: 550px">
                        {{apritem.aprName}}
                    </FormItem>
                </div >
                <div style="margin-left: -63px">
                    <FormItem label="签发日期" class="bottom5" style="height: 32px;display: inline-block" :key="aprindex">
                        <DatePicker v-model="apritem.aprissuerTime" type="date" placeholder="选择颁发时间" style="width: 345px"></DatePicker>
                    </FormItem>
                    <FormItem label="有效截止日期" class="bottom5" style="height: 32px;display: inline-block" :key="aprindex">
                        <DatePicker v-model="apritem.aprexpireTime" style="width: 345px" type="date" placeholder="选择到期时间"></DatePicker>
                    </FormItem>
                </div>
                <!--
                <Row style="height: 45px">
                    <Col :md="12" style="height: 32px">
                    <FormItem label="有效性" class="bottom5" style="height: 32px" :key="aprindex">
                        <Select v-model="apritem.apravailable" placeholder="">
                            <Option value="0">是</Option>
                            <Option value="1">否</Option>
                        </Select>
                    </FormItem>
                    </Col>
                </Row>
                -->
            </div>
            <!--<Row style="height: 32px">
                <Col span="24" style="text-align: center;">
                <Button type="primary" @click="handleAdd" icon="plus"
                        style="width:150px;margin-right:10px;" size="small">增  加</Button>
                </Col>
            </Row>-->
            <div class="bottom5" style="margin-top:10px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <Button type="primary" @click="add_aprlpc_showfuntion" icon="plus" style="width: 98px;height: 32px;margin:24px 20px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>
                <div class="bottom5" style="margin-top:5px;width:98.5%;padding-left: 20px">
                    <Table border :columns="arlmccolumns" :data="allinfo.aircraftrepair.arlmc" ></Table>
                </div>
                <span style="width:100%;display:inline-block;text-align:right;">
                    <Button type="primary" @click="add_aprlsc_showfuntion" icon="plus" style="width: 98px;height: 32px;margin:24px 20px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>

                <div class="bottom5" style="margin-top:5px;width:98.5%;padding-left: 20px">
                    <Table border :columns="aprlsccolumns" :data="allinfo.aircraftrepair.arlsc"></Table>
                </div>
            </div>
        </Form>
        <Modal v-model="add_aprlpc_show" width="400" title="航空器维修执照机型签发" :closable="false" @on-ok="addarlmc"
               @on-cancel="cancelaprlpc"
               :styles="{top: '20px'}">
            <Form :label-width="100">
                <div >
                    <Row>
                        <Col :md="23">
                            <FormItem label="航空器型号" class="bottom5">
                                <Input v-model="arlmcentity.aircraftModel" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="航空器类别" class="bottom5" style="height: 37px">
                                <Select v-model="arlmcentity.aircraftClass" placeholder="">
                                    <Option value="固定翼">固定翼</Option>
                                    <Option value="旋翼">旋翼</Option>
                                    <Option value="滑翔机">滑翔机</Option>
                                    <Option value="其他">其他</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="签署机型日期" class="bottom5">
                                <DatePicker v-model="arlmcentity.checkDate" type="date" placeholder="选择日期" style="width: 252px"></DatePicker>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="签署人" class="bottom5">
                                <!--<DatePicker v-model="arlmcentity.checkorUserId" type="date" placeholder="选择日期"></DatePicker>-->
                                <Input v-model="arlmcentity.checkorman" placeholder="请输入"></Input>
                            </FormItem>
                        </Col>
                    </Row>
                </div>
            </Form>
        </Modal>
        <Modal v-model="add_aprlsc_show" width="450" title="航空器维修执照专业续签" :closable="false" @on-ok="addarlsc"
               @on-cancel="cancelaprlsc"
               :styles="{top: '20px'}">
            <Form :label-width="160">
                <div >
                    <Row>
                        <Col :md="23">
                            <FormItem label="执照续签专业代码" class="bottom5">
                                <Select v-model="arlscentity.specialCode" placeholder="">
                                    <Option  :value="item.key" :key="item.key" v-for="item in aircraftpartcodeNamelist">{{item.key}}</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="执照续签签发时间" class="bottom5">
                                <DatePicker v-model="arlscentity.checkDate" type="date" placeholder="选择日期" style="width: 246px"></DatePicker>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="执照续签有效截止日期" class="bottom5">
                                <DatePicker v-model="arlscentity.expireDate" type="date" placeholder="选择日期" style="width: 246px"></DatePicker>
                            </FormItem>
                        </Col>
                        <Col :md="23">
                            <FormItem label="签署人" class="bottom5">
                                <!--<DatePicker v-model="arlscentity.checkor" type="date" placeholder="选择日期"></DatePicker>-->
                                <Input v-model="arlscentity.checkorman" placeholder="请输入"></Input>
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
                aircraftpartcodeNamelist:aircraftdata.aircraftcodeNamelist,
                initlicenseId:'',
                initcheckorUserId:'',
                arrayindex:'',//用于保存修改数组中的下标,从点击修改函数开始赋值(arrayindex=index),到点击取消或者保存按钮结束(arrayindex=''),
                arlmcentity:{//与model绑定
                    id:'',
                    licenseId:'',
                    aircraftClass:'',
                    aircraftModel:'',
                    checkorUserId:'',
                    checkDate:'',
                    checkorman:''

                },
                arlscentity:{//与model绑定
                    id:'',
                    licenseId:'',
                    specialCode:'',
                    checkorUserId:'',
                    checkDate:'',
                    expireDate:'',
                    checkorman:''

                },
                mapaprlpcentity:{},//作为model与aircraftpart的中间变量,保存到数组中
                arlmccolumns: [

                    {
                        title: '航空器型号',
                        width: 244,
                        key: 'aircraftModel',
                        align: 'center'
                    },
                    {
                        title: '航空器类别',
                        width: 250,
                        key: 'aircraftClass',
                        align: 'center'
                    },
                    {
                        title: '签署人',
                        width: 300,
                        key: 'checkorman',
                        align: 'center'
                    },
                    {
                        title: '签署机型日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 243
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
                                            this.handleRemoveaprlpc(params.index);
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
                        key: 'checkorman',
                        align: 'center',
                        width: 300
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
                                            this.updateCheckaprlsc(params.index)
                                        }
                                    }
                                }),
                                /* h('Button', {
                                     props: {
                                         type: 'error',
                                         size: 'small'
                                     },
                                     on: {
                                         click: () => {
                                             if(params.row.id!='') {
                                                 this.$Modal.confirm({
                                                     title: '<p >确认对话框标题</p>',
                                                     content: '<p >确定删除</p>',
                                                     onOk: () => {
                                                         let self = this;//删除

                                                         self.$http.httpDelete("/eim/api/aircraftrlsc/" + params.row.id, {}).then(function (response) {
                                                             self.handleRemoveaprlsc(params.index);
                                                         }).catch(function (error) {
                                                             console.log(error);
                                                         });
                                                     },
                                                     onCancel: () => {
                                                         this.$Message.info('点击了取消');
                                                     }
                                                 });
                                             }else{
                                                 this.handleRemoveaprlsc(params.index);
                                             }
                                         }
                                     }
                                 }, '删除')*/
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
                                            this.handleRemoveaprlsc(params.index);
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],
                add_aprlpc_show:false,
                add_aprlsc_show:false,
                secondSpecialInfolist:[{
                    aprName:'',
                    aprCode:'',
                    aprissuerTime:'',
                    aprexpireTime:'',
                    apravailable:'',
                }],
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
                }
            }
        },
        props:['personQulificationInfo','employee'],
        methods: {
            handlesubmit()
            {
                let self = this;
                console.log(self.$refs.aircraftrepairref);


                //      if(data.primarySpecialName!=''&&data.primarySpecialCode!=''&& data.primarySpecialName1!=''&&data.primarySpecialCode1!=''&&data.primarySpecialIssueDate!=''&&data.primarySpecialExpireDate!=''){
                //       self.$refs.aircraftrepairref.validate((valid)=>{
                //           if(valid){
                self.allinfo.personQulificationInfo=self.personQulificationInfo;
               /* self.$profile.getProfile().then((profile)=> {
                    self.allinfo.aircraftrepair.issueUserId=profile.user.userId;//保存当前登录的用户ID
                }, (error)=> {
                    console.log('error', error);
                });*/

                for(var i=0;i<self.secondSpecialInfolist.length;i++)
                {
                    var info = self.secondSpecialInfolist[i];
                    if (info.aprissuerTime != null&&info.aprexpireTime != '') {

                        var d = new Date(info.aprissuerTime );
                        self.secondSpecialInfolist[i].aprissuerTime  = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                    };
                    if (info.aprexpireTime != null&&info.aprexpireTime != '') {

                        var d = new Date(info.aprexpireTime );
                        self.secondSpecialInfolist[i].aprexpireTime  = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                    };
                }

                self.allinfo.aircraftrepair.secondSpecialInfo = JSON.stringify(self.secondSpecialInfolist);
                console.log("self.allinfo.aircraftrepair.secondSpecialInfo ==",self.allinfo.aircraftrepair.secondSpecialInfo);

                self.$http.httpPost("/eim/api/aircraftrl",self.allinfo).then(function (response) {
//                self.$Message.success("提交成功");
                    alert("执照信息保存成功！")
                    //self.$router.push({path: '/pilot_info', query: {qualificationisshow: true}});
                    self.$router.push({path: '/name2',query:{employeeId:this.personQulificationInfo.employeeId,tab:'name2'}});

                }).catch(function (error) {
                    console.log(error);
                    self.$router.push({path: '/name2',query:{employeeId:this.personQulificationInfo.employeeId,tab:'name2'}});
                    self.$Message.error("提交失败");
                });
            },
            initinfo(varlicenseId) {
                let self = this;
                self.initlicenseId=varlicenseId;
                //self.initcheckorUserId=profile.user.userId;

                self.arlmcentity.licenseId=self.initlicenseId;
                self.arlmcentity.checkorUserId=self.initcheckorUserId;
                self.arlscentity.licenseId=self.initlicenseId;
                self.arlscentity.checkorUserId=self.initcheckorUserId;

                self.$http.httpGet("/eim/api/aircraftrl/"+varlicenseId,{}).then(function (response) {
                    let datainfo = response.data;
                    if(typeof(datainfo)!='undefined'&&datainfo!=''&&datainfo!=null) {

                        for(var key in datainfo)
                        {
                            self.$set(self.allinfo.aircraftrepair,key,datainfo[key]);
                        }

                        if (self.allinfo.aircraftrepair.secondSpecialInfo != null) {
                            self.secondSpecialInfolist = eval(self.allinfo.aircraftrepair.secondSpecialInfo);
                        }
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
            },
            handleAdd () {
                let info={
                    id:this.initlicenseId,
                    issueUserId:'',
                    aprName:'',
                    aprCode:'',
                    aprissuerTime:'',
                    aprexpireTime:'',
                    apravailable:'',
                };
                console.log("info.aprexpireTime==",info.aprexpireTime,typeof (info.aprexpireTime));
//                if (info.aprissuerTime != null&&info.aprexpireTime != '') {
//
//                    var d = new Date(info.aprissuerTime );
//                    info.aprissuerTime  = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
//                };
//                if (info.aprexpireTime != null&&info.aprexpireTime != '') {
//
//                    var d = new Date(info.aprexpireTime );
//                    info.aprexpireTime  = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
//                };
                console.log("info.aprexpireTime==",info.aprexpireTime,typeof (info.aprexpireTime));
                this.secondSpecialInfolist.push(info);
            },
            handleRemove (index) {
                this.$Modal.confirm({
                    title: '<p >确认对话框标题</p>',
                    content: '<p >确定删除</p>',
                    onOk: () => {
                        this.secondSpecialInfolist.splice(index, 1);
                    },
                    onCancel: () => {
                        this.$Message.info('点击了取消');
                    }
                });

            },
            add_aprlsc_showfuntion(){
                this.add_aprlsc_show=true,
                    this.arlscentity={  id:'',
                        licenseId:'',
                        specialCode:'',
                        checkorUserId:'',
                        checkDate:'',
                        expireDate:'',}
            },
            add_aprlpc_showfuntion(){
                this.add_aprlpc_show=true,
                    this.arlmcentity={
                        id:'',
                        licenseId:'',
                        aircraftClass:'',
                        aircraftModel:'',
                        checkorUserId:'',
                        checkDate:'',
                    }
            },
            addarlmc(){
                let self = this;
               /* self.$profile.getProfile().then((profile)=>{
                    self.mapaprlpcentity.checkorUserId=profile.user.userId;
                }, (error)=> {
                    console.log('error', error);
                });*/
                if (self.arlmcentity.checkDate != null&&self.arlmcentity.checkDate != '') {
                        alert("a");
                    var d = new Date(self.arlmcentity.checkDate );
                    self.arlmcentity.checkDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                };
                if (self.arlmcentity.expireDate != null&&self.arlmcentity.expireDate != '') {
                    alert("b");
                    var d = new Date(self.arlmcentity.expireDate );
                    self.arlmcentity.expireDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                };


                self.mapaprlpcentity={
                    id:self.arlmcentity.id,
                    licenseId:self.arlmcentity.licenseId,
                    aircraftModel:self.arlmcentity.aircraftModel,
                    aircraftClass:self.arlmcentity.aircraftClass,
                    checkDate:self.arlmcentity.checkDate,
                    expireDate:self.arlmcentity.expireDate,
                    checkorman:self.arlmcentity.checkorman
                }
                if(self.mapaprlpcentity.projectName!=''&& self.mapaprlpcentity.id==''&& self.arrayindex == ''&&typeof (this.arrayindex)=='string') {
                   alert("c");
                    self.allinfo.aircraftrepair.arlmc.push(self.mapaprlpcentity);
                }else {
                    for(var key in self.mapaprlpcentity)
                    {
                        alert('d');
                        self.$set(self.allinfo.aircraftrepair.arlmc[self.arrayindex],key,self.mapaprlpcentity[key]);
                    }
                }
                this.arrayindex='';
            },
            addarlsc(){
                let self = this;
                if (self.arlscentity.checkDate != null&&self.arlscentity.checkDate != '') {
                    var d = new Date(self.arlscentity.checkDate );
                    self.arlscentity.checkDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                };
                if (self.arlscentity.expireDate != null&&self.arlscentity.expireDate != '') {

                    var d = new Date(self.arlscentity.expireDate );
                    self.arlscentity.expireDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                };
                self.mapaprlpcentity={
                    id:self.arlscentity.id,
                    licenseId:self.arlscentity.licenseId,
                    checkorUserId:self.arlscentity.checkorUserId,
                    specialCode:self.arlscentity.specialCode,
                    checkDate:self.arlscentity.checkDate,
                    expireDate:self.arlscentity.expireDate,
                    checkorman:self.arlscentity.checkorman
                }
               /* self.$profile.getProfile().then((profile)=>{
                    self.mapaprlpcentity.checkorUserId=profile.user.userId;
                }, (error)=> {
                    console.log('error', error);
                });*/
                if(self.mapaprlpcentity.id==''&& self.arrayindex == ''&&typeof (this.arrayindex)=='string') {
                    self.allinfo.aircraftrepair.arlsc.push(self.mapaprlpcentity);
                }else {

                    for(var key in self.mapaprlpcentity)
                    {
                        self.$set(self.allinfo.aircraftrepair.arlsc[self.arrayindex],key,self.mapaprlpcentity[key]);
                    }

                }
                this.arrayindex='';
            },
            handleRemoveaprlpc (index) {
                this.allinfo.aircraftrepair.arlmc.splice(index, 1);
            },
            handleRemoveaprlsc (index) {
                this.allinfo.aircraftrepair.arlsc.splice(index, 1);
            },
            updateCheckaprlpc(index)
            {
                this.add_aprlpc_show=true;
                this.arrayindex=index;
                for(var key in this.allinfo.aircraftrepair.arlmc[index])
                {
                    this.$set(this.arlmcentity,key,this.allinfo.aircraftrepair.arlmc[index][key]);
                }
                let self=this;
                for(var key in this.allinfo.aircraftrepair.arlmc[index])
                {
                    self.$set(self.mapaprlpcentity,key,self.allinfo.aircraftrepair.arlmc[index][key]);
                }
            },
            updateCheckaprlsc(index)
            {
                this.add_aprlsc_show=true;
                this.arrayindex=index;
                let self=this;
                for(var key in this.allinfo.aircraftrepair.arlsc[index])
                {
                    this.$set(self.arlscentity,key,this.allinfo.aircraftrepair.arlsc[index][key]);
                    console.log(this.allinfo.aircraftrepair.arlsc[index][key]);
                }

                for(var key in this.allinfo.aircraftrepair.arlsc[index])
                {
                    this.$set(self.mapaprlpcentity,key,this.allinfo.aircraftrepair.arlsc[index][key]);
                    console.log(this.allinfo.aircraftrepair.arlsc[index][key]);
                }

            },
            cancelaprlpc()
            {
                this.allinfo.aircraftrepair.arlmc[this.arrayindex].aircraftModel=this.mapaprlpcentity.aircraftModel;
                this.allinfo.aircraftrepair.arlmc[this.arrayindex].aircraftClass=this.mapaprlpcentity.aircraftClass;
                this.allinfo.aircraftrepair.arlmc[this.arrayindex].checkDate=this.mapaprlpcentity.checkDate;
                this.arrayindex='';
                this.add_aprlpc_show=false;
            },
            cancelaprlsc()
            {
                this.allinfo.aircraftrepair.arlsc[this.arrayindex].specialCode=this.mapaprlpcentity.specialCode;
                this.allinfo.aircraftrepair.arlsc[this.arrayindex].checkDate=this.mapaprlpcentity.checkDate;
                this.allinfo.aircraftrepair.arlsc[this.arrayindex].expireDate=this.mapaprlpcentity.expireDate;
                this.arrayindex='';
                this.add_aprlsc_show=false;
            }
        },
        watch:{
            getallinfo()
            {
                let self=this;
                self.$emit("listenairprot",self.allinfo);
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