package com.atosenet.ga.sys.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atosenet.ga.sys.entity.Role;
import com.atosenet.ga.sys.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author Exrickx
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);
}
