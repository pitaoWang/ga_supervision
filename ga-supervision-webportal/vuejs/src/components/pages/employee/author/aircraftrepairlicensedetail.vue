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
    .infoPart1 table{
        border-collapse:collapse;
        border-spacing:0;
        background:#efefef;
        width: 100%;}
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
    <div class="infoPart1" style="width: 965px">
        <table style="width: 965px;border:1px solid;margin-bottom: 2px;float: left;border-spacing:0px;" :key="aprindex" v-for="(apritem, aprindex) in secondSpecialInfolist">
            <tbody >
            <tr>
                <td class="tr-1 tr-3" style="width: 150px !important; text-align: center;" colspan="4">
                    航空器维修专业执照{{aprindex+1}}信息
                </td>
            </tr>
            <tr>
                <td class="tr-1 tr-3" style="width: 150px !important;">执照名称</td>
                <td class="tr-2 tr-4" style="width: 150px !important;;">{{apritem.aprName}}</td>
                <td class="tr-1 tr-3" style="width: 150px !important;;">执照代码</td>
                <td class="tr-2 tr-4" style="width: 150px !important;;">{{apritem.aprCode}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3" style="width: 150px !important;">签发日期</td>
                <td class="tr-2 tr-4" style="width: 150px !important;;">{{apritem.aprissuerTime}}</td>
                <td class="tr-1 tr-3" style="width: 150px !important;;">有效截止日期</td>
                <td class="tr-2 tr-4" style="width: 150px !important;;">{{apritem.aprexpireTime}}</td>
            </tr>
            <tr>
                <td class="tr-1 tr-3" style="width: 150px !important;">是否有效</td>
                <td class="tr-2 tr-4" style="width: 150px;">{{apritem.apravailable}}</td>
                <td class="tr-1 tr-3" style="width: 150px !important;;"></td>
                <td class="tr-2 tr-4" style="width: 150px;"></td>
            </tr>
            </tbody>
        </table>
        <div class="bottom5" style="margin-top:10px;width: 750px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <h2 style="float:left; font-size: 14px;padding: 5px;color: rgb(1,107,217)">航空器维修执照机型签发</h2>
                </span>

            <div class="bottom5" style="margin-top:5px; margin-bottom: 5px">
                <Table border :columns="arlmccolumns" :data="allinfo.aircraftrepair.arlmc" style="width: 965px;"></Table>
            </div>
            <span style="width:100%;display:inline-block;text-align:right;">
                    <h2 style="float:left;font-size: 14px;padding: 5px;color: rgb(1,107,217)">航空器维修执照专业续签</h2>
                </span>

            <div class="bottom5" style="margin-top:5px;">
                <Table border :columns="aprlsccolumns" :data="allinfo.aircraftrepair.arlsc" style="width: 965px;"></Table>
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
            <img :src="showimgpath" style="width: auto;max-width: 800px; height: 500px;padding-bottom: 20px;  margin:5px auto;">
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
                initlicenseId: '',
                initcheckorUserId: '',
                uploadList: [],
                userMap:{},
                arlmcentity: {//与model绑定
                    id: '',
                    licenseId: '',
                    aircraftClass: '',
                    aircraftModel: '',
                    checkorUserId: '',
                    checkDate: '',
                },
                arlscentity: {//与model绑定
                    id: '',
                    licenseId: '',
                    specialCode: '',
                    checkorUserId: '',
                    checkDate: '',
                    expireDate: '',
                },
                mapaprlpcentity: {},//作为model与aircraftpart的中间变量,保存到数组中
                add_aprlpc_show: false,
                add_aprlsc_show: false,
                secondSpecialInfolist: [{
                    aprName: '',
                    aprCode: '',
                    aprissuerTime: '',
                    aprexpireTime: '',
                    apravailable: '',
                }],
                allinfo: {
                    aircraftrepair: {
                        secondSpecialInfo: '',
                        issueUserId: '',
                        primarySpecialName: '',
                        primarySpecialCode: '',
                        primarySpecialName1: '',
                        primarySpecialCode1: '',
                        primarySpecialIssueDate: '',
                        primarySpecialExpireDate: '',
                        arlmc: [],
                        arlsc: []
                    },

                },
                arlmccolumns: [
                    {
                        title: '签署机型日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 300
                    },
                    {
                        title: '航空器型号',
                        width: 300,
                        key: 'aircraftModel',
                        align: 'center'
                    },
                    {
                        title: '航空器类别',
                        width: 363,
                        key: 'aircraftClass',
                        align: 'center'
                    },
                ],
                aprlsccolumns: [
                    {
                        title: '执照续签专业代码',
                        key: 'specialCode',
                        align: 'center',
                        width: 300
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
/*
                    {
                        title: '签署项目签署人',
                        key: 'checkorUserId',
                        width: 180,
                        align: 'center',
                    }
                    */
                ],
            }
        },
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
//                if(datainfo.resultCode==200)
//                {
//                    for(var i=0;i<datainfo.data.length;i++)
//                    {
//                        self.userMap.set(datainfo.data[i].userId,datainfo.data[i].fullname);
//                    }
//                }
//            }).catch (function (error) {
//                console.log(error);
//            });
//        },
//        mounted: function () {
//            let qid = this.$route.query.licenseId;
//            this.initinfo(qid);
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
                let self = this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'airpartpartrepair'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'airpartpartrepair'}});
                }

            },
            initinfo(varlicenseId) {
                let self = this;
                self.initlicenseId = varlicenseId;
                self.$profile.getProfile().then((profile)=> {

                    self.initcheckorUserId = profile.user.id;
                    self.arlmcentity.licenseId = this.initlicenseId;
                    self.arlmcentity.checkorUserId = this.initcheckorUserId;
                    self.arlscentity.licenseId = this.initlicenseId;
                    self.arlscentity.checkorUserId = this.initcheckorUserId;

                    self.$http.httpGet("/eim/api/aircraftrl/" + varlicenseId, {}).then(function (response) {
                        let datainfo = response.data;
                        if (typeof(datainfo) != 'undefined' && datainfo != '' && datainfo != null) {
                          //  self.allinfo.aircraftrepair = datainfo;
                            for(var key in datainfo)
                            {
                                self.$set(self.allinfo.aircraftrepair,key,datainfo[key]);
                            }
                            console.log("self.allinfo.aircraftrepair====",self.allinfo.aircraftrepair )
                            if (self.allinfo.aircraftrepair.secondSpecialInfo != null) {
                                self.secondSpecialInfolist = eval(self.allinfo.aircraftrepair.secondSpecialInfo);

                            //    self.allinfo.aircraftrepair.issueUserId=self.userMap.get(self.allinfo.aircraftrepair.issueUserId);

                                for (var i = 0; i < self.secondSpecialInfolist.length; i++) {
                                    if(self.secondSpecialInfolist[i].aprissuerTime!=''&&self.secondSpecialInfolist[i].aprissuerTime!=null)
                                    {var d = new Date(self.secondSpecialInfolist[i].aprissuerTime);
                                        self.secondSpecialInfolist[i].aprissuerTime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                                    }
                                    if(self.secondSpecialInfolist[i].aprexpireTime!=''&&self.secondSpecialInfolist[i].aprexpireTime!=null)
                                    {
                                        d = new Date(self.secondSpecialInfolist[i].aprexpireTime);
                                        self.secondSpecialInfolist[i].aprexpireTime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
                                    }

                                    self.secondSpecialInfolist[i].aprCode = self.secondSpecialInfolist[i].aprName;
                                    self.secondSpecialInfolist[i].aprName = aircraftdata.aircraftcodeNamelistdetail[self.secondSpecialInfolist[i].aprCode];
                                    self.secondSpecialInfolist[i].apravailable=self.secondSpecialInfolist[i].apravailable==0?'是':'否';

                                }
                            }
                            if (typeof (self.allinfo.aircraftrepair.arlmc) == 'undefined' || self.allinfo.aircraftrepair.arlmc == '') {
                                self.allinfo.aircraftrepair.arlmc = [];
                            }
                            if (typeof (self.allinfo.aircraftrepair.arlsc) == 'undefined' || self.allinfo.aircraftrepair.arlsc == '') {
                                self.allinfo.aircraftrepair.arlsc = [];
                            }
                            for(var i=0;i<self.allinfo.aircraftrepair.arlmc.length;i++)
                            {
                       //         self.allinfo.aircraftrepair.arlmc[i].checkorUserId=self.userMap.get(self.allinfo.aircraftrepair.arlmc[i].checkorUserId);
                            }
                            for(var i=0;i<self.allinfo.aircraftrepair.arlsc.length;i++)
                            {
                       //         self.allinfo.aircraftrepair.arlsc[i].checkorUserId=self.userMap.get(self.allinfo.aircraftrepair.arlsc[i].checkorUserId);
                            }
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


                }, (error)=> {
                    console.log('error', error);
                });
            },
        },
    }

</script>