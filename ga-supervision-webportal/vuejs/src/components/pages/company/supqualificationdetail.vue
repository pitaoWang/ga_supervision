<template>
    <div style="width: 1280px; height: 900px; background:white; ;">

        <span style="width:100%; display:inline-block;text-align:right; padding-right: 5px;margin:10px 0px 10px -65px;">
                    <Button primary @click="black()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
        <div style="">
            <!--<Table border  :columns="columns" :data="data"></Table>-->
            <table style="width: 1190px; border-spacing:0px; margin-left: 20px;">
                <tr>
                    <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                        企业资质详细信息
                    </td>
                </tr>
                <tbody>
                <tr>
                    <td class="tr-1 tr-3" >所属单位</td>
                    <td class="tr-2 tr-4">{{companyName}}</td>
                    <td class="tr-1 tr-3" rowspan="4">运行合格证扫描件</td>
                    <td class="tr-2 tr-4" rowspan="4" align="right">
                        <div  v-if="gaqual.attchmentaddress" style="width: 180px;height: 135px;padding-left: 65px;">
                            <img :src="this.imageUrl+gaqual.attchmentaddress+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail(imageUrl+gaqual.attchmentaddress)">
                        </div>
                        <div v-else>
                            <img :src="noimg" style="width: 180px;height: 135px;padding-left:65px;"/>
                        </div>

                    </td>

                </tr>
                <tr>
                    <td class="tr-1 tr-3">资质名称</td>
                    <td class="tr-2 tr-4">{{gaqual.qualificationname}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">资质编号</td>
                    <td class="tr-2 tr-4">{{gaqual.code}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">生效时间</td>
                    <td class="tr-2 tr-4">{{gaqual.starttime}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">失效时间</td>
                    <td class="tr-2 tr-4">{{gaqual.endtime}}</td>
                     <td class="tr-1 tr-3"></td>
                     <td class="tr-2 tr-4"></td>
                </tr>

                </tbody>
            </table>
        </div>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>

<script>
    import imgdetail from '../../views/imgdetail.vue';
    export default {
        name: "supqualificationdetail",
        components: {
            'imgdetail':imgdetail,
        },
        data(){
            return{
                gaqual:{},
                companyName:''
            }
        },
        methods:{
            black:function () {
                this.$router.push({path:'/suptabs',query: {value:'supqual',gacompany:JSON.stringify(this.gaqual)}})
            },
            imgdetail:function (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },
        },
        mounted(){
            this.gaqual = JSON.parse(this.$route.query.gaqual)
            this.$http.httpGet('/eim/api/company/'+this.gaqual.companyId).then(res=>{
                this.companyName = res.data.name;
            })
        },

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