<template>
    <div>
        <Modal
                :title=modaltitle
                v-model="showimg"
                :mask-closable="false"
                on-ok="closemodal"
                :styles="{width:'850px'}"
        >
            <img :src="showimgpath" style="width: auto;max-width: 800px; margin:5px auto; height: 500px;padding-bottom: 20px;">
            <div slot="footer" style="text-align: center;">
                <Button type="info" size="large" @click="closemodal">关闭</Button>
            </div>
        </Modal>

    </div>

</template>
<script>

    export default{
        data(){
            return{
                showimg:false,
                modaltitle:'图片详情',
                showimgpath:'',
            }
        },
        methods:{
            initimgmodal(initmodaltitle,initshowimgpath)
            {
                this.showimg=true;
                if(initmodaltitle!='')
                {
                    this.modaltitle=initmodaltitle;
                }
                this.showimgpath=initshowimgpath;
            },
            closemodal:function()
            {
                this.showimg=false;
                this.showimgpath='';
            },
        }
    }

</script>