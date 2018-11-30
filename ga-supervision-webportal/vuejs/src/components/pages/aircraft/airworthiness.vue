
<template>
    <table class='aircraftlise'>
         <tbody><tr>
           <td class="tr-1 tr-3">航空器登记号</td>
           <td class="tr-2 tr-4">{{infodata.registernumber}}</td>
           <td class="tr-1 tr-3 tr-l">航空器型号</td>
            <td class="tr-2 tr-4">{{infodata.aircraftModel}}</td>
            </tr><tr>
            <td class="tr-1 tr-5">出厂序列号</td>
            <td class="tr-2 tr-6">{{infodata.airframeid}}</td>
            <td class="tr-1 tr-5 tr-l">适航证编号</td>
            <td class="tr-2 tr-6">{{infodata.airworthinessCertificateNumber}}</td>
            </tr><tr>
            <td class="tr-1 tr-3">电台制执照编号</td>
            <td class="tr-2 tr-4">{{infodata.radioLicenceNumber}}</td>
            <td class="tr-1 tr-3 tr-l">航空器出厂日期</td>
            <td class="tr-2 tr-4">{{infodata.productionDate}}</td>
            </tr><tr>
            <td class="tr-1 tr-5" style="border-bottom: 0">所属企业</td>
            <td class="tr-2 tr-6" style="border-bottom: 0">{{infodata.companyId.name}}</td>
             <td class="tr-1 tr-5 tr-l" style="border-bottom: 0"></td>
             <td class="tr-2 tr-6" style="border-bottom: 0"></td>
             </tr>
        </tbody></table>
</template>
<script>
    export default {
        data () {
            return {
                infodata:{
                    aircraftRegid:1466,
                    aircraftModel:1233,
                    airframeid:126746346887,
                    airworthinessCertificateNumber:263864264836846,
                    radioLicenceNumber:76485983645498,
                    productionDate:24465766576,
                    manufacturer:3754785684368436
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
                self.$http.httpGet('/app/api/aircraft/'+newval,{
//                    params: {
//                        ID: newval
//                    }
                }).then(function (response) {
                    let datainfo=response.data;
                    self.infodata=datainfo;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }

    }
</script>
