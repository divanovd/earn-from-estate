package com.scalefocus.EarnFromEstate.repositories.role;

import com.scalefocus.EarnFromEstate.entities.Role;

public interface RoleRepository {

    Role getRoleByName(String name);
    Role saveRole(final Role role);
}
