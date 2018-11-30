<template>
    <div>

        <div class="div-float-with">
            <div class="div-float-addair" style="position: relative">
                <!--<span  class="span-label-1">航空器45度照片</span>-->
                <div class="demo-upload-list-pliot"  style="height: 150px;" v-show="firturlshow">
                    <div class="demo-upload-list-cover-pliot"  >
                        <Icon type="ios-trash-outline" @click.native="handleRemoveitemflag1(url1)"></Icon>
                    </div>
                    <div style="width: 250px;height:150px;border: 1px solid #DCDFE6;margin-top: -27px">
                        <img :src="this.imageUrl+url1+'.thumb.jpg'" style="width: 250px;height:150px;line-height: 58px;">
                    </div>
                </div>
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
                        :action=this.uploadUrl
                        style="display: inline-block;width:250px;">



                    <div style="width: 250px;height:150px;background-color:#f8fcff;border: 1px solid #DCDFE6;margin-top: -27px" v-show="firturlshows">
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 37px;margin-left: 112px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">航空器照片<br>图片小于2M</h4>
                    </div>


                </Upload>

            </div>
            <div class="div-float-addair" style="position: relative">
                <!--<span  class="span-label-1">适航证正面扫描件</span>-->
                <div class="demo-upload-list-pliot" style="height: 150px;" v-show="firturlshow1">
                    <div class="demo-upload-list-cover-pliot"  >
                        <Icon type="ios-trash-outline" @click.native="handleRemoveitemflag2(url2)"></Icon>
                    </div>
                    <div style="width: 250px;height:150px;border: 1px solid #DCDFE6; margin-top: -27px">
                        <img :src="this.imageUrl+url2+'.thumb.jpg'" style="width: 250px;height:150px;line-height: 58px;">
                    </div>

                </div>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess2"
                        :before-upload="handleBeforeUploadurl2"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        multiple
                        :action=this.uploadUrl
                        style="display: inline-block;width:250px;">



                    <div style="width: 250px;height:150px;background-color:#f8fcff;border: 1px solid #DCDFE6;margin-top: -27px"  v-show="firturlshows1">
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 37px;margin-left: 112px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">国籍登记证扫描件<br>图片小于2M</h4>
                    </div>

                </Upload>

            </div>
            <div class="div-float-addair" style="position: relative">
                <!--  <span  class="span-label-1">国籍登记证扫描件</span>-->
                <div class="demo-upload-list-pliot" style="height: 150px;" v-show="firturlshow2">
                    <div class="demo-upload-list-cover-pliot"  >
                        <Icon type="ios-trash-outline" @click.native="handleRemoveitemflag4(url4)"></Icon>
                    </div>
                    <div style="width: 250px;height:150px;border: 1px solid #DCDFE6;margin-top: -27px" >
                        <img :src="this.imageUrl+url4+'.thumb.jpg'" style="width: 250px;height:150px;line-height: 58px;">
                    </div>

                </div>
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess4"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        :before-upload="handleBeforeUploadurl4"
                        multiple
                        :action=this.uploadUrl
                        style="display: inline-block;width:250px;">



                    <div style="width: 250px;height:150px;background-color:#f8fcff;border: 1px solid #DCDFE6;margin-top: -27px"  v-show="firturlshows2">
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 37px;margin-left: 112px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">电台执照扫描件<br>图片小于2M</h4>
                    </div>

                </Upload>

            </div>
            <div class="div-float-addair" style="position: relative">
                <div class="demo-upload-list-pliot" style="height: 150px;" v-show="firturlshow3">
                    <div class="demo-upload-list-cover-pliot"  >
                        <Icon type="ios-trash-outline" @click.native="handleRemoveitemflag5(url5)"></Icon>
                    </div>
                    <div style="width: 250px;height:150px;border: 1px solid #DCDFE6;margin-top: -27px" >
                        <img :src="this.imageUrl+url5+'.thumb.jpg'" style="width: 250px;height:150px;line-height: 58px;">
                    </div>

                </div>
                <!-- <span  class="span-label">电台执照扫描件</span>-->
                <Upload ref="upload"
                        :show-upload-list="false"
                        :on-success="handleSuccess5"
                        :format="['jpg','jpeg','png']"
                        :max-size="2048"
                        :on-format-error="handleFormatError"
                        :on-exceeded-size="handleMaxSize"
                        :before-upload="handleBeforeUploadurl5"
                        multiple
                        :action=this.uploadUrl
                        style="display: inline-block;width:250px;">


                    <div style="width: 250px;height:150px;background-color:#f8fcff;border: 1px solid #DCDFE6;margin-top: -27px"  v-show="firturlshows3">
                        <img src="../../../assets/添加图片图标.png" style="margin-top: 37px;margin-left: 112px">
                        <h4 style="font-size: 13px;opacity: 0.66;color: rgb(39,129,221);text-align: center;margin-top: 15px">适航证件扫描<br>图片小于2M</h4>
                    </div>

                </Upload>

            </div>
        </div>


        <!-- <Modal transfer="false" title="图片详情" v-model="visible" :styles="{width:'850px'}" >
             <img :src="imgurl" v-if="visible" style="width: 100%;padding-bottom: 3.5%">
         </Modal>-->
    </div>
</template>
<script>
    import Cookies from 'js-cookie';
    export default {
        data () {

            return {
                firturlshow:false,//身份证真面
                firturlshows:true,//身份证默认
                firturlshow1:false,//身份证真面
                firturlshows1:true,//身份证默认
                firturlshow2:false,//身份证真面
                firturlshows2:true,//身份证默认
                firturlshow3:false,//身份证真面
                firturlshows3:true,//身份证默认
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
            let buz = JSON.parse(Cookies.get("profile"));
                self.companyid = buz.buz.companyId;
                self.info.path="/" + self.companyid + "/aircraft"

        },
        methods: {

            handleSuccess1 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url1=res.url;
                this.$emit('childurlone',res.url,'url1');
                this.firturlshows = false;
                this.firturlshow = true;
            },
            handleSuccess2 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url2=res.url;
                this.$emit('childurlone',res.url,'url2');
                this.firturlshows1 = false;
                this.firturlshow1 = true;
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
                this.firturlshows2 = false;
                this.firturlshow2 = true;
            },
            handleSuccess5 (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                this.url5=res.url;
                this.$emit('childurlone',res.url,'url5');
                this.firturlshows3 = false;
                this.firturlshow3 = true;
            },
            handleRemoveitemflag1(){
                this.url1='';
                this.$emit('childurlone',this.url1,'url1');
                this.firturlshow = false;
                this.firturlshows = true;
            },
            handleRemoveitemflag2(){
                this.url2='';
                this.$emit('childurlone',this.url2,'url2');
                this.firturlshow1 = false;
                this.firturlshows1 = true;
            },
            handleRemoveitemflag3(){
                this.url3='';
                this.$emit('childurlone',this.url3,'url3');
            },
            handleRemoveitemflag4(){
                this.url4='';
                this.$emit('childurlone',this.url4,'url4');
                this.firturlshow2 = false;
                this.firturlshows2 = true;
            },
            handleRemoveitemflag5(){
                this.url5='';
                this.$emit('childurlone',this.url5,'url5');
                this.firturlshow3 = false;
                this.firturlshows3 = true;
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
                if(obj.aircraftCatalogue!=null&&obj.aircraftCatalogue!=""){
                    this.url1=obj.aircraftCatalogue;
                    this.firturlshows = false;
                    this.firturlshow = true;
                }
                if(obj.scanCatalogue!=null&&obj.scanCatalogue!=""){
                    this.url2=obj.scanCatalogue;
                    this.firturlshows1 = false;
                    this.firturlshow1 = true;
                }
                if(obj.airworthinessBackCatalogue!=null){
                    this.url3=obj.airworthinessBackCatalogue;
                }
                if(obj.radioLicenceCatalogue!=null&&obj.radioLicenceCatalogue!=""){
                    this.url4=obj.radioLicenceCatalogue;
                    this.firturlshows2 = false;
                    this.firturlshow2 = true;
                }
                if(obj.airworthinessCertificate!=null&&obj.airworthinessCertificate!=""){
                    this.url5=obj.airworthinessCertificate;
                    this.firturlshows3 = false;
                    this.firturlshow3 = true;
                }
            },updateclean(){
                this.url1='';
                this.url2='';
                this.url3='';
                this.url4='';
                this.url5=''
            },
            handleRemoveitemflag1(){
                this.url1='';
                this.$emit('childurlone',this.url1,'url1');
                this.firturlshow = false;
                this.firturlshows = true;
            },
            handleRemoveitemflag2(){
                this.url2='';
                this.$emit('childurlone',this.url2,'url2');
                this.firturlshow1 = false;
                this.firturlshows1 = true;

            },

            handleRemoveitemflag4(){
                this.url4='';
                this.$emit('childurlone',this.url4,'url4');
                this.firturlshow2 = false;
                this.firturlshows2 = true;
            },
            handleRemoveitemflag5(){
                this.url5='';
                this.$emit('childurlone',this.url5,'url5');
                this.firturlshow3 = false;
                this.firturlshows3 = true;
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
        color: #DCDFE6;
    }
    .span-label-1{
        display: block;
        float: left;
        width: 90px;
        padding-left: 15px;
        padding-right: 15px;
        margin-left: 15px;
        font-size: 14px;
        color:#DCDFE6;
    }
    .div-float-addair{
        float: left;
        margin-left: 20px;
    }

    .div-float-with{
        margin-top: 20px;
        width: 1280px;


    }



    .demo-upload-list-coer i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
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
    .demo-upload-list-pliot:hover .demo-upload-list-cover-pliot{
        display: block;
    }
    .demo-upload-list-cover-pliot{
        display: none;
        position: absolute;
        top: 90px;
        bottom: 0;
        left: 2px;
        right: 0px;
        background: rgba(0,0,0,.6);
        /* width: 120px; */
        height: 30px;
        width: 98%;
        /* padding-left: 199px; */
        text-align: right;
        padding-right: 10px;
        padding-top: 5px;
        margin-bottom: 16px;
    }
    .demo-upload-list-cover-pliot i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
</style>
