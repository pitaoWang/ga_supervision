import 'babel-polyfill'
import Vue from 'vue';
import App from './App';
import { router } from './router/index'
import store from './store'
import axios from 'axios';
import VueFormWizard from 'vue-form-wizard'
import iView from 'iview';
import '../static/css/iview-theme-ga-new/iview.css';
//import 'iview/dist/styles/iview.css';
import '../static/css/theme-default-self/main.css';    // 覆盖样式
import {imageUrl,uploadUrl,authUrl} from './config/env'
import '../static/css/flybase.css';
import 'vue-form-wizard/dist/vue-form-wizard.min.css'
import "babel-polyfill";
import http from './config/http'
// import profile from './config/profile'
import { setStore, getStore, removeStore } from '@/libs/storage'
import util from '@/libs/util';
import hasPermission from '@/libs/hasPermission';
import { getRequest, postRequest, putRequest, deleteRequest, uploadFileRequest } from '@/libs/axios'
Vue.use(iView);
Vue.use(VueFormWizard);
Vue.use(hasPermission);
Vue.prototype.$axios = axios;

Vue.prototype.$http = http;
//Vue.prototype.$profile = profile;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.uploadFileRequest = uploadFileRequest;
Vue.prototype.setStore = setStore;
Vue.prototype.getStore = getStore;
Vue.prototype.removeStore = removeStore;
// Vue.prototype.$profile = profile;
//上传文件保存路径http://47.96.91.129:18083/file
Vue.prototype.imageUrl=imageUrl
//上传文件请求路径http://47.96.91.129:18083/upload
Vue.prototype.uploadUrl=uploadUrl
// Vue.prototype.$profile = profile;
Vue.prototype.authUrl=authUrl

new Vue({
    router,
    store,
    render: h => h(App),
    mounted() {
        // 初始化菜单
        util.initRouter(this);
        this.currentPageName = this.$route.name;
        // 显示打开的页面的列表
        this.$store.commit('setOpenedList');
        this.$store.commit('initCachepage');
    }
}).$mount('#app');

