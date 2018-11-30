<template>
    <div style="width: 1280px; height: auto; background:white">
        <div  style="margin-left: 9px;">
           <!-- <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                <Button type="primary" @click="modalshow('physicalvalidate')" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
            </span>-->
            <div class="bottom5" style="width: 1160px; margin-left: 10px">
                <Table border :columns="columns7" :data="formValidate.physicalRecords" @on-row-dblclick="info"></Table>
            </div>
        </div>
        <Modal v-model="add_dialog_show"  width="700" title="体检记录"
           @on-cancel="dealcancel" :styles="{top: '20px'}">
            <Form :label-width="100" ref="physicalvalidate" :model="check_form" :rules="rule" style="height:100%;">
                <FormItem label="合格证编号" prop="certificateNo" style="width: 50%;margin-top: 12px;height: 34px;margin-bottom: 24px" class="bottom5">
                    <Input v-model="check_form.certificateNo"  placeholder="请输入合格证编号"></Input>
                </FormItem>

                <FormItem label="合格证级别" prop="certificateGrade" style="width: 50%;margin-top: 12px;height: 34px;margin-bottom: 24px" class="bottom5">
                    <!--<Input v-model="check_form.certificateGrade"  placeholder="请输入合格级别"></Input>-->
                    <Select v-model="check_form.certificateGrade" placeholder="请选择" >
                        <Option value="Ⅰ级">Ⅰ级</Option>
                        <Option value="Ⅱ级">Ⅱ级</Option>
                        <Option value="Ⅲa级">Ⅲa级</Option>
                        <Option value="Ⅲb级">Ⅲb级</Option>
                        <Option value="Ⅳa级">Ⅳa级</Option>
                        <Option value="Ⅳb级">Ⅳb级</Option>
                    </Select>
                </FormItem>

                <FormItem label="合格状态" prop="passStatus" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                    <Select v-model="check_form.passStatus" placeholder="请选择">
                        <Option value="0">通过</Option>
                        <Option value="1">不通过</Option>
                    </Select>
                </FormItem>
                <FormItem label="颁发单位" prop="issueUnit" style="width: 50%;margin-top: 12px;height: 34px;margin-bottom: 24px" class="bottom5">
                    <Input v-model="check_form.issueUnit"  placeholder="请输入颁发单位"></Input>
                </FormItem>

                <FormItem label="颁发时间" prop="certificateStartTime"  style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                    <DatePicker v-model="check_form.certificateStartTime"  placeholder="选择日期" style="width: 235px"></DatePicker>
                </FormItem>

                <FormItem label="截止时间" prop="certificateValidityTime" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                    <DatePicker v-model="check_form.certificateValidityTime" type="date" placeholder="选择日期" style="width: 235px"></DatePicker>
                </FormItem>

                <FormItem label="备注" prop="remark" style="width: 50%;height: 34px;margin-bottom: 24px"class="bottom5">
                    <Input v-model="check_form.remark":rows="4" placeholder="请输入备注信息"></Input>
                </FormItem>
                <div class="physicalpimg" style="float: right;">
                    <word :itemflag=check_form.certificateFilepath style="margin-top: 0px;" class="child" v-on:click="getCheckform()" v-on:listenTochildEvent="getuploadList($event,check_form)" ></word>
                </div>
        </Form>
    </Modal>
    </div>
</template>
<script>
    import word from '../../company/uploadfile.vue';
    import Cookies from 'js-cookie';
    export default {
        components: {
            'word': word,
        },
        data() {
            return {
                rule:{
                    certificateNo:[{required:true, message: '合格证编号不能为空', trigger: 'blur'}],
                    certificateGrade:[{required:true, message: '合格级别不能为空', trigger: 'blur'}],
                    passStatus:[{required:true,message:'合格状态不能为空',trigger:'change'}],
                    issueUnit:[{required:true,message:'颁发单位不能为空',trigger:'change'}],
                    certificateStartTime:[{required: true,type:"date", message: '颁发日期不能为空', trigger: 'change'}],
                    certificateValidityTime:[{required: true,type:"date",message: '截止日期不能为空', trigger: 'change'}],
                },
                columns7: [
                    {
                        title: '合格证编号',
                        key: 'certificateNo',
                        align: 'center',
                        width: 202
                    },
                    {
                        title: '合格证级别',
                        key: 'certificateGrade',
                        align: 'center',
                        width: 198
                    },
                    {
                        title: '合格状态',
                        width: 198,
                        key: 'passStatus',
                        align: 'center'
                    },
                    {
                        title: '颁发单位',
                        width: 198,
                        key: 'issueUnit',
                        align: 'center'
                    },

                    {
                        title: '颁发时间',
                        key: 'certificateStartTime',
                        align: 'center',
                        width: 180
                    },
                    {
                        title: '截止时间',
                        width: 182,
                        key: 'certificateValidityTime',
                        align: 'center'
                    },

                    /*{
                        title: '操作',
                        key: 'action',
                        width:120,
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
                    {physicalRecords:[
                        {
                            id:'',
                            employeeid:'',
                            certificateGrade: '',
                            certificateStartTime: '',
                            certificateValidityTime: '',
                            passStatus:'',
                            remark: '',
                            certificateFilepath: '',
                            createTime:'',
                            updateTime:'',
                            flag: '',
                        },
                        ]
                    },
                add_dialog_show: false,
                check_form: {
                    id:'',
                    employeeid:'',
                    certificateNo:'',
                    certificateGrade: '',
                    issueUnit:'',
                    certificateStartTime: '',
                    certificateValidityTime: '',
                    passStatus:'',
                    remark: '',
                    certificateFilepath: '',
                    createTime:'',
                    updateTime:'',
                    flag: '',},
            }
        },
        props:['employeeId'],

        mounted(){
            alert(this.employeeId);
            this.getPhysicalrecords(this.employeeId);
        },

        methods: {
            info(obj){
                this.$refs['physicalvalidate'].resetFields();
                this.check_form = obj;
                if(this.check_form.passStatus == "通过")
                {
                    this.check_form.passStatus = "0";
                }
                else
                {
                    this.check_form.passStatus = "1";
                }
                /*this.formValidate.physicalRecords[index].certificateStartTime = new Date(this.formValidate.physicalRecords[index].certificateStartTime);
                this.formValidate.physicalRecords[index].certificateValidityTime = new Date(this.formValidate.physicalRecords[index].certificateValidityTime);*/
                this.add_dialog_show = true;
            },
            modalshow(val){

                this.$refs[val].resetFields();
                this.add_dialog_show = true
            },
            getData(){
              for(var i=0;i<this.formValidate.physicalRecords.length;i++){
                    if(this.formValidate.physicalRecords[i].passStatus == 0)
                    {
                        this.formValidate.physicalRecords[i].passStatus = "通过";
                    }
                    else
                        {
                            this.formValidate.physicalRecords[i].passStatus = "不通过";
                        }
                }
            },
            getCheckform() {
                return this.check_form;
            },
            getuploadList: function ($event, check_form) {
                console.log($event);
                check_form.certificateFilepath = $event;
            },
            getPhysicalrecords(employeeId) {
                let self = this;
                self.formValidate.physicalRecords=[{}];
                            self.$http.httpGet('/eim/api/physical/serach/getbyemployeeids', {
                                employeeId:employeeId,
                            }).then(function (response) {
                                self.formValidate.physicalRecords = response.data;
                                self.getData();
                            }).catch(function (error) {
                                console.log(error);
                            });


//                    (error) => {
//                        self.$Message.error('user profile error = ' + error);
//                    });
            },
            submitCheckForm() {
                let self = this;
                this.$refs.physicalvalidate.validate((valid) => {
                    let self = this;
                    let url = "/eim/api";
                    let checkUrl = url + "physical";
                    if (valid) {
                        self.check_form.employeeid = this.employeeId;
                        console.log(self.check_form.certificateStartTime.valueOf(), "and,", self.check_form.certificateValidityTime.valueOf())
                        if (self.check_form.certificateStartTime.valueOf() > self.check_form.certificateValidityTime.valueOf()) {
                            self.$Message.error('生效时间不能大于到期时间！');
                            self.getPhysicalrecords(self.employeeId);
                            return false;
                        }
                        else {
                            if (self.check_form.certificateStartTime != null) {

                                var d = new Date(self.check_form.certificateStartTime);
                                self.check_form.certificateStartTime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                            };
                            if (self.check_form.certificateValidityTime != null) {

                                var d = new Date(self.check_form.certificateValidityTime);
                                self.check_form.certificateValidityTime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                            };
                            console.log("createTime", self.check_form.createTime)
                            self.$http.jsonPost('/eim/api/physical', {
                                id: self.check_form.id,
                                employeeId: this.employeeId,
                                certificateNo:self.check_form.certificateNo,
                                issueUnit:self.check_form.issueUnit,
                                certificateGrade: self.check_form.certificateGrade,
                                certificateStartTime: self.check_form.certificateStartTime,
                                certificateValidityTime: self.check_form.certificateValidityTime,
                                passStatus: self.check_form.passStatus,
                                remark: self.check_form.remark,
                                certificateFilepath: self.check_form.certificateFilepath,
                                createTime: self.check_form.createTime,
                                flag: 2,
                            }).then(function (res) {
                                let result = res.data;
                                console.log("result", result.resultCode)
                                if (result.resultCode == 200) {
                                    alert("保存成功");
                                    self.check_form = {};
                                    self.getPhysicalrecords(self.employeeId);
                                }else{
                                    alert("保存失败")
                                }
                            }).catch(function (error) {
                                console.log(error);
                            });
                        }

                    }
                    else {
                        self.getPhysicalrecords(self.employeeId);
                        this.$Message.error('表单验证失败!');
                        return;
                    }

                })
            },
            updateCheck(index) {
                this.$refs['physicalvalidate'].resetFields();
                this.check_form = this.formValidate.physicalRecords[index];
                if(this.check_form.passStatus == "通过")
                {
                    this.check_form.passStatus = "0";
                }
                else
                {
                    this.check_form.passStatus = "1";
                }
                this.formValidate.physicalRecords[index].certificateStartTime = new Date(this.formValidate.physicalRecords[index].certificateStartTime);
                this.formValidate.physicalRecords[index].certificateValidityTime = new Date(this.formValidate.physicalRecords[index].certificateValidityTime);
                this.add_dialog_show = true;
            },
            cancelform: function (index) {
                this.formValidate.physicalRecords.splice(index, 1);
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
                let checkUrl = url + "/physical/delete/" + this.formValidate.physicalRecords[index].id;
                console.log("this.formValidate.physicalRecords[index]",this.formValidate.physicalRecords[index])
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
                this.getPhysicalrecords(this.employeeId)
            }
        },
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