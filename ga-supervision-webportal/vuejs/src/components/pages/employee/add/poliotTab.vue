<template>
    <div style="width: 965px; margin-left: 12px;">
       <Form   :label-width="120"  style="width:900px; padding:10px auto; padding-top:5px;padding-bottom: 30px;padding-left: 65px;">
        <h2>飞行员信息</h2>
        <div  class="bottom5" style="border:2px solid rgb(46, 130, 220);height:auto">
            <Row style="margin-top: 10px">
                <Col span="10">
                    <FormItem label="技术身份级别" style="width: 350px;" prop="techClass">
                        <Select  placeholder="请输入">
                            <Option v-for="item in level" :value="item.id">{{item.name}}</Option>
                        </Select>
                    </FormItem>
                </Col>
            </Row>
        </div>
        </Form>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                level: [
                    {
                        name: '副驾驶',
                        id: '副驾驶'
                    }, {
                        name: '机长',
                        id: '机长'
                    }, {
                        name: '理论教员',
                        id: '理论教员'
                    }, {
                        name: '飞行教员',
                        id: '飞行教员'
                    }, {
                        name: '检查员',
                        id: '检查员'
                    }, {
                        name: '考试员',
                        id: '考试员'
                    }, {
                        name: '监察员',
                        id: '监察员'
                    }, {
                        name: '主任教员',
                        id: '主任教员'
                    }, {
                        name: '助理主任教员',
                        id: '助理主任教员'
                    }

                ],

            }

        }
    }

</script>