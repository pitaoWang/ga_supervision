<style>
    .com-quali {
        width: 964px;
        border-spacing: 0px;
        border: 1px solid rgb(1,107,217);
        text-align: left;
        margin-top: 4px;
    }
    .com-quali .tr-1 {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border:0px ;
        border-bottom: solid 1px #55a2eb ;
    }

    .com-quali .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        width: 286px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border:0px ;
        border-left: solid 1px #55a2eb ;
        border-bottom: solid 1px #55a2eb ;
        text-align: center;
    }
    .com-quali .tr-3{
        background-color: rgb(179,221,252);
    }

    .com-quali .tr-4{
        background-color: rgb(218,236,252);
    }

    .com-quali .tr-5{
        background-color: rgb(201,231,253);
    }

    .com-quali .tr-6{
        background-color: rgb(239,246,253);
    }

</style>
<template>
    <div >
    <table class='com-quali'  v-for="(item, index) in formValidate.items" >
        <tbody><tr>
            <td class="tr-1 tr-3">资质名称</td>
            <td class="tr-2 tr-4">{{item.qualificationname}}</td>
            <td class="tr-2 tr-4" rowspan="5" style="border-bottom: 0"><img :src="item.attchmentaddress" style="width: 300px;margin: auto"  @click="imgdetail('',item.attchmentaddress)"></td>
        </tr>

        <tr>
            <td class="tr-1 tr-5">证书编号</td>
            <td class="tr-2 tr-6">{{item.code}}</td>
        </tr><tr>
            <td class="tr-1 tr-3">生效时间</td>
            <td class="tr-2 tr-4">{{item.starttime}}</td>
        </tr><tr>
            <td class="tr-1 tr-5">到期时间</td>
            <td class='tr-2 tr-6'>{{item.endtime}}</td>
        </tr><tr>
            <td class="tr-1 tr-3" style=" border-bottom: 0">颁发单位</td>
            <td class="tr-2 tr-4" style=" border-bottom: 0" >{{item.issueorgname}}</td>
        </tr>
        </tbody></table>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>
<script>
    import word from './uploadfile.vue';
    import imgdetail from '../../views/imgdetail.vue';
    export default {
        data() {
            return {


                uploadListarray: [],
                formdatearray: [],

                formValidate: {
                    items: [
                        {

                        },
                    ]

                },

            }
        },
        props: ['companyId'],
        watch:{
            companyId: function (newval) {
                this.getqua(newval);
            }
        },
        components: {
            'word': word,
            imgdetail
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            getqua: function (newval) {
                let self = this;
                /*self.gacompany={name:'xxx',authorityId:1,postcode:10}*/
                self.$http.httpGet('/app/api/cq/serach/getbycompanyid', {
                            companyId:newval
                }).then(function (response) {

                    let datainfo = response.data;
                    self.formValidate.items.splice(0, 1);
                    for (var i = 0; i < datainfo.length; i++) {
//

                        self.formValidate.items.push(datainfo[i]);
                    }

                }).catch(function (error) {
                    console.log(error);
                });
            },


        }

    }

</script>