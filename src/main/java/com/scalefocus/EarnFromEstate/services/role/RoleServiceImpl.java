package com.scalefocus.EarnFromEstate.services.role;

import com.scalefocus.EarnFromEstate.entities.Role;
import com.scalefocus.EarnFromEstate.repositories.role.RoleRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(final String name) {
        return roleRepository.getRoleByName(name);
    }
}
