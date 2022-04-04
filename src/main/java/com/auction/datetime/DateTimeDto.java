package com.auction.datetime;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateTimeDto {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
