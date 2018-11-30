<template>
    <div>
        <Form :model="resource" :label-width="80">
            <FormItem label="资源名称">
                <Input v-model="resource.resName" placeholder="请输入资源名称"></Input>
            </FormItem>

            <FormItem label="资源排序">
                <Input v-model="resource.sort" placeholder="请输入资源排序"></Input>
            </FormItem>
            <FormItem label="资源链接">
                <Input v-model="resource.href" placeholder="请输入资源链接"></Input>
            </FormItem>
            <FormItem label="资源目标">
                <Input v-model="resource.target" placeholder="请输入资源目标"></Input>
            </FormItem>
            <FormItem label="权限标识">
                <Input v-model="resource.resIdentity" placeholder="请输入资源目标"></Input>
            </FormItem>
            <FormItem label="是否显示">
                <RadioGroup v-model="resource.show">
                    <Radio label="1">是</Radio>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <Radio label="0">否</Radio>
                </RadioGroup>
            </FormItem>
            <FormItem label="选择图标">
                <span @click="iconclick()" style="cursor: pointer;" ref="spanicon">{{dianji}}</span>
            </FormItem>
            <div v-show="iconst">
                <div style="font-size:40px;" ref="icodiv">
                    <Icon type="checkmark"/>
                    <Icon type="ionic"></Icon>
                    <Icon type="arrow-up-a"></Icon>
                    <Icon type="arrow-right-a"></Icon>
                    <Icon type="arrow-down-a"></Icon>
                    <Icon type="arrow-left-a"></Icon>
                    <Icon type="arrow-up-b"></Icon>
                    <Icon type="arrow-right-b"></Icon>
                    <Icon type="arrow-down-b"></Icon>
                    <Icon type="arrow-left-b"></Icon>
                    <Icon type="arrow-up-c"></Icon>
                    <Icon type="arrow-right-c"></Icon>
                    <Icon type="arrow-down-c"></Icon>
                    <Icon type="arrow-left-c"></Icon>
                    <Icon type="arrow-return-right"></Icon>
                    <Icon type="arrow-swap"></Icon>
                    <Icon type="arrow-shrink"></Icon>
                    <Icon type="arrow-expand"></Icon>
                    <Icon type="arrow-move"></Icon>
                    <Icon type="arrow-resize"></Icon>
                    <Icon type="chevron-up"></Icon>
                    <Icon type="chevron-right"></Icon>
                    <Icon type="chevron-down"></Icon>
                    <Icon type="chevron-left"></Icon>
                    <Icon type="navicon-round"></Icon>
                    <Icon type="navicon"></Icon>
                    <Icon type="drag"></Icon>
                    <Icon type="log-in"></Icon>
                    <Icon type="log-out"></Icon>
                </div>
            </div>
        </Form>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                iconst:false,
                sel:'',
                dianji:'点击选择',
                resource: {}
            }

        },
        methods: {
            iconclick:function () {
                let self=this;
                self.iconst=true;
                var lk=self.$refs.icodiv.childNodes;
                for(var k=0;k<lk.length;k++){
                    lk[k].onclick = function tst(){
                        self.sel=event.target;
                        self.iconst=false;
                        self.dianji='';
                        self.resource.icon=self.sel.outerHTML;
                        self.$refs.spanicon.innerHTML=self.sel.outerHTML;
                    };
                };

            },addresourcechind:function (obj) {
                let self = this;
                let ks=self.resource;
                ks.parent=obj;
                self.$http.jsonPost('/app/api/resource',
                    ks
                ).then(function (response) {
                    alert("保存成功");
                    self.resource={};
                    self.$emit('childbegindata','1');
                }).catch(function (error) {
                    console.log(error);
                    self.$emit('childshow','1');
                });
            },showinfo:function (obj) {
                let self=this;
                self.$http.httpGet('/app/api/resource/'+obj,{
                }).then((res) => {
                    self.resource=res.data;
                }).catch(function (error) {
                    console.log(error);
                });
            },cleaninfo:function () {
                this.resource={};
            }
        }
    }
</script>