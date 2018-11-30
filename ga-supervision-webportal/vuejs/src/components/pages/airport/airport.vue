<style>
    .airport-btn {
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

    .airport-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }

    .airport-div-height {
        height: 32px;
    }
</style>
<template>
    <div>
    <div style="width: 609px; margin: 10px auto;">
        <Form ref="airportval" :rules="airportvalidate" :model="airport" :label-width="160"
              styles="width:710px; margin:10px auto; background-color:rgb(220,238,255); text-align: -webkit-center;">
            <FormItem label="机场名称" class="airport-div-height" prop="airportName">
                <Input v-model="airport.airportName" placeholder=""></Input>
            </FormItem>

            <FormItem label="机场等级" prop="grade" class="airport-div-height">
                <Select v-model="airport.grade" placeholder="">
                    <Option :value="item.key" :key="item.key" v-for="item in airportgrades">{{item.value}}</Option>
                </Select>
            </FormItem>
            <FormItem label="机场类型" prop="airporttype" class="airport-div-height">
                <Select v-model="airport.airporttype" placeholder="">
                    <Option :value="item.key" :key="item.key" v-for="item in types">{{item.value}}</Option>
                </Select>
            </FormItem>
            <FormItem label="隶属管理局" class="airport-div-height" prop="authorityId">
                <Select v-model.Number="airport.authorityId" placeholder="">
                    <Option v-for="item in authoritys" :key="item.id" :value="item.id">{{item.name}}</Option>
                </Select>

            </FormItem>
            <FormItem label="隶属监管局" class="airport-div-height" prop="superviseOrgid">
                <Select v-model.Number="airport.superviseOrgid" placeholder="">
                    <Option :value="item.id" :key="item.id" v-for="item in supervisions">{{item.name}}</Option>
                </Select>
            </FormItem>
            <FormItem label="消防等级" class="airport-div-height">
                <Select v-model="airport.fireGrade" placeholder="">
                    <Option :value="item.key" :key="item.key" v-for="item in fireGrades">{{item.value}}</Option>
                </Select>
            </FormItem>
            <FormItem label="许可证起始日期" style="width: 390px;display: inline-block;"
                      class="airport-div-height" prop="licenseBegindate">
                <Row>
                    <Col span="18">
                    <DatePicker type="date" placeholder="选择日期" v-model="airport.licenseBegindate"
                                style="width: 280px;"></DatePicker>
                    </Col>
                </Row>
            </FormItem>
            <FormItem label="许可证截止日期" style="width: 390px;display: inline-block;"
                      class="airport-div-height" prop="licenseEnddate">
                <Row>
                    <Col span="11">
                    <DatePicker placeholder="选择日期" type="date" v-model="airport.licenseEnddate"
                                style="width: 280px;"></DatePicker>
                    </Col>
                </Row>
            </FormItem>
            <FormItem label="是否可用" class="airport-div-height">
                <RadioGroup v-model.number="airport.isavailable" style="color: #016bd9">
                    <Radio label="0">可用</Radio>
                    <Radio label="1">不可用</Radio>
                </RadioGroup>
            </FormItem>
            <FormItem label="是否是水上机场" class="airport-div-height">
                <RadioGroup v-model.number="airport.iswater" style="color: #016bd9">
                    <Radio label="0">否</Radio>
                    <Radio label="1">是</Radio>
                </RadioGroup>
            </FormItem>
            <FormItem label="所在省" class="airport-div-height">
                <Select v-model="airport.province" placeholder="" @on-change="getProv(airport.province)">
                    <Option v-for="item in cityList" :value="item.p" :key="item.p">{{ item.p }}</Option>
                </Select>
            </FormItem>
            <FormItem label="所在市" class="airport-div-height" prop="city">
                <Select v-model="airport.city" placeholder="">
                    <Option v-for="item in citys" :key="item.n" :value="item.n">{{ item.n }}</Option>
                </Select>
            </FormItem>
            <FormItem label="机场基准点经度" class="airport-div-height" style="color:  #016bd9">
                <InputNumber v-model="longitudedegrees" :min="0" :max="180" placeholder=""
                       style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>度
                <InputNumber v-model="longitudemins" :min="0" :max="60" placeholder=""
                       style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>分
                <InputNumber v-model="longitudesecs" :min="0" :max="60" placeholder=""
                       style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>秒
            </FormItem>
            <FormItem label="机场基准点纬度" class="airport-div-height" style="color: #016bd9">
                <InputNumber v-model="latitudedegrees" placeholder=""  :min="0" :max="180"
                       style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>度
                <InputNumber v-model="latitudemins" placeholder=""  :min="0" :max="60"
                       style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>分
                <InputNumber v-model="latitudesecs" placeholder=""  :min="0" :max="60"
                       style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>秒
            </FormItem>
            <FormItem label="机场高标" class="airport-div-height">
                <Input v-model.number="airport.height" placeholder=""></Input>
            </FormItem>
            <FormItem label="机场使用性质" class="airport-div-height">
                <Select v-model="airport.property" placeholder="">
                    <Option value="1">军用</Option>
                    <Option value="2">商用</Option>
                    <Option value="3">公用</Option>
                </Select>
            </FormItem>
            <FormItem label="机场运营公司" class="airport-div-height">
                <Select v-model.Number="airport.entNumber" placeholder="">
                    <Option v-for="item in airpcompanys" :key="item.companyId" :value="item.companyId+''">{{item.name}}
                    </Option>
                </Select>
            </FormItem>
            <FormItem label="机场管理机构名称" class="airport-div-height">
                <Input v-model="airport.orgName" placeholder=""></Input>
            </FormItem>
            <FormItem label="ICAO编码" class="airport-div-height">
                <Input v-model="airport.icaoNumber" placeholder=""></Input>
            </FormItem>
            <FormItem label="IATA编码" class="airport-div-height">
                <Input v-model="airport.iatanumber" placeholder=""></Input>
            </FormItem>
            <FormItem label="机场地址" class="airport-div-height">
                <Input v-model="airport.address" placeholder=""></Input>
            </FormItem>
            <FormItem label="机场服务电话" class="airport-div-height">
                <Input v-model="airport.serviceTel" placeholder=""></Input>
            </FormItem>
            <FormItem label="站调联系电话" class="airport-div-height">
                <Input v-model="airport.tel" placeholder=""></Input>
            </FormItem>
            <FormItem label="隶属于战区" class="airport-div-height">
                <Select v-model="airport.warZone" placeholder="">
                    <Option value="东部战区">东部战区</Option>
                    <Option value="南部战区">南部战区</Option>
                    <Option value="西部战区">西部战区</Option>
                    <Option value="北部战区">北部战区</Option>
                    <Option value="中部战区">中部战区</Option>
                </Select>
            </FormItem>
            <FormItem label="塔台主频率" class="airport-div-height">
                <Input v-model="airport.towerMainFrequency" placeholder=""></Input>
            </FormItem>
            <FormItem label="塔台备频率" class="airport-div-height">
                <Input v-model="airport.towerReserveFrequency" placeholder=""></Input>
            </FormItem>
            <FormItem label="进近主频率" class="airport-div-height">
                <Input v-model="airport.mainFrequency" placeholder=""></Input>
            </FormItem>
            <FormItem label="进近备频率" class="airport-div-height">
                <Input v-model="airport.reserveFrequency" placeholder=""></Input>
            </FormItem>

            <FormItem label="导航台NDB" class="airport-div-height">
                <Input v-model="airport.ndbFrequency" placeholder=""></Input>
            </FormItem>

            <FormItem label="导航台VOR/DME频率" class="airport-div-height">
                <Input v-model="airport.vorDemFrequency" placeholder=""></Input>
            </FormItem>
            <FormItem label="仪表着陆设备是否具备" class="airport-div-height">
                <RadioGroup v-model.number="airport.islandingDevice" style="color: #016bd9">
                    <Radio label="0">否</Radio>
                    <Radio label="1">是</Radio>
                </RadioGroup>
            </FormItem>

            <FormItem style="text-align: center; margin-left: -110px; height: 25px;    padding-bottom: 50px;"
                      class="airport-div">
                <a data-v-942377fa="" href="javascript:;" @click="handleSubmit()" class="airport-btn">保存</a>
             <!--   <a data-v-942377fa="" href="javascript:;" @click="closemodelairport" class="airport-btn">返回</a> -->
            </FormItem>
            <Modal v-model="airportmodal"
                   :mask-closable="false"
                   width="300px" hight="200px" :styles="{top: '40%'}">
                <p slot="header" style="color:#e9e9e9;text-align:left;font-size: 15px;">
                    <span>系统提示</span>
                </p>
                <p style="height: 100px; height: 80px;padding-top: 24px;text-align: center;margin-bottom: 2px; text-align: center;">
                    {{airportmassage}}</p>
            </Modal>
        </Form>
    </div>
    </div>
</template>
<script>
    import airportdata from '../../../data/airport/index';
    export default {
        props: ['getairportid'],
        data () {
            return {
                airportmodal: false,
                airportmassage: '',
                supervisions: [],
                authoritys: [],
                cityList: airportdata.allcitys,
                citys: [],
                types: airportdata.types,
                fireGrades: airportdata.firegrades,
                airportgrades: airportdata.grades,
                airpcompanys: [],//所有公司
                longitudedegrees: '',//经度
                longitudemins: '',
                longitudesecs: '',
                latitudedegrees: '',//纬度
                latitudemins: '',
                latitudesecs: '',
                airport: {
                    airportId: '',
                    airportName: '',
                    grade: '',
                    iswater: '',
                    province: '',
                    city: '',
                    longitude: '',
                    latitude: '',
                    property: '',
                    height: '',
                    entNumber:'',
                    orgName: '',
                    icaoNumber: '',
                    iatanumber: '',
                    address: '',
                    serviceTel: '',
                    tel: '',
                    superviseOrgid: '',
                    authorityId: '',
                    warZone: '',
                    towerMainFrequency: '',
                    towerReserveFrequency: '',
                    mainFrequency: '',
                    reserveFrequency: '',
                    islandingDevice: '',
                    ndbFrequency: '',
                    vorDemFrequency: '',
                    companyId: '', /*用来传参术到后台*/
                    airporttype: '',
                    isavailable: '', /* 是否可用0可用,1不可用*/
                    fireGrade: '', /* 消防等级*/
                    licenseBegindate: null, /*许可证起始日期*/
                    licenseEnddate: null, /*许可证截止日期*/
                },
                airportvalidate: {
                    airportName: [{required: true, message: '机场名称不能为空', trigger: 'blur'}],
                    grade: [{required: true, message: '机场等级不能为空', trigger: 'change'}],
//                    iswater: [{required: true, type:"number", message: '该属性不能为空', trigger: 'change'}],
//                    longitude: [{required: true, message: '经度不能为空', trigger: 'blur'}],
//                    latitude: [{required: true, message: '纬度不能为空', trigger: 'blur'}],
//                    property: [{required: true, message: '机场使用性质不能为空', trigger: 'blur'}],
//                    height: [{required: true,type:'number', message: '机场高标不能为空', trigger: 'blur'}],
//                    entNumber: [{required: true, message: '机场运营公司编号不能为空', trigger: 'change'}],
//                    orgName: [{required: true, message: '机场管理机构名称不能为空', trigger: 'blur'}],
//                    icaoNumber: [{required: true, message: 'ICAO编码不能为空', trigger: 'blur'}],
//                    iatanumber: [{required: true, message: 'IATA编码不能为空', trigger: 'blur'}],
//                    address: [{required: true, message: '机场地址不能为空', trigger: 'blur'}],
//                    serviceTel: [{required: true, message: '机场服务电话不能为空', trigger: 'blur'}],
//                    tel: [{required: true, message: '站调联系电话不能为空', trigger: 'blur'}],
//                    warZone: [{required: true, message: '隶属于战区不能为空', trigger: 'change'}],
//
//                    towerMainFrequency: [{required: true, message: '塔台主频率不能为空', trigger: 'blur'}],
//                    towerReserveFrequency: [{required: true, message: '塔台备频率不能为空', trigger: 'blur'}],
//
//                    mainFrequency: [{required: true, message: '进近主频率不能为空', trigger: 'blur'}],
//                    reserveFrequency: [{required: true, message: '进近备频率不能为空', trigger: 'blur'}],
//                    islandingDevice: [{required: true,type:"number", message: '仪表着陆设备是否具备不能为空', trigger: 'change'}],
//                    ndbFrequency: [{required: true, message: '导航台NDB频率不能为空', trigger: 'blur'}],
//                    vorDemFrequency: [{required: true, message: '导航台VOR/DME频率不能为空', trigger: 'blur'}],
                    airporttype: [{required: true, message: '机场类型不能为空', trigger: 'blur'}],
//
//                    isavailable: [{required: true,type:'number', message: '是否是水上机场不能为空', trigger: 'change'}],
//                    fireGrade: [{required: true, message: '消防等级不能为空', trigger: 'change'}],
//                    licenseBegindate: [{required: true,type:'date', message: '许可证起始日期不能为空', trigger: 'change'}],
//                    licenseEnddate: [{required: true,type:'date', message: '许可证截止日期不能为空', trigger: 'change'}],
//                    province: [{required: true, message: '所在省不能为空', trigger: 'change'}],
//                    city: [{required: true, message: '所在市不能为空', trigger: 'change'}],
                    superviseOrgid: [{required: true, type: "number", message: '隶属监管局不能为空', trigger: 'change'}],
                    authorityId: [{required: true, type: "number", message: '隶属管理局不能为空', trigger: 'change'}],

                }
            }
        },
        beforeMount: function (newval) {
            let self = this;

            self.$http.httpGet('/app/api/company/search/orgname', {
                //查找全部管理局信息
                orgtype: 2
            }).then(function (response) {
                let datainfo = response.data;
                self.authoritys = datainfo;
            }).catch(function (error) {
                console.log(error);
            });

            self.$http.httpGet('/app/api/company/search/orgname', {
                //查找全部监管局信息
                orgtype: 1
            }).then(function (response) {
                let datainfo = response.data;
                self.supervisions = datainfo;
            }).catch(function (error) {
                console.log(error);
            });
            self.$http.httpGet('/app/api/company', {
                //查询所有公司

            }).then(function (response) {
                let datainfo = response.data;
                self.airpcompanys = datainfo;
            }).catch(function (error) {
                console.log(error);
            });
        },
        watch: {
            getairportid: function (newval) {
                this.initairport(newval);
            }
        },
        methods: {

            initairport: function (newval) {
                let self = this;
                self.airport.airportId = newval;
                self.$http.httpGet('/app/api/airport/' + self.airport.airportId, {
                    //  params: {
                    //      companyId: 14
                    //  }
                }).then(function (response) {
                    let datainfo = response.data;
                    if (typeof(datainfo) != "undefined" && typeof(datainfo.airportName) != "undefined") {
                   //     self.airport = datainfo;
                        for(var key in datainfo)
                        {
                            self.$set(self.airport,key,datainfo[key]);
                        }
                        self.$emit("listenairprot", self.airport.airportId);
                        self.airport.superviseOrgid = datainfo.superviseOrgid;
//                        if(datainfo.licenseBegindate!=''&&datainfo.licenseBegindate!=undefined)
//                        {
//                            datainfo.licenseBegindate=new Date(datainfo.licenseBegindate);
//                        }
//                        if(datainfo.licenseEnddate!=''&&datainfo.licenseEnddate!=undefined)
//                        {
//                            datainfo.licenseEnddate=new Date(datainfo.licenseEnddate);
//                        }

                        var longitudelist = self.airport.longitude.split('-');
                        if (longitudelist != null && longitudelist != '' && longitudelist != 'undefined') {
                            self.longitudedegrees = longitudelist[0];
                            self.longitudemins = longitudelist[1];
                            self.longitudesecs = longitudelist[2];
                        }
                        var latitudelist = self.airport.latitude.split('-');
                        if (latitudelist != null && latitudelist != '' && latitudelist != 'undefined') {
                            self.latitudedegrees = latitudelist[0];
                            self.latitudemins = latitudelist[1];
                            self.latitudesecs = latitudelist[2];
                        }
                        //     self.airport.warZone=datainfo.warZone+'';
                    } else {//当查询不到数据时,赋值为空
                        self.longitudedegrees = '',//经度
                                self.longitudemins = '',
                                self.longitudesecs = '',
                                self.latitudedegrees = '',//纬度
                                self.latitudemins = '',
                                self.latitudesecs = '',
                                self.airport = {
                                    airportId: '',
                                    airportName: '',
                                    grade: '',
                                    iswater: '',
                                    province: '',
                                    city: '',
                                    longitude: '',
                                    latitude: '',
                                    property: '',
                                    height: '',
                                    entNumber: '',
                                    orgName: '',
                                    icaoNumber: '',
                                    iatanumber: '',
                                    address: '',
                                    serviceTel: '',
                                    tel: '',
                                    superviseOrgid: '',
                                    authorityId: '',
                                    warZone: '',
                                    towerMainFrequency: '',
                                    towerReserveFrequency: '',
                                    mainFrequency: '',
                                    reserveFrequency: '',
                                    islandingDevice: '',
                                    ndbFrequency: '',
                                    vorDemFrequency: '',
                                    companyId: '', /*用来传参术到后台*/
                                    type: '',
                                    isavailable: '', /* 是否可用0可用,1不可用*/
                                    fireGrade: '', /* 消防等级*/
                                    licenseBegindate: null, /*许可证起始日期*/
                                    licenseEnddate: null, /*许可证截止日期*/
                                }
                    }
                })
                        .catch(function (error) {
                            console.log(error);
                        });
            },
            getProv: function (prov) {
                let tempCity = [];
                this.citys = [];
                let self = this;
                self.airport.city = self.airport.city;
                for (var i = 0; i < this.cityList.length; i++) {
                    if (this.cityList[i].p == prov) {
                        this.citys = this.cityList[i].c;
                    }
                }
            },
            handleReset(name) {
                this.$refs[name].resetFields();
            }, closemodelairport: function () {
                this.$emit('childshowairport');
            },
            handleSubmit: function () {
                this.$refs.airportval.validate((valid) => {
                    if (valid) {
                        let self = this;
                        self.$profile.getProfile().then((profile) => {

                            self.airport.companyId = profile.buz.companyId;
//                        if (self.airport.licenseBegindate != null&&self.airport.licenseBegindate!='') {
//                            var d = new Date(self.airport.licenseBegindate);
//                            self.airport.licenseBegindate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
//                        }
//                        if (self.airport.licenseEnddate != null &&self.airport.licenseEnddate != '') {
//
//                            var d = new Date(self.airport.licenseEnddate);
//                            self.airport.licenseEnddate = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
//                            ;
//                        }
                        self.airport.longitude = self.longitudedegrees + '-' + self.longitudemins + '-' + self.longitudesecs;
                        self.airport.latitude = self.latitudedegrees + '-' + self.latitudemins + '-' + self.latitudesecs;
                        self.$http.jsonPost('/app/api/airport', self.airport).then(function (response) {
                            let datainfo = response.data;
                            self.airport.airportId = datainfo.id;

                            self.$emit('listenairprot', datainfo.id);

                            self.$Message.success('操作成功');
                        }).catch(function (error) {
                            console.log(error);
                            self.$Message.error('操作失败');
                        });
                    },(error) =>
                        {
                            console.log('error', error);
                        }
                    );
                    } else {
                        this.$Message.error('表单验证失败');
            }
            });
            },
        },

    }
</script>