package com.home.service;

import com.home.dao.PermissionDao;
import com.home.dao.PermissionDaoimpl;
import com.home.entity.Permission;

/**
 * @author guxc
 * @date 2020/2/16
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoimpl();


    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
