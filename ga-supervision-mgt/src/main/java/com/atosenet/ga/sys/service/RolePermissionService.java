package com.atosenet.ga.sys.service;

import java.util.List;

import com.atosenet.ga.sys.base.XbootBaseService;
import com.atosenet.ga.sys.entity.RolePermission;

/**
 * 角色权限接口
 * @author Exrick
 */
public interface RolePermissionService extends XbootBaseService<RolePermission,String> {

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