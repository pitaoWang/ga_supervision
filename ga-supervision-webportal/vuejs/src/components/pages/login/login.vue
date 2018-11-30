<style>
    .ga-spin-icon-load{
        animation: ani-demo-spin 1s linear infinite;
    }
    @keyframes ani-demo-spin {
        from { transform: rotate(0deg);}
        50%  { transform: rotate(180deg);}
        to   { transform: rotate(360deg);}
    }
</style>
<template>
    <Row type="flex" justify="center" align="middle" class="login" @keydown.enter.native="submitLogin">
        <Col :xs="{span:22}" style="width: 368px;">
            <Row class="header">
                <div class="description">通航企业管理系统</div>
            </Row>
            <Alert type="error" show-icon v-if="error">{{errorMsg}}</Alert>
            <Row class="login-form" v-if="!socialLogining">
                <Form ref="usernameLoginForm" :model="form" :rules="rules" class="form">
                    <FormItem prop="username">
                        <Input v-model="form.username" prefix="ios-contact" size="large" clearable placeholder="请输入用户名" autocomplete="off" />
                    </FormItem>
                    <FormItem prop="password">
                        <Input type="password" v-model="form.password" prefix="ios-lock" size="large" clearable placeholder="请输入密码" autocomplete="off" />
                    </FormItem>
                </Form>
                <Row type="flex" justify="space-between" class="code-row-bg">
                    <Checkbox v-model="saveLogin" size="large">自动登录</Checkbox>
                    <!--<a class="forget-pass" @click="showAccount">忘记密码</a>-->
                </Row>
                <Row>
                    <Button class="login-btn" type="primary" size="large" :loading="loading" @click="submitLogin" long>
                        <span v-if="!loading">登录</span>
                        <span v-else>登录中...</span>
                    </Button>
                </Row>
                <!--<Row type="flex" justify="space-between" class="code-row-bg other-login">-->
                    <!--<div class="other-way icons">-->
                        <!--其它方式登录-->
                        <!--<div class="other-icon" @click="toGithubLogin">-->
                            <!--<icon scale="1.1" name="brands/github"></icon>-->
                        <!--</div>-->
                        <!--<div class="other-icon" @click="toQQLogin">-->
                            <!--<icon name="brands/qq"></icon>-->
                        <!--</div>-->
                        <!--<div class="other-icon" @click="toWeiboLogin">-->
                            <!--<icon scale="1.2" name="brands/weibo"></icon>-->
                        <!--</div>-->
                        <!--<div class="other-icon" @click="toWeixinLogin">-->
                            <!--<icon scale="1.2" name="brands/weixin"></icon>-->
                        <!--</div>-->
                    <!--</div>-->
                    <!--<router-link to="/regist"><a class="forget-pass">注册账户</a></router-link>-->
                <!--</Row>-->
            </Row>

            <Row class="foot">
                <Row type="flex" justify="center" class="code-row-bg copyright">
                    Copyright © 2018-Present 中航工业 版权所有
                </Row>
            </Row>
        </Col>
    </Row>
</template>
<script>
    import Cookies from "js-cookie";
    import {
        login,
        userInfo,
        githubLogin,
        qqLogin,
        weiboLogin,
        getJWT,
        sendSms,
        smsLogin
    } from "@/api/index";
    import util from "@/libs/util.js";
    export default {
        data() {
            const validateMobile = (rule, value, callback) => {
                var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
                if (!reg.test(value)) {
                    callback(new Error("手机号格式错误"));
                } else {
                    callback();
                }
            };
            return {
                socialLogining: false,
                error: false,
                errorMsg: "",
                tabName: "username",
                saveLogin: true,
                loading: false,
                sending: false,
                sended: false,
                count: 60,
                countButton: "60 s",
                maxLength: 6,
                errorCode: "",
                form: {
                    username: "test",
                    password: "123456",
                    code: ""
                },
                rules: {
                    username: [
                        {
                            required: true,
                            message: "账号不能为空",
                            trigger: "blur"
                        }
                    ],
                    password: [
                        {
                            required: true,
                            message: "密码不能为空",
                            trigger: "blur"
                        }
                    ],
                    mobile: [
                        {
                            required: true,
                            message: "手机号不能为空",
                            trigger: "blur"
                        },
                        {
                            validator: validateMobile,
                            trigger: "blur"
                        }
                    ]
                }
            };
        },
        methods: {
            showErrorMsg(msg) {
                this.error = true;
                this.errorMsg = msg;
            },
            countDown() {
                let that = this;
                if (this.count === 0) {
                    this.sended = false;
                    this.count = 60;
                    return;
                } else {
                    this.countButton = this.count + " s";
                    this.count--;
                }
                setTimeout(function() {
                    that.countDown();
                }, 1000);
            },
            submitLogin() {
                this.$refs.usernameLoginForm.validate(valid => {
                    if (valid) {
                        this.loading = true;
                        login({
                            username: this.form.username,
                            password: this.form.password,
                            saveLogin: this.saveLogin
                        }).then(res => {
                            if (res.success === true) {
                                this.setStore("accessToken", res.result);

                                // 获取用户信息
                                userInfo().then(res => {
                                    if (res.success === true) {
                                        // 避免超过大小限制
                                        delete res.result.permissions;
                                        if (this.saveLogin) {
                                            // 保存7天
                                            Cookies.set("userInfo", JSON.stringify(res.result), {
                                                expires: 7
                                            });
                                        } else {
                                            Cookies.set("userInfo", JSON.stringify(res.result));
                                        }
                                        this.setStore("userInfo", res.result);
                                        this.$store.commit("setAvatarPath", res.result.avatar);
                                        // 加载菜单
                                        //util.initRouter(this);
                                        this.$router.push('/homes');
                                    } else {
                                        this.loading = false;
                                    }
                                });
                            } else {
                                this.loading = false;
                            }
                        });
                    }
                });
            } ,
            relatedLogin() {

            },
        },
        mounted() {

        }
    };
</script>
