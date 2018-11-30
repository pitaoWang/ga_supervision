<template>
    <div style="width: 1280px; height: 900px; margin-top:10px; background:white">
        <!--<span style="width:99%; display:inline-block;text-align:right; ">
                    <Button primary @click="black()"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>-->
        <div style="padding-top: 10px;">
            <Table  :columns="columns" :data="data"  @on-row-dblclick="dbDetails($event)"
                   style="width:1260px;margin-left: 10px;"></Table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "base",
        data(){
            return{
                columns: [
                    {
                        title: '基地名称',
                        key: 'baseName',
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
                                        title: params.row.baseName
                                    }
                                }, params.row.baseName)
                            ]);

                        }
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
                        title: '基地电话',
                        key: 'tel',
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
                                        title: params.row.tel
                                    }
                                }, params.row.tel)
                            ]);

                        }
                    },

                ],
                data:[],
            }
        },
        methods:{
            initsearch:function (val) {
                this.$http.httpGet('/eim/api/operation/search/bycompanyid?companyId='+val).then((res)=>{
                    this.data =res.data
                })
            },
            dbDetails:function (column) {
                this.$router.push({path:'/supbasedetail',query: {supbase:JSON.stringify(column) }})
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