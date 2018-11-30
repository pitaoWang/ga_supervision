<style>
    .companydetail {
        width: 964px;
        border-spacing: 0px;
        border: 1px solid rgb(1,107,217);
        text-align: left;
        margin-top: 4px;
    }


    .tr-1-company {
        font-size: 14px;
        width: 150px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border:0px ;
        border-bottom: solid 1px #55a2eb ;
    }

    .tr-l-company{
        border-left: solid 1px #55a2eb ;
    }

    .tr-2-company {
        padding-left: 10px;
        padding-right: 10px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border:0px ;
        border-left: solid 1px #55a2eb ;
        border-bottom: solid 1px #55a2eb ;
        text-align: center;
    }
    .tr-3-company{
        background-color: rgb(179,221,252);
    }

    .tr-4-company{
        background-color: rgb(218,236,252);
    }
    .tr-5-company{
        background-color: rgb(201,231,253);
    }
    .tr-6-company{
        background-color: rgb(239,246,253);
    }

</style>
<template>
    <div>
    <table class='companydetail'>
        <tbody><tr>
            <td class="tr-1-company tr-3-company">通航企业名称</td>
            <td class="tr-2-company tr-4-company">{{companyinfo.name}}</td>
            <td class="tr-1-company tr-3-company tr-l-company" rowspan="3">企业Logo</td>
            <td class="tr-2-company tr-4-company" rowspan="3">
                <img :src="companyinfo.logoFile" style="width:180px;padding: 6px;" @click="imgdetail('logo',companyinfo.logoFile)"/>
            </td>
        </tr>

        <tr>
            <td class="tr-1-company tr-5-company">企业法人</td>
            <td class="tr-2-company tr-6-company">{{userIdName}}</td>
        </tr><tr>
            <td class="tr-1-company tr-3-company">所属管理局</td>
            <td class="tr-2-company tr-4-company">{{companyinfo.authorityName}}</td>
        </tr><tr>
            <td class="tr-1-company tr-5-company">所属监管局</td>
            <td class='tr-2-company tr-6-company'>{{companyinfo.supervisionName}}</td>
            <td class="tr-1-company tr-5-company tr-l-company" rowspan="3">运行合格证扫描件</td>
            <td class="tr-2-company tr-6-company" rowspan="3">
                <img :src="companyinfo.registerCertFile" style="width:180px;padding: 6px;" @click="imgdetail('运行合格证扫描件',companyinfo.registerCertFile)"/>
            </td>
        </tr><tr>
            <td class="tr-1-company tr-3-company">运行合格证编号</td>
            <td class="tr-2-company tr-4-company">{{companyinfo.certId}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">企业性质</td>
            <td class="tr-2-company tr-6-company">{{companyinfo.operateType}}</td>
        </tr>


        <tr>
            <td class="tr-1-company tr-3-company">主任运行监察员</td>
            <td class="tr-2-company tr-4-company" >{{companyinfo.poi}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">主任适航监察员</td>
            <td class="tr-2-company tr-4-company" >{{companyinfo.pmi}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">运行合格证颁发日期</td>
            <td class="tr-2-company tr-6-company">{{companyinfo.licenseBegindate}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">运行合格证生效日期</td>
            <td class="tr-2-company tr-6-company" >{{companyinfo.licenseEnddate}}</td>
        </tr>

        <tr>
            <td class="tr-1-company tr-3-company">经营类别</td>
            <td class="tr-2-company tr-4-company" colspan="3">{{companyinfo.businessType}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">经营范围</td>
            <td class="tr-2-company tr-6-company" colspan="3">{{companyinfo.businessScope}}</td>
        </tr>

        <tr>
            <td class="tr-1-company tr-3-company">省份</td>
            <td class="tr-2-company tr-4-company">{{companyinfo.province}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">城市</td>
            <td class="tr-2-company tr-4-company" >{{companyinfo.city}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">企业详细地址</td>
            <td class="tr-2-company tr-6-company" colspan="3">{{companyinfo.address}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company">联系电话</td>
            <td class="tr-2-company tr-4-company">{{companyinfo.telephone}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">邮编</td>
            <td class="tr-2-company tr-4-company">{{companyinfo.postcode}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company">传真</td>
            <td class="tr-2-company tr-6-company" colspan="3">{{companyinfo.fax}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-3-company" style="border-bottom: 0">运行手册</td>
            <td class="tr-2-company tr-4-company" colspan="3" style="border-bottom: 0"><ul>
                <li v-for="item in handbookfilelist" :key="item.url"><a :href="item.url" style="color: rgb(1,107,217);">{{item.name}}</a></li>
            </ul></td>
        </tr>
        </tbody></table>
        <imgdetail ref="imgdetailref"></imgdetail></div>
</template>
<script>
    import gacopanydata from '../../../data/company/index'
    import imgdetail from '../../views/imgdetail.vue';
    export default {
        components:{
            imgdetail
        },
        data () {
            return {
                handbookfilelist:[],
                userIdName:'',
                companyinfo:{
                   companyId:'',
                     orgId:'',
                     userId:'',
                     mainBase:'',
                     createtime:'',
                     updatetime:'',
                     flag:'',
                    name:'',
                    province:'',
                    city:'',
                    authorityId:'',
                    authorityName:'',
                    supervisionId:'',
                    supervisionName:'',
                    operateType:'',
                    address:'',
                    postcode:'',
                    telephone:'',
                    fax:'',
                    certId:'',
                    logoFile:'',
                    registerCertFile:'',
                    businesslist:[],
                    businessType:'',//经营范围类别，多个类别用逗号分隔 1-甲类 2-乙类 3-丙类 4-飞行俱乐部
                    handbookFile:'',
                    poi:'',
                    pmi:'',
                    licenseBegindate: null, /*许可证起始日期*/
                    licenseEnddate: null, /*许可证截止日期*/
                },
                orgMap:{},
                businessTypeMap:{},
            }
        },
        props: ['companyId'],
        created:function () {
            let self = this;
            self.$http.httpGet('/app/api/org/', {}).then(function (res){
                let result = res.data;
                console.log("self.orgMap==",result);
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
        watch: {
            // 如果 question 发生改变，这个函数就会运行
            companyId: function (newval) {
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

                self.$http.httpGet('/app/api/company/'+newval, {

                }).then(function (response){
                    let datainfo=response.data;
                    self.companyinfo=datainfo;

                    self.$http.httpGet('/app/api/legalperson/findByCompanyId', {
                        companyId:datainfo.companyId
                    }).then(function (res){
                        self.userIdName = res.data.legalpersonName;

                    }).catch(function (error) {
                        console.log("comdetail====",error);
                    });
                    self.companyinfo.supervisionName=self.orgMap.get(datainfo.supervisionId);
                    self.companyinfo.authorityName=self.orgMap.get(datainfo.authorityId);
                    self.handbookfilelist=eval(self.companyinfo.handbookFile);
                    var businessTypelist = self.companyinfo.businessType.split(",");
                    self.companyinfo.businessType="";

                    self.businessTypeMap=new Map();

                    self.businessTypeMap.set("1","甲类");
                    self.businessTypeMap.set("2","乙类");
                    self.businessTypeMap.set("3","丙类");
                    self.businessTypeMap.set("4","飞行俱乐部");
                    self.companyinfo.businesslist=[];
                    for(var i=0;i<businessTypelist.length;i++)
                    {

                        self.companyinfo.businesslist.push(self.businessTypeMap.get(businessTypelist[i]));
                    }
                    self.companyinfo.businessType=self.companyinfo.businesslist.join(",");
                })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
        }
    }
</script>
