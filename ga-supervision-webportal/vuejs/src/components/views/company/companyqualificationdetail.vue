<template>
    <div style="width: 1280px; height: 900px; margin-top: 60px;background:white">
        <div style="">
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
                            企业资质详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3" >所属单位</td>
                        <td class="tr-2 tr-4">{{comqual.companyId}}</td>
                        <td class="tr-1 tr-3" rowspan="3">运行合格证扫描件</td>
                        <td class="tr-2 tr-4" rowspan="3" align="right">
                            <div  v-if="comqual.attchmentaddress" style="width: 180px;height: 135px;padding-left: 65px;">
                                <img :src="comqual.attchmentaddress"  style="width: 180px;height: 135px;"  @click="imgdetail(comqual.attchmentaddress)">
                            </div>
                            <div v-else>
                                <img :src="noimg" style="width: 180px;height: 135px;padding-left:65px;"/>
                            </div>

                        </td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">资质编号</td>
                        <td class="tr-2 tr-4">{{comqual.code}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">失效时间</td>
                        <td class="tr-2 tr-4">{{comqual.endtime}}</td>
                       <!-- <td class="tr-1 tr-3">颁发单位</td>
                        <td class="tr-2 tr-4">{{comqual.issueorgname}}</td>-->
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
        name: "companyqualificationdetail",
        data(){
            return{
                comqual:'',
            }
        },
        methods:{
            imgdetail:function (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },
            black:function () {
                this.$router.push({path:'/home',query: {value:'companyqualificationnew' }})
            }
        },
        mounted(){
            /*this.$profile.getProfile().then((profile) => {*/
            let buz = JSON.parse(Cookies.get("profile"));

                this.comqual =  this.$route.query.comqual;
                this.comqual.companyId= buz.org.orgName;

           /* })*/
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