<style scoped>
    .qualificationp{
        width: 965px;
        float: right;
        margin-right: 7px;
    }


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
    /*.qualification{*/
        /*!*height:400px;*!*/
        /*!*width: 930px;    !* 多出26像素是滚动条的位置，会被父容器盖住就看不到了 *!*!*/
        /*!*overflow-x: hidden;*!*/
        /*!*overflow-y: scroll;*!*/
    /*}*/
    .form{
        width: 900px;
        background-color: #363636
    }

    .ivu-form .ivu-form-item-label{
        color:#b3b3b3;
        font-size: 14px;

    }


.ivu-input-icon+.ivu-input{
    height: 30px;
    background: #b3b3b3;
    padding-right: 0;
}
    .ivu-row{
      margin-bottom: 30px;
    }
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
        background-color:rgba(37,128,221,1);
        /*background: -webkit-linear-gradient(#a3a3a3, #e7e7e7);*/
        /*background: linear-gradient(#a3a3a3, #e7e7e7);*/
    }

    .gacompany-div a:hover {
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 30px;
        border: 1px solid #ececec;
        border-radius: 3px;
        color: black;
        background: rgba(37,128,221,1);
        /*background: -webkit-linear-gradient(#e7e7e7, #a3a3a3);*/
        /*background: linear-gradient(#e7e7e7, #a3a3a3);*/
    }
</style>
<template>
<div class="qualificationp">
    <div style="width:965px; font-size:16px; background: rgb(46, 130, 220); height: 36px; padding: 6px 0px 0px; margin: 47px 0px 3px;"><span style="margin-right: 13px;margin-left: 22px;"><Icon type="person-add" size="20" style="color:rgb(255,255,255);"></Icon></span>资质信息</div>
    <!--<img src="/static/images/基础资料图标.png" style="position: absolute;margin-left: 22px;margin-top: 9px;"/>-->
    <!--<div style="background-color: rgb(46, 130, 220);width: 900px;height: 36px"><div style="font-size: 18px;margin-left: 50px;padding-top: 7px;font-size: 16px;">资质信息</div></div>-->

        <Form class="form" ref="formVali" :model="formValidate" :label-width="100" style="margin-top:3px;background-color: rgba(193, 220, 248, 0.5);width: 965px ">
            <div class="everyf"  v-for="(item, index) in formValidate.items" style="border:1px solid rgba(1,107,217,0.5);">
                <FormItem style="display: none">
                    <Input    v-model="item.id" ></Input>
                </FormItem>

            <FormItem style="width: 64%;margin-top: 35px;height: 34px" label="资质名称x" :key="index" :prop="'items.' + index + '.qualificationname'" :rules="  { required: true, message: '资质名不能为空', trigger: 'blur' }">
                <Input  v-model="item.qualificationname" placeholder="资质名称"></Input>
            </FormItem>
              <FormItem style="width: 64%;height: 34px" label="证书编号"  :key="index" :prop="'items.' + index + '.code'" :rules=" { required: true, message: '证书编号不能为空', trigger: 'blur' }"  >
                            <Input v-model="item.code" placeholder="证书编号"></Input>
                </FormItem>


                    <FormItem label="长久有效" :key="index" style="height: 34px">
                        <RadioGroup v-model="item.radio" style="color: #016bd9">
                            <Radio label="yes">是</Radio>
                            <Radio label="no">否</Radio>
                        </RadioGroup>
                    </FormItem>


                <Row style="height: 34px">
                    <Col span="7">
                    <FormItem label="生效时间" :key="index" :prop="'items.' + index + '.starttime'" :rules="   { required: true, type: 'date', message: '请选择时间', trigger: 'change' }">
                        <DatePicker type="date" placeholder="生效时间" placement="bottom" :editable="false" v-model="item.starttime"></DatePicker>
                    </FormItem>
                    </Col>
                    <Col span="7" style="margin-left: 42px" v-if="item.radio=='no'">
                    <FormItem label="到期时间" :key="index" :prop="'items.' + index + '.endtime'" >
                        <DatePicker  placeholder="到期时间"  placement="bottom" v-model="item.endtime" :editable="false"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>

               <FormItem style="width: 64%;height: 34px" label="颁发单位" :key="index" :prop="'items.' + index + '.issueorgname'" :rules=" { required: true, message: '颁发单位能不能为空', trigger: 'blur' }">
                <Input  v-model="item.issueorgname" placeholder="颁发单位"></Input>
                </FormItem>
               <word :itemflag=item.attchmentaddress class="child" v-on:click="getitem(item)" v-on:listenTochildEvent="getuploadList($event,item)" ></word>
                    <div  class="flag" v-on:click="handleremove(item)">
                        <Icon type="close" size=25></Icon>
                    </div>
            </div>

        </Form>
    <Modal v-model="qualificationmodel"
           :mask-closable="false"
           width="300px" hight="200px" :styles="{top: '40%'}">
        <p slot="header" style="color:#e9e9e9;text-align:left;font-size: 15px;">
            <span>系统提示</span>
        </p>
        <p style="height: 100px; height: 80px;padding-top: 24px;text-align: center;margin-bottom: 2px; text-align: center;">{{warnmsg}}</p>
    </Modal>
    <div  class="gacompany-div" style="float: left; text-align: center; padding-bottom: 10px; padding-top: 10px; width: 900px;">
        <a data-v-942377fa="" href="javascript:;" @click="addformdate()" class="gacompany-btn">添加</a>
        <a data-v-942377fa="" href="javascript:;" @click="submitfroms()" class="gacompany-btn">保存</a>
    </div>
    </div>



</template>
<script>
    import word from './uploadfile.vue';
    export default {

        data() {
            return {
                warnmsg:'',
                qualificationmodel: false, /*提示框*/
                comid:'',
                isInitqualification:0,
                isUpdatequalification:'n',
                isuti:'n',
                uploadListarray: [],
                formdatearray: [],
                formValidate: {
                    items: [

                    ]
                },
            }
        },
        components: {
            'word': word,
        },
        methods: {
            companyqualificationinit: function (newval) {
                let self = this;
                self.isInitqualification=0;
                self.isUpdatequalification='n';
                self.$profile.getProfile().then((profile) =>{
                    self.comid=newval;
                    self.$http.httpGet('/app/api/cq/serach/getbycompanyid', {
                        companyId:newval
                    }).then(function (response) {
                        console.log(response);
                        let datainfo=response.data;
                        self.formValidate.items.splice(0);//将数组长度设置为0
                        console.log(datainfo);
                        for(var i=0;i<datainfo.length;i++){

                            self.formValidate.items.push(datainfo[i]);
                        }
                        for(var i=0;i<self.formValidate.items.length;i++){
                            if(self.formValidate.items[i].endtime=='2099-09-09'||self.formValidate.items[i].endtime=='2099-09-06'||self.formValidate.items[i].endtime===null){
                                self.$set(self.formValidate.items[i],'radio','yes');
                            }else{
                                self.$set(self.formValidate.items[i],'radio','no');
                            }
                        }
                    }) .catch(function (error) {
                        console.log(error);
                    });
                }, (error) => {
                    console.log('error', error);
                });

            },
            addformdate: function () {
                this.formValidate.items.push({
                    radio:'yes',
                    qualificationname: '',
                    code: '',
                    companyid: '',
                    issueorgname:'',
                    starttime: '',
                    endtime: '',
                    attchmentaddress: '',
                    id: '',
                    flag: '',
                },);


            },
            getuploadList: function ($event, item) {


                item.attchmentaddress=$event;


            },
            getitem:function (item) {
                return item;

            },

            submitfroms: function () {
                for (var i = 0; i < this.formValidate.items.length; i++) {
                    for (var j = i + 1; j < this.formValidate.items.length; j++){
                        if (this.formValidate.items[i].code == this.formValidate.items[j].code) {
                            this.warnmsg = "证书编号已被使用或者证书编号不能重复";
                            this.qualificationmodel = true;
                            this.loading = false;
                            return false;
                        }
                }
                        }
                this.$refs.formVali.validate((valid) => {
                    let self = this;
                    if (valid) {
                        let submitflag ;
                        for (var i = 0; i < self.formValidate.items.length; i++) {
                            if(self.formValidate.items[i].radio=='yes'){
                                self.formValidate.items[i].endtime=null;
                            }
                                self.$http.jsonPost('/app/api/cq', {
                                    id: self.formValidate.items[i].id,
                                    qualificationname: self.formValidate.items[i].qualificationname,
                                    companyid: self.comid,
                                    code: self.formValidate.items[i].code,
                                    starttime: self.formValidate.items[i].starttime,
                                    endtime: self.formValidate.items[i].endtime,
                                    flag: 2,
                                    issueorgname: self.formValidate.items[i].issueorgname,
                                    attchmentaddress: self.formValidate.items[i].attchmentaddress,
                                }).then(function (response) {
                                    if (response.data != '') {
                                        let qualificationid = JSON.stringify(response.data);
                                        console.log(response);
                                        console.log( self.formValidate)
                                        if(self.formValidate.items.length==i){
                                        self.isuti='y';
                                        self.formValidate.items[i - 1].id = qualificationid;
                                        self.formValidate.items[i - 1].flag = '删除';
                                        self.$emit("isUpdateQualificationEmit",'n',self.isInitqualification);
                                        }else{
                                            self.formValidate.items[i - 1].id = qualificationid;
                                            self.formValidate.items[i - 1].flag = '删除';
                                        }
                                    }
                                    else {
                                        self.warnmsg ="证书编号已被使用或者证书编号不能重复"
                                        self.qualificationmodel = true;
                                        /*提示框*/
                                    }

                                }).catch(function (error) {
                                    alert(error);
                                    return false;
                                })
//                            }


                        }
//                        self.companyqualificationinit(self.comid);
                          alert("信息保存成功");

                    }
                    else{
                        this.$Message.error('表单验证失败!');
                        return;
                    }

                })

            },
            handleremove: function (item) {
                if (item.flag == "") {

                    this.cancelform(item);
                }
               else {
                    this.$Modal.confirm({
                        title: '警告框：',
                        content: '<p>该数据已保存是否删除？？</p>',
                        onOk: () => {
                            this.deletedform(item);

                        },
                        onCancel: () => {

                        }
                    });


            }
        },
            cancelform: function (item) {
                this.formValidate.items.splice(this.formValidate.items.indexOf(item), 1);
            },
            deletedform: function (item) {
              let self = this;
              let  baseUrl = 'http://192.168.0.200:38080';
                let id = item.id;
                self.$http.httpDelete('/app/api/cq/'+id)
                    .then(function (response) {
                        console.log(response)
                        self.cancelform(item);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
        },
        watch: {
            formValidate:
            {
                handler(newValue, oldValue) {
                    console.log(newValue,oldValue);
                    this.isInitqualification=this.isInitqualification+1;
                    if(this.isInitqualification>3)//判断是否是初始化,0表示是初始化,大于2(监听用户修改的数据)
                    {
                        if(this.isuti==='n'){
                            this.isUpdatequalification='y';
                        }else{
                            this.isUpdatequalification='n';
                            this.isuti='n';
                        }

                    }
                    this.$emit("isUpdateQualificationEmit",this.isUpdatequalification,this.isInitqualification);
                },
                deep: true,
            }
        },

    }

</script>