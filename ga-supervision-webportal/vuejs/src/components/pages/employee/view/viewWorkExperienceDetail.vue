<template>
    <div style="width: 1280px; height: 900px; background:white">
        <div style="">
            <span style="width:96%; display:inline-block;text-align:right;margin-top: 10px; margin-bottom: 10px; padding-right: 17px;">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>


                <!--<Table border stripe :columns="columns" :data="data"></Table>-->
                <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                            工作经历
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3" >工作单位</td>
                        <td class="tr-2 tr-4">{{experience.workUnit}}</td>
                        <td class="tr-1 tr-3">所任职位</td>
                        <td class="tr-2 tr-4">{{experience.post}}</td>


                    </tr>
                    <tr>

                        <td class="tr-1 tr-3">证明人</td>
                        <td class="tr-2 tr-4">{{experience.witness}}</td>
                        <td class="tr-1 tr-3">证明人联系方式</td>
                        <td class="tr-2 tr-4">{{experience.proofContact}}</td>

                    </tr>

                    <tr>
                        <td class="tr-1 tr-3">工作起始时间</td>
                        <td class="tr-2 tr-4">{{experience.startTime}}</td>
                        <td class="tr-1 tr-3" >工作截止时间</td>
                        <td class="tr-2 tr-4" >{{experience.endTime}}</td>
                    </tr>
                    <tr>

                        <td class="tr-1 tr-3">工作内容</td>
                        <td class="tr-2 tr-4" colspan="3">{{experience.jobContent}}</td>
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
                experience:'',
                employeeId:'',
                employeeData:''
            }
        },
        methods:{
            imgdetail:function (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },
            black:function () {
                this.$router.push({path:'/viewEmployee',query: {tab:'name6',employeeId:this.employeeId, user:JSON.stringify(this.employeeData)}})
            }
        },
        mounted(){
            let buz = JSON.parse(Cookies.get("profile"));
                this.experience =  JSON.parse(this.$route.query.obj);
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