<template>
    <div style="width: 1280px; height: 900px; margin-top: 10px;background:white">
        <div>
            <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                <Button v-has="'upload'" type="primary" icon="plus" style="width: 98px;height: 32px;margin:24px 43px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small"   v-on:click="uploaddochandle">上传</Button>
            </span>
        </div>
        <div class="bottom5" style="width: 1160px; margin-left: 10px">
            <Table border  :columns="columns" :data="data"></Table>
        </div>
        <Modal v-model="add_doctemplate_modal" width="400" title="上传模板" @on-ok="savefile('doctemplate')" :loading="loading"
               @on-cancel="cancelhandle" :styles="{top: '20px'}">
            <Form ref="doctemplate" :label-width="80" :model="doctemplate" :rules="doctemplateValid" >
                <div >

                            <FormItem label="文件类型" prop="templateType" class="bottom5" >
                                <Select v-model="doctemplate.templateType" >
                                    <Option value="1">维修工程管理手册</Option>
                                    <Option value="2">培训大纲</Option>
                                    <Option value="3">航空器维修方案</Option>
                                    <Option value="4">航空器检查大纲</Option>
                                    <Option value="5">可靠性方案</Option>
                                    <Option value="6">最低设备清单</Option>
                                    <Option value="7">运行规范</Option>
                                    <Option value="8">运行手册</Option>
                                    <Option value="9">飞行训练管理手册</Option>
                                    <Option value="10">训练大纲</Option>
                                    <Option value="11">航空理论</Option>
                                    <Option value="12">法规政策</Option>
                                    <Option value="13">视频资料</Option>
                                </Select>
                            </FormItem>
                           <!-- <FormItem label="上传人" class="bottom5" >
                                <Input v-model="doctemplate .uploadingPerson" type="text" placeholder="请输入上传人"></Input>
                            </FormItem>-->
                            <FormItem label="版本号" prop="versionNumber" class="bottom5" >
                                <Input v-model="doctemplate.versionNumber" type="text" placeholder="请输入版本号"  ></Input>
                            </FormItem>
                </div>
            </Form>



            <div style="margin-left: 80px;"  >


                <!--<uploadcompany :uploadList=handbookfilelist :versionNum = datafile.versionNumber
                                   v-on:gacomplistenTochildEvent="getuploadListcomp" ></uploadcompany>-->
                <upload :uploadList=handbookfile :templateType = doctemplate.templateType :versionNum=doctemplate.versionNumber
                        v-on:gacomplistenTochildEvent="getuploadListcomp" >

                </upload>

            </div>
        </Modal>
    </div>
</template>

<script>
    import uploadcompany from './uploadcompanyfile.vue'
    import upload from './uploadtemp.vue'
    import Cookies from 'js-cookie';
    export default {
        components:{
           // 'uploadcompany':uploadcompany,
            'upload':upload
        },
        name: "doctemplate",
        data(){
            return {
                permTypes:[],
                columns:[
                   /* {   title:'公司名称',
                        key:'companyId',
                    },*/
                    {
                        title:'模板名称',
                        key:'templateName',
                        align:'center',
                    },
                    {
                        title:'模板类型',
                        key:'templateType',
                        align:'center',
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.templateType == 1) {
                                texts = "维修工程管理手册"
                            } else if (params.row.templateType == 2) {
                                texts = "培训大纲"
                            } else if (params.row.templateType == 3) {
                                texts = "航空器维修方案"
                            }
                            else if (params.row.templateType == 4) {
                                texts = "航空器检查大纲"
                            }
                            else if (params.row.templateType == 5) {
                                texts = "可靠性方案"
                            }
                            else if (params.row.templateType == 6) {
                                texts = "最低设备清单"
                            }
                            else if (params.row.templateType == 7) {
                                texts = "运行规范"
                            }
                            else if (params.row.templateType == 8) {
                                texts = "运行手册"
                            }
                            else if (params.row.templateType == 9) {
                                texts = "飞行训练管理手册"
                            }
                            else if (params.row.templateType == 10) {
                                texts = "训练大纲"
                            }
                            else if (params.row.templateType == 11) {
                                texts = "航空理论"
                            }
                            else if (params.row.templateType == 12) {
                                texts = "法规政策"
                            }
                            else if (params.row.templateType == 13) {
                                texts = "视频资料"
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    {
                        title:'版本号',
                        key:'versionNumber',
                        align:'center'
                    },
                    {
                        title:'上传人',
                        key:'uploadingPerson',
                        align:'center'
                    },
                    /*{
                        title:'上传路径',
                        key:'uploadPath'
                    },*/

                    /*{
                        title:'创建时间',
                        key:'createTime'
                    },
                    {
                        title:'更新时间',
                        key:'updateTime'
                    },*/
                    {
                        title: "操作",
                        key: "action",
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
                                                background: 'url(../../../../static/images/icon_operate_download.png) no-repeat',
                                                border: 'none'
                                            },
                                            on: {
                                                click: () => {
                                                    window.location.href = params.row.uploadPath;
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
                                                    let index = params.index;
                                                    let tempId = this.data[index].templateId;
                                                    this.detetetemplate(tempId, index);

                                                }
                                            }

                                        },
                                        ""
                                    )
                                ]);
                            }
                        }


                    },
                ],
                add_doctemplate_modal:false,
                loading:true,
                handbookfile:null,
                data:[],
                doctemplate:{
                    companyId:'',
                    templateName:'',
                    templateType:'',
                    uploadingPerson:'',
                    uploadPath:'',
                    versionNumber:''
                },
                doctemplateValid:{
                    templateType:[{required: true, message: '请选择模板类型', trigger: 'change'}],
                    versionNumber:[{required: true, message: '请输入版本号', trigger: 'blur'}],
                },
            }
        },
        methods:{
            change (status) {
                this.$Message.info('开关状态：' + status);
            },
            getuploadListcomp(selectType)
            {
                this.handbookfile = selectType

            },
            initsearch:function(){
                this.$http.httpGet('/eim/api/doctemplate/findbycompanyid?companyId='+this.doctemplate.companyId).then(res=>{
                    if(res.data.resultCode==200){
                        this.data = res.data.data;
                    }
                })
            },
            savefile:function (name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        let self = this;

                        //self.datafile.cId= profile.buz.companyId
                        /*this.$profile.getProfile().then((profile) => {*/
                        let buz = JSON.parse(Cookies.get("profile"));
                            this.doctemplate.uploadingPerson = buz.user.fullname;
                            if(this.handbookfile!=null){
                                let name = self.handbookfile.name
                                let url = self.handbookfile.url

                                self.doctemplate.templateName = name;
                                self.doctemplate.uploadPath = url;
                            }
                            self.$http.httpPost('/eim/api/doctemplate/savedoctemplate',self.doctemplate).then(res=>{
                                if(res.data.resultCode==200){
                                    self.data .push(res.data.data) ;
                                    this.loading = false;
                                    this.add_doctemplate_modal = false;
                                }
                            })
                       /* })*/
                    } else {
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                        this.$Message.error('验证失败!');
                    }
                })



            },
            cancelhandle:function(){
                this.add_doctemplate_modal = false;
                this.$refs['doctemplate'].resetFields();
            },
            uploaddochandle:function () {
                this.add_doctemplate_modal=true;

                this.doctemplate.templateType='';
                this.doctemplate.versionNumber='' ;
                this.handbookfile=null
            },
            detetetemplate:function (tempId,index) {
                this.$http.httpDelete('/eim/api/doctemplate/delete/'+tempId).then(response=> {
                    if(response.data.resultCode==200){
                        this.data.splice(index,1);
                        this.$Message.info("删除成功")
                    }
                }).catch(function (error) {
                    console.log(error);

                });
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
                this.doctemplate.companyId=buz.buz.companyId
                this.initsearch()
            this.initMeta();

            /*})*/

        }

    }
</script>


<style scoped>

</style>