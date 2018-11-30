<style scoped>
    .tr-1 {
        font-size: 14px;
        width: 180px;
        padding-left: 13px;
        height: 45px;
        color:  #606265;
        border-left: solid 1px #DCDFE6;
        border-right: solid 1px #DCDFE6;
        border-bottom: solid 1px #DCDFE6;
        background-color: #F8F8F9;
    }
    .tr-2 {
        width: 200px;
        text-align: center;
        color:  #606265;
        background-color: #FFF;
    }

    table{
        border-collapse:collapse;
    }
    table tr td{
        border:1px solid #DCDFE6;
    }

    .tr-3{
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
    /*background-color: #FFF;*/
    /*}*/
    .tr-5{
        background-color: #f8f8f9;
    }


    .intelligenceimg>img{
        float: left;
        margin-left: 1%;

    }
    .infoPart1 table{
        border-collapse:collapse;
        border-spacing:0;
        width: 100%;}
</style>
<template>
    <div>
        <div>
            <div>
                <div>
                    <table style="width: 1190px; border-spacing:0px; margin-left: 40px">
                        <tbody>
                        <tr>
                            <td  class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">情报执照详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照类别</td>
                            <td class="tr-2 tr-4">情报执照</td>
                            <td class="tr-1 tr-3">执照编号</td>
                            <td class="tr-2 tr-4">{{qualification.licenseno}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发时间</td>
                            <td class="tr-2 tr-4">{{qualification.issuedate}}</td>
                            <td class="tr-1 tr-3 tr-l">执照到期时间</td>
                            <td class="tr-2 tr-4">{{qualification.expiredate}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发单位</td>
                            <td class="tr-2 tr-4">{{intelligencelicense.issueUnit}}</td>
                            <td class="tr-1 tr-3 tr-l">执照更新时间</td>
                            <td class="tr-2 tr-4">{{qualification.licenseUpdatetime}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照适用地区</td>
                            <td class="tr-2 tr-4"  colspan="3">{{intelligencelicense.licenseArea}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="margin-top: 12px; margin-left: 40px;">
                <Table border :columns="columns7" :data="licenseChecks" style="width: 1190px;"></Table>
            </div>

            <div style="margin-top: 20px;width: 1190px;" v-for="item in uploadList">
                <img :src="item" style="width: 180px; height: 150px; margin-left: 40px; margin-bottom: 30px"   @click="imgdetail('',item)">
            </div>
        </div>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>

<script>
    import imgdetail from '../views/imgdetail.vue';
    export default {
        components: {
            imgdetail
        },
        data () {
            return {
                columns7: [
                    {
                        title: '签注时间',
                        key: 'licenseCheckDate',
                        align: 'center',
                        width: 518
                    },
                    {
                        title: '签注内容',
                        width: 668,
                        key: 'licenseCheckContent',
                        align: 'center'
                    },

                ],
                qualification:{
                    licenseno:'',
                    issuedate:'',
                    expiredate:'',
                    issueUnit:'',
                    licenseUpdatetime:'',
                },
                uploadList:[],
                licenseChecks: [{}],
                intelligencelicense:{
                    id: '',
                    issueUnit:'',
                    licenseArea:'',
                }
            }
        },
        props:['licenseId'],
        watch:{
            licenseId (val) {
                this.init(val);
            },
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            init(val){
                let self = this;
                let id=val;
                self.$http.httpGet("/eim/api/qulification/search/license",{id: id,  licensetype: "情报执照"}).then((res)=>{
                    let result = res.data;
                    console.log("*****result:",result)


                    if (result.data.personQulificationInfo == null || result.data.intelligenceLicense == null) {
                        console.log("error")
                    }
                    else {
                        self.qulification = result.data.personQulificationInfo;
                        self.intelligencelicense = result.data.intelligenceLicense
                        if (result.data.personQulificationInfo.filePath !=""&result.data.personQulificationInfo.filePath !=null) {
                            self.uploadList = result.data.personQulificationInfo.filePath.split(",");
                        }
                    }

                    if (result.data.licenseChecks == null || result.data.licenseChecks.lenght == 0) {
                        console.log("error")
                    }
                    else {
                        self.licenseChecks = result.data.licenseChecks;
                    }
                }).catch(function (error) {
                    console.log(error);
                    return;
                });
            },
            backtotraininfo(){
                let self=this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'intelligencelicense'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'intelligencelicense'}});
                }

            }
        },
    }
</script>
