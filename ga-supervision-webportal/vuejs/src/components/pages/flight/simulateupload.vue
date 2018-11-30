<template>
    <div>
        <div class="div-float-with">
            <div class="div-float-addair" style="position: relative">
                <div class="demo-upload-list-pliot"  style="height: 150px;" v-show="firturlshow">
                    <div class="demo-upload-list-cover-pliot">
                        <Icon type="ios-trash-outline" @click.native="handleRemoveitemflag1(url1)"></Icon>
                    </div>
                    <div style="width: 250px;height:150px;border: 1px solid rgb(1,107,217);margin-top: -27px" >
                        <img :src="url1" style="width: 250px;height:150px;line-height: 58px;">
                    </div>

                </div>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess1"
                        :before-upload="handleBeforeUploadurl1"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        multiple
                        action="http://localhost:18081/upload"
                        style="display: inline-block;width:250px;">
                    <div style="width: 250px;height:150px;background-color:#f8fcff;border: 1px solid rgb(1,107,217);margin-top: -27px" v-show="firturlshow1">
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 37px;margin-left: 112px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">飞行器照片<br>图片小于2M</h4>
                    </div>

                    <!--style="display: inline-block;width:300px;">
                <div style="width: 300px;height:150px;border: 1px solid #DCDFE6;margin-top: -27px" v-if="url1">
                    <img :src="url1" style="width: 300px;height:200px;line-height: 58px;">
                </div>
                <div style="width: 300px;height:200px;background-color:#f8fcff;border: 1px solid #DCDFE6;margin-top: -27px" v-else>
                    <img src="../../../assets/添加图片图标.png" style="margin-top: 37px;margin-left: 112px">
                    <h4 style="font-size: 13px;opacity: 0.66;#8A909F;text-align: center;margin-top: 15px">飞行器照片<br>图片小于2M</h4>
                </div>-->
                </Upload>

            </div>
            <div class="div-float-addair" style="position: relative">
                <div class="demo-upload-list-pliot"  style="height: 150px;" v-show="firturlshows">
                    <div class="demo-upload-list-cover-pliot">
                        <Icon type="ios-trash-outline" @click.native="handleRemoveitemflag2(url2)"></Icon>
                    </div>
                    <div style="width: 250px;height:150px;border: 1px solid rgb(1,107,217);margin-top: -27px" >
                        <img :src="url2" style="width: 250px;height:150px;line-height: 58px;">
                    </div>
                </div>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess2"
                        :before-upload="handleBeforeUploadurl2"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        multiple
                        action="http://localhost:18081/upload"
                        style="display: inline-block;width:250px;">
                    <div style="width: 250px;height:150px;background-color:#f8fcff;border: 1px solid rgb(1,107,217);margin-top: -27px" v-show="firturlshows1">
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 37px;margin-left: 112px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">合格证扫描件<br>图片小于2M</h4>
                    </div>

                </Upload>
            </div>
        </div>
        <Modal transfer="false" title="图片详情" v-model="visible" :styles="{width:'850px'}" >
            <img :src="imgurl" v-if="visible" style="width: 100%;padding-bottom: 3.5%">
        </Modal>
    </div>
</template>
<script>
    import Cookies from 'js-cookie';
    export default {
        data () {
            return {
                firturlshow:false,//身份证真面
                firturlshow1:true,//身份证默认
                firturlshows:false,//身份证真面
                firturlshows1:true,//身份证默认
                visible: false,
                uploadList: [],
                url:'',
                imgurl:'',
                props:['simulateupdateurl'],
                url1:'',
                url2:'',
                imgName: '',
                info:{
                    name:'',
                    path:'',
                },
                companyid:'',
            }
        },
        mounted(){
            let self = this;
            let buz = JSON.parse(Cookies.get("profile"));
                self.companyid = buz.buz.companyId;
                self.info.path="/" + self.companyid + "/simulate"

        },
        methods: {
            handleSuccess1 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url1=res.url;
                this.$emit('childurlone',res.url,'url1');
                this.firturlshow1 = false;
                this.firturlshow = true;

            },
            handleSuccess2 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url2=res.url;
                this.$emit('childurlone',res.url,'url2');
                this.firturlshows1 = false;
                this.firturlshows = true;
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
            },urlupdate (obj){

                if(obj.photos!=null&&obj.photos!=""){

                    this.url1=obj.photos;
                    this.firturlshow1 = false;
                    this.firturlshow = true;
                }
                if(obj.certificateFile!=null&&obj.certificateFile!=""){
                    this.url2=obj.certificateFile;
                    this.firturlshows1 = false;
                    this.firturlshows = true;
                }
            },updateclean(){
                this.url1='';
                this.url2='';
            },
            handleRemoveitemflag1(){
                this.url1='';
                this.$emit('childurlone',this.url1,'url1');
                this.firturlshow = false;
                this.firturlshow1 = true;
            },
            handleRemoveitemflag2(){
                this.url2='';
                this.firturlshows = false;
                this.firturlshows1 = true;
                this.$emit('childurlone',this.url2,'url2');
            },
            handleBeforeUploadurl1()
            {
                this.url1 =require( '../../../assets/imgupload.gif');
            },
            handleBeforeUploadurl2()
            {
                this.url2 =require( '../../../assets/imgupload.gif');
            },
        }
    }
</script>
<style>
    .span-label{
        display: block;
        float: left;
        width: 90px;
        padding-left: 15px;
        padding-right: 15px;
        margin-left: 20px;
        font-size: 14px;
        color: #016bd9;
    }
    .span-label-1{
        display: block;
        float: left;
        width: 90px;
        padding-left: 15px;
        padding-right: 15px;
        margin-left: 15px;
        font-size: 14px;
        color: #016bd9;
    }
    .div-float-addair{
        float: left;
        margin-left: 40px;
    }

    .div-float-with{
        margin-top: 20px;
        width: 1280px;


    }



    .demo-upload-list-coer i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }


    .demo-upload-list-coer{
        position: relative;
        top:199px;
        color: #fff;
        background: rgb(55,122,220);
        height: 28px;
        text-align: right;
        padding-right: 10px;
        font-size: 20px;
        cursor: pointer;
    }
    .demo-upload-list-pliot:hover .demo-upload-list-cover-pliot{
        display: block;
    }
    .demo-upload-list-cover-pliot{
        display: none;
        position: absolute;
        top: 90px;
        bottom: 0;
        left: 2px;
        right: 0px;
        background: rgba(0,0,0,.6);
        /* width: 120px; */
        height: 30px;
        width: 98%;
        /* padding-left: 199px; */
        text-align: right;
        padding-right: 10px;
        padding-top: 5px;
        margin-bottom: 16px;
    }
    .demo-upload-list-cover-pliot i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
</style>
