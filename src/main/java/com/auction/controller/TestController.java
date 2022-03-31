package com.auction.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "SwaggerTestController", tags = "REST APIs related to TestController")
@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "get 'hello' string",
            description = "just returns 'hello' string")
    @GetMapping("/hello")
    public String hello() {
        return "Hello you are really responded";
    }

    @Operation(summary = "returns string named 'string' passed in path")
    @Parameter(name = "string", in = ParameterIn.PATH)
    @GetMapping("/{string}")
    public String getStringBack(@PathVariable String string) {
        return string;
    }
}
