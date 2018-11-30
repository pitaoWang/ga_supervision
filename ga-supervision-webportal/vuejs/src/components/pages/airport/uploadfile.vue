<template>
    <div>
        <div class="airportmap-upload-list" v-for="item in uploadList">
            <template>
                <img :src="item.url" style="width: 283px;">
                <div class="airportmap-upload-list-cover">
                    <Icon type="ios-trash-outline" @click.native="handleRemove(item.url)"></Icon>
                    <Icon type="ios-eye-outline" @click.native="handleView(item.url)"></Icon>
                    <div style="display: inline-block; text-align: center; padding-top: 5px;">{{item.name}}</div>
                </div>

            </template>
        </div>
        <Upload
                ref="upload"
                :show-upload-list="false"
                :on-success="handleSuccess"
                :format="['jpg','jpeg','png']"
                :max-size="2048"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUpload"
                :data=pathparam
                multiple
                type="drag"
                action="http://fs.glinkus.com:18081/upload"
                style="display: inline-block;width:283px;height:175px;margin-top: 10px;margin-left: 10px">
            <div class="uploadimg5">
                <div style="height: 90px;">
                    <img src="../../../assets/defalutupload.png" class="airportmap-upload-div-img" style="margin-left: 122px; padding-top: 45px;">
                </div>
                <div class="uploadfile-upload-div-div">图片小于2M</br>尺寸小于640*480</div>
            </div>
        </Upload>
        <!--    <Modal title="查看图片" v-model="visible" :styles="{'z-index':'20'}">
                <img :src="imgName" v-if="visible" style="width: 100%">
            </Modal>
            -->
    </div>
</template>
<script>
    export default {
        props: ['uploadList', 'filepath'],
        data () {
            return {
                imgName: '',
                visible: false,
                pathparam: {
                    path: 'ss',
                },
            }
        },
        methods: {
            handleView (name) {
                this.imgName = name;
                this.visible = true;
                this.$emit('listenTochildEvent', this.uploadList, false, this.imgName);
            },
            handleRemove (file) {
                // 从 upload 实例删除数据
                this.uploadList.splice(this.uploadList.indexOf(file), 1);
            },
            handleSuccess (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.uploadList.splice(this.uploadList.length - 1, 1);
                this.uploadList.push({name: res.name, url: res.url});

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
                const check = this.uploadList.length < 20;
                if (!check) {
                    this.$Notice.warning({
                        title: '最多只能上传 20 张图片。'
                    });
                }
                this.uploadList.push({name: '', url: require('../../../assets/imgupload.gif')});
                return check;
            }
        },
        watch: {
            filepath: function () {
                this.pathparam.path = this.filepath;

            }
        },
        mounted () {
            this.$emit('listenTochildEvent', this.uploadList, true, '');
        }
    }
</script>
<style>
    .uploadfile-upload-div-div {
        font-size: 0.7rem;
        text-align: center;
        text-align: center;
        width: 100%;
        float: left;
        display: block;
        margin-top: 12px;
        line-height: 17px;
        height: 67px;
        color: rgba(1, 107, 217, 0.5);
    }
    .airportmap-upload-list {
        display: inline-block;
        width: 283px;
        height: 175px;
        left: 10px;
        text-align: center;
        /*line-height: 60px;*/
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        background: #fff;
        position: relative;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
        margin-right: 4px;
    }

    .airportmap-upload-list img {
        width: 100%;
        height: 100%;
    }

    .airportmap-upload-list-cover {
        display: none;
        position: absolute;
        top: 10;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0, 0, 0, .6);
        height: 30px;
    }

    .airportmap-upload-list:hover .airportmap-upload-list-cover {
        display: block;
    }

    .airportmap-upload-list-cover i {
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
        float: right;
        padding-top: 8px;
        /* margin: 8px; */
        padding-right: 8px;
    }
</style>
