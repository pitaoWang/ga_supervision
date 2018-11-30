package com.atosenet.ga.sys.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.sys.dao.RoleDao;
import com.atosenet.ga.sys.entity.Role;
import com.atosenet.ga.sys.service.RoleService;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色接口实现
 * @author Exrickx
 */
@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleDao getRepository() {
        return roleDao;
    }

    @Override
    public List<Role> findByDefaultRole(Boolean defaultRole) {
        return roleDao.findByDefaultRole(defaultRole);
    }
}
