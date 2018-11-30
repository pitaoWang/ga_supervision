<style>
    .div-bttn-model{
        margin-top: 35px;
        width: 190px;
        height: 34px;
        color: rgb(1,107,217);
        font-size: 14px;
        background:#bddeff;
        border:1px dashed;
        border-radius: 3px;
    }
    .bttm {
        margin-top: 20px;
        text-align: center;
    }
    .div-lise {
        overflow: hidden;
        border: solid 1px rgb(1,107,217);
        width: 197px;
        height: 34px;
        text-align: center;
        background:#c1dcf9;
        color: white;
        margin-bottom: 4px;
        cursor: pointer;
    }
    .div-lise:hover {
        background: rgb(159,203,250);
    }
    .img-chg-page{
        display: initial;
        margin-bottom: -3px;
    }
    .div-inputt{
        width: 197px;
    }
    .div-inputt-input{
        width: 129px;
        height: 34px;
        border: 1px solid rgb(1,107,217);
        color: #0e0e0e;
        opacity: 0.65;
    }
    .div-butt{
        height: 34px;
        margin-left: 7px;
        width: 60px;
        float: right;
        background: rgb(1,107,217);
        font-size: 15px;
        border-radius: 5px;
    }
    .div-chaun{
        margin-bottom: 15px;
        height: 30px;
    }
    .registernumber{
        width:139px;
        text-align: left;
        padding-top: 4px;
        font-size: 14px;
        margin-left: 20px;
        float: left;
        color:rgb(1,107,217);
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden
    }
    .registernumber:focus{
        color: #fff;
    }

    .moadl-content-1-2{
        width: 1020px;
        border-radius: 0px;
        background: #222222;
    }
    .titel-div{
        height: 33px;
        padding-top: 4px;
        font-size: 15px;
        padding-left: 20px;
        background: #075db7;
        border-bottom: solid 1px white;
        color: white;
    }
    .modal-content {
        position: relative;
        background-color: #fff;
        background-clip: padding-box;
        outline: 0;
        box-shadow: 0 3px 9px rgba(0,0,0,.5);
        margin: auto;
    }
    .fade {
        transition: opacity .15s linear;
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
</style>
<template>
    <div style="margin-left: 16px;margin-right: 29px;">
        <ul >
            <div class="div-chaun">
                <div class="div-inputt">
                    <input class="div-inputt-input"  v-model="serchcontect" placeholder="   请输入"/>
                    <button class="div-butt" v-on:click="searchcrad">查询</button>
                </div>
            </div>
            <li v-for="item,index in items" >
                <div class="div-lise" ref="myk" :acr="item.aircraftId" :acrname="item.aircraftRegid" @click="infoclick(item.aircraftId,item.aircraftRegid,index)">
                    <div ref="chg" class="registernumber">{{item.registernumber}}</div>
                </div>
            </li>
            <div class="bttm">
                <img src="../../../../static/img/左.png"  class="img-chg-page" @click="doupclick()">
                <div style="display: initial;color:rgb(1,107,217);font-size: 14px">&nbsp;{{page+1}}/{{totalPages}}&nbsp;</div>
                <img src="../../../../static/img/右.png" @click="doclick()" class="img-chg-page">
            </div>
        </ul>
        <div>
            <div @click="showmodel" class="div-bttn-model">
                <img style="margin: 8px 8px 0 44px;float: left;" src="../../../../static/img/添加图标.png"/>
                <div style="float: left;margin-top: 5px">创建航空器</div>
            </div>
        </div>
        <div>
            <button style="margin-top: 9px;border-radius:5px;background-color:#dd9123;width: 93px;height: 35px;color: #fff;font-size: 14px" @click="updateaircraft">修改航空器</button>
            <button style="margin-top: 9px;border-radius:5px;background-color:#dd3507;width: 93px;height: 35px;color: #fff;font-size: 14px" @click="deleteconfirm">删除航空器</button>
        </div>
        <div class="modal-backdrop fade in" v-show="isdiv" style="padding-left: 155px;">
            <div class="modal-content moadl-content-1-2" style="margin-top: 100px;border: 1px solid white;">
                <div class="titel-div" >
                    航空器信息
                    <div style="float: right; margin-right: 15px;" @click="closemodelchild">
                        <Icon type="close"></Icon>
                    </div>
                </div>
                <addaircraft  v-on:childshow="closemodelchild" ref="aircraftinfosuy" :counts="counts"></addaircraft>
            </div>
        </div>
    </div>
</template>
<script>
    import addaircraft from './addaircraft'
    export default {
        data () {
            return {
                isred:true,
                isd:false,
                modal1: false,
                isdiv:false,
                page:0,
                pageSize: 10,
                totalPages:1,
                last:false,
                first:true,
                serchcontect:'',
                deleteid:-1,
                items: [],
            }
        },
        props:['counts'],
        components:{
            addaircraft
        },
        mounted(){
            this.aircraftlist();
        },
        updated(){
            if( this.deleteid == -1){
            let id=this.$refs.myk[0].getAttribute('acr');
            let name=this.$refs.myk[0].getAttribute('acrname');
            this.deleteid = id;
            this.$emit('child-info-id',id,name);
            this.$refs.myk[0].style.backgroundColor="rgb(62,148,246)";
            this.$refs.chg[0].style.color = '#fff';
            }else{
                this.$emit('child-info-id',this.deleteid,"");
            }
        },
        methods:{
            doupclick:function () {
                for(var k in this.$refs.myk){
                    this.$refs.myk[k].style="";
                    this.$refs.chg[k].style="";
                };
                if(this.first){
                    this.page=0;
                }else{
                    this.page=this.page-1;
                    this.aircraftlist();
                }
            },
            doclick:function () {
                for(var k in this.$refs.myk){
                    this.$refs.myk[k].style="";
                    this.$refs.chg[k].style="";
                };
                if(this.last) {

                }else {
                    this.page = this.page + 1;
                    this.aircraftlist();
                }
            },
            aircraftlist:function () {
                let self = this;
                self.$profile.getProfile().then((profile) => {
                    self.$http.httpGet('/eim/api/aircraft/search/companyname',{
                        pageno: self.page,
                        pagesize: self.pageSize,
                        companyids:profile.buz.companyId,
                        copnameoricn: this.serchcontect
                    }).then((res) => {
                        self.loading = false;
                        self.totalPages = res.data.totalPages
                        self.items = res.data.content;
                        for(var i = 0;i<self.items.length;i++){
                            if(self.items[i].flag!=4){
                                self.items[i].flag=-3;
                            }
                        }
                        self.last=res.data.last;
                        self.first=res.data.first;
                        self.flagfuction(res.data.content,self.items);
                    }).catch(function (error) {
                        console.log(error);
                    });
                }, (error) => {
                    console.log('error', error);
                });

            },
            infoclick:function(obj,number,index){
                for(var k in this.$refs.myk){
                    this.$refs.myk[k].style="";
                    this.$refs.chg[k].style="";
                };
                this.$refs.myk[index].style.backgroundColor = 'rgb(62,148,246)';
                this.$refs.chg[index].style.color = "#fff";
                this.deleteid=obj;
                this.$emit('child-info-id',obj,number);
            },
            showmodel:function (){
                this.isdiv=true;
                this.deleteid=-1;
                for(var k in this.$refs.myk){
                    this.$refs.myk[k].style="";
                };
                this.$refs.aircraftinfosuy.initvalidate();
                this.$refs.aircraftinfosuy.initadd();
                this.$refs.aircraftinfosuy.initurl();
                this.$refs.aircraftinfosuy.isaddnew = true;
            },
            closemodelchild:function (msg) {
                if(this.deleteid == -1){
                    this.deleteid = this.$refs.myk[0].getAttribute('acr');
                    this.$emit('child-info-id',this.deleteid,'');
                }
                this.isdiv=false;
                this.aircraftlist();
                this.getaircraftclassinfo();
            },
            searchcrad:function () {
                this.aircraftlist();
            },
            deleteconfirm(){
                this.$Modal.confirm({
                    title: '警告框：',
                    content: '<p>确认删除该条记录？</p>',
                    onOk: () => {
                        this.deleteaircraft ();

                    },
                    onCancel: () => {

                    }
                });
            },
            deleteaircraft:function () {
                if(this.deleteid==-1){
                    alert("请选择需要删除的航空器");
                }else{
                    let self = this;
                    self.$http.httpDelete('/eim/api/aircraft/'+this.deleteid,{
                    }).then((res) => {
                        alert("删除航空器成功");
                        this.deleteid = -1;
                        for(var k in this.$refs.myk){
                            this.$refs.myk[k].style="";
                            this.$refs.chg[k].style="";
                        };
                        this.aircraftlist();
                        this.getaircraftclassinfo();
                    }).catch(function (error) {
                        alert("删除航空器失败");
                        this.aircraftlist();
                    });
                }
            },
            updateaircraft:function () {
                if(this.deleteid==-1){
                    alert("请选择需要修改的航空器");
                }else{
                    this.$refs.aircraftinfosuy.initvalidate();
                    this.$refs.aircraftinfosuy.initurl();
                    this.$refs.aircraftinfosuy.aircraftinfos(this.deleteid);
                    this.$refs.aircraftinfosuy.isaddnew = false;
                    this.isdiv=true;
                }
            },
            flagfuction:function (obj,ite) {
                let self=this;
                for(var i=0;i<obj.length;i++){
                    if(self.items[i].flag!=4){
                    (function (i) {
                        self.$http.httpGet('/app/api/processrun/businessKey',{
                            businessKey:obj[i].aircraftId,
                            defId:'hkqzlsh'
                        }).then((res) => {
                            if(res.data.data){
                                self.items[i].flag=res.data.data.status;
                            }else{
                                self.items[i].flag=4;
                            }
                        }).catch(function (error) {
                            console.log(error);
                        });
                    })(i);
                    }
                }
            },
            getaircraftclassinfo(){
                let self = this;
                self.$http.httpGet('/eim/api/aircraft/search/findaircraftclassbycompany',{
                    companyIds: this.$profile.getStoredProfile().buz.companyId,
                }).then((res) => {
                    //获取固定翼的数量
                    for(let line of res.data){
                        if(line[0] == "固定翼"){
                            self.counts.fixedwing = line[1];
                        }
                        else if(line[0] == "旋翼"){
                            self.counts.rotarywing = line[1];
                        }
                        else if(line[0] == "滑翔机"){
                            self.counts.glider = line[1];
                        }
                        else{
                            self.counts.extra = line[1];
                        }
                    }
                    self.counts.aircraftclassnum = self.counts.fixedwing+self.counts.rotarywing+self.counts.glider+self.counts.extra;
                }).catch(function (error) {
                    console.log(error);
                });
            },
        }

    }
</script>

