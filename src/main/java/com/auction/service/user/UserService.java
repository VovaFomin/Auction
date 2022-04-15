package com.auction.service.user;

import com.auction.dto.user.UserDto;
import com.auction.entity.user.User;
import com.auction.exception.UserNotFoundException;
import com.auction.mapper.user.UserMapper;
import com.auction.repository.user.UserRepository;
import com.auction.validation.user.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceProvider {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserDto createOne(UserDto userDto) {
        userValidator.validateCreate(userDto);
        User user = UserMapper.instance.convert(userDto);
        return UserMapper.instance.convert(userRepository.save(user));
    }

    public UserDto getUserById(Long lotId) {
        User user =
            userRepository.findById(lotId).orElseThrow(() -> new UserNotFoundException(lotId));
        return UserMapper.instance.convert(user);
    }
}
