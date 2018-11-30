<template>

    <div style="width: 1280px; height: 900px; margin-top: 60px; background:white ;padding-top: 40px;">
        <div style="border: 1px solid #f0f0f0;   margin-left: 40px; margin-right: 40px;" >
            <Form ref="operationbase" :model="operationbase" :label-width="100" style="" :rules="operationValid">
                <div style="width:1100px;  padding-top: 60px; margin-right: 45px;margin-left: 45px; ">
                    <FormItem label="基地名称" style=" width:500px ;display: inline-block; " prop="baseName">
                        　　　<Input v-model="operationbase.baseName" placeholder="" style=""></Input>　　　
                    </FormItem>
                    <FormItem label="基地类型" style="float:right;width:500px ;display: inline-block;" prop="baseType">
                        <Select v-model="operationbase.baseType">
                            <Option value="1">运行基地</Option>
                            <Option value="2">飞行基地</Option>
                            <Option value="3">维修基地</Option>
                            <Option value="4">训练基地</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="主基地" style="float:right;width:500px ;display: inline-block;" prop="isMainbase">
                        <RadioGroup v-model="operationbase.isMainbase">
                            <Radio label="1">是</Radio>
                            <Radio label="2">否</Radio>
                        </RadioGroup>
                    </FormItem>
                    <FormItem label="所在省" style="width:500px ;display: inline-block;" prop="provience">
                        <Select v-model="operationbase.provience" placeholder="请选择省"
                                @on-change="getProv(operationbase.provience)">
                            <Option v-for="item in cityList" :value="item.p" :key="item.p">{{ item.p }}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="所在市" style="width:500px ;display: inline-block;" prop="city">
                        <Select v-model="operationbase.city" placeholder="请选择市">
                            <Option v-for="item in citys" :key="item.n" :value="item.n">{{ item.n }}</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="基地地址" style="float:right;width:500px ;display: inline-block;" prop="address">
                        <Input v-model="operationbase.address" placeholder="" style=""></Input>
                    </FormItem>
                    <FormItem label="基地电话" style="width:500px ;display: inline-block;" prop="tel">
                        <Input v-model="operationbase.tel" placeholder="" style=""></Input>
                    </FormItem>

                </div>
                <!--基地教室-->

                <div>
                <span style="width:99%; display:inline-block;text-align:right; margin-top: -30px">
                    <Button type="primary" icon="plus"
                            style="width: 98px;height: 32px;margin:24px 43px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;"
                            size="small" @click="addclass()">添加</Button>
                </span>
                </div>

                <Table border style="width: 1100px; margin-left: 45px;margin-right: 45px; margin-bottom: 40px" :columns="classroomcols"
                       :data="classroomdata"></Table>


                <!--承担课程-->
                <div>
                <span style="width:99%; display:inline-block;text-align:right; margin-top: -30px">
                    <Button type="primary" icon="plus"
                            style="width: 98px;height: 32px;margin:24px 43px 11px 0px;border: 1px dashed #2b82da;background: #DDF4FF;font-size: 14px;color: #2FA5FB;"
                            size="small" @click="addcourse">添加</Button>
                </span>
                </div>
                <Table border style="width: 1100px;margin-left: 45px;margin-right: 45px;  margin-bottom: 40px" :columns="coursecols"
                       :data="coursedata"></Table>
            </Form>

            <div class="bottom5" style="margin-top:10px;height:180px;">
                <div class="bottom5" style="margin-top:20px;height:40px;">
                    <div style="padding-left:100px;float: right; text-align: center; padding-bottom: 10px; padding-top: 10px; margin-right:60px;">
                        <Button primary href="javascript:;" @click="submitOperation('operationbase')"
                                class="gacompany-btton">保存
                        </Button>

                        <Button primary href="javascript:;" @click="blackOperation" class="gacompany-btton">返回</Button>
                    </div>
                </div>

            </div>
        </div>
        <Modal v-model="add_classroom_modal" width="400" :title=classroomtitle @on-ok="saveclassroom"
               @on-cancel="add_classroom_modal = false">
            <Form :label-width="100" v-model="classroom">
                <div>


                    <FormItem label="教室类型" class="bottom5">
                        <Select v-model="classroom.classroomType">
                            <Option value="1">教室</Option>
                            <Option value="2">讲评室</Option>
                        </Select>
                    </FormItem>


                    <FormItem label="教室地址" class="bottom5">
                        <Input v-model="classroom.address" type="text" :rows="4"
                               placeholder=""></Input>
                    </FormItem>


                    <FormItem label="学员最大容量" class="bottom5">
                        <Input v-model="classroom.maxStu" type="text" :rows="4"
                               placeholder=""></Input>
                    </FormItem>


                </div>
            </Form>
        </Modal>
        <Modal v-model="add_course_modal" width="400" :title=coursetitle @on-ok="savecourse"
               @on-cancel="add_course_modal = false">
            <Form :label-width="90" v-model="course">
                <div>

                    <FormItem label="课程等级" class="bottom5">
                        <Select v-model="course.courseGrade">
                            <Option value="1">等级一</Option>
                            <Option value="2">等级二</Option>
                        </Select>
                    </FormItem>


                    <FormItem label="训练大纲ID" class="bottom5">
                        <Input v-model="course.syllabusId" type="text" :rows="4"
                               placeholder="请输入训练大纲ID..."></Input>
                    </FormItem>


                    <FormItem label="课程分类" class="bottom5">
                        <Select v-model="course.courseType">
                            <Option value="1">课程一</Option>
                            <Option value="2">课程二</Option>
                        </Select>
                    </FormItem>
                    <FormItem label="生效日期"  prop="effectiveDate">

                        <DatePicker type="date" style="width:280px;" placeholder="选择日期" v-model="course.effectiveDate"></DatePicker>
                    </FormItem>

                    <FormItem label="最大容量" class="bottom5">
                        <Input v-model="course.capacity" type="text" :rows="4" placeholder="请输入最大容量..."></Input>
                    </FormItem>
                </div>
            </Form>
        </Modal>
    </div>
</template>

<script>
    import companydata from '../../../data/company/index.js';
    import Cookies from 'js-cookie';
    export default {
        name: "operationbaseadd",
        data() {
            return {
                cityList: [],
                citys: [],
                classroomtitle: '',
                coursetitle: '',
                classroomcols: [
                    {
                        title: '教室类型',
                        key: 'classroomType',
                        align:'center',
                        width:240,
                        render:(h,params)=>{
                            let texts = '';
                            if(params.row.classroomType==1){
                                texts = '教室'
                            }else if(params.row.classroomType==2){
                                texts='讲评室'
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    {
                        title: '教室地址',
                        key: 'address',
                        align:'center',
                        width:380,
                    }, {
                        title: '学员最大容量',
                        key: 'maxStu',
                        align:'center',
                        width:313,
                    },
                    {
                        title: "操作",
                        key: "action",
                        width: 165,
                        align: "center",
                        render: (h, params) => {
                            return h("div", [
                                h(
                                    "Button",
                                    {
                                        props: {
                                            type: "primary",
                                            size: "small"
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
                                                this.classroomtitle = '修改基地教室信息';
                                                this.add_classroom_modal = true;
                                                this.classroom.classroomId = this.classroomdata[params.index].classroomId;
                                                this.classroomIdex = params.index;
                                                this.classroom.classroomType = this.classroomdata[params.index].classroomType;
                                                this.classroom.address = this.classroomdata[params.index].address;
                                                this.classroom.maxStu = this.classroomdata[params.index].maxStu;
                                            }
                                        }

                                    },
                                    ""
                                ),
                                h(
                                    "Button",
                                    {
                                        props: {
                                            type: "error",
                                            size: "small"
                                        },
                                        style: {
                                            marginRight: '16px',
                                            marginTop: '4px',
                                            width: '30px',
                                            height: '30px',
                                            background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                            border: 'none'
                                        },
                                        on: {
                                            click: () => {
                                                let index = params.index;
                                                let classroomId = this.classroomdata[index].classroomId;
                                                console.log(classroomId + "classId");
                                                this.deteteClassroom(classroomId, index);

                                            }
                                        }

                                    },
                                    ""
                                )
                            ]);
                        }


                    },

                ],
                classroomdata: [],
                classroomIndex: '',
                //承担课程
                coursecols: [
                    {
                        title: '课程等级',
                        key: 'courseGrade',
                        align:'center',
                        width:150,
                        render:(h,params)=>{
                            let texts = '';
                            if(params.row.courseGrade==1){
                                texts = '等级一'
                            }else if(params.row.courseGrade==2){
                                texts='等级二'
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    {
                        title: '训练大纲',
                        key: 'syllabusId',
                        align:'center',
                        width:220,
                    },
                    {
                        title: '课程分类',
                        key: 'courseType',
                        align:'center',
                        width:200,
                        render:(h,params)=>{
                            let texts = '';
                            if(params.row.courseType==1){
                                texts = '课程一'
                            }else if(params.row.courseType==2){
                                texts='课程二'
                            }
                            return h('div', {
                                props: {},
                            }, texts)
                        }
                    },
                    {
                        title: '生效日期',
                        key: 'effectiveDate',
                        align:'center',
                        width:180,
                    },
                    {
                        title: '最大容量',
                        key: 'capacity',
                        align:'center',
                        width:183,
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 165,
                        align:'center',
                        render: (h, params) => {
                            return h("div", [
                                h(
                                    "Button",
                                    {
                                        props: {
                                            type: "primary",
                                            size: "small"
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
                                                this.coursetitle = '修改承担课程信息';
                                                this.add_course_modal = true;
                                                this.course.takecourseId = this.coursedata[params.index].takecourseId;
                                                this.courseIndex = params.index;
                                                this.course.courseGrade = this.coursedata[params.index].courseGrade;
                                                this.course.syllabusId = this.coursedata[params.index].syllabusId;
                                                this.course.courseType = this.coursedata[params.index].courseType;
                                                this.course.effectiveDate = this.coursedata[params.index].effectiveDate;
                                                this.course.capacity = this.coursedata[params.index].capacity;
                                            }
                                        }

                                    },
                                    ""
                                ),
                                h(
                                    "Button",
                                    {
                                        props: {
                                            type: "error",
                                            size: "small"
                                        },
                                        style: {
                                            marginRight: '16px',
                                            marginTop: '4px',
                                            width: '30px',
                                            height: '30px',
                                            background: 'url(../../../../static/images/icon_operate_delete.png) no-repeat',
                                            border: 'none'
                                        },
                                        on: {
                                            click: () => {
                                                let index = params.index;
                                                let takecourseId = this.coursedata[index].takecourseId;
                                                this.deleteCourse(takecourseId, index);
                                            }
                                        }

                                    },
                                    ""
                                ),
                            ])
                        }
                    }
                ],
                coursedata: [],
                courseIndex: '',
                //运行基地表单v-model对象
                operationbase: {
                    companyId: '',
                    baseName: '',
                    baseType: '',
                    tel: '',
                    provience: '',
                    city: '',
                    address: '',
                    isMainbase:'1',
                },
                //基地教室表单v-model对象
                classroom: {
                    classroomType: '',
                    address: '',
                    maxStu: '',
                },
                //承担课程v-model对象
                course: {
                    courseGrade: '',
                    syllabusId: '',
                    courseType: '',
                    capacity: '',
                    effectiveDate:''
                },
                //基地教室添加对话框默认关闭
                add_classroom_modal: false,
                //承担课程添加对话框默认关闭
                add_course_modal: false,
                //运行基地表单验证
                operationValid: {
                    baseName: [{required: true, message: '请输入基地名称', trigger: 'blur'}],
                    baseType: [{required: true, message: '请选择基地类型', trigger: 'change'}],
                    tel: [{required: true, message: '请输入基地电话', trigger: 'blur'},{pattern:/^1(3|4|5|7|8)\d{9}$/,message: '基地电话输入不合法', trigger: 'blur'}],
                    provience: [{required: true, message: '请选择所属省', trigger: 'change'}],
                    city: [{required: true, message: '请选择所属市', trigger: 'change'}],
                    address: [{required: true, message: '请输入详细地址', trigger: 'blur'}]
                }
            }
        },
        methods: {
            //保存基地教室信息
            saveclassroom: function () {
                console.log(this.classroom.classroomId + "classid");
                let self = this;
                let id = this.classroom.classroomId;
                if (typeof id == 'undefined') {
                    self.$http.httpPost('/eim/api/operation/saveclassroom', self.classroom).then(res => {
                        if (res.data.resultCode == 200) {
                            self.classroom = {};
                            self.classroomdata.push(res.data.data);
                            self.$Message.info("保存成功")
                        }
                    })
                } else {
                    //this.classroom.set('classroomId',id);
                    this.$set(this.classroom, 'classroomId', id);
                    self.$http.httpPost('/eim/api/operation/saveclassroom', self.classroom).then(res => {
                        if (res.data.resultCode == 200) {
                            self.classroom = {};
                            self.classroomdata.splice(self.classroomIdex, 1, self.classroom)
                            self.classroomdata[self.classroomIdex] = res.data.data;
                            self.$Message.info("修改成功")
                        }
                    })
                }

            },
            //保存运行基地信息包括基地教室、课程
            submitOperation: function (name) {
                let self = this
                let request = {}
                request = {
                    operationBase: self.operationbase,
                    classrooms: self.classroomdata,
                    courses: self.coursedata
                }
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.$Message.success("验证成功")
                        this.$http.jsonPost('/eim/api/operation/saveoperationall', request).then((res) => {
                            if (res.data.resultCode == 200) {
                                this.$Message.success("保存成功")
                            }
                        })
                    } else {
                        this.$Message.error('验证失败!')
                    }
                })
            },
            //保存承担课程信息
            savecourse: function () {
                let self = this;
                let id = this.course.takecourseId;
                if (typeof id == 'undefined') {

                    self.$http.httpPost('/eim/api/operation/savetakecourse', self.course).then((res) => {
                        if (res.data.resultCode == 200) {
                            self.course = {};
                            self.coursedata.push(res.data.data);
                            self.$Message.info('保存成功');
                        }
                    })
                } else {
                    this.$set(this.course, 'takecourseId', id);
                    self.$http.httpPost('/eim/api/operation/savetakecourse', self.course).then((res) => {
                        if (res.data.resultCode == 200) {
                            self.course = {};
                            self.coursedata.splice(self.courseIndex, 1, self.course);
                            self.coursedata[self.courseIndex] = res.data.data;
                            self.$Message.info("修改成功")
                        }
                    })
                }

            },
            addcourse:function(){
                this.add_course_modal=true
                this.coursetitle='新增承担课程记录'
                this.course={}
                this.$refs['course'].resetFields();
            },
            addclass:function(){
                this.add_classroom_modal=true
                this.classroomtitle='新增基地教室记录'
                this.classroom={}
                this.$refs['classroom'].resetFields();
            },
            //删除基地教室
            deteteClassroom: function (classroomId, index) {
                this.$http.httpDelete('/eim/api/operation/deletebaseroombyid?classroomId=' + classroomId).then((res) => {
                    if (res.data.resultCode == 200) {
                        this.classroomdata.splice(index, 1);
                        this.$Message.info("删除成功，id=" + res.data);
                    }
                })
            },
            //删除承担课程
            deleteCourse: function (takecourseId, index) {
                this.$http.httpDelete('/eim/api/operation/deletetakecoursebyid?takecourseId=' + takecourseId).then((res) => {
                    if (res.data.resultCode == 200) {
                        this.coursedata.splice(index, 1);
                        this.$Message.info("删除成功")
                    }
                })
            },
            blackOperation: function () {
                this.$router.push({path:'/company/company',query: {value:'operationbase' }})
            },
            getProv: function (prov) {
                let tempCity = [];
                this.citys = [];
                let self = this;
                self.operationbase.city='';
                //self.operationbase.city = self.operationbase.city;
                for (var i = 0; i < this.cityList.length; i++) {
                    if (this.cityList[i].p == prov) {
                        this.citys = this.cityList[i].c;
                    }
                }
            },
        },
        mounted() {
            /*this.$profile.getProfile().then((profile) => {*/
            let buz = JSON.parse(Cookies.get("profile"));
                this.operationbase.companyId = buz.buz.companyId
            /*})*/
            this.cityList = companydata.citys;
        }
    }
</script>

<style scoped>

</style>