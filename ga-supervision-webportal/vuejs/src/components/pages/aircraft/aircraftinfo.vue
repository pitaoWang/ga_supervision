<style>
    .aircraftlise {
        width: 716px;
        border-spacing: 0px;
        border: 1px solid rgb(1,107,217);
        margin-bottom: 4px;
    }
    .aircraftlise .tr-1 {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border:0px ;
        border-bottom: solid 1px #55a2eb ;
    }
    .aircraftlise .tr-l{
        border-left: solid 1px #55a2eb ;
    }
    .aircraftlise .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border:0px ;
        border-left: solid 1px #55a2eb ;
        border-bottom: solid 1px #55a2eb ;
        text-align: center;
    }
    .aircraftlise .tr-3{
        background-color: rgb(179,221,252);
    }

    .aircraftlise .tr-4{
        background-color: rgb(218,236,252);
    }
    .aircraftlise .tr-5{
        background-color: rgb(201,231,253);
    }
    .aircraftlise .tr-6{
        background-color: rgb(239,246,253);
    }
</style>
<template>
    <table class='aircraftlise'>
        <tbody><tr>
            <td class="tr-1 tr-3">航空器型号</td>
            <td class="tr-2 tr-4">{{infodata.aircraftModel}}</td>
            <td class="tr-1 tr-3 tr-l">生产厂家</td>
            <td class="tr-2 tr-4">{{infodata.manufacturer}}</td>
        </tr><tr>
            <td class="tr-1 tr-5">出厂日期</td>
            <td class="tr-2 tr-6">{{infodata.productionDate}}</td>
            <td class="tr-1 tr-5 tr-l">出厂序列号</td>
            <td class='tr-2 tr-6'>{{infodata.airframeid}}</td>
        </tr><tr>
            <td class="tr-1 tr-3">航空器所有权</td>
            <td class="tr-2 tr-4">{{infodata.isRent}}</td>
            <td v-if="infodata.isRent =='自有'" class="tr-1 tr-3 tr-l"></td>
            <td v-else class="tr-1 tr-3 tr-l">归属企业名称</td>
            <td v-if="infodata.isRent =='自有'"class="tr-2 tr-4"></td>
            <td v-else class="tr-2 tr-4">{{infodata.companyName}}</td>
        </tr><tr>
            <td class="tr-1 tr-5">类别</td>
            <td class="tr-2 tr-6">{{infodata.aircraftClass}}</td>
            <td class="tr-1 tr-5 tr-l">型别</td>
            <td class="tr-2 tr-6">{{infodata.aircraftType}}</td>
        </tr><tr>
            <td class="tr-1 tr-3">航空器登记号</td>
            <td class="tr-2 tr-4">{{infodata.registernumber}}</td>
            <td class="tr-1 tr-3 tr-l">适航证编号</td>
            <td class="tr-2 tr-4">{{infodata.airworthinessCertificateNumber}}</td>
        </tr><tr>
            <td class="tr-1 tr-5">电台执照编号</td>
            <td class="tr-2 tr-6">{{infodata.radioLicenceNumber}}</td>
            <td class="tr-1 tr-5 tr-l">航空器状态</td>
            <td class="tr-2 tr-6">{{infodata.aircraftStatus}}</td>
        </tr><tr>
            <td class="tr-1 tr-3">运行区域</td>
            <td class="tr-2 tr-4" >{{infodata.area}}</td>
            <td class="tr-1 tr-3 tr-l">运行种类</td>
            <td class="tr-2 tr-4" >{{infodata.functionintent}}</td>
        </tr><tr>
            <td class="tr-1 tr-5">发动机数量</td>
            <td class="tr-2 tr-6" >{{infodata.enginenumber}}</td>
            <td class="tr-1 tr-5 tr-l"></td>
            <td class="tr-2 tr-6" ></td>
        </tr><tr>
            <td class="tr-1 tr-3" v-if="infodata.enginenumber>0">1号发动机编号</td>
            <td class="tr-2 tr-4" v-if="infodata.enginenumber>0">{{infodata.engineOne}}</td>
            <td class="tr-1 tr-3 tr-l" v-if="infodata.enginenumber>1">2号发动机编号</td>
            <td class="tr-2 tr-4" v-if="infodata.enginenumber>1">{{infodata.engineTwo}}</td>
            <td class="tr-1 tr-3 tr-l" v-if="infodata.enginenumber==1"></td>
            <td class="tr-2 tr-4" v-if="infodata.enginenumber==1"></td>
        </tr><tr>
            <td class="tr-1 tr-5" v-if="infodata.enginenumber>2">3号发动机编号</td>
            <td class="tr-2 tr-6" v-if="infodata.enginenumber>2">{{infodata.engineThree}}</td>
            <td class="tr-1 tr-5 tr-l" v-if="infodata.enginenumber>3">4号发动机编号</td>
            <td class="tr-2 tr-6" v-if="infodata.enginenumber>3">{{infodata.engineFour}}</td>
            <td class="tr-1 tr-5 tr-l" v-if="infodata.enginenumber==3"></td>
            <td class="tr-2 tr-6" v-if="infodata.enginenumber==3"></td>
        </tr><tr>
            <td class="tr-1 tr-3" v-if="infodata.enginenumber>2">适航证正面扫描件</td>
            <td class="tr-1 tr-5" v-else>适航证正面扫描件</td>
            <td class="tr-2 tr-4" v-if="infodata.enginenumber>2"><img :src="infodata.airworthinessFrontCatalogue" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.airworthinessFrontCatalogue)"></td>
            <td class="tr-2 tr-6" v-else><img :src="infodata.airworthinessFrontCatalogue" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.airworthinessFrontCatalogue)"></td>

            <td class="tr-1 tr-3 tr-l" v-if="infodata.enginenumber>2">适航证背面扫描件</td>
            <td class="tr-1 tr-5 tr-l" v-else>适航证背面扫描件</td>
            <td class="tr-2 tr-4" v-if="infodata.enginenumber>2"><img :src="infodata.airworthinessBackCatalogue" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.airworthinessBackCatalogue)"></td>
            <td class="tr-2 tr-6" v-else><img :src="infodata.airworthinessBackCatalogue" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.airworthinessBackCatalogue)"></td>
        </tr><tr>
            <td class="tr-1 tr-5" v-if="infodata.enginenumber>2">国籍登记证扫描件</td>
            <td class="tr-1 tr-3" v-else>国籍登记证扫描件</td>
            <td class="tr-2 tr-6" v-if="infodata.enginenumber>2"><img :src="infodata.scanCatalogue" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.scanCatalogue)"></td>
            <td class="tr-2 tr-4" v-else><img :src="infodata.scanCatalogue" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.scanCatalogue)"></td>
            <td class="tr-1 tr-5 tr-l" v-if="infodata.enginenumber>2">电台执照扫描件:</td>
            <td class="tr-1 tr-3 tr-l" v-else>电台执照扫描件</td>
            <td class="tr-2 tr-6" v-if="infodata.enginenumber>2"><img :src="infodata.radioLicenceCatalogue" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.radioLicenceCatalogue)"></td>
            <td class="tr-2 tr-4" v-else><img :src="infodata.radioLicenceCatalogue" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.radioLicenceCatalogue)"></td>
        </tr><tr>
            <td class="tr-1 tr-3"style="border-bottom: 0" v-if="infodata.enginenumber>2">航空器45度照片</td>
            <td class="tr-1 tr-5"style="border-bottom: 0" v-else>航空器45度照片</td>
            <td class="tr-2 tr-4"style="border-bottom: 0" v-if="infodata.enginenumber>2" >
                <div class="demo-upload-list-cover">
                    <img :src="infodata.picturePath" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.picturePath)">
                </div>
            </td>
            <td class="tr-2 tr-6" v-else style="border-bottom: 0">
                <div class="demo-upload-list-cover">
                    <img :src="infodata.picturePath" style="width:180px;padding: 6px;" @click="imgdetail('',infodata.picturePath)">
                </div>
            </td>
            <td class="tr-1 tr-3 tr-l"style="border-bottom: 0" v-if="infodata.enginenumber>2"></td>
            <td class="tr-1 tr-5 tr-l" style="border-bottom: 0"v-else></td>
            <td class="tr-2 tr-4" v-if="infodata.enginenumber>2" style="border-bottom: 0"></td>
            <td class="tr-2 tr-6" v-else style="border-bottom: 0"></td>
        </tr>
        </tbody>
        <imgdetail ref="imgdetailref"></imgdetail>
    </table>
</template>
<script>
    import imgdetail from '../../views/imgdetail';
    export default {
        data () {
            return {
                infodata:{
                    pictruedefalue:'../../../../static/img/imgpictrue.png',
                    aircraftRegid: '',
                    companyId: '',
                    productionDate:null,
                    manufacturer:'',
                    aircraftType:1,
                    airworthinessCertificateNumber:'',
                    airframeid:'',
                    engineOne:'',
                    enginenumber:'',
                    aircraftClass:'',
                    registernumber:'',
                    engineTwo:'',
                    radioLicenceNumber:'',
                    engineThree:'',
                    engineFour:'',
                    picturePath:'../../../../static/img/imgpictrue.png',
                    radioLicenceCatalogue:'../../../../static/img/imgpictrue.png',
                    airworthinessFrontCatalogue:'../../../../static/img/imgpictrue.png',
                    airworthinessBackCatalogue:'../../../../static/img/imgpictrue.png',
                    scanCatalogue:'../../../../static/img/imgpictrue.png',
                    aircraftModel:'',
                    area:'',
                }

            }
        },
        props: ['idinfobase'],
        components: {
            imgdetail
        },
        watch: {
            // 如果 question 发生改变，这个函数就会运行
            idinfobase: function (newval) {
                this.getData(newval);
            }
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            getData:function (newval){
                let self = this;
                self.$http.httpGet('/app/api/aircraft/'+newval, {
//                    params: {
//                        ID: newval
//                    }
                }).then(function (response){
                    let datainfo=response.data;
                    if (datainfo.isftd == 1)
                    {
                        datainfo.isftd = "是";
                    }
                    else{
                        datainfo.isftd = "否";
                    }
                    self.infodata=datainfo;
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }
</script>
