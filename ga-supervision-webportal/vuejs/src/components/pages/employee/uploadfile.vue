
<template>
    <div>
        <div class="license-demo-upload-list" v-for="item in uploadList">
            <template >
                <img :src="item" style="width: 140px;">
                <div class="license-demo-upload-list-cover">

                    <div style="margin-top: 3%;margin-left: 65%;">
                        <Icon type="ios-eye-outline" @click.native="handleView(item)"></Icon>
                        <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
                    </div>
                    <div style="display: inline-block; text-align: center; padding-top: 5px;">{{item.name}}</div>
                </div>
            </template>

        </div>
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess"
                :format="['jpg','jpeg','png']"
                :max-size="204800"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUpload"
                multiple
                type="drag"
                action="http://127.0.0.1:18081/upload"
                style="display: inline-block;width:140px;margin-top: 35px;margin-left: 10px">
            <div style="width:140px;height: 89px;">
                <!--<img src="../../../assets/defalutupload.png" style="width: 140px;">-->
                <div style="background-color:#f8fcff;">
                    <img src="../../../assets/添加图片图标.png" style="padding-left: 50px">
                    <h4 style="font-size: 6px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">图片小于2M<br>尺寸小于640*480</h4>
                </div>
            </div>
        </Upload>
        <Modal transfer="false" title="图片详情" v-model="visible" :styles="{width:'850px'}" v-if="imgName==''" >
            <img src="../../../assets/defalutupload.png" v-if="visible" style="width: 100%;line-height: 58px;padding-bottom: 3.5%">
        </Modal>
        <Modal transfer="false" title="图片详情" v-model="visible" :styles="{width:'850px'}" v-else >
            <img :src="imgName" v-if="visible" style="width: 100%;padding-bottom: 3.5%">
        </Modal>
    </div>
</template>
<script>
    export default {
        props:['uploadList'],
        data () {
            return {


                imgName: '',
                visible: false,
            }
        },
        methods: {
            handleView (name) {
                this.imgName = name;
                this.visible = true;
            },
            handleRemove (file) {
                // 从 upload 实例删除数据
                this.uploadList.splice(this.uploadList.indexOf(file), 1);
            },
            handleSuccess (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                console.log(res.url);
                this.uploadList.push(res.url);

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
            handleBeforeUpload () {
                const check = this.uploadList.length < 4;
                if (!check) {
                    this.$Notice.warning({
                        title: '最多只能上传 5 张图片。'
                    });
                }
                return check;
            }
        },
        mounted () {
            this.$emit('listenTochildEvent',this.uploadList);
        }
    }
</script>
<style>
    .license-demo-upload-list{
        /*display: inline-block;*/
        /*width: 140px;*/
        /*height: 89px;*/
        /*left: 10px;*/
        /*text-align: center;*/
        /*line-height: 60px;*/
        /*border: 1px solid transparent;*/
        /*border-radius: 4px;*/
        /*overflow: hidden;*/
        /*background: #fff;*/
        /*position: relative;*/
        /*box-shadow: 0 1px 1px rgba(0,0,0,.2);*/
        /*margin-right: 4px;*/
        display: inline-block;
        width: 140px;
        height: 89px;
        left: 10px;
        text-align: center;
        /*line-height: 60px;*/
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        background: #fff;
        position: relative;
        box-shadow: 0 1px 1px rgba(0,0,0,.2);
        margin-right: 4px;
    }
    .license-demo-upload-list img{
        width: 100%;
        height: 100%;
    }
    .license-demo-upload-list-cover{
        /*display: none;*/
        /*position: absolute;
        top: 145px;
        left: 0;
        right: 0;
        background: rgba(0,0,0,.6);
        text-align: right;
        padding-right: 5px;
        padding-top: 5px;
        width: 100%;
        height: 30px;*/
        display: none;
        position: absolute;
        top: 10;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(1, 107, 217,0.6);
        height: 30px;
    }
    .license-demo-upload-list:hover .license-demo-upload-list-cover{
        display: block;
    }
    .license-demo-upload-list-cover i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
</style>