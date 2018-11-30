<template>
    <div style="width: 1280px; height: auto; margin-top: 20px;background:white">
        <div  style="margin-top: 16px;margin-left: 9px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <button @click="addmodel('formvali')" data-v-6cb0b875="" type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style="width: 133px; height: 32px; margin: 24px 56px 11px 0px; border: 1px dashed rgb(43, 130, 218); background: rgb(178, 214, 251); font-size: 14px; color: rgb(36, 128, 221);"><!----> <i class="ivu-icon ivu-icon-plus"></i> <span>增  加</span></button>
                </span>
            <div class="bottom5" style="width: 870px;margin-left: 47px">
                <Table border :columns="columns7" :data="formValidate.certificateRecords"></Table>
            </div>
        </div>
        <Modal v-model="add_dialog_show" width="700"  title="新增培训证书记录" @on-ok="submitCheckForm"
               @on-cancel="dealcancel" :styles="{top: '20px'}">
            <Form :label-width="100" :model="trainingCertificate" style="height: 100%" ref="formvali" :rules="rule">
                <FormItem label="证书类型" style="width: 50%;margin-top: 12px;height: 34px;margin-bottom: 24px" class="bottom5" prop="certificatetype">
                    <Input v-model="trainingCertificate.certificatetype"  placeholder="请输入证书编号"></Input>
                </FormItem>

                <FormItem label="证书编号" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5" prop="certificateno">
                    <Input v-model="trainingCertificate.certificateno"  placeholder="请输入证书编号"></Input>
                </FormItem>

                <FormItem label="颁发单位" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5"  prop="issueoffice" >
                    <Input v-model="trainingCertificate.issueoffice" placeholder="请输入颁发单位"></Input>
                </FormItem>

                <FormItem label="颁发时间" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5"  prop="issuetime">
                    <DatePicker v-model="trainingCertificate.issuetime" type="date" placeholder="选择日期"></DatePicker>
                </FormItem>

                <FormItem label="有效时间" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5" prop="expiretime">
                    <DatePicker v-model="trainingCertificate.expiretime" type="date" placeholder="选择日期"></DatePicker>
                </FormItem>

                <div class="physicalpimg" style="float: right">
                    <word :itemflag="trainingCertificate.certificatefile" style="margin-top: 0px" class="child" v-on:click="getCheckform()" v-on:listenTochildEvent="getuploadList($event,trainingCertificate)" ></word>
                </div>
            </Form>
        </Modal>
    </div>
</template>
<script>
    import word from '../../company/uploadfile.vue';
    import employeedata from '../../../../data/employee/index'
    export default {
        components: {
            'word': word,
        },
        data() {
            return {
                rule: {
                    certificatetype: [{required: true, message: '证书类型不能为空', trigger: 'blur'}],
                    certificateno: [{required: true, message: '证书编号不能为空', trigger: 'blur'}],
                    issueoffice: [{required: true, message: '颁发单位不能为空', trigger: 'blur'}],
                    issuetime: [{required: true, type: "date", message: '颁发时间不能为空', trigger: 'change'}],
                    expiretime: [{required: true, type: "date", message: '有效时间不能为空', trigger: 'change'}],
                },
//                items:employeedata.certificatetype,
                columns7: [
                    {
                        title: '证书类型',
                        key: 'certificatetype',
                        align: 'center',
                        width: 140
                    },
                    {
                        title: '证书编号',
                        width: 149,
                        key: 'certificateno',
                        align: 'center'
                    },
                    {
                        title: '颁发单位',
                        width: 130,
                        key: 'issueoffice',
                        align: 'center'
                    },

                    {
                        title: '颁发时间',
                        key: 'issuetime',
                        align: 'center',
                        width: 150
                    },
                    {
                        title: '有效时间',
                        width: 150,
                        key: 'expiretime',
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
                                            this.confirm(params.index)
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],
                formValidate:
                    {certificateRecords:[
                        {
                            id:'',
                            certificatetype:'',
                            employeeid:'',
                            certificateno:'',
                            issueoffice:'',
                            issuetime: '',
                            expiretime: '',
                            certificatefile:''
                        },
                    ]
                    },
                add_dialog_show: false,
                trainingCertificate: {
                    id:'',
                    certificatetype:'',
                    employeeid:'',
                    certificateno:'',
                    issueoffice:'',
                    issuetime: '',
                    expiretime: '',
                    certificatefile:''
                   },
            }
        },
        props:['employeeId'],
        created() {
            this.getCertificateRecords(this.employeeId)
        },

        methods: {
            //添加框
            addmodel(val){
                //清除信息
                this.$refs[val].resetFields();
                this.add_dialog_show=true;
            },
            getCheckform() {
                return this.trainingCertificate;
            },
            getuploadList: function ($event, trainingCertificate) {
                console.log($event);
                trainingCertificate.certificatefile = $event;
            },
            getCertificateRecords(id) {
                let self = this;
                self.formValidate.certificateRecords=[{}];
                            self.$http.httpGet('/eim/api/trainingcertificate/serach/getbyemployeeId', {
                                  employeeId: id,
                            }).then(function (response) {
                                console.log(response);
                                let datainfo = response.data;
                                self.formValidate.certificateRecords.splice(0, 1);
                                console.log(datainfo);
                                for (var i = 0; i < datainfo.length; i++) {
                                    self.formValidate.certificateRecords.push(datainfo[i]);
                                }
                            }).catch(function (error) {
                                console.log(error);
                            });


            },

            submitCheckForm() {
                let self = this;
                self.$refs.formvali.validate((valid) => {
                    if(valid) {
                        let tc = self.trainingCertificate;
                        self.trainingCertificate.employeeId = self.employeeId
                        if ((tc.issuetime.valueOf()) > (tc.expiretime.valueOf())) {
                            this.$Message.error('有效期日期要比颁发日期大，请重新添加！');
                            return;
                        }

                            console.log("self.trainingCertificate.certificatefile", self.trainingCertificate)
                            self.$http.jsonPost('/app/api/trainingcertificate/savaTrainingCertificate', self.trainingCertificate).then(function (res) {
                                let result = res.data;
                                if (result.resultCode == 200) {
                                    console.log("start init")
                                    self.trainingCertificate = {};
                                    self.getCertificateRecords(self.employeeId);
                                }
                                self.getCertificateRecords(self.employeeId);
                                self.trainingCertificate = {};
                            }).catch(function (error) {
                                console.log(error);
                                return;
                            });
                            alert("添加成功");

                    }
                    else{
                        this.$Message.error('有信息未填写，表单验证失败!');
                        return;
                    }
                    })
            },

            updateCheck(index) {
                this.trainingCertificate = this.formValidate.certificateRecords[index];
                this.formValidate.certificateRecords[index].issuetime = new Date(this.formValidate.certificateRecords[index].issuetime);
                this.formValidate.certificateRecords[index].expiretime = new Date(this.formValidate.certificateRecords[index].expiretime);
                this.add_dialog_show = true;
                this.$refs['formvali'].resetFields();
                this.getCertificateRecords(this.employeeId);
            },
            cancelform: function (index) {
                this.formValidate.certificateRecords.splice(index, 1);
                alert("删除成功");
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
            removeCheck(index) {
                let self = this;
                let url = "/app/api/trainingcertificate";
                let checkUrl = url + "/deleteTrainingCertificateById/";
                let id=this.formValidate.certificateRecords[index].id;
                self.$http.httpDelete(checkUrl+id,{}).then(function (response) {
                    console.log(response)
                    self.cancelform(index);
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            dealcancel(){
                this.add_dialog_show = false;
                this.trainingCertificate={
                    id:'',
                    certificatetype:'',
                    employeeid:'',
                    certificateno:'',
                    issueoffice:'',
                    issuetime: '',
                    expiretime: '',
                    certificatefile:''
                };
                this.getCertificateRecords(this.employeeId);
            }
        }
    }
</script>
<style scoped>
    .gacompany-btn {
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
    .gacompany-div a:hover {
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
    .bottom5 {
        margin-bottom: 15px;
    }
    .child{
        position: absolute;
        top:100px;
        right: 30px;
        width: 283px;
    }
</style>