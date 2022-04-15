package com.auction.service.user;

import com.auction.dto.user.UserDto;

public interface UserServiceProvider {

    UserDto createOne(UserDto userDto);

    UserDto getUserById(Long userId);
}
