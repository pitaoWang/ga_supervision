<style>
    .topolit a{
        color:#2196F3;
        text-decoration:underline;
    }
    .userlistsubmit-btn {
        padding-left: 45px;
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
</style>
<template>
    <!--<Table :columns="columns6" :data="tableData" size="small" ref="table" :stripe="true"></Table>-->
    <!--<table class='m_changeList' style="width: 700px   ; border: 1px solid #0e0e0e;">-->
        <!--<thead >-->
        <!--<tr>-->
            <!--<th height="48px">用户名</th>-->
            <!--<th height="48px">账号</th>-->
            <!--<th  height="48px">角色</th>-->
            <!--<th height="48px">组织名称</th>-->
        <!--</tr>-->
        <!--</thead>-->
        <!--<tbody>-->
        <!--<tr v-for="item in userlist" style="font-size: 14px;padding-left: 13px;width: 700px; border-bottom: solid 1px rgb(14, 14, 14); background-color:#3d3d3d;">-->
            <!--<td  height="48px" align="center" class="topolit"><router-link :to="{path:'/for_pilot',query: {pilotId: item.pilotId}}">{{item.username}}</router-link></td>-->
            <!--<td height="48px" align="center">{{item[3]}}</td>-->
            <!--<td  height="48px" align="center">{{item[2]}}</td>-->
            <!--<td  height="48px" align="center">{{item[5]}}</td>-->
            <!--<td  height="48px" align="center">{{item[4]}}</td>-->
        <!--</tr>-->
        <!--</tbody>-->
    <!--</table>-->
    <div style="width: 800px;margin: auto;">
         <span style="width:100%;display:inline-block;text-align:right;">
                    <h2 style="float:left;font-size: 18px">用户列表</h2>
                    <Button type="primary" @click="add_dialog_show=true" icon="plus"
                            style="width:150px;margin-right:10px;margin-bottom: 10px" size="small">增  加</Button>
                </span>
        <hr style="width: 100%;margin-bottom: 10px">
        <div style="border: 1px solid  white;margin-bottom: 10px">
            <Form :label-width="80">
                <Row style="height: 54px;margin-top: 15px;">
                <Col span="8">
                <FormItem label="用户名">
                    <Input ></Input>
                </FormItem>
                </Col>
                <Col span="8" style="margin-left: 50px">
                <FormItem label="企业名称">
                    <Input   ></Input>
                </FormItem>
                </Col>
                    <Col >

                        <a data-v-942377fa="" href="javascript:;" @click="" class="userlistsubmit-btn">查询</a>

                    </Col>
                </Row>
            </Form>
        </div>
    <Table :data="tableData1" :columns="columns6" ></Table>
    <div style="margin: 10px;overflow: hidden">
        <div style="float: right;">
            <Page :total="100" :current="1" @on-change="changePage"></Page>
        </div>
    </div>

        <Modal v-model="add_dialog_show" width="800" title="新增用户"
                :styles="{top: '20px'}">
            <createnewuser :tempaccount="tempaccount"></createnewuser>
            <div slot="footer" style="height: 30px;">
            </div>
        </Modal>
    </div>
</template>
<script>
        import createnewuser from './createnewuser.vue';

    export default {
        components: {
            createnewuser
        },
        data () {
            return {
                tableData1:[],
                columns6:[
                    {
                        "title": "用户名",
                        "key": "username",
                        "fixed": "left",


                    },
                    {
                        "title": "账号",
                        "key": "account",
                        "fixed": "left",


                    },
                    {
                        "title": "角色",
                        "key": "role",
                        "fixed": "left",


                    },
                    {
                        "title": "组织名称",
                        "key": "orgname",
                        "fixed": "left",


                    },
                    {
                        title: '操作',
                        key: 'action',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'text',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }, '查看'),
                                h('Button', {
                                    props: {
                                        type: 'text',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }, '编辑')
                            ]);
                        }
                    }

                ],
                tableData: [],
                employee_id:0,
                keyword: '',
                page: 1,
                pageSize: 50,
                loading: false,
                add_dialog_show: false,
                tempaccount:''
            }
        },
        created(){
            this.getData();
        },
        methods: {
            updateCheck:function (index) {
                this.tempaccount=this.tableData1[index].account;
                this.add_dialog_show=true;
            },
            changePage:function () {
                this.getData();
            },
            showmodel:function (){
                this.isdiv=true;
            },
            closemodelchild:function (msg) {
                this.isdiv=false;
            },
            mouseoutcolor:function (event) {
                event.target.parentNode.style.background="#353535"
            },
            mouseovercolor:function (event) {
                event.target.parentNode.style.background="#afafaf"
            },
            previous () {
                if (this.page === 1) {
                    this.$message('已是第一页');
                } else {
                    this.page--
                }
            },
            next () {
                this.page++
            },
            getData :function () {
                let self = this;
                let dataara = [];
                self.$profile.getProfile().then((profile)=>{
                        let orgid=profile.org.id;
                self.$http.httpGet('/eim/api/user/search/listbyorgid', {
                    orgid: 39,
                    page: self.page,
                    pageSize: self.pageSize,
                }).then((res) => {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        let temp = result.data;
                        for(let i=0;i< temp.length;i++){
                            dataara.push({
                                username:temp[i][3],
                                account:temp[i][2],
                                role:temp[i][5],
                                orgname:temp[i][4]
                            })
                        }
                        console.log(dataara);
                        self.tableData1=dataara;

                    }
                    else {
                        console.log(result.message)
                    }

                    self.loading = false;
                })
                }, (error) => {
                    console.log('error', error);
                });
            },

        },

    }
</script>

<style scoped>

</style>
