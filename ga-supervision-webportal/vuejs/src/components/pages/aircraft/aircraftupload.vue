<template>
    <div>
        <div class="div-float-with">
            <div style="position: relative">
                <span  class="span-label-1">航空器45度照片</span>
                <Upload
                            ref="upload"
                            :show-upload-list="false"
                            :on-success="handleSuccess1"
                            :before-upload="handleBeforeUploadurl1"
                            :format="['jpg','jpeg','png']"
                            :max-size="2048"
                            :on-format-error="handleFormatError"
                            :on-exceeded-size="handleMaxSize"
                            multiple
                            action="http://fs.glinkus.com:18081/upload"
                            style="display: inline-block;width:300px;">
                        <div class="demo-upload-list-coer">
                            <Icon   class="icon" type="ios-compose-outline"></Icon>
                        </div>

                        <div style="width: 300px;height:200px;border: 1px solid rgb(1,107,217);margin-top: -27px" v-if="url1">
                            <img :src="url1" style="width: 300px;height:200px;line-height: 58px;">
                        </div>
                        <div style="width: 300px;height:200px;background-color:#f8fcff;border: 1px solid rgb(1,107,217);margin-top: -27px" v-else>
                            <img src="../../../assets/添加图片图标.png" style="margin-top: 57px;margin-left: 132px">
                            <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">图片小于2M<br>尺寸小于640*480</h4>
                        </div>
                    </Upload>
                <Icon class=" icon1" style="left:356px;" type="ios-eye-outline" @click.native="handleView(url1)"></Icon>
                <Icon class=" icon1" style="left:380px;" type="ios-trash-outline" @click.native="handleRemoveitemflag1(url1)"></Icon>
            </div>
        </div>

        <div class="div-float-with">
            <div class="div-float-addair" style="position: relative">
                <span  class="span-label-1">适航证正面扫描件</span>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess2"
                        :before-upload="handleBeforeUploadurl2"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        multiple
                        action="http://fs.glinkus.com:18081/upload"
                        style="display: inline-block;width:300px;">
                    <div class="demo-upload-list-coer">
                        <Icon   class="icon" type="ios-compose-outline"></Icon>
                    </div>
                    <div style="width: 300px;height:200px;border: 1px solid rgb(1,107,217);margin-top: -27px" v-if="url2">
                        <img :src="url2" style="width: 300px;height:200px;line-height: 58px;">
                    </div>
                    <div style="width: 300px;height:200px;background-color:#f8fcff;border: 1px solid rgb(1,107,217);margin-top: -27px" v-else>
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 57px;margin-left: 132px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">图片小于2M<br>尺寸小于640*480</h4>
                    </div>
                </Upload>
                <Icon class=" icon1" style="left:356px;" type="ios-eye-outline" @click.native="handleView(url2)"></Icon>
                <Icon class=" icon1" style="left:380px;" type="ios-trash-outline" @click.native="handleRemoveitemflag2(url2)"></Icon>
            </div>
            <div style="position: relative">
                <span  class="span-label">适航证反面扫描件</span>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess3"
                        :before-upload="handleBeforeUploadurl3"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        multiple
                        action="http://fs.glinkus.com:18081/upload"
                        style="display: inline-block;width:300px;">
                    <div class="demo-upload-list-coer">
                        <Icon   class="icon" type="ios-compose-outline"></Icon>
                    </div>
                    <div style="width: 300px;height:200px;border: 1px solid rgb(1,107,217);margin-top: -27px" v-if="url3">
                        <img :src="url3" style="width: 300px;height:200px;line-height: 58px;">
                    </div>
                    <div style="width: 300px;height:200px;background-color:#f8fcff;border: 1px solid rgb(1,107,217);margin-top: -27px" v-else>
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 57px;margin-left: 132px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">图片小于2M<br>尺寸小于640*480</h4>
                    </div>
                </Upload>
                <Icon class=" icon1" style="left:767px;" type="ios-eye-outline" @click.native="handleView(url3)"></Icon>
                <Icon class=" icon1" style="left:789px;" type="ios-trash-outline" @click.native="handleRemoveitemflag3(url3)"></Icon>
            </div>
        </div>

        <div class="div-float-with">
            <div class="div-float-addair" style="position: relative">
                <span  class="span-label-1">国籍登记证扫描件</span>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess4"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        :before-upload="handleBeforeUploadurl4"
                        multiple
                        action="http://fs.glinkus.com:18081/upload"
                        style="display: inline-block;width:300px;">
                    <div class="demo-upload-list-coer">
                        <Icon   class="icon" type="ios-compose-outline"></Icon>
                    </div>
                    <div style="width: 300px;height:200px;border: 1px solid rgb(1,107,217);margin-top: -27px" v-if="url4">
                        <img :src="url4" style="width: 300px;height:200px;line-height: 58px;">
                    </div>
                    <div style="width: 300px;height:200px;background-color:#f8fcff;border: 1px solid rgb(1,107,217);margin-top: -27px" v-else>
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 57px;margin-left: 132px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">图片小于2M<br>尺寸小于640*480</h4>
                    </div>
                </Upload>
                <Icon class=" icon1" style="left:356px;" type="ios-eye-outline" @click.native="handleView(url4)"></Icon>
                <Icon class=" icon1" style="left:380px;" type="ios-trash-outline" @click.native="handleRemoveitemflag4(url4)"></Icon>
            </div>
            <div style="position: relative">
                <span  class="span-label">电台执照扫描件</span>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess5"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        :before-upload="handleBeforeUploadurl5"
                        multiple
                        action="http://fs.glinkus.com:18081/upload"
                        style="display: inline-block;width:300px;">
                    <div class="demo-upload-list-coer">
                        <Icon   class="icon" type="ios-compose-outline"></Icon>
                    </div>
                    <div style="width: 300px;height:200px;border: 1px solid rgb(1,107,217);margin-top: -27px" v-if="url5">
                        <img :src="url5" style="width: 300px;height:200px;line-height: 58px;">
                    </div>
                    <div style="width: 300px;height:200px;background-color:#f8fcff;border: 1px solid rgb(1,107,217);margin-top: -27px" v-else>
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 57px;margin-left: 132px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">图片小于2M<br>尺寸小于640*480</h4>
                    </div>
                </Upload>
                <Icon class=" icon1" style="left:767px;" type="ios-eye-outline" @click.native="handleView(url5)"></Icon>
                <Icon class=" icon1" style="left:789px;" type="ios-trash-outline" @click.native="handleRemoveitemflag5(url5)"></Icon>
            </div>
        </div>
        <Modal transfer="false" title="图片详情" v-model="visible" :styles="{width:'850px'}" >
            <img :src="imgurl" v-if="visible" style="width: 100%;padding-bottom: 3.5%">
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {

            return {
                visible: false,
                uploadList: [],
                url:'',
                imgurl:'',
                props:['aircraftupdateurl'],
                url1:'',
                url2:'',
                url3:'',
                url4:'',
                url5:'',
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
            self.$profile.getProfile().then((profile) => {
                self.companyid = profile.buz.companyId;
                self.info.path="/" + self.companyid + "/aircraft"
            }, (error) => {
                console.log('error', error);
            });
        },
        methods: {
            handleRemoveitemflag1 () {
                this.url1='';
                this.$emit('childurlone',url1,'url1');
            },
            handleView (self) {
                this.visible = true;
                this.imgurl = self;
            },
            handleSuccess1 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url1=res.url;
                this.$emit('childurlone',res.url,'url1');
            },
            handleSuccess2 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url2=res.url;
                this.$emit('childurlone',res.url,'url2');
            },
            handleSuccess3 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url3=res.url;
                this.$emit('childurlone',res.url,'url3');
            },
            handleSuccess4 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url4=res.url;
                this.$emit('childurlone',res.url,'url4');
            },
            handleSuccess5 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url5=res.url;
                this.$emit('childurlone',res.url,'url5');
            },
            handleRemoveitemflag1(){
                this.url1='';
                this.$emit('childurlone',this.url1,'url1');
            },
            handleRemoveitemflag2(){
                this.url2='';
                this.$emit('childurlone',this.url2,'url2');
            },
            handleRemoveitemflag3(){
                this.url3='';
                this.$emit('childurlone',this.url3,'url3');
            },
            handleRemoveitemflag4(){
                this.url4='';
                this.$emit('childurlone',this.url4,'url4');
            },
            handleRemoveitemflag5(){
                this.url5='';
                this.$emit('childurlone',this.url5,'url5');
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
                if(obj.picturePath!=null){
                    this.url1=obj.picturePath;
                }
                if(obj.airworthinessFrontCatalogue!=null){
                    this.url2=obj.airworthinessFrontCatalogue;
                }
                if(obj.airworthinessBackCatalogue!=null){
                    this.url3=obj.airworthinessBackCatalogue;
                }
                if(obj.scanCatalogue!=null){
                    this.url4=obj.scanCatalogue;
                }
                if(obj.radioLicenceCatalogue!=null){
                    this.url5=obj.radioLicenceCatalogue;
                }
            },updateclean(){
                this.url1='';
                this.url2='';
                this.url3='';
                this.url4='';
                this.url5=''
            },
            handleBeforeUploadurl1()
            {
                this.url1 =require( '../../../assets/imgupload.gif');
            },
            handleBeforeUploadurl2()
            {
                this.url2 =require( '../../../assets/imgupload.gif');
            },
            handleBeforeUploadurl3()
            {
                this.url3 =require( '../../../assets/imgupload.gif');
            },
            handleBeforeUploadurl4()
            {
                this.url4 =require( '../../../assets/imgupload.gif');
            },
            handleBeforeUploadurl5()
            {
                this.url5 =require( '../../../assets/imgupload.gif');
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
    }

    .div-float-with{
        width: 925px;
        margin-top: 15px;
        margin-bottom:43px
    }



    .demo-upload-list-coer i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
    .icon{
        padding-top:5px;
        padding-right: 40px;
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
    .icon1{
        font-size: 20px;
        position: absolute;
        color: #fff;

        top:205px;
        padding-left: 5px;
        cursor: pointer;
    }

</style>
