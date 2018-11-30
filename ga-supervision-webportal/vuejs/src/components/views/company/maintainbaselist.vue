<style>
    .topolit a{
        color:#2196F3;
        text-decoration:underline;
    }
    .maintainbasetable{
        width: 964px;
    }
    .maintainbasediv{
        width: 964px;
        margin-top: 4px;
    }
    .tablewidth{
        width:33%;
    }
</style>
<template>
    <div class="maintainbasediv">
        <Table border :columns="tabledata" :data="maintainbaselist" class="maintainbasetable"></Table>
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
                maintainbaseid:0,
                keyword: '',
                maintainbaselist: [],
                airportmap:{},
                airportlist:[],
                loading: false,
                tabledata: [
                    {
                        title: '维修基地名称',
                        key: 'maintainbasename',
                        align: 'center',
                        className:'tablewidth'
                    },
                    {
                        title: '机场名称',
                        key: 'airportid',
                        align: 'center',
                        className:'tablewidth'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        className:'tablewidth',
                        width:234,
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
                                    this.maintainbasedetail(params.row.maintainbaseid)
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
                this.getData(newval);
            }
        },
        created:function()
        {
            let self = this;
            self.$http.httpGet('/app/api/airport/', {
            }).then((res) => {
                let result = res;
                if (result.status == 200) {
                    let temp = result.data;
                    self.airportlist = temp;

                    self.airportmap=new Map();
                    for(var i=0;i<self.airportlist.length;i++){
                        self.airportmap.set(self.airportlist[i].airportId,self.airportlist[i].airportName);
                    }

                }
                else {
                    console.log(result.message)
                }

            self.loading = false;
        });
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
                self.$http.httpGet('/app/api/mb/search/getlistbymainrightcompanyid', {
                    companyid: newval,
                }).then((res) => {
                    let result = res;
                    console.log("result",result)
                    if (result.status == 200) {
                        self.maintainbaselist = result.data;
                        for(var i=0;i<self.maintainbaselist.length;i++){
                            self.maintainbaselist[i].airportid=self.airportmap.get(self.maintainbaselist[i].airportid);
                        }
                    }
                    else {
                    }
                    self.loading = false;
                })
            },
            maintainbasedetail(mbid){
                let self = this;
                self.$emit("mbemit",mbid);
            }
        }
    }
</script>

