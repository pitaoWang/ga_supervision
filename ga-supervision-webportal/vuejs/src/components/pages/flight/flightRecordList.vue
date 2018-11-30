<template>
    <div class="container_div">

        <Form :model="check_form"  ref="flightRecordRef">
            <FormItem label="所属企业" :label-width="70" style="width:320px;float: left;" prop="organizationId">
                <Select filterable  v-model="check_form.organizationId" placeholder="请选择" ref="sss">
                    <Option v-for="site in supervision1" :key="site.companyId" :value="site.companyId">{{ site.name }}</Option>
                </Select>
            </FormItem>
            <FormItem label="飞行类型" :label-width="70" style="width:320px;float: left;margin-left: 40px;" prop="flightNature">
                <Select v-model="check_form.flightNature" placeholder="请选择" >
                    <Option value="1">任务飞行</Option>
                    <Option value="2">训练飞行</Option>
                    <Option value="3">检查飞行</Option>
                    <Option value="4">实践考试</Option>
                </Select>
            </FormItem>
            <FormItem label="航空器注册号" :label-width="100" style="width:320px;float: left;margin-left: 40px;" prop="aircraftNo">
                <Input v-model="check_form.aircraftNo" placeholder="请输入注册号"></Input>
            </FormItem>
            <!--<FormItem label="人员类型" style="width:267px;float: left;margin-left: 8px;">
                <Input v-model="formItem.jobTitle" placeholder="请输入帐号"></Input>
            </FormItem>-->
            <div style="display: inline-block;">
            </div>
            <FormItem label="起飞时间" :label-width="70" style="width:320px;float: left;" prop="flightTimeBegin">
                <TimePicker v-model="check_form.flightTimeBegin" type="time" placeholder="begin time" style="width:250px" ></TimePicker>
            </FormItem>

            <FormItem label="降落时间" :label-width="70" style="width:320px;float: left;margin-left: 40px;" prop="flightTimeEnd">
                <TimePicker v-model="check_form.flightTimeEnd" type="time" placeholder="end time" style="width:250px"></TimePicker>
            </FormItem>

            <FormItem  style="width:100px;height: 20px; float: left; margin-left: 40px;">
                <Button type="primary" @click="init(1)">查询</Button>
            </FormItem>
            <FormItem  style="width:100px;height: 20px; float: left; margin-left: -32px;">
                <Button type="primary" @click="reset">重置</Button>
            </FormItem>
        </Form>
        <div style="clear: both;"></div>
                 <div>
                    <div class="bottom5" >
                        <Table  :columns="columns7" :data="flightRecordList"  @on-row-dblclick="viewPrepareRelease"></Table>
                    </div>
                    <Page :total="pagecount" show-total @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
                </div>

    </div>
</template>

<script>


    import Cookies from 'js-cookie';
    export default {
        name: "prepareReleaseList",
        data() {
            return {
                supervision1:[],
                check_form: {
                    aircraftNo:'',
                    flightTimeBegin:'',
                    flightTimeEnd:'',
                    organizationId:'',
                    flightNature:''
                },
                pagesize:10,
                pagecount:0,
                count:1,
                flightRecordList: [],
                columns7: [
                    {
                        title: '飞行人员',

                        key: 'flightPersonName',
                        align: 'center'
                    },{
                        title: '飞行日期',
                        key: 'flightDate',
                        align: 'center',

                    }, {
                        title: '航空器注册号',

                        key: 'aircraftNo',
                        align: 'center'
                    }, {
                        title: '起飞机场',

                        key: 'portDepartureName',
                        align: 'center'
                    },{
                        title: '降落机场',

                        key: 'portDestinationName',
                        align: 'center'
                    },{
                        title: '起飞时刻',

                        key: 'flightTimeBegin',
                        align: 'center'
                    },{
                        title: '降落时刻',

                        key: 'flightTimeEnd',
                        align: 'center'
                    }

                ],

            }
        },

        created: function () {
            let self = this;

            let buz = JSON.parse(Cookies.get("profile"));

            self.$http.httpGet('/eim/api/company/search/byids', {
                companyId:buz.buz.companyId

            }).then(function (response) {

                let datainfo = response.data;

                self.supervision1 = datainfo;

            }).catch(function (error) {
                console.log(error);
            });
            this.init(1);
        },
        methods: {
            init: function (obj) {
                this.getAircraftList(obj);
            },
            viewPrepareRelease:function (obj) {
                obj =JSON.stringify(obj);
                this.$router.push({path: '/viewFlightRecord',query:{flightRecord:obj}});
            },
            pageclick:function (obj) {
                this.count = obj;
                this.getAircraftList(obj);
            },
            getAircraftList:function (obj) {
                let self = this;
                if(self.check_form.organizationId ==""||typeof(self.check_form.organizationId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.check_form.organizationId;
                }
                if(typeof(self.check_form.flightNature) != "undefined"){
                    var status = self.check_form.flightNature;
                }else{
                    var status = "";
                }
                self.$http.httpGet('/eim/api/flightRecord/search/findFlightRecordByInfo',{
                    organizationId:companyIds,
                    aircraftNo:self.check_form.aircraftNo,
                    flightTimeBegin:self.check_form.flightTimeBegin,
                    flightTimeEnd:self.check_form.flightTimeEnd,
                    flightNature:status,
                    pageSize:self.pagesize,
                    currentPage:obj
                }).then(function (response) {

                    let datainfo = response.data.content;

                    self.flightRecordList=datainfo;

                    self.pagecount=response.data.totalElements;
                }).catch(function (error) {
                    console.log(error);
                });

            },pagesizeclick:function (obj) {

                this.pagesize=obj;

                let self = this;
                if(self.check_form.organizationId ==""||typeof(self.check_form.organizationId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.check_form.organizationId;
                }
                if(typeof(self.check_form.flightNature) != "undefined"){
                    var status = self.check_form.flightNature;
                }else{
                    var status = "";
                }
                self.$http.httpGet('/eim/api/flightRecord/search/findFlightRecordByInfo',{
                    organizationId:companyIds,
                    aircraftNo:self.check_form.aircraftNo,
                    flightTimeBegin:self.check_form.flightTimeBegin,
                    flightTimeEnd:self.check_form.flightTimeEnd,
                    flightNature:status,
                    pageSize:self.pagesize,
                    currentPage:1
                }).then(function (response) {

                    let datainfo = response.data.content;
                    self.flightRecordList=datainfo;
                    self.pagecount=response.data.totalElements;

                }).catch(function (error) {
                    console.log(error);
                });
            },reset:function(){
                //console.log("qqqqqqqqqqqqqq"+this.$refs['aircraftRef'].resetField())
                this.$refs['flightRecordRef'].resetFields();

                this.$refs.sss.reset();
            }

        }
    }
</script>

<style scoped>

</style>