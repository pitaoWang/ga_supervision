<template>
    <div>
        <div>
            <div>
                <div>
                    <table style="width: 1190px; border-spacing:0px; margin-left: 40px">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">飞行签派员执照详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1">执照类别</td>
                            <td class="tr-2 tr-4">飞行签派员执照</td>
                            <td class="tr-1 tr-3">执照编号</td>
                            <td class="tr-2 tr-4">{{qualification.licenseno}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发时间</td>
                            <td class="tr-2 tr-4">{{qualification.issuedate}}</td>
                            <td class="tr-1 tr-3">执照到期时间</td>
                            <td class="tr-2 tr-4">{{qualification.expiredate}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照颁发单位</td>
                            <td class="tr-2 tr-4">{{qualification.issueUnit}}</td>
                            <td class="tr-1 tr-3">执照更新时间</td>
                            <td class="tr-2 tr-4">{{qualification.licenseUpdatetime}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">执照状态</td>
                            <td class="tr-2 tr-4">{{flightdispatcher.licenseState}}</td>
                            <td class="tr-1 tr-3">执照类型</td>
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
            <div style="margin-top: 12px">
                <Table border :columns="columns7" :data="licenseChecks" style="width: 1190px; margin-left: 40px; margin-bottom: 40px"></Table>
            </div>
            <div class="flightdispatcherimg" style="margin-top: 20px;width: 750px;margin-left: 235px" v-for="item in uploadList">
                <img :src="item" style="width: 19%;"  @click="imgdetail('',item)">
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
                uploadList:[],
                licenseChecks: [{}],
                qualification:{
                    licenseno:'',
                    issuedate:'',
                    expiredate:'',
                    issueUnit:'',
                    licenseUpdatetime:'',
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

    .flightdispatcherimg>img{
        float: left;
        margin-left: 1%;

    }
    .infoPart1 table{
        border-collapse:collapse;
        border-spacing:0;
        border-left:1px solid #888;
        border-top:1px solid #888;
        background:#efefef;
        width: 100%;
    }
    .div-button{
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }

</style>
