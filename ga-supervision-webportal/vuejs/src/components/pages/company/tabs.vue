<template>
    <div style="width: 1280px; height:auto; margin-top: 30px; padding-top: 40px; padding-left: 20px; background:white">
        <Tabs v-model="value" :animated="ani"  style="border: 1px solid #f0f0f0; margin-left: 40px; margin-right: 40px ">
            <TabPane label="基本信息" name="gacompany"><gacompany ></gacompany></TabPane>
            <TabPane label="资质信息" name="companyqualificationnew"><companyqualificationnew></companyqualificationnew></TabPane>
            <TabPane label="基地信息" name="operationbase"><operationbase></operationbase></TabPane>
            <!--<TabPane label="其他基地" name="otherbase"><otherbase></otherbase></TabPane>-->
            <TabPane label="运行资料" name="datafile"><datafile></datafile></TabPane>
            <TabPane label="资料模板" name="doctemplate"><doctemplate></doctemplate></TabPane>
        </Tabs>
    </div>

</template>
<script>
    import gacompany from './gacompany.vue'
    import companyqualification from './companyqualification.vue'
    import entinfo from './entinfo.vue'
    import legalperson from './legalperson.vue'
    import applyauthor from './applyauthor.vue'
    import operationbase from './operationbase.vue'
    import otherbase from './otherbase.vue'
    import datafile from './datafile.vue'
    import doctemplate from './doctemplate.vue'
    import companyqualificationnew from './companyqualificationnew.vue'
    export default {
        components: {
            'companyqualification': companyqualification,
            'gacompany': gacompany,
            'entinfo': entinfo,
            'legalperson': legalperson,
            'applyauthor':applyauthor,
            'operationbase':operationbase,
            'otherbase':otherbase,
            'datafile':datafile,
            'doctemplate':doctemplate,
            'companyqualificationnew':companyqualificationnew,
        },
        data(){
            return{
                value:'',
                ani:false
            }
        },
        mounted() {



        },
        created(){
            this.value = this.$route.query.value;
        },

    }
</script>
