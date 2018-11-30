<style>

    .apdatarigtht{
        float: left;
        width: 100%;
    }

    .div-list-airportdato{
        width: 965px;
        height: 34px;
        background-color: rgba(255,255,255,0.65) ;
        border:1px solid rgb(1,107,217);
        font-size: 14px;
        font-family: "Microsoft YaHei Regular", "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", Arial, sans-serif;
        color: rgb(1,107,217);
        margin-bottom: 4px;
    }

    .div-list-airportdato .logoimgairport{
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

</style>
<template>
    <div style="width: 965px;">
        <div class="apdatarigtht">
            <div>
                <div class=" active div-list-airportdato" @click="islogshow1=!islogshow1">
                    <div class="name" >
                        <div class="logoimgairport">
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
                    <airportdetail v-bind:idinfobase="infodata"></airportdetail>
                </div>
            </div>
            <div>
                <div class=" active div-list-airportdato" @click="islogshow2=!islogshow2">
                    <div class="name" >
                        <div class="logoimgairport">
                            <img src="../../../assets/基本信息.png"  >
                        </div >
                        <div class="name-text">
                            机场航图
                        </div>
                        <div class="name-img">
                            <img v-if="islogshow2" src="../../../assets/减号.png" style="margin-top: 15px">
                            <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                        </div>

                    </div>
                </div>
                <div v-show="islogshow2">
                    <airportmapdetail v-bind:idinfobase="infodata"></airportmapdetail>
                </div>
            </div>
            <div>
                <div class=" active div-list-airportdato" @click="islogshow3=!islogshow3">
                    <div class="name" >
                        <div class="logoimgairport">
                            <img src="../../../assets/基本信息.png"  >
                        </div >
                        <div class="name-text">
                            机场跑道信息
                        </div>
                        <div class="name-img">
                            <img v-if="islogshow3" src="../../../assets/减号.png" style="margin-top: 15px">
                            <img v-else src="../../../assets/加号.png" style="margin-top: 8px">
                        </div>

                    </div>
                </div>
                <div v-show="islogshow3">
                    <airworthiness v-bind:idinfobase="infodata"></airworthiness>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import airworthiness from './flightrunwaydetail'
    import airportdetail from './airportdetail'
    import airportmapdetail from './airportmapdetail'
    export default {
        data () {
            return {
                imgds: "../../../imgs/jiantou1.png",
                infodata: "",
                islogshow1: false,
                islogshow2: false,
                islogshow3: false,
            }
        },
        components: {
            airworthiness, airportdetail, airportmapdetail
        },
        props:['airportdataid'],
        methods: {
            getData(newid)
            {
                this.infodata = newid;
            },
        },


    }
</script>





