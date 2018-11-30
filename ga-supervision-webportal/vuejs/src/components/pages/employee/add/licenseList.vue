<template>
    <div style="width: 1280px; height:auto; background:white">
        <div  style="margin-left: 9px;">
                <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                    <Button type="primary" @click="addlicense()" icon="plus" style="width: 98px;height: 32px;margin:24px 12px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;" size="small">增  加</Button>
                </span>
            <div class="bottom5" style="width: 1160px; margin-left: 10px" >
                <Table border :columns="columns7" :data="PersonQulificationInfolist"></Table>
            </div>
        </div>
    </div>
</template>
<script>
    import word from '../../employee/licenseinfo.vue';
    import Cookies from 'js-cookie';
    export default {
        components: {
            'word': word,
        },
        data() {
            return {
                PersonQulificationInfolist:[],
                columns7: [
                    {
                        title: '执照编号',
                        key: 'licenseno',
                        align: 'center',
                        width: 360
                    },
                    {
                        title: '执照名称',
                        width: 360,
                        key: 'licensetype',
                        align: 'center'
                    },
                    {
                        title: '执照状态',
                        key: 'expireStatu',
                        align: 'center',
                        width: 317,
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 120,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '16px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_edit.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }, ),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style:{
                                        marginLeft: '4px',
                                        marginTop: '4px',
                                        width: '30px',
                                        height:'30px',
                                        background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                        border:'none'
                                    },
                                    on: {
                                        click: () => {
                                            this.confirm(params.index)
                                        }
                                    }
                                })
                            ]);
                        }
                    }
                ],

                add_dialog_show: false,
            }
        },
        /*props:['employeeId','employee','pilot'],*/
        props:['employeeId'],
        created() {
            let buz = JSON.parse(Cookies.get("profile"));
            this.employeeId=buz.buz.employeeId;
            this.getPersonQulificationInfolist(this.employeeId)
        },


        methods: {


            getCheckform() {
                return this.check_form;
            },
            getuploadList: function ($event, check_form) {
                console.log($event);
                check_form.certificateFilepath = $event;
            },
            getPersonQulificationInfolist(employeeId) {
                let self = this;
                self.$http.httpGet('/eim/api/qulification/search/findByEmployeeId/'+employeeId, {
                }).then(function (response) {
                    console.log(response);
                    let datainfo = response.data;
                    console.log(datainfo);
                    for (var i = 0; i < datainfo.length; i++) {

                        if(datainfo[i].expiredate=='2099-09-09'||datainfo[i].expiredate=='2099-09-06'||datainfo[i].expiredate===null) {
                            datainfo[i].expireStatu= '长期有效';
//                                        self.$set(datainfo[i],datainfo[i].cellClassName, {});
                            self.$set(datainfo[i], 'cellClassName',{});
                            self.$set(datainfo[i].cellClassName, 'expireStatu','demo-table-info-cell-name');
//                                        datainfo[i].cellClassName.expireStatu='demo-table-info-cell-name'
                        }else{
                            var now = new Date();

                            if(Date.parse(new Date(datainfo[i].expiredate))<now){

                                datainfo[i].expireStatu='过期'
                            }else{
//                                            self.$set(datainfo[i].cellClassName, {});
                                self.$set(datainfo[i], 'cellClassName',{});
                                self.$set(datainfo[i].cellClassName, 'expireStatu','demo-table-info-cell-age');
                                datainfo[i].expireStatu=datainfo[i].expiredate+'到期'
//                                            self.$set(datainfo[i], 'cellClassName','demo-table-info-cell-age');
//                                            datainfo[i].cellClassName.expireStatu='demo-table-info-cell-age'
                            }
                        }

                        self.PersonQulificationInfolist.push(datainfo[i]);
                    }
                    console.log(self.PersonQulificationInfolist)
                }).catch(function (error) {
                    console.log(error);
                });

//                    },
//                    (error) => {
//                        self.$Message.error('user profile error = ' + error);
//                    });
            },
            updateCheck(index) {
                console.log(this.$router);
                if(this.PersonQulificationInfolist[index].licensetype=='飞行执照') {
                    alert('飞行执照');
                    this.$router.push({
                        path:'/for_pliceninfo',
                        query: {licenseId: this.PersonQulificationInfolist[index].id,employeeId:this.employeeId,employee:this.employee,type:'add'}
                    });
                }
                else{
                    this.$router.push({
                        path: '/newAddLicense',
                        //query: {licenseId: this.PersonQulificationInfolist[index].id,licensetype:this.PersonQulificationInfolist[index].licensetype},
                        query: {licenseId: this.PersonQulificationInfolist[index].id,licensetype:this.PersonQulificationInfolist[index].licensetype,employeeId:this.employeeId,employee:this.employee,type:'add'}
                    });
                }
            },
            cancelform: function (index) {
                this.PersonQulificationInfolist.splice(index, 1);
            },
            confirm (index) {
                this.$Modal.confirm({
                    title: '警告框：',
                    content: '<p>确认删除该条记录？</p>',
                    onOk: () => {
                        this.removeCheck (index);

                    },
                    onCancel: () => {

                    }
                });
            },
            removeCheck(index) {
                let self = this;
                let url = "/eim/api";
                let checkUrl = url + "/qulification/delete";
                console.log("this.PersonQulificationInfolist[index]",this.PersonQulificationInfolist[index])

                self.$http.httpPost(checkUrl,this.PersonQulificationInfolist[index]).then(function (response) {
                    alert("删除成功！")
                    self.cancelform(index);
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
//            dealcancel(){
//                this.add_dialog_show = false;
//                this.check_form = {};
//                this.getPhysicalrecords()
//            }
            addlicense(){
                this.$router.push({path: '/newAddLicense',query:{id:this.employeeId,/*employee:this.employee,pilot:this.pilot,*/type:"add"}});
            }
        },
    }
</script>
<style >
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
    .bottom5 {
        margin-bottom: 15px;
    }
    .child{
        position: absolute;
        top:100px;
        right: 30px;
        width: 283px;
    }
    .ivu-table .demo-table-info-cell-name{

        color: rgb(10,166,73) !important;
    }
    .ivu-table .demo-table-info-cell-age {

        color: rgb(222,53,8) !important;
    }

</style>