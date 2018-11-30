<template>
    <div>

        <div style="width: 1280px; height: auto;  background:white; ">
            <span style="width:100%; display:inline-block;text-align:right; margin-top:10px;margin-bottom: 10px; ">
                    <Button primary @click="cancels()" style="width: 88px;"
                            class="gacompany-btton"
                            size="">返 回</Button>

            </span>
                <table style="width: 100%; border-spacing:0px;">
                    <tbody>
                    <tr>
                        <td class="tr-1 tr-3">姓名</td>
                        <td class="tr-2 tr-4">{{recordFssuser.name}}</td>
                        <td class="tr-1 tr-3">性别</td>
                        <td class="tr-2 tr-4">{{recordFssuser.gender}}</td>
                        <td rowspan="4" colspan="1" style="width:180px;padding-left: 35px">
                            <div  v-if="recordFssuser.headImg">
                                <img :src="this.imageUrl+recordFssuser.headImg" style="width:180px;height: 135px;padding-left: 25px;padding-right: 15px"  @click="imgdetail('',recordFssuser.headImg)">
                            </div>
                            <div  v-else>
                                <img src="../../../../static/images/默认人像.png" style="width:140px;height: 135px;margin-left:20px;padding-left:40px;">
                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">年龄</td>
                        <td class="tr-2 tr-4">{{recordFssuser.age}}</td>
                        <td class="tr-1 tr-3">出生年月</td>
                        <td class="tr-2 tr-4">{{recordFssuser.birthday}}</td>

                    </tr>
                    <tr>

                        <td class="tr-1 tr-3">国籍</td>
                        <td class="tr-2 tr-4">{{recordFssuser.nationality}}</td>
                        <td class="tr-1 tr-3">民族</td>
                        <td class="tr-2 tr-4">{{recordFssuser.nation}}</td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">学历</td>
                        <td class="tr-2 tr-4">{{recordFssuser.education}}</td>

                        <td class="tr-1 tr-3">毕业院校</td>
                        <td class="tr-2 tr-4">{{recordFssuser.graduateSchool}}</td>

                    </tr>
                    <tr>

                        <td class="tr-1 tr-3">邮政编号</td>
                        <td class="tr-2 tr-4">{{recordFssuser.postcode}}</td>
                        <td class="tr-1 tr-3">身份证号码</td>
                        <td class="tr-2 tr-4">{{recordFssuser.idcard}}</td>
                        <td rowspan="3" colspan="1" style="width:180px;height:150px;padding:0 10px 0 22px">
                            <div  v-if="recordFssuser.idcardScanningImg1" style="width: 180px;height: 135px;">
                                <img :src="this.imageUrl+recordFssuser.idcardScanningImg1"  style="width: 180px;height: 135px;margin-left: 20px"  @click="imgdetail('',recordFssuser.idcardScanningImg1)">
                            </div>
                            <div v-else>
                                <img :src="noimg" style="width: 180px;height: 135px;margin-left: 40px"/>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="tr-1 tr-3">飞行服务站</td>
                        <td class="tr-2 tr-4">{{recordFssuser.fssName}}</td>
                        <td class="tr-1 tr-3">手机号码</td>
                        <td class="tr-2 tr-4">{{recordFssuser.tel}}</td>
                    </tr>


                    <tr>
                        <td class="tr-1 tr-3">职位</td>
                        <td class="tr-2 tr-4">{{recordFssuser.position}}</td>
                        <td class="tr-1 tr-3">出勤状态</td>
                        <td class="tr-2 tr-4">{{recordFssuser.attendanceStatus}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">紧急联系人电话</td>
                        <td class="tr-2 tr-4">{{recordFssuser.emergencyContactTel}}</td>
                        <td class="tr-1 tr-3">合同状态</td>
                        <td class="tr-2 tr-4">{{recordFssuser.contractStatus}}</td>
                        <td rowspan="3" colspan="1" style="width:180px;height:150px;padding:0 10px 0 22px;border-bottom: solid 1px #EEF1F7;">
                            <div  v-if="recordFssuser.idcardScanningImg2" style="width: 180px;height: 135px;">
                                <img :src="this.imageUrl+recordFssuser.idcardScanningImg2"  style="width: 180px;height: 135px;margin-left: 20px"  @click="imgdetail('',recordFssuser.idcardScanningImg2)">
                            </div>
                            <div v-else>
                                <img :src="noimg" style="width: 180px;height: 135px;margin-left: 40px"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">邮箱</td>
                        <td class="tr-2 tr-4">{{recordFssuser.email}}</td>
                        <td class="tr-1 tr-3">紧急联系人</td>
                        <td class="tr-2 tr-4">{{recordFssuser.emergencyContact}}</td>

                    </tr>
                    <tr>
                        <td class="tr-1 tr-3">通讯地址</td>
                        <td  class="tr-2 tr-4" colspan="3">{{recordFssuser.address}}</td>
                    </tr>
                    </tbody>
                </table>
        </div>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>



</template>

<script>
    import noimg from '../../../../static/img/noimgpictrue.png';
    import imgdetail from '../../views/imgdetail.vue';
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                noimg: noimg,

                recordFssuser: {
                    headImg: '',
                    name: '',
                    fssName: '',
                    gender: '',
                    age: '',
                    idcard: '',
                    idcardScanningImg1: '',
                    idcardScanningImg2: '',
                    birthday: '',
                    nationality: '',
                    nation: '',
                    education: '',
                    graduateSchool: '',
                    address: '',
                    postcode: '',
                    tel: '',
                    email: '',
                    emergencyContact: '',
                    emergencyContactTel: '',
                    position: '',
                    attendanceStatus: '',
                    contractStatus: '',

                }

            }
        },
        components: {

            imgdetail
        },
        mounted() {
            let self = this;


            self.recordFssuser = JSON.parse(self.$route.query.recordFssuser);


        },
        methods: {
            imgdetail: function (title, path) {
                this.$refs.imgdetailref.initimgmodal(title, this.imageUrl + path);
            },
            cancels: function () {

                this.$router.push({path: '/employee/recordFssuser'});

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

    .flag>img{
        width: 30px;
        display: block;
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