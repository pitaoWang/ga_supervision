<style scoped>

     a:hover {
        color: #a09898;
        outline: 0;
    }

   .aclore{
       color: #439cf7;
     }
.redstr{
    border-bottom: solid 2px #3399ff;
}

</style>
<template>
    <div style="width: 1280px; height: 900px; margin-top: 40px; background:white;  padding-top: 40px">
        <div style="width: 850px;border-bottom: solid 2px white;margin-left: 40px; height: 35px;">
        <div style="font-size: 16px;display: block;float: left;width: 100px;text-align: center; height: 35px;"
             :class="{redstr:isred}" @click="getmyreciver()">
            <a class="aclore">我接收的</a>
        </div>
        <div @click="getmysend(0)" :class="{redstr:isredto}" style="font-size: 16px;text-align: center;width: 100px;float: left;height: 35px">
          <a class="aclore">我发送的</a>
        </div>
        </div>
    <div style="margin-left: 40px">
        <div style="margin-top:5px; width:1190px">
            <Table border :columns="columns7" :data="list"></Table>
        </div>
        <div style="overflow: hidden; margin-top:5px; width:1190px">
            <div style="float: right;margin-right: 10px;">
                <Page :total="pagecount" :current="currentpage" @on-change="changePage"></Page>
            </div>
        </div>
    </div>

    </div>
</template>
<script>
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                mangge:false,
                isred:true,
                isredto:false,
                pagecount:'',
                list:[],
                pageno:0,
                pagesize:10,
                columns7: [
                    {
                        title: '发件人',
                        key: 'username',
                        align: 'center',
                        width: 280
                    },
                    {
                        title: '发件人所属公司',
                        width: 240,
                        key: 'companyname',
                        align: 'center'
                    },
                    {
                        title: '发送时间',
                        width: 200,
                        key: 'createtime',
                        align: 'center'
                    },
                    {
                        title: '主题',
                        key: 'title',
                        align: 'center',
                        width: 420
                    },
//                    {
//                        title: '主题',
//                        key: 'title',
//                        align: 'center',
//                        width: 236
//                    },
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
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.toview(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
                pageflag:true
            }
        },
        created(){
            if(this.$route.query.defalg==1){
                this.getmysend(0);
            }else{
            this.$profile.getProfile().then((profile) => {
                    if (profile.buz && profile.buz.employeeId) {
                        let roles =profile.roles;
                        let companyId = profile.buz.companyId;
                        let jobtitle=profile.buz.jobtitle;
                        if(roles[0]==="operation_thqycy"){
                            this.getData(companyId,jobtitle,0);

                        }
                        else if(roles[0]==="operation_role_company_admin"){
                            this.getData1(companyId,"公司",0);
                            this.mangge=true;
                            this.columns7= [
                                {
                                    title: '发件人',
                                    key: 'superviseemployeename',
                                    align: 'center',
                                    width: 240
                                },
                                {
                                    title: '发件人所属公司',
                                    width: 240,
                                    key: 'supervisecompanyname',
                                    align: 'center'
                                },
                                {
                                    title: '发送时间',
                                    width: 160,
                                    key: 'createtime',
                                    align: 'center'
                                },
                                {
                                    title: '主题',
                                    key: 'title',
                                    align: 'center',
                                    width: 420
                                },
//                    {
//                        title: '主题',
//                        key: 'title',
//                        align: 'center',
//                        width: 236
//                    },
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
                                                    marginRight: '5px'
                                                },
                                                on: {
                                                    click: () => {
                                                        this.toview(params.index)
                                                    }
                                                }
                                            }, '查看'),

                                        ]);
                                    }
                                }
                            ]
                        }

                    }
                    else{
                        alert("获取profile失败")
                    }
                },
                (error) => {
                    this.$Message.error('user profile error = ' + error);
                });
//            if(this.$route.query.qualificationisshowfactor) {
//                this.qualificationisshowfactor=this.$route.query.qualificationisshowfactor;
//            }
            }
        },

        methods:{
            getmyreciver(){
                if(this.isred===true&&this.isred===false){
                    this.isred=false;
                    this.isredto=true;
                }else{
                    this.isred=true;
                    this.isredto=false;
                }
                this.$profile.getProfile().then((profile) => {
                    let companyId = profile.buz.companyId;
                this.getData1(companyId,"公司",0);
                this.mangge=true;
                this.columns7= [
                    {
                        title: '发件人',
                        key: 'superviseemployeename',
                        align: 'center',
                        width: 240
                    },
                    {
                        title: '发件人所属公司',
                        width: 240,
                        key: 'supervisecompanyname',
                        align: 'center'
                    },
                    {
                        title: '发送时间',
                        width: 160,
                        key: 'createtime',
                        align: 'center'
                    },
                    {
                        title: '主题',
                        key: 'title',
                        align: 'center',
                        width: 420
                    },
//                    {
//                        title: '主题',
//                        key: 'title',
//                        align: 'center',
//                        width: 236
//                    },
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
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.toview(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ]
                    },
                    (error) => {
                        this.$Message.error('user profile error = ' + error);
                    });
            },
            getData1 (val1,val2,val3) {
                let self = this;

                self.$http.httpGet('/app/api/doclist/search/findlistbycompanyidcompanymange', {
                    pageno: val3,
                    pagesize: 10,
                    companyId: val1,
                    jobTitle:val2
                }).then((res) => {
                    let result = res.data;
                    self.list=result.content;
                    self.pagecount=result.totalElements;

                })



            },
            changePage(val){
                if(this.pageflag) {
                    this.$profile.getProfile().then((profile) => {
                            if (profile.buz && profile.buz.employeeId) {
                                let roles = profile.roles;
                                let companyId = profile.buz.companyId;
                                let jobtitle = profile.buz.jobtitle;
                                if (roles[0] === "operation_thqycy") {
                                    this.getData(companyId, jobtitle, val - 1);

                                }
                                else if (roles[0] === "operation_role_company_admin") {
                                    this.getData1(companyId, "公司", val - 1);
                                }

                            }
                            else {
                                alert("获取profile失败")
                            }
                        },
                        (error) => {
                            this.$Message.error('user profile error = ' + error);
                        });
                }else{
                    this.getmysend(val-1);
                }
            },
            toview(index) {

                    this.$router.push({
                        path: '/documentview',
                        query: {Id: this.list[index].id}
                    });



            },

            getData (val1,val2,val3) {
                let self = this;

                    self.$http.httpGet('/app/api/doclist/search/findlistbycompanyid', {
                        pageno: val3,
                        pagesize: 10,
                        companyId: val1,
                        jobTitle:val2
                    }).then((res) => {
                        let result = res.data;
                        self.list=result.content;
                        self.pagecount=result.totalElements;

                    })



            },
            getmysend(val){
                if(this.isredto===true&&this.isredto===false){
                    this.isred=true;
                    this.isredto=false;
                }else{
                    this.isred=false;
                    this.isredto=true;
                }
                let self = this;
                self.pageflag=false;
                self.columns7=[
                    {
                        title: '发件人',
                        key: 'username',
                        align: 'center',
                        width: 180
                    },
                    {
                        title: '发件人所属公司',
                        width: 240,
                        key: 'companyname',
                        align: 'center'
                    },
                    {
                        title: '发送时间',
                        width: 180,
                        key: 'createtime',
                        align: 'center'
                    },
                    {
                        title: '主题',
                        key: 'title',
                        align: 'center',
                        width: 420
                    },
//                    {
//                        title: '主题',
//                        key: 'title',
//                        align: 'center',
//                        width: 236
//                    },
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
                                            this.toview(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
                /*self.$profile.getProfile().then((profile) => {*/
                    datasss = JSON.parse(Cookies.get("userInfo"));
                self.$http.httpGet('/eim/api/doclist/search/geilistbycreator', {
                    pageno: val,
                    pagesize: 10,
                    employeeid: datasss.employeeId,
                }).then((res) => {
                    let result = res.data;
                    self.list=result.content;
                    self.pagecount=result.totalElements;

                })
                   /* },
                    (error) => {
                        self.$Message.error('user profile error = ' + error);
                    });*/
            }

        }
    }
</script>