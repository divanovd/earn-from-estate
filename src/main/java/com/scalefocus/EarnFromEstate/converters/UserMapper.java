package com.scalefocus.EarnFromEstate.converters;

import com.scalefocus.EarnFromEstate.dtos.UserDto;
import com.scalefocus.EarnFromEstate.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "userAddress", target = "userAddress")
    User toUser(UserDto userDto);

    @Mapping(source = "userAddress", target = "userAddress")
    UserDto toUserDto(User user);
}
