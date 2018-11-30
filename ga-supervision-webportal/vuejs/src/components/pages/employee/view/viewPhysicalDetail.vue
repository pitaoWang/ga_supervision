<template>
    <div style="width: 1280px; height: 900px; background:white">
        <div style="">
             <span style="width:96%; display:inline-block;text-align:right;margin-top: 10px; margin-bottom: 10px  ;padding-right: 17px;">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>


                <!--<Table border stripe :columns="columns" :data="data"></Table>-->
                <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                            体检详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3" >合格证编号</td>
                        <td class="tr-2 tr-4">{{physical.certificateNo}}</td>
                        <td class="tr-1 tr-3" rowspan="4">体检证扫描件</td>
                        <td class="tr-2 tr-4" rowspan="4">
                            <div  v-if="physical.certificateFilepath" style="width: 180px;height: 135px;margin-left: 10px">
                                <img :src="this.imageUrl+physical.certificateFilepath"  style="width: 180px;height: 135px;"  @click="imgdetail()">
                            </div>
                            <div v-else>
                                <img :src="noimg" style="width: 180px;height: 135px;padding-left:65px;"/>
                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">合格证级别</td>
                        <td class="tr-2 tr-4">{{physical.certificateGrade}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">合格状态</td>
                        <td class="tr-2 tr-4">{{physical.passStatus}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">颁发单位</td>
                        <td class="tr-2 tr-4">{{physical.issueUnit}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">颁发时间</td>
                        <td class="tr-2 tr-4">{{physical.certificateStartTime}}</td>
                        <td class="tr-1 tr-3">截止时间</td>
                        <td class="tr-2 tr-4">{{physical.certificateValidityTime}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">备注</td>
                        <td class="tr-2 tr-4">{{physical.remark}}</td>
                        <td class="tr-1 tr-3"></td>
                        <td class="tr-2 tr-4"></td>

                    </tr>

                    </tbody>
                </table>
            </div>

        </div>

    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        /*name: "companyqualificationdetail",*/
        data(){
            return{
                physical:'',
                employeeId:'',
                employeeData:''
            }
        },
        methods:{
            imgdetail:function (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },
            black:function () {
                this.$router.push({path:'/viewEmployee',query: {tab:'name3',employeeId:this.employeeId, user:JSON.stringify(this.employeeData)}})
            }
        },
        mounted(){
            let buz = JSON.parse(Cookies.get("profile"));
                this.physical =  JSON.parse(this.$route.query.obj);
                this.employeeId =  this.$route.query.employeeId;
            this.employeeData =  JSON.parse(this.$route.query.employeeData);
               // this.comqual.companyId= buz.org.orgName;

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