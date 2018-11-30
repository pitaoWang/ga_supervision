<template>
    <div class="main_div">
        <div style="width:100%; float: left">
            <div>
                <div class="bottom5" >
                    <Table  :columns="columns9" :data="companyqualmessageList" ></Table>
                </div>
                <Page :total="pagecount" show-total  @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
            </div>
        </div>

    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        name: "companyqualmessage",
        data() {
            return {
                //permTypes:[],
                page:1,
                pageSize: 10,
                pagecount:0,
                companyqualmessageList: [],
                columns9:[
                    {
                        title: '资质名称',
                        key: 'qualificationname',
                        align:'center',
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
                                        title: params.row.qualificationname
                                    }
                                }, params.row.qualificationname)
                            ]);

                        }
                    },
                    {
                        title: '证书编号',
                        key: 'code',
                        align:'center',
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
                                        title: params.row.code
                                    }
                                }, params.row.code)
                            ]);

                        }
                    },
                    {
                        title: '生效日期',
                        key: 'starttime',
                        align:'center',
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
                                        title: params.row.starttime
                                    }
                                }, params.row.starttime)
                            ]);

                        }
                    },
                    {
                        title: '失效日期',
                        key: 'endtime',
                        align:'center',
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
                                        title: params.row.endtime
                                    }
                                }, params.row.endtime)
                            ]);

                        }
                    },
                    {
                        title: '颁发单位',
                        key: 'issueorgname',
                        align:'center',
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
                                        title: params.row.issueorgname
                                    }
                                }, params.row.issueorgname)
                            ]);

                        }
                    },
                    {
                        title: '合格证类型',
                        key: 'operateType',
                        align:'center',
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
                                        title: params.row.operateType
                                    }
                                }, params.row.operateType)
                            ]);

                        }
                    },
                ],
            }
        },

        created(){
            this.init(1);
        },
        methods:{
            init: function () {
                this.getConfigureList(1);
            },
            pageclick:function (obj) {
                this.page = obj;
                this.getConfigureList(obj);
            },
            getConfigureList:function (no) {
                let self = this;
                let profile = JSON.parse(Cookies.get("profile"));
                //   let buz = JSON.parse(Cookies.get("profile"));
                self.$http.httpGet('/eim/api/message/findRecordByCompanyId',{
                    companyIds: profile.buz.companyId,
                    page:no,
                    pageSize: self.pageSize
                }).then(function (response) {
                    let datainfo = response.data.content;
                    self.companyqualmessageList=datainfo;

                    self.pagecount=response.data.totalElements;
                    self.page=no;

                }).catch(function (error) {
                    console.log(error);
                });
            },
            pagesizeclick:function (size) {
                this.pageSize = size;
                this.getConfigureList(1);
            },
        },

    }
</script>

<style scoped>

</style>