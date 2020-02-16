package com.home.dao;

import com.home.entity.Permission;

public interface PermissionDao {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
