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
import java.util.List;
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

    @Test
    void convertListLotToListDto_PassListLot_GetCorrectListDto() {
        Lot lotOne = getLot(START_TIME, END_TIME, 1L);
        Lot lotTwo = getLot(START_TIME, END_TIME, 2L);
        List<Lot> lots = List.of(lotOne, lotTwo);

        LotDto lotDtoOne = getLotDto(START_TIME, END_TIME, 1L);
        LotDto lotDtoTwo = getLotDto(START_TIME, END_TIME, 2L);
        List<LotDto> expected = List.of(lotDtoOne, lotDtoTwo);

        List<LotDto> actualResult = LotMapper.instance.convert(lots);

        assertThat(actualResult).isEqualTo(expected);
    }
}
