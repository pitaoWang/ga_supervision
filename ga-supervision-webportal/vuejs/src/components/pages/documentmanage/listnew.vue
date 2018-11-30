<template>
    <div class="container_div">
        <Table  :columns="columns7" :data="data" @on-row-dblclick="dbDetails($event)"></Table>
        <Page :total="totalPages"  @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize"
                  style="height: 50px;float:right;margin-top: 20px;margin-right: -10px;" show-sizer show-total></Page>

    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        name: "listnew",
        data(){
            return{
                data:[],
                page: 0,
                pageSize: 10,
                //keyword: '',
                totalPages: '',
                columns7: [
                    {
                        title: '发件人',
                        key: 'username',
                        align: 'center',
                        //width: 280
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
                                        title: params.row.username
                                    }
                                }, params.row.username)
                            ]);

                        }
                    },
                    {
                        title: '发件人所属单位',
                        //width: 240,
                        key: 'companyname',
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
                                        title: params.row.companyname
                                    }
                                }, params.row.companyname)
                            ]);

                        }
                    },
                    {
                        title: '主题',
                        key: 'title',
                        align: 'center',
                        //width: 420
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
                                        title: params.row.title
                                    }
                                }, params.row.title)
                            ]);

                        }
                    },
                    {
                        title: '发送时间',
                        //width: 200,
                        key: 'createtime',
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
                                        title: params.row.createtime
                                    }
                                }, params.row.createtime)
                            ]);

                        }
                    },
                    {
                        title: '状态',
                        //width: 200,
                        key: 'flag',
                        align: 'center',
                        render: (h, params) => {
                            let texts = params.row.flag;
                            if(texts==0){
                                texts ='已发送'
                            }

                            if(texts==1){
                                texts='已保存'
                            }

                            /*return h('div', {
                                props: {},
                            }, texts)*/
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
                                        title: texts
                                    }
                                }, texts)
                            ]);
                        }
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                /*h('Button', {
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
                                }, '查看'),*/
                                h(
                                    "Button",
                                    {
                                        props: {
                                            type: "error",
                                            size: "small"
                                        },
                                        style: {
                                            marginLeft: '4px',
                                            marginTop: '4px',
                                            width: '30px',
                                            height: '30px',
                                            background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                            border: 'none'
                                        },
                                        on: {
                                            click: () => {
                                                let index = params.index;
                                                let id = this.data[index].id;
                                                //alert(JSON.stringify(this.data[index]));
                                                /*this.deteteDatafile(datafileId, index);*/
                                                this.deleteList(id,index);
                                            }
                                        }

                                    },
                                    ""
                                )
                            ]);
                        }
                    }
                ],
            }
        },
        methods:{
            inint: function () {
                let self = this;
                let url = '';
                let data = {};

                //url = '/eim/api/doclist/search/geilistbycreator';
                url = '/eim/api/doclist/search/getListBySupervisionId';
                data.pageno = self.page;
                data.pagesize = self.pageSize;
                let datas = JSON.parse(Cookies.get("userInfo"));
                //data.employeeid = datas.employeeId
                data.superviseId = datas.companyId;
                self.$http.httpGet(url, data).then((res) => {
                    self.totalPages = res.data.totalElements;
                    self.data = res.data.content;

                }).catch(function (error) {
                    console.log(error);
                });
                /* },
                 (error) =>
                 {
                     console.log('error', error);
                 }
             )*/
                ;
            },
            pageclick: function (obj) {
                this.page = obj - 1;
                this.inint();
            },
            pagesizeclick: function (obj) {
                this.pageSize = obj;
                this.inint();
            },
            /*toview(index) {

                this.$router.push({
                    path: '/documentview',
                    query: {Id: this.data[index].id}
                });
            },*/
            dbDetails(column){
                this.$router.push({
                    path: '/documentview',
                    query: {Id: column.id}
                });
            },
            deleteList: function (id, index) {
                this.$http.httpDelete('/eim/api/doclist/'+id).then(response => {
                        this.data.splice(index, 1);
                    this.totalPages = this.totalPages-1;
                        this.$Message.info("删除成功")
                }).catch(function (error) {
                    console.log(error);

                });
            },
        },
        mounted(){
            this.inint()
        }
    }
</script>

<style scoped>

</style>