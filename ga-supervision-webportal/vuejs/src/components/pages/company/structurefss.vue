<template>
    <div class="container_div">
        <Form ref="structure" :model="structure" inline :label-width="110" style="">
            <FormItem label="飞行服务站名称" style="width:350px;float: left;margin-left: 0px;" prop="name">
                <Input v-model="structure.name" placeholder=""></Input>
            </FormItem>
            <FormItem label="飞行服务站编号" style="width:350px;float: left;margin-left: 0px;" prop="code">
                <Input v-model="structure.code" placeholder=""></Input>
            </FormItem>

            <FormItem  style="width:100px; height: 20px; float: left;margin-left: -56px;">
                <Button type="primary" @click="inint">查询</Button>
            </FormItem>
            <FormItem  style="width:100px;height: 20px; float: left;margin-left: -32px; ">
                <Button type="primary" @click="reset('structure')">重置</Button>
            </FormItem>
        </Form>
        <div style="padding-top: 60px;">
            <Table  :columns="columns" :data="data" ref="table" @on-row-dblclick="dbDetails($event)" style="width: 100%;"></Table>
            <Page :total="totalPages"   @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize" style="height: 50px;float:right;margin-top: 20px;margin-right: -10px;" show-sizer show-total ></Page>
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        name: "structurefss",
        data(){
            return{
                columns: [
                    {
                        title: '飞行服务站名称',
                        key: 'name',
                        align:'center',
                        width:300,
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
                        title: '飞行服务站编号',
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
                        title: '飞行服务站类型',
                        key:'type',
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
                                        title: params.row.type
                                    }
                                }, params.row.type)
                            ]);

                        }
                    },
                ],
                data:[],
                page: 0,
                pageSize: 10,
                //keyword: '',
                totalPages:'',
                structure:{
                    name:'',
                    code:'',
                },
            }
        },
        methods:{
            pageclick:function(obj){
                this.page = obj-1;
                this.inint();
            },
            pagesizeclick:function (obj) {
                this.pageSize=obj;
                this.inint();
            },
            inint(){
                let self = this;
                let url='';
                let data={};
                let datas =JSON.parse(Cookies.get("profile"));
                data.type=datas.buz.type;
                if(data.type==1){
                    url='/eim/api/structure/findbyadministrationId';
                    data.pageno=self.page;
                    data.pagesize=self.pageSize;
                    //data.supervisionId=profile.org.orgId;
                    let datas =JSON.parse(Cookies.get("userInfo"));
                    data.administrationId=datas.companyId;
                    data.name = self.structure.name
                    data.code = self.structure.code
                    /*  }*/
                    self.$http.httpGet(url, data).then((res) => {
                        self.totalPages=res.data.totalElements;
                        self.data=res.data.content;
                    }).
                    catch(function (error) {
                        console.log(error);
                    });
                }else if(data.type==2){
                    url='/eim/api/structure/findbysupervisionbureauId';
                    data.pageno=self.page;
                    data.pagesize=self.pageSize;
                    //data.supervisionId=profile.org.orgId;
                    let datas =JSON.parse(Cookies.get("userInfo"));
                    data.supervisionBureauId=datas.companyId;
                    data.name = self.structure.name
                    data.code = self.structure.code
                    /*  }*/
                    self.$http.httpGet(url, data).then((res) => {
                        self.totalPages=res.data.totalElements;
                        self.data=res.data.content;
                    }).
                    catch(function (error) {
                        console.log(error);
                    });
                }

            },
            reset:function (name) {
                this.$refs[name].resetFields();
            },
            dbDetails:function (column) {
                this.$router.push({path:'/structurefssdetail',query: {structure:JSON.stringify(column) }})
            }
        },
        mounted(){
            this.inint();

        }
    }
</script>

<style scoped>

</style>