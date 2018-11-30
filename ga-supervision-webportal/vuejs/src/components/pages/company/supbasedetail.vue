<template>
    <div style="width: 1280px; height: 900px; background:white">
        <div>
            <span style="width:95%; display:inline-block;text-align:right; margin-top: 10px; margin-bottom: 10px ;padding-right: 5px;">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>
                <!--<Table border stripe :columns="columns" :data="data"></Table>-->
                <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                            基地详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3">基地名称</td>
                        <td class="tr-2 tr-4">{{supbase.baseName}}</td>
                        <td class="tr-1 tr-3">所属单位</td>
                        <td class="tr-2 tr-4">{{name}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">类型</td>
                        <td class="tr-2 tr-4">{{supbase.baseType}}</td>
                        <td class="tr-1 tr-3">基地电话</td>
                        <td class="tr-2 tr-4">{{supbase.tel}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">所在省</td>
                        <td class="tr-2 tr-4">{{supbase.provience}}</td>
                        <td class="tr-1 tr-3">所在市</td>
                        <td class="tr-2 tr-4">{{supbase.city}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">详细地址</td>
                        <td class="tr-2 tr-4">{{supbase.address}}</td>
                        <td class="tr-1 tr-3">创建时间</td>
                        <td class="tr-2 tr-4">{{supbase.createTime}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">是否主基地</td>
                        <td class="tr-2 tr-4">{{supbase.isMainbase}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div v-if="flag">
            <div class="bottom5" style="margin-top:20px;">
                <Table style="width: 1190px; margin-left: 20px; margin-bottom: 40px"  :columns="columns1"
                       :data="data1"></Table>
            </div>
            <div class="bottom5" style="margin-top:12px;">
                <Table style="width: 1190px; margin-left: 20px; "  :columns="columns2"
                       :data="data2"></Table>
            </div>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: "supbasedetail",
        data(){
            return{
                flag:false,
                supbase:{},
                name:'',
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
                        title: '地址',
                        width: 500,
                        key: 'address',
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
                                        title: params.row.address
                                    }
                                }, params.row.address)
                            ]);

                        }
                    },
                    {
                        title: '学员最大容量',
                        align:'center',
                        key: 'maxStu',
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
                                        title: params.row.maxStu
                                    }
                                }, params.row.maxStu)
                            ]);

                        }
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
                        title: '训练大纲',
                        key: 'syllabusId',
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
                                        title: params.row.syllabusId
                                    }
                                }, params.row.syllabusId)
                            ]);

                        }
                    },
                    {
                        title: '有效期',
                        key: 'effectiveDate',
                        align:'center',
                        width:200,
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
                                        title: params.row.effectiveDate
                                    }
                                }, params.row.effectiveDate)
                            ]);

                        }
                    },
                    {
                        title: '容量',
                        key: 'capacity',
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
                                        title: params.row.capacity
                                    }
                                }, params.row.capacity)
                            ]);

                        }
                    }
                ],
                data1: [],
                data2: [],
            }
        },
        methods:{
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
                this.$router.push({path:'/suptabs',query: {value:'supbase', gacompany:JSON.stringify(this.supbase)}})
            }
        },
        mounted(){
            this.supbase = JSON.parse(this.$route.query.supbase)
            this.searchtakecourse(this.supbase.baseId)
            this.searchclassroom(this.supbase.baseId)
            this.$http.httpGet('/eim/api/company/'+this.supbase.companyId).then(res => {
                this.name = res.data.name;
            }).catch(function (error) {
                console.log(error);

            });
            if(this.supbase.baseType==1){
                this.flag = true
                this.supbase.baseType='运行基地'
            }
            if(this.supbase.baseType==2){
                this.flag = false
                this.supbase.baseType='飞行基地'
            }
            if(this.supbase.baseType==3){
                this.flag = false
                this.supbase.baseType='维修基地'
            }
            if(this.supbase.baseType==4){
                this.flag = false
                this.supbase.baseType='训练基地'
            }

            if(this.supbase.isMainbase==1){
                this.supbase.isMainbase='是'
            }else{
                this.supbase.isMainbase='否'
            }
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