<template>
    <div style="width: 1280px; height: 900px; margin-top: 60px;background:white">
        <div style="">
        <span style="width:96%; display:inline-block;text-align:right; margin-bottom: 20px; margin-top: 40px ;">
                    <Button primary @click="add_dialog_show()"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
            <div>


                <!--<Table border  :columns="columns" :data="data"></Table>-->
                <table style="width: 1190px; border-spacing:0px; margin-left: 40px">
                    <tr>
                        <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                            其他基地详细信息
                        </td>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3">基地名称</td>
                        <td class="tr-2 tr-4">{{otherbase.baseName}}</td>
                        <td class="tr-1 tr-3">所属单位</td>
                        <td class="tr-2 tr-4">{{otherbase.companyId}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">类型</td>
                        <td class="tr-2 tr-4">{{otherbase.baseType}}</td>
                        <td class="tr-1 tr-3">基地电话</td>
                        <td class="tr-2 tr-4">{{otherbase.baseTel}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">所在省</td>
                        <td class="tr-2 tr-4">{{otherbase.baseProvince}}</td>
                        <td class="tr-1 tr-3">所在市</td>
                        <td class="tr-2 tr-4">{{otherbase.baseCity}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">详细地址</td>
                        <td class="tr-2 tr-4">{{otherbase.baseAddress}}</td>
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
    export default {
        name: "otherbasedetail",
        data() {
            return {
                otherbase:'',
            }

        },
        methods: {



        },
        mounted() {
            this.$profile.getProfile().then((profile) => {
                this.otherbase =  this.$route.query.otherbase;
                this.otherbase.companyId= profile.org.orgName;
                if(this.otherbase.baseType==1){
                    this.otherbase.baseType = '飞行基地';
                }else if(this.otherbase.baseType==2){
                    this.otherbase.baseType='维修基地'
                }else if(this.otherbase.baseType==3){
                    this.otherbase.baseType = '训练基地'
                }
            })


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