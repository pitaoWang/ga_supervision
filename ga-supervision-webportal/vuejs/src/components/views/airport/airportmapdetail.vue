<style scoped>
    .upload_imgs
    {
        margin-top: 12px;
        margin-right: 10px;
        width: 283px;
        height: 175px;
    }
    .view_img
    {
        width:280px;height:175px;margin: 0px auto;
        border: 1px;
        border-radius: 4px;
        margin-left: 10px;
        margin: 10px;
    }
    .airprot-map-img{
        margin-bottom: 15px;
        padding: 5px;
        width: 100%;
        border: solid 1px rgb(1,107,217);
    }
    .img-text{
        font-size:14px;
        padding: 5px;
        color: rgb(1,107,217);
    }
</style>

<template>
    <div style="width: 964px">
        <Form :model="airpmap" :label-width="100"  style="width: 100%; padding:10px auto;background-color: rgba(255,255,255,0.65)">
            <div style="width: 100%;">
                <div style=" border:1px solid rgb(1,107,217);width: 100%;margin-bottom: 15px; text-align: center; padding: 5px;">
                    <!-- 上传图片后,需要将图片路径保存 -->
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                            <div style="width: 283px;height:175px;line-height: 58px;" v-show="infoMapseen">
                                <img :src="airpmap.infoMap" class="view_img" @click="imgdetail('机场信息图',airpmap.infoMap)"></img>
                            </div>
                        <div v-show="infoMapseen1" style="width: 283px;height:175px; background-color:white; display: inline-block;  border: 1px dashed #d7dde4; border-radius: 4px;line-height: 58px; padding-top: 30px">
                            <Icon type="camera" size="100"></Icon>
                        </div>
                        <div class="img-text">机场信息图</div>
                    </div>

                    <!-- 上传图片后,需要将图片路径保存 -->
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <!--    <img src="./1.png"> -->
                            <div style="width: 283px;height:175px;line-height: 58px;" v-show="planeMapseen">
                                <img :src='airpmap.planeMap' class="view_img" @click="imgdetail('机场平面图',airpmap.planeMap)"></img>
                            </div>
                        <div v-show="planeMapseen1" style="width: 283px;height:175px; background-color:white; display: inline-block;  border: 1px dashed #d7dde4; border-radius: 4px;line-height: 58px; padding-top: 30px">
                            <Icon type="camera" size="100"></Icon>
                        </div>
                        <div class="img-text">机场平面图</div>
                    </div>
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <!--    <img src="./1.png"> -->
                            <div style="width: 283px;height:175px;line-height: 58px;" v-show="oilMapseen">
                                <img :src='airpmap.oilMap' class="view_img"  @click="imgdetail('放油图路劲',airpmap.oilMap)"></img>
                            </div>
                            <div v-show="oilMapseen1" style="width: 283px;height:175px; background-color:white; display: inline-block;  border: 1px dashed #d7dde4; border-radius: 4px;line-height: 58px; padding-top: 30px">
                                <Icon type="camera" size="100"></Icon>
                            </div>
                        <div class="img-text">放油图路劲</div>
                    </div>
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <!--    <img src="./1.png"> -->
                            <div style="width: 283px;height:175px;line-height: 58px;" v-show="parkingMap1seen">
                                <img :src='airpmap.parkingMap1' class="view_img" @click="imgdetail('停机位图1路劲',airpmap.parkingMap1)"></img>
                            </div>
                        <div v-show="parkingMap1seen1" style="width: 283px;height:175px; background-color:white; display: inline-block;  border: 1px dashed #d7dde4; border-radius: 4px;line-height: 58px; padding-top: 30px">
                            <Icon type="camera" size="100"></Icon>
                        </div>
                        <div class="img-text">停机位图1路劲</div>
                    </div>
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <!--    <img src="./1.png"> -->
                            <div style="width: 283px;height:175px;line-height: 58px;" v-show="parkingMap2seen">
                                <img :src='airpmap.parkingMap2' class="view_img" @click="imgdetail('停机位图2路劲',airpmap.parkingMap2)"></img>
                            </div>
                        <div v-show="parkingMap2seen1" style="width: 283px;height:175px; background-color:white; display: inline-block;  border: 1px dashed #d7dde4; border-radius: 4px;line-height: 58px; padding-top: 30px">
                            <Icon type="camera" size="100"></Icon>
                        </div>
                        <div class="img-text">停机位图2路劲</div>
                    </div>
                </div>
                <div>
                    <div class="airprot-map-img">
                        <h2 style="font-size: 15px;color: rgb(1,107,217)">标准仪表进场图</h2>
                        <div style="width: 290px;height:175px;line-height: 58px;padding-bottom: 10px;display: inline-block; text-align: center;" v-for="item in meterEnterMapList">
                            <img :src='item.url' class="view_img" @click="imgdetail('标准仪表进场图',item.url)"></img>
                            <div class="img-text">{{item.name}}</div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="airprot-map-img">
                        <h2 style="font-size: 15px;color: rgb(1,107,217)">标准仪表离场图</h2>
                        <div style="width: 290px;height:175px;line-height: 58px;padding-bottom: 10px;display: inline-block; text-align: center;" v-for="item in meterLeaveMapList">
                            <img :src='item.url' class="view_img" @click="imgdetail('标准仪表离场图',item.url)"></img>
                            <div class="img-text">{{item.name}}</div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="airprot-map-img">
                        <h2 style="font-size: 15px;color: rgb(1,107,217)">标准仪表进近图</h2>
                        <div style="width: 290px;height:175px;line-height: 58px;padding-bottom:10px;display: inline-block; text-align: center;" v-for="item in meterApproachMapList">
                            <img :src='item.url' class="view_img" @click="imgdetail('标准仪表进近图',item.url)"></img>
                            <div class="img-text">{{item.name}}</div>
                        </div>
                    </div>
                </div>
            </div>
        </Form>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>

</template>

<script>
    import imgdetail from '../../views/imgdetail.vue';
    export default {
        components:{
            imgdetail
        },
        props: ['idinfobase'],
        data () {
            return {
                airpmapmodal:false,
                airpmapmessage:'',
                infoMapseen:false,
                planeMapseen:false,
                oilMapseen:false,
                parkingMap1seen:false,
                parkingMap2seen:false,
                infoMapseen1:true,
                oilMapseen1:true,
                planeMapseen1:true,
                parkingMap1seen1:true,
                parkingMap2seen1:true,
                copmodal:false,/*提示框*/
                copmassage:'',
                meterLeaveMapList:[],
                meterEnterMapList:[],
                meterApproachMapList:[],
                uploadList:[],
                airpmap: {
                    id:'',
                    airportId:'',
                    infoMap:'',
                    planeMap:'',
                    oilMap:'',
                    parkingMap1:'',
                    parkingMap2:'',
                    meterLeaveMap:'',

                    meterEnterMap:'',

                    meterApproachMap:'',

                }
            }
        },
        watch: {
            // 如果 question 发生改变，这个函数就会运行
            idinfobase: function (newval) {
                this.initmapdetail(newval);
            }
        },
        methods: {
            imgdetail:function(title,path)
            {
                console.log("this.$refs.imgdetailref",this.$refs.imgdetailref);
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            initmapdetail:function (newval) {
                let self = this;
                self.airpmap.airportId=newval;
                self.$http.httpGet('/app/api/airpmap/byairportid/'+self.airpmap.airportId, {//根据机场id查询
                    //查找全部管理局信息

                }).then(function (response) {
                    let datainfo = response.data;
                    if (datainfo != null && datainfo != '' && typeof(datainfo) != 'undefined') {

                        self.airpmap = datainfo;
                        var meterLeaveMapjson=eval(self.airpmap.meterLeaveMap);
                        var meterApproachMapjson=eval(self.airpmap.meterApproachMap);
                        var meterEnterMapjson=eval(self.airpmap.meterEnterMap);
                        if (meterLeaveMapjson != null && meterLeaveMapjson != '' && typeof(meterLeaveMapjson) != 'undefined')
                        {self.meterLeaveMapList =meterLeaveMapjson ;}
                        if (meterApproachMapjson != null && meterApproachMapjson != '' && typeof(meterApproachMapjson) != 'undefined')
                        {self.meterApproachMapList =meterApproachMapjson ;}
                        if (meterEnterMapjson != null && meterEnterMapjson != '' && typeof(meterEnterMapjson) != 'undefined')
                        {self.meterEnterMapList =meterEnterMapjson ;}
                        if(self.airpmap.infoMap!='')
                        {
                            self.infoMapseen=true;
                            self.infoMapseen1=false;
                        }else{
                            self.infoMapseen=false;
                            self.infoMapseen1=true ;
                        }
                        if(self.airpmap.planeMap!='')
                        {
                            self.planeMapseen=true;
                            self.planeMapseen1=false;
                        }else {
                            self.planeMapseen1=true;
                            self.planeMapseen=false;
                        }
                        if(self.airpmap.oilMap!=''){
                            self.oilMapseen=true;
                            self.oilMapseen1=false;
                        }else {
                            self.oilMapseen1=true;
                            self.oilMapseen=false;
                        }
                        if(self.airpmap.parkingMap1!='')
                        {
                            self.parkingMap1seen=true;
                            self.parkingMap1seen1=false;
                        }else{
                            self.parkingMap1seen=false;
                            self.parkingMap1seen1=true ;
                        }
                        if(self.airpmap.parkingMap2!='')
                        {
                            self.parkingMap2seen=true;
                            self.parkingMap2seen1=false;
                        }else{
                            self.parkingMap2seen=false;
                            self.parkingMap2seen1=true ;
                        }
                    }else
                    {
                        self.airpmap = {
                            airportId: '',
                            infoMap: '',
                            planeMap: '',
                            oilMap: '',
                            parkingMap1: '',
                            parkingMap2: '',
                            meterLeaveMap: '',

                            meterEnterMap: '',

                            meterApproachMap: '',
                        }
                        self.meterLeaveMapList =[] ;
                        self.meterApproachMapList =[] ;
                        self.meterEnterMapList =[] ;
                        if(self.airpmap.infoMap!='')
                        {
                            self.infoMapseen=true;
                            self.infoMapseen1=false;
                        }else{
                            self.infoMapseen=false;
                            self.infoMapseen1=true ;
                        }
                        if(self.airpmap.planeMap!='')
                        {
                            self.planeMapseen=true;
                            self.planeMapseen1=false;
                        }else {
                            self.planeMapseen1=true;
                            self.planeMapseen=false;
                        }
                        if(self.airpmap.oilMap!=''){
                            self.oilMapseen=true;
                            self.oilMapseen1=false;
                        }else {
                            self.oilMapseen1=true;
                            self.oilMapseen=false;
                        }
                        if(self.airpmap.parkingMap1!='')
                        {
                            self.parkingMap1seen=true;
                            self.parkingMap1seen1=false;
                        }else{
                            self.parkingMap1seen=false;
                            self.parkingMap1seen1=true ;
                        }
                        if(self.airpmap.parkingMap2!='')
                        {
                            self.parkingMap2seen=true;
                            self.parkingMap2seen1=false;
                        }else{
                            self.parkingMap2seen=false;
                            self.parkingMap2seen1=true ;
                        }
                    }
                }) .catch(function (error) {
                    console.log(error);
                });

            },

            handleView (name) {
                this.imgName = name;
                this.visible = true;
            },
            handleBeforeUpload()
            {},

            },

    }
</script>
