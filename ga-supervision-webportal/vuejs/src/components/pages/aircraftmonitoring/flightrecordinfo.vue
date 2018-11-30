<template>
    <div id="flightrecordinfo" style="width: 1280px; height:auto; margin-top: 10px;display: inline-block;background: white;margin-bottom:10px;">
        <!--<Form style="margin-bottom:16px;">-->
            <div>
                <!--<div style="width: 100%;; display:inline-block;text-align:right;">
                    <Button @click="onclose" class="cancelBtn">返 回</Button>
                </div>-->
                <span style="width:96%; display:inline-block;text-align:right; margin-bottom: 10px; margin-top: 10px ; ">
                    <Button primary @click="onclose()" style="width: 88px;margin-right: -70px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            </div>

        <table style="width: 100%; border-spacing:0px; margin-left: 20px">
            <tbody>
            <tr>
                <td class="tr-1 tr-3">飞行记录本编号</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.no}}</td>
                <td class="tr-1 tr-3">公司名称</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.companyName}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">航空器型号</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.aircraftModel}}</td>
                <td class="tr-1 tr-3">航空器注册号</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.aircraftRegNo}}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">飞行时间</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.aircraftFlyTime}}</td>
                <td class="tr-1 tr-3">起落架次</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.riseFall}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">发动机循环数</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.loop1}}</td>
                <td class="tr-1 tr-3">机长</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.captain}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">放行人</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.releaser}}</td>
                <td class="tr-1 tr-3">放行时间</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.releaseTime}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">推出时刻</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.detrusionTime}}</td>
                <td class="tr-1 tr-3">起飞时刻</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.takeOffTime}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">降落时刻</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.landTime}}</td>
                <td class="tr-1 tr-3">关车时刻</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.closeTime}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">燃油添加量</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.addFuel}}</td>
                <td class="tr-1 tr-3">总燃油量</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.fuel}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">剩余燃油量</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.surplusFuel}}</td>
                <td class="tr-1 tr-3">工作记录</td>
                <td class="tr-2 tr-4">{{flightrecordinfo.workRecord}}</td>
            </tr>

            </tbody>
        </table>

        <Tabs class="tablerow" type="card">
            <TabPane label="排故记录">
                <div class="bottom5">
                    <Table border :columns="columns1" :data="data1"/>
                </div>
                <Page :total="pagecount1" @on-change="pageclick1" @on-page-size-change="pagesizeclick1" :page-size="pagesize1" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
            </TabPane>
            <TabPane label="换件记录">
                <div class="bottom5">
                    <Table border :columns="columns2" :data="data2"/>
                </div>
                <Page :total="pagecount2" @on-change="pageclick2" @on-page-size-change="pagesizeclick2" :page-size="pagesize2" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
            </TabPane>
            <TabPane label="AD/SB执行记录">
                <div class="bottom5">
                    <Table border :columns="columns3" :data="data3"/>
                </div>
                <Page :total="pagecount3" @on-change="pageclick3" @on-page-size-change="pagesizeclick3" :page-size="pagesize3" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
            </TabPane>
        </Tabs>
        <!-- <div class="tablerow">
            <div class="bottom5">
                <span class="tabletitle">排故记录</span>
                <Table border :columns="columns1" :data="data1"/>
            </div>
            <Page :total="pagecount1" @on-change="pageclick1" @on-page-size-change="pagesizeclick1" :page-size="pagesize1" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
        </div> -->
        <!-- <div class="tablerow">
            <div class="bottom5">
                <span class="tabletitle">换件记录</span>
                <Table border :columns="columns2" :data="data2"/>
            </div>
            <Page :total="pagecount2" @on-change="pageclick2" @on-page-size-change="pagesizeclick2" :page-size="pagesize2" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
        </div> -->
        <!-- <div class="tablerow">
            <div class="bottom5">
                <span class="tabletitle">AD/SB执行记录</span>
                <Table border :columns="columns3" :data="data3"/>
            </div>
            <Page :total="pagecount3" @on-change="pageclick3" @on-page-size-change="pagesizeclick3" :page-size="pagesize3" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
        </div> -->
        
    </div>
</template>

<script>
export default {
  name: "flightrecordinfo",
  props: ['id'],
  data() {
    return {
      flightrecordinfo: {},
      columns1: [
        {
            title: '发现故障',
            key: 'faultDescribe',
            align: 'center',
        },
        {
            title: '发现人',
            key: 'discovery',
            align: 'center',
        },
        {
            title: '处理措施',
            key: 'dealWith',
            align: 'center',
        },
        {
            title: '处理措施',
            key: 'dealWho',
            align: 'center',
        },
        {
            title: '是否保留',
            key: 'isReserve',
            align: 'center',
        },
        {
            title: '更新时间',
            key: 'reviseTime',
            align: 'center',
        },
      ],
      data1: [],
      columns2: [
        {
            title: '部件类型',
            key: 'partsType',
            align: 'center',
        },
        {
            title: '拆卸部件件号',
            key: 'brokenPartNo',
            align: 'center',
        },
        {
            title: '拆卸部件序号',
            key: 'brokenSerialNo',
            align: 'center',
        },
        {
            title: '安装部件件号',
            key: 'installPartNo',
            align: 'center',
        },
        {
            title: '安装部件序号',
            key: 'installSerialNo',
            align: 'center',
        },
        {
            title: '安装部件航材合格证',
            key: 'installPartCertificate',
            align: 'center',
        },
        {
            title: '更新时间',
            key: 'reviseTime',
            align: 'center',
        },
      ],
      data2: [],
      columns3: [
        {
            title: '适航指令/服务编号通告',
            key: 'asNo',
            align: 'center',
        },
        {
            title: '通告标题',
            key: 'asTitle',
            align: 'center',
        },
        {
            title: '通告类型',
            key: 'parentType',
            align: 'center',
        },
        {
            title: '更新时间',
            key: 'reviseTime',
            align: 'center',
        },
      ],
      data3: [],
      pageno1:1,
      pagecount1:0,
      pagesize1: 10,
      pageno2:1,
      pagecount2:0,
      pagesize2: 10,
      pageno3:1,
      pagecount3:0,
      pagesize3: 10,
        aircraft:{},
    };
  },
  created: function() {
      this.id = this.$route.query.Id;
      this.aircraft=this.$route.query.aircraft;
      this.init();
  },
  methods: {
    init: function() {
        this.getFlightRecordById(this.id);
        this.getFaultRecordByParentId(1);
        this.getReplaceRecordByParentId(1);
        this.getADRecordByParentId(1);
    },
    onclose: function() {
       this.$router.push({path:'/aircraftmonitoring/tabs',query: {value:'flightrecord',regNumber: this.flightrecordinfo.aircraftRegNo, aircraft: this.aircraft}})
    },
    getFlightRecordById: function (id) {
        var self = this;
        self.$http.httpGet('/eim/api/mmis/getFlightRecordById', {
            id: id
        }).then((res) => {
            self.flightrecordinfo = res.data;

        }).catch(function (error) {
            console.log(error);
        });
    },
    pageclick1:function (obj) {
        this.pageno1 = obj;
        this.getFaultRecordByParentId(obj);
    },
    pagesizeclick1: function(size) {
        this.pagesize1 = size;
        this.getFaultRecordByParentId(1);
    },
    getFaultRecordByParentId: function (no) {
        var self = this;
        self.$http.httpGet('/eim/api/mmis/getFaultRecordByParentId', {
            parentId: this.id,
            pageSize: self.pagesize1,
            page: no
        }).then((res) => {
            self.data1 = res.data.content;
            self.pagecount1 = res.data.totalElements;
            self.pageno1 = no;

        }).catch(function (error) {
            console.log(error);
        });
    },
    pageclick2:function (obj) {
        this.pageno2 = obj;
        this.getReplaceRecordByParentId(obj);
    },
    pagesizeclick2: function(size) {
        this.pagesize2 = size;
        this.getReplaceRecordByParentId(1);
    },
    getReplaceRecordByParentId: function (no) {
        var self = this;
        self.$http.httpGet('/eim/api/mmis/getReplaceRecordByParentId', {
            parentId: this.id,
            pageSize: self.pagesize2,
            page: no
        }).then((res) => {
            self.data2 = res.data.content;
            self.pagecount2 = res.data.totalElements;
            self.pageno2 = no;

        }).catch(function (error) {
            console.log(error);
        });
    },
    pageclick3:function (obj) {
        this.pageno3 = obj;
        this.getADRecordByParentId(obj);
    },
    pagesizeclick3: function(size) {
        this.pagesize3 = size;
        this.getADRecordByParentId(1);
    },
    getADRecordByParentId: function (no) {
        var self = this;
        self.$http.httpGet('/eim/api/mmis/getADRecordByParentId', {
            parentId: this.id,
            pageSize: self.pagesize3,
            page: no
        }).then((res) => {
            self.data3 = res.data.content;
            self.pagecount3 = res.data.totalElements;
            self.pageno3 = no;

        }).catch(function (error) {
            console.log(error);
        });
    }

    
  }
};
</script>


<style scoped>
    .tr-1 {
        font-size: 14px;
        width: 180px;
        padding-left: 13px;
        height: 45px;
        color: #606265;
        border-left: solid 1px #DCDFE6;
        border-right: solid 1px #DCDFE6;
        border-bottom: solid 1px #DCDFE6;
        background-color: #F8F8F9;
    }

    .tr-2 {
        width: 200px;
        text-align: center;
        color: #606265;
        background-color: #FFF;
    }

    table {
        border-collapse: collapse;
    }

    table tr td {
        border: 1px solid #DCDFE6;
    }

    .tr-3 {
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
    /*background-color: #FFF;*/
    /*}*/
    .tr-5 {
        background-color: #f8f8f9;
    }
#flightrecordinfo table td {
    width: 25%;
    font-size: 14px;
}
/* .tabletitle {
    margin: 20px 10px 10px 10px;
    display: block;
    font-size: 14px;
} */
.cancelBtn {
  width: 98px;
  height: 32px;
  /* margin:24px 43px 11px 0px; */
  border: 1px dashed #2b82da;
  background: #ddf4ff;
  font-size: 14px;
  color: #2fa5fb;
}
.tablerow {
     width: 100%;
    /*height: auto;
    overflow: hidden; */
    margin-top: 20px;
    margin-left: 20px;
}
#flightrecordtable .ivu-table-tbody td:nth-child(2n-1) {
    background-color: #F8F8F9;
}
</style>

