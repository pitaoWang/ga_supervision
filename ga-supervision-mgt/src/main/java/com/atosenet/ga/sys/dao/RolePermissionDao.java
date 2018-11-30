package com.atosenet.ga.sys.dao;

import java.util.List;

import com.atosenet.ga.sys.base.XbootBaseDao;
import com.atosenet.ga.sys.entity.RolePermission;

/**
 * 角色权限数据处理层
 * @author Exrick
 */
public interface RolePermissionDao extends XbootBaseDao<RolePermission,String> {

    /**
     * 通过permissionId获取
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}