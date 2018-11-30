<template>
    <div style="width: 1280px; height: 900px; margin-top:10px; background:white">
        <!--<span style="width:99%; display:inline-block;text-align:right; ">
                    <Button primary @click="black()"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>-->
        <div style="padding-top: 10px;">
            <Table  :columns="columns" :data="data"  @on-row-dblclick="dbDetails($event)"
                   style="width: 1260px;margin-left: 10px;"></Table>
        </div>
    </div>
</template>

<script>
    export default {
        name: "supqualification",
        data(){
            return{
                columns: [
                    {
                        title: '资质名称',
                        key: 'qualificationname',
                        align:'center',
                        width:300,
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
                                        title: params.row.qualificationname
                                    }
                                }, params.row.qualificationname)
                            ]);

                        }
                    },

                    {
                        title: '证书编号',
                        key: 'code',
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
                                        title: params.row.code
                                    }
                                }, params.row.code)
                            ]);

                        }
                    },
                    {
                        title: '生效日期',
                        key:'starttime',
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
                                        title: params.row.starttime
                                    }
                                }, params.row.starttime)
                            ]);

                        }
                    },
                    {
                        title: '失效日期',
                        key: 'endtime',
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
                                        title: params.row.endtime
                                    }
                                }, params.row.endtime)
                            ]);

                        }
                    },
                    {
                        title: '颁发单位',
                        key: 'issueorgname',
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
                                        title: params.row.issueorgname
                                    }
                                }, params.row.issueorgname)
                            ]);

                        }
                    },

                ],
                data:[],
            }
        },
        methods:{
            initsearch:function (val) {
                this.$http.httpGet('/eim/api/cq/serach/getbycompanyid?companyId='+val).then((res)=>{
                    this.data =res.data
                })
            },
            dbDetails:function (column) {
                this.$router.push({path:'/supqualdetail',query: {gaqual:JSON.stringify(column) }})
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