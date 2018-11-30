package com.atosenet.ga.sys.service;


import java.util.List;

import com.atosenet.ga.sys.base.XbootBaseService;
import com.atosenet.ga.sys.entity.UserRole;

/**
 * 用户角色接口
 * @author Exrickx
 */
public interface UserRoleService extends XbootBaseService<UserRole,String> {

    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(String roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(String userId);
}
