package com.auction.constant;

public final class Constants {
    /**
     * Load all existing lots' and auctions statuses from mongo to provide info for scheduler.
     */
    public static final int POPULATE_EXISTING_MACHINES_ORDER = 10;

    /**
     * For correct work of schedulers StateMachineService's cache needs to be populated.
     */
    public static final int SCHEDULE_EXISTING_MACHINES_ORDER =
        POPULATE_EXISTING_MACHINES_ORDER + 10;
}
