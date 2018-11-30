<template>
<div >
        <Table border :columns="columns" :data="checkinfos" style="width: 716px;margin-bottom: 4px"></Table>
</div>
</template>

<script>
    export default {
        components: {
        },
        data () {
            return {
                checkinfos:[],
                columns: [
                    {
                        title: '检查类型',
                        key: 'checktype',
                        align: 'center',
                        className:'tablewidth',
                    },
                    {
                        title: '检查内容',
                        className:'tablewidth',
                        key: 'checkcontent',
                        align: 'center'
                    },
                    {
                        title: '检查等级',
                        key: 'grade',
                        align: 'center',
                        className:'tablewidth',
                    },{
                        title: '检查结果',
                        key: 'checkresult',
                        align: 'center',
                        className:'tablewidth',
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
                                            this.gpcheckinfo(params.index)
                                        }
                                    }
                                }, '查看'),

                            ]);
                        }
                    }
                ],
            }
        },
        created () {
//            this.getcheckinfos();
        },
        props: ['aircraftid'],
        watch: {
            aircraftid (val) {
                if(val!=''){
                    this.getcheckinfos();
                }

            },
        },
        methods: {
            getcheckinfos(){
                let self = this;
                self.$http.httpGet('/app/api/ak/serach/getbyaircraftid', {
                    aircraftId: self.aircraftid,
                }).then(function (response) {
                    self.checkinfos = response.data;
                    for(let i = 0;i<self.checkinfos.length;i++){
                        if(self.checkinfos[i].checktype == "0"){
                            self.checkinfos[i].checktype = "定检"
                        }
                        else{
                            self.checkinfos[i].checktype = "年检"
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });


            },
            gpcheckinfo(index){
                let id = this.checkinfos[index].id;
                this.$emit('childinfobycheckinfoid',id);
            }
        }
    }
</script>
<style scoped>
    .logoimgpoilt{
        height: 19px;
        display: inline;
        /* padding: 3px; */
        vertical-align: middle;
        padding: 1px 2px;
    }
    .tablewidth{
            width: 20%;
    }
    .content .row-4 {
        float: left;
        width: 79%;
    }

    .fr {
        float: right !important;
    }

    .mod {
        margin-bottom: 15px;
    }

    .mod .tit {
        padding: 5px 7px 5px 8px;
        border: 1px solid #373737;
        font-size: 14px;
        color: rgba(255, 255, 255, 0.85);
        border-radius: 1px;
    }

    .mod .tit:before, .mod .tit:after {
        content: " ";
        display: table;
    }

    .icon-font {
        display: inline-block;
        width: 24px;
        height: 22px;
        margin-top: -2px;
        vertical-align: middle;
        background-position: center;
        background-repeat: no-repeat;
    }

    .mod .tit .icon-font {
        margin-right: 5px;
    }

    .icon-font.icon00 {
        background-image: url(../../../static/images/pilot.png);
    }
    .noimg {
        background-image: url(../../../static/img/noimgpictrue.png);
    }
    .mod .cut {
        padding: 0;
        border: 1px solid #0e0e0e;
    }

</style>