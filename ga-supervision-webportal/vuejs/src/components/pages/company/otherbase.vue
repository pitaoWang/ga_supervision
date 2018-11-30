<template>
    <div style="width: 1280px; height: 900px; margin-top: 10px;background:white">
        <div>
            <span style="width:93%; display:inline-block;text-align:right; margin-top: -30px">
                   <Button type="primary" icon="plus"
                           style="width: 98px;height: 32px;margin:24px 43px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;"
                           size="small" v-on:click="addotherbasemodal">添加基地</Button>
              </span>
        </div>
        <div class="bottom5" style="width: 1160px; margin-left: 10px">
            <Table border  @on-row-dblclick="dbDetails($event)"  :columns="columns" :data="data1"></Table>
        </div>
        <Modal v-model="add_otherbase_modal" width="400" :loading="loading" title="新增其他基地记录" @on-ok="saveotherbase('otherbase')"
               @on-cancel="cancelhandle" :styles="{top: '20px'}">
            <Form ref="otherbase" :label-width="80" :model="otherbase" :rules ="otherbaseValid" >
                <div>

                        <!-- <Col :md="23">
                             <FormItem label="所属单位ID" class="bottom5" prop="companyId" >
                                 <Input v-model="otherbase.companyId" type="text" :rows="4" placeholder="所属单位ID..." ></Input>
                             </FormItem>
                         </Col>-->


                            <FormItem label="基地名称" class="bottom5" prop="baseName">
                                <Input v-model="otherbase.baseName"   placeholder="基地名称..."></Input>
                            </FormItem>

                            <FormItem label="基地类型" class="bottom5" prop="baseType">
                                <Select v-model="otherbase.baseType">
                                    <Option value="1">飞行基地</Option>
                                    <Option value="2">维修基地</Option>
                                    <Option value="3">训练基地</Option>
                                </Select>
                            </FormItem>

                            <FormItem label="所在省" class="bottom5" prop="baseProvince">
                                <Select v-model="otherbase.baseProvince" placeholder=""
                                        @on-change="getProv(otherbase.baseProvince)">
                                    <Option v-for="item in cityList" :value="item.p" :key="item.p">{{ item.p }}</Option>
                                </Select>
                            </FormItem>

                            <FormItem label="所在市" class="bottom5" prop="baseCity">
                                <Select v-model="otherbase.baseCity" placeholder="">
                                    <Option v-for="item in citys" :key="item.n" :value="item.n">{{ item.n }}</Option>
                                </Select>
                            </FormItem>

                            <FormItem label="详细地址" class="bottom5" prop="baseAddress">
                                <Input v-model="otherbase.baseAddress"
                                       placeholder="详细地址..."></Input>
                            </FormItem>

                            <FormItem label="基地电话" class="bottom5" prop="baseTel">
                                <Input v-model="otherbase.baseTel"   placeholder="基地电话..."></Input>
                            </FormItem>
                </div>
            </Form>
        </Modal>
    </div>

</template>

<script>
    import companydata from '../../../data/company/index.js';

    export default {
        name: "flybase",
        data() {
            return {
                columns: [
                    /*{
                        title: '所属单位',
                        key: 'companyId',
                        render: (h, params) => {
                            let texts = this.companyName;
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },*/
                    {
                        title: '基地名称',
                        key: 'baseName',
                        align:'center',
                    },
                    {
                        title: '基地类型',
                        key: 'baseType',
                        align:'center',
                        render: (h, params) => {

                            let texts = '';
                            if (params.row.baseType == 1) {
                                texts = "飞行基地"
                            } else if (params.row.baseType == 2) {
                                texts = "维修基地"
                            } else if (params.row.baseType == 3) {
                                texts = "训练基地"
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    /*{
                        title: '所在省',
                        key: 'baseProvince',
                    },
                    {
                        title: '所在市',
                        key: 'baseCity',
                    },
                    {
                        title: '详细地址',
                        key: 'baseAddress',
                    },*/
                    {
                        title: '基地电话',
                        key: 'baseTel',
                        align:'center',
                    },
                    {
                        title: '操作',
                        width: 120,
                        align:'center',
                        key: 'action',
                        render: (h, params) => {
                            return h(
                                "div", [
                                    h("Button", {
                                            props: {
                                                type: 'primary',
                                                size: 'small'
                                            },
                                            style: {
                                                marginRight: '16px',
                                                marginTop: '4px',
                                                width: '30px',
                                                height: '30px',
                                                background: 'url(../../../../static/images/icon_operate_edit.png) no-repeat',
                                                border: 'none'
                                            },
                                            on: {
                                                click: () => {
                                                    this.forval(params);

                                                }
                                            }
                                        },
                                        ""
                                    ),
                                    h("Button", {
                                            props: {
                                                type: 'error',
                                                size: 'small'
                                            },
                                            style: {
                                                marginLeft: '4px',
                                                marginTop: '4px',
                                                width: '30px',
                                                height: '30px',
                                                background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                                border: 'none'
                                            },
                                            on: {
                                                click: () => {
                                                    this.deleteotherbase(params);

                                                }
                                            }
                                        },
                                        ""
                                    )
                                ]
                            )
                        }
                    }
                ],
                data1: [],
                cityList: [],
                citys: [],
                companyName: '',
                otherbase: {
                    companyId: '',
                    baseName: '',
                    baseType: '',
                    baseProvince: '',
                    baseCity: '',
                    baseAddress: '',
                    baseTel: ''
                },
                otherbaseValid:{
                    companyId:[{required: true, message: '请输入所属企业', trigger: 'blur'}],
                    baseName:[{required: true, message: '请输入企业名称', trigger: 'blur'}],
                    baseType:[{required: true, message: '请选择基地类型', trigger: 'change'}],
                    baseProvince:[{required: true, message: '请选择所属省', trigger: 'change'}],
                    baseCity:[{required: true, message: '请选择所属市', trigger: 'change'}],
                    baseAddress:[{required: true, message: '请输入详细地址', trigger: 'blur'}],
                    baseTel:[{required: true, message: '请输入基地电话', trigger: 'blur'},{pattern:/^1(3|4|5|7|8)\d{9}$/,message: '基地电话输入不合法', trigger: 'blur'}],
                },
                add_otherbase_modal: false,
                loading: true,
                otherbaseIndex: '',

            }
        },
        methods: {
            //查询飞行基地列表
            searchotherbase: function (companyId) {
                this.$http.httpGet('/eim/api/otherbase/findByCompanyId?companyId=' + companyId).then(res => {
                    if (res.data.resultCode == 200) {
                        this.data1 = res.data.data;
                    }

                }).catch(function (error) {
                    console.log(error);
                })


            },
            //保存基地信息
            saveotherbase: function (name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        debugger;
                        let id = this.otherbase.baseId;
                        this.$profile.getProfile().then((profile) => {
                            this.otherbase.companyId = profile.buz.companyId
                            if (typeof id == 'undefined') {
                                this.$http.httpPost('/eim/api/otherbase/saveotherbase', this.otherbase).then(res => {
                                    if (res.data.resultCode == 200) {
                                        this.data1.push(res.data.data)
                                        this.loading = false;
                                        this.add_otherbase_modal = false;
                                    }
                                }).catch(function (error) {
                                    console.log(error);
                                })
                            } else {

                                this.$set(this.otherbase, 'baseId', id);
                                this.$http.httpPost('/eim/api/otherbase/saveotherbase', this.otherbase).then(res => {
                                    if (res.data.resultCode == 200) {
                                        this.data1.splice(this.otherbaseIndex, 1, this.otherbase)
                                        this.data1[this.otherbaseIndex] = res.data.data;
                                        this.$Message.info("修改成功")
                                        this.loading = false;
                                        this.add_otherbase_modal = false;
                                    }
                                })

                            }
                        })
                    } else {
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 2000);
                        this.$Message.error('验证失败!');
                    }
                })



            },
            //赋值
            forval: function (params) {
                this.add_otherbase_modal = true;
                this.otherbase.companyId = this.data1[params.index].companyId;
                this.otherbase.baseName = this.data1[params.index].baseName;
                this.otherbase.baseId = this.data1[params.index].baseId;
                this.otherbaseIndex = params.index;
                this.otherbase.baseType = this.data1[params.index].baseType;
                this.otherbase.baseProvince = this.data1[params.index].baseProvince;
                this.otherbase.baseCity = this.data1[params.index].baseCity;
                this.otherbase.baseAddress = this.data1[params.index].baseAddress;
                this.otherbase.baseTel = this.data1[params.index].baseTel;
            },
            addotherbasemodal: function () {
                this.add_otherbase_modal = true;
                this.$refs['otherbase'].resetFields();
            },
            cancelhandle:function(){
                this.add_otherbase_modal = false;
                this.$refs['otherbase'].resetFields();
            },
            deleteotherbase: function (params) {
                this.$http.httpDelete('/eim/api/otherbase/deleteotherbase/' + this.data1[params.index].baseId).then((res) => {
                    if (res.data.resultCode == 200) {
                        this.data1.splice(params.index, 1);
                        this.$Message.info("删除成功")
                    }
                })
            },
            getProv: function (prov) {
                let tempCity = [];
                this.citys = [];
                let self = this;
                //self.operationbase.city = self.operationbase.city;
                for (var i = 0; i < this.cityList.length; i++) {
                    if (this.cityList[i].p == prov) {
                        this.citys = this.cityList[i].c;
                    }
                }
            },
            dbDetails:function (column) {
                this.$router.push({path:'/otherbasedetail',query: {otherbase:column }})
            }
        },
        mounted() {
            this.$profile.getProfile().then((profile) => {
                this.otherbase.companyId = profile.buz.companyId
                this.companyName = profile.org.orgName;
                this.searchotherbase(this.otherbase.companyId);
            })
            this.cityList = companydata.citys;
        }
    }
</script>

<style scoped>

</style>