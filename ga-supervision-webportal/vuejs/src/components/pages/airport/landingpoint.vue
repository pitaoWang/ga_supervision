<style>
    .landingpoint-btn {
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
    .landingpoint-div a:hover {
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
    <div style="width: 900px;background-color: rgb(220,238,255); padding-top: 10px;padding-left: 45px;">
        <Form ref="landingpointval" :rules="landingpointvalidate" :model="landingpoint" :label-width="110" styles="width:965px; margin:10px auto; text-align: -webkit-center;">
                    <FormItem label="起降点名称" style="width: 390px;display: inline-block;" prop="name">
                        <Input v-model="landingpoint.name" placeholder=""></Input>
                    </FormItem>

                    <FormItem label="电话" style="width: 390px;display: inline-block;margin-left: 15px;" prop="tel">
                        <Input v-model="landingpoint.tel" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="所属管理局" style="display: inline-block; width: 390px;" prop="manageOrgId">
                        <Select number="true" v-model.number="landingpoint.manageOrgId" placeholder="">
                            <Option v-for="site in authority" :key="site.id" :value="site.id">{{ site.name }}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="所属监管局" style="width: 390px;display: inline-block;margin-left: 15px;" prop="superviseOrgId">
                        <Select number="true" v-model.number="landingpoint.superviseOrgId" placeholder="">
                            <Option v-for="site in supervision" :key="site.id" :value="site.id">{{ site.name }}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="经度" style="width: 390px;display: inline-block; color: #fff;">
                        <InputNumber v-model="longitudedegrees" placeholder="" :min="0" :max="180"  style="display: inline-block; width: 70px; margin-right: 8px"></InputNumber>度
                        <InputNumber v-model="longitudemins" placeholder="" :min="0" :max="60" style="display: inline-block; width: 70px; margin-right: 8px"></InputNumber>分
                        <InputNumber v-model="longitudesecs" placeholder="" :min="0" :max="60" style="display: inline-block; width: 70px; margin-right: 8px"></InputNumber>秒
                    </FormItem>
                    <FormItem label="纬度" style="width: 390px;display: inline-block;margin-left: 15px;color: #fff;">
                        <InputNumber v-model="latitudedegrees" placeholder="" :min="0" :max="180" style="display: inline-block; width: 70px; margin-right: 8px"></InputNumber>度
                        <InputNumber v-model="latitudemins" placeholder="" :min="0" :max="60" style="display: inline-block; width: 70px; margin-right: 8px"></InputNumber>分
                        <InputNumber v-model="latitudesecs" placeholder="" :min="0" :max="60" style="display: inline-block; width: 70px; margin-right: 8px"></InputNumber>秒
                    </FormItem>
                    <FormItem label="起降点等级" style="width: 390px;display: inline-block;" prop="landingGrade">
                        <Select v-model="landingpoint.landingGrade" placeholder="">
                            <Option :value="item.key" :key="item.key" v-for="item in landingGrades">{{item.value}}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="是否支持夜航" style="width: 390px;display: inline-block; margin-left: 15px;" prop="isnightfight">
                        <Select v-model="landingpoint.isnightfight" placeholder="">
                            <Option value="0">不支持</Option>
                            <Option value="1">支持</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="是否支持塔台服务" style="width: 390px;display: inline-block;" prop="iscontroltower">
                        <Select v-model="landingpoint.iscontroltower" placeholder="">
                            <Option value="0">不支持</Option>
                            <Option value="1">支持</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="是否有导航设施" style="width: 390px;display: inline-block; margin-left: 15px;" prop="isnavidevice">
                        <Select v-model="landingpoint.isnavidevice" placeholder="">
                            <Option value="0">不支持</Option>
                            <Option value="1">支持</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="消防等级" style="width: 390px;display: inline-block;" prop="fireGrade">
                        <Select v-model="landingpoint.fireGrade" placeholder="">
                            <Option :value="item.key" :key="item.key" v-for="item in firegrades">{{item.value}}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="海拔高度" style="width: 390px;display: inline-block;margin-left: 15px;" prop="altitude">
                        <Input v-model="landingpoint.altitude" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="许可证起始日期" style="width: 390px;display: inline-block;" prop="licenseBegindate">
                        <Row>
                            <Col span="18">
                            <DatePicker type="date" placeholder="选择日期" v-model="landingpoint.licenseBegindate" style="width: 280px;"></DatePicker>
                            </Col>
                        </Row>
                    </FormItem>
                    <FormItem label="许可证截止日期" style="width: 390px;display: inline-block; margin-left: 15px;" prop="licenseEnddate">
                        <Row>
                            <Col span="11">
                            <DatePicker type="date" placeholder="选择日期" v-model="landingpoint.licenseEnddate" style="width: 280px;"></DatePicker>
                            </Col>
                        </Row>
                    </FormItem>
                    <FormItem label="所属省份" style="width: 390px;display: inline-block;" prop="province">
                        <Select v-model="landingpoint.province" placeholder="" @on-change="getProv(landingpoint.province)">
                            <Option v-for="item in cityList" :value="item.p" :key="item.p">{{ item.p }}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="地市" style="width: 390px;display: inline-block;margin-left: 15px;" prop="city">
                        <Select v-model="landingpoint.city"  placeholder="">
                            <Option v-for="item in citys" :key="item.n" :value="item.n">{{ item.n }} </Option>
                        </Select>
                    </FormItem>

                    <FormItem label="地址" style="width: 800px;" prop="address">
                        <Input v-model="landingpoint.address" placeholder=""></Input>
                    </FormItem>
                    <FormItem label="是否可用" style="width: 390px;display: inline-block;" prop="isavailable">
                        <Select v-model="landingpoint.isavailable" placeholder="">
                            <Option value="0">可用</Option>
                            <Option value="1">不可用</Option>
                        </Select>
                    </FormItem>
                    <FormItem style="text-align: center; padding-bottom: 50px; margin-left: -110px; height: 25px" class="landingpoint-div">
                        <a data-v-942377fa="" href="javascript:;" @click="handleSubmit()" class="landingpoint-btn">保存</a>
                        <a data-v-942377fa="" href="javascript:;" @click="onhandleclose" class="landingpoint-btn">返回</a>
                    </FormItem>

        </Form>
    </div>
</template>
<script>
    import landingpointdata from '../../../data/landingpoint/index';
    export default {

        data () {
            return {
                cityList:landingpointdata.allcitys,
                authority:[],
                supervision:[],
                citys:[],
                landingGrades:landingpointdata.grades,
                firegrades:landingpointdata.firegrades,
                longitudedegrees:0,//经度
                longitudemins:0,
                longitudesecs:0,
                latitudedegrees:0,//纬度
                latitudemins:0,
                latitudesecs:0,
                landingpoint: {
                    id:'',
                    name: '',//
                    longitude:'',
                    latitude:'',
                    landingGrade:'',
                    isnightfight:'',/*是否支持夜航*/
                    iscontroltower:'',/* 是否塔台服务*/
                    isnavidevice:'',/* 是否有导航设施*/
                    fireGrade:'',/* 消防等级*/
                    altitude:'',/*海拔高度*/
                    licenseBegindate:null,/*许可证起始日期*/
                    licenseEnddate:null,/*许可证截止日期*/
                    isavailable:'',/*是否可用*/
                    province: '',//
                    city:'',//
                    manageOrgId:'',//
                    superviseOrgId:'',//
                    address:'',
                    tel:'',
                    gaCompanyId:'',

                },
                landingpointvalidate:{
                    name:[{required:true,message:'起降点名称不能为空',trigger:'blur'}],
//                    province:[{required:true,message:'所在省不能为空',trigger:'change'}],
//                    city:[{required:true,message:'所在市不能为空',trigger:'change'}],
                    superviseOrgId:[{required:true,type:"number",message:'隶属监管局不能为空',trigger:'change'}],
                    manageOrgId:[{required:true,type:"number",message:'隶属管理局不能为空',trigger:'change'}],
//                    longitude:[{required:true,message:'经度不能为空',trigger:'blur'}],
//                    latitude:[{required:true,message:'纬度不能为空',trigger:'blur'}],
//                    landingGrade:[{required:true,message:'起降点等级不能为空',trigger:'change'}],
//                    isnightfight:[{required:true,message:'是否支持夜航不能为空',trigger:'blur'}],
//                    iscontroltower:[{required:true,message:'是否塔台服务不能为空',trigger:'blur'}],
//                    isnavidevice:[{required:true,message:'是否有导航设施不能为空',trigger:'blur'}],
//                    fireGrade:[{required:true,message:'消防等级不能为空',trigger:'blur'}],
//                    altitude:[{required:true,message:'海拔高度不能为空',trigger:'blur'}],
//                    licenseBegindate:[{required:true,type:'date',message:'许可证起始日期不能为空',trigger:'blur'}],
//                    licenseEnddate:[{required:true,type:'date',message:'许可证截止日期不能为空',trigger:'blur'}],
//                    isavailable:[{required:true,message:'是否可用不能为空',trigger:'change'}],
//                    address:[{required:true,message:'地址不能为空',trigger:'blur'}],
//                    tel:[{required:true,message:'联系电话不能为空',trigger:'blur'}],

                }
            }
        },
        created: function (newval) {
            let self = this;
         //   let path=self.$axios.defaults.baseURL;
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

//            self.$http.httpGet('/app/api/landingpoint/', {
//                //  params: {
//                //      companyId: 14
//                //  }
//            }).then(function (response) {
//                let datainfo=response.data;
//                if(typeof(datainfo) != 'undefined'&&typeof (datainfo.name)!='undefined') {
//               //     self.landingpoint = datainfo;
//
//                    for(var key in datainfo)
//                    {
//                       // self.landingpoint[key]=datainfo[key];
//                        self.$set(self.landingpoint,key,datainfo[key]);
//                    }
//
//                    self.landingpoint.isnightfight=datainfo.isnightfight+'';
//                    var longitudelist=self.landingpoint.longitude.split('-');
//                    if(longitudelist!=null&&longitudelist!=''&&longitudelist!='undefined') {
//                        self.longitudedegrees = longitudelist[0];
//                        self.longitudemins = longitudelist[1];
//                        self.longitudesecs = longitudelist[2];
//                    }
//                    var latitudelist=self.landingpoint.latitude.split('-');
//                    if(latitudelist!=null&&latitudelist!=''&&latitudelist!='undefined') {
//                        self.latitudedegrees =latitudelist[0];
//                        self.latitudemins =latitudelist[1];
//                        self.latitudesecs =latitudelist[2];
//                    }
//                }else {
//                    self.longitudedegrees='',//经度
//                            self.longitudemins='',
//                            self.longitudesecs='',
//                            self.latitudedegrees='',//纬度
//                            self.latitudemins='',
//                            self.latitudesecs='',
//                    self.landingpoint={
//                                id:'',
//                                name: '',
//                                longitude:'',
//                                latitude:'',
//                                landingGrade:'',
//                                isnightfight:'',/*是否支持夜航*/
//                                iscontroltower:'',/* 是否塔台服务*/
//                                isnavidevice:'',/* 是否有导航设施*/
//                                fireGrade:'',/* 消防等级*/
//                                altitude:'',/*海拔高度*/
//                                licenseBegindate:null,/*许可证起始日期*/
//                                licenseEnddate:null,/*许可证截止日期*/
//                                isavailable:'',/*是否可用*/
//                                province: '',
//                                city:'',
//                                manageOrgId:'',
//                                superviseOrgId:'',
//                                address:'',
//                                tel:'',
//                                gaCompanyId:'',
//                    }
//                }
//            })
//                    .catch(function (error) {
//                        console.log(error);
//                    });
        },
        methods: {
            handleSubmit: function () {
                this.$refs.landingpointval.validate((valid) => {
                    if (valid) {
                let self = this;
                self.$profile.getProfile().then((profile)=>{

                self.landingpoint.gaCompanyId=profile.buz.companyId;
                    self.landingpoint.longitude=self.longitudedegrees+'-'+self.longitudemins+'-'+self.longitudesecs;
                    self.landingpoint.latitude=self.latitudedegrees+'-'+self.latitudemins+'-'+self.latitudesecs;
//                if (self.landingpoint.licenseBegindate != null) {
//                    var d = new Date(self.landingpoint.licenseBegindate);
//                    self.landingpoint.licenseBegindate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
//                }
//                if (self.landingpoint.licenseEnddate != null) {
//
//                   var d = new Date(self.landingpoint.licenseEnddate);
//                    self.landingpoint.licenseEnddate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
//                    ;
//                }
                self.$http.jsonPost('/app/api/landingpoint',self.landingpoint).then(function (response) {
                    self.$Message.success('提交成功!');
                    self.onhandleclose();
                }).catch(function (error) {
                            console.log(error);
                    self.$Message.error('操作失败');
                        });
            },(error)=>{
                    console.log('error', error);
                });
                    }
                });
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },onhandleclose:function(){
                 this.$emit('childshowlandingpoint');
            },
            getProv:function (prov) {
                let tempCity=[];
                this.citys=[];
                let self = this;
                self.landingpoint.city=self.landingpoint.city;
                for(var i=0;i<this.cityList.length;i++){
                    if(this.cityList[i].p==prov){
                        this.citys = this.cityList[i].c;
                    }
                }
            },
            landinginfost:function (obj) {
                let self=this;
                self.$http.httpGet('/app/api/landingpoint/'+obj, {
                }).then(function (response) {
                    self.landingpoint=response.data;
                    if(self.landingpoint!='undefined') {
                        self.landingpoint.isnightfight = self.landingpoint.isnightfight + '';
                        self.landingpoint.iscontroltower = self.landingpoint.iscontroltower + '';
                        /* 是否塔台服务*/
                        self.landingpoint.isnavidevice = self.landingpoint.isnavidevice + '';
                        /* 是否有导航设施*/
                        self.landingpoint.isavailable = self.landingpoint.isavailable + '';
                        var longitudelist = self.landingpoint.longitude.split('-');
                        if (longitudelist != null && longitudelist != '' && longitudelist != 'undefined') {
                            self.longitudedegrees = longitudelist[0];
                            self.longitudemins = longitudelist[1];
                            self.longitudesecs = longitudelist[2];
                        }
                        var latitudelist = self.landingpoint.latitude.split('-');
                        if (latitudelist != null && latitudelist != '' && latitudelist != 'undefined') {
                            self.latitudedegrees = latitudelist[0];
                            self.latitudemins = latitudelist[1];
                            self.latitudesecs = latitudelist[2];
                        }
                    }
                }) .catch(function (error) {
                    console.log(error);
                });
            },addlandinginfo:function(){
              let self=this;
              self.landingpoint={};
            }
        }
    }
        </script>