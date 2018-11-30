<style>
    .topolit a{
        color:#2196F3;
        text-decoration:underline;
    }
    .airporttable{
        width: 964px;
    }
    .airportdiv{
        width: 964px;
        margin-top: 4px;
    }
    /*.airpdetail40{*/
        /*width: 40%;*/
    /*}*/
    .airpdetail25{
        width: 25%;
    }
    .airpdetail30{
        width: 30%;
    }
    .airpdetail35{
        width: 35%;
    }
    /*.airpdetail20{*/
        /*width: 20%;*/
    /*}*/
    .tablewidth{
        width: 25%;
    }

</style>
<template>
    <div class="airportdiv">
        <Table border :columns="tabledata" :data="airportlist" class="airporttable"></Table>
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
                tabledata: [
                    {
                        title: '机场名称',
                        key: 'airportName',
                        align: 'center',
                        className: 'tablewidth',
                    },
                    {
                        title: '机场等级',
                        className: 'tablewidth',
                        key: 'grade',
                        align: 'center',
                    },
                    {
                        title: '是否可用',
                        key: 'isavailable',
                        align: 'center',
                        className: 'tablewidth',
                    },
                    {
                        title: '操作',
                        key: 'action',
                        className: 'tablewidth',
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
              //              <router-link :to="{path:'/airport',query: {airportid: item.airportId}}">{{item.airportName}}</router-link>
                                    this.apdetail(params.row.airportId);
                            }
                    }
            }, '查看')
        ]);
        }
        }
        ],
                airportlist: [],
                page: 1,
                pageSize: 50,
                loading: false
            }
        },
        computed: {
        },

        props: ['companyid'],
        watch: {
            companyid:function (newval) {
       //         this.getData(newval);
            }
        },
        methods: {
            showmodel:function (){
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
            previous () {
                if (this.page === 1) {
                    this.$message('已是第一页');
                } else {
                    this.page--
                }
            },
            next () {
                this.page++
            },
            getData :function (newval) {
                let self = this;
                self.$http.httpGet('/app/api/airport/search/airportbymaincompanyid', {
                    companyid: newval,
                }).then((res) => {
                    let result = res;
                    if (result.status == 200) {
                        let temp = result.data;
                            self.airportlist = temp;
                            for(var i=0;i<self.airportlist.length;i++){
                                self.airportlist[i].isavailable=self.airportlist[i].isavailable=='0'?'是':'否';
                            }
                    }
                    else {

                    }

                    self.loading = false;
                })
            },
            apdetail(apid){
                let self=this;
                self.$emit("airportemit",apid);
            }

        }
    }
</script>

