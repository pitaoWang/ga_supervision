<style>
    .bottom5 {
        margin-bottom: 24px;
    }
</style>
<template>
    <div>
        <!--<h2 style="color: #657180;">执照信息</h2>-->
        <!--<Row>
            <Col :md="12">-->
        <div>
            <FormItem label="执照状态" style="display: inline-block;width: 550px">
                <Select v-model="flightdispatcher.licenseState" placeholder="请选择">
                    <Option value="在用">在用</Option>
                    <Option value="作废">作废</Option>
                </Select>
            </FormItem>
            <!--</Col>
            <Col :md="11">-->
            <FormItem label="执照类型" style="display: inline-block;width: 550px" >
                <Select v-model="flightdispatcher.licenseProp" placeholder="请选择">
                    <Option value="临时">临时</Option>
                    <Option value="永久">永久</Option>
                </Select>
            </FormItem>
            <!--   </Col>
           </Row>-->
        </div>
        <div>
            <!--<Row>
                <Col :md="12">-->
            <FormItem label="执照颁发人" style="display: inline-block;width: 550px">
                <Input v-model="flightdispatcher.holderName" placeholder="请输入执照颁发人"></Input>
            </FormItem>
            <!--</Col>
            <Col :md="11">-->
            <FormItem label="持证人执业公司" style="display: inline-block;width: 550px">
                <Input v-model="flightdispatcher.companyId" placeholder="请输入持证人执业公司ID"></Input>
            </FormItem>
            <!--</Col>
        </Row-->
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                flightdispatcher: {
                    id: '',
                    licenseState: '',
                    licenseProp: '',
                    holderName: '',
                    companyId: '',
                },
            }
        },
        methods: {
        },

    }
</script>