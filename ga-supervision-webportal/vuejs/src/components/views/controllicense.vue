<template>
    <div>
        <div class="infoPart1">
            <div>
                <div>
                    <table>
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">管制人员执照详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5">执照类别</td>
                            <td class="tr-2 tr-4">管制执照</td>
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
                            <td class="tr-1 tr-3">执照颁发单位</td>
                            <td class="tr-2 tr-4">{{qualification.issueUnit}}</td>
                            <td class="tr-1 tr-3 tr-l">执照更新时间</td>
                            <td class="tr-2 tr-4">{{qualification.licenseUpdatetime}}</td>

                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
            <div style="margin-top: 12px; margin-bottom: 12px">
                <Table border :columns="columns7" :data="licenseChecks" style="width: 1190px; margin-left: 40px"></Table>
            </div>
            <div class="flightdispatcherimg" style="width: 1190px; margin-left: 235px" v-for="item in uploadList">
                <img :src="item" style="width: 400px; height: 400px"  @click="imgdetail('',item)">
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
                self.$http.httpGet("/eim/api/qulification/search/license",{id: id,  licensetype: "管制执照"}).then((res)=>{
                    let result = res.data;
                    if (result.data.personQulificationInfo == null) {
                        console.log("error")
                    }
                    else {
                        self.qualification = result.data.personQulificationInfo;

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
                    self.$router.push({path: '/for_pilot', query: {backtype: 'controllicense'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'controllicense'}});
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
        color:  #606265;
        border: solid 1px #DCDFE6;
    }
    .tr-2 {
        width: 200px;
        text-align: center;
        color:  #606265;
        border: solid 1px #DCDFE6;
        background: #FFF;
    }

    .tr-3{
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
    /*background-color: rgb(239,246,253);*/
    /*}*/
    .tr-5{
        background-color: #f8f8f9;
    }
    /*.tr-6{*/
    /*background-color: rgb(239,246,253);*/
    /*}*/

    .flightdispatcherimg>img{
        float: left;
        margin-left: 1%;
        margin-bottom: 30px;
    }
    .infoPart1 table{
        width: 1190px;
        border:1px solid #DCDFE6;
        border-spacing: 0px;
        margin-left: 40px;
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
