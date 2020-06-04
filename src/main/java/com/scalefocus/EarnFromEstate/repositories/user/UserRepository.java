package com.scalefocus.EarnFromEstate.repositories.user;

import com.scalefocus.EarnFromEstate.entities.User;

import java.util.Optional;


public interface UserRepository {

    String registerUser(User user);

    Optional<User> getUserByEmail(String email);

    int insertUserRoleRelationship(Long userId, Long roleId);
}
