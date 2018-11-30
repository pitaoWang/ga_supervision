
<template>
    <div style="width: 1280px; height: auto; background:white">
        <div style="margin-left: 9px;">
            <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                <Button type="primary" @click="add_dialog_show(('formvali'))" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
            </span>
            <div class="bottom5" style="width: 1160px; margin-left: 10px">
                <Table border :columns="columns7" :data="formValidate.awardRecords"></Table>

            </div>
        </div>

        <Modal v-model="modal1" title="奖惩记录" width="350px" @on-ok="submit" @on-cancel="dealcancel" :styles="{top: '20px'}">
            <Form ref="formvali" :rules="rule" :model="awardRecord" :label-width="120" style="height: 100%;">
                <FormItem style="display: none">
                    <Input v-model="awardRecord.id"/>
                </FormItem>

                <FormItem style="display: none">
                    <Input v-model="awardRecord.employeeId"/>
                </FormItem>

                <FormItem class="bottom5" label="发生时间" prop="time"
                          style="width: 100%;height: 34px;">
                    <DatePicker type="date" placeholder="发生时间" placement="bottom"
                                v-model="awardRecord.time" style="width: 197px"></DatePicker>
                </FormItem>

                <FormItem class="bottom5" label="内容" prop="content"
                          style="width: 100%;height: 34px;">
                    <Input v-model="awardRecord.content" placeholder="内容"/>
                </FormItem>

                <FormItem class="bottom5" style="width: 100%;height: 34px;" label="所在单位"  prop="unit">
                    <Input v-model="awardRecord.unit" placeholder="所在单位"/>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>


<script>
    import IForm from "../../../../../node_modules/iview/src/components/form/form.vue";
    import Cookies from 'js-cookie';

    export default {
        data() {
            return {
                rule: {
                    /*time: [{required: true, message: '时间不能为空', trigger: 'blur'}],*/
                    content: [{required: true, message: '内容不能为空', trigger: 'blur'}],
                    unit: [{required: true, message: '所在单位不能为空', trigger: 'blur'}],

                },
                visible: false,
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                formValidate: {
                    awardRecords: [
                        {
                            id: '',
                            employeeId: '',
                            time:'',
                            content:'',
                            unit:'',
                        },
                    ]

                },

                awardRecord:
                    {
                        id: '',
                        employeeId: '',
                        time:'',
                        content:'',
                        unit:'',
                    },


                columns7: [
                    {
                        title: '时间',
                        key: 'time',
                        align: 'center',
                        width: 346
                    },
                    {
                        title: '内容',
                        width: 346,
                        key: 'content',
                        align: 'center'
                    },
                    {
                        title: '所在单位',
                        width: 346,
                        key: 'unit',
                        align: 'center'
                    },

                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
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
        },
        created: function (newval) {
            let buz = JSON.parse(Cookies.get("profile"));
            this.employeeId=buz.buz.employeeId;
            this.init(this.employeeId);
        },

        methods: {
            dealcancel() {
                let self = this;
                self.awardRecord = {};
                self.init(self.employeeId);
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
                self.formValidate.awardRecords = [{}];
                self.$http.httpGet('/eim/api/award/search/'+this.employeeId, {
                }).then(function (response) {
                    console.log(response, '.................');
                    let datainfo = response.data.data;
                    self.formValidate.awardRecords.splice(0, 1);
                    console.log(datainfo);
                    for (var i = 0; i < datainfo.length; i++) {
                        self.formValidate.awardRecords.push(datainfo[i]);
                    }
                })
            },
            //添加框
            add_dialog_show: function (val) {
                let self = this;
              /*  if(self.employeeId == null || self.employeeId===''){
                    alert('您尚未添加基础人员，请先添加基础人员');
                    return false;
                }*/
                //清除信息
                this.$refs[val].resetFields();
                this.modal1 = true;
            },
            //修改
            update_dialog_show: function (id) {
                let self = this;
                this.modal1 = true;
                self.$http.httpGet('/eim/api/record/serach/getbyid', {
                    id
                }).then(function (response) {
                    self.awardRecord = response.data;

                    self.init(self.employeeId);
                }).catch(function (error) {
                    console.log(error);
                });


            },
            //修改框
            updateCheck(index) {
                this.awardRecord = this.formValidate.awardRecords[index];
                this.$refs['formvali'].resetFields();
                this.modal1 = true;
               // this.init(this.employeeId);
            },
            //删除
            removeCheck(index) {
                let self = this;
                let id = self.formValidate.awardRecords[index].id;
                self.$http.httpDelete('/eim/api/record/delete/' + id, {}
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
                        self.awardRecord.employeeId = self.employeeId;
                        self.$http.jsonPost('/eim/api/award/save', self.awardRecord).then(function (response) {
                            self.awardRecord = {};
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
