<style scoped>
    .physicalp{
        margin-left: 28px;
        /*width: 900px;*/
        /*height: 400px;*/
        /*overflow: hidden;*/
    }

    .everyf{
        border: 1px solid ;
        border-color: #bbbec4;
        margin-top: 5px;
        position: relative;
    }
    .child{
        position: absolute;
        top:60px;
        right: 20px;
        width: 283px;
    }

    .flag{
        position: absolute;
        top: 5px;
        right: 3px;

    }
    .flag>img{
        width: 30px;
        display: block;
    }

    .ivu-form .ivu-form-item-label{
        color:#b3b3b3;
        font-size: 14px;

    }


    .ivu-input-icon+.ivu-input{
        height: 30px;
        background: #b3b3b3;
        padding-right: 0;
    }
    .ivu-row{
        margin-bottom: 30px;
    }
    .submit-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);
        background: linear-gradient(#a3a3a3, #e7e7e7);
    }
    .flybase-submit-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        background: -webkit-linear-gradient(#e7e7e7, #a3a3a3);
        background: linear-gradient(#e7e7e7, #a3a3a3);
    }
</style>
<template >
    <div>
        <div style="border:1px solid rgb(46, 130, 220);margin-top: 16px;margin-left: 8px;width: 965px;background: rgba(193, 220, 248, 0.5);position: absolute;top:169px;bottom: 0px;"></div>
        <div  style="margin-top: 16px;margin-left: 9px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <button @click="add_dialog_show(('formvali'))" data-v-6cb0b875="" type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style="width: 133px; height: 32px; margin: 24px 56px 11px 0px; border: 1px dashed rgb(43, 130, 218); background: rgb(178, 214, 251); font-size: 14px; color: rgb(36, 128, 221);"><!----> <i class="ivu-icon ivu-icon-plus"></i> <span>增  加</span></button>
                    <!--<Button type="primary" @click="modalshow('physicalvalidate')" icon="plus"-->
                    <!--style="width:150px;margin-right:10px;" size="small">增  加</Button>-->
                </span>
            <div class="bottom5" style="width: 870px;margin-left: 47px">
                <Table border :columns="columns7" :data="formValidate.trainingRecords"></Table>
            </div>

        </div>
        <!--<Form :label-width="120" style="margin:-15px 10px;">-->
            <!--<div class="bottom5" style="margin-top:10px;height:180px;">-->
            <!--<span style="width:100%;display:inline-block;text-align:right;">-->

                <!--<Button type="primary" @click="add_dialog_show(('formvali'))" icon="plus"-->
                        <!--style="width:150px;margin-right:10px;" size="small">增  加</Button>-->
            <!--</span>-->

                <!--<div class="bottom5" style="margin-top:5px;padding:10px;">-->
                    <!--<Table border :columns="columns7" :data="formValidate.trainingRecords"></Table>-->
                <!--</div>-->
            <!--</div>-->
        <!--</Form>-->

    <Modal v-model="modal1" title="培训信息" transfer="false"  width="700px" @on-ok="submittraining" @on-cancel="dealcancel" :styles="{top: '20px'}">
        <Form  ref="formvali" :rules="rule" :model="trainingRecord" :label-width="100" style="height: 100%;" >
            <FormItem style="display: none">
                <Input    v-model="trainingRecord.id" ></Input>
            </FormItem>

            <FormItem class="bottom5" label="培训类型"  prop="trainType" style="width: 50%;height: 34px;margin-bottom: 24px" >
                <Input  v-model="trainingRecord.trainType" placeholder="培训类型" ></Input>
            </FormItem>

            <FormItem class="bottom5" label="培训科目"  prop="trainSubject" style="width: 50%;height: 34px;margin-bottom: 24px" >
                <Input  v-model="trainingRecord.trainSubject" placeholder="培训科目"></Input>
            </FormItem>

            <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="培训内容"  prop="trainContent" >
                <Input  v-model="trainingRecord.trainContent" placeholder="培训内容"></Input>
            </FormItem>

            <FormItem class="bottom5" label="开始时间" style="width: 50%;height: 34px;margin-bottom: 24px" prop="beginDate" >
                <DatePicker type="date" placeholder="开始时间" placement="bottom" v-model="trainingRecord.beginDate"></DatePicker>
            </FormItem>

            <FormItem class="bottom5" label="结束时间" style="width: 50%;height: 34px;margin-bottom: 24px" prop="endDate" >
                <DatePicker type="date" placeholder="结束时间"  placement="bottom" v-model="trainingRecord.endDate"></DatePicker>
            </FormItem>

            <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="考核结果"  prop="result" >
                <Input  v-model="trainingRecord.result" placeholder="考核结果"></Input>
            </FormItem>

            <div class="physicalpimg" style="float: right">
                <word ref="abc" :itemflag=trainingRecord.certifactefile style="margin-top: 80px;" class="child" v-on:click="getCheckform()" v-on:listenTochildEvent="getuploadList($event,trainingRecord)" ></word>
               <!-- <h3 style="margin-top: -93%;">
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
                       action="http://fs.glinkus.com:18081/upload"
                       style="display: inline-block;">
                        <a style="color: #fff;">编辑</a>
                   </Upload>&nbsp;&nbsp;

                   <a style="color: #fff;" v-on:click="handleView(trainingRecord.certifactefile)">查看</a>
                   &nbsp;&nbsp;<a style="color: #fff;" v-on:click="handleRemove(trainingRecord.certifactefile)">删除</a></h3>
           --> </div>
        </Form>
    </Modal>
        <!--<Modal title="View Image" v-model="visible" v-if="trainingRecord.certifactefile==''">-->
            <!--<img src="../../../assets/defalutupload.png" v-if="visible" style="width: 100%;line-height: 58px;padding-bottom: 3.5%">-->
        <!--</Modal>-->
        <!--<Modal title="View Image" v-model="visible" v-else>-->
            <!--<img :src="trainingRecord.certifactefile" v-if="visible" style="width: 100%;padding-bottom: 3.5%">-->
        <!--</Modal>-->

    </div>

</template>


<script>
    import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";
    export default {

        data() {
            return {

                rule:{
                    trainType:[{required:true, message: '培训类型不能为空', trigger: 'blur'}],
                    trainSubject:[{required:true, message: '培训科目不能为空', trigger: 'blur'}],
                    trainContent:[{required:true, message: '培训内容不能为空', trigger: 'blur'}],
                    beginDate:[{required: true,type:"date", message: '开始时间不能为空', trigger: 'change'}],
                    endDate:[{required: true,type:"date", message: '结束时间不能为空', trigger: 'change'}],
                    result:[{required:true, message: '考核结果不能为空', trigger: 'blur'}],
                },
                visible: false,
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                formValidate: {
                    trainingRecords: [
                        {
                            id:'',
                            employeeid:'',
                            trainType: '',
                            trainSubject:'',
                            trainContent:'',
                            beginDate: '',
                            endDate: '',
                            createTime:'',
                            updateTime:'',
                            result: '',
                            certifactefile:'',
                            flag: ''
                        },
                    ]

                },
                trainingRecord:
                    {
                        id:'',
                        employeeid:'',
                        trainType: '',
                        trainSubject:'',
                        trainContent:'',
                        beginDate: '',
                        endDate: '',
                        createTime:'',
                        updateTime:'',
                        result: '',
                        certifactefile:'',
                        flag: ''
                    },


                columns7: [
                    {
                        title: '培训类型',
                        key: 'trainType',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '培训科目',
                        width: 100,
                        key: 'trainSubject',
                        align: 'center'
                    },
                    {
                        title: '培训内容',
                        width: 150,
                        key: 'trainContent',
                        align: 'center'
                    },
                    {
                        title: '开始时间',
                        width: 120,
                        key: 'beginDate',
                        align: 'center'
                    },
                    {
                        title: '结束时间',
                        width: 120,
                        key: 'endDate',
                        align: 'center'
                    },
                    {
                        title: '培训结果',
                        width: 129,
                        key: 'result',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }, '修改'),

                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {

                                            this.confirm(params.index);
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],


            }
        },
        props:['employeeId'],

        components: {
            IForm,
            'word': word,
        },
        created: function (newval) {
           // alert("进到这个");
            this.init(this.employeeId);
        },

        methods: {
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

            handleSuccess(res,file){
                this.$refs.abc.h4show=false;
                this.trainingRecord.certifactefile=res.url;
                this.$emit('listenTochildEvent',this.itemflag);
            },
            handleRemove (file) {
                this.$refs.abc.h4show=true;
                this.trainingRecord.certifactefile='';

            },
            handleView (val) {
                this.visible = true;
            },
            dealcancel(){
                let self=this;
                self.trainingRecord = {};
                self.init(self.employeeId);
            },

            getCheckform() {
                return this.trainingRecord;
            },
            getuploadList: function ($event, trainingRecord) {
                console.log($event);
                trainingRecord.certifactefile = $event;
            },
            notnull(datainfo){
                if(datainfo=='' || datainfo==null){
                    alert("空的");
                }
            },
            confirm (index) {
                this.$Modal.confirm({
                    title: '警告框：',
                    content: '<p>确认删除该条记录？</p>',
                    onOk: () => {
                        this.removeCheck (index);

                    },
                    onCancel: () => {

                    }
                });
            },
            init:function(id){
                let self = this;
                self.formValidate.trainingRecords=[{}];
                console.log("go to get search");




                    self.$http.httpGet('/eim/api/training/serach/getbyemployeeId', {
                        employeeId:id,
                    }).then(function (response) {
                        console.log(response,'.................');
                        let datainfo=response.data;
                        self.formValidate.trainingRecords.splice(0, 1);
                        console.log(datainfo);
                        for(var i=0;i<datainfo.length;i++){
                            self.formValidate.trainingRecords.push(datainfo[i]);
                        }
                    })
            },
            //添加框
            add_dialog_show:function (val){
                //清除信息
                this.$refs[val].resetFields();
                this.modal1=true;
            },
            //修改
            update_dialog_show:function (id){
                let self=this;
                this.modal1=true;
                self.$http.httpGet('/eim/api/training/serach/getTrainingRecordById', {
                    id
                }).then(function (response) {
                    self.trainingRecord=response.data;

                    self.init(self.employeeId);
                }) .catch(function (error) {
                    console.log(error);
                });


            },
            //修改框
            updateCheck (index) {

               // this.check_form = this.formValidate.trainingRecords[index].id;
                this.trainingRecord=this.formValidate.trainingRecords[index];
                if( this.trainingRecord.certifactefile=='' ){
                    this.$refs.abc.h4show=true;
                }
                else{
                    this.$refs.abc.h4show=false;
                }
                this.$refs['formvali'].resetFields();
//                this.update_dialog_show(id);
                this.formValidate.trainingRecords[index].beginDate = new Date(this.formValidate.trainingRecords[index].beginDate);
                this.formValidate.trainingRecords[index].endDate = new Date(this.formValidate.trainingRecords[index].endDate);
                this.modal1=true;
                this.init(this.employeeId);


            },
            //删除
            removeCheck (index) {

                let self=this;

                let id=self.formValidate.trainingRecords[index].id;
                self.$http.httpDelete('/eim/api/training/deleteTrainingRecordById/'+id,{}

                ).then(function (response) {
                    alert("删除成功");
                    self.init(self.employeeId);
                }) .catch(function (error) {
                    console.log(error);
                });
            },
            submittraining:function(){
                this.$refs.formvali.validate((valid) => {
                    let self = this;
                    if(valid) {
                        if ((self.trainingRecord.beginDate.valueOf()) > (self.trainingRecord.endDate.valueOf())) {
                            this.$Message.error('开始时间不能大于结束时间，请重新添加！');
                            return;
                        }
                        self.trainingRecord.employeeId = self.employeeId
                        self.$http.jsonPost('/eim/api/training', self.trainingRecord).then(function (response) {
                            console.log(response.data, '******************');
                            self.trainingRecord = {};
                            self.init(self.employeeId);
                        }).catch(function (error) {
                            console.log(error);
                            return;
                        });
                        alert("添加培训信息成功");
                    }
                    else{
                        this.$Message.error('有信息未填写，表单验证失败!');
                    }
                })



            },
            submitCheckForm(){
                let self = this;
                let url = "/eim/api";
                let checkUrl = url + "/license/check/save";
                let is_update = false;
                if(self.check_form.id)
                {
                    is_update = true;
                }

                if(!is_update)
                {
                    self.check_form.pilotLicenseId = self.qulification.licenseId;

                }

                self.$http.httpPost(checkUrl, self.check_form).then(function(res) {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.check_form = {};
                        if(!is_update)
                        {
                            self.licenseChecks.push(result.data);
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
            addformdate: function () {
                this.formValidate.trainingRecords.push({
                    id:'',
                    employeeid:'',
                    traintype: '',
                    trainsubject:'',
                    trainContent:'',
                    certificateStartTime: '',
                    certificateValidityTime: '',
                    beginDate: '',
                    endDate: '',
                    result:'',
                    createTime:'',
                    updateTime:'',
                    flag: '',
                },);
            },
            getuploadList: function ($event, item) {
                console.log($event);
                item.certificateFilepath=$event;
            },
            getitem:function (item) {

                return item;
            },

            handleremove: function (item) {
                if (item.flag == "") {

                    this.cancelform(item);
                }
                else {
                    alert("该数据已保存是否删除？");
                    this.deletedform(item);
                }
            },
            cancelform: function (item) {
                this.formValidate.trainingRecords.splice(this.formValidate.trainingRecords.indexOf(item), 1);
            },
            deletedform: function (item) {
                let self = this;
                let  baseUrl = 'http://192.168.0.200:38080';
                let id = item.id;
                self.$http.httpDelete('/eim/api/physical/'+id)
                    .then(function (response) {
                        console.log(response)
                        self.cancelform(item);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },



        wtach: {
            'formValidate.trainingRecords':function (newval) {
                console.log(newval);
            }
        },

    }

</script>
