<template>
    <div>
        <div style="border:1px solid rgb(46, 130, 220);margin-top: 16px;margin-left: 8px;width: 965px;background: rgba(193, 220, 248, 0.5);position: absolute;top:169px;bottom: 0px;"></div>
        <div  style="margin-top: 16px;margin-left: 9px;">
                <span style="width:100%;display:inline-block;text-align:right;">
                    <button v-on:click="addlicense()" data-v-6cb0b875="" type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style="width: 133px; height: 32px; margin: 24px 56px 11px 0px; border: 1px dashed rgb(43, 130, 218); background: rgb(178, 214, 251); font-size: 14px; color: rgb(36, 128, 221);"><i class="ivu-icon ivu-icon-plus"></i> <span>增  加</span></button>
                </span>
                  <div class="bottom5" style="width: 870px;margin-left: 47px" >
                    <Table border :columns="columns7" :data="PersonQulificationInfolist"></Table>
                  </div>
        </div>
    </div>
</template>
<script>
    import word from '../employee/licenseinfo.vue';
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
                        width: 252
                    },
                    {
                        title: '执照名称',
                        width: 250,
                        key: 'licensetype',
                        align: 'center'
                    },
                    {
                        title: '执照状态',
                        key: 'expireStatu',
                        align: 'center',
                        width: 217,
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.updateCheck(params.index)
                                        }
                                    }
                                }, '修改'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.confirm(params.index)
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],

                add_dialog_show: false,
            }
        },
        props:['employeeId'],
        created() {


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
//                self.$profile.getProfile().then((profile) => {
//                    let companyid = profile.buz.companyId;
//                        if (profile.buz && profile.buz.employeeId) {
//                            self.employeeId = profile.buz.employeeId;
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
                    this.$router.push({
                        path: '/for_liceninfo',
                        query: {licenseId: this.PersonQulificationInfolist[index].id}
                    });
                }
                else{
                    this.$router.push({
                        path: '/for_addlicen',
                        query: {licenseId: this.PersonQulificationInfolist[index].id,licensetype:this.PersonQulificationInfolist[index].licensetype}
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

                this.$router.push({path: '/for_addlicen'});
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