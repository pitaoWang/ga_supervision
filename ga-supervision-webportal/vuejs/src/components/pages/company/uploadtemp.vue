<template>
    <div>
        <Upload
                ref="upload"
                :on-success="handleSuccess"
                :on-error="handleError"
                :format="['pdf']"
                :max-size="50240"
                :show-upload-list="false"
                :on-format-error="handleFormatError"
                :on-exceeded-size="handleMaxSize"
                :before-upload="handleBeforeUpload"
                :data=pathparam
                action="http://127.0.0.1:18081/upload/companyfile">
            <Button icon="ios-cloud-upload-outline">选择上传的文件</Button>
        </Upload>
        <div  v-if="selectType!= null"><div>{{ selectType.name }}</div> <Button type="primary" @click="upload" :loading="loadingStatus">{{ loadingStatus ? '上传中' : '点击上传' }}</Button></div>
    </div>
</template>

<script>
    export default {
        props:['uploadList','filepath','templateType','versionNum'],
        data () {
            return {
                selectType:this.uploadList,
                //name:'',
                loadingStatus: false,
                pathparam:{
                    path:'',
                    name:'',
                },
            }
        },
        methods: {
            handleError(error,file){
                this.$Message.success('上传失败')
            },
            handleSuccess (res, file) {
                // 因为上传过程为实例，这里模拟添加 url

                this.selectType={name:res.name,url:res.url};
                this.$emit('gacomplistenTochildEvent',this.selectType);
                this.name = this.selectType.name
                this.selectType = null;
                this.$Message.success('上传成功')
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
            getToken (file) {
                let companyId = this.pathparam.path
                let templateName = file.name
                let versionNumber = this.versionNum
                return new Promise((resolve, reject) => {
                    this.$http.httpGet('/eim/api/doctemplate/findbycompanyidandtemplatenameandversionnumber?companyId='+companyId+'&templateName='+templateName+'&versionNumber='+versionNumber).then(res=>{

                        if(res.data.resultCode==200){

                            if(res.data.data!=null){

                                this.$Notice.warning({
                                    title: '版本号',
                                    desc: '文件 ' + file.name + ' 版本号，不能重复。'
                                });

                                reject()
                            }
                            else{

                                //resolve()
                                this.selectType=file;
                                return false;
                            }

                        }

                    })
                })
            },



            handleBeforeUpload (file) {



                this.pathparam.name= this.templateType

                if(this.versionNum==''){
                    this.$Notice.warning({
                        title: '版本号',
                        desc: '文件 ' + file.name + ' 版本号，不能为空。'
                    });
                    return false;
                }

                return this.getToken(file)
                this.selectType =file
                return false;
            },


            upload () {
                this.loadingStatus = true;
                setTimeout(() => {
                    this.$refs.upload.post(this.selectType);

                    this.loadingStatus = false;
                }, 1500);


            }
        },
        watch:
            {
                filepath:function(ss)
                {
                    console.log(this.filepath+"filepath")
                    this.pathparam.path=this.filepath;
                },

            },
        mounted () {
            this.$profile.getProfile().then((profile) => {
                this.pathparam.path= profile.buz.companyId
            })


        }
    }
</script>

<style scoped>

</style>