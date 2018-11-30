<template>
    <div class="content" style="margin-left:15px;">
        <Breadcrumb separator="/">
            <BreadcrumbItem href="/">
                <Icon type="android-apps" style="font-size:18px;"></Icon>
                系统设置
            </BreadcrumbItem>
            <BreadcrumbItem>个人信息</BreadcrumbItem>
        </Breadcrumb>
        <hr style="">

        <Form :model="employee" :label-width="100" style="width:900px; margin:10px auto;background-color: #363636;">
            <div style="width: 900px">
                <div style="width:600px;float: left;margin-top: 35px">
                    <FormItem label="姓名" style="width:590px;">
                        <Input v-model="employee.userName" placeholder="请输入"></Input>
                    </FormItem>
                    <FormItem label="姓" style="width:590px;">
                        <Input v-model="employee.familyName" placeholder="请输入"></Input>
                    </FormItem>
                    <FormItem label="名" style="width:590px;">
                        <Input v-model="employee.givenName" placeholder="请输入"></Input>
                    </FormItem>
                    <FormItem label="生日" style="width: 298px;">
                        <Input v-model="employee.year" placeholder="请输入"></Input>
                    </FormItem>
                    <FormItem label="生日时区" style="width: 590px;">
                        <Input v-model="employee.timezone" placeholder="请输入"></Input>
                    </FormItem>
                    <FormItem label="性别" style="display: inline-block; width: 298px;">
                        <Select v-model="employee.gender" placeholder="请选择">
                            <Option value="1">男</Option>
                            <Option value="2">女</Option>
                        </Select>
                    </FormItem>
                </div>
                <Input v-model="employee.employeeId" placeholder=""></Input>
                <Button type="ghost" class="navi-btn" v-on:click="handleSubmit()">提交</Button>
                <Button type="ghost" class="navi-btn" v-on:click="getData()" style="margin-left: 8px;margin-top: 17px">
                    取消
                </Button>
            </div>
        </Form>
    </div>

</template>
<script>
    export default {

        data () {
            return {
                employee: {}
            }
        },
        created () {
            this.getData()
        },

        methods: {
            getData () {
                let self = this;
                let employeeId = 2;
                let url = '/api/employee/' + employeeId;
                self.$http.httpGet(url, {}).then((res) => {
                    let result = res.data;
                    if (result.resultCode == 200) {
                        self.employee = result.data;
                    }
                    else {
                        console.log(result.message)
                    }
                })
            },
            handleSubmit: function () {
                let self = this;
                let url = '/api/employee/save';
                self.$http.httpPost(url, self.employee).then(function (response) {
                    console.log(response);
                }).catch(function (error) {
                    console.log(error);
                });
            }
        }
    }
</script>