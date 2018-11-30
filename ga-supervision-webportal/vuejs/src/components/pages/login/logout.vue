<template></template>
<script>
    export default {
      name: 'logout',
      created() {

          window.location.href='http://192.168.1.101:8088/oauth2-server/logout'
          //window.location.href='http://localhost:19092/oauth2-server/logout'
      }
    }
</script>
