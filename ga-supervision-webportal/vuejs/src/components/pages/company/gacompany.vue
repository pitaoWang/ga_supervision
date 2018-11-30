<style scoped>
    .navi-btn {
        margin-left: 305px;
        margin-top: 14px;
        background: #c4c4c4;
        width: 120px;
        height: 35px;
        font-size: 14px;
        margin-right: 50px;
    }
    .icondel{
        position: absolute;background-color: #4a4a4a;
        top: 10px;
        right: 10px;
        width: 20px;
        height: 20px;
        border-radius: 3px;
        text-align: -webkit-center;
    }
    .upload_img {
        /*margin-top: 12px;*/
        /*margin-left: 20px;*/
        width: 193px;
        height: 118px;
        /*margin-right: 40px;*/
    }

    .gacompany-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background-color:rgba(37,128,221,1);
        /*background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);*/
        /*background: linear-gradient(#a3a3a3, #e7e7e7);*/
    }

    .gacompany-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
        /*background: -webkit-linear-gradient(#e7e7e7, #a3a3a3);*/
        /*background: linear-gradient(#e7e7e7, #a3a3a3);*/
    }

    .company-demo-upload-list img{
        /*width: 100%;*/
        /*height: 100%;*/
    }
    .company-demo-upload-list-cover{
        /* position: absolute; */
        /* top: 118px; */
        /* bottom: 0; */
        /* left: 0px; */
        /* right: 0px; */
        background: rgba(37,128,221,0.66);
        /* width: 120px; */
        height: 28px;
        /* padding-left: 199px; */
        text-align: right;
        padding-right: 2px;
        padding-top: 1px;
        /* margin-bottom: 16px; */
    }
    .company-demo-upload-list:hover .company-demo-upload-list-cover{
        /*display: block;*/
    }
    .company-demo-upload-list-cover i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
    .company-upload-div
    {
        width: 193px;
        height: 118px;
        line-height: 58px;
        background-color: rgb(255,255,255);
        border: solid 1px rgba(1,107,217,0.5);
    }
    .company-upload-div-img
    {
        float: right;
        margin-right: 79px;
        margin-top:26px;
    }
    .company-upload-div-div{
        font-size: 0.7rem;
        text-align: center;
        text-align: center;
        width: 100%;
        float: left;
        display: block;
        margin-top: 12px;
        line-height: 17px;
        color:rgba(1,107,217,0.5);
    }
    .onclickimg1
    {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/search.png");
    }
    .onclickimg1:active{
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/searchonclick.png");
    }
    .onclickimg2
    {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/edit.png");
    }
    .onclickimg2:active{
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/editonclick.png");
    }
    .onclickimg3
    {
        width: 39px;
        margin-left: -3px;
        display: inline-block;
        height: 26px;
        cursor: pointer;
        background-image: url("../../../assets/newdelete.png");
    }
    .onclickimg3:active{
        width: 39px;
        margin-left: -3px;
        height: 26px;
        display: inline-block;
        cursor: pointer;
        background-image: url("../../../assets/deleteonclick.png");
    }

</style>

<template>
    <div style="width: 1280px;  margin-top: 10px; display: inline-block;background: white">

        <Form ref="gacomp" :model="gacompany"  :rules="gacompanyValidate" :label-width="200" style="padding: 28px 0 0 0;">
            <div style="width: 1280px;   color: rgb(1,107,217);">
                <div style="width:900px;float: left;margin-top: 0px">
                    <FormItem label="企业名称" style="width: 100%;" prop="name">
                        <Input v-model="gacompany.name" placeholder="" readonly="readonly" style=""></Input>
                    </FormItem>

                    <FormItem label="企业联系电话" prop="telephone"   style="display: inline-block; width:450px;">
                        <Input v-model="gacompany.telephone" placeholder=""></Input>
                    </FormItem>

                    <FormItem label="法人代表" prop="legalPerson"  style="float:right; display: inline-block; width:450px;">
                        <Input v-model="gacompany.legalPerson" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="法人身份证" prop="cardid" style=" display: inline-block; width:450px;">
                        <Input   v-model="gacompany.cardid" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="法人联系电话" prop="userTel" style=" float:right; display: inline-block; width:450px;">
                        <Input v-model="gacompany.userTel" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="法人性别" style="width:450px ;display: inline-block;" prop="legalSex">
                        <Select v-model="gacompany.legalSex" placeholder="" >
                            <Option :value="1">男</Option>
                            <Option :value="2">女</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="法人邮箱" prop="legalEmail" style=" float:right; display: inline-block; width:450px;">
                        <Input v-model="gacompany.legalEmail" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="企业注册地址" prop="registerAdd" style=" display: inline-block; width:450px;">
                        <Input v-model="gacompany.registerAdd" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="企业注册资本(万元)" prop="registerCost" style=" float:right; display: inline-block; width:450px;">
                        <Input v-model="gacompany.registerCost"  placeholder=""></Input>
                    </FormItem>

                    <FormItem label="所在省" style="width:450px ;display: inline-block;" prop="province">
                        <Select v-model="gacompany.province" placeholder="请选择省" @on-change="getProv(gacompany.province)">
                            <Option v-for="item in cityList" :key="item.p" :value="item.p" >{{ item.p }}</Option>
                        </Select>
                    </FormItem>

                    <FormItem label="所在市" style="float:right;width:450px ;display: inline-block;" prop = "city">
                        <Select v-model="gacompany.city"  placeholder="请选择城市">
                            <Option v-for="item in citys" :key="item.n" :value="item.n" >{{item.n}}</Option>
                        </Select>
                    </FormItem>

                    <FormItem label="所属管理局" prop="authorityId" style=" display: inline-block; width:450px;">
                        <Select v-model="gacompany.authorityId" placeholder="请选择">
                            <Option v-for="site in authority" :key="site.id" :value="site.id+''">{{ site.name }}
                            </Option>
                        </Select>
                    </FormItem>
                    <FormItem label="所属监管局" prop="supervisionId"  style="float:right; display: inline-block; width:450px;">
                        <Select v-model="gacompany.supervisionId" placeholder="请选择">
                            <Option v-for="site in supervision" :key="site.id" :value="site.id+''">{{ site.name }}
                            </Option>
                        </Select>
                    </FormItem>

                    <FormItem label="主任运行监查员" style=" display: inline-block; width:450px;">

                        <Input v-model="gacompany.poi" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="主任适航监察员"   style="float:right; display: inline-block; width:450px;">
                        <Input v-model="gacompany.pmi" placeholder=""></Input>
                    </FormItem>
                    <!--by dgw-->
                    <!--<FormItem label="运行合格证编号" prop="certId" style="display: inline-block; width:450px;">
                        <Input v-model="gacompany.certId" placeholder=""></Input>
                    </FormItem>-->
                    <FormItem label="经营许可证编号" prop="businessid" style=" display: inline-block; width:450px;">
                        <Input v-model="gacompany.businessid" placeholder=""></Input>
                    </FormItem>
                    <!--by dgw-->
                   <!-- <FormItem label="运行合格证颁发日期" style="display: inline-block; width:450px;" prop="licenseBegindate">

                            <DatePicker type="date" style="width:250px;" placeholder="选择日期" v-model="gacompany.licenseBegindate"></DatePicker>
                    </FormItem>
                    <FormItem label="运行合格证生效日期" style="float:right; display: inline-block; width:450px;" prop="licenseEnddate">
                            <DatePicker placeholder="选择日期" type="date" style="width:250px;" v-model="gacompany.licenseEnddate"></DatePicker>
                    </FormItem>-->
                    <FormItem label="经营许可证颁发单位" prop="businessCompany"  style="float:right;display: inline-block; width:450px;">
                        <Input v-model="gacompany.businessCompany" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="经营许可证颁发日期" style="display: inline-block; width:450px;" prop="businessBegindate">

                        <DatePicker type="date" style="width:250px;" placeholder="选择日期" v-model="gacompany.businessBegindate" ></DatePicker>
                    </FormItem>
                    <FormItem label="经营许可证有效日期" style="float:right; display: inline-block; width:450px;" prop="businessEnddate">

                        <DatePicker type="date" style="width:250px;" placeholder="选择日期" v-model="gacompany.businessEnddate"></DatePicker>
                    </FormItem>

                    <FormItem label="工商管理证编号" prop="registerCertCode" style=" display: inline-block; width:450px;">
                        <Input v-model="gacompany.registerCertCode" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="工商管理证颁发单位" prop="registerCompany"  style="float:right;display: inline-block; width:450px;">
                        <Input v-model="gacompany.registerCompany" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="工商管理证颁发日期" style="display: inline-block; width:450px;" prop="registerBegindate">

                        <DatePicker type="date" style="width:250px;" placeholder="选择日期" v-model="gacompany.registerBegindate" ></DatePicker>
                    </FormItem>
                    <FormItem label="工商管理证有效日期" style="float:right; display: inline-block; width:450px;" prop="registerEnddate">

                        <DatePicker type="date" style="width:250px;" placeholder="选择日期" v-model="gacompany.registerEnddate" ></DatePicker>
                    </FormItem>

                    <FormItem label="基地机场" prop="baseAirport"  style="display: inline-block; width:450px;">
                        <Input v-model="gacompany.baseAirport" placeholder=""></Input>
                    </FormItem>
                   <!-- by dgw-->
                    <!--<FormItem label="运行合格证类型" prop="operateTypelist" style="width: 100%;">
                        <CheckboxGroup v-model="gacompany.operateTypelist">
                            <Checkbox label="91" ><span class="checkboxspan" style="color: #8a909f;">91</span></Checkbox>
                            <Checkbox label="135"><span class="checkboxspan" style="color: #8a909f;">135</span></Checkbox>
                            <Checkbox label="141"><span class="checkboxspan" style="color: #8a909f;">141</span></Checkbox>
                            <Checkbox label="145"><span class="checkboxspan" style="color: #8a909f;">145</span></Checkbox>
                        </CheckboxGroup>
                    </FormItem>-->
                    <FormItem label="运行种类" prop="standardtypelist" style="width: 100%;">
                        <CheckboxGroup v-model="gacompany.standardtypelist">
                            <Checkbox label="1" ><span class="checkboxspan" style="color: #8a909f;">一般商业飞行</span></Checkbox>
                            <Checkbox label="2"><span class="checkboxspan" style="color: #8a909f;">农林喷洒</span></Checkbox>
                            <Checkbox label="3"><span class="checkboxspan" style="color: #8a909f;">外载荷作业</span></Checkbox>
                            <Checkbox label="4"><span class="checkboxspan" style="color: #8a909f;">训练飞行</span></Checkbox>
                            <Checkbox label="5"><span class="checkboxspan" style="color: #8a909f;">空中游览</span></Checkbox>
                            <Checkbox label="6"><span class="checkboxspan" style="color: #8a909f;">小型运输</span></Checkbox>
                            <Checkbox label="7"><span class="checkboxspan" style="color: #8a909f;">大型运输</span></Checkbox>
                        </CheckboxGroup>
                    </FormItem>
                    <FormItem label="企业类别" prop="companytypelist">
                        <CheckboxGroup v-model="gacompany.companytypelist">
                            <Checkbox label="1">
                                <span class="checkboxspan" style="color: #8a909f;">合资</span>
                            </Checkbox>
                            <Checkbox label="2">
                                <span class="checkboxspan" style="color: #8a909f;">独资</span>
                            </Checkbox>
                            <Checkbox label="3">
                                <span class="checkboxspan" style="color: #8a909f;">国有</span>
                            </Checkbox>
                            <Checkbox label="4">
                                <span class="checkboxspan" style="color: #8a909f;">私营</span>
                            </Checkbox>
                            <Checkbox label="5">
                                <span class="checkboxspan" style="color: #8a909f;">全名所有制</span>
                            </Checkbox>
                            <Checkbox label="6">
                                <span class="checkboxspan" style="color: #8a909f;">集体所有制</span>
                            </Checkbox>
                            <Checkbox label="7">
                                <span class="checkboxspan" style="color: #8a909f;">股份制</span>
                            </Checkbox>
                            <Checkbox label="8">
                                <span class="checkboxspan" style="color: #8a909f;">有限责任</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </FormItem>
                    <FormItem label="经营项目类别" prop="businessprojectlist">
                        <CheckboxGroup v-model="gacompany.businessprojectlist">
                            <Checkbox label="1">
                                <span class="checkboxspan" style="color: #8a909f;">经营</span>
                            </Checkbox>
                            <Checkbox label="2">
                                <span class="checkboxspan" style="color: #8a909f;">非经营</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </FormItem>
                    <FormItem label="经营项目" prop="businesslist">
                        <CheckboxGroup v-model="gacompany.businesslist">
                            <Checkbox label="1">
                                <span class="checkboxspan" style="color: #8a909f;">甲类</span>
                            </Checkbox>
                            <Checkbox label="2">
                                <span class="checkboxspan" style="color: #8a909f;">乙类</span>
                            </Checkbox>
                            <Checkbox label="3">
                                <span class="checkboxspan" style="color: #8a909f;">丙类</span>
                            </Checkbox>
                            <Checkbox label="4">
                                <span class="checkboxspan" style="color: #8a909f;">丁类</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </FormItem>
                   <!-- <FormItem label="经营范围" prop="businessScope">
                        <Input v-model="gacompany.businessScope" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder=""></Input>
                    </FormItem>-->
                   <!-- <div style="padding-left: 30px;">
                        <div style="padding:5px; font-size: 15px;text-align: left;width: 885px;   display: inline-block;">手册/大纲</div>
                        <uploadfilecompany :uploadList=handbookfilelist
                                           v-on:gacomplistenTochildEvent="getuploadListcomp"></uploadfilecompany>
                    </div>-->
                </div>
                <div style="width: 261px; height:auto;margin-left: 24px; margin-right: 92px; float: right; background-color: rgba(255,255,255,0.5); padding-left: 32px;  ">
                    <!-- 上传图片后,需要将图片路径保存 -->
                    <div style=" font-size: 14px; margin-bottom: 5px;  color: #8a909f; margin-top: 19px;">企业Logo</div>
                    <div class="upload_img" style="position: relative;color:#8a909f;">
                        <div class="company-demo-upload-list" style="color:#8a909f;" v-show="imglogo">

                            <div style="width: 193px;height:118px;line-height: 58px; text-align: center; color:#8a909f;" v-show="imglogo">
                                <img :src="gacompany.logoFile" alt="正在加载中.." style="width:193px;height:118px;margin: 0px auto;color:#8a909f;"></img>
                            </div>

                        </div>
                        <Upload ref="upload" :show-upload-list="false" :on-success="handleSuccess" :format="['jpg','jpeg','png']" :max-size="2048" :on-format-error="handleFormatError" :on-exceeded-size="handleMaxSize" :before-upload="handleBeforeUpload" multiple action="http://127.0.0.1:18081/upload"
                                style="width:193px;color:#8a909f;">
                            <div class="company-upload-div" style="color:#8a909f;" id="logoid" v-show="imglogo1">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="company-upload-div-img">
                                </div>
                                <div class="company-upload-div-div" v-show="imglogo1">图片小于2M<br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="company-demo-upload-list-cover">
                            <div class="onclickimg1" @click="handleView(gacompany.logoFile)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('logoid')"> &nbsp; </div>
                            <div class="onclickimg3" @click="handleremoveimg('logo')"> &nbsp; </div>
                        </div>
                    </div>


                    <!-- 上传图片后,需要将图片路径保存 -->
                    <div style="font-size:14px; color:#8a909f; margin-top: 61px; margin-bottom: 5px;">工商等级证扫描件</div>
                    <div class="upload_img" style="position: relative;">
                        <div class="company-demo-upload-list" v-show="imgregister">

                            <div style="width: 193px;height:118px;line-height: 58px; text-align: center;" v-show="imgregister">
                                <img :src="gacompany.registerCertFile" alt="正在加载中.." style="width:193px;height:118px;margin: 0px auto;" ></img>
                            </div>
                        </div>
                        <Upload ref="upload" :show-upload-list="false" :on-success="handleSuccesscert" :format="['jpg','jpeg','png']" :max-size="2048" :on-format-error="handleFormatError" :on-exceeded-size="handleMaxSize" :before-upload="handleBeforeUpload" multiple action="http://127.0.0.1:18081/upload" style="width:193px;">
                            <div class="company-upload-div" id="resid" v-show="imgregister1">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="company-upload-div-img">
                                </div>
                                <div class="company-upload-div-div" v-show="imgregister1">图片小于2M<br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="company-demo-upload-list-cover">
                            <div class="onclickimg1" @click="handleView(gacompany.registerCertFile)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('resid') "> &nbsp; </div>
                            <div class="onclickimg3" @click="handleremoveimg('register')"> &nbsp; </div>
                        </div>

                    </div>

                    <!-- 上传图片后,需要将图片路径保存 -->
                   <!-- <div style="font-size:14px;color:#8a909f;margin-top: 61px; margin-bottom: 5px;">运行合格证扫描件</div>
                    <div class="upload_img" style="position: relative;">
                        <div class="company-demo-upload-list" v-show="imgstandard">

                            <div style="width: 193px;height:118px;line-height: 58px; text-align: center;" v-show="imgstandard">
                                <img :src="gacompany.standardCertFile" alt="正在加载中.." style="width:193px;height:118px;margin: 0px auto;" ></img>
                            </div>
                        </div>
                        <Upload ref="upload" :show-upload-list="false" :on-success="handleSuccessstand" :format="['jpg','jpeg','png']" :max-size="2048" :on-format-error="handleFormatError" :on-exceeded-size="handleMaxSize" :before-upload="handleBeforeUpload" multiple action="http://127.0.0.1:18081/upload" style="width:193px;">
                            <div class="company-upload-div" id="standid" v-show="imgstandard1">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="company-upload-div-img">
                                </div>
                                <div class="company-upload-div-div" v-show="imgstandard1">图片小于2M<br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="company-demo-upload-list-cover">
                            <div class="onclickimg1" @click="handleView(gacompany.standardCertFile)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('standid') "> &nbsp; </div>
                            <div class="onclickimg3" @click="handleremoveimg('stand')"> &nbsp; </div>
                        </div>

                    </div>-->
                    <!-- 上传图片后,需要将图片路径保存 -->
                    <div style="font-size:14px;margin-top: 61px;color:#8a909f; margin-bottom: 5px;">经营许可证扫描件</div>
                    <div class="upload_img" style="position: relative;">
                        <div class="company-demo-upload-list" v-show="imgbusiness">

                            <div style="width: 193px;height:118px;line-height: 58px; text-align: center;" v-show="imgbusiness">
                                <img :src="gacompany.businessCertFile" alt="正在加载中.." style="width:193px;height:118px;margin: 0px auto;" ></img>
                            </div>
                        </div>
                        <Upload ref="upload" :show-upload-list="false" :on-success="handleSuccessbusiness" :format="['jpg','jpeg','png']" :max-size="2048" :on-format-error="handleFormatError" :on-exceeded-size="handleMaxSize" :before-upload="handleBeforeUpload" multiple action="http://127.0.0.1:18081/upload" style="width:193px;">
                            <div class="company-upload-div" id="businessid" v-show="imgbusiness1">
                                <div>
                                    <img src="../../../assets/defalutupload.png" class="company-upload-div-img">
                                </div>
                                <div class="company-upload-div-div" v-show="imgbusiness1">图片小于2M<br>尺寸小于640*480</div>
                            </div>
                        </Upload>
                        <div class="company-demo-upload-list-cover">
                            <div class="onclickimg1" @click="handleView(gacompany.businessCertFile)"> &nbsp; </div>
                            <div class="onclickimg2" @click="handleBtnClick('businessid') "> &nbsp; </div>
                            <div class="onclickimg3" @click="handleremoveimg('business')"> &nbsp; </div>
                        </div>
                    </div>
                </div>
                <div class=""
                     style="text-align: center; margin-bottom: 20px; width: 1280px;display:inline-block; ">
                    <Button  v-has="'add'" primary size="large" data-v-942377fa="" href="javascript:;" @click="handleSubmit()" class="">保存</Button>
                </div>
            </div>
        </Form>
        <Modal v-model="copmodal"
               :mask-closable="false"
               width="300px" hight="200px" :styles="{top: '40%'}">
            <p slot="header" style="text-align:left;font-size: 15px;">
                <span>系统提示</span>
            </p>
            <p style="height: 100px; height: 80px;padding-top: 24px;text-align: center;margin-bottom: 2px; text-align: center;">
                {{copmessage}}</p>
        </Modal>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>
<script>
    import companydata from '../../../data/company/index.js';
    import uploadfilecompany from '../company/uploadfilegacompany';
    import imgdetail from '../../views/imgdetail.vue';
    import Cookies from 'js-cookie';
    export default {
        components: {
            /*'uploadfilecompany': uploadfilecompany,*/
            'imgdetail':imgdetail,
        },
        data () {
            return {
                permTypes:[],
                isInit:0,
                isUpdate:'n',//y表示修改,n表示未修改
                copmodal: false, /*提示框*/
                copmessage: '',
                cityList: [],
                citys: [],
                authority: [],
                supervision: [],
                handbookfilelist: [],
                imglogo: false,
                imglogo1: true,
                imgregister: false,
                imgregister1: true,
                imgstandard:false,
                imgstandard1:true,
                imgbusiness:false,
                imgbusiness1:true,
                gacompany: {
                    companyId: '',
                    orgId: '',
                    userId: '',
                    mainBase: '',
                    baseAirport:'',
                    createtime: '',
                    userTel:'',
                    businessBegindate:'',
                    businessEnddate:'',
                    businessid:'',
                    registerCost:'',
                    updatetime: '',
                    flag: '',
                    handbookFile: '',//运行手册
                    name: '',
                    province: '',
                    city: '',
                    legalEmail:'',
                    legalSex:'',
                    registerCertCode:'',
                    registerBegindate:'',
                    registerEnddate:'',
                    registerCompany:'',
                    businessCompany:'',
                    authorityId: '',
                    supervisionId: '',
                    businessScope: '',
                    operateType: '',
                    companyType:'',
                    companytypelist:[],
                    standardType:'',
                    standardtypelist:[],
                    registerAdd:'',
                    //address: '',
                    //postcode: '',
                    telephone: '',
                    //fax: '',
                    cardid:'',
                    legalPerson:'',
                    certId: '',
                    companyids:0,
                    logoFile: '',
                    registerCertFile: '',
                    standardCertFile:'',
                    businessCertFile:'',
                    businessType: '',
                    businesslist:[],
                    operateTypelist:[],
                    businessProject:'',
                    businessprojectlist:[],
                    licenseBegindate: null, /*许可证起始日期*/
                    licenseEnddate: null, /*许可证截止日期*/
                    poi:'',
                    pmi:'',
                },
                startTimeOption: {},
                endTimeOption: {},
                gacompanyValidate: {
                    name: [
                        {required: true, message: '企业名称不能为空', trigger: 'blur'}
                    ],
                    province:[{required: true, message: '请选择所属省', trigger: 'change'}],
                    city:[{required: true, message: '请选择所属市', trigger: 'change'}],

                    legalPerson: [
                         {required: true, message: '企业法定代表人不能为空', trigger: 'blur'}
                    ],
                    businessCompany:[
                        {required: true, message: '经营许可证颁发单位不能为空', trigger: 'blur'}
                    ],
                    registerCompany:[
                        {required: true, message: '工商管理证颁发单位不能为空', trigger: 'blur'}
                    ],
                    registerCertCode:
                        [
                            {required: true, message: '工商管理证编号不能为空', trigger: 'blur'}
                        ],
                    userTel: [
                          {required: true, message: '企业法定代表人联系电话不能为空', trigger: 'blur'},{pattern:/^1(3|4|5|7|8)\d{9}$/,message: '法人联系电话输入不合法', trigger: 'blur'}
                    ],
                    cardid: [
                        {required: true, message: '企业法定代表人身份证号不能为空', trigger: 'blur'},{pattern:/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,message: '身份证号输入不合法', trigger: 'blur'}
                    ],
                    registerAdd: [
                        {required: true, message: '企业注册地址不能为空', trigger: 'blur'}
                    ],
                    registerCost: [
                        {required: true,message: '企业注册资金不能为空',trigger:'blur'}
                    ],
                    authorityId: [
                        {required: true, message: '管理局不能为空', trigger: 'change'}
                    ],
                    supervisionId: [
                        {required: true, message: '监督局不能为空', trigger: 'change'}
                    ],
                    certId: [
                        {required: true, message: '运行合格证编号不能为空', trigger: 'blur'}
                    ],
                    businessid:[
                        {required:true,message:'经营许可证编号不能为空',trigger:'blur'}
                    ],
                    licenseBegindate:[
                        {required:true,type:'date',message:'运行合格证颁发日期不能为空',trigger:'change'}
                    ],
                    licenseEnddate:[
                        {required:true,type:'date',message:'运行合格证失效日期不能为空',trigger:'change'}
                    ],
                    businessBegindate:[
                        {required:true,type:'date',message:'经营许可证颁发日期不能为空',trigger:'change'}
                    ],
                    businessEnddate:[
                        {required:true,type:'date',message:'经营许可证有效日期不能为空',trigger:'change'}
                    ],
                    registerBegindate:[
                        {required:true,type:'date',message:'工商管理证颁发日期不能为空',trigger:'change'}
                    ],
                    registerEnddate:[
                        {required:true,type:'date',message:'工商管理证有效日期不能为空',trigger:'change'}
                    ],
                    baseAirport:[
                        {required:true,message:'基地机场不能为空',trigger:'blur'}
                    ],
                    businessScope: [
                        {required: true, message: '经营范围不能为空', trigger: 'blur'}
                    ],
                    operateTypelist: [
                        { required: true,type: "array", min: 1, message: '运营种类不能为空', trigger: 'change' }
                    ],
                    businesslist: [
                        {required: true, type: "array", min: 1, message: '企业类别至少选一个', trigger: 'change'},
                    ],
                    standardtypelist:[
                        {required:true,type:"array",min:1,message:'运行种类至少选一个',tigger:'change'}
                    ],
                    companytypelist: [
                        {required: true, type: "array", min: 1, message: '企业类别至少选一个', trigger: 'change'},
                    ],
                    businessprojectlist:[
                        {required:true,type:"array",min:1,message:'经营项目类别至少选一个',trigger:'change'}
                    ],
                    postcode: [
                        {required: true, message: '邮编不能为空', trigger: 'blur'}
                    ],
                    telephone: [
                        {required: true, message: '企业联系电话不能为空', trigger: 'blur'},{pattern:/^1(3|4|5|7|8)\d{9}$/,message: '企业联系电话输入不合法', trigger: 'blur'}
                    ]

                }
            }
        },
        methods: {
            handleBtnClick: function(docid){
                if(docid=='logoid')
                {
                    document.getElementById("logoid").click();
                }else if('resid'==docid)
                {
                    document.getElementById("resid").click();
                }else if('standid'==docid){
                    document.getElementById("standid").click();
                }else if('businessid'==docid){
                    document.getElementById("businessid").click();
                }

            },
            gacompanyinit (nal) {
                let self = this;

                self.isInit=0;
                self.isUpdate='n';
                self.companyids=nal;
                //self.cityList = companydata.citys;
                self.$http.httpGet('/eim/api/company/search/orgname', {
                    //查找全部管理局信息
                    orgtype: 2
                }).then(function (response) {
                    let datainfo = response.data;
                    self.authority = datainfo;
                }).catch(function (error) {
                    console.log(error);
                });

                self.$http.httpGet('/eim/api/company/search/orgname', {
                    //查找全部监管局信息
                    orgtype: 1
                }).then(function (response) {
                    let datainfo = response.data;
                    self.supervision = datainfo;
                }).catch(function (error) {
                    console.log(error);
                });
               /* self.$profile.getProfile().then((profile) => {*/

                    /*add by dgw begin*/
                    //let comId = nal;
                    let buz = JSON.parse(Cookies.get("profile"));
                    //let comId = profile.buz.companyId;
                    let comId = buz.buz.companyId;
                    /*end*/
                    self.$http.httpGet('/eim/api/company/' + comId, {
                        //   params: {
                        //       companyId: 38
                        //     }
                    }).then(function (response) {
                        let datainfo = response.data;


                        if (typeof(datainfo.name) != "undefined") {
                            //self.gacompany = datainfo;

                            for(var key in datainfo) {
                                self.$set(self.gacompany,key,datainfo[key]);
                            }
                            if(self.gacompany.orgId !=null&&self.gacompany.orgId !='')
                            {

                            }else {
                                //self.gacompany.orgId = profile.org.orgId; bydgw
                            }
                            if(self.gacompany.userId !=null&&self.gacompany.userId!='')
                            {

                            }else {
                                //self.gacompany.userId = profile.user.userId; bydgw
                            }
                            if(self.gacompany.name=='') {
                                //self.gacompany.name = profile.user.orgName; bydgw
                            }
                            console.log("init  datainfo.licenseBegindate",datainfo.licenseBegindate)
                            if(datainfo.licenseBegindate!=null&&datainfo.licenseBegindate!=''&&datainfo.licenseBegindate!=undefined)
                            {
                                self.gacompany.licenseBegindate = new Date(datainfo.licenseBegindate);
                            }
                            if(datainfo.licenseEnddate!=null&&datainfo.licenseEnddate!=''&&datainfo.licenseEnddate!=undefined)
                            {
                                self.gacompany.licenseEnddate = datainfo.licenseEnddate;
                            }
                            self.gacompany.authorityId = datainfo.authorityId + '';
                            self.gacompany.supervisionId = datainfo.supervisionId + '';
                            self.gacompany.province = datainfo.province;
                            self.gacompany.city = datainfo.city;
                            self.gacompany.legalEmail = datainfo.legalEmail;
                            self.gacompany.legalSex = datainfo.legalSex;
                            self.gacompany.businessCompany = datainfo.businessCompany;
                            self.gacompany.registerCertCode = datainfo.registerCertCode;
                            self.gacompany.registerEnddate = datainfo.registerEnddate;
                            self.gacompany.registerCompany = datainfo.registerCompany;
                            self.cityList = companydata.citys;
                            for (var i = 0; i < self.cityList.length; i++) {
                                if (self.cityList[i].p == self.gacompany.province) {
                                    self.citys = self.cityList[i].c;
                                }
                            }
                            if (self.gacompany.logoFile != '' && typeof(self.gacompany.logoFile) != 'undefined' && self.gacompany.logoFile != null) {
                                self.imglogo = true;
                                self.imglogo1 = false;
                            } else {
                                self.imglogo1 = true;
                                self.imglogo = false;
                            }
                            if (self.gacompany.registerCertFile != '' && typeof(self.gacompany.registerCertFile) != 'undefined' && self.gacompany.registerCertFile != null) {
                                self.imgregister = true;
                                self.imgregister1 = false;
                            } else {
                                self.imgregister1 = true;
                                self.imgregister = false;
                            }
                            if (self.gacompany.businessCertFile != '' && typeof(self.gacompany.businessCertFile) != 'undefined' && self.gacompany.businessCertFile != null) {
                                self.imgbusiness = true;
                                self.imgbusiness1 = false;
                            } else {
                                self.imgbusiness1 = true;
                                self.imgbusiness = false;
                            }
                            if (self.gacompany.standardCertFile != '' && typeof(self.gacompany.standardCertFile) != 'undefined' && self.gacompany.standardCertFile != null) {
                                self.imgstandard = true;
                                self.imgstandard1 = false;
                            } else {
                                self.imgstandard1 = true;
                                self.imgstandard = false;
                            }
                            if (datainfo.handbookFile != null && typeof (datainfo.handbookFile) != 'undefined' && datainfo.handbookFile != '') {
                                self.handbookfilelist = eval(datainfo.handbookFile);
                            }
                            if (datainfo.businessType != '' && typeof(datainfo.businessType) != 'undefined') {
                                self.gacompany.businesslist = datainfo.businessType.split(",");
                            } else {
                                self.gacompany.businesslist = [];
                            }
                            if (datainfo.businessProject != '' && typeof(datainfo.businessProject) != 'undefined') {
                                self.gacompany.businessprojectlist = datainfo.businessProject.split(",");
                            } else {
                                self.gacompany.businessprojectlist = [];
                            }
                            if (datainfo.companyType != '' && typeof(datainfo.companyType) != 'undefined') {
                                self.gacompany.companytypelist = datainfo.companyType.split(",");
                            } else {
                                self.gacompany.companytypelist = [];
                            }
                            if (datainfo.standardType != '' && typeof(datainfo.standardType) != 'undefined') {
                                self.gacompany.standardtypelist = datainfo.standardType.split(",");
                            } else {
                                self.gacompany.standardtypelist = [];
                            }
                            if (datainfo.operateType != '' && typeof(datainfo.operateType) != 'undefined') {
                                self.gacompany.operateTypelist = datainfo.operateType.split(",");
                            } else {
                                self.gacompany.operateTypelist = [];
                            }

                        }
                    }).catch(function (error) {
                        console.log(error);
                    });

              /*  }, (error) => {
                    console.log('error', error);
                });*/

            },
            handleView (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },
            handleRemove (file) {
                // 从 upload 实例删除数据
                const fileList = this.$refs.upload.fileList;
                this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
            },


            handleremoveimg(imgtype)
            {
                let self = this;
                if(imgtype=='logo'){
                    self.gacompany.logoFile ='';
                    self.imglogo = false;
                    self.imglogo1 = true;
                }else if(imgtype=='register'){
                    self.gacompany.registerCertFile = '';
                    self.imgregister = false;
                    self.imgregister1 = true;
                }else if(imgtype=='stand'){
                    self.gacompany.standardCertFile = '';
                    self.imgstandard = false;
                    self.imgstandard1 = true;
                }
                else if(imgtype=='business'){
                    self.gacompany.businessCertFile = '';
                    self.imgbusiness = false;
                    self.imgbusiness1 = true;
                }
            },
            handleSuccess (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                let self = this;
                file.url = res.url;
                self.gacompany.logoFile = res.url;
                alert(self.gacompany.logoFile);
                self.imglogo = true;
                self.imglogo1 = false;
            },
            handleSuccesscert (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                let self = this;
                file.url = res.url;
                self.gacompany.registerCertFile = res.url;
                self.imgregister = true;
                self.imgregister1 = false;
            },
            handleSuccessbusiness (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                let self = this;
                file.url = res.url;
                self.gacompany.businessCertFile = res.url;
                self.imgbusiness = true;
                self.imgbusiness1 = false;
            },
            handleSuccessstand (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                let self = this;
                file.url = res.url;
                self.gacompany.standardCertFile = res.url;
                self.imgstandard = true;
                self.imgstandard1 = false;
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
            handleBeforeUpload()
            {

            },
            getuploadListcomp(msg)
            {

            },
            handleSubmit: function () {
                this.$refs.gacomp.validate((valid) => {
                    if (valid) {
                        let self = this;
                        /*self.$profile.getProfile().then((profile) => {*/
                        let buz = JSON.parse(Cookies.get("profile"));
                            let comId = self.companyids;
                            //self.gacompany.orgId = profile.org.orgId;
                            //self.gacompany.userId = profile.user.userId;
                            self.gacompany.handbookFile = JSON.stringify(self.handbookfilelist);
                            if (typeof(self.gacompany.businesslist) != "undefined" &&self.gacompany.businesslist!= '') {
                                console.log("self.gacompany.businesslist=====",self.gacompany.businesslist)
                                self.gacompany.businessType = self.gacompany.businesslist.join(",");
                            }
                            if (typeof(self.gacompany.businessprojectlist) != "undefined" &&self.gacompany.businessprojectlist!= '') {
                                console.log("self.gacompany.businessprojectlist=====",self.gacompany.businesslist)
                                self.gacompany.businessProject = self.gacompany.businessprojectlist.join(",");
                            }
                            if (typeof(self.gacompany.companytypelist) != "undefined" &&self.gacompany.companytypelist!= '') {
                                console.log("self.gacompany.companytypelist=====",self.gacompany.businesslist)
                                self.gacompany.companyType = self.gacompany.companytypelist.join(",");
                            }
                            if (typeof(self.gacompany.standardtypelist) != "undefined" &&self.gacompany.standardtypelist!= '') {
                                console.log("self.gacompany.standardtypelist=====",self.gacompany.businesslist)
                                self.gacompany.standardType = self.gacompany.standardtypelist.join(",");
                            }
                            if (typeof(self.gacompany.operateTypelist) != "undefined" && typeof(self.gacompany.operateTypelist) != '') {

                                self.gacompany.operateType = self.gacompany.operateTypelist.join(",");
                            }
                            console.log("self.gacompany.licenseBegindate",self.gacompany.licenseBegindate);
//
//                            if (self.gacompany.licenseBegindate != null &&self.gacompany.licenseBegindate !='') {
//                                var d = new Date(self.gacompany.licenseBegindate);
//                                self.gacompany.licenseBegindate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
//                            }
//                            if (self.gacompany.licenseEnddate != null&&self.gacompany.licenseEnddate != '') {
//
//                                var d = new Date(self.gacompany.licenseEnddate);
//                                self.gacompany.licenseEnddate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
//                            }

                            console.log("self.gacompany.licenseBegindate",self.gacompany.licenseBegindate,typeof(self.gacompany.licenseBegindate));
                            //     let path=self.$axios.defaults.baseURL;
                            console.log("self.gacompany==="+self.gacompany);

                            self.$http.httpPost('/eim/api/company/save', self.gacompany).then(function (response) {
                        //        self.copmodal = true;
                                self.copmessage = '提交成功';
                                self.$emit("isUpdateEmit",'n',self.isInit);
                                self.$Message.success('保存成功');

                            }).catch(function (error) {
                                console.log(error);
                        //        self.copmodal = true;
                                self.copmessage = '保存失败';
                                self.$Message.error(self.copmessage);
                            });

                      /*  }, (error) => {
                            console.log('error', error);
                        });*/
                    } else {
                        this.$Message.error('表单验证失败!');
                    }
                });
            },
            getProv: function (prov) {
                let self = this;
                self.citys = [];
                self.gacompany.city='';
                //self.operationbase.city = self.operationbase.city;
                for (var i = 0; i < self.cityList.length; i++) {
                    if (self.cityList[i].p == prov) {
                        self.citys = self.cityList[i].c;
                    }
                }
            },
            initMeta() {
                let permTypes = this.$route.meta.permTypes;
                if (permTypes !== null && permTypes !== undefined) {
                    this.permTypes = permTypes;
                }
            },
        },
        mounted(){
            this.gacompanyinit();
            this.initMeta();


        },

        watch: {
            gacompany:
            {
                handler(newValue, oldValue) {
                    this.isInit=this.isInit+1;
                    if(this.isInit>2)//判断是否是初始化,0表示是初始化,大于2(监听用户修改的数据)
                    {
                        this.isUpdate='y';

                    }
                    this.$emit("isUpdateEmit",this.isUpdate,this.isInit);
                 },
                deep: true
            }
        },
        handleReset (name) {
            this.$refs[name].resetFields();
        },
    }
</script>
<style>


</style>