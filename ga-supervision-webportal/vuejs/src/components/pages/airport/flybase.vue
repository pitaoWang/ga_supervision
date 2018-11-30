<style>
    .flybase-btn {
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
    .flybase-div a:hover {
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
    <Form ref="flyBase" :model="flyBase" :rules="ruleValidate" :label-width="120" style="background-color:rgb(220,238,255);width: 800px;height: 450px; padding-top: 10px;    margin-left: 23px;">
        <FormItem label="基地名称" prop="flybasename">
            <Input v-model="flyBase.flybasename" placeholder=""></Input>
        </FormItem>
        <FormItem label="机场" prop="airportId" style="width: 392px;display: inline-block;">
            <Select v-model="flyBase.airportId" placeholder="">
                <Option v-for="site in airport" :key="site.airportId" :value="site.airportId+''">{{ site.airportName }}</Option>
            </Select>
        </FormItem>
        <FormItem label="联系电话" prop="flybaseTel" style="width: 382px;display: inline-block;margin-left: 20px;">
            <Input v-model="flyBase.flybaseTel" placeholder=""></Input>
        </FormItem>
        <FormItem label="所属管理局" prop="authorityId" style="width: 392px;display: inline-block;">
            <Select v-model="flyBase.authorityId" placeholder="">
                <Option v-for="site in authority" :key="site.id" :value="site.id+''">{{ site.name }}</Option>
            </Select>
        </FormItem>
        <FormItem label="所属监管局" prop="supervisionId" style="width: 382px;display: inline-block;margin-left: 20px;">
            <Select v-model="flyBase.supervisionId" placeholder="">
                <Option v-for="site in supervision" :key="site.id" :value="site.id+''">{{ site.name }}</Option>
            </Select>
        </FormItem>
        <FormItem label="基地类型" prop="baseType">
            <Select v-model="flyBase.baseType" placeholder="">
                <Option :value="item.key" :key="item.key" v-for="item in baseTypes">{{item.value}}</Option>
            </Select>
        </FormItem>

        <FormItem label="所在省" prop="flybaseProvince" style="width: 390px;display: inline-block;">
            <Select v-model="flyBase.flybaseProvince" placeholder="" @on-change="getProv(flyBase.flybaseProvince)">
                <Option v-for="item in cityList" :value="item.p" :key="item.p">{{ item.p }}</Option>
            </Select>
        </FormItem>
        <FormItem label="所在市" prop="flybaseCity" style="width: 390px;display: inline-block;margin-left: 15px;">
            <Select v-model="flyBase.flybaseCity"  placeholder=""  >
                <Option v-for="item in citys" :key="item.n" :value="item.n">{{ item.n }} </Option>
            </Select>
        </FormItem>

        <FormItem label="详细地址" prop="flybaseDetailedAdress">
            <Input v-model="flyBase.flybaseDetailedAdress" placeholder=""></Input>
        </FormItem>
        <FormItem style="text-align: center; height: 25px; padding-bottom: 30px;" class="flybase-div">
            <a data-v-942377fa="" href="javascript:;" @click="handleSubmit('flyBase')" class="flybase-btn">保存</a>
            <a data-v-942377fa="" href="javascript:;" @click="closeflybase" class="flybase-btn">返回</a>
        </FormItem>
    </Form>
    </div>
</template>
<script>
    import flybasedata from '../../../data/flybase/index';
    export default {

        data (){
            return {
                cityList:flybasedata.citys,
                citys:[],
                authority:[],
                supervision:[],
                baseTypes:flybasedata.basetypes,
                flyBase: {
                    flybasename:'',
                    authorityId:'',
                    supervisionId:'',
                    airportId:'',
                    flybaseTel:'',
                    flybaseProvince:'',
                    flybaseCity:'',
                    flybaseDetailedAdress:'',
                    gaCompanyId:'',/*用于向后台传输id,保存到关联表*/
                    baseType:''
                },
                ruleValidate: {
                    flybasename: [
                        { required: true, message: '基地名称不能为空', trigger: 'blur' }
                    ],
                    authorityId: [
                        { required: true, message: '所属管理局不能为空', trigger: 'change' }
                    ],
                    supervisionId:[
                        { required: true, message: '所属监管局不能为空', trigger: 'change' }
                    ],
//                    airportId: [
//                        { required: true, message: '机场不能为空', trigger: 'change' }
//                    ],
//                    flybaseProvince:[
//                        { required: true, message: '所在省不能为空', trigger: 'blur' }
//                    ],
//                    flybaseCity:[
//                        { required: true, message: '所在市不能为空', trigger: 'change' }
//                    ],
//                    flybaseTel: [
//                        { required: true, message: '电话不能为空', trigger: 'blur' }
//                    ],
//                    flybaseDetailedAdress: [
//                        { required: true, message: '详细地址不能为空', trigger: 'blur' }
//                    ],
//                    baseType: [
//                        { required: true, message: '基地类型不能为空', trigger: 'change' }
//                    ],
                },
                airport:[]
            }
        },
        created: function (newval) {
            let self = this;
            self.$http.httpGet('/app/api/airport', {//获取机场列表,保存到select中

            }).then(function (response) {
                let datainfo=response.data;
                self.airport=datainfo;
            }) .catch(function (error) {
                console.log(error);
            });

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
        },
        methods: {
            handleSubmit (name) {
                this.$refs[name].validate((valid) => {

                        let self = this;
                   //     let path=self.$axios.defaults.baseURL;
                        self.$profile.getProfile().then((profile)=>{

                        self.flyBase.gaCompanyId=profile.buz.companyId;
                        self.$http.jsonPost('/app/api/flybase', self.flyBase).then(function (response) {
                            self.$Message.success('提交成功!');
                            self.closeflybase();
                        }).catch(function (error) {
                            console.log(error);
                            self.$Message.error('提交失败!');
                        });
                        },(error)=>{
                            console.log('error', error);
                        });


                });
            },

            getProv:function (prov) {
                let tempCity=[];
                this.citys=[];
                let self = this;
                self.flyBase.flybaseCity=self.flyBase.flybaseCity;
                for(var i=0;i<this.cityList.length;i++){
                    if(this.cityList[i].p==prov){
                        this.citys = this.cityList[i].c;
                    }
                }
            },

            handleReset (name) {
                this.$refs[name].resetFields();
            },closeflybase:function () {
                this.$emit('childshowaddflybase');
            },flybaseinfo(obj){
                let self=this;
                self.$http.httpGet('/app/api/flybase/'+obj, {
                }).then(function (response) {
                    self.flyBase=response.data;
                    self.flyBase.supervisionId = self.flyBase.supervisionId + '';
                    self.flyBase.authorityId = self.flyBase.authorityId + '';
                    self.flyBase.airportId=self.flyBase.airportId+'';
                }) .catch(function (error) {
                    console.log(error);
                });
            },addflybaseinfo(){
                this.flyBase={};
            }
        }
        }
</script>