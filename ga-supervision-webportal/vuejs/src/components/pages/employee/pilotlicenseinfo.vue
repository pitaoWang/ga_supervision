
<template>
    <div>
        <!--<h2>执照信息</h2>-->
        <!--<Row>
            <Col :md="12">-->
        <div>
            <FormItem label="切换执照类型" style="display: inline-block;width: 550px" prop="licenseclass" :rules="{required: true, message: '请选择执照编号', trigger: 'change'}">
                <Select v-model="license.licenseclass" placeholder="请选择">
                    <Option value="学生驾驶员" >学生驾驶员</Option>
                    <Option value="运动驾驶员">运动驾驶员</Option>
                    <Option value="私用驾驶员">私用驾驶员</Option>
                    <Option value="商用驾驶员">商用驾驶员</Option>
                    <Option value="多人制机组驾驶员">多人制机组驾驶员</Option>
                    <Option value="航线运输驾驶员">航线运输驾驶员</Option>
                </Select>
            </FormItem>
            <!--</Col>
            </Row>-->
        </div>
        <div  v-if="license.licenseclass=='学生驾驶员'" >

            <!--</Row>-->
        </div>
        <div  v-else-if="license.licenseclass=='运动驾驶员'" >
            <div>
                <!--<Row>
                    <Col :md="12">-->
                <FormItem label="是否是运动教员" style="display: inline-block;width: 550px" prop="issportspilotteacher" :rules="{required: true, message: '请选择是否运动教员', trigger: 'change'}">
                    <Select  v-model="license.issportspilotteacher"  placeholder="请选择">
                        <Option value="1">是</Option>
                        <Option value="0">否</Option>
                    </Select>
                </FormItem>
                <!--</Col>
                <Col :md="11">-->
                <FormItem label="航空器类别等级" style="display: inline-block;width: 550px" :required=true>
                    <Select v-model="license.sportsaircraftclass" placeholder="请选择">
                        <Option value="初级飞机">初级飞机</Option>
                        <Option value="自转旋翼机">自转旋翼机</Option>
                        <Option value="滑翔机">滑翔机</Option>
                        <Option value="自由气球">自由气球</Option>
                        <Option value="小型飞艇">小型飞艇</Option>
                    </Select>
                </FormItem>
                <!--   </Col>
               </Row>-->
            </div>
            <div>
                <!--<Row>
                    <Col :md="12">-->
                <FormItem label="航空器级别等级" style="display: inline-block;width: 550px" :required=true>
                    <Select v-model="license.sportsaircraftgrade" placeholder="请选择">
                        <Option value="初级飞机-陆地">初级飞机-陆地</Option>
                        <Option value="初级飞机-水上">初级飞机-水上</Option>
                    </Select>
                </FormItem>
                <!--   </Col>
               </Row>-->
            </div>
        </div>
        <div  v-else-if="license.licenseclass=='多人制机组驾驶员'" >
            <!--<Row>
                <Col :md="12">-->
            <div>
                <FormItem label="航空器类别等级" style="display: inline-block;width: 550px"  :required=true>
                    <Input v-model="license.multiaircraftclass" placeholder="请输入"></Input>
                </FormItem>
                <!--</Col>
                <Col :md="11">-->
                <FormItem label="航空器级别等级" style="display: inline-block;width: 550px" :required=true>
                    <Input v-model="license.multiaircraftgrade" placeholder="请输入"></Input>
                </FormItem>
                <!--  </Col>
              </Row>
              <Row>-->
            </div>
            <FormItem label="航空器型别等级" style="display: inline-block;width: 550px" :required=true>
                <CheckboxGroup v-model="multiaircrafttype">
                    <Checkbox label="1">
                        <span>5700千克以上的航空器</span>
                    </Checkbox>
                    <Checkbox label="2">
                        <span>3180千克以上的航空器</span>
                    </Checkbox>
                    <Checkbox label="3">
                        <span>涡轮喷气动力飞机</span>
                    </Checkbox>
                    <Checkbox label="4">
                        <span>其他航空器</span>
                    </Checkbox>
                </CheckboxGroup>
            </FormItem>
            <!--</Row>-->
        </div>
        <div  v-else >
            <!--<Row>
                <Col :md="12">-->
            <div>
                <FormItem label="是否是飞行教员" style="display: inline-block;width: 550px" prop="ispilotteacher" :rules="{required: true, message: '请选择是否飞行教员', trigger: 'change'}">
                    <Select  v-model="license.ispilotteacher"  placeholder="请选择">
                        <Option value="1">是</Option>
                        <Option value="0">否</Option>
                    </Select>
                </FormItem>
                <!--   </Col>
               </Row>-->
            </div>
            <div v-if="license.ispilotteacher==1">
                <!--<Row>
                    <Col :md="12">-->
                <div>
                    <FormItem label="教员仪表等级" style="display: inline-block;width: 550px" :required=true>
                        <Select v-model="license.teachermetergrade" placeholder="请选择">
                            <Option value="无">无</Option>
                            <Option value="仪表-飞机">仪表-飞机</Option>
                            <Option value="仪表-直升机">仪表-直升机</Option>
                            <Option value="仪表-飞艇">仪表-飞艇</Option>
                            <Option value="仪表-倾转旋翼机">仪表-倾转旋翼机</Option>
                        </Select>
                    </FormItem>
                    <!--</Col>
                    <Col :md="11">-->
                    <FormItem label="教员基础教员等级" style="display: inline-block;width: 550px" :required=true>
                        <Select v-model="license.teachergrade" placeholder="请选择">
                            <Option value="单发飞机">单发飞机</Option>
                            <Option value="多发飞机">多发飞机</Option>
                            <Option value="直升机">直升机</Option>
                            <Option value="飞艇">飞艇</Option>
                            <Option value="倾转旋翼机">倾转旋翼机</Option>
                        </Select>
                    </FormItem>
                    <!--   </Col>
                   </Row>-->
                </div>
                <div>
                    <FormItem label="教员航空器型别" style="display: inline-block;width: 550px" :required=true>
                        <CheckboxGroup v-model="teacheraircrafttype">
                            <Checkbox label="1">
                                <span>5700千克以上的航空器</span>
                            </Checkbox>
                            <Checkbox label="2">
                                <span>3180千克以上的航空器</span>
                            </Checkbox>
                            <Checkbox label="3">
                                <span>涡轮喷气动力飞机</span>
                            </Checkbox>
                            <Checkbox label="4">
                                <span>其他航空器</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </FormItem>
                </div>
            </div>
            <div v-if="license.ispilotteacher==0">
                <div>
                    <!--<Row>
                        <Col :md="12">-->
                    <FormItem label="仪表等级" style="display: inline-block;width: 550px" :required=true>
                        <Select v-model="license.metergrade" placeholder="请选择">
                            <Option value="无">无</Option>
                            <Option value="仪表-飞机">仪表-飞机</Option>
                            <Option value="仪表-直升机">仪表-直升机</Option>
                            <Option value="仪表-飞艇">仪表-飞艇</Option>
                            <Option value="仪表-倾转旋翼机">仪表-倾转旋翼机</Option>
                        </Select>
                    </FormItem>
                    <!--    </Col>
                    </Row>-->
                    <FormItem label="驾驶员航空器类别" style="display: inline-block;width: 550px" :required=true>
                        <CheckboxGroup v-model="aircraftClass">
                            <Checkbox label="1">
                                <span>飞机</span>
                            </Checkbox>
                            <Checkbox label="2">
                                <span>直升机</span>
                            </Checkbox>
                            <Checkbox label="3">
                                <span>飞艇</span>
                            </Checkbox>
                            <Checkbox label="4">
                                <span>倾转旋翼机</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </FormItem>
                </div>
                <div>
                    <FormItem label="驾驶员航空器型别" style="display: inline-block;width: 550px" :required=true>
                        <CheckboxGroup v-model="aircraftType">
                            <Checkbox label="1">
                                <span>5700千克以上的航空器</span>
                            </Checkbox>
                            <Checkbox label="2">
                                <span>3180千克以上的航空器</span>
                            </Checkbox>
                            <Checkbox label="3">
                                <span>涡轮喷气动力飞机</span>
                            </Checkbox>
                            <Checkbox label="4">
                                <span>其他航空器</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </FormItem>


                    <FormItem label="驾驶员航空器级别" style="display: inline-block;width: 550px" :required=true>
                        <CheckboxGroup v-model="aircraftgrade">
                            <Checkbox label="1">
                                <span>单发陆地</span>
                            </Checkbox>
                            <Checkbox label="2">
                                <span>多发陆地</span>
                            </Checkbox>
                            <Checkbox label="3">
                                <span>单发水上</span>
                            </Checkbox>
                            <Checkbox label="4">
                                <span>单发水上</span>
                            </Checkbox>
                            <Checkbox label="5">
                                <span>多发水上</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </FormItem>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        data (){
            return {
                license: {
                    id:'',
                    archivesno:'',
                    property:'',
                    issuedept:'',
                    licenseclass:'学生驾驶员',
                    aircraftType:'',
                    aircraftgrade:'',
                    aircraftClass:'',
                    metergrade:'',
                    ispilotteacher:'',
                    teachergrade:'',
                    teachermetergrade:'',
                    teacheraircrafttype:'',
                    multiaircraftclass:'',
                    multiaircraftgrade:'',
                    multiaircrafttype:'',
                    issportspilotteacher:'',
                    sportsaircraftclass:'',
                    sportsaircraftgrade:''
                },
                multiaircrafttype:[],
                teacheraircrafttype:[],
                aircraftClass:[],
                aircraftType:[],
                aircraftgrade:[]
            }
        },

        watch: {
            multiaircrafttype:function (newval) {
                let self = this;
                self.license.multiaircrafttype=newval.join(",");
            },
            teacheraircrafttype:function (newval) {
                let self = this;
                self.license.teacheraircrafttype=newval.join(",");
            },
            aircraftClass:function (newval) {
                let self = this;
                self.license.aircraftClass=newval.join(",");
            }, aircraftType:function (newval) {
                let self = this;
                self.license.aircraftType=newval.join(",");
            }, aircraftgrade:function (newval) {
                let self = this;
                self.license.aircraftgrade=newval.join(",");
            }
        },
    }
</script>