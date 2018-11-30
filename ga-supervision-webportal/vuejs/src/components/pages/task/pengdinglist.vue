<style scoped>
    .physicalp{
        margin-left: 47px;
        margin-right: 47px;
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

    .ivu-form .ivu-form-item-label{
        color:#b3b3b3;
        font-size: 14px;

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
            <img src="../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
            <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">事务处理-我的待办</span>
        </div>
        <div style="border: solid 1px rgba(1, 107, 217, 0.5);background: rgba(193, 220, 248, 0.5); position: absolute;bottom: 0px;top: 76px;width: 965px;right: 26px;"></div>
        <div style="position: relative;bottom:0px;">
            <Form :model="formItem" :label-width="119" style="padding: 28px 0 44px 0px;">
                <FormItem label="流程名称" style="width:386px;height: 20px;float: left;">
                    <Input v-model="formItem.processName" placeholder="请输入姓名" ></Input>
                </FormItem>
                <FormItem  style="height: 20px;float: left;margin-left: -98px;">
                    <Button type="primary" @click="init(1)" style="width:98px;height:33px">查询</Button>
                </FormItem>
            </Form>
            <div class="physicalp" >
                <div  style="margin-top:10px;">
                    <div class="bottom5" style="margin-top:5px;padding:10px;">
                        <Table border :columns="columns7" :data="pendlist"></Table>
                    </div>
                    <Page :total="pagecount" @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;padding-right:35px;" show-sizer ></Page>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";
    const typelist=['挂起','正在运行','结束','人工结束','草稿','撤销','驳回','追回','','','逻辑删除'];
    export default {
        data() {
            return {
                formItem: {
                    processName: ''
                },
                pagesize:10,
                pagecount:1,
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                pendlist: [],
                columns7: [
                    {
                        title: '流程名称',
                        width: 190,
                        key: 'processName',
                        align: 'center'
                    },
                    {
                        title: '创建人',
                        key: 'creator',
                        align: 'center',
                        width: 190
                    },
                    {
                        title: '创建时间',
                        width: 200,
                        key: 'createTime',
                        align: 'center'
                    },
                    {
                        title: '状态',
                        width: 150,
                        key: 'taskStatus',
                        align: 'center',
                        render (h, params) {
                            return typelist[params.row.taskStatus];
                        }
                    },
                    {
                        title: '操作',
                        key: 'action',
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
                                            this.task(params.row.id)
                                        }
                                    }
                                }, '办理')
                            ]);
                        }
                    }


                ],


            }
        },

        components: {
            IForm
        },
        created: function (newval) {
            this.init(1);
        },
        methods: {
            init: function (obj) {
                let self = this;
                self.$http.httpGet('/app/api/bpmtask/search/pengdinglist',{
                    pageSize:self.pagesize,
                    currentPage:obj,
                    processName:self.formItem.processName
                    }).then(function (response) {
                    let datainfo = response.data.data;
                    self.pendlist=datainfo.list;
                    self.pagecount=response.data.data.pageCount;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            pageclick:function (obj) {
            this.init(obj);
            },pagesizeclick:function (obj) {
            this.pagesize=obj;
            this.init(1);
            },
             //发起
            task(index) {
                this.$http.httpHref('/app/api/bpmtask/search/taskflowform',
                    {taskId:index}
                );
            }
        }
    }

</script>
