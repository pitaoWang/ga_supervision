<template>
  <Row type="flex" justify="center" align="middle" @keydown.enter.native="submitLogin">
    <Col style="width: 368px; height: 910px">

    <div style="width:100%; height:320px"> </div>
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
      </Row>
      <Row>
        <Button class="login-btn" type="primary" size="large" :loading="loading" @click="submitLogin" long>
            <span v-if="!loading">登录</span>
            <span v-else>登录中...</span>
        </Button>
      </Row>
      <Row type="flex" justify="space-between" class="code-row-bg other-login">
        <!--<router-link to="/regist"><a class="forget-pass">注册账户</a></router-link>-->
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
    profile,
  getJWT
} from "@/api/index";
import util from "@/libs/util.js";
import Qs from 'qs';
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
        username: "admin",
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
      if (this.tabName === "username") {
        this.$refs.usernameLoginForm.validate(valid => {
          if (valid) {
            this.loading = true;
              let data = {
                  grant_type:'password',
                  password: this.form.password,
                  username: this.form.username
              }
              this.$axios({
                  method: 'post',
                  url: this.authUrl+"/AuthenticationManager/oauth/token",
                  params : {grant_type:'password',
                      password: this.form.password,
                      username: this.form.username},
                  headers: {
                      'Content-Type': 'text/plain',
                      'Authorization': 'Basic c3lzdGVtX3dlYjpzeXN0ZW0='
                  } }).then(res => {
                  if(typeof (res.response)!="undefined"){
                      alert("用户名或密码错误")
                      this.loading = false;
                  }else{
                      console.info("res=============================" + "Bearer "+res.access_token);
                      this.setStore("access_token", "Bearer "+res.access_token);
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
                                      util.initRouter(this);


                                  } else {
                                      this.loading = false;
                                  }
                                  if(res.result.type==1){
                                      this.$router.push({
                                          name: "userManage"
                                      });
                                  }else{
                                      profile({companyId:res.result.companyId,userId:res.result.id}).then(res =>{
                                          if (res.success === true) {
                                              Cookies.set("profile", JSON.stringify(res.result));
                                              this.setStore("profile", res.result);
                                              this.$router.push({
                                                  name: "home_index"
                                              });
                                          }else {
                                              this.loading = false;
                                          }
                                      });
                                  }

                              });
                          } else {
                              alert("该用户没有维护数据");

                              this.loading = false;
                          }
                      });
                  }
              });
          }
        });
      }
    }

  },
  mounted() {

  }
};
</script>
