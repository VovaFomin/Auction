package com.auction.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
