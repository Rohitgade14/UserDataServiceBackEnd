package com.spcodage.service;

import com.spcodage.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionService extends JpaRepository<Permission,Integer> {
}
