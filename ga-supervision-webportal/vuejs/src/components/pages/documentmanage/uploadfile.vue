
<template>
    <div>
    <div>

    <Upload
            ref="upload"
            :show-upload-list="true"
            :on-success="handleSuccess"
            :on-remove="handleRemove"
            :max-size="50240"
            :on-format-error="handleFormatError"
            :on-exceeded-size="handleMaxSize"
            :before-upload="handleBeforeUpload"
            multiple
            type="drag"
            :action=this.uploadUrl
            style="display: inline-block;width:197px;height:50px;margin-top: 10px;float: left">
        <Button style="width: 197px;color:#606265; float: left" type="ghost" icon="ios-cloud-upload-outline">浏览</Button>
    </Upload>
        <!--<div class="attach-upload-list" style="width: 600px" >
            <template v-for="item in uploadList" >
                <a :href="item.url" style="color: #fff; margin-right: 10px; margin-top: 5px; margin-bottom: 10px; font-size: 14px;">{{item.name}}</a><Icon type="ios-trash-outline" size="20" @click.native="handleRemove (item)" style="margin-right: 20px;"></Icon>
            </template>
        </div>-->

</div>
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
            handleRemove (file,fileList) {
                // 从 upload 实例删除数据
                //this.uploadList.splice(this.uploadList.indexOf(file), 1);
                //alert(this.uploadList.indexOf(file))
                this.$emit('gacomplistenTochildEvent',fileList);
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
                    desc: '文件 ' + file.name + ' 格式不正确，请上传pdf,doc,docx,jpg,jpeg,png,wps,xls格式文件。'
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
            }
        },
        mounted () {
            this.$emit('gacomplistenTochildEvent',this.uploadList);
        }
    }
</script>
<style>
    .attach-upload-list{
        top: 12px;
        left: 14px;
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
    .attach-upload-list img{
        width: 100%;
        height: 100%;
    }
    .attach-upload-list-cover{
        display: none;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0,0,0,.6);
    }
    .attach-upload-list:hover .attach-upload-list-cover{
        display: block;
    }
    .attach-upload-list-cover i{
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
