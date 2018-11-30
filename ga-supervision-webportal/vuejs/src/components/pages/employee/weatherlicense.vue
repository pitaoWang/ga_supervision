<style>
    .flybase-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);
        background: linear-gradient(#a3a3a3, #e7e7e7);
    }
    .flybase-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        background: -webkit-linear-gradient(#e7e7e7, #a3a3a3);
        background: linear-gradient(#e7e7e7, #a3a3a3);
    }
</style>
<template>
    <div >
        <div>
            <FormItem label="观测资格有效性" prop="obsereffect" style="display: inline-block;width: 550px" required=true>
                <Select v-model="weatherlicense.obsereffect" placeholder="请选择"  >
                    <Option value="有效">有效</Option>
                    <Option value="无效">无效</Option>
                </Select>
            </FormItem>
            <FormItem label="预测资格有效性" prop="forecasteffect" style="display: inline-block;width: 550px" required=true>
                <!--<Input v-model="weatherlicense.forecasteffect" placeholder="" ></Input>-->
                <Select v-model="weatherlicense.forecasteffect" placeholder="请选择">
                    <Option value="有效">有效</Option>
                    <Option value="无效">无效</Option>
                </Select>
            </FormItem>
        </div>
        <div>
            <!--<Row>
                <Col :md="12" >-->
            <FormItem label="观测设备保障资格有效性" prop="obserequipeffect" style="height: 32px;display: inline-block;width: 550px" required=true>
                <!--<Input v-model="weatherlicense.obserequipeffect" placeholder="" ></Input>-->
                <Select v-model="weatherlicense.obserequipeffect" placeholder="请选择">
                    <Option value="有效">有效</Option>
                    <Option value="无效">无效</Option>
                </Select>
            </FormItem>
            <!-- </Col>-->
            <!--<Col :md="11">-->
            <FormItem label="气象雷达设备保障资格有效性" prop="radarequipeffect" style="height: 32px;display: inline-block;width: 550px" required=true>
                <Select v-model="weatherlicense.radarequipeffect" placeholder="请选择">
                    <Option value="有效">有效</Option>
                    <Option value="无效">无效</Option>
                </Select>
                <!--<Input v-model="weatherlicense.radarequipeffect" placeholder=""></Input>-->
            </FormItem>
            <!--     </Col>
             </Row>-->
        </div>
        <div>
            <!--<Row>
                <Col :md="12" >-->
            <FormItem label="系统设备保障资格有效性" prop="infoequipeffect" style="height: 32px;display: inline-block;width: 550px" required=true>
                <!--<Input v-model="weatherlicense.infoequipeffect" placeholder=""></Input>-->
                <Select v-model="weatherlicense.infoequipeffect" placeholder="请选择">
                    <Option value="有效">有效</Option>
                    <Option value="无效">无效</Option>
                </Select>
            </FormItem>
            <!--</Col>
        </Row>-->
            <FormItem label="专业类型" prop="majorType" style="height: 32px;display: inline-block;width: 550px" required=true>
                <Input v-model="weatherlicense.majorType" placeholder="请输入"></Input>
            </FormItem>

        </div>
        <div>
            <FormItem label="适用地区" style="height: 32px;color:#657180;display: inline-block" required=true>

                <CheckboxGroup v-model="businesslist">
                    <Checkbox label="华北">
                        <span>华北</span>
                    </Checkbox>
                    <Checkbox label="东北">
                        <span>东北</span>
                    </Checkbox>
                    <Checkbox label="华东">
                        <span>华东</span>
                    </Checkbox>
                    <Checkbox label="中南">
                        <span>中南</span>
                    </Checkbox>
                    <Checkbox label="西南">
                        <span>西南</span>
                    </Checkbox>
                    <Checkbox label="西北">
                        <span>西北</span>
                    </Checkbox>
                    <Checkbox label="新疆">
                        <span>新疆</span>
                    </Checkbox>
                </CheckboxGroup>
            </FormItem>

        </div>
    </div>
</template>
<script>
    export default {
        data (){
            return {

                weatherlicense:{
                    id:'',
                    obsereffect:'',
                    forecasteffect:'',
                    obserequipeffect:'',
                    radarequipeffect:'',
                    infoequipeffect:'',
                    licensearea:'',
                    majorType:''
                },
                ruleValidate: {
                    obsereffect: [
                        { required: true, message: '观测资格有效性不能为空', trigger: 'blur' }
                    ],

                    forecasteffect: [
                        { required: true, message: '预测资格有效性不能为空', trigger: 'blur' }
                    ],

                    obserequipeffect: [
                        { required: true, message: '自动气象观测设备保障资格有效性不能为空', trigger: 'blur' }
                    ],

                    radarequipeffect: [
                        { required: true, message: '气象雷达设备保障资格有效性不能为空', trigger: 'blur' }
                    ],

                    infoequipeffect: [
                        { required: true, message: '气象信息系统设备保障资格有效性不能为空', trigger: 'blur' }
                    ],
                    majorType: [
                        { required: true, message: '专业类型不能为空', trigger: 'blur' }
                    ],
                },
                businesslist:[],

            }
        },

        created: function (newval) {
//            let licenseId=this.$route.query.licenseId;
//            let licensetype=this.$route.query.licensetype;
//            let self=this;
//            console.log(licenseId,'licenseId........');
//            self.$http.httpGet("/app/api/qulification/search/license",{
//                id:licenseId,
//                licensetype:licensetype
//            }).then((res) => {
//                let result = res.data.data;
//                console.log(result,'result............');
//
//                self.weatherlicense=result.weatherLicense;
//                self.businesslist=result.weatherLicense.licensearea.split(",");
//                if (result.resultCode == 200) {
//                    self.$Message.info('执照信息修改成功');
//                    this.$router.push({path: '/pilot_info', query: {qualificationisshow: true}});
//                }
//            }).catch(function (error) {
//                console.log(error);
//            });
        },
        methods: {
        },
//        watch: {
//            weatherlicense: {
//                handler (oldval,newval) {
//                    console.log(newval);
//                    let self = this;
//                    let licensearea='';
//                    for(let i=0;i<self.businesslist.length;i++){
//                        if(i==(self.businesslist.length-1)){
//                            licensearea+=self.businesslist[i];
//                        }
//                        else{
//                            licensearea+=self.businesslist[i]+',';
//                        }
//                    }
//                    self.weatherlicense.licensearea=licensearea;
//                    console.log(licensearea,'licensearea.....');
//                    console.log(self.weatherlicense.licensearea,'112212131312121212121');
//                    this.$emit("getChildMsg",self.weatherlicense);
//                },
//                deep:true
//            }
//        },
    }
</script>