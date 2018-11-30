<template>
    <div id="repairmodified" style="width: 100%;display: inline-block;background: white;padding: 0 10px;margin-bottom:16px;">
        <Form ref="formItem" :model="formItem" :label-width="110" inline style="width:100%;font-size:0;">
            <FormItem :label-width="70" class="ws-form-span8" style="margin-right: 0;" label="编号">
                <Input v-model="formItem.no" placeholder="请输入编号" />
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;" label="类型" >
                <Select v-model="formItem.type" number="true"  placeholder="请选择">
                    <Option value="修理">修理</Option>
                    <Option value="改装">改装</Option>
                </Select>
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;" label="项目" >
                <Select v-model="formItem.project" number="true"  placeholder="请选择">
                    <Option value="机体">机体</Option>
                    <Option value="动力装置">动力装置</Option>
                    <Option value="螺旋桨">螺旋桨</Option>
                    <Option value="设备设置">设备设置</Option>
                </Select>
            </FormItem>
            <FormItem :label-width="70" class="ws-form-span8" style="margin-right: 0;" label="处理状态" >
                <Select v-model="formItem.status" number="true"  placeholder="请选择">
                    <Option value="待完成">待完成</Option>
                    <Option value="已完成">已完成</Option>
                    <Option value="已退单">已退单</Option>
                </Select>
            </FormItem>
            <FormItem class="ws-form-span8" style="margin-right: 0;">
                <Button type="primary" @click="getRepairList(1)" style="margin-left:0;">查询</Button>
                <Button type="primary" @click="reset" style="margin-left:0;">重置</Button>
            </FormItem>
        </Form>
        <div>
            <div class="bottom5">
                <Table  :columns="columns" :data="repairmodifieds" style="width:100%;"/>
            </div>
            <Page :total="pagecount" @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;margin-right: -10px;margin-top: 8px;" show-sizer show-total></Page>
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            formItem: {
                no: '',
                type:'',
                project: '',
                status: ''
            },
            repairmodifieds: [],
            columns: [
                {
                    title: '编号',
                    key: 'id',
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
                    width: 180,
                    key: 'aircraftModel',
                    align: 'center'
                },
                {
                    title: '航空器注册号',
                    width: 180,
                    key: 'aircraftRegNo',
                    align: 'center'
                },
                {
                    title: '类型',
                    width: 160,
                    key: 'type',
                    align: 'center'
                },{
                    title: '项目',
                    // width: 240,
                    key: 'project',
                    align: 'center'
                },{
                    title: '处理状态',
                    width: 180,
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
        this.getRepairList(1);
    },
    methods: {
        toview(index) {
            this.$router.push({
                path: '/aircraftmonitoring/repairmodifiedinfo',
                query: {Id: this.repairmodifieds[index].id, aircraft:this.aircraft}
            });
        },
        pageclick:function (obj) {
            this.pageno = obj;
            this.getRepairList(obj);
        },
        pagesizeclick: function(size) {
            this.pagesize = size;
            this.getRepairList(1);
        },
        getRepairList: function(no) {            
            let self = this;
            var reserveDate = self.formItem.reserveDate;

            var reserveDateStr = "";
            if(reserveDate) {
                reserveDateStr = reserveDate.getFullYear() + "-" + (reserveDate.getMonth() + 1) + "-" + reserveDate.getDate();
            }

            self.$http.httpGet('/eim/api/mmis/getRepairListByPage', {
                regNumber: self.regNumber,
                no: self.formItem.no,
                reserveDate:reserveDateStr,
                status: self.formItem.status,
                pageSize: self.pagesize,
                page: no
            }).then((res) => {
                console.log("getRepairListByPage res", res);
                self.repairmodifieds = res.data.content;
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
   #repairmodified .ws-form-span8 {
        width: 33.33333%;
    }
    #repairmodified, #repairmodified table td, #repairmodified table th {
        font-size: 14px;
    }
</style>


