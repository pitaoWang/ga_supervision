package com.atosenet.ga.sys.service;


import java.util.List;

import com.atosenet.ga.sys.base.XbootBaseService;
import com.atosenet.ga.sys.entity.Role;

/**
 * 角色接口
 * @author Exrickx
 */
public interface RoleService extends XbootBaseService<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
