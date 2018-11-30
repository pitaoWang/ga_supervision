
<template>
    <div class="demo-upload" style="">
        <div style="display: inline-block;margin-left: 20px">
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess3"
                :format="['jpg','jpeg','png']"
                :max-size="2048"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUploadurl3"
                multiple
                action="http://127.0.0.1:18081/upload"
                style="display: inline-block;width:200px;">
            <div style="width: 220px;height:150px;border: 1px solid #DCDFE6" v-if="itemflag3">
                <img :src="itemflag3" style="width: 220px;height:150px;line-height: 58px;">
            </div>
            <div style="width: 220px;height:150px;background-color:#f8fcff;border: 1px solid #DCDFE6" v-else>
                <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 90px">
                <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 6px">无线电通信资格证<br>图片小于2M<br>尺寸小于640*480</h4>
            </div>
        </Upload>
            <div class="company-demo-upload-list-cover" style="width: 220px;" v-if="itemflag3">
                <div class="onclickimg3" @click="handleRemoveitemflag3(itemflag3)"> &nbsp; </div>
            </div>
        </div>

        <div style="display: inline-block;margin-left: 90px">
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess4"
                :format="['jpg','jpeg','png']"
                :max-size="2048"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUploadurl4"
                multiple
                action="http://127.0.0.1:18081/upload"
                style="display: inline-block;width:200px;">
            <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
            <div style="width: 220px;height:150px;border: 1px solid #DCDFE6" v-if="itemflag4">
                <img :src="itemflag4" style="width: 220px;height:150px;line-height: 58px;">
            </div>
            <div style="width: 220px;height:150px;background-color:#f8fcff;border: 1px  solid #DCDFE6" v-else>
                <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 90px">
                <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 6px">英语通信等级证<br>图片小于2M<br>尺寸小于640*480</h4>
            </div>
        </Upload>
            <div class="company-demo-upload-list-cover" style="width: 220px;" v-if="itemflag4">
                <div class="onclickimg3" @click="handleRemoveitemflag4(itemflag4)"> &nbsp; </div>
            </div>
        </div>

        <div style="display: inline-block;margin-left: 90px">
            <Upload
                    ref="upload"
                    :show-upload-list="false"
                    :on-success="handleSuccess5"
                    :format="['jpg','jpeg','png']"
                    :max-size="2048"
                    :on-format-error="handleFormatError"
                    :on-exceeded-size="handleMaxSize"
                    :before-upload="handleBeforeUploadurl5"
                    multiple
                    action="http://127.0.0.1:18081/upload"
                    style="display: inline-block;width:200px;">
                <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
                <div style="width: 220px;height:150px;border: 1px solid #DCDFE6" v-if="itemflag5">
                    <img :src="itemflag5" style="width: 220px;height:150px;line-height: 58px;">
                </div>
                <div style="width: 220px;height:150px;background-color:#f8fcff;border: 1px solid #DCDFE6" v-else>
                    <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 90px">
                    <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 6px">汉语通信等级证<br>图片小于2M<br>尺寸小于640*480</h4>
                </div>
            </Upload>
            <div class="company-demo-upload-list-cover" style="width: 220px;" v-if="itemflag5">
                <div class="onclickimg3" @click="handleRemoveitemflag5(itemflag5)"> &nbsp; </div>
            </div>
        </div>


        <div style="display: inline-block;margin-left: 90px">
            <Upload
                    ref="upload"
                    :show-upload-list="false"
                    :on-success="handleSuccess6"
                    :format="['jpg','jpeg','png']"
                    :max-size="2048"
                    :on-format-error="handleFormatError"
                    :on-exceeded-size="handleMaxSize"
                    :before-upload="handleBeforeUploadurl6"
                    multiple
                    action="http://127.0.0.1:18081/upload"
                    style="display: inline-block;width:200px;">
                <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
                <div style="width: 220px;height:150px;border: 1px solid rgb(1,107,217)" v-if="itemflag6">
                    <img :src="itemflag6" style="width: 220px;height:150px;line-height: 58px;">
                </div>
                <div style="width: 220px;height:150px;background-color:#f8fcff;border: 1px solid #DCDFE6" v-else>
                    <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 90px">
                    <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 6px">飞行经历扫描件<br>图片小于2M<br>尺寸小于640*480</h4>
                </div>
            </Upload>
            <div class="company-demo-upload-list-cover" style="width: 220px;" v-if="itemflag6">
                <div class="onclickimg3" @click="handleRemoveitemflag6(itemflag6)"> &nbsp; </div>
            </div>
        </div>
    </div>
</template>
<script>
    import defaultImage from '../../../../assets/defalutupload.png';
    export default {
       // props:['itemflag'],
        data () {
            return {
                h4show:true,
                imgName: '',
                visible: false,
                uploadList: [],
                url:'',
                itemflag3:'',
                itemflag4:'',
                itemflag5:'',
                itemflag6:'',
            }
        },

        methods: {

            handleRemoveitemflag3 (file) {
                this.h4show=true;
                this.itemflag3='';
                this.$emit('childurlone2',this.itemflag3,'itemflag3');
            },
            handleRemoveitemflag4 (file) {
                this.h4show=true;
                this.itemflag4='';
                this.$emit('childurlone2',this.itemflag4,'itemflag4');
            },
            handleRemoveitemflag5 (file) {
                this.h4show=true;
                this.itemflag5='';
                this.$emit('childurlone2',this.itemflag5,'itemflag5');
            },
            handleRemoveitemflag6 (file) {
                this.h4show=true;
                this.itemflag6='';
                this.$emit('childurlone2',this.itemflag6,'itemflag6');
            },

            handleSuccess3 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag3=res.url;

                this.$emit('childurlone2',this.itemflag3,'itemflag3');
            },
            handleSuccess4 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag4=res.url;

                this.$emit('childurlone2',this.itemflag4,'itemflag4');
            },
            handleSuccess5 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag5=res.url;

                this.$emit('childurlone2',this.itemflag5,'itemflag5');
            },
            handleSuccess6 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag6=res.url;

                this.$emit('childurlone2',this.itemflag6,'itemflag6');
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
            handleBeforeUploadurl3() {
                this. itemflag3=require( '../../../../assets/imgupload.gif');
            },
            handleBeforeUploadurl4() {
                this.itemflag4 = require('../../../../assets/imgupload.gif');

            },
            handleBeforeUploadurl5() {
                this.itemflag5 = require('../../../../assets/imgupload.gif');

            },
            handleBeforeUploadurl6() {
                this.itemflag6 = require('../../../../assets/imgupload.gif');

            },
            urlupdate (obj){
                if(obj.wqc!=null){
                    this.itemflag3 = obj.wqc;
                };
                if(obj.esCertificate != null){
                    this.itemflag4 =obj.esCertificate;
                };
                if(obj.chCertificate != null){
                    this.itemflag5 =obj.chCertificate;
                };
                if(obj.scanning != null){
                    this.itemflag6 =obj.scanning;
                }
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
        background: rgb(55,122,220);
        height: 28px;
        text-align: right;
        padding-right: 10px;
        color: #fff;
        font-size: 20px;
        cursor: pointer;
    }
    .company-demo-upload-list-cover{
        background: #52D2E2;
        height: 28px;
        text-align: right;
        padding-right: 2px;
        padding-top: 1px;
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