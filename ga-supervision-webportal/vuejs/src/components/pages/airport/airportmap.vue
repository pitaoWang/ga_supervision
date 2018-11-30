<style scoped>
    .navi-btn {
        margin-left: 305px;
        margin-top: 14px;
        background: #c4c4c4;
        width: 120px;
        height: 35px;
        font-size: 14px;
        margin-right: 50px;
    }

    .upload_imgs {
        margin-top: 12px;
        /*margin-left: 20px;*/
        margin-right: 10px;
        width: 283px;
        height: 175px;
        position: relative;
    }

    .view_img {
        width: 283px;
        height: 175px;
        margin: 0px auto;
        border: 1px;
        border-radius: 4px;
        /*margin-left: 10px;*/
        /*margin: 10px;*/
        vertical-align: middle;
    }

    .delorselect {
        position: absolute;
        color: #fff;
        top: 15px;
        right: 0px;
        font-size: 16px;
        background-color: #3d3d3d;
        border-radius: 4px;
        padding: 0px 4px;

    }

    .gacompany-btn {
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

    .gacompany-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }

    .airprot-map-img {
        margin-bottom: 30px;
    }

    .demo-upload-list-cover-airmap {
        /* position: absolute; */
        /* top: 118px; */
        /* bottom: 0; */
        /* left: 0px; */
        /* right: 0px; */
        background: rgba(37, 128, 221, 0.66);
        /* width: 120px; */
        height: 28px;
        /* padding-left: 199px; */
        text-align: right;
        padding-right: 2px;
        padding-top: 1px;
        /* margin-bottom: 16px; */
    }

    .demo-upload-list:hover .demo-upload-list-cover-airmap {
        /*display: block;*/
    }

    .demo-upload-list-cover-airmap i {
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }

    .onclickimg1 {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/search.png");
    }

    .onclickimg1:active {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/searchonclick.png");
    }

    .onclickimg2 {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/edit.png");
    }

    .onclickimg2:active {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/editonclick.png");
    }

    .onclickimg3 {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/newdelete.png");
    }

    .onclickimg3:active {
        width: 39px;
        margin-left: -3px;
        height: 26px;
        display: inline-block;
        cursor: pointer;
        background-image: url("../../../assets/deleteonclick.png");
    }

    .company-upload-div-div {
        font-size: 0.7rem;
        text-align: center;
        text-align: center;
        width: 100%;
        float: left;
        display: block;
        margin-top: 12px;
        line-height: 17px;
        color: rgba(1, 107, 217, 0.5);
    }

    .airportmap-upload-div-img {
        float: right;
        margin-right: 130px;
        margin-top: 26px;
    }

    .uploadimg5 {
        color: #4a4a4a;
        width: 283px;
        height: 175px;
        background-color: white;
        border: 1px solid rgb(1, 107, 217);
        /*border-radius: 4px;*/
        line-height: 58px;
        padding-top: 30px;
    }
</style>

<template>
    <div style="width: 950px;">
        <Form :model="airpmap" :label-width="100" v-show="airportmapshow"
              style="width:900px; height: 900px; padding:10px auto; padding-bottom: 30px;/* background-color: #363636; */">
            <div style="width: 900px;">
                <div style=" border:1px solid #919191;width: 900px;margin: 30px auto; text-align: center; padding: 5px;">
                    <!-- 上传图片后,需要将图片路径保存 -->
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <div class="demo-upload-list" v-show="infoMapseen">
                            <img :src="airpmap.infoMap" class="view_img" v-show="infoMapseen"></img>
                        </div>
                        <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :on-success="handleSuccessinfoMap"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUploadinfoMap"
                                multiple
                                :data=pathparam
                                action="http://fs.glinkus.com:18081/upload"
                        >

                            <div v-show="infoMapseen1" id="infoMapid" class="uploadimg5">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="airportmap-upload-div-img">
                                </div>
                                <div class="company-upload-div-div">图片小于2M</br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="demo-upload-list-cover-airmap">
                            <div class="onclickimg1" @click="handleView(airpmap.infoMap)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('infoMap')"> &nbsp; </div>
                            <div class="onclickimg3" @click="removeinfoMap(airpmap.infoMap)"> &nbsp; </div>
                        </div>
                        <div style=" font-size:14px;padding: 5px;">机场信息图</div>
                    </div>

                    <!-- 上传图片后,需要将图片路径保存 -->
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <!--    <img src="./1.png"> -->
                        <div class="demo-upload-list" v-show="planeMapseen">
                            <img :src="airpmap.planeMap" class="view_img" v-show="planeMapseen"></img>
                        </div>
                        <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :on-success="handleSuccessplaneMap"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUploadplaneMap"
                                multiple
                                :data=pathparam
                                action="http://fs.glinkus.com:18081/upload"
                        >
                            <div v-show="planeMapseen1" id="planeMapid" class="uploadimg5">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="airportmap-upload-div-img">
                                </div>
                                <div class="company-upload-div-div">图片小于2M</br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="demo-upload-list-cover-airmap">
                            <div class="onclickimg1" @click="handleView(airpmap.planeMap)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('planeMap')"> &nbsp; </div>
                            <div class="onclickimg3" @click="removeplaneMap(airpmap.planeMap)"> &nbsp; </div>
                        </div>
                        <div style="font-size:14px;padding: 5px;">机场平面图</div>
                    </div>
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <div class="demo-upload-list" v-show="oilMapseen">
                            <img :src="airpmap.oilMap" class="view_img" v-show="oilMapseen"></img>
                        </div>
                        <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :on-success="handleSuccessoilMap"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUploadoilMap"
                                multiple
                                :data=pathparam
                                action="http://fs.glinkus.com:18081/upload"
                        >
                            <div v-show="oilMapseen1" id="oilMapid"
                                 class="uploadimg5">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="airportmap-upload-div-img">
                                </div>
                                <div class="company-upload-div-div"">图片小于2M</br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="demo-upload-list-cover-airmap">
                            <div class="onclickimg1" @click="handleView(airpmap.oilMap)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('oilMap')"> &nbsp; </div>
                            <div class="onclickimg3" @click="removeoilMap(airpmap.oilMap)"> &nbsp; </div>
                        </div>
                        <div style="font-size:14px;padding: 5px;">放油图</div>
                    </div>
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <div class="demo-upload-list" v-show="parkingMap1seen">
                            <img :src="airpmap.parkingMap1" class="view_img" v-show="parkingMap1seen"></img>
                        </div>
                        <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :on-success="handleSuccessparkingMap1"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUploadparkingMap1"
                                multiple
                                :data=pathparam
                                action="http://fs.glinkus.com:18081/upload"
                        >
                            <div v-show="parkingMap1seen1" id="parkingMap1id"
                                 class="uploadimg5">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="airportmap-upload-div-img">
                                </div>
                                <div class="company-upload-div-div">图片小于2M</br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="demo-upload-list-cover-airmap">
                            <div class="onclickimg1" @click="handleView(airpmap.parkingMap1)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('parkingMap1')"> &nbsp; </div>
                            <div class="onclickimg3" @click="removeparkingMap1(airpmap.parkingMap1)"> &nbsp; </div>
                        </div>
                        <div style="font-size:14px;padding: 5px;">停机位图1路劲</div>
                    </div>
                    <div class="upload_imgs" style="margin-top: 12px; display: inline-block;">
                        <div class="demo-upload-list" v-show="parkingMap2seen">
                            <img :src="airpmap.parkingMap2" class="view_img" v-show="parkingMap2seen"></img>
                        </div>
                        <Upload
                                ref="upload"
                                :show-upload-list="false"
                                :on-success="handleSuccessparkingMap2"
                                :format="['jpg','jpeg','png']"
                                :max-size="2048"
                                :on-format-error="handleFormatError"
                                :on-exceeded-size="handleMaxSize"
                                :before-upload="handleBeforeUploadparkingMap2"
                                multiple
                                :data=pathparam
                                action="http://fs.glinkus.com:18081/upload"
                        >
                            <div v-show="parkingMap2seen1" id="parkingMap2id"
                                 class="uploadimg5">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="airportmap-upload-div-img">
                                </div>
                                <div class="company-upload-div-div">图片小于2M</br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="demo-upload-list-cover-airmap">
                            <div class="onclickimg1" @click="handleView(airpmap.parkingMap2)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('parkingMap2')"> &nbsp; </div>
                            <div class="onclickimg3" @click="removeparkingMap2(airpmap.parkingMap2)"> &nbsp; </div>
                        </div>
                        <div style="font-size:14px;padding: 5px;">停机位图2路劲</div>
                    </div>
                </div>
                <div>
                    <div class="airprot-map-img" style="border:1px solid #919191;">
                        <h2 style="padding:5px; font-size: 15px;">标准仪表进场图</h2>
                        <uploadfile :uploadList=meterEnterMapList :filepath=pathparam.path
                                    v-on:listenTochildEvent="getuploadList"></uploadfile>
                    </div>
                </div>
                <div>
                    <div class="airprot-map-img" style="border:1px solid #919191;">
                        <h2 style="padding: 5px; font-size: 15px;">标准仪表离场图</h2>
                        <uploadfile :uploadList=meterLeaveMapList :filepath=pathparam.path
                                    v-on:listenTochildEvent="getuploadList"></uploadfile>
                    </div>
                </div>
                <div>
                    <div class="airprot-map-img" style="border:1px solid #919191;">
                        <h2 style="padding: 5px; font-size: 15px;">标准仪表进近图</h2>
                        <uploadfile :uploadList=meterApproachMapList :filepath=pathparam.path
                                    v-on:listenTochildEvent="getuploadList"></uploadfile>
                    </div>
                </div>

                <div class="gacompany-div"
                     style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px; width: 900px;">
                    <a data-v-942377fa="" href="javascript:;" @click="handleSubmit()" class="gacompany-btn">保存</a>
                    <!--            <a data-v-942377fa="" href="javascript:;" @click="" class="gacompany-btn">返回</a> -->
                </div>
            </div>
            <Modal v-model="airpmapmodal"
                   :mask-closable="false"
                   width="300px" hight="200px" :styles="{top: '40%'}">
                <p slot="header" style="color:#e9e9e9;text-align:left;font-size: 15px;">
                    <span>系统提示</span>
                </p>
                <p style="height: 100px; height: 80px;padding-top: 24px;text-align: center;margin-bottom: 2px; text-align: center;">
                    {{airpmapmessage}}</p>
            </Modal>
        </Form>
        <div title="查看图片" v-model="visible" style="text-align: center;padding-bottom: 30px;">
            <img :src="imgName" v-if="visible"
                 style="width:600px;height: 400px;text-align: center;    margin-left: 169px;">
            <div @click="backairportmap" v-if="visible">返回</div>
        </div>
    </div>

</template>

<script>
    import uploadfile from '../airport/uploadfile.vue';
    export default {

        components: {
            'uploadfile': uploadfile,
        },
        props: ['getairportid'],
        data () {
            return {
                pathparam: {
                    path: '',
                },
                imgName: '',
                visible: false,
                airportmapshow: true,
                airid: '',
                airpmapmodal: false,
                airpmapmessage: '',
                infoMapseen: false,
                planeMapseen: false,
                oilMapseen: false,
                parkingMap1seen: false,
                parkingMap2seen: false,
                infoMapseen1: true,
                oilMapseen1: true,
                planeMapseen1: true,
                parkingMap1seen1: true,
                parkingMap2seen1: true,
                copmodal: false, /*提示框*/
                copmassage: '',
                meterLeaveMapList: [],
                meterEnterMapList: [],
                meterApproachMapList: [],
                uploadList: [],
                airpmap: {
                    id: '',
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
            }
        },
//        props: {
//            getairportid: {
//                type: Number,
//                default: 0
//            }
//        },

        methods: {

            initmap: function (newval) {
                let self = this;
                self.airpmap.airportId = newval;
                self.pathparam.path = '/' + self.airpmap.airportId + '/airportinfo';
                self.$http.httpGet('/app/api/airpmap/byairportid/' + self.airpmap.airportId, {//根据机场id查询
                    //查找全部管理局信息

                }).then(function (response) {


                    self.airpmap.airportId = newval;
                    let datainfo = response.data;
                    if (datainfo != null && datainfo != '' && typeof(datainfo) != 'undefined') {

                        self.airpmap = datainfo;
                        var meterLeaveMapjson = eval(self.airpmap.meterLeaveMap);
                        var meterApproachMapjson = eval(self.airpmap.meterApproachMap);
                        var meterEnterMapjson = eval(self.airpmap.meterEnterMap);
                        if (meterLeaveMapjson != null && meterLeaveMapjson != '' && typeof(meterLeaveMapjson) != 'undefined') {
                            self.meterLeaveMapList = meterLeaveMapjson;
                        }
                        if (meterApproachMapjson != null && meterApproachMapjson != '' && typeof(meterApproachMapjson) != 'undefined') {
                            self.meterApproachMapList = meterApproachMapjson;
                        }
                        if (meterEnterMapjson != null && meterEnterMapjson != '' && typeof(meterEnterMapjson) != 'undefined') {
                            self.meterEnterMapList = meterEnterMapjson;
                        }
                        if (self.airpmap.infoMap != '') {
                            self.infoMapseen = true;
                            self.infoMapseen1 = false;
                        } else {
                            self.infoMapseen = false;
                            self.infoMapseen1 = true;
                        }
                        if (self.airpmap.planeMap != '') {
                            self.planeMapseen = true;
                            self.planeMapseen1 = false;
                        } else {
                            self.planeMapseen1 = true;
                            self.planeMapseen = false;
                        }
                        if (self.airpmap.oilMap != '') {
                            self.oilMapseen = true;
                            self.oilMapseen1 = false;
                        } else {
                            self.oilMapseen1 = true;
                            self.oilMapseen = false;
                        }
                        if (self.airpmap.parkingMap1 != '') {
                            self.parkingMap1seen = true;
                            self.parkingMap1seen1 = false;
                        } else {
                            self.parkingMap1seen = false;
                            self.parkingMap1seen1 = true;
                        }
                        if (self.airpmap.parkingMap2 != '') {
                            self.parkingMap2seen = true;
                            self.parkingMap2seen1 = false;
                        } else {
                            self.parkingMap2seen = false;
                            self.parkingMap2seen1 = true;
                        }
                    } else {
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
                        self.meterLeaveMapList = [];
                        self.meterApproachMapList = [];
                        self.meterEnterMapList = [];
                        if (self.airpmap.infoMap != '') {
                            self.infoMapseen = true;
                            self.infoMapseen1 = false;
                        } else {
                            self.infoMapseen = false;
                            self.infoMapseen1 = true;
                        }
                        if (self.airpmap.planeMap != '') {
                            self.planeMapseen = true;
                            self.planeMapseen1 = false;
                        } else {
                            self.planeMapseen1 = true;
                            self.planeMapseen = false;
                        }
                        if (self.airpmap.oilMap != '') {
                            self.oilMapseen = true;
                            self.oilMapseen1 = false;
                        } else {
                            self.oilMapseen1 = true;
                            self.oilMapseen = false;
                        }
                        if (self.airpmap.parkingMap1 != '') {
                            self.parkingMap1seen = true;
                            self.parkingMap1seen1 = false;
                        } else {
                            self.parkingMap1seen = false;
                            self.parkingMap1seen1 = true;
                        }
                        if (self.airpmap.parkingMap2 != '') {
                            self.parkingMap2seen = true;
                            self.parkingMap2seen1 = false;
                        } else {
                            self.parkingMap2seen = false;
                            self.parkingMap2seen1 = true;
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });

            },
            handleBtnClick: function (docid) {
                if (docid == 'infoMap') {
                    document.getElementById("infoMapid").click();
                } else if ('planeMap' == docid) {
                    document.getElementById("planeMapid").click();
                }
                else if ('oilMap' == docid) {
                    document.getElementById("oilMapid").click();
                }
                else if ('parkingMap1' == docid) {
                    document.getElementById("parkingMap1id").click();
                }
                else if ('parkingMap2' == docid) {
                    document.getElementById("parkingMap2id").click();
                }
            },
            backairportmap()
            {
                this.visible = false;
                this.airportmapshow = true;
            },
            handleView (name) {
                this.imgName = name;
                this.visible = true;
                this.airportmapshow = false;
            },
            handleSuccessinfoMap (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                let self = this;
                self.airpmap.infoMap = res.url;
                if (self.airpmap.infoMap != '') {
                    self.infoMapseen = true;
                    self.infoMapseen1 = false;
                } else {
                    self.infoMapseen = false;
                    self.infoMapseen1 = true;
                }
            },
            removeinfoMap(){
                let self = this;
                self.airpmap.infoMap = '';
                self.infoMapseen = false;
                self.infoMapseen1 = true;
            },
            handleSuccessplaneMap (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                file.url = res.url;
                let self = this;
                self.airpmap.planeMap = res.url;
                if (self.airpmap.planeMap != '') {
                    self.planeMapseen = true;
                    self.planeMapseen1 = false;
                } else {
                    self.planeMapseen1 = true;
                    self.planeMapseen = false;
                }
            },
            removeplaneMap(){
                let self = this;
                self.airpmap.planeMap = ''
                self.planeMapseen1 = true;
                self.planeMapseen = false;
            },
            handleSuccessoilMap (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                file.url = res.url;
                let self = this;
                self.airpmap.oilMap = res.url;
                if (self.airpmap.oilMap != '') {
                    self.oilMapseen = true;
                    self.oilMapseen1 = false;
                } else {
                    self.oilMapseen1 = true;
                    self.oilMapseen = false;
                }
            },
            removeoilMap(){
                let self = this;
                self.airpmap.oilMap = '';
                self.oilMapseen1 = true;
                self.oilMapseen = false;
            },
            handleSuccessparkingMap1 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                file.url = res.url;
                let self = this;
                self.airpmap.parkingMap1 = res.url;
                if (self.airpmap.parkingMap1 != '') {
                    self.parkingMap1seen = true;
                    self.parkingMap1seen1 = false;
                } else {
                    self.parkingMap1seen = false;
                    self.parkingMap1seen1 = true;
                }
            },
            removeparkingMap1()
            {
                let self = this;
                self.airpmap.parkingMap1 = '';
                self.parkingMap1seen = false;
                self.parkingMap1seen1 = true;
            },
            handleSuccessparkingMap2(res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                file.url = res.url;
                let self = this;
                self.airpmap.parkingMap2 = res.url;
                if (self.airpmap.parkingMap2 != '') {
                    self.parkingMap2seen = true;
                    self.parkingMap2seen1 = false;
                } else {
                    self.parkingMap2seen = false;
                    self.parkingMap2seen1 = true;
                }
            },
            removeparkingMap2()
            {
                let self = this;
                self.airpmap.parkingMap2 = '';
                self.parkingMap2seen = false;
                self.parkingMap2seen1 = true;
            },
            handleFormatError (file) {
                this.$Notice.warning({
                    title: '文件格式不正确',
                    desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
                });
            },
            handleMaxSize (file) {
                this.$Notice.warning({
                    title: '超出文件大小限制',
                    desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
                });
            },
            handleBeforeUploadinfoMap()
            {
                this.airpmap.infoMap = require('../../../assets/imgupload.gif');
            },
            handleBeforeUploadplaneMap()
            {
                this.airpmap.planeMap = require('../../../assets/imgupload.gif');
            },
            handleBeforeUploadoilMap()
            {
                this.airpmap.oilMap = require('../../../assets/imgupload.gif');
            },
            handleBeforeUploadparkingMap1()
            {
                this.airpmap.parkingMap1 = require('../../../assets/imgupload.gif');
            },
            handleBeforeUploadparkingMap2()
            {
                this.airpmap.parkingMap2 = require('../../../assets/imgupload.gif');
            },


            handleSubmit: function () {
                let self = this;

                //     let path=self.$axios.defaults.baseURL;
                self.airpmap.airportId = self.airid;
                if (typeof (self.meterLeaveMapList) == 'undefined' && typeof (self.meterEnterMapList) == 'undefined' && typeof (self.meterApproachMapList) == 'undefined') {

                } else {

                    self.airpmap.meterLeaveMap = JSON.stringify(self.meterLeaveMapList);//将json转换成字符串
                    self.airpmap.meterEnterMap = JSON.stringify(self.meterEnterMapList);
                    self.airpmap.meterApproachMap = JSON.stringify(self.meterApproachMapList);

                    //var myE = eval(normalstring); 将字符串转成JSON

                }
                if (self.airpmap.airportId > 0) {
                    self.$http.jsonPost('/app/api/airpmap', self.airpmap).then(function (response) {
                        // self.airpmapmodal=true;
                        self.airpmapmessage = '提交成功';
                        self.$Message.success('提交成功');
                    }).catch(function (error) {
                        console.log(error);
                        //   self.airpmapmodal=true;
                        self.airpmapmessage = '提交失败';
                        self.$Message.error('提交失败');
                    });
                } else {
                    // self.airpmapmodal=true;
                    // self.airpmapmessage='请先完善机场信息';
                }

            },


            getuploadList(pathlist, flg, path){
                if (flg) {
                    this.visible = false;
                    this.airportmapshow = true;
                } else {
                    this.visible = true;
                    this.airportmapshow = false;
                    this.imgName = path;
                }
            },

        },


        watch: {
            getairportid: function (newval) {
                this.airid = newval;

                //       this.initmap(newval);
            }
        }
    }
</script>
<style>


</style>