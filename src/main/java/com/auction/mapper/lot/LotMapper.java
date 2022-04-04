package com.auction.mapper.lot;

import com.auction.dto.LotDto;
import com.auction.entity.Lot;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LotMapper {

    LotMapper instance = Mappers.getMapper(LotMapper.class);

    @Mapping(source = "lotDateTime.startDateTime", target = "startDateTime")
    @Mapping(source = "lotDateTime.endDateTime", target = "endDateTime")
    Lot convert(LotDto lotDto);

    @Mapping(source = "startDateTime", target = "lotDateTime.startDateTime")
    @Mapping(source = "endDateTime", target = "lotDateTime.endDateTime")
    LotDto convert(Lot lot);

    List<LotDto> convert(List<Lot> lots);
}
