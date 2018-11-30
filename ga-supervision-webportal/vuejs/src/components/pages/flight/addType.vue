<template>
  <div style="width: 1280px; height: 900px; margin-top: 30px;background:white; padding-top: 40px">
    <div style="border: 1px solid #f0f0f0; margin-left: 40px; margin-right: 40px;">
      <Form :model="planType" :label-width="110" :rules="planTypeValidate" ref="planTypeRef" style="margin-left: 40px; margin-top: 40px">
        <div class="form-addair">
          <FormItem label="机型编号:" style="width: 45%;display: inline-block;" prop="typeNumber">
            <Input v-model="planType.typeNumber" placeholder="输入编号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="机型名称:" style="width: 45%;display: inline-block;margin-left: 20px" prop="typeName">
            <Input v-model="planType.typeName" placeholder="输入名称" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="类别:" style="width: 45%;display: inline-block;" prop="aircraftType">
            <Select v-model="planType.aircraftType" placeholder="请选择" style="width: 90%;">
              <Option value="飞机">飞机</Option>
              <Option value="直升机">直升机</Option>
              <Option value="自转旋翼机">自转旋翼机</Option>
              <Option value="倾转旋翼机">倾转旋翼机</Option>
              <Option value="飞艇">飞艇</Option>
              <Option value="气球">气球</Option>
              <Option value="滑翔机">滑翔机</Option>
              <Option value="无人机">无人机</Option>
            </Select>
          </FormItem>
          <FormItem label="级别:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="aircraftLevel">
            <Select v-model="planType.aircraftLevel" placeholder="请选择" style="width: 90%;">
              <Option value="固定翼">固定翼</Option>
              <Option value="旋翼">旋翼</Option>
              <Option value="滑翔机">滑翔机</Option>
              <Option value="其他">其他</Option>
            </Select>
          </FormItem>
          <FormItem label="型别:" style="width: 45%;display: inline-block" prop="aircraftModel">
            <Select v-model="planType.aircraftModel" placeholder="请选择" style="width: 90%;">
              <Option value="5700千克以上的航空器">5700千克以上的航空器</Option>
              <Option value="3180千克以上的航空器">3180千克以上的航空器</Option>
              <Option value="涡轮喷气动力飞机">涡轮喷气动力飞机</Option>
              <Option value="其他航空器">其他航空器</Option>
            </Select>
          </FormItem>
          <FormItem label="制造厂家:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="manufacturer">
            <Input v-model="planType.manufacturer" placeholder="制造厂家" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="最大起飞重量:" style="width: 45%;display: inline-block;" prop="maxFlyWeight">
            <Input v-model="planType.maxFlyWeight" placeholder="最大起飞重量" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="最大起飞速度:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="maxFlySpeed">
            <Input v-model="planType.maxFlySpeed" placeholder="最大起飞速度" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="最大飞行距离:"  style="width: 45%;display: inline-block" prop="maxFly">
            <Input v-model="planType.maxFly" placeholder="最大飞行距离" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="宽度:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="width">
            <Input v-model="planType.width" placeholder="宽度" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="长度:"  style="width: 45%;display: inline-block" prop="length">
            <Input v-model="planType.length" placeholder="长度" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="耗油量:" style="width: 45%;display: inline-block;;margin-left: 20px" prop="fuelConsumption">
            <Input v-model="planType.fuelConsumption" placeholder="耗油量" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="驾驶员配置数:"  style="width: 45%;display: inline-block;" prop="driverNumber">
            <Input v-model="planType.driverNumber" placeholder="理论驾驶员配置数" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="座位数量:" style="width: 45%;display: inline-block;margin-left: 20px" prop="siteNumber">
            <Input v-model="planType.siteNumber" placeholder="座位数量" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="所属企业:" style="width: 45%;display: inline-block;" prop="companyId">
            <Input v-model="planType.companyName" placeholder="所属企业" :readonly="true" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="仪表飞行能力:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="meterFlag">
            <RadioGroup v-model="planType.meterFlag">
              <Radio label="1">是</Radio>
              <Radio label="0">否</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="复杂飞机:" style="width: 45%;display: inline-block;" prop="complexFlag">
            <RadioGroup v-model="planType.complexFlag">
              <Radio label="1">是</Radio>
              <Radio label="0">否</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="高性能:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="performanceFlag">
            <RadioGroup v-model="planType.performanceFlag">
              <Radio label="1">是</Radio>
              <Radio label="0">否</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="实用类:" style="width: 45%;display: inline-block;" prop="practicalFlag">
            <RadioGroup v-model="planType.practicalFlag">
              <Radio label="1">是</Radio>
              <Radio label="0">否</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="特技类:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="trickFlag">
            <RadioGroup v-model="planType.trickFlag">
              <Radio label="1">是</Radio>
              <Radio label="0">否</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="hobbs表:" style="width: 45%;display: inline-block;" prop="hobbsFlag">
            <RadioGroup v-model="planType.hobbsFlag">
              <Radio label="1">是</Radio>
              <Radio label="0">否</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="FTD:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="ftdFlag">
            <RadioGroup v-model="planType.ftdFlag">
              <Radio label="1">是</Radio>
              <Radio label="0">否</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="FPS:" style="width: 45%;display: inline-block;" prop="fpsFlag">
            <RadioGroup v-model="planType.fpsFlag">
              <Radio label="1">是</Radio>
              <Radio label="0">否</Radio>
            </RadioGroup>
          </FormItem>
          <div class="div-sumbit-but">
            <div style="float: left; text-align: right; padding-bottom: 10px; padding-top: 20px; width: 980px; margin-bottom: 40px;">
              <Button type="primary" @click="cancel" style="margin-right: 15px">取消</Button>
              <Button type="primary" @click="saveType">保存</Button>
            </div>
          </div>
        </div>
      </Form>
    </div>
  </div>
</template>

<script>
    //   import aircraftupload from '../../pages/aircraft/aircraftupload'
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                // isshow: true,
                // isshowupload: false,
                planType: {
                    typeNumber: '',
                    typeName: '',
                    companyId:'',
                    companyName:'',
                    aircraftType: '',
                    aircraftLevel: '',
                    aircraftClass: '',
                    aircraftType: '',
                    aircraftModel: '',
                    manufacturer: '',
                    maxFlyWeight: 0,
                    maxFlySpeed: 0,
                    maxFly: 0,
                    width: 0,
                    length: 0,
                    fuelConsumption: 0,
                    driverNumber: 0,
                    siteNumber: 0,
                    meterFlag: '',
                    complexFlag: '',
                    practicalFlag: '',
                    trickFlag: '',
                    hobbsFlag: '',
                    ftdFlag: '',
                    fpsFlag: '',
                },
                planTypeValidate: {
                    typeNumber: [{required: true, message: '航空器编号不能为空', trigger: 'blur'}],
                   // typeName: [{required: true, message: '机型名称不能为空', trigger: 'blur'}],
                    aircraftType: [{required: true, message: '航空器类别不能为空', trigger: 'change'}],
                   // aircraftLevel: [{required: true, message: '航空器级别不能为空', trigger: 'change'}],
                   // aircraftModel: [{required: true, message: '航空器型别不能为空', trigger: 'change'}],
                    maxFlyWeight: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    maxFlySpeed: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    maxFly: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    width: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    length: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    fuelConsumption: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    driverNumber: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    siteNumber: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],
                    //                   registernumber: [{required: true, message: '国籍登记证号不能为空', trigger: 'blur'}]
                    /*                    airworthinessCertificateNumber: [{required: true, message: '适航证编号不能为空', trigger: 'blur'}],
                                        radioLicenceNumber: [{required: true, message: '电台执照编号不能为空', trigger: 'blur'}],*/
                    /*                    area: [{required: true, message: '运行区域不能为空', trigger: 'blur'}],*/
                    /*                    functionintentList: [{required: true, type: 'array', min: 1, message: '运行种类至少选一个', trigger: 'change'}],*/
//                    enginenumber: [{required: true, type: "number", message: '发动机数量不能为空', trigger: 'change'}]
                }
            }
        },
        components: {

        },
        mounted() {
            let id = this.$route.query.id;
            let self = this;
            if(id != null) {
                self.$http.httpGet('/eim/api/plantype/findbyplantypeid/'+ id, {
                }).then(function (response) {
                    self.planType = response.data.data;
                    if(response.data.data.meterFlag == 1) {
                        self.planType.meterFlag = "1";
                    } else {
                        self.planType.meterFlag = "0";
                    }
                    if(response.data.data.complexFlag == 1) {
                        self.planType.complexFlag = "1";
                    } else {
                        self.planType.complexFlag = "0";
                    }
                    if(response.data.data.performanceFlag == 1) {
                        self.planType.performanceFlag = "1";
                    } else {
                        self.planType.performanceFlag = "0";
                    }
                    if(response.data.data.practicalFlag == 1) {
                        self.planType.practicalFlag = "1";
                    } else {
                        self.planType.practicalFlag = "0";
                    }
                    if(response.data.data.trickFlag == 1) {
                        self.planType.trickFlag = "1";
                    } else {
                        self.planType.trickFlag = "0";
                    }
                    if(response.data.data.hobbsFlag == 1) {
                        self.planType.hobbsFlag = "1";
                    } else {
                        self.planType.hobbsFlag = "0";
                    }
                    if(response.data.data.ftdFlag == 1) {
                        self.planType.ftdFlag = "1";
                    } else {
                        self.planType.ftdFlag = "0";
                    }
                    if(response.data.data.fpsFlag == 1) {
                        self.planType.fpsFlag = "1";
                    } else {
                        self.planType.fpsFlag = "0";
                    }

                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
        created() {
            let self = this;
            let buz = JSON.parse(Cookies.get("profile"));
            self.planType.companyName = buz.buz.companyName;
            self.planType.companyId = buz.buz.companyId;
        },
        methods: {
            initvalidate(){
                this.$refs['planTypeRef'].resetFields();
            },
            // showupload: function () {
            //     this.isshow = false;
            //     this.isshowupload = true;
            // },
            // closeupload: function () {
            //     this.isshow = true;
            //     this.isshowupload = false;
            // },
            addPlanType: function () {
                this.$refs.planTypeRef.validate((valid) => {
                    if (valid) {
                        let self = this;
                        self.$http.jsonPost('/eim/api/plantype/save',
                            self.planType
                        ).then(function (response) {
                            alert("保存成功");
                            self.$router.push({path: '/flight/flight_equipment'});
                        })
                            .catch(function (error) {
                                console.log(error);
                                self.$emit('childshow', '1');
                            });
                    } else {
                        alert("表单验证失败");
                    }
                });
            },
            saveType:function () {
                let self=this;
                self.addPlanType();
            },
            cancel:function () {
                this.$router.go(-1);
            }
        }
    }
</script>

<style scoped>
  .everyf{
    border: 1px solid ;
    border-color: #bbbec4;
    margin-top: 5px;
    position: relative;
  }
  .child{
    position: absolute;
    top:60px;
    right: 20px;
    width: 283px;
  }

  .flag{
    position: absolute;
    top: 5px;
    right: 3px;

  }
  .flag>img{
    width: 30px;
    display: block;
  }

  .form{
    width: 900px;
    background-color: #363636
  }




  .ivu-input-icon+.ivu-input{
    height: 30px;
    background: #b3b3b3;
    padding-right: 0;
  }
  .ivu-row{
    margin-bottom: 30px;
  }
  .submit-btn {
    font-size: 14px;
    margin: 0px 30px;
    display: inline-block;
    width: 120px;
    height: 35px;
    line-height: 30px;
    border: 1px solid #ececec;
    border-radius: 3px;
    color: #FFF;
    background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);
    background: linear-gradient(#a3a3a3, #e7e7e7);
  }
  .flybase-submit-div a:hover {
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