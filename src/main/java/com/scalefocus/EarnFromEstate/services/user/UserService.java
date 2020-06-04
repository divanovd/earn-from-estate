package com.scalefocus.EarnFromEstate.services.user;

import com.scalefocus.EarnFromEstate.entities.User;

public interface UserService {
    String createAccount(User user);
    User getUserByEmail(String email);
}
