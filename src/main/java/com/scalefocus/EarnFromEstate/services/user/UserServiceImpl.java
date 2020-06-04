package com.scalefocus.EarnFromEstate.services.user;

import com.scalefocus.EarnFromEstate.entities.Role;
import com.scalefocus.EarnFromEstate.entities.User;
import com.scalefocus.EarnFromEstate.entities.UserAddress;
import com.scalefocus.EarnFromEstate.exceptions.UserException;
import com.scalefocus.EarnFromEstate.exceptions.messages.ExceptionMessages;
import com.scalefocus.EarnFromEstate.repositories.role.RoleRepository;
import com.scalefocus.EarnFromEstate.repositories.user.UserRepository;
import com.scalefocus.EarnFromEstate.repositories.userАddress.UserAddressRepository;
import com.scalefocus.EarnFromEstate.utils.RolesHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserAddressRepository userAddressRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository,
                           final UserAddressRepository userAddressRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userAddressRepository = userAddressRepository;
    }

    /**
     * Used for account creation.
     *
     * @param user the user to be registered.
     * @return the user's email.
     */
    @Override
    @Transactional
    public String createAccount(final User user) {
        if (!isPasswordMatching(user)) {
            log.warn("In createAccount(), passwords do not match!");
            throw new UserException(ExceptionMessages.PASSWORD_MATCHING_MESSAGE);
        }
        final User existingUser = userRepository.getUserByEmail(user.getEmail())
                .orElseThrow(() -> {
                    log.error("In createAccount(), user with email: {} already exists!", user.getEmail());
                    return new UserException(ExceptionMessages.USER_EXISTS_MESSAGE);
                });

        final Role role = roleRepository.getRoleByName(RolesHolder.ROLE_TENANT);
        user.setRoles(Collections.singleton(role));
        user.setIsEnabled("True");

        userAddressRepository.saveUserAddress(user.getUserAddress());
        final UserAddress persistedUserAddress =
                userAddressRepository.getUserAddressByAllFields(user.getUserAddress());
        user.setUserAddress(persistedUserAddress);

        final String email = userRepository.registerUser(user);
        log.info("Successfully registered user: {}", user);

        //TODO: fix the mapping of address id from the db to userAddress object and add roles.
        // or use the objects persistedUserAddress and role? Because it fails to fetch address and roles
        // because the UserRowMapper doesn't has object when i query the user.
        final User registeredUser = userRepository.getUserByEmail(email).
        registeredUser.setUserAddress(persistedUserAddress);
        userRepository.insertUserRoleRelationship(registeredUser.getId(), role.getId());
        registeredUser.setRoles(Collections.singleton(role));

        return registeredUser.getEmail();

    }

    /**
     * Gets an user if available, by an email.
     *
     * @param email users's email.
     * @return the user.
     */
    @Override
    public User getUserByEmail(final String email) {
        final User user = userRepository.getUserByEmail(email);
        if (Objects.isNull(user)) {
            log.error("In getUserByEmail(), user with such email: {}, does not exists!", email);
            throw new UserException(ExceptionMessages.USER_NOT_EXISTS_MESSAGE);
        }
        return user;
    }

    /**
     * Used to check if password fields match.
     *
     * @param user user object.
     * @return true/false
     */
    private boolean isPasswordMatching(final User user) {
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
