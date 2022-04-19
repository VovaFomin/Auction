package com.auction.exception;

import java.util.Set;
import lombok.Getter;

@Getter
public class UserCannotBeCreated extends RuntimeException {

    private Set<String> messages;

    public UserCannotBeCreated(Set<String> messages) {
        this.messages = messages;
    }
}
