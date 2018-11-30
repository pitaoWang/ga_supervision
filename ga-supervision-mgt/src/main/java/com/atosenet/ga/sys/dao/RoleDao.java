package com.atosenet.ga.sys.dao;

import java.util.List;

import com.atosenet.ga.sys.base.XbootBaseDao;
import com.atosenet.ga.sys.entity.Role;

/**
 * 角色数据处理层
 * @author Exrickx
 */
public interface RoleDao extends XbootBaseDao<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
