<template>
    <div class="content" style="margin-left:12px;" ref="personnal">
        <!--<Breadcrumb separator="/">-->
            <!--<BreadcrumbItem href="/">-->
                <!--<Icon type="android-apps" style="font-size:18px;"></Icon>-->
                <!--系统设置-->
            <!--</BreadcrumbItem>-->
            <!--<BreadcrumbItem>个人信息</BreadcrumbItem>-->
        <!--</Breadcrumb>-->
        <div class="contet">
            <!--<div class="contetchild">-->
            <div class="steps">
                <div class="stepsimg" style="width: 100%;height: 31px;">
                    <img v-bind:src="img1" style="width: 14%" v-on:click="shows('show1')">
                    <img v-bind:src="img2" style="width: 14%" v-on:click="shows('show2')">
                    <img v-bind:src="img3" style="width: 14%" v-on:click="shows('show3')">
                    <img v-bind:src="img4" style="width: 14%" v-on:click="shows('show4')">
                    <img v-bind:src="img5" style="width: 14%" v-on:click="shows('show5')">
                    <img v-bind:src="img6" style="width: 14%" v-on:click="shows('show6')">
                    <img v-bind:src="img7" style="width: 14%" v-on:click="shows('show7')">
                </div>
                <div class="stepschild">
                    <span ref="jichu" class="jichuclass" v-bind:style="styleObject1" style="
   font-size: 14px;
    display: inline-block;
 width: 100px;
    padding-left: 35px;
    color: rgb(21, 184, 252);">{{baseinfo}}<div style="color:red;font-size: 14px;display: inline-block;" v-if="empUpdate">*</div></span>
                    <span class="jichuclass" ref="quali" v-bind:style="styleObject" style="margin-left:70px;font-size: 14px ;">{{zhizhaoinfo}}</span>
                    <span class="jichuclass" ref="tjian" style="margin-left: 70px;font-size: 14px;">{{tijianinfo}}</span>
                    <span class="jichuclass" ref="kshi" style="margin-left: 73px;font-size: 14px;">{{kshiinfo}}</span>
                    <span class="jichuclass" ref="pxun" style="margin-left: 52px;font-size: 14px;">{{peixuninfo}}</span>
                    <span class="jichuclass" ref="zshu" style="margin-left: 76px;font-size: 14px;">{{zhshuinfo}}</span>
                    <span class="jichuclass" ref="tjsh" style="margin-left: 70px;font-size: 14px;" :class="">{{applyinfo}}</span>
                </div>

                <div style="background: #2e82dc;height: 36px;padding-top: 6px;margin: 56px 0px 3px 0px;padding: 6px 0 0 0;">
                    <img src="../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
                    <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">{{titleflag}}</span>
                </div>
            </div>
            <div title="填写资质信息" v-if="qualificationisshow"
                 style="">
                <licenselist :employeeId="employeeId" ></licenselist>
            </div>

            <div title="填写基础资料" id="bg" ref="bg" v-show="jichuxinxi" style=" color: rgba(1, 107, 217, 1);">
                <Form :label-width="110":model="employee" style="  width: 965px;border:1px solid rgb(46, 130, 220);background: rgba(193, 220, 248, 0.5);margin-top: 20px;margin-left: 8px" ref="formVali">
                    <div style="width:75%;display:inline-table;margin-left:15px;margin-bottom: 50px;" ref="baseinfobot">
                        <h2 >基本信息</h2>
                        <div class="bottom5" style="border:1px solid rgb(46, 130, 220);height:100%;">

                            <Row style="padding-right: 5px;margin-top: 20px;">
                                <Col :md="12">
                                <FormItem label="姓名" class="bottom5">
                                    <Input v-model="employee.userName" placeholder="请输入" readonly></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="性别" class="bottom5">
                                    <Select v-model="employee.gender" placeholder="请选择" >
                                        <Option value="男">男</Option>
                                        <Option value="女">女</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12" >
                                <FormItem label="国籍" class="bottom5" prop="nationality"  :rules="{required: true, message: '国籍不能为空', trigger: 'blur'}" >
                                    <Input v-model="employee.nationality" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="民族" class="bottom5">
                                    <Input v-model="employee.ethnicity" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">
                                <FormItem label="出生年月" prop="birthday"  class="bottom5" :rules="  { required: true, type: 'date', message: '请选择日期', trigger: 'change' }" >
                                    <DatePicker type="date" placeholder="选择日期" :editable="false" v-model="employee.birthday" ></DatePicker>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="身份证号" class="bottom5" prop="cardId"  :rules="[{required: true, message: '身份证号不能为空', trigger: 'blur'},{pattern:/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,message: '身份证号输入不合法', trigger: 'blur'}]">
                                    <Input v-model="employee.cardId" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">
                                <FormItem label="护照号码" class="bottom5" >
                                    <Input v-model="employee.passPort" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="教育程度" class="bottom5" prop="education" :rules="{required: true, message: '请选择教育程度', trigger: 'change'}" >
                                    <Select v-model="employee.education" placeholder="请选择" >
                                        <Option value="1">博士</Option>
                                        <Option value="2">研究生</Option>
                                        <Option value="3">本科</Option>
                                        <Option value="4">大专</Option>
                                        <Option value="5">大专以下</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">
                                <FormItem label="政治面貌" class="bottom5" prop="politicalStatus" :rules="{required: true, message: '请选择政治面貌', trigger: 'change'}" >
                                    <Select v-model="employee.politicalStatus" placeholder="请选择" >
                                        <Option value="1">党员</Option>
                                        <Option value="2">团员</Option>
                                        <Option value="3">群众</Option>
                                        <Option value="4">其他党派</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="入职时间" class="bottom5" prop="joinDate"  :rules="  { required: true, type: 'date', message: '请选择日期', trigger: 'change' }"  >
                                    <DatePicker type="date" placeholder="选择日期" v-model="employee.joinDate" ></DatePicker>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">
                                <FormItem label="部门" class="bottom5">
                                    <Input v-model="deptname" placeholder="请输入" readonly></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="岗位" class="bottom5">
                                    <Input v-model="employee.jobTitle" placeholder="请输入" readonly></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">
                                <FormItem label="工号" class="bottom5">
                                    <Input v-model="employee.workId" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="微信号" class="bottom5">
                                    <Input v-model="employee.wechatNo" placeholder="请输入" ></Input>
                                </FormItem>

                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">
                                <FormItem label="局方委任代表" class="bottom5">
                                    <Select v-model="employee.isagent" placeholder="请选择" >
                                        <Option value="是">是</Option>
                                        <Option value="否">否</Option>
                                    </Select>
                                </FormItem>
                                </Col>

                            </Row>
                        </div>
                        <h2 class="h2" v-if="employee.jobtitle==='飞行人员'||employee.jobtitle==='飞行学员'">扩展信息</h2>
                        <div class="bottom5" style="border:1px solid rgb(46, 130, 220);height:250px;" v-if="employee.jobtitle==='飞行人员'||employee.jobtitle==='飞行学员'">

                            <Row style="margin-top: 20px;">
                                <Col :md="12">
                                <FormItem label="可飞机型" class="bottom5">
                                    <Input v-model="pilot.aircraftType" placeholder="请输入"></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="所属大队" class="bottom5">
                                    <Input v-model="pilot.flightTeam" placeholder="请输入"></Input>
                                </FormItem>

                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="员工状态" class="bottom5">
                                    <Select v-model="employee.status" placeholder="请选择">
                                        <Option :value="0">全职</Option>
                                        <Option :value="1">兼职</Option>
                                        <Option :value="2">离职</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="汉语水平" class="bottom5">
                                    <Select v-model="pilot.chineselevel" placeholder="请选择" >
                                        <Option value="汉语语言4级">汉语语言4级</Option>
                                        <Option value="汉语语言5级">汉语语言5级</Option>
                                        <Option value="汉语语言6级">汉语语言6级</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="12">
                                <FormItem label="英语水平" class="bottom5">
                                    <Select v-model="pilot.englishLevel" placeholder="请选择" >
                                        <Option value="ICAO英语4级">ICAO英语4级</Option>
                                        <Option value="ICAO英语5级">ICAO英语5级</Option>
                                        <Option value="ICAO英语6级">ICAO英语6级</Option>
                                    </Select>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="通过时间" class="bottom5">
                                    <DatePicker type="date" placeholder="选择日期"
                                                v-model="pilot.englisPassDate"></DatePicker>
                                </FormItem>
                                </Col>
                            </Row>
                            <!--<Row>-->
                                <!--<Col :md="12">-->
                                <!--<FormItem label="汉语水平" class="bottom5">-->
                                    <!--<Select v-model="pilot.chineselevel" placeholder="请选择" >-->
                                        <!--<Option value="汉语语言4级">汉语语言4级</Option>-->
                                        <!--<Option value="汉语语言5级">汉语语言5级</Option>-->
                                        <!--<Option value="汉语语言6级">汉语语言6级</Option>-->
                                    <!--</Select>-->
                                <!--</FormItem>-->
                                <!--</Col>-->
                            <!--</Row>-->
                        </div>
                        <h2 >紧急联系人信息</h2>
                        <div class="bottom5" style="border:1px solid rgb(46, 130, 220);height:100%;">

                            <Row style="padding-right: 5px;margin-top: 20px;">
                                <Col :md="12">
                                <FormItem label="姓名" class="bottom5">
                                    <Input v-model="employee.homeName" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="邮编" class="bottom5">
                                    <Input v-model="employee.homePostcode" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">
                                <FormItem label="固定电话" class="bottom5" prop="homeTel" :rules="{pattern:/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/,message: '固定电话输入不合法', trigger: 'blur'}">
                                    <Input v-model="employee.homeTel" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="移动电话" class="bottom5" prop="homeMobile" :rules="{pattern:/^1(3|4|5|7|8)\d{9}$/,message: '移动电话输入不合法', trigger: 'blur'}">
                                    <Input v-model="employee.homeMobile" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">


                                <FormItem label="家庭所在国家" class="bottom5">
                                    <Input v-model="employee.homeCountry" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col :md="24">
                                <FormItem label="家庭地址" class="bottom5">
                                    <Input v-model="employee.homeAddress" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                            </Row>
                        </div>
                        <h2 >单位主管联系人信息</h2>
                        <div class="bottom5" style="border:1px solid rgb(46, 130, 220);height:100%;">

                            <Row style="padding-right: 5px; margin-top: 10px">
                                <Col :md="12">
                                <FormItem label="姓名" class="bottom5">
                                    <Input v-model="employee.businessName" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="单位所在国家" class="bottom5">
                                    <Input v-model="employee.businessCountry" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>

                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="12">
                                <FormItem label="邮编" class="bottom5">
                                    <Input v-model="employee.businessPostcode" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                                <Col :md="12">
                                <FormItem label="手机号码" class="bottom5"  prop="businessTel" :rules="{pattern:/^1(3|4|5|7|8)\d{9}$/,message: '工作电话输入不合法', trigger: 'blur'}">
                                    <Input v-model="employee.businessTel" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                            </Row>
                            <Row style="padding-right: 5px">
                                <Col :md="24">
                                <FormItem label="单位地址" class="bottom5">
                                    <Input v-model="employee.businessAddress" placeholder="请输入" ></Input>
                                </FormItem>
                                </Col>
                            </Row>
                        </div>

                        <div  class="bottom5" style="margin-top:20px;height:40px;" v-if="showedit">
                            <div class="gacompany-div"
                                 style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px;margin-left: 230px;">
                                <a data-v-942377fa="" href="javascript:;" @click="submitEmployeeInfo(employeeId)"
                                   class="gacompany-btn">保存</a>
                                <a data-v-942377fa="" href="javascript:;" @click="resetting"
                                   class="gacompany-btn">取消</a>
                            </div>
                        </div>
                    </div>
                    <FormItem style="display: none">
                        <Input    v-model="employee.employeeId" ></Input>
                    </FormItem>
                    <div style="width:20%;display:inline-table;margin-left:10px;padding:10px 0;">
                        <div class="bottom5" style="border:1px solid rgb(46, 130, 220);">
                            <h2>用户头像</h2>
                            <div class="image-list">
                                <div class="demo-upload-list-pliot" v-show="photoshow" style="height: 100%;">
                                    <div style="height: 100%">
                                        <img :src="employee.photo" alt="正在加载中.." style="width:100%;max-height:200px;margin: 0px auto;"></img>
                                    </div>
                                    <div class="demo-upload-list-cover-pliot">
                                        <Icon type="ios-compose-outline" @click.native="handleBtnClick('photoid')"></Icon>
                                        <Icon type="ios-eye-outline" @click.native="handleView('用户头像',employee.photo)"></Icon>
                                        <Icon type="ios-trash-outline" @click.native="handleremoveimg('photo')"></Icon>
                                    </div>
                                </div>
                                <Upload name="file"
                                        :on-success="handleSuccess"
                                        :before-upload="handleBeforeUpload"
                                        action="http://fs.glinkus.com:18081/upload">
                                    <div v-show="photoshow1" id="photoid" style="width: 110px;
    height: 150px;
">
                                        <img src="../../../../static/images/默认人像.png" style="margin-left: 26px; margin-top: 9px;">

                                    </div>
                                </Upload>
                            </div>
                            <div v-show="photoshow1" style="    margin-top: -18px;">
                                <h4 style="font-size: 0.7rem;text-align: center; color: white;" v-show="photoshow1">图片小于2M尺寸小于640*480</h4>
                            </div>
                        </div>


                        <div class="bottom5" style="border:1px solid rgb(46, 130, 220);margin-top: 35px;">
                            <h2>身份证正面</h2>
                            <div class="image-list">
                                <div class="demo-upload-list-pliot" v-show="firturlshow" style="height: 100%;">
                                    <div style="height: 100%">
                                        <img :src="scanCardlist.firturl" alt="正在加载中.." style="width:100%;max-height:200px;margin: 0px auto;"></img>
                                    </div>
                                    <div class="demo-upload-list-cover-pliot">
                                        <Icon type="ios-compose-outline" @click.native="handleBtnClick('firturlid')"></Icon>
                                        <Icon type="ios-eye-outline" @click.native="handleView('身份证正面',scanCardlist.firturl)"></Icon>
                                        <Icon type="ios-trash-outline" @click.native="handleremoveimg('firturl')"></Icon>
                                    </div>
                                </div>
                                <Upload name="file" :with-credentials="true"
                                        :on-success="handleSuccess1"
                                        :before-upload="handleBeforeUpload1"
                                        action="http://fs.glinkus.com:18081/upload">
                                    <div v-show="firturlshow1" id="firturlid" style="width: 148px; height: 97px; margin-top: 30px;">
                                        <img src="../../../assets/defalutupload.png" style="margin-top: 9px;">

                                    </div>
                                </Upload>
                            </div>
                            <div v-show="firturlshow1" style="    margin-top: -18px;">
                                <h4 style="font-size: 0.7rem;text-align: center; color: white;" v-show="firturlshow1">图片小于2M尺寸小于640*480</h4>
                            </div>
                        </div>
                        <div class="bottom5" style="border:1px solid rgb(46, 130, 220);margin-top: 35px;">
                            <h2>身份证反面</h2>
                            <div class="image-list">
                                <div class="demo-upload-list-pliot" v-show="lasturlshow" style="height: 100%;">
                                    <div style="height: 100%">
                                        <img :src="scanCardlist.lasturl" alt="正在加载中.." style="width:100%;max-height:200px;margin: 0px auto;"></img>
                                    </div>
                                    <div class="demo-upload-list-cover-pliot">
                                        <Icon type="ios-compose-outline" @click.native="handleBtnClick('lasturlid')"></Icon>
                                        <Icon type="ios-eye-outline" @click.native="handleView('身份证正面',scanCardlist.lasturl)"></Icon>
                                        <Icon type="ios-trash-outline" @click.native="handleremoveimg('lasturl')"></Icon>
                                    </div>
                                </div>
                                <Upload name="file" :with-credentials="true"
                                        :on-success="handleSuccess2"
                                        :before-upload="handleBeforeUpload2"
                                        action="http://fs.glinkus.com:18081/upload">
                                    <div v-show="lasturlshow1" id="lasturlid" style="width: 148px; height: 97px; margin-top: 30px;">
                                        <img src="../../../assets/defalutupload.png" style="margin-top: 9px;">

                                    </div>
                                </Upload>
                            </div>
                            <div v-show="lasturlshow1" style="    margin-top: -18px;">
                                <h4 style="font-size: 0.7rem;text-align: center; color: white;" v-show="lasturlshow1">图片小于2M尺寸小于640*480</h4>
                            </div>
                        </div>
                        <div class="bottom5" style="border:1px solid rgb(46, 130, 220);margin-top: 35px;">
                            <h2>个人签名</h2>
                            <div class="image-list">
                                <div class="demo-upload-list-pliot" v-show="scanSignatureshow" style="height: 100%;">
                                    <div style="height: 100%">
                                        <img :src="employee.scanSignature" alt="正在加载中.." style="width:100%;max-height:200px;margin: 0px auto;"></img>
                                    </div>
                                    <div class="demo-upload-list-cover-pliot">
                                        <Icon type="ios-compose-outline" @click.native="handleBtnClick('scanSignatureid')"></Icon>
                                        <Icon type="ios-eye-outline" @click.native="handleView('签名',employee.scanSignature)"></Icon>
                                        <Icon type="ios-trash-outline" @click.native="handleremoveimg('scanSignature')"></Icon>
                                    </div>
                                </div>
                                <Upload name="file" :with-credentials="true"
                                        :before-upload="handleBeforeUpload3"
                                        :on-success="handleSuccess3" :on-error="handleError"
                                        action="http://fs.glinkus.com:18081/upload">
                                    <div v-show="scanSignatureshow1" id="scanSignatureid" style="width: 148px; height: 97px; margin-top: 30px;">
                                        <img src="../../../assets/defalutupload.png" style="margin-top: 9px;">

                                    </div>
                                </Upload>
                            </div>
                            <div v-show="scanSignatureshow1" style="    margin-top: -18px;">
                                <h4 style="font-size: 0.7rem;text-align: center; color: white;" v-show="scanSignatureshow1">图片小于2M尺寸小于640*480</h4>
                            </div>
                        </div>
                    </div>
                </Form>
            </div>
            <div v-if="hideshodowshow" id="show" ref="hideshodow">
                <div style="margin-top: 1072px;width: 964px;">
                <div class="gacompany-div"
                     style="margin-left: 32px;float: left; text-align: center; padding-bottom: 10px;  text-align: center;width: 100%;">
                    <a data-v-942377fa="" id="btnclose" href="javascript:;" @click="hidediv"
                       class="gacompany-btn" >编辑</a>
                </div>
                </div>
            </div>
            <div title="填写体检信息" v-if="physicalrecordlist"
                 style="">
                <physicalrecordlist :employeeId="employeeId"></physicalrecordlist>
            </div>

            <div title="填写考试信息" v-if="personExamRecord" style="">
                <personExamRecord :employeeId="employeeId"></personExamRecord>

            </div>

            <div title="填写培训信息" v-if="trainInfo" style="">
                <trainInfo :employeeId="employeeId"></trainInfo>

            </div>

            <div title="填写培训证书信息" v-if="trainingcertificate" style="">
                <trainingcertificate :employeeId="employeeId"></trainingcertificate>
            </div>

            <div title="提交审核" v-if="applyauthor" style="margin-top: 15px">
                <applyauthor :employeeId="employeeId" ref="authorinfo"></applyauthor>
            </div>

            <!--<template slot="footer" scope="props">-->
            <!--<div class="wizard-footer-left">-->
            <!--<wizard-button @click.native="props.prevTab()" style="text-align:left;">-->
            <!--<Icon type="chevron-left" style="font-size:40px;color:#3498db"></Icon>-->
            <!--</wizard-button>-->
            <!--</div>-->
            <!--<div class="wizard-footer-right">-->
            <!--<wizard-button @click.native="props.nextTab()">-->
            <!--<Icon type="chevron-right" style="font-size:40px;color:#3498db"></Icon>-->
            <!--</wizard-button>-->
            <!--</div>-->
            <!--</template>-->


        </div>
        <Modal v-model="modelShow"
               :mask-closable="false"
               @on-ok="confirm"
               @on-cancel="cancel"
               width="300px" hight="200px" :styles="{top: '40%'}">
            <p slot="header" style="color:#e9e9e9;text-align:left;font-size: 15px;">
                <span>系统提示</span>
            </p>
            <p style="height: 100px; height: 80px;padding-top: 24px;text-align: center;margin-bottom: 2px; text-align: center;">
                信息未保存!</br>是否继续跳转....</p>
        </Modal>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>
</template>

<script>
    import licenselist from './licenseinlist.vue';
    import physicalrecordlist from './physicalrecordlist.vue';
    import personExamRecord from './personexamrecord.vue';
    import trainingcertificate from './trainingcertificate.vue';
    import trainInfo from './traininfo.vue';
    import applyauthor from './applyauthor';
    import imgdetail from '../../views/imgdetail.vue';
    export default {
        components: {
            licenselist,
            physicalrecordlist,
            personExamRecord,
            trainingcertificate,
            trainInfo,
            applyauthor,
            imgdetail,
        },
        data (){
            return {
                titleflag:'系统设置-个人信息',
                hideshodowshow:true,
                photoshow:false,//头像图片显示
                photoshow1:true,//头像默认图片
                firturlshow:false,//身份证真面
                firturlshow1:true,//身份证默认
                lasturlshow:false,//身份证反面
                lasturlshow1:true,
                scanSignatureshow:false,//签名
                scanSignatureshow1:true,
                initNum:0,//记录数据变化次数
                empUpdate:false,
                modelShow:false,
                showedit:false,
                linkedurl:'',
                readable:'',
                styleObject:{
                    color:''
                },
                styleObject1:{
                    color:''
                },
                pro:'',
                employeeId:'',
                employee: {
//                    employeeId:'',
//                    companyId:'',
//                    userId:'',
//                    workId:'',
//                    joinDate:'',
//                    cardId:'',
                },
                pilot: {},
                scanCardaray: [],
                scanCardlist: {
                    firturl: '',
                    lasturl: ''
                },
                img1: '',
                img2: '',
                img3: '',
                img4: '',
                img5: '',
                img6: '',
                img7: '',
                qualificationisshow: false,
                jichuxinxi: false,
                physicalrecordlist: false,
                personExamRecord: false,
                trainingcertificate:false,
                trainInfo:false,
                applyauthor:false,
                deptname:'',
                baseinfo:'',
                zhizhaoinfo:'',
                tijianinfo:'',
                kshiinfo:'',
                peixuninfo:'',
                zhshuinfo:'',
                applyinfo:'',
                ststus:''

            }
        },
        mounted(){
            let flag;
            if(this.$route.query.qualificationisshow){
                this.$profile.getProfile().then((profile) => {
                        if (profile.buz && profile.buz.employeeId) {
                            this.titleflag='系统设置-资质信息',
                            this.jichuxinxi = false;
                            this.hideshodowshow=false;
                            this.qualificationisshow = true;
                            this.physicalrecordlist = false;
                            this.personExamRecord = false;
                            this.trainInfo = false;
                            this.trainingcertificate=false;
                            this.applyauthor=false;
                            this.img1 = require('../../../assets/灰1.png');
                            this.img2 = require('../../../assets/2.png');
                            this.img3 = require('../../../assets/灰3.png');
                            this.img4 = require('../../../assets/灰4.png');
                            this.img5 = require('../../../assets/灰5.png');
                            this.img6 = require('../../../assets/灰6.png');
                            this.img7 = require('../../../assets/灰7.png');
                            this.styleObject.color='rgb(21, 184, 252)';
                            this.styleObject1.color='';
                            this.baseinfo = '基础资料';
                            this.zhizhaoinfo = '执照信息';
                            this.tijianinfo = '体检信息';
                            this.kshiinfo = '考试/检查信息';
                            this.peixuninfo = '培训信息';
                            this.zhshuinfo = '证书信息';
                            this.applyinfo = '提交审核';
                            flag=0;
                            this.employeeId = profile.buz.employeeId;


                        }
                        else{
                            alert("获取profile失败")
                        }
                    },
                    (error) => {
                        self.$Message.error('user profile error = ' + error);
                    });


            }
            else{
                flag=1;
            }
            this.$profile.getProfile().then((profile) => {
                    if (profile.buz && profile.buz.employeeId) {
                        this.employeeId = profile.buz.employeeId;
                        this.getPilotInfo(this.employeeId);

                    }
                    else{
                        alert("获取profile失败")
                    }
                },
                (error) => {
                    self.$Message.error('user profile error = ' + error);
                });
            this.gatdata(flag);



        },
        methods: {
            hidediv(){
                this.$refs.baseinfobot.style.margin="0px 0px 0px 15px";
                this.showedit=true;
                this.hideshodowshow = false;
                this.empUpdate = true;
            },

//            change(){
//                for(var i=0;i<this.$refs.personnal.getElementsByTagName('input').length;i++){
//                    this.$refs.personnal.getElementsByTagName('input')[i].removeAttribute("readonly");
//                }
//                alert('您可以开始编辑了!');
//            },
            handleView (title,name) {
                this.$refs.imgdetailref.initimgmodal(title,name);
            },
            handleremoveimg(imgtype)
            {
                let self = this;
                if(imgtype=='photo'){
                    self.employee.photo = '';
                    self.photoshow = false;
                    self.photoshow1 = true;
                }else if(imgtype=='firturl'){
                    self.scanCardlist.firturl='';
                    self.scanCardaray[0]='';
                   self.firturlshow=false;
                    self.firturlshow1 = true;
                }else if(imgtype=='lasturl'){
                    self.scanCardlist.lasturl='';
                    self.scanCardaray[1]='';
                    self.lasturlshow=false;
                    self.lasturlshow1 = true;
                }
            else if(imgtype=='scanSignature'){
                self.employee.scanSignature='';
                self.scanSignatureshow=false;
                self.scanSignatureshow1 = true;
            }
            },
            handleBtnClick: function(docid){
                if(docid=='photoid')
                {
                    document.getElementById("photoid").click();
                }else if('firturlid'==docid)
                {
                    document.getElementById("firturlid").click();
                }
                else if('lasturlid'==docid)
                {
                    document.getElementById("lasturlid").click();
                }
                else if('scanSignatureid'==docid)
                {
                    document.getElementById("scanSignatureid").click();
                }

            },
            confirm() {
                var url = this.linkedurl;
                this.empUpdate = false;
                if(url=='show1')
                {
                    this.chiladshow1();
                }else if(url == 'show2')
                {
                    this.chiladshow2();
                }else if(url == 'show3')
                {
                    this.chiladshow3();
                }else if(url == 'show4')
                {
                    this.chiladshow4();
                }else if(url == 'show5')
                {
                    this.chiladshow5();
                }else if(url == 'show6')
                {
                    this.chiladshow6();
                }else if(url == 'show7')
                {
                    this.chiladshow7();
                }
            },
            cancel()
            {
                //  this.islinked=false;
            },
            shows(url)
            {
                if(this.empUpdate)
                {
                    this.modelShow = true
                }else
                {
                    if(url=='show1')
                    {
                        this.chiladshow1();
                    }else if(url == 'show2')
                    {
                        this.chiladshow2();
                    }else if(url == 'show3')
                    {
                        this.chiladshow3();
                    }else if(url == 'show4')
                    {
                        this.chiladshow4();
                    }else if(url == 'show5')
                    {
                        this.chiladshow5();
                    }else if(url == 'show6')
                    {
                        this.chiladshow6();
                    }else if(url == 'show7')
                    {
                        this.chiladshow7();
                    }
                }
                this.linkedurl =url;

            },
            handleBeforeUpload () {
//                const check = this.uploadList.length < 1;
//                if (!check) {
//                    this.$Notice.warning({
//                        title: '最多只能上传 5 张图片。'
//                    });
//                }
                this.employee.photo=require( '../../../assets/imgupload.gif');
            },
            handleBeforeUpload1 () {
                this.scanCardlist.firturl=require( '../../../assets/imgupload.gif');
            },
            handleBeforeUpload2 () {
                this.scanCardlist.lasturl=require( '../../../assets/imgupload.gif');
            },
            handleBeforeUpload3 () {
                this.employee.scanSignature=require( '../../../assets/imgupload.gif');
            },
            resetting(){
                this.getPilotInfo(this.employeeId);
            },
            chiladshow2(){
                if(this.ststus===1){
                    alert("待审核状态不可修改");
                }else {
                    this.titleflag='系统设置-资质信息',
                    this.jichuxinxi = false;
                    this.hideshodowshow=false;
                    this.qualificationisshow = true;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.applyauthor = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = 'rgba(1, 107, 217, 1)';
                    this.$refs.jichu.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.$refs.tjsh.style.color = '';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
                    this.applyinfo = '提交审核';
                }
            },
            chiladshow1(){
                if(this.ststus===1){
                    alert("待审核状态不可修改");
                }else {
                    this.showedit=false;
                    this.$refs.baseinfobot.style.margin="0px 0px 50px 15px";
                    this.titleflag='系统设置-个人信息',
                    this.jichuxinxi = true;
                    this.hideshodowshow=true;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.applyauthor = false;
                    this.img1 = require('../../../assets/1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.$refs.tjsh.style.color = '';
                    this.$refs.jichu.style.color = 'rgba(1, 107, 217, 1)';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息x';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
                    this.applyinfo = '提交审核';
                }
            },
            chiladshow3(){
                if(this.ststus===1){
                    alert("待审核状态不可修改");
                }else {
                    this.titleflag='系统设置-体检信息',
                    this.jichuxinxi = false;
                    this.hideshodowshow=false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = true;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.applyauthor = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = 'rgba(1, 107, 217, 1)';
                    this.$refs.jichu.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.$refs.tjsh.style.color = '';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
                    this.applyinfo = '提交审核';
                }
            },
            chiladshow4(){
                if(this.ststus===1){
                    alert("待审核状态不可修改");
                }else {
                    this.titleflag='系统设置-考试信息',
                    this.jichuxinxi = false;
                    this.hideshodowshow=false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = true;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.applyauthor = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.jichu.style.color = '';
                    this.$refs.kshi.style.color = 'rgba(1, 107, 217, 1)';
                    this.$refs.pxun.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.$refs.tjsh.style.color = '';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
                    this.applyinfo = '提交审核';
                }
            },
            chiladshow5(){
                if(this.ststus===1){
                    alert("待审核状态不可修改");
                }else {
                    this.titleflag='系统设置-培训信息',
                    this.jichuxinxi = false;
                    this.hideshodowshow=false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = true;
                    this.trainingcertificate = false;
                    this.applyauthor = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.jichu.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.pxun.style.color = 'rgba(1, 107, 217, 1)';
                    this.$refs.zshu.style.color = '';
                    this.$refs.tjsh.style.color = '';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
                    this.applyinfo = '提交审核';
                }
            },
            chiladshow6(){
                if(this.ststus===1){
                    alert("待审核状态不可修改");
                }else {
                    this.titleflag='系统设置-证书信息',
                    this.jichuxinxi = false;
                    this.hideshodowshow=false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = true;
                    this.applyauthor = false;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/6.png');
                    this.img7 = require('../../../assets/灰7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.jichu.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.tjsh.style.color = '';
                    this.$refs.zshu.style.color = 'rgba(1, 107, 217, 1)';
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
                    this.applyinfo = '提交审核';
                }
            },chiladshow7(){
                this.titleflag='系统设置-提交审核',
                    this.jichuxinxi = false;
                this.hideshodowshow=false;
                    this.qualificationisshow = false;
                    this.physicalrecordlist = false;
                    this.personExamRecord = false;
                    this.trainInfo = false;
                    this.trainingcertificate = false;
                    this.applyauthor = true;
                    this.img1 = require('../../../assets/灰1.png');
                    this.img2 = require('../../../assets/灰2.png');
                    this.img3 = require('../../../assets/灰3.png');
                    this.img4 = require('../../../assets/灰4.png');
                    this.img5 = require('../../../assets/灰5.png');
                    this.img6 = require('../../../assets/灰6.png');
                    this.img7 = require('../../../assets/7.png');
                    this.$refs.quali.style.color = '';
                    this.$refs.tjian.style.color = '';
                    this.$refs.kshi.style.color = '';
                    this.$refs.jichu.style.color = '';
                    this.$refs.pxun.style.color = '';
                    this.$refs.zshu.style.color = '';
                    this.$refs.tjsh.style.color = 'rgba(1, 107, 217, 1)';
                    this.$refs.authorinfo.dataref();
                    this.baseinfo = '基础资料';
                    this.zhizhaoinfo = '执照信息';
                    this.tijianinfo = '体检信息';
                    this.kshiinfo = '考试/检查信息';
                    this.peixuninfo = '培训信息';
                    this.zhshuinfo = '证书信息';
                if(this.ststus===1){
                    this.applyinfo = '待审核';
                }


            },

            gatdata(val){

          if(val==1) {
              let self = this;
              self.$profile.getProfile().then((profile) => {
                  if (profile.buz.employeeId) {
                      self.$http.httpGet('/app/api/processrun/businesskeyrun', {
                          defId: 'TEST',
                          businessKey: profile.buz.employeeId
                      }).then(function (response) {
                          if (response.data.data) {
                              self.ststus = 1;
                              self.chiladshow7();
                          } else {
                              self.chiladshow1();
                          }
                      }).catch(function (error) {
                          console.log(error);
                      });
                  }
              }, (error) => {
                  self.$Message.error('user profile error = ' + error);
              });
          }
          else{
              return
          }
            },
            

            getPilotInfo(id){
                let self = this;
                let url = "/eim/api";
                self.empUpdate = false;
                let userId;
                            let infoUrl = url + "/pilot/search/" +id;
                            self.$http.httpGet(infoUrl, {}).then((res) => {
                                let result = res.data
                                if (result.resultCode == 200) {
                                    if (result.data.employee) {
                                        self.employee = result.data.employee;
                                         userId=self.employee.userId;
                                        this.$http.httpGet('/app/api/sysuser/search/byuserid', {
                                            //roleid:profile.id,
                                            userid:userId
                                        }).then((res) => {
                                            let orgId=res.data.data.orgId;
                                            this.$http.httpGet('/app/api/sysorg/search/byId', {
                                                //roleid:profile.id,
                                                orgId:orgId
                                            }).then((res) => {
                                                self.deptname=res.data.data.orgName;
                                            }).catch(function (error) {
                                                console.log(error);
                                            });

                                        }).catch(function (error) {
                                            console.log(error);
                                        });


                                        self.employeeId = self.employee.employeeId;
                                        if (result.data.employee.scanCard.length>0) {

                                            self.scanCardaray = result.data.employee.scanCard.split(",");
                                            console.log("self.scanCardaray",self.scanCardaray);
                                            self.scanCardlist.firturl = self.scanCardaray[0];
                                            self.scanCardlist.lasturl = self.scanCardaray[1];
                                            if(self.scanCardlist.firturl=='')
                                            {
                                                self.firturlshow1 = true;
                                                self.firturlshow = false;
                                            }else {
                                                self.firturlshow = true;
                                                self.firturlshow1 = false;
                                            }

                                            if(self.scanCardlist.lasturl =='')
                                            {
                                                self.lasturlshow1 = true;
                                                self.lasturlshow = false;
                                            }else {
                                                self.lasturlshow = true;
                                                self.lasturlshow1 = false;
                                            }
                                        }
                                    }
                                    //飞行员
                                    if (result.data.pilot) {
                                        self.pilot = result.data.pilot;
                                    }
                                }
                            });
                console.log("getinit=====",self.scanCardlist.firturl,self.scanCardlist.lasturl);
                if(self.employee.photo=='')
                {
                    self.photoshow=false;
                    self.photoshow1=true;
                }else {
                    self.photoshow1=false;
                    self.photoshow=true;
                }

                if(self.employee.scanSignature=='')
                {
                    self.scanSignatureshow1 = true;
                    self.scanSignatureshow = false;
                }else {
                    self.scanSignatureshow = true;
                    self.scanSignatureshow1 = false;
                }


            },
            submitEmployeeInfo(id){
                let self = this;
                let url = "/eim/api";

                self.$refs.formVali.validate((valid) => {
                    if(valid) {
                self.$profile.getProfile().then((profile) => {

                        let employeeUrl = url + "/employee/save";
                       if(profile.buz.employeeId){
                        self.employee.employeeId= profile.buz.employeeId;
                    }
                    if(self.employee.gender==null||self.employee.gender==''){
                           if(profile.user.sex=='0'){
                               self.employee.gender="男"
                           }
                           else{
                               self.employee.gender="女"
                           }
                    }
                    if(self.employee.jobtitle==="飞行人员"||self.employee.jobtitle==="飞行学员") {
                        self.employee.deptname = self.deptname;
                        self.employee.scanCard = self.scanCardaray.join(",");
                        self.$http.httpPost(employeeUrl, self.employee).then((res) => {
                            let result = res.data
                            if (result.resultCode == 200) {
//                                self.$Message.info('员工信息保存成功');
                                //更新保存飞行员信息
                                let employee = result.data;
                                self.pilot.employeeId = employee.employeeId;
                                let pilotUrl = url + "/pilot/save";
                                self.$http.httpPost(pilotUrl, self.pilot).then((res) => {
                                    let result = res.data;
                                    if (result.resultCode == 200) {
                                        self.pilot=result.data;
                                        alert('信息保存成功');
                                        self.hideshodowshow = true;
                                        self.$profile.getProfile(true).then((updatedProfile) => {
                                            console.log("updatedProfile", updatedProfile)
                                        })

                                    }
                                });
                            }
                            else {
                                self.$Message.info(result.message);
                            }

                            console.log("%%%%%")
                        }).catch(function (error) {
                            self.$Message.error(error);
                        });
                    }
                    else{
                        self.employee.deptname = self.deptname;
                        self.employee.scanCard = self.scanCardaray.join(",");
                        console.log(self.employee.scanCard);
                        self.$http.httpPost(employeeUrl, self.employee).then((res) => {
                            let result = res.data
                            if (result.resultCode == 200) {
                                alert('信息保存成功');
                                self.hideshodowshow = true;
                            }
                            else {
                                self.$Message.info(result.message);
                            }


                        }).catch(function (error) {
                            self.$Message.error(error);
                        });

                    }
                    self.empUpdate = false;
                }, (error) => {
                    self.$Message.error('user profile error = ' + error);
                });
                    }
                    else{
                        this.$Message.error('表单验证失败!');
                        return;
                    }
                })
            },
            handleSuccess (res, file) {
                //{"data":["/upload/avatar/0????.jpg"],"resultCode":200}
                if (res.resultCode == 200) {

                    this.employee.photo = res.url;
                    this.photoshow1=false;
                    this.photoshow=true;

                }
            },
            handleSuccess1 (res, file) {
                if (res.resultCode == 200) {
                    console.log(this.scanCardlist);

                    this.scanCardlist.firturl = res.url;

                    this.scanCardaray[0] = res.url;
                    this.firturlshow = true;
                    this.firturlshow1 = false;

                }
            },
            handleSuccess2 (res, file) {
                if (res.resultCode == 200) {

                    this.scanCardlist.lasturl = res.url;
                    this.scanCardaray[1] = res.url;
                    this.lasturlshow = true;
                    this.lasturlshow1 = false;

                }
            },
            handleSuccess3 (res, file) {
                //{"data":["/upload/avatar/0????.jpg"],"resultCode":200}
                if (res.resultCode == 200) {

                    this.employee.scanSignature = res.url;
                    this.scanSignatureshow = true;
                    this.scanSignatureshow1 = false;

                }
            },
            handleError (res, file) {
                console.log(res)
            }
        },
        watch: {
//            employee:{
//                handler(newValue, oldValue){
//                    this.initNum=this.initNum+1;
//                    if(this.initNum>2)
//                    {
//                        this.empUpdate = true;
//                    }else {
//                        this.empUpdate = false;
//                    }
//                    deep:true
//                }
//
//            }
        }
    }

</script>

<style scoped>
    #btnclose{
        position: relative;
        background-color:rgba(37,128,221,1);
        width: 107px;
        font-size: 18px;
    }
    #bg {
        /*display:none;*/

        /*width:100%;*/
        /*height:100%;*/
        /*!*background-color:black;*!*/
        /*z-index:1001;*/
        /*-moz-opacity:0.7;*/
        /*opacity:.70;*/
        /*filter:alpha(opacity=70);*/
    }
    #show {
        display:block;
        position:absolute;
        top:180px;
        left:0;
        width:100%;
        height: 1169px;
        /*background: rgba(0,0,0,0.5);*/
        z-index:100;

    }
    .h2
    {
        background-color: white;
        color: black;
        padding-left: 5px;
        width: 140px;
        border-radius: 3px;
        font-size: 14px;
    }
    .bottom5 {
        margin-bottom: 24px;
        padding-right: 5px;
    }

    .btn-list {
        margin: 10px 10px;
        display: inline-block;
    }

    .image-list {
        margin: 9px 13px;
        display: inline-block;
        width: 150px;
        text-align: center;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        position: relative;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
        /* margin-right: 10px; */
        height: 170px;
        /*margin-right: 10px;*/
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
        padding-top: 22px;
        width: 965px;
        height: 150px;
        vertical-align: middle;

    }

    .stepsimg > img {
        width: 25%;
        float: left;
    }

    .gacompany-btn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background-color:rgba(37,128,221,1);
    }

    .gacompany-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        /*border: 1px solid #ececec;*/
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
    .demo-upload-list-cover-pliot{
        display: none;
        position: absolute;
        top: 138px;
        bottom: 0;
        left: 0px;
        right: 0px;
        background: rgba(0,0,0,.6);
        /* width: 120px; */
        height: 30px;
        /* padding-left: 199px; */
        text-align: right;
        padding-right: 10px;
        padding-top: 5px;
        margin-bottom: 16px;
    }
    .demo-upload-list-pliot:hover .demo-upload-list-cover-pliot{
        display: block;
    }
    .demo-upload-list-cover-pliot i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
    .jichuclass{
        color: rgba(24, 24, 24, 1);
     }
</style>