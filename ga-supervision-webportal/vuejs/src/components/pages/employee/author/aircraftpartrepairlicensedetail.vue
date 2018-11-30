<style>
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

</style>
<template>
    <div class="infoPart1">
    <table style="width: 965px;border:1px solid;border-spacing:0px;">
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
        <div style="margin-top:10px;">
                <span style="display:inline-block;text-align:right;">
                    <h2 style="float:left;font-size: 14px;padding: 5px;color:rgb(1,107,217);">航空器部件维修执照项目签署</h2>
                </span>

            <div style="margin-top:5px; margin-bottom: 5px;width: 965px">
                <Table border :columns="aprlpccolumns" :data="allinfo.aircraftpart.aprlpc" ></Table>
            </div>
            <span style="display:inline-block;text-align:right;">
                    <h2 style="float:left; font-size: 14px;padding: 5px;color:rgb(1,107,217);">航空器部件维修执照专业续签</h2>
                </span>

            <div style="margin-top:5px;width: 965px">
                <Table border :columns="aprlsccolumns" :data="allinfo.aircraftpart.aprlsc" ></Table>
            </div>
        </div>
        <div class="flightdispatcherimg" style="margin-top: 20px;width:100%;">
            <span :key="item" v-for="item in uploadList">
                <img :src="item" style="width: 150px; height: 100px; display: inline-block;margin: 5px;"
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
    import aircraftdata from '../../../../data/aircraft/index'
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
                        title: '签署项目名称',
                        width: 563,
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
                        width:300
                    },
                    {
                        title: '执照续签签发日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 300
                    },
                    {
                        title: '执照续签有效截止日期',
                        key: 'expireDate',
                        align: 'center',
                        width: 363
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
                    self.$profile.getProfile().then((profile) =>{
                    self.initcheckorUserId=profile.user.id;

                    self.aprlpcentity.licenseId=this.initlicenseId;
                    self.aprlpcentity.checkorUserId=this.initcheckorUserId;
                    self.aprlscentity.licenseId=this.initlicenseId;
                    self.aprlscentity.checkorUserId=this.initcheckorUserId;

                    self.$http.httpGet("/eim/api/aircraftprl/"+varlicenseId,{}).then(function (response) {
                        let datainfo = response.data;
                        if(typeof(datainfo)!='undefined'&&datainfo!=''&&datainfo!=null) {
                            self.allinfo.aircraftpart = datainfo;
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
                console.log("varlicenseId", result);
                if (result != '') {
                    self.uploadList = result.split(',');
                }
                console.log("varlicenseId", result);
            }).catch(function (error) {
                    console.log(error);
                });


                },(error)=> {
                    console.log('error', error);
                });
            },
        },
    }

</script>