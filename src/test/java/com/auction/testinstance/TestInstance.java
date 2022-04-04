package com.auction.testinstance;

import com.auction.datetime.DateTimeDto;
import com.auction.dto.LotDto;
import com.auction.entity.Lot;
import java.time.Duration;
import java.time.LocalDateTime;

public class TestInstance {

    private static final String LOT_NAME = "PowerBank";
    private static final String DESCRIPTION = "Extra power";
    private static final Long START_BID = 5L;
    private static final Integer MAX_BID_RATE_STEP = 20;

    private TestInstance() {
        throw new UnsupportedOperationException();
    }

    public static Lot getLot(LocalDateTime startDateTime, LocalDateTime endDateTime, Long lotId) {
        return Lot.builder()
                  .id(lotId)
                  .name(LOT_NAME)
                  .description(DESCRIPTION)
                  .startBid(START_BID)
                  .maxBidRateStep(MAX_BID_RATE_STEP)
                  .startDateTime(startDateTime)
                  .endDateTime(endDateTime)
                  .build();
    }

    public static Lot getLot(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return getLot(startDateTime, endDateTime, 1L);
    }

    public static Lot getLot(Long lotId) {
        LocalDateTime startTime = LocalDateTime.now().plus(Duration.ofSeconds(3));
        LocalDateTime endTime = startTime.plus(Duration.ofSeconds(2));

        return getLot(startTime, endTime, lotId);
    }

    public static Lot getLot() {
        return getLot(1L);
    }

    public static LotDto getLotDto(LocalDateTime startTime,
                                   LocalDateTime endTime, Long lotId) {
        return LotDto.builder()
                     .id(lotId)
                     .name(LOT_NAME)
                     .description(DESCRIPTION)
                     .startBid(START_BID)
                     .maxBidRateStep(MAX_BID_RATE_STEP)
                     .lotDateTime(new DateTimeDto(startTime, endTime))
                     .build();
    }

    public static LotDto getLotDto(LocalDateTime startTime, LocalDateTime endTime) {
        return getLotDto(startTime, endTime, 1L);
    }
}
