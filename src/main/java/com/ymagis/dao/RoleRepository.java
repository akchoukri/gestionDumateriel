package com.ymagis.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ymagis.model.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>{
public AppRole findByRoleName(String roleName);
}
