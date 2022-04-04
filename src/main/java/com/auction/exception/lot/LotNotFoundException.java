package com.auction.exception.lot;

public class LotNotFoundException extends RuntimeException {

    public LotNotFoundException(Long lotId) {
        super("Lot not found " + lotId);
    }
}
