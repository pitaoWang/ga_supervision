
<template>
    <div style="margin-right: 29px;margin-left: 16px">

            <ul>
                <div class="" style="margin-bottom: 15px">
                    <div class="" style="width: 197px;height: 34px">
                        <input v-model="keyword" class="" style="color: black;height: 34px;width: 141px;border:solid 1px rgba(1, 107, 217, 1);opacity: 0.65; " />
                        <button class=""  @click="search" style="height: 34px;margin-left: 0px;background-color:rgba(62, 148, 246, 1) ;    border: none;    width: 50px;border-radius: 5px;">查询</button>
                    </div>
                </div>


                <li v-for="employee in employeelist" >
                    <div v-if="employeeId==employee.employeeId" >
                    <div class="div-listpilot" style="position: relative;color:white;backgroundColor:rgba(62, 148, 246, 1)" @click="employeeDetail(employee)"   >
                        <div class="div-1-2" style="margin-left: 20px"  >{{ employee.userName }}</div>
                    </div>
                    </div>
                    <div v-else class="div-listpilot-one">
                        <div class="div-listpilot" style="position: relative; " @click="employeeDetail(employee)">
                            <div class="div-1-2" style="margin-left: 20px"  >{{ employee.userName }}</div>
                        </div>

                    </div>
                </li>
                <div class="btm">
                    <img src="../../../static/img/jiantou.png"  class="img-input-page" @click="previous()">
                    <div style="color:rgba(1, 107, 217, 1); display: initial; font-size: 14px;">{{page}}/{{pagetatel}}</div>
                    <img src="../../../static/img/jiantou1.png" @click="next()" class="img-input-page">
                </div>
            </ul>
        </div>


</template>
<script>


    export default {
        components: {

        },
        data () {
            return {
                employeelist:[],
                licensetype:'',
                licenseId:'',
                traincertificateId:'',
                traininfoId:'',
                personexamid:'',
                physicalrecid:'',
                showpdetail:true,
                traininfoshow:false,
                traincertificateshow:false,
                showpersonexam:false,
                showphysicalrecord:false,
                weatherlicenseshow:false,
                showflylicense:false,
                showflightdispatcher:false,
                showintelligence:false,
                showaircraftpartrepair:false,
                showaircraftrepair:false,

                keyword: '',
                employeeId:0,
                pilots: [],
                page: 1,
                pageSize: 10,
                loading: false,
                cid:1,
                pagetatel:''
            }
        },
        created () {
            let jobTitle1;
            if (this.$route.path == '/aircraftcrew') {
                jobTitle1="机务人员";
            }
            else if(this.$route.path == '/othercrew'){
                jobTitle1="其他人员";
            }

            else if(this.$route.path == '/flightcrew'){

                jobTitle1="航务人员";
            }
            this.getData(jobTitle1);


        },

        watch: {
            '$route' (to, from) {
                let jobTitle1;
                this.employeelist=[];
                this.page=1;
                this.keyword='';
                if (this.$route.path == '/aircraftcrew') {
                    jobTitle1="机务人员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/othercrew'){
                    jobTitle1="其他人员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/flightcrew'){
                    jobTitle1="航务人员";
                    this.employeeId='';
                }
                this.getData(jobTitle1);


            }

        },
        methods: {


            search:function(){//搜索
                var _this=this;

                if(_this.keyword){
                    let jobTitle1;
                    this.employeelist=[];
                    if (this.$route.path == '/aircraftcrew') {
                        jobTitle1="机务人员";
                        this.employeeId='';
                    }
                    else if(this.$route.path == '/othercrew'){
                        jobTitle1="其他人员";
                        this.employeeId='';
                    }
                    else if(this.$route.path == '/flightcrew'){
                        jobTitle1="航务人员";
                        this.employeeId='';
                    }
                    this.getData(jobTitle1);
                }else{
                    alert('请输入筛选条件!')
                };
            },
            showmodel:function (){
                this.isdiv=true;
            },
            closemodelchild:function (msg) {
                this.isdiv=false;
            },
            mouseoutcolor:function (event) {
                event.target.parentNode.style.background="#353535"
            },
            mouseovercolor:function (event) {
                event.target.parentNode.style.background="#afafaf"
            },
            previous () {

                if (this.page === 1){
                    alert('已是第一页');
                    return
                } else {
                    this.page--
                }
                let jobTitle1;
                this.employeelist=[];
                if (this.$route.path == '/aircraftcrew') {
                    jobTitle1="机务人员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/othercrew'){
                    jobTitle1="其他人员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/flightcrew'){
                    jobTitle1="航务人员";
                    this.employeeId='';
                }
                this.getData(jobTitle1);

            },
            next () {
                if(this.page=this.pagetatel||this.page>this.pagetatel){
                    return
                }
                this.page++;
                let jobTitle1;
                this.employeelist=[];
                if (this.$route.path == '/aircraftcrew') {
                    jobTitle1="机务人员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/othercrew'){
                    jobTitle1="其他人员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/flightcrew'){
                    jobTitle1="航务人员";
                    this.employeeId='';
                }
                this.getData(jobTitle1);

            },
            getData (jobTitle1) {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    self.$http.httpGet('/app/api/employee/search/findEmployeeByjobtitleAndcompanyId', {
                        companyId: profile.buz.companyId,
                        page: self.page,
                        pageSize: self.pageSize,
                        keyword:self.keyword,
                        jobTitle:jobTitle1
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            let temp = result.data;
                            if (temp && temp.length > 0) {
                                self.employeelist = temp;
                                for(var i=0;i<self.employeelist.length;i++){
                                    if(self.employeelist[i].flag!=4){
                                        self.employeelist[i].flag=-3;
                                    }
                                }
                                self.flagfuction(temp);
                            }else {
                                this.$message('已是最后一页');
                            }
                            // self.pageSize = 2;
                            // self.page = ;
                            // console.log('总记录数 : ' + result.data.totalElements);
                            // console.log('总页数 : ' + result.data.totalPages);
                            // console.log('当前页 : ' + (result.data.number + 1));
                            // console.log('页记录数 : ' + result.data.size);
                            self.employeeId = self.employeelist[0].employeeId;
                            self.$emit('child-info',self.employeeId );
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                    self.$http.httpGet('/app/api/employee/search/findcountEmployeeByjobtitleAndcompanyId', {
                        companyId: profile.buz.companyId,
                        page: self.page,
                        pageSize: self.pageSize,
                        keyword:self.keyword,
                        jobTitle:jobTitle1
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            let pagecount = result.data;
                            if(pagecount<self.pageSize||pagecount==self.pageSize){
                                self.pagetatel=1;
                            }else{
                                self.pagetatel=parseInt(pagecount/self.pageSize)+1
                            }
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                }, (error) => {
                    console.log('error', error);
                });

            },
            employeeDetail(employee){
                this.employeeId = employee.employeeId;
                this.$emit('child-info',this.employeeId );
            },flagfuction:function (obj) {
                let self=this;
                for(var i=0;i<obj.length;i++){
                    if(self.employeelist[i].flag!=4){
                    (function (i) {
                        self.$http.httpGet('/app/api/processrun/businessKey',{
                            businessKey:obj[i].employeeId,
                            defId:'TEST'
                        }).then((res) => {
                            if(res.data.data){
                                self.employeelist[i].flag=res.data.data.status;
                            }else{
                                self.employeelist[i].flag=4;
                            }
                        }).catch(function (error) {
                                console.log(error);
                        });
                    })(i);
                    }
                }
            }
        }
    }
</script>
<style scoped>
    .btm {
        margin-top: 20px;
        text-align: center;
    }
    .div-list{
        border: solid 1px #ececec;
        width: 190px;
        height: 30px;
        text-align: center;
        background: #353535;
        color: white;
        margin-bottom: 5px;
    }
    .img-input-page{
        display: initial;
    }
    .div-input{
        height: 30px;
        width: 190px;
    }
    .div-input-input{
        width: 125px;
        height: 26px;
    }
    .div-button{
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }
    .div-chxun{
        margin-bottom: 15px;
        height: 30px;
    }
    .div-1-2{
        width: 140px;
        margin: 0 auto;
        text-align: left;
        padding-top: 5px;
    }
    .div-listpilot {
        border: solid 1px rgba(1, 107, 217, 1);
        width: 197px;
        height: 34px;
        text-align: center;
        background: rgba(159, 203, 250, 0.5);
        color:rgba(1, 107, 217, 1) ;
        margin-bottom: 5px;
    }
.div-listemployee{
    border: solid 1px #ececec;
    width: 190px;
    height: 30px;
    text-align: center;
    background: #353535;
    color: white;
    margin-bottom: 5px;
}
    .div-listpilot-one :hover{
        background: rgb(159,203,250);
    }
</style>