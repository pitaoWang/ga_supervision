<style scoped>
    .qualificationp{
        margin-left: 70px;
        /*width: 900px;*/
        /*height: 400px;*/
        /*overflow: hidden;*/
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

</style>
<template>
    <div class="qualificationp">
        <span style="font-size: 18px;font-style: italic">资质信息</span>
        <hr style="width: 898px;height:3px;background-color: aliceblue;">

        <Form class="form" ref="formValidate" :model="formValidate" :label-width="80">
            <div class="everyf"  v-for="(item, index) in formValidate.items">
                <FormItem style="display: none">
                    <Input    v-model="item.id" ></Input>
                </FormItem>

                <FormItem style="width: 64%;margin-top: 35px;height: 34px" label="资质名称" :key="index" :prop="'items.' + index + '.qualificationname'" :rules="  { required: true, message: '资质名不能为空', trigger: 'blur' }">
                    <Input  v-model="item.qualificationname" placeholder="资质名称"></Input>
                </FormItem>
                <FormItem style="width: 64%;height: 34px" label="证书编号"  :key="index" :prop="'items.' + index + '.code'" :rules=" { required: true, message: '证书编号不能为空', trigger: 'blur' }"  >
                    <Input v-model="item.code" placeholder="证书编号"></Input>
                </FormItem>

                <Row style="height: 34px">
                    <Col span="7">
                    <FormItem label="生效时间" :key="index" :prop="'items.' + index + '.starttime'" :rules="   { required: true, type: 'date', message: '请选择时间', trigger: 'change' }">
                        <DatePicker type="date" placeholder="生效时间" placement="bottom" v-model="item.starttime"></DatePicker>
                    </FormItem>
                    </Col>
                    <Col span="7" style="margin-left: 42px">
                    <FormItem label="到期时间" :key="index" :prop="'items.' + index + '.endtime'" :rules=" { required: true, type: 'date', message: '请选择时间', trigger: 'change' }">
                        <DatePicker type="date" placeholder="到期时间"  placement="bottom" v-model="item.endtime"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>


                <FormItem style="width: 64%;height: 34px" label="颁发单位" :key="index" :prop="'items.' + index + '.issueorgname'" :rules=" { required: true, message: '颁发单位能不能为空', trigger: 'change' }">
                    <Input  v-model="item.issueorgname" placeholder="颁发单位"></Input>
                </FormItem>
                <word class="child" multiple="false" v-on:listenTochildEvent="getuploadList()" ></word>
                <div  class="flag" v-on:click="handleremove(item)">
                    <img src="../../../assets/delete.png"></img>
                </div>
            </div>

        </Form>

    </div>



</template>
<script>
    import word from './uploadfile.vue';
    export default {

        data() {
            return {


                uploadListarray: [],
                formdatearray: [],

                formValidate: {
                    items: [
                        {
                            qualificationname: '',
                            code: '',
                            companyid: '',
                            org:'',
                            starttime: '',
                            endtime: '',
                            attchmentaddress: '',
                            id: '',
                            flag: '',
                        },
                    ]

                },

            }
        },
        components: {
            'word': word,
        },
        created: function (newval) {
            let self = this;
            let path=self.$axios.defaults.baseURL;
            /*      self.gacompany={name:'xxx',authorityId:1,postcode:10}*/
            self.$axios.get('http://localhost:18080/app/api/cq', {
                params: {
                    CompanyQualification:{
                        companyId: 1
                    }
                }
            }).then(function (response) {

                let datainfo=response.data;
                self.formValidate.items.splice(0, 1);
                for(var i=0;i<datainfo.length;i++){
                    self.formValidate.items.push(datainfo[i]);
                }

            }) .catch(function (error) {
                console.log(error);
            });
        },

        methods: {


            addformdate: function () {
                this.formValidate.items.push({
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
//            getuploadList: function (data) {
//
//                this.attchmentaddress.push(data);
//            },
            submitfroms: function () {
                let self = this;
//                let path=self.$axios.defaults.baseURL;
                let baseUrl = 'http://192.168.0.200:38080';

                for (var i = 0; i < self.formValidate.items.length; i++) {

                    if (self.formValidate.items[i].starttime > self.formValidate.items[i].endtime) {
                        self.$Message.error('生效时间不能大于！');
                        self.loading = false;
                        return false;
                    }
                    else {

                        self.$axios.post('http://localhost:18080/app/api/cq', {
                            id: self.formValidate.items[i].id,
                            qualificationname: self.formValidate.items[i].qualificationname,
                            code: self.formValidate.items[i].code,
                            starttime: self.formValidate.items[i].starttime,
                            endtime: self.formValidate.items[i].endtime,
                            flag:2,
                            issueorgname: self.formValidate.items[i].issueorgname,
//                    issueorgid: self.formValidate.items[i].org,
                        }).then(function (response) {
                            let qualificationid = JSON.stringify(response.data);
                            self.formValidate.items[i - 1].qualificationid = qualificationid;
                            self.formValidate.items[i - 1].flag = '删除';
                            alert("保存成功");
                        }).catch(function (error) {
                            alert(error);
                        })
                    }
                }
            },
            handleremove: function (item) {
                if (item.flag == "") {

                    this.cancelform(item);
                }
                else {
                    alert("该数据已保存是否删除？");
                    this.deletedform(item);
                }
            },
//            handleremove: function (item,index) {
//                if (item.flag == "取消") {
//
//                    this.cancelform(item);
//            this.cancelform(item,index);
//                }
//                else {
//                    alert("该数据已保存是否删除？");
//                    this.deletedform(item);
//                }
//            },
            cancelform: function (item) {
                this.formValidate.items.splice(this.formValidate.items.indexOf(item), 1);
            },
//            cancelform: function (item,index) {
//                this.formValidate.items.splice(this.formValidate.items.indexOf(item), 1);
//            this.uploadListarray.(index,1);
//            },
            deletedform: function (item) {
                let self = this;
//                let path=self.$axios.defaults.baseURL;
                let  baseUrl = 'http://192.168.0.200:38080';
                let id = item.qualificationid;
//                alert(id);
                self.$axios.delete('http://localhost:18080/app/api/cq/'+id)
                    .then(function (response) {
                        self.cancelform(item);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
        },


        mounted() {

        },

    }

</script>