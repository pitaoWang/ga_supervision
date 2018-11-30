<style scoped>
    .content {
        /*position: absolute;*/
        width: 99%;
        box-sizing: border-box;
        /*font-size: 18px;*/

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
        margin: auto;
        padding-top: 34px;
        width: 668px;
        height: 83px;
        vertical-align: middle;

    }

    .steps > img {
        float: left;
    }

    /*.stepschild span:first-child{*/
    /*color: rgb(21, 184, 252);*/
    /*margin-left: 47px;*/
    /*}*/
    /*.stepschild span:nth-child(2){*/
    /*margin-left: 90px;*/
    /*}*/
    /*.stepschild span:nth-child(3){*/
    /*margin-left: 94px;*/
    /*}*/
    /*.stepschild span:nth-child(4){*/
    /*margin-left: 107px;*/
    /*}*/

    .contet {
        width: 100%;
        /*height: 600px;*/
        /*overflow: hidden;*/
    }

    /*.contetchild{*/
    /*!*height:600px;*!*/
    /*width: 100%;    !* 多出26像素是滚动条的位置，会被父容器盖住就看不到了 *!*/
    /*!*overflow-x: hidden;*!*/
    /*!*overflow-y: scroll;*!*/
    /*}*/
</style>

<template>
    <div class="content">
        <div class="contet">
            <!--<div class="contetchild">-->
            <div>
                <div class="steps" style="padding-left: 107px;">
                    <img v-bind:src="img1" v-on:click="airp()">
                    <img v-bind:src="img2" v-on:click="map()">
                    <img v-bind:src="img3" v-on:click="runway()">

                </div>
                <div class="stepschild" style="text-align:center;width: 650px; padding-left: 53px; margin: 10px auto; color: black"><span ref="fairp" style=" color: rgb(21, 184, 252);font-size: 14px">机场信息</span> <span
                        ref="fmap" style="margin-left:97px;font-size: 14px ">机场航图信息</span> <span ref="frunway" style="margin-left: 97px;font-size: 14px">跑道信息</span>
                </div>
            </div>

            <airportchild :getairportid=getAiportId v-show="airportshow" v-on:listenairprot="getAirportIdfunction" ref="refairportchild"></airportchild>
            <airprotmapchild :getairportid=getAiportId v-show="mapshow" ref="airprotmapchild"></airprotmapchild>
            <runwaychild :runwaychildgetairportid=getAiportId v-show="runwayshow" v-on:closeaddairportemit="closeaddairport()" ref="runwaychild"></runwaychild>
        </div>
    </div>
    <!--</div>-->
</template>
<script>
    import airportchild from './airport.vue'
    import airprotmapchild from './airportmap.vue'
    import runwaychild from './airtportrunway.vue'

    export default {
        data (){
            return {
                getAiportId:'',
                airportshow: true,
                mapshow: false,
                runwayshow: false,
                img1: require("../../../assets/1.png"),
                img2: require('../../../assets/灰2.png'),
                img3: require('../../../assets/灰3.png'),

            }
        },
        components: {
            'airportchild': airportchild,
            'airprotmapchild': airprotmapchild,
            'runwaychild': runwaychild
        },
        methods: {
            airp: function () {
                let self = this;
                self.airportshow = true;
                self.mapshow = false;
                self.runwayshow = false;
                self.img1 = require('../../../assets/1.png');
                self.img2 = require('../../../assets/灰2.png');
                self.img3 = require('../../../assets/灰3.png');


                self.$refs.fairp.style.color = 'rgb(21, 184, 252)';
                self.$refs.fmap.style.color = '';
                self.$refs.frunway.style.color = '';


            },
            map: function () {
                let self = this;
                self.airportshow = false;
                self.mapshow = true;
                self.runwayshow =false;
                self.img1 = require('../../../assets/灰1.png');
                self.img2 = require('../../../assets/2.png');
                self.img3 = require('../../../assets/灰3.png');
                self.$refs.fairp.style.color = '';
                self.$refs.fmap.style.color = 'rgb(21, 184, 252)';
                self.$refs.frunway.style.color = '';
                self.$refs.airprotmapchild.initmap(self.getAiportId);

            },
            runway: function () {
                let self = this;
                self.airportshow = false;
                self.mapshow = false;
                self.runwayshow = true;
                self.img1 = require('../../../assets/灰1.png');
                self.img2 = require('../../../assets/灰2.png');
                self.img3 = require('../../../assets/3.png');
                self.$refs.fairp.style.color = '';
                self.$refs.fmap.style.color = '';
                self.$refs.frunway.style.color = 'rgb(21, 184, 252)';
                self.$refs.runwaychild.initrunway(self.getAiportId);

            },
            getAirportIdfunction(msg){
                this.getAiportId=msg;
            },addairoprtinfo(){
                let self = this;
                self.airportshow = true;
                self.mapshow = false;
                self.runwayshow = false;
                self.img1 = require('../../../assets/1.png');
                self.img2 = require('../../../assets/灰2.png');
                self.img3 = require('../../../assets/灰3.png');


                self.$refs.fairp.style.color = 'rgb(21, 184, 252)';
                self.$refs.fmap.style.color = '';
                self.$refs.frunway.style.color = '';
                this.getAiportId=-1;//机场表中不在的值
                this.$refs.refairportchild.initairport(this.getAiportId);
           },airportinfo(obj){
      //          alert(obj);
                let self = this;
                self.airportshow = true;
                self.mapshow = false;
                self.runwayshow = false;
                self.img1 = require('../../../assets/1.png');
                self.img2 = require('../../../assets/灰2.png');
                self.img3 = require('../../../assets/灰3.png');


                self.$refs.fairp.style.color = 'rgb(21, 184, 252)';
                self.$refs.fmap.style.color = '';
                self.$refs.frunway.style.color = '';
                this.getAiportId=obj;
            },
            closeaddairport(isClose)
            {
                this.$emit("childshowairport",isClose);
            }
        },
        watch:{function () {
            let self = this;
            self.airportshow = true;
            self.mapshow = false;
            self.runwayshow = false;
            self.img1 = require('../../../assets/1.png');
            self.img2 = require('../../../assets/灰2.png');
            self.img3 = require('../../../assets/灰3.png');


            self.$refs.fairp.style.color = 'rgb(21, 184, 252)';
            self.$refs.fmap.style.color = '';
            self.$refs.frunway.style.color = '';

        }}
    }


</script>