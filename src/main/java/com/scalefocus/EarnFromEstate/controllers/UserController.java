package com.scalefocus.EarnFromEstate.controllers;

import com.scalefocus.EarnFromEstate.converters.UserMapper;
import com.scalefocus.EarnFromEstate.dtos.UserDto;
import com.scalefocus.EarnFromEstate.entities.User;
import com.scalefocus.EarnFromEstate.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
        this.userMapper = Mappers.getMapper(UserMapper.class);
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid final UserDto user) {
        log.info("Calling UserController's register() method with UserDto: {}.", user);
        final User userEntity = userMapper.toUser(user);

        return new ResponseEntity<>(userService.createAccount(userEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam final String email) {
        log.info("Calling UserController's getUserByEmail() method with email: {}.", email);
        final UserDto user = userMapper.toUserDto(userService.getUserByEmail(email));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
