<style>
    .ivu-tree-title-selected {
        color: #0ac1cf;
    }
  .rtreedata{
      background: #373737;
      width: 180px;
      padding: 25px;
      border: solid 1px white;
  }
</style>
<template>
    <div class="treedata">
    <Tree :data="baseData" :show-checkbox="kst" :multiple="kset" @on-select-change="ontree" style="font-size: 16px;"></Tree>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                kst:false,
                kset:false,
                baseData: [],
                dates:[],
                titleid:'',
                deptname:'',
            }
        },created(){
            this.test();
        },methods: {
            test:function () {
                this.$http.httpGet('/app/api/sysorg/search/info',{
                    orgId:10000000190461
                }).then((res) => {
                    var list=res.data.data;
                    this.dates=list;
                    var parent=this.studata(list);
                    this.baseData.push(parent);

                }).catch(function (error) {
                    console.log(error);
                });
            },addtitle:function (list) {
              var ks=[];
              var kst="<i data-v-4ed37512=''  class='ivu-icon ivu-icon-android-checkbox'  style='font-size: 16px;color: #b0b0b0;'></i> ";
              var kj="<i data-v-4ed37512=''  class='ivu-icon ivu-icon-android-checkbox-blank'  style='font-size: 16px;'></i> ";
              for(var i=0;i<list.length;i++){
                  if(this.titleid===list[i].orgId){
                  list[i]['title']=kst+list[i].orgName;
                  }else{
                   list[i]['title']=kj+list[i].orgName;
                  }
//                  console.log(list[i].children=='undefined');
//                  if(list[i].children){
//                      //list[i]['children']='';
//                  }
                  list[i]['expand']=true;
                  ks.push(list[i]);
              }
              return ks;
            },
            convert:function (source,orgid){
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
                    //if(!("children" in tmp[item.orgId]))tmp[item.orgId].children=[];
                    if(item.orgId!=item.orgSupId){
                        if(tmp[item.orgSupId]){
                            if(!tmp[item.orgSupId].children){
                                tmp[item.orgSupId].children=[];
                            }
                            console.log(tmp[item.orgId]);
                            tmp[item.orgSupId].children.push(tmp[item.orgId]);
                        }
                        else{
                            tmp[item.orgSupId]={children:[tmp[item.orgId]]};
                        }
                    }
                }
                return tmp[orgid];
            },ontree:function (obj){
                this.titleid=obj[0].orgId;
                this.deptname=obj[0].orgName;
                var trlist=[];
                for(var i=0;i<this.dates.length;i++){
                    var ps={};
                    ps['orgId']=this.dates[i].orgId;
                    ps['orgSupId']=this.dates[i].orgSupId;
                    ps['orgName']=this.dates[i].orgName;
                    ps['orgSupName']=this.dates[i].orgSupName;
                    trlist.push(ps);
                }
                var parent=this.studata(trlist);
                this.baseData=[];
                this.baseData.push(parent);
                //obj[0].title=kst+obj[0].orgName;
            },studata:function (list) {
                var treelist=this.addtitle(list);
                var hy=this.convert(treelist,10000000190461);
                var parent={};
                var kj="<i data-v-4ed37512=''  class='ivu-icon ivu-icon-android-checkbox-blank'  style='font-size: 16px;'></i> ";
                var kst="<i data-v-4ed37512=''  class='ivu-icon ivu-icon-android-checkbox'  style='font-size: 16px;color: #b0b0b0;'></i> ";
                parent['orgName']=hy.children[0].orgSupName;
                if(this.titleid===hy.children[0].orgSupId){
                    parent['title']=kst+hy.children[0].orgSupName;
                }else{
                    parent['title']=kj+hy.children[0].orgSupName;
                }
                parent['orgId']=hy.children[0].orgSupId;
                parent['children']=hy.children;
                parent['expand']=true;
                return parent;
            }
        }

    }
</script>
