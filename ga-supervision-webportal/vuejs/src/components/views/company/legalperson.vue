<template>
    <div>
        <table class='companydetail'>
            <tbody>
                <tr>
                    <td class="tr-1-company tr-3-company">法人姓名</td>
                    <td class="tr-2-company tr-4-company">{{legalperson.legalpersonName}}</td>
                </tr>

                <tr>
                    <td class="tr-1-company tr-5-company">性别</td>
                    <td class="tr-2-company tr-6-company">{{legalperson.gender}}</td>
                </tr>
                <tr>
                    <td class="tr-1-company tr-3-company">身份证号码</td>
                    <td class="tr-2-company tr-4-company">{{legalperson.cardid}}</td>
                </tr>
                <tr>
                    <td class="tr-1-company tr-5-company">联系电话</td>
                    <td class='tr-2-company tr-6-company'>{{legalperson.telephone}}</td>
                </tr>
                <tr>
                    <td class="tr-1-company tr-3-company" style="border-bottom: 0">电子邮箱</td>
                    <td class="tr-2-company tr-4-company" style="border-bottom: 0">{{legalperson.email}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                legalperson:{},
            }
        },
        props: ['companyId'],
        created:function () {
        },
        watch: {
            // 如果 question 发生改变，这个函数就会运行
            companyId: function (newval) {
                this.getData(newval);
            }
        },

        methods: {
            getData:function (newval){
                let self = this;
                let comId = self.companyId
                self.$http.httpGet('/app/api/legalperson/findByCompanyId?companyId='+comId).then(function (response) {
                    if(response.data){
                        self.legalperson=response.data;
                    }
                    if(self.legalperson.gender == 1){
                        self.legalperson.gender = '男'
                    }
                    else{
                        self.legalperson.gender = '女'
                    }
                    console.log(response.data,'.................self.legalperson//////////////');
                }).catch(function (error) {
                    console.log(error);

                });
            }
        }
    }
</script>
