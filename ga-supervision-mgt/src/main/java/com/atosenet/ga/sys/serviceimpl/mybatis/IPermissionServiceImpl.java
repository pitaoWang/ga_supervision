package com.atosenet.ga.sys.serviceimpl.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atosenet.ga.sys.dao.mapper.PermissionMapper;
import com.atosenet.ga.sys.entity.Permission;
import com.atosenet.ga.sys.service.mybatis.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * @author Exrickx
 */
@Service
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper; ;

    @Override
    public List<Permission> findByUserId(String userId) {

        return permissionMapper.findByUserId(userId);
    }

    @Override
    public List<Permission> findByRoleId(String roleId) {

        return permissionMapper.findByRoleId(roleId);
    }
}
