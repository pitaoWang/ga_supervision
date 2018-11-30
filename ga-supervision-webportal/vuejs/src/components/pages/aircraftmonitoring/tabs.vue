<template>
    <div>
    <div style = "width: 1240px;">
        <!--<button class="div-button" style="width:88px; height:30px; border-radius:4px; border:1px solid #33A6FC; background-color: #FFFFFF; color:#33A6FC; float: right; cursor:pointer;margin-top: 30px; margin-bottom: 30px;"
                @click="cancel()" >返  回</button>-->
        <span style="width:100%; display:inline-block;text-align:right;margin-top:10px;margin-bottom: 10px;margin-left: 40px; ">
                    <Button primary @click="cancel()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
    </div>
    <div style="width: 1280px; height:auto; padding-left: 20px; background:white">

        <Tabs v-model="value" :animated="ani"  style="border: 1px solid #f0f0f0;  "  type="card">
            <TabPane label="基本信息" name="baseInfo"><baseInfo /></TabPane>
            <TabPane label="发动机信息" name="engine"><engine :regNumber="regNumber"/></TabPane>
            <TabPane label="飞行记录本" name="flightrecord"><flightrecord :regNumber="regNumber" :aircraft="aircraft"/></TabPane>
            <TabPane label="工作保留单" name="workreserve"><workreserve :regNumber="regNumber" :aircraft="aircraft"/></TabPane>
            <TabPane label="故障保留单" name="faultreserve"><faultreserve :regNumber="regNumber" :aircraft="aircraft"/></TabPane>
            <TabPane label="重要维修与改装" name="repairmodified"><repairmodified :regNumber="regNumber" :aircraft="aircraft"/></TabPane>
        </Tabs>
    </div>
    </div>
</template>
<script>
import workreserve from './workreserve.vue'
import faultreserve from './faultreserve.vue'
import repairmodified from './repairmodified.vue'
import flightrecord from './flightrecord.vue'
import engine from './engine.vue'
import baseInfo from '../flight/viewAircraft.vue'

export default {
    components: {
        baseInfo,
        engine,
        flightrecord,
        workreserve,
        faultreserve,
        repairmodified,
    },
    data(){
        return{
            value:'',
            ani:false,
            aircraft :[],
            regNumber: ''
        }
    },
    mounted() {
    },
    created(){
        this.aircraft = this.$route.query.aircraft;
        this.value = this.$route.query.value;
        if(this.$route.query.regNumber) {
            this.regNumber = this.$route.query.regNumber;
        }
    },
    methods:{
        cancel:function () {
            this.$router.push({path: '/airworthiness/aircraftList'});
        }
    }
}
</script>

