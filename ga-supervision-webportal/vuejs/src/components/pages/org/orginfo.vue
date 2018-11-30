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
        color:  white;
        font-size: 14px;
    }
    .user-tr-3{
        background: #c9e7fb;
    }
    .user-tr-5{
        background: #dcecfc;
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
            <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">系统管理-机构详情</span>
        </div>
        <div style="border: solid 1px rgba(1, 107, 217, 0.5);background: rgba(193, 220, 248, 0.5); position: absolute;bottom: 0px;top: 76px;width: 965px;right: 26px;">
        <table class='userlist'>
            <tbody><tr>
                <td class="user-tr-1 user-tr-3">上级机构</td>
                <td class="user-tr-2 user-tr-3">{{newOrg.orgSupName}}</td>
            </tr>
            <tr>
                <td class="user-tr-1 user-tr-5">组织名称</td>
                <td class="user-tr-2 user-tr-5">{{newOrg.orgName}}</td>
            </tr>
            <tr>
                <td class="user-tr-1 user-tr-3">预计编制人数</td>
                <td class="user-tr-2 user-tr-3">{{newOrg.orgStaff}}</td>
            </tr>
            <tr>
                <td class="user-tr-1 user-tr-5">组织描述</td>
                <td class='user-tr-2 user-tr-5'><div style="word-break: break-all; width:650px;padding-top: 12px;padding-bottom: 12px;">{{newOrg.orgDesc}}</div></td>
            </tr>
            </tbody>
        </table>
        <div  class="flybase-submit-dept" style=" text-align: center; padding-bottom: 10px; padding-top: 10px; width: 830px;float: left; margin-left: 34px;">
            <a data-v-942377fa="" href="javascript:;" @click="clear" class="submit-btn-dept">返回</a>
        </div>
    </div>
    </div>
</template>
<script>
    export default {

        data () {
            return {
                newOrg: {}
            }
        },
        created: function () {
            this.init();
        },
        methods: {
            init: function(){
                this.userinfo(this.$route.query.org);
            },
            userinfo:function (org){
                let self=this;
                self.newOrg=org;
            },
            clear:function(){
                history.go(-1);
            }
        }
    }
</script>
