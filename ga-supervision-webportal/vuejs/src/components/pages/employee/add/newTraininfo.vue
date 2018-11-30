<style>
    .child{
        top:210px;
        right: 70px;
        width: 283px;
    }
</style>
<template>
    <div style="width: 1280px; height: auto; background:white">
        <div style="margin-left: 9px;">
            <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                <Button type="primary" @click="add_dialog_show(('formvali'))" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
            </span>
            <div class="bottom5" style="width: 1160px; margin-left: 10px">
                <Table border :columns="columns7" :data="formValidate.trainingRecords"></Table>
            </div>
        </div>

        <Modal v-model="modal1" title="培训信息" width="700px"  @on-ok="submittraining" @on-cancel="dealcancel" :styles="{top: '20px'}">

            <Form ref="formvali" :rules="rule" :model="trainingRecord" :label-width="120" style="height: 100%;">
                <FormItem style="display: none">
                    <Input v-model="trainingRecord.id"></Input>
                </FormItem>
                <div>
                <FormItem class="bottom5" label="培训课题" prop="trainSubject" style="width:45%;display: inline-block">
                    <Input v-model="trainingRecord.trainSubject" placeholder="培训课题"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width:45%;display: inline-block" label="培训机构名称"  prop="trainOorganization">
                    <Input v-model="trainingRecord.trainOorganization" placeholder="培训机构名称"></Input>
                </FormItem>
                </div>

                <div>
                <FormItem class="bottom5" label="培训开始时间" style="width: 45%;display: inline-block"
                          prop="beginDate">
                    <DatePicker type="date" placeholder="培训开始时间" placement="bottom"
                                v-model="trainingRecord.beginDate" style="width: 182px"></DatePicker>
                </FormItem>
                <FormItem class="bottom5" label="培训结束时间" style="width: 45%;display: inline-block"
                          prop="endDate">
                    <DatePicker type="date" placeholder="培训结束时间" placement="bottom"
                                v-model="trainingRecord.endDate" style="width: 182px"></DatePicker>
                </FormItem>
                </div>

                <div>
                <FormItem class="bottom5" style="width: 45%;display: inline-block" label="培训结果"
                          prop="result">
                    <Input v-model="trainingRecord.result" placeholder="培训结果"></Input>
                </FormItem>

                <!--<FormItem class="bottom5" style="width: 45%;display: inline-block" label="证明人"
                          prop="witness">
                    <Input v-model="trainingRecord.witness" placeholder="证明人"></Input>
                </FormItem>-->
                </div>

                <div>
                <!--<FormItem class="bottom5" style="width: 45%;display: inline-block" label="证明人联系方式"
                          prop="witnessPhone">
                    <Input v-model="trainingRecord.witnessPhone" placeholder="证明人联系方式"></Input>
                </FormItem>-->
                    <FormItem class="bottom5" style="width: 45%;display: inline-block" label="培训合格证编号"
                              prop="certificateNo">
                        <Input v-model="trainingRecord.certificateNo" placeholder="培训合格证编号"></Input>
                    </FormItem>

                <!--<FormItem class="bottom5" style="width: 45%;display: inline-block" label="培训合格证编号"
                          prop="certificateNo">
                    <Input v-model="trainingRecord.certificateNo" placeholder="培训合格证编号"></Input>
                </FormItem>-->
                </div>
                <div >
                <FormItem class="bottom5" label="颁发日期" style="width: 45%;"
                          prop="beginDate">
                    <DatePicker type="date" placeholder="颁发日期" placement="bottom"
                                v-model="trainingRecord.issueDate" style="width: 182px"></DatePicker>
                </FormItem>
                </div>
                <div>
                    <FormItem class="bottom5" style="width: 45%;display: inline-block" label="证明人"
                              prop="witness">
                        <Input v-model="trainingRecord.witness" placeholder="证明人"></Input>
                    </FormItem>
                </div>
                <div>
                    <!--<FormItem class="bottom5" style="width: 45%;display: inline-block" label="培训合格证编号"
                              prop="certificateNo">
                        <Input v-model="trainingRecord.certificateNo" placeholder="培训合格证编号"></Input>
                    </FormItem>-->
                    <FormItem class="bottom5" style="width: 45%;display: inline-block" label="证明人联系方式"
                              prop="witnessPhone">
                        <Input v-model="trainingRecord.witnessPhone" placeholder="证明人联系方式"></Input>
                    </FormItem>
                </div>

                <div>
                    <word ref="abc" :itemflag=trainingRecord.certificateScanning  class="child"
                          v-on:click="getCheckform()"
                          v-on:listenTochildEvent="getuploadList($event,trainingRecord)"></word>
                </div>

            </Form>
        </Modal>
    </div>
</template>


<script>
    import word from '../../company/uploadfile.vue';
    import IForm from "../../../../../node_modules/iview/src/components/form/form.vue";
    import Cookies from 'js-cookie';

    export default {
        data() {
            return {
                rule: {
                    trainSubject: [{required: true, message: '培训课题不能为空', trigger: 'blur'}],
                    trainOorganization: [{required: true, message: '培训机构名称不能为空', trigger: 'blur'}],
                    beginDate: [{required: true, type: "date", message: '开始时间不能为空', trigger: 'change'}],
                    endDate: [{required: true, type: "date", message: '结束时间不能为空', trigger: 'change'}],
                    result: [{required: true, message: '培训结果不能为空', trigger: 'blur'}],
                    witness: [{required: true, message: '证明人不能为空', trigger: 'blur'}],
                    witnessPhone: [{required: true, message: '证明联系方式不能为空', trigger: 'blur'}],
                    certificateNo: [{required: true, message: '培训合格证编号不能为空', trigger: 'blur'}],
                    issueDate: [{required: true, message: '颁发日期不能为空', trigger: 'blur'}],
                },
                visible: false,
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                formValidate: {
                    trainingRecords: [
                        {
                            id: '',
                            employeeid: '',
                            trainSubject: '',
                            trainOorganization: '',
                            beginDate: '',
                            endDate: '',
                            createTime: '',
                            updateTime: '',
                            result: '',
                            certificateScanning: '',
                            flag: '',

                            witness:'',
                            witnessPhone:'',
                            certificateNo:'',
                            issueDate:''


                        },
                    ]

                },
                trainingRecord:
                    {
                        id: '',
                        employeeid: '',
                        trainType: '',
                        trainSubject: '',
                        trainContent: '',
                        beginDate: '',
                        endDate: '',
                        createTime: '',
                        updateTime: '',
                        result: '',
                        certificateScanning: '',
                        flag: '',
                        trainOorganization:'',
                        witness:'',
                        witnessPhone:'',
                        certificateNo:'',
                        issueDate:''
                    },

                columns7: [
                    {
                        title: '培训合格证编号',
                        width: 178,
                        key: 'certificateNo',
                        align: 'center'
                    },
                    {
                        title: '培训课题',
                        width: 178,
                        key: 'trainSubject',
                        align: 'center'
                    },
                    {
                        title: '培训机构名称',
                        width: 168,
                        key: 'trainOorganization',
                        align: 'center'
                    },
                    {
                        title: '开始时间',
                        width: 168,
                        key: 'beginDate',
                        align: 'center'
                    },
                    {
                        title: '结束时间',
                        width: 168,
                        key: 'endDate',
                        align: 'center'
                    },
                    {
                        title: '培训结果',
                        width: 178,
                        key: 'result',
                        align: 'center'
                    },

                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                /*h('Button', {
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
                                }, '删除')*/
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '16px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../../static/images/icon_operate_edit.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginLeft: '4px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.confirm(params.index);
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],


            }
        },
        props: ['employeeId'],

        components: {
            IForm,
            'word': word,
        },
        created: function (newval) {
            let buz = JSON.parse(Cookies.get("profile"));
            this.employeeId=buz.buz.employeeId;
            this.init(this.employeeId);
        },

        methods: {
            handleFormatError(file) {
                this.$Notice.warning({
                    title: '文件格式不正确',
                    desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
                });
            },
            handleMaxSize(file) {
                this.$Notice.warning({
                    title: '超出文件大小限制',
                    desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
                });
            },

            handleSuccess(res, file) {
                this.$refs.abc.h4show = false;
                this.trainingRecord.certificateScanning = res.url;
                this.$emit('listenTochildEvent', this.itemflag);
            },
            handleRemove(file) {
                this.$refs.abc.h4show = true;
                this.trainingRecord.certificateScanning = '';

            },
            handleView(val) {
                this.visible = true;
            },
            dealcancel() {
                let self = this;
                self.trainingRecord = {};
                self.init(self.employeeId);
            },

            getCheckform() {
                return this.trainingRecord;
            },
            getuploadList: function ($event, trainingRecord) {
                console.log($event);
                trainingRecord.certificateScanning = $event;
            },
            notnull(datainfo) {
                if (datainfo == '' || datainfo == null) {
                    alert("空的");
                }
            },
            confirm(index) {
                this.$Modal.confirm({
                    title: '警告框：',
                    content: '<p>确认删除该条记录？</p>',
                    onOk: () => {
                        this.removeCheck(index);

                    },
                    onCancel: () => {

                    }
                });
            },
            init: function (id) {
                let self = this;
                self.formValidate.trainingRecords = [{}];
                self.$http.httpGet('/eim/api/training/serach/getbyemployeeId', {
                    employeeId: id,
                }).then(function (response) {
                    console.log(response, '.................');
                    let datainfo = response.data;
                    self.formValidate.trainingRecords.splice(0, 1);
                    console.log(datainfo);
                    for (var i = 0; i < datainfo.length; i++) {
                        self.formValidate.trainingRecords.push(datainfo[i]);
                    }
                })
            },
            //添加框
            add_dialog_show: function (val) {
                let self = this;
                if(self.employeeId == null || self.employeeId===''){
                    alert('您尚未添加基础人员，请先添加基础人员');
                    return false;
                }
                //清除信息
               this.$refs[val].resetFields();
                this.modal1 = true;
            },
            //修改
            update_dialog_show: function (id) {
                let self = this;
                this.modal1 = true;
                self.$http.httpGet('/app/api/training/serach/getTrainingRecordById', {
                    id
                }).then(function (response) {
                    self.trainingRecord = response.data;

                    self.init(self.employeeId);
                }).catch(function (error) {
                    console.log(error);
                });


            },
            //修改框
            updateCheck(index) {

                // this.check_form = this.formValidate.trainingRecords[index].id;
                this.trainingRecord = this.formValidate.trainingRecords[index];
                if (this.trainingRecord.certificateScanning == '') {
                    this.$refs.abc.h4show = true;
                }
                else {
                    this.$refs.abc.h4show = false;
                }
                this.$refs['formvali'].resetFields();
//                this.update_dialog_show(id);
                this.formValidate.trainingRecords[index].beginDate = new Date(this.formValidate.trainingRecords[index].beginDate);
                this.formValidate.trainingRecords[index].endDate = new Date(this.formValidate.trainingRecords[index].endDate);
                this.modal1 = true;
                this.init(this.employeeId);


            },
            //删除
            removeCheck(index) {

                let self = this;

                let id = self.formValidate.trainingRecords[index].id;
                self.$http.httpDelete('/eim/api/training/deleteTrainingRecordById/' + id, {}
                ).then(function (response) {
                    alert("删除成功");
                    self.init(self.employeeId);
                }).catch(function (error) {
                    console.log(error);
                });
            },
            submittraining: function () {
                this.$refs.formvali.validate((valid) => {
                    let self = this;
                    if (valid) {
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
                    else {
                        this.$Message.error('有信息未填写，表单验证失败!');
                    }
                })


            },
            submitCheckForm() {
                let self = this;
                let url = "/app/api";
                let checkUrl = url + "/license/check/save";
                let is_update = false;
                if (self.check_form.id) {
                    is_update = true;
                }

                if (!is_update) {
                    self.check_form.pilotLicenseId = self.qulification.licenseId;

                }

                self.$http.httpPost(checkUrl, self.check_form).then(function (res) {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.check_form = {};
                        if (!is_update) {
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
                id: '',
                employeeid: '',
                traintype: '',
                trainsubject: '',
                trainContent: '',
                certificateStartTime: '',
                certificateValidityTime: '',
                beginDate: '',
                endDate: '',
                result: '',
                createTime: '',
                updateTime: '',
                flag: '',
            },);
        },
        getuploadList: function ($event, item) {
            console.log($event);
            item.certificateFilepath = $event;
        },
        getitem: function (item) {

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
            let baseUrl = 'http://192.168.0.200:38080';
            let id = item.id;
            self.$http.httpDelete('/app/api/physical/' + id)
                .then(function (response) {
                    console.log(response)
                    self.cancelform(item);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        wtach: {
            'formValidate.trainingRecords': function (newval) {
                console.log(newval);
            }
        },

    }

</script>
