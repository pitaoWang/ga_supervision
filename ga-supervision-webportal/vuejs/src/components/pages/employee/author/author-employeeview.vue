<template>
    <div class="container">
        <div class="rigtht-1" v-show="politdetail"  style="float: left;margin-top: 15px;">
            <p-detail v-bind:employeeId="employeeId" v-on:childinfotypeid="typeidfunction"></p-detail>
        </div>
        <div class="rigtht-1" v-show="politdetail1"  style="float: left">
            <div  class="gacompany-div" style="text-align: center;font-size: 14px; padding-top: -1px;float: right;margin-right: 5px">
                <button class="div-button" style="width: 100px;height:34px;margin-top: 5px;margin-bottom: 10px;background-color: rgb(1,107,217);color: #fff;"  @click="backcompanysup()" >返  回</button>
            </div>
            <div>
                <traininfo v-show="traininfoshow" :id="traininfoid"></traininfo>
                <traincertificate v-show="traincertificateshow" :id="traincertificateId"></traincertificate>
                <personexamrecinfo v-show="showpersonexam" :id="personexamid"></personexamrecinfo>
                <physicalrecinfo v-show="showphysicalrecord" :id="physicalrecid"></physicalrecinfo>
                <weatherlicense v-show="weatherlicenseshow" :licenseId="licenseId" :licensetype="licensetype"></weatherlicense>
                <flightdispatcher v-show="showflightdispatcher" :licenseId="licenseId"></flightdispatcher>
                <intelligenceslice v-show="showintelligence" :licenseId="licenseId"></intelligenceslice>

                <flylicense v-bind:licenseId="licenseId" v-show="showflylicense"></flylicense>
                <aircraftpartrepair v-show="showaircraftpartrepair" ref="aircraftpartrepair1" :licenseId="licenseId"></aircraftpartrepair>
                <aircraftrepair v-show="showaircraftrepair" ref="aircraftrepair1" :licenseId="licenseId"></aircraftrepair>
                <flightrecord v-show="showflightrecord" :id="flightrecordId"></flightrecord>
            </div>
        </div>
    </div>
</template>

<script>

    import pList from '../../../views/pilotservicelist.vue';
    import pDetail from './employeedetail.vue';
    import traininfo from './traininfoshow1.vue';
    import traincertificate from './traincertificateshow.vue';
    import personexamrecinfo from './personexamrecinfo.vue';
    import physicalrecinfo from './physicalrecinfo.vue';
    import weatherlicense from './weatherlicenseshow.vue';
    import flightdispatcher from './flightdisplicenseinfo.vue';
    import intelligenceslice from './intelligenceliceinfo.vue';
    import flylicense from './flylicenseview.vue';
    import aircraftpartrepair from './aircraftpartrepairlicensedetail.vue';
    import aircraftrepair from './aircraftrepairlicensedetail.vue';
    import flightrecord from './flightimplementrecordshow.vue';

    export default {
        components: {
            pList,
            pDetail,
            traininfo,
            traincertificate,
            personexamrecinfo,
            physicalrecinfo,
            weatherlicense,
            flightdispatcher,
            intelligenceslice,
            flylicense,
            aircraftpartrepair,
            aircraftrepair,
            flightrecord
        },
        data(){
            return {
                traininfoid:'',
                traincertificateId:'',
                personexamid:'',
                physicalrecid:'',
                licenseId:'',
                licensetype:'',
                politdetail:true,
                politdetail1:false,
                traininfoshow:false,
                traincertificateshow:false,
                showpersonexam:false,
                showphysicalrecord:false,
                weatherlicenseshow:false,
                showflightdispatcher:false,
                showintelligence:false,
                showflylicense:false,
                showaircraftpartrepair:false,
                showaircraftrepair:false,
                showflightrecord:false,
            }
        },
        props:['employeeId'],
        methods: {
            typeidfunction(val1,val2,val3){

                if(val2=="Qulification"){
                    if(val3=="飞行执照") {
                        this.politdetail = false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.showflightdispatcher=false;
                        this.showintelligence=false;
                        this.showflylicense=true;
                        this.showaircraftpartrepair=false;
                        this.showaircraftrepair=false;
                        this.showflightrecord=false;

                    }
                    else if(val3=="情报执照") {
                        this.politdetail = false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.showflightdispatcher=false;
                        this.showintelligence=true;
                        this.showflylicense=false;
                        this.showaircraftpartrepair=false;
                        this.showaircraftrepair=false;
                        this.showflightrecord=false;

                    }
                    else if(val3=="飞行签派员执照") {
                        this.politdetail = false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.showflightdispatcher=true;
                        this.showintelligence=false;
                        this.showflylicense=false;
                        this.showaircraftpartrepair=false;
                        this.showaircraftrepair=false;
                        this.showflightrecord=false;

                    }
                    else if(val3=="航空器维修执照") {
                        this.politdetail = false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.showflightdispatcher=false;
                        this.showintelligence=false;
                        this.showflylicense=false;
                        this.showaircraftpartrepair=false;
                        this.showaircraftrepair=true;
                        this.showflightrecord=false;

                    }
                    else if(val3=="航空器部件维修执照") {
                        this.politdetail = false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.showflightdispatcher=false;
                        this.showintelligence=false;
                        this.showflylicense=false;
                        this.showaircraftpartrepair=true;
                        this.showaircraftrepair=false;
                        this.showflightrecord=false;

                    }
                    else if(val3=="气象执照") {
                        this.politdetail = false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=true;
                        this.showflightdispatcher=false;
                        this.showintelligence=false;
                        this.showflylicense=false;
                        this.showaircraftpartrepair=false;
                        this.showaircraftrepair=false;
                        this.showflightrecord=false;

                    }
                    this.licenseId=val1;

//                    this.$refs.flylicenseref.getLicenseInfo(self.licenseId);
//                    self.licensetype=val3;
                }
                else if(val2=="traincertificate"){
                    this.politdetail = false;
                    this.politdetail1 = true;
                    this.traininfoshow=false;
                    this.traincertificateshow=true;
                    this.showpersonexam=false;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=false;
                    this.traincertificateId=val1;
                }
                else if(val2=="traininfo"){
                    this.politdetail = false;
                    this.politdetail1 = true;
                    this.traininfoshow=true;
                    this.traincertificateshow=false;
                    this.showpersonexam=false;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=false;
                    this.traininfoid=val1;
                }
                else if(val2=="physicalrecinfo"){
                    this.politdetail = false;
                    this.politdetail1 = true;
                    this.traininfoshow=false;
                    this.traincertificateshow=false;
                    this.showpersonexam=false;
                    this.showphysicalrecord=true;
                    this.weatherlicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=false;
                    this.physicalrecid=val1;
                }
                else if(val2=="personexamrecinfo"){
                    this.politdetail = false;
                    this.politdetail1 = true;
                    this.traininfoshow=false;
                    this.traincertificateshow=false;
                    this.showpersonexam=true;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=false;

                    this.personexamid=val1;
                }

            },
            backcompanysup(){
                this.politdetail = true;
                this.politdetail1 = false;
            }
        },
        watch: {
            'politdetail1': function (val) {
                this.$emit('tofather1', val);

            }
        }
    }
</script>

<style>
    .container{ overflow: hidden;}

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
        background-image: url(../../../../../static/images/icon-font50.png);
    }
    .icon-font.icon51 {
        background-image: url(../../../../../static/images/icon-font51.png);
    }
    .icon-font.icon52 {
        background-image: url(../../../../../static/images/icon-font52.png);
    }
    .checkbox input[type='checkbox'] {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
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


    .div-heard-1{
        width: 1009px;
        margin-bottom: 10px;
    }
    .topBar li {
        padding-right: 35px;
        height: 60px;
        background: url(../../../../../static/images/topBarright.png) center right no-repeat;
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

    .div-list-aircraftdata1-author{
        width: 965px;
        height: 34px;
        /*background: #272727;*/
        font-size: 16px;
        color: white;
        /*padding-top: 7px;*/
        border: solid 1px #1879dc;
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

</style>