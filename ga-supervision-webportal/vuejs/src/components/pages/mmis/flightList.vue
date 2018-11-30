<template>
  <div style="width: 1280px; height: 900px; margin-top: 30px; background:white">
    <Form :model="formItem" :label-width="100" style="padding: 28px 0 0 0;" ref="flightRef">
      <FormItem label="通航企业" style="width:267px;float: left;" prop="name">
        <Select v-model="formItem.type" placeholder="请选择" style="width: 90%;">
          <Option value="飞行训练器">飞行训练器</Option>
          <Option value="飞行模拟器">飞行模拟器</Option>
        </Select>
      </FormItem>
      <FormItem label="飞行类型" style="width:267px;float: left;margin-left: 8px;">
        <Select v-model="formItem.type" placeholder="请选择" style="width: 90%;">
          <Option value="飞行训练器">飞行训练器</Option>
          <Option value="飞行模拟器">飞行模拟器</Option>
        </Select>
      </FormItem>

      <FormItem label=" 航空器注册号" style="width:267px;float: left;" prop="type">
        <Select v-model="formItem.type" placeholder="请选择" style="width: 90%;">
          <Option value="飞行训练器">飞行训练器</Option>
          <Option value="飞行模拟器">飞行模拟器</Option>
        </Select>
      </FormItem>
      <FormItem label="起飞时间" style="width:267px;float: left;" prop="name">
        <Input v-model="formItem.name" placeholder="请输入名称"></Input>
      </FormItem>
      <FormItem label="降落时间" style="width:267px;float: left;margin-left: 8px;">
        <Input v-model="formItem.jobTitle" placeholder="请输入帐号"></Input>
      </FormItem>
      <FormItem  style="width:100px;height: 20px; float: left; margin-left: -32px;">
        <Button type="primary" @click="init(1)">查询</Button>
      </FormItem>
      <FormItem  style="width:100px;height: 20px; float: left; margin-left: -32px;">
        <Button type="primary" @click="reset">重置</Button>
      </FormItem>
    </Form>
    <div>
      <div>
        <div class="bottom5" style="margin-right: 43px; margin-left: 43px">
          <Table border :columns="columns7" :data="flightList" @on-row-dblclick="viewFlight"></Table>
        </div>
        <Page :total="pagecount" @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
      </div>
    </div>

  </div>
</template>

<script>
    import word from '../company/uploadfile.vue';
    import IForm from "../../../../node_modules/iview/src/components/form/form.vue";
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                permTypes:[],
                count:1,
                modal2:false,
                formItem: {
                    name: '',
                    type: '',
                },
                modal1: false,
                page:0,
                pageSize: 10,
                totalPages:1,
                flightList: [],
                columns7: [
                    {
                        title: '飞行人员',
                        key: 'equipmentName',
                        align: 'center',
                        width: 220
                    },
                    {
                        title: '飞行日期',
                        width: 190,
                        key: 'equipmentType',
                        align: 'center'
                    },
                    {
                        title: '航空器',
                        width: 190,
                        key: 'equipmentModel',
                        align: 'center'
                    },
                    {
                        title: '起飞机场',
                        width: 290,
                        key: 'companyName',
                        align: 'center'
                    },
                    {
                        title: '降落机场',
                        width: 182,
                        key: 'equipmentStatus',
                        align: 'center'
                    },
                    {
                        title: '起飞时刻',
                        width: 182,
                        key: 'equipmentStatus',
                        align: 'center'
                    },
                    {
                        title: '降落时刻',
                        width: 182,
                        key: 'equipmentStatus',
                        align: 'center'
                    }
                ],
            }
        },

        components: {
            IForm,
            'word': word,
        },
        props: ['jobTitle'],
        created: function () {
            this.init(1);
        },
        watch: {

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
            notnull(datainfo) {
                if (datainfo == '' || datainfo == null) {
                    alert("空的");
                }
            },
            confirm(id) {
                this.$Modal.confirm({
                    title: '警告框：',
                    content: '<p>确认删除该条记录？</p>',
                    onOk: () => {
                        this.deleteSimulate(id);
                    },
                    onCancel: () => {

                    }
                });
            },
            init: function (obj) {
                this.getFlightList(obj);
            },
            viewFlight(obj) {
                this.$router.push({path: '/viewSimulate',query:{simulate:obj}});
            },
            cancelform: function (item) {
                this.formValidate.orgs.splice(this.formValidate.orgs.indexOf(item), 1);
            },
            pageclick: function (obj) {
                this.count = obj;
                this.getSimulateList(obj);
            },
            getFlightList:function (obj) {
                let self = this;
                let buz = JSON.parse(Cookies.get("profile"));
                    //console.info(buz.buz.companyId);
                    self.$http.httpGet('/eim/api/simulate/getListByPage',{
                        companyId: buz.buz.companyId,
                        name: this.formItem.name,
                        type: this.formItem.type,
                        currentPage:obj,
                        pageSize: self.pageSize
                    }).then(function (response) {
                        let datainfo = response.data.content;
                        self.flightList=datainfo;
                        self.pagecount=response.data.totalElements;
                    }).catch(function (error) {
                        console.log(error);
                    });
            },
            pagesizeclick:function (obj) {
                this.pageSize=obj;
                let self = this;
                let buz = JSON.parse(Cookies.get("profile"));
                    self.$http.httpGet('/eim/api/simulate/getListByPage',{
                        companyId: buz.buz.companyId,
                        name: this.formItem.name,
                        type: this.formItem.type,
                        pageSize:self.pageSize,
                        currentPage:1
                    }).then(function (response) {
                        let datainfo = response.data.content;
                        self.flightList=datainfo;
                        self.pagecount=response.data.totalElements;
                    }).catch(function (error) {
                        console.log(error);
                    });
            },
            reset:function(){
                this.$refs['flightRef'].resetFields();
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