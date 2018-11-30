<style>
    .runway{
        width: 964px;
        border-spacing: 0px;
        border: 1px solid rgb(1,107,217);
        text-align: left;
        margin-bottom: 10px;
    }
    .tr-1-company{
        width:200px;
    }

    .tr-2-company{
        width:282px;
    }

</style>
<template>
    <ul><li  v-for="flightrunwaydetail in items">
    <table class='runway'>
        <tbody><tr>
            <td class="tr-1-company tr-3-company">跑道编号</td>
            <td class="tr-2-company tr-4-company">{{flightrunwaydetail.runwayNo}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">跑道运行类别</td>
            <td class="tr-2-company tr-4-company">{{flightrunwaydetail.runwayType}}</td>
        </tr><tr>
            <td class="tr-1-company tr-5-company">跑道长度</td>
            <td class="tr-2-company tr-6-company">{{flightrunwaydetail.length}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">中心点高度</td>
            <td class="tr-2-company tr-6-company">{{flightrunwaydetail.height}}</td>
        </tr><tr>
            <td class="tr-1-company tr-3-company">中心点经度</td>
            <td class="tr-2-company tr-4-company">{{flightrunwaydetail.longitude}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">中心点纬度</td>
            <td class="tr-2-company tr-4-company">{{flightrunwaydetail.latitude}}</td>
        </tr><tr>
            <td class="tr-1-company tr-5-company">跑道仪表着陆设备是否具备</td>
            <td class="tr-2-company tr-6-company" >{{ flightrunwaydetail.islandingDevice==1?"是":"否"}}</td>
            <td class="tr-1-company tr-5-company tr-l-company">ILS频率</td>
            <td class="tr-2-company tr-6-company" >{{flightrunwaydetail.ils}}</td>
        </tr><tr>
            <td class="tr-1-company tr-3-company">助航灯光是否具备</td>
            <td class="tr-2-company tr-4-company">{{ flightrunwaydetail.islamplight==1?"是":"否"}}</td>
            <td class="tr-1-company tr-3-company tr-l-company">是否可供固定翼飞机起降</td>
            <td class="tr-2-company tr-4-company">{{flightrunwaydetail.isfixedAerofoil==1?"是":"否"}}</td>
        </tr>
        <tr>
            <td class="tr-1-company tr-5-company" style="border-bottom: 0">是否可供直升机起降</td>
            <td class="tr-2-company tr-6-company" style="border-bottom: 0">{{flightrunwaydetail.ishelicopter==1?"是":"否"}}</td>
            <td class="tr-1-company tr-5-company tr-l-company" style="border-bottom: 0"></td>
            <td class="tr-2-company tr-6-company" style="border-bottom: 0"></td>
        </tr>
        </tbody></table>
    </li></ul>
</template>
<script>
    export default {
        data () {
            return {
                    items: [
                    ]
            }},
        props: ['idinfobase'],
        watch: {
            // 如果 question 发生改变，这个函数就会运行
            idinfobase: function (newval) {
                this.getData(newval);
            }
        },
        methods: {
            getData:function (newval){
                let self = this;
                self.$http.httpGet('/app/api/flightrunway/byairportid/'+newval, {
//                    params: {
//                        ID: newval
//                    }
                }).then(function (response){
                    let datainfo=response.data;
                    self.items=datainfo;
                    for(var i=0;i<self.items.length;i++)
                    {
                        var  strlist = [];
                        if(typeof (self.items[i].latitude)!='undefined') {
                            strlist = self.items[i].latitude.split('-');
                            if(typeof (strlist[0])=='undefined')
                            {
                                strlist[0]='  ';
                            }
                            if(typeof (strlist[1])=='undefined')
                            {
                                strlist[1]='  ';
                            }
                            if(typeof (strlist[2])=='undefined')
                            {
                                strlist[2]='  ';
                            }

                            self.items[i].latitude = strlist[0] + ' 度 ' + strlist[1] + ' 分 ' + strlist[2] + ' 秒 ';
                        }
                        if(typeof (self.items[i].longitude)!='undefined') {
                            strlist = self.items[i].longitude.split('-');

                            if(typeof (strlist[0])=='undefined')
                            {
                                strlist[0]='  ';
                            }
                            if(typeof (strlist[1])=='undefined')
                            {
                                strlist[1]='  ';
                            }
                            if(typeof (strlist[2])=='undefined')
                            {
                                strlist[2]='  ';
                            }

                            self.items[i].longitude = strlist[0] + ' 度 ' + strlist[1] + ' 分 ' + strlist[2] + ' 秒 ';
                        }
                    }

                })
                        .catch(function (error) {
                            console.log(error);
                        });
            }
        }
    }
</script>
