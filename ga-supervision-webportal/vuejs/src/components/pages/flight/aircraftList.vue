<template>
  <div class="container_div">

    <Form :model="aircraft"   ref="aircraftRef">
      <FormItem label="航空器注册号" :label-width="100" style="width:300px;float: left;" prop="regNumber">
        <Input v-model="aircraft.regNumber" placeholder="请输入注册号"></Input>
      </FormItem>
      <!--<FormItem label="人员类型" style="width:267px;float: left;margin-left: 8px;">
          <Input v-model="formItem.jobTitle" placeholder="请输入帐号"></Input>
      </FormItem>-->

      <FormItem label="航空器状态" :label-width="90" style="width:300px;float: left;margin-left: 40px;" prop="aircraftStatus">
        <Select v-model="aircraft.aircraftStatus" placeholder="请选择"  >
          <Option value="在用">在用</Option>
          <Option value="大修">大修</Option>
          <Option value="报停">报停</Option>
          <Option value="报废">报废</Option>
          <Option value="维护">维护</Option>
        </Select>
      </FormItem>

      <FormItem label="所属企业" :label-width="70" style="width:300px;float: left;margin-left: 40px;" prop="companyId">
        <Select filterable  v-model="aircraft.companyId" placeholder="请选择" ref="sss">
          <Option v-for="site in supervision1" :key="site.companyId" :value="site.companyId">{{ site.name }}</Option>
        </Select>
      </FormItem>

      <FormItem  style="width:100px;height: 20px; float: left; margin-left: 40px;">
        <Button type="primary" @click="init(1)">查询</Button>
      </FormItem>
      <FormItem  style="width:100px;height: 20px; float: left; margin-left: -32px;">
        <Button type="primary" @click="reset">重置</Button>
      </FormItem>
    </Form>
    <div>
      <div>
        <div>
                <span style="width:100%; display:inline-block;text-align:right;">
                     <Button v-has="'output'" type="primary" @click="down_dialog_show()" icon="plus" style="width: 98px;height: 32px; margin:-10px 0px 20px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">导 出</Button>
                </span>

          <div class="bottom5" >
            <Table  :columns="columns7" :data="aircraftList"  @on-row-dblclick="viewAircraft"></Table>
          </div>
          <Page :total="pagecount" show-total @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pagesize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
        </div>
      </div>
    </div>

  </div>
</template>

<script>

   /* import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";*/
    import Cookies from 'js-cookie';
    import axios from 'axios'
    import {getStore} from "../../../libs/storage";
    export default {
        data() {
            return {
                supervision1:[],
                permTypes:[],
                aircraft: {
                    regNumber: '',
                    aircraftStatus: '',
                    companyId:'',
                },

                pagesize:10,
                pagecount:0,
                count:1,
                aircraftList: [],
                columns7: [
                    {
                        title: '机型编号',

                        key: 'typeNumber',
                        align: 'center',
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
                                        title: params.row.typeNumber
                                    }
                                }, params.row.typeNumber)
                            ]);

                        }
                    },{
                        title: '注册号',
                        key: 'regNumber',
                        align: 'center',
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
                                        title: params.row.regNumber
                                    }
                                }, params.row.regNumber)
                            ]);

                        }

                    },
                    {
                        title: '航空器拥有形式',

                        key: 'aircraftModel',
                        align: 'center',
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
                                        title: params.row.aircraftModel
                                    }
                                }, params.row.aircraftModel)
                            ]);

                        }
                    },
                    {
                        title: '企业名称',

                        key: 'companyName',
                        align: 'center',
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
                                        title: params.row.companyName
                                    }
                                }, params.row.companyName)
                            ]);

                        }
                    },{
                        title: '监管局',

                        key: 'supervisionName',
                        align: 'center',
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
                                        title: params.row.supervisionName
                                    }
                                }, params.row.supervisionName)
                            ]);

                        }
                    },{
                        title: '航空器状态',

                        key: 'aircraftStatus',
                        align: 'center',
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
                                        title: params.row.aircraftStatus
                                    }
                                }, params.row.aircraftStatus)
                            ]);

                        }
                    }
                ],

            }
        },

       /* components: {
            IForm,
            'word': word,
        },*/

        created: function (newval) {
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
        mounted(){
            this.initMeta();
        },
        methods: {
            initMeta() {
                let permTypes = this.$route.meta.permTypes;
                if (permTypes !== null && permTypes !== undefined) {
                    this.permTypes = permTypes;
                }
            },


            init: function (obj) {
                this.getAircraftList(obj);
            },
            viewAircraft:function (obj) {

                this.$router.push({path: '/aircraftmonitoring/tabs',query:{regNumber:obj.regNumber, value:obj.id, aircraft:JSON.stringify(obj)}});
            },
            //下载
            down_dialog_show: function () {
                let accessToken = getStore('accessToken');
                let self = this;
                if(typeof(self.aircraft.aircraftStatus) != "undefined"){
                    var status = self.aircraft.aircraftStatus;
                }else{
                    var status = "";
                }
                if(self.aircraft.companyId ==""||typeof(self.aircraft.companyId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.aircraft.companyId;
                }

                axios({
                    method: 'post',
                    url: '/eim/api/aircraft/search/downAircraftExcel',
                    params: {
                        companyId:companyIds,
                        regNumber:self.aircraft.regNumber,
                        aircraftStatus:status,
                    },
                    responseType: 'blob',
                    headers: {
                        'accessToken': accessToken,

                    }
                }).then(response => {
                    this.download(response)
                }).catch((error) => {

                })

               /* self.$http.LocationHref('/eim/api/aircraft/search/downAircraftExcel', {
                        companyId:companyIds,
                        regNumber:self.aircraft.regNumber,
                        aircraftStatus:status,
                    }).then(function (response) {
                    }).catch(function (error) {
                        console.log(error);
                    });*/


            },
            download (data) {
                if (!data) {
                    return
                }
                let url = window.URL.createObjectURL(new Blob([data]))
                let link = document.createElement('a')
                link.style.display = 'none'
                link.href = url
                link.setAttribute('download', '航空器列表.xlsx')

                document.body.appendChild(link)
                link.click()
            },
            pageclick:function (obj) {
                this.count = obj;
                this.getAircraftList(obj);
            },
            getAircraftList:function (obj) {

                let self = this;
                if(typeof(self.aircraft.aircraftStatus) != "undefined"){
                    var status = self.aircraft.aircraftStatus;
                }else{
                    var status = "";
                }

                if(self.aircraft.companyId ==""||typeof(self.aircraft.companyId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.aircraft.companyId;
                }
                    self.$http.httpGet('/eim/api/aircraft/search/findAircraftByInfo',{
                        companyId: companyIds,
                        regNumber:self.aircraft.regNumber,
                        aircraftStatus:status,
                        pageSize:self.pagesize,
                        currentPage:obj
                    }).then(function (response) {

                        let datainfo = response.data.data;

                        self.aircraftList=datainfo;
                        if(typeof (response.data.data[0])=="undefined"||response.data.data[0].total==null){

                            self.pagecount= 0;
                        }else{

                            self.pagecount=response.data.data[0].total;
                        }

                    }).catch(function (error) {
                        console.log(error);
                    });

            },pagesizeclick:function (obj) {

                this.pagesize=obj;
                let self = this;
                if(typeof(self.aircraft.aircraftStatus) != "undefined"){
                    var status = self.aircraft.aircraftStatus;
                }else{
                    var status = "";
                }
                if(self.aircraft.companyId ==""||typeof(self.aircraft.companyId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.aircraft.companyId;
                }
                    self.$http.httpGet('/eim/api/aircraft/search/findAircraftByInfo',{
                        companyId: companyIds,
                        regNumber:self.aircraft.regNumber,
                        aircraftStatus:status,
                        pageSize:self.pagesize,
                        currentPage:1
                    }).then(function (response) {

                        let datainfo = response.data.data;
                        self.aircraftList=datainfo;

                        if(typeof (response.data.data[0])=="undefined"||response.data.data[0].total==null){

                            self.pagecount= 0;
                        }else{

                            self.pagecount=response.data.data[0].total;
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
            },reset:function(){
                //console.log("qqqqqqqqqqqqqq"+this.$refs['aircraftRef'].resetField())
                this.$refs['aircraftRef'].resetFields();

                this.$refs.sss.reset();
            }

        }
    }
</script>

<style scoped>
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

  .form{
    width: 900px;
    background-color: #363636
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