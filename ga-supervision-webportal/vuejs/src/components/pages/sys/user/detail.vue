<style>

    .fade.in {
        opacity: 1;
    }
    .submit-btn-user{
        color: rgb(255,255,255);
        background-color: rgba(37,128,221,1);
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
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
    <div style="width: 965px; margin-left: 12px;">
        <div style="background: #2e82dc;height: 36px;padding-top: 6px;margin: 22px 0px 3px 0px;padding: 6px 0 0 0;">
            <img src="../../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
            <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">系统管理-用户详情</span>
        </div>
        <div style="border: solid 1px rgba(1, 107, 217, 0.5);background: rgba(193, 220, 248, 0.5); position: absolute;bottom: 0px;top: 76px;width: 965px;right: 26px;">
        <Form :model="newUser" :label-width="120"  style="height: 550px ;width:900px; padding:10px auto; padding-top:35px;padding-bottom: 30px;padding-left: 65px;" :rules="ruleInline" ref="formCustom">
            <Row>
                <Col span="10">
            <FormItem label="帐号" style="width: 350px;" prop="account" >
                <Input v-model="newUser.account" placeholder="请输入" :readonly="editname"></Input>
            </FormItem>
                </Col>
                <Col span="9">
            <FormItem label="用户姓名" style="width: 350px;" prop="fullname">
                <Input v-model="newUser.fullname" placeholder="请输入"  ></Input>
            </FormItem>
                </Col>
                </Row>
            <Row>
                <Col span="10">
            <FormItem label="邮箱地址" style="width: 350px;" prop="email">
                <Input v-model="newUser.email" placeholder="请输入"></Input>
            </FormItem>
                </Col>
                <Col span="9">
            <FormItem label="手机" style="width: 350px;" prop="mobile">
                <Input v-model="newUser.mobile" placeholder="请输入"></Input>
            </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="10">
            <FormItem label="用户角色" style="width: 350px;" prop="roleId">
                <Select v-model.number="newUser.roleId" :placeholder="placeholderdata">
                    <Option v-for="role in roless" :value="role.roleId" >{{role.roleName}} </Option>
                </Select>
            </FormItem>
                </Col>
                <Col span="9">
            <FormItem label="所属部门" style="width: 350px;" prop="dept">
                <div style="position: relative;cursor: pointer;" @click="adddept" >
                <Input v-model="newUser.dept" placeholder="请选择"  readonly="true" ></Input>
                <Icon type="ios-search-strong" style="position: absolute;top: 2px;font-size: 30px;right: 8px;color: #363636;"></Icon>
                </div>
            </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="10">
            <FormItem label="岗位类型" style="width: 350px;float: left;" prop="jobtype">
                <Select v-model="newUser.jobtype" placeholder="请选择岗位类型" @on-change="techgradechange()"  >
                    <Option  v-for="emp in employeetype" :value="emp.employeeid">{{emp.employeename}}</Option>
                </Select>
            </FormItem>
                </Col>
                <Col span="9">
            <FormItem label="技术级别" style="width: 350px;float: left;" prop="techgrade">
                <Select v-model="newUser.techgrade" placeholder="请选择技术级别"  >
                    <Option  v-for="empteach in techgradearr" :value="empteach.techgrade">{{empteach.techgrade}}</Option>
                </Select>
            </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="10">
            <FormItem label="行政职务" style="width: 350px;float: left;" prop="jobId">
                <Select v-model.number="newUser.jobId" placeholder="请选择用户职务"  @on-change="jobchange()">
                    <Option v-for="job in jobs" :value="job.jobid">{{job.jobname}} </Option>
                </Select>
            </FormItem>
                </Col>
                <Col span="9">
            <FormItem label="技术职务" style="width: 350px;float: left;" prop="Technicalname">
                <Select v-model="newUser.Technicalname" placeholder="请选择用户类型"  >
                    <Option  v-for="Technical in Technicalpost" :value="Technical.Technicalname">{{Technical.Technicalid}}</Option>
                </Select>
            </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="9">
                <FormItem label="员工状态" style="width: 350px;float: left;" prop="userStatus">
                    <Select v-model="newUser.userStatus" placeholder="请选择用户类型"  >
                        <Option  v-for="status in userstatus" :value="status.userStatus">{{status.statusname}}</Option>
                    </Select>
                </FormItem>
                </Col>
            </Row>
            <div  class="flybase-submit-user" style=" text-align: center; padding-bottom: 10px; padding-top: 10px; width: 830px;float: left;">
                <a data-v-942377fa="" href="javascript:;" @click="handleSubmit" class="submit-btn-user">保存</a>
                <a data-v-942377fa="" href="javascript:;" @click="clear" class="submit-btn-user">返回</a>
            </div>
        </Form>

        <div class="modal-backdrop fade in" v-show="isdiv" style="padding-left: 155px;">
            <div class="modal-content " style="margin-top: 90px;border-radius: 0px;width: 265px;min-height: 325px;border: 1px solid rgb(218, 233, 249);color: #353434d9;">
                <div  style="font-size: 15px; border-bottom: solid 1px #989da1;\padding-bottom: 5px;padding-top: 5px;padding-left: 13px;">
                    选择部门
                    <div style="float: right; margin-right: 15px;" @click="userdeptclose">
                        <Icon type="close"></Icon>
                    </div>
                </div>
                <div style="padding-left: 12px;overflow: hidden">
                <treedept ref="depttreeid"></treedept>
                </div>
                <div style="border-bottom: solid 1px #989da1;position: absolute;bottom: 35px;width: 100%;"></div>
                <div style="position:absolute;bottom:5px;right: 8px;" >
                    <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" @click="savedeptshow" ><span>确定</span></button>
                    <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" @click="userdeptclose" ><span>关闭</span></button>
                </div>
            </div>
        </div>

        <Modal  v-model="modal2"
                title="友情提示"
                @on-ok="ok"
                @on-cancel="cancel">
            <p style=" padding-bottom: 12px;">该用户已在其他公司创建，是否极续创建?</p>
        </Modal>
        </div>
    </div>
</template>
<script>
    import treedept from './treedata.vue';
    export default {

        data () {
            return {
                techgradearr:[],
                companytechgrade:[],
                employeetype:[
                    { employeename:'飞行人员',
                      employeeid:'飞行人员'
                    },
                    { employeename:'机务人员',
                      employeeid:'机务人员'
                    },
                    { employeename:'航务人员',
                      employeeid:'航务人员'
                    },
                    { employeename:'其他人员',
                      employeeid:'其他人员'
                    },
                ],
                userstatus:[
                    {   statusname:'全职',
                        userStatus:'0'
                    },
                    {   statusname:'兼职',
                        userStatus:'1'
                    },
                    {   statusname:'离职',
                        userStatus:'2'
                    }
                     ],
                Technicalpost:[
                    { Technicalname:'',
                        Technicalid:''
                    },
                    { Technicalname:'主任教员',
                        Technicalid:'主任教员'
                    },
                    { Technicalname:'地面主任教员',
                        Technicalid:'地面主任教员'
                    },
                    { Technicalname:'助理主任教员',
                        Technicalid:'助理主任教员'
                    },
                    { Technicalname:'（企业）检查员',
                        Technicalid:'（企业）检查员'
                    },
                ],
                modal1: false,
                modal2: false,
                isdiv:false,
                newUser: {},
                roless:[],
                jobs:[],
                companyList:[],
                optionsent:[],
                editname:false,
                ruleInline: {
                    account: [
                        { required: true, message: '帐号不能为空', trigger: 'blur' },
                        { pattern:/^[a-zA-Z0-9_-]{4,16}$/,message: '只能输入字母和数字下划线随意的组合',trigger:'blur'}
                    ],
                    fullname:[
                        { required: true, message: '用户名不能为空', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '邮箱不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                    ],
                    jobId:[
                        { required: true,type:'number', message: '职务不能为空', trigger: 'change' }
                    ],
                    dept:[
                        { required: true,message: '部门不能为空', trigger: 'change'}
                    ],
                    jobtype:[
                        { required: true,message: '用户类型不能为空', trigger: 'change'}
                    ],
                    userStatus:[
                        { required: true,message: '员工状态不能为空', trigger: 'change'}
                    ],
                    roleId:[
                        { required: true,type:'number', message: '角色不能为空', trigger: 'change' }
                    ],
                    mobile:[
                        { required: true, message: '手机不能为空', trigger: 'blur' },
                        { pattern:/^\d{11}$/,message: '请输入11位手机号',trigger:'blur'}
                    ]
                },
                placeholderdata:"请选择角色"

            }
        },
        created: function (){
            this.init();
            this.rolelist();
            this.joblist();
            this.initCompanyTechGrade();
            this.findEmployeeByuserid();
        }, components:{
            treedept
        },
        methods: {
            init: function(){
                if(this.$route.query.userfalg==2){
                    this.userinfo(this.$route.query.user);
                    this.editname=true;
                }else{
                    this.editname=false;
                }
            },
            handleSubmit: function () {
             let self=this;
             this.$refs['formCustom'].validate((valid) => {
             if (valid) {
                 if(self.newUser.userStatus==='1'&&!self.newUser.userId){
                     self.modal2=true;
                 }else{
                     self.ok();
                 }
                } else {
                    this.$Message.error('表单验证失败!');
                }
              })
            },
            clear:function(){
                history.go(-1);
            },adddept:function () {
                this.isdiv=true;
            },userdeptclose:function () {
                this.isdiv=false;
            },savedeptshow:function () {
                let self=this;
                self.$profile.getProfile().then((profile) => {
                    if(self.$refs.depttreeid.titleid===profile.org.orgId){
                        self.isdiv=false;
                    }else{
                        self.newUser.orgId=self.$refs.depttreeid.titleid;
                        self.newUser.dept=self.$refs.depttreeid.deptname;
                        self.isdiv=false;
                    }
                });
            },rolelist:function () {
                    let self=this;
                    self.$http.httpGet('/app/api/sysrole/search/list',{
                        type:1
                    }).then(function (response) {
                        self.roless=response.data.data;
                    }).catch(function (error) {
                        console.log(error);
                    });
            },
            joblist:function () {
                let self=this;
                self.$http.httpGet('/app/api/sysuser/search/joblist',{
                }).then(function (response) {
                    let jobds=response.data.data;
                    for(var i=0;i<jobds.length;i++){
                        if(jobds[i].grade===1){
                            self.jobs.push(jobds[i]);
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            userinfo:function (user){
                let self=this;
                self.$http.httpGet('/app/api/sysuser/search/byuserid',{
                    userid:user.userId
                }).then(function (response) {
                    self.newUser=response.data.data;
                    self.newUser.roleId=response.data.data.rolesId;
                    self.newUser.dept=user.orgName;
                    self.newUser.orgId=user.orgId;
                    self.newUser.authorities="";
                    self.$http.httpGet('/app/api/employee/search/byuserid',{
                        userid:user.userId
                    }).then(function (response) {
                        self.$set(self.newUser, 'jobtype', response.data.data.jobTitle);
                        self.$set(self.newUser, 'techgrade', response.data.data.techgrade);
                        self.$set(self.newUser, 'Technicalname', response.data.data.techpos);


                    }).catch(function (error) {
                        console.log(error);
                    });
                    console.log(user);
                }).catch(function (error) {
                    console.log(error);
                });
            },
            jobchange:function () {
                for(var i=0;i<this.jobs.length;i++){
                if(this.newUser.jobId===this.jobs[i].jobid){
                    this.newUser.jobname=this.jobs[i].jobname;
                  }
                }
            },
            initCompanyTechGrade:function (){
                let self=this;
                self.$http.httpGet('app/api/companytechgrade',{
                }).then(function (response) {
                    self.companytechgrade=response.data;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            techgradechange(){
                this.techgradearr=[];
                if(this.newUser.jobtype=="飞行人员"){
                    for(var i=0;i<this.companytechgrade.length;i++){
                     if(this.companytechgrade[i].posclass=="飞行人员"){
                         this.techgradearr.push(this.companytechgrade[i])

                     }
                    }
                }
                else if(this.newUser.jobtype=="机务人员"){
                    for(var i=0;i<this.companytechgrade.length;i++){
                        if(this.companytechgrade[i].posclass=="机务人员"){
                            this.techgradearr.push(this.companytechgrade[i])

                        }
                    }

                }
                else if(this.newUser.jobtype=="航务人员"){
                    for(var i=0;i<this.companytechgrade.length;i++){
                        if(this.companytechgrade[i].posclass=="航务人员"){
                            this.techgradearr.push(this.companytechgrade[i])
                        }
                    }

                }
            },roleclick:function (obj) {

            },ok:function () {
                let self=this;
                //获取用户名
                self.$profile.getProfile().then((profile) => {
                    self.newUser.gaorgId=profile.buz.companyId;
                    self.$http.jsonPost('/app/api/sysuser', self.newUser
                    ).then(function (response) {
                        if(response.data.resultCode===450){
                            alert("用户帐号已经存在");
                        }else if(response.data.resultCode===410){
                            alert("该用户帐号已经注册，请更换帐号");
                        }else if(response.data.resultCode===411){
                            alert("人员信息更新失败");
                        }else if(response.data.resultCode===412){
                            alert("当前登陆用户无权限编辑用户信息");
                        }else if(response.data.resultCode===413){
                            alert("用户邮箱未填写");
                        }else{
                            alert("用户保存成功");
                        }
                        history.go(-1);
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },cancel:function () {
                
            }

        }
    }
</script>
