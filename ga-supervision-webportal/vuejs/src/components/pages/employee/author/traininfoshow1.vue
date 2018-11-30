<template>
    <div>
        <div class="rightPart">
            <div>
                <div class="cut" v-show="open.base_info_open"  v-if="personQulificationInfo!=null" style="border-bottom-color:white">
                    <table class="m_changeList" style="width: 965px;border: 1px solid rgb(1,107,217)">
                        <tbody>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center;" colspan="5">雇员培训详细信息</td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="width: 298px;text-align: center">培训类型</td>
                            <td class="tr-2 tr-4" style="width: 298px;">{{trainingRecord.trainType}}</td>
                            <td rowspan="6" colspan="2" style="background-color: #eff6fd">
                                <div v-if="trainingRecord.certifactefile==''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="noimg" alt=""/>
                                </div>
                                <div v-if="trainingRecord.certifactefile!=''" style="width: 200px;height:180px; margin-left: 30px">
                                    <img style="width:245px;height:180px;padding-left:5px;margin: auto;" v-bind:src="trainingRecord.certifactefile" alt=""  @click="imgdetail('',trainingRecord.certifactefile)"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">培训科目</td>
                            <td class="tr-2 tr-4">{{trainingRecord.trainSubject}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-5" style="text-align: center">培训内容</td>
                            <td class="tr-2 tr-4">{{trainingRecord.trainContent}}</td>

                        </tr>
                        <tr>
                            <td class="tr-1 tr-3" style="text-align: center">开始时间</td>
                            <td class="tr-2 tr-4">{{trainingRecord.beginDate}}</td>

                        </tr>
                        <tr v-if="pilotLicense!=null">
                            <td class="tr-1 tr-5" style="text-align: center">结束时间</td>
                            <td class="tr-2 tr-4">{{trainingRecord.endDate}}</td>

                        </tr>
                        <tr v-if="pilotLicense!=null">
                            <td class="tr-1 tr-3" style="text-align: center;border-bottom: 0px">考核结果</td>
                            <td class="tr-2 tr-4" style="border-bottom: 0px">{{trainingRecord.result}}</td>

                        </tr>


                        </tbody>
                    </table>
                </div>
            </div>

        </div>
        <imgdetail ref="imgdetailref"></imgdetail>
    </div>

</template>

<script>
    import noimg from '../../../../../static/img/noimgpictrue.png';
    import imgdetail from './imgdetail.vue';
    export default {
        components: {
            imgdetail
        },
        data () {
            return {
                open: {
                    base_info_open: true,
                },
                noimg: noimg,
                keyword: '',
                employee: {},
                pilot: {},
                flightRecordVOs: [],
                prList: [],
                personQulificationInfoVo: {},
                personQulificationInfo: {},
                pilotLicense: {},
                scanCardarra:[],
                personQuliPic:[],
                trainingRecord:
                    {
                        id:'',
                        employeeid:'',
                        trainType: '',
                        trainSubject:'',
                        trainContent:'',
                        beginDate: '',
                        endDate: '',
                        createTime:'',
                        updateTime:'',
                        result: '',
                        certifactefile:'',
                        flag: ''
                    },
            }
        },
        created () {

//            let id=this.$route.query.traininfo;


        },
        props: {
            id: {
                type: Number,
                default: 0
            }
        },
//
        watch:{
            id (val) {
                this.hehe(val);
            },
        },
        methods: {
            imgdetail:function(title,path)
            {
                this.$refs.imgdetailref.initimgmodal(title,path);
            },
            hehe(val){
                let self=this;
                let id=val;
                //alert(id);
                if(id!=0){
                    self.$http.httpGet('eim/api/training/serach/getTrainingRecordById', {
                        id
                    }).then(function (response) {
                        self.trainingRecord=response.data;

                        console.log(self.trainingRecord,'............');

                    }) .catch(function (error) {
                        console.log(error);
                    });
                }

            },
            backtotraininfo(){
                let self=this;
                if (self.$route.path == '/for_pilot') {
                    self.$router.push({path: '/for_pilot', query: {backtype: 'traininfo'}});
                }
                else {
                    self.$router.push({path: '/pilot', query: {backtype: 'traininfo'}});
                }

            },

            modifyData(index) {
//                this.selected = index; // 修改的位置
//                this.selectedlist = this.list[index];
                this.visible = true;
            },
            onComplete: function () {
                //console.log(JSON.stringify(this.pilot));
                alert("done")
                this.visible = false;
            }
        }
    }
</script>
<style scoped>
    .content .row-4 {
        float: left;
        width: 79%;
    }

    .mod .tit {
        padding: 5px 7px 5px 8px;
        border: 1px solid #373737;
        font-size: 14px;
        color: rgba(255, 255, 255, 0.85);
        border-radius: 1px;
    }

    .mod .tit:before, .mod .tit:after {
        content: " ";
        display: table;
    }

    .mod .tit .icon-font {
        margin-right: 5px;
    }

    .mod .cut {
        padding: 0;
        border: 1px solid #0e0e0e;
    }

    .container{ overflow: hidden;}

    .checkbox input[type='checkbox'] {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        z-index: 2;
        opacity: 0;
    }



    .topBar li {
        padding-right: 35px;
        height: 60px;
        background: url(../../../../../static/images/topBarright.png) center right no-repeat;
    }

    .topBar li label.item {
        margin: 0 5px;
        font-size: 16px;
        color: #fff;
        font-style: italic;
        line-height: 60px;
        vertical-align: middle;
    }

    .rightPart1 table{
        border-collapse:collapse;
        border-spacing:0;
        border-left:1px solid #888;
        border-top:1px solid #888;
        background:#efefef;}

    .radio input[type='radio'] {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        z-index: 2;
        opacity: 0;
    }




    .tr-1 {
        font-size: 14px;
        width: 180px;
        padding-left: 13px;
        height: 48px;
        color: rgb(1,107,217);
        border: 0px;
        border-bottom: solid 1px #55a2eb;

    }
    .tr-2 {
        padding-left: 10px;
        padding-right: 10px;
        color:  rgb(35,24,21);
        font-size: 13px;
        border:solid 1px #55a2eb;
        border-top:0px;
        text-align: center;
    }

    .tr-3{
        background-color: rgb(179,221,252);
    }

    .tr-4{
        background-color: rgb(218,236,252);
    }
    .tr-5{
        background-color: rgb(201,231,253);
    }


    .mod{margin-bottom:15px}
    .mod .tit{padding:5px 7px 5px 8px;border:1px solid #d6d6d6;font-size:14px;color:rgba(255,255,255,.85);border-radius:1px}
    .mod .tit .icon-font{margin-right:5px}
    .mod .tit01 .name{float:left;line-height:26px}
    .mod .tit01 .op{float:right}
    .mod .tit01 .date{margin-left:2px;padding:0 10px;width:55px;line-height:26px;border:0;box-shadow:inset 3px 3px 5px #4a4a4a;border-radius:3px;color:#444;font-size:12px}
    .mod .tit01 .refresh{margin-left:2px}

    .rightPart .mod .cut table{width:100%;}
    .mod .cut td{padding-left:28px;height:30px;line-height:30px;
        width:60px;border-right:1px solid #313131;border-bottom:1px solid #313131}
    .mod .cut td:first-child{padding-left:0}.mod .cut td:last-child{border-right:0 none}


    .gacompany-btn {
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

    .infoPart1 table{
        border-collapse:collapse;
        border-spacing:0;
        border-left:1px solid #888;
        border-top:1px solid #888;
        background:#efefef;
        width: 100%;}
    .gacompany-div a:hover {
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

    .div-chxun{
        margin-bottom: 15px;
        height: 30px;
    }

    .div-input{
        height: 30px;
        width: 190px;
    }

    .div-button{
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }

</style>
