<style>
    .ivu-collapse-content {
        background-color:  #282828 !important;
    }
    .ivu-collapse>.ivu-collapse-item {
        border-top: 1px solid #3c3c3c !important;
    }
</style>
<template>
    <div style="width: 900px; margin-left: 70px;overflow: hidden;margin-bottom: 50px; ">
        <div style="margin-top: 25px;">
            <span style="font-size: 18px;font-style: italic">系统管理-菜单管理</span>
            <span><Button type="info" style="margin-left: 625px;margin-bottom: 10px;" @click="showmodel()">新增菜单</Button></span>
            <hr style="  height: 3px;  background-color: aliceblue;width: 898px">
        </div>
        <div style="padding-top: 0px;">
            <Modal
                    v-model="modal1"
                    title="菜单编辑"
                    @on-ok="ok"
                    @on-cancel="cancel">
                <div><Detail ref="datailfunc"  v-on:childbegindata="syslist()"></Detail></div>
            </Modal>
        </div>
        <div style="margin-top: 15px;">
            <Collapse accordion v-model="value3" >
                <Panel  style="background: rgb(51, 51, 51);color: white;font-size:16px;" v-for="menu in menulist">
                    {{menu.resName}}
                    <!--<div style="width: 125px;">-->
                    <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style=" margin-right: 5px; margin-left: 610px;margin-bottom: 4px;" @click="showmodel(menu.id)"><span>增加</span></button>
                    <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style="margin-right: 5px;margin-bottom: 4px;" @click="updatemodel(1,menu.id)"><span>修改</span></button>
                    <button type="button" class="ivu-btn ivu-btn-error ivu-btn-small" style="margin-right: 5px;margin-bottom: 4px;" @click="deletemenu(menu.id)"><span>删除</span></button>
                    <!--</div>-->
                    <div slot="content" style="background-color: #282828;">
                        <Collapse accordion v-model="value4" style="background: rgb(51, 51, 51);color: white;font-size: 16px;margin-left: 45px;border: solid 1px #3c3c3c;" v-if="menu.children.length!==0">
                            <Panel v-for="childmenu in menu.children">
                                {{childmenu.resName}}
                                <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-small" style=" margin-right: 5px; margin-left: 598px;margin-bottom: 4px;" @click="updatemodel(menu.id,childmenu.id)"><span>修改</span></button>
                                <button type="button" class="ivu-btn ivu-btn-error ivu-btn-small" style="margin-right: 5px;margin-bottom: 4px;" @click="deletemenu(childmenu.id)"><span>删除</span></button>
                                <p slot="content" >
                                    <CheckboxGroup v-model="social">
                                        <Checkbox label="twitter" style="margin-left: 60px;margin-right: 60px;">
                                            <span>增加</span>
                                        </Checkbox>
                                        <Checkbox label="facebook" style="margin-right: 60px;">
                                            <span>修改</span>
                                        </Checkbox>
                                        <Checkbox label="github"  style="margin-right: 60px;">
                                            <span>删除</span>
                                        </Checkbox>
                                        <Checkbox label="snapchat" style="margin-right: 60px;">
                                            <span>查看</span>
                                        </Checkbox>
                                    </CheckboxGroup>
                                </p>
                            </Panel>
                        </Collapse>
                    </div>
                </Panel>
            </Collapse>
        </div>
    </div>
</template>
<script>
    import Button from "../../../../../node_modules/iview/src/components/button/button";
    import Modal from "../../../../../node_modules/iview/src/components/modal/modal";
    import Detail from "./detail.vue";
    export default {
        data () {
            return {
                pid:null,
                modal1:false,
                value3: '1',
                value4: '1-1',
                menulist:[{
                    id: 3,
                    children: [],
                    operations: [
                        {
                            id: 4,
                            opName: "删除",
                            opIdentity: "delete"
                        },
                        {
                            id: 2,
                            opName: "详细查看",
                            opIdentity: "view"
                        },
                        {
                            id: 3,
                            opName: "修改",
                            opIdentity: "edit"
                        },
                        {
                            id: 1,
                            opName: "列表查看",
                            opIdentity: "list"
                        }
                    ],
                    parentIds: null,
                    resIdentity: "org",
                    resName: "机构管理",
                    sort: 20,
                    href: "/sys/org",
                    target: null,
                    icon: null,
                    show: true,
                    permission: null,
                    createTime: "2017-09-21 03:22:30",
                    updateTime: "2017-09-21 03:23:05",
                    delFlag: 0
                }]
            }
        },
        components: {
            Modal,
            Button,Detail},
        mounted(){
            this.syslist();
        },
        methods: {
            showmodel:function (obj) {
                this.modal1 = true;
                if(obj){
                    this.pid=obj;
                }else{
                }
            },
            updatemodel:function (ks,obj) {
                this.modal1 = true;
                this.pid=ks;
                this.$refs.datailfunc.showinfo(obj);
            },
            ok () {
                this.$refs.datailfunc.addresourcechind(this.pid);
                //this.$Message.info('点击了确定');
            },
            cancel () {
                //this.$Message.info('点击了取消');
                this.$refs.datailfunc.cleaninfo();
            },menumodalshow(){
                this.menumodal=true;
            },syslist:function () {
                let self=this;
                self.$http.httpGet('/app/api/resource',{
                }).then((res) => {
                    self.menulist=res.data;
                }).catch(function (error) {
                    console.log(error);
                });
            },deletemenu:function (obj) {
                let self=this;
                self.$http.httpDelete('/app/api/resource/'+obj,{
                }).then((res) => {
                    alert("删除成功");
                    self.syslist();
                }).catch(function (error) {
                    console.log(error);
                    self.syslist();
                });
            }
        }
    }
</script>
