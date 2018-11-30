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
    .tit {
        position: relative;
    }
    .tit {
        padding: 5px 7px 5px 8px;
        border: 1px solid #d6d6d6;
        font-size: 14px;
        color: rgba(255,255,255,.85);
        border-radius: 1px;
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
    .left-1{
        float: left;
    }
    .rigtht-1{
        float: left;
        margin-left: 15px;
    }
    .tr-6-air{
        text-align: center;
        font-size: 13px;
    }
    .logoimgaircraft{
        height: 19px;
        display: inline;
        /* padding: 3px; */
        vertical-align: middle;
        padding: 1px 2px;
    }
</style>
<template>

    <div>
        <div class="rigtht-1">

            <div>
                <div class="tit active div-list-aircraftdata" @click="islogshow1=!islogshow1">
                    <div class="name">
                        <div class="checkbox">
                            <input type="checkbox" name="toplist">
                            <span></span>
                        </div>
                        <img src="../../../assets/基本信息.png" class="logoimgaircraft">基本信息
                        <span class="fr">
                    <Icon :type="islogshow1? 'minus':'plus'"></Icon>
                </span>
                    </div>

                </div>
                <div v-show="islogshow1">
                    <aircraftinfo v-bind:idinfobase="infodata"  ></aircraftinfo>
                </div>
            </div>

            <div>
                <div class="tit active div-list-aircraftdata" @click="showinfo('isshow2')" >
                    <div class="name">
                        <div class="checkbox">
                            <input type="checkbox" name="toplist">
                            <span></span>
                        </div>
                        <img src="../../../assets/飞行记录列表.png" class="logoimgaircraft">适航信息
                        <span class="fr">
                         <Icon :type="islogshow2? 'plus':'minus'"></Icon>
                </span>
                    </div>

                </div>
                <div v-show="isshow2">
                    <airworthiness v-bind:idinfobase="infodata"></airworthiness>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import airworthiness from '../../pages/aircraft/airworthiness'
    import aircraftinfo from '../../pages/aircraft/aircraftinfo'
    export default {
        data () {
            return {
                imgds:"../../../imgs/jiantou1.png",
                isshow1:true,
                isshow2:true,
                isshow3:false,
                isshow4:false,
                isshow5:false,
                infodata:"",
                islogshow1:false,
                islogshow2:false,
                islogshow3:true,
                islogshow4:true,
                islogshow5:true,
                aircraftclassnum:0,
                fixedwing:0,
                rotarywing:0,
                glider:0,
                extra:0
            }
        },
        mounted (){
            let msg=this.$route.query.id;
            this.infodata=msg;
        },
        components:{
            airworthiness,aircraftinfo
        },
        methods: {
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

            }
        }


    }
</script>





