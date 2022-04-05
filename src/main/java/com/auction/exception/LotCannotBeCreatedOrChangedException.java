package com.auction.exception;

import java.util.Set;
import lombok.Getter;

@Getter
public class LotCannotBeCreatedOrChangedException extends RuntimeException {

    private Set<String> messages;

    public LotCannotBeCreatedOrChangedException(Set<String> messages) {
        this.messages = messages;
    }
}
