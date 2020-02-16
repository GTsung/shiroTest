package com.home.service;

import com.home.entity.Permission;

public interface PermissionService {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
