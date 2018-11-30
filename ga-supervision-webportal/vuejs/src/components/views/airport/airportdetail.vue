<template>
    <table class='companydetail' style="margin-bottom: 4px">
        <tbody><tr>
            <td class="tr-1-company tr-3-company" >产权公司名称</td>
            <td class="tr-2-company tr-4-company"><router-link :to="{path:'/gacompany',query: {companyId:airportdetail.companyId}}"><div style="color: rgb(35,24,21);cursor:pointer;">{{airportdetail.companyName}}</div></router-link></td>
            <td class="tr-1-company tr-3-company tr-l-company">机场等级</td>
            <td class="tr-2-company tr-4-company">{{airportdetail.grade}}</td>
        </tr><tr>
            <td class="tr-1-company tr-5-company">所在省</td>
            <td class='tr-2-company tr-6-company'>{{airportdetail.province}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">所在城市</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.city}}</td>
        </tr><tr>
            <td class="tr-1-company tr-3-company">机场基准点经度</td>
            <td class="tr-2-company tr-4-company">{{airportdetail.longitude}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">机场基准点纬度</td>
            <td class="tr-2-company tr-4-company">{{airportdetail.latitude}}</td>
        </tr><tr>
            <td class="tr-1-company tr-5-company">机场使用性质</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.property}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">是否水上机场</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.iswater}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company">机场类型</td>
            <td class="tr-2-company tr-4-company" >{{airportdetail.airporttype}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">是否可用</td>
            <td class="tr-2-company tr-4-company" >{{airportdetail.isavailable}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">许可证起始日期</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.licenseBegindate}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">许可证截止日期</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.licenseEnddate}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company">机场标高</td>
            <td class="tr-2-company tr-4-company" >{{airportdetail.height}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">机场运营公司编号</td>
            <td class="tr-2-company tr-4-company" >{{airportdetail.entNumber}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">机场管理机构名称</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.orgName}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">ICAO编码</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.icaoNumber}}</td>
        </tr><tr>
            <td class="tr-1-company tr-3-company">IATA编码 ：</td>
            <td class="tr-2-company tr-4-company" >{{airportdetail.iatanumber}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">机场地址</td>
            <td class="tr-2-company tr-4-company" >{{airportdetail.address}}</td>
        </tr><tr>
            <td class="tr-1-company tr-5-company">机场服务电话</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.serviceTel}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">站调联系电话</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.tel}}</td>
        </tr>

        <tr>
            <td class="tr-1-company tr-3-company">隶属管理局：</td>
            <td class="tr-2-company tr-4-company">{{airportdetail.authorityName}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">隶属监管局：</td>
            <td class="tr-2-company tr-4-company">{{airportdetail.superviseOrgName}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">隶属于战区:</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.warZone}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">塔台主频率：</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.serviceTel}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company">进近主频率:</td>
            <td class="tr-2-company tr-4-company">{{airportdetail.mainFrequency}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">进近备频率：</td>
            <td class="tr-2-company tr-4-company">{{airportdetail.reserveFrequency}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">仪表着陆设备是否具备:</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.islandingDevice}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">导航台NDB频率：</td>
            <td class="tr-2-company tr-6-company">{{airportdetail.ndbFrequency}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company" style="border-bottom: 0">导航台VOR/DME频率:</td>
            <td class="tr-2-company tr-4-company" style="border-bottom: 0">{{airportdetail.vorDemFrequency}}</td>
            <td class="tr-1-company tr-3-company tr-l-company" style="border-bottom: 0"></td>
            <td class="tr-2-company tr-4-company" style="border-bottom: 0"></td>
        </tr>

        </tbody></table>
</template>
<script>
    export default {
        data () {
            return {
                airportdetail: {
                    companyId:'',
                    companyName:'',
                    airportName:'',
                    grade:'',
                    iswater:'',
                    province:'',
                    city:'',
                    longitude:'',
                    latitude:'',
                    property:'',
                    height:'',
                    entNumber:'',
                    orgName:'',
                    icaoNumber:'',
                    iatanumber:'',
                    address:'',
                    serviceTel:'',
                    tel:'',
                    superviseOrgName:'',
                    superviseOrgid:'',
                    authorityName:'',
                    authorityId:'',
                    warZone:'',
                    towerMainFrequency:'',
                    towerReserveFrequency:'',
                    mainFrequency:'',
                    reserveFrequency:'',
                    islandingDevice:'',
                    ndbFrequency:'',
                    vorDemFrequency:'',
                    vfrMap:'',
                    satelliteMap:'',
                    isavailable:'',
                    licenseBegindate:'',
                    licenseEnddate:''
                }

            }
        },
        props: ['idinfobase'],
        watch: {
            // 如果 question 发生改变，这个函数就会运行
            idinfobase: function (newval) {
                this.getData(newval);
            }
        },
        methods: {
            getData:function (newval){
                let self = this;
                self.$http.httpGet('/app/api/airport/search/'+newval, {

                }).then(function (response){
                    let datainfo=response.data;
                    self.airportdetail=datainfo;
                })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
        }
    }
</script>
