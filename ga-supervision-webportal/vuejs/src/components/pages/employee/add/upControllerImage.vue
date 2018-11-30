
<template>
    <div class="demo-upload" style="">
        <div style="display: inline-block;margin-left: 20px">
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess7"
                :format="['jpg','jpeg','png']"
                :max-size="2048"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUploadurl7"
                multiple
                action="http://127.0.0.1:18081/upload"
                style="display: inline-block;width:200px;">
            <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
            <div style="width: 283px;height:174px;border: 1px solid #DCDFE6" v-if="itemflag7">
                <img :src="itemflag7" style="width: 281px;height:174px;line-height: 58px;">
            </div>
            <div style="width: 283px;height:174px;background-color:#f8fcff;border: 1px solid #DCDFE6" v-else>
                <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 120px">
                <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">特殊技能扫描件<br>图片小于2M<br>尺寸小于640*480</h4>
            </div>
        </Upload>
        </div>
        <div style="display: inline-block;margin-left: 90px">
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess8"
                :format="['jpg','jpeg','png']"
                :max-size="2048"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUploadurl8"
                multiple
                action="http://127.0.0.1:18081/upload"
                style="display: inline-block;width:200px;">
            <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
            <div style="width: 283px;height:174px;border: 1px solid #DCDFE6" v-if="itemflag8">
                <img :src="itemflag8" style="width: 281px;height:174px;line-height: 58px;">
            </div>
            <div style="width: 283px;height:174px;background-color:#f8fcff;border: 1px solid rgb(1,107,217)" v-else>
                <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 120px">
                <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">英语资格通信等级扫描件<br>图片小于2M<br>尺寸小于640*480</h4>
            </div>
        </Upload>
        </div>

        <div style="display: inline-block;margin-left: 90px">
            <Upload
                    ref="upload"
                    :show-upload-list="false"
                    :on-success="handleSuccess9"
                    :format="['jpg','jpeg','png']"
                    :max-size="2048"
                    :on-format-error="handleFormatError"
                    :on-exceeded-size="handleMaxSize"
                    :before-upload="handleBeforeUploadurl9"
                    multiple
                    action="http://127.0.0.1:18081/upload"
                    style="display: inline-block;width:200px;">
                <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
                <div style="width: 283px;height:174px;border: 1px solid #DCDFE6" v-if="itemflag9">
                    <img :src="itemflag9" style="width: 281px;height:174px;line-height: 58px;">
                </div>
                <div style="width: 283px;height:174px;background-color:#f8fcff;border: 1px solid rgb(1,107,217)" v-else>
                    <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 120px">
                    <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">英语无线电路空通信资格扫描件<br>图片小于2M<br>尺寸小于640*480</h4>
                </div>
            </Upload>
        </div>

        <div style="display: inline-block;margin-left: 90px">
            <Upload
                    ref="upload"
                    :show-upload-list="false"
                    :on-success="handleSuccess10"
                    :format="['jpg','jpeg','png']"
                    :max-size="2048"
                    :on-format-error="handleFormatError"
                    :on-exceeded-size="handleMaxSize"
                    :before-upload="handleBeforeUploadurl10"
                    multiple
                    action="http://127.0.0.1:18081/upload"
                    style="display: inline-block;width:200px;">
                <!--<Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>-->
                <div style="width: 283px;height:174px;border: 1px solid #DCDFE6" v-if="itemflag10">
                    <img :src="itemflag10" style="width: 281px;height:174px;line-height: 58px;">
                </div>
                <div style="width: 283px;height:174px;background-color:#f8fcff;border: 1px solid rgb(1,107,217)" v-else>
                    <img src="../../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 120px">
                    <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">特殊技能水平扫描件<br>图片小于2M<br>尺寸小于640*480</h4>
                </div>
            </Upload>
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
                itemflag7:'',
                itemflag8:'',
                itemflag9:'',
                itemflag10:'',
            }
        },

        methods: {

            handleRemoveitemflag7 (file) {
                this.h4show=true;
                this.itemflag7='';
                this.$emit('childurlone3',this.itemflag7,);
            },
            handleRemoveitemflag8 (file) {
                this.h4show=true;
                this.itemflag8='';
                this.$emit('childurlone3',this.itemflag8,);
            },
            handleRemoveitemflag9 (file) {
                this.h4show=true;
                this.itemflag9='';
                this.$emit('childurlone3',this.itemflag9,);
            },
            handleRemoveitemflag10 (file) {
                this.h4show=true;
                this.itemflag10='';
                this.$emit('childurlone3',this.itemflag10,);
            },

            handleSuccess7 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag7=res.url;

                this.$emit('childurlone3',this.itemflag7,'itemflag7');
            },
            handleSuccess8 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag8=res.url;

                this.$emit('childurlone3',this.itemflag8,'itemflag8');
            },
            handleSuccess9 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag9=res.url;

                this.$emit('childurlone3',this.itemflag9,'itemflag9');
            },
            handleSuccess10 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag10=res.url;

                this.$emit('childurlone3',this.itemflag10,'itemflag10');
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
            handleBeforeUploadurl7() {
                this. itemflag7=require( '../../../../assets/imgupload.gif');
            },
            handleBeforeUploadurl8() {
                this.itemflag8 = require('../../../../assets/imgupload.gif');

            },
            handleBeforeUploadurl9() {
                this.itemflag9 = require('../../../../assets/imgupload.gif');

            },
            handleBeforeUploadurl10() {
                this.itemflag10 = require('../../../../assets/imgupload.gif');

            },
            urlupdate (obj){
                if(obj.skill!=null){
                    this.itemflag7 = obj.skill;
                };
                if(obj.englishLevel != null){
                    this.itemflag8 =obj.englishLevel;
                };
                if(obj.commuStatus != null){
                    this.itemflag9 =obj.commuStatus;
                };
                if(obj.skillLevel != null){
                    this.itemflag10 =obj.skillLevel;
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
        background: #DCDFE6;
        height: 28px;
        text-align: right;
        padding-right: 10px;
        color: #fff;
        font-size: 20px;
        cursor: pointer;
    }


</style>