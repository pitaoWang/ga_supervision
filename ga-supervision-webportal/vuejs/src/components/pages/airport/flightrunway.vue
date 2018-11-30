<style>
    .flightrunway-btn {
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
    .flightrunway-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
    .airport-div-height
    {
        height: 32px;
    }
</style>
<template>
    <Form ref="flightrunway" :model="flightrunway" :rules="ruleValidate" :label-width="190" style="width: 640px; margin: 10px auto; text-align: left;">
        <FormItem label="跑道编号" prop="runwayNo" style="height: 30px;">
            <Input v-model="flightrunway.runwayNo" placeholder=""></Input>
        </FormItem>
            <FormItem label="跑道运行类别" prop="runwayType" style="height: 30px;">
                <Select v-model="flightrunway.runwayType" placeholder="">
                    <Option value="4D">4D</Option>
                    <Option value="4E">4E</Option>
                    <Option value="4F">4F</Option>
                </Select>
            </FormItem>
            <FormItem label="跑道长度" prop="runwayLength" style="height: 30px;">
                <Input v-model="flightrunway.runwayLength" placeholder=""></Input>
            </FormItem>
        <FormItem label="中心点经度" style="color: #fff;height: 30px;">
            <InputNumber v-model="longitudedegrees" :min="0" :max="180" placeholder=""  style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>度
            <InputNumber v-model="longitudemins" :min="0" :max="60" placeholder=""  style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>分
            <InputNumber v-model="longitudesecs" :min="0" :max="60" placeholder=""  style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>秒
        </FormItem>
        <FormItem label="中心点纬度" style="color: #fff;height: 30px;">
            <InputNumber v-model="latitudedegrees" :min="0" :max="180" placeholder=""  style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>度
            <InputNumber v-model="latitudemins" :min="0" :max="60" type="number" placeholder=""  style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>分
            <InputNumber v-model="latitudesecs" :min="0" :max="60" placeholder=""  style="display: inline-block; width: 90px; margin-right: 10px"></InputNumber>秒
        </FormItem>

        <FormItem label="跑道仪表着陆设备是否具备" prop="islandingDevice" style="height: 30px;">
            <Select v-model.number="flightrunway.islandingDevice" placeholder="">
                <Option value="0">是</Option>
                <Option value="1">否</Option>
            </Select>
        </FormItem>
        <FormItem label="是否可用" prop="isavailable" class="airport-div-height">
            <RadioGroup v-model.number="flightrunway.isavailable" style="color: #016bd9">
                <Radio label="0">
                    <span>不可用</span>
                </Radio>
                <Radio label="1">
                    <span>可用</span>
                </Radio>
            </RadioGroup>
        </FormItem>
        <FormItem label="跑道朝向" prop="direction" style="height: 30px;">
            <Input v-model="flightrunway.direction" placeholder=""></Input>
        </FormItem>
        <FormItem label="中心点高度" prop="height" style="height: 30px;">
            <Input v-model="flightrunway.height" placeholder=""></Input>
        </FormItem>
            <FormItem label="ILS频率" prop="ils" style="height: 30px;">
                <Input v-model="flightrunway.ils" placeholder=""></Input>
            </FormItem>
            <FormItem label="助航灯光是否具备" prop="islamplight" style="height: 30px;">
                <Select v-model.number="flightrunway.islamplight" placeholder="">
                    <Option value="0">是</Option>
                    <Option value="1">否</Option>
                </Select>
            </FormItem>
            <FormItem label="是否可供固定翼飞机起降" prop="isfixedAerofoil" style="height: 30px;">
                <Select v-model.number="flightrunway.isfixedAerofoil" placeholder="">
                    <Option value="0">是</Option>
                    <Option value="1">否</Option>
                </Select>
            </FormItem>
            <FormItem label="是否可供固定机起降" prop="ishelicopter" style="height: 30px;">
                <Select v-model.number="flightrunway.ishelicopter" placeholder="">
                    <Option value="0">是</Option>
                    <Option value="1">否</Option>
                </Select>
            </FormItem>
            <FormItem style="text-align: center; height: 25px; padding-bottom: 30px; margin-left: -190px" class="flightrunway-div">
                <a data-v-942377fa="" href="javascript:;" @click="handleSubmit('flightrunway')" class="flightrunway-btn">保存</a>
                <a data-v-942377fa="" href="javascript:;" @click="reback()" class="flightrunway-btn">返回</a>
            </FormItem>
    </Form>
</template>
<script>
    export default {
        props:['runwayentitys'],
        data () {
            return {
                longitudedegrees:'',//经度
                longitudemins:'',
                longitudesecs:'',
                latitudedegrees:'',//纬度
                latitudemins:'',
                latitudesecs:'',
                flightrunway: {
                    id:'',
                    runwayNo: '',
                    runwayType: '',
                    runwayLength: '',//
                    longitude: '',
                    latitude: '',
                    height: '',
                    islandingDevice: '',
                    ils:'',
                    islamplight:'',
                    isfixedAerofoil:'',
                    ishelicopter:'',
                    airdromeId:{},
                    isavailable:'',
                    direction:''
                },
                ruleValidate: {
                    runwayNo: [
                        { required: true, message: '跑道编号不能为空', trigger: 'blur' }
                    ],
//                    runwayType: [
//                        { required: true, message: '请选择跑道运行类别', trigger: 'change' }
//                    ],
//                    longitude: [
//                        { required: true, message: '中心点经度不能为空', trigger: 'blur' }
//                    ],
//                    latitude: [
//                        { required: true, message: '中心点纬度不能为空', trigger: 'blur' }
//                    ],
//                    height: [
//                        { required: true,  message: '中心点高度不能为空', trigger: 'blur' }
//                    ],
//                    islandingDevice: [
//                        { required: true,type:'number',  message: '跑道仪表着陆设备是否具备不能为空', trigger: 'change' }
//                    ],
//                    ils: [
//                        { required: true, message: '请输入请输入ILS频率不能为空', trigger: 'blur' }
//                    ],
//                    islamplight: [
//                        { required: true,type:'number', message: '助航灯光是否具备不能为空', trigger: 'change' }
//                    ],
//
//                    isfixedAerofoil: [
//                        { required: true,type:'number', message: '是否可供固定翼飞机起降不能为空', trigger: 'change' }
//                    ],
//                    ishelicopter:[
//                        { required: true,type:'number', message: '是否可供固定机起降不能为空', trigger: 'change' }
//                        ],
//                    isavailable :[
//                    { required: true,type:'number', message: '是否可用不能为空', trigger: 'change' }
//                    ],
//                    runwayLength :[
//                        { required: true, message: '跑道长度不能为空', trigger: 'blur' }
//                    ],
//                    direction:[
//                        { required: true, message: '跑道朝向不能为空', trigger: 'blur' }
//                    ],
                }
            }
        },
        mounted:function() {

                let self = this;
                self.flightrunway.id=this.runwayentitys.flyrunwayid;
                if(self.flightrunway.id=='')
                {
                    self.flightrunway.id=0;
                }
                self.$http.httpGet('/app/api/flightrunway/'+self.flightrunway.id, {
                }).then(function (response) {
                    let datainfo = response.data;
                    datainfo.islamplight=datainfo.islamplight+'';
                    datainfo.islandingDevice=datainfo.islandingDevice+'';
                    datainfo.isfixedAerofoil=datainfo.isfixedAerofoil+'';
                    datainfo.ishelicopter=datainfo.ishelicopter+'';
                    if (datainfo != null && datainfo != '' && typeof(datainfo) != 'undefined') {
                        self.flightrunway = datainfo;
                        var longitudelist=self.flightrunway.longitude.split('-');
                        if(longitudelist!=null&&longitudelist!=''&&longitudelist!='undefined') {
                            self.longitudedegrees = longitudelist[0];
                            self.longitudemins = longitudelist[1];
                            self.longitudesecs = longitudelist[2];
                        }
                        var latitudelist=self.flightrunway.latitude.split('-');
                        if(latitudelist!=null&&latitudelist!=''&&latitudelist!='undefined') {
                            self.latitudedegrees =latitudelist[0];
                            self.latitudemins =latitudelist[1];
                            self.latitudesecs =latitudelist[2];
                        }
                    }else{
                        self.longitudedegrees='',//经度
                                self.longitudemins='',
                                self.longitudesecs='',
                                self.latitudedegrees='',//纬度
                                self.latitudemins='',
                                self.latitudesecs='',
                                self.flightrunway={       id:'',
                                    runwayNo: '',
                                    runwayType: '',
                                    runwayLength: '',
                                    longitude: '',
                                    latitude: '',
                                    height: '',
                                    islandingDevice: '',
                                    ils:'',
                                    islamplight:'',
                                    isfixedAerofoil:'',
                                    ishelicopter:'',
                                    airdromeId:{},
                                    isavailable:'',
                                    direction:''};
                    }
                }) .catch(function (error) {
                    console.log(error);
                });
        },
        methods: {
            handleSubmit (name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        let self = this;
                     //   let path=self.$axios.defaults.baseURL;
                        self.flightrunway.airdromeId={airportId:this.runwayentitys.airportid};
                        self.flightrunway.longitude=self.longitudedegrees+'-'+self.longitudemins+'-'+self.longitudesecs;
                        self.flightrunway.latitude=self.latitudedegrees+'-'+self.latitudemins+'-'+self.latitudesecs;
                        self.$http.jsonPost('/app/api/flightrunway',self.flightrunway).then(function (response) {
                            self.flightrunway.id=response.id;
                            self.$Message.success('提交成功!');
                            self.$emit("listenadrun",true,false);
                        }).catch(function (error) {
                            console.log(error);
                            self.$Message.error('提交失败!');
                        });
                    } else {
                        this.$Message.error('表单验证失败!');
                    }
                })
            },
            reback () {
                this.$emit("listenadrun",true,false);
            }
        }
    }
</script>
