<style>


    .submit-btn {
        font-size: 14px;
        margin: 0px 60px;
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


    .flybase-submit-dept a:hover{
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
    .submit-btn-dept{
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
</style>

<template>

    <div style="width: 965px; margin-left: 12px;">
        <div style="background: #2e82dc;height: 36px;padding-top: 6px;margin: 22px 0px 3px 0px;padding: 6px 0 0 0;">
            <img src="../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
            <span style="font-size: 18px;font-style: italic">系统管理-创建组织</span>
        </div>
        <div style="border: solid 1px rgba(1, 107, 217, 0.5);background: rgba(193, 220, 248, 0.5); position: absolute;bottom: 0px;top: 76px;width: 965px;right: 26px;">
        <Form :model="org" :label-width="259" ref="orgVali" style="padding-top:28px;">

            <FormItem label="上级组织" style="width: 769px;">
                <Input v-model="org.orgSupName" placeholder="请输入" readonly="true"></Input>
            </FormItem>

            <FormItem label="组织名称" prop="orgName"  style="width: 769px;" :rules="{required: true, message: '组织名称不能为空', trigger: 'blur'}">
                <Input v-model="org.orgName" placeholder="请输入组织名称"></Input>
            </FormItem>

            <FormItem label="预计编制人数" prop="orgStaff"  style="width: 769px;">
                <Input v-model="org.orgStaff" placeholder="请输入预计编制人数"></Input>
            </FormItem>

            <FormItem label="组织描述" prop="orgDesc"  style="width: 769px;" >
                <Input v-model="org.orgDesc" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入组织描述"></Input>
            </FormItem>

            <div  class="flybase-submit-dept" style=" text-align: center; padding-bottom: 10px; padding-top: 10px; width:965px;">
                <a data-v-942377fa="" href="javascript:;" @click="handleSubmit()" class="submit-btn-dept">保存</a>
                <a data-v-942377fa="" href="javascript:;" @click="clear()" class="submit-btn-dept">返回</a>
            </div>
        </Form>
        </div>
    </div>
</template>
<script>
    export default {

        data () {
            return {
                org: {},
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
            //this.listall();
        },
        methods: {
            init:function () {

                if(this.$route.query.deflag==1){
                    this.org.demName=this.$route.query.org.demName;
                    this.org.demId=this.$route.query.org.demId;
                    this.org.orgSupName=this.$route.query.org.orgName;
                    this.org.orgSupId=this.$route.query.org.orgId;
                }else{
                    this.org=this.$route.query.org;
                    this.org.orgSupName=this.$route.query.org.orgSupName;
                }

            },
            showloading:function (){
                this.modal1=true;
            },
            handleSubmit: function (){
                let self = this;
                //获取用户名
                self.$refs.orgVali.validate((valid) => {
                   if (valid) {
                       self.$profile.getProfile().then((profile) => {
                           console.log("aircraftlist get profile", profile);
                           self.org.children = "";
                           self.org.demId=1;
                           self.$http.jsonPost('/app/api/sysorg', self.org).then(function (response) {

                               alert("保存成功");
                               history.go(-1);
                           }).catch(function (error) {
                               console.log(error);
                           });
                       });
                   }
                   else{
                           this.$Message.error('表单验证失败!');
                           return;
                       }
                })


            },
            clear:function(){
                //history.go(-1);
                this.$router.back();
            }
        }
    }
</script>