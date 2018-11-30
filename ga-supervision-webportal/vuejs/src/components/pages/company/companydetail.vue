<template>
    <div style="width: 1280px; height: auto; margin-top: 10px;background:white">


        <div style="margin-top: 10px;">
            <!--<Table border stripe :columns="columns" :data="data"></Table>-->
            <table style="width: 1260px; border-spacing:0px;margin-left: 10px;margin-right: 10px;">
                <tr>
                    <td class="tr-1 tr-5" style="width: 150px !important; text-align: center; " colspan="4">
                        企业基本信息
                    </td>
                </tr>
                <tbody>
                <tr>
                    <td class="tr-1 tr-3">企业名称</td>
                    <td class="tr-2 tr-4">{{gacompany.name}}</td>
                    <td class="tr-1 tr-3" rowspan="4">企业Logo</td>
                    <td class="tr-2 tr-4" rowspan="4">

                        <div  v-if="gacompany.logoFile" style="width: 180px;height: 135px;padding-left: 65px;">
                            <img :src="this.imageUrl+gacompany.logoFile+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail(imageUrl+gacompany.logoFile)">
                        </div>
                        <div v-else>
                            <img :src="noimg" style="width: 180px;height: 135px;padding-left:65px;"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">企业联系电话</td>
                    <td class="tr-2 tr-4">{{gacompany.telephone}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">法人代表</td>
                    <td class="tr-2 tr-4">{{gacompany.legalPerson}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">法人身份证</td>
                    <td class="tr-2 tr-4">{{gacompany.cardid}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">法人联系电话</td>
                    <td class="tr-2 tr-4">{{gacompany.userTel}}</td>
                    <td class="tr-1 tr-3" rowspan="4">工商等级证扫描件</td>
                    <td class="tr-2 tr-4" rowspan="4">
                        <div  v-if="gacompany.registerCertFile" style="width: 180px;height: 135px;padding-left: 65px;">
                            <img :src="this.imageUrl+gacompany.registerCertFile+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail(imageUrl+gacompany.registerCertFile)">
                        </div>
                        <div v-else>
                            <img :src="noimg" style="width: 180px;height: 135px;padding-left:65px;"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">法人性别</td>
                    <td class="tr-2 tr-4">{{gacompany.legalSex}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">法人邮箱</td>
                    <td class="tr-2 tr-4">{{gacompany.legalEmail}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">企业注册地址</td>
                    <td class="tr-2 tr-4">{{gacompany.registerAdd}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">企业注册资本</td>
                    <td class="tr-2 tr-4">{{gacompany.registerCost}}</td>
                    <td class="tr-1 tr-3" rowspan="4">经营许可证扫描件</td>
                    <td class="tr-2 tr-4" rowspan="4">
                        <div  v-if="gacompany.businessCertFile" style="width: 180px;height: 135px;padding-left: 65px;">
                            <img :src="this.imageUrl+gacompany.businessCertFile+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail(imageUrl+gacompany.businessCertFile)">
                        </div>
                        <div v-else>
                            <img :src="noimg" style="width: 180px;height: 135px;padding-left:65px;"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">所属管理局</td>
                    <td class="tr-2 tr-4">{{gacompany.authorityName}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">所属监管局</td>
                    <td class="tr-2 tr-4">{{gacompany.supervisionName}}</td>

                </tr>
                <tr>
                    <td class="tr-1 tr-3">主任运行监查员</td>
                    <td class="tr-2 tr-4">{{gacompany.poi}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">主任适航监察员</td>
                    <td class="tr-2 tr-4">{{gacompany.pmi}}</td>
                    <td class="tr-1 tr-3">基地机场</td>
                    <td class="tr-2 tr-4">{{gacompany.baseAirport}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">经营许可证编号</td>
                    <td class="tr-2 tr-4">{{gacompany.businessid}}</td>
                    <td class="tr-1 tr-3">经营许可证颁发单位</td>
                    <td class="tr-2 tr-4">{{gacompany.businessCompany}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">经营许可证颁发日期</td>
                    <td class="tr-2 tr-4">{{gacompany.businessBegindate}}</td>
                    <td class="tr-1 tr-3">经营许可证有效日期</td>
                    <td class="tr-2 tr-4">{{gacompany.businessEnddate}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">工商管理证编号</td>
                    <td class="tr-2 tr-4">{{gacompany.registerCertCode}}</td>
                    <td class="tr-1 tr-3">工商管理证颁发单位</td>
                    <td class="tr-2 tr-4">{{gacompany.registerCompany}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">工商管理证颁发日期</td>
                    <td class="tr-2 tr-4">{{gacompany.registerBegindate}}</td>
                    <td class="tr-1 tr-3">工商管理证有效日期</td>
                    <td class="tr-2 tr-4">{{gacompany.registerEnddate}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">运行种类</td>
                    <td class="tr-2 tr-4">{{gacompany.standardType}}</td>
                    <td class="tr-1 tr-3">企业类别</td>
                    <td class="tr-2 tr-4">{{gacompany.companyType}}</td>
                </tr>
                <tr>
                    <td class="tr-1 tr-3">经营项目类别</td>
                    <td class="tr-2 tr-4">{{gacompany.businessProject}}</td>
                    <td class="tr-1 tr-3">经营项目</td>
                    <td class="tr-2 tr-4">{{gacompany.businessType}}</td>
                </tr>

                </tbody>
            </table>
        </div>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>

</template>

<script>
    import imgdetail from '../../views/imgdetail.vue';
    export default {
        components: {
            'imgdetail':imgdetail,
        },
        name: "companydetail",
        props:['gacompanyinfo'],
        data(){
            return{
                gacompany:{},
                authorityName:'',
                supervisionName:'',
            }
        },
        methods:{
            imgdetail:function (name) {
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },

        },
        mounted(){

            this.gacompany = JSON.parse(this.$route.query.gacompany);
            this.$http.httpGet('/eim/api/company/'+ this.gacompany.companyId).then((res) => {
                this.gacompany = res.data;
                if(this.gacompany.legalSex==1){
                    this.gacompany.legalSex='男'
                }
                else if(this.gacompany.legalSex==2){
                    this.gacompany.legalSex='女'
                }
                if(!this.gacompany.registerCost &&typeof (this.gacompany.registerCost)!="undefined"){
                    this.gacompany.registerCost = 0+'万元'
                }else{
                    this.gacompany.registerCost = this.gacompany.registerCost+'万元'

                }

                if(!this.gacompany.standardType && typeof(this.gacompany.standardType)!="undefined" && this.gacompany.standardType!=0){

                }else{
                    let standarTypeArray = this.gacompany.standardType.split(',');
                    let standarArray = []
                    for(let i=0;i<standarTypeArray.length;i++){
                        if(standarTypeArray[i]==1){
                            standarTypeArray[i]='一般商业飞行'
                        }
                        if(standarTypeArray[i]==2){
                            standarTypeArray[i]='农林喷洒'
                        }
                        if(standarTypeArray[i]==3){
                            standarTypeArray[i]='外载荷作业'
                        }
                        if(standarTypeArray[i]==4){
                            standarTypeArray[i]='训练飞行'
                        }
                        if(standarTypeArray[i]==5){
                            standarTypeArray[i]='空中游览'
                        }
                        if(standarTypeArray[i]==6){
                            standarTypeArray[i]='小型运输'
                        }
                        if(standarTypeArray[i]==7){
                            standarTypeArray[i]='大型运输'
                        }
                        standarArray.push(standarTypeArray[i])
                    }
                    this.gacompany.standardType = standarArray.join(',');
                }


                if(!this.gacompany.companyType && typeof(this.gacompany.companyType)!="undefined" && this.gacompany.companyType!=0){

                }else{
                    let companyTypeArray = this.gacompany.companyType.split(',');
                    let companyArray = [];
                    for(let i=0;i<companyTypeArray.length;i++){
                        if(companyTypeArray[i]==1){
                            companyTypeArray[i]='合资'
                        }
                        if(companyTypeArray[i]==2){
                            companyTypeArray[i]='独资'
                        }
                        if(companyTypeArray[i]==3){
                            companyTypeArray[i]='国有'
                        }
                        if(companyTypeArray[i]==4){
                            companyTypeArray[i]='私营'
                        }
                        if(companyTypeArray[i]==5){
                            companyTypeArray[i]='全民所有制'
                        }
                        if(companyTypeArray[i]==6){
                            companyTypeArray[i]='集体所有制'
                        }
                        if(companyTypeArray[i]==7){
                            companyTypeArray[i]='股份制'
                        }
                        if(companyTypeArray[i]==8){
                            companyTypeArray[i]='有限责任'
                        }
                        companyArray.push(companyTypeArray[i])
                    }
                    this.gacompany.companyType = companyArray.join(',')
                }

                if(!this.gacompany.businessProject && typeof(this.gacompany.businessProject)!="undefined" && this.gacompany.businessProject!=0){

                }else{
                    let businessProjectArray = this.gacompany.businessProject.split(',');
                    let businessArray = [];
                    for(let i=0;i<businessProjectArray.length;i++){
                        if(businessProjectArray[i]==1){
                            businessProjectArray[i]='经营'
                        }
                        if(businessProjectArray[i]==2){
                            businessProjectArray[i]='非经营'
                        }
                        businessArray.push(businessProjectArray[i]);
                    }
                    this.gacompany.businessProject = businessArray.join(',')
                }


                if(!this.gacompany.businessType && typeof(this.gacompany.businessType)!="undefined" && this.gacompany.businessType!=0){

                }else{
                    let businessTypeArray = this.gacompany.businessType.split(',');
                    let businessTypeArray1 = [];
                    for(let i=0;i<businessTypeArray.length;i++){
                        if(businessTypeArray[i]==1){
                            businessTypeArray[i]='甲类'
                        }
                        if(businessTypeArray[i]==2){
                            businessTypeArray[i]='乙类'
                        }
                        if(businessTypeArray[i]==3){
                            businessTypeArray[i]='丙类'
                        }
                        if(businessTypeArray[i]==4){
                            businessTypeArray[i]='丁类'
                        }
                        businessTypeArray1.push(businessTypeArray[i]);
                    }
                    this.gacompany.businessType = businessTypeArray1.join(',')

                }

                this.$http.httpGet('/eim/api/company/selectsupvisionnamebysupid?id='+this.gacompany.authorityId).then(res=>{
                    this.authorityName = res.data;
                })
                this.$http.httpGet('/eim/api/company/selectsupvisionnamebysupid?id='+this.gacompany.supervisionId).then(res=>{
                    this.supervisionName = res.data;
                })
            }).catch(function (error) {
                console.log(error);
            });

        }
    }
</script>

<style scoped>
    .tr-1 {
        font-size: 14px;
        width: 180px;
        padding-left: 13px;
        height: 45px;
        color: #606265;
        border-left: solid 1px #DCDFE6;
        border-right: solid 1px #DCDFE6;
        border-bottom: solid 1px #DCDFE6;
        background-color: #F8F8F9;
    }

    .tr-2 {
        font-size: 14px;
        width: 200px;
        text-align: center;
        color: #606265;
        background-color: #FFF;
    }

    table {
        border-collapse: collapse;
    }

    table tr td {
        border: 1px solid #DCDFE6;
    }

    .tr-3 {
        background-color: #f8f8f9;
    }

    /*.tr-4{*/
    /*background-color: #FFF;*/
    /*}*/
    .tr-5 {
        background-color: #f8f8f9;
    }

    .flightdispatcherimg > img {
        float: left;
        margin-left: 1%;

    }

    .infoPart1 table {
        border-collapse: collapse;
        border-spacing: 0;
        border-left: 1px solid #888;
        border-top: 1px solid #888;
        background: #efefef;
        width: 100%;
    }

    .div-button {
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }
</style>