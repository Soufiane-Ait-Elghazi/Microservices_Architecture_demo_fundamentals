package org.sfn.securityservice.security.service;

import org.sfn.securityservice.security.entities.AppRole;
import org.sfn.securityservice.security.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUserName(String username);
    List<AppUser> lisUsers();
}
