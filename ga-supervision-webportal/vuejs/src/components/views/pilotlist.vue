
<template>
    <div style="margin-left: 16px;margin-right: 29px;">

            <ul>
                <div class="" style="margin-bottom: 15px">
                    <div class="" style="width: 197px;height: 34px">
                        <input v-model="keyword" class="" style="color: black;height: 34px;width: 141px;border:solid 1px rgba(1, 107, 217, 1); opacity: 0.65;" />
                        <button class=""  @click="search" style="height: 34px;margin-left: 0px;background-color:rgba(62, 148, 246, 1) ;    border: none;    width: 50px;border-radius: 5px;">查询</button>
                    </div>
                </div>

                <li v-for="pilot in pilots" >
                    <div v-if="employeeId==pilot.employeeId" >
                    <div class="div-listpilot" style="position: relative;color:white;backgroundColor:rgba(62, 148, 246, 1)" @click="pilotDetail(pilot)" v-bind:class="{'li-click': employeeId==pilot.employeeId}"  >
                        <div class="div-1-2"   >{{ pilot.username }}</div>
                    </div>
                    </div>
                    <div v-else class="div-listpilot-one">
                        <div class="div-listpilot" style="position: relative;" @click="pilotDetail(pilot)" v-bind:class="{'li-click': employeeId==pilot.employeeId}"  >
                            <div class="div-1-2"  >{{ pilot.username }}</div>
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
                //增加
                flightrecordId:'',
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
                showflightrecord:false,
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
            if (this.$route.path == '/student') {
                jobTitle1="飞行学员";
            }
            else if(this.$route.path == '/pilot'){
                jobTitle1="飞行人员";
            }


            this.getData(jobTitle1);


        },

        watch: {
            '$route' (to, from) {
                let jobTitle1;
                this.pilots=[];
                if (this.$route.path == '/student') {
                    jobTitle1="飞行学员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/pilot'){
                    jobTitle1="飞行人员";
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
                    this.pilots=[];
                    if (this.$route.path == '/student') {
                        jobTitle1="飞行学员";
                        this.employeeId='';
                    }
                    else if(this.$route.path == '/pilot'){
                        jobTitle1="飞行人员";
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
                this.pilots=[];
                if (this.$route.path == '/student') {
                    jobTitle1="飞行学员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/pilot'){
                    jobTitle1="飞行人员";
                    this.employeeId='';
                }
                this.getData(jobTitle1);
            },
            next () {
                if(this.page=this.pagetatel||this.page>this.pagetatel){
                    return
                }
                this.page++
                let jobTitle1;
                this.pilots=[];
                if (this.$route.path == '/student') {
                    jobTitle1="飞行学员";
                    this.employeeId='';
                }
                else if(this.$route.path == '/pilot'){
                    jobTitle1="飞行人员";
                    this.employeeId='';
                }
                this.getData(jobTitle1);
            },
            getData (val) {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    self.$http.httpGet('/app/api/pilot/search/company', {
                        companyId: profile.buz.companyId,
                        page: self.page,
                        pageSize: self.pageSize,
                        keyword:self.keyword,
                        jobTitle:val
                    }).then((res) => {
                        let result = res.data;
                        if (result.resultCode == 200) {
                            let temp = result.data;

                            if (temp && temp.length > 0) {
                                for(var i=0;i<temp.length;i++){
                                    if(temp[i].flag!=4){
                                        temp[i].flag=-3;
                                    }
                                }
                                self.pilots = temp;
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
                            self.employeeId = self.pilots[0].employeeId;
                            self.$emit('child-info-id',self.employeeId );
                        }
                        else {
                            console.log(result.message)
                        }
                    })
                    self.$http.httpGet('/app/api/pilot/search/companyPilotcountBycompanyId', {
                        companyId: profile.buz.companyId,
                        page: self.page,
                        pageSize: self.pageSize,
                        keyword:self.keyword,
                        jobTitle:val
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
            pilotDetail(pilot){
                this.employeeId = pilot.employeeId;
                this.$emit('child-info-id',this.employeeId);
            },flagfuction:function (obj) {
                let self=this;
                for(var i=0;i<obj.length;i++){
                    if(self.pilots[i].flag!=4){
                    (function (i) {
                        self.$http.httpGet('/app/api/processrun/businessKey',{
                            businessKey:obj[i].employeeId,
                            defId:'TEST'
                        }).then((res) => {
                            if(res.data.data){
                               self.pilots[i].flag=res.data.data.status;
                            }else{
                               self.pilots[i].flag=4;
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
<style >
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
        height: 99px;
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
        height: 34px;
    }
    .div-1-2{
        width: 140px;
        margin: 0 auto;
        text-align: left;
        padding-top: 7px;
        font-size: 14px;
    }
    /*。pilot*/
    .div-listpilot {
        border: solid 1px rgba(1, 107, 217, 1);
        width: 197px;
        height: 34px;
        text-align: center;
        background: rgba(159, 203, 250, 0.5);
        color:rgba(1, 107, 217, 1) ;
        margin-bottom: 5px;
    }
.div-listpilot-one :hover{
    background: rgb(159,203,250);
}
</style>
