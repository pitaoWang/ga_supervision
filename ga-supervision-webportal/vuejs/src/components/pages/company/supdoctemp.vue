<template>
    <div style="width: 1280px; height: 900px; margin-top: 10px;background:white">
        <!--<span style="width:99%; display:inline-block;text-align:right; ">
                    <Button primary @click="black()"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>-->
        <div class="bottom5" style="width: 1260px; margin-left: 10px;margin-top: 10px;">
            <Table  :columns="columns" :data="data"></Table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "supdoctemp",
        data(){
            return{
                columns:[
                    /* {   title:'公司名称',
                         key:'companyId',
                     },*/
                    {
                        title:'模板名称',
                        key:'templateName',
                        align:'center',
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
                                        title: params.row.templateName
                                    }
                                }, params.row.templateName)
                            ]);

                        }
                    },
                    {
                        title:'模板类型',
                        key:'templateType',
                        align:'center',
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.templateType == 1) {
                                texts = "维修工程管理手册"
                            } else if (params.row.templateType == 2) {
                                texts = "培训大纲"
                            } else if (params.row.templateType == 3) {
                                texts = "航空器维修方案"
                            }
                            else if (params.row.templateType == 4) {
                                texts = "航空器检查大纲"
                            }
                            else if (params.row.templateType == 5) {
                                texts = "可靠性方案"
                            }
                            else if (params.row.templateType == 6) {
                                texts = "最低设备清单"
                            }
                            else if (params.row.templateType == 7) {
                                texts = "运行规范"
                            }
                            else if (params.row.templateType == 8) {
                                texts = "运行手册"
                            }
                            else if (params.row.templateType == 9) {
                                texts = "飞行训练管理手册"
                            }
                            else if (params.row.templateType == 10) {
                                texts = "训练大纲"
                            }
                            else if (params.row.templateType == 11) {
                                texts = "航空理论"
                            }
                            else if (params.row.templateType == 12) {
                                texts = "法规政策"
                            }
                            else if (params.row.templateType == 13) {
                                texts = "视频资料"
                            }
                            /*return h('div', {
                                props: {},
                            }, texts)*/
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
                                        title: texts
                                    },
                                    props: {},
                                },texts)
                            ]);
                        }
                    },
                    {
                        title:'版本号',
                        key:'versionNumber',
                        align:'center',
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
                                        title: params.row.versionNumber
                                    }
                                }, params.row.versionNumber)
                            ]);

                        }
                    },
                    {
                        title:'上传人',
                        key:'uploadingPerson',
                        align:'center',
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
                                        title: params.row.uploadingPerson
                                    }
                                }, params.row.uploadingPerson)
                            ]);

                        }
                    },
                    /*{
                        title:'上传路径',
                        key:'uploadPath'
                    },*/

                    /*{
                        title:'创建时间',
                        key:'createTime'
                    },
                    {
                        title:'更新时间',
                        key:'updateTime'
                    },*/
                    {
                        title: "操作",
                        key: "action",
                        width: 120,
                        align: "center",
                        render: (h, params) => {
                            /*if(this.permTypes.includes("add")) {*/
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
                                                background: 'url(../../../../static/images/icon_operate_download.png) no-repeat',
                                                border: 'none'
                                            },
                                            on: {
                                                click: () => {
                                                    window.location.href = this.imageUrl+params.row.uploadPath;
                                                }
                                            }

                                        },
                                        ""
                                    ),

                                ]);
                         /*   }*/
                        }


                    },
                ],
                data:[]
            }
        },
        methods:{
            initsearch:function(val){
                this.$http.httpGet('/eim/api/doctemplate/findbycompanyid?companyId='+val).then(res=>{
                    if(res.data.resultCode==200){
                        this.data = res.data.data;
                    }
                })
            },
            black:function () {
                this.$router.push({path:'/black'})
            }
        },
        mounted(){
                this.initsearch(JSON.parse(this.$route.query.gacompany).companyId)
        }
    }
</script>

<style scoped>

</style>