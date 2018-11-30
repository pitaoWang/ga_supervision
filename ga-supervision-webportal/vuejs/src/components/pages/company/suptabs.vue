<template>
    <div style="width: 1280px; height:auto;margin-left: 20px;  background:white">
        <span style="width:100%; display:inline-block;text-align:right;margin-top:10px;margin-bottom: 10px; ">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
        <Tabs v-model="value" :animated="ani" style="border: 1px solid #f0f0f0; ">
            <TabPane label="基本信息"  name="gacompany"><companydetail ></companydetail></TabPane>
            <TabPane label="企业资质"  name="supqual"><supqualification></supqualification></TabPane>
            <TabPane label="基地信息"  name="supbase"><supbase></supbase></TabPane>
            <TabPane label="运行资料"  name="supdatafile"><supdatafile></supdatafile></TabPane>
            <TabPane label="资料模板"  name="supdoctemp"><supdoctemp></supdoctemp></TabPane>
        </Tabs>
    </div>
</template>

<script>
    import companydetail from './companydetail.vue'
    import supqualification from './supqualification.vue'
    import supbase from './supbase.vue'
    import supdatafile from './supdatafile.vue'
    import supdoctemp from './supdoctemp.vue'
    export default {
        name: "suptabs",
        components: {
            'companydetail': companydetail,
            'supqualification':supqualification,
            'supbase':supbase,
            'supdatafile':supdatafile,
            'supdoctemp':supdoctemp
        },
        data(){
            return{
                gacompany:{

                },
                companyId:'',
                value:'',
                ani:false
            }
        },
        methods:{
            black:function () {
                //history.go(-1);
                //this.$router.go(-1)
                this.$router.push({path:'/black'})

            }
        },
        mounted() {

            this.gacompany = JSON.parse(this.$route.query.gacompany) ;
        },
         created(){
             this.value = this.$route.query.value;
         },
    }
</script>

<style scoped>

</style>