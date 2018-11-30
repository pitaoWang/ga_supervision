<style scoped>

    .cdcard{
        background: url(/static/images/cdcard.png) center / contain no-repeat;
        background-size: 405px 32px;
    }
    .cdcard>input{
        display: inline-block;
        width: 100%;
        height: 32px;
        line-height: 1.5;
        padding: 0px 9px;
        font-size: 12px;
        border-radius: 4px;
        color: #657180;
        position: relative;
        cursor: text;
        transition: border .2s ease-in-out,background .2s ease-in-out,box-shadow .2s ease-in-out;
        border: 0px;
        background: rgba(0, 0, 0, 0) !important;
        letter-spacing: 15.2px;
    }

    .submit-bttn {
        font-size: 14px;
        margin: 0px 30px;
        display: inline-block;
        width: 120px;
        height: 35px;
        line-height: 35px;
        background-color: #257fdd;
        border-radius: 3px;
        color: white;
    }




</style>

<template>
    <div style="width: 965px; margin-left: 12px;overflow: hidden;padding-top: 2px;margin-top: 4px; ">
        <img src="/static/images/基础资料图标.png" style="position: absolute;margin-left: 22px;margin-top: 52px;"/>
        <div style="background-color: rgb(46, 130, 220);height: 36px"><div style="font-size: 18px;margin-left: 50px;padding-top: 7px;font-size: 16px;margin-top: 41px;color:rgb(255, 255, 255)">法人信息</div></div>
        <Form :model="legalperson" :label-width="120"  style=" width: 965px;padding:10px auto; padding-top:35px;padding-bottom: 30px;border: 1px solid rgb(74, 163, 255);background-color: rgba(193,220,248,.5); position: absolute;top: 184px;bottom: 0;" ref="formvali" :rules="rule">


            <FormItem label="法人姓名" style="display: inline-block;width: 261px;    margin-left: 150px;" prop="legalpersonName">
                <Input v-model="legalperson.legalpersonName" placeholder="请输入"></Input>
            </FormItem>

            <FormItem label="联系电话" style="display: inline-block; width: 261px;" prop="telephone">
                <Input v-model="legalperson.telephone" placeholder="请输入"></Input>
            </FormItem>

            <FormItem label="身份证号码" style="width: 525px;    margin-left: 150px;" prop="cardid">
                <div class="cdcard" style="" >
                    <input @blur="substringval()" type="text" maxlength="18"  v-model="legalperson.cardid"  onkeyup='this.value=this.value.replace(/([^0-9xX])/gi,"")'/>
                </div>
            </FormItem>

            <FormItem label="性别" style="display: inline-block; width: 20%;    margin-left: 150px;" prop="gender">
                <Select v-model="legalperson.gender" placeholder="请选择" >
                    <Option value="1">男</Option>
                    <Option value="2">女</Option>
                </Select>
            </FormItem>

            <FormItem label="电子邮箱" style="display: inline-block; width: 329px;    margin-left: 0px;" prop="email">
                <Input v-model="legalperson.email" placeholder="请输入"></Input>
            </FormItem>


            <div style=" text-align: center; padding-bottom: 10px; padding-top: 10px; width: 900px;position:relative;">
                <a  @click="handleSubmit()" class="submit-bttn">保存</a>
            </div>
        </Form>
    </div>
</template>
<script>
    export default {

        data () {

            return {
                rule: {
                    cardid: [{required: true, message: '身份证号码不能为空', trigger: 'blur'}],
                    legalpersonName: [{required: true, message: '法人姓名不能为空', trigger: 'blur'}],
                    gender: [{required: true, message: '性别不能为空', trigger: 'change'}],
                    telephone: [{required: true, message: '联系电话不能为空', trigger: 'blur'}],
                    email: [{required: true, message: '电子邮箱不能为空', trigger: 'blur'}],
                },
                isInitPerson:0,
                isUpdateperson:'n',
                legalperson: {
                    id:'',
                    cardid:'',
                    legalpersonName:'',
                    gender:'',
                    telephone:'',
                    email:'',
                    companyId:''
                }

            }
        },
        created: function () {
            //let self = this;
            //this.init();
        },

        methods: {
            //防止按了搜狗输入法打汉字的时候输入n个x导致的bug，所以onblur保留前18位
            substringval(){

                this.legalperson.cardid=this.legalperson.cardid.substring(0,18);
            },
            legalpersoninit(nal){
                let self=this;
                self.isInitPerson=0;
                self.isUpdateperson='n';
                self.$profile.getProfile().then((profile) => {
                       self.legalperson.companyId=nal;
                       let comId = nal;
                    self.$http.httpGet('/app/api/legalperson/findByCompanyId?companyId='+comId).then(function (response) {
                        if(response.data){
                            self.legalperson=response.data;
                        }
                        console.log(response.data,'.................self.legalperson//////////////');
                    }).catch(function (error) {
                        console.log(error);

                    });

                }).catch(function (error) {
                    console.log(error);
                });
            },

            searchPersonInfo(){
                let self = this;
                let cartId=self.legalperson.cardid;
                //根据身份证查询法人表里面的信息（错的）
                self.$http.httpGet('/app/api/legalperson/findCompanyLegalpersonByCartId?cartId='+cartId).then(function (response) {
                    let datainfo=response.data;

                    self.legalperson=datainfo.data;
                    console.log(self.legalperson,'self.legalperson');
                    console.log(response);
                }).catch(function (error) {
                    console.log(error);
                });
            },

            mounted () {
            },
            handleSubmit: function () {
                let self = this;
                if(self.legalperson.cardid.length<18){
                    self.$Message.error('身份证不足18位！');
                    return;
                }
                self.$refs.formvali.validate((valid) => {
                    if(valid) {
                        self.$http.jsonPost('/app/api/legalperson/save', self.legalperson).then(function (response) {
                            alert("保存成功");
                            self.$emit("isUpdatePersonEmit",'n',self.isInitPerson);
                        }).catch(function (error) {
                            console.log(error);
                        });
                    }
                    else{
                        this.$Message.error('有信息未填写，请重新输入！');
                        return;
                    }
                })
            }
        },
        watch: {
            legalperson:
            {
                handler(newValue, oldValue) {
                    if(this.legalperson.legalpersonName!='')
                    {
                        this.isInitPerson=this.isInitPerson+1;
                    }
                    if(this.isInitPerson>1)//判断是否是初始化,0表示是初始化,大于2(监听用户修改的数据)
                    {
                        this.isUpdateperson='y';
                        //    console.log(this.isUpdate,newValue,oldValue);
                    }
                    this.$emit("isUpdatePersonEmit",this.isUpdateperson,this.isInitPerson);
                },
                deep: true,
            }
        },
    }
</script>