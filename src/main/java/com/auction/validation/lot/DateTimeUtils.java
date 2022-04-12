package com.auction.validation.lot;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DateTimeUtils {

    private static final String INVALID_DATE_ORDER_MSG =
        "Invalid End Time. The End Time should be later than the Start Time.";

    public Set<String> checkThatDateTimeIsFuture(LocalDateTime dateTime, String message) {
        if (dateTime != null && LocalDateTime.now().isAfter(dateTime)) {
            return Collections.singleton(message);
        }
        return Collections.emptySet();
    }

    public Set<String> checkThatStartDateTimeAndEndDateTimeHasCorrectOrder(
        LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime != null && endDateTime != null && startDateTime.isAfter(endDateTime)) {
            return Collections.singleton(DateTimeUtils.INVALID_DATE_ORDER_MSG);
        }
        return Collections.emptySet();
    }
}