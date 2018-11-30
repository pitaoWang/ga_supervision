<template>

    <div class="container" style="width: 1280px; height: auto; margin-top: 30px; background:white">
        <div v-show="politdetail1s"  style="float: right;">
            <div>
                <button class="div-button" style="width:88px; height:30px; border-radius:4px; border:1px solid #33A6FC; background-color: #FFFFFF; margin-bottom: -80px; color:#33A6FC; cursor:pointer; margin-top: 20px; margin-right: 50px"
                        @click="backcompanysups()" >返  回</button>
            </div>
        </div>

        <div v-show="politdetail"  style="float: left; margin-bottom:30px;">
            <p-detail v-bind:employeeId="employeeId" v-on:childinfotypeid="typeidfunction"></p-detail>
        </div>

        <div class="rigtht-1" v-show="politdetail1"  style="float: left;">
            <div style = "width: 1190px;">
                <button class="div-button" style="width:88px; height:30px; border-radius:4px; border:1px solid #33A6FC; background-color: #FFFFFF; color:#33A6FC; float: right; cursor:pointer; margin-top: 20px; margin-bottom: 10px; margin-right:-40px"
                        @click="backcompanysup()" >返  回</button>
            </div>
            <div>
                <!-- 雇员培训详细信息 -->
                <traininfo v-show="traininfoshow" :id="traininfoid"></traininfo>
                <!--雇员证书详细信息 -->
<!--                <traincertificate v-show="traincertificateshow" :id="traincertificateId"></traincertificate> -->
                <!--熟练检查详细信息 -->
                <skilledcheckinfo v-show="skilledcheckinfoshow" :id="skilledcheckinfoId"></skilledcheckinfo>
                <!--更新检查详细信息 -->
                <updatecheckinfo v-show="updatecheckinfoshow" :id="updatecheckinfoId"></updatecheckinfo>
                <!--定期检查详细信息-->
                <periodicalcheckinfo v-show="periodicalcheckinfoshow" :id="periodicalcheckinfoId"></periodicalcheckinfo>
                <!--雇员考试详细信息-->
                <personexamrecinfo v-show="showpersonexam" :id="personexamid"></personexamrecinfo>
                <!--雇员体检详细信息-->
                <physicalrecinfo v-show="showphysicalrecord" :id="physicalrecid"></physicalrecinfo>
                <!--气象执照详细信息-->
                <weatherlicense v-show="weatherlicenseshow" :licenseId="licenseId" :licensetype="licensetype"></weatherlicense>
                <!--管制人员执照详细信息-->
                <controllicense v-show="controllicenseshow" :licenseId="licenseId" ></controllicense>
                <!--飞行签派员执照详细信息-->
                <flightdispatcher v-show="showflightdispatcher" :licenseId="licenseId"></flightdispatcher>
                <!--情报执照详细信息-->
                <intelligenceslice v-show="showintelligence" :licenseId="licenseId"></intelligenceslice>
                <!--飞行执照详细信息-->
                <flylicense v-bind:licenseId="licenseId" v-show="showflylicense"></flylicense>
                <!--航空器部件维修执照详细信息-->
                <aircraftpartrepair v-show="showaircraftpartrepair" ref="aircraftpartrepair1" :licenseId="licenseId"></aircraftpartrepair>
                <!--航空器维修执照详细信息-->
                <aircraftrepair v-show="showaircraftrepair" ref="aircraftrepair1" :licenseId="licenseId"></aircraftrepair>
                <!--飞行记录详细信息-->
                <flightrecord v-show="showflightrecord" :id="flightrecordId"></flightrecord>
            </div>
        </div>
    </div>
</template>

<script>
  
    import pList from '../../views/pilotlist.vue';
    import pDetail from '../../views/pilotdetail.vue';
    import traininfo from '../../views/traininfoshow1.vue';
    import traincertificate from '../../views/traincertificateshow.vue';
    import personexamrecinfo from '../../views/personexamrecinfo.vue';
    import skilledcheckinfo from '../../views/skilledcheckinfo.vue';
    import updatecheckinfo from '../../views/updatecheckinfo.vue';
    import periodicalcheckinfo from '../../views/periodicalcheckinfo.vue';
    import physicalrecinfo from '../../views/physicalrecinfo.vue';
    import weatherlicense from '../../views/weatherlicenseshow.vue';
    import controllicense from '../../views/controllicense.vue';
    import flightdispatcher from '../../views/flightdisplicenseinfo.vue';
    import intelligenceslice from '../../views/intelligenceliceinfo.vue';
    import flylicense from '../../views/flylicenseview.vue';
    import aircraftpartrepair from '../../views/aircraftpartrepairlicensedetail.vue';
    import aircraftrepair from '../../views/aircraftrepairlicensedetail.vue';
    import flightrecord from '../../views/flightimplementrecordshow.vue';

    export default {
        components: {
            pList,
            pDetail,
            traininfo,
            traincertificate,
            personexamrecinfo,
            skilledcheckinfo,
            updatecheckinfo,
            periodicalcheckinfo,
            physicalrecinfo,
            weatherlicense,
            controllicense,
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
                skilledcheckinfoId:'',
                updatecheckinfoId:'',
                periodicalcheckinfoId:'',
                physicalrecid:'',
                licenseId:'',
                licensetype:'',
                employeeId:'',
                politdetail1s:true,
                politdetail:true,
                politdetail1:false,
                traininfoshow:false,
                traincertificateshow:false,
                showpersonexam:false,
                skilledcheckinfoshow:false,
                updatecheckinfoshow:false,
                periodicalcheckinfoshow:false,
                showphysicalrecord:false,
                weatherlicenseshow:false,
                controllicenseshow:false,
                showflightdispatcher:false,
                showintelligence:false,
                showflylicense:false,
                showaircraftpartrepair:false,
                showaircraftrepair:false,
                showflightrecord:false,
            }
        },mounted: function () {
            let self = this;
            let ids= this.$route.query.user.employeeId;
            self.employeeId=ids;

            //self.$refs.jb.getData(ids);
        },
        watch: {
            '$route' (to, from) {
                Object.assign(this.$data, this.$options.data());
            }

        },
        methods: {

            typeidfunction(val1,val2,val3){

                if(val2=="Qulification"){
                    if(val3=="飞行执照") {
                        this.politdetail = false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                            this.traincertificateshow=false;
                            this.politdetail1s=false;
                            this.showpersonexam=false;
                           this.skilledcheckinfoshow=false;
                        this.updatecheckinfoshow=false;
                        this.periodicalcheckinfoshow=false;
                            this.showphysicalrecord=false;
                            this.weatherlicenseshow=false;
                            this.controllicenseshow=false;
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
                        this.politdetail1s=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.skilledcheckinfoshow=false;
                        this.updatecheckinfoshow=false;
                        this.periodicalcheckinfoshow=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.controllicenseshow=false;
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
                        this.politdetail1s=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.skilledcheckinfoshow=false;
                        this.updatecheckinfoshow=false;
                        this.periodicalcheckinfoshow=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.controllicenseshow=false;
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
                        this.politdetail1s=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.skilledcheckinfoshow=false;
                        this.updatecheckinfoshow=false;
                        this.periodicalcheckinfoshow=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.controllicenseshow=false;
                        this.showflightdispatcher=false;
                        this.showintelligence=false;
                        this.showflylicense=false;
                        this.showaircraftpartrepair=false;
                        this.showaircraftrepair=true;
                        this.showflightrecord=false;

                    }
                    else if(val3=="航空器部件维修执照") {
                        this.politdetail = false;
                        this.politdetail1s=false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.skilledcheckinfoshow=false;
                        this.updatecheckinfoshow=false;
                        this.periodicalcheckinfoshow=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.controllicenseshow=false;
                        this.showflightdispatcher=false;
                        this.showintelligence=false;
                        this.showflylicense=false;
                        this.showaircraftpartrepair=true;
                        this.showaircraftrepair=false;
                        this.showflightrecord=false;

                    }

                    else if(val3=="管制执照") {
                        this.politdetail = false;
                        this.politdetail1s=false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.skilledcheckinfoshow=false;
                        this.updatecheckinfoshow=false;
                        this.periodicalcheckinfoshow=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=false;
                        this.controllicenseshow=true;
                        this.showflightdispatcher=false;
                        this.showintelligence=false;
                        this.showflylicense=false;
                        this.showaircraftpartrepair=false;
                        this.showaircraftrepair=false;
                        this.showflightrecord=false;

                    }

                    else if(val3=="气象执照") {
                        this.politdetail = false;
                        this.politdetail1 = true;
                        this.traininfoshow=false;
                        this.politdetail1s=false;
                        this.traincertificateshow=false;
                        this.showpersonexam=false;
                        this.skilledcheckinfoshow=false;
                        this.updatecheckinfoshow=false;
                        this.periodicalcheckinfoshow=false;
                        this.showphysicalrecord=false;
                        this.weatherlicenseshow=true;
                        this.controllicenseshow=false;
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
                    this.politdetail1s=false;
                    this.traincertificateshow=true;
                    this.showpersonexam=false;
                    this.skilledcheckinfoshow=false;
                    this.updatecheckinfoshow=false;
                    this.periodicalcheckinfoshow=false;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.controllicenseshow=false;
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
                    this.politdetail1s=false;
                    this.traincertificateshow=false;
                    this.showpersonexam=false;
                    this.skilledcheckinfoshow=false;
                    this.updatecheckinfoshow=false;
                    this.periodicalcheckinfoshow=false;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.controllicenseshow=false;
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
                    this.politdetail1s=false;
                    this.traincertificateshow=false;
                    this.showpersonexam=false;
                    this.skilledcheckinfoshow=false;
                    this.updatecheckinfoshow=false;
                    this.periodicalcheckinfoshow=false;
                    this.showphysicalrecord=true;
                    this.weatherlicenseshow=false;
                    this.controllicenseshow=false;
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
                    this.skilledcheckinfoshow=false;
                    this.updatecheckinfoshow=false;
                    this.periodicalcheckinfoshow=false;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.controllicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.politdetail1s=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=false;

                    this.personexamid=val1;
                }

                else if(val2=="skilledcheckinfo"){
                    this.politdetail = false;
                    this.politdetail1 = true;
                    this.traininfoshow=false;
                    this.traincertificateshow=false;
                    this.showpersonexam=false;
                    this.skilledcheckinfoshow=true;
                    this.updatecheckinfoshow=false;
                    this.periodicalcheckinfoshow=false;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.controllicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.politdetail1s=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=false;

                    this.skilledcheckinfoId=val1;
                }

                else if(val2=="periodicalcheckinfo"){
                    this.politdetail = false;
                    this.politdetail1 = true;
                    this.traininfoshow=false;
                    this.traincertificateshow=false;
                    this.showpersonexam=false;
                    this.skilledcheckinfoshow=false;
                    this.updatecheckinfoshow=false;
                    this.periodicalcheckinfoshow=true;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.controllicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.politdetail1s=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=false;

                    this.periodicalcheckinfoId=val1;
                }
                else if(val2=="updatecheckinfo"){
                    this.politdetail = false;
                    this.politdetail1 = true;
                    this.traininfoshow=false;
                    this.traincertificateshow=false;
                    this.showpersonexam=false;
                    this.skilledcheckinfoshow=false;
                    this.updatecheckinfoshow=true;
                    this.periodicalcheckinfoshow=false;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.controllicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.politdetail1s=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=false;

                    this.updatecheckinfoId=val1;
                }
                else if(val2=="flightrecord"){
                    this.politdetail = false;
                    this.politdetail1 = true;
                    this.traininfoshow=false;
                    this.traincertificateshow=false;
                    this.showpersonexam=false;
                    this.skilledcheckinfoshow=false;
                    this.updatecheckinfoshow=false;
                    this.periodicalcheckinfoshow=false;
                    this.politdetail1s=false;
                    this.showphysicalrecord=false;
                    this.weatherlicenseshow=false;
                    this.controllicenseshow=false;
                    this.showflightdispatcher=false;
                    this.showintelligence=false;
                    this.showflylicense=false;
                    this.showaircraftpartrepair=false;
                    this.showaircraftrepair=false;
                    this.showflightrecord=true;

                    this.flightrecordId=val1;
                }

            },
            backcompanysup(){
                this.politdetail = true;
                this.politdetail1 = false;
                this.politdetail1s=true;
            },
            backcompanysups(){
                this.$router.go(-1);
            }
        }
    }
</script>

<style>
   .container{ overflow: hidden;}
.container .leftPart{float: left;margin-left: 36px;margin-right: 16px;}
.container .rightPart{
    float:left;
    margin-left:15px;
}

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
    background-image: url(../../../../static/images/icon-font50.png);
}
.icon-font.icon51 {
    background-image: url(../../../../static/images/icon-font51.png);
}
.icon-font.icon52 {
    background-image: url(../../../../static/images/icon-font52.png);
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


.div-list-aircraftdata1{
    width: 1190px;
    background-color: #DDF4FF;
    height: 46px;
    line-height: 46px;
    margin-left: 40px;
    /*background: #272727;*/
    font-size: 14px;
    color: #8A909F;
    /*padding-top: 7px;*/
   /*border: solid 1px #1879dc;*/
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

/*.btm {*/
    /*margin-top: 20px;*/
    /*text-align: center;*/
/*}*/
/*.div-list{*/
    /*border: solid 1px #ececec;*/
    /*width: 190px;*/
    /*height: 30px;*/
    /*text-align: center;*/
    /*background: #353535;*/
    /*color: white;*/
    /*margin-bottom: 5px;*/
/*}*/

.div-heard{
    background: #cacaca;
    height: 30px;
    width: 300px;
    margin-bottom: 10px;
    text-align: center;
    padding-top: 10px;
}
/*.img-input-page{*/
    /*display: initial;*/
/*}*/
/*.div-input{*/
    /*height: 30px;*/
    /*width: 190px;*/
/*}*/
/*.div-input-input{*/
    /*width: 125px;*/
    /*height: 26px;*/
/*}*/
/*.div-button{*/
    /*height: 30px;*/
    /*margin-left: 7px;*/
    /*width: 50px;*/
    /*background: #b1b1b1;*/
    /*border: none;*/
    /*border-radius: 5px;*/
/*}*/
/*.div-chxun{*/
    /*margin-bottom: 15px;*/
    /*height: 30px;*/
/*}*/
/*.div-1-2{*/
    /*width: 140px;*/
    /*margin: 0 auto;*/
    /*text-align: left;*/
    /*padding-top: 5px;*/
/*}*/
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
/*.com-wrap-1190{ width: 1190px; margin: 0 auto; overflow: hidden;}*/
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
/*.mod .cut.container-center tr:nth-child(2n-1) td{ background-color: #313233;}
.mod .cut.container-center tr td:nth-child(2n){ background-color: #3d3d3d;}*/

</style>