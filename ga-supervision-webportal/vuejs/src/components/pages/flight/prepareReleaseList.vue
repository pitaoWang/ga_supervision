<template>
    <div class="container_div">

        <Form :model="check_form"  ref="prepareReleaseRef">
            <FormItem label="所属企业" :label-width="70" style="width:300px;float: left;" prop="organizationId">
                <Select filterable  v-model="check_form.organizationId" placeholder="请选择" ref="sss">
                    <Option v-for="site in supervision1" :key="site.companyId" :value="site.companyId">{{ site.name }}</Option>
                </Select>
            </FormItem>
            <FormItem label="航空器注册号" :label-width="100" style="width:300px;float: left;margin-left: 40px;" prop="aircraftNo">
                <Input v-model="check_form.aircraftNo" placeholder="请输入注册号"></Input>
            </FormItem>
            <!--<FormItem label="人员类型" style="width:267px;float: left;margin-left: 8px;">
                <Input v-model="formItem.jobTitle" placeholder="请输入帐号"></Input>
            </FormItem>-->

            <FormItem label="放行时间" :label-width="70" style="width:300px;float: left;margin-left: 40px;" prop="releaseTime">
                <DatePicker v-model="check_form.releaseTime" type="daterange" placement="bottom-end" placeholder="选择日期范围" style="width: 95%"></DatePicker>
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
                        <Table  :columns="columns7" :data="prepareReleaseList"  @on-row-dblclick="viewPrepareRelease"></Table>
                    </div>
                    <Page :total="pagecount"  show-total @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
                </div>

    </div>
</template>

<script>
  /*  import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";*/
    import Cookies from 'js-cookie';
    export default {
        name: "prepareReleaseList",
        data() {
            return {
                supervision1:[],
                check_form: {
                    aircraftNo:'',
                    releaseTime:'',
                    organizationId:'',
                },
                pagesize:10,
                pagecount:0,
                count:1,
                prepareReleaseList: [],
                columns7: [
                    {
                        title: '飞行计划',

                        key: 'flightPlanName',
                        align: 'center'
                    },{
                        title: '航空器型别',
                        key: 'aircraftType',
                        align: 'center'

                    },
                    {
                        title: '航空器注册号',

                        key: 'aircraftNo',
                        align: 'center'
                    },
                    {
                        title: '机长',

                        key: 'captainName',
                        align: 'center'
                    },{
                        title: '副机长',

                        key: 'copilotName',
                        align: 'center'
                    },{
                        title: '起飞机场',

                        key: 'airportDepartureName',
                        align: 'center'
                    },{
                        title: '降落机场',

                        key: 'airportDestinationName',
                        align: 'center'
                    },{
                        title: '备降机场',

                        key: 'airportAlternateName',
                        align: 'center'
                    }

                ],

            }
        },
     /*   components: {
            IForm,
            'word': word,
        },*/
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
                this.$router.push({path: '/viewPrepareRelease',query:{prepareRelease:obj}});
            },
            pageclick:function (obj) {
                this.count = obj;
                this.getAircraftList(obj);
            },
            getAircraftList:function (obj) {
                let self = this;
                let releaseTime="";
                if(self.check_form.organizationId ==""||typeof(self.check_form.organizationId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.check_form.organizationId;
                }
                if(self.check_form.releaseTime!=null&&self.check_form.releaseTime!=""&&self.check_form.releaseTime!=","){

                    var s =self.check_form.releaseTime.toString().split(",");

                    var d = new Date(s[0]);
                    let startDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                    var d = new Date(s[1]);
                    let endDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                     releaseTime = startDate+","+endDate;
                }

                self.$http.httpGet('/eim/api/prepareRelease/search/findPrepareReleaseByInfo',{
                    organizationId:companyIds,
                    aircraftNo:self.check_form.aircraftNo,
                    releaseTime:releaseTime,
                    pageSize:self.pagesize,
                    currentPage:obj
                }).then(function (response) {

                    let datainfo = response.data.content;

                    self.prepareReleaseList=datainfo;

                    self.pagecount=response.data.totalElements;
                }).catch(function (error) {
                    console.log(error);
                });

            },pagesizeclick:function (obj) {

                this.pagesize=obj;
                let self = this;
                let releaseTime="";
                if(self.check_form.organizationId ==""||typeof(self.check_form.organizationId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.check_form.organizationId;
                }

                if(self.check_form.releaseTime!=null&&self.check_form.releaseTime!=""&&self.check_form.releaseTime!=","){

                    var s =self.check_form.releaseTime.toString().split(",");

                    var d = new Date(s[0]);
                    let startDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                    var d = new Date(s[1]);
                    let endDate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                     releaseTime = startDate+","+endDate;
                }
                self.$http.httpGet('/eim/api/prepareRelease/search/findPrepareReleaseByInfo',{
                    organizationId:companyIds,
                    aircraftNo:self.check_form.aircraftNo,
                    releaseTime:releaseTime,
                    pageSize:self.pagesize,
                    currentPage:1
                }).then(function (response) {

                    let datainfo = response.data.content;
                    self.prepareReleaseList=datainfo;
                    self.pagecount=response.data.totalElements;

                }).catch(function (error) {
                    console.log(error);
                });
            },reset:function(){
                //console.log("qqqqqqqqqqqqqq"+this.$refs['aircraftRef'].resetField())
                this.$refs['prepareReleaseRef'].resetFields();

                this.$refs.sss.reset();
            }

        }
    }
</script>

<style scoped>

</style>