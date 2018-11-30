<style>

    .ivu-form .ivu-form-item-label{
        color:#b3b3b3;
        font-size: 14px;

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
    <div style="width: 900px; margin-left: 70px;overflow: hidden; ">
        <span style="font-size: 18px;font-style: italic">系统管理-创建用户</span>
        <hr style="  height: 3px;  background-color: aliceblue;width: 898px">
        <Form :model="newUser" :label-width="100"  style="height: 550px ;width:900px; padding:10px auto; padding-top:35px;padding-bottom: 30px; background-color: #363636;">

            <FormItem label="用户姓名" style="width: 525px;    margin-left: 150px;">
                <Input v-model="newUser.userName" placeholder="请输入"></Input>
            </FormItem>

            <FormItem label="用户账号" style="width: 525px;    margin-left: 150px;">
                <Input v-model="newUser.userUserName" placeholder="请输入"></Input>
            </FormItem>

            <FormItem label="电子邮箱" style="width: 525px;    margin-left: 150px;">
                <Input v-model="newUser.email" placeholder="请输入"></Input>
            </FormItem>
            <FormItem label="企业名称" style="display: inline-block; width: 525px;margin-left: 150px;">

                <!--<Input v-model="newUser.companyName" placeholder="请输入" @blur="entquery(newUser.companyName)" ></Input>-->
                <Select :label="newUser.companyName"
                        v-model="newUser.companyId"
                        filterable
                        remote
                        :loading="loading1"
                        :remote-method="entquery"
                        ref="hehe"

                         style="width: 296px;height: 30px;float: left"><!--@on-change="changeCountairport(airport.airportId,index1)"-->

                    <Option v-for="(option, index) in optionsent" :value="option.value" :key="index">{{option.label}}</Option>
                </Select>
                <div  class="flybase-submit-div" style="float: left; width: 120px; ">
                    <a data-v-942377fa="" href="javascript:return false;"  @click="showloading" class="submit-btn" ref="hehe1" style="height: 32px;width: 100px;text-align: center;">创建企业</a>
                </div>
            </FormItem>

            <Modal
                    v-model="modal1"
                    title="新增企业"
                    width="500px"
                    @on-ok="ok"
                    @on-cancel="cancel">

                    <div style="height: 50px;">
                    企业名称 <Input v-model="newUser.companyName" placeholder="请输入" style="height: 32px;width:300px;text-align: center;"></Input>
                    </div>
                <div style="height: 50px;">
                    企业类型
                    <Select v-model="newUser.companyType" placeholder="请选择"  style="height: 32px;width:300px;text-align: center;">
                        <Option value="1">监管局</Option>
                        <Option value="2">管理局</Option>
                        <Option value="3">通航企业</Option>
                        <Option value="5">通航机场</Option>
                        <Option value="6">维修企业</Option>

                     </Select>
                </div>
            </Modal>



            <FormItem label="用户角色" style="width: 525px;    margin-left: 150px;">
               <!-- <Input v-model="legalperson.address" placeholder="请输入"></Input>-->
                <Select v-model="newUser.userRole" placeholder="请选择" id="roles" ref="roles" style="">
                    <!--<Option value="企业管理员">企业管理员</Option>-->
                    <!--<Option value="管理局管理员">管理局管理员</Option>-->
                    <Option v-for="role in roles" :value="role.rolename" >{{role.alias}} </Option>
                </Select>
            </FormItem>


            <div  class="flybase-submit-div" style=" text-align: center; padding-bottom: 10px; padding-top: 10px; width: 900px;">
                <a data-v-942377fa="" href="javascript:;" @click="handleSubmit()" class="submit-btn">保存</a>
                <a data-v-942377fa="" href="javascript:;" @click="clear()" class="submit-btn">重置</a>
            </div>
        </Form>
    </div>
</template>
<script>
    export default {

        data () {
            return {
                modal1: false,
                newUser: {
                    account:'',
                    userName:'',
                    userUserName:'',
                    email:'',
                    companyId:'',
                    gongsiId:'',
                    companyName:'',
                    companyType:'',
                    userRole:''
                },
                roles:[
                    {
                        rolename:'',
                        alias:''
                    }

                ],
                companyList:[],
                optionsent:[]


            }
        },
        created: function () {
           this.init();
           this.listall();
        },
        methods: {

            showloading:function (){
                this.modal1=true;
            },
            ok:function(){
                let self=this;

                if(self.newUser.companyName=='' ||self.newUser.companyType==''){
                    alert('有必要信息未填');
                    return;
                }
                self.$http.jsonPost('/app/api/org/createOrg', self.newUser).then(function (response) {
                   // alert(response.data.data);
                    self.newUser.gongsiId=response.data.data;
                    self.listall();
                    self.newUser.companyName='';

                }).catch(function (error) {
                    console.log(error);
                });
                alert("创建企业成功");
                self.newUser.companyName=='';
                self.newUser.companyType=='';
            },
            init: function(){

                let self = this;
                //console.log(self.$profile.getProfile());
//                let account=self.$profile.getProfile().user.account;
                    self.$profile.getProfile().then((profile) => {
                    console.log("aircraftlist get profile", profile);

//                console.log(account)
               // let account ='admin';

                    let account=profile.user.account;
                    self.$http.httpGet('/app/api/user/findUserRoleByAccount?account='+account).then(function (response) {
                        self.roles=response.data.data;
                        //alert(response.data.data);
                        console.log(response.data.data);
                    }).catch(function (error) {

                        console.log(error);
                    });
                    let orgname=profile.org.name;
                    if(orgname.length!=0){
                        self.newUser.companyName=orgname;
//                        self.$refs.hehe.disabled=true;
//                        self.$refs.hehe1.style.display="none";
                        self.$refs.hehe1.href ="javascript:return false;";
                        self.$refs.hehe1.onclick="return false;";
                    }
                });
            },
            handleSubmit: function () {

                let self = this;
                //获取用户名
                self.$profile.getProfile().then((profile) => {
                    console.log("aircraftlist get profile", profile);
                    let account = profile.user.account;
                    self.newUser.account=account;
                    self.$http.jsonPost('/app/api/user/saveUser', self.newUser).then(function (response) {
                        console.log(response);
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
                alert("添加成功");
            },
            clear:function(){

                let self=this;
//                let hehe=self.$profile.getProfile().user.id;

                self.newUser.userName='';
                self.newUser.userUserName='';
                self.newUser.email='';
                self.newUser.companyName='';
                self.newUser.companyType='';
                self.newUser.userRole='';
            },
            listall:function () {
                //获取所有的企业
                let self=this;
                self.$http.httpGet('/app/api/org')
                    .then(function (response) {
                    self.companyList=response.data;

                }).catch(function (error) {
                    console.log(error);
                });
            },
            entquery:function (query) {

                if (query !== '') {
                    setTimeout(() => {
                        const list = this.companyList.data.map(item => {
                            return {
                                value: item.id,
                                label: item.name
                            };
                        });
                        this.optionsent = list.filter(item => item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);
                    }, 200);
                } else {

                }
            }
        }
    }
</script>