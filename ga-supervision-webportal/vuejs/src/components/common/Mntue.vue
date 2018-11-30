<template>
    <div class="topbar">
        <div>
            <div class="breadcrumbs">
                <a class="level" href="#" data-permalink="">{{name2}}</a>
                <a  class="level" href="#" data-permalink="">{{name1}}</a>
            </div>
        </div>
    </div>

</template>

<script>
    import sidebarMenu from '../../views/main-components/shrinkable-menu/components/sidebarMenu.vue';
    export default {
        name: "Mntue",
        components: {
            sidebarMenu,
        },
        data () {
            return {
                ss:'',
                name1: '首页',
                name2: '中国航空工业'
            }
        },
        /* watch:{
             ss:function(){
            this.$nextTick(function(){
                this.childFn("swagger")
               });
            }

        },*/
        methods: {
            childFn(name){
                let self = this;
              if(name == 1){
                  self.name1 = "消息中心";
                  self.name2 ="中国航空工业";
              }else{


                  self.$http.httpGet('/eim/api/aircraft/search/mut',{

                      name:name
                  }).then(function (response) {

                      let datainfo = response.data.data[0];


                      self.name1 = datainfo[0];
                      self.name2 = datainfo[1];

                  }).catch(function (error) {
                      console.log(error);
                  });
              }
            },

        }

    }
</script>

<style scoped>
    .topbar{
       padding-top: 10px;
        padding-bottom: 30px;
    }
    .topbar .level:first-child::before {
        content: '';
        padding: 0;
    }
    .topbar .breadcrumbs {
        float: left;
    }
    .topbar .level {
        font-size: 14px;
        color: #778592;
        display: inline-block;
        text-transform: uppercase;
    }
    .topbar .level::before {
        content: '/';
        font-size: 14px;
        color: #a6bdd1;
        padding: 0 8px;
    }
</style>