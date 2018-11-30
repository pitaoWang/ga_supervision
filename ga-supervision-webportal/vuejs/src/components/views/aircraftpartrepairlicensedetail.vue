<style scoped>
    .tpartrepair {
        width: 1190px;
        border:1px solid #DCDFE6;
        margin-left: 40px;
        border-spacing: 0px;
    }
    .tr-1 {
        font-size: 14px;
        width: 200px;
        padding-left: 13px;
        height: 48px;
        color:  #606265;
        border:0px;
        border-bottom: solid 1px #DCDFE6;
    }
    .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        font-size: 14px;
        border:solid 1px #DCDFE6;
        border-top:0px;
        text-align: center;
        background:#FFF;
    }

    .tr-3{
        background:#f8f8f9;
    }

    /*.tr-4{*/
        /*background-color: rgba(218, 236, 252, 1);*/
    /*}*/
    .tr-5{
        background:#f8f8f9;
    }
    .arpl-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        text-align: center;
        background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);
        background: linear-gradient(#a3a3a3, #e7e7e7);
    }

    .arpl-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        text-align: center;
        background: -webkit-linear-gradient(#e7e7e7, #a3a3a3);
        background: linear-gradient(#e7e7e7, #a3a3a3);
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
<template>

    <div>
        <table class="tpartrepair">
            <tbody>
            <tr>
                <td class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">航空器部件维修执照详细信息</td>
            </tr>
            <tr>
                <!--160px 227px-->
                <td class="tr-1 tr-5" style="width: 150px;">执照类型</td>
                <td class="tr-2 tr-6" style="width: 200px;">{{allinfo.personQulificationInfo.licensetype}}</td>
                <td class="tr-1 tr-5 tr-l" style="width: 150px;">执照编号</td>
                <td class="tr-2 tr-6" style="width: 200px;">{{allinfo.personQulificationInfo.licenseno}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">执照颁发日期</td>
                <td class="tr-2 tr-4">{{allinfo.personQulificationInfo.issuedate}}</td>
                <td class="tr-1 tr-3 tr-l">执照到期日期</td>
                <td class="tr-2 tr-4">{{allinfo.personQulificationInfo.expiredate}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3">执照颁发单位</td>
                <td class="tr-2 tr-4">{{allinfo.personQulificationInfo.issueUnit}}</td>
                <td class="tr-1 tr-3 tr-l">执照更新时间</td>
                <td class="tr-2 tr-4">{{allinfo.personQulificationInfo.licenseUpdatetime}}</td>

            </tr>

            </tbody>
        </table>

        <table style="width: 1190px; border-spacing:0px; border: 1px solid #DCDFE6; margin-left: 40px;">
        <tbody :key="aprindex" v-for="(apritem, aprindex) in repairSpecialInfolist">
        <tr>
            <td class="tr-1 tr-3" style="width: 150px !important; text-align: center;" colspan="4">航空器部件维修专业执照{{aprindex+1}}信息</td>
        </tr>
        <tr>
            <td class="tr-1 tr-3" style="width: 150px !important;">航空器维修执照名称</td>
            <td class="tr-2 tr-4" style="width: 150px !important;;">{{apritem.aprName}}</td>
            <td class="tr-1 tr-3" style="width: 150px !important;;">航空器维修执照代码</td>
            <td class="tr-2 tr-4" style="width: 150px !important;;">{{apritem.aprCode}}</td>
        </tr><tr>
            <td class="tr-1 tr-3" style="width: 150px !important;">签发日期</td>
            <td class="tr-2 tr-4" style="width: 150px !important;;">{{apritem.aprissuerTime}}</td>
            <td class="tr-1 tr-3" style="width: 150px !important;;">截止日期</td>
            <td class="tr-2 tr-4" style="width: 150px !important;">{{apritem.aprexpireTime}}</td>
        </tr>
        <tr>
            <td class="tr-1 tr-3" style="width: 150px !important;">是否有效</td>
            <td class="tr-2 tr-4" style="width: 150px !important;">{{apritem.apravailable}}</td>
            <td class="tr-1 tr-3" style="width: 150px !important;;"><!--执照颁发人--></td>
            <td class="tr-2 tr-4" style="width: 150px !important;"><!--{{allinfo.aircraftpart.issueUserId}}--></td>
        </tr>
        </tbody>
        </table>
        <div style="margin-top:10px; margin-left: 40px">
                <span style="display:inline-block;text-align:right;">
                    <h2 style="float:left;font-size: 14px;padding: 5px;">航空器部件维修执照项目签署</h2>
                </span>
            <div style="margin-top:5px; margin-bottom: 5px;width: 1190px">
                <Table border :columns="aprlpccolumns" :data="allinfo.aircraftpart.aprlpc" ></Table>
            </div>
            <span style="display:inline-block;text-align:right;">
                    <h2 style="float:left; font-size: 14px;padding: 5px;">航空器部件维修执照专业续签</h2>
                </span>
            <div style="margin-top:5px;width: 1190px">
                <Table border :columns="aprlsccolumns" :data="allinfo.aircraftpart.aprlsc" ></Table>
            </div>
        </div>
        <div class="flightdispatcherimg" style="margin-top: 20px;width:100%;">
            <span :key="item" v-for="item in uploadList">
                <img :src="item" style="width: 180px; height: 150px; display: inline-block; margin: 12px 0px 30px 100px;"
                     @click="showimgfunction(item)">
            </span>
        </div>
        <Modal
                title="证件照详情"
                v-model="showimg"
                :mask-closable="false"
                on-ok="closemodal"
                :styles="{width:'850px'}"
        >
            <img :src="showimgpath" style="width: auto;max-width: 800px; margin:5px auto; height: 500px;padding-bottom: 20px;">
            <div slot="footer" style="text-align: center;">
                <Button type="info" size="large" @click="closemodal">关闭</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
    import aircraftdata from '../../data/aircraft/index'
    export default {
        data (){
            return {
                showimg: false,
                showimgpath: '',
                initlicenseId:'',
                initcheckorUserId:'',
                userMap:{},
                uploadList:[],
                aprlpcentity:{//与model绑定
                    id:'',
                    licenseId:'',
                    projectName:'',
                    checkorUserId:'',
                    checkDate:'',
                },
                aprlscentity:{//与model绑定
                    id:'',
                    licenseId:'',
                    specialCode:'',
                    checkorUserId:'',
                    checkDate:'',
                    expireDate:'',
                },
                mapaprlpcentity:{},//作为model与aircraftpart的中间变量,保存到数组中
                add_aprlpc_show:false,
                add_aprlsc_show:false,
                repairSpecialInfolist:[{
                    aprName:'',
                    aprCode:'',
                    aprissuerTime:'',
                    aprexpireTime:'',
                    apravailable:'',
                }],
                allinfo:{
                    aircraftpart:{
                        repairSpecialInfo:'',
                        issueUserId:'',
                        aprlpc:[],
                        aprlsc:[]
                    },
                    personQulificationInfo:{

                    }
                },
                aprlpccolumns: [
                    {
                        title: '签署项目日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 400,

                    },
                    {
                        title: '签署人',
                        key: 'checkorman',
                        align: 'center',
                        width: 398
                    },
                    {
                        title: '签署项目名称',
                        width: 390,
                        key: 'projectName',
                        align: 'center'
                    }, /* {
                        title: '签署人',
                        width: 218,
                        key: 'checkorUserId',
                        align: 'center'
                    }*/
                ],
                aprlsccolumns: [
                    {
                        title: '执照续签专业代码',
                        key: 'specialCode',
                        align: 'center',
                        width:380
                    },
                    {
                        title: '执照续签签发日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 250

                    },
                    {
                        title: '签署人',
                        key: 'checkorman',
                        align: 'center',
                        width: 300
                    },
                    {
                        title: '执照续签有效截止日期',
                        key: 'expireDate',
                        align: 'center',
                        width: 258
                    },
             /*       {
                        title: '签署项目签署人',
                        width: 218,
                        key: 'checkorUserId',
                        align: 'center'
                   }*/
                ],
            }

        },
//        props:['aircraftpartrepair'],
//        mounted: function () {
//            let qid = this.$route.query.licenseId;
//            this.initinfo(qid);
//        },
//        created:function()
//        {
//            let qid = this.$route.query.licenseId;
//            this.initinfo(qid);
//
//        },
        props:['licenseId'],


        watch: {
            licenseId:function(val){


                this.initinfo(val)
            },
        },
//        created()
//        {
//            let self = this;
//            self.userMap=new Map();
//            self.$http.httpGet("/app/api/sysuser/search/list/", {}).then(function (response) {
//                let datainfo=response.data;
//                console.log("self.userMap",datainfo.data);
//                if(datainfo.resultCode==200)
//                {
//                    for(var i=0;i<datainfo.data.length;i++)
//                    {
//                        self.userMap.set(datainfo.data[i].userId,datainfo.data[i].fullname);
//                    }
//                }
//                console.log("self.userMap===",self.userMap);
//            }).catch (function (error) {
//                console.log(error);
//            });
//        },
//        watch:function(){
//
//            this.initinfo( aircraftpartrepair);
//        },
        methods: {
            showimgfunction(path){
                this.showimg = true,
                        this.showimgpath = path
            },
            closemodal()
            {
                this.showimg = false,
                        this.showimgpath = ''
            },
            backtotraininfo(){
                let self=this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'airpartrepair'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'airpartrepair'}});
                }

            },
            initinfo(varlicenseId) {
                let self = this;
                self.initlicenseId=varlicenseId;

                    self.aprlpcentity.licenseId=this.initlicenseId;
                   // self.aprlpcentity.checkorUserId=this.initcheckorUserId;
                    self.aprlscentity.licenseId=this.initlicenseId;
                   // self.aprlscentity.checkorUserId=this.initcheckorUserId;

                    self.$http.httpGet("/eim/api/aircraftprl/"+varlicenseId,{}).then(function (response) {
                        let datainfo = response.data;

                        if(typeof(datainfo)!='undefined'&&datainfo!=''&&datainfo!=null) {
                            self.allinfo.aircraftpart = datainfo;
                            console.log("1111111111111111111111="+self.allinfo.aircraftpart.repairSpecialInfo)
                   //         self.allinfo.aircraftpart.issueUserId=self.userMap.get(self.allinfo.aircraftpart.issueUserId);
                            if (self.allinfo.aircraftpart.repairSpecialInfo != null) {
                                self.repairSpecialInfolist = eval(self.allinfo.aircraftpart.repairSpecialInfo);

                                for(var i = 0;i < self.repairSpecialInfolist.length; i++) {
                                    if(self.repairSpecialInfolist[i].aprissuerTime!=''&&self.repairSpecialInfolist[i].aprissuerTime!=null)
                                    {
                                        var d = new Date(self.repairSpecialInfolist[i].aprissuerTime);
                                        self.repairSpecialInfolist[i].aprissuerTime= d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                                    }

                                    if(self.repairSpecialInfolist[i].aprexpireTime!=''&&self.repairSpecialInfolist[i].aprexpireTime!=null)
                                    {
                                        d = new Date(self.repairSpecialInfolist[i].aprexpireTime);
                                        self.repairSpecialInfolist[i].aprexpireTime= d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                                    }
                                    self.repairSpecialInfolist[i].aprCode=self.repairSpecialInfolist[i].aprName;
                                    self.repairSpecialInfolist[i].aprName=aircraftdata.aircraftpartcodeNamelistdetail[self.repairSpecialInfolist[i].aprCode];
                                    self.repairSpecialInfolist[i].apravailable =self.repairSpecialInfolist[i].apravailable==0?'是':'否';
                                }
                            }
                            if (typeof (self.allinfo.aircraftpart.aprlpc) == 'undefined' || self.allinfo.aircraftpart.aprlpc == '') {
                                self.allinfo.aircraftpart.aprlpc = [];
                            }
                            if (typeof (self.allinfo.aircraftpart.aprlsc) == 'undefined' || self.allinfo.aircraftpart.aprlsc == '') {
                                self.allinfo.aircraftpart.aprlsc = [];
                            }
                         /*   for(var i=0;i<self.allinfo.aircraftpart.aprlpc.length;i++)
                            {
                                self.allinfo.aircraftpart.aprlpc[i].checkorUserId=self.userMap.get(self.allinfo.aircraftpart.aprlpc[i].checkorUserId);
                            }
                            for(var i=0;i<self.allinfo.aircraftpart.aprlsc.length;i++)
                            {
                                self.allinfo.aircraftpart.aprlsc[i].checkorUserId=self.userMap.get(self.allinfo.aircraftpart.aprlsc[i].checkorUserId);
                          } */
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                self.$http.httpGet("/eim/api/qulification/" + varlicenseId, {}).then((res)=> {
                    let result = res.data.filePath;
                    this.allinfo.personQulificationInfo = res.data;
                console.log("varlicenseId", result);
                if (result != '') {
                    self.uploadList = result.split(',');
                }
                console.log("varlicenseId", result);
            }).catch(function (error) {
                    console.log(error);
                });

            },
        },
    }

</script>