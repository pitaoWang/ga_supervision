<style scoped>
    .content {
        /*position: absolute;*/
        width: 99%;
        box-sizing: border-box;
        /*font-size: 18px;*/

    }
    .jichuclass{
        color: rgba(24, 24, 24, 1);
    }
    .crumbs {
        /*margin-bottom: 20px;*/
        padding-bottom: 4px;
        border-bottom: 1px solid #373737;
        height: 14px;
    }

    .ivu-steps .ivu-steps-head {
        background: #282828;
    }

    .steps {
        padding-top: 22px;
        width: 965px;
        height: 83px;
        vertical-align: middle;
        margin-left: 65px;

    }

    .steps > img {
        float: left;
    }


    .contet {
        width: 100%;
    }
.stepspan
{
    display: inline-block;
    width: 135px;
    text-align: center;
    font-size: 14px;
}
</style>

<template>
    <div class="content">
        <div class="contet">
            <!--<div class="contetchild">-->
            <div class="steps">
                <img v-bind:src="img1" v-on:click="show('show1')" style="width: 178px;height: 41px;"><img v-bind:src="img2" v-on:click="show('show2')" style="width: 178px;height: 41px;">
                <img v-bind:src="img3" v-on:click="show('show')" style="width: 178px;height: 41px;"><img v-bind:src="img4" v-on:click="show('show4')" style="width: 178px;height: 41px;">
                <img v-bind:src="img5" v-on:click="show('show5')" style="width: 178px;height: 41px;">
                <div class="stepschild "><span ref="jichu" class="jichuclass" style="display: inline-block;
    width: 175px;
    text-align: center;
    font-size: 14px;">{{basecontent}}<div style="color:red;font-size: 14px;display: inline-block;" v-if="companysave">*</div></span> <span
                        ref="fr" class="jichuclass" style="display: inline-block;
    width: 180px;
    text-align: center;
    font-size: 14px;">{{fareninfo}}<div style="color:red;font-size: 14px;display: inline-block;" v-if="personsave">*</div></span> <span ref="zz" class="jichuclass" style="display: inline-block;
    width: 183px;
    text-align: center;
    font-size: 14px;">{{zizhiinfo}}<div style="color:red;font-size: 14px;display: inline-block;" v-if="qualificationsave">*</div></span>
                    <span class="jichuclass" ref="qita" style="display: inline-block;
    width: 158px;
    text-align: center;
    font-size: 14px;" >{{otherinfo}}</span>
                    <span class="jichuclass" ref="tjsh" style="display: inline-block;
    width: 185px;
    text-align: center;
    font-size: 14px;">{{applyauther}}</span></div>
                </div>
            </div>
            <companyqualification v-show="qualificationisshow" v-on:isUpdateQualificationEmit="getisUpdateQualification" ref="companyqualificationref"></companyqualification>
            <legalperson v-show="legalpersonshow" v-on:isUpdatePersonEmit="getIsUpdatePerson" ref="legalpersonref"></legalperson>
            <gacompany v-show="gacompanyisshow" v-on:isUpdateEmit="getIsUpdate" ref="gacompanyref" ></gacompany>
            <entinfo v-if="entinfoshow" ref="entinforef" :gascompanyid="companyid"></entinfo>
            <applyauthor v-if="applyauthorshow" ref="authorinfo" :gascompanyid="companyid"></applyauthor>
        <Modal v-model="modelShow"
               :mask-closable="false"
               @on-ok="confirm"
               @on-cancel="cancel"
               width="300px" hight="200px" :styles="{top: '40%'}">
            <p slot="header" style="color:#e9e9e9;text-align:left;font-size: 15px;">
                <span>系统提示</span>
            </p>
            <p style="height: 100px; height: 80px;padding-top: 24px;text-align: center;margin-bottom: 2px; text-align: center;">
                信息未保存!</br>是否继续跳转....</p>
        </Modal>
        </div>
</template>
<script>
    import gacompany from './gacompany.vue'
    import companyqualification from './companyqualification.vue'
    import entinfo from './entinfo.vue'
    import legalperson from './legalperson.vue'
    import applyauthor from './applyauthor.vue'

    export default {
        data (){
            return {
                modelShow:false,
                companysave:false,
                personsave:false,
                qualificationsave:false,
                islinked:true,
                legalpersonshow: false,
                entinfoshow: false,
                gacompanyisshow: false,
                qualificationisshow: false,
                applyauthorshow:false,
                img1: "",
                img2: "",
                img3: "",
                img4: "",
                img5: "",
                basecontent:'',
                zizhiinfo:'',
                fareninfo:'',
                otherinfo:'',
                applyauther:'',
                ststus:'',
                companyid:0
            }
        },
        components: {
            'companyqualification': companyqualification,
            'gacompany': gacompany,
            'entinfo': entinfo,
            'legalperson': legalperson,
            'applyauthor':applyauthor
        },
        mounted(){
            let self = this;
            self.$profile.getProfile().then((profile) => {
                self.companyid=profile.buz.companyId;
                if(profile.buz.companyId){
                    self.$http.httpGet('/app/api/processrun/businesskeyrun', {
                        defId:'thqyzlsh',
                        businessKey:profile.buz.companyId
                    }).then(function (response) {
                        if(response.data.data){
                            self.ststus=1;
                            self.chiladshow5();
                        }else{
                            self.chiladshow1();
                        }
                    }) .catch(function (error) {
                        alert("获取流程数据失败");
                    });
                }
            },(error) => {
                self.$Message.error('user profile error = ' + error);
            });
    },
        methods: {

            confirm() {
                var urlfunction = this.linkedurl;
                this.companysave = false;
                this.personsave = false;
                this.qualificationsave = false;
                if(urlfunction=='show')
                {
                    this.chiladshow();
                }else if(urlfunction=='show1')
                {
                    this.chiladshow1();
                }else if(urlfunction=='show2')
                {
                    this.chiladshow2();
                }
                else if(urlfunction=='show3')
                {
                    this.chiladshow3();
                }else if(urlfunction=='show4')
                {
                    this.chiladshow4();
                }else if(urlfunction=='show5')
                {
                    this.chiladshow5();
                }
            },
            cancel()
            {
              //  this.islinked=false;
            },
            getIsUpdate:function (ss,s1) {
              console.log(ss+'    '+s1);
                if(ss=='y')
                {
                    this.companysave=true;
                }else {
                    this.companysave=false;
                }
            },
            getIsUpdatePerson:function (update,s1) {
                console.log(update+"   "+s1);
                if(update=='y')
                {
                    this.personsave = true;
                }else
                {
                    this.personsave = false;
                }
            },
            getisUpdateQualification:function (update,s1) {
                console.log(update+"   "+s1);
                if(update=='y')
                {
                    this.qualificationsave = true;
                }else
                {
                    this.qualificationsave = false;
                }
            },
            show(urlfunction){
                this.linkedurl=urlfunction;
                if(this.companysave||this.personsave||this.qualificationsave)
                {
                   this.modelShow=true;
                }else {
                    if(urlfunction=='show')
                    {
                        this.chiladshow();
                    }else if(urlfunction=='show1')
                    {
                        this.chiladshow1();
                    }else if(urlfunction=='show2')
                    {
                        this.chiladshow2();
                    }
                    else if(urlfunction=='show3')
                    {
                        this.chiladshow3();
                    }else if(urlfunction=='show4')
                    {
                        this.chiladshow4();
                    }else if(urlfunction=='show5')
                    {
                        this.chiladshow5();
                    }
                }

            },
            chiladshow: function () {
                let self = this;
                if(self.ststus===1){
                   alert("审核状态不可修改");
                }else{
                self.$refs.companyqualificationref.companyqualificationinit(self.companyid);
                self.qualificationisshow = true;
                self.entinfoshow = false;
                self.gacompanyisshow = false;
                self.legalpersonshow = false;
                self.applyauthorshow = false;
                self.basecontent='基础资料';
                self.zizhiinfo='资质信息';
                self.fareninfo='法人信息';
                self.otherinfo='其他信息';
                self.applyauther='提交审核';
                self.img1 = require('../../../assets/灰1.png');
                self.img2 = require('../../../assets/灰2.png');
                self.img3 = require('../../../assets/3.png');
                self.img4 = require('../../../assets/灰4.png');
                self.img5 = require('../../../assets/灰5.png'),
                self.$refs.jichu.style.color = '';
                self.$refs.fr.style.color = '';
                self.$refs.zz.style.color = 'rgba(1, 107, 217, 1)';
                self.$refs.qita.style.color = '';
                self.$refs.tjsh.style.color = '';
                }
            },
            chiladshow1: function () {
                let self = this;
                if(self.ststus===1){
                    alert("审核状态不可修改");
                }else {
                    self.$refs.gacompanyref.gacompanyinit(self.companyid);
                    self.qualificationisshow = false;
                    self.entinfoshow = false;
                    self.gacompanyisshow = true;
                    self.legalpersonshow = false;
                    self.applyauthorshow = false;
                    self.img1 = require('../../../assets/1.png');
                    self.img2 = require('../../../assets/灰2.png');
                    self.img3 = require('../../../assets/灰3.png');
                    self.img4 = require('../../../assets/灰4.png');
                    self.img5 = require('../../../assets/灰5.png'),
                    self.basecontent = '基础资料';
                    self.zizhiinfo = '资质信息';
                    self.fareninfo = '法人信息';
                    self.otherinfo = '其他信息';
                    self.applyauther = '提交审核';
                    self.$refs.jichu.style.color = 'rgba(1, 107, 217, 1)';
                    self.$refs.fr.style.color = '';
                    self.$refs.zz.style.color = '';
                    self.$refs.qita.style.color = '';
                    self.$refs.tjsh.style.color = '';
                }
            },
            chiladshow2: function () {
                let self = this;
                if(self.ststus===1){
                    alert("审核状态不可修改");
                }else {
                        self.$refs.legalpersonref.legalpersoninit(self.companyid);
                        self.qualificationisshow = false;
                        self.entinfoshow = false;
                        self.legalpersonshow = true;
                        self.gacompanyisshow = false;
                        self.applyauthorshow = false;
                        self.img1 = require('../../../assets/灰1.png');
                        self.img2 = require('../../../assets/2.png');
                        self.img3 = require('../../../assets/灰3.png');
                        self.img4 = require('../../../assets/灰4.png');
                        self.img5 = require('../../../assets/灰5.png');
                        self.basecontent = '基础资料';
                        self.zizhiinfo = '资质信息';
                        self.fareninfo = '法人信息';
                        self.otherinfo = '其他信息';
                        self.applyauther = '提交审核';
                        self.$refs.jichu.style.color = '';
                        self.$refs.fr.style.color = 'rgba(1, 107, 217, 1)';
                        self.$refs.zz.style.color = '';
                        self.$refs.qita.style.color = '';
                        self.$refs.tjsh.style.color = '';
                }
            },
            chiladshow4: function () {
                let self = this;
                if(self.ststus===1){
                    alert("审核状态不可修改");
                }else {
                    //self.$refs.entinforef.entinfomounted(self.companyid);
                    self.qualificationisshow = false;
                    self.gacompanyisshow = false;
                    self.entinfoshow = true;
                    self.legalpersonshow = false;
                    self.applyauthorshow = false;
                    self.img1 = require('../../../assets/灰1.png');
                    self.img2 = require('../../../assets/灰2.png');
                    self.img3 = require('../../../assets/灰3.png');
                    self.img4 = require('../../../assets/4.png');
                    self.img5 = require('../../../assets/灰5.png');
                    self.basecontent = '基础资料';
                    self.zizhiinfo = '资质信息';
                    self.fareninfo = '法人信息';
                    self.otherinfo = '其他信息';
                    self.applyauther = '提交审核';
                    self.$refs.jichu.style.color = '';
                    self.$refs.fr.style.color = '';
                    self.$refs.zz.style.color = '';
                    self.$refs.tjsh.style.color = '';
                    self.$refs.qita.style.color = 'rgba(1, 107, 217, 1)';
                }
            },chiladshow5: function () {
                let self = this;
                self.qualificationisshow = false;
                self.gacompanyisshow = false;
                self.entinfoshow = false;
                self.legalpersonshow = false;
                self.applyauthorshow = true;
                self.img1 = require('../../../assets/灰1.png');
                self.img2 = require('../../../assets/灰2.png');
                self.img3 = require('../../../assets/灰3.png');
                self.img4 = require('../../../assets/灰4.png');
                self.img5 = require('../../../assets/5.png');
                self.basecontent='基础资料';
                self.zizhiinfo='资质信息';
                self.fareninfo='法人信息';
                self.otherinfo='其他信息';
                if(self.ststus===1){
                    self.applyauther='待审核';
                }else{
                    self.applyauther='提交审核';
                }
                self.$refs.jichu.style.color = '';
                self.$refs.fr.style.color = '';
                self.$refs.zz.style.color = '';
                self.$refs.tjsh.style.color = 'rgba(1, 107, 217, 1)';
                self.$refs.qita.style.color = '';
                //self.$refs.authorinfo.getdatainfo(self.companyid);
            },

        },
        watch: {

        }
    }


</script>