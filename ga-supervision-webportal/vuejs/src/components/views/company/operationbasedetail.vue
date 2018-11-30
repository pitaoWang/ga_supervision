<template>
    <div style="width: 1280px; height: 900px; margin-top: 60px;background:white">
        <div>
        <span style="width:96%; display:inline-block;text-align:right; margin-bottom: 20px; margin-top: 40px ;">
                    <Button primary @click="black()"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>
                <!--<Table border  :columns="columns" :data="data"></Table>-->
                <table style="width: 1190px; border-spacing:0px; margin-left: 40px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                                基地详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3">基地名称</td>
                        <td class="tr-2 tr-4">{{baseName}}</td>
                        <td class="tr-1 tr-3">所属单位</td>
                        <td class="tr-2 tr-4">{{companyId}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">类型</td>
                        <td class="tr-2 tr-4">{{baseType}}</td>
                        <td class="tr-1 tr-3">基地电话</td>
                        <td class="tr-2 tr-4">{{tel}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">所在省</td>
                        <td class="tr-2 tr-4">{{provience}}</td>
                        <td class="tr-1 tr-3">所在市</td>
                        <td class="tr-2 tr-4">{{city}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">详细地址</td>
                        <td class="tr-2 tr-4">{{address}}</td>
                        <td class="tr-1 tr-3">创建时间</td>
                        <td class="tr-2 tr-4">{{createTime}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">是否主基地</td>
                        <td class="tr-2 tr-4">{{isMainbase}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="bottom5" style="margin-top:20px;">
                <Table style="width: 1190px; margin-left: 40px; margin-bottom: 40px" border :columns="columns1"
                       :data="data1"></Table>
            </div>
            <div class="bottom5" style="margin-top:12px;">
                <Table style="width: 1190px; margin-left: 40px; margin-bottom: 40px" border :columns="columns2"
                       :data="data2"></Table>
            </div>
        </div>
    </div>
</template>


<script>
    import Cookies from 'js-cookie';
    export default {
        name: "operationbasedetail",
        data() {
            return {
                companyId: '',
                baseName: '',
                baseType: '',
                tel: '',
                provience: '',
                city: '',
                address: '',
                createTime: '',
                isMainbase:'',
                columns: [
                    /* {
                         title: '运行基地id',
                         key: 'baseId'
                     },*/
                    {
                        title: '所属单位',
                        width: 100,
                        key: 'companyId',
                        align:'center',
                    },
                    {
                        title: '基地名称',
                        key: 'baseName',
                        align:'center'
                    },
                    {
                        title: '类型',
                        key: 'baseType',
                        align:'center',
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.baseType == 1) {
                                texts = "主运行基地"
                            } else if (params.row.baseType == 2) {
                                texts = "辅运行基地"
                            } else if (params.row.baseType == 3) {
                                texts = "转场基地"
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    {
                        title: '所在省',
                        key: 'provience',
                        align:'center'
                    },
                    {
                        title: '所在市',
                        key: 'city',
                        align:'center'
                    },
                    {
                        title: '详细地址',
                        key: 'address',
                        align:'center'
                    },
                    {
                        title: '基地电话',
                        key: 'tel',
                        align:'center'
                    },
                    {
                        title: '创建时间',
                        key: 'createTime',
                        align:'center'
                    }
                ],
                columns1: [
                    {
                        title: '教室类型',
                        key: 'classroomType',
                        align:'center',
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.classroomType == 1) {
                                texts = "教室"
                            } else if (params.row.classroomType == 2) {
                                texts = "讲评室"
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    {
                        title: '地址',
                        width: 500,
                        key: 'address',
                        align:'center'
                    },
                    {
                        title: '学员最大容量',
                        align:'center',
                        key: 'maxStu'
                    }
                ],
                columns2: [
                    {
                        title: '课程等级',
                        key: 'courseGrade',
                        align:'center',
                        width:200,
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.courseGrade == 1) {
                                texts = "课程等级一"
                            } else if (params.row.courseGrade == 2) {
                                texts = "课程等级二"
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    {
                        title: '课程分类',
                        width: 200,
                        align:'center',
                        key: 'courseType',
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.courseType == 1) {
                                texts = "课程一"
                            } else if (params.row.courseType == 2) {
                                texts = "课程二"
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    {
                        title: '训练大纲',
                        key: 'syllabusId',
                        align:'center',
                        width:300,
                    },
                    {
                        title: '有效期',
                        key: 'effectiveDate',
                        align:'center',
                        width:200,
                    },
                    {
                        title: '容量',
                        key: 'capacity',
                        align:'center'
                    }
                ],
                data: [],
                data1: [],
                data2: [],
            }

        },
        methods: {
            search: function (baseId) {
                this.$http.httpGet('/eim/api/operation/findbybaseid?baseId=' + baseId).then(res => {
                    //this.data.push(res.data.data);
                    /*this.$profile.getProfile().then((profile) => {*/
                    let buz = JSON.parse(Cookies.get("profile"));
                        this.companyId = buz.buz.companyName;
                        this.baseName = res.data.data.baseName;
                        let baseType = res.data.data.baseType;
                        if (baseType == 1) {
                            this.baseType = '运行基地'
                        } else if (baseType == 2) {
                            this.baseType = '飞行基地'
                        } else if (baseType == 3) {
                            this.baseType = '维修基地'
                        }else if (baseType == 3) {
                            this.baseType = '训练基地'
                        }
                        this.tel = res.data.data.tel;
                        this.provience = res.data.data.provience;
                        this.city = res.data.data.city;
                        this.address = res.data.data.address;
                        this.createTime = res.data.data.createTime;
                        this.isMainbase = res.data.data.isMainbase
                    if (this.isMainbase == 1) {
                        this.isMainbase = '是'
                    } else if (this.isMainbase == 2) {
                        this.isMainbase = '否'
                    }
                   /* })*/


                }).catch(function (error) {
                    console.log(error);
                })
            },
            searchtakecourse: function (baseId) {
                this.$http.httpGet('/eim/api/operation/findtakecoursebybaseid?baseId=' + baseId).then(res => {
                    this.data2 = res.data.data;
                }).catch(function (error) {
                    console.log(error);

                });
            },
            searchclassroom: function (baseId) {
                this.$http.httpGet('/eim/api/operation/findclassroombybaseid?baseId=' + baseId).then(res => {
                    this.data1 = res.data.data
                }).catch(function (error) {
                    console.log(error);

                });
            },
            black:function () {
                this.$router.push({path:'/company/company',query: {value:'operationbase' }})
            }
        },
        mounted() {
            this.search(this.$route.query.operationbase.baseId);
            this.searchtakecourse(this.$route.query.operationbase.baseId);
            this.searchclassroom(this.$route.query.operationbase.baseId);
        }
    }
</script>

<style scoped>
    .tr-1 {
        font-size: 14px;
        width: 180px;
        padding-left: 13px;
        height: 45px;
        color: #606265;
        border-left: solid 1px #DCDFE6;
        border-right: solid 1px #DCDFE6;
        border-bottom: solid 1px #DCDFE6;
        background-color: #F8F8F9;
    }

    .tr-2 {
        width: 200px;
        text-align: center;
        color: #606265;
        background-color: #FFF;
    }

    table {
        border-collapse: collapse;
    }

    table tr td {
        border: 1px solid #DCDFE6;
    }

    .tr-3 {
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
    /*background-color: #FFF;*/
    /*}*/
    .tr-5 {
        background-color: #f8f8f9;
    }

    .flightdispatcherimg > img {
        float: left;
        margin-left: 1%;

    }

    .infoPart1 table {
        border-collapse: collapse;
        border-spacing: 0;
        border-left: 1px solid #888;
        border-top: 1px solid #888;
        background: #efefef;
        width: 100%;
    }

    .div-button {
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }
</style>