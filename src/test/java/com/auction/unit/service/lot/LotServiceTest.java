package com.auction.unit.service.lot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.auction.dto.lot.LotDto;
import com.auction.entity.lot.Lot;
import com.auction.mapper.lot.LotMapper;
import com.auction.repository.lot.LotRepository;
import com.auction.service.lot.LotService;
import com.auction.testinstance.lot.TestInstanceLot;
import com.auction.validation.lot.LotValidator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LotServiceTest {

    @InjectMocks
    private LotService lotService;

    @Mock
    private LotRepository lotRepository;

    @Mock
    private LotValidator lotValidator;

    @Test
    public void creteOne_createNewLot_LotIsCreatedSuccessfully() {
        Lot lot = TestInstanceLot.getLot(1L);
        when(lotRepository.save(any(Lot.class))).thenReturn(lot);

        LotDto actualResult = lotService.createOne(LotMapper.instance.convert(lot));

        assertThat(actualResult).isEqualTo(LotMapper.instance.convert(lot));
    }

    @Test
    public void getLot_getLotById_LotReceivedSuccessfully() {
        Lot lot = TestInstanceLot.getLot(1L);
        when(lotRepository.findById(1L)).thenReturn(Optional.of(lot));

        LotDto actualResult = lotService.getLot(1L);

        assertThat(actualResult).isEqualTo(LotMapper.instance.convert(lot));
        verify(lotRepository, times(1)).findById(1L);
    }

    @Test
    public void getAllLots_getAllLots_ReceivedAllLotsSuccessfully() {
        Lot firstLot = TestInstanceLot.getLot(1L);
        Lot secondLot = TestInstanceLot.getLot(2L);
        List<Lot> lotList = List.of(firstLot, secondLot);
        when(lotRepository.findAll()).thenReturn(lotList);

        List<LotDto> actualResults = lotService.getAllLots();

        assertThat(actualResults).isEqualTo(LotMapper.instance.convert(lotList));
    }

    @Test
    public void deleteLot_deleteLotById_LotDeleteSuccessfully() {
        Lot lot = TestInstanceLot.getLot(1L);
        when(lotRepository.findById(1L)).thenReturn(Optional.of(lot));

        lotService.deleteLot(1L);

        assertThat(0).isEqualTo(lotRepository.findAll().size());
        verify(lotRepository, times(1)).deleteById(1L);
    }
}
