package com.scalefocus.EarnFromEstate.repositories.user;

import com.scalefocus.EarnFromEstate.entities.User;


public interface UserRepository {

    String registerUser(User user);

    User getUserByEmail(String email);

    int insertUserRoleRelationship(Long userId, Long roleId);
}
