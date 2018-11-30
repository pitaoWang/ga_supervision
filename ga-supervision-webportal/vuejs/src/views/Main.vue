<template>
    <div style="height: 100%;width: 100%">
        <div  class="wrap">
            <v-head @on-change="handleChange"></v-head>
            <div class="left-nav">
                <shrinkable-menu :menu-list="menuList"  @on-change="handleChange">
                </shrinkable-menu>
            </div>
            <div style="float: left;width:88%;height:92%;overflow: auto;">
                <div class="main_div">
                    <shrinkableMenuaa ref="child"></shrinkableMenuaa>
                    <router-view></router-view>
                </div>

            </div>
            <!--<div style="clear: both;"></div>-->

        </div>
        <div style="width: 100%;height:4%;">
            <v-footer></v-footer>
        </div>
    </div>
</template>

<script>
import shrinkableMenu from "./main-components/shrinkable-menu/shrinkable-menu.vue";
import shrinkableMenuaa from "../components/common/Mntue.vue";
import util from "@/libs/util.js";
import vHead from '@/components/common/Header.vue';
import vFooter from '@/components/common/Footer.vue';
export default {
  components: {
    shrinkableMenu,
      shrinkableMenuaa,
      vHead, vFooter
  },
  data() {
    return {
        name:'',
      shrink: false,
      openedSubmenuArr: this.$store.state.app.openedSubmenuArr
    };
  },
  computed: {
    //菜单
    menuList() {
      return this.$store.state.app.menuList;
    },
    avatarPath() {
      return localStorage.avatorImgPath;
    },
    cachePage() {
      return this.$store.state.app.cachePage;
    }
  },
  stompClient: {
    monitorIntervalTime: 100,
    stompReconnect: true,
    timeout(orgCmd) {}
  },
  methods: {
      handleChange (name) {
         let self =this;
          self.$refs.child.childFn(name);
      },
    init() {
    },
  },
  watch: {
    $route(to) {
      this.$store.commit("setCurrentPageName", to.name);
      let pathArr = util.setCurrentPath(this, to.name);
      if (pathArr.length > 2) {
        this.$store.commit("addOpenSubmenu", pathArr[1].name);
      }
      localStorage.currentPageName = to.name;

    }
  },
  mounted() {
    this.init();
    window.addEventListener("resize", this.scrollBarResize);
  },
  created() {
    // 显示打开的页面的列表
    this.$store.commit("setOpenedList");
  },
  dispatch() {
    window.removeEventListener("resize", this.scrollBarResize);
  }
};
</script>
