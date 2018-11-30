<style>
    .miantainbase-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
    .miantainbase-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
</style>
<template>
    <div style="width: 900px; background-color:rgb(220,238,255);">
    <Form ref="maintainbase" :model="maintainbase" :rules="ruleValidate" :label-width="110"  style=" background-color:rgb(220,238,255);width: 820px; height: 450px; padding: 10px">
        <FormItem label="基地名称" prop="maintainbasename" >
            <Input v-model="maintainbase.maintainbasename" placeholder="" ></Input>
        </FormItem>
        <FormItem label="所在省" prop="province" style="width: 390px;display: inline-block;">
            <Select v-model="maintainbase.province" placeholder="" @on-change="getProv(maintainbase.province)">
                <Option v-for="item in cityList" :value="item.p" :key="item.p">{{ item.p }}</Option>
            </Select>
        </FormItem>
        <FormItem label="所在市" prop="city" style="width: 390px;display: inline-block;margin-left: 15px;">
            <Select v-model="maintainbase.city"  placeholder="" @on-change="geiairport">
                <Option v-for="item in citys" :key="item.n" :value="item.n">{{ item.n }} </Option>
            </Select>
        </FormItem>
        <FormItem label="机场" prop="airportid" style="width: 390px;display: inline-block;">
            <Select v-model.number="maintainbase.airportId" placeholder="">
                <Option v-for="item in airport" :key="item.airportId" :value="item.airportId">{{ item.airportName }} </Option>
            </Select>
        </FormItem>
        <FormItem label="联系电话" prop="telephone" style="width: 390px;display: inline-block;margin-left: 15px;">
            <Input v-model="maintainbase.telephone" placeholder=""></Input>
        </FormItem>
        <!--<FormItem label="經度" prop="maintainbaseLongitude" style="width: 390px;display: inline-block;" >-->
            <!--<Input  v-model="maintainbase.maintainbaseLongitude" placeholder=""></Input>-->
        <!--</FormItem>-->
        <!--<FormItem label="纬度" prop="maintainbaseLatitude" style="width: 390px;display: inline-block;margin-left: 15px;">-->
            <!--<Input v-model="maintainbase.maintainbaseLatitude" placeholder=""></Input>-->
        <!--</FormItem>-->
        <FormItem label="所属管理局" prop="authorityid" style="width: 390px;display: inline-block;">
            <Select v-model.number="maintainbase.authorityid" placeholder="">
                <Option v-for="site in authority" :key="site.id" :value="site.id">{{ site.name }}</Option>
            </Select>
        </FormItem>
        <FormItem label="所属监管局" prop="superviseid" style="width: 390px;display: inline-block;margin-left: 15px;">
            <Select v-model.number="maintainbase.superviseid" placeholder="">
                <Option v-for="site in supervision" :key="site.id" :value="site.id">{{ site.name }}</Option>
            </Select>
        </FormItem>
        <FormItem label="详细地址" prop="address">
            <Input v-model="maintainbase.address" placeholder=""></Input>
        </FormItem>
        <FormItem style="text-align: center;padding-bottom: 30px; height: 25px" class="miantainbase-div">
            <a data-v-942377fa="" href="javascript:;" @click="handleSubmit('flyBase')" class="miantainbase-btn">保存</a>
            <a data-v-942377fa="" href="javascript:;" @click="closemiantainbase" class="miantainbase-btn">返回</a>
        </FormItem>
    </Form>
    </div>
</template>

<script>
    import airportdata from '../../../data/airport/index';
    export default {
        data () {
            return {
                airport:[],
                cityList:airportdata.allcitys,
                citys:[],
                authority:[],
                supervision:[],
                maintainbase: {
                    airportid:'',
                    authorityid:'',
                    city:'',
                    flag:'',
                    gaCompanyId:'',
                    maintainbaseid:'',
                    maintainbasename:'',
                    postcode:'',
                    province:'',
                    superviseid:'',
                    telephone:'',
                    address:''
                },
                ruleValidate: {
                    maintainbasename: [
                        { required: true, message: '基地名称不能为空', trigger: 'blur' }
                    ],
                    authorityid: [
                        { required: true, type:'number',message: '所属管理局不能为空', trigger: 'change' }
                    ],
                    superviseid:[
                        { required: true, type:'number',message: '所属监管局不能为空', trigger: 'change' }
                    ],
//                    airportId: [
//                        { required: true,type: 'number', message: '机场不能为空', trigger: 'change' }
//                    ],
//                    maintainbaseProvince:[
//                        { required: true, message: '所在省不能为空', trigger: 'blur' }
//                    ],
//                    maintainbaseCity:[
//                        { required: true, message: '所在市不能为空', trigger: 'change' }
//                    ],
                    telephone: [
                        { required: true, message: '电话不能为空', trigger: 'blur' }
                    ],
                    address: [
                        { required: true, message: '详细地址不能为空', trigger: 'blur' }
                    ]

                }
            }
        },

        created: function (newval) {
            let self = this;

            self.$http.httpGet('/app/api/company/search/orgname', {
                //查找全部管理局信息
                orgtype:2
            }).then(function (response) {
                let datainfo=response.data;
                self.authority=datainfo;
            }) .catch(function (error) {
                console.log(error);
            });

            self.$http.httpGet('/app/api/company/search/orgname', {
                //查找全部监管局信息
                orgtype:1
            }).then(function (response) {
                let datainfo=response.data;
                self.supervision=datainfo;
            }) .catch(function (error) {
                console.log(error);
            });
            self.$http.httpGet('/app/api/airport',{

            }).then(function (response) {
//                        tempairport.push
                self.airport=response.data;
//                self.$Message.success('提交成功!');
            }).catch(function (error) {
                console.log(error);
                self.$Message.error('操作失败');
            });

        },

        methods: {
            handleSubmit (name) {

                        let self = this;
                    //    let path=self.$axios.defaults.baseURL;
                self.$profile.getProfile().then((profile)=>{

                        self.maintainbase.gaCompanyId=profile.buz.companyId;
                        self.$http.jsonPost('/app/api/mb',{
                            maintainbasename: self.maintainbase.maintainbasename,
                            authorityid: self.maintainbase.authorityid,
                            superviseid: self.maintainbase.superviseid,
                            airportid: self.maintainbase.airportid,
//                    issueorgid: 15789,
                            telephone: self.maintainbase.telephone,
                            province: self.maintainbase.province,
                            city: self.maintainbase.city,
                            address: self.maintainbase.address,
                            gaCompanyId:self.maintainbase.gaCompanyId,
                            maintainbaseid:self.maintainbase.maintainbaseid


                        } ).then(function (response) {
                            self.closemiantainbase();
                            self.$Message.success("提交成功");
                        }).catch(function (error) {
                            console.log(error);
                            self.$Message.error('表单提交失败!');
                        });
                },(error)=>{
                    console.log('error', error);
                });


            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },
            getProv: function (prov) {
                let tempCity = [];
                this.citys = [];
                let self = this;
                self.maintainbase.maintainbaseCity = '';
                for (var i = 0; i < this.cityList.length; i++) {
                    if (this.cityList[i].p == prov) {
                        this.citys = this.cityList[i].c;
                    }
                }
            },
            geiairport:function () {
                let self = this;

                let maintainbaseCity= self.maintainbase.maintainbaseCity;

                if(maintainbaseCity==""||maintainbaseCity==null){
                    return false;
                }else{

                    let Province= self.maintainbase.maintainbaseProvince;
                    let city=maintainbaseCity;
                    //         let path=self.$axios.defaults.baseURL;
//                    <!--self.$http.httpGet('/app/api/airport/listbyprovinceandcity/pro',{-->
//                        <!--"province":Province,-->
//                        <!--"city":city-->
                    self.$http.httpGet('/app/api/airport',{

                    }).then(function (response) {
//                        tempairport.push
                        self.airport=response.data;
                        self.$Message.success('提交成功!');
                    }).catch(function (error) {
                        console.log(error);
                        self.$Message.error('操作失败');
                    });

                }
            },closemiantainbase:function () {
                this.$emit('childshowamiantainbase');
            },maintaininfo(obj) {
                let self=this;
                self.$http.httpGet('/app/api/mb/'+obj, {
                }).then(function (response) {
                    self.maintainbase=response.data;
                    console.log("self.maintainbase=====",self.maintainbase);
//                    for(var key in response.data){
//
//                        self.$set(self.maintainbase,key,response.data[key]);
//                    }
                }) .catch(function (error) {
                    console.log(error);
                });
            },addmaintaininfo(){
                this.maintainbase={}
            }
        },
//        watch: {
//            maintainbase: {
//                deep: true,
//                handler (val) {
//                    this.getCode(val)
//                }
//            }
//        }
        watch: {
//            'maintainbase.maintainbaseCity': function (vcity, oldVal) {
//                let self = this;
//
//                let maintainbaseCity= self.maintainbase.maintainbaseCity;
//                console.log("maintainbaseCity",maintainbaseCity)
//
//                if(vcity==""||vcity==null){
//                    return false;
//                }else{
//
//                  let Province= self.maintainbase.maintainbaseProvince;
//                  let city=vcity;
//           //         let path=self.$axios.defaults.baseURL;
//                    self.$http.httpGet('/app/api/airport/listbyprovinceandcity/pro',{
//                        "province":Province,
//                        "city":city
//                    }).then(function (response) {
////                        tempairport.push
//                        self.airport=response.data;
//                        self.$Message.success('提交成功!');
//                    }).catch(function (error) {
//                        console.log(error);
//                        self.$Message.error('操作失败');
//                    });
//
//                }
//
//            },

        }
    }
</script>
<style scoped>
    .leftdiv{
        width: 390px;display: inline-block;
    }
    .rightdiv
    {
        width: 390px;display: inline-block;margin-left: 15px;
    }
</style>