package com.auction.mapper.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.auction.dto.user.UserDto;
import com.auction.entity.user.User;
import com.auction.mapper.TestMapper;
import com.auction.testinstance.user.TestInstanceUser;
import org.junit.jupiter.api.Test;

public class UserMapperTest {

    @Test
    void convertUserDtoToUser_PassUserDto_GetCorrectUser() {
        UserDto userDto = TestInstanceUser.getUserDto();
        User expected = TestMapper.userDtoToUser(userDto);

        User actualResult = UserMapper.instance.convert(userDto);

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expected);
    }
}
