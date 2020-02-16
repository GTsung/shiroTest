package com.home.service;

import com.home.dao.RoleDao;
import com.home.dao.RoleDaoImpl;
import com.home.entity.Role;

/**
 * @author guxc
 * @date 2020/2/16
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId, permissionIds);
    }
}
