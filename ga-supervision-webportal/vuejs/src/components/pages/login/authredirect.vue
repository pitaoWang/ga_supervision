<style>
    .ga-spin-icon-load{
        animation: ani-demo-spin 1s linear infinite;
    }
    @keyframes ani-demo-spin {
        from { transform: rotate(0deg);}
        50%  { transform: rotate(180deg);}
        to   { transform: rotate(360deg);}
    }
</style>
<template>
    <Spin fix>
        <Icon type="load-c" size=18 class="ga-spin-icon-load"></Icon>
        <div>正在校验用户信息...</div>
    </Spin>
</template>
<script>
  import { getQueryObject } from '@/utils/index'
  import {service} from '@/utils/fetch'
  export default {
    name: 'authredirect',
    mounted() {
      const hash = window.location.search.slice(1)
      const hashObj = getQueryObject(hash)
      const originUrl = window.location.origin
      const redirectUrl = encodeURIComponent(originUrl + '/#/authredirect')
      history.replaceState({}, '', originUrl)
        service({
        url: '/auth_code',
        method: 'post',
        params: {
          'code': hashObj['code'],
          'redirect': redirectUrl
        }
      }).then((res) => {
        this.$store.dispatch('SetUserToken', res.data['access_token'])
        this.$router.push({ path: '/' })
      })
    }
  }
</script>
