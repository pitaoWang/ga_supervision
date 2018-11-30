
<template>
    <div class="demo-upload" style="">
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess"
                :format="['jpg','jpeg','png']"
                :max-size="2048"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUpload"
                multiple
                v-bind:action=this.uploadUrl
                style="display: inline-block;width:200px;">
            <Icon class="demo-upload-lise-cover icon" type="ios-compose-outline"></Icon>
            <div style="width: 283px;height:174px;border: 1px solid rgb(1,107,217)" v-if="itemflag">
                <img :src="this.imageUrl+itemflag+'.thumb.jpg'" style="width: 281px;height:174px;line-height: 58px;">
            </div>
            <div style="width: 283px;height:174px;background-color:#f8fcff;border: 1px solid rgb(1,107,217)" v-else>
                <img src="../../../assets/添加图片图标.png" style="margin-top: 50px;margin-left: 120px">
                <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">图片小于2M<br>尺寸小于640*480</h4>
            </div>
        </Upload>

        <div v-if="itemflag" class="demo-upload-lise-cover">
            <Upload
                    ref="upload"
                    :show-upload-list="false"
                    :on-success="handleSuccess"
                    :format="['jpg','jpeg','png']"
                    :max-size="2048"
                    :on-format-error="handleFormatError"
                    :on-exceeded-size="handleMaxSize"
                    :before-upload="handleBeforeUpload"
                    multiple
                    v-bind:action=this.uploadUrl
                    style="display: inline-block;">
                <Icon type="ios-compose-outline"></Icon>
            </Upload>&nbsp;&nbsp;

            <Icon type="ios-eye-outline" @click.native="handleView(itemflag)"></Icon>
            <Icon type="ios-trash-outline" @click.native="handleRemoveitemflag(itemflag)"></Icon>
        </div>
        <Modal transfer="false" title="图片详情" v-model="visible" :styles="{width:'850px'}">
            <img :src="this.imageUrl+itemflag" v-if="visible" style="width: 100%;padding-bottom: 3.5%">
        </Modal>
    </div>

</template>
<script>
    import defaultImage from '../../../assets/defalutupload.png';
    export default {
        props:['itemflag'],
        data () {
            return {
                h4show:true,
                imgName: '',
                visible: false,
                uploadList: [],
                url:''
            }
        },
//        watch:{
//            itemflag: function (newval) {
//                 console.log(newval);
//                if(newval==null){
//                    this.url = defaultImage;
//                }
//                else {
//                    this.url=newval;
//
//                }
//            }
//
//
//
//        },
        methods: {
            handleView (name) {
                this.imgName = name;
                this.visible = true;
            },
            handleRemove (file) {
                // 从 upload 实例删除数据
                const fileList = this.$refs.upload.fileList;
                this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
            },
            handleRemoveitemflag (file) {
                this.h4show=true;
                this.itemflag='';
                this.$emit('listenTochildEvent',this.itemflag);
            },
            handleSuccess (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.h4show=false;
                this.itemflag=res.url;

                this.$emit('listenTochildEvent',this.itemflag);
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
                const check = this.uploadList.length < 5;
                if (!check) {
                    this.$Notice.warning({
                        title: '最多只能上传 5 张图片。'
                    });
                }
                this.itemflag=require( '../../../assets/imgupload.gif');
                return check;
            }
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


</style>