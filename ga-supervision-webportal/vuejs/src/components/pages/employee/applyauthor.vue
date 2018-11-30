<template>
    <div class="" id="detaill" style="">
        <p-detail :employeeId="employeeId" v-on:tofather="listenToMyBoy" style="margin-left: 8px;" v-show="isdetail"></p-detail>
        <employeedetail :employeeId="employeeId" v-on:tofather1="listenToMyBoy1"  v-show="ispdetail" style="padding-left: 8px;"> </employeedetail>
        <div v-show="funcshow" class="bottom5" style="margin-top:20px;height:40px;margin-left: 285px;">
            <div class="employee-div-author" id="btnprint"
                 style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px;" >
                <a data-v-942377fa="" href="javascript:;"
                   class="employee-btn-author" @click="doPrint">打印</a>
                <a data-v-942377fa="" href="javascript:;"
                   class="employee-btn-author" @click="apply">提交审核</a>
            </div>
        </div>

        <div v-show="isprint" id="isprint" class="bottom5" style="margin-top:20px;height:75px;margin-left: 335px;">
            <div class="employee-div-author"
                 style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px;" >
                <a data-v-942377fa="" href="javascript:;"
                   class="employee-btn-author" @click="Print">打印</a>
            </div>
        </div>

        <div v-show="titlieshow" style="width: 600px;height: 92px;float: left;margin-left: 10px;margin-top: 20px;font-size: 18px;color: red;">
         注:人员信息在提交等待审核的过程中不允许修改</div>
    </div>
</template>

<script>
    import pDetail from '../employee/author/author-politview.vue';//politview.vue
    import employeedetail from '../employee/author/author-employeeview.vue';
    import FormItem from "../../../../node_modules/iview/src/components/form/form-item";
    import IForm from "../../../../node_modules/iview/src/components/form/form";
    export default {
        data () {
            return {
                funcshow:true,
                titlieshow:true,
                employeeId:0,
                ispdetail:false,
                isdetail:false,
                isprint:false
            }
        },
        components: {
            IForm,
            FormItem,
            pDetail,
            employeedetail
        },
        mounted: function () {
            this.dataref();
            this.updatedom();
        },methods: {
            //给预览页面添加样式
            updatedom(){
                var url=location.href;
                var detaill=document.getElementById('detaill');
                if(url.substring(url.length-14,url.length)==="print-emplpyee"){
                detaill.style.width="975px";
                detaill.style.margin="0 auto";
                var btnprint=document.getElementById('btnprint');
                var isprint=document.getElementById('isprint');
                btnprint.style.display="none";
                isprint.style.display="block";
                };
            },
            //打印预览页面打开
            doPrint (){
                window.open("/#/print-emplpyee");
            },
            //实现打印功能
            Print (){
                var parent=document.getElementsByClassName('employeecut');
                for(var k in parent){
                    if(parent[k].style){
                        parent[k].style.display="block";
                    }
                };
                window.print();
                window.close();
            },
            listenToMyBoy(val){
              if(val==true){
                  this.funcshow=false;
                  this.titlieshow=false;
              }else{
                  this.funcshow=true;
                  this.titlieshow=true;
              }
            },
            listenToMyBoy1(val){
                if(val==true){
                    this.funcshow=false;
                    this.titlieshow=false;
                }else{
                    this.funcshow=true;
                    this.titlieshow=true;
                }
            },
            apply:function (){
                let self = this;
                self.$profile.getProfile().then((profile) => {
                if(profile.buz.employeeId){
                    self.$http.httpGet('/app/api/processrun/businesskeyrun', {
                        defId:'TEST',
                        businessKey:profile.buz.employeeId
                    }).then(function (response) {
                        if(response.data.data){
                            alert("流程审核已经提交，不可重复发起");
                        }else{
                            self.startrun(profile.buz.employeeId);
                        }
                    }) .catch(function (error) {
                        console.log(error);
                    });
                }
                },(error) => {
                    self.$Message.error('user profile error = ' + error);
                });
            },startrun:function (obj) {
                let self=this;
                self.$http.httpGet('/app/api/bpmtask/search/startdata', {
                    defId:'TEST',
                    businessKey:obj
                }).then(function (response) {
                    alert("提交成功");
                }) .catch(function (error) {
                    console.log(error);
                });
             },
             dataref:function (){
                 this.employeeId = 0;
                 let self = this;
                 self.$profile.getProfile().then((profile) => {
                     if(profile.user.jobtitle==='飞行人员'||profile.user.jobtitle==='飞行学员'){
                         self.isdetail=true;
                         self.ispdetail=false;
                     }else{
                         self.ispdetail=true;
                         self.isdetail=false;
                     }if (profile.buz && profile.buz.employeeId) {
                         self.employeeId = profile.buz.employeeId;
                     }
                 },(error) => {
                     self.$Message.error('user profile error = ' + error);
                 });
             }
        }
    }
</script>
<style>
    .topBar li {
        padding-right: 35px;
        height: 60px;
        background: url(../../../../static/images/topBarright.png) center right no-repeat;
    }
    .topBar {
        display: flex;
        justify-content: space-between;
    }
    .topBar li label.item {
        margin: 0 5px;
        font-size: 16px;
        color: #fff;
        font-style: italic;
        line-height: 60px;
        vertical-align: middle;
    }

    .rightPart1 table{
        border-collapse:collapse;
        border-spacing:0;
        border-left:1px solid #888;
        border-top:1px solid #888;
        background:#efefef;}

    .radio {
        width: 16px;
        height: 16px;
        border: 1px solid #fff;
        position: relative;
        border-radius: 50%;
        display: inline-block;
        vertical-align: middle;
    }
    .radio input[type='radio'] {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        z-index: 2;
        opacity: 0;
    }


    .div-list-aircraftdata{
        width: 700px;
        height: 35px;
        background: #272727;
        font-size: 16px;
        color: white;
        padding-top: 7px;
        border: solid 1px #ececec;
    }
    .moadl-content-1-2{
        width: 1020px;
        margin-top: 140px;
        border: 1px solid white;
        border-radius: 0px;
        background: #222222;
    }
    .titel-div{
        height: 33px;
        padding-top: 12px;
        font-size: 14px;
        padding-left: 20px;
        background: #191919;
        border-bottom: solid 1px white;
        color: white;
    }
    .div-btn-model{
        margin-top: 20px;
        width: 190px;
        height: 30px;
        color: black;
    }

    .div-heard{
        background: #cacaca;
        height: 30px;
        width: 300px;
        margin-bottom: 10px;
        text-align: center;
        padding-top: 10px;
    }

    .div-button-1{
        width: 45px;
        float: left;
        height: 30px;
        margin-left: 5px;
        background: #b1b1b1;
        text-align: center;
        padding-top: 6px;
        border-radius: 5px;
    }

    .m_changeList {
        width: 702px;
        margin-bottom: 10px;
        border-spacing: 0px;
        border: 1px solid #0e0e0e;
    }


    .tdbody{
        border: 1px solid #999;
        border: 1px solid rgba(0,0,0,.2);

    }
    .mod{margin-bottom:15px}
    .mod .tit{padding:5px 7px 5px 8px;border:1px solid #d6d6d6;font-size:14px;color:rgba(255,255,255,.85);border-radius:1px}
    .mod .tit .icon-font{margin-right:5px}
    .mod .tit01 .name{float:left;line-height:26px}
    .mod .tit01 .op{float:right}
    .mod .tit01 .date{margin-left:2px;padding:0 10px;width:55px;line-height:26px;border:0;box-shadow:inset 3px 3px 5px #4a4a4a;border-radius:3px;color:#444;font-size:12px}
    .mod .tit01 .refresh{margin-left:2px}

    .rightPart .mod .cut table{width:100%;}
    .mod .cut td{padding-left:28px;height:30px;line-height:30px;
        width:60px;border-right:1px solid #313131;border-bottom:1px solid #313131}
    .mod .cut td:first-child{padding-left:0}.mod .cut td:last-child{border-right:0 none}
    .com-wrap-1190{ width: 1190px; margin: 0 auto; overflow: hidden;}
    .footer{ padding: 0;}
    .radio{ width: 16px; height: 16px; border:1px solid #fff; position: relative; border-radius: 50%; display: inline-block; vertical-align: middle;}
    .radio span{ position: absolute; width: 6px; height: 6px; border-radius: 50%;top: 50%;left: 50%;transform: translate(-50%,-50%); background-color: #fff; z-index: 1;opacity: 0;}
    .radio input[type='radio']{ position: absolute; left: 50%;top: 50%;transform: translate(-50%,-50%);z-index: 2; opacity: 0;}
    .radio input[type='radio']:checked+span{ opacity: 1;}
    .topBar li label.item{ margin: 0 5px; font-size: 16px; color: #fff; font-style: italic; line-height: 60px; vertical-align: middle;}
    .row.width100{ width: 100%; padding-left: 16px; box-sizing: border-box;}
    .mod .tit:after{content: ""; width: 2px; height: 15px; background-color: #fff; position: absolute; right: 17px; top: 50%; transform: translateY(-50%); visibility: visible;}
    .mod .tit.active:after{ display: none;}
    .mod .tit .icon-slide{ width: 15px; height: 2px;background-color: #fff; position: absolute; top: 50%; transform: translateY(-50%); right: 10px;}
    .mod .cut.container-center td{  padding-top: 10px; padding-bottom: 10px; font-size: 14px;}
    .mod .cut.container-center tr:nth-child(2n-1) td{ background-color: #313233;}
    .mod .cut.container-center tr td:nth-child(2n){ background-color: #3d3d3d;}

    .employee-btn-author {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border-radius: 3px;
        color: rgb(255,255,255);
        background-color:rgba(37,128,221,1);
    }

    .employee-div-author a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
</style>
