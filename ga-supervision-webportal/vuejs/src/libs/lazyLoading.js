function lazy(name) {

    let file = name.substring(name.lastIndexOf('/')+1);
    if (file !== 'userManage'&&file !== 'roleManage'&&file !== 'menuManage'&&file !== 'departmentManage'&&file !== '404'&&file !== 'Main') {

        return ()=> import(`@/${name}.vue`)


    } else {

        return ()=> import(`@/views/${name}.vue`)
    }

}
export {lazy}
