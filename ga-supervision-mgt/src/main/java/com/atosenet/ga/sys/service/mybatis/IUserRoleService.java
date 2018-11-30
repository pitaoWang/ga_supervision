package com.atosenet.ga.sys.service.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.atosenet.ga.sys.entity.Role;
import com.atosenet.ga.sys.entity.UserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author Exrickx
 */
//@CacheConfig(cacheNames = "userRole")
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    //@Cacheable(key = "#userId")
    List<Role> findByUserId(@Param("userId") String userId);
}
