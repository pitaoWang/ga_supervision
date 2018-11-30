<style>
    .float-div-buten{
        height: 30px;
        background: silver;
        width: 55px;
        border: none;
        border-radius: 4px;
    }

    .otherinfo-div{
        width: 825px;
        background:#353535;
    }
    .main-others{
        position: relative;
        width: 900px;
        background:#353535;
        padding-top: 30px;
        overflow-y:auto;
        height: 500px;
    }
    .otherinfo-div-1{
        text-align: center;
        color: white;
        background: radial-gradient(circle, black, #353535);
        height: 30px;
        padding-top: 6px;
        width: 500px;
        margin: 0 auto;
    }
    .otherinfo-div-input-1{
        text-align: center;
        margin: 0 auto;
    }
    .otherinfo-div-input{
        background: #b3b3b3;
        height: 30px;
        width: 500px;
        margin-bottom: 20px;
    }
    .otherinfo-button{
        height: 30px;
        width: 100px;
        border: none;
        border-radius: 5px;
    }
    .otherinfo-button-1{
        height: 30px;
        width: 120px;
        border: none;
        border-radius: 5px;
    }
    .otherinfo-div-2{
        height: 5px;
    }
    .otherinfo-3-div{
        text-align: center;
        height: 45px;
        padding-top: 6px;
        margin-bottom: 15px;
    }
    .others-img-model{
        z-index: 20;
        width: 100%;
        height: 50px;
        position: absolute;
        text-align: center;
        background: linear-gradient(rgba(53, 53, 53, 0.45), rgba(0, 0, 0, 0.84));
        bottom: 0px;
        padding-top: 15px;
    }
    .div-img-delete{
        margin-left: 10px;
        display: -webkit-inline-box;
    }
    .main-ent-div{
        height: 500px;
        width: 800px;
        overflow: hidden;
    }
    .fade.in {
        opacity: 1;
    }
    .fade {
        opacity: 0;
        -webkit-transition: opacity .15s linear;
        -o-transition: opacity .15s linear;
        transition: opacity .15s linear;
    }
    .modal.in .modal-dialog {
        -webkit-transform: translate(0,0);
        -ms-transform: translate(0,0);
        -o-transform: translate(0,0);
        transform: translate(0,0);
    }
    .modal.fade .modal-dialog {
        -webkit-transition: -webkit-transform .3s ease-out;
        -o-transition: -o-transform .3s ease-out;
        transition: transform .3s ease-out;
        -webkit-transform: translate(0,-25%);
        -ms-transform: translate(0,-25%);
        -o-transform: translate(0,-25%);
        transform: translate(0,-25%);
    }
    .modal-content {
        -webkit-box-shadow: 0 5px 15px rgba(0,0,0,.5);
        box-shadow: 0 5px 15px rgba(0,0,0,.5);
    }
    .modal-content {
        position: relative;
        background-color: #fff;
        -webkit-background-clip: padding-box;
        background-clip: padding-box;
        border: 1px solid #999;
        border: 1px solid rgba(0,0,0,.2);
        border-radius: 6px;
        outline: 0;
        -webkit-box-shadow: 0 3px 9px rgba(0,0,0,.5);
        box-shadow: 0 3px 9px rgba(0,0,0,.5);
        margin: auto;
    }
    .modal-backdrop.in {
        filter: alpha(opacity=50);
        opacity: .5;
    }
    .modal-backdrop.fade {
        filter: alpha(opacity=0);
        opacity: 0;
    }

    .fade.in {
        opacity: 1;
    }

    .modal-backdrop {
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: 1040;
        background-color: rgba(0, 0, 0, 0.37);
    }

    .fade {
        opacity: 0;
        -webkit-transition: opacity .15s linear;
        -o-transition: opacity .15s linear;
        transition: opacity .15s linear;
    }

</style>
<template>
<div class="main-ent-div">
<div class="main-others">
<div class="otherinfo-div" >

    <div class="modal-backdrop fade in" v-if="isdiv">
        <div class="modal-content" style="width: 1000px;">
          <div v-if="addairport">
          <addaircraft></addaircraft>
          </div>
            <div v-if="addloading">
                <addaircraft></addaircraft>
            </div>
            <div v-if="addflybase">
                <addaircraft></addaircraft>
            </div>
            <div v-if="addmaintain">
                <addaircraft></addaircraft>
            </div>
        </div>
    </div>

    <ul>
        <li v-for="(airport,index) in airports">
          <div class="otherinfo-div-input-1" >
              <input v-model="airport.airportName"  class="otherinfo-div-input" v-on:blur="changeCountairport(airport.airportName)" />
              <img class="div-img-delete" src="../../../assets/delete.png" @click="airportdelete(index)"/>
          </div>
            <div class="otherinfo-div-2"></div>
        </li>
    </ul>
    <div class="otherinfo-3-div">
    <button class="otherinfo-button" @click="showaddairport" >＋添加产权机场</button>
    </div>

    <ul>
        <li v-for="(cto, index) in ctol">
            <div class="otherinfo-div-input-1" >
                <input v-model="cto.name"  class="otherinfo-div-input" v-on:blur="changeCountctol(cto.name)"/>
                <img class="div-img-delete" src="../../../assets/delete.png" @click="ctodelete(index)"/>
            </div>
            <div class="otherinfo-div-2"></div>
        </li>
    </ul>
    <div class="otherinfo-3-div">
        <button class="otherinfo-button" @click="showloading">＋添加产权起降点</button>
    </div>
    <!--<div class="others-img-model" v-if="isshow1">-->
        <!--<img src="../../../../static/img/morejiantou.png" style="display: -webkit-inline-box" @click="shownext"/>-->
    <!--</div>-->
</div>

    <div class="otherinfo-div" v-if="isshow2">
        <ul>
            <li v-for="(flyaddres, index) in flyaddress">
                <div  class="otherinfo-div-input-1">
                    <input v-model="flyaddres.flybasename" v-on:blur="changeCountflyadd(flyaddres.flybasename)"  class="otherinfo-div-input" />
                    <img class="div-img-delete" src="../../../assets/delete.png" @click="flyaddresdelete(index)"/>
                </div>
                <div class="otherinfo-div-2"></div>
            </li>
        </ul>
        <div class="otherinfo-3-div">
            <button class="otherinfo-button-1" @click="showflybase">＋选择/添加飞行基地</button>
        </div>

        <ul>
            <li v-for="(mainta, index) in maintain">
                <div  class="otherinfo-div-input-1">
                <input v-model="mainta.maintainbasename"  class="otherinfo-div-input" v-on:blur="changeCountmaintain(mainta.maintainbasename)"/>
                    <img class="div-img-delete" src="../../../assets/delete.png" @click="maintadelete(index)"/>
                </div>
                <div class="otherinfo-div-2"></div>
            </li>
        </ul>
        <div class="otherinfo-3-div" style="height: 90px;">
            <button class="otherinfo-button-1"  @click="showmaintain">＋选择/添加维修基地</button>
            <div style="text-align: center;margin-top: 20px;">
                <button class="float-div-buten" @click="resetting">重置</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="float-div-buten" @click="save">保存</button>
            </div>
        </div>
    </div>
</div>
 </div>
</template>
<script>
    import addaircraft from './addaircraft'
    export default {
        data () {
            return {
                addairport:false,
                addloading:false,
                addflybase:false,
                addmaintain:false,
                isshow1:true,
                isshow2:true,
                isdiv:false,
               airports:[{
                    airportName: '机场1'},
                   {airportName:'机场2'},
                   {airportName:'机场2'},
                   {airportName:'机场2'},
                   {airportName:'机场2'},
                   {airportName:'机场2'}
                   ],
               ctol:[
                   {name:'起降点1'},
                   {name:'起降点2'},
                   {name:'起降点2'},
                   {name:'起降点2'},
                   {name:'起降点2'},
                   {name:'起降点2'},
                   {name:'起降点2'}
                    ],
                flyaddress:[
                    {flybasename:'飞行基地1'},
                    {flybasename:'飞行基地2'}
                    ],
                maintain:[
                    {maintainbasename:'维修基地1'},
                    {maintainbasename:'维修基地2'}
                ]
            }
        },components:{
            addaircraft
        },
        mounted(){
            //数据的初始化加载
            let self = this;
            self.$axios.get('http://192.168.0.71:18080/app/api/company/pagelist',{
                params: {
                    page: 1,
                    size: 3
                }
            }).then(function (response) {

            })
                .catch(function (error) {
                    console.log(error);
                });
        },
        methods:{
        shownext:function () {
            this.isshow1=false;
            this.isshow2=true;
        },
        lastshow:function () {
            this.isshow2=false;
            this.isshow1=true;
        },
        changeCountflyadd: function(value) {
               console.log(value);
         },
        changeCountmaintain: function(value) {
                console.log(value);
        },
        changeCountctol: function(value) {
                console.log(value);
        },
        changeCountairport: function(value) {
                console.log(value);
        },
        showaddairport:function (){
               this.isdiv=true;
               this.addairport=true;
         },
        showloading:function (){
                this.isdiv=true;
                this.addloading=true;
         },
        showflybase:function (){
                this.isdiv=true;
                this.addflybase=true;
         },
        showmaintain:function (){
                this.isdiv=true;
                this.addmaintain=true;
        },
        closemodal:function (){
               this.isdiv=false;
         },
        airportdelete:function (index) {
            this.airports.splice(index,1);
        },
        ctodelete:function (index) {
            this.ctol.splice(index,1);
       },
       flyaddresdelete:function (index) {
           this.flyaddress.splice(index,1);
       },
       maintadelete:function (index) {
           this.maintain.splice(index,1);
       },
       save:function () {

         }
    }
    }
</script>
