<style>
    .gacompany-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }

    .gacompany-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;

        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
</style>
<template>
    <div style="" id="detaillccompany">
        <companysup ref="compay" v-on:emitSubmit="getShowOrNot"></companysup>
        <div class="bottom5" style="margin-top:20px;height:40px;margin-left: 285px;" id="btnprint">
            <!--<div class="gacompany-div"-->
                 <!--style="float: left; text-align: center; padding-bottom: 10px; padding-top: 26px;" v-show="isShow">-->
                <!--<a data-v-942377fa="" href="javascript:;"-->
                   <!--class="gacompany-btn" @click="">上一页</a>-->
            <!--</div>-->
            <div class="gacompany-div"
                 style="float: left; text-align: center; padding-bottom: 10px; padding-top: 26px;" v-show="isShow">
                <a data-v-942377fa="" href="javascript:;"
                   class="gacompany-btn" @click="doPrint">打印</a>
            </div>
            <div class="gacompany-div"
                 style="float: left; text-align: center; padding-bottom: 10px; padding-top: 26px;" v-show="isShow">
                <a data-v-942377fa="" href="javascript:;"
                   class="gacompany-btn" @click="apply">提交审核</a>
            </div>
        </div>

        <div class="bottom5" style="margin-top:20px;height:40px;margin-left: 212px;" id="isprint" v-show="isprint">
            <div class="gacompany-div"
                 style="text-align: center;margin-right: 185px;" v-show="isShow">
                <a data-v-942377fa="" href="javascript:;"
                   class="gacompany-btn" @click="Print">打印</a>
            </div>
        </div>
        <div style="width: 600px;height: 92px;float: left;margin-left: 20px;margin-top: 20px;font-size: 18px;color: red;" v-show="isShow">
            注:企业信息在提交等待审核的过程中不允许修改</div>
    </div>
</template>

<script>
    import companysup from '../../views/company/companysups.vue'
    export default {
        data () {
            return {
                employeeId:0,
                isShow:true,
                isprint:false
            }
        },
        components: {
            companysup
        },
        props:['gascompanyid'],
        mounted(){
           if(this.gascompanyid){
                this.getdatainfo(this.gascompanyid);
            }else{
               this.$profile.getProfile().then((profile) => {
                 this.getdatainfo(profile.buz.companyId);
               },(error) => {
                   self.$Message.error('user profile error = ' + error);
               });
           }
            this.updatedom();
        },
        methods: {
            //给预览页面添加样式
            updatedom(){
                var url=location.href;
                var detaill=document.getElementById('detaillccompany');
                var companysh=document.getElementById('companysh');
                if(url.substring(url.length-13,url.length)==="print-company"){
                    detaill.style.width="975px";
                    detaill.style.margin="0 auto";
                    var btnprint=document.getElementById('btnprint');
                    var isprint=document.getElementById('isprint');
                    btnprint.style.display="none";
                    companysh.style.display="none";
                    isprint.style.display="block";
                };
            },
            //打印预览页面打开
            doPrint (){
                window.open("/#/print-company");
            },
            //实现打印功能
            Print (){
                var parent=document.getElementsByClassName('companycut');
                for(var k in parent){
                    if(parent[k].style){
                        parent[k].style.display="block";
                    }
                };
                window.print();
                window.close();
            },
            apply:function () {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    if(profile.buz.companyId){
                        self.$http.httpGet('/app/api/processrun/businesskeyrun', {
                            defId:'thqyzlsh',
                            businessKey:profile.buz.companyId
                        }).then(function (response) {
                            if(response.data.data){
                                alert("流程审核已经提交，不可重负发起");
                            }else{
                                self.startrun(profile.buz.companyId);
                            }
                        }) .catch(function (error) {
                            console.log(error);
                        });
                    }
                },(error) => {
                    self.$Message.error('user profile error = ' + error);
                });
            },getdatainfo:function (obj) {
                let self = this;
                self.employeeId =obj;
                self.$refs.compay.ftsde(obj);
            },startrun:function (obj) {
                let self = this;
                self.$http.httpGet('/app/api/bpmtask/search/startdata',{
                    defId:'thqyzlsh',
                    businessKey:obj
                }).then(function (response) {
                    alert("提交成功");
                }) .catch(function (error) {
                    console.log(error);
                });
            },
            getShowOrNot:function(isShow){
                console.log(isShow);
                this.isShow=isShow;
            },
        }
    }
</script>