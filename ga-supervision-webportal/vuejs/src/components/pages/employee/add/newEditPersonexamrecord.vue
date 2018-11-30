<template>
    <div style="width: 1280px; height:auto; margin-top: 20px;background:white">
        <div  style="margin-top: 16px;margin-left: 9px;">
                <!--<span style="width:100%;display:inline-block;text-align:right;">
                    <button @click="modalshow('formvali')" data-v-6cb0b875="" type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style="width: 133px; height: 32px; margin: 24px 56px 11px 0px; border: 1px dashed rgb(43, 130, 218); background: rgb(178, 214, 251); font-size: 14px; color: rgb(36, 128, 221);">&lt;!&ndash;&ndash;&gt; <i class="ivu-icon ivu-icon-plus"></i> <span>增  加</span></button>
                </span>-->
            <div class="bottom5" style="width: 870px;margin-left: 47px">
                <Table border :columns="columns7" :data="formValidate.personExamRecords"></Table>
            </div>

        </div>

    <Modal v-model="add_dialog_show" width="700px" title="考试记录" @on-ok="submitCheckForm" @on-cancel="dealcancel" :styles="{top: '20px'}">
        <Form :label-width="130" ref="formvali" :model="check_form" :rules="rule" style="height:100%;">
            <FormItem label="考试科目" prop="subject" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5">
                <Input v-model="check_form.subject"  placeholder="请输入考试科目"></Input>
            </FormItem>

            <FormItem label="考试成绩" prop="score" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                <Input v-model="check_form.score"  placeholder="请输入考试成绩"></Input>
            </FormItem>

            <FormItem label="是否通过" prop="passStatus" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                <Select v-model="check_form.passStatus" placeholder="请选择">
                    <Option value="通过">通过</Option>
                    <Option value="不通过">不通过</Option>
                </Select>
            </FormItem>

            <FormItem label="考试时间" prop="exameDate" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5">
                <DatePicker v-model="check_form.exameDate" type="date" placeholder="选择日期"></DatePicker>
            </FormItem>

            <FormItem label="考试地点" prop="examSite" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                <Input v-model="check_form.examSite":rows="4" placeholder="请输入考试地点"></Input>
            </FormItem>

            <FormItem label="检查员/考试员" prop="examTeacherId" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5">
                <Input v-model="check_form.examTeacherId":rows="4" placeholder="请输入监考老师姓名"></Input>
            </FormItem>

            <div class="physicalpimg" style="float: right">
                <word :itemflag=check_form.scoreFile style="margin-top: 0px;" class="child" v-on:click="getCheckform()" v-on:listenTochildEvent="getuploadList($event,check_form)" ></word>
            </div>
        </Form>
    </Modal>
    </div>
</template>
<script>
    import word from '../../company/uploadfile.vue';
    export default {
        components: {
            'word': word,
        },
        data() {
            return {
                rule:{
                    subject:[{required:true, message: '考试科目不能为空', trigger: 'blur'}],
                    score:[{pattern:/^[0-9]+([.]{1}[0-9]+){0,1}$/,message:'请输入数字',trigger:'blur'}],
                    passStatus:[{required:true,message:'合格状态不能为空',trigger:'change'}],
//                    exameDate:[{required: true,type:"date", message: '考试日期不能为空', trigger: 'change'}],
                    examSite:[{required: true, message: '考试地点不能为空', trigger: 'blur'}],
//                    examTeacherId:[{required:true,message:'监考老师不能为空',trigger:'blur'}],
                },
                columns7: [
                    {
                        title: '考试科目',
                        key: 'subject',
                        align: 'center',
                        width: 220
                    },
                    {
                        title: '是否通过',
                        width: 220,
                        key: 'passStatus',
                        align: 'center'
                    },
                    {
                        title: '考试地点',
                        width: 279,
                        key: 'examSite',
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
                    {
                        personExamRecords: [
                            {
                                id: '',
                                employeeid: '',
                                subject: '',
                                score: '',
                                exameDate: '',
                                examSite: '',
                                examTeacherId: '',
                                scoreFile:'',
                                createTime:'',
                                updateTime:'',
                                flag:'',
                            },
                        ]
                    },
                add_dialog_show: false,
                view_per:{},
                check_form: {
                    id: '',
                    employeeid: '',
                    subject: '',
                    score: '',
                    passStatus:'',
                    exameDate: null,
                    examSite: '',
                    examTeacherId: '',
                    scoreFile:'',
                    createTime:'',
                    updateTime:'',
                    flag:'',
                }
            }
        },
        props:['employeeId'],
        created() {

            this.getPersonExamRecords(this.employeeId)
        },
        methods: {
            modalshow(val){
                this.$refs[val].resetFields();
                this.add_dialog_show = true
            },
            getCheckform() {
                return this.check_form;
            },
            getuploadList: function ($event, check_form) {
                console.log($event);
                check_form.scoreFile = $event;
            },
            getPersonExamRecords(employeeId) {
                let self = this;
                self.formValidate.personExamRecords=[{}];
//                self.$profile.getProfile().then((profile) => {
//                        if (profile.buz && profile.buz.employeeId) {
//                            self.employeeId = profile.buz.employeeId;
                            self.$http.httpGet('/eim/api/personexam/serach/getbyemployeeId', {
                                employeeId:employeeId,
                            }).then(function (response) {
                                self.formValidate.personExamRecords = response.data;
                            }).catch(function (error) {
                                console.log(error);
                            });
//                        }
//                    },
//                    (error) => {
//                        self.$Message.error('user profile error = ' + error);
//                    });
            },
            submitCheckForm() {
                this.$refs.formvali.validate((valid) => {
                    let self = this;
                    let url = "/app/api";
                    let checkUrl = url + "personexam";
                    if(valid)
                    {
                        if (self.check_form.exameDate != null && self.check_form.exameDate!='') {

                            var d = new Date(self.check_form.exameDate);
                            self.check_form.exameDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                        };
                        self.check_form.employeeid = this.employeeId;
                        self.$http.jsonPost('/app/api/personexam', {
                            id: self.check_form.id,
                            employeeId: this.employeeId,
                            subject: self.check_form.subject,
                            score: self.check_form.score,
                            passStatus:self.check_form.passStatus,
                            exameDate: self.check_form.exameDate,
                            examSite: self.check_form.examSite,
                            examTeacherId: self.check_form.examTeacherId,
                            scoreFile: self.check_form.scoreFile,
                            createTime: self.check_form.createTime,
                            flag: 2,
                        }).then(function (res) {
                            let result = res.data;
                            console.log("result", result.resultCode)
                            if (result.resultCode == 200) {
                                self.check_form = {};
                                self.getPersonExamRecords(self.employeeId);
                            }
                            alert("保存成功");
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                    else{
                        self.getPersonExamRecords(self.employeeId);
                        this.$Message.error('表单验证失败!');
                        return;
                    }
                })
                },
            updateCheck(index) {
                this.$refs['formvali'].resetFields();
                this.check_form = this.formValidate.personExamRecords[index];
                this.add_dialog_show = true;
            },
            cancelform: function (index) {
                this.formValidate.personExamRecords.splice(index, 1);
                alert("删除成功")
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
                let url = "/app/api";
                let checkUrl = url + "/personexam/" + this.formValidate.personExamRecords[index].id;
                console.log("this.formValidate.personExamRecords[index]",this.formValidate.personExamRecords[index])
                console.log(checkUrl)
                self.$http.httpDelete(checkUrl, ).then(function (response) {
                    self.cancelform(index);
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            dealcancel(){
                this.add_dialog_show = false;
                this.check_form = {};
                this.getPersonExamRecords(this.employeeId)
            }
        },
    }
</script>
<style scoped>
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