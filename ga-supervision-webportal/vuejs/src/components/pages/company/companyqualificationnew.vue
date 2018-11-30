<template>
    <div style="width: 1280px; height: 900px; margin-top: 10px;background:white">
        <div>
              <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                 <Button v-has="'add'" type="primary" icon="plus" style="width: 98px;height: 32px;margin:24px 43px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small"    v-on:click="add()">增 加</Button>
             </span>
        </div>
        <div class="bottom5" style="width: 1160px; margin-left: 10px">
            <Table border  @on-row-dblclick="dbDetails($event)"  :columns="columns" :data="data" ></Table>
        </div>
        <Modal v-model="add_companyqual_modal" width="660" :loading="loading"  :title="title" @on-ok="saveocomqual('companyqual')" @on-cancel="cancelhandle"
               :styles="{top: '20px'}">
            <Form ref="companyqual" :label-width="100" :model="companyqual" :rules="companyqualValid" style="" >
                <div>

                    <FormItem label="资质名称" class="bottom5" prop="qualificationname">
                        <Input v-model="companyqual.qualificationname" style="width: 300px"  placeholder=""></Input>
                    </FormItem>

                    <FormItem label="证书编号" class="bottom5" prop="code">
                        <Input v-model="companyqual.code" style="width: 300px"  placeholder=""></Input>
                    </FormItem>

                    <FormItem label="生效时间" class="bottom5" prop="starttime" >
                        <DatePicker type="date" style="width:300px;" placeholder="请选择生效日期" v-model="companyqual.starttime"  ></DatePicker>
                    </FormItem>
                    <FormItem label="是否长久有效">
                        <RadioGroup v-model="radio" style="color: #016bd9">
                            <Radio label="yes">是</Radio>
                            <Radio label="no">否</Radio>
                        </RadioGroup>
                    </FormItem>

                    <FormItem label="失效时间" class="bottom5" prop="endtime" v-if=" radio=='no'">
                        <DatePicker type="date" style="width:300px;" placeholder="请选择失效日期" v-model="companyqual.endtime"></DatePicker>
                    </FormItem>

                    <FormItem label="颁发单位" class="bottom5" prop="issueorgname">
                        <Input v-model="companyqual.issueorgname" style="width: 300px"    placeholder=""></Input>
                    </FormItem>

                </div>
            </Form>
            <!-- 上传图片后,需要将图片路径保存 -->
           <!-- <div style="font-size:14px;color:#8a909f;margin-top: 61px; margin-bottom: 5px;">运行合格证扫描件</div>-->
            <div class="upload_img" style="float:right;margin-top: -260px;">
                <div class="company-demo-upload-list" v-show="imgstandard">

                    <div style="width: 193px;height:118px;line-height: 58px; text-align: center;" v-show="imgstandard">
                        <img :src="companyqual.attchmentaddress"  style="width:193px;height:118px;margin: 0px auto;" ></img>
                    </div>
                </div>
                <Upload ref="upload" :show-upload-list="false" :on-success="handleSuccessstand" :format="['jpg','jpeg','png']" :max-size="2048" :on-format-error="handleFormatError" :on-exceeded-size="handleMaxSize" :before-upload="handleBeforeUpload" multiple action="http://127.0.0.1:18081/upload" style="width:193px;">
                    <div class="company-upload-div" id="comqualid" v-show="imgstandard1">
                        <div>
                            <img src="../../../assets/defalutupload.png" class="company-upload-div-img">
                        </div>
                        <div class="company-upload-div-div" v-show="imgstandard1">图片小于2M<br>尺寸小于640*480</div>
                    </div>
                </Upload>
                <div class="company-demo-upload-list-cover">
                    <div class="onclickimg1" @click="handleView(companyqual.attchmentaddress)"> &nbsp; </div>
                    <div class="onclickimg2" @click="handleBtnClick('comqualid') "> &nbsp; </div>
                    <div class="onclickimg3" @click="handleremoveimg('comqid')"> &nbsp; </div>
                </div>

            </div>
        </Modal>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>

<script>
    import imgdetail from '../../views/imgdetail.vue';
    import Cookies from 'js-cookie';
    export default {
        name: "companyqualificationnew",
        components:{
            'imgdetail':imgdetail,
        },
        data(){
            return{
                radio:'yes',
                permTypes:[],
                columns:[
                    {
                        title: '资质名称',
                        key: 'qualificationname',
                        align:'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.qualificationname
                                    }
                                }, params.row.qualificationname)
                            ]);

                        }
                    },
                    {
                        title: '证书编号',
                        key: 'code',
                        align:'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.code
                                    }
                                }, params.row.code)
                            ]);

                        }
                    },
                    {
                        title: '生效日期',
                        key: 'starttime',
                        align:'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.starttime
                                    }
                                }, params.row.starttime)
                            ]);

                        }
                    },
                    {
                        title: '失效日期',
                        key: 'endtime',
                        align:'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.endtime
                                    }
                                }, params.row.endtime)
                            ]);

                        }
                    },
                    {
                        title: '颁发单位',
                        key: 'issueorgname',
                        align:'center',
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    style: {
                                        display: 'inline-block',
                                        width: '100%',
                                        overflow: 'hidden',
                                        textOverflow: 'ellipsis',
                                        whiteSpace: 'nowrap'
                                    },
                                    domProps: {
                                        title: params.row.issueorgname
                                    }
                                }, params.row.issueorgname)
                            ]);

                        }
                    },
                    {
                        title: "操作",
                        key: "action",
                        align:'center',
                        width: 120,
                        align: "center",
                        render: (h, params) => {
                            if(this.permTypes.includes("add")) {
                                return h("div", [
                                    h(
                                        "Button",
                                        {
                                            props: {
                                                type: "error",
                                                size: "small"
                                            },
                                            style: {
                                                marginRight: '16px',
                                                marginTop: '4px',
                                                width: '30px',
                                                height: '30px',
                                                background: 'url(../../../../static/images/icon_operate_edit.png) no-repeat',
                                                border: 'none'
                                            },
                                            on: {
                                                click: () => {
                                                    this.title = '修改企业资质记录'
                                                    this.forval(params);

                                                    if(this.companyqual.endtime=='2099-09-09'||this.companyqual.endtime=='2099-09-06'||this.companyqual.endtime===null ) {
                                                        this.radio = 'yes';
                                                    }else{
                                                        this.radio = 'no';
                                                    }
                                                }
                                            }

                                        },
                                        ""
                                    ),
                                    h(
                                        "Button",
                                        {
                                            props: {
                                                type: "error",
                                                size: "small"
                                            },
                                            style: {
                                                marginLeft: '4px',
                                                marginTop: '4px',
                                                width: '30px',
                                                height: '30px',
                                                background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                                border: 'none'
                                            },
                                            on: {
                                                click: () => {
                                                    console.log(params.index);
                                                    this.deletecomqual(params.row.id, params.index);


                                                }
                                            }

                                        },
                                        ""
                                    )
                                ]);
                            }
                        }
                    }
                ],
                data:[],
                loading:true,
                add_companyqual_modal:false,
                companyqual:{
                    companyid:'',
                    qualificationname:'',
                    code:'',
                    starttime:'',
                    endtime:'',
                    issueorgname:'',
                    attchmentaddress:''
                },
                imgstandard:false,
                imgstandard1:true,
                companyqualIndex:'',
                companyqualValid:{
                    qualificationname:[{required: true, message: '请输入资质名称', trigger: 'blur'}],
                    code:[{required: true, message: '请输入证书编号', trigger: 'blur'}],
                    starttime:[{required: true, type:"date",message: '请选择生效时间', trigger: 'change'}],
                    endtime:[{required: true,type:"date", message: '请选择截止时间', trigger: 'change'}],
                    issueorgname:[{required: true, message: '请输入颁发单位', trigger: 'blur'}],
                },
                title:'',
            }
        },
        methods:{
            companyqualificationinit: function (newval) {
                let self = this;
                /*self.$profile.getProfile().then((profile) =>{*/
                    //self.comid=newval;
                    self.$http.httpGet('/eim/api/cq/serach/getbycompanyid', {
                        companyId:newval
                    }).then(function (response) {
                        console.log(response);
                        self.data=response.data;

                    }) .catch(function (error) {
                        console.log(error);
                    });
               /* }, (error) => {
                    console.log('error', error);
                });*/

            },
            add:function () {
                this.add_companyqual_modal=true
                this.title='新增企业资质记录'
                this.imgstandard=false
                this.imgstandard1=true
                this.companyqual = {}
                this.$refs['companyqual'].resetFields();
            },
            cancelhandle:function(){
                this.add_companyqual_modal = false;
                this.$refs['companyqual'].resetFields();
            },
            //赋值
            forval: function (params) {
                this.add_companyqual_modal = true;
                this.companyqual.companyId = this.data[params.index].companyId;
                this.companyqual.id = this.data[params.index].id;
                this.companyqual.qualificationname = this.data[params.index].qualificationname;
                this.companyqualIndex = params.index;
                this.companyqual.code = this.data[params.index].code;
                this.companyqual.starttime = this.data[params.index].starttime;
                this.companyqual.endtime = this.data[params.index].endtime;
                this.companyqual.issueorgname = this.data[params.index].issueorgname;
                this.companyqual.attchmentaddress = this.data[params.index].attchmentaddress;
                if(this.companyqual.attchmentaddress!=null && this.companyqual.attchmentaddress!=''&& typeof (this.companyqual.attchmentaddress)!='undefined'){
                    this.imgstandard = true;
                    this.imgstandard1 = false;
                }
            },
            saveocomqual:function(name){

                this.$refs[name].validate((valid) => {
                    if (valid) {
                        let self = this;
                        let id = self.companyqual.id;
                        let buz = JSON.parse(Cookies.get("profile"));
                        this.companyqual.companyid = buz.buz.companyId;
                        if(self.radio=='yes'){
                            self.companyqual.endtime=null;
                        }
                        if(typeof id=='undefined'){
                            self.$http.httpPost('/eim/api/cq',self.companyqual).then(function (response) {
                                self.data.push(response.data)
                                self.loading = false;
                                self.add_companyqual_modal = false;
                            }).catch(function (error) {
                                console.log(error);
                            })
                        }else{
                            self.$set(self.companyqual, 'id', id);
                            self.$http.httpPost('/eim/api/cq',self.companyqual).then(function(res) {
                                self.data.splice(self.companyqualIndex, 1, self.companyqual)
                                self.data[self.companyqualIndex] = res.data;
                                self.$Message.info("修改成功")
                                self.loading = false;
                                self.add_companyqual_modal = false;
                            })
                        }
                    } else {
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 2000);
                        this.$Message.error('验证失败!');
                    }
                })


            },
            handleSuccessstand (res, file) {
                // 因为上传过程为实例，这里模拟添加 url
                let self = this;
                file.url = res.url;
                self.companyqual.attchmentaddress = res.url;
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
            handleView (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },
            handleBtnClick: function(docid){
                if(docid=='logoid')
                {
                    document.getElementById("logoid").click();
                }else if('resid'==docid)
                {
                    document.getElementById("resid").click();
                }else if('comqualid'==docid){
                    document.getElementById("comqualid").click();
                }else if('businessid'==docid){
                    document.getElementById("businessid").click();
                }

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
                }else if(imgtype=='comqid'){
                    self.companyqual.attchmentaddress = '';
                    self.imgstandard = false;
                    self.imgstandard1 = true;
                }
                else if(imgtype=='business'){
                    self.gacompany.businessCertFile = '';
                    self.imgbusiness = false;
                    self.imgbusiness1 = true;
                }
            },
            deletecomqual:function (id,index) {
                this.$http.httpDelete('/eim/api/cq/' + id).then((res) => {
                        this.data.splice(index, 1);
                        this.$Message.info("删除成功")
                })
            },
            dbDetails:function (column) {
                this.$router.push({path:'/comqualdetail',query: {comqual:column }})
            },
            initMeta() {
                let permTypes = this.$route.meta.permTypes;
                if (permTypes !== null && permTypes !== undefined) {
                    this.permTypes = permTypes;
                }
            },
        },
        mounted(){
            /*this.$profile.getProfile().then((profile) => {*/
            let buz = JSON.parse(Cookies.get("profile"));
            this.companyqual.companyid = buz.buz.companyId;
            this.companyqualificationinit(this.companyqual.companyid);
            this.initMeta();

       /* })*/
        }
    }
</script>

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