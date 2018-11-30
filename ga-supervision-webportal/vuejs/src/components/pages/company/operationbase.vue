
<template >
    <div style="width: 1280px; height: 900px; margin-top: 10px;background:white">
        <div>
              <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                 <Button v-has="'add'" type="primary" icon="plus" style="width: 98px;height: 32px;margin:24px 43px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small"    v-on:click="add()">增 加</Button>
             </span>
        </div>
        <div class="bottom5" style="width: 1160px; margin-left: 10px">
            <Table border  @on-row-dblclick="dbDetails($event)"  :columns="columns" :data="data" ></Table>
        </div>


    </div>
</template>
<script>

    import Cookies from 'js-cookie';
    export default {
        data () {
            return {
                permTypes:[],
                columns: [
                   /* {
                        title: '运行基地id',
                        key: 'baseId',
                    },*/
                    /*{
                        title: '所属单位',
                        width:100,
                        key: 'companyId',
                        render: (h, params) => {
                            let texts = this.companyName;
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },*/
                    {
                        title: '基地名称',
                        key: 'baseName',
                        align:'center'
                    },
                    {
                        title: '类型',
                        key: 'baseType',
                        align:'center',
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.baseType == 1) {
                                texts = "运行基地"
                            } else if (params.row.baseType == 2) {
                                texts = "飞行基地"
                            } else if (params.row.baseType == 3) {
                                texts = "维修基地"
                            }else if (params.row.baseType == 4) {
                                texts = "训练基地"
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                   /* {
                        title: '所在省',
                        key: 'provience'
                    },
                    {
                        title: '所在市',
                        key: 'city'
                    },
                    {
                        title: '详细地址',
                        key: 'address'
                    },*/
                    {
                        title: '基地电话',
                        key: 'tel',
                        align:'center'
                    },
                   /* {
                        title: '创建时间',
                        key: 'createTime'
                    },*/
                    {
                        title: "操作",
                        key: "action",
                        align:'center',
                        width: 120,
                        align: "center",
                        render: (h, params) => {
                            if(this.permTypes.includes("add")) {
                                return h("div", [
                                    h(
                                        "Button",
                                        {
                                            props: {
                                                type: "error",
                                                size: "small"
                                            },
                                            style: {
                                                marginRight: '16px',
                                                marginTop: '4px',
                                                width: '30px',
                                                height: '30px',
                                                background: 'url(../../../../static/images/icon_operate_edit.png) no-repeat',
                                                border: 'none'
                                            },
                                            on: {
                                                click: () => {
                                                    //console.log(params.row.baseId);
                                                    this.updateOperation(params.row);


                                                }
                                            }

                                        },
                                        ""
                                    ),
                                    /*   h(
                                    "Button",
                                    {
                                        props: {
                                            type: "error",
                                            size: "small"
                                        },

                                        on: {
                                            click: () => {
                                                //console.log(params.row.baseId);
                                                this.searchDetails(params.row);


                                            }
                                        }

                                    },
                                    "查看详情"
                                ),*/
                                    h(
                                        "Button",
                                        {
                                            props: {
                                                type: "error",
                                                size: "small"
                                            },
                                            style: {
                                                marginLeft: '4px',
                                                marginTop: '4px',
                                                width: '30px',
                                                height: '30px',
                                                background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                                border: 'none'
                                            },
                                            on: {
                                                click: () => {
                                                    console.log(params.index);
                                                    this.deleteOperation(params.row.baseId, params.index);


                                                }
                                            }

                                        },
                                        ""
                                    )
                                ]);
                            }
                        }
                    }
                ],
                data:[],
                companyName:''
            }
        },
        methods:{
            //初始化查询
            search:function(){
                /*this.$profile.getProfile().then((profile) => {*/
                let buz = JSON.parse(Cookies.get("profile"));
                    let companyId=buz.buz.companyId
                    this.$http.httpGet('/eim/api/operation/search/bycompanyid?companyId='+companyId).then(response=> {
                        this.data =response.data;
                        //console.log(this.data)
                    }).catch(function (error) {
                        console.log(error);

                    });

                /*})*/

            },
            //跳转到查询详情页面
            searchDetails:function (v) {
               this.$router.push({path:'/operationbasedetail',query: {operationbase:v }})
            },
            //跳转到添加页面
            add:function () {
                this.$router.push({path:'/operationbaseadd'});
            },
            updateOperation:function(v){
                this.$router.push({path:'/operationbaseupdate',query:{operationbase:v}})
            },
            deleteOperation:function (baseId,index) {
                this.$http.httpDelete('/eim/api/operation/delete?baseId='+baseId).then(response=> {
                   if(response.data.resultCode==200){
                       this.data.splice(index,1);
                       this.$Message.info("删除成功")
                   }
                }).catch(function (error) {
                    console.log(error);

                });
            },
            dbDetails:function (column) {
                this.$router.push({path:'/operationbasedetail',query: {operationbase:column }})
            },
            initMeta() {
                let permTypes = this.$route.meta.permTypes;
                if (permTypes !== null && permTypes !== undefined) {
                    this.permTypes = permTypes;
                }
            },

        },

        mounted() {
            this.search();
            /*this.$profile.getProfile().then((profile) => {*/
            let buz = JSON.parse(Cookies.get("profile"));
                this.companyName = buz.org.orgName;
            /*})*/
            this.initMeta();
        }
    }
</script>

<style scoped>

</style>