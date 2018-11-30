<template>



        <div style="width: 716px">
                <Table border :columns="columns3" :data="flightimplementRecords"></Table>
                <div style="margin: 10px;overflow: hidden">
                        <div style="float: right;">
                                <Page :total="pagecount" :current="currentpage" @on-change="changePage"></Page>
                        </div>
                </div>

        </div>





</template>


<script>
    export default {
        components: {
        },
        data () {
            return {
                pagecount:'',
                employeeId:'',
                keyword: '',
                employee: {},
                pilot: {},
                flightRecordVOs: [],
                prList: [],
                peList: [],
                personQulificationInfoVo: {},
                personQulificationInfo: {},
                pilotLicense: {},
                scanCardarra:[],
                personQuliPic:[],
                PersonQulificationInfolist:[],

                flightimplementRecords:[],
                columns3: [
                    {
                        title: '飞行日期',
                        key: 'flyDate',
                        align: 'center',
                        width: 150
                    },
                    {
                        title: '航空器型号',
                        width: 85,
                        key: 'aircraftModel',
                        align: 'center'
                    },
                    {
                        title: '登记号',
                        width: 85,
                        key: 'aircraftCerNumber',
                        align: 'center'
                    },
                    {
                        title: '起飞站',
                        key: 'origin',
                        align: 'center',
                        width: 74
                    },{
                        title: '起飞时刻',
                        key: 'takeOffTime',
                        align: 'center',
                        width: 200
                    },
                    {
                        title: '降落站',
                        key: 'destination',
                        align: 'center',
                        width: 74
                    },
                    {
                        title: '降落时刻',
                        key: 'landTime',
                        align: 'center',
                        width: 200
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 80,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
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
                                            this.gpflightrecordinfo(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
            }
        },
        created () {

        },
        props: {
            aircraftid: {
                type: Number,
                default: 0
            },
            employeeid:{
                type: Number,
                default: 0
            }
        },
        watch: {
            aircraftid (val) {
                this.getflightmplementrecordsDataByAircraftId(val,0);
            },
            employeeid (val) {
                this.getflightmplementrecordsData(val,0);
            }
        },
        methods: {
            changePage(val){
                if(this.aircraftid==0){
                    this.getflightmplementrecordsData(this.employeeid,val-1);
                }
                else{
                    this.getflightmplementrecordsDataByAircraftId(this.aircraftid,val-1);
                }
            },
            //根据employeeid加载飞行记录的信息
            getflightmplementrecordsData(val,val1){
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    if (val > 0) {
                        self.$http.httpGet('/app/api/flightimplementrecord/findPageFlightImplementRecordsByEmployeeId', {
                            employeeId:val,
                            pageno:val1,
                            pagesize:10
                        }).then((res) => {
                            let result ;
                            self.pagecount=res.data.totalElements;
                            //self.items = res.data;
                            result=res.data.content;
                            console.log(result,'...........findFlightImplementRecordsByEmployeeId...........');
                            self.flightimplementRecords= result;
                        })
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },

            //根据aircraftid加载飞行记录的信息
            getflightmplementrecordsDataByAircraftId(val,val1){
                let self = this;
                if(val>0){
                    self.$http.httpGet('/app/api/flightimplementrecord/findpageFlightImplementRecordsByAircraftId',{
                        aircraftId:val,
                        pageno:val1,
                        pagesize:10
                    }).then((res) => {
                        let result ;
                        self.pagecount=res.data.totalElements;
                        //self.items = res.data;
                        result=res.data.content;
                        console.log(result,'...........findFlightImplementRecordsByEmployeeId...........');
                        self.flightimplementRecords= result;
                    })
                }
            },

            //人员里面的
            gpflightrecordinfo(index){
                let id = this.flightimplementRecords[index].id;
                //  this.$router.push({path: '/pilot', query: {flightrecordId: id,type:'flightrecord'}});
                this.$emit('childinfobyflyrecordid',id,"traininfo");
            },


        }
    }
</script>
<style scoped>
        .logoimgpoilt{
                height: 19px;
                display: inline;
                /* padding: 3px; */
                vertical-align: middle;
                padding: 1px 2px;
        }
        .content .row-4 {
                float: left;
                width: 79%;
        }

        .fr {
                float: right !important;
        }

        .mod {
                margin-bottom: 15px;
        }

        .mod .tit {
                padding: 5px 7px 5px 8px;
                border: 1px solid #373737;
                font-size: 14px;
                color: rgba(255, 255, 255, 0.85);
                border-radius: 1px;
        }

        .mod .tit:before, .mod .tit:after {
                content: " ";
                display: table;
        }

        .icon-font {
                display: inline-block;
                width: 24px;
                height: 22px;
                margin-top: -2px;
                vertical-align: middle;
                background-position: center;
                background-repeat: no-repeat;
        }

        .mod .tit .icon-font {
                margin-right: 5px;
        }

        .icon-font.icon00 {
                background-image: url(../../../static/images/pilot.png);
        }
        .noimg {
                background-image: url(../../../static/img/noimgpictrue.png);
        }
        .mod .cut {
                padding: 0;
                border: 1px solid #0e0e0e;
        }

</style>