package com.ymagis.service;

import com.ymagis.model.AppRole;
import com.ymagis.model.AppUser;

public interface AccountService {
public AppUser saveUser(AppUser user);
public AppRole saveRole(AppRole role);
public void addRoleToUse(String username,String roleName);
public AppUser findUserByUsername(String username);
}
