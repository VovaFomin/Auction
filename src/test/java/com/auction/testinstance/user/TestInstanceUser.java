package com.auction.testinstance.user;

import com.auction.dto.user.UserDto;
import com.auction.entity.user.User;

public class TestInstanceUser {

    private static final String FIRST_NAME = "Ivan";
    private static final String LAST_NAME = "Ivanov";
    private static final String EMAIL = "ivan_ivanov1@epam.com";

    public TestInstanceUser() {
        throw new UnsupportedOperationException();
    }

    private static User getUser() {
        return User.builder()
                   .id(1L)
                   .firstName(FIRST_NAME)
                   .lastName(LAST_NAME)
                   .email(EMAIL)
                   .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
                      .id(1L)
                      .firstName(FIRST_NAME)
                      .lastName(LAST_NAME)
                      .email(EMAIL)
                      .build();
    }
}
