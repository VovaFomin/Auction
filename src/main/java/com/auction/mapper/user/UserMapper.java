package com.auction.mapper.user;

import com.auction.dto.user.UserDto;
import com.auction.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User convert(UserDto userDto);

    UserDto convert(User user);
}
