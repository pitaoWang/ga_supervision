<template>
    <div id="flightrecord" style="width: 100%;display: inline-block;background: white;padding: 0 10px;margin-bottom:16px;">
        <Form ref="formItem" :model="formItem" :label-width="70" inline style="width:100%;font-size:0;">
            <FormItem :label-width="40" class="ws-form-span8" style="margin-right: 0;" label="编号">
                <Input v-model="formItem.no" placeholder="请输入编号" />
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;" label="放行人" >
                <Input v-model="formItem.releaser" placeholder="请输入放行人"/>
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;margin-left: 10px;" label="放行时间" >
                <!-- <Input v-model="formItem.releaseTime" placeholder="请输入放行人时间"/> -->
                <DatePicker v-model="formItem.releaseTime" type="date" placement="bottom-end" placeholder="请选择放行时间" style="width:100%;"/>
            </FormItem>
            <FormItem style="">
                <Button type="primary" @click="getFlightRecordList(1)" style="margin-left:0;">查询</Button>
                <Button type="primary" @click="reset" style="margin-left:0;">重置</Button>
            </FormItem>
        </Form>
        <div>
            <div class="bottom5">
                <Table  :columns="columns" :data="flightrecords" style="width:100%;"/>
            </div>
            <Page :total="pagecount" @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;margin-top: 8px;margin-right: -10px;" show-sizer show-total ></Page>
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            formItem: {
                no: '',
                releaser:'',
                releaseTime: '',
            },
            flightrecords: [],
            columns: [
                {
                    title: '编号',
                    key: 'no',
                    align: 'center',
                    width: 100,
                    render: (h, params) => {
                        console.log(params, params.column);
                        
                        return h('div',  [
                            h('a', {
                                props: {
                                   href:"#"
                                },
                                style: {
                                    "color": "#2d8cf0"
                                },
                                on: {
                                    click: () => {
                                        this.toview(params.index);
                                    }
                                }
                            }, params.row.no),
                        ]);
                    }
                },
                {
                    title: '航空器型号',
                    width: 160,
                    key: 'aircraftModel',
                    align: 'center'
                },
                {
                    title: '航空器注册号',
                    width: 160,
                    key: 'aircraftRegNo',
                    align: 'center'
                },
                {
                    title: '机长',
                    width: 160,
                    key: 'captain',
                    align: 'center'
                },{
                    title: '放行人',
                    // width: 240,
                    key: 'releaser',
                    align: 'center'
                },{
                    title: '放行时间',
                    width: 160,
                    key: 'releaseTime',
                    align: 'center'
                },
                // {
                //     title: '操作',
                //     // width: 137,
                //     key: 'action',
                //     align: 'center',
                //     render: (h, params) => {
                //         console.log(params, params.column);
                        
                //         return h('div',  [
                //             h('a', {
                //                 props: {
                //                    href:"#"
                //                 },
                //                 style: {
                //                    "color": "#2d8cf0"
                //                 }
                //             }, "历史记录"),
                //         ]);
                //     }
                // }
            ],
            pageno:1,
            pagecount:0,
            pagesize: 10,
        }
    },
    //props: ['regNumber'],
    props: ['regNumber', 'aircraft'],
    mounted () {
        this.getFlightRecordList(1);
    },
    methods: {
        toview(index) {
            this.$router.push({
                path: '/aircraftmonitoring/flightrecordinfo',
                query: {Id: this.flightrecords[index].id,aircraft: this.aircraft}
            });
        },
        pageclick:function (obj) {
            this.pageno = obj;
            this.getFlightRecordList(obj);
        },
        pagesizeclick: function(size) {
            this.pagesize = size;
            this.getFlightRecordList(1);
        },
        getFlightRecordList: function(no) {            
            let self = this;
            var releaseTime = self.formItem.releaseTime;

            var releaseTimeStr = "";
            if(releaseTime) {
                releaseTimeStr = releaseTime.getFullYear() + "-" + (releaseTime.getMonth() + 1) + "-" + releaseTime.getDate();
            }

            self.$http.httpGet('/eim/api/mmis/getFlightRecordListByPage', {
                regNumber: self.regNumber,
                no: self.formItem.no,
                releaseTime:releaseTimeStr,
                releaser: self.formItem.releaser,
                pageSize: self.pagesize,
                page: no,
                // companyIds: "111"
            }).then((res) => {
                console.log("getFlightRecordListByPage res", res);
                self.flightrecords = res.data.content;
                self.pagecount = res.data.totalElements;
                self.pageno = no;

            }).catch(function (error) {
                console.log(error);
            });
        },
        reset:function(){
            this.formItem = {
                no: '',
                releaser:'',
                releaseTime: '',
            };
            this.$refs['formItem'].resetFields();
        }
    }
}
</script>
<style scoped>
    #flightrecord .ws-form-span8 {
        width: 25%;
    }
    #flightrecord, #flightrecord table td, #flightrecord table th {
        font-size: 14px;
    }
</style>


