import axios from 'axios'
import {baseUrl} from '@/config/env'
import store from '@/store'
import { getToken } from '@/utils/auth'
import {getStore} from "../libs/storage";
import { param } from '@/utils/index'

// 创建axios实例
const service = axios.create({
  baseURL: baseUrl, // api的base_url
  timeout: 15000                  // 请求超时时间
})

// request拦截器
service.interceptors.request.use(config => {
    let accessToken = getStore('accessToken');
    // Do something before request is sent

    config.headers['accessToken'] = accessToken // 让每个请求携带token 请根据实际情况自行修改
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

const serviceHref = function(url, data) {
  if (store.getters.token) {
      data['_token'] = getToken();
  }
  const paramStr = param(data);
  window.open(baseUrl + '/' + url + '?' + paramStr);
}

const serviceLocationHref = function(url, data) {
    let accessToken = getStore('accessToken');
    data['_token'] = accessToken;
    const paramStr = param(data);
    window.location.href=baseUrl + '' + url + '?' + paramStr;
}
// respone拦截器
service.interceptors.response.use(
  response => response,
  /**
  * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
  * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
  */
//  const res = response.data;
//     if (res.code !== 20000) {
//       Message({
//         message: res.message,
//         type: 'error',
//         duration: 5 * 1000
//       });
//       // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
//       if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
//         MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
//           confirmButtonText: '重新登录',
//           cancelButtonText: '取消',
//           type: 'warning'
//         }).then(() => {
//           store.dispatch('FedLogOut').then(() => {
//             location.reload();// 为了重新实例化vue-router对象 避免bug
//           });
//         })
//       }
//       return Promise.reject('error');
//     } else {
//       return response.data;
//     }
  error => {

    return Promise.reject(error)
  }
)

export { service, serviceHref ,serviceLocationHref}
