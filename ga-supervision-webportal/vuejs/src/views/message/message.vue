<template>
  <div class="main_div">
    <div style="width:100%; float: left">
      <div>
        <div class="bottom5" >
          <Table  :columns="columns8" :data="messageList" ></Table>
        </div>
        <Page :total="pagecount" show-total @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
      </div>
    </div>

  </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                permTypes:[],
                page:1,
                pageSize: 10,
                pagecount:0,
                messageList: [],
                columns8: [
                    {
                        title: '编号',
                        key: 'id',
                        align: 'center',
                    },
                    {
                        title: '用户名',
                        key: 'userName',
                        align: 'center'
                    },
                    {
                        title: '执照编号',
                        key: 'licenseno',
                        align: 'center'
                    },
                    {
                        title: '执照类型',
                        key: 'licensetype',
                        align: 'center',
                        // render: (h, params) => {
                        //     let re = "";
                        //     if (params.row.isExecute === 1) {
                        //         re = "执行";
                        //     } else if (params.row.isExecute === 0) {
                        //         re = "不执行";
                        //     }
                        //     return h("div", re);
                        // }
                    },
                    {
                        title: '到期时间',
                        key: 'expiredate',
                        align: 'center'
                    }
                ],
            }
        },

        components: {

        },
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
            init: function () {
                this.getMessageList(1);
            },
            pageclick:function (obj) {
                this.page = obj;
                this.getMessageList(obj);
            },
            getMessageList:function (no) {
                let self = this;
                let profile = JSON.parse(Cookies.get("profile"));
                //   let buz = JSON.parse(Cookies.get("profile"));
                self.$http.httpGet('/eim/api/message/getListByCompanyIds1',{
                    companyIds: profile.buz.companyId,
                    page:no,
                    pageSize: self.pageSize
                }).then(function (response) {
                    let datainfo = response.data.content;
                    self.messageList=datainfo;
                    self.pagecount=response.data.totalElements;
                    self.page=no;

                }).catch(function (error) {
                    console.log(error);
                });
            },
            pagesizeclick:function (size) {
                this.pageSize = size;
                this.getMessageList(1);
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

</style>