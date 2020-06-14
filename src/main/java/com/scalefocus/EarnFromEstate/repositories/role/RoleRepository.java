package com.scalefocus.EarnFromEstate.repositories.role;

import com.scalefocus.EarnFromEstate.entities.Role;

import java.util.Set;

public interface RoleRepository {

    Role getRoleByName(String name);

    Set<Role> getRolesByUserId(Long userId);

    Role saveRole(Role role);
}
