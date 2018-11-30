<template>
    <div class="container_div">
        <Form ref="formItems" :model="formItem">
            <FormItem label="飞行服务站" :label-width="90" style="width:300px;float: left;" prop="fssId">
                <Select v-model="formItem.fssId" placeholder="请选择" >
                    <Option v-for="site in supervision" :key="site.id" :value="site.id">{{ site.name }}</Option>
                </Select>
            </FormItem>
            <FormItem label="姓名" :label-width="90" style="width:300px;float: left;margin-left: 40px;" prop="name">
                <Input v-model="formItem.name" placeholder="请输入姓名"></Input>
            </FormItem>
            <FormItem label="手机号码" :label-width="90" style="width:300px;float: left;margin-left: 40px;" prop="tel">
                <Input v-model="formItem.tel" placeholder="请输入手机号码"></Input>
            </FormItem>

            <FormItem  style="width:100px; height: 20px; float: left; margin-left: 40px;">
                <Button type="primary" @click="init(1)">查询</Button>
            </FormItem>
            <FormItem  style="width:100px;height: 20px; float: left; margin-left: -32px;">
                <Button type="primary" @click="reset">重置</Button>
            </FormItem>
        </Form>
        <div>
            <div>
                <span style="width:100%; display:inline-block;text-align:right;">
                    <!--<Button type="primary" @click="add_dialog_show()" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>-->
                    <Button type="primary" @click="down_dialog_show()" icon="plus" style="width: 98px;height: 32px;margin:-10px 0px 20px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">导 出</Button>
                </span>

                <div class="bottom5" >
                    <Table  :columns="columns7" :data="users" @on-row-dblclick="userinfo"></Table>
                </div>
                <Page :total="pagecount"show-total @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
            </div>
        </div>

    </div>
</template>

<script>
   /* import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";*/
    import Cookies from 'js-cookie';
    import axios from 'axios'
    import {getStore} from "../../../libs/storage";
    export default {
        data() {
            return {
                supervision:[],
                user:{},
                jobTitle: '',
                formItem: {
                    fssId:'',
                    name: '',
                    /*baseId:'',
                    techpos: '',*/
                    tel: ''
                },
                users: [],
                pagesize:10,
                pagecount:0,
                count:1,
                columns7: [
                    {
                        title: '姓名',
                        key: 'name',
                        align: 'center'
                    },
                    {
                        title: '民族',

                        key: 'nation',
                        align: 'center'
                    },
                    {
                        title: '职位',

                        key: 'position',
                        align: 'center'
                    },{
                        title: '手机号码',

                        key: 'tel',
                        align: 'center'
                    },{
                        title: '身份证号',

                        key: 'idcard',
                        align: 'center'
                    },{
                        title: '飞行服务站',

                        key: 'fssName',
                        align: 'center'
                    },
                ],
            }
        },
/*

        components: {
            IForm,
            'word': word,
        },
*/

        created: function (newval) {
            let self = this;
            let userss = JSON.parse(Cookies.get("userInfo"));
            var datas =JSON.parse(Cookies.get("profile"));
            var data=datas.buz;
            self.$http.httpGet('/eim/api/recordFssuser/search/byids', {
                type: data.type,
                companyId:userss.companyId,

            }).then(function (response) {

                let datainfo = response.data.data;

                self.supervision = datainfo;


            }).catch(function (error) {
                console.log(error);
            });
            this.init(1);
        },
        methods: {

            init: function (obj) {
                this.searchuser(obj);

            },

            //下载
            down_dialog_show: function () {
                let accessToken = getStore('accessToken');
                let self = this;
                let userss = JSON.parse(Cookies.get("userInfo"));
                var datas =JSON.parse(Cookies.get("profile"));
                var data=datas.buz;
                if(typeof(self.formItem.fssId) != "undefined"){
                    var status = self.formItem.fssId;
                }else{
                    var status = "";
                }

                axios({
                    method: 'post',
                    url: '/eim/api/recordFssuser/search/downEmployeeExcel',
                    params: {
                        type: data.type,
                        name:self.formItem.name,
                        companyId:userss.companyId,
                        fssId:status,
                        /* baseId:bes,*/
                        //jobTitle:self.jobTitle,
                        /*techpos:self.formItem.techpos,*/
                        tel:self.formItem.tel,
                    },
                    responseType: 'blob',
                    headers: {
                        'accessToken': accessToken,

                    }
                }).then(response => {
                    this.download(response)
                }).catch((error) => {

                })

              /*  self.$http.LocationHref('/eim/api/recordFssuser/search/downEmployeeExcel', {
                    type: data.type,
                    name:self.formItem.name,
                    companyId:userss.companyId,
                    fssId:status,
                    /!* baseId:bes,*!/
                    //jobTitle:self.jobTitle,
                    /!*techpos:self.formItem.techpos,*!/
                    tel:self.formItem.tel,
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
                link.setAttribute('download', '飞行服务站人员列表.xlsx')

                document.body.appendChild(link)
                link.click()
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
                if(typeof(self.formItem.fssId) != "undefined"){
                    var status = self.formItem.fssId;
                }else{
                    var status = "";
                }

                self.$http.httpGet('/eim/api/recordFssuser/search/findRecordFssuserByInfo',{
                    type: data.type,
                    name:self.formItem.name,
                    companyId:userss.companyId,
                    fssId:status,
                    /* baseId:bes,*/
                    //jobTitle:self.jobTitle,
                    /*techpos:self.formItem.techpos,*/
                    tel:self.formItem.tel,
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
                let userss = JSON.parse(Cookies.get("userInfo"));
                var datas =JSON.parse(Cookies.get("profile"));
                var data=datas.buz;
                if(typeof(self.formItem.fssId) != "undefined"){
                    var status = self.formItem.fssId;
                }else{
                    var status = "";
                }
                let buz = JSON.parse(Cookies.get("profile"));
                self.$http.httpGet('/eim/api/recordFssuser/search/findRecordFssuserByInfo',{
                        type: data.type,
                        name:self.formItem.name,
                        companyId:userss.companyId,
                        fssId:status,
                        /* baseId:bes,*/
                        //jobTitle:self.jobTitle,
                        /*techpos:self.formItem.techpos,*/
                        tel:self.formItem.tel,
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
                this.$refs['formItems'].resetFields();
            },
            userinfo:function (obj) {
                obj =JSON.stringify(obj);
                this.$router.push({path: '/viewFssuser',query:{recordFssuser:obj}});
            },
            cancel:function () {

            }

        }
    }
</script>

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