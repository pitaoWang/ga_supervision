import Vue from 'vue';
import iView from 'iview';
import Util from '../libs/util';
import VueRouter from 'vue-router';
import Cookies from 'js-cookie';
import { routers, otherRouter } from './router';
import store from '../store';

Vue.use(VueRouter);

// 路由配置
const RouterConfig = {
    // mode: 'history',
    routes: routers
};

export const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    iView.LoadingBar.start();
    Util.title(to.meta.title);
    if (Cookies.get('locking') === '1' && to.name !== 'locking') {
        // 判断当前是否是锁定状态
        next({
            replace: true,
            name: 'locking'
        });
    } else if (Cookies.get('locking') === '0' && to.name === 'locking') {
        next(false);
    } else {
        if (!Cookies.get('userInfo') && to.name !== 'login' && to.name !== 'regist' && to.name !== 'regist-result' && to.name !== 'relate') { // 判断是否已经登录且前往的页面不是登录页
            next({
                name: 'login'
            });
        } else if (Cookies.get('userInfo') && to.name === 'login') {
            // 判断是否已经登录且前往的是登录页
            Util.title();
            next({
                name: 'home_index'
            });
        } else {
            Util.toDefaultPage([...routers], to.name, router, next);
        }
    }
});

router.afterEach((to) => {
    Util.openNewPage(router.app, to.name, to.params, to.query);
    iView.LoadingBar.finish();
    window.scrollTo(0, 0);
});


// export const constantRouterMap = [
//     { path: '/login', component: resolve => require(['../views/login.vue'], resolve), hidden: true },
//     { path: '/authredirect', component: resolve => require(['../components/pages/login/authredirect.vue'], resolve), hidden: true },
//     { path: '/logout', component: resolve => require(['../components/pages/login/logout.vue'], resolve), hidden: true },
//     {
//         path: '/bpmn_employee',
//         component: resolve => require(['../components/common/bpmn/employee.vue'], resolve),
//     },{
//         path: '/bpmn_gacompany',
//         component: resolve => require(['../components/common/bpmn/gacompany.vue'], resolve)
//     },{
//         path: '/bpmn_aircraft',
//         component: resolve => require(['../components/common/bpmn/aircraft.vue'], resolve)
//
//     },{
//         path: '/bpmn_aircraftdata',
//         component: resolve => require(['../components/common/bpmn/aircraftdata.vue'], resolve)
//
//     },{
//         path: '/bpmn_gacompanydata',
//         component: resolve => require(['../components/common/bpmn/gacompanydata.vue'], resolve)
//
//     },{
//     path: '/bpmn_emplpyeedata',
//         component: resolve => require(['../components/common/bpmn/emplpyeedata.vue'], resolve)
//     },{
//         path: '/print-emplpyee',
//         component: resolve => require(['../components/pages/employee/applyauthor.vue'], resolve)
//     },{
//         path: '/print-company',
//         component: resolve => require(['../components/pages/company/applyauthor.vue'], resolve)
//     }
// ]
//
// export const asyncRouterMap = [
//     {
//         path: '/',
//         redirect: '/home'
//     },
//     {
//         path: '/home',
//         component: resolve => require(['../components/common/Home.vue'], resolve),
//         children : [
//             {
//                 path: '/',
//                 component: resolve => require(['../components/pages/company/tabs.vue'], resolve)
//             },
//             {
//                 path: '/pilot',
//                 component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//             },
//             {
//                 path: '/need-implemention',
//                 component: resolve => require(['../components/pages/Readme.vue'], resolve)
//
//             },
//             {
//                 path: '/information',
//                 component: resolve => require(['../components/pages/employee/information.vue'], resolve)
//             },
//             {
//                 path: '/controller',
//                 component: resolve => require(['../components/pages/employee/controller.vue'], resolve)
//             },
//             {
//                 path: '/executive',
//                 component: resolve => require(['../components/pages/employee/executive.vue'], resolve)
//             },
//             {
//                 path: '/pilots',
//                 component: resolve => require(['../components/pages/employee/pilots.vue'], resolve)
//             },
//             {
//                 path: '/attendant',
//                 component: resolve => require(['../components/pages/employee/attendant.vue'], resolve)
//             },
//             {
//                 path: '/all',
//                 component: resolve => require(['../components/pages/employee/all.vue'], resolve)
//             },
//             {
//
//                 path: '/weather',
//                 component: resolve => require(['../components/pages/employee/weather.vue'], resolve)
//             },
//             {
//
//                 path: '/fdispatch',
//                 component: resolve => require(['../components/pages/employee/fdispatch.vue'], resolve)
//             },
//             {
//
//                 path: '/alls',
//                 component: resolve => require(['../components/pages/employee/alls.vue'], resolve)
//             },
//
//             {
//
//                 path: '/employeestatic',
//                 component: resolve => require(['../components/pages/analysis/employeestatic.vue'], resolve)
//             },
//             {
//                 path: '/aircraftoperation',
//                 component: resolve => require(['../components/pages/analysis/aircraftstatic.vue'], resolve)
//             },
//             {
//                 path: '/pilot_info',
//                 component: resolve => require(['../components/pages/employee/pilotinfo.vue'], resolve)
//             },
//
//             {
//                 //飞行设备管理
//                 path: '/flight_equipment',
//                 component: resolve => require(['../components/pages/flight/typeList.vue'], resolve)
//
//             },
//             {
//                 //机型管理
//                 path: '/type_manage',
//                 component: resolve => require(['../components/pages/flight/typeList.vue'], resolve)
//
//             },
//             {
//                 //新增机型
//                 path: '/addType',
//                 component: resolve => require(['../components/pages/flight/addType.vue'], resolve)
//
//             },
//             {
//                 //新增航空器
//                 path: '/addAircraft',
//                 component: resolve => require(['../components/pages/flight/addAircraft.vue'], resolve)
//
//             },
//             {
//                 //查看航空器
//                 path: '/viewAircraft',
//                 component: resolve => require(['../components/pages/flight/viewAircraft.vue'], resolve)
//
//             },
//             {
//                 //查看模拟器
//                 path: '/viewSimulate',
//                 component: resolve => require(['../components/pages/flight/viewSimulate.vue'], resolve)
//
//             },
//             {
//                 //修改机型
//                 path: '/editType',
//                 component: resolve => require(['../components/pages/flight/addType.vue'], resolve)
//
//             },
//             {
//                 //查看机型
//                 path: '/viewType',
//                 component: resolve => require(['../components/pages/flight/viewType.vue'], resolve)
//
//             },
//             {
//                 //航空器列表
//                 path:'/aircraftList',
//                 component: resolve => require(['../components/pages/flight/aircraftList.vue'], resolve)
//             },
//             {
//                 //机载设备列表
//                 path:'/equipmentList',
//                 component: resolve => require(['../components/pages/flight/equipmentList.vue'], resolve)
//             },
//             {
//                 //模拟器列表
//                 path:'/simulateList',
//                 component: resolve => require(['../components/pages/flight/simulateList.vue'], resolve)
//             },
//             {
//                 //新增模拟器
//                 path:'/addSimulate',
//                 component: resolve => require(['../components/pages/flight/addSimulate.vue'], resolve)
//             },
//             {
//                 //新增模拟器
//                 path:'/editSimulate',
//                 component: resolve => require(['../components/pages/flight/addSimulate.vue'], resolve)
//             },
//             {
//                 path: '/company',
//                 component: resolve => require(['../components/pages/company/tabs.vue'], resolve),
//
//             },
//
//             {
//                 path: '/operationbaseadd',
//                 component: resolve => require(['../components/views/company/operationbaseadd.vue'], resolve)
//
//             },
//             {
//                 path: '/operationbasedetail',
//                 component: resolve => require(['../components/views/company/operationbasedetail.vue'], resolve)
//
//             },
//             {
//                 path: '/operationbaseupdate',
//                 component: resolve => require(['../components/views/company/operationbaseupdate.vue'], resolve)
//
//             },
//             {
//                 path: '/otherbasedetail',
//                 component: resolve => require(['../components/views/company/otherbasedetail.vue'], resolve)
//
//             },
//             {
//                 path:'/runway/:id',
//                 name:'runway',
//
//                 component: resolve => require(['../components/pages/airport/airtportrunway.vue'], resolve)
//             },
//             {
//                 path:'/newuser',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/sys/user/list.vue'], resolve)
//             },
//            /* {
//                 path:'/adduser',
//                 //component: resolve => require(['../components/pages/employee/createnewuser.vue'], resolve)
//                 //component: resolve => require(['../components/pages/sys/user/list.vue'], resolve)
//                 component: resolve => require(['../components/pages/sys/user/detail.vue'], resolve)
//
//             },*/
//
//             {
//                 path:'/adduser',
//                 component: resolve => require(['../components/pages/employee/add/addEmployee.vue'], resolve)
//             },
//
//             {
//                 path:'/editEmployee',
//                 component: resolve => require(['../components/pages/employee/add/editEmployee.vue'], resolve)
//             },
//
//
//             {
//                 path:'/for_ent',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/sys/user/list.vue'], resolve)
//
//             },
//             {
//                 path:'/userlist',
//                 component: resolve => require(['../components/pages/employee/ueserlist.vue'], resolve)
//
//
//             },
//             {
//                 path: '/orgmgt',
//                 component: resolve => require(['../components/pages/employee/deptlist.vue'], resolve)
//             },
//             {
//                 path:'/for_org',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/org/info.vue'], resolve)
//
//             },
//
//             {
//                 path:'/for_liceninfo',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/licenseinfo.vue'], resolve)
//
//             },
//             {
//                 path:'/for_addlicen',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/addlicense.vue'], resolve)
//
//             },
//
//             //添加执照
//             {
//                 path:'/newAddLicense',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/add/newAddLicense.vue'], resolve)
//             },
//
//             {
//                 path:'/for_traininfoshow1',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/traininfoshow1.vue'], resolve)
//
//             },
//             {
//                 path:'/for_traincertificateshow',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/traincertificateshow.vue'], resolve)
//
//             },
//             {
//                 path:'/for_weatherlicenseshow',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/weatherlicenseshow.vue'], resolve)
//
//             },
//             {
//                 path:'/for_flylicenseview',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/flylicenseview.vue'], resolve)
//
//             },
//             {
//                 path:'/for_flightdisplicenseinfo',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/flightdisplicenseinfo.vue'], resolve)
//
//             },
//             {
//                 path:'/for_controllicense',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/controllicense.vue'], resolve)
//
//             },
//             {
//                 path:'/for_intelligenceliceinfo',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/intelligenceliceinfo.vue'], resolve)
//
//             },
//             {
//                 path:'/addaprl',
//                 component:resolve=>require(['../components/pages/aircraft/addaprl.vue'],resolve)
//             }
//             ,
//             {
//                 path:'/for_aircraftpartrepairlicensedetail',
//                 component:resolve=>require(['../components/views/aircraftpartrepairlicensedetail.vue'],resolve)
//             },{
//                 path:'/for_aircraftrepairlicensedetail',
//                 component:resolve=>require(['../components/views/aircraftrepairlicensedetail.vue'],resolve)
//             },
//             {
//                 path:'/for_personexamrecinfo',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/personexamrecinfo.vue'], resolve)
//
//             },
//             {
//                 path:'/for_skilledcheckinfo',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/skilledcheckinfo.vue'], resolve)
//
//             },
//             {
//                 path:'/for_updatecheckinfo',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/updatecheckinfo.vue'], resolve)
//
//             },
//             {
//                 path:'/for_periodicalcheckinfo',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/periodicalcheckinfo.vue'], resolve)
//
//             },
//             {
//                 path:'/for_physicalrecinfo',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/physicalrecinfo.vue'], resolve)
//
//             },
//             {
//                 path:'/aircraftcrew',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/pilotservice.vue'], resolve)
//
//             },{
//                 path:'/requestlist',
//                 component: resolve => require(['../components/pages/task/requestlist.vue'], resolve)
//              },{
//                 path:'/pengdinglist',
//                 component: resolve => require(['../components/pages/task/pengdinglist.vue'], resolve)
//             },
//             {
//                 path:'/student',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//
//             },
//             {
//                 path:'/othercrew',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/pilotservice.vue'], resolve)
//
//             },
//             {
//                 path:'/flightcrew',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/pilotservice.vue'], resolve)
//
//             },
//             {
//                 path:'/for_flight_record',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/flightimplementrecord_new.vue'], resolve)
//
//             },
//             {
//                 path:'/for_flight_record_show',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/views/flightimplementrecordshow.vue'], resolve)
//
//             },
//             {
//                 path:'/for_flight_records',
//                 //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
//                 component: resolve => require(['../components/pages/employee/allflightrecord.vue'], resolve)
//
//             },{
//                 path:'/completedlist',
//                 component: resolve => require(['../components/pages/task/completedlist.vue'], resolve)
//             },{
//                 path:'/endlist',
//                 component: resolve => require(['../components/pages/task/entlist.vue'], resolve)
//             },
//             {
//                 path:'/userinfo',
//                 component: resolve => require(['../components/pages/sys/user/edit.vue'], resolve)
//
//             },
//             {
//                 path:'/orginfo',
//                 component: resolve => require(['../components/pages/org/orginfo.vue'], resolve)
//
//             },
//             // {
//             //     path:'/messagelist',
//             //     component: resolve => require(['../components/pages/message/list.vue'], resolve)
//             //
//             // },
//             {
//                 path:'/document',
//                 component: resolve => require(['../components/pages/documentmanage/form.vue'], resolve)
//
//             },
//             {
//                 path:'/documentlist',
//                 component: resolve => require(['../components/pages/documentmanage/list.vue'], resolve)
//
//             },
//             {
//                 path:'/documentview',
//                 component: resolve => require(['../components/pages/documentmanage/view.vue'], resolve)
//
//             }
//         ]
//     }
//
// ]
//
// export default new Router({
//     routes: constantRouterMap
// })
