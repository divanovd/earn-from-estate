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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserAddressRepository userAddressRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository,
                           final UserAddressRepository userAddressRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userAddressRepository = userAddressRepository;
        this.passwordEncoder = passwordEncoder;
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

        final User existingUser = userRepository.getUserByEmail(user.getEmail());
        if (Objects.nonNull(existingUser)) {
            log.error("In createAccount(), user with email: {} already exists!", user.getEmail());
            throw new UserException(ExceptionMessages.USER_EXISTS_MESSAGE);
        }

        final Role role = roleRepository.getRoleByName(RolesHolder.ROLE_TENANT.toString());
        user.setRoles(Collections.singleton(role));

        //TODO: fix user enabled to boolean.
        user.setIsEnabled("True");

        userAddressRepository.saveUserAddress(user.getUserAddress());
        final UserAddress persistedUserAddress =
                userAddressRepository.getUserAddressByAllFields(user.getUserAddress());
        user.setUserAddress(persistedUserAddress);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        final String email = userRepository.registerUser(user);
        log.info("Successfully registered user: {}", user);

        final User registeredUser = userRepository.getUserByEmail(email);

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

        final UserAddress userAddress = userAddressRepository.getUserAddressById(user.getUserAddress().getId());
        user.setUserAddress(userAddress);

        final Set<Role> userRoles = roleRepository.getRolesByUserId(user.getId());
        user.setRoles(userRoles);
        return user;
    }

}
