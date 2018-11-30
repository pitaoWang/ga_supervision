<style scoped>
    .titel-div-1 {
        height: 33px;
        padding-top: 4px;
        font-size: 15px;
        padding-left: 10px;
        background: rgb(1, 107, 217);
        /*border-bottom: solid 1px white;*/
        color: white;
    }
.submit-btn-1{
   color: rgb(255,255,255);
    background-color: rgba(37,128,221,1);
    font-size: 14px;
    margin: 0px 30px;
    display: inline-block;
    width: 120px;
    height: 35px;
    line-height: 30px;
    border: 1px solid #ececec;
    border-radius: 3px;
}
    .modal-heard-1 {
        padding-top: 50px;
        padding-left: 165px;
    }

    .airport-div-madel-1 {
        overflow: hidden;
        height: 460px;
        width: 900px;
    }

    .airport-div-madel {
        background: rgba(220, 238, 255, 1);
        overflow-y: auto;
        height: 480px;
        width: 1190px;
    }

    .float-div-buten {
        font-size: 14px;
        height: 30px;
        background: silver;
        width: 120px;
        border: none;
        border-radius: 4px;
    }

    .float-div-buten:hover {
        background-color: white;
        color: #03a9f4;
    }

    .otherinfo-div {
        width: 900px;
    }

    .main-others {
        color: #0e0e0e;
        padding: 18px 101px 0px 101px;
        position: relative;
    }

    .otherinfo-div-1 {
        text-align: center;
        color: white;
        background: radial-gradient(circle, black, #353535);
        height: 30px;
        padding-top: 6px;
        width: 500px;
        margin: 0 auto;
    }

    .otherinfo-div-input-1 {
        position: relative;
        margin: 0 auto;
    }

    .otherinfo-div-input {
        background: #b3b3b3;
        height: 30px;
        width: 500px;
        margin-bottom: 20px;
        padding-left: 15px;
    }
    .flybase-submit-div a:hover{
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border-radius: 3px;
        color: rgb(255,255,255);
        background: rgba(37,128,221,1);
    }
    .otherinfo-button {
        height: 33px;
        width: 189px;
        border-radius: 5px;
        border: 1px dashed rgb(43, 130, 218);
        background: rgb(178, 214, 251);
        font-size: 14px;
        color: rgb(36, 128, 221);
        margin-bottom: 12px;
    }

    .otherinfo-button-1 {
        height: 125px;
        width: 179px;
        border-radius: 5px;
        border: 1px dashed rgb(43, 130, 218);
        background: rgb(178, 214, 251);
        font-size: 14px;
        color: rgb(36, 128, 221);
        margin-bottom: 12px;
    }

    .otherinfo-div-2 {
        height: 15px;
    }

    .otherinfo-3-div {
        text-align: center;
        height: 45px;
        padding-top: 15px;
        margin-bottom: 25px;
    }

    .others-img-model {
        z-index: 20;
        width: 100%;
        height: 50px;
        position: absolute;
        text-align: center;
        background: linear-gradient(rgba(53, 53, 53, 0.45), rgba(0, 0, 0, 0.84));
        bottom: 0px;
        padding-top: 15px;
    }

    .div-img-delete {
        position: absolute;
        top: 6px;
        margin-left: 23px;
        display: -webkit-inline-box;
        height: 20px;

    }

    .div-img-delete-1 {
        position: absolute;
        top: 6px;
        margin-left: 122px;
        display: -webkit-inline-box;
        height: 20px;
    }

    .div-img-delete-2 {
        position: absolute;
        top: 6px;
        margin-left: 154px;
        display: -webkit-inline-box;
        height: 20px;
        font-size: 14px;
        color: rgba(1, 107, 217, 1);
    }

    .div-img-delete-3 {
        position: absolute;
        top: 6px;
        margin-left: 55px;
        display: -webkit-inline-box;
        height: 20px;
        font-size: 14px;
        color: rgba(1, 107, 217, 1);
    }

    .div-img-delete-4 {
        position: absolute;
        top: 10px;
        margin-left: 98px;
        display: -webkit-inline-box;
        height: 14px;
        border-right: solid 1px rgba(1, 107, 217, 1);
    }

    .main-ent-div {
        margin-left: 70px;
    }

    .fade.in {
        opacity: 1;
    }

    .fade {
        opacity: 0;
        -webkit-transition: opacity .15s linear;
        -o-transition: opacity .15s linear;
        transition: opacity .15s linear;
    }

    .modal.in .modal-dialog {
        -webkit-transform: translate(0, 0);
        -ms-transform: translate(0, 0);
        -o-transform: translate(0, 0);
        transform: translate(0, 0);
    }

    .modal.fade .modal-dialog {
        -webkit-transition: -webkit-transform .3s ease-out;
        -o-transition: -o-transform .3s ease-out;
        transition: transform .3s ease-out;
        -webkit-transform: translate(0, -25%);
        -ms-transform: translate(0, -25%);
        -o-transform: translate(0, -25%);
        transform: translate(0, -25%);
    }

    .modal-content {
        -webkit-box-shadow: 0 5px 15px rgba(0, 0, 0, .5);
        box-shadow: 0 5px 15px rgba(0, 0, 0, .5);
    }

    .modal-content {
        position: relative;
        background-color: #fff;
        -webkit-background-clip: padding-box;
        background-clip: padding-box;
        border: 1px solid #999;
        border: 1px solid rgba(0, 0, 0, .2);
        border-radius: 6px;
        outline: 0;
        -webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
        box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
        margin: auto;
    }

    .modal-backdrop.in {
        filter: alpha(opacity=50);
        opacity: .5;
    }

    .modal-backdrop.fade {
        filter: alpha(opacity=0);
        opacity: 0;
    }

    .fade.in {
        opacity: 1;
    }

    .modal-backdrop {
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        z-index: 1040;
        background-color: rgba(0, 0, 0, 0.37);
    }

    .fade {
        opacity: 0;
        -webkit-transition: opacity .15s linear;
        -o-transition: opacity .15s linear;
        transition: opacity .15s linear;
    }

</style>
<template>
    <div style="width: 965px; margin-left: 12px;">
        <div style="background: #2e82dc;height: 36px;padding-top: 6px;margin: 47px 0px 3px 0px;padding: 6px 0 0 0;">
            <img src="../../../../static/images/基础资料图标.png" style="float: left;margin: 4px 13px 0px 22px;">
            <span style="font-size: 16px;color: rgba(255, 255, 255, 1);">其他信息</span>
        </div>
        <div style="border: solid 1px rgba(1, 107, 217, 0.5);background: rgba(193, 220, 248, 0.5); position: absolute;bottom: 0px;top:184px;width: 965px;right: 26px;"></div>
        <div class="main-others">
            <div style="border: solid 1px rgba(1, 107, 217, 0.5);padding: 15px 15px 5px 15px;margin-bottom: 15px;"
                 v-show="airports.length!=0">
                <ul>
                    <li v-for="(airport,index1) in airports">
                        <div class="otherinfo-div-input-1">
                            <!--查询机场-->
                            <Select :label="airport.airportName"
                                    v-model="airport.airportId"
                                    filterable
                                    remote
                                    :remote-method="remoteMethod1"
                                    style="width: 500px;height: 30px;">
                                <Option v-for="(option, index) in options1" :value="option.value" :key="index"
                                        v-if="airport.companyId!==comid">{{option.label}}
                                </Option>
                            </Select>
                            <img class="div-img-delete" src="../../../assets/删除.png"
                                 @click="airportdelete(index1,airport.companyId,airport.airportId)"/>
                            <span class="div-img-delete-3"
                                  @click="airportdelete(index1,airport.companyId,airport.airportId)">删除</span>
                            <span class="div-img-delete-4"></span>
                            <img class="div-img-delete-1" src="../../../assets/修改.png"
                                 @click="updateairport(airport.airportId)" v-if="airport.companyId===comid">
                            <span class="div-img-delete-2" @click="updateairport(airport.airportId)"
                                  v-if="airport.companyId===comid">修改</span>
                        </div>
                        <div class="otherinfo-div-2"></div>
                    </li>
                </ul>
            </div>

            <div style="border: solid 1px rgba(1, 107, 217, 0.5);padding: 15px 15px 5px 15px;margin-bottom: 15px;"
                 v-show="ctol.length!=0">
                <ul>
                    <li v-for="(cto, index1) in ctol">
                        <div class="otherinfo-div-input-1">
                            <Select :label="cto.name"
                                    v-model="cto.id"
                                    filterable
                                    remote
                                    :remote-method="remoteMethod2"
                                    style="width: 500px;height: 30px;">
                                <Option v-for="(option, index) in options2" :value="option.value" :key="index"
                                        v-if="cto.companyId!==comid">{{option.label}}
                                </Option>
                            </Select>
                            <img class="div-img-delete" src="../../../assets/删除.png"
                                 @click="ctodelete(index1,cto.companyId,cto.id)"/>
                            <span class="div-img-delete-3" @click="ctodelete(index1,cto.companyId,cto.id)">删除</span>
                            <span class="div-img-delete-4"></span>
                            <img class="div-img-delete-1" src="../../../assets/修改.png" v-if="cto.companyId===comid"
                                 @click="updatecto(cto.id)">
                            <span class="div-img-delete-2" v-if="cto.companyId===comid"
                                  @click="updatecto(cto.id)">修改</span>
                        </div>
                        <div class="otherinfo-div-2"></div>
                    </li>
                </ul>
            </div>
            <div style="border: solid 1px rgba(1, 107, 217, 0.5);padding: 15px 15px 5px 15px;margin-bottom: 15px;"
                 v-show="maintain.length!=0">
                <ul>
                    <li v-for="(mainta, index1) in maintain">
                        <div class="otherinfo-div-input-1">
                            <Select :label="mainta.maintainbasename"
                                    v-model="mainta.maintainbaseid"
                                    filterable
                                    remote
                                    :remote-method="remoteMethod4"
                                    style="width: 500px;height: 30px;">
                                <Option v-for="(option, index) in options4" :value="option.value" :key="index"
                                        v-if="mainta.companyId!==comid">{{option.label}}
                                </Option>
                            </Select>
                            <img class="div-img-delete" src="../../../assets/删除.png"
                                 @click="maintadelete(index1,mainta.companyId,mainta.maintainbaseid)"/>
                            <span class="div-img-delete-3"
                                  @click="maintadelete(index1,mainta.companyId,mainta.maintainbaseid)">删除</span>
                            <span class="div-img-delete-4"></span>
                            <img class="div-img-delete-1" src="../../../assets/修改.png" v-if="mainta.companyId===comid"
                                 @click="updatemaintain(mainta.maintainbaseid)">
                            <span class="div-img-delete-2" v-if="mainta.companyId===comid"
                                  @click="updatemaintain(mainta.maintainbaseid)">修改</span>
                        </div>
                        <div class="otherinfo-div-2"></div>
                    </li>
                </ul>
            </div>
            <div style="border: solid 1px rgba(1, 107, 217, 0.5);padding: 15px 15px 5px 15px;"
                 v-show="flyaddress.length!=0">
                <ul>
                    <li v-for="(flyaddres, index1) in flyaddress">
                        <div class="otherinfo-div-input-1">
                            <Select :label="flyaddres.flybasename"
                                    v-model="flyaddres.flybaseid"
                                    remote
                                    filterable
                                    :remote-method="remoteMethod3"
                                    style="width: 500px;height: 30px;">
                                <Option v-for="(option, index) in options3" :value="option.value" :key="index"
                                        v-if="1!==1">{{option.label}}
                                </Option>
                            </Select>
                            <img class="div-img-delete" src="../../../assets/删除.png"
                                 @click="flyaddresdelete(index1,flyaddres.flybaseid)"/>
                            <span class="div-img-delete-3"
                                  @click="flyaddresdelete(index1,flyaddres.flybaseid)">删除</span>
                            <span class="div-img-delete-4"></span>
                            <img class="div-img-delete-1" src="../../../assets/修改.png"
                                 @click="flyaddresdelete(index1,flyaddres.flybaseid)">
                            <span class="div-img-delete-2" @click="updateflyaddres(flyaddres.flybaseid)">修改</span>
                        </div>
                        <div class="otherinfo-div-2"></div>
                    </li>
                </ul>
            </div>

            <div style=" height: 155px;margin-top:21px;">
                <div style="width:242px;float: left;height: 155px;border: solid 1px rgba(1, 107, 217, 0.5);margin-right: 18px;text-align: center;padding-top: 15px;">
                    <button class="otherinfo-button" @click="showaddairport()">添加产权机场</button>
                    <button class="otherinfo-button" @click="showflybase">添加产权飞行基地</button>
                    <button class="otherinfo-button" @click="showmaintain">添加产权维修基地</button>
                </div>
                <div style="width:242px;float: left;height: 155px;border: solid 1px rgba(1, 107, 217, 0.5);margin-right: 18px;text-align: center;padding-top: 15px;">
                    <button class="otherinfo-button-1" @click="showflybase">添加产权飞行基地</button>
                </div>
                <div style="width:243px;float: left;height: 155px;border: solid 1px rgba(1, 107, 217, 0.5);text-align: center;padding-top: 15px;">
                    <button class="otherinfo-button" @click="addaiportinput">添加常用机场</button>
                    <button class="otherinfo-button" @click="addmaintaininput">添加常用维修基地</button>
                    <button class="otherinfo-button" @click="addloadinginput">添加常用起降点</button>
                </div>
            </div>


            <div class="otherinfo-3-div" style="height: 150px;">
                <div class="flybase-submit-div"
                     style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px; width: 772px;display:inline-block;">
                    <a data-v-942377fa="" href="javascript:;" @click="save"  class="submit-btn-1">保存</a>
                </div>
            </div>

            <div class="modal-backdrop fade in modal-heard-1" v-show="isdiv">
                <div class="modal-content" style="width:902px; border: solid 1px rgb(1,107,217); border-radius: 0;">
                    <div class="titel-div-1">
                        {{istitel}}
                        <div style="float: right; background-color: rgba(220,238,255,0.2);margin-top: -1px; margin-right: 3px;"
                             @click="closemodal">
                            <img src="../../../assets/closediv.png">
                        </div>
                    </div>
                    <div v-show="addairport">
                        <div class="airport-div-madel-1">
                            <div class="airport-div-madel">
                                <addairport v-on:childshowairport="closemodelchildairport"
                                            ref="airportstfost"></addairport>
                            </div>
                        </div>
                    </div>
                    <div v-show="addloading">
                        <div class="airport-div-madel-1">
                            <div class="airport-div-madel">
                                <landingpoint v-on:childshowlandingpoint="closemodelchildlandingpoint"
                                              ref="loadingfosuysrt"></landingpoint>
                            </div>
                        </div>
                    </div>
                    <div v-show="addflybase">
                        <flybases v-on:childshowaddflybase="closemodelchildaddflybase" ref="flybasefost"></flybases>
                    </div>
                    <div v-show="addmaintain">
                        <miantainbase v-on:childshowamiantainbase="closemodelchildmiantainbase"
                                      ref="maintainfost"></miantainbase>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>
<script>
    import addairport from '../airport/addairport'
    import landingpoint from '../airport/landingpoint'
    import flybases from '../airport/flybase'
    import miantainbase from '../airport/miantainbase'
    import FormItem from "../../../../node_modules/iview/src/components/form/form-item";
    import IForm from "../../../../node_modules/iview/src/components/form/form";
    export default {
        data () {
            return {
                btnisshow: false,
                addairport: false,
                addloading: false,
                addflybase: false,
                addmaintain: false,
                isshow1: true,
                isshow2: true,
                isdiv: false,
                showname: '',
                istitel: '添机场',
                airports: [],
                ctol: [],
                flyaddress: [],
                maintain: [],
                options1: [],
                airportlist: [],
                options2: [],
                ctollist: [],
                options3: [],
                flyaddresslist: [],
                options4: [],
                maintainlist: [],
                comid: ''
            }
        }, components: {
            IForm,
            FormItem,
            addairport, landingpoint
            , miantainbase, flybases
        },
        props: ['gascompanyid'],
        mounted(){
            this.entinfomounted(this.gascompanyid);
        },
        methods: {
            entinfomounted: function (newval) {
                let self = this;
                self.comid = newval;
                self.resetting(newval);
            },
            resetting: function (newval) {
                let self = this;
                self.begindata(newval);

            },
            shownext: function () {
                this.isshow1 = false;
                this.isshow2 = true;
            },
            lastshow: function () {
                this.isshow2 = false;
                this.isshow1 = true;
            },

            //飞行基地事件
            changeCountflyadd: function (value, index) {
                console.log(value);
                this.showname = "飞行基地";
                var li = 0;
                for (var i = 0; i < this.flyaddress.length; i++) {
                    if (this.flyaddress[i].flybaseid == value && this.flyaddress.length > 1) {
                        li = 1;
                    }
                }

                if (li == 0) {
                    this.flyaddress[index].flybaseid = value;
                } else {
                    this.flyaddress.splice(index, 1);
                    this.flyaddress.push(
                            {
                                flybasename: '',
                                flybaseid: ''
                            }
                    );
                    alert("该飞行基地已经存在");
                }
            },

            //维修基地事件
            changeCountmaintain: function (value, index) {
                this.showname = "维修基地";
                var li = 0;
                for (var i = 0; i < this.maintain.length; i++) {
                    if (this.maintain[i].maintainbaseid == value && this.maintain.length > 1) {
                        li = 1;
                    }
                }

                if (li == 0) {
                    this.maintain[index].maintainbaseid = value;
                } else {
                    alert("该维修基地已存在");
                    this.maintain.splice(index, 1);
                    this.maintain.push({maintainbaseid: '', maintainbasename: ''});
                }
            },

            //起降点事件
            changeCountctol: function (value, index) {
                this.showname = "起降点";
                var li = 0;
                for (var i = 0; i < this.ctol.length; i++) {
                    if (this.ctol[i].id == value && this.ctol.length > 1) {
                        li = 1;
                    }
                }

                if (li == 0) {
                    this.ctol[index].id = value;
                } else {
                    alert("该起降点已经存在");
                    this.ctol.splice(index, 1);
                    this.ctol.push(
                            {name: '', id: ''}
                    );
                }
            },

            // 机场事件
            changeCountairport: function (value, index) {
                this.showname = "机场";
                var li = 0;
                for (var i = 0; i < this.airports.length; i++) {
                    if (this.airports[i].airportId == value && this.airports.length > 1) {
                        li = 1;
                    }
                }

                if (li == 1) {
                    alert("该机场已经存在");
                    this.airports.splice(index, 1);
                    this.airports.push(
                            {
                                airportName: '',
                                airportId: ''
                            }
                    );
                } else {

                }
            },
            showaddairport: function () {
                this.isdiv = true;
                this.addairport = true;
                this.addloading = false;
                this.addflybase = false;
                this.addmaintain = false;
                this.istitel = '添加机场';
                this.$refs.airportstfost.addairoprtinfo();
            },
            showloading: function () {
                this.isdiv = true;
                this.addloading = true;
                this.addairport = false;
                this.addflybase = false;
                this.addmaintain = false;
                this.istitel = '添加起降点';
                this.$refs.loadingfosuysrt.addlandinginfo()

            },
            showflybase: function () {
                this.isdiv = true;
                this.addflybase = true;
                this.addloading = false;
                this.addairport = false;
                this.addmaintain = false;
                this.istitel = '添加飞行基地';
                this.$refs.flybasefost.addflybaseinfo();
            },
            showmaintain: function () {
                this.isdiv = true;
                this.addmaintain = true;
                this.addloading = false;
                this.addflybase = false;
                this.addairport = false;
                this.istitel = '添加维修基地';
                this.$refs.maintainfost.addmaintaininfo();
            },
            closemodal: function () {
                this.isdiv = false;
                this.resetting();
            },
            airportdelete: function (index, cmid, id) {
                //删除当前机场
                if (cmid === this.comid) {
                    this.airports.splice(index, 1);
                    this.$http.httpDelete('/app/api/airport/' + id, {}).then((res) => {
                        alert("删除飞机成功")
                    }).catch(function (error) {
                        console.log(error);
                    });
                } else {
                    this.airports.splice(index, 1);
                }
                //this.airports.splice(index,1);
            },
            ctodelete: function (index, coid, id) {
                if (coid === this.comid) {
                    this.ctol.splice(index, 1);
                    this.$http.httpDelete('/app/api/landingpoint/' + id, {}).then((res) => {
                        alert("删除起降点成功")
                    }).catch(function (error) {
                        console.log(error);
                    });
                } else {
                    this.ctol.splice(index, 1);
                }

            },
            flyaddresdelete: function (index, id) {
                this.flyaddress.splice(index, 1);
                this.$http.httpDelete('/app/api/flybase/' + id, {}).then((res) => {
                    alert("删除飞行基地成功")
                }).catch(function (error) {
                    console.log(error);
                });
            },
            maintadelete: function (index, coid, id) {
                if (coid === this.comid) {
                    this.maintain.splice(index, 1);
                    this.$http.httpDelete('/app/api/mb/' + id, {}).then((res) => {
                        alert("删除维修基地成功")
                    }).catch(function (error) {
                        console.log(error);
                    });
                } else {
                    this.maintain.splice(index, 1);
                }
            },
            updateairport: function (obj) {
                this.isdiv = true;
                this.addairport = true;
                this.addloading = false;
                this.addflybase = false;
                this.addmaintain = false;
                this.istitel = '添加机场';
                this.$refs.airportstfost.airportinfo(obj);
            },
            updatecto: function (obj) {
                this.isdiv = true;
                this.addloading = true;
                this.addairport = false;
                this.addflybase = false;
                this.addmaintain = false;
                this.istitel = '添加起降点';
                this.$refs.loadingfosuysrt.landinginfost(obj);
            },
            updatemaintain: function (obj) {
                this.isdiv = true;
                this.addmaintain = true;
                this.addloading = false;
                this.addflybase = false;
                this.addairport = false;
                this.istitel = '添加维修基地';
                this.$refs.maintainfost.maintaininfo(obj);
            }, updateflyaddres: function (obj) {
                this.isdiv = true;
                this.addflybase = true;
                this.addloading = false;
                this.addairport = false;
                this.addmaintain = false;
                this.istitel = '添加飞行基地';
                this.$refs.flybasefost.flybaseinfo(obj);
            },
            save: function () {
                //机场
                var tr = "";
                for (var i = 0; i < this.airports.length; i++) {
                    if (this.airports[i].airportId != '' && this.airports[i].companyId !== this.comid) {
                        tr += this.airports[i].airportId + ",";
                    }
                }
                var airportres = tr.substring(0, tr.length - 1);

                //飞行基地
                var flyads = "";
                for (var j = 0; j < this.flyaddress.length; j++) {
                    if (this.flyaddress[j].flybaseid != '' && this.flyaddress[j].companyId !== this.comid) {
                        flyads += this.flyaddress[j].flybaseid + ",";
                    }
                }
                var flyaddresss = flyads.substr(0, flyads.length - 1);

                //维修基地
                var mainta = "";
                for (var k = 0; k < this.maintain.length; k++) {
                    if (this.maintain[k].maintainbaseid !== '' && this.maintain[k].companyId !== this.comid) {
                        mainta += this.maintain[k].maintainbaseid + ",";
                    }
                }
                var maintains = mainta.substr(0, mainta.length - 1);
                console.log(maintains);

                //起降点
                var ctols = "";
                for (var f = 0; f < this.ctol.length; f++) {
                    if (this.ctol[f].id != '' && this.ctol[f].companyId !== this.comid) {
                        ctols += this.ctol[f].id + ",";
                    }
                }
                var ctolss = ctols.substr(0, ctols.length - 1);
                let self = this;
                //保存企业信息
                self.$http.httpGet('/app/api/company/save/flybaseent', {
                    companyid: self.$profile.getStoredProfile().buz.companyId,
                    flybaseid: flyaddresss,
                    airport: airportres,
                    maintainid: maintains,
                    landingid: ctolss
                }).then((res) => {
                    self.flyaddress = res.data;
                    self.loading = false;
                    alert("新增成功");
                    self.$profile.getProfile().then((profile) => {
                        console.log("aircraftlist get profile", profile);
                        let companyid = profile.buz.companyId;
                        self.begindata(companyid);
                    }, (error) => {
                        console.log('error', error);
                    });
                }).catch(function (error) {
                    console.log(error);
                    alert("新增失败")
                    self.begindata();
                });
            },
            loads: function () {
                this.btnisshow = false;
            },
            loadsclose: function () {
                this.btnisshow = false;
                if (this.showname == "飞行基地") {
                    this.showflybase();
                }
                if (this.showname == "维修基地") {
                    this.showmaintain();
                }
                if (this.showname == "起降点") {
                    this.showloading();
                }
                if (this.showname == "机场") {
                    this.showaddairport();
                }
            },
            closemodelchildairport: function () {
                this.isdiv = false;
                this.addairport = false;
                this.resetting();
            }, closemodelchildlandingpoint: function () {
                this.isdiv = false;
                this.addloading = false;
                this.resetting();
            }, closemodelchildaddflybase: function () {
                this.isdiv = false;
                this.addflybase = false;
                this.resetting();
            }, closemodelchildmiantainbase: function () {
                this.isdiv = false;
                this.addmaintain = false;
                this.resetting();
            }, addaiportinput: function () {
                let k = 0;
                for (var i = 0; i < this.airports.length; i++) {
                    if (this.airports[i].airportId == '') {
                        k = 1;
                    }
                }
                if (k == 1) {
                    alert("请先输入机场，机场不能为空")
                } else {
                    this.airports.push(
                            {
                                airportName: '',
                                airportId: ''
                            }
                    );
                }

            }, addloadinginput: function () {
                let k = 0;
                for (var f = 0; f < this.ctol.length; f++) {
                    if (this.ctol[f].id == '') {
                        k = 1;
                    }
                }
                if (k == 1) {
                    alert("请先输入起降点,起降点不能为空");
                } else {
                    this.ctol.push(
                            {
                                name: '',
                                id: ''
                            }
                    );
                }

            }, addflybaseinput: function () {
                let k = 0;
                for (var j = 0; j < this.flyaddress.length; j++) {
                    if (this.flyaddress[j].flybaseid == '') {
                        k = 1;
                    }
                }
                if (k == 1) {
                    alert("请先输入飞行基地,飞行基地不能为空");
                } else {
                    this.flyaddress.push({
                        flybasename: '',
                        flybaseid: ''
                    });
                }
            }, addmaintaininput: function () {
                let o = 0;
                for (var k = 0; k < this.maintain.length; k++) {
                    if (this.maintain[k].maintainbaseid == '') {
                        o = 1;
                    }
                }
                if (o == 1) {
                    alert("请先输入维修基地，维修基地不能为空");
                } else {
                    this.maintain.push({maintainbaseid: '', maintainbasename: ''});
                }
            }, begindata: function (comid) {
                //数据的初始化加载
                let self = this;
                //机场数据 根据企业ID
                self.$http.httpGet('/app/api/airport/search/airportbycompanyid', {
                    companyid: comid
                }).then((res) => {
                    self.airports = res.data;
                }).catch(function (error) {
                    console.log(error);
                });

                //获取所有的机场
                self.$http.httpGet('/app/api/airport/search/airportall', {}).then((res) => {
                    self.airportlist = res.data;
                }).catch(function (error) {
                    console.log(error);
                });


                //维修基地数据
                self.$http.httpGet('/app/api/mb/search/mainbycompanyid', {
                    companyid: comid
                }).then((res) => {
                    self.maintain = res.data;
                }).catch(function (error) {
                    console.log(error);
                });

                //获取所有的维修基地数据
                self.$http.httpGet('/app/api/mb/search/maintainall', {}).then((res) => {
                    self.maintainlist = res.data;
                }).catch(function (error) {
                    console.log(error);
                });


                //起降点数据
                self.$http.httpGet('/app/api/landingpoint/search/landingbycompanyid', {
                    companyid: comid
                }).then((res) => {
                    self.ctol = res.data;
                }).catch(function (error) {
                    console.log(error);
                });

                //获取所有的起降点数据
                self.$http.httpGet('/app/api/landingpoint/search/landingall', {
                    companyid: comid
                }).then((res) => {
                    self.ctollist = res.data;
                }).catch(function (error) {
                    console.log(error);
                });


                //飞行基地数据
                self.$http.httpGet('/app/api/flybase/search/flybasebycompanyid', {
                    companyid: comid
                }).then((res) => {
                    self.flyaddress = res.data;
                }).catch(function (error) {
                    console.log(error);
                });

                //获取所有的飞行基地数据
                self.$http.httpGet('/app/api/flybase/search/flybaseall', {}).then((res) => {
                    self.flyaddresslist = res.data;
                }).catch(function (error) {
                    console.log(error);
                });
            },
            remoteMethod1 (query) {
//            检索机场函数
                let airnew = this.airportlist;
                let airnews = this.airportlist;
                for (var i = 0; i < airnew.length; i++) {
                    for (var j = 0; j < this.airports.length; j++) {
                        if (airnew[i].airportId == this.airports[j].airportId) {
                            airnews.splice(i, 1);
                        }
                    }
                }

                if (query !== '') {
                    setTimeout(() => {
                        const list = airnews.map(item => {
                            return {
                                value: item.airportId,
                                label: item.airportName
                            };
                        });
                        this.options1 = list.filter(item => item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);

                    }, 50);
                } else {

                }
            },
            remoteMethod2 (query) {
                //       检索起降点函数
                let ctolnew = this.ctollist;
                let ctolnews = this.ctollist;
                for (var i = 0; i < ctolnew.length; i++) {
                    for (var j = 0; j < this.ctol.length; j++) {
                        if (ctolnew[i].id == this.ctol[j].id) {
                            ctolnews.splice(i, 1);
                        }
                    }
                }

                if (query !== '') {
                    this.loading1 = true;
                    setTimeout(() => {
                        this.loading1 = false;
                        const list = ctolnews.map(item => {
                            return {
                                value: item.id,
                                label: item.name
                            };
                        });
                        this.options2 = list.filter(item => item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);

                    }, 200);
                } else {

                }
            },
            remoteMethod3(query) {
                //检索飞行基地函数
                let flynew = this.flyaddresslist;
                let flynews = this.flyaddresslist;
                for (var i = 0; i < flynew.length; i++) {
                    for (var j = 0; j < this.flyaddress.length; j++) {
                        if (flynew[i].flybaseid == this.flyaddress[j].flybaseid) {
                            flynews.splice(i, 1);
                        }
                    }
                }

                if (query !== '') {
                    this.loading1 = true;
                    setTimeout(() => {
                        this.loading1 = false;
                        const list = flynews.map(item => {
                            return {
                                value: item.flybaseid,
                                label: item.flybasename
                            };
                        });
                        this.options3 = list.filter(item => item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);

                    }, 200);
                } else {

                }
            },
            remoteMethod4(query) {
                //检索飞行基地函数
                let maintainnew = this.maintainlist;
                let maintainnews = this.maintainlist;
                for (var i = 0; i < maintainnew.length; i++) {
                    for (var j = 0; j < this.maintain.length; j++) {
                        if (maintainnew[i].maintainbaseid == this.maintain[j].maintainbaseid) {
                            maintainnews.splice(i, 1);
                        }
                    }
                }
                if (query !== '') {
                    setTimeout(() => {
                        const list = maintainnews.map(item => {
                            return {
                                value: item.maintainbaseid,
                                label: item.maintainbasename
                            };
                        });
                        this.options4 = list.filter(item => item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);

                    }, 200);
                } else {

                }
            }
        }
    }
</script>
