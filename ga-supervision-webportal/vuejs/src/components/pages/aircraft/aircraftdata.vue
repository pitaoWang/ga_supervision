<style>
    .div-head-1{
        width: 1009px;
        margin-bottom: 16px;
        margin-top: 10px;

    }
    .topBarr li {
        padding-right: 30px;
        height: 60px;
        background: url(../../../../static/images/topBarright.png) center right no-repeat;
    }
    .topBarr {
        display: flex;
        justify-content: space-between;
    }
     @font-face {
         font-family: "FZDahei";
         src: url(../../../assets/方正大黑简体.ttf) format("truetype");
     }
     .topBarr li label.item {
        margin: 0 5px;
        font-size: 17px;
        font-family: "FZDahei";
         text-shadow:rgb(0,65,131) 2px 0 0,rgb(0,65,131) 0 2px 0,rgb(0,65,131) -2px 0 0,rgb(0,65,131) 0 -2px 0;
         color: #fff;
        font-style: italic;
        line-height: 60px;
        vertical-align: middle;
    }
    .topBarr li label.item .radio {
        width: 19px;
        height: 19px;
        border:1px solid #000;
        background-color: #fff;
        margin-right: 5px;
        margin-top: 20px;
        float:left;
    }

    .div-list-aircraftdato{
        width: 716px;
        height: 34px;
        background-color: rgba(255,255,255,0.65) ;
        border:1px solid rgb(1,107,217);
        font-size: 16px;
        color: rgb(1,107,217);
        margin-bottom: 4px;
    }
    .left-1{
        float: left;
    }
    .rigtht-1{
        float: left;
    }
    .div-list-aircraftdato .logoimgaircraft{
         width: 34px;
         float:left;
         vertical-align: middle;
     }
    .name-text{
        float:left;
        margin-left: 16px;
        line-height: 34px
    }
    .name-img{
        float: right;
        margin-right: 11px;
    }
    .div-button{
        height: 34px;
        margin-left: 7px;
        width: 80px;
        background: #026bd9;
        border: none;
        font-size:15px;
        border-radius: 5px;
        cursor: pointer;
    }
</style>
<template>
    <div>
        <div class="div-head-1">
            <ul class="topBarr">
                <li>
                    <label class="item">
                        航空器总数 {{counts.aircraftclassnum}}
                    </label>
                </li>
                <li>
                    <label class="item">
                        固定翼 {{counts.fixedwing}}
                    </label>
                </li>
                <li>
                    <label class="item">

                        旋翼 {{counts.rotarywing}}
                    </label>
                </li>
                <li>
                    <label class="item">
                        滑翔机 {{counts.glider}}
                    </label>
                </li>
                <li>
                    <label class="item">
                        其它 {{counts.extra}}
                    </label>
                </li>
            </ul>
        </div>
        <div class="left-1">
            <aircraftlist v-on:child-info-id="childinfoid" :counts="counts"></aircraftlist>
        </div>
        <div style="float: left" v-show="allinfo">
            <div>
                <div class=" active div-list-aircraftdato" @click="islogshow1=!islogshow1">
                    <div class="name" >
                        <div class="logoimgaircraft">
                            <img src="../../../assets/基本信息.png"  >
                        </div >
                        <div class="name-text">
                            基本信息
                        </div>
                        <div class="name-img">
                            <img v-if="islogshow1" src="../../../assets/减号.png" style="margin-top: 15px">
                            <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                        </div>

                    </div>
                </div>
                <div v-show="islogshow1">
                    <aircraftinfo v-bind:idinfobase="infodata" ref="aircraftinforef"></aircraftinfo>
                </div>
            </div>
            <div>
                <div class=" active div-list-aircraftdato" @click="islogshow2=!islogshow2">
                    <div class="name" >
                        <div class="logoimgaircraft">
                            <img src="../../../assets/适航信息.png"  >
                        </div >
                        <div class="name-text">
                            适航信息
                        </div>
                        <div class="name-img">
                            <img v-if="islogshow2" src="../../../assets/减号.png" style="margin-top: 15px">
                            <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                        </div>

                    </div>
                </div>
                <div v-show="islogshow2">
                    <airworthiness v-bind:idinfobase="infodata" ref="airworthinessref"></airworthiness>
                </div>
            </div>
            <div>
                <div class=" active div-list-aircraftdato" @click="islogshow3=!islogshow3" >
                    <div class="name" >
                        <div class="logoimgaircraft">
                            <img src="../../../assets/航空器检查信息.png"  >
                        </div >
                        <div class="name-text">
                            检查信息列表
                        </div>
                        <div class="name-img">
                            <img v-if="islogshow3" src="../../../assets/减号.png" style="margin-top: 15px">
                            <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                        </div>
                    </div>
                </div>
                <div v-show="islogshow3">
                    <div data-v-04d26170="" class="cut" style="" >
                        <checkinfolist  :aircraftid="aircraftid" v-on:childinfobycheckinfoid="getcheckinfoid"></checkinfolist>
                    </div>
                </div>
            </div>
            <div>
                <div class="active div-list-aircraftdato" @click="islogshow4=!islogshow4" >
                    <div class="name" >
                        <div class="logoimgaircraft">
                            <img src="../../../assets/飞行记录列表.png"  >
                        </div >
                        <div class="name-text">
                            飞行记录列表
                        </div>
                        <div class="name-img">
                            <img v-if="islogshow4" src="../../../assets/减号.png" style="margin-top: 15px">
                            <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                        </div>
                    </div>

                </div>
                <div v-show="islogshow4">
                    <div data-v-04d26170="" class="cut" style="" >
                        <share  :aircraftid="aircraftid" v-show="share" v-on:childinfobyflyrecordid="getflyrecordid"></share>
                    </div>
                </div>
            </div>
        </div>
        <div class="rigtht-1" v-show="buttonbackshow"  style="float: left;">
            <div class="gacompany-div" style=" text-align: center; padding-bottom: 10px; padding-top: -1px; margin-left: 640px;">
                <button class="div-button" style="float: right;width: 100px;margin-bottom: 15px;background: rgb(1, 107, 217);"  @click="backcompanysup()" >返  回</button>
            </div>
            <div>
                <flightrecord v-show="showflightrecord" :id="flightrecordId" ref="placeflightrecord"></flightrecord>
                <checkinfodata v-show="showcheckinfo" :id="checkinfoid"></checkinfodata>
            </div>
        </div>

    </div>
</template>
<script>
    import airworthiness from './airworthiness';
    import aircraftinfo from './aircraftinfo';
    import aircraftlist from './list-aircraft';
    import share from '../../views/shareflightrecords.vue';
    import flightrecord from '../../views/flightimplementrecordshow.vue';
    import checkinfolist from '../../views/aircraftcheckinfolist.vue'
    import checkinfodata from '../../views/aircraftcheakinfodata.vue'
    export default {
        data () {
            return {
                buttonbackshow:false,
                flightrecordId:'',
                checkinfoid:'',
                allinfo:true,
                showflightrecord:false,
                showcheckinfo:false,
                imgds:"../../../imgs/jiantou1.png",
                aircraftid:'',
                share:true,
                infodata:"",
                islogshow1:false,
                islogshow2:false,
                islogshow3:false,
                islogshow4:false,
                counts:{
                    aircraftclassnum:0,
                    fixedwing:0,
                    rotarywing:0,
                    glider:0,
                    extra:0,
                },

                }
        },
        created (){
           this.getaircraftclass();
        },
        components:{
            airworthiness,aircraftlist,aircraftinfo,share,
            flightrecord,checkinfolist,checkinfodata
        },
        methods: {
            getflyrecordid(mesg){
                this.buttonbackshow=true;
                this.allinfo=false;
                this.showflightrecord=true;
                this.showcheckinfo=false;
                this.flightrecordId=mesg;
            },
            getcheckinfoid(mesg){
                this.buttonbackshow=true;
                this.allinfo=false;
                this.showflightrecord=false;
                this.showcheckinfo=true;
                this.checkinfoid=mesg;
            },
            backcompanysup(){
                this.buttonbackshow=false,
                this.allinfo=true;
                this.showflightrecord=false;
                this.showcheckinfo=false;
                this.$refs.placeflightrecord.open.base_info_open1=false;
                this.$refs.placeflightrecord.open.base_info_open2=false;
                this.$refs.placeflightrecord.open.base_info_open3=false;
            },

            getaircraftclass(){
                let self = this;
                self.$http.httpGet('/app/api/aircraft/search/findaircraftclassbycompany',{
                    companyIds: this.$profile.getStoredProfile().buz.companyId,
                }).then((res) => {
                    //获取固定翼的数量
                    for(let line of res.data){
                        if(line[0] == "固定翼"){
                            self.counts.fixedwing = line[1];
                        }
                        else if(line[0] == "旋翼"){
                            self.counts.rotarywing = line[1];
                        }
                        else if(line[0] == "滑翔机"){
                            self.counts.glider = line[1];
                        }
                        else{
                            self.counts.extra = line[1];
                        }
                    }
                    self.counts.aircraftclassnum = self.counts.fixedwing+self.counts.rotarywing+self.counts.glider+self.counts.extra;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            showinfo:function (msg) {
              if(msg=='isshow1'){
                  if(this.isshow1==false){
                      this.isshow1=true;
                      this.islogshow1=false;
                  }else{
                      this.isshow1=false;
                      this.islogshow1=true;
                  }
              }
                if(msg=='isshow2'){
                    if(this.isshow2==false){
                        this.isshow2=true;
                        this.islogshow2=false;
                    }else{
                        this.isshow2=false;
                        this.islogshow2=true;
                    }
                }
                if(msg=='isshow3'){
                    if(this.isshow3==false){
                        this.isshow3=true;
                        this.islogshow3=false;
                    }else{
                        this.isshow3=false;
                        this.islogshow3=true;
                    }
                }
                if(msg=='isshow4'){
                    if(this.isshow4==false){
                        this.isshow4=true;
                        this.islogshow4=false;
                    }else{
                        this.isshow4=false;
                        this.islogshow4=true;
                    }
                }
                if(msg=='isshow5'){
                    if(this.isshow5==false){
                        this.isshow5=true;
                        this.islogshow5=false;
                    }else{
                        this.isshow5=false;
                        this.islogshow5=true;
                    }
                }

            },
            childinfoid:function (msg,jd) {
              //  alert(msg)
                this.aircraftid=msg;
                this.$refs.aircraftinforef.getData(msg);
                this.$refs.airworthinessref.getData(msg)
               // this.infodata=msg;
            }

        }
        

    }
</script>





