package com.auction.dto;

import com.auction.datetime.DateTimeDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
public class LotDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Please provide a name for the Present")
    @Size(min = 1, max = 60, message = "The Present Name needs to be between 1 and 60 characters "
                                       + "long and contain cyrillic symbols, latin symbols and "
                                       + "numbers.")
    private String name;

    @Size(min = 1, max = 1600, message = "The Description field needs to be between 1 and 1600 "
                                         + "characters long and contain cyrillic symbols, latin "
                                         + "symbols, numbers and special symbols")
    private String description;

    @Min(value = 0, message = "Invalid Bid. The field must contain only a positive integer.")
    @Max(value = Long.MAX_VALUE)
    private Long startBid;

    @Min(value = 1, message = "Invalid Max bid rate step. The field must contain only a positive "
                              + "integer.")
    @Max(value = Integer.MAX_VALUE)
    private Integer maxBidRateStep;

    private DateTimeDto lotDateTime;
}
