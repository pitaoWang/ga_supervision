<style scoped>
    .license{
        border-spacing: 0px;
        width: 965px;
        border:1px solid rgb(1,107,217)
    }
    .license .tr-1 {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border-bottom: solid 1px #55a2eb;
    }
    .license .tr-l{
        border-left: solid 1px #55a2eb;
    }
    .license .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border-left: solid 1px #55a2eb;
        border-bottom: solid 1px #55a2eb;
        text-align: center;
    }
    .license .tr-3{
        background-color: rgb(179,221,252);
    }

    .license .tr-4{
        background-color: rgb(218,236,252);
    }
    .license .tr-5{
        background-color: rgb(201,231,253);
    }
    .license .tr-6{
        background-color: rgb(239,246,253);
    }

    .intelligenceimg>img{
        float: left;
        margin-left: 1%;

    }
    .infoPart1 table{
        border-collapse:collapse;
        border-spacing:0;
    }
</style>
<template>
    <div>
        <div class="infoPart1">
            <div>
                <div class="cut container-center">
                    <table class="license" >
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">情报执照详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">执照类别</td>
                            <td class="tr-2 tr-6">情报执照</td>
                            <td class="tr-1 tr-5 tr-l">执照编号</td>
                            <td class="tr-2 tr-6">{{qualification.licenseno}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发时间</td>
                            <td class="tr-2 tr-4">{{qualification.issuedate}}</td>
                            <td class="tr-1 tr-3 tr-l">执照到期时间</td>
                            <td class="tr-2 tr-4">{{qualification.expiredate}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">执照颁发单位</td>
                            <td class="tr-2 tr-6">{{intelligencelicense.issueUnit}}</td>
                            <td class="tr-1 tr-5 tr-l">执照适用地区</td>
                            <td class="tr-2 tr-6">{{intelligencelicense.licenseArea}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="margin-top: 2px">
                <Table border :columns="columns7" :data="licenseChecks" style="width: 965px;"></Table>
            </div>

            <div class="intelligenceimg" style="margin-top: 20px;width: 750px;" v-for="item in uploadList">
                <img :src="item" style="width: 19%;"   @click="imgdetail('',item)">
            </div>
        </div>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>

<script>
    import imgdetail from './imgdetail.vue';
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
                        width: 400
                    },
                    {
                        title: '签注内容',
                        width: 563,
                        key: 'licenseCheckContent',
                        align: 'center'
                    },

                ],
                qualification:{
                    licenseno:'',
                    issuedate:'',
                    expiredate:'',
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
