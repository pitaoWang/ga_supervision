<template>
    <div id="workreserve" style="width: 100%;display: inline-block;background: white;padding: 0 10px;margin-bottom:16px;">
        <Form ref="workreserveRef" :model="formItem" :label-width="110" inline style="width:100%;font-size:0;">
            <FormItem :label-width="70" class="ws-form-span8" style="margin-right: 0;" label="编号">
                <Input v-model="formItem.no" placeholder="请输入编号" />
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;" label="工作单卡编号" >
                <Input v-model="formItem.wocNo" placeholder="请输入工作单卡编号"/>
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;" label="推迟期限" >
                <!-- <Input v-model="formItem.delayDate" placeholder="请输入工作单卡编号"/> -->
                <DatePicker v-model="formItem.delayDate" type="date" placement="bottom-end" placeholder="请选择推迟期限"  style="width:100%;"/>
            </FormItem>
            <FormItem :label-width="70" class="ws-form-span8" style="margin-right: 0;" label="处理状态" >
                <Select v-model="formItem.status" number="true"  placeholder="请选择">
                    <Option value="待完成">待完成</Option>
                    <Option value="已完成">已完成</Option>
                    <Option value="已退单">已退单</Option>
                </Select>
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;">
                <Button type="primary" @click="getWorkList(1)" style="margin-left:0;">查询</Button>
                <Button type="primary" @click="reset()" style="margin-left:0;">重置</Button>
            </FormItem>
        </Form>
        <div>
            <div class="bottom5">
                <Table  :columns="columns" :data="workreserves" style="width:100%;"/>
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
                wocNo:'',
                delayDate: '',
                status: ''
            },
            workreserves: [
                {
                    no:"0701",
                    aircraftModel:"",
                    aircraftRegNo:"",
                    wocNo:"",
                    wocTitle:"",
                    delayDate:"",
                    status:"",
                    action:"",
                }
            ],
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
                    width: 120,
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
                    title: '工作单卡编号',
                    width: 160,
                    key: 'wocNo',
                    align: 'center'
                },{
                    title: '工作单卡标题',
                    // width: 240,
                    key: 'wocTitle',
                    align: 'center'
                },{
                    title: '推迟期限',
                    width: 160,
                    key: 'delayDate',
                    align: 'center',
                    // render: (h, params) => {
                    //     console.log(params, params.column);

                    //     var date = new Date(params.row.delayDate);
                        
                    //     return h('div',  [
                    //         h('div', {
                    //             props: {
                    //                href:"#"
                    //             },
                    //             style: {
                                    
                    //             }
                    //         }, this.formatDate(date)),
                    //     ]);
                    // }
                },{
                    title: '处理状态',
                    width: 100,
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

                //         var date = new Date(params.row.delayDate);
                        
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
    props: ['regNumber','aircraft'],
    mounted () {
        this.getWorkList(1);
    },
    methods: {
        formatDate: function (date, format) {
            var yyyy = ('000' + date.getFullYear()).slice(-4),
                yy = yyyy.slice(-2),
                mm = ('0' + (date.getMonth()+1)).slice(-2),
                dd = ('0' + date.getDate()).slice(-2);
            format = format || 'yyyy-mm-dd';
            return format.replace(/yyyy/ig, yyyy).replace(/yy/ig, yy).replace(/mm/ig, mm).replace(/dd/ig, dd);
        },
        toview(index) {
            this.$router.push({
                path: '/aircraftmonitoring/workreserveinfo',
                query: {Id: this.workreserves[index].id, aircraft: this.aircraft}
            });
        },
        pageclick:function (obj) {
            this.pageno = obj;
            this.getWorkList(obj);
        },
        pagesizeclick: function(size) {
            this.pagesize = size;
            this.getWorkList(1);
        },
        getWorkList: function(no) {            
            let self = this;
            var delayDate = self.formItem.delayDate;

            var delayDateStr = "";
            if(delayDate) {
                delayDateStr = delayDate.getFullYear() + "-" + (delayDate.getMonth() + 1) + "-" + delayDate.getDate();
            }

            self.$http.httpGet('/eim/api/mmis/getWorkListByPage', {
                regNumber: self.regNumber,
                no: self.formItem.no,
                wocNo:self.formItem.wocNo,
                delayDate:delayDateStr,
                status: self.formItem.status,
                pageSize: self.pagesize,
                page: no
            }).then((res) => {
                console.log("getWorkListByPage res", res);
                self.workreserves = res.data.content;
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
                delayDate: '',
                status: ''
            };
            this.$refs['workreserveRef'].resetFields();
        }
    }
}
</script>
<style>
    #workreserve .ws-form-span8 {
        width: 33.33333%;
    }
    td {
        max-width: 100%;
    }
    #workreserve, #workreserve table td, #workreserve table th {
        font-size: 14px;
    }
</style>


