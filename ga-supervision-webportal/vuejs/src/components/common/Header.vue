<template>
<div>
    <header class="header">
        <span>
        <a href="/home">
        <div class="operation-logo" style="float: left"></div>
        <div class="title">中国航空工业</div>
        </a>
        </span>
        <div class="user">
            <div style="float: right; margin-top: 10px">
                <Dropdown trigger="click" style="margin-left: 4px; margin-right: 30px">
                    <a href="javascript:void(0)">
                    <span style="color:#8E9396;">{{username}}</span>
                    <Icon type="ios-arrow-down" style="color:#8E9396"></Icon>
                </a>
                    <DropdownMenu slot="list">
                        <DropdownItem>
                            <a href="javascript:;"  @click="updatepwd">
                                <span style="color:#8E9396">修改密码</span>
                            </a>
                        </DropdownItem>
                        <DropdownItem>
                            <a @click="logout"><span style="color:#8E9396">退出</span></a>
                        </DropdownItem>
                    </DropdownMenu>
                </Dropdown>
            </div>
            <div style="float: right; margin-top: 5px">
                <Avatar icon="ios-person" style="background-color: #C7DBEA;"></Avatar>
            </div>
            <div style="float: right; margin-top: 12px; margin-right: 60px">
                <message-tip v-model="counts" @on-change="handleChange"></message-tip>
            </div>

            <div style="float: right; margin-top: 12px; margin-right: 60px">
                <div id="time"></div>
            </div>
        </div>
    </header>
    <Modal v-model="modal1" title="修改密码" @on-ok="oks('formCustom')" @on-cancel="cancel">
           <Form :model="newUser" :label-width="80"  style="padding-bottom: 1px;" :rules="ruleInline" ref="formCustom" inline>
               <FormItem  label="旧密码" style="" prop="cp">
                   <Input type="password" v-model="newUser.cp" placeholder="请输入" style="width: 320px;"></Input>
               </FormItem>

               <FormItem label="新密码" style="" prop="np">
                   <Input type="password" v-model="newUser.np" placeholder="请输入" style="width: 320px;"></Input>
               </FormItem>

               <FormItem label="确认密码" style="" prop="qnp">
                   <Input type="password" v-model="newUser.qnp" placeholder="请输入" style="width: 320px;"></Input>
               </FormItem>
           </Form>
    </Modal>
</div>
</template>
<style scoped>
    .user{
        float: right;
    }

</style>
<script>
    import Cookies from "js-cookie";
    import util from "@/libs/util.js";
    import messageTip from "@/views/main-components/message-tip.vue";
    import {getStore} from "../../libs/storage";
    import {
        changePass,
        changePasss
    } from "@/api/index";
    export default {
        components: {
            messageTip,
        },
        data () {
            const validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.newUser.qnp !== '') {
                        // 对第二个密码框单独验证
                        this.$refs.formCustom.validateField('qnp');
                    }
                    callback();
                }
            };
            const validatePassCheck = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.newUser.np) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                logo:'',
                imgsrc:'../../../static/images/default-user.png',
                username: '',
                userId: "",
                employeeId:'',
                newUser: {},
                ruleInline: {
                    cp: [
                        { required: true, message: '请填写旧密码', trigger: 'blur' }
                    ],
                    np: [
                        { validator: validatePass, required: true,trigger: 'blur' },
                        { required: true, message: '请填写新密码', trigger: 'blur' },
                        { type: 'string', min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
                    ],
                    qnp:[
                        { validator: validatePassCheck,required: true, trigger: 'blur' },
                        { required: true, message: '请填写确认密码', trigger: 'blur' },
                        { type: 'string', min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
                    ]
                },
                modal1: false,

                count:0,
                companyIds:'',
                page:1,
                pageSize:10
            }
        },
        created(){
            this.getlogo();
            // this.$profile.getProfile().then((profile) => {
            //     this.userName = profile.user.fullname
            // }, (error) => {
            //     console.log('error', error);
            // });
        },
        methods:{
            handleChange (name) {

                this.$emit('on-change', name);
            },
            init() {
                let userInfo = JSON.parse(Cookies.get("userInfo"));
                this.username = userInfo.username;
                this.userId = userInfo.id;
                this.employeeId = userInfo.employeeId;
            },

            logout: function () {

               if(getStore("access_token")!=null){
                   let access_token = getStore("access_token").substring(7);

                   this.$axios({
                       method: 'post',
                       url: this.authUrl+"/AuthenticationManager/oauth/logout",
                       params : {
                           access_token:access_token
                       },
                       headers: {
                           'Content-Type': 'text/plain',
                           'Authorization': 'Basic c3lzdGVtX3dlYjpzeXN0ZW0='
                       } }).then(res => {
                       // this.setStore("access_token", "Bearer "+res.access_token);
                       this.$store.commit("logout", this);
                       // 强制刷新页面 重新加载router
                       location.reload();
                   }).catch(function (error) {
                       console.log(error);
                   });

               }else{
                   this.$store.commit("logout", this);
                   // 强制刷新页面 重新加载router
                   location.reload();
               }

            },
            getlogo(){
                let self = this;
            },
            updatepwd:function () {
               this.modal1=true;
            },
            oks (name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        let self=this;

                        changePasss({
                            employeeId: self.employeeId,
                            oldpassword:self.newUser.cp,
                            newpassword:self.newUser.qnp
                        }).then(function (response) {

                            if (response.succ === true) {
                                changePass({
                                    id: self.userId,
                                    password:self.newUser.cp,
                                    newPass:self.newUser.qnp
                                }).then(function (response) {

                                    if(response.code===200){
                                        self.$Message.success('密码修改成功!');
                                    }else {
                                        self.$Message.success('密码修改失败!');
                                    }

                                });
                            }else{
                                self.$Message.success('密码修改失败!');
                            }
                        });
                    } else {
                        this.$Message.error('表单验证失败!');
                    }
                })
            },
            cancel () {
                this.$Message.info('点击了取消');
            },
            mesCount() {
                return this.$store.state.app.messageCount;
            },
            getMessageList:function (no) {
                let self = this;
                let profile = JSON.parse(Cookies.get("profile"));
                //   let buz = JSON.parse(Cookies.get("profile"));
                self.$http.httpGet('/eim/api/message/getListByCompanyIds1',{
                    companyIds: profile.buz.companyId,
                    page:no,
                    pageSize: self.pageSize
                }).then(function (response) {
                    let datainfo = response.data.content;
                    //self.messageList=datainfo;
                    self.count=response.data.totalElements;
                    self.page=no;

                }).catch(function (error) {
                    console.log(error);
                });
            },
        },
        mounted() {
            this.init();
            this.getMessageList(1);
        },
        computed:{
            counts:function () {
                return this.count;
            }
        },
        watch:{
            counts:function () {
                this.getMessageList(1)
            }
        }

    }

</script>