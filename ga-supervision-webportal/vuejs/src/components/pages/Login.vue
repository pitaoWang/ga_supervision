<template>
    <div id='login-panel'>
        <div class="website-title">
            <img src="../../../static/images/logo1920.png" class="title-icon"/>
            <div class="title-text">
                <span class="website-title_zh">通用航空运行信息平台</span>
                <span class="website-title_en">General aviation operation management imformation platform</span>
            </div>
        </div>
        <div class="login-wrap">
            <img src="../../../static/images/login_header.png"/>
            <Form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
                <FormItem prop="username">
                    <Input type="text" class="text_input" v-model="ruleForm.username" placeholder="用户名:"></Input>
                </FormItem>
                <FormItem prop="password">
                    <Input type="password" class="text_input" v-model="ruleForm.password" placeholder="密码:"></Input>
                </FormItem>
                <FormItem prop="validCode">
                    <Input type="text" class="text_input" name="validCode" placeholder="验证码:"></Input>
                </FormItem>
                <FormItem class="passDiv">
                    <input type="checkbox" id="rememberPass"/>
                    <label for="rememberPass">记住密码</label>
                    <a href="#">忘记密码？</a>
                </FormItem>
                <div class="login-btn">
                    <Button @click="submitForm('ruleForm')">登录</Button>
                </div>
                <p style="font-size:12px;line-height:30px;color:#999;">密码填写任意值</p>
            </Form>
        </div>
    </div>
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
        data: function () {
            document.getElementById('app').style.height = '100%';
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    validCode: ''
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                const self = this;
                self.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$Loading.start();
                        self.$http.jsonPost('/login', {
                            username: self.ruleForm.username,
                            password: self.ruleForm.password
                        }).then((res) => {
                            this.$Loading.finish();
                            let result = res.data;
                            if (result.resultCode == 200) {
                                let authority = 'un=' + result.un + ';ts=' + result.ts.toString() + ';tk=' + encodeURIComponent(result.tk);
                                self.$store.set('Authority', authority);
                                self.$http.setHeader('Authority', authority);
                                self.$router.push('/home');
                            }
                            else {
                                this.$Loading.error();

                                return false;
                            }
                        });
                    }
                });
            }
        }
    }
</script>

<style scoped>
    #login-panel {
        position: relative;
        width: 100%;
        height: 100%;
        min-height: 580px;
        margin: auto;
        background: url(../../../static/images/login_bg_1920.png) center no-repeat;
    }

    .website-title {
        position: absolute;
        top: 50%;
        left: 3.9%;
    }

    .website-title > div, .website-title > img {
        display: inline-block;
        vertical-align: middle;
    }

    .website-title .title-text {
        margin-left: 5px;
    }

    .website-title .title-text > span {
        display: block;
    }

    .website-title .title-text .website-title_zh {
        letter-spacing: 23px;
    }

    .website-title .title-text .website-title_en {
    }

    .website-title > img {
        width: 26.5%;
    }

    @media screen and (min-width: 1829px) {
        .login-wrap .text_input, .login-btn button {
            height: 65px;
        }

        .website-title .title-text .website-title_zh {
            font-size: 40px;
            letter-spacing: 23px;
        }

        .website-title .title-text .website-title_en {
            font-size: 25px;
        }
    }

    @media screen and (min-width: 1588px) and (max-width: 1829px) {
        .login-wrap .text_input, .login-btn button {
            height: 50px;
        }

        .website-title .title-text .website-title_zh {
            font-size: 35px;
            letter-spacing: 13px;
        }

        .website-title .title-text .website-title_en {
            font-size: 20px;
        }
    }

    @media screen and (max-width: 1588px) {
        .login-wrap .text_input, .login-btn button {
            height: 40px;
        }

        .website-title .title-text .website-title_zh {
            font-size: 30px;
            letter-spacing: 13px;
        }

        .website-title .title-text .website-title_en {
            font-size: 15px;
        }
    }

    .login-wrap {
        position: absolute;
        right: 8.6%;
        top: 10%;
        width: 29.6%;
        height: 69.26%;
        min-height: 500px;
        background-color: #717171;
        text-align: center;
    }

    .login-wrap > img {
        width: 100%;
    }

    .ms-title {
        position: absolute;
        top: 50%;
        width: 100%;
        margin-top: -230px;
        text-align: center;
        font-size: 30px;
        color: #fff;
    }

    .ms-login {
        position: absolute;
        left: 2.82%;
        top: 22.28%;
        width: 465px;
        padding: 40px;
    }

    .login-btn {
        text-align: center;
    }

    .login-btn button {
        width: 81.9%;
        margin-left: 7.2%;
        border-radius: 7px;
    }

    .login-wrap .text_input {
        width: 81.9%;
        margin-left: 12.4%;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        background-color: #fff;
        background-image: none;
        border-radius: 4px;
        border: 1px solid #bfcbd9;
        box-sizing: border-box;
        color: #1f2d3d;
        display: block;
        font-size: 18px;
        line-height: 1;
        outline: 0;
        padding: 3px 10px;
        transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
    }

    .passDiv input, .passDiv a, .passDiv label {
        display: inline-block;
        vertical-align: middle;
        font-size: 16px;
    }

    .passDiv input {
        width: 18px;
        height: 18px;
    }

    .passDiv label {
        margin-left: 5px;
        margin-right: 15px;
        color: #333;
    }

    .passDiv a {
        color: white;
    }
</style>
