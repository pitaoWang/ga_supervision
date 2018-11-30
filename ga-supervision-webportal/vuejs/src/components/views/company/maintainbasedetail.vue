<template>
    <table class='companydetail'>
        <tbody><tr>
            <td class="tr-1-company tr-3-company">维修基地名称</td>
            <td class="tr-2-company tr-4-company">{{modelinfo.maintainbasename}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">机场名称</td>
            <td class="tr-2-company tr-4-company">
               {{modelinfo.airportname}}
            </td>
        </tr>

        <tr>
            <td class="tr-1-company tr-5-company">所在省</td>
            <td class="tr-2-company tr-6-company">{{modelinfo.province}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">所在市</td>
            <td class="tr-2-company tr-6-company">{{modelinfo.city}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company">所属管理局</td>
            <td class='tr-2-company tr-4-company'>{{modelinfo.authorityName}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">所属监管局</td>
            <td class="tr-2-company tr-4-company">
                {{modelinfo.superviseName}}
            </td>
        </tr><tr>
            <td class="tr-1-company tr-5-company" style="border-bottom: 0">联系电话</td>
            <td class="tr-2-company tr-6-company" style="border-bottom: 0">{{modelinfo.telephone}}</td>
            <td class="tr-1-company tr-5-company tr-l-company" style="border-bottom: 0">详细地址</td>
            <td class="tr-2-company tr-6-company" style="border-bottom: 0">{{modelinfo.address}}</td>
        </tr>
        </tbody></table>
</template>
<script>
    import gacopanydata from '../../../data/company/index'
    export default {
        data () {
            return {
                modelinfo: {
                    maintainbasename:'',
                    authorityid:'',
                    superviseid:'',
                    airportid:'',
                    airportname:'',
                    telephone:'',
                    province:'',
                    city:'',
                    address:'',
                    companyId:'',
                    superviseName:'',
                    authorityName:'',
                },
                orgMap:{},
                airportmap:{},
            }
        },
        props: ['maintainbaseid'],
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
                    console.log(result);
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
            getData:function (newval){
                let self = this;
                self.$http.httpGet('/app/api/mb/'+newval, {
                }).then(function (response){
                    let datainfo=response.data;
                   // datainfo.airportId=self.airportmap.get(datainfo.airportId);
                    self.modelinfo.superviseName=self.orgMap.get(datainfo.superviseid);
                    self.modelinfo.authorityName =self.orgMap.get(datainfo.authorityid);
                    self.modelinfo=datainfo;
                    self.modelinfo.airportname = self.airportmap.get(self.modelinfo.airportid);
                }).catch(function (error) {
                            console.log(error);
                        });

            }
        }
    }
</script>
