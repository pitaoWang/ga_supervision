<style scoped>
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

    <div style="width: 965px; margin-left: 12px;">
        <div style="background: #2e82dc;height: 36px;padding-top: 6px;margin: 22px 0px 3px 0px;padding: 6px 0 0 0;">
            <img src="../../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
            <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">用户管理</span>
        </div>
        <div style="border: solid 1px rgba(1, 107, 217, 0.5);background: rgba(193, 220, 248, 0.5); position: absolute;bottom: 0px;top: 76px;width: 965px;right: 26px;"></div>
           <div style="position: relative;bottom: 0px;">
            <Form :model="formItem" :label-width="100" style="padding: 28px 0 0 0;">
                <FormItem label="姓名" style="width:367px;float: left;">
                    <Input v-model="formItem.fullname" placeholder="请输入姓名"></Input>
                </FormItem>
                <FormItem label="帐号" style="width:367px;float: left;margin-left: 20px;">
                    <Input v-model="formItem.account" placeholder="请输入帐号"></Input>
                </FormItem>
                <FormItem  style="width:120px;height: 20px;float: left;margin-left: -32px;">
                    <Button type="primary" @click="init" style="width:98px;height:33px;background: #2c81da;">查询</Button>
                </FormItem>
            </Form>
            <div>
                <div  style="margin-top:10px;height:180px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <Button type="primary" @click="add_dialog_show()" icon="plus" style="width: 133px;height: 32px;margin:24px 43px 11px 0px;border: 1px dashed #2b82da;background: #b2d6fb;font-size: 14px;color: #2480dd;" size="small">增  加</Button>
                </span>

                    <div class="bottom5" style="margin-right: 43px;margin-left: 43px">
                        <Table border :columns="columns7" :data="users"></Table>
                    </div>
                    <Page :total="pagecount" @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
                </div>
            </div>
            <Modal
                    v-model="modal2"
                    title="修改密码"
                    @on-ok="oks()"
                    @on-cancel="cancel">
                <Form :model="user" :label-width="80"  style="padding-bottom: 1px;">
                    <FormItem  label="密码" style="" >
                        <Input type="password" v-model="user.password" placeholder="请输入" style="width: 320px;"></Input>
                    </FormItem>
                </Form>
            </Modal>
        </div>
    </div>
</template>


<script>
    import word from '../../company/uploadfile.vue';
    import IForm from "../../../../../node_modules/iview/src/components/form/form.vue";
    export default {
        data() {
            return {
                user:{},
                modal2:false,
                formItem: {
                    fullname: '',
                    account: ''
                },
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                users: [],
                pagesize:10,
                pagecount:'',
                columns7: [
                    {
                        title: '姓名',
                        key: 'fullname',
                        align: 'center',
                        width: 120
                    },
                    {
                        title: '帐号',
                        width: 120,
                        key: 'account',
                        align: 'center'
                    },
                    {
                        title: '所属部门',
                        width: 190,
                        key: 'orgName',
                        align: 'center'
                    },
                    {
                        title: '岗位类型',
                        width: 100,
                        key: 'postype',
                        align: 'center'
                    },

                    {
                        title: '操作',
                        key: 'action',
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [ h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '4px',
                                    width: '60px',
                                    background:'#1ca44d',
                                    border:'none'
                                },
                                on: {
                                    click: () => {
                                        this.userinfo(params.row)
                                    }
                                }
                                }, '查看'),
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '4px',
                                        width: '60px',  background: '#db9033',
                                        border: 'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.row)
                                        }
                                    }
                                }, '修改'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '4px',
                                        width: '60px',
                                        background: '#dc371c',
                                        border: 'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.confirm(params.row.userId);
                                        }
                                    }
                                }, '删除'),
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginLeft: '4px',
                                        width: '79px',
                                        background: '#2b82da',
                                        border: 'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.resetpwd(params.row.account)
                                        }
                                    }
                                }, '重置密码')
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
               this.searchuser(1);

            },
            //添加框
            add_dialog_show: function () {
                this.$router.push({path: '/adduser',query:{userfalg:1}});
            }, //修改框
            updateCheck(index) {
                this.$router.push({path: '/adduser',query:{userfalg:2,user:index}});
            },
            //删除
            removeCheck(index) {
                let self = this;
                self.$http.httpDelete('/app/api/sysuser/'+index, {
                    //id
                }).then(function (response) {
                    alert("删除成功");
                    self.init();
                }).catch(function (error) {
                    console.log(error);
                });
            },
            //添加
            submittraining: function () {
                //{ path: '/goods', component: goods }
                const router = new VueRouter();
                console.log(router);
                //this.router.go({name: 'user', params: {userId: 1}});

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
            },pageclick:function (obj) {
                this.searchuser(obj);
            },searchuser:function (obj) {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    self.$http.httpGet('/app/api/sysuser/search/byorgid',{
                        orgid:profile.org.orgId,
                        fullname:self.formItem.fullname,
                        account:self.formItem.account,
                        pageSize:self.pagesize,
                        currentPage:obj
                    }).then(function (response) {
                        let datainfo = response.data.data.list;
                        self.users=datainfo;
                        self.pagecount=response.data.data.pageCount;

                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },pagesizeclick:function (obj) {
                 this.pagesize=obj;
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    self.$http.httpGet('/app/api/sysuser/search/byorgid',{
                        orgid:profile.org.orgId,
                        fullname:self.formItem.fullname,
                        account:self.formItem.account,
                        pageSize:self.pagesize,
                        currentPage:1
                    }).then(function (response) {
                        let datainfo = response.data.data.list;
                        self.users=datainfo;
                        self.pagecount=response.data.data.pageCount;
                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },resetpwd:function (obj) {
                this.user.username=obj;
                this.modal2=true;
            },oks:function () {
                let self=this;
                self.$http.httpGet('/app/api/sysuser/update/resetpwd',{
                    password:self.user.password,
                    username:self.user.username
                }).then(function (response) {
                    if(response.data._status_code===200){
                        self.$Message.success('密码重置成功!');
                    }else if(response.data._status_code===403){
                        self.$Message.error('无权限重置密码!');
                    }else{
                        self.$Message.error('密码重置失败!');
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },userinfo:function (obj) {
                this.$router.push({path: '/userinfo',query:{user:obj}});
            },cancel:function () {

            }

        }
    }

</script>
