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

    .form{
        width: 900px;
        background-color: #363636
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
        <span style="width: 100%">

            <div style="width: 30%">
                企业名称<Input  v-model="selectorg.name" placeholder="企业名称" ></Input>
            </div>
            <div style="width: 30%">
                企业类型
                <Select v-model="selectorg.orgtype" placeholder="请选择"  >
                    <Option value="0">--请选择--</Option>
                    <Option value="1">监管局</Option>
                    <Option value="2">管理局</Option>
                    <Option value="3">通航企业</Option>
                    <Option value="5">通航机场</Option>
                    <Option value="6">维修企业</Option>

                </Select>
            </div>
            <div style="width: 10%">
                <Button type="primary" icon="ios-search" @click="search_org()">搜索</Button>
            </div>
        </span>
        <div class="physicalp" style="height: 500px">
            <div  style="margin-top:10px;height:180px;">
                <span style="width:100%;display:inline-block;text-align:right;">

                    <Button type="primary" @click="add_dialog_show()" icon="plus"
                            style="width:150px;margin-right:10px;" size="small">增  加</Button>
                </span>

                <div class="bottom5" style="margin-top:5px;padding:10px;">
                    <Table border :columns="columns7" :data="formValidate.orgs"></Table>
                </div>

            </div>
        </div>

        <Modal
                v-model="modal1"
                title="培训信息"
                width="400px"
                @on-ok="submittraining"
                @on-cancel="cancel">

            <Form class="form"  ref="formValidate" :model="formValidate" :label-width="80" style="width: 350px;height: 350px;" >
                <!--<div class="everyf"  v-model="formValidate.orgs">-->
                <FormItem style="display: none">
                    <Input    v-model="org.id" ></Input>
                </FormItem>

                <FormItem  label="企业名称" :key="index" :prop="'items.' + index + '.qualificationname'" :rules="  { required: true, message: '培训类型不能为空', trigger: 'blur' }">
                    <Input  v-model="org.name" placeholder="企业名称" ></Input>
                </FormItem>

             <!--   <FormItem  label="企业类型" :key="index" :prop="'items.' + index + '.qualificationname'" :rules="  { required: true, message: '培训科目不能为空', trigger: 'blur' }">
                    <Input  v-model="org.trainSubject" placeholder="企业类型"></Input>
                </FormItem>-->

                <FormItem  label="企业类型" :key="index" :prop="'items.' + index + '.qualificationname'" :rules="  { required: true, message: '培训科目不能为空', trigger: 'blur' }">

                    <Select v-model="org.orgtype" placeholder="请选择"  >

                        <Option value="1">监管局</Option>
                        <Option value="2">管理局</Option>
                        <Option value="3">通航企业</Option>
                        <Option value="5">通航机场</Option>
                        <Option value="6">维修企业</Option>

                    </Select>
                </FormItem>


                <FormItem style="height: 34px" label="组织电话" :key="index" :prop="'items.' + index + '.issueorgname'" >
                    <Input  v-model="org.telephone" placeholder="企业名称"></Input>
                </FormItem>


                <FormItem style="height: 34px" label="状态" :key="index" :prop="'items.' + index + '.issueorgname'" >
                    <!--<Input  v-model="org.result" placeholder="状态"></Input>-->

                    <Select v-model="org.delFlag" placeholder="请选择"  >
                        <Option value="0">可用</Option>
                        <Option value="1">不可用</Option>


                    </Select>
                </FormItem>
                <!--</div>-->

            </Form>

        </Modal>
    </div>

</template>


<script>
    import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";
    export default {
        data() {
            return {
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                formValidate: {
                    orgs: [
                        {
                            id: '',
                            depth: '',
                            name: '',
                            orgtype: '',
                            jobtitle: '',
                            telephone: '',
                            sort: '',
                            parentids: '',
                            createTime: '',
                            updateTime: '',
                            delFlag: ''
                        },
                    ]

                },
                org:
                    {
                        id: '',
                        depth: '',
                        name: '',
                        orgtype: '',
                        jobtitle: '',
                        telephone: '',
                        sort: '',
                        parentids: '',
                        createTime: '',
                        updateTime: '',
                        delFlag: ''
                    },
                selectorg: {
                    name: '',
                    orgtype: ''
                },

                columns7: [
                    {
                        title: '企业名称',
                        key: 'name',
                        align: 'center',
                        width: 200
                    },
                    {
                        title: '企业类型',
                        width: 200,
                        key: 'orgtype',
                        align: 'center'
                    },
                    {
                        title: '组织电话',
                        width: 200,
                        key: 'telephone',
                        align: 'center'
                    },
                    {
                        title: '状态',
                        width: 200,
                        key: 'delFlag',
                        align: 'center'
                    },

                    {
                        title: '操作',
                        key: 'action',
                        width: 158,
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

        components: {
            IForm,
            'word': word,
        },
        created: function (newval) {
            // alert("进到这个");
            this.init();
        },

        methods: {
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
            init: function () {
                let self = this;
                self.formValidate.orgs = [{}];
                console.log("go to get search");
                self.$http.httpGet('/app/api/org').then(function (response) {

                    let datainfo = response.data.data;
                    self.formValidate.orgs.splice(0, 1);
                    console.log(datainfo, '*****datainfo******');
                    for (var i = 0; i < datainfo.length; i++) {
                        self.formValidate.orgs.push(datainfo[i]);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            //添加框
            add_dialog_show: function () {
                this.modal1 = true;
            },
            //修改并提交
            update_dialog_show: function (id) {
                let self = this;
                self.$http.httpGet('/app/api/org/findOrgById', {
                    id
                }).then(function (response) {
                    self.org = response.data;
                    //企业类型
                    if (self.org.orgtype == 1) {
                        self.org.orgtype = '1';
                    }
                    else if (self.org.orgtype == 2) {
                        self.org.orgtype = '2';
                    }
                    else if (self.org.orgtype == 3) {
                        self.org.orgtype = '3';
                    }
                    else if (self.org.orgtype == 5) {
                        self.org.orgtype = '5';
                    }
                    else if (self.org.orgtype == 6) {
                        self.org.orgtype = '6';
                    }
                    //状态
                    if (self.org.delFlag == 0) {
                        self.org.delFlag = '0';
                    }
                    else if (self.org.delFlag == 1) {
                        self.org.delFlag = '1';
                    }
                    //self.init();
                }).catch(function (error) {
                    console.log(error);
                });

                this.modal1 = true;

            },
            //修改框
            updateCheck(index) {
                // this.check_form = this.formValidate.orgs[index].id;
                let id = this.formValidate.orgs[index].id;
                this.update_dialog_show(id);

            },
            //删除
            removeCheck(index) {
                let self = this;
                let id = self.formValidate.orgs[index].id;
                self.$http.httpGet('/app/api/org/deleteOrgById', {
                    id
                }).then(function (response) {
                    self.init();
                }).catch(function (error) {
                    console.log(error);
                });
            },
            //添加
            submittraining: function () {
                //alert("来添加了。。。。");
                let self = this;

//                let baseUrl = 'http://192.168.0.200:38080';

                self.$http.jsonPost('/app/api/org/createCompleteOrg', self.org).then(function (response) {
                    console.log(response.data, '******************');
                    self.init();
                }).catch(function (error) {
                    console.log(error);
                });
                //添加完有缓存，将其清空
                self.org = '';
                alert("添加培训信息成功");

            },

            search_org: function () {
                let self = this;
                self.formValidate.orgs = [];
                self.$http.httpPost('/app/api/org/searchByOrgNameAndOrgType', self.selectorg).then(function (res) {
                    let datainfo = res.data.data;

//                    self.formValidate.orgs.splice(0, 1);
                    for (var i = 0; i < datainfo.length; i++) {
                        self.formValidate.orgs.push(datainfo[i]);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            addformdate: function () {
                this.formValidate.orgs.push({
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
                this.formValidate.orgs.splice(this.formValidate.orgs.indexOf(item), 1);
            },

            wtach: {
                'formValidate.orgs': function (newval) {
                    console.log(newval);
                }
            },

        }
    }

</script>
