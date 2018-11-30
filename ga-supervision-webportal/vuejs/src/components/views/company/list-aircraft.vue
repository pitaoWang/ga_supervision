<style>
.aircraftRegid a{
color: #2196F3;
    text-decoration:underline;
}
    .tablewidth{
        width:25%
    }
</style>
<template>
    <div class="landingpointdiv">
        <Table border :columns="columns6" :data="tableData" class="langdingpointtable" style="width: 700px"></Table>
    </div>
    <!--<table class='aircraftList' style="width: 700px;border: 1px solid #0e0e0e;">-->
        <!--<thead style="font-size: 14px;padding-left: 13px;width: 700px; background-color: #313233;border-bottom: solid 1px rgb(14, 14, 14);">-->
        <!--<tr>-->
            <!--<th width="200px" height="48px">注册号</th>-->
            <!--<th width="250px" height="48px">型别</th>-->
            <!--<th width="250px" height="48px">所属企业名称</th>-->
        <!--</tr>-->
        <!--</thead>-->
        <!--<tbody>-->
        <!--<tr v-for="item in items" style="font-size: 14px;padding-left: 13px;width: 700px; border-bottom: solid 1px rgb(14, 14, 14); background-color:#3d3d3d;">-->
            <!--<td  width="200px" height="48px" align="center" class="aircraftRegid" ><router-link :to="{path:'/aircraft',query: {aircraftId: item.aircraftId}}">{{item.aircraftRegid}}</router-link></td>-->
            <!--<td width="250px" height="48px" align="center">{{item.aircraftType}}</td>-->
            <!--<td width="250px" height="48px" align="center">{{item.companyId.name}}</td>-->
        <!--</tr>-->
        <!--</tbody>-->
    <!--</table>-->
</template>
<script>
//    import addaircraft from './addaircraft'
    export default {
        data () {
            return {
                columns6: [
                    {
                        title: "航空器型号",
                        key: "aircraftModel",
                        className:'tablewidth'

                    },
                    {
                        title: "型别",
                        key: "aircraftType",
                        className:'tablewidth'

                    },
                    {
                        title: "类别",
                        key: "aircraftClass",
                        className:'tablewidth'

                    },
                    {
                        title: '操作',
                        key: 'action',
                        className:'tablewidth',
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
                                            this.$router.push({path: '/aircraft', query: {aircraftId: params.row.aircraftId}});
                                        }
                                    }
                                }, '查看')
                            ]);
                        }
                    }

                ],
                tableData: [],
                urltoaircft:'',
                modal1: false,
                isdiv:false,
                page: 0,
                pageSize: 10,
                last:false,
                first:true,
                serchcontect:'',
                items: [

                ]
            }
        },
        components:{
//            addaircraft
        },
        mounted(){
            this.aircraftlist();
        },
        props: ['companyId'],
        watch: {
            companyId:function (newval) {
                this.aircraftlist(newval)
            }
        },
        methods:{
            doupclick:function () {
                if(this.first){
                    this.page=0;
                }else{
                    this.page=this.page-1;
                    this.aircraftlist();
                }
            },
            doclick:function () {
                if(this.last) {

                }else {
                    this.page = this.page + 1;
                    this.aircraftlist();
                }
             },
            aircraftlist:function (newval) {
                let self = this;
                self.loading = true;
               if(typeof(newval)=='undefined'){
                   return
               }
                self.$http.httpGet('/app/api/aircraft/search/companyname', {
                    pageno: self.page,
                    pagesize: self.pageSize,
                    companyids:newval,
                    copnameoricn: this.serchcontect
                }).then((res) => {

                    self.loading = false;
                    self.tableData=res.data.content;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            infoclick:function(obj){
                this.$emit('child-info-id',obj);
            }, showmodel:function (){
                this.isdiv=true;
            },
            closemodelchild:function (msg) {
                this.isdiv=false;
            },
            mouseoutcolor:function (event) {
                event.target.parentNode.style.background="#353535"
            },
            mouseovercolor:function (event) {
                event.target.parentNode.style.background="#afafaf"
            },
            searchcrad:function () {
                this.aircraftlist();
            },

    }

    }
</script>

