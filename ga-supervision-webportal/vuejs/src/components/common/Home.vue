<template>
<div>

  <div class="com-wrap-1190 wrap">
      <v-head></v-head>
      <v-menu></v-menu>
  </div>
    <v-footer></v-footer>
</div>
</template>

<script>
    import vHead from './Header.vue';
    import vMenu from './Menu.vue';
    import vFooter from './Footer.vue';
    export default {
        name: "change_pass",
        components: {
            vHead, vMenu, vFooter
        }
    }
</script>
