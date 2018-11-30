<template>
  <div class="container_div">
      <Form :model="formItem" :label-width="70" ref="evaluateRef">
        <FormItem label="通告编号" style="width:275px;float: left;" prop="name">
          <Input v-model="formItem.no" placeholder="请输入名称"></Input>
        </FormItem>
        <FormItem label="评估单编号" :label-width="100" style="width:275px; float: left; ">
          <Input v-model="formItem.asInformNo" placeholder="请输入名称"></Input>
        </FormItem>
        <FormItem label="执行方式" :label-width="100" style="width:275px; float: left;" prop="type">
          <Select v-model="formItem.executeWay" placeholder="请选择" style="width: 100%;">
            <Option value="工程指令">工程指令</Option>
            <Option value="技术通告">技术通告</Option>
          </Select>
        </FormItem>
        <FormItem label="处理状态" :label-width="100" style="width:275px; float: left;margin-left: 0px;">
          <Select v-model="formItem.status" placeholder="请选择" style="width: 100%;">
            <Option value="待制作">待制作</Option>
            <Option value="已制作">已制作</Option>
            <Option value="无需制作">无需制作</Option>
          </Select>
        </FormItem>
        <FormItem  style="width:100px;height: 20px; float: left; margin-left: -50px;">
          <Button type="primary" @click="init(1)">查询</Button>
        </FormItem>
        <FormItem  style="width:100px;height: 20px; float: left; margin-left: -32px;">
          <Button type="primary" @click="reset">重置</Button>
        </FormItem>
      </Form>
    <div style="width:100%; float: left">
        <div>
          <Table  :columns="columns8" :data="evaluateList" @on-row-dblclick="viewEvlauate"></Table>

        </div>
        <Page :total="pagecount" @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize" style="height: 50px;float:right;margin-top: 20px;margin-right: -10px;" show-sizer show-total ></Page>
    </div>
  </div>
</template>

<script>
   /* import IForm from "../../../../node_modules/iview/src/components/form/form.vue";*/
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                permTypes:[],
                formItem: {
                    no: '',
                    asInformNo: '',
                    executeWay: '',
                    status: ''
                },
                page:1,
                pageSize: 10,
                pagecount:0,
                evaluateList: [],
                columns8: [
                    {
                        title: '编号',
                        key: 'no',
                        align: 'center',
                    },
                    {
                        title: '适航资料编号',
                        key: 'asInformNo',
                        align: 'center'
                    },
                    {
                        title: '标题',
                        key: 'title',
                        align: 'center'
                    },
                    {
                        title: '是否执行',
                        key: 'isExecute',
                        align: 'center',
                        render: (h, params) => {
                            let re = "";
                            if (params.row.isExecute === 1) {
                                re = "执行";
                            } else if (params.row.isExecute === 0) {
                                re = "不执行";
                            }
                            return h("div", re);
                        }
                    },
                    {
                        title: '评估人',
                        key: 'evaluator',
                        align: 'center'
                    },
                    {
                        title: '执行方式',
                        key: 'executeWay',
                        align: 'center',
                    },
                    {
                        title: '处理状态',
                        key: 'status',
                        align: 'center',
                    },
                    {
                        title: '更新时间',
                        key: 'reviseTime',
                        align: 'center'
                    }
                ],
            }
        },
      /*  components: {
            IForm,
        },*/

        created: function () {
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
            init: function () {
                this.getEvaluateList(1);
            },
            viewEvlauate(obj) {
                this.$router.push({path: '/viewEvaluation',query:{id:obj.id}});
            },
            pageclick:function (obj) {
                this.page = obj;
                this.getEvaluateList(obj);
            },
            getEvaluateList:function (no) {
                let self = this;
                let profile = JSON.parse(Cookies.get("profile"));
             //   let buz = JSON.parse(Cookies.get("profile"));
                    self.$http.httpGet('/eim/api/mmis/getEvaluateListByPage',{
                        companyIds: profile.buz.companyId,
                        no: this.formItem.no,
                        asInformNo: this.formItem.asInformNo,
                        executeWay: this.formItem.executeWay,
                        status: this.formItem.status,
                        page:no,
                        pageSize: self.pageSize
                    }).then(function (response) {
                        let datainfo = response.data.content;
                        self.evaluateList=datainfo;
                        self.pagecount=response.data.totalElements;

                    }).catch(function (error) {
                        console.log(error);
                    });
            },
            pagesizeclick:function (obj) {
                this.page=obj;
                this.getEvaluateList(1);
            },
            cancel:function () {

            },
            reset:function(){
                this.$refs['evaluateRef'].resetFields();
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