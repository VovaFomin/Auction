package com.auction.service.lot;

import com.auction.dto.LotDto;
import com.auction.entity.Lot;
import com.auction.exception.lot.LotNotFoundException;
import com.auction.mapper.lot.LotMapper;
import com.auction.repository.lot.LotRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LotService implements LotServiceProvider {

    private final LotRepository lotRepository;

    @Override
    public LotDto createOne(LotDto lotDto) {
        Lot lot = LotMapper.instance.convert(lotDto);
        return LotMapper.instance.convert(lotRepository.save(lot));
    }

    public List<LotDto> getAllLots() {
        return LotMapper.instance.convert(lotRepository.findAll());
    }

    @Override
    @Transactional
    public void deleteLot(Long lotId) {
        lotRepository.findById(lotId)
                     .orElseThrow(() -> new LotNotFoundException(lotId));
        lotRepository.deleteById(lotId);
    }

    @Override
    public LotDto getLot(Long lotId) {
        Lot retrievedLot =
            lotRepository.findById(lotId).orElseThrow(() -> new LotNotFoundException(lotId));
        return LotMapper.instance.convert(retrievedLot);
    }
}
