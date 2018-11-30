<style scoped>

    .flybase-submit-user a:hover{
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }

    .modal.in .modal-dialog {
        -webkit-transform: translate(0,0);
        -ms-transform: translate(0,0);
        -o-transform: translate(0,0);
        transform: translate(0,0);
    }
    .modal.fade .modal-dialog {
        -webkit-transition: -webkit-transform .3s ease-out;
        -o-transition: -o-transform .3s ease-out;
        transition: transform .3s ease-out;
        -webkit-transform: translate(0,-25%);
        -ms-transform: translate(0,-25%);
        -o-transform: translate(0,-25%);
        transform: translate(0,-25%);
    }
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
</style>
<template>
    <div style="width: 1280px; height:auto; ;margin-left: 20px; background:white">
        <span style="width:100%; display:inline-block;text-align:right;margin-top:10px;margin-bottom: 10px; ">
                    <Button primary @click="clear()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

         </span>
        <Tabs v-model="value" style="border: 1px solid #f0f0f0;  ">
            <TabPane label="员工基本信息" name="name1">

                <div style="width: 98%;  margin-top: 10px; margin-left:10px;margin-right:10px; display: inline-block;background: white">
                    <table style="width:100%;">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-3">姓名</td>
                            <td class="tr-2 tr-4">{{employee.userName}}</td>
                            <td class="tr-1 tr-3">性别</td>
                            <td class="tr-2 tr-4">{{employee.gender}}</td>
                            <td rowspan="3"  style="width:180px;padding-left: 20px">
                                <div  v-if="employee.photo">
                                    <img :src="this.imageUrl+employee.photo+'.thumb.jpg'" style="width:140px;height: 135px;padding-left:40px;"  @click="imgdetail('',employee.photo)">
                                </div>
                                <div  v-else>
                                    <img src="../../../../../static/images/默认人像.png" style="width:140px;height: 135px;padding-left:40px;">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="">局方委任代表</td>
                            <td  class="tr-2 tr-4" style="">{{employee.isagent}}</td>
                            <td class="tr-1 tr-3">出生年月</td>
                            <td class="tr-2 tr-4">{{employee.birthday}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">国籍</td>
                            <td class="tr-2 tr-4">{{employee.nationality}}</td>
                            <td class="tr-1 tr-3">民族</td>
                            <td class="tr-2 tr-4">{{employee.ethnicity}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">教育程度</td>
                            <td class="tr-2 tr-4">{{employee.education}}</td>

                            <td class="tr-1 tr-3">毕业院校</td>
                            <td class="tr-2 tr-4">{{employee.school}}</td>
                            <td rowspan="3"  style="width:180px;height:150px;padding:0 10px 0 22px">
                                <div  v-if="employee.scanCard" style="width: 180px;height: 135px;margin-left: 0px">
                                    <img :src="this.imageUrl+employee.scanCard+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail('',employee.scanCard)">
                                </div>
                                <div v-else>
                                    <img :src="noimg" style="width: 180px;height: 135px;padding-left:15px;"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">所属企业</td>
                            <td class="tr-2 tr-4">{{employee.companyName}}</td>
                            <td class="tr-1 tr-3">身份证号码</td>
                            <td class="tr-2 tr-4">{{employee.cardId}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">所属地区管理局</td>
                            <td class="tr-2 tr-4">{{employee.authorityName}}</td>
                            <td class="tr-1 tr-3">所属监管局</td>
                            <td class="tr-2 tr-4">{{employee.supervisionName}}</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">职位</td>
                            <td class="tr-2 tr-4">{{employee.techpos}}</td>
                            <td class="tr-1 tr-3">出勤状态</td>
                            <td class="tr-2 tr-4">{{employee.turnOut}}</td>
                            <td rowspan="3"  style="width:180px;height:150px;padding:0 10px 0 22px;border-bottom: solid 0px #EEF1F7;">
                                <div  v-if="employee.backScancard" style="width: 180px;height: 135px;margin-left: 0px">
                                    <img :src="this.imageUrl+employee.backScancard+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail('',employee.backScancard)">
                                </div>
                                <div v-else>
                                    <img :src="noimg" style="width: 180px;height: 135px;padding-left:15px;"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3">所属运行基地</td>
                            <td class="tr-2 tr-4">{{employee.baseName}}</td>
                            <td class="tr-1 tr-3">合同状态</td>
                            <td class="tr-2 tr-4">{{employee.contract}}</td>

                        </tr>

                        <tr>
                            <td class="tr-1 tr-3">邮政编号</td>
                            <td class="tr-2 tr-4">{{employee.homePostcode}}</td>
                            <td class="tr-1 tr-3">手机号码</td>
                            <td class="tr-2 tr-4">{{employee.homeMobile}}</td>
                        </tr>
                        <tr style="border-bottom:1px solid #DCDFE6;">
                            <td class="tr-1 tr-3">紧急联系人</td>
                            <td  class="tr-2 tr-4">{{employee.urgencyLinkman}}</td>
                            <td class="tr-1 tr-3">电子邮箱</td>
                            <td class="tr-2 tr-4">{{employee.email}}</td>
                            <td rowspan="3"  style="width:180px;height:150px;padding:0 10px 0 22px;border-bottom:1px solid #DCDFE6;">
                                <div  v-if="employee.namePhoto" style="width: 180px;height: 135px;margin-left: 0px">
                                    <img :src="this.imageUrl+employee.namePhoto+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail('',employee.namePhoto)">
                                </div>
                                <div v-else>
                                    <img :src="noimg" style="width: 180px;height: 135px;padding-left:15px;"/>
                                </div>
                            </td>
                        </tr>
                        <tr></tr>
                        <tr style="border-bottom:1px solid #DCDFE6;">
                            <td class="tr-1 tr-3">有效通讯地址</td>
                            <td class="tr-2 tr-4">{{employee.homeAddress}}</td>
                            <td class="tr-1 tr-3">人员类型</td>
                            <td class="tr-2 tr-4">{{employee.jobTitle}}</td>

                        </tr>
                        </tbody>
                    </table>
                    <div v-show="isController" class="bottom5" style="width:100%">
                   <span style="width:100%; display:inline-block; text-align:right; padding-top: 10px">
                    <h2 style="float:left;">管制人员信息</h2>
                   </span>
                      <table style="width:100%;">
                          <tbody>
                          <tr>
                            <td  style="border:solid 1px #DCDFE6;" >
                                <div  v-if="employee.skill" style="width: 200px;height: 135px;margin-left: 70px;float:left;">
                                    <img :src="this.imageUrl+employee.skill+'.thumb.jpg'"  style="width: 200px;height:135px;"  @click="imgdetail('',employee.skill)">
                                </div>
                                <div v-else style="margin-left:70px;float:left">
                                    <img :src="noimg" style="width: 200px;height:135px;padding-left:5px;"/>
                                </div>
                                <div  v-if="employee.englishLevel" style="width: 200px;height: 135px;margin-left: 70px;float:left">
                                    <img :src="this.imageUrl+employee.englishLevel+'.thumb.jpg'"  style="width: 200px;height:135px;"  @click="imgdetail('',employee.englishLevel)">
                                </div>
                                <div v-else style="margin-left: 70px;float:left">
                                    <img :src="noimg" style="width: 200px;height:135px;padding-left:5px;"/>
                                </div>
                                <div  v-if="employee.commuStatus" style="width: 200px;height: 135px;margin-left: 70px;float:left">
                                    <img :src="this.imageUrl+employee.commuStatus+'.thumb.jpg'"  style="width: 200px;height:135px;"  @click="imgdetail('',employee.commuStatus)">
                                </div>
                                <div v-else style="margin-left: 70px;float:left">
                                    <img :src="noimg" style="width: 200px;height:135px;padding-left:5px;"/>
                                </div>
                                <div  v-if="employee.skillLevel" style="width: 200px;height: 135px;margin-left: 70px;margin-right: 70px;float:left">
                                    <img :src="this.imageUrl+employee.skillLevel+'.thumb.jpg'"  style="width: 200px;height:135px;"  @click="imgdetail('',employee.skillLevel)">
                                </div>
                                <div v-else style="margin-left: 70px;margin-right:70px;float:left">
                                    <img :src="noimg" style="width: 200px;height:135px;padding-left:5px;"/>
                                </div>
                            </td>
                        </tr>
                          </tbody>
                </table>

                        </div>

                    <div v-show="isPoliot" class="bottom5" style="width:100%">
                   <span style="width:100%; display:inline-block; text-align:right; padding-top: 10px">
                    <h2 style="float:left;">飞行人员信息</h2>
                   </span>
                        <table style="width:100%;">
                            <tbody>
                            <tr v-show="isPoliot">
                                <td class="tr-1 tr-3">所属大队</td>
                                <td class="tr-2 tr-4" >{{pilot.flightTeam}}</td>
                                <td class="tr-1 tr-3">技术身份级别</td>
                                <td class="tr-2 tr-4">{{pilot.techClass}}</td>
                            </tr>
                            <tr v-show="isPoliot">
                                <td class="tr-1 tr-3">无线电通信资格证编号</td>
                                <td class="tr-2 tr-4" >{{pilot.wqn}}</td>
                                <td class="tr-1 tr-3">英语通信等级证编号</td>
                                <td class="tr-2 tr-4" style="border-top:solid 1px #EEF1F7;" >{{pilot.esNum}}</td>
                            </tr>
                            <tr v-show="isPoliot">
                                <td class="tr-1 tr-3">汉语通信等级证编号</td>
                                <td class="tr-2 tr-4" >{{pilot.chNum}}</td>
                            </tr>
                            <tr v-show="isPoliot">
                                <td  colspan="5" style="width:200px;height:150px;border-bottom:solid 1px #EEF1F7;" >
                                    <div  v-if="pilot.wqc" style="width: 200px;height: 135px;margin-left: 70px;float:left;">
                                        <img :src="this.imageUrl+pilot.wqc+'.thumb.jpg'"  style="width: 200px;height:135px;"  @click="imgdetail('',pilot.wqc)">
                                    </div>
                                    <div v-else style="margin-left:70px;float:left">
                                        <img :src="noimg" style="width: 200px;height:135px;padding-left:5px;"/>
                                    </div>
                                    <div  v-if="pilot.esCertificate" style="width: 200px;height: 135px;margin-left: 70px;float:left">
                                        <img :src="this.imageUrl+pilot.esCertificate+'.thumb.jpg'"  style="width: 200px;height:135px;"  @click="imgdetail('',pilot.esCertificate)">
                                    </div>
                                    <div v-else style="margin-left: 70px;float:left">
                                        <img :src="noimg" style="width: 200px;height:135px;padding-left:5px;"/>
                                    </div>
                                    <div  v-if="pilot.chCertificate" style="width: 200px;height: 135px;margin-left: 70px;float:left">
                                        <img :src="this.imageUrl+pilot.chCertificate+'.thumb.jpg'"  style="width: 200px;height:135px;"  @click="imgdetail('',pilot.chCertificate)">
                                    </div>
                                    <div v-else style="margin-left: 70px;float:left">
                                        <img :src="noimg" style="width: 200px;height:135px;padding-left:5px;"/>
                                    </div>
                                    <div  v-if="pilot.scanning" style="width: 200px;height: 135px;margin-left: 70px;margin-right:70px;float:left">
                                        <img :src="this.imageUrl+pilot.scanning+'.thumb.jpg'"  style="width: 200px;height:135px;"  @click="imgdetail('',pilot.scanning)">
                                    </div>
                                    <div v-else style="margin-left: 70px;margin-right:70px;float:left">
                                        <img :src="noimg" style="width: 200px;height:135px;padding-left:5px;"/>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                    </div>


                    <div v-show="isTeach" class="bottom5" style="width:100%">
                   <span style="width:100%; display:inline-block; text-align:right; padding-top: 10px">
                    <h2 style="float:left;">教员信息信息</h2>
                   </span>
                        <table style="width:100%;">
                            <tbody>
                            <tr v-show="isTeach">
                                <td rowspan="4"  colspan="3" style="width:180px;height:150px;">
                                    <div  v-if="pilot.scannedTeacher" style="width: 180px;height: 135px;margin-left: 110px;float:left;">
                                        <img :src="this.imageUrl+pilot.scannedTeacher+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail('',pilot.scannedTeacher)">
                                    </div>
                                    <div v-else style="margin-left: 110px;float:left;">
                                        <img :src="noimg" style="width: 180px;height: 135px;padding-left:5px;"/>
                                    </div>
                                    <div  v-if="pilot.teacherQualification" style="width: 180px;height: 135px;margin-left: 110px;float:left;">
                                        <img :src="this.imageUrl+pilot.teacherQualification+'.thumb.jpg'"  style="width: 180px;height: 135px;"  @click="imgdetail('',pilot.teacherQualification)">
                                    </div>
                                    <div v-else style="margin-left: 110px;float:left;">
                                        <img :src="noimg" style="width: 180px;height: 135px;padding-left:5px;"/>
                                    </div>
                                <td rowspan="2" class="tr-1 tr-3" style="border-left: 1px solid #DCDFE6;">任命书有效期</td>
                                <td  rowspan="2" class="tr-2 tr-4" style="border-right:1px solid #DCDFE6;">{{pilot.validityTime}}</td>
                            </tr>
                            <tr style="border-right:1px solid #DCDFE6;border-bottom:1px solid #DCDFE6;" v-show="teachType">
                            </tr>
                            <tr v-show="teachType"><td rowspan="2" class="tr-1 tr-3" style="border-bottom:0px;border-left: solid 1px #EEF1F7;">理论教员教学类别</td>
                                <td  rowspan="2" class="tr-2 tr-4"  style="border-right:1px solid #DCDFE6;border-bottom:1px solid #DCDFE6;">{{pilot.teachCategories}}</td>
                            </tr >
                            <tr style="border-right:1px solid #EEF1F7;border-bottom:1px solid #DCDFE6;"v-show="teachType">
                            </tr>
                            </tbody>
                        </table>

                    </div>
                      <!--  <tr v-show="isPoliot">
                            <td class="tr-1 tr-3">所属大队</td>
                            <td class="tr-2 tr-4" >{{pilot.flightTeam}}</td>
                            <td class="tr-1 tr-3">技术身份级别</td>
                            <td class="tr-2 tr-4" style="border-top:solid 1px #EEF1F7;" >{{pilot.techClass}}</td>
                        </tr>
                        <tr v-show="isPoliot">
                            <td class="tr-1 tr-3" colspan="5" style="border-right:0px;">飞行员证书</td>

                        </tr>
                        <tr v-show="isPoliot">
                            <td  colspan="5" style="width:180px;height:150px;border-bottom:solid 1px #EEF1F7;" >
                                <div  v-if="pilot.wqc" style="width: 180px;height: 135px;margin-left: 20px;float:left;">
                                    <img :src="pilot.wqc"  style="width: 180px;height:135px;"  @click="imgdetail('',pilot.wqc)">
                                </div>
                                <div v-else style="margin-left:20px;float:left">
                                    <img :src="noimg" style="width: 180px;height:135px;padding-left:5px;"/>
                                </div>
                                <div  v-if="pilot.esCertificate" style="width: 180px;height: 135px;margin-left: 20px;float:left">
                                    <img :src="pilot.esCertificate"  style="width: 180px;height:135px;"  @click="imgdetail('',pilot.esCertificate)">
                                </div>
                                <div v-else style="margin-left: 20px;float:left">
                                    <img :src="noimg" style="width: 180px;height:135px;padding-left:5px;"/>
                                </div>
                                <div  v-if="pilot.chCertificate" style="width: 180px;height: 135px;margin-left: 20px;float:left">
                                    <img :src="pilot.chCertificate"  style="width: 180px;height:135px;"  @click="imgdetail('',pilot.chCertificate)">
                                </div>
                                <div v-else style="margin-left: 20px;float:left">
                                    <img :src="noimg" style="width: 180px;height:135px;padding-left:5px;"/>
                                </div>
                                <div  v-if="pilot.scanning" style="width: 180px;height: 135px;margin-left: 20px;float:left">
                                    <img :src="pilot.scanning"  style="width: 180px;height:135px;"  @click="imgdetail('',pilot.scanning)">
                                </div>
                                <div v-else style="margin-left: 20px;float:left">
                                    <img :src="noimg" style="width: 180px;height:135px;padding-left:5px;"/>
                                </div>
                            </td>
                        </tr>

                        <tr v-show="isTeach">
                            <td class="tr-1 tr-3" colspan="5" style="border-right:0px;">教员信息</td>
                        </tr>
                        <tr v-show="isTeach">
                            <td rowspan="4"  colspan="3" style="width:180px;height:150px;">
                                <div  v-if="pilot.scannedTeacher" style="width: 180px;height: 135px;margin-left: 30px;float:left;">
                                    <img :src="pilot.scannedTeacher"  style="width: 180px;height: 135px;"  @click="imgdetail('',pilot.scannedTeacher)">
                                </div>
                                <div v-else style="margin-left: 30px;float:left;">
                                    <img :src="noimg" style="width: 180px;height: 135px;padding-left:5px;"/>
                                </div>


                                <div  v-if="pilot.teacherQualification" style="width: 180px;height: 135px;margin-left: 30px;float:left;">
                                    <img :src="pilot.teacherQualification"  style="width: 180px;height: 135px;"  @click="imgdetail('',pilot.teacherQualification)">
                                </div>
                                <div v-else style="margin-left: 30px;float:left;">
                                    <img :src="noimg" style="width: 180px;height: 135px;padding-left:5px;"/>
                                </div>
                            <td rowspan="2" class="tr-1 tr-3" style="border-left: solid 1px #EEF1F7;">任命书有效期</td>
                            <td  rowspan="2" class="tr-2 tr-4" style="border-right:0px;">{{pilot.validityTime}}</td>
                        </tr>
                        <tr v-show="teachType">
                        </tr>
                        <tr v-show="teachType"><td rowspan="2" class="tr-1 tr-3" style="border-bottom:0px;border-left: solid 1px #EEF1F7;">理论教员教学类别</td>
                            <td  rowspan="2" class="tr-2 tr-4"  style="border-right:0px;border-bottom:0px;">{{pilot.teachCategories}}</td>
                        </tr >
                        <tr v-show="teachType">
                        </tr>-->



                    <!--<Row>
                        <Col span="10">
                            <FormItem label="隶属管理局" style="width: 350px;float: left;" prop="authorityId">
                                <Select v-model.Number="employee.authorityId" placeholder="请选择">
                                    <Option v-for="item in authoritys" :key="item.id" :value="item.id">
                                        {{item.name}}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="9">
                            <FormItem label="隶属监管局" style="width: 350px;float: left;" prop="supervisionId">
                                <Select v-model.Number="employee.supervisionId" placeholder="请选择">
                                    <Option v-for="item in supervisions" :key="item.id" :value="item.id">
                                        {{item.name}}
                                    </Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>-->


                </div>

                <imgdetail ref="imgdetailref"></imgdetail>
            </TabPane>

            <TabPane label="执照信息" name="name2">
                <licenselist :employeeId="employee.employeeId" :employeeData="this.employeeData"></licenselist>
            </TabPane>

            <TabPane label="体检信息" name="name3">
                <phrecord :employeeId="employee.employeeId" :employeeData="this.employeeData"></phrecord>
            </TabPane>

            <TabPane label="考试记录" name="name4">
                <personexam :employeeId="employee.employeeId" :employeeData="this.employeeData"></personexam>
            </TabPane>

            <TabPane label="培训记录" name="name5">
                <traininfo :employeeId="employee.employeeId" :employeeData="this.employeeData"></traininfo>
            </TabPane>

            <TabPane label="工作经历" name="name6">
                <workexperience :employeeId="employee.employeeId" :employeeData="this.employeeData"></workexperience>
            </TabPane>

            <TabPane label="奖惩记录" name="name7">
                <award-record :employeeId="employee.employeeId" :employeeData="this.employeeData"></award-record>
            </TabPane>
        </Tabs>
    </div>
</template>
<script>
    import pList from './poliotTab.vue';
    import cList from './controllerManTab.vue';
    import word from './upImage.vue';
    import word1 from './upPilotImage.vue';
    import word2 from './upControllerImage.vue';
    import licenselist from './viewLicenseList.vue';
    import phrecord from './viewPhysicalrecordlist.vue';
    import personexam from './viewPersonexamrecord.vue';
    import traininfo from './viewTraininfo.vue';
    import certificate from './newTrainingcertificate.vue';
    import workexperience from './viewWorkExperiencelist.vue';
    import awardRecord from './viewAwardRecordlist.vue';
    import word3 from './upPilotTeachImage.vue';
    import imgdetail from '../../../views/imgdetail.vue';
    import noimg from '../../../../../static/img/noimgpictrue.png';
    import Cookies from 'js-cookie';

    export default {
        components: {
            'imgdetail':imgdetail,
            pList,
            cList,
            'word': word,
            'word1': word1,
            'word2':word2,
            'word3':word3,
            licenselist,
            phrecord,
            personexam,
            traininfo,
            certificate,
            workexperience,
            /*skillcheck,
            periodcheck,
            updateccheck,*/
            awardRecord,
        },
        data() {
            return {
                noimg: noimg,
                value:'name1',
                authoritys: [],
                supervisions: [],
                operationbase: [],
                datas:[],
                TeachPos: [
                    {
                        TeachPosName: '主任教员',
                        TeachPosId: '主任教员'
                    },
                    {
                        TeachPosName: '地面主任教员',
                        TeachPosId: '地面主任教员'
                    },
                    {
                        TeachPosName: '助理主任教员',
                        TeachPosId: '助理主任教员'
                    },
                    {
                        TeachPosName: '（企业）检查员',
                        TeachPosId: '（企业）检查员'
                    }
                ],
                Technicalpost: [{Technicalname: '飞行人员', Technicalid: '飞行人员'},{Technicalname: '管制人员', Technicalid: '管制人员'},{Technicalname: '维修人员', Technicalid: '维修人员'},
                    {Technicalname: '气象人员', Technicalid: '气象人员'},{Technicalname: '情报人员', Technicalid: '情报人员'},{Technicalname: '飞行签派人员', Technicalid: '飞行签派人员'},
                    {Technicalname: '市场人员', Technicalid: '市场人员'},{Technicalname: '财务人员', Technicalid: '财务人员'},{Technicalname: '保障人员', Technicalid: '保障人员'},
                    {Technicalname: '飞行管理人员', Technicalid: '飞行管理人员'}],
                sex: [
                    {
                        sexName: '男',
                        sexId: '男'
                    },
                    {
                        sexName: '女',
                        sexId: '女'
                    }
                ],
                turn: [
                    {
                        turnName: '出勤',
                        turnId: '出勤'
                    },
                    {
                        turnName: '病假',
                        turnId: '病假'
                    },
                    {
                        turnName: '事假',
                        turnId: '事假'
                    },
                    {
                        turnName: '休假',
                        turnId: '休假'
                    },
                ],
                cons: [
                    {
                        consName: '全职',
                        consId: '全职'
                    },
                    {
                        consName: '兼职',
                        consId: '兼职'
                    }
                ],
                wethor: [
                    {
                        weName: '是',
                        weId: '是'
                    },
                    {
                        weName: '否',
                        weId: '否'
                    }
                ],
                level: [
                    {
                        name: '副驾驶',
                        id: '副驾驶'
                    }, {
                        name: '机长',
                        id: '机长'
                    }, {
                        name: '理论教员',
                        id: '理论教员'
                    }, {
                        name: '飞行教员',
                        id: '飞行教员'
                    }, {
                        name: '检查员',
                        id: '检查员'
                    }, {
                        name: '考试员',
                        id: '考试员'
                    }, {
                        name: '监察员',
                        id: '监察员'
                    }, {
                        name: '主任教员',
                        id: '主任教员'
                    }, {
                        name: '助理主任教员',
                        id: '助理主任教员'
                    }

                ],

                isPoliot: false,
                isController: false,
                teachType:false,
                isTeach:false,
                pilot:{
                    pilotId:'',
                    techClass:'',
                    teachCategories:'',
                    validityTime:'',
                    wqc: '',
                    esCertificate: '',
                    chCertificate: '',
                    scanning: '',
                    wqn:'',
                    esNum:'',
                    chNum:'',
                    scannedTeacher:'',
                    teacherQualification:'',
                    flightTeam:'',  //所属大队
                    flySumTime:'',  //飞行总时长
                    flySumIllegal:'' //违规次数
                },
                employee: {
                    employeeId:'',
                    userName: '',
                    jobTitle: '',
                    companyId: '',
                    userId: '',
                    pilot: '',
                    workId: '',
                    cardId: '',
                    gender: '',
                    birthday: '',
                    urgencyLinkman: '',
                    homePostcode: '',
                    nationality: '',
                    ethnicity: '',
                    education: '',
                    isagent: '',
                    age: '',
                    homeAddress: '',
                    school: '',
                    techpos:'',
                    homeMobile: '',
                    email: '',
                    authorityId: '',
                    supervisionId: '',
                    turnOut: '',
                    contract: '',
                    baseId: '',
                    techClass: '',
                    photo: '',
                    scanCard: '',
                    backScancard:'',
                    namePhoto:'',
                    companyName:"",
                    wqc: '',
                    esCertificate: '',
                    chCertificate: '',
                    scanning: '',
                    skill:'',
                    englishLevel:'',
                    commuStatus:'',
                    skillLevel:'',
                    pilotId:'',
                    teachCategories:'',
                    scannedTeacher:'',
                    teacherQualification:'',
                    validityTime:'',
                    flightTeam:'',
                    flySumTime:'',
                    flySumIllegal:''

                },
                employeeData:'',
                employeeValidate: {
                    userName:[
                        { required: true, message: '姓名不能为空', trigger: 'blur' },
                    ],
                    gender:[{ required: true, message: '性别不能为空', trigger: 'blur' },],
                    contract:[{ required: true, message: '合同状态不能为空', trigger: 'blur' },],
                    nationality:[{ required: true, message: '国籍不能为空', trigger: 'blur' },],
                    /*baseId:[{ required: true, message: '所属运行基地不能为空', trigger: 'blur' },],*/
                    /* email: [
                         { required: true, message: '邮箱不能为空', trigger: 'blur' },
                         { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                     ],*/
                    cardId: [{required: true, message: '身份证号不能为空', trigger: 'blur'},
                        {pattern:/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,message: '身份证号输入不合法', trigger: 'blur'}],
                    homeMobile:[
                        { required: true, message: '手机号码不能为空', trigger: 'blur' },
                        {pattern:/^1(3|4|5|7|8)\d{9}$/,message: '工作电话输入不合法', trigger: 'blur'}
                    ],

                }
            }

        },
        created:function(){
            if(this.$route.query.tab){
                this.value = this.$route.query.tab;
            }
            if(this.$route.query.user) {
                let self = this;
                var data=JSON.parse(self.$route.query.user);
                this.employee.employeeId=data.employeeId;
                this.employeeData=data;
            }

        },
        mounted:function(){
            let self = this;
            if(self.$route.query.user){
                var userdata = JSON.parse(self.$route.query.user);
                self.employee=userdata;
                if(self.$route.query.bir){
                    self.employee.birthday=self.$route.query.bir;
                }

                switch (self.employee.education){
                    case '1':self.employee.education='博士';break;
                    case '2':self.employee.education='研究生';break;
                    case '3':self.employee.education='本科';break;
                    case '4':self.employee.education='大专';break;
                    case '5':self.employee.education='大专以下';break;
                    default:''
                }

                if(self.employee.jobTitle.indexOf("飞行人员")!=-1){
                    self.isPoliot = true;
                    //获取飞行人员子表信息
                    let id = self.employee.employeeId;
                    self.$http.httpGet('/eim/api/pilot/search/byEmployeeId', {
                        id,
                    }).then(function (response) {
                        let pdata = response.data.data;
                        self.pilot=pdata;
                        self.employee.pilotId=pdata.pilotId,
                            self.employee.techClass=pdata.techClass,
                            self.employee.teachCategories=pdata.teachCategories,
                            self.employee.validityTime=pdata.validityTime,
                            self.employee.wqc=pdata.wqc,
                            self.employee.esCertificate=pdata.esCertificate,
                            self.employee.chCertificate=pdata.chCertificate,
                            self.employee.scanning=pdata.scanning,
                            self.employee.scannedTeacher=pdata.scannedTeacher,
                            self.employee.teacherQualification=pdata.teacherQualification,
                            self.employee.flightTeam=pdata.flightTeam,
                            self.employee.flySumTime=pdata.flySumTime,
                            self.employee.flySumIllegal=pdata.flySumIllegal

                        if(pdata.techClass==='理论教员'||pdata.techClass==='飞行教员'||pdata.techClass==='主任教员'||pdata.techClass==='助理主任教员'){
                            self.isTeach=true;
                            if(self.pilot.techClass==='理论教员'){
                                self.teachType=true;
                            }
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                };

                if(self.employee.jobTitle.indexOf("管制人员")!=-1){

                    self.isController = true;
                    //获取管制人员子表信息
                    let id = self.employee.employeeId;
                    self.$http.httpGet('/eim/api/controlEmpl/search/byEmployeeId', {
                        id,
                    }).then(function (response) {
                        var controlData = response.data.data;
                        self.employee.skill=controlData.skill;
                        self.employee.englishLevel=controlData.englishLevel;
                        self.employee.commuStatus=controlData.commuStatus;
                        self.employee.skillLevel=controlData.skillLevel;

                    }).catch(function (error) {
                        console.log(error);
                    });
                }
                /*}).catch(function (error) {
                    console.log(error);
                });*/
            }


        },

        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,this.imageUrl+path);
            },
            /* handleView (name) {
                 this.$refs.imgdetailref.initimgmodal("图片详情",name);
             },*/
            selectop: function () {
                if(this.employee.jobTitle.indexOf("飞行人员")!=-1 && this.employee.jobTitle.indexOf("管制人员")!=-1){
                    //包含飞行人员 并且包含管制人员
                    this.isPoliot = true;
                    this.isController = true;
                }
                if(this.employee.jobTitle.indexOf("飞行人员")!=-1&&this.employee.jobTitle.indexOf("管制人员")==-1){
                    //包含飞行人员 但不包含管制人员
                    this.isPoliot = true;
                    this.isController = false;
                }
                if(this.employee.jobTitle.indexOf("飞行人员")==-1&&this.employee.jobTitle.indexOf("管制人员")!=-1){
                    //包含管制人员人员 但不包含飞行人员
                    this.isPoliot = false;
                    this.isController = true;
                }
                if(this.employee.jobTitle.indexOf("飞行人员")==-1&&this.employee.jobTitle.indexOf("管制人员")==-1){
                    //都不包含
                    this.isPoliot = false;
                    this.isController = false;
                }
            },
            selectType:function(){
                if(this.pilot.techClass == '理论教员'){
                    this.teachType=true;
                }else{
                    this.teachType=false;
                    this.pilot.teachCategories='';
                }
                if(this.pilot.techClass =='理论教员'||this.pilot.techClass =='飞行教员'||this.pilot.techClass =='主任教员'||this.pilot.techClass =='助理主任教员'){
                    this.isTeach=true;
                }else{
                    this.isTeach=false;
                    this.pilot.validityTime='';
                    this.pilot.teachCategories='';
                    this.employee.scannedTeacher='';
                    this.employee.teacherQualification='';
                }
            },

            urldata(msg, rt) {
                if (rt == 'itemflag') {
                    this.employee.photo = msg;
                } else if (rt == 'itemflag1') {
                    this.employee.scanCard = msg;
                }else if(rt == 'itemflag2'){
                    this.employee.backScancard = msg;
                }else if(rt=='itemflag3'){
                    this.employee.namePhoto = msg;
                }
            },
            view(name){
                this.$refs.imgdetailref.initimgmodal("图片详情",name);
            },

            urlPilotData(msg, rt) {
                if (rt == 'itemflag3') {
                    this.employee.wqc = msg;
                } else if (rt == 'itemflag4') {
                    this.employee.esCertificate = msg;
                } else if (rt == 'itemflag5') {
                    this.employee.chCertificate = msg;
                } else if (rt == 'itemflag6') {
                    this.employee.scanning = msg;
                }
            },

            urlPilotTeachData(msg, rt){

                if(rt == 'itemflag3'){
                    this.employee.scannedTeacher = msg;
                }else if(rt=='itemflag4'){
                    this.employee.teacherQualification=msg;
                }
            },

            urlControllerData(msg, rt) {
                if (rt == 'itemflag7') {
                    this.employee.skill = msg;
                } else if (rt == 'itemflag8') {
                    this.employee.englishLevel = msg;
                } else if (rt == 'itemflag9') {
                    this.employee.commuStatus = msg;
                } else if (rt == 'itemflag10') {
                    this.employee.skillLevel = msg;
                }
            },


            getitem: function (item) {
                return item;
            },
            getuploadList: function ($event, employee) {
                employee.photo = $event;
            },

            handleSubmit: function () {
                this.$refs.employeeval.validate((valid) => {
                    if (valid) {
                        let self = this;
                        self.employee.techClass=self.pilot.techClass;
                        self.employee.teachCategories=self.pilot.teachCategories;
                        self.employee.validityTime=self.pilot.validityTime;
                        self.employee.flightTeam=self.pilot.flightTeam;
                        self.employee.flySumTime=self.pilot.flySumTime;
                        self.employee.flySumIllegal=self.pilot.flySumIllegal;
                        self.$http.jsonPost('/eim/api/employee/update', self.employee).then(function (response) {
                            if (response.data.resultCode === 200) {
                                //self.employee={};
                                //如果是飞行员  获取飞行员id
                                /*if(response.data.data.jobTitle === '飞行人员'){
                                    self.getPilotId(response.data.data.employeeId)
                                }*/
                                // self.employee.employeeId=response.data.data.employeeId;
                                alert('操作成功');
                            } else {
                                alert('操作失败');
                            }

                        }).catch(function (error) {
                            console.log(error);
                            self.$Message.error('操作失败');
                        });
                    } else {
                        this.$Message.error('表单验证失败');
                    }
                });

            },
            clear: function () {
               // this.$router.go(-1);
                //history.go(-1);
                this.$router.push({path: '/employee/alls'});


            },

            getPilotId:function (id) {
                let self = this;
                self.$http.httpGet('/eim/api/pilot/search/byEmployeeId', {
                    id,
                }).then(function (response) {
                    let datainfo = response.data.data;
                    self.employee.pilotId = datainfo.pilotId;
                }).catch(function (error) {
                    console.log(error);
                });
            }

        }
    }
</script>