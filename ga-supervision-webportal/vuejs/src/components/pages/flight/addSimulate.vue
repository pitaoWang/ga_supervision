<template>

  <div style="width: 1280px; height: auto; margin-top:30px;background:white; padding-top: 40px; padding-left: 30px; padding-bottom: 40px">
    <div style="border: 1px solid #f0f0f0; margin-left: 40px; margin-right: 40px;">
      <Form :model="simulate" :label-width="180" :rules="simulateValidate" ref="simulateRef" style="margin-top: 40px">
        <div class="form-addair">
          <FormItem label="设备型号:" style="width: 45%;display: inline-block;" prop="equipmentModel">
            <Input v-model="simulate.equipmentModel" placeholder="设备型号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="设备名称:" style="width: 45%;display: inline-block;margin-left: 20px" prop="equipmentName">
            <Input v-model="simulate.equipmentName" placeholder="设备名称" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="合格证编号:" style="width: 45%;display: inline-block;" prop="certificateNum">
            <Input v-model="simulate.certificateNum" placeholder="合格证编号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="合格证有效期:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="certificateValidityPeriod">
            <DatePicker v-model="simulate.certificateValidityPeriod" :editable="false" type="date" placeholder="合格证有效期" style="width: 90%;"></DatePicker>
          </FormItem>
          <FormItem label="设备类型:" style="width: 45%;display: inline-block" prop="equipmentType">
            <Select v-model="simulate.equipmentType" placeholder="请选择"  @on-change="getLevel" style="width: 90%;">
              <Option value="飞行训练器">飞行训练器</Option>
              <Option value="飞行模拟器">飞行模拟器</Option>
            </Select>
          </FormItem>
          <FormItem label="生产序号:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="productionSn">
            <Input v-model="simulate.productionSn" placeholder="生产序号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="等级:" style="width: 45%;display: inline-block;" prop="level">
            <Select v-if="simulate.equipmentType== '' || simulate.equipmentType== '飞行训练器'" v-model="simulate.level" placeholder="请选择" style="width: 90%;">
              <Option v-for="item in tranList" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
            <Select v-if="simulate.equipmentType== '飞行模拟器'" v-model="simulate.level" placeholder="请选择" style="width: 90%;">
              <Option v-for="item in simList" :value="item.value" :key="item.value">{{item.label}}</Option>
            </Select>
          </FormItem>
          <FormItem label="设备安装地点:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="installationSite">
            <Input v-model="simulate.installationSite" placeholder="设备安装地点" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="设备生产厂家:" style="width: 45%;display: inline-block;" prop="equipmentManufacturer">
            <Input v-model="simulate.equipmentManufacturer" placeholder="设备生产厂家" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="设备生产日期:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="equipmentProductDate">
            <DatePicker v-model="simulate.equipmentProductDate" :editable="false" type="date" placeholder="设备生产日期" style="width: 90%;"></DatePicker>
          </FormItem>
          <FormItem label="初始鉴定日期:" style="width: 45%;display: inline-block;" prop="identificationDate">
            <DatePicker v-model="simulate.identificationDate" :editable="false" type="date" placeholder="初始鉴定日期" style="width: 90%;"></DatePicker>
          </FormItem>
          <FormItem label="视景系统制造厂家:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="visionSystemManufacturer">
            <Input v-model="simulate.visionSystemManufacturer" placeholder="视景系统制造厂家" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="视影系统显示类型:" style="width: 45%;display: inline-block;" prop="visionSystemType">
            <Input v-model="simulate.visionSystemType" placeholder="视影系统显示类型" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="视影系统型号:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="visionSystemModel">
            <Input v-model="simulate.visionSystemModel" placeholder="视影系统型号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="运动系统型号:" style="width: 45%;display: inline-block;" prop="sportSystemModel">
            <Input v-model="simulate.sportSystemModel" placeholder="运动系统型号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="运动系统制造厂家:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="sprotSystemManufacturer">
            <Input v-model="simulate.sprotSystemManufacturer" placeholder="运动系统制造厂家" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="运动系统自由度:" style="width: 45%;display: inline-block;" prop="sprotSystemFree">
            <Input v-model="simulate.sprotSystemFree" placeholder="运动系统自由度" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="模拟航空器型号:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="aircraftModel">
            <Input v-model="simulate.aircraftModel" placeholder="模拟航空器型号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="空气动力数据修订号:" style="width: 45%;display: inline-block;" prop="airAmendmentNumber">
            <Input v-model="simulate.airAmendmentNumber" placeholder="空气动力数据修订号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="空气动力数据出处:"  style="width: 45%;display: inline-block;margin-left: 20px" prop="airDataSource">
            <Input v-model="simulate.airDataSource" placeholder="空气动力数据出处" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="飞行操纵数据修订号:" style="width: 45%;display: inline-block;" prop="flightAmendmentNuber">
            <Input v-model="simulate.flightAmendmentNuber" placeholder="飞行操纵数据修订号" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="飞行操纵数据出处:" style="width: 45%;display: inline-block;margin-left: 20px" prop="flightDataSource">
            <Input v-model="simulate.flightDataSource" placeholder="飞行操纵数据出处" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="飞行管理系统标识:" style="width: 45%;display: inline-block;" prop="flightManagerFlag">
            <Input v-model="simulate.flightManagerFlag" placeholder="飞行管理系统标识" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="飞行管理系统修订版本:" style="width: 45%;display: inline-block;margin-left: 20px" prop="systemVersion">
            <Input v-model="simulate.systemVersion" placeholder="飞行管理系统修订版本" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="飞行模拟设备计算机标识:" style="width: 45%;display: inline-block;" prop="computerFlag">
            <Input v-model="simulate.computerFlag" placeholder="飞行模拟设备计算机标识" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="模拟航空器制造厂家:" style="width: 45%;display: inline-block;margin-left: 20px" prop="aircraftManufacturer">
            <Input v-model="simulate.aircraftManufacturer" placeholder="模拟航空器制造厂家" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="运行总时间:" style="width: 45%;display: inline-block;" prop="totalTime">
            <Input v-model="simulate.totalTime" placeholder="运行总时间" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="设备状态:" style="width: 45%;display: inline-block;margin-left: 20px" prop="equipmentStatus">
            <Input v-model="simulate.equipmentStatus" placeholder="设备状态" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="所属管理局:" style="width: 45%;display: inline-block;" prop="manageId">
            <Input v-model="simulate.manageId" type="text" :readonly="true" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="所属监管局:" style="width: 45%;display: inline-block;margin-left: 20px" prop="superId">
            <Input v-model="simulate.superId" type="text" :readonly="true" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="所属企业:" style="width: 45%;display: inline-block;" prop="companyId">
            <Input v-model="simulate.companyName" type="text" :readonly="true" style="width: 90%;"></Input>
          </FormItem>
          <FormItem style="width: 45%;display: inline-block;margin-left: 20px" prop="additionalFeatures">

          </FormItem>
          <FormItem label="附加设备和功能:" style="width: 45%;display: inline-block;" prop="additionalFeatures">
            <Input v-model="simulate.additionalFeatures" type="textarea" placeholder="附加设备和功能" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="鉴定测试指南:" style="width: 45%;display: inline-block;margin-left: 20px" prop="testGuide">
            <Input v-model="simulate.testGuide" type="textarea" placeholder="鉴定测试指南" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="训练附加要求:" style="width: 45%;display: inline-block;" prop="trainingRequirements">
            <Input v-model="simulate.trainingRequirements" type="textarea" placeholder="训练附加要求" style="width: 90%;"></Input>
          </FormItem>
          <FormItem label="使用声明:" style="width: 45%;display: inline-block;margin-left: 20px" prop="userGuide">
            <Input v-model="simulate.userGuide" type="textarea" placeholder="使用声明" style="width: 90%;"></Input>
          </FormItem>
          <div>
            <simulateupload v-on:childurlone="dataUpload" ref="simulateupdateurl"></simulateupload>
          </div>
          <div class="bottom5" style="width:87%;margin-left:40px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">鉴定记录</h2>
                     <Button type="primary" @click="add_dialog_show=true" icon="plus" style="width: 98px;height: 32px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>

                </span>

            <div class="bottom5" style="margin-top:5px;">
              <Table border :columns="columns7" :data="simulatorRecord"></Table>
            </div>

          </div>

          <div class="bottom5" style="width:87%;margin-left:40px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">适用课程</h2>
                     <Button type="primary" @click="add_dialog_show1=true" icon="plus" style="width: 98px;height: 32px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>

            <div class="bottom5" style="margin-top:5px;">
              <Table border :columns="columns8" :data="course"></Table>
            </div>.

          </div>

          <div class="bottom5" style="width:87%;margin-left:40px;" >
                <span style="width:100%;display:inline-block;text-align:right;padding-top: 10px">
                    <h2 style="float:left;">发动机</h2>
                     <Button type="primary" @click="add_dialog_show2=true" icon="plus" style="width: 98px;height: 32px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>

            <div class="bottom5" style="margin-top:5px;">
              <Table border :columns="columns9" :data="engine"></Table>
            </div>

          </div>

          <div style="width: 90%; text-align: right; margin-right: 200px;margin-top: 30px">
            <div class="div-sumbit-but">
              <Button type="primary" @click="cancel">取消</Button>&nbsp;&nbsp;&nbsp;&nbsp;
              <Button type="primary" @click="saveSimulate">保存</Button>
            </div>
          </div>
        </div>
      </Form>
      <Modal v-model="add_dialog_show" width="500" title="新增鉴定记录"  :mask-closable="false"  :styles="{top: '20px'}">
        <Form :label-width="120" :model="check_form" :rules="checkValidate" ref="checks"  >
          <div >
            <Row>
              <Col :md="23">
                <FormItem label="鉴定记录类型" class="bottom5" prop="recordType" >
                  <Select v-model="check_form.recordType" placeholder="请选择"  style="width: 90%;">
                    <Option value="初始鉴定">初始鉴定</Option>
                    <Option value="升级鉴定">升级鉴定</Option>
                    <Option value="定期鉴定">定期鉴定</Option>
                    <Option value="附加鉴定">附加鉴定</Option>
                  </Select>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="鉴定结果" class="bottom5" prop="identificationResult">
                  <Input v-model="check_form.identificationResult"  placeholder="请输入鉴定结果..." style="width: 90%;"></Input>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="申请等级" class="bottom5" prop="applicationLever">
                  <Select v-model="check_form.applicationLever" placeholder="请选择"  style="width: 90%;">
                    <Option value="1">1</Option>
                    <Option value="2">2</Option>
                    <Option value="3">3</Option>
                    <Option value="4">4</Option>
                    <Option value="5">5</Option>
                    <Option value="6">6</Option>
                  </Select>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="鉴定标准" class="bottom5" prop="identStandard">
                  <Select v-model="check_form.identStandard" placeholder="请选择"  style="width: 90%;">
                    <Option value="CCAR-60">CCAR-60</Option>
                    <Option value="ICAO Doc9625">ICAO Doc9625</Option>
                    <Option value="FAR-60">FAR-60</Option>
                    <Option value="CS-FSTD(A)/(H)">CS-FSTD(A)/(H)</Option>
                  </Select>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="开始鉴定时间" class="bottom5"  prop="beginTime">
                  <DatePicker v-model="check_form.beginTime" type="date" placeholder="选择日期" style="width: 90%;"></DatePicker>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="结束鉴定时间" class="bottom5"  prop="endTime">
                  <DatePicker v-model="check_form.endTime" type="date" placeholder="选择日期" style="width: 90%;"></DatePicker>
                </FormItem>
                <Col :md="23">
                  <FormItem label="备注信息" class="bottom5" prop="content">
                    <Input v-model="check_form.content"  placeholder="请输入备注信息..." style="width: 90%;"></Input>
                  </FormItem>
                </Col>
              </Col>
            </Row>
          </div>
        </Form>
        <div slot="footer">
          <Button type="text" @click="add_dialog_show = false">取消</Button>
          <Button type="primary"  @click="submitCheckForm">提交</Button>
        </div>
      </Modal>

      <Modal v-model="add_dialog_show1" width="400" title="增加适用课程"  :mask-closable="false" @on-ok="submitCheckForm1"
             @on-cancel="add_dialog_show1 = false" :styles="{top: '20px'}">
        <Form :label-width="100" v-model="check_form1" >
          <div >
            <Row>
              <Col :md="23">
                <FormItem label="适用课程编号" class="bottom5">
                  <Input v-model="check_form1.courseNum" placeholder="请输入适用课程编号"></Input>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="适用课程类型" class="bottom5">
                  <Select v-model="check_form1.courseType" placeholder="请选择"  style="width: 90%;">
                    <Option value="私用驾驶员执照课程">私用驾驶员执照课程</Option>
                    <Option value="商用驾驶员执照课程">商用驾驶员执照课程</Option>
                    <Option value="仪表等级课程">仪表等级课程</Option>
                    <Option value="高性能训练课程">高性能训练课程</Option>
                  </Select>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="适用课程名称" class="bottom5">
                  <Input v-model="check_form1.courseName" placeholder="请输入适用课程名称"></Input>
                </FormItem>
              </Col>
            </Row>
          </div>
        </Form>
      </Modal>

      <Modal v-model="add_dialog_show2" width="400" title="增加发动机"  :mask-closable="false" @on-ok="submitCheckForm2"
             @on-cancel="add_dialog_show2 = false" :styles="{top: '20px'}">
        <Form :label-width="100" v-model="check_form2" >
          <div >
            <Row>
              <Col :md="23">
                <FormItem label="发动机型号" class="bottom5">
                  <Input v-model="check_form2.engineType" placeholder="请输入发动机型号"></Input>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="数据修订号" class="bottom5">
                  <Input v-model="check_form2.dataRevisionNum" placeholder="请输入发动机数据修订号"></Input>
                </FormItem>
              </Col>
              <Col :md="23">
                <FormItem label="数据出处" class="bottom5">
                  <Input v-model="check_form2.dataSource" placeholder="发动机数据出处"></Input>
                </FormItem>
              </Col>
            </Row>
          </div>
        </Form>
      </Modal>
    </div>
  </div>
</template>

<script>
    import simulateupload from './simulateupload'
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                add_dialog_show2: false,
                temp2:{
                    id:'',
                    simulatorId:'',
                    engineType:'',
                    dataRevisionNum:'',
                    dataSource:''

                },
                check_form2: {},
                engine:[],
                engineindex:'',
                columns9: [
                    {
                        title: '发动机型号',
                        key: 'engineType',
                        align: 'center',
                        width: 305
                    },
                    {
                        title: '发动机数据修订号',
                        width: 275,
                        key: 'dataRevisionNum',
                        align: 'center'
                    },
                    {
                        title: '发动机数据出处',
                        width: 313,
                        key: 'dataSource',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '16px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_edit.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck2(params.index)
                                        }
                                    }
                                }),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginLeft: '4px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.removeCheck2(params.index)
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],



                add_dialog_show1: false,
                temp1:{
                    id:'',
                    simulatorId:'',
                    courseNum:'',
                    courseType:'',
                    courseName:''

                },
                check_form1: {},
                course:[],
                courseindex:'',
                columns8: [
                    {
                        title: '适用课程编号',
                        key: 'courseNum',
                        align: 'center',
                        width: 305
                    },
                    {
                        title: '适用课程类型',
                        width: 275,
                        key: 'courseType',
                        align: 'center'
                    },
                    {
                        title: '适用课程名称',
                        width: 313,
                        key: 'courseName',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '16px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_edit.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck1(params.index)
                                        }
                                    }
                                }),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginLeft: '4px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.removeCheck1(params.index)
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],
                add_dialog_show: false,
                temp:{
                    id:'',
                    simulatorId:'',
                    recordType:'',
                    identificationResult:'',
                    applicationLever:'',
                    identStandard:'',
                    beginTime:'',
                    endTime:'',
                    content:''

                },
                check_form: {
                    id:'',
                    simulatorId:'',
                    recordType:'',
                    identificationResult:'',
                    applicationLever:'',
                    identStandard:'',
                    beginTime:'',
                    endTime:'',
                    content:''
                },
                simulatorRecord:[],
                simulatorRecordindex:'',
                columns7: [
                    {
                        title: '鉴定类型',
                        key: 'recordType',
                        align: 'center',
                        width: 100
                    },
                    {
                        title: '鉴定结果',
                        width: 154,
                        key: 'identificationResult',
                        align: 'center'
                    },
                    {
                        title: '申请等级',
                        width: 90,
                        key: 'applicationLever',
                        align: 'center'
                    },
                    {
                        title: '鉴定标准',
                        width: 130,
                        key: 'identStandard',
                        align: 'center'
                    },
                    {
                        title: '开始鉴定时间',
                        width: 110,
                        key: 'beginTime',
                        align: 'center'
                    },
                    {
                        title: '结束鉴定时间',
                        width: 110,
                        key: 'endTime',
                        align: 'center'
                    },
                    {
                        title: '备注信息',
                        width: 200,
                        key: 'content',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '16px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_edit.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginLeft: '4px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.removeCheck(params.index)
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],
                // isshow: true,
                // isshowupload: false,
                manageList:[],
                superList:[],
                tranList: [
                    {
                        value: '1',
                        label: '1'
                    },
                    {
                        value: '2',
                        label: '2'
                    },
                    {
                        value: '3',
                        label: '3'
                    },
                    {
                        value: '4',
                        label: '4'
                    },
                    {
                        value: '5',
                        label: '5'
                    },
                    {
                        value: '6',
                        label: '6'
                    },
                    {
                        value: '7',
                        label: '7'
                    }
                ],
                simList: [
                    {
                        value: 'A',
                        label: 'A'
                    },
                    {
                        value: 'B',
                        label: 'B'
                    },
                    {
                        value: 'C',
                        label: 'C'
                    },
                    {
                        value: 'D',
                        label: 'D'
                    }
                ],
                simulate: {
                    id:'',
                    equipmentModel: '',
                    equipmentName: '',
                    companyId: '',
                    superId: '',
                    manageId: '',
                    certificateNum: '',
                    certificateValidityPeriod: '',
                    certificateFile: '',
                    equipmentType: '',
                    equipmentModel: '',
                    productionSn: '',
                    level: '',
                    installationSite: '',
                    equipmentManufacturer: '',
                    equipmentProductDate: '',
                    identificationDate: '',
                    visionSystemManufacturer: '',
                    visionSystemType: '',
                    visionSystemModel: '',
                    sportSystemModel: '',
                    sprotSystemManufacturer: '',
                    sprotSystemFree: '',
                    additionalFeatures: '',
                    photos: '',
                    aircraftModel: '',
                    airAmendmentNumber: '',
                    airDataSource: '',
                    flightAmendmentNuber: '',
                    flightDataSource: '',
                    flightManagerFlag: '',
                    systemVersion: '',
                    computerFlag: '',
                    trainingRequirements: '',
                    testGuide: '',
                    aircraftManufacturer: '',
                    userGuide: '',
                    totalTime: 0.0,
                    equipmentStatus: '',
                    companyName:'',
                    superName:'',
                    manageName:''
                },
                certificateFile:[],
                photos:[],
                isflg:'',
                simulateValidate: {
                    equipmentType: [{required: true, message: '设备类型不能为空', trigger: 'change'}],
                    equipmentModel: [{required: true, message: '设备型号不能为空', trigger: 'blur'}],
                    equipmentName: [{required: true, message: '设备名称不能为空', trigger: 'blur'}],
                    certificateNum:[{required: true, message: '合格证编号不能为空', trigger: 'change'}],
                    totalTime: [{  pattern: /^[0-9]+([.]{1}[0-9]+){0,1}$/,message: '必須輸入数字或小数！' }],


                },
                checkValidate: {
                    recordType: [{required: true, message: '鉴定类型不能为空', trigger: 'blur'}],
                    beginTime:[{required: true,type:"date", message: '开始鉴定时间不能为空', trigger: 'change'}],
                    endTime:[{required: true, type:"date",message: '结束鉴定时间不能为空', trigger: 'change'}],

                }
            }
        },
        components: {
            simulateupload
        },
        mounted() {
            let id = this.$route.query.id;
            let self = this;
            if(id != null) {
                self.$http.httpGet('/eim/api/simulate/getSimulatorById/'+ id, {
                }).then(function (response) {
                    self.simulate = response.data.simulator;
                    self.$refs.simulateupdateurl.urlupdate(self.simulate);
                    self.engine = response.data.engine;
                    self.course = response.data.course;
                    self.simulatorRecord = response.data.simulatorRecord;
                    let buz = JSON.parse(Cookies.get("profile"));
                        self.simulate.companyName = buz.buz.companyName;
                        self.simulate.companyId = buz.buz.companyId;
                }).catch(function (error) {
                    console.log(error);
                });


                self.isflg = '1'
            }
        },
        created() {
            let self = this;

            let buz = JSON.parse(Cookies.get("profile"));

                self.simulate.companyName = buz.buz.companyName;
                self.simulate.companyId = buz.buz.companyId;
            self.simulate.superId = buz.buz.supervisionId;
            self.simulate.manageId = buz.buz.authorityId;

            self.$http.httpGet('/eim/api/company/search/orgname', {orgtype:2}).then(function (response) {
                self.manageList = response.data;
            }).catch(function (error) {
                console.log(error);
            });

            self.$http.httpGet('/eim/api/company/search/orgname', {orgtype:1}).then(function (response) {
                self.superList = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        methods: {

            removeCheck2(index) {
                let self = this;

                let url = "/eim/api";

                let checkUrl = url + "/engine/" + this.engine[index].id;
                self.$http.httpDelete(checkUrl, {}).then((res) => {
                    let result = res.data
                    if (result.resultCode == 200) {
                        self.engine.splice(index, 1);
                        self.$Message.info("删除成功，id=" + result.data);
                    }
                });


            },
            updateCheck2(index) {
                this.temp2.id=this.engine[index].id;
                this.temp2.simulatorId=this.engine[index].simulatorId;
                this.temp2.engineType=this.engine[index].engineType;
                this.temp2.dataRevisionNum=this.engine[index].dataRevisionNum;
                this.temp2.dataSource=this.engine[index].dataSource;


                this.check_form2 = this.temp2;
                this.engineindex = index;
                this.add_dialog_show2 = true;
            },
            submitCheckForm2() {
                let self = this;
                let url = "/eim/api";
                let checkUrl = url + "/engine";
                let is_update = false;
                if (self.check_form2.id) {
                    is_update = true;
                }
                self.$http.httpPost(checkUrl, self.check_form2).then(function (res) {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.check_form2 = {};
                        if (!is_update) {
                            self.engine.push(result.data);

                        }
                        else{
                            self.engine.splice(self.engineindex,1,self.temp2)

                            self.engine[self.engineindex] = result.data;


                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },



            removeCheck1(index) {
                let self = this;

                let url = "/eim/api";

                let checkUrl = url + "/course/" + this.course[index].id;
                self.$http.httpDelete(checkUrl, {}).then((res) => {
                    let result = res.data
                    if (result.resultCode == 200) {
                        self.course.splice(index, 1);
                        self.$Message.info("删除成功，id=" + result.data);
                    }
                });


            },
            updateCheck1(index) {
                this.temp1.id=this.course[index].id;
                this.temp1.simulatorId=this.course[index].simulatorId;
                this.temp1.courseNum=this.course[index].courseNum;
                this.temp1.courseType=this.course[index].courseType;
                this.temp1.courseName=this.course[index].courseName;


                this.check_form1 = this.temp1;
                this.courseindex = index;
                this.add_dialog_show1 = true;
            },
            submitCheckForm1() {
                let self = this;
                let url = "/eim/api";
                let checkUrl = url + "/course";
                let is_update = false;
                if (self.check_form1.id) {
                    is_update = true;
                }
                self.$http.httpPost(checkUrl, self.check_form1).then(function (res) {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.check_form1 = {};
                        if (!is_update) {
                            self.course.push(result.data);

                        }
                        else{
                            self.course.splice(self.courseindex,1,self.temp1)

                            self.course[self.courseindex] = result.data;


                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },


            removeCheck(index) {
                let self = this;

                let url = "/eim/api";

                let checkUrl = url + "/simulatorRecode/one/" + this.simulatorRecord[index].id;
                self.$http.httpDelete(checkUrl, {}).then((res) => {
                    let result = res.data
                    if (result.resultCode == 200) {
                        self.simulatorRecord.splice(index, 1);
                        self.$Message.info("删除成功，id=" + result.data);
                    }
                });


            },
            updateCheck(index) {
                this.temp.id=this.simulatorRecord[index].id;
                this.temp.simulatorId=this.simulatorRecord[index].simulatorId;
                this.temp.recordType=this.simulatorRecord[index].recordType;
                this.temp.identificationResult=this.simulatorRecord[index].identificationResult;
                this.temp.applicationLever=this.simulatorRecord[index].applicationLever+"";
                this.temp.identStandard=this.simulatorRecord[index].identStandard;
                this.temp.beginTime=this.simulatorRecord[index].beginTime;
                this.temp.endTime=this.simulatorRecord[index].endTime;
                this.temp.content=this.simulatorRecord[index].content;

                this.check_form = this.temp;

                this.simulatorRecordindex = index;
                this.add_dialog_show = true;
            },
            submitCheckForm() {

                this.$refs.checks.validate((valid) => {
                    if (valid) {

                            let self = this;
                            let url = "/eim/api";
                            let checkUrl = url + "/simulatorRecode";
                            let is_update = false;
                            if (self.check_form.id) {
                                is_update = true;
                            }

                            self.$http.httpPost(checkUrl, self.check_form).then(function (res) {
                                let result = res.data;
                                if (result.resultCode == 200) {

                                    self.check_form = {};
                                    if (!is_update) {
                                        self.simulatorRecord.push(result.data);

                                    }
                                    else{
                                        self.simulatorRecord.splice(self.simulatorRecordindex,1,self.temp)

                                        self.simulatorRecord[self.simulatorRecordindex] = result.data;


                                    }

                                    self.add_dialog_show = false;
                                }
                            }).catch(function (error) {
                                console.log(error);
                            });
                        }
                })
                    },
            getLevel() {
                if(this.simulate.equipmentType == '飞行模拟器') {
                    var data = [
                        {
                            value: 'A',
                            name: 'A'
                        },
                        {
                            value: 'B',
                            name: 'B'
                        },
                        {
                            value: 'C',
                            name: 'C'
                        },
                        {
                            value: 'D',
                            name: 'D'
                        }
                    ]
                    this.simulate.levelList = data;
                }
            },
            dataUpload : function(msg, rt) {
                let self = this;
                if (rt == 'url1') {
                    self.simulate.photos = msg;
                } else if (rt == 'url2') {
                    self.simulate.certificateFile = msg;
                }
            },
            // showupload: function () {
            //     this.isshow = false;
            //     this.isshowupload = true;
            // },
            // closeupload: function () {
            //     this.isshow = true;
            //     this.isshowupload = false;
            // },
            addSimulate: function () {

                let self = this;

                let request = {
                    simulator: self.simulate,
                    engine: self.engine,
                    course:self.course,
                    simulatorRecord:self.simulatorRecord,
                };

                self.$http.jsonPost('/eim/api/simulate',
                    request
                ).then(function (response) {
                    alert("保存成功");
                    self.$router.push({path: '/flight/simulateList'});
                })
                    .catch(function (error) {
                        console.log(error);
                        self.$emit('childshow', '1');
                    });


            },
            saveSimulate:function () {
                let self=this;
                this.$refs.simulateRef.validate((valid) => {
                    if (valid) {
                        if (self.isflg == 1) {
                            let buz = JSON.parse(Cookies.get("profile"));
                                self.$http.httpGet('/eim/api/simulate/search/findsimulatebycertificateNums', {
                                    id: self.simulate.id,
                                    certificateNum: self.simulate.certificateNum,
                                    companyId: buz.buz.companyId
                                }).then(function (response) {
                                    let result = response.data.data;
                                    if (result != null && result != '') {
                                        alert("模拟训练器已存在，不能重复添加");
                                    } else {
                                        self.addSimulate();
                                    }
                                })
                                    .catch(function (error) {
                                        console.log(error);
                                        self.$emit('childshow', '1');
                                    });

                        }else{
                            let buz = JSON.parse(Cookies.get("profile"));
                                self.$http.httpGet('/eim/api/simulate/search/findsimulatebycertificateNum', {
                                    certificateNum: self.simulate.certificateNum,
                                    companyId: buz.buz.companyId
                                }).then(function (response) {
                                    let result = response.data.data;
                                    if (result != null && result != '') {
                                        alert("模拟训练器已存在，不能重复添加");
                                    } else {
                                        self.addSimulate();
                                    }
                                })
                                    .catch(function (error) {
                                        console.log(error);
                                        self.$emit('childshow', '1');
                                    });

                        }
                    } else {
                        alert("表单验证失败");
                    }
                });

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