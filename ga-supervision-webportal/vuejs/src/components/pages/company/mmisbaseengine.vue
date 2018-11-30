<template>
    <div class="container_div">
        <Form ref="mmisengine" :model="mmisengine" inline :label-width="85" style="">
            <FormItem label="发动机型号" style="width:350px;float: left;margin-left: 0px;" prop="model">
                <Input v-model="mmisengine.model" placeholder=""></Input>
            </FormItem>
            <FormItem label="装机航空器" style="width:350px;float: left;margin-left: 0px;" prop="aircraftRegNo">
                <Input v-model="mmisengine.aircraftRegNo" placeholder=""></Input>
            </FormItem>
            <FormItem label="装机状态" style="width:350px;float: left;margin-left: 0px;" prop="equipmentStatus">
                <Select v-model="mmisengine.equipmentStatus" ref="select">
                    <Option value="1">未装机</Option>
                    <Option value="2">已装机</Option>
                </Select>
            </FormItem>

            <FormItem  style="width:100px; height: 20px; float: left;margin-left: -56px;">
                <Button type="primary" @click="inint">查询</Button>
            </FormItem>
            <FormItem  style="width:100px;height: 20px; float: left;margin-left: -32px; ">
                <Button type="primary" @click="reset('mmisengine')">重置</Button>
            </FormItem>
        </Form>
        <div style="padding-top: 60px;">
            <Table  :columns="columns" :data="data" ref="table" @on-row-dblclick="dbDetails($event)" style="width: 100%;"></Table>
            <Page :total="totalPages"  @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize" style="height: 50px;float:right;margin-top: 20px;margin-right: -10px;" show-sizer show-total ></Page>
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        name: "mmisbaseengine",
        data(){
            return{
                columns: [
                    {
                        title: '发动机型号',
                        key: 'model',
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
                                        title: params.row.model
                                    }
                                }, params.row.model)
                            ]);

                        }
                    },

                    {
                        title: '序号',
                        key: 'serial',
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
                                        title: params.row.serial
                                    }
                                }, params.row.serial)
                            ]);

                        }
                    },
                    {
                        title: '循环数寿命(次)',
                        key:'lifeCycleTime',
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
                                        title: params.row.lifeCycleTime
                                    }
                                }, params.row.lifeCycleTime)
                            ]);

                        }
                    },
                    {
                        title: '剩余循环数(次)',
                        key: 'remainCycleTime',
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
                                        title: params.row.remainCycleTime
                                    }
                                }, params.row.remainCycleTime)
                            ]);

                        }
                    },
                    {
                        title: '装机状态',
                        key: 'equipmentStatus',
                        align:'center',
                        render: (h, params) => {
                            let texts = '';
                            if (params.row.equipmentStatus == 1) {
                                texts = "未装机"
                            } else if (params.row.equipmentStatus == 2) {
                                texts = "已装机"
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
                        title: '装机日期',
                        key: 'equipmentDate',
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
                                        title: params.row.equipmentDate
                                    }
                                }, params.row.equipmentDate)
                            ]);

                        }
                    },
                ],
                data:[],
                page: 0,
                pageSize: 10,
                //keyword: '',
                totalPages:'',
                mmisengine:{
                    model:'',
                    aircraftRegNo:'',
                    equipmentStatus:'',
                }
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

                url='/eim/api/mmisengine/findbycompanyId';
                data.pageno=self.page;
                data.pagesize=self.pageSize;
                //data.supervisionId=profile.org.orgId;
                //data.supervisionId='10000000690920'
                let datas =JSON.parse(Cookies.get("profile"));
                data.companyIds=datas.buz.companyId
                data.model = self.mmisengine.model
                data.aircraftRegNo = self.mmisengine.aircraftRegNo
                data.equipmentStatus = self.mmisengine.equipmentStatus;
                if(typeof data.equipmentStatus=='undefined'){
                    data.equipmentStatus=''
                }
                /*  }*/
                self.$http.httpGet(url, data).then((res) => {
                    self.totalPages=res.data.totalElements;
                    self.data=res.data.content;
                }).
                catch(function (error) {
                    console.log(error);
                });
            },
            reset:function (name) {
                this.$refs[name].resetFields();
                this.$refs.select.reset();

            },
            dbDetails:function (column) {
                this.$router.push({path:'/mmisbaseenginedetail',query: {mmisbaseengine:column }})
            }
        },
        mounted(){
            this.inint();
        }
    }
</script>

<style scoped>

</style>