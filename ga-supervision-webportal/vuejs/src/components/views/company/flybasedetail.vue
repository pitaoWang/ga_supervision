<template>
    <table class='companydetail'>
        <tbody><tr>
            <td class="tr-1-company tr-3-company">飞行基地名称</td>
            <td class="tr-2-company tr-4-company">{{modelinfo.flybasename}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">机场名称</td>
            <td class="tr-2-company tr-4-company">
                {{modelinfo.airportId}}
            </td>
        </tr>

        <tr>
            <td class="tr-1-company tr-5-company">所在省</td>
            <td class="tr-2-company tr-6-company">{{modelinfo.flybaseProvince}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">所在市</td>
            <td class="tr-2-company tr-6-company">{{modelinfo.flybaseCity}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company">所属管理局</td>
            <td class='tr-2-company tr-4-company'>{{modelinfo.authorityName}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">所属监管局</td>
            <td class="tr-2-company tr-4-company">
                {{modelinfo.superviseName}}
            </td>
        </tr><tr>
            <td class="tr-1-company tr-5-company">基地类型</td>
            <td class="tr-2-company tr-6-company">{{modelinfo.baseType}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">联系电话</td>
            <td class="tr-2-company tr-6-company">{{modelinfo.flybaseTel}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company" style="border-bottom: 0px">详细地址</td>
            <td class="tr-2-company tr-4-company" style="border-bottom: 0px">{{modelinfo.flybaseDetailedAdress}}</td>
            <td class="tr-1-company tr-3-company tr-l-company" style="border-bottom: 0px"></td>
            <td class="tr-2-company tr-4-company" style="border-bottom: 0px"></td>
        </tr>
        </tbody></table>
</template>
<script>
    import flybasedata from '../../../data/flybase/index';
    export default {
        data () {
            return {
                modelinfo: {
                    flybaseid:'',
                    flybasename:'',
                    authorityId:'',
                    superviseName:'',
                    supervisionId:'',
                    authorityName:'',
                    airportId:'',
                    flybaseTel:'',
                    flybaseProvince:'',
                    flybaseCity:'',
                    flybaseDetailedAdress:'',
                    gaCompanyId:'',/*用于向后台传输id,保存到关联表*/
                    baseType:''
                },
                OrgMap:{},
                airportmap:{},

            }
        },
        props: ['flybaseid'],
//        watch: {
//            // 如果 question 发生改变，这个函数就会运行
//            flybaseid: function (newval) {
//                this.getData(newval);
//            }
//        },
        created:function()
        {
            let self = this;
            self.$http.httpGet('/app/api/airport/', {
            }).then((res) => {
                let result = res;
                if (result.status == 200) {
                    let temp = result.data;
                    self.airportmap=new Map();
                    for(var i=0;i<temp.length;i++){
                        self.airportmap.set(temp[i].airportId,temp[i].airportName);
                    }
                }
                else {
                }

                self.loading = false;
            });
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
            initinfo(newval){
                this.getData(newval);
            },
            getData:function (newval){
                let self = this;
                self.$http.httpGet('/app/api/flybase/'+newval, {
                }).then(function (response){
                    let datainfo=response.data;
                    datainfo.airportId=self.airportmap.get(datainfo.airportId);
                    self.modelinfo.superviseName=self.orgMap.get(datainfo.supervisionId);
                    self.modelinfo.authorityName =self.orgMap.get(datainfo.authorityId);
                    self.modelinfo=datainfo;
                })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
        }
    }
</script>
