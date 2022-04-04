package com.auction.mapper;

import static com.auction.testinstance.TestInstance.getLot;
import static com.auction.testinstance.TestInstance.getLotDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.auction.dto.LotDto;
import com.auction.entity.Lot;
import com.auction.mapper.lot.LotMapper;
import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class LotMapperTest {

    private static final LocalDateTime START_TIME = LocalDateTime.now().plus(Duration.ofMinutes(5));
    private static final LocalDateTime END_TIME = LocalDateTime.now().plus(Duration.ofMinutes(55));

    @Test
    void convertLotToDto_PassLot_GetCorrectDto() {
        Lot lot = getLot(START_TIME, END_TIME);
        LotDto expected = getLotDto(START_TIME, END_TIME);

        LotDto actualResult = LotMapper.instance.convert(lot);

        assertThat(actualResult).isEqualTo(expected);
    }

    @Test
    void convertDtoToLot_PassDto_GetCorrectLot() {
        LotDto lotDto = getLotDto(START_TIME, END_TIME);
        Lot expected = getLot(START_TIME, END_TIME);

        Lot actualResult = LotMapper.instance.convert(lotDto);

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expected);
    }
}
