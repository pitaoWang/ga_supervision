<template>
  <div style="width: 1280px; height: 900px; background:white; ">
    <div style = "width: 1190px;">
      <!--<button class="div-button" style="width:88px; height:30px; border-radius:4px; border:1px solid #33A6FC; background-color: #FFFFFF; color:#33A6FC; float: right; cursor:pointer; margin-top: 10px; margin-bottom: 10px; margin-right:-20px"
              @click="backcompanysup()" >返  回</button>-->
      <span style="width:100%; display:inline-block;text-align:right;margin-top:10px;margin-bottom: 10px;margin-left: 20px; ">
                    <Button primary @click="backcompanysup()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
    </div>
    <table style="width: 1190px; border-spacing:0px; margin-left: 20px">
      <tbody>
      <tr>
        <td class="tr-1">机型编号</td>
        <td class="tr-2 tr-4">{{planType.typeNumber}}</td>
        <td class="tr-1 tr-3">机型名称</td>
        <td class="tr-2 tr-4">{{planType.typeName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">类别</td>
        <td class="tr-2 tr-4">{{planType.aircraftType}}</td>
        <td class="tr-1 tr-3">级别</td>
        <td class="tr-2 tr-4">{{planType.aircraftLevel}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">型别</td>
        <td class="tr-2 tr-4">{{planType.aircraftModel}}</td>
        <td class="tr-1 tr-3">制造厂家</td>
        <td class="tr-2 tr-4"> {{planType.manufacturer}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">最大起飞重量</td>
        <td class="tr-2 tr-4">{{planType.maxFlyWeight}}</td>
        <td class="tr-1 tr-3">最大起飞速度</td>
        <td class="tr-2 tr-4">{{planType.maxFlySpeed}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">最大飞行距离</td>
        <td class="tr-2 tr-4">{{planType.maxFly}}</td>
        <td class="tr-1 tr-3">宽度</td>
        <td class="tr-2 tr-4">{{planType.width}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">长度</td>
        <td class="tr-2 tr-4">{{planType.length}}</td>
        <td class="tr-1 tr-3">所属企业</td>
        <td class="tr-2 tr-4">{{planType.companyName}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">耗油量</td>
        <td class="tr-2 tr-4" >{{planType.fuelConsumption}}</td>
        <td class="tr-1 tr-3">驾驶员配置数</td>
        <td class="tr-2 tr-4" >{{planType.driverNumber}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">座位数量</td>
        <td class="tr-2 tr-4" >{{planType.siteNumber}}</td>
        <td class="tr-1 tr-3">仪表飞行能力</td>
        <td class="tr-2 tr-4" >{{planType.meterFlag}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">复杂飞机</td>
        <td class="tr-2 tr-4" >{{planType.complexFlag}}</td>
        <td class="tr-1 tr-3">高性能</td>
        <td class="tr-2 tr-4" >{{planType.performanceFlag}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">实用类</td>
        <td class="tr-2 tr-4" >{{planType.practicalFlag}}</td>
        <td class="tr-1 tr-3">特技类</td>
        <td class="tr-2 tr-4" >{{planType.driverNumber}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">hobbs表</td>
        <td class="tr-2 tr-4" >{{planType.hobbsFlag}}</td>
        <td class="tr-1 tr-3">FTD</td>
        <td class="tr-2 tr-4" >{{planType.ftdFlag}}</td>
      </tr>
      <tr>
        <td class="tr-1 tr-3">FPS</td>
        <td class="tr-2 tr-4" colspan="3" > {{planType.fpsFlag}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
    //   import aircraftupload from '../../pages/aircraft/aircraftupload'

    export default {
        data() {
            return {
                aircraft:{},
                isfg:'',
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
                }
            }
        },
        components: {

        },
        mounted() {
            this.isfg = this.$route.query.isfg;
            let id;
            if(this.isfg!=3){
                this.aircraft = JSON.parse(this.$route.query.aircraft);
                id = JSON.parse(this.$route.query.aircraft).planTypeId;
            }else{
                id = this.$route.query.id;
            }
            let self = this;
            if(id != null) {
                self.$http.httpGet('/eim/api/plantype/findbyplantypeid/'+ id, {
                }).then(function (response) {
                    self.planType = response.data.data;
                    if(response.data.data.meterFlag == 1) {
                        self.planType.meterFlag = "是";
                    } else {
                        self.planType.meterFlag = "否";
                    }
                    if(response.data.data.complexFlag == 1) {
                        self.planType.complexFlag = "是";
                    } else {
                        self.planType.complexFlag = "否";
                    }
                    if(response.data.data.performanceFlag == 1) {
                        self.planType.performanceFlag = "是";
                    } else {
                        self.planType.performanceFlag = "否";
                    }
                    if(response.data.data.practicalFlag == 1) {
                        self.planType.practicalFlag = "是";
                    } else {
                        self.planType.practicalFlag = "否";
                    }
                    if(response.data.data.trickFlag == 1) {
                        self.planType.trickFlag = "是";
                    } else {
                        self.planType.trickFlag = "否";
                    }
                    if(response.data.data.hobbsFlag == 1) {
                        self.planType.hobbsFlag = "是";
                    } else {
                        self.planType.hobbsFlag = "否";
                    }
                    if(response.data.data.ftdFlag == 1) {
                        self.planType.ftdFlag = "是";
                    } else {
                        self.planType.ftdFlag = "否";
                    }
                    if(response.data.data.fpsFlag == 1) {
                        self.planType.fpsFlag = "是";
                    } else {
                        self.planType.fpsFlag = "否";
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            }
        },
        created() {

        },
        methods: {
            backcompanysup(){
                if(this.isfg==1){
                    this.$router.push({path: '/aircraftmonitoring/tabs',query:{regNumber:this.aircraft.regNumber,aircraft:JSON.stringify(this.aircraft)}});
                }else if(this.isfg==2){
                    this.$router.push({path: '/addAircraft',query:{aircraft:JSON.stringify(this.aircraft)}});
                }else{
                    this.$router.go(-1);
                }

            },
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
  .tr-1 {
    font-size: 14px;
    width: 180px;
    padding-left: 13px;
    height: 45px;
    color:  #606265;
    border-left: solid 1px #DCDFE6;
    border-right: solid 1px #DCDFE6;
    border-bottom: solid 1px #DCDFE6;
    background-color: #F8F8F9;
  }
  .tr-2 {
    font-size: 14px;
    width: 200px;
    text-align: center;
    color:  #606265;
    background-color: #FFF;
  }

  table{
    border-collapse:collapse;
  }
  table tr td{
    border:1px solid #DCDFE6;
  }

  .tr-3{
    background-color: #f8f8f9;
  }

  /*.tr-4{*/
  /*background-color: #FFF;*/
  /*}*/
  .tr-5{
    background-color: #f8f8f9;
  }
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