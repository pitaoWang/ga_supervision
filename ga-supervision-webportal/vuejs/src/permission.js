import router from './router'
import store from './store'
import { getToken } from '@/utils/auth' // 验权
import iView from 'iview';

// permissiom judge
function hasPermission(roles, permissionRoles) {
  if (roles.indexOf('ROLE_SUPER') >= 0) return true // admin权限 直接通过
  if (!permissionRoles) return true
  return roles.some(role => permissionRoles.indexOf(role) >= 0)
}

// register global progress.
const whiteList = ['/login', '/authredirect', '/401', '/403', '/404', '/500', '/logout']// 不重定向白名单
router.beforeEach((to, from, next) => {
    iView.LoadingBar.start();
  if (getToken()) { // 判断是否有token
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
        store.dispatch('ValidateToken').then(res => { // 验证token是否有效
            const valid = res
            if(valid) {
                if (store.getters.roles.length === 0) { // 判断当前用户是否已拉取完user_info信息
                    store.dispatch('GetUserInfo').then(res => { // 拉取user_info
                        const roles = res.roles
                        store.dispatch('GenerateRoutes', { roles }).then(() => { // 生成可访问的路由表
                            router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
                            console.log('goto router next', to)
                            next({path: to.path, query: to.query, params: to.params, hash: to.hash }) // hack方法 确保addRoutes已完成
                        })
                    }).catch((error) => {
                        console.log('error', error)
                        store.dispatch('FedLogOut').then(() => {
                            next({ path: '/logout' })
                        })
                    })
                } else {
                    // 没有动态改变权限的需求可直接next() 删除下方权限判断 ↓
                    if (hasPermission(store.getters.roles, to.meta.role)) {
                        next()//
                    } else {
                        next({ path: '/401', query: { noGoBack: true }})
                    }
                    // 可删 ↑
                }
            } else {
                store.dispatch('FedLogOut').then(() => {
                    next({ path: '/logout' })
                })
            }
        }).catch((error) => {
            console.log('error', error)
            store.dispatch('FedLogOut').then(() => {
                next({ path: '/logout' })
            })
        })
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next()
    } else {
      next('/login') // 否则全部重定向到登录页
        iView.LoadingBar.finish();
    }
  }
})

router.afterEach(() => {
    iView.LoadingBar.finish(); // 结束Progress
})
