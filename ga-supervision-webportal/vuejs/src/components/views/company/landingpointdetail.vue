<template>
    <table class='companydetail'>
            <tbody><tr>
                <td class="tr-1-company tr-3-company">起降点名称</td>
                <td class="tr-2-company tr-4-company">{{modelinfo.name}}</td>
                <td class="tr-1-company tr-3-company tr-l-company">消防等级</td>
                <td class="tr-2-company tr-4-company">
                    {{modelinfo.fireGrade}}
                </td>
            </tr>

            <tr>
                <td class="tr-1-company tr-5-company">所属管理局</td>
                <td class='tr-2-company tr-6-company'>{{modelinfo.manageOrgName}}</td>
                <td class="tr-1-company tr-5-company tr-l-company">所属监管局</td>
                <td class="tr-2-company tr-6-company">
                    {{modelinfo.superviseOrgName}}
                </td>
            </tr>

            <tr>
                <td class="tr-1-company tr-3-company">经度</td>
                <td class='tr-2-company tr-4-company'>{{modelinfo.longitude}}</td>
                <td class="tr-1-company tr-3-company tr-l-company">纬度</td>
                <td class="tr-2-company tr-4-company">
                    {{modelinfo.latitude}}
                </td>
            </tr>

            <tr>
                <td class="tr-1-company tr-5-company">起降点等级</td>
                <td class='tr-2-company tr-6-company'>{{modelinfo.landingGrade}}</td>
                <td class="tr-1-company tr-5-company tr-l-company">是否有导航设施</td>
                <td class="tr-2-company tr-6-company">
                    {{modelinfo.iscontroltower}}
                </td>
            </tr>
            <tr>
                <td class="tr-1-company tr-3-company">是否有导航设施</td>
                <td class='tr-2-company tr-4-company'>{{modelinfo.isnavidevice}}</td>
                <td class="tr-1-company tr-3-company tr-l-company">海拔高度</td>
                <td class="tr-2-company tr-4-company">
                    {{modelinfo.altitude}}
                </td>
            </tr>

            <tr>
                <td class="tr-1-company tr-5-company">许可证起始日期</td>
                <td class='tr-2-company tr-6-company'>{{modelinfo.licenseBegindate}}</td>
                <td class="tr-1-company tr-5-company tr-l-company">许可证截止日期</td>
                <td class="tr-2-company tr-6-company">
                    {{modelinfo.licenseEnddate}}
                </td>
            </tr>
            <tr>
                <td class="tr-1-company tr-3-company">所在省</td>
                <td class='tr-2-company tr-4-company'>{{modelinfo.province}}</td>
                <td class="tr-1-company tr-3-company tr-l-company">所在市</td>
                <td class="tr-2-company tr-4-company">
                    {{modelinfo.city}}
                </td>
            </tr>
            <tr>
                <td class="tr-1-company tr-5-company">联系电话</td>
                <td class="tr-2-company tr-6-company">{{modelinfo.tel}}</td>
                <td class="tr-1-company tr-5-company tr-l-company">详细地址</td>
                <td class="tr-2-company tr-6-company">{{modelinfo.address}}</td>
            </tr>
            <tr>
                <td class="tr-1-company tr-3-company" style="border-bottom: 0px">是否可用</td>
                <td class="tr-2-company tr-4-company" style="border-bottom: 0px">{{modelinfo.isavailable}}</td>
                <td class="tr-1-company tr-3-company tr-l-company" style="border-bottom: 0px">是否有导航设施</td>
                <td class="tr-2-company tr-4-company" style="border-bottom: 0px">{{modelinfo.isnavidevice}}</td>
            </tr>
            </tbody></table>
</template>
<script>
    import gacopanydata from '../../../data/company/index'
    export default {
        data () {
            return {
                modelinfo: {
                    id:'',
                    name: '',
                    longitude:'',
                    latitude:'',
                    landingGrade:'',
                    isnightfight:'',/*是否支持夜航*/
                    iscontroltower:'',/* 是否塔台服务*/
                    isnavidevice:'',/* 是否有导航设施*/
                    fireGrade:'',/* 消防等级*/
                    altitude:'',/*海拔高度*/
                    licenseBegindate:null,/*许可证起始日期*/
                    licenseEnddate:null,/*许可证截止日期*/
                    isavailable:'',/*是否可用*/
                    province: '',
                    city:'',
                    manageOrgId:'',
                    manageOrgName:'',
                    superviseOrgId:'',
                    superviseOrgName:'',
                    address:'',
                    tel:'',
                    gaCompanyId:'',
                },
                orgMap:{},
            }
        },
        props: ['landingpointid'],
//        watch: {
//            // 如果 question 发生改变，这个函数就会运行
//
//            landingpointid: function (newval) {
//                this.getData(newval);
//            }
//        },
        created:function () {
            let self = this;
            self.$http.httpGet('/app/api/org/', {}).then(function (res){
                let result = res.data;
                self.orgMap=new Map();
                if (result.resultCode == 200) {
                    let temp = result.data;

                    for(var i=0;i<temp.length;i++){
                        self.orgMap.set(temp[i].id,temp[i].name);
                    }
                }
            }).catch(function (error) {
                console.log(error);
            });
        },
        methods: {
            getData:function (newval){
                let self = this;
                self.$http.httpGet('/app/api/landingpoint/'+newval, {


                }).then(function (response){
                    let datainfo=response.data;
                    self.modelinfo=datainfo;
                    self.modelinfo.superviseOrgName=self.orgMap.get(datainfo.superviseOrgId);
                    self.modelinfo.manageOrgName=self.orgMap.get(datainfo.manageOrgId);
                    self.modelinfo.isnightfight=datainfo.isnightfight==0?'不支持':'支持';
                    self.modelinfo.iscontroltower=datainfo.iscontroltower==0?'不支持':'支持';
                    self.modelinfo.isnavidevice=datainfo.isnavidevice==0?'不支持':'支持';
                    self.modelinfo.isavailable=datainfo.isavailable==0?'是':'否';
                    var  strlist = [];
                    if(typeof (self.modelinfo.latitude)!='undefined') {
                        strlist = self.modelinfo.latitude.split('-');
                        if(strlist.length>2) {
                            if(strlist[0]==undefined)
                            {
                                strlist[0]='  '
                            }
                            if(strlist[1]==undefined)
                            {
                                strlist[1]='  '
                            }
                            if(strlist[2]==undefined)
                            {
                                strlist[2]='  '
                            }
                            self.modelinfo.latitude = strlist[0] + ' 度 ' + strlist[1] + ' 分 ' + strlist[2] + ' 秒 ';
                        }else {
                            self.modelinfo.latitude="";
                        }
                    }
                    if(typeof (self.modelinfo.longitude)!='undefined') {
                        strlist = self.modelinfo.longitude.split('-');
                        if(strlist.length>2) {

                            if(strlist[0]==undefined)
                            {
                                strlist[0]='  '
                            }
                            if(strlist[1]==undefined)
                            {
                                strlist[1]='  '
                            }
                            if(strlist[2]==undefined)
                            {
                                strlist[2]='  '
                            }

                            self.modelinfo.longitude = strlist[0] + ' 度 ' + strlist[1] + ' 分 ' + strlist[2] + ' 秒 ';
                        }else{
                            self.modelinfo.longitude="";
                        }
                    }
                })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
        }
    }
</script>
