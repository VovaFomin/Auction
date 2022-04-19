package com.auction.unit.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.auction.dto.user.UserDto;
import com.auction.entity.user.User;
import com.auction.exception.UserNotFoundException;
import com.auction.mapper.TestMapper;
import com.auction.mapper.user.UserMapper;
import com.auction.repository.user.UserRepository;
import com.auction.service.user.UserService;
import com.auction.testinstance.user.TestInstanceUser;
import com.auction.validation.user.UserValidator;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @Test
    public void creteOne_createNewUser_UserIsCreatedSuccessfully() {
        UserDto userDto = TestInstanceUser.getUserDto();
        User user = TestMapper.userDtoToUser(userDto);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto actualResult = userService.createOne(userDto);

        assertThat(actualResult).isEqualTo(UserMapper.instance.convert(user));
    }

    @Test
    public void getUserById_passCorrectId_getUserSuccessfully() {
        UserDto userDto = TestInstanceUser.getUserDto();
        User user = TestMapper.userDtoToUser(userDto);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        UserDto actualResult = userService.getUserById(1L);

        assertThat(actualResult).isEqualTo(UserMapper.instance.convert(user));
    }

    @Test
    public void getUserById_InvalidUserId_ExceptionThrown() {
        assertThrows(UserNotFoundException.class,
                     () -> userService.getUserById(null));
    }
}
