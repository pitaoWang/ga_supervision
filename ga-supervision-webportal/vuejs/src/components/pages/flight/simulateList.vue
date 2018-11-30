<template>
  <div class="container_div">

    <Form :model="formItem"  ref="simRef">
      <FormItem label="名称" :label-width="50" style="width:300px;float: left;" prop="name">
        <Input v-model="formItem.name" placeholder="请输入名称"></Input>
      </FormItem>
      <FormItem label="类型" :label-width="50" style="width:300px;float: left;margin-left: 40px;" prop="type">
        <Select v-model="formItem.type" placeholder="请选择" style="width: 90%;">
          <Option value="飞行训练器">飞行训练器</Option>
          <Option value="飞行模拟器">飞行模拟器</Option>
        </Select>
      </FormItem>
      <FormItem label="所属企业" :label-width="100" style="width:300px;float: left;margin-left: 40px;" prop="companyId">
        <Select filterable v-model="formItem.companyId" placeholder="请选择" ref="sss">
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
        <span style="width:100%; display:inline-block;text-align:right;">

          <Button v-has="'output'" type="primary" @click="down_dialog_show()" icon="plus" style="width: 98px;height: 32px;margin:-10px 0px 20px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">导 出</Button>
        </span>

        <div class="bottom5" >
          <Table  :columns="columns7" :data="simulateList" @on-row-dblclick="viewSimulate"></Table>
        </div>
        <Page :total="pagecount" show-total @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
      </div>
    </div>

  </div>
</template>

<script>
  /*  import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";*/
    import Cookies from 'js-cookie';
    import axios from 'axios'
    import {getStore} from "../../../libs/storage";
    export default {
        data() {
            return {
                supervision1:[],
                permTypes:[],
                count:1,
                modal2:false,
                pagecount:1,
                formItem: {
                    name: '',
                    type: '',
                    companyId:'',
                },
                modal1: false,
                page:0,
                pageSize: 10,
                totalPages:1,
                simulateList: [],
                columns7: [
                    {
                        title: '设备名称',
                        key: 'equipmentName',
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
                                        title: params.row.equipmentName
                                    }
                                }, params.row.equipmentName)
                            ]);

                        }

                    },
                    {
                        title: '设备类型',

                        key: 'equipmentType',
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
                                        title: params.row.equipmentType
                                    }
                                }, params.row.equipmentType)
                            ]);

                        }
                    },
                    {
                        title: '设备型号',

                        key: 'equipmentModel',
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
                                        title: params.row.equipmentModel
                                    }
                                }, params.row.equipmentModel)
                            ]);

                        }
                    },
                    {
                        title: '生产序号',

                        key: 'productionSn',
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
                                        title: params.row.productionSn
                                    }
                                }, params.row.productionSn)
                            ]);

                        }
                    },
                    {
                        title: '设备状态',

                        key: 'equipmentStatus',
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
                                        title: params.row.equipmentStatus
                                    }
                                }, params.row.equipmentStatus)
                            ]);

                        }
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
                this.getSimulateList(obj);
            },
            viewSimulate(obj) {
                obj =JSON.stringify(obj);
                this.$router.push({path: '/viewSimulate',query:{simulate:obj}});
            },
            //下载
            down_dialog_show: function () {
                let accessToken = getStore('accessToken');
                let self = this;
                if(self.formItem.companyId ==""||typeof(self.formItem.companyId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.formItem.companyId;
                }

                axios({
                    method: 'post',
                    url: '/eim/api/simulate/export/companyid',
                    params: {
                        companyId: companyIds,
                        name: this.formItem.name,
                        type: this.formItem.type,
                    },
                    responseType: 'blob',
                    headers: {
                        'accessToken': accessToken,

                    }
                }).then(response => {
                    this.download(response)
                }).catch((error) => {

                })
                    /*self.$http.LocationHref('/eim/api/simulate/export/companyid', {
                        companyId: companyIds,
                        name: this.formItem.name,
                        type: this.formItem.type,
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
                link.setAttribute('download', '飞行模拟训练器.xlsx')

                document.body.appendChild(link)
                link.click()
            },
            cancelform: function (item) {
                this.formValidate.orgs.splice(this.formValidate.orgs.indexOf(item), 1);
            },pageclick:function (obj) {
                this.count = obj;
                this.getSimulateList(obj);
            },getSimulateList:function (obj) {
                let self = this;
                if(self.formItem.companyId ==""||typeof(self.formItem.companyId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.formItem.companyId;
                }

                    //console.info(buz.buz.companyId);
                    self.$http.httpGet('/eim/api/simulate/getListByPage',{
                        companyId:companyIds,
                        name: this.formItem.name,
                        type: this.formItem.type,
                        currentPage:obj,
                        pageSize: self.pageSize
                    }).then(function (response) {
                        let datainfo = response.data.content;

                        self.simulateList=datainfo;
                       // console.log("sssssssssss"+response.data.totalElements)
                        self.pagecount=response.data.totalElements;


                    }).catch(function (error) {
                        console.log(error);
                    });
            },pagesizeclick:function (obj) {
                this.pageSize=obj;
                let self = this;
                if(self.formItem.companyId ==""||typeof(self.formItem.companyId) == "undefined"){
                    let buz = JSON.parse(Cookies.get("profile"));

                    var companyIds = buz.buz.companyId;
                }else{
                    var companyIds = self.formItem.companyId;
                }
                    self.$http.httpGet('/eim/api/simulate/getListByPage',{
                        companyId: companyIds,
                        name: this.formItem.name,
                        type: this.formItem.type,
                        pageSize:self.pageSize,
                        currentPage:1
                    }).then(function (response) {
                        let datainfo = response.data.content;
                        self.simulateList=datainfo;
                        self.pagecount=response.data.totalElements;

                    }).catch(function (error) {
                        console.log(error);
                    });
            },reset:function(){
                //console.log("qqqqqqqqqqqqqq"+this.$refs['aircraftRef'].resetField())
                this.$refs['simRef'].resetFields();
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