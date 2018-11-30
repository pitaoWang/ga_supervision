<template>
    <div class="container_div">
        <Form ref="gacompany" :model="gacompany" inline :label-width="70" style="">
           <!-- <FormItem label="企业名称" style="width:400px;float: left;margin-left: 0px;" prop="companyName">
                <Input v-model="gacompany.companyId" placeholder="请输入企业名称"></Input>
            </FormItem>-->
            <FormItem label="企业名称" style="width:350px;float: left;margin-left: 0px;" prop="companyId" >
                <Select v-model="gacompany.companyId" filterable ref="select">
                    <Option v-for="item in companys" :value="item.companyId" :key="item.index">{{ item.name }}</Option>
                </Select>
            </FormItem>
            <FormItem label="法人代表" style="width:350px;float: left;margin-left: 0px;" prop="legalPerson">
                <Input v-model="gacompany.legalPerson" placeholder="请输入法人代表"></Input>
            </FormItem>

            <FormItem style="width:100px; height: 20px; float: left;margin-left: -56px;">
                <Button type="primary" @click="inint">查询</Button>
            </FormItem>
            <FormItem style="width:100px;height: 20px; float: left;margin-left: -32px; ">
                <Button type="primary" @click="reset('gacompany')">重置</Button>
            </FormItem>
            <FormItem style="width:100px;height: 20px; float: left;margin-left: -32px; ">
                <Button type="primary" @click="exportData()">
                    <Icon type="ios-download-outline"></Icon>
                    导出数据
                </Button>
            </FormItem>
        </Form>
        <div style="padding-top: 60px;">
            <Table  :columns="columns" :data="data" ref="table" @on-row-dblclick="dbDetails($event)"
                   style="width: 100%"></Table>
            <Page :total="totalPages"  @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize"
                  style="height: 50px;float:right;margin-top: 20px;margin-right: -10px;" show-sizer show-total></Page>
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie';

    export default {
        name: "companysuper",
        data() {
            return {
                columns: [
                    {
                        title: '企业名称',
                        key: 'name',
                        align: 'center',
                        width: 300,
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
                                        title: params.row.name
                                    }
                                }, params.row.name)
                            ]);

                        }
                    },

                    {
                        title: '主运行基地',
                        key: 'mainBase',
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
                                        title: params.row.mainBase
                                    }
                                }, params.row.mainBase)
                            ]);

                        }
                    },
                    {
                        title: '运行合格证类型',
                        key: 'businessScope',
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
                                        title: params.row.businessScope
                                    }
                                }, params.row.businessScope)
                            ]);

                        }
                    },
                    {
                        title: '法定代表人',
                        key: 'legalPerson',
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
                                        title: params.row.legalPerson
                                    }
                                }, params.row.legalPerson)
                            ]);

                        }
                    },
                    {
                        title: '企业联系电话',
                        key: 'telephone',
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
                                        title: params.row.telephone
                                    }
                                }, params.row.telephone)
                            ]);

                        }
                    },
                    {
                        title: '经营项目',
                        key: 'businessType',
                        align: 'center',
                        render: (h, params) => {
                            let array = params.row.businessType.split(',');
                            let array1 = [];
                            for (let i = 0; i < array.length; i++) {
                                if (array[i] == 1) {
                                    array[i] = '甲类'
                                }
                                if (array[i] == 2) {
                                    array[i] = '乙类'
                                }
                                if (array[i] == 3) {
                                    array[i] = '丙类'
                                }
                                if (array[i] == 4) {
                                    array[i] = '丁类'
                                }
                                array1.push(array[i]);
                            }
                            let texts;
                            texts = array1.join(',')
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
                                    },
                                    props: {},
                                }, texts)
                            ]);
                        }
                    },
                ],
                data: [],
                companys:[],
                page: 0,
                pageSize: 10,
                //keyword: '',
                totalPages: '',
                gacompany: {
                    companyId: '',
                    legalPerson: '',
                }
            }
        },
        methods: {
            inint: function () {
                let self = this;
                let url = '';
                let data = {};

                url = '/eim/api/company/search/listbycomids';
                data.pageno = self.page;
                data.pagesize = self.pageSize;
                //data.supervisionId=profile.org.orgId;
                //data.supervisionId='10000000690920'
                let datas = JSON.parse(Cookies.get("profile"));
                data.companyIds = datas.buz.companyId
                data.companyId = self.gacompany.companyId;
                if(typeof data.companyId=='undefined'){
                    data.companyId=''
                }
                data.legalPerson = self.gacompany.legalPerson;
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
                self.findselect()
            },
            findselect(){
                let self = this;
                let url='';
                let data={};
                let datas =JSON.parse(Cookies.get("profile"));
                url='/eim/api/company/search/listbycomids1';
                data.companyIds=datas.buz.companyId;
                //data.companyId = self.mmisillegalflight.companyId
                self.$http.httpGet(url,data).then(res=>{
                    self.companys = res.data;
                })
            },
            pageclick: function (obj) {
                this.page = obj - 1;
                this.inint();
            },
            pagesizeclick: function (obj) {
                this.pageSize = obj;
                this.inint();
            },
            reset: function (name) {
                this.$refs[name].resetFields();
                this.$refs.select.reset();

            },
            exportData: function () {
                this.$refs.table.exportCsv({
                    filename: '企业信息',
                });
            },
            dbDetails: function (column) {
                this.$router.push({path: '/suptabs', query: {gacompany: JSON.stringify(column)}})
            }
        },
        mounted() {
            this.inint();

        }
    }
</script>

<style scoped>

</style>