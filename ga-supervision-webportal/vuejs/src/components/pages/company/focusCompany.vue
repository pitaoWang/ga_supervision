<template>
    <div class="container_div">
        <Form ref="gacompany" :model="gacompany" :label-width="100">
            <FormItem label="企业名称" :label-width="70" style="width:400px;float: left;margin-left: 0px;" prop="companyName">
                <Input v-model="gacompany.companyName" placeholder="请输入企业名称"></Input>
            </FormItem>
            <FormItem label="法人代表" :label-width="70" style="width:400px;float: left;margin-left: 40px;" prop="legalPerson">
                <Input v-model="gacompany.legalPerson" placeholder="请输入法人代表"></Input>
            </FormItem>

            <FormItem  style="width:100px; height: 20px; float: left;margin-left:40px;">
                <Button type="primary" @click="inint">查询</Button>
            </FormItem>
            <FormItem  style="width:100px;height: 20px; float: left;margin-left: -32px; ">
                <Button type="primary" @click="reset('gacompany')">重置</Button>
            </FormItem>
            <FormItem  style="width:100px;height: 20px; float: left;margin-left: -32px; ">
                <Button type="primary"  @click="focus()"><Icon type="ios-download-outline"></Icon> 设置关注</Button>
            </FormItem>
        </Form>
        <div style="padding-top: 60px;width: 100%;">
            <Table  :columns="columns" :data="data"  @on-selection-change="handleRowChange"  ref="selection" style="width: 100%;"></Table>
   <!--         <Page :total="totalPages"  @on-change="pageclick" @on-page-size-change="pagesizeclick" :page-size="pageSize" style="height: 50px;float:right;padding-right:32px;margin-top: 8px;" show-sizer ></Page>
      -->
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    export default {
        name: "companysuper",
        data(){
            return{
                columns: [
                    {
                        type: 'selection',
                        key: 'comapnayId',
                        align: 'center'
                    },

                    {
                        title: '企业名称',
                        key: 'name',
                        align:'center',

                    },

                    {
                        title: '主运行基地',
                        key: 'mainBase',
                        align:'center'
                    },
                    {
                        title: '运行合格证类型',
                        key:'operateType',
                        align:'center'
                    },
                    {
                        title: '法定代表人',
                        key: 'legalPerson',
                        align:'center'
                    },
                    {
                        title: '企业联系电话',
                        key: 'telephone',
                        align:'center',
                    },
                    {
                        title: '经营项目',
                        key: 'businessType',
                        align:'center'
                    },
                ],
                selections:[],
                data:[],
                gacompany:{
                    companyName:'',
                    legalPerson:'',
                }
            }
        },
       /* watch:{
            data:function(){
            this.$nextTick(function(){
                this.handleSelectAll(true)
               });
            }

        },*/
        mounted(){
            this.inint();

        },
        methods:{
            /*handleSelectAll (status) {
                this.$refs.selection.selectAll(status);
            },*/
            handleRowChange(currentRow){
                this.selections = currentRow;
            },
            focus: function() {
                console.info(this.selections);
                var arr = this.selections;
                let multis = [];
                for (var i=0; i < arr.length; i++) {
                    multis.push(arr[i].companyId);
                }
                let self = this;
                let userInfo = JSON.parse(Cookies.get("userInfo"));
                let profile = JSON.parse(Cookies.get("profile"));
                let data={};
                let url='';
                url='/eim/fousCompany/saveFocusCompany';
                //data.supervisionId=profile.org.orgId;
                data.superId=userInfo.companyId;
                data.companyIds=multis.toString();
                self.$http.httpPost(url, data).then((res) => {
                    console.info(res);
                    if (res.status == 200)  {
                        this.$Message.success('设置成功！');
                        profile.buz.companyId=multis.toString();
                        Cookies.set("profile",profile);
                    } else {
                        this.$Message.error('设置失败!');
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            inint: function () {
                let self = this;
                let url='';
                let data={};
                let user = JSON.parse(Cookies.get("userInfo"));
                let profile = JSON.parse(Cookies.get("profile"));
                console.log(JSON.stringify(profile))
                url='/eim/fousCompany/getCompanyListByManageId';

                //data.supervisionId=profile.org.orgId;
                data.manageId=user.companyId;

                data.type= profile.buz.type;
                // data.companyName = self.gacompany.companyName;
                // data.legalPerson = self.gacompany.legalPerson;
                self.$http.httpGet(url, data).then((res) => {
                    self.data = res.data;

                }).catch(function (error) {
                    console.log(error);
                });
                /* },
                 (error) =>
                 {
                     console.log('error', error);
                 }
             )*/

            },
            pageclick:function(obj){
                this.page = obj-1;
                this.inint();
            },
            pagesizeclick:function (obj) {
                this.pageSize=obj;
                this.inint();
            },
            reset:function (name) {
                this.$refs[name].resetFields();
            },

            getMockData () {
                let mockData = [];
                for (let i = 1; i <= 20; i++) {
                    mockData.push({
                        key: i.toString(),
                        label: 'Content ' + i,
                        description: 'The desc of content  ' + i,
                        disabled: Math.random() * 3 < 1
                    });
                }
                return mockData;
            },
            getTargetKeys () {
                return this.getMockData()
                    .filter(() => Math.random() * 2 > 1)
                    .map(item => item.key);
            },
            handleChange3 (newTargetKeys) {
                this.targetKeys3 = newTargetKeys;
            },
            render3 (item) {
                return item.label + ' - ' + item.description;
            },
            reloadMockData () {
                this.data3 = this.getMockData();
                this.targetKeys3 = this.getTargetKeys();
            }

        }

    }
</script>

<style scoped>

</style>