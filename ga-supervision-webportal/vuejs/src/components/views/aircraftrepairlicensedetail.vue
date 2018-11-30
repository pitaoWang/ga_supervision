<style>
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
    <div>
        <table style="width: 1190px; border-spacing:0px; margin-left: 40px">
            <tbody>
            <tr>
                <td class="tr-1 tr-5" style="width: 150px !important; text-align: center;" colspan="4">航空器维修执照详细信息</td>
            </tr>
            <tr>
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
        <table style="width: 1190px; border-spacing:0px; margin-left: 40px; margin-top: 10px" :key="aprindex" v-for="(apritem, aprindex) in secondSpecialInfolist">
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
                <td class="tr-2 tr-4">{{apritem.apravailable}}</td>
                <td class="tr-1 tr-3" style="width: 150px !important;"></td>
                <td class="tr-2 tr-4"></td>
            </tr>
            </tbody>
        </table>
        <div>
                <span style="width:100%;display:inline-block;text-align:right; margin-left: 40px">
                    <h2 style="float:left; font-size: 14px;padding: 5px;">航空器维修执照机型签发</h2>
                </span>

            <div style="width: 1190px; border-spacing:0px; margin-left: 40px; margin-top:5px; margin-bottom: 5px">
                <Table border :columns="arlmccolumns" :data="allinfo.aircraftrepair.arlmc" ></Table>
            </div>
            <span style="width:100%;display:inline-block;text-align:right; margin-left: 40px">
                    <h2 style="float:left;font-size: 14px;padding: 5px;">航空器维修执照专业续签</h2>
                </span>

            <div style="width: 1190px; border-spacing:0px; margin-left: 40px; margin-top:5px;">
                <Table border :columns="aprlsccolumns" :data="allinfo.aircraftrepair.arlsc"></Table>
            </div>
        </div>
        <div class="flightdispatcherimg" style="margin-top: 20px;width:100%; margin-left: 40px">
            <span :key="item" v-for="item in uploadList">
                <img :src="item" style="width: 180px; height: 150px; display: inline-block;margin: 5px 0px 30px 100px; "
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
    import aircraftdata from '../../data/aircraft/index'
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
                    }, personQulificationInfo:{

                    }

                },
                arlmccolumns: [
                    {
                        title: '签署机型日期',
                        key: 'checkDate',
                        align: 'center',
                        width: 290

                    },
                    {
                        title: '签署人',
                        key: 'checkorman',
                        align: 'center',
                        width: 309
                    },
                    {
                        title: '航空器型号',
                        width: 294,

                        key: 'aircraftModel',
                        align: 'center'
                    },
                    {
                        title: '航空器类别',
                        width: 294,

                        key: 'aircraftClass',
                        align: 'center'
                    },
                        /*
                    {
                        title: '签署人',
                        key: 'checkorUserId',
                        width: 180,
                        align: 'center',
                    }
                    */
                ],
                aprlsccolumns: [
                    {
                        title: '执照续签专业代码',
                        key: 'specialCode',
                        align: 'center',
                        width: 380
                    },
                    {
                        title: '签署人',
                        key: 'checkorman',
                        align: 'center',
                        width: 300
                    },
                    {
                        title: '执照续签签发日期',
                        key: 'checkDate',
                        align: 'center',

                        width: 254

                    },
                    {
                        title: '执照续签有效截止日期',
                        key: 'expireDate',
                        align: 'center',

                        width: 253

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


                   // self.initcheckorUserId = profile.user.id;
                    self.arlmcentity.licenseId = this.initlicenseId;
                  //  self.arlmcentity.checkorUserId = this.initcheckorUserId;
                    self.arlscentity.licenseId = this.initlicenseId;
                  //  self.arlscentity.checkorUserId = this.initcheckorUserId;

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
                               console.log("1111111111111111111111="+secondSpecialInfolist)
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