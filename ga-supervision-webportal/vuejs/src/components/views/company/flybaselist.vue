<style>
    .topolit a{
        color:#2196F3;
        text-decoration:underline;
    }
    .flybasetable{
        width: 964px;
    }
    .flybasediv{
        width: 964px;
        margin-top: 4px;
    }
    .tablewidth{
        width: 33%;
    }
</style>
<template>
    <div class="flybasediv">
        <Table border :columns="tabledata" :data="flybaselist" class="flybasetable"></Table>
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
                        title: '飞行基地名称',
                        key: 'flybasename',
                        align: 'center',
                        className:'tablewidth',
                    },
                    {
                        title: '类型',
                        className:'tablewidth',
                        key: 'baseType',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        className:'tablewidth',
                        align: 'center',
                        width:234,
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
                                    this.fbdetaiil(params.row.flybaseid);
                            }
                    }
            }, '查看')
        ]);
        }
        }
        ],
            flybaseid:0,
                keyword: '',

                flybaselist: [],
                loading: false
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
        methods: {

//            showmodel:function (){
//                this.isdiv=true;
//            },
//            closemodelchild:function (msg) {
//                this.isdiv=false;
//            },
            mouseoutcolor:function (event) {
                event.target.parentNode.style.background="#353535"
            },
            mouseovercolor:function (event) {
                event.target.parentNode.style.background="#afafaf"
            },
            getData :function (newval) {
                let self = this;
                self.$http.httpGet('/app/api/flybase/search/listbymaincompanyid', {
                    companyid: newval,
                }).then((res) => {
                    let result = res;

                    if (result.status == 200) {
                        self.flybaselist = result.data;
                    }
                    else {

                    }
                    self.loading = false;
                })
            },
            fbdetaiil(fbid){
                this.$emit("fbemit",fbid);
            }

        }
    }
</script>

