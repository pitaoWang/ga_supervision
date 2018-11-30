
<template>
    <div style="width: 1280px; height: 900px; margin-top: 20px;background:white">
        <div style="margin-top: 16px;margin-left: 9px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <button @click="add_dialog_show(('formvali'))" data-v-6cb0b875="" type="button"
                            class="ivu-btn ivu-btn-primary ivu-btn-small"
                            style="width: 133px; height: 32px; margin: 24px 56px 11px 0px; border: 1px dashed rgb(43, 130, 218); background: rgb(178, 214, 251); font-size: 14px; color: rgb(36, 128, 221);"><!----> <i
                            class="ivu-icon ivu-icon-plus"></i> <span>增  加</span></button>
                </span>
            <div class="bottom5" style="width: 870px;margin-left: 47px">
                <Table border :columns="columns7" :data="formValidate.workRecords"></Table>

            </div>
        </div>

        <Modal v-model="modal1" title="工作记录" width="700px" @on-ok="submit" @on-cancel="dealcancel" :styles="{top: '20px'}">


            <Form ref="formvali" :rules="rule" :model="workRecord" :label-width="100" style="height: 100%;">
                <FormItem style="display: none">
                    <Input v-model="workRecord.id"></Input>
                </FormItem>

                <FormItem class="bottom5" label="工作单位" prop="workUnit"
                          style="width: 50%;height: 34px;margin-bottom: 24px">
                    <Input v-model="workRecord.workUnit" placeholder="工作单位"></Input>
                </FormItem>

                <FormItem class="bottom5" label="工作时间" prop="workTime"
                          style="width: 50%;height: 34px;margin-bottom: 24px">
                    <Input v-model="workRecord.workTime" placeholder="工作时间"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="所任职位"  prop="post">
                    <Input v-model="workRecord.post" placeholder="所任职位"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="工作内容" prop="jobContent">
                    <Input v-model="workRecord.jobContent" placeholder="工作内容"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="证明人"
                          prop="witness">
                    <Input v-model="workRecord.witness" placeholder="证明人"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="证明人联系方式"
                          prop="proofContact">
                    <Input v-model="workRecord.proofContact" placeholder="证明人联系方式"></Input>
                </FormItem>

                <!--<div class="physicalpimg" style="float: right">
                    <word ref="abc" :itemflag=trainingRecord.certifactefile style="margin-top: 80px;" class="child"
                          v-on:click="getCheckform()"
                          v-on:listenTochildEvent="getuploadList($event,trainingRecord)"></word>
                </div>-->

            </Form>
        </Modal>
    </div>
</template>


<script>
    import IForm from "../../../../../node_modules/iview/src/components/form/form.vue";

    export default {
        data() {
            return {
                rule: {
                    workUnit: [{required: true, message: '工作单位不能为空', trigger: 'blur'}],
                    workTime: [{required: true, message: '工作时间不能为空', trigger: 'blur'}],
                    post: [{required: true, message: '所任职位不能为空', trigger: 'blur'}],
                    jobContent:[{required: true, message: '工作内容不能为空', trigger: 'blur'}],
                    /*beginDate: [{required: true, type: "date", message: '开始时间不能为空', trigger: 'change'}],
                    endDate: [{required: true, type: "date", message: '结束时间不能为空', trigger: 'change'}],*/
                    witness: [{required: true, message: '证明人不能为空', trigger: 'blur'}],
                    proofContact: [{required: true, message: '证明人联系方式不能为空', trigger: 'blur'}],
                },
                visible: false,
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                formValidate: {
                    workRecords: [
                        {
                            id: '',
                            employeeId: '',
                            workUnit: '',
                            workTime: '',
                            post: '',
                            jobContent: '',
                            witness: '',
                            proofContact: '',
                        },
                    ]

                },

                workRecord:
                    {
                        id: '',
                        employeeId: '',
                        workUnit: '',
                        workTime: '',
                        post: '',
                        jobContent: '',
                        witness: '',
                        proofContact: '',

                    },


                columns7: [
                    {
                        title: '工作单位',
                        key: 'workUnit',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '工作时间',
                        width: 100,
                        key: 'workTime',
                        align: 'center'
                    },
                    {
                        title: '所任职务',
                        width: 150,
                        key: 'post',
                        align: 'center'
                    },
                    {
                        title: '证明人',
                        width: 120,
                        key: 'witness',
                        align: 'center'
                    },
                    {
                        title: '证明人联系方式',
                        width: 120,
                        key: 'proofContact',
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
        props: ['employeeId'],

        components: {
            IForm,
        },
        created: function (newval) {
            this.init(this.employeeId);
        },

        methods: {
          /*  handleFormatError(file) {
                this.$Notice.warning({
                    title: '文件格式不正确',
                    desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
                });
            },*/
           /* handleMaxSize(file) {
                this.$Notice.warning({
                    title: '超出文件大小限制',
                    desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
                });
            },*/

          /*  handleSuccess(res, file) {
                this.$refs.abc.h4show = false;
                this.trainingRecord.certifactefile = res.url;
                this.$emit('listenTochildEvent', this.itemflag);
            },*/
           /* handleRemove(file) {
                this.$refs.abc.h4show = true;
                this.trainingRecord.certifactefile = '';

            },*/
            /*handleView(val) {
                this.visible = true;
            },*/
            dealcancel() {
                let self = this;
                self.workRecord = {};
                self.init(self.employeeId);
            },

            /*getCheckform() {
                return this.trainingRecord;
            },*/
           /* getuploadList: function ($event, trainingRecord) {
                console.log($event);
                trainingRecord.certifactefile = $event;
            },*/
           /* notnull(datainfo) {
                if (datainfo == '' || datainfo == null) {
                    alert("空的");
                }
            },*/
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
                self.formValidate.workRecords = [{}];
                self.$http.httpGet('/eim/api/workrecord/serach/getbyemployeeId', {
                    employeeId: id,
                }).then(function (response) {
                    console.log(response, '.................');
                    let datainfo = response.data;
                    self.formValidate.workRecords.splice(0, 1);
                    console.log(datainfo);
                    for (var i = 0; i < datainfo.length; i++) {
                        self.formValidate.workRecords.push(datainfo[i]);
                    }
                })
            },
            //添加框
            add_dialog_show: function (val) {
                //清除信息
                this.$refs[val].resetFields();
                this.modal1 = true;
            },
            //修改
            update_dialog_show: function (id) {
                let self = this;
                this.modal1 = true;
                self.$http.httpGet('/eim/api/workrecord/serach/getbyId', {
                    id
                }).then(function (response) {
                    self.workRecord = response.data;

                    self.init(self.employeeId);
                }).catch(function (error) {
                    console.log(error);
                });


            },
            //修改框
            updateCheck(index) {
                this.workRecord = this.formValidate.workRecords[index];
                /*if (this.workRecord.certifactefile == '') {
                    this.$refs.abc.h4show = true;
                }
                else {
                    this.$refs.abc.h4show = false;
                }*/
                this.$refs['formvali'].resetFields();
//                this.update_dialog_show(id);
   //             this.formValidate.trainingRecords[index].beginDate = new Date(this.formValidate.trainingRecords[index].beginDate);
   //             this.formValidate.trainingRecords[index].endDate = new Date(this.formValidate.trainingRecords[index].endDate);
                this.modal1 = true;
                this.init(this.employeeId);
            },
            //删除
            removeCheck(index) {
                let self = this;
                let id = self.formValidate.workRecords[index].id;
                self.$http.httpDelete('/eim/api/workrecord/' + id, {}
                ).then(function (response) {
                    alert("删除成功");
                    self.init(self.employeeId);
                }).catch(function (error) {
                    console.log(error);
                });
            },
            submit: function () {
                //调用子组件校验
                this.$refs.formvali.validate((valid) => {
                    let self = this;
                    if (valid) {
                        /*if ((self.trainingRecord.beginDate.valueOf()) > (self.trainingRecord.endDate.valueOf())) {
                            this.$Message.error('开始时间不能大于结束时间，请重新添加！');
                            return;
                        }*/
                        self.workRecord.employeeId = self.employeeId;
                        self.$http.jsonPost('/eim/api/workrecord', self.workRecord).then(function (response) {
                            console.log(response.data, '******************');
                            self.workRecord = {};
                            self.init(self.employeeId);
                        }).catch(function (error) {
                            console.log(error);
                            return;
                        });
                        alert("添加成功");
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
