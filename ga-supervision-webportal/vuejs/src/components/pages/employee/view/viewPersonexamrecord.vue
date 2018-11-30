<template>
    <div style="width: 1280px; height: auto; background:white">
        <div  style="">
            <!--<span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                <Button type="primary" @click="modalshow('formvali')" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
            </span>-->
            <div class="bottom5" style="width: 1260px; margin-left: 10px">
                <Table  :columns="columns7" :data="formValidate.personExamRecords" @on-row-dblclick="info"></Table>
            </div>

        </div>

    <Modal v-model="add_dialog_show" width="700px" title="考试记录" @on-ok="dealcancel" @on-cancel="dealcancel" :styles="{top: '20px'}">
        <Form :label-width="130" ref="formvali" :model="check_form" :rules="rule" style="height:100%;">
            <FormItem label="考试科目" prop="subject" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5">
                <Input v-model="check_form.subject" readonly="true" placeholder="请输入考试科目"></Input>
            </FormItem>

            <FormItem label="考试成绩" prop="score" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                <Input v-model="check_form.score" readonly="true" placeholder="请输入考试成绩"></Input>
            </FormItem>

            <FormItem label="是否通过" prop="passStatus" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                <Select v-model="check_form.passStatus" placeholder="请选择">
                    <Option value="通过">通过</Option>
                    <Option value="不通过">不通过</Option>
                </Select>
            </FormItem>

            <FormItem label="考试时间" prop="exameDate" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5">
                <DatePicker v-model="check_form.exameDate"  type="date" placeholder="选择日期"></DatePicker>
            </FormItem>

            <FormItem label="考试地点" prop="examSite" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                <Input v-model="check_form.examSite":rows="4" readonly="true" placeholder="请输入考试地点"></Input>
            </FormItem>

            <FormItem label="检查员/考试员" prop="examTeacherId" style="width: 50%;height: 34px;margin-bottom: 24px" class="bottom5">
                <Input v-model="check_form.examTeacherId":rows="4" readonly="true" placeholder="请输入监考老师姓名"></Input>
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
    import Cookies from 'js-cookie';
    export default {
        props: ['employeeId','employeeData'],
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
                        //width: 290
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
                                        title: params.row.subject
                                    }
                                }, params.row.subject)
                            ]);

                        }
                    },
                    {
                        title: '是否通过',
                        //width: 290,
                        key: 'passStatus',
                        align: 'center',
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
                                        title: params.row.passStatus
                                    }
                                }, params.row.passStatus)
                            ]);

                        }
                    },
                    {
                        title: '考试地点',
                        //width: 290,
                        key: 'examSite',
                        align: 'center',
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
                                        title: params.row.examSite
                                    }
                                }, params.row.examSite)
                            ]);

                        }
                    },
                    {
                        title: '考试时间',
                        //width: 288,
                        key: 'exameDate',
                        align: 'center',
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
                                        title: params.row.exameDate
                                    }
                                }, params.row.exameDate)
                            ]);

                        }
                    },

                    /*{
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
                    }*/
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
        created() {
            /*let buz = JSON.parse(Cookies.get("profile"));
            this.employeeId=buz.buz.employeeId;*/
            this.getPersonExamRecords(this.employeeId)
        },
        methods: {
            modalshow(val){
                if(this.employeeId == null || this.employeeId === ''){
                    alert("您尚未添加人员基本信息，请先添加人员基本信息");
                    return false;
                }
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
                            self.$http.httpGet('/eim/api/personexam/serach/getbyemployeeIds', {
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
                    let url = "/eim/api";
                    let checkUrl = url + "personexam";
                    if(valid)
                    {
                        if (self.check_form.exameDate != null && self.check_form.exameDate!='') {

                            var d = new Date(self.check_form.exameDate);
                            self.check_form.exameDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                        };
                        self.check_form.employeeid = this.employeeId;
                        self.$http.jsonPost('/eim/api/personexam', {
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
            info(obj){
                /*this.$refs['formvali'].resetFields();
                this.check_form = obj;
                this.add_dialog_show = true;*/
                this.$router.push({path:'/viewPersonExamDetail',query: {obj:JSON.stringify(obj),employeeId:this.employeeId,employeeData:JSON.stringify(this.employeeData)}})
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
                let url = "/eim/api";
                let checkUrl = url + "/personexam/delete/" + this.formValidate.personExamRecords[index].id;
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