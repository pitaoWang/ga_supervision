
<template>
    <div>
    <div class="gacompany-upload-list" v-for="item in uploadList">
        <template >

            <a :href="item.url" style=" margin-right: 10px; margin-top: 5px; margin-bottom: 10px; font-size: 14px;">{{item.name}}</a><Icon type="ios-trash-outline" size="20" @click.native="handleRemove(item.url)"></Icon>
        </template>
    </div>
        <div class="gacompany-upload-list" v-if="visible">
            <Progress :percent="60" status="active" stroke-width="10">
                <Icon type="checkmark-circled"></Icon>
                <span>正在上传...</span>
            </Progress>
            </div>
    <Upload
            ref="upload"
            :show-upload-list="false"
            :on-success="handleSuccess"
            :format="['pdf']"
            :max-size="50240"
            :on-format-error="handleFormatError"
            :on-exceeded-size="handleMaxSize"
            :before-upload="handleBeforeUpload"
            multiple
            type="drag"
            :data=pathparam
            action="http://127.0.0.1:18081/upload"
            style="display: inline-block;width:100px;height:50px;margin-top: 10px;margin-left: 70px">

        <div style="color: #4a4a4a;">
            <Icon type="ios-cloud-upload-outline" size="27"></Icon>
        </div>
    </Upload>

</div>
</template>
<script>
    export default {
        props:['uploadList','filepath'],
        data () {
            return {
                imgName: '',
                visible: false,
                pathparam:{
                    path:'',
                },
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
                this.visible=false;
                this.uploadList.push({name:res.name,url:res.url});

            },
            handleFormatError (file) {
                this.visible=false;
                this.$Notice.warning({
                    title: '文件格式不正确',
                    desc: '文件 ' + file.name + ' 格式不正确，请上传pdf格式文件。'
                });
            },
            handleMaxSize (file) {
                this.visible=false;
                this.$Notice.warning({
                    title: '超出文件大小限制',
                    desc: '文件 ' + file.name + ' 太大，不能超过 50M。'
                });
            },
            handleBeforeUpload () {
                this.visible=true;
            },
        },
        watch:
        {
            filepath:function(ss)
            {
                this.pathparam.path=this.filepath;
            }
        },
        mounted () {
            this.$emit('gacomplistenTochildEvent',this.uploadList);
        }
    }
</script>
<style>
    .gacompany-upload-list{
        left: 10px;
        text-align: left;
        line-height:10px;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        color: #fff;
        position: relative;
        margin-right: 4px;
        margin-left: 60px;
    }
    .gacompany-upload-list img{
        width: 100%;
        height: 100%;
    }
    .gacompany-upload-list-cover{
        display: none;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0,0,0,.6);
    }
    .gacompany-upload-list:hover .gacompany-upload-list-cover{
        display: block;
    }
    .gacompany-upload-list-cover i{
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
