<template >
    <div style="width: 89%; margin-left: 70px; ">
        <img src="/static/images/基础资料图标.png" style="position: absolute;margin-left: 57px;margin-top: 9px;"/>
        <div style="margin-left: 4%;margin-top: 5%;width: 792px;height:36px;background-color:rgb(46, 130, 220) ">
            <div style="padding-top: 5px;padding-left: 53px;color: rgb(255,255,255);font-size: 16px;">系统管理-飞行记录</div>
        </div>
        <!--<hr style="height: 3px; margin-left: 2.5%; background-color: aliceblue;">-->

        <div title="填写基础资料" style="padding-left:20px;padding-right:30px;margin-top: 17px;">
            <Form :label-width="145" style="margin:-14px 0px;width: 95%;" ref="formvali" :model="flightImplementRecord" :rules="rule">
                <div style="width:100%;display:inline-table;margin-left:15px;">
                    <div class="bottom5 alldiv-border" style="background-color: rgba(193,220,248,.5)">


                        <h2>基本信息</h2>
                        <Row >
                            <Col :md="22">
                            <FormItem label="企业名称" class="bottom5" >
                                <Input v-model="companyName" :readonly="true"></Input>
                            </FormItem>
                            </Col>

                        </Row>
                        <!--基础信息-->
                        <div class="title-border" style="font-size: 15px;"  >

                            <div style="text-align: -webkit-center;">架次信息</div>
                        </div>
                        <div class="cut container-center" v-show="open.base_info_open=true" style="margin-top: 17px">
                            <Row>
                                <Col :md="11">
                                <FormItem label="计划编号" class="bottom5">
                                    <Input v-model="flightImplementRecord.flyPlanId" placeholder="" ></Input>

                                </FormItem>

                                </Col>

                                <Col :md="11">
                                <FormItem label="飞行任务书编号" class="bottom5">
                                    <Input v-model="flightImplementRecord.flyTaskId" placeholder="" ></Input>
                                </FormItem>
                                </Col>

                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="飞行种类" class="bottom5" :required="true">
                                    <!--<Input v-model="flightImplementRecord.flyType" placeholder=""></Input>-->
                                    <Select v-model="flightImplementRecord.flyType" placeholder="请选择" >
                                        <Option value="1">一般商业飞行</Option>
                                        <Option value="2">农林喷洒飞行</Option>
                                        <Option value="3">训练飞行（私照、商照）</Option>
                                        <Option value="4">旋翼机外载荷</Option>
                                        <Option value="5">空中旅游</Option>
                                        <Option value="6">其他</Option>
                                    </Select>
                                </FormItem>
                                </Col>

                                <Col :md="11">
                                <FormItem label="飞行性质" class="bottom5">
                                    <Select v-model="flightImplementRecord.flightProperties" placeholder="请选择">
                                        <Option :value="item1.key" v-for="item1 in flightpropertiesitems">{{item1.value}}</Option>
                                    </Select>
                                </FormItem>
                                </Col>




                            </Row>
                            <Row>
                                <Col :md="22">
                                <FormItem label="飞行任务" class="bottom5" :required="true">
                                    <!--<Input v-model="flightImplementRecord.flyType" placeholder=""></Input>-->
                                    <Select  style="width: 12%" v-model="flyTaskType" placeholder="请选择" @on-change="getFlyTask(flyTaskType)">
                                        <Option v-for="item in items" :value="item.p" :key="item.p">{{ item.p }}</Option>
                                    </Select>&nbsp;&nbsp;

                                    <Select  style="width:  24%" v-model="flightImplementRecord.flyTask" placeholder="请选择" @on-change="selectFlyTask">
                                        <Option  v-for="item in citys" :value="item.n" :key="item.n">{{item.n}}</Option>
                                    </Select>&nbsp;&nbsp;
                                    <Input style="width:  60.5%" v-model="flyTaskOther" v-show="flyTaskOthershow" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>

                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="航空器来源" class="bottom5" :required="true">
                                    <Select v-model="flightImplementRecord.aircraftFrom" placeholder="请选择" @on-change="selectcraftfrom">
                                        <Option value="自有航空器">自有航空器</Option>
                                        <Option value="其他公司航空器">其他公司航空器</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="航空器所属公司" class="bottom5" :required="true">
                                    <Select
                                            :label="flightImplementRecord.aircraftCompanyName"
                                            v-model="flightImplementRecord.aircraftCompanyName"
                                            filterable
                                            remote
                                            :remote-method="selectaircraftcompanyName"
                                            ref="hehe"
                                            @on-change="aircraftlistall"
                                            placeholder="请输入航空器所属公司"
                                            :loading="loading4"
                                            style="">

                                        <Option v-for="(option, index) in optionsent3" :value="option.value"  :key="index">{{option.card}}</Option>
                                    </Select>
                                </FormItem>
                                </Col>

                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="航空器登记号" class="bottom5" :required="true">
                                    <!--<Input v-model="flightImplementRecord.aircraftId" placeholder="请输入ID" ></Input>-->
                                    <Select
                                            :label="aircraftName"
                                            v-model="flightImplementRecord.aircraftId"
                                            filterable
                                            remote
                                            :remote-method="selectaircraft"
                                            ref="hehe"
                                            @on-change="findaircraftCerNumber"
                                            placeholder="请输入航空器登记号"
                                            :loading="loading3"

                                            style=""><!--@on-change="changeCountairport(airport.airportId,index1)"-->

                                        <Option v-for="(option, index) in optionsent1" :value="option.value"  :key="index">{{option.card}}</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="航空器型号" class="bottom5" :required="true">
                                    <Input v-model="aircraftModelshow" :readonly="true" placeholder=""></Input>
                                </FormItem>
                                </Col>

                            </Row>
                            <Row>


                                <Col :md="11">
                                <FormItem label="日期" :required="true" class="bottom5">
                                    <DatePicker type="date" placeholder="选择日期"  v-model="flightImplementRecord.flyDate" @on-change="putdatetime"></DatePicker>
                                </FormItem>
                                </Col>

                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="机长/教员状态" class="bottom5" :required="true">
                                    <Select v-model="flightImplementRecord.captainState" placeholder="请选择" @on-change="selectcaptainstate">
                                        <Option value="在职员工">在职员工</Option>
                                        <Option value="兼职员工">兼职员工</Option>
                                        <Option value="离职员工">离职员工</Option>
                                        <Option value="其他">其他</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="机长/教员所属公司" class="bottom5">
                                  <!--  <Select v-model="flightImplementRecord.captainCompanyName" placeholder="请选择">
                                        <Option :value="item1.key" v-for="item1 in flightpropertiesitems">{{item1.value}}</Option>
                                    </Select>-->
                                    <Select
                                            :label="captainCompanyName"
                                            v-model="flightImplementRecord.captainCompanyId"
                                            filterable
                                            remote
                                            :remote-method="selectcaptaincompanyName"
                                            ref="hehe"
                                            @on-change="listallcaptain"
                                            placeholder="请输入机长/教员所属公司"
                                            :loading="loading5"
                                            style="">

                                        <Option v-for="(option, index) in optionsent5" :value="option.value"  :key="index">{{option.card}}</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="副驾驶/学员状态" class="bottom5">
                                    <Select v-model="flightImplementRecord.copilotState" placeholder="请选择" @on-change="selectcopilotstate">
                                        <Option value="在职员工">在职员工</Option>
                                        <Option value="兼职员工">兼职员工</Option>
                                        <Option value="离职员工">离职员工</Option>
                                        <Option value="其他">其他</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="副驾驶/学员所属公司" class="bottom5">
                                   <!-- <Select v-model="flightImplementRecord.copilotCompanyName" placeholder="请选择">
                                        <Option :value="item1.key" v-for="item1 in flightpropertiesitems">{{item1.value}}</Option>
                                    </Select>-->

                                    <Select
                                            :label="copilotCompanyName"
                                            v-model="flightImplementRecord.copilotCompanyId"
                                            filterable
                                            remote
                                            :remote-method="selectcopilotcompanyName"
                                            ref="hehe"
                                            @on-change="listallcopilot"
                                            placeholder="请输入副驾驶/学员所属公司"
                                            :loading="loading6"
                                            style="">

                                        <Option v-for="(option, index) in optionsent6" :value="option.value"  :key="index">{{option.card}}</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="机长/教员" class="bottom5">
                                    <!--<Input v-model="flightImplementRecord.captainId" placeholder="请输入" ></Input>-->
                                    <Select
                                            :label="captainName"
                                            v-model="flightImplementRecord.captainId"
                                            filterable
                                            remote
                                            :remote-method="entquery"
                                            ref="hehe"
                                            placeholder="请输入机长名称"
                                            :loading="loading1"
                                            style=""><!--@on-change="changeCountairport(airport.airportId,index1)"-->

                                        <Option v-for="(option, index) in optionsent" :value="option.value" :key="index">{{option.card}}</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="副驾驶/学员" class="bottom5">
                                    <!--<Input v-model="flightImplementRecord.copilotId" placeholder="请输入" ></Input>-->
                                    <Select
                                            :label="copilotName"
                                            v-model="flightImplementRecord.copilotId"
                                            filterable
                                            remote
                                            :remote-method="entquery2"
                                            ref="hehe"

                                            :loading="loading2"
                                            placeholder="请输入副驾驶名称"

                                            style=""><!--@on-change="changeCountairport(airport.airportId,index1)"-->
                                        <Option v-for="(option, index) in optionsent2"  :value="option.value" :key="index">{{option.card}}</Option>
                                    </Select>
                                </FormItem>
                                </Col>

                            </Row>

                           <!-- <Row>
                                <Col :md="11">
                                <FormItem label="随机机长" class="bottom5">
                                    <Input v-model="flightImplementRecord.randomCaptain" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="随机学员" class="bottom5">
                                    <Input v-model="flightImplementRecord.randomCopilot" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>


                            </Row>-->


                            <Row>
                                <Col :md="11">
                                <FormItem label="起飞站" class="bottom5">
                                    <Input v-model="flightImplementRecord.origin" placeholder="" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="降落站" class="bottom5">
                                    <Input v-model="flightImplementRecord.destination" placeholder=""></Input>
                                </FormItem>
                                </Col>


                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="起飞时间" class="bottom5" :required="true">
                                    <DatePicker type="datetime" format="yyyy-MM-dd HH:mm"  placeholder="选择日期" v-model="flightImplementRecord.takeOffTime" @on-change="datefoamat" @on-open-change="getsortieflytime"></DatePicker>
                                    <!--<TimePicker v-model="flightImplementRecord.takeOffTime" format="HH:mm:ss" placeholder="选择时间" @on-change="datefoamat" @on-open-change="getsortieflytime"></TimePicker>-->
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="降落时间" class="bottom5" :required="true">
                                    <DatePicker type="datetime" format="yyyy-MM-dd HH:mm"  placeholder="选择日期" v-model="flightImplementRecord.landTime" @on-change="datefoamatlandTime" @on-open-change="getsortieflytime"></DatePicker>
                                    <!--<TimePicker v-model="flightImplementRecord.landTime" format="HH:mm:ss" placeholder="选择时间" @on-change="datefoamatlandTime" @on-open-change="getsortieflytime"></TimePicker>-->

                                </FormItem>

                                </Col>

                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="本任务飞行时间" class="bottom5">
                                    <Input v-model="flightImplementRecord.sortieFlyTime" placeholder="" ></Input>
                                </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="本任务昼间降落架次" class="bottom5" prop="sortieDiurnalCount">
                                    <Input v-model="flightImplementRecord.sortieDiurnalCount" placeholder="" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="本任务夜间降落架次" class="bottom5" prop="sortieNightCount">
                                    <Input v-model="flightImplementRecord.sortieNightCount" placeholder=""></Input>
                                </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="本任务昼间飞行时间" class="bottom5">
                                    <Input v-model="flightImplementRecord.sortieDiurnalFlytime" placeholder="" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="本任务夜间飞行时间" class="bottom5">
                                    <Input v-model="flightImplementRecord.sortieNightFlytime" placeholder="" ></Input>
                                </FormItem>
                                </Col>
                            </Row>

                        </div>
                        <!--机长-->
                        <div class="title-border" style="font-size: 15px;width: 100%"  @click="open.base_info_open1 = !open.base_info_open1">
                            <span class="fr">
                                <Icon :type="open.base_info_open1? 'minus':'plus'"></Icon>
                            </span>
                            <div style="text-align: -webkit-center">机长/教员飞行经历</div>
                        </div>
                        <div class="cut container-center" v-show="open.base_info_open1" style="margin-top: 17px">
                            <Row>
                                <Col :md="11">
                                <FormItem label="昼间降落次数" class="bottom5" prop="diurnalCount" >
                                    <Input  v-model="flightImplementRecord.diurnalCount"  placeholder="请输入数字" @on-blur="countdiurnalCount"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="夜间降落次数" class="bottom5" prop="nightCount"  >
                                    <Input v-model="flightImplementRecord.nightCount"  placeholder="请输入数字" @on-blur="countnightCount"></Input>
                                </FormItem>
                                </Col>


                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="昼间飞行时间" class="bottom5" >
                                    <InputNumber class="hour-mins"  :min="0" v-model="diumalFlyTimeHour"  :precision="0" @on-change="allcountflytime"></InputNumber>     &nbsp;&nbsp;时
                                    <!--<Input v-model="flightImplementRecord.diumalFlyTime" placeholder="单位为小时"></Input>-->
                                    <!--<Input v-model="diumalFlyTimeHour" type="number" style="display: inline-block; width: 40%; margin-right: 10px" @on-blur="countdiumalflytime"></Input>时-->
                                    <!--<Input v-model="diumalFlyTimeMins" type="number" style="display: inline-block; width: 40%; margin-right: 6px" @on-blur="countdiumalflytime"></Input>分-->
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="diumalFlyTimeMins" :precision="0" @on-change="allcountflytime"></InputNumber> 分
                                </FormItem>
                                </Col>

                                <Col :md="11">
                                <FormItem label="夜间飞行时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="nightFlyTimeHour"  :precision="0" @on-change="allcountflytime"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="nightFlyTimeMins" :precision="0" @on-change="allcountflytime"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="11">

                                <FormItem label="机长飞行时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainFlyTimeHour" :readonly="true" :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" :readonly="true" v-model="captainFlyTimeMins" :precision="0"></InputNumber> 分

                                </FormItem>
                                </Col>

                                <Col :md="11">
                                <FormItem label="机长单飞时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainAloneFlyTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="captainAloneFlyTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="机长转场时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainTransitTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="captainTransitTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>


                                <Col :md="11">
                                <FormItem label="机长夜航时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainNightFlyTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="captainNightFlyTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col :md="22">
                                <FormItem label="机长仪表类型" class="bottom5">
                                    <CheckboxGroup v-model="instrumentlist">
                                        <Checkbox label="ILS">
                                            <span>ILS</span>
                                        </Checkbox>
                                        <Checkbox label="NDB">
                                            <span>NDB</span>
                                        </Checkbox>
                                        <Checkbox label="VOR">
                                            <span>VOR</span>
                                        </Checkbox>
                                        <Checkbox label="其他">
                                            <span>其他</span>
                                        </Checkbox>
                                    </CheckboxGroup>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="机长真实仪表时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="actualInstrumenTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="actualInstrumenTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="机长模拟仪表时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="simulateInstrumenTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="simulateInstrumenTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="机长特技飞行时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainStuntFlyTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="captainStuntFlyTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>


                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="监视时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainWatchTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="captainWatchTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="被监视时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainWatchedTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="captainWatchedTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="PF时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainPfTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="captainPfTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="PNF时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="captainPnfTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="captainPnfTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>

                        </div>


                        <!--副驾驶-->
                        <div class="title-border" style="font-size: 15px;width: 100%"  @click="open.base_info_open2 = !open.base_info_open2">
                            <span class="fr">
                                <Icon :type="open.base_info_open2? 'minus':'plus'"></Icon>
                            </span>
                            <div style="text-align: -webkit-center">副驾驶/学员飞行经历</div>
                        </div>
                        <div class="cut container-center" v-show="open.base_info_open2" style="margin-top: 17px">
                            <Row>
                                <Col :md="11">
                                <FormItem label="昼间降落次数" class="bottom5" prop="copilotDiurnalCount" >
                                    <Input v-model="flightImplementRecord.copilotDiurnalCount" placeholder="请输入数字" @on-blur="countdiurnalCount"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="夜间降落次数" class="bottom5" prop="copilotNightCount">
                                    <Input v-model="flightImplementRecord.copilotNightCount" placeholder="请输入数字" @on-blur="countnightCount"></Input>
                                </FormItem>
                                </Col>

                            </Row>



                            <Row>
                                <Col :md="11">
                                <FormItem label="昼间飞行时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotDiurnalFlyTimeHour"  :precision="0" @on-change="allcountflytime"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotDiurnalFlyTimeMins" :precision="0" @on-change="allcountflytime"></InputNumber> 分
                                </FormItem>
                                </Col>

                                <Col :md="11">
                                <FormItem label="夜间飞行时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotNightFlyTimeHour"  :precision="0" @on-change="allcountflytime"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotNightFlyTimeMins" :precision="0" @on-change="allcountflytime"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="飞行时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotFlyTimeHour" :readonly="true" :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" :readonly="true" v-model="copilotFlyTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>

                                <Col :md="11">
                                <FormItem label="被带飞时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="leadFlyTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="leadFlyTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="转场时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotTransitTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotTransitTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="被带飞转场时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="leadedTransitTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时

                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="leadedTransitTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>

                                <Col :md="11">
                                <FormItem label="特技时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotStuntFlyTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotStuntFlyTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>

                            </Row>
                            <Row>
                                <Col :md="22">
                                <FormItem label="仪表类型" class="bottom5">
                                    <CheckboxGroup v-model="copilotInstrumentTypeList">
                                        <Checkbox label="ILS">
                                            <span>ILS</span>
                                        </Checkbox>
                                        <Checkbox label="NDB">
                                            <span>NDB</span>
                                        </Checkbox>
                                        <Checkbox label="VOR">
                                            <span>VOR</span>
                                        </Checkbox>
                                        <Checkbox label="其他">
                                            <span>其他</span>
                                        </Checkbox>
                                    </CheckboxGroup>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="真实仪表时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotActualInstrumenTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotActualInstrumenTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="模拟仪表时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotSimulateInstrumenTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotSimulateInstrumenTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="监视时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotWatchTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotWatchTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="被监视时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotWatchedTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotWatchedTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="PF时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotPfTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotPfTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="PNF时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="copilotPnfTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="copilotPnfTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                            </Row>

                        </div>
                        <!--模拟训练记录-->
                        <div class="title-border" style="font-size: 15px;width: 100%"  @click="open.base_info_open3 = !open.base_info_open3">
                            <span class="fr">
                                <Icon :type="open.base_info_open3? 'minus':'plus'"></Icon>
                            </span>
                            <div style="text-align: -webkit-center">模拟训练记录</div>
                        </div>
                        <div class="cut container-center" v-show="open.base_info_open3" style="margin-top: 17px">
                            <Row>
                                <Col :md="11">
                                <FormItem label="模拟/训练器训练机构" class="bottom5">
                                    <Input v-model="flightImplementRecord.simulateOrg" placeholder=""></Input>
                                </FormItem>
                                </Col>

                            </Row>
                            <Row>
                                <Col :md="11">
                                <FormItem label="模拟器时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="simulatorTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="simulatorTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>
                                <Col :md="11">
                                <FormItem label="训练器时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="trainingTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="trainingTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>

                            </Row>

                            <Row>
                                <Col :md="11">
                                <FormItem label="教员时间" class="bottom5">
                                    <InputNumber class="hour-mins" :min="0" v-model="instructorTimeHour"  :precision="0"></InputNumber>     &nbsp;&nbsp;时
                                    <InputNumber class="hour-mins" :max="59" :min="0" v-model="instructorTimeMins" :precision="0"></InputNumber> 分
                                </FormItem>
                                </Col>


                            </Row>
                        </div>
                        <div class="title-border" style="font-size: 15px;width: 100%" >
                            <div style="text-align: -webkit-center">其他信息</div>
                        </div>
                        <div class="cut container-center" style="margin-top: 17px">
                        <Row>
                            <Col :md="11">
                            <FormItem label="教员/机长签字" class="bottom5">
                                <!--<DatePicker type="date" placeholder="选择日期" v-model="flightImplementRecord.instructorSignTime"></DatePicker>-->
                                <Input v-model="flightImplementRecord.instructorSign" placeholder="填写名称"></Input>
                            </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col :md="11">
                            <FormItem label="任务执行情况" class="bottom5">
                                <Input v-model="flightImplementRecord.taskInfo" type="textarea" :rows="4" placeholder="请输入"></Input>
                            </FormItem>
                            </Col>
                            <Col :md="11">
                            <FormItem label="备注" class="bottom5">
                                <Input v-model="flightImplementRecord.remark" type="textarea" :rows="4" placeholder="请输入"></Input>
                            </FormItem>
                            </Col>
                        </Row>
                        </div>
                    </div>
                    <div class="bottom5" style="margin-top:20px;height:40px;">
                        <div
                             style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px;margin-left: 21%;">
                            <a data-v-942377fa="" href="javascript:;" @click="submitInfo"
                               class="gacompany-btton" style="margin-left: 100px;margin-bottom: 50px">保存</a>
                            <a data-v-942377fa="" href="javascript:;" @click="backtonext"
                               class="gacompany-btton">返回</a>
                        </div>

                    </div>
                </div>
            </Form>

        </div>

    </div>

</template>


<script>

    import employeedata from '../../../data/employee/index'
    export default {
        components: {

        },
        data (){
            return {
                formattakeofftime:'',
                formatlandtime:'',
                open: {
                    base_info_open: false,
                    base_info_open1: false,
                    base_info_open2: false,
                    base_info_open3: false,

                },
                rule:{
                    diurnalCount:[{pattern:/^[0-9]*$/,message:'请输入正确数字（大于等于0的整数）',trigger:'blur'}],
                    nightCount:[{pattern:/^[0-9]*$/,message:'请输入数字（大于等于0的整数）',trigger:'blur'}],
                    copilotDiurnalCount:[{pattern:/^[0-9]*$/,message:'请输入数字（大于等于0的整数）',trigger:'blur'}],
                    copilotNightCount:[{pattern:/^[0-9]*$/,message:'请输入数字（大于等于0的整数）',trigger:'blur'}],
                    sortieDiurnalCount:[{pattern:/^[0-9]*$/,message:'请输入数字（大于等于0的整数）',trigger:'blur'}],
                    sortieNightCount:[{pattern:/^[0-9]*$/,message:'请输入数字（大于等于0的整数）',trigger:'blur'}],
                },
                loading1: false,
                loading2: false,
                loading3: false,

                //航空器型号
                aircraftModelshow:'',
                //飞行任务
                citys:[],

                //PNF时间
                copilotPnfTimeHour:'',
                copilotPnfTimeMins:'',

                //PF时间
                copilotPfTimeHour:'',
                copilotPfTimeMins:'',

                //副驾驶被监视时间
                copilotWatchedTimeHour:'',
                copilotWatchedTimeMins:'',

                //副驾驶监视时间
                copilotWatchTimeHour:'',
                copilotWatchTimeMins:'',

                //副驾驶模拟仪表时间
                copilotSimulateInstrumenTimeHour:'',
                copilotSimulateInstrumenTimeMins:'',

                //副驾驶真实仪表时间
                copilotActualInstrumenTimeHour:'',
                copilotActualInstrumenTimeMins:'',

                //副驾驶特技时间
                copilotStuntFlyTimeHour:'',
                copilotStuntFlyTimeMins:'',

                //被带飞转场时间
                leadedTransitTimeHour:'',
                leadedTransitTimeMins:'',

                //副驾驶夜晚飞行时间
                copilotNightFlyTimeHour:'',
                copilotNightFlyTimeMins:'',

                //副驾驶白天飞行时间
                copilotDiurnalFlyTimeHour:'',
                copilotDiurnalFlyTimeMins:'',

                //机长PNF时间
                captainPnfTimeHour:'',
                captainPnfTimeMins:'',

                //机长PF时间
                captainPfTimeHour:'',
                captainPfTimeMins:'',

                //机长被监视时间
                captainWatchedTimeHour:'',
                captainWatchedTimeMins:'',

                //机长监视时间
                captainWatchTimeHour:'',
                captainWatchTimeMins:'',

                //机长特技飞行时间
                captainStuntFlyTimeHour:'',
                captainStuntFlyTimeMins:'',

                //机长夜航时间
                captainNightFlyTimeHour:'',
                captainNightFlyTimeMins:'',

                //教员时间
                instructorTimeHour:'',
                instructorTimeMins:'',
                //训练器时间
                trainingTimeMins:'',
                trainingTimeHour:'',
                //模拟器时间
                simulatorTimeHour:'',
                simulatorTimeMins:'',
                //模拟仪表时间
                simulateInstrumenTimeMins:'',
                simulateInstrumenTimeHour:'',
                //真实转场时间
                actualInstrumenTimeHour:'',
                actualInstrumenTimeMins:'',
                //副驾驶转场时间
                copilotTransitTimeMins:'',
                copilotTransitTimeHour:'',
                //副驾驶飞行时间
                copilotFlyTimeHour:'',
                copilotFlyTimeMins:'',
                //带飞转场时间
                leadTransitTimeHour:'',
                leadTransitTimeMins:'',
                //带飞时间
                leadFlyTimeHour:'',
                leadFlyTimeMins:'',
                //机长转场事件
                captainTransitTimeMins:'',
                captainTransitTimeHour:'',
                //机长单飞时间
                captainAloneFlyTimeMins:'',
                captainAloneFlyTimeHour:'',
                //机长飞行时间
                captainFlyTimeMins:'',
                captainFlyTimeHour:'',
                //昼间飞行时间
                nightFlyTimeHour:'',
                nightFlyTimeMins:'',
                //夜晚飞行时间
                diumalFlyTimeHour:'',
                diumalFlyTimeMins:'',
                //仪表集合
                instrumentlist:[],
                copilotInstrumentTypeList:[],
                //飞行任务
                items:employeedata.flightrecordflytask,
                //飞行性质
                flightpropertiesitems:employeedata.flightproperties,

                //根据其查询飞行任务初始化是否存在
                findexitflyTask:'',

                captaindisabled:false,

                //飞行任务选择其他输入框隐藏
                flyTaskOthershow:false,
                //飞行任务选择其他时输入的信息
                flyTaskOther:'',
                captainName:'',
                captainCompanyName:'',
                copilotCompanyName:'',
                copilotName:'',
                aircraftName:'',
                card:'111',
                flightrecordId:'',
                companyId:'',
                companyName:'',
                aircraftList:[],
                captain:'',
                employeeList:[],
                employeeListcopilot:[],
                employeeListcaptain:[],
                captaincompanyNameList:[],
                copilotcompanyNameList:[],
                //航空器公司名的集合
                aircompanyNameList:[],
                takeOffTime:'',
                landTime:'',
                datetime:'',
                optionsent:[],
                optionsent1:[],
                optionsent2:[],
                optionsent3:[],
                optionsent5:[],
                optionsent6:[],
                flightImplementRecord:{
                    id:'',
                    companyId:'',
                    flyPlanId:'',
                    flyTaskId:'',
                    aircraftId:'',
                    flyDate:'',
                    captainId:'',
                    copilotId:'',
                    origin:'',
                    destination:'',
                    takeOffTime:'',
                    landTime:'',
                    flyType:'',
                    diurnalCount:'',
                    nightCount:'',
                    diumalFlyTime:'',
                    nightFlyTime:'',
                    captainFlyTime:'',
                    captainAloneFlyTime:'',
                    captainTransitTime:'',
                    leadFlyTime:'',
                    leadTransitTime:'',
                    copilotFlyTime:'',
                    copilotTransitTime:'',
                    instrumentType:'',
                    actualInstrumenTime:'',
                    simulateInstrumenTime:'',
                    simulateOrg:'',
                    simulatorTime:'',
                    trainingTime:'',
                    instructorTime:'',
                    instructorSign:'',
                    taskInfo:'',
                    remark:'',
                    flyTask:'',
                    aircraftCerNumber:'',
                    flightProperties:'',
                    randomCaptain:'',
                    randomCopilot:'',
                    sortieFlyTime:'',
                    sortieDiurnalCount:'',
                    sortieNightCount:'',
                    sortieDiurnalFlytime:'',
                    sortieNightFlytime:'',
                    captainNightFlyTime:'',
                    captainStuntFlyTime:'',
                    captainWatchTime:'',
                    captainWatchedTime:'',
                    captainPfTime:'',
                    captainPnfTime:'',
                    copilotDiurnalCount:'',
                    copilotNightCount:'',
                    copilotDiurnalFlyTime:'',
                    copilotNightFlyTime:'',
                    leadedTransitTime:'',
                    copilotStuntFlyTime:'',
                    copilotInstrumentType:'',
                    copilotActualInstrumenTime:'',
                    copilotSimulateInstrumenTime:'',
                    copilotWatchTime:'',
                    copilotWatchedTime:'',
                    copilotPfTime:'',
                    copilotPnfTime:'',
                    captainState:'在职员工',
                    copilotState:'',
                    aircraftFrom:'自有航空器',
                    aircraftCompanyName:'',
                    captainCompanyId:'',
                    copilotCompanyId:''

                }
            }
        },
        created() {
            this.flightrecordId =this.$route.query.flightrecordId;

            this.searchCompanyame();
//            this.listall();
//            this.listallcaptain();


        },
        mounted(){

        },
        methods: {
            selectcopilotstate(){
                let self=this;
                if(self.flightImplementRecord.copilotState!='在职员工'){
                    self.copilotCompanyName='';
                    self.flightImplementRecord.copilotCompanyId='';
                    self.$http.httpGet('/app/api/company/', {
                    }).then(function (response) {
                        console.log(response,'+++++++++++++++++++++++++++++');
                        self.copilotcompanyNameList= response.data;
                    }).catch(function (error) {
                        console.log(error);
                    });
                }
                if(self.flightImplementRecord.copilotState=='在职员工'){

                    self.copilotCompanyName=self.companyName;
                    self.flightImplementRecord.copilotCompanyId=self.flightImplementRecord.companyId;
                }
            },

            //选择机长教员状态发生变化时触发
            selectcaptainstate(){
                let self=this;
                if(self.flightImplementRecord.captainState!='在职员工'){
                    self.captainCompanyName='';
                    self.flightImplementRecord.captainCompanyId='';
                    self.$http.httpGet('/app/api/company/', {
                    }).then(function (response) {
                        console.log(response,'+++++++++++++++++++++++++++++');
                        self.captaincompanyNameList= response.data;
                    }).catch(function (error) {
                        console.log(error);
                    });
                }
                if(self.flightImplementRecord.captainState=='在职员工'){
                    self.captainCompanyName=self.companyName;
                    self.flightImplementRecord.captainCompanyId=self.flightImplementRecord.companyId;
                }
            },
            //选择航空器来源发生变化时触发
            selectcraftfrom(){
                let self=this;
                if(self.flightImplementRecord.aircraftFrom!='自有航空器'){
                    if(self.companyName==self.flightImplementRecord.aircraftCompanyName){
                        self.flightImplementRecord.aircraftCompanyName='';
                    }
                    self.$http.httpGet('/eim/api/aircraft/search/airbelongcompanyname', {
                    }).then(function (response) {
                        console.log(self.aircompanyNameList,"..............+++++++++++............");
                        self.aircompanyNameList= response.data.data;
                    }).catch(function (error) {
                        console.log(error);
                    });
                }
                if(self.flightImplementRecord.aircraftFrom=='自有航空器'){
                    self.flightImplementRecord.aircraftCompanyName=this.companyName;
                }
            },
            //改变日期触发
            putdatetime(val){
                this.flightImplementRecord.takeOffTime=val+" 07:30";
                this.formattakeofftime=val+" 07:30:00";
                this.flightImplementRecord.landTime=val+" 07:30";
            },

            //选择飞行任务其他触发的方法
            selectFlyTask(){
                let self=this;
                if(self.flightImplementRecord.flyTask=='其他'){
                    self.flyTaskOthershow=true;
                }
                else{
                    self.flyTaskOthershow=false;
                }
            },


            allcountflytime(){
              this.countnightflytime();
              this.countdiumalflytime();
            },

            countnightflytime(){
                let self=this;
                let hour1=self.copilotNightFlyTimeHour;
                let hour2=self.nightFlyTimeHour;
                let mins1=self.copilotNightFlyTimeMins;
                let mins2=self.nightFlyTimeMins;
                let hour3=self.copilotDiurnalFlyTimeHour;
                let mins3=self.copilotDiurnalFlyTimeMins;
                let hour4=self.diumalFlyTimeHour;
                let mins4=self.diumalFlyTimeMins;
                if(hour1==''){
                    hour1='0';
                }
                if(mins1==''){
                    mins1='0';
                }
                if(hour2==''){
                    hour2='0';
                }
                if(mins2==''){
                    mins2='0';
                }
                if(hour3==''){
                    hour3='0';
                }
                if(mins3==''){
                    mins3='0';
                }if(hour4==''){
                    hour4='0';
                }
                if(mins4==''){
                    mins4='0';
                }

                //
                let minss=parseInt(mins1)+parseInt(mins3);
                let hours=parseInt(hour1)+parseInt(hour3);
                if(minss>=60){
                    hours+=parseInt(minss/60);
                    minss=minss%60;
                }

                self.copilotFlyTimeHour=hours;
                self.copilotFlyTimeMins=minss;


                let minss1=parseInt(mins4)+parseInt(mins2);
                let hours1=parseInt(hour4)+parseInt(hour2);
                if(minss1>=60){
                    hours1+=parseInt(minss1/60);
                    minss1=minss1%60;
                }

                    self.captainFlyTimeHour=hours1;
                    self.captainFlyTimeMins=minss1;



                let mins=parseInt(mins1)+parseInt(mins2);
                let hour=parseInt(hour1)+parseInt(hour2);
                if(mins>=60){
                    hour+=parseInt(mins/60);
                    mins=mins%60;
                }
                if(hour==0 && mins==0){
                    self.flightImplementRecord.sortieNightFlytime='';
                    return;
                }
                if(hour==0){
                    self.flightImplementRecord.sortieNightFlytime=mins+"分";
                    return;
                }
                if(mins==0){
                    self.flightImplementRecord.sortieNightFlytime=hour+"小时";
                    return;
                }
                self.flightImplementRecord.sortieNightFlytime=hour+"小时"+mins+"分";



            },

            //本架次昼间飞行时间
            countdiumalflytime(){
                let self=this;
                let hour1=self.diumalFlyTimeHour;
                let hour2=self.copilotDiurnalFlyTimeHour;
                let mins1=self.diumalFlyTimeMins;
                let mins2=self.copilotDiurnalFlyTimeMins;
                let hour3=self.nightFlyTimeHour;
                let mins3=self.nightFlyTimeMins;
                let mins4=self.copilotNightFlyTimeMins;
                let hour4=self.copilotNightFlyTimeHour;
                if(hour1==''){
                    hour1='0';
                }
                if(mins1==''){
                    mins1='0';
                }
                if(hour2==''){
                    hour2='0';
                }
                if(mins2==''){
                    mins2='0';
                }
                if(hour3==''){
                    hour3='0';
                }
                if(mins3==''){
                    mins3='0';
                }
                if(hour4==''){
                    hour4='0';
                }
                if(mins4==''){
                    mins4='0';
                }

                //
                let minss=parseInt(mins1)+parseInt(mins3);
                let hours=parseInt(hour1)+parseInt(hour3);
                if(minss>=60){
                    hours+=parseInt(minss/60);
                    minss=minss%60;
                }

                self.captainFlyTimeHour=hours;
                self.captainFlyTimeMins=minss;

                //
                let minss1=parseInt(mins2)+parseInt(mins4);
                let hours1=parseInt(hour2)+parseInt(hour4);
                if(minss1>=60){
                    hours1+=parseInt(minss1/60);
                    minss1=minss1%60;
                }
                self.copilotFlyTimeHour=hours1;
                self.copilotFlyTimeMins=minss1;

                //
                let mins=parseInt(mins1)+parseInt(mins2);
                let hour=parseInt(hour1)+parseInt(hour2);
                if(mins>=60){
                    hour+=parseInt(mins/60);
                    mins=mins%60;
                }
                if(hour==0 && mins==0){
                    self.flightImplementRecord.sortieDiurnalFlytime='';
                    return;
                }
                if(hour==0){
                    self.flightImplementRecord.sortieDiurnalFlytime=mins+"分";
                    return;
                }
                if(mins==0){
                    self.flightImplementRecord.sortieDiurnalFlytime=hour+"小时";
                    return;
                }
                self.flightImplementRecord.sortieDiurnalFlytime=hour+"小时"+mins+"分";

            },

            countdiurnalCount(){
                let self=this;
                if(self.flightImplementRecord.diurnalCount=='' && self.flightImplementRecord.copilotDiurnalCount==''){
                    self.flightImplementRecord.sortieDiurnalCount='';
                    return;
                }
                if(self.flightImplementRecord.diurnalCount==''){
                    self.flightImplementRecord.sortieDiurnalCount= parseInt(self.flightImplementRecord.copilotDiurnalCount);
                    return;
                }
                if(self.flightImplementRecord.copilotDiurnalCount==''){
                    self.flightImplementRecord.sortieDiurnalCount=parseInt(self.flightImplementRecord.diurnalCount);
                    return;
                }
                self.flightImplementRecord.sortieDiurnalCount= parseInt(self.flightImplementRecord.diurnalCount)+parseInt(self.flightImplementRecord.copilotDiurnalCount);
            },
            countnightCount(){
                let self=this;
                if(self.flightImplementRecord.nightCount=='' && self.flightImplementRecord.copilotNightCount==''){
                    self.flightImplementRecord.sortieNightCount='';
                    return;
                }
                if(self.flightImplementRecord.nightCount==''){
                    self.flightImplementRecord.sortieNightCount= parseInt(self.flightImplementRecord.copilotNightCount);
                    return;
                }
                if(self.flightImplementRecord.copilotNightCount==''){
                    self.flightImplementRecord.sortieNightCount=parseInt(self.flightImplementRecord.nightCount);
                    return;
                }
                self.flightImplementRecord.sortieNightCount= parseInt(self.flightImplementRecord.nightCount)+parseInt(self.flightImplementRecord.copilotNightCount);

            },
            getFlyTask: function (prov) {
                this.citys = [];
                for (let i = 0; i < this.items.length; i++) {
                    if (this.items[i].p == prov) {
                        this.citys = this.items[i].c;
                        //设置默认值
                        this.flightImplementRecord.flyTask=this.items[i].c[0].n;
                    }

                }
                //此处不做判断，初始化时，会先加载初始化，再触发此方法，导致飞行任务呗覆盖为默认值
                if(this.findexitflyTask!=''){
                    this.flightImplementRecord.flyTask=this.findexitflyTask;
                    this.findexitflyTask='';
                }
            },

            findaircraftCerNumber(){
                let self=this;
                let id=self.flightImplementRecord.aircraftId;
                self.$http.httpGet('/eim/api/aircraft/' + id, {
                }).then(function (response) {
                    self.flightImplementRecord.aircraftCerNumber= response.data.registernumber;
                    self.aircraftModelshow=response.data.aircraftModel;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            copilotclick(){
                alert("hehe");
            },
            //添加初始化的公司名字查询
            searchCompanyame(){
                let self=this;
                self.$profile.getProfile().then((profile) => {
                    self.flightImplementRecord.companyId = profile.buz.companyId;
                    self.companyId=self.flightImplementRecord.companyId;
                    self.$http.httpGet('/app/api/company/' + self.companyId, {
                    }).then(function (response) {
                        console.log(response.data,'response.data............company');
                        let datainfo = response.data;
                        self.companyName= datainfo.name;
                        self.flightImplementRecord.aircraftCompanyName=self.companyName;
                        self.captainCompanyName=self.companyName;
                        self.flightImplementRecord.captainCompanyId=self.flightImplementRecord.companyId;
                        //如果为空
                        self.aircraftlistall();

                        self.searchByFlightRecord();

                    }).catch(function (error) {
                        console.log(error);
                    });
                });
            },

            //根据id加载信息
            searchByFlightRecord(){
                let flightrecordId= this.flightrecordId;
                let self=this;
                if(flightrecordId){
                    self.$http.httpGet('/eim/api/flightimplementrecord/findFlightImplementRecordById',{id:flightrecordId})
                        .then(function (response) {
                            console.log(response.data,'。。。。......flightrecordId........................');
                            self.flightImplementRecord = response.data;
                            self.formattakeofftime=self.flightImplementRecord.takeOffTime;


                            //加载飞行任务
                            let arrflytask=self.flightImplementRecord.flyTask.split("-");
                            self.flyTaskType=arrflytask[0];
                            self.flightImplementRecord.flyTask=arrflytask[1];
                            //用一个参数接收飞行任务的值
                            self.findexitflyTask=self.flightImplementRecord.flyTask;
                            if(self.flightImplementRecord.flyTask=='其他'){
                                if(arrflytask.length==2){
                                    self.flyTaskOthershow=true;
                                }
                                else{
                                    self.flyTaskOther=arrflytask[2];
                                    self.flyTaskOthershow=true;
                                }

                            }
                            self.instrumentlist=self.flightImplementRecord.instrumentType.split(',');
                            if(self.flightImplementRecord.copilotInstrumentType!=null){
                                self.copilotInstrumentTypeList=self.flightImplementRecord.copilotInstrumentType.split(',');
                            }
                            self.flightImplementRecord.instrumentType=self.flightImplementRecord.instrumentType+'';
                            self.flightImplementRecord.flyType=self.flightImplementRecord.flyType+'';


                            //初始化航空器
                            self.$http.httpGet('/eim/api/aircraft/'+self.flightImplementRecord.aircraftId,{})
                                .then(function (response) {
                                    console.log(response.data,'...........aircraft...............');
                                    self.aircraftName= response.data.registernumber;
//                                    self.aircraftName= '航空器登记号:'+response.data.registernumber+'  型号:'+response.data.aircraftModel;
                                }).catch(function (error) {
                                console.log(error);
                            });

                            //初始化机长
                            self.$http.httpGet('/eim/api/employee/'+self.flightImplementRecord.captainId,{})
                                .then(function (response) {
                                    console.log(response.data,'...........captainId...............');
                                    self.captainName= '姓名:'+response.data.data.userName+'  工号:'+response.data.data.workId;
                                }).catch(function (error) {
                                console.log(error);
                            });
                            //初始化副机长
                            self.$http.httpGet('/eim/api/employee/'+self.flightImplementRecord.copilotId,{})
                                .then(function (response) {
                                    self.copilotName= '姓名:'+response.data.data.userName+'  工号:'+response.data.data.workId;
                                }).catch(function (error) {
                                console.log(error);
                            });

                            //初始化机长所属公司
                            self.$http.httpGet('/app/api/company/'+self.flightImplementRecord.captainCompanyId,{})
                                .then(function (response) {
                                    self.captainCompanyName=response.data.name;
                                    self.flightImplementRecord.captainCompanyId=response.data.companyId;
                                }).catch(function (error) {
                                console.log(error);
                            });

                            //初始化副机长所属公司
                            self.$http.httpGet('/app/api/company/'+self.flightImplementRecord.copilotCompanyId,{})
                                .then(function (response) {
                                    console.log(response,'------------------------------');
                                    self.copilotCompanyName= response.data.name;
                                    self.flightImplementRecord.copilotCompanyId=response.data.companyId;
                                }).catch(function (error) {
                                console.log(error);
                            });

                            //记载时间
                            if(self.flightImplementRecord.diumalFlyTime!=null && self.flightImplementRecord.diumalFlyTime!=''){
                                let arr=self.flightImplementRecord.diumalFlyTime.split('-');
                                if(arr[0]!='0'){

                                    self.diumalFlyTimeHour=arr[0];
                                }
                                if(arr[1]!='0'){
                                    self.diumalFlyTimeMins=arr[1];
                                }
                            }
                            if(self.flightImplementRecord.nightFlyTime!=null && self.flightImplementRecord.nightFlyTime!=''){
                                let arr1=self.flightImplementRecord.nightFlyTime.split('-');
                                if(arr1[0]!='0'){
                                    self.nightFlyTimeHour=arr1[0];
                                }
                                if(arr1[1]!='0'){
                                    self.nightFlyTimeMins=arr1[1];
                                }
                            }
                            if(self.flightImplementRecord.captainFlyTime!=null && self.flightImplementRecord.captainFlyTime!=''){
                                let arr2=self.flightImplementRecord.captainFlyTime.split('-');
                                if(arr2[0]!='0'){
                                    self.captainFlyTimeHour=arr2[0];
                                }
                                if(arr2[1]!='0'){
                                    self.captainFlyTimeMins=arr2[1];
                                }
                            }
                            if(self.flightImplementRecord.captainAloneFlyTime!=null && self.flightImplementRecord.captainAloneFlyTime!=''){
                                let arr3=self.flightImplementRecord.captainAloneFlyTime.split('-');
                                if(arr3[0]!='0'){
                                    self.captainAloneFlyTimeHour=arr3[0];
                                }
                                if(arr3[1]!='0'){
                                    self.captainAloneFlyTimeMins=arr3[1];
                                }
                            }

                            if(self.flightImplementRecord.captainTransitTime!=null && self.flightImplementRecord.captainTransitTime!=''){
                                let arr4=self.flightImplementRecord.captainTransitTime.split('-');
                                if(arr4[0]!='0'){
                                    self.captainTransitTimeHour=arr4[0];
                                }
                                if(arr4[1]!='0'){
                                    self.captainTransitTimeMins=arr4[1];
                                }
                            }

                            if(self.flightImplementRecord.leadFlyTime!=null && self.flightImplementRecord.leadFlyTime!=''){
                                let arr5=self.flightImplementRecord.leadFlyTime.split('-');
                                if(arr5[0]!='0'){
                                    self.leadFlyTimeHour=arr5[0];
                                }
                                if(arr5[1]!='0'){
                                    self.leadFlyTimeMins=arr5[1];
                                }
                            }

                            if(self.flightImplementRecord.leadTransitTime!=null && self.flightImplementRecord.leadTransitTime!=''){
                                let arr6=self.flightImplementRecord.leadTransitTime.split('-');
                                if(arr6[0]!='0'){
                                    self.leadTransitTimeHour=arr6[0];
                                }
                                if(arr6[1]!='0'){
                                    self.leadTransitTimeMins=arr6[1];
                                }
                            }

                            if(self.flightImplementRecord.copilotFlyTime!=null && self.flightImplementRecord.copilotFlyTime!=''){
                                let arr7=self.flightImplementRecord.copilotFlyTime.split('-');
                                if(arr7[0]!='0'){
                                    self.copilotFlyTimeHour=arr7[0];
                                }
                                if(arr7[1]!='0'){
                                    self.copilotFlyTimeMins=arr7[1];
                                }

                            }

                            if(self.flightImplementRecord.copilotTransitTime!=null && self.flightImplementRecord.copilotTransitTime!=''){
                                let arr8=self.flightImplementRecord.copilotTransitTime.split('-');
                                if(arr8[0]!='0'){
                                    self.copilotTransitTimeHour=arr8[0];
                                }
                                if(arr8[1]!='0'){
                                    self.copilotTransitTimeMins=arr8[1];
                                }
                            }

                            if(self.flightImplementRecord.instructorTime!=null && self.flightImplementRecord.instructorTime!=''){
                                let arr9=self.flightImplementRecord.instructorTime.split('-');
                                if(arr9[0]!='0'){
                                    self.instructorTimeHour=arr9[0];
                                }
                                if(arr9[1]!='0'){
                                    self.instructorTimeMins=arr9[1];
                                }
                            }

                            if(self.flightImplementRecord.trainingTime!=null && self.flightImplementRecord.trainingTime!=''){
                                let arr10=self.flightImplementRecord.trainingTime.split('-');
                                if(arr10[0]!='0'){
                                    self.trainingTimeHour=arr10[0];
                                }
                                if(arr10[1]!='0'){
                                    self.trainingTimeMins=arr10[1];
                                }
                            }

                            if(self.flightImplementRecord.actualInstrumenTime!=null && self.flightImplementRecord.actualInstrumenTime!=''){
                                let arr11=self.flightImplementRecord.actualInstrumenTime.split('-');
                                if(arr11[0]!='0'){
                                    self.actualInstrumenTimeHour=arr11[0];
                                }
                                if(arr11[1]!='0'){
                                    self.actualInstrumenTimeMins=arr11[1];
                                }
                            }

                            if(self.flightImplementRecord.simulateInstrumenTime!=null && self.flightImplementRecord.simulateInstrumenTime!=''){
                                let arr12=self.flightImplementRecord.simulateInstrumenTime.split('-');
                                if(arr12[0]!='0'){
                                    self.simulateInstrumenTimeHour=arr12[0];
                                }
                                if(arr12[1]!='0'){
                                    self.simulateInstrumenTimeMins=arr12[1];
                                }
                            }

                            if(self.flightImplementRecord.simulatorTime!=null && self.flightImplementRecord.simulatorTime!=''){
                                let arr13=self.flightImplementRecord.simulatorTime.split('-');
                                if(arr13[0]!='0'){
                                    self.simulatorTimeHour=arr13[0];
                                }
                                if(arr13[1]!='0'){
                                    self.simulatorTimeMins=arr13[1];
                                }
                            }

                            if(self.flightImplementRecord.copilotPnfTime!=null && self.flightImplementRecord.copilotPnfTime!=''){
                                let arr35=self.flightImplementRecord.copilotPnfTime.split('-');
                                if(arr35[0]!='0'){
                                    self.copilotPnfTimeHour=arr35[0];
                                }
                                if(arr35[1]!='0'){
                                    self.copilotPnfTimeMins=arr35[1];
                                }
                            }

                            if(self.flightImplementRecord.copilotPfTime!=null && self.flightImplementRecord.copilotPfTime!=''){
                                let arr34=self.flightImplementRecord.copilotPfTime.split('-');
                                if(arr34[0]!='0'){
                                    self.copilotPfTimeHour=arr34[0];
                                }
                                if(arr34[1]!='0'){
                                    self.copilotPfTimeMins=arr34[1];
                                }
                            }

                            if(self.flightImplementRecord.copilotWatchedTime!=null && self.flightImplementRecord.copilotWatchedTime!=''){
                                let arr33=self.flightImplementRecord.copilotWatchedTime.split('-');
                                if(arr33[0]!='0'){
                                    self.copilotWatchedTimeHour=arr33[0];
                                }
                                if(arr33[1]!='0'){
                                    self.copilotWatchedTimeMins=arr33[1];
                                }
                            }

                            if(self.flightImplementRecord.copilotWatchTime!=null && self.flightImplementRecord.copilotWatchTime!=''){
                                let arr32=self.flightImplementRecord.copilotWatchTime.split('-');
                                if(arr32[0]!='0'){
                                    self.copilotWatchTimeHour=arr32[0];
                                }
                                if(arr32[1]!='0'){
                                    self.copilotWatchTimeMins=arr32[1];
                                }
                            }

                            if(self.flightImplementRecord.copilotSimulateInstrumenTime!=null && self.flightImplementRecord.copilotSimulateInstrumenTime!=''){
                                let arr31=self.flightImplementRecord.copilotSimulateInstrumenTime.split('-');
                                if(arr31[0]!='0'){
                                    self.copilotSimulateInstrumenTimeHour=arr31[0];
                                }
                                if(arr31[1]!='0'){
                                    self.copilotSimulateInstrumenTimeMins=arr31[1];
                                }
                            }

                            if(self.flightImplementRecord.copilotActualInstrumenTime!=null && self.flightImplementRecord.copilotActualInstrumenTime!=''){
                                let arr30=self.flightImplementRecord.copilotActualInstrumenTime.split('-');
                                if(arr30[0]!='0'){
                                    self.copilotActualInstrumenTimeHour=arr30[0];
                                }
                                if(arr30[1]!='0'){
                                    self.copilotActualInstrumenTimeMins=arr30[1];
                                }
                            }

                            if(self.flightImplementRecord.copilotStuntFlyTime!=null && self.flightImplementRecord.copilotStuntFlyTime!=''){
                                let arr29=self.flightImplementRecord.copilotStuntFlyTime.split('-');
                                if(arr29[0]!='0'){
                                    self.copilotStuntFlyTimeHour=arr29[0];
                                }
                                if(arr29[1]!='0'){
                                    self.copilotStuntFlyTimeMins=arr29[1];
                                }

                            }
                            if(self.flightImplementRecord.leadedTransitTime!=null && self.flightImplementRecord.leadedTransitTime!=''){
                                let arr28=self.flightImplementRecord.leadedTransitTime.split('-');
                                if(arr28[0]!='0'){
                                    self.leadedTransitTimeHour=arr28[0];
                                }
                                if(arr28[1]!='0'){
                                    self.leadedTransitTimeMins=arr28[1];
                                }

                            }

                            if(self.flightImplementRecord.copilotNightFlyTime!=null && self.flightImplementRecord.copilotNightFlyTime!=''){
                                let arr27=self.flightImplementRecord.copilotNightFlyTime.split('-');
                                if(arr27[0]!='0'){
                                    self.copilotNightFlyTimeHour=arr27[0];
                                }
                                if(arr27[1]!='0'){
                                    self.copilotNightFlyTimeMins=arr27[1];
                                }

                            }

                            if(self.flightImplementRecord.copilotDiurnalFlyTime!=null && self.flightImplementRecord.copilotDiurnalFlyTime!=''){
                                let arr26=self.flightImplementRecord.copilotDiurnalFlyTime.split('-');
                                if(arr26[0]!='0'){
                                    self.copilotDiurnalFlyTimeHour=arr26[0];
                                }
                                if(arr26[1]!='0'){
                                    self.copilotDiurnalFlyTimeMins=arr26[1];
                                }

                            }

                            if(self.flightImplementRecord.captainPnfTime!=null && self.flightImplementRecord.captainPnfTime!=''){
                                let arr25=self.flightImplementRecord.captainPnfTime.split('-');
                                if(arr25[0]!='0'){
                                    self.captainPnfTimeHour=arr25[0];
                                }
                                if(arr25[1]!='0'){
                                    self.captainPnfTimeMins=arr25[1];
                                }

                            }

                            if(self.flightImplementRecord.captainPfTime!=null && self.flightImplementRecord.captainPfTime!=''){
                                let arr24=self.flightImplementRecord.captainPfTime.split('-');
                                if(arr24[0]!='0'){
                                    self.captainPfTimeHour=arr24[0];
                                }
                                if(arr24[1]!='0'){
                                    self.captainPfTimeMins=arr24[1];
                                }

                            }
                            if(self.flightImplementRecord.captainWatchedTime!=null && self.flightImplementRecord.captainWatchedTime!=''){
                                //机长被监视时间
                                let arr23=self.flightImplementRecord.captainWatchedTime.split('-');
                                if(arr23[0]!='0'){
                                    self.captainWatchedTimeHour=arr23[0];
                                }
                                if(arr23[1]!='0'){
                                    self.captainWatchedTimeMins=arr23[1];
                                }
                            }
                            if(self.flightImplementRecord.captainWatchTime!=null && self.flightImplementRecord.captainWatchTime!=''){
                                //机长监视时间
                                let arr22=self.flightImplementRecord.captainWatchTime.split('-');
                                if(arr22[0]!='0'){
                                    self.captainWatchTimeHour=arr22[0];
                                }
                                if(arr22[1]!='0'){
                                    self.captainWatchTimeMins=arr22[1];
                                }

                            }

                            if(self.flightImplementRecord.captainStuntFlyTime!=null && self.flightImplementRecord.captainStuntFlyTime!=''){
                                let arr21=self.flightImplementRecord.captainStuntFlyTime.split('-');
                                if(arr21[0]!='0'){
                                    self.captainStuntFlyTimeHour=arr21[0];
                                }
                                if(arr21[1]!='0'){
                                    self.captainStuntFlyTimeMins=arr21[1];
                                }

                            }

                            if(self.flightImplementRecord.captainNightFlyTime!=null && self.flightImplementRecord.captainNightFlyTime!=''){
                                let arr20=self.flightImplementRecord.captainNightFlyTime.split('-');
                                if(arr20[0]!='0'){
                                    self.captainNightFlyTimeHour=arr20[0];
                                }
                                if(arr20[1]!='0'){
                                    self.captainNightFlyTimeMins=arr20[1];
                                }

                            }


                            //修改信息时的公司名字查询
                            self.$http.httpGet('/app/api/company/' + self.flightImplementRecord.companyId, {
                            }).then(function (response) {
                                console.log(response.data,'response.data............company');
                                let datainfo = response.data;
                                self.companyName= datainfo.name;


                            }).catch(function (error) {
                                console.log(error);
                            });

                        }).catch(function (error) {
                        console.log(error);
                    });
                }
            },
            //返回到上一个页面
            backtonext(){
                this.$router.push(
                    {
                        path: '/for_flight_records',
                        query: {}
                    });
            },
            //加载所有的航空器的信息
            aircraftlistall(){
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    let companyId = profile.buz.companyId;
                    let companyName=self.flightImplementRecord.aircraftCompanyName;
                    self.$http.httpGet('/eim/api/aircraft/search/searchAircraftsByCompanyName', {companyName: companyName})
                        .then(function (response) {
                            console.log(response.data, '。。。。。。。。。。。。0000000........................');
                            self.aircraftList = response.data;
                        }).catch(function (error) {
                        console.log(error);
                    });
                })
            },
            //加载所有的员工信息
            listall: function (val) {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    let companyId=profile.buz.companyId;
                    self.$http.httpGet('/eim/api/employee/search/findEmployeetoPilotListBycompanyId',

                        {companyId:companyId}
                    )
                        .then(function (response) {
                            console.log(response.data, 'response.date........................');
                            self.employeeList = response.data;

                        }).catch(function (error) {
                        console.log(error);
                    });
                })
            },
            listallcaptain: function (val) {
                let self=this;
                let companyId=val;
                self.$profile.getProfile().then((profile) => {
                    self.$http.httpGet('/eim/api/employee/search/findEmployeetoPilotListBycompanyId',

                        {companyId:companyId}
                    )
                        .then(function (response) {
                            console.log(response.data, 'response.date........................');
                            self.employeeListcaptain = response.data;

                        }).catch(function (error) {
                        console.log(error);
                    });
                })
            },
            listallcopilot: function (val) {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    let companyId=val;
                    self.$http.httpGet('/eim/api/employee/search/findEmployeetoPilotListBycompanyId',

                        {companyId:companyId}
                    )
                        .then(function (response) {
                            console.log(response.data, 'response.date........................');
                            self.employeeListcopilot = response.data;

                        }).catch(function (error) {
                        console.log(error);
                    });
                })
            },

            getsortieflytime(){
                let self=this;
                let takeOffTime=self.flightImplementRecord.takeOffTime;
                let landTime=self.flightImplementRecord.landTime;
                //本架次飞行时间
                if(landTime!='' && takeOffTime!=''){
                    let landAndTakeOff=landTime.valueOf()-takeOffTime.valueOf();
                    let day=parseInt(landAndTakeOff/ (1000 * 60 * 60 * 24));
                    let hour=parseInt((landAndTakeOff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    hour+=day*24;
                    let mins=parseInt((landAndTakeOff % (1000 * 60 * 60)) / (1000 * 60));
//                    let seds=parseInt((landAndTakeOff % (1000 * 60)) / 1000);
                    self.flightImplementRecord.sortieFlyTime=hour+'小时'+mins+'分钟';
                }
            },
            submitInfo() {

                let self = this;
                if(self.$route.query.copy){
                    self.$delete(self.flightImplementRecord,"id");
//                    alert(self.flightImplementRecord.flightrecordId);
                }
                let time;
                self.flightImplementRecord.copilotPnfTime=self.copilotPnfTimeHour+"-"+self.copilotPnfTimeMins;
                self.flightImplementRecord.copilotPfTime=self.copilotPfTimeHour+"-"+self.copilotPfTimeMins;
                self.flightImplementRecord.copilotWatchedTime=self.copilotWatchedTimeHour+"-"+self.copilotWatchedTimeMins;
                self.flightImplementRecord.copilotWatchTime=self.copilotWatchTimeHour+"-"+self.copilotWatchTimeMins;
                self.flightImplementRecord.copilotSimulateInstrumenTime=self.copilotSimulateInstrumenTimeHour+"-"+self.copilotSimulateInstrumenTimeMins;
                self.flightImplementRecord.copilotActualInstrumenTime=self.copilotActualInstrumenTimeHour+"-"+self.copilotActualInstrumenTimeMins;
                self.flightImplementRecord.copilotStuntFlyTime=self.copilotStuntFlyTimeHour+"-"+self.copilotStuntFlyTimeMins;
                self.flightImplementRecord.leadedTransitTime=self.leadedTransitTimeHour+"-"+self.leadedTransitTimeMins;
                self.flightImplementRecord.copilotNightFlyTime=self.copilotNightFlyTimeHour+"-"+self.copilotNightFlyTimeMins;
                self.flightImplementRecord.copilotDiurnalFlyTime=self.copilotDiurnalFlyTimeHour+"-"+self.copilotDiurnalFlyTimeMins;
                self.flightImplementRecord.captainPnfTime=self.captainPnfTimeHour+"-"+self.captainPnfTimeMins;
                self.flightImplementRecord.captainPfTime=self.captainPfTimeHour+"-"+self.captainPfTimeMins;
                self.flightImplementRecord.captainWatchedTime=self.captainWatchedTimeHour+"-"+self.captainWatchedTimeMins;
                self.flightImplementRecord.captainWatchTime=self.captainWatchTimeHour+"-"+self.captainWatchTimeMins;
                self.flightImplementRecord.captainStuntFlyTime=self.captainStuntFlyTimeHour+"-"+self.captainStuntFlyTimeMins;
                self.flightImplementRecord.captainNightFlyTime=self.captainNightFlyTimeHour+"-"+self.captainNightFlyTimeMins;
                self.flightImplementRecord.instructorTime=self.instructorTimeHour+"-"+self.instructorTimeMins;
                self.flightImplementRecord.trainingTime=self.trainingTimeHour+"-"+self.trainingTimeMins;
                self.flightImplementRecord.simulatorTime=self.simulatorTimeHour+"-"+self.simulatorTimeMins;
                self.flightImplementRecord.simulateInstrumenTime=self.simulateInstrumenTimeHour+"-"+self.simulateInstrumenTimeMins;
                self.flightImplementRecord.actualInstrumenTime=self.actualInstrumenTimeHour+"-"+self.actualInstrumenTimeMins;
                self.flightImplementRecord.copilotTransitTime=self.copilotTransitTimeHour+"-"+self.copilotTransitTimeMins;
                self.flightImplementRecord.copilotFlyTime=self.copilotFlyTimeHour+"-"+self.copilotFlyTimeMins;
                self.flightImplementRecord.leadTransitTime=self.leadTransitTimeHour+"-"+self.leadTransitTimeMins;
                self.flightImplementRecord.leadFlyTime=self.leadFlyTimeHour+"-"+self.leadFlyTimeMins;
                self.flightImplementRecord.captainTransitTime=self.captainTransitTimeHour+"-"+self.captainTransitTimeMins;
                self.flightImplementRecord.captainAloneFlyTime=self.captainAloneFlyTimeHour+"-"+self.captainAloneFlyTimeMins;
                self.flightImplementRecord.captainFlyTime=self.captainFlyTimeHour+"-"+self.captainFlyTimeMins;
                self.flightImplementRecord.nightFlyTime=self.nightFlyTimeHour+"-"+self.nightFlyTimeMins;
                self.flightImplementRecord.diumalFlyTime=self.diumalFlyTimeHour+"-"+self.diumalFlyTimeMins;
                //仪表类型
                self.flightImplementRecord.instrumentType=self.instrumentlist.join(',');
                self.flightImplementRecord.copilotInstrumentType=self.copilotInstrumentTypeList.join(',');


                let url = "/eim/api/flightimplementrecord/save";
                console.log(self.flightImplementRecord, '.................self.flightImplementRecord......');
                let aircraftId=self.flightImplementRecord.aircraftId;
                let flyDate=self.flightImplementRecord.flyDate;
                let captainId=self.flightImplementRecord.captainId;
                let copilotId=self.flightImplementRecord.copilotId;
                let takeOffTime=self.flightImplementRecord.takeOffTime;
                let landTime=self.flightImplementRecord.landTime;
                let flyType=self.flightImplementRecord.flyType;
                let flyTask=self.flightImplementRecord.flyTask;
                let aircraftCerNumber=self.flightImplementRecord.aircraftCerNumber;
                let aircraftCompanyName=self.flightImplementRecord.aircraftCompanyName;

                self.$refs.formvali.validate((valid) => {
                    if(valid) {
                        if((captainId == '' ||captainId ==0) && (copilotId=='' || copilotId==0)){
                            alert("机长副驾驶至少填写一个，请重新输入！");
                            return;
                        }
                        else if(captainId==copilotId){
                            alert("机长副驾驶不能为同一个人，请重新输入！");
                            return;
                        }
                        if (aircraftId == '' || flyDate == '' ||  aircraftCerNumber == '' || flyType == '' || flyTask == '' ||takeOffTime=='' ||landTime==''||aircraftCompanyName=='') {
                            alert("有*号必填项未填写，请重新输入！");
                            return;
                        }
                        if (takeOffTime != null && landTime != null) {
                            if (takeOffTime.valueOf() > landTime.valueOf()) {
                                alert('起飞日期要比降落日期小，请重新选择！');
                                return;
                            }
                        }
                        if(self.flyTaskOther==''){
                            self.flightImplementRecord.flyTask=self.flyTaskType+'-'+flyTask;
                        }
                        else{
                            self.flightImplementRecord.flyTask=self.flyTaskType+'-'+flyTask+'-'+self.flyTaskOther;

                        }
                        self.$http.httpGet('/eim/api/flightimplementrecord/findByRegisternumberAndTakeofftime',
                            {aircraftCerNumber:aircraftCerNumber, takeofftime: self.formattakeofftime}
                        ).then(function (response) {
                                 if(response.data>0){
                                     if(self.flightImplementRecord.id!=response.data) {
                                         alert("飞行记录已经存在，请重新录入！");
                                     }
                                     //修改的情况 也是存在的 但是必须id相同 才可以保存
                                     else{
                                         self.$http.jsonPost(url, self.flightImplementRecord).then(function (res) {
                                             alert("提交成功");
                                             self.$router.push(
                                                 {
                                                     path: '/for_flight_records',
                                                     query: {}
                                                 });
                                         }).catch(function (error) {
                                             console.log(error);
                                         });
                                     }
                                 }
                                 else{
                                     self.$http.jsonPost(url, self.flightImplementRecord).then(function (res) {
                                         alert("提交成功");
                                         self.$router.push(
                                             {
                                                 path: '/for_flight_records',
                                                 query: {}
                                             });
                                     }).catch(function (error) {
                                         console.log(error);
                                     });
                                 }
                            }).catch(function (error) {

                        });
                    }
                    else{
                        this.$Message.error('昼间，夜间降落次数只能为数字！');
                    }
                })

            },
            datefoamat(val) {
                this.flightImplementRecord.flyDate=val;
                this.formattakeofftime=val+":00";
                this.flightImplementRecord.takeofftime=val;
            },
            datefoamatlandTime(val) {
                this.formatlandtime=val;
                this.flightImplementRecord.landtime=val;
            },

            entquery: function (query) {
                if (query !== '') {
                    this.loading1 = true;
                    setTimeout(() => {
                        this.loading1 = false;
                        const list = this.employeeListcaptain.data.map(item => {
                            return {
                                value: item.employeeId,
                                label: item.userName,
                                card : '姓名:'+item.userName+'  工号:'+item.workId
                            };
                        });

                        this.optionsent = list.filter(item => item.label!=null && item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);
                    }, 200);
                } else {

                }
            },
            entquery2: function (query) {
                console.log(this.employeeListcopilot.data,'this.employeeList.data.......');
                if (query !== '') {
                    this.loading2 = true;
                    setTimeout(() => {
                        this.loading2 = false;
                        const list = this.employeeListcopilot.data.map(item => {
                            return {
                                value: item.employeeId,
                                label: item.userName,
                                card : '姓名:'+item.userName+'  工号:'+item.workId
                            };
                        });

                        this.optionsent2 = list.filter(item => item.label!=null && item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);
                    }, 200);
                } else {

                }
            },


            selectcopilotcompanyName: function (query) {
                if (query !== '') {
                    this.loading6 = true;
                    setTimeout(() => {
                        this.loading6 = false;
                        const list = this.copilotcompanyNameList.map(item => {
                            return {
                                value: item.companyId,
                                label: item.name,
                                card : item.name,
                            };
                        });

                        this.optionsent6 = list.filter(item => item.label!=null && item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);
                    }, 200);
                } else {

                }
            },
            selectcaptaincompanyName: function (query) {
                if (query !== '') {
                    this.loading5 = true;
                    setTimeout(() => {
                        this.loading5 = false;
                        const list = this.captaincompanyNameList.map(item => {
                            return {
                                value: item.companyId,
                                label: item.name,
                                card : item.name,
                            };
                        });

                        this.optionsent5 = list.filter(item => item.label!=null && item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);
                    }, 200);
                } else {

                }
            },

            selectaircraftcompanyName: function (query) {
                if (query !== '') {
                    this.loading4 = true;
                    setTimeout(() => {
                        this.loading4 = false;
                        const list = this.aircompanyNameList.map(item => {
                            return {
                                value: item,
                                label: item,
                                card : item,
                            };
                        });

                        this.optionsent3 = list.filter(item => item.label!=null && item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);
                    }, 200);
                } else {

                }
            },

            selectaircraft: function (query) {
                console.log(this.aircraftList.data,'......................this.aircraftList.data.......');
                if (query !== '') {
                    this.loading3 = true;
                    setTimeout(() => {
                        this.loading3 = false;
                        const list = this.aircraftList.data.map(item => {
                            return {
                                value: item.aircraftId,
                                label: item.registernumber,
                                card : item.registernumber,
//                                card : '航空器登记号:'+item.registernumber+'  型号:'+item.aircraftModel,
                            };

                        });

                        this.optionsent1 = list.filter(item => item.label!=null && item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);
                    }, 200);
//
                } else {

                }
            },

        },

        watch: {}
    }

</script>

<style scoped>
    .bottom5 {
        color:#657180;
        margin-bottom: 15px;

    }

    .btn-list {
        margin: 10px 10px;
        display: inline-block;
    }

    .image-list {
        margin: 10px 10px;
        display: inline-block;
        width: 150px;
        text-align: center;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        position: relative;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
        margin-right: 10px;
    }

    .image-list img {
        width: 100%;
        height: 100%;
    }

    .image-list-cover {
        display: none;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0, 0, 0, .6);
    }

    .image-list:hover .image-list-cover {
        display: block;
    }

    .image-list-cover i {
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }

    .ivu-steps .ivu-steps-head {
        background: #282828;
    }

    .steps {
        margin: auto;
        padding-top: 34px;
        width: 893px;
        height: 150px;
        vertical-align: middle;

    }

    .stepsimg > img {
        width: 25%;
        float: left;
    }

    .gacompany-btton {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: white;
        background-color: #257fdd;
    }


    .hour-mins{
        width:85px;

    }
    .alldiv-border{
        border: 1px solid rgba(1,107,217,.5);
    }
    .title-border{
        margin-top: 1px;
        border-bottom: 1px solid rgba(1,107,217,.5);
        border-top: 1px solid rgba(1,107,217,.5);
    }

</style>