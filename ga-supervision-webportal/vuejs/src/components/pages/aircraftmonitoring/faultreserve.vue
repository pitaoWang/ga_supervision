<template>
    <div id="faultreserve" style="width: 100%;display: inline-block;background: white;padding: 0 10px;margin-bottom:16px;">
        <Form ref="formItem" :model="formItem" :label-width="110" inline style="width:100%;font-size:0;">
            <FormItem :label-width="40" class="ws-form-span8" style="margin-right: 0;" label="编号">
                <Input v-model="formItem.no" placeholder="请输入编号" />
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;" label="保留期限" >
                <!-- <Input v-model="formItem.reserveDate" placeholder="请输入保留期限"/> -->
                <DatePicker v-model="formItem.reserveDate" type="date" placement="bottom-end" placeholder="请选择保留期限"  style="width:100%;"/>
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;" label="处理状态" >
                <Select v-model="formItem.status" number="true"  placeholder="请选择">
                    <Option value="待完成">待完成</Option>
                    <Option value="已完成">已完成</Option>
                    <Option value="已退单">已退单</Option>
                </Select>
            </FormItem>
            <FormItem style="">
                <Button type="primary" @click="getFaultList(1)" style="margin-left:0;">查询</Button>
                <Button type="primary" @click="reset" style="margin-left:0;">重置</Button>
            </FormItem>
        </Form>
        <div>
            <div class="bottom5">
                <Table  :columns="columns" :data="faultreserves" style="width:100%;"/>
            </div>
            <Page :total="pagecount" @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;margin-right: -10px;margin-top: 8px;" show-sizer show-total ></Page>
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            formItem: {
                no: '',
                reserveDate:'',
                status: '',
            },
            faultreserves: [],
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
                    title: 'ATA章节号',
                    width: 160,
                    key: 'ataSection',
                    align: 'center'
                },{
                    title: '保留期限',
                    // width: 240,
                    key: 'reserveDate',
                    align: 'center'
                },{
                    title: '处理状态',
                    width: 160,
                    key: 'status',
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
    props: ['regNumber', 'aircraft'],
    mounted () {
        this.getFaultList(1);
    },
    methods: {
        toview(index) {
            this.$router.push({
                path: '/aircraftmonitoring/faultreserveinfo',
                query: {Id: this.faultreserves[index].id, aircraft: this.aircraft}
            });
        },
        pageclick:function (obj) {
            this.pageno = obj;
            this.getFaultList(obj);
        },
        pagesizeclick: function(size) {
            this.pagesize = size;
            this.getFaultList(1);
        },
        getFaultList: function(no) {            
            let self = this;
            var reserveDate = self.formItem.reserveDate;

            var reserveDateStr = "";
            if(reserveDate) {
                reserveDateStr = reserveDate.getFullYear() + "-" + (reserveDate.getMonth() + 1) + "-" + reserveDate.getDate();
            }

            self.$http.httpGet('/eim/api/mmis/getFaultListByPage', {
                regNumber: self.regNumber,
                no: self.formItem.no,
                reserveDate:reserveDateStr,
                status: self.formItem.status,
                pageSize: self.pagesize,
                page: no
            }).then((res) => {
                console.log("getFaultListByPage res", res);
                self.faultreserves = res.data.content;
                self.pagecount = res.data.totalElements;
                self.pageno = no;

            }).catch(function (error) {
                console.log(error);
            });
        },
        reset:function(){
            this.formItem = {
                no: '',
                wocNo:'',
                reserveDate: '',
                status: ''
            };
            this.$refs['formItem'].resetFields();
        }
    }
}
</script>
<style>
    #faultreserve .ws-form-span8 {
        width: 25%;
    }
    #faultreserve, #faultreserve table td, #faultreserve table th {
        font-size: 14px;
    }
</style>


