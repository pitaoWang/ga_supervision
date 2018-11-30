package com.atosenet.ga.sys.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atosenet.ga.sys.entity.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author Exrickx
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Permission> findByUserId(@Param("userId") String userId);

    /**
     * 通过roleId获取
     * @param roleId
     * @return
     */
    List<Permission> findByRoleId(@Param("roleId") String roleId);
}
