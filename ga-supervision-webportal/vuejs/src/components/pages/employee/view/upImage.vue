
<template>
    <div class="demo-upload" style="">
        <div style="margin-bottom: 20px">
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess"
                :format="['jpg','jpeg','png','bmp']"
                :max-size="2048"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUploadurl1"
                multiple
                v-bind:action=this.uploadUrl
                style="display: inline-block;width:200px;">
            <div style="margin-left:80px;width: 155px;height:165px;border: 1px solid #DCDFE6;" v-if="itemflag">
                <img :src="this.imageUrl+itemflag+'.thumb.jpg'" style="width: 153px;height:174px;line-height: 58px;">
            </div>
            <div style="margin-left:80px;width: 155px;height:174px;background-color:#f8fcff;border: 1px solid #DCDFE6" v-else>
                <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 60px">
                <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">一寸照片<br>图片小于2M<br>尺寸小于640*480</h4>
            </div>
        </Upload>
            <div class="company-demo-upload-list-cover" style="margin-left:80px;margin-top:9px;width: 155px;" v-if="itemflag">
                <!--http://fs.glinkus.com:18081/file/6b7fe8211b7b4735a72d0072c0f2d6ec.png-->
                <div class="onclickimg1" @click="handleView('0')"> &nbsp; </div>
                <div class="onclickimg3" @click="handleRemoveitemflag(itemflag)"> &nbsp; </div>
            </div>
        </div>



        <div style="margin-bottom: 20px">
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess1"
                :format="['jpg','jpeg','png','bmp']"
                :max-size="2048"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUploadurl2"
                multiple
                v-bind:action="this.uploadUrl"
                style="display: inline-block;width:200px;">
            <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
            <!--<div class="demo-upload-list-pliot2">
                <div class="demo-upload-list-cover-pliot2"  v-if="itemflag1">
                    <Icon class="demo-upload-lise-cover icon" type="ios-trash-outline" @click.native="handleRemoveitemflag1(itemflag1)"></Icon>
                </div>-->

            <div style="width: 283px;height:174px;border: 1px solid #DCDFE6" v-if="itemflag1">
                <img :src="this.imageUrl+itemflag1+'.thumb.jpg'" style="width: 281px;height:174px;line-height: 58px;">
            </div>
            <div style="width: 283px;height:174px;background-color:#f8fcff;border: 1px solid #DCDFE6" v-else>
                <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 120px">
                <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">身份证正面照<br>图片小于2M<br>尺寸小于640*480</h4>
            </div>
        </Upload>
            <div class="company-demo-upload-list-cover" style="width: 282px" v-if="itemflag1">
                <div class="onclickimg1" @click="handleView('1')"> &nbsp; </div>
                <div class="onclickimg3" @click="handleRemoveitemflag1(itemflag1)"> &nbsp; </div>
            </div>
        </div>

        <div style="margin-bottom: 20px">
            <Upload
                    ref="upload"
                    :show-upload-list="false"
                    :on-success="handleSuccess2"
                    :format="['jpg','jpeg','png','bmp']"
                    :max-size="2048"
                    :on-format-error="handleFormatError"
                    :on-exceeded-size="handleMaxSize"
                    :before-upload="handleBeforeUploadurl3"
                    multiple
                    v-bind:action="this.uploadUrl"
                    style="display: inline-block;width:200px;">
                <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
                <!--<div class="demo-upload-list-pliot3">
                    <div  class="demo-upload-list-cover-pliot3"  v-if="itemflag2">
                        <Icon class="demo-upload-lise-cover icon" type="ios-trash-outline" @click.native="handleRemoveitemflag2(itemflag2)"></Icon>
                    </div>-->

                <div style="width: 283px;height:174px;border: 1px solid #DCDFE6" v-if="itemflag2">
                    <img :src="this.imageUrl+itemflag2+'.thumb.jpg'" style="width: 281px;height:174px;line-height: 58px;">
                </div>
                <div style="width: 283px;height:174px;background-color:#f8fcff;border: 1px solid #DCDFE6" v-else>
                    <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 120px">
                    <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">身份证反面照<br>图片小于2M<br>尺寸小于640*480</h4>
                </div>
                <!--</div>-->
            </Upload>
            <div class="company-demo-upload-list-cover" style="width: 282px" v-if="itemflag2">
                <!--<div class="onclickimg1" @click=""> &nbsp; </div>-->
                <!--<div class="onclickimg2" @click=""> &nbsp; </div>-->
                <div class="onclickimg1" @click="handleView('2')"> &nbsp; </div>
                <div class="onclickimg3" @click="handleRemoveitemflag2(itemflag2)"> &nbsp; </div>
            </div>
        </div>

        <div>
            <Upload
                    ref="upload"
                    :show-upload-list="false"
                    :on-success="handleSuccess3"
                    :format="['jpg','jpeg','png']"
                    :max-size="2048"
                    :on-format-error="handleFormatError"
                    :on-exceeded-size="handleMaxSize"
                    :before-upload="handleBeforeUploadurl4"
                    multiple
                    v-bind:action="this.uploadUrl"
                    style="display: inline-block;width:200px;">
                <div style="width: 283px;height:174px;border: 1px solid #DCDFE6" v-if="itemflag3">
                    <img :src="this.imageUrl+itemflag3+'.thumb.jpg'" style="width: 281px;height:174px;line-height: 58px;">
                </div>
                <div style="width: 283px;height:174px;background-color:#f8fcff;border: 1px solid #DCDFE6" v-else>
                    <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 120px">
                    <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">电子签名<br>图片小于2M<br>尺寸小于640*480</h4>
                </div>
            </Upload>
            <div class="company-demo-upload-list-cover" style="width: 282px" v-if="itemflag3">
                <div class="onclickimg1" @click="handleView('3')"> &nbsp; </div>
                <div class="onclickimg3" @click="handleRemoveitemflag3(itemflag3)"> &nbsp; </div>
            </div>
        </div>
    </div>
    <!--<imgdetail ref="imgdetailref"></imgdetail>-->
</template>
<script>
    import defaultImage from '../../../../assets/defalutupload.png';
    /*import imgdetail from '../../../views/imgdetail.vue';*/
    export default {
       // props:['itemflag'],

        components:{
           /* 'imgdetail':imgdetail,*/
        },
        data () {
            return {
                h4show:true,
                imgName: '',
                visible: false,
                uploadList: [],
                url:'',
                itemflag:'',
                itemflag1:'',
                itemflag2:'',
                itemflag3:'',
            }
        },

        methods: {
            handleView (type) {
                if(type =='0'){
                    this.$emit('im',this.imageUrl+this.itemflag);
                }
                if(type=='1'){
                    this.$emit('im',this.imageUrl+this.itemflag1);
                }
                if(type=='2'){
                    this.$emit('im',this.imageUrl+this.itemflag2);
                }
                if(type=='3'){
                    this.$emit('im',this.imageUrl+this.itemflag3);
                }
            },

            handleRemoveitemflag (file) {
                this.h4show=true;
                this.itemflag='';
                this.$emit('childurlone',this.itemflag,'itemflag');
            },
            handleRemoveitemflag1 (file) {
                this.h4show=true;
                this.itemflag1='';
                this.$emit('childurlone',this.itemflag1,'itemflag1');
            },
            handleRemoveitemflag2 (file) {
                this.h4show=true;
                this.itemflag2='';
                this.$emit('childurlone',this.itemflag2,'itemflag2');
            },
            handleRemoveitemflag3 (file) {
                this.h4show=true;
                this.itemflag3='';
                this.$emit('childurlone',this.itemflag3,'itemflag3');
            },
            //一寸照片
            handleSuccess (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag=res.url;
                this.$emit('childurlone',this.itemflag,'itemflag');
            },
            //身份证正面
            handleSuccess1 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag1=res.url;

                this.$emit('childurlone',this.itemflag1,'itemflag1');
            },
            //身份证反面
            handleSuccess2 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag2=res.url;

                this.$emit('childurlone',this.itemflag2,'itemflag2');
            },

            handleSuccess3 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag3=res.url;

                this.$emit('childurlone',this.itemflag3,'itemflag3');
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
          /*  handleBeforeUpload () {
                const check = this.uploadList.length < 5;
                if (!check) {
                    this.$Notice.warning({
                        title: '最多只能上传 5 张图片。'
                    });
                }
                this.itemflag=require( '../../../../assets/imgupload.gif');
                return check;
            },*/
            handleBeforeUploadurl1()
            {
                this. itemflag=require( '../../../../assets/imgupload.gif');
            },
            handleBeforeUploadurl2() {
                this.itemflag1 = require('../../../../assets/imgupload.gif');

            },
            handleBeforeUploadurl3() {
                this.itemflag2 = require('../../../../assets/imgupload.gif');

            },
            handleBeforeUploadurl4() {
                this.itemflag3 = require('../../../../assets/imgupload.gif');

            },

            urlupdate (obj){
                if(obj.photo!=null){
                    this.itemflag = obj.photo;
                };
                if(obj.scanCard !=null){
                    this.itemflag1=obj.scanCard;
                };
                if(obj.backScancard !=null){
                    this.itemflag2=obj.backScancard;
                };
                if(obj.namePhoto !=null){
                    this.itemflag3=obj.namePhoto;
                };

            },


            },
        mounted () {
            console.log(this.itemflag)

        }
    }
</script>
<style>

    .demo-upload-list img{
        width: 100%;
        height: 100%;
    }


    .demo-upload-lise-cover i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
    .icon{
        padding-top:5px;
    }

    .demo-upload-lise-cover{
        display: block;
        position: absolute;
        bottom: -24px;
        left: 0;
        right: 0;
        background: #DCDFE6;
        height: 28px;
        text-align: right;
        padding-right: 10px;
        color: #fff;
        font-size: 20px;
        cursor: pointer;
    }
    .demo-upload-list-pliot:hover .demo-upload-list-cover-pliot{
        display: block;
    }
    .demo-upload-list-cover-pliot{
        display: none;
        position: absolute;
        top: 165px;
        bottom: 0;
        right: 224px;
        /*background: rgba(0,0,0,.6);*/
        /* width: 120px; */
        height: 30px;
        width: 155px;
        /* padding-left: 199px; */
        /*text-align: right;
        padding-right: 10px;
        padding-top: 5px;
        margin-bottom: 16px;*/
    }
    .demo-upload-list-pliot2:hover .demo-upload-list-cover-pliot2{
        display: block;
    }
    .demo-upload-list-cover-pliot2{
        display: none;
        position: absolute;
        top: 356px;
        bottom: 0;
        right: 98px;
        height: 30px;
        width: 280px;

    }
    .demo-upload-list-pliot3:hover .demo-upload-list-cover-pliot3{
        display: block;
    }
    .demo-upload-list-cover-pliot3{
        display: none;
        position: absolute;
        top: 550px;
        bottom: 0;
        right: 98px;
        height: 30px;
        width: 280px;

    }

    .company-demo-upload-list-cover{
        /* position: absolute; */
        /* top: 118px; */
        /* bottom: 0; */
        /* left: 0px; */
        /* right: 0px; */
        background: #52D2E2;
        /* width: 120px; */
        height: 28px;
        /* padding-left: 199px; */
        text-align: right;
        padding-right: 2px;
        padding-top: 1px;

        /* margin-bottom: 16px; */
    }
    .onclickimg1
    {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../../assets/search.png");
    }
    .onclickimg2
    {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../../assets/edit.png");
    }
    .onclickimg3
    {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../../assets/newdelete.png");
    }
</style>