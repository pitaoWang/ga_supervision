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
        name: "supdatafile",
        data(){
            return{
                columns: [
                    /* {   title:'公司名称',
                         key:'companyId',
                     },*/
                    {
                        title: '文件名',
                        key: 'dataFileName',
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
                                        title: params.row.dataFileName
                                    }
                                }, params.row.dataFileName)
                            ]);

                        }
                    },
                    {
                        title: '文件类型',
                        key: 'dataFileType',
                        align:'center',
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.dataFileType == 1) {
                                texts = "维修工程管理手册"
                            } else if (params.row.dataFileType == 2) {
                                texts = "培训大纲"
                            } else if (params.row.dataFileType == 3) {
                                texts = "航空器维修方案"
                            }
                            else if (params.row.dataFileType == 4) {
                                texts = "航空器检查大纲"
                            }
                            else if (params.row.dataFileType == 5) {
                                texts = "可靠性方案"
                            }
                            else if (params.row.dataFileType == 6) {
                                texts = "最低设备清单"
                            }
                            else if (params.row.dataFileType == 7) {
                                texts = "运行规范"
                            }
                            else if (params.row.dataFileType == 8) {
                                texts = "运行手册"
                            }
                            else if (params.row.dataFileType == 9) {
                                texts = "飞行训练管理手册"
                            }
                            else if (params.row.dataFileType == 10) {
                                texts = "训练大纲"
                            }
                            else if (params.row.dataFileType == 11) {
                                texts = "航空理论"
                            }
                            else if (params.row.dataFileType == 12) {
                                texts = "法规政策"
                            }
                            else if (params.row.dataFileType == 13) {
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

                    /*{
                        title:'上传路径',
                        key:'uploadPath'
                    },*/
                    {
                        title: '版本号',
                        key: 'versionNumber',
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
                        title: '上传人',
                        key: 'upPerson',
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
                                        title: params.row.upPerson
                                    }
                                }, params.row.upPerson)
                            ]);

                        }
                    },
                    /*{
                        title:'创建时间',
                        key:'createTime'
                    },
                    {
                        title:'更新时间',
                        key:'upTime'
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
                                                type: "primary",
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
                          /*  }*/
                        }


                    },
                ],
                data:[]
            }
        },
        methods:{
            initsearch: function (val) {
                this.$http.httpGet('/eim/api/doc/search/listbycompanyid?companyid=' + val).then(res => {
                    if (res.status == 200) {
                        this.data = res.data;
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