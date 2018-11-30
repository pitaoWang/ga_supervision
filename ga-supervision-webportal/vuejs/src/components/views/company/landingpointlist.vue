<style>
    .topolit a{
        color:#2196F3;
        text-decoration:underline;
    }
    .langdingpointtable{
        width: 964px;
    }
    .landingpointdiv{
        width: 964px;
        margin-top: 4px;
    }
    .tablewidth{
        width: 25%;
    }
</style>
<template>
    <div class="landingpointdiv">
        <Table border :columns="tabledata" :data="landingpointlist" class="langdingpointtable"></Table>
    </div>
</template>
<script>
    //    import pDetail from '../views/pilotdetail.vue';

    export default {
        components: {
//            pDetail
        },
        data () {
            return {

                id:0,
                keyword: '',

                landingpointlist: [],
                loading: false,
                tabledata: [
                    {
                        title: '起降点名称',
                        key: 'name',
                        align: 'center',
                        className:'tablewidth'
                    },
                    {
                        title: '等级',
                        className:'tablewidth',
                        key: 'landingGrade',
                        align: 'center'
                    },
                    {
                        title: '是否可用',
                        key: 'isavailable',
                        align: 'center',
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
                                            this.landingpointDetail(params.row.id)
                                        }
                                    }
                                }, '查看')
                            ]);
                        }
                    }
                ],

            }
        },
        computed: {

        },

        props: ['companyid'],
        watch: {
            companyid:function (newval) {
 //               this.getData(newval);
            }
        },
        mounted:function () {
          //  this.getData(51);
        },
        methods: {

            mouseoutcolor:function (event) {
                event.target.parentNode.style.background="#353535"
            },
            mouseovercolor:function (event) {
                event.target.parentNode.style.background="#afafaf"
            },
            getData :function (newval) {
                let self = this;
                self.$http.httpGet('/app/api/landingpoint/search/getlistbymaincompanyid', {
                    companyid: newval,
                }).then((res) => {
                    let result = res;
                    if (result.status == 200) {
                        self.landingpointlist = result.data;
                        for(var i=0;i<self.landingpointlist.length;i++){
                            self.landingpointlist[i].isavailable=self.landingpointlist[i].isavailable=='0'?'是':'否';
                        }
                    }
                    else {
                        self.landingpointlist=[];
                    }
                    self.loading = false;
                })
            },
            landingpointDetail(landingpointid){
                let self = this;
                self.$emit("landingpointemit",landingpointid);
            }

        }
    }
</script>

