<template>
    <div class="container_div">
        <Form ref="mmisillegalflight" :model="mmisillegalflight" inline :label-width="70" style="">
            <FormItem label="违规单位" style="width:350px;float: left;margin-left: 0px;" prop="companyId" >
                <Select v-model="mmisillegalflight.companyId" filterable ref="select">
                    <Option v-for="item in companys" :value="item.companyId" :key="item.index">{{ item.name }}</Option>
                </Select>
            </FormItem>
            <FormItem label="航空器注册号" :label-width="100" style="width:350px;float: left;margin-left: 0px;" prop="aircraftRegNo">
                <Input v-model="mmisillegalflight.aircraftRegNo" placeholder=""></Input>
            </FormItem>
            <FormItem label="违规开始时间" :label-width="100" style="width:350px;float: left;margin-left: 0px;" prop="starttime">
                <DatePicker type="date" style="width:200px;" placeholder="选择日期" v-model="mmisillegalflight.starttime" ></DatePicker>
            </FormItem>

            <FormItem label="违规结束时间" :label-width="100" style="width:350px;float: left;margin-left: 0px;" prop="endtime">
                <DatePicker type="date" style="width:200px;" placeholder="选择日期" v-model="mmisillegalflight.endtime" ></DatePicker>
            </FormItem>
            <FormItem  style="width:100px; height: 20px; float: left;margin-left: -100px;">
                <Button type="primary" @click="inint">查询</Button>
            </FormItem>
            <FormItem  style="width:100px;height: 20px; float: left;margin-left: -32px; ">
                <Button type="primary" @click="reset('mmisillegalflight')">重置</Button>
            </FormItem>
        </Form>
        <div style="padding-top: 60px;">
            <Table  :columns="columns" :data="data" ref="table" @on-row-dblclick="dbDetails($event)" style="width: 100%;"></Table>
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        name: "mmisillegalflight",
        data(){
            return{
                columns: [
                    {
                        title: '违规单位',
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
                        title: '航空器注册号',
                        key: 'aircraftId',
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
                                        title: params.row.aircraftId
                                    }
                                }, params.row.aircraftId)
                            ]);

                        }
                    },
                    {
                        title: '违规开始时间',
                        key:'starttime',
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
                        title: '违规结束时间',
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
                        title: '违规原因',
                        key: 'reason',
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
                                        title: params.row.reason
                                    }
                                }, params.row.reason)
                            ]);

                        }
                    },
                    {
                        title: '更新时间',
                        key: 'updatetime',
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
                                        title: params.row.updatetime
                                    }
                                }, params.row.updatetime)
                            ]);

                        }
                    },
                ],
                data:[],
                mmisillegalflight:{
                    companyId:'',
                    aircraftRegNo:'',
                    starttime:'',
                    endtime:'',
                },
                companys:[],

            }
        },
        methods:{
            inint(){
                let self = this;
                let url='';
                let data={};
                let datas =JSON.parse(Cookies.get("profile"));
                url='/eim/api/mmisillegal/findbycompanyId';
                data.companyIds=datas.buz.companyId
                data.companyId = self.mmisillegalflight.companyId

                if(typeof data.companyId=='undefined'){
                    data.companyId=''
                }
                data.aircraftRegNo = self.mmisillegalflight.aircraftRegNo
                data.starttime = self.mmisillegalflight.starttime;
                data.endtime = self.mmisillegalflight.endtime;
                /*  }*/
                self.$http.httpGet(url, data).then((res) => {
                    self.data=res.data;
                    self.companys = res.data;
                }).
                catch(function (error) {
                    console.log(error);
                });
                this.findselect()
            },
            findselect(){
                let self = this;
                let url='';
                let data={};
                let datas =JSON.parse(Cookies.get("profile"));
                url='/eim/api/mmisillegal/findbycompanyId1';
                data.companyIds=datas.buz.companyId;
                //data.companyId = self.mmisillegalflight.companyId
                self.$http.httpGet(url,data).then(res=>{
                    self.companys = res.data;
                })
            },
            reset:function (name) {
                this.$refs[name].resetFields();
                this.$refs.select.reset();
            },
            dbDetails:function (column) {
                this.$router.push({path:'/mmisillegalflightdetail',query: {mmisillegalflight:column }})
            }
        },
        mounted(){
            this.inint()

        }
    }
</script>

<style scoped>

</style>