<style>
    .moadl-content-1-2 {
        width: 1020px;
        margin-top: 140px;
        border: 1px solid white;
        border-radius: 0px;
        background: #222222;
    }

    .titel-div {
        height: 33px;
        padding-top: 5px;
        font-size: 15px;
        padding-left: 20px;
        background: #191919;
        border-bottom: solid 1px white;
        color: white;
    }

    .div-btn-model {
        margin-top: 20px;
        width: 190px;
        height: 30px;
        color: black;
    }

    .btm {
        margin-top: 20px;
        text-align: center;
    }

    .div-list {
        overflow: hidden;
        border: solid 1px #ececec;
        width: 190px;
        height: 30px;
        text-align: center;
        background: #353535;
        color: white;
        margin-bottom: 5px;
    }

    .div-heard {
        background: #cacaca;
        height: 30px;
        width: 300px;
        margin-bottom: 10px;
        text-align: center;
        padding-top: 10px;
    }

    .img-input-page {
        display: initial;
    }

    .div-input {
        height: 30px;
        width: 190px;
        border: solid 1px black;
    }

    .div-input-input {
        width: 125px;
        height: 26px;
    }

    .div-button {
        height: 30px;
        margin-left: 7px;
        width: 50px;
        background: #b1b1b1;
        border: none;
        border-radius: 5px;
    }

    .div-chxun {
        margin-bottom: 15px;
        height: 30px;
    }

    .div-1-2 {
        width: 120px;
        margin: 0 auto;
        text-align: left;
        padding-top: 5px;
        font-size: 14px;
        position: relative;
    }

    .div-button-1 {
        width: 45px;
        float: left;
        height: 30px;
        margin-left: 5px;
        background: #b1b1b1;
        text-align: center;
        padding-top: 6px;
        border-radius: 5px;
    }

    .ivu-modal-footer {
        display: none;
    }

    .ivu-modal-body {
        padding: 16px;
        font-size: 12px;
        line-height: 1.5;
        background: #222222;
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

    .imgstauts {
        position: absolute;
        top: 3px;
        left: 120px;
    }

    .div-list:hover {
        background: #afafaf;
    }
</style>
<template>
    <div style="margin-left: 36px;margin-right: 16px;">
        <ul>
            <div class="div-chxun">
                <div class="div-input">
                    <input class="div-input-input" v-model="serchcontect" style="color: black;"/>
                    <button class="div-button" v-on:click="searchcrad">查询</button>
                </div>
            </div>

            <!--<div class="div-heard"><img src="../../../../static/img/jiantou.png"  class="img-input-page" @click="doclick"></div>-->
            <li v-for="item in items">
                <div class="div-list" @click="infoclick(item.companyId)" ref="kst" :acr="item.companyId">
                    <div class="div-1-2" @mouseout="mouseoutcolor" @mouseover="mouseovercolor">{{ item.name }}
                        <!--<div v-if="item.flag===3" class="imgstauts">-->
                            <!--<img src="../../../../static/images/等待审核.png">-->
                        <!--</div>-->
                        <!--<div v-if="item.flag===4" class="imgstauts">-->
                            <!--<img src="../../../../static/images/审核驳回.png">-->
                        <!--</div>-->
                        <!--<div v-if="item.flag===0" class="imgstauts">-->
                            <!--<img src="../../../../static/images/审核通过.png">-->
                        <!--</div>-->
                    </div>
                </div>
            </li>
            <!--<div class="div-heard"><img src="../../../../static/img/jiantou1.png" @click="doclick" class="img-input-page"></div>-->
            <!--<div class="btm">-->
            <!--<img src="../../../../static/img/jiantou.png"  class="img-input-page" @click="doupclick()">-->
            <!--<img src="../../../../static/img/jiantou1.png" @click="doclick()" class="img-input-page">-->
            <!--</div>-->
        </ul>
        <div class="modal-backdrop fade in" v-show="isdiv" style="padding-left: 155px;">
            <div class="modal-content moadl-content-1-2"
                 style="margin-top: 40px;border: 1px solid white;border-radius: 0px;">
                <div class="titel-div">
                    创建航空器
                    <div style="float: right; margin-right: 15px;" @click="closemodelchild">
                        <Icon type="close"></Icon>
                    </div>
                </div>
                <!--<addaircraft  v-on:childshow="closemodelchild"></addaircraft>-->
            </div>
        </div>
    </div>
</template>
<script>
    //    import addaircraft from './addaircraft'
    export default {
        data () {
            return {
                modal1: false,
                isdiv: false,
                page: 0,
                pageSize: 10,
                last: false,
                first: true,
                serchcontect: '',
                items: [],
                imgstatus: '',

            }
        },
        mounted(){
            this.inint();

        }, updated(){
            let id = this.$refs.kst[0].getAttribute('acr');
            this.$emit('child-info-id', id);
            this.$refs.kst[0].style.backgroundColor = "black";
        },
        methods: {
            inint: function () {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    let listbysupid = profile.org.orgId;
                let orgtype = 3; //profile.org.orgtype;
                let url = '/app/api/company/search/listbysupid';
              // url = '/app/api/company/';
                self.$http.httpGet(url, {

                    pageno: self.page,
                    pagesize: self.pageSize,
                    supervisionId:listbysupid,
                    //      orgtype:orgtype

                }).then((res) => {
                    this.items = res.data;
                    this.items=res.data.content;
                this.last = res.data.last;
                this.first = res.data.first;


            }).
                catch(function (error) {
                    console.log(error);
                });
            },
                (error) =>
                {
                    console.log('error', error);
                }
                )
                ;
            },
            infoclick: function (companyId) {
                for (var k in this.$refs.kst) {
                    this.$refs.kst[k].style = "";
                }
                ;
                event.target.parentNode.style.backgroundColor = "black";
                this.$emit('child-info-id', companyId);
            },
            tranfor: function () {
                for (var k in this.$refs.kst) {
                    console.log(this.$refs.kst[k].attributes['acr'].nodeValue, "12220");
                    //this.$refs.kst[k].style="";
                }
                ;
                event.target.parentNode.style.backgroundColor = "black";
                this.$emit('child-info-id', companyId);
            },


            showmodel: function () {
                this.isdiv = true;
            },
            closemodelchild: function (msg) {
                this.isdiv = false;
            },
            searchcrad: function () {
                let self = this;
                if (self.serchcontect == "") {
                    self.aircraftlist();
                    return
                }
                else {
                    self.loading = true;
                    self.$http.httpGet('/app/api/company/search/infobyname', {
                        name: self.serchcontect,

                    }).then((res)=>
                    {
                        self.loading = false;
                        this.items = [];
                        this.items.push(res.data);
                    }
                ).
                    catch(function (error) {
                        console.log(error);
                    });
                }
            }
        }

    }
</script>

