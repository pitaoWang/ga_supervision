<template>
    <div style="width: 1280px; height: auto; background:white">
        <div style="margin-left: 9px;">
            <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                <Button type="primary" @click="add_dialog_show(('formvali'),pilotId)" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
            </span>
            <div class="bottom5" style="width: 1160px; margin-left: 10px">
                <Table border :columns="columns7" :data="formValidate.upRecords"></Table>
            </div>
        </div>

        <Modal v-model="modal1" title="更新检查记录" width="700px" @on-ok="submit" @on-cancel="dealcancel" :styles="{top: '20px'}">


            <Form ref="formvali" :rules="rule" :model="upRecord" :label-width="100" style="height: 100%;">
                <FormItem style="display: none">
                    <Input v-model="upRecord.id"></Input>
                </FormItem>

                <FormItem class="bottom5" label="更新检查记录标单号" prop="labelNo"
                          style="width: 50%;height: 34px;margin-bottom: 24px">
                    <Input v-model="upRecord.labelNo" placeholder="更新检查记录标单号"></Input>
                </FormItem>

                <FormItem class="bottom5" label="检查单位名称" prop="checkUnit"
                          style="width: 50%;height: 34px;margin-bottom: 24px">
                    <Input v-model="upRecord.checkUnit" placeholder="检查单位名称"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="更新检查设备类型"  prop="type">
                    <Input v-model="upRecord.type" placeholder="更新检查设备类型"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="设备型号" prop="equipmentModel">
                    <Input v-model="upRecord.equipmentModel" placeholder="设备型号"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="检查日期" prop="checkDate">
                    <DatePicker type="date" placeholder="检查日期" placement="bottom"
                                v-model="upRecord.checkDate"></DatePicker>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="检查时间" prop="checkTime">
                    <DatePicker type="date" placeholder="检查时间" placement="bottom"
                                v-model="upRecord.checkTime"></DatePicker>
                </FormItem>


                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="检查人"
                          prop="checkPerson">
                    <Input v-model="upRecord.checkPerson" placeholder="检查人"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="检查结果"
                          prop="checkResult">
                    <Input v-model="upRecord.checkResult" placeholder="检查结果"></Input>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="下次检查期限" prop="nextTerm">
                    <DatePicker type="date" placeholder="下次检查期限" placement="bottom"
                                v-model="upRecord.nextTerm"></DatePicker>
                </FormItem>

                <FormItem class="bottom5" style="width: 50%;height: 34px;margin-bottom: 24px" label="更新检查工作实践单"
                          prop="checkList">
                    <Input v-model="upRecord.checkList" placeholder="更新检查工作实践单"></Input>
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
                    labelNo: [{required: true, message: '更新检查记录标单号不能为空', trigger: 'blur'}],
                    checkUnit: [{required: true, message: '检查单位名称不能为空', trigger: 'blur'}],
                    type: [{required: true, message: '更新检查设备类型不能为空', trigger: 'blur'}],
                    equipmentModel:[{required: true, message: '设备型号不能为空', trigger: 'blur'}],
                    checkDate: [{required: true, type: "date", message: '检查日期不能为空', trigger: 'change'}],
                    checkTime: [{required: true, type: "date", message: '检查时间不能为空', trigger: 'change'}],
                    checkPerson: [{required: true, message: '检查人不能为空', trigger: 'blur'}],
                    checkResult: [{required: true, message: '检查结果不能为空', trigger: 'blur'}],
                    nextTerm:[{required: true, type: "date", message: '下次检查期限不能为空', trigger: 'change'}],
                    checkList: [{required: true, message: '更新检查工作实践单不能为空', trigger: 'blur'}],
                },
                visible: false,
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                formValidate: {
                    upRecords: [
                        {
                            id: '',
                            pilotId: '',
                            labelNo: '',
                            checkUnit: '',
                            type: '',
                            equipmentModel: '',
                            checkDate: '',
                            checkTime: '',
                            checkPerson:'',
                            checkResult:'',
                            nextTerm:'',
                            checkList:''
                        },
                    ]
                },

                upRecord:
                    {
                        id: '',
                        pilotId: '',
                        labelNo: '',
                        checkUnit: '',
                        type: '',
                        equipmentModel: '',
                        checkDate: '',
                        checkTime: '',
                        checkPerson:'',
                        checkResult:'',
                        nextTerm:'',
                        checkList:'',
                    },


                columns7: [
                    {
                        title: '更新检查记录标单号',
                        key: 'labelNo',
                        align: 'center',
                        width: 182
                    },
                    {
                        title: '检查单位名称',
                        width: 178,
                        key: 'checkUnit',
                        align: 'center'
                    },
                    {
                        title: '更新检查设备类型',
                        width: 168,
                        key: 'type',
                        align: 'center'
                    },
                    {
                        title: '设备型号',
                        width: 168,
                        key: 'equipmentModel',
                        align: 'center'
                    },
                    {
                        title: '检查日期',
                        width: 164,
                        key: 'checkDate',
                        align: 'center'
                    },
                    {
                        title: '检查人',
                        width: 178,
                        key: 'checkPerson',
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
                                }, '修改'),*/
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

                                /*h('Button', {
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
                            ]);
                        }
                    }
                ],


            }
        },
        // props: ['employeeId'],
        props:['pilotId'],
        components: {
            IForm,
        },
        created: function (newval) {
            this.init(this.pilotId);
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
                self.pcRecord = {};
                self.init(self.pilotId);
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
                self.formValidate.upRecords = [{}];
                self.$http.httpGet('/eim/api/updateCheckRecord/search/getbypilotId', {
                    pilotId: id,
                }).then(function (response) {
                    console.log(response, '.................');
                    let datainfo = response.data;
                    self.formValidate.upRecords.splice(0, 1);
                    console.log(datainfo);
                    for (var i = 0; i < datainfo.length; i++) {
                        self.formValidate.upRecords.push(datainfo[i]);
                    }
                })
            },
            //添加框
            add_dialog_show: function (val,pilotId) {
                //清除信息
                if(pilotId === '' || pilotId == null){
                    alert("所添加人员非飞行员，无法添加定期检查记录");
                    return false;
                }
                this.$refs[val].resetFields();
                this.modal1 = true;
            },
            //修改
            update_dialog_show: function (id) {
                let self = this;
                this.modal1 = true;
                self.$http.httpGet('/eim/api/updateCheckRecord/serach/getbyId', {
                    id
                }).then(function (response) {
                    self.upRecord = response.data;
                    self.init(self.pilotId);
                }).catch(function (error) {
                    console.log(error);
                });
            },

            //修改框
            updateCheck(index) {
                //alert('xxxx');
                this.upRecord = this.formValidate.upRecords[index];
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
                this.init(this.pilotId);
            },
            //删除
            removeCheck(index) {
                let self = this;
                let id = self.formValidate.upRecords[index].id;
                self.$http.httpDelete('/eim/api/updateCheckRecord/' + id, {}
                ).then(function (response) {
                    alert("删除成功");
                    self.init(self.pilotId);
                }).catch(function (error) {
                    console.log(error);
                });
            },
            submit: function () {
                //alert('xxxxxxxxxxxxx');
                //调用子组件校验
                this.$refs.formvali.validate((valid) => {
                    let self = this;
                    if (valid) {
                        /*if ((self.trainingRecord.beginDate.valueOf()) > (self.trainingRecord.endDate.valueOf())) {
                            this.$Message.error('开始时间不能大于结束时间，请重新添加！');
                            return;
                        }*/
                        self.upRecord.pilotId = self.pilotId;
                        self.$http.jsonPost('/eim/api/updateCheckRecord' , self.upRecord).then(function (response) {
                            self.upRecord = {};
                            self.init(self.pilotId);
                        }).catch(function (error) {
                            console.log(error);
                            return;
                        });
                        //alert("添加成功");
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
