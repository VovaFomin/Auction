package com.auction.controller.user;

import com.auction.dto.user.UserDto;
import com.auction.service.user.UserServiceProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "User", tags = "REST APIs related to User")
@RestController
@RequestMapping("/api/v1/Users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceProvider userService;

    @Operation(summary = "Create user")
    @ApiResponse(code = 400, message = "Invalid request body")
    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.createOne(userDto);
    }

    @Operation(summary = "Get user by Id")
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}
