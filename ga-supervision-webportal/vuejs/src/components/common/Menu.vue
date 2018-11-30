<template>
    <div >
        <div class="left-nav">
                <shrinkable-menu :menu-list="menuList" @on-change="handleChange">
                </shrinkable-menu>
        </div>
        <div class="main clearfix ">
            <div v-show="false"><shrinkableMenuaa ref="child"></shrinkableMenuaa></div>
                <router-view></router-view>
        </div>
    </div>

</template>

<script>
    import shrinkableMenu from "../../views/main-components/shrinkable-menu/shrinkable-menu.vue";
    import shrinkableMenuaa from "../../components/common/Mntue.vue";
    import scrollBar from "@/views/my-components/scroll-bar/vue-scroller-bars";
    import footer from "./Footer.vue";

    import Cookies from "js-cookie";
    import util from "@/libs/util.js";

    export default {
        components: {
            shrinkableMenu,
            shrinkableMenuaa,
            scrollBar,
            footer
        },
        data() {
            return {
                shrink: false,
                username: "",
                userId: "",
                isFullScreen: false,
                openedSubmenuArr: this.$store.state.app.openedSubmenuArr
            };
        },
        computed: {
            //菜单
            menuList() {
                return this.$store.state.app.menuList;
            },
            pageTagsList() {
                return this.$store.state.app.pageOpenedList; // 打开的页面的页面对象
            },
            currentPath() {

                return this.$store.state.app.currentPath; // 当前面包屑数组
            },
            avatarPath() {
                return localStorage.avatorImgPath;
            },
            cachePage() {
                return this.$store.state.app.cachePage;
            },
            lang() {
                return this.$store.state.app.lang;
            },
            menuTheme() {
                return this.$store.state.app.menuTheme;
            },
            mesCount() {
                return this.$store.state.app.messageCount;
            }
        },
        stompClient: {
            monitorIntervalTime: 100,
            stompReconnect: true,
            timeout(orgCmd) {}
        },
        methods: {
            handleChange (name) {
                alert(name)
                let self =this;
                self.$refs.child.childFn(name);
            },
            init() {

                let pathArr = util.setCurrentPath(this, this.$route.name);

                // this.$store.commit("updateMenulist");
                // if (pathArr.length >= 2) {
                //     this.$store.commit("addOpenSubmenu", pathArr[1].name);
                // }
                let userInfo = JSON.parse(Cookies.get("userInfo"));
                this.username = userInfo.username;
                this.userId = userInfo.id;
                this.checkTag(this.$route.name);
            },
            toggleClick() {
                this.shrink = !this.shrink;
            },
            handleLanDropdown(name) {
                localStorage.lang = name;
                this.$store.commit("switchLang", name);
            },
            handleClickUserDropdown(name) {
                if (name === "ownSpace") {
                    util.openNewPage(this, "ownspace_index");
                    this.$router.push({
                        name: "ownspace_index"
                    });
                } else if (name === "changePass") {
                    util.openNewPage(this, "change_pass");
                    this.$router.push({
                        name: "change_pass"
                    });
                } else if (name === "loginout") {
                    // 退出登录
                    this.$store.commit("logout", this);
                    this.$store.commit("clearOpenedSubmenu");
                    // 强制刷新页面 重新加载router
                    location.reload();
                }
            },
            // checkTag(name) {
            //     let openpageHasTag = this.pageTagsList.some(item => {
            //         if (item.name === name) {
            //             return true;
            //         }
            //     });
            //     if (!openpageHasTag) {
            //         //  解决关闭当前标签后再点击回退按钮会退到当前页时没有标签的问题
            //         util.openNewPage(
            //             this,
            //             name,
            //             this.$route.params || {},
            //             this.$route.query || {}
            //         );
            //     }
            // },
            // handleSubmenuChange(val) {
            //     // console.log(val)
            // },
            // beforePush(name) {
            //     // console.log(name)
            //     return true;
            // },
            // fullscreenChange(isFullScreen) {
            //     // console.log(isFullScreen);
            // },
            // scrollBarResize() {
            //     this.$refs.scrollBar.resize();
            // }
        },
        watch: {
            $route(to) {
                this.$store.commit("setCurrentPageName", to.name);
                let pathArr = util.setCurrentPath(this, to.name);
                if (pathArr.length > 2) {
                    this.$store.commit("addOpenSubmenu", pathArr[1].name);
                }
                this.checkTag(to.name);
                localStorage.currentPageName = to.name;
            },
            lang() {
                util.setCurrentPath(this, this.$route.name); // 在切换语言时用于刷新面包屑
            },
            openedSubmenuArr() {
                setTimeout(() => {
                    this.scrollBarResize();
                }, 500);
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


