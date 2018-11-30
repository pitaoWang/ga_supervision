<template>
    <div>
        <div class="infoPart1">

            <div>
                <div class="cut container-center">
                    <table style="width: 965px;border:1px solid;border-spacing:0px;">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">飞行签派员执照详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">执照类别</td>
                            <td class="tr-2 tr-4">飞行签派员执照</td>
                            <td class="tr-1 tr-5">执照编号</td>
                            <td class="tr-2 tr-4">{{qualification.licenseno}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发时间</td>
                            <td class="tr-2 tr-4">{{qualification.issuedate}}</td>
                            <td class="tr-1 tr-3">执照到期时间</td>
                            <td class="tr-2 tr-4">{{qualification.expiredate}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">执照状态</td>
                            <td class="tr-2 tr-4">{{flightdispatcher.licenseState}}</td>
                            <td class="tr-1 tr-5">执照类型</td>
                            <td class="tr-2 tr-4">{{flightdispatcher.licenseProp}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发人</td>
                            <td class="tr-2 tr-4" >{{flightdispatcher.holderName}}</td>
                            <td class="tr-1 tr-3">持证人执业公司</td>
                            <td class="tr-2 tr-4" >{{flightdispatcher.companyId}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="margin-top: 2px">
                <Table border :columns="columns7" :data="licenseChecks" style="width: 965px;"></Table>
            </div>
            <div class="flightdispatcherimg" style="margin-top: 20px;width: 750px;margin-left: 235px" v-for="item in uploadList">
                <img :src="item" style="width: 19%;"  @click="imgdetail('',item)">
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
                uploadList:[],
                licenseChecks: [{}],
                qualification:{
                    licenseno:'',
                    issuedate:'',
                    expiredate:'',
                },
                flightdispatcher:{
                    id: '',
                    licenseState: '',
                    licenseProp: '',
                    holderName: '',
                    companyId: '',
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
                self.$http.httpGet("/eim/api/qulification/search/license",{id: id,  licensetype: "飞行签派员执照"}).then((res)=>{
                    let result = res.data;
                    if (result.data.personQulificationInfo == null || result.data.flightDispatcherLicense == null) {
                        console.log("error")
                    }
                    else {
                        self.qualification = result.data.personQulificationInfo;
                        self.flightdispatcher = result.data.flightDispatcherLicense
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
                    self.$router.push({path: '/for_pilot', query: {backtype: 'flightdisplicense'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'flightdisplicense'}});
                }

            }
        },
    }
</script>
<style scoped>
    .tr-1 {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 45px;
        color:  rgba(1, 107, 217, 1);
        border: solid 1px rgba(1, 107, 217, 1);
    }
    .tr-2 {
        width: 200px;
        text-align: center;
        color:  black;
        border: solid 1px rgba(1, 107, 217, 1);
    }

    .tr-3{
        background-color: rgba(179, 221, 252, 1);
    }

    .tr-4{
        background-color: rgba(218, 236, 252, 1);
    }
    .tr-5{
        background-color: rgba(201, 231, 253, 1);
    }

    .flightdispatcherimg>img{
        float: left;
        margin-left: 1%;

    }

</style>
