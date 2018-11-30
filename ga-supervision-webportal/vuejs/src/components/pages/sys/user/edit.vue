<style>
    .userlist{
        width: 850px;
        margin-left: 55px;
        border-spacing: 0px;
        border: 1px solid #016bd9cc;
        margin-top: 45px;
    }
    .user-tr-1 {
        font-size: 14px;
        width: 146px;
        padding-left: 13px;
        height: 40px;
        color: #4b8fdf;
        text-align: center;
    }

    .user-tr-2 {
        padding-left: 36px;
        padding-right: 10px;
        color: #000000c4;
        font-size: 14px;
    }

    .user-tr-2-1 {
        padding-left: 36px;
        padding-right: 10px;
        color: #000000c4;
        font-size: 14px;
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

    .user-tr-3{
        background-color: #99cffb;
    }

    .user-tr-4{
        background-color: #dbebfb;
    }
    .user-tr-4-1{
        background-color: #eff6fe;
    }
    .user-tr-5{
        background-color: #cae8fc;
    }
</style>

<template>
    <div style="width: 965px; margin-left: 12px;">
        <div style="background: #2e82dc;height: 36px;padding-top: 6px;margin: 22px 0px 3px 0px;padding: 6px 0 0 0;">
            <img src="../../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
            <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">系统管理-用户详情</span>
        </div>
        <div style="border: solid 1px rgba(1, 107, 217, 0.5);background: rgba(193, 220, 248, 0.5); position: absolute;bottom: 0px;top: 76px;width: 965px;right: 26px;">
        <table class='userlist'>
            <tbody><tr>
                <td class="user-tr-1 user-tr-3">帐号</td>
                <td class="user-tr-2 user-tr-4">{{newUser.account}}</td>
                <td class="user-tr-1 user-tr-3">用户姓名</td>
                <td class="user-tr-2 user-tr-4">{{newUser.fullname}}</td>
            </tr><tr>
                <td class="user-tr-1 user-tr-5">邮箱地址</td>
                <td class="user-tr-2 user-tr-4-1">{{newUser.email}}</td>
                <td class="user-tr-1 user-tr-5">手机</td>
                <td class='user-tr-2 user-tr-4-1'>{{newUser.mobile}}</td>
            </tr><tr>
                <td class="user-tr-1 user-tr-3">用户角色</td>
                <td class="user-tr-2 user-tr-4">{{newUser.roleName}}</td>
                <td class="user-tr-1 user-tr-3">所属部门</td>
                <td class="user-tr-2 user-tr-4">{{newUser.dept}}</td>
            </tr><tr>
                <td class="user-tr-1 user-tr-5">行政职务</td>
                <td class="user-tr-2-1 user-tr-4-1">{{newUser.jobName}}</td>
                <td class="user-tr-1 user-tr-5">技术职务</td>
                <td class="user-tr-2-1 user-tr-4-1">{{newUser.Technicalname}}</td>
            </tr><tr>
                <td class="user-tr-1 user-tr-3">岗位类型</td>
                <td class="user-tr-2 user-tr-4">{{newUser.jobtype}}</td>
                <td class="user-tr-1 user-tr-3">技术级别</td>
                <td class="user-tr-2 user-tr-4">{{newUser.techgrade}}</td>
            </tr>
            </tbody>
        </table>
        <div  class="flybase-submit-user" style="text-align: center; padding-bottom: 10px; padding-top: 10px; width: 830px;float: left; margin-left: 34px;">
            <a data-v-942377fa="" href="javascript:;" @click="clear" class="submit-btn-user">返回</a>
        </div>
    </div>
    </div>
</template>
<script>
    export default {

        data () {
            return {
                newUser: {}
            }
        },
        created: function () {
            this.init();
        },
        methods: {
            init: function(){
                this.userinfo(this.$route.query.user);
            },
            userinfo:function (user){
                let self=this;
                self.$http.httpGet('/app/api/sysuser/search/byuserid',{
                    userid:user.userId
                }).then(function (response) {
                    self.newUser=response.data.data;
                    self.newUser.dept=user.orgName;
                    self.$http.httpGet('/app/api/employee/search/byuserid',{
                        userid:user.userId
                    }).then(function (response) {
                        self.$set(self.newUser, 'jobtype', response.data.data.jobTitle);
                        self.$set(self.newUser, 'techgrade', response.data.data.techgrade);
                        self.$set(self.newUser, 'Technicalname', response.data.data.techpos);

                    }).catch(function (error) {
                        console.log(error);
                    });
                }).catch(function (error) {
                    console.log(error);
                });
            },
            clear:function(){
                history.go(-1);
            }
        }
    }
</script>
