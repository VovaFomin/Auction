package com.auction.service.lot;

import com.auction.dto.LotDto;
import java.util.List;

public interface LotServiceProvider {

    LotDto createOne(LotDto lotDto);

    void deleteLot(Long lotId);

    LotDto getLot(Long lotId);

    List<LotDto> getAllLots();
}
