import Main from '@/views/Main.vue';

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
    path: '/login',
    name: 'login',
    meta: {
        title: '登录--航空安全监管系统 '
    },
    component: () => import('@/views/login.vue')
};

export const registRouter = {
    path: '/regist',
    name: 'regist',
    meta: {
        title: '注册--航空安全监管系统'
    },
    component: () => import('@/views/regist.vue')
};

export const registResult = {
    path: '/regist-result',
    name: 'regist-result',
    meta: {
        title: '注册结果--航空安全监管系统'
    },
    component: () => import('@/views/regist-result.vue')
};

export const relateRouter = {
    path: '/relate',
    name: 'relate',
    meta: {
        title: '绑定账号 '
    },
    component: () => import('@/views/relate.vue')
};

// export const page404 = {
//     path: '/*',
//     name: 'error-404',
//     meta: {
//         title: '404-页面不存在'
//     },
//     component: () => import('@/views/error-page/404.vue')
// };

export const page403 = {
    path: '/403',
    meta: {
        title: '403-权限不足'
    },
    name: 'error-403',
    component: () => import('@/views/error-page/403.vue')
};

export const page500 = {
    path: '/500',
    meta: {
        title: '500-服务端错误'
    },
    name: 'error-500',
    component: () => import('@/views/error-page/500.vue')
};

export const locking = {
    path: '/locking',
    name: 'locking',
    component: () => import('@/views/main-components/lockscreen/components/locking-page.vue')
};

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
    path: '/',
    name: 'otherRouter',
    redirect: '/home',
    component: () => import('../views/Main.vue'),
    children: [
        { path: 'home', title: { i18n: 'home' }, name: 'home_index', component: () => import('@/components/common/Homemain.vue') },
        { path: '/addAircraft', title: '新增航空器', name: 'addAircraft', component: () => import('@/components/pages/flight/addAircraft.vue') },
//        { path: '/viewAircraft', title: '查看航空器', name: 'viewAircraft', component: () => import('@/components/pages/flight/viewAircraft.vue') },
        { path: '/aircraftmonitoring/tabs', title: '查看航空器', name: 'viewAircraft', component: () => import('@/components/pages/aircraftmonitoring/tabs.vue') },
        { path: '/message/tabs', title: '消息', name: 'messages', component: () => import('@/views/message/tabs.vue') },
        {path: '/viewSimulate', component: resolve => require(['../components/pages/flight/viewSimulate.vue'], resolve)},
        {path: '/viewPrepareRelease', component: resolve => require(['../components/pages/flight/viewPrepareRelease.vue'], resolve)},
        {path: '/viewFlightRecord', component: resolve => require(['../components/pages/flight/viewFlightRecord.vue'], resolve)},
        {path:'/addSimulate', component: resolve => require(['../components/pages/flight/addSimulate.vue'], resolve)},
        {path:'/editSimulate', component: resolve => require(['../components/pages/flight/addSimulate.vue'], resolve)},
        {path: '/addType', component: resolve => require(['../components/pages/flight/addType.vue'], resolve)},
        {path: '/editType', component: resolve => require(['../components/pages/flight/addType.vue'], resolve)},
        {path: '/viewType', component: resolve => require(['../components/pages/flight/viewType.vue'], resolve)},
        { path: 'ownspace', title: '个人中心', name: 'ownspace_index', component: () => import('@/views/own-space/own-space.vue') },
        { path: 'change-pass', title: '修改密码', name: 'change_pass', component: () => import('@/views/change-pass/change-pass.vue') },
        { path: 'message', title: '消息中心', name: 'message_index', component: () => import('@/views/message/message.vue') },
        { path: 'add', title: '添加', name: 'add', component: () => import('@/views/xboot-vue-template/new-window/add.vue') },
        { path: 'edit', title: '编辑', name: 'edit', component: () => import('@/views/xboot-vue-template/new-window/edit.vue') },
     //   { path: 'message-send-detail', title: '消息发送详情', name: 'message_send_detail', component: () => import('@/views/sys/message-manage/messageSendDetail.vue') },
        {path: '/comqualdetail', component: resolve => require(['../components/views/company/companyqualificationdetail.vue'], resolve)},
        {path: '/operationbaseadd', component: resolve => require(['../components/views/company/operationbaseadd.vue'], resolve)},
        {path: '/operationbasedetail', component: resolve => require(['../components/views/company/operationbasedetail.vue'], resolve)},
        {path: '/operationbaseupdate', component: resolve => require(['../components/views/company/operationbaseupdate.vue'], resolve)},
        {path: '/viewEvaluation', component: resolve => require(['@/components/pages/mmis/viewEvaluate.vue'], resolve)},
        {
            path:'/adduser',
            component: resolve => require(['../components/pages/employee/add/addEmployee.vue'], resolve),
            children:[{
                path:'/name1',
                component: resolve => require(['../components/pages/employee/add/addEmployee.vue'], resolve),
            },{
                path:'/name2',
                component: resolve => require(['../components/pages/employee/add/licenseList.vue'], resolve),
            }],
        },
//添加执照
        {
            path:'/newAddLicense',
            //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            component: resolve => require(['../components/pages/employee/add/newAddLicense.vue'], resolve)
        },
        {
             path:'/editEmployee',
            component: resolve => require(['../components/pages/employee/add/editEmployee.vue'], resolve)
        },
        {
             path:'/for_liceninfo',
             //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
             component: resolve => require(['../components/pages/employee/licenseinfo.vue'], resolve)

         },
        {
            path:'/for_pliceninfo',
            //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            component: resolve => require(['../components/pages/employee/add/eidtLicenseinfo.vue'], resolve)

        },
        {
            path:'/userManage',
            name: 'userManage',
            //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            component: resolve => require(['../views/sys/user-manage/userManage.vue'], resolve)

        },
        {
             path:'/for_addlicen',
            //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
             component: resolve => require(['../components/pages/employee/addlicense.vue'], resolve)

         },
        {
            path: '/viewFssuser',
            component: resolve => require(['../components/pages/employee/viewFssuser.vue'], resolve)
        },
        {
             path: '/pilot',
            component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
        },
        //企业tabs
        {
            path: '/suptabs',
            component: resolve => require(['../components/pages/company/suptabs.vue'], resolve)
        },
        {
            path: '/black',
            component: resolve => require(['../components/pages/company/companysuper.vue'], resolve)
        },
        {
            path: '/blackstruct',
            component: resolve => require(['../components/pages/company/structurefss.vue'], resolve)
        },
        {
            path: '/supqualdetail',
            component: resolve => require(['../components/pages/company/supqualificationdetail.vue'], resolve)
        },
        {
            path: '/supbasedetail',
            component: resolve => require(['../components/pages/company/supbasedetail.vue'], resolve)
        },
        {
            path: '/structurefssdetail',
            component: resolve => require(['../components/pages/company/structurefssdetail.vue'], resolve)
        },
        {
            path: '/mmisbaseenginedetail',
            component: resolve => require(['../components/pages/company/mmisbaseenginedetail.vue'], resolve)
        },

        {
            //飞行记录本
            path: '/aircraftmonitoring/flightrecordinfo',
            component: resolve => require(['../components/pages/aircraftmonitoring/flightrecordinfo.vue'], resolve)
        },
        {
            //工作保留单
            path: '/aircraftmonitoring/workreserveinfo',
            component: resolve => require(['../components/pages/aircraftmonitoring/workreserveinfo.vue'], resolve)
        },
        {
            //重要维修及改装
            path: '/aircraftmonitoring/repairmodifiedinfo',
            component: resolve => require(['../components/pages/aircraftmonitoring/repairmodifiedinfo.vue'], resolve)
        },
        {
            //故障保留单
            path: '/aircraftmonitoring/faultreserveinfo',
            component: resolve => require(['../components/pages/aircraftmonitoring/faultreserveinfo.vue'], resolve)
        },
        {
            path: '/mmisillegalflightdetail',
            component: resolve => require(['../components/pages/company/mmisillegalflightdetail.vue'], resolve)
        },
        {
            path:'/viewEmployee',
            component: resolve => require(['../components/pages/employee/view/viewEmployee.vue'], resolve)
        },
        {
            path:'/viewPilotLicense',
            //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            component: resolve => require(['../components/pages/employee/view/viewPilotLicense.vue'], resolve)

        },
        {
            path:'/viewLicense',
            //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            component: resolve => require(['../components/pages/employee/view/viewLicense.vue'], resolve)
        },
        {
            path:'/viewPilotLicenseDetail',
            component: resolve => require(['../components/pages/employee/view/viewPilotLicenseDetail.vue'], resolve)
        },
        {
            path:'/viewLicenseDetail',
            component: resolve => require(['../components/pages/employee/view/viewLicenseDetail.vue'], resolve)
        },
        {
            path:'/viewPhysicalDetail',
            component: resolve => require(['../components/pages/employee/view/viewPhysicalDetail.vue'], resolve)
        },
        {
            path:'/viewPersonExamDetail',
            component: resolve => require(['../components/pages/employee/view/viewPersonExamDetail.vue'], resolve)
        },
        {
            path:'/viewTrainDetail',
            component: resolve => require(['../components/pages/employee/view/viewTrainDetail.vue'], resolve)
        },
        {
            path:'/viewWorkExperienceDetail',
            component: resolve => require(['../components/pages/employee/view/viewWorkExperienceDetail.vue'], resolve)
        },
        {
            path:'/viewAwardDetail',
            component: resolve => require(['../components/pages/employee/view/viewAwardDetail.vue'], resolve)
        },
        {
            path:'/documentview',
            component: resolve => require(['../components/pages/documentmanage/view.vue'], resolve)
        },

    ]
};


// export const otherRouter = {
//         path: '/',
//         name: 'otherRouter',
//         redirect: '/home',
//         component: () => import('@/components/common/Home.vue'),
//         children : [
//             {
//                 path: '/home',
//                 name:'home_index',
//                 component: () => import('@/components/pages/company/tabs.vue')
//             }
            // {
            //     path: '/pilot',
            //     component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            // },
            // {
            //     path: '/need-implemention',
            //     component: resolve => require(['../components/pages/Readme.vue'], resolve)
            //
            // },
            // {
            //     path: '/information',
            //     component: resolve => require(['../components/pages/employee/information.vue'], resolve)
            // },
            // {   管制人员
            //     path: '/controller',
            //     component: resolve => require(['../components/pages/employee/controller.vue'], resolve)
            // },
            // {
            //     path: '/executive',
            //     component: resolve => require(['../components/pages/employee/executive.vue'], resolve)
            // },
            // {
                    //飞行人员
            //     path: '/pilots',
            //     component: resolve => require(['../components/pages/employee/pilots.vue'], resolve)
            // },
            // {
            //     path: '/attendant',
            //     component: resolve => require(['../components/pages/employee/attendant.vue'], resolve)
            // },
            // {
            //     path: '/all',
            //     component: resolve => require(['../components/pages/employee/all.vue'], resolve)
            // },
            // {
            //
            //     path: '/weather',
            //     component: resolve => require(['../components/pages/employee/weather.vue'], resolve)
            // },
            // {
            //
            //     path: '/fdispatch',
            //     component: resolve => require(['../components/pages/employee/fdispatch.vue'], resolve)
            // },
            // {
            //   全部人员
            //     path: '/alls',
            //     component: resolve => require(['../components/pages/employee/alls.vue'], resolve)
            // },
            //
            // {
            //
            //     path: '/employeestatic',
            //     component: resolve => require(['../components/pages/analysis/employeestatic.vue'], resolve)
            // },
            // {
            //     path: '/aircraftoperation',
            //     component: resolve => require(['../components/pages/analysis/aircraftstatic.vue'], resolve)
            // },
            // {
            //     path: '/pilot_info',
            //     component: resolve => require(['../components/pages/employee/pilotinfo.vue'], resolve)
            // },
            //
            // {
            //     //飞行设备管理
            //     path: '/flight_equipment',
            //     component: resolve => require(['../components/pages/flight/typeList.vue'], resolve)
            //
            // },
            // {
            //     //机型管理
            //     path: '/type_manage',
            //     component: resolve => require(['../components/pages/flight/typeList.vue'], resolve)
            //
            // },
            // {
            //     //新增机型
            //     path: '/addType',
            //     component: resolve => require(['../components/pages/flight/addType.vue'], resolve)
            //
            // },
            // {
            //     //新增航空器
            //     path: '/addAircraft',
            //     component: resolve => require(['../components/pages/flight/addAircraft.vue'], resolve)
            //
            // },
            // {
            //     //查看航空器
            //     path: '/viewAircraft',
            //     component: resolve => require(['../components/pages/flight/viewAircraft.vue'], resolve)
            //
            // },
            // {
            //     //查看模拟器
            //     path: '/viewSimulate',
            //     component: resolve => require(['../components/pages/flight/viewSimulate.vue'], resolve)
            //
            // },
            // {
            //     //修改机型
            //     path: '/editType',
            //     component: resolve => require(['../components/pages/flight/addType.vue'], resolve)
            //
            // },
            // {
            //     //查看机型
            //     path: '/viewType',
            //     component: resolve => require(['../components/pages/flight/viewType.vue'], resolve)
            //
            // },
            // {
            //     //航空器列表
            //     path:'/aircraftList',
            //     component: resolve => require(['../components/pages/flight/aircraftList.vue'], resolve)
            // },
            // {
            //     //机载设备列表
            //     path:'/equipmentList',
            //     component: resolve => require(['../components/pages/flight/equipmentList.vue'], resolve)
            // },
            // {
            //     //模拟器列表
            //     path:'/simulateList',
            //     component: resolve => require(['../components/pages/flight/simulateList.vue'], resolve)
            // },
            // {
            //     //新增模拟器
            //     path:'/addSimulate',
            //     component: resolve => require(['../components/pages/flight/addSimulate.vue'], resolve)
            // },
            // {
            //     //新增模拟器
            //     path:'/editSimulate',
            //     component: resolve => require(['../components/pages/flight/addSimulate.vue'], resolve)
            // },
            // {
            //     path: '/company',
            //     component: resolve => require(['../components/pages/company/tabs.vue'], resolve),
            //
            // },
            //
            // {
            //     path: '/operationbaseadd',
            //     component: resolve => require(['../components/views/company/operationbaseadd.vue'], resolve)
            //
            // },
            // {
            //     path: '/operationbasedetail',
            //     component: resolve => require(['../components/views/company/operationbasedetail.vue'], resolve)
            //
            // },
            // {
            //     path: '/operationbaseupdate',
            //     component: resolve => require(['../components/views/company/operationbaseupdate.vue'], resolve)
            //
            // },
            // {
            //     path: '/otherbasedetail',
            //     component: resolve => require(['../components/views/company/otherbasedetail.vue'], resolve)
            //
            // },
            // {
            //     path:'/runway/:id',
            //     name:'runway',
            //
            //     component: resolve => require(['../components/pages/airport/airtportrunway.vue'], resolve)
            // },
            // {
            //     path:'/newuser',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/sys/user/list.vue'], resolve)
            // },
            // /* {
            //      path:'/adduser',
            //      //component: resolve => require(['../components/pages/employee/createnewuser.vue'], resolve)
            //      //component: resolve => require(['../components/pages/sys/user/list.vue'], resolve)
            //      component: resolve => require(['../components/pages/sys/user/detail.vue'], resolve)
            //
            //  },*/
            //
            // {
            //     path:'/adduser',
            //     component: resolve => require(['../components/pages/employee/add/addEmployee.vue'], resolve)
            // },
            //
            // {
            //     path:'/editEmployee',
            //     component: resolve => require(['../components/pages/employee/add/editEmployee.vue'], resolve)
            // },
            //
            //
            // {
            //     path:'/for_ent',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/sys/user/list.vue'], resolve)
            //
            // },
            // {
            //     path:'/userlist',
            //     component: resolve => require(['../components/pages/employee/ueserlist.vue'], resolve)
            //
            //
            // },
            // {
            //     path: '/orgmgt',
            //     component: resolve => require(['../components/pages/employee/deptlist.vue'], resolve)
            // },
            // {
            //     path:'/for_org',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/org/info.vue'], resolve)
            //
            // },
            //
            // {
            //     path:'/for_liceninfo',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/licenseinfo.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_addlicen',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/addlicense.vue'], resolve)
            //
            // },
            //
            // //添加执照
            // {
            //     path:'/newAddLicense',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/add/newAddLicense.vue'], resolve)
            // },
            //
            // {
            //     path:'/for_traininfoshow1',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/traininfoshow1.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_traincertificateshow',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/traincertificateshow.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_weatherlicenseshow',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/weatherlicenseshow.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_flylicenseview',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/flylicenseview.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_flightdisplicenseinfo',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/flightdisplicenseinfo.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_controllicense',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/controllicense.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_intelligenceliceinfo',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/intelligenceliceinfo.vue'], resolve)
            //
            // },
            // {
            //     path:'/addaprl',
            //     component:resolve=>require(['../components/pages/aircraft/addaprl.vue'],resolve)
            // }
            // ,
            // {
            //     path:'/for_aircraftpartrepairlicensedetail',
            //     component:resolve=>require(['../components/views/aircraftpartrepairlicensedetail.vue'],resolve)
            // },{
            //     path:'/for_aircraftrepairlicensedetail',
            //     component:resolve=>require(['../components/views/aircraftrepairlicensedetail.vue'],resolve)
            // },
            // {
            //     path:'/for_personexamrecinfo',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/personexamrecinfo.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_skilledcheckinfo',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/skilledcheckinfo.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_updatecheckinfo',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/updatecheckinfo.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_periodicalcheckinfo',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/periodicalcheckinfo.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_physicalrecinfo',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/physicalrecinfo.vue'], resolve)
            //
            // },
            // {
            //     path:'/aircraftcrew',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/pilotservice.vue'], resolve)
            //
            // },{
            //     path:'/requestlist',
            //     component: resolve => require(['../components/pages/task/requestlist.vue'], resolve)
            // },{
            //     path:'/pengdinglist',
            //     component: resolve => require(['../components/pages/task/pengdinglist.vue'], resolve)
            // },
            // {
            //     path:'/student',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //
            // },
            // {
            //     path:'/othercrew',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/pilotservice.vue'], resolve)
            //
            // },
            // {
            //     path:'/flightcrew',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/pilotservice.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_flight_record',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/flightimplementrecord_new.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_flight_record_show',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/views/flightimplementrecordshow.vue'], resolve)
            //
            // },
            // {
            //     path:'/for_flight_records',
            //     //component: resolve => require(['../components/pages/employee/pilot.vue'], resolve)
            //     component: resolve => require(['../components/pages/employee/allflightrecord.vue'], resolve)
            //
            // },{
            //     path:'/completedlist',
            //     component: resolve => require(['../components/pages/task/completedlist.vue'], resolve)
            // },{
            //     path:'/endlist',
            //     component: resolve => require(['../components/pages/task/entlist.vue'], resolve)
            // },
            // {
            //     path:'/userinfo',
            //     component: resolve => require(['../components/pages/sys/user/edit.vue'], resolve)
            //
            // },
            // {
            //     path:'/orginfo',
            //     component: resolve => require(['../components/pages/org/orginfo.vue'], resolve)
            //
            // },
            // // {
            // //     path:'/messagelist',
            // //     component: resolve => require(['../components/pages/message/list.vue'], resolve)
            // //
            // // },
            // {
            //     path:'/document',
            //     component: resolve => require(['../components/pages/documentmanage/form.vue'], resolve)
            //
            // },
            // {
            //     path:'/documentlist',
            //     component: resolve => require(['../components/pages/documentmanage/list.vue'], resolve)
            //
            // },
            // {
            //     path:'/documentview',
            //     component: resolve => require(['../components/pages/documentmanage/view.vue'], resolve)
            //
            // }
    //     ]
    // };
// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
// export const otherRouter = {
//     path: '/',
//     name: 'otherRouter',
//     redirect: '/home',
//     component: Main,
//     children: [
//         { path: 'home', title: { i18n: 'home' }, name: 'home_index', component: () => import('@/views/home/home.vue') },
//         { path: 'ownspace', title: '个人中心', name: 'ownspace_index', component: () => import('@/views/own-space/own-space.vue') },
//         { path: 'change-pass', title: '修改密码', name: 'change_pass', component: () => import('@/views/change-pass/change-pass.vue') },
//         { path: 'message', title: '消息中心', name: 'message_index', component: () => import('@/views/message/message.vue') },
//         { path: 'add', title: '添加', name: 'add', component: () => import('@/views/xboot-vue-template/new-window/add.vue') },
//         { path: 'edit', title: '编辑', name: 'edit', component: () => import('@/views/xboot-vue-template/new-window/edit.vue') },
//         { path: 'message-send-detail', title: '消息发送详情', name: 'message_send_detail', component: () => import('@/views/sys/message-manage/messageSendDetail.vue') }
//     ]
// };

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
// export const appRouter = [
//     {
//         path: '/form',
//         icon: 'ios-gear',
//         name: 'sys',
//         title: '系统管理',
//         component: Main,
//         children: [
//             { path: 'user-manage', title: '用户管理', name: 'user-manage', icon: 'android-person', component: () => import('@/views/sys/user-manage/userManage') },
//             { path: 'role-manage', title: '角色管理', name: 'role-manage', icon: 'android-contacts', component: () => import('@/views/sys/role-manage/roleManage.vue') },
//             { path: 'menu-manage', title: '菜单管理', name: 'menu-manage', icon: 'navicon-round', component: () => import('@/views/sys/menu-manage/menuManage.vue') },
//             { path: 'log-manage', title: '日志管理', name: 'log-manage', icon: 'android-list', component: () => import('@/views/sys/log-manage/logManage.vue') }
//         ]
//     }
// ];

export const appRouter = [];

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
    loginRouter,
    registRouter,
    registResult,
    relateRouter,
    otherRouter,
    locking,
    ...appRouter,
    page500,
    page403
];
