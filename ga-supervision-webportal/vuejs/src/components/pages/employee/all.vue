<template>
    <div class="container_div">
        <Form ref="formItem" :model="formItem" inline :label-width="70" style="">
            <div style="margin-bottom: -24px;">
                <FormItem label="姓名" :label-width="40" style="display: inline-block;width: 320px">
                    <Input v-model="formItem.userName" placeholder="请输入姓名"></Input>
                </FormItem>
            <FormItem label="通航企业" style="display: inline-block;width: 320px">
                <!--<Input v-model="formItem.companyName" placeholder="请输入企业名称"></Input>-->
                <Select v-model="formItem.companyId" placeholder="请选择">
                    <Option v-for="item in company" :key="item.companyId" :value="item.companyId">
                        {{item.name}}
                    </Option>
                </Select>
            </FormItem>
            <FormItem label="人员类型" style="display: inline-block;width: 320px">
                <!--<Input v-model="formItem.jobTitle" placeholder="请选择"></Input>-->
                <Select v-model="formItem.jobTitle" placeholder="请选择用户类型">
                    <Option v-for="te in Technicalpost" :value="te.Technicalname">
                        {{te.Technicalid}}
                    </Option>
                </Select>
            </FormItem>
            </div>
            <div style="display: inline-block;">
                <FormItem label="职位" :label-width="40" style="display: inline-block;width: 320px">
                    <Input v-model="formItem.techpos" placeholder="请输入职位"></Input>
                </FormItem>
            <FormItem label="手机号码" style="display: inline-block;width: 320px">
                <Input v-model="formItem.homeMobile" placeholder="请输入手机号码"></Input>
            </FormItem>


                <FormItem label="身份证号" style="display: inline-block;width: 320px">
                    <Input v-model="formItem.cardId" placeholder="请输入身份证号"></Input>
                </FormItem>
            </div>
           <!-- <FormItem  style="width:100px; height: 20px; float: left; margin-left: -56px;">
                <Button type="primary" @click="init(1)">查询</Button>
            </FormItem>
            <FormItem  style="width:100px;height: 20px; float: left; margin-left: -32px;">
                <Button type="primary" @click="reset">重置</Button>
            </FormItem>-->
            <div style="display: inline-block;margin-left: 20px" >
                <span>
                    <!--<Button type="primary" @click="add_dialog_show()" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>-->
                    <Button type="primary" @click="init(1)" icon="search" style="width: 70px;height: 32px;margin:24px 15px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">查询</Button>
                    <Button type="primary" @click="reset" icon="reset" style="width: 70px;height: 32px;margin:24px 15px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">重置</Button>
                    <Button type="primary" @click="down_dialog_show()" icon="" style="width: 70px;height: 32px;margin:24px 15px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">导 出</Button>
                </span>
            </div>
        </Form>
        <div>
            <div  >
               <!-- <span style="width:100%; display:inline-block;text-align:right; margin-top: -30px">
                    &lt;!&ndash;<Button type="primary" @click="add_dialog_show()" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>&ndash;&gt;
                    <Button type="primary" @click="down_dialog_show()" icon="plus" style="width: 98px;height: 32px;margin:24px 42px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">导 出</Button>
                </span>-->

                <!--<div class="bottom5" style="width:100%">-->
                    <Table  :columns="columns7"  :data="users" @on-row-dblclick="userinfo" ></Table>
                <!--</div>-->
                <Page :total="pagecount" @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;margin-top: 20px;margin-right: -10px;border-right: 0px" show-sizer show-total ></Page>
            </div>
        </div>

    </div>
</template>

<script>
    /*import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";*/
    import Cookies from 'js-cookie';
    import axios from 'axios'
    import {getStore} from "../../../libs/storage";
    export default {
        data() {
            return {
                company:[],
                supervision:[],
                user:{},
                jobTitle: '',
                formItem: {
                    companyId:'',
                    userName: '',
                    jobTitle:'',
                    techpos: '',
                    homeMobile: '',
                    cardId:'',
                },
                Technicalpost: [{Technicalname: '飞行人员', Technicalid: '飞行人员'},{Technicalname: '管制人员', Technicalid: '管制人员'},{Technicalname: '维修人员', Technicalid: '维修人员'},
                    {Technicalname: '气象人员', Technicalid: '气象人员'},{Technicalname: '情报人员', Technicalid: '情报人员'},{Technicalname: '飞行签派人员', Technicalid: '飞行签派人员'},
                    {Technicalname: '行政人员', Technicalid: '行政人员'}],
                users: [],
                pagesize:10,
                pagecount:0,
                count:1,
                columns7: [
                    {
                        title: '姓名',
                        key: 'userName',
                        align: 'center',
                        //width: 180
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
                                        title: params.row.userName
                                    }
                                }, params.row.userName)
                            ]);

                        }
                    },
                    {
                        title: '人员类型',
                        //width: 180,
                        key: 'jobTitle',
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
                                        title: params.row.jobTitle
                                    }
                                }, params.row.jobTitle)
                            ]);

                        }
                    },
                    {
                        title: '职位',
                        //width: 212,
                        key: 'techpos',
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
                                        title: params.row.techpos
                                    }
                                }, params.row.techpos)
                            ]);

                        }
                    },{
                        title: '手机号码',
                        //width: 170,
                        key: 'homeMobile',
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
                                        title: params.row.homeMobile
                                    }
                                }, params.row.homeMobile)
                            ]);

                        }
                    },{
                        title: '身份证号',
                        //width: 220,
                        key: 'cardId',
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
                                        title: params.row.cardId
                                    }
                                }, params.row.cardId)
                            ]);

                        }
                    },{
                        title: '工作单位',
                        //width: 230,
                        key: 'companyName',
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
                                        title: params.row.companyName
                                    }
                                }, params.row.companyName)
                            ]);

                        }
                    },
                ],
            }
        },

     /*   components: {
            IForm,
            'word': word,
        },*/
        props: ['jobTitle'],
        created: function (newval) {
            let self=this;
            var datas =JSON.parse(Cookies.get("profile"));
            var data=datas.buz;
            var id = data.companyId;
            self.$http.httpGet('/eim/api/company/search/byids', {
                companyId: id,
            }).then(function (response) {
                let datainfo = response.data;
                self.company = datainfo;
            }).catch(function (error) {
                console.log(error);
            });
            this.init(1);
        },
        watch: {
            jobTitle:function (newvals) {
                let self = this;
              self.jobTitle = newvals;
            }
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
            init: function (obj) {
                this.searchuser(obj);

            },
            //添加框
            add_dialog_show: function () {
                let user = JSON.parse(Cookies.get("userInfo"));
                this.$router.push({path: '/adduser',query:{userfalg:1,user:user,employee:{}}});

            },
            //下载
            down_dialog_show: function () {
                let accessToken = getStore('accessToken');
                let self = this;
                if(typeof(self.formItem.baseId) != "undefined"){
                    var bes = self.formItem.baseId;
                }else{
                    var bes = "";
                }
                let buz = JSON.parse(Cookies.get("profile"));
                axios({
                    method: 'post',
                    url: '/eim/api/employee/search/downEmployeeExcel',
                    params: {
                        companyId: buz.buz.companyId,
                        baseId:bes,
                        userName:self.formItem.userName,
                        jobTitle:self.jobTitle,
                        techpos:self.formItem.techpos,
                        homeMobile:self.formItem.homeMobile
                    },
                    responseType: 'blob',
                    headers: {
                        'accessToken': accessToken,

                    }
                }).then(response => {
                    this.download(response)
                }).catch((error) => {

                })

                   /* self.$http.LocationHref('/eim/api/employee/search/downEmployeeExcel', {
                        companyId: buz.buz.companyId,
                        baseId:bes,
                        userName:self.formItem.userName,
                        jobTitle:self.jobTitle,
                        techpos:self.formItem.techpos,
                        homeMobile:self.formItem.homeMobile
                    }).then(function (response) {
                    }).catch(function (error) {
                        console.log(error);
                    });*/


               },
            download (data) {
                if (!data) {
                    return
                }
                let url = window.URL.createObjectURL(new Blob([data]))
                let link = document.createElement('a')
                link.style.display = 'none'
                link.href = url
                link.setAttribute('download', '人员列表.xlsx')

                document.body.appendChild(link)
                link.click()
            },
            updateCheck(obj) {
                //alert(obj.employeeId);
                //alert(obj.jobTitle);
                let self = this;
                if(obj.jobTitle === '飞行人员'){
                    var id = obj.employeeId;
                    self.$http.httpGet('/eim/api/pilot/search/byEmployeeId', {
                        id,
                    }).then(function (response) {
                        let pilot = response.data.data;
                        //alert(obj.supervisionId);
                        self.$router.push({path: '/editEmployee',query:{employee:obj,pilot:pilot}});
                    }).catch(function (error) {
                        console.log(error);
                    });
                }else if(obj.jobTitle === '管制人员'){
                    var id = obj.employeeId;
                    self.$http.httpGet('/eim/api/controlEmpl/search/byEmployeeId', {
                        id,
                    }).then(function (response) {
                        let control = response.data.data;
                        self.$router.push({path: '/editEmployee',query:{employee:obj,control:control}});
                    }).catch(function (error) {
                        console.log(error);
                    });
                }else {
                    self.$router.push({path: '/editEmployee',query:{employee:obj}});
                }
            },
            //删除
            removeCheck(index) {

                let self = this;
                self.$http.httpDelete('/eim/api/employee/'+index, {
                    //id
                }).then(function (response) {
                    alert("删除成功");
                    self.init(self.count);
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
           pageclick:function (obj) {
                 this.count = obj;
                this.searchuser(obj);
            },

            searchuser:function (obj) {
                let self = this;
                let userss = JSON.parse(Cookies.get("userInfo"));
                var datas =JSON.parse(Cookies.get("profile"));
                var data=datas.buz;
                var id;

                if(typeof (this.formItem.jobTitle)=='undefined'){
                    this.formItem.jobTitle='';
                }
                if(this.formItem.companyId==''||typeof (this.formItem.companyId)=='undefined'){
                    id = data.companyId;
                }else{
                    id=self.formItem.companyId;
                };
                    self.$http.httpGet('/eim/api/employee/search/findEmployeeByInfo',{
                        companyId: id,
                        userName:self.formItem.userName,
                        /*jobTitle:self.jobTitle,*/
                        jobTitle:self.formItem.jobTitle,
                        techpos:self.formItem.techpos,
                        homeMobile:self.formItem.homeMobile,
                        cardId:self.formItem.cardId,
                        pageSize:self.pagesize,
                        currentPage:obj
                    }).then(function (response) {
                        let datainfo = response.data.data;
                        self.users=datainfo;
                        if(typeof (response.data.data[0])=="undefined"||response.data.data[0].total==null){

                            self.pagecount= 0;
                        }else{

                            self.pagecount=response.data.data[0].total;
                        }

                    }).catch(function (error) {
                        console.log(error);
                    });
            },pagesizeclick:function (obj) {

                this.pagesize=obj;
                let self = this;

                if(typeof(self.formItem.baseId) != "undefined"){
                    var bes = self.formItem.baseId;
                }else{
                    var bes = "";
                }
                let buz = JSON.parse(Cookies.get("profile"));
                    self.$http.httpGet('/eim/api/employee/search/findEmployeeByInfo',{
                        companyId: buz.buz.companyId,
                        userName:self.formItem.userName,
                        baseId:bes,
                        jobTitle:self.jobTitle,
                        techpos:self.formItem.techpos,
                        homeMobile:self.formItem.homeMobile,
                        pageSize:self.pagesize,
                        currentPage:1
                    }).then(function (response) {

                        let datainfo = response.data.data;
                        self.users=datainfo;
                        if(typeof (response.data.data[0])=="undefined"||response.data.data[0].total==null){

                            self.pagecount= 0;
                        }else{

                            self.pagecount=response.data.data[0].total;
                        }

                    }).catch(function (error) {
                        console.log(error);
                    });

            },
            reset:function () {
                this.formItem={
                    companyId:'',
                    userName: '',
                    jobTitle:'',
                    techpos: '',
                    homeMobile: '',
                    cardId:'',
                }

            },
            userinfo:function (obj) {
                    //this.$router.push({path: '/pilot',query:{user:obj}});
                //this.$router.push({path: '/adduser',query:{user:obj}});
                obj =JSON.stringify(obj);
                this.$router.push({path: '/viewEmployee',query:{user:obj,t:'view'}});
            },
            cancel:function () {

            }

        }
    }
</script>

<style scoped>
    table{
        border:0px;
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