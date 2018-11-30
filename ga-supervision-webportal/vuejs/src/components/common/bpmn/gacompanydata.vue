

<style scoped>
    .content {
        width: 99%;
        box-sizing: border-box;

    }

    .crumbs {
        padding-bottom: 4px;
        border-bottom: 1px solid #373737;
        height: 14px;
    }

    .ivu-steps .ivu-steps-head {
        background: #282828;
    }

    .steps {
        margin: auto;
        padding-top: 34px;
        width: 668px;
        height: 83px;
        vertical-align: middle;
        margin-bottom: 45px;

    }

    .steps > img {
        float: left;
    }
    .contet {
        width: 100%;
    }
</style>

<template>
    <div class="content">
        <div class="contet" style="width: 1000px;">
            <!--<div class="contetchild">-->
            <div class="steps">
                <img v-bind:src="img1" v-on:click="chiladshow1()" style="    width: 25%;"><img v-bind:src="img2" v-on:click="chiladshow2()" style="width: 25%;">
                <img v-bind:src="img3" v-on:click="chiladshow()" style="    width: 25%;"><img v-bind:src="img4" v-on:click="chiladshow4()" style="width: 25%;">
                <div class="stepschild"><span ref="jichu" style=" color: rgb(21, 184, 252);margin-left: 26px;font-size: 14px">{{basecontent}}</span> <span
                        ref="fr" style="margin-left:98px;font-size: 14px ">{{fareninfo}}</span> <span ref="zz" style="margin-left: 80px;font-size: 14px">{{zizhiinfo}}</span>
                    <span ref="qita" style="margin-left: 90px;font-size: 14px">{{otherinfo}}</span>
                    </div>
            </div>
        </div>
        <companyqualification v-show="qualificationisshow" ref="companyqualificationref"></companyqualification>
        <legalperson v-show="legalpersonshow" ref="legalpersonref"></legalperson>
        <gacompany v-show="gacompanyisshow" ref="gacompanyref" ></gacompany>
        <entinfo v-if="entinfoshow" ref="entinforef" :gascompanyid="companyid"></entinfo>
    </div>
</template>
<script>
    import gacompany from '../../pages/company/gacompany.vue'
    import companyqualification from '../../pages/company/companyqualification.vue'
    import entinfo from '../../pages/company/entinfo.vue'
    import legalperson from '../../pages/company/legalperson.vue'

    export default {
        data (){
            return {
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
            'legalperson': legalperson
        },
        mounted(){
            let self = this;
            let msg=self.$route.query.id;
            self.companyid=msg;
            self.chiladshow1();
        },
        methods: {
            chiladshow: function () {
                let self = this;
                    self.$refs.companyqualificationref.companyqualificationinit(self.companyid);
                    self.qualificationisshow = true;
                    self.entinfoshow = false;
                    self.gacompanyisshow = false;
                    self.legalpersonshow = false;
                    self.applyauthorshow = false;
                    self.basecontent='填写基础资料';
                    self.zizhiinfo='填写资质信息';
                    self.fareninfo='填写法人信息';
                    self.otherinfo='填写其他';
                    self.applyauther='提交审核';
                    self.img1 = require('../../../assets/灰1.png');
                    self.img2 = require('../../../assets/灰2.png');
                    self.img3 = require('../../../assets/3.png');
                    self.img4 = require('../../../assets/灰4.png');
                    self.img5 = require('../../../assets/灰5.png'),
                        self.$refs.jichu.style.color = '';
                    self.$refs.fr.style.color = '';
                    self.$refs.zz.style.color = 'rgb(21, 184, 252)';
                    self.$refs.qita.style.color = '';
                    self.$refs.tjsh.style.color = '';
            },
            chiladshow1: function () {
                let self = this;
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
                        self.basecontent = '填写基础资料';
                    self.zizhiinfo = '填写资质信息';
                    self.fareninfo = '填写法人信息';
                    self.otherinfo = '填写其他';
                    self.applyauther = '提交审核';
                    self.$refs.jichu.style.color = 'rgb(21, 184, 252)';
                    self.$refs.fr.style.color = '';
                    self.$refs.zz.style.color = '';
                    self.$refs.qita.style.color = '';
                    self.$refs.tjsh.style.color = '';
            },
            chiladshow2: function () {
                let self = this;
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
                    self.basecontent = '填写基础资料';
                    self.zizhiinfo = '填写资质信息';
                    self.fareninfo = '填写法人信息';
                    self.otherinfo = '填写其他';
                    self.applyauther = '提交审核';
                    self.$refs.jichu.style.color = '';
                    self.$refs.fr.style.color = 'rgb(21, 184, 252)';
                    self.$refs.zz.style.color = '';
                    self.$refs.qita.style.color = '';
                    self.$refs.tjsh.style.color = '';
            },
            chiladshow4: function () {
                let self = this;
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
                    self.basecontent = '填写基础资料';
                    self.zizhiinfo = '填写资质信息';
                    self.fareninfo = '填写法人信息';
                    self.otherinfo = '填写其他';
                    self.applyauther = '提交审核';
                    self.$refs.jichu.style.color = '';
                    self.$refs.fr.style.color = '';
                    self.$refs.zz.style.color = '';
                    self.$refs.tjsh.style.color = '';
                    self.$refs.qita.style.color = 'rgb(21, 184, 252)';
            }
            },
        watch: {}
    }


</script>