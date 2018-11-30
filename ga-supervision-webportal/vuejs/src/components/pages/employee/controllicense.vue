<style>
    .bottom5 {
        margin-bottom: 15px;
    }
</style>
<template>
    <div style="border:1px solid rgba(1,107,217,.5);background-color:rgba(193,220,248,.5);">
        <h2 style="color: #657180;">执照信息</h2>
        <Row>
            <Col :md="12">
                <FormItem label="执照颁发单位" class="bottom5">
                    <Input v-model="controllicense.givenUnit" placeholder="请输入执照颁发单位"></Input>
                </FormItem>
            </Col>
        </Row>

    </div>
</template>
<script>
    export default {
        data() {
            return {
                controllicense: {
                    givenUnit:'',


                },

            }
        },
    }
</script>