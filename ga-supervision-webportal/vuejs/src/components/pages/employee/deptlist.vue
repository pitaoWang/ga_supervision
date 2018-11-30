<template >

  <div id="example" style="width: 965px; margin-left: 12px;">
    <div style="background: #2e82dc;height: 36px;padding-top: 6px;margin: 22px 0px 3px 0px;padding: 6px 0 0 0;">
      <img src="../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
      <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">系统管理-组织架构</span>
    </div>
    <div style="border: solid 1px rgba(1, 107, 217, 0.5);background: rgba(193, 220, 248, 0.5); position: absolute;bottom: 0px;top: 76px;width: 965px;right: 26px;">  </div>
    <div style="padding: 16px 44px 0px 44px;">
    <zk-table
      ref="table"
      sum-text="sum"
      index-text="#"
      :data="treedata"
      :columns="columns"
      :stripe="props.stripe"
      :border="props.border"
      :show-header="props.showHeader"
      :show-summary="props.showSummary"
      :show-row-hover="props.showRowHover"
      :show-index="props.showIndex"
      :tree-type="props.treeType"
      :is-fold="props.isFold"
      :expand-type="props.expandType"
      :selection-type="props.selectionType">
      <template slot="eidtli" scope="scope">
      <router-link :to="{ path:'/orginfo', query: {org:scope.row}}" style="color: #d3d3d3;" v-if="scope.row.orgId!==dataorgid" >
          <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style="width:60px;background:#1ca44d;border:none" ><span>查看</span></button>
      </router-link>
      <router-link :to="{ path:'/for_org', query: { org: scope.row,deflag:1 }}" style="color: #d3d3d3;" >
       <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style=" width:79px;background: #2b82da;border:none" ><span>增加下级</span></button>
      </router-link>
        <router-link :to="{ path:'/for_org', query: { org: scope.row,deflag:2}}" style="color: #d3d3d3;" v-if="scope.row.orgId!==dataorgid">
          <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style="width:60px;background:#db9033;border:none" ><span>修改</span></button>
        </router-link>
        <button type="button" class="ivu-btn ivu-btn-error ivu-btn-small" style="width:60px;background:#dc371c;border:none"  @click="confirm(scope.row.orgId)" v-if="scope.row.orgId!==dataorgid"><span>删除</span></button>
      </template>
    </zk-table>
    </div>
    </div>
</template>

<script>
  import ZkSwitch from 'vue-table-with-tree-grid';
  import ZkTable from 'vue-table-with-tree-grid'
  export default {
    name: 'example',
    components: {
      ZkSwitch,
       ZkTable
    },
    data() {
      return {
        props: {
          stripe: true,
          border: true,
          showHeader: true,
          showSummary: false,
          showRowHover: true,
          showIndex: false,
          treeType: true,
          isFold: false,
          expandType: false,
          selectionType: false,
        },
        treedata: [],
        orguid:'',
        dataorgid:'',
        columns: [
          {
            label: '组织名称',
            prop: 'orgName',
            width: '260px',
            headerAlign:'center'
          },
          {
            label: '创建时间',
            prop: 'createtime',
            width: '140px',
            headerAlign:'center',
            align:'center'
          },
          {
                label: '人数(人)',
                prop: 'count',
                width: '90px',
                headerAlign:'center',
               align:'center'
           },
           {
                label: '操作',
                type:'template',
                width:'280px',
                template:'eidtli',
                headerAlign:'center',
                align:'center'
            },
        ],
      };
    },
    computed: {
      propList() {
        return Object.keys(this.props).map(item => ({
          name: item,
        }));
      },
    },
  created(){
        this.test();
  },
    methods: {
        confirm(index) {
            this.$Modal.confirm({
                title: '警告框：',
                content: '<p>确认删除该条记录？</p>',
                onOk: () => {
                    this.findbyorg(index);
                },
                onCancel: () => {

                }
            });
        },
        init:function () {
            let self = this;
            self.$profile.getProfile().then((profile) =>{
                let orgid=profile.org.id;
                self.$http.httpGet('/eim/api/dept/'+orgid, {
                }).then(function (response) {
                    console.log(response);
                    self.treedata.push(response.data);
                }) .catch(function (error) {
                    console.log(error);
                });
            }, (error) => {
                console.log('error', error);
            });

        },test:function () {
            let self = this;
            //获取用户名
            self.$profile.getProfile().then((profile) => {
                self.orguid=profile.org.orgId;
                self.dataorgid=profile.org.orgId;
                this.$http.httpGet('/eim/api/sysorg/search/info', {
                    //roleid:profile.id,
                    orgId: profile.org.orgId
                }).then((res) => {
                    var list = res.data.data;
                    var sum=0;
                    for(var i=0;i<list.length;i++){
                        sum+=list[i].count;
                    }
                    var hy =[];
                    var parent = {};
                    if(list.length>0){
                        hy=this.convert(list, this.orguid);
                        parent['orgName'] = profile.org.orgName;
                        parent['orgId'] = profile.org.orgId;
                        parent['count'] = sum;
                        parent['children'] = hy.children;
                        this.treedata=[];
                        this.treedata.push(parent);
                    }else{
                        parent['orgName'] = profile.org.orgName;
                        parent['orgId'] = profile.org.orgId;
                        parent['count'] = sum;
                        this.treedata=[];
                        this.treedata.push(parent);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            });
        },convert:function (source,orgid){
            var tmp={},parent,n;
            for(n in source){
                var item=source[n];
                if(item.orgId==item.orgSupId){
                    parent=item.orgId;
                }
                if(!tmp[item.orgId]){
                    tmp[item.orgId]={};
                }
                tmp[item.orgId]=item;
                tmp[item.orgId]=item;
                if(!("children" in tmp[item.orgId]))tmp[item.orgId].children=[];
                if(item.ID!=item.orgSupId){
                    if(tmp[item.orgSupId]){
                        tmp[item.orgSupId].children.push(tmp[item.orgId]);
                    }
                    else{
                        tmp[item.orgSupId]={children:[tmp[item.orgId]]};
                    }
                }
            }
            console.log(tmp[this.orguid]);
            return tmp[orgid];
        },deleteinfo:function (obj) {
            let left=this;
            left.$http.httpDelete('/eim/api/sysorg/'+obj,{
              //  orgId:obj
            }).then((res) => {
                //this.$Message.error('删除成功!');
                alert("删除成功");
                this.test();
            }).catch(function (error) {
                alert("删除失败");
                //this.$Message.error('删除失败!');
            });
        },
        //根据组织ID获取人员
        findbyorg:function (obj) {
            let self=this;
            self.$http.httpGet('/eim/api/sysuser/search/byorgid',{
                orgid:obj,
                fullname:"",
                account:"",
                pageSize:10,
                currentPage:0
            }).then((res) => {
                if(res.data.data.list.length==0){
                  this.deleteinfo(obj);
                }else{
                    //this.$Message.error('该组织存在用户，不可删除!');
                    alert('该组织存在用户，不可删除!');
                }
            }).catch(function (error) {
                console.log(error);
            });
        }
    },
  };
</script>

<style>
  *  {
    margin: 0;
    padding: 0;
  }
  .switch-list {
    margin: 20px 0;
    list-style: none;
    overflow: hidden;
  }
  .switch-item {
    margin: 20px;
    float: left;
  }

  .zk-table {
    color: #2480dd !important;
    border: 1px solid rgba(1, 107, 217, 0.5) !important;
    background-color: #e5f1fc !important;
  }

  .zk-table__header-row {
    background-color: #91c7fa !important;
    border-bottom: 1px solid rgba(1, 107, 217, 0.5) !important;
  }

  .zk-table--row-hover{
    background-color: rgba(1, 107, 217, 0) !important;
  }

  .zk-table__body-row {
    height: 34px !important;
  }

  .zk-table__cell-inner {
    font-size: 13px;
  }

  .zk-table--stripe-row {
    background-color: #ddecfc !important;
  }

  .zk-table__body-row:not(:first-of-type) {
    border-top: 1px solid rgba(1, 107, 217, 0.5) !important;
  }

  .zk-table--border-cell:not(:last-of-type) {
    border-right: 1px solid rgba(1, 107, 217, 0.5) !important;
  }
</style>