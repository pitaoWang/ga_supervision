<style scoped>
    .physicalp{
        margin-left: 28px;
        /*width: 900px;*/
        /*height: 400px;*/
        /*overflow: hidden;*/
    }

    .everyf{
        border: 1px solid ;
        border-color: #bbbec4;
        margin-top: 5px;
        position: relative;
    }
    .child{
        position: absolute;
        top:60px;
        right: 20px;
        width: 283px;
    }

    .flag{
        position: absolute;
        top: 5px;
        right: 3px;

    }
    .flag>img{
        width: 30px;
        display: block;
    }

    .ivu-form .ivu-form-item-label{
        color:#b3b3b3;
        font-size: 14px;

    }


    .ivu-input-icon+.ivu-input{
        height: 30px;
        background: #b3b3b3;
        padding-right: 0;
    }
    .ivu-row{
        margin-bottom: 30px;
    }
    .submit-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);
        background: linear-gradient(#a3a3a3, #e7e7e7);
    }
    .flybase-submit-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        background: -webkit-linear-gradient(#e7e7e7, #a3a3a3);
        background: linear-gradient(#e7e7e7, #a3a3a3);
    }
</style>
<template >
    <div style="padding-top: 2%;">
        <div>
            <img src="/static/images/基础资料图标.png" style="position: absolute;margin-left: 24px;margin-top: 9px;"/>
            <div style="margin-left: 0%;margin-top: 5%;width: 930px;height:36px;background-color:rgb(46, 130, 220) ">
                <div style="padding-top: 5px;padding-left: 53px;color: rgb(255,255,255);font-size: 16px;">系统管理-飞行记录</div>
            </div>
        </div>
    <div style="background-color: rgba(193, 220, 248, 0.5);width: 93.6%;border: 1px solid rgba(74, 163, 255, 1);height: 500px;margin-top: 3px">

        <Form :label-width="120" style="margin:29px 10px;">
            <div class="bottom5" style="margin-top:10px;height:180px;">
            <span style="width:100%;display:inline-block;text-align:right;height: 31%;">
                <Row >
                    <div id="12345" style="float: left;border:1px solid rgba(74, 163, 255, 1);height: 49px;margin-left: 1%;width: 78%" >
                       <Col :md="6" style="width: 32%;margin-top: 1%">
                                    <FormItem label="起始日期:"  class="">
                                        <DatePicker type="date" placeholder="选择日期"   @on-change="datefoamat"></DatePicker>
                                    </FormItem>
                        </Col>
                        <Col :md="6" style="width: 32%;margin-top: 1%">
                                    <FormItem label="终止日期:"  class="bottom5">
                                        <DatePicker type="date" placeholder="选择日期"   @on-change="datefoamatlandTime"></DatePicker>
                                    </FormItem>
                        </Col>
                        <Col :md="6" style="margin-left: 6%;margin-top: 1.5%">
                                    <Button type="primary"  icon="plus"
                                            style="width:150px;margin-right:10px;" size="small" @click="exportData">导  出</Button>
                        </Col>
                    </div>
                    <div style="float: left;height: 49px;width: 20%;padding-left: 3%;" >
                        <Col :md="6" style="margin-top:7%">
                                     <Button type="primary" @click="add_dialog_show()" icon="plus"
                                             style="width:150px;margin-right:10px;" size="small">增  加</Button>
                        </Col>
                    </div>
                </Row>

            </span>

                <div class="bottom5" style="margin-top:5px;padding:10px;">
                    <Table border :columns="columns7" ref="flighttable" :data="flightimplementRecords"></Table>
                </div>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="pagecount" :current="currentpage" @on-change="changePage"></Page>
                    </div>
                </div>
            </div>
        </Form>


    </div>
    </div>

</template>


<script>
    import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";
    export default {

        data() {
            return {
                pagecount:'',
                employeeId:'',
                modal1: false,
                uploadListarray: [],
                formdatearray: [],
                formValidate: {
                    trainingRecords: [
                        {
                            id:'',
                            employeeid:'',
                            trainType: '',
                            trainSubject:'',
                            trainContent:'',
                            beginDate: '',
                            endDate: '',
                            createTime:'',
                            updateTime:'',
                            result: '',
                            certifactefile:'',
                            flag: ''
                        },
                    ]

                },
                trainingRecord:
                    {
                        id:'',
                        employeeid:'',
                        trainType: '',
                        trainSubject:'',
                        trainContent:'',
                        beginDate: '',
                        endDate: '',
                        createTime:'',
                        updateTime:'',
                        result: '',
                        certifactefile:'',
                        flag: ''
                    },
                flighttakeOffTime:'',
                flightlandTime:'',
                flightimplementRecords:[],
//                flighttable:'',
                columns7: [
                    {
                        title: '起飞时间',
                        width: 145,
                        key: 'takeOffTime',
                        align: 'center'
                    },
                    {
                        title: '降落时间',
                        width: 142,
                        key: 'landTime',
                        align: 'center'
                    },
                    {
                        title: '航空器型号',
                        width: 109,
                        key: 'aircraftModel',
                        align: 'center'
                    },
                    {
                        title: '航空器登记号',
                        width: 128,
                        key: 'aircraftCerNumber',
                        align: 'center'
                    },
                    {
                        title: '起飞站',
                        width: 100,
                        key: 'origin',
                        align: 'center'
                    },

                    {
                        title: '降落站',
                        width: 108,
                        key: 'destination',
                        align: 'center'
                    },

                    {
                        title: '操作',
                        key: 'action',
                        width: 170,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {

                                            this.copy(params.index);
                                        }
                                    }
                                }, '复制'),
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }, '修改'),

                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {

                                            this.confirm(params.index);
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],


            }
        },

        components: {
            IForm,
            'word': word,
        },
        created: function (newval) {
            // alert("进到这个");
            this.init(0);
        },

        methods: {
            datefoamat(val) {
                this.flighttakeOffTime=val;
            },
            datefoamatlandTime(val) {
//                this.formatlandtime=val;
                this.flightlandTime=val;
            },

            exportData () {
                let self=this;
                if (self.flighttakeOffTime != '' && self.flightlandTime != '') {
                    if (self.flighttakeOffTime.valueOf() > self.flightlandTime.valueOf()) {
                        alert('起始日期要比终止日期小，请重新选择！');
                        return;
                    }
                }
                self.$profile.getProfile().then((profile) => {
                    console.log("...............................",profile.buz);
                    let companyId=profile.buz.companyId;
                    self.$http.LocationHref('/eim/api/flightimplementrecord/xiazaiword', {
                        companyId:companyId,
                        takeofftime:self.flighttakeOffTime,
                        landtime:self.flightlandTime,
                    }).then(function (response) {
                    }).catch(function (error) {
                        console.log(error);
                    });

//                    window.location.href ="/app/api/flightimplementrecord/xiazaiword?companyId="+companyId+
//                        "&takeofftime="+self.flighttakeOffTime+
//                        "&landtime="+self.flightlandTime;
                })



//                    this.$refs.flighttable.exportCsv({
//                        filename: 'The original data'
//                    });

            },
            dealcancel(){
                this.trainingRecord={
                    id:'',
                    employeeid:'',
                    trainType: '',
                    trainSubject:'',
                    trainContent:'',
                    beginDate: '',
                    endDate: '',
                    createTime:'',
                    updateTime:'',
                    result: '',
                    certifactefile:'',
                    flag: ''
                };
            },

            getCheckform() {
                return this.trainingRecord;
            },
            getuploadList: function ($event, trainingRecord) {
                console.log($event);
                trainingRecord.certifactefile = $event;
            },
            notnull(datainfo){
                if(datainfo=='' || datainfo==null){
                    alert("空的");
                }
            },
            confirm (index) {
                this.$Modal.confirm({
                    title: '警告框：',
                    content: '<p>确认删除该条记录？</p>',
                    onOk: () => {
                        this.removeCheck (index);

                    },
                    onCancel: () => {

                    }
                });
            },
            changePage(val){
                this.init(val-1)
            },
            init:function(val){

                let self = this;
                console.log("go to get search");
                //刚进页面要根据employeeId查询，所以先赋值
                // self.trainingRecord.employeeId=this.employeeId;
                //alert("xunlian"+self.trainingRecord.employeeId);
                self.$profile.getProfile().then((profile) => {
                    console.log(profile,'%%%%%%%%%%%%%%%%%%%%%%%%%%');
                    var roles=profile.roles;
                    let companyId=profile.buz.companyId;
                    for(var i=0;i<roles.length;i++){
                        if(roles[i]==("operation_role_company_admin")){
                            self.$http.httpGet('/eim/api/flightimplementrecord/findpageFlightImplementRecordsByCompanyId', {
                                companyId:companyId,
                                pageno:val,
                                pagesize:10
                            }).then((res) => {
                                let result ;
                                self.pagecount=res.data.totalElements;
                                //self.items = res.data;
                                result=res.data.content;
                                console.log(result,'...........findFlightImplementRecordsByEmployeeId...........');
                                self.flightimplementRecords= result;
//                                for(var i=0;i<self.flightimplementRecords.length;i++) {
//
//                                }

                            })
                            return;
                        }
                    }
                     self.employeeId=profile.buz.employeeId;
                     if (self.employeeId > 0) {
                         self.$http.httpGet('/eim/api/flightimplementrecord/findPageFlightImplementRecordsByEmployeeId', {
                             employeeId:self.employeeId,
                             pageno:val,
                             pagesize:10
                         }).then((res) => {
                             let result = res.data.content;
                             self.pagecount=res.data.totalElements;
                             console.log(result,'.。。。。。。。。。findFlightImplementRecordsByEmployeeId...........');
                             self.flightimplementRecords= result;
                         })
                     }

                });
            },
            //添加页面
            add_dialog_show:function (){
                this.$router.push(
                    {
                        path: '/for_flight_record',
                        query: {}
                    });
            },
           /* //修改
            update_dialog_show:function (id){
                let self=this;
                self.$http.httpGet('/app/api/training/serach/getTrainingRecordById', {
                    id
                }).then(function (response) {
                    self.trainingRecord=response.data;

                    self.init();
                }) .catch(function (error) {
                    console.log(error);
                });
                this.modal1=true;

            },*/
            //修改页面
            copy(index){
                let self=this;
                let id=self.flightimplementRecords[index].id;
//                self.$router.push(
//                    {
//                        path: '/for_flight_record',
//                        query: {backtype,id}
//                    });
                self.$router.push({path: '/for_flight_record', query: {flightrecordId:id,copy:true}});
            },
            updateCheck (index) {
                let self=this;
                let id=self.flightimplementRecords[index].id;
//                self.$router.push(
//                    {
//                        path: '/for_flight_record',
//                        query: {backtype,id}
//                    });
                self.$router.push({path: '/for_flight_record', query: {flightrecordId:id}});
            },
            //删除
            removeCheck (index) {

                let self=this;

                let id=self.flightimplementRecords[index].id;
                self.$http.httpDelete('/eim/api/flightimplementrecord/deleteFlightImplementRecordById/'+id, {
                }).then(function (response) {
                    self.init(0);
                }) .catch(function (error) {
                    console.log(error);
                });
            },
            submittraining:function(){
                let self = this;
                //判断所有字段不能为空


                /*  if((self.trainingRecord.beginDate.valueOf())>(self.trainingRecord.endDate.valueOf())){
                      alert('开始时间不能大于结束时间，请重新添加！');
                      return;
                  }*/
                //判断时间结束时间不能大于开始时间
//                if(){
//
//                }
                console.log(self.trainingRecord,'******************');
//                let baseUrl = 'http://192.168.0.200:38080';

                if(self.trainingRecord.trainType ==''
                    || self.trainingRecord.trainSubject ==''
                    || self.trainingRecord.trainContent ==''
                    || self.trainingRecord.beginDate ==''
                    || self.trainingRecord.endDate ==''
                    || self.trainingRecord.result ==''
                ){
                    alert('有信息未填写，请重新添加！');
//                        self.$Notice.info({
//                            title: '这是通知标题',
//                            desc: false ? '' : '有信息未填写，请重新添加！'
//                        });
                    return;
                }
                if((self.trainingRecord.beginDate.valueOf())>(self.trainingRecord.endDate.valueOf())){
                    alert('开始时间不能大于结束时间，请重新添加！');
                    return;
                }
                self.$http.jsonPost('/eim/api/training', self.trainingRecord).then(function (response) {
                    console.log(response.data, '******************');
                    self.trainingRecord = {};
                    self.init(0);
                }).catch(function (error) {
                    console.log(error);
                });

                alert("添加培训信息成功");

            },
            submitCheckForm(){
                let self = this;
                let url = "/eim/api";
                let checkUrl = url + "/license/check/save";
                let is_update = false;
                if(self.check_form.id)
                {
                    is_update = true;
                }

                if(!is_update)
                {
                    self.check_form.pilotLicenseId = self.qulification.licenseId;

                }

                self.$http.httpPost(checkUrl, self.check_form).then(function(res) {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.check_form = {};
                        if(!is_update)
                        {
                            self.licenseChecks.push(result.data);
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
        addformdate: function () {
            this.formValidate.trainingRecords.push({
                id:'',
                employeeid:'',
                traintype: '',
                trainsubject:'',
                trainContent:'',
                certificateStartTime: '',
                certificateValidityTime: '',
                beginDate: '',
                endDate: '',
                result:'',
                createTime:'',
                updateTime:'',
                flag: '',
            },);
        },
        getuploadList: function ($event, item) {
            console.log($event);
            item.certificateFilepath=$event;
        },
        getitem:function (item) {

            return item;
        },

        handleremove: function (item) {
            if (item.flag == "") {

                this.cancelform(item);
            }
            else {
                alert("该数据已保存是否删除？");
                this.deletedform(item);
            }
        },
        cancelform: function (item) {
            this.formValidate.trainingRecords.splice(this.formValidate.trainingRecords.indexOf(item), 1);
        },
        deletedform: function (item) {
            let self = this;
            let  baseUrl = 'http://192.168.0.200:38080';
            let id = item.id;
            self.$http.httpDelete('/eim/api/physical/'+id)
                .then(function (response) {
                    console.log(response)
                    self.cancelform(item);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },



        wtach: {
            'formValidate.trainingRecords':function (newval) {
                console.log(newval);
            }
        },

    }

</script>
