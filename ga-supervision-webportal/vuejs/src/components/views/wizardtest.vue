<Button @click="show = true">设置宽度</Button>

        <Modal v-model="show" width="600"
               title="普通的Modal对话框标题"
               @on-ok="show = false"
               @on-cancel="show = false">
            <Form :label-width="80">
                <form-wizard @on-complete="onComplete" ref="wizard" shape="cicle" color="#3498db">

                    <tab-content title="填写基础资料">
                        <FormItem label="输入框">
                            <Input placeholder="请输入"></Input>
                        </FormItem>
                    </tab-content>
                    <tab-content title="填写角色信息">
                        <FormItem label="选择器">
                            <Select placeholder="请选择">
                                <Option value="beijing">北京市</Option>
                                <Option value="shanghai">上海市</Option>
                                <Option value="shenzhen">深圳市</Option>
                            </Select>
                        </FormItem>
                    </tab-content>
                    <tab-content title="填写资质信息">
                        <FormItem label="日期控件">
                            <Row>
                                <Col span="11">
                                <DatePicker type="date" placeholder="选择日期"></DatePicker>
                                </Col>
                                <Col span="2" style="text-align: center">
                                -</Col>
                                <Col span="11">
                                <TimePicker type="time" placeholder="选择时间"></TimePicker>
                                </Col>
                            </Row>
                        </FormItem>
                    </tab-content>
                    <tab-content title="填写体检信息">
                        <FormItem label="文本域">
                            <Input type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></Input>
                        </FormItem>

                    </tab-content>

                    <template slot="footer" scope="props">
                        <div class="wizard-footer-left">
                            <wizard-button @click.native="props.prevTab()" style="text-align:left;">
                                <Icon type="chevron-left" style="font-size:48px;color:#3498db"></Icon>
                            </wizard-button>
                        </div>
                        <div class="wizard-footer-right" style="width:90px;">
                            <wizard-button @click.native="props.nextTab()" style="text-align:right;">
                                <Icon type="chevron-right" style="font-size:48px;color:#3498db"></Icon>
                            </wizard-button>
                        </div>

                    </template>
                </form-wizard>
            </Form>
        </Modal>
    </div>