<style>
    .icon-slide {
        width: 15px;
        height: 2px;
        background-color: #fff;
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        right: 10px;
    }

    .icon-font.icon50 {
        /*background-image: url(../../../static/images/icon-font50.png);*/
    }

    .icon-font.icon51 {
        /*background-image: url(../../../static/images/icon-font51.png);*/
    }

    .icon-font.icon52 {
        /*background-image: url(../../../static/images/icon-font52.png);*/
    }

    .checkbox input[type='checkbox'] {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: 2;
        opacity: 0;
    }

    .checkbox {
        width: 15px;
        height: 15px;
        border: 1px solid #ababab;
        position: relative;
        border-radius: 1px;
        display: inline-block;
        vertical-align: middle;
    }

    .tit {
        position: relative;
    }

    .tit {
        padding: 5px 7px 5px 8px;
        border: 1px solid #d6d6d6;
        font-size: 14px;
        color: rgba(255, 255, 255, .85);
        border-radius: 1px;
    }

    .div-heard-1 {
        width: 1009px;
        margin-bottom: 10px;
    }

    .topBar li {
        padding-right: 35px;
        height: 60px;
        /*background: url(../../../../static/images/topBarright.png) center right no-repeat;*/
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
        transform: translate(-50%, -50%);
        z-index: 2;
        opacity: 0;
    }

    .div-list-aircraftdata {
        width: 700px;
        height: 35px;
        /*background: #272727;*/
        font-size: 16px;
        color: white;
        padding-top: 7px;
        border: solid 1px #ececec;
    }

    .left-1 {
        float: left;
    }

    .rigtht-1 {
        float: left;
        margin-left: 15px;
    }
    .logoimgcompany{
        height: 19px;
        display: inline;
        /* padding: 3px; */
        vertical-align: middle;
        padding: 1px 2px;
    }

</style>
<template>
    <div>
        <div class="rigtht-1" v-show="companysuplistshow">
            <div>
                <!--<div  @click="showinfo('isshow1')" class="div-list-aircraftdata"><img v-bind:src="imgds">基本信息</div>-->
                <div class="tit active div-list-aircraftdata" @click="islogshow1=!islogshow1">
                    <div class="name">
                        <div class="checkbox">
                            <input type="checkbox" name="toplist">
                            <span></span>
                        </div>
                        <img src="../../../assets/企业基本信息.png" class="logoimgcompany">企业基本信息
                        <span class="fr">
                        <Icon :type="islogshow1? 'minus':'plus'"></Icon></span>
                    </div>
                </div>
                <div v-show="islogshow1">
                    <gacompany v-bind:companyId="infodata"></gacompany>
                </div>
            </div>
            <div>
                <div class="tit active div-list-aircraftdata" @click="showinfo('show3')">
                    <div class="name">
                        <div class="checkbox">
                            <input type="checkbox" name="toplist">
                            <span></span>
                        </div>
                        <img src="../../../assets/资质信息.png" class="logoimgcompany">资质信息
                        <span class="fr">
                    <Icon :type="islogshow3? 'plus':'minus'"></Icon>
                </span>
                    </div>
                </div>
                <div v-show="isshow3">
                    <companyqualification v-bind:companyId="infodata"></companyqualification>
                </div>
            </div>
            <!--<div>-->
            <!--<div class="tit active div-list-aircraftdata" @click="showinfo('show4')">-->
            <!--<div class="name">-->
            <!--<div class="checkbox">-->
            <!--<input type="checkbox" name="toplist">-->
            <!--<span></span>-->
            <!--</div>-->
            <!--<img src="../../../assets/飞行员列表.png" class="logoimgcompany">飞行员列表-->
            <!--<span class="fr">-->
            <!--<Icon :type="islogshow4? 'plus':'minus'"></Icon>-->
            <!--</span>-->
            <!--</div>-->
            <!--</div>-->
            <!--<div v-show="isshow4">-->
            <!--<pilotlist v-bind:companyId="infodata"></pilotlist>-->
            <!--</div>-->
            <!--</div>-->
            <!--<div>-->
            <!--<div class="tit active div-list-aircraftdata" @click="showinfo('show2')">-->
            <!--<div class="name">-->
            <!--<div class="checkbox">-->
            <!--<input type="checkbox" name="toplist">-->
            <!--<span></span>-->
            <!--</div>-->
            <!--<img src="../../../assets/航空器列表.png" class="logoimgcompany">航空器列表-->
            <!--<span class="fr">-->
            <!--<Icon :type="islogshow2? 'plus':'minus'"></Icon>-->
            <!--</span>-->
            <!--</div>-->

            <!--</div>-->
            <!--<div v-show="isshow2">-->
            <!--<listaircraft v-bind:companyId="infodata"></listaircraft>-->
            <!--</div>-->
            <!--</div>-->
            <div>
                <div class="tit active div-list-aircraftdata" @click="showinfo('show5')">
                    <div class="name">
                        <div class="checkbox">
                            <input type="checkbox" name="toplist">
                            <span></span>
                        </div>
                        <img src="../../../assets/产权机场列表.png" class="logoimgcompany">产权机场列表
                        <span class="fr">
                    <Icon :type="islogshow5? 'plus':'minus'"></Icon>
                </span>
                    </div>

                </div>
                <div v-show="isshow5">
                    <airportlist v-bind:companyid="infodata" v-on:apemit="getapid"></airportlist>
                </div>
            </div>
            <div>
                <div class="tit active div-list-aircraftdata" @click="showinfo('show6')">
                    <div class="name">
                        <div class="checkbox">
                            <input type="checkbox" name="toplist">
                            <span></span>
                        </div>
                        <img src="../../../assets/产权起降点列表.png" class="logoimgcompany">产权起降点列表
                        <span class="fr">
                    <Icon :type="islogshow6? 'plus':'minus'"></Icon>
                </span>
                    </div>

                </div>

                <div v-show="isshow6">
                    <landingpointlist v-bind:companyid="infodata" v-on:landingpointemit="getlandingpointid"></landingpointlist>
                </div>
            </div>
            <div>
                <div class="tit active div-list-aircraftdata" @click="showinfo('show7')">
                    <div class="name">
                        <div class="checkbox">
                            <input type="checkbox" name="toplist">
                            <span></span>
                        </div>
                        <img src="../../../assets/产权飞行基地列表.png" class="logoimgcompany">产权飞行基地列表
                        <span class="fr">
                    <Icon :type="islogshow7? 'plus':'minus'"></Icon>
                </span>
                    </div>

                </div>
                <div v-show="isshow7">
                    <flybaselist v-bind:companyid="infodata" ref="jb" v-on:fbemit="getfbid"></flybaselist>
                </div>
            </div>
            <div>
                <div class="tit active div-list-aircraftdata" @click="showinfo('show8')">
                    <div class="name">
                        <div class="checkbox">
                            <input type="checkbox" name="toplist">
                            <span></span>
                        </div>
                        <img src="../../../assets/产权维修基地列表.png" class="logoimgcompany">产权维修基地列表
                        <span class="fr">
                    <Icon :type="islogshow8? 'plus':'minus'"></Icon>
                </span>
                    </div>
                </div>
                <div v-show="isshow8">
                    <maintainbaselist v-bind:companyid="infodata" v-on:mbemit="getmbid"></maintainbaselist>
                </div>
            </div>
        </div>

        <div class="rigtht-1">
            <div  class="gacompany-div" style="float: right; text-align: center; padding-bottom: 10px; padding-top: -1px; margin-left: 200px;">
                <button class="div-button" style="width: 100px;margin-bottom: 5px" v-show="backbnt" @click="backcompanysup()" >返  回</button>
            </div>
            <div>
                <landingpointdetail v-bind:landingpointid="id" v-show="lpdetailshow" ref="lpdetailref"></landingpointdetail>
                <flybasedetail v-bind:flybaseid="id" v-show="fbdetailshow" ref="fbdetailref"></flybasedetail>
                <maintainbasedetail v-bind:maintainbaseid="id" v-show="mbdetailshow" ref="mbdetailref"></maintainbasedetail>
            </div>
        </div>

    </div>
</template>
<script>
    import gacompany from '../../views/company/gacompanydetail'
    import companyqualification from '../../views/company/companyqualification'
    import companylist from '../../views/company/list-company'
    import pilotlist from '../../views/company/pilotlist'
    import listaircraft from '../../views/company/list-aircraft'
    import airportlist from '../../views/company/airportlist'
    import flybaselist from '../../views/company/flybaselist'
    import landingpointlist from '../../views/company/landingpointlist'
    import maintainbaselist from '../../views/company/maintainbaselist'
    import maintainbasedetail from '../../views/company/maintainbasedetail'
    import landingpointdetail from '../../views/company/landingpointdetail'
    import flybasedetail from '../../views/company/flybasedetail'

    export default {
        data () {
            return {
                companysuplistshow:true,
                lpdetailshow:false,
                mbdetailshow:false,
                fbdetailshow:false,
                backbnt:false,
                id:'',
                imgds: "../../../imgs/jiantou1.png",
                isshow1: true,
                isshow2: false,
                isshow3: false,
                isshow4: false,
                isshow5: false,
                isshow6: false,
                isshow7: false,
                isshow8: false,
                infodata: "",
                islogshow1: false,
                islogshow2: true,
                islogshow3: true,
                islogshow4: true,
                islogshow5: true,
                islogshow6: true,
                islogshow7: true,
                islogshow8: true,
                companytype: {},

            }
        },
        components: {
            gacompany,
            companylist,
            companyqualification,
            pilotlist,
            listaircraft,
            airportlist,
            flybaselist,
            landingpointlist,
            maintainbaselist,
            maintainbasedetail,
            landingpointdetail,
            flybasedetail
        },
        mounted: function () {
            let self = this;
            let ids=self.$route.query.id;
            self.infodata=ids;
            //self.$refs.jb.getData(ids);
        },

        methods: {
            getlandingpointid:function (childid) {
                let self=this;
                self.id=childid;
                self.companysuplistshow=false;
                self.lpdetailshow=true;
                self.backbnt=true;
                self.$refs.lpdetailref.getData(self.id);
            },
            getmbid:function(childid)
            {
                let self=this;
                self.id=childid;
                self.companysuplistshow=false;
                self.mbdetailshow=true;
                self.backbnt=true;
                self.$refs.mbdetailref.getData(self.id);
            },
            getfbid:function(childid)

            {
                let self=this;
                self.id=childid;
                self.companysuplistshow=false;
                self.fbdetailshow=true;
                self.backbnt=true;
                self.$refs.fbdetailref.getData(self.id);
            },
            getapid:function(childid)
            {
                let self=this;
                self.id=childid;

            },
            backcompanysup()
            {
                this.companysuplistshow=true;
                this.backbnt=false;
                this.lpdetailshow=false;
                this.mbdetailshow=false;
                this.fbdetailshow=false;
            },
            showinfo: function (msg) {
                if (msg === 'show1') {
                    if (this.isshow1 == false) {
                        this.isshow1 = true;
                        this.islogshow1 = false;
                    } else {
                        this.isshow1 = false;
                        this.islogshow1 = true;
                    }
                }
                if (msg == 'show2') {
                    if (this.isshow2 == false) {
                        this.isshow2 = true;
                        this.islogshow2 = false;
                    } else {
                        this.isshow2 = false;
                        this.islogshow2 = true;
                    }
                }
                if (msg == 'show3') {
                    if (this.isshow3 == false) {
                        this.isshow3 = true;
                        this.islogshow3 = false;
                    } else {
                        this.isshow3 = false;
                        this.islogshow3 = true;
                    }
                }
                if (msg == 'show4') {
                    if (this.isshow4 == false) {
                        this.isshow4 = true;
                        this.islogshow4 = false;
                    } else {
                        this.isshow4 = false;
                        this.islogshow4 = true;
                    }
                }
                if (msg == 'show5') {
                    if (this.isshow5 == false) {
                        this.isshow5 = true;
                        this.islogshow5 = false;
                    } else {
                        this.isshow5 = false;
                        this.islogshow5 = true;
                    }
                }
                if (msg == 'show6') {
                    if (this.isshow6 == false) {
                        this.isshow6 = true;
                        this.islogshow6 = false;
                    } else {
                        this.isshow6 = false;
                        this.islogshow6 = true;
                    }
                }
                if (msg == 'show7') {
                    if (this.isshow7 == false) {
                        this.isshow7 = true;
                        this.islogshow7 = false;
                    } else {
                        this.isshow7 = false;
                        this.islogshow7 = true;
                    }
                }
                if (msg == 'show8') {
                    if (this.isshow8 == false) {
                        this.isshow8 = true;
                        this.islogshow8 = false;
                    } else {
                        this.isshow8 = false;
                        this.islogshow8 = true;
                    }
                }

            }
//            childinfoid: function (msg) {
//                this.infodata = msg;
//                this.backbnt=false;
//                this.companysuplistshow=true;
//                this.lpdetailshow=false;
//                this.mbdetailshow=false;
//                this.fbdetailshow=false;
//                var comid = this.$route.query.companyId;
//                console.log(comid);
//                if (typeof (comid) == 'number') {
//                    console.log(this.$route);
//                    this.$router.push({
////                        path: '/for_weatherlicenseshow',
//                        query: {}
//                    });
//                    this.infodata = comid;
//                }
//
//            }
        },


    }
</script>





