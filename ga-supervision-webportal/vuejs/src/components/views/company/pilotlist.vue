<style>
.tablewidth{
    width:25%;
}
</style>

<template>
    <div class="landingpointdiv">
        <Table border :columns="columns6" :data="tableData" class="langdingpointtable"></Table>
    </div>
</template>
<!--<table class='m_changeList' style="width: 700px   ; border: 1px solid #0e0e0e;">-->
<!--<thead style="font-size: 14px;padding-left: 13px;width: 700px; background-color: #313233;border-bottom: solid 1px rgb(14, 14, 14);">-->
<!--<tr>-->
<!--<th width="200px" height="48px">姓名</th>-->
<!--<th width="250px" height="48px">等级</th>-->
<!--<th width="250px" height="48px">飞机总时长</th>-->
<!--</tr>-->
<!--</thead>-->
<!--<tbody>-->
<!--<tr v-for="item in PilotListVOs" style="font-size: 14px;padding-left: 13px;width: 700px; border-bottom: solid 1px rgb(14, 14, 14); background-color:#3d3d3d;">-->
<!--<td  width="200px" height="48px" align="center" class="topolit"><router-link :to="{path:'/for_pilot',query: {pilotId: item.pilotId}}">{{item.username}}</router-link></td>-->
<!--<td width="250px" height="48px" align="center">{{item.techClass}}</td>-->
<!--<td width="250px" height="48px" align="center">{{item.flySumTime}}</td>-->
<!--</tr>-->
<!--</tbody>-->
<!--</table>-->


<script>
    //    import pDetail from '../views/pilotdetail.vue';

    export default {
        components: {
//            pDetail
        },
        data() {
            return {
                columns6: [
                    {
                        title: "姓名",
                        key: "username",
                        className:'tablewidth',

                    },
                    {
                        title: "等级",
                        key: "techClass",
                        className:'tablewidth'

                    },
                    {
                        title: "飞行总时长",
                        key: "flySumTime",
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
                                            this.$router.push({path: '/pilot', query: {id: params.row.employeeId}});
                                        }
                                    }
                                }, '查看')
                            ]);
                        }
                    }

                ],
                tableData: [],
                employee_id: 0,
                keyword: '',

                PilotListVOs: [],
                page: 1,
                pageSize: 50,
                loading: false
            }
        },


        props: ['companyId'],
        watch: {
            companyId: function (newval) {
                this.getData(newval)
            }
        },
        methods: {
            showmodel: function () {
                this.isdiv = true;
            },
            closemodelchild: function (msg) {
                this.isdiv = false;
            },
            mouseoutcolor: function (event) {
                event.target.parentNode.style.background = "#353535"
            },
            mouseovercolor: function (event) {
                event.target.parentNode.style.background = "#afafaf"
            },
            previous() {
                if (this.page === 1) {
                    this.$message('已是第一页');
                } else {
                    this.page--
                }
            },
            next() {
                this.page++
            },
            getData: function (newval) {
                console.log(1254844);
                let self = this;
                self.$http.httpGet('/app/api/pilot/search/company', {
                    companyId: newval,
                    page: self.page,
                    pageSize: self.pageSize,
                    keyword: ''
                }).then((res) => {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        let temp = result.data;
                        console.log(temp);
                        self.tableData = temp;
                        console.log(self.tableData);


                        // self.pageSize = 2;
                        // self.page = ;
                        // console.log('总记录数 : ' + result.data.totalElements);
                        // console.log('总页数 : ' + result.data.totalPages);
                        // console.log('当前页 : ' + (result.data.number + 1));
                        // console.log('页记录数 : ' + result.data.size);

//                        self.employee_id = self.pilots[0].employeeId;

                    }
                    else {
                        console.log(result.message)
                    }

                    self.loading = false;
                })
            },
            pilotDetail(pilot) {
                this.employee_id = pilot.employeeId;
            }

        }
    }
</script>

<style scoped>

</style>
