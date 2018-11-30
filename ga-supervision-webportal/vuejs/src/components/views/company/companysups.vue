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

    .checkbox input[type='checkbox'] {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: 2;
        opacity: 0;
    }

    .checkbox {
        width: 20px;
        height: 20px;
        border: 1px solid rgb(94,94,94);
        position: relative;
        border-radius: 1px;
        display: inline-block;
        vertical-align: middle;
        background-color: rgba(255,255,255,0.87);
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

    .ga-div-list-aircraftdata {
        width: 932px;
        height: 34px;
        background: rgb(220, 238, 255);
        font-size: 16px;
        color: rgb(1, 107, 217);
        /*padding-top: 7px;*/
        margin-left: -4px;
        border: solid 1px rgb(1, 107, 217);
        display: inline-block;
        margin-top: 4px;
    }

    .right-1 {
        width: 965px;
    }

    .logoimgcompany {
        height: 34px;
        display: inline-block;
        /* padding: 3px; */
        vertical-align: middle;
        margin-top: 2px;
        /* padding: 1px 2px; */
    }

    .company-airport-btn {
        font-size: 14px;
        margin: 0px 0px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37, 128, 221, 1);
    }

    .gacompany-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37, 128, 221, 1);
    }

    .companysups-name {
        color: rgb(1, 107, 217);
        display: inline-block;
        padding-top: 0px;
        vertical-align: middle;
        padding-left: 16px;
        padding-right: 11px;
        width: 100%;
    }
</style>
<template>
    <div style="width: 965px; margin-left: 12px;">
        <div style="background: #2e82dc;height: 36px;padding-top: 6px;margin: 47px 0px 6px 0px;padding: 6px 0 0 0;" id="companysh">
            <img src="../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
            <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">提交审核</span>
        </div>
        <div class="right-1" v-show="companysuplistshow">
            <div>
                <img src="../../../assets/企业基本信息.png" class="logoimgcompany">
                <div class="ga-div-list-aircraftdata" @click="islogshow1=!islogshow1">

                    <div class="companysups-name">

                        企业基本信息
                        <span class="fr">
                        <Icon :type="islogshow1? 'minus':'plus'"></Icon></span>
                    </div>
                </div>
                <div v-show="islogshow1" class="companycut" style="">
                    <gacompany v-bind:companyId="infodata" ref="garef"></gacompany>
                </div>
            </div>
            <div>
                <img src="../../../assets/法人信息.png" class="logoimgcompany">
                <div class="ga-div-list-aircraftdata" @click="showinfo('show2')">
                    <div class="companysups-name">
                        法人信息
                        <span class="fr">
                    <Icon :type="islogshow2? 'plus':'minus'"></Icon>
                </span>
                    </div>
                </div>
                <div v-show="isshow2" class="companycut" style="">
                    <legalperson v-bind:companyId="infodata" ref="legpref"></legalperson>
                </div>
            </div>
            <div>
                <img src="../../../assets/资质信息.png" class="logoimgcompany">
                <div class="ga-div-list-aircraftdata" @click="showinfo('show3')">
                    <div class="companysups-name">
                        资质信息
                        <span class="fr">
                    <Icon :type="islogshow3? 'plus':'minus'"></Icon>
                </span>
                    </div>
                </div>
                <div v-show="isshow3" class="companycut" style="">
                    <companyqualification v-bind:companyId="infodata" ref="cqref"></companyqualification>
                </div>
            </div>
            <div>
                <img src="../../../assets/产权机场列表.png" class="logoimgcompany">
                <div class="ga-div-list-aircraftdata" @click="showinfo('show5')">
                    <div class="companysups-name">
                        产权机场列表
                        <span class="fr">
                    <Icon :type="islogshow5? 'plus':'minus'"></Icon>
                </span>
                    </div>

                </div>
                <div v-show="isshow5" class="companycut" style="">
                    <airportlist v-bind:companyid="infodata" v-on:airportemit="getairportdataid"
                                 ref="apref"></airportlist>
                </div>
            </div>
            <div>
                <img src="../../../assets/产权起降点列表.png" class="logoimgcompany">
                <div class="ga-div-list-aircraftdata" @click="showinfo('show6')">
                    <div class="companysups-name">
产权起降点列表
                        <span class="fr">
                    <Icon :type="islogshow6? 'plus':'minus'"></Icon>
                </span>
                    </div>

                </div>
                <div v-show="isshow6" class="companycut" style="">
                    <landingpointlist v-bind:companyid="infodata" v-on:landingpointemit="getlandingpointid"
                                      ref="lpref"></landingpointlist>
                </div>
            </div>
            <div>
                <img src="../../../assets/产权飞行基地列表.png" class="logoimgcompany">
                <div class="ga-div-list-aircraftdata" @click="showinfo('show7')">
                    <div class="companysups-name">

                        产权飞行基地列表
                        <span class="fr">
                    <Icon :type="islogshow7? 'plus':'minus'"></Icon>
                </span>
                    </div>

                </div>
                <div v-show="isshow7" class="companycut" style="">
                    <flybaselist v-bind:companyid="infodata" v-on:fbemit="getfbid" ref="fbref"></flybaselist>
                </div>
            </div>
            <div>
                <img src="../../../assets/产权维修基地列表.png" class="logoimgcompany">
                <div class="ga-div-list-aircraftdata" @click="showinfo('show8')">
                    <div class="companysups-name">

                       产权维修基地列表
                        <span class="fr">
                    <Icon :type="islogshow8? 'plus':'minus'"></Icon>
                </span>
                    </div>
                </div>
                <div v-show="isshow8" class="companycut" style="">
                    <maintainbaselist v-bind:companyid="infodata" v-on:mbemit="getmbid" ref="mbref"></maintainbaselist>
                </div>
            </div>
        </div>

        <div class="rigtht-1">
            <div v-show="backbnt" class="gacompany-div"
                 style="float: right; text-align: center; padding-bottom: 10px; padding-top: -1px; margin-left: 200px;">
                <a data-v-942377fa="" href="javascript:;"
                   class="company-airport-btn" @click="backcompanysup">返回</a>
            </div>
            <div>
                <landingpointdetail v-bind:landingpointid="id" v-show="lpdetailshow"
                                    ref="lpdetailref"></landingpointdetail>
                <flybasedetail v-bind:flybaseid="id" v-show="fbdetailshow" ref="fbdetailref"></flybasedetail>
                <maintainbasedetail v-bind:maintainbaseid="id" v-show="mbdetailshow"
                                    ref="mbdetailref"></maintainbasedetail>
                <airportdatadetail v-bind:airportdataid="id" v-show="airportdatashow"
                                   ref="airportdataref"></airportdatadetail>
            </div>
        </div>

    </div>
</template>
<script>
    import gacompany from './gacompanydetail'
    import legalperson from './legalperson'
    import companyqualification from './companyqualification'
    import companylist from './list-company'
    import pilotlist from './pilotlist'
    import listaircraft from './list-aircraft'
    import airportlist from './airportlist'
    import flybaselist from './flybaselist'
    import landingpointlist from './landingpointlist'
    import maintainbaselist from './maintainbaselist'
    import maintainbasedetail from './maintainbasedetail'
    import landingpointdetail from './landingpointdetail'
    import flybasedetail from './flybasedetail'
    import airportdatadetail from '../airport/airportdata'

    export default {
        data () {
            return {
                companysuplistshow: true,
                lpdetailshow: false,
                mbdetailshow: false,
                fbdetailshow: false,
                airportdatashow: false,
                backbnt: false,
                id: '',
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
            legalperson,
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
            flybasedetail,
            airportdatadetail,
        },
        watch: {},
        mounted: function () {
//            let self = this;
//            self.$profile.getProfile().then((profile)=> {
//                    let id = profile.buz.companyId;
//                    if(typeof (id)=='number'){
//                        self.infodata=id;
//                    }
//                },
//                (error) => {
//                    console.log('error', error);
//                });
        },
        methods: {
            getairportdataid: function (childid) {
                let self = this;
                self.id = childid;
                self.companysuplistshow = false;
                self.airportdatashow = true;
                self.backbnt = true;
                self.$emit("emitSubmit", self.companysuplistshow);
                self.$refs.airportdataref.getData(childid);
            },
            ftsde: function (obj) {
                let self = this;
                //self.infodata=0;
                if (typeof (obj) == 'number') {
                    self.infodata = obj;
                    self.$refs.mbref.getData(self.infodata);
                    self.$refs.apref.getData(self.infodata);
                    self.$refs.lpref.getData(self.infodata);
                    self.$refs.fbref.getData(self.infodata);
                    self.$refs.garef.getData(self.infodata);
                    self.$refs.cqref.getqua(self.infodata);
                    self.$refs.legpref.getData(self.infodata);
                }
            },
            getlandingpointid: function (childid) {
                let self = this;
                self.id = childid;
                self.companysuplistshow = false;
                self.$emit("emitSubmit", self.companysuplistshow);
                self.lpdetailshow = true;
                self.backbnt = true;
                self.$refs.lpdetailref.getData(self.id);
            },
            getmbid: function (childid) {
                let self = this;
                self.id = childid;
                self.companysuplistshow = false;
                self.$emit("emitSubmit", self.companysuplistshow);
                self.mbdetailshow = true;
                self.backbnt = true;
                self.$refs.mbdetailref.getData(self.id);
            },
            getfbid: function (childid) {
                let self = this;
                self.id = childid;
                self.companysuplistshow = false;
                self.$emit("emitSubmit", self.companysuplistshow);
                self.fbdetailshow = true;
                self.backbnt = true;
                self.$refs.fbdetailref.getData(self.id);
            },

            backcompanysup()
            {
                this.companysuplistshow = true;

                this.backbnt = false;
                this.lpdetailshow = false;
                this.mbdetailshow = false;
                this.fbdetailshow = false;
                this.airportdatashow = false;
                this.$emit("emitSubmit", this.companysuplistshow);
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

            },
        },
    }
</script>





