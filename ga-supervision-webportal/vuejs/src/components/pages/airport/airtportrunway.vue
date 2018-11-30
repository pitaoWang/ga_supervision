<style>
    .airportrunway-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
    .airportrunway-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
    .div-flyimg-delete{
        display: inline-block;
    }
</style>
<template>
    <div style="width:965px;margin: 10px auto; text-align: center;" >
         <div style=" width:600px;text-align: center; margin: auto; height: 30px" v-if="runwaylistseen">
            <Row v-for="i in flyrunwaylist" :key="i.flyrunwayid" style="height: 30px;margin-bottom: 20px;padding-left: 30px; margin-left: 50px;">
                <Col span="18" style="text-align: left;background-color: rgb(204, 204, 204);margin-right: 10px; height: 30px; padding-left: 5px; padding-top: 5px; border-radius: 4px;">
                {{i.runwayNo}}
                </Col>
                <Col span="4">
                <img class="div-flyimg-delete" src="../../../assets/delete.png" @click="runWayRemove(i.id)"/>
                <img class="div-flyimg-delete" src="../../../../static/images/edit.png" @click="runwayupdate(i.id)">
                </Col>
            </Row>
           <Row style="text-align: center; height: 25px;padding-bottom: 30px; margin: 0px auto;">
             <div style="text-align: center; text-align: center; height: 25px" class="airportrunway-div">
                 <a data-v-942377fa="" href="javascript:;" @click="runWayAdd()" class="airportrunway-btn" style="margin: auto;">添加机场跑道</a>
                 <a data-v-942377fa="" href="javascript:;" @click="closeaddairprot()" class="airportrunway-btn">完成</a>
             </div>
           </Row>
        </div>
        <frwtemplete :runwayentitys=runwayentity v-if="addrunseen" v-on:listenadrun="getlistenrunway"></frwtemplete>
    </div>
</template>
<script>
    import frwtemplete from '../../pages/airport/flightrunway.vue';
    export default {
        props:['runwaychildgetairportid'],//获取机场ID,根据机场ID获取跑道列表
        data () {
            return {
                runwaylistseen:true,
                addrunseen:false,
                runwayentity:{//传值到添加跑道页面
                    flyrunwayid:'',
                    airportid:'',
                },
                flyrunwaylist:[
                ]
            }
        },
        components:{
            frwtemplete,
        },
        created:function () {

        },
//        mounted: function () {
//            let self = this;
//            self.runwayentity.airportid=this.runwaychildgetairportid;
//            if(self.runwayentity.airportid=='')
//            {
//                self.runwayentity.airportid=-1;
//            }
//                    self.$http.httpGet('/app/api/flightrunway/byairportid/'+self.runwayentity.airportid, {
//                    }).then(function (response) {
//                        let datainfo=response.data;
//                        self.flyrunwaylist=datainfo;
//
//                    })
//                            .catch(function (error) {
//                                console.log(error);
//                            });
//
//        },

        methods: {
            getrunway:function()
            {
                let self = this;
                self.runwayentity.airportid=self.runwaychildgetairportid;
                self.initrunway(self.runwayentity.airportid);

            },
            runwayupdate(flyid)
            {
                this.runwayentity.flyrunwayid=flyid;
                let self = this;
                self.runwaylistseen=false;
                self.addrunseen=true;
            },
            initrunway:function(obj)
            {
                let self = this;
                self.runwayentity.airportid=obj;
                if(self.runwayentity.airportid!=undefined&&self.runwayentity.airportid!=''&&self.runwayentity.airportid>0) {
                    self.$http.httpGet('/app/api/flightrunway/byairportid/' + self.runwayentity.airportid, {}).then(function (response) {
                        let datainfo = response.data;
                        self.flyrunwaylist = datainfo;

                    })
                            .catch(function (error) {
                                console.log(error);
                                self.$Message.error('操作失败');
                            });
                }
            },
            deleterunway:function (id) {
                let self = this;
                self.$http.httpDelete('/app/api/flightrunway/'+id, {
                }).then(function (response) {
                    self.getrunway();
                    this.$Message.success('删除成功');
                })
                        .catch(function (error) {
                            console.log(error);
                            this.$Message.error('删除失败');
                        });
            },
            runWayAdd:function () {
                this.runwayentity.flyrunwayid=0;
                let self = this;
                self.runwaylistseen=false;
                self.addrunseen=true;
            },
            runWayRemove:function (id){
                this.deleterunway(id);
                this.getrunway();
            },
            getlistenrunway:function (runwaylistseen1,addrunseen1) {
                this.runwaylistseen=runwaylistseen1;
                this.addrunseen=addrunseen1;
                this.getrunway();
            },
            closeaddairprot()
            {
              this.$emit("closeaddairportemit",false);
            }
        },

        watch: {
            init:function () {

            }
        }
    }
</script>